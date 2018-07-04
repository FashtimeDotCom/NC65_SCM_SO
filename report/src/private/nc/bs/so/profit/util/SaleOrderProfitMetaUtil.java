package nc.bs.so.profit.util;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.so.orderprofit.ProfitViewVODBPage;
import nc.impl.so.pub.summary.util.SQLCreateUtil;
import nc.pub.smart.context.SmartContext;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.paravo.ProfitContext;
import nc.vo.so.report.paravo.ProfitQryInfoParaVO;
import nc.vo.so.report.profit.OrderPorfitViewMeta;
import nc.vo.so.report.profit.OrderProfitViewVO;
import nc.vo.so.report.profit.ProfitModelTable;

/**
 * ���ݴ���12
 * 
 * @since 6.0
 * @version 2011-4-6 ����05:06:12
 * @author ô��
 */
public class SaleOrderProfitMetaUtil {

  /**
   * ���췽��
   */
  public SaleOrderProfitMetaUtil() {
    // TODO
  }

  /**
   * 
   * @param content
   * @return ff
   */
  public String getTempTablename(SmartContext content) {
    TimeLog.logStart();
    String key_iquerycondition =
        com.ufida.report.anareport.FreeReportContextKey.KEY_IQUERYCONDITION;
    com.ufida.report.anareport.base.BaseQueryCondition condition =
        (com.ufida.report.anareport.base.BaseQueryCondition) content
            .getAttribute(key_iquerycondition);

    QueryScheme scheme = (QueryScheme) condition.getUserObject();

    ConditionVO[] conds = (ConditionVO[]) scheme.get("logicalcondition");
    // ��������
    Set<String> groupset = new HashSet<String>();
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SUMMARYCONDITIONS)) {
        String groupstr = cond.getValue();
        groupstr = groupstr.replace("(", "");
        groupstr = groupstr.replace(")", "");
        groupstr = groupstr.replace("'", "");
        String[] keys = groupstr.substring(0, groupstr.length()).split(",");
        for (String key : keys) {
          groupset.add(key);
        }
      }
    }

    QuerySchemeProcessor qsp = new QuerySchemeProcessor(scheme);

    String subtablename =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.class, SaleOrderBVO.PK_ORG);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    this.appendForm(qsp, subtablename);

    // 1��ƴ�Ӳ�ѯ���۶������ݵ�sql
    SqlBuilder sql = new SqlBuilder();

    this.getSelectSQL(qsp, sql, subtablename, groupset);
    String fromwheresql = qsp.getFinalFromWhere();
    sql.append(fromwheresql);
    // 2�� ʹ�÷�ҳ��ѯ����
    IPage<OrderProfitViewVO> ds1 =
        new ProfitViewVODBPage(sql.toString().replace("inner join bd_custsale",
            "left join bd_custsale"), 5000);
    // 3.������ʱ��
    TimeLog.logStart();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    String[] fieldnames = this.getFieldNames();
    ITableMeta table = this.createTable();
    String tablename = tmptableutil.createTempTable(table);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0005")/* @res "����������Ҫ����ʱ��" */);
    SaleOrderProfitDataUtil datautil = new SaleOrderProfitDataUtil();

    while (ds1.hasNext()) {
      OrderProfitViewVO[] viewvos = ds1.next();

      // 4�����ݴ���
      datautil.processData(viewvos, content);
      // 5��ִ�в���
      tmptableutil.insertIntoTempTable(tablename, fieldnames, viewvos);

    }

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0006")/* @res "���ݼӹ����������߼�ʱ��" */);

    QueryCondition qc = qsp.getQueryCondition("pk_org");
    Object[] orgs = qc.getValues();
    // 6���������ݼӹ�ִ�����sql
    SqlBuilder sb = new SqlBuilder();
    Set<String> displaykeys =
        new ProfitQryInfoParaVO().getDisplayKeys(groupset);
    sb.append("select ");
    boolean isorgfalg = false;
    for (String key : displaykeys) {
      sb.append(key + ",");
      if ("pk_org".equals(key)) {
        isorgfalg = true;
      }
    }
    
    if (!isorgfalg) {
      sb.append("'" + orgs[0] + "' pk_org,");
    }

    getFinalSql(sb, OrderPorfitViewMeta.DOUBLEFIELDS);
    getFinalSql(sb, OrderPorfitViewMeta.DOUSELECT);

    // ��˰���� = ��˰��� ��������
    String nntaxprice =
        SQLCreateUtil.getSumDivsql(OrderProfitViewVO.NNTAXMNY,
            OrderProfitViewVO.NMAINNUM);
    sb.appendCaseWhen("sum(nmainnum)<>0", nntaxprice, "0");
    sb.append("nntaxprice,");

    // �ɱ����� = �ɱ���� ��������
    String ncostprice =
        SQLCreateUtil.getSumDivsql(OrderProfitViewVO.NTOTALCOSTMNY,
            OrderProfitViewVO.NMAINNUM);
    sb.appendCaseWhen("sum(nmainnum)<>0", ncostprice, "0");
    sb.append("ncostprice,");

    // ë���� = ��˰��� ��ë��
    String nprofitcate =
        SQLCreateUtil.getSumDivsql(OrderProfitViewVO.NPROFITMNY,
            OrderProfitViewVO.NNTAXMNY);
    sb.appendCaseWhen("sum(nntaxmny)<>0", nprofitcate, "0");
    sb.append(" nprofitcate ");
    sb.append(" from ");
    sb.append(tablename);
    sb.append(getSumGroupbySQL(displaykeys));
    return sb.toString();
  }

  public String getSumGroupbySQL(Set<String> groupkeys) {
    SqlBuilder sumsql = new SqlBuilder();
    sumsql.append(" group by  ");
    for (String sumkey : groupkeys) {
      sumsql.append(sumkey);
      sumsql.append(",");
    }
    sumsql.deleteLastChar();
    return sumsql.toString();
  }

  private void getFinalSql(SqlBuilder sb, String[] doublefields) {
    for (String field : doublefields) {
      if ("nntaxprice".equals(field) || "ncostprice".equals(field)
          || "nprofitcate".equals(field)) {
        continue;
      }
      sb.append("sum(");
      sb.append(field);
      sb.append(") ");
      sb.append(field);
      sb.append(", ");
    }
  }

  private void addKeys(String key, IDataViewMeta viewmeta, Table table) {
    IAttributeMeta attrmeta = viewmeta.getAttribute(key);
    if (null != attrmeta.getColumn()) {
      table.add(attrmeta.getColumn());
    }
    else {
      if (key.startsWith("n")) {
        Column column = new Column(table, key);
        column.setLength(28);
        column.setSqlType(Types.DECIMAL);
        column.setPrecision(8);
        column.setLabel(key);
        table.add(column);
      }
      else {
        Column column = new Column(table, key);
        column.setLength(20);
        column.setSqlType(Types.CHAR);
        column.setPrecision(0);
        column.setLabel(key);
        table.add(column);
      }

    }
  }

  /**
   * 
   * @param qsp
   * @param sql
   * @param subtablename
   * @param groupset
   */
  private void appendDynaSelect(QuerySchemeProcessor qsp, SqlBuilder sql,
      String subtablename, Set<String> groupset) {
    sql.append("select distinct ");
    String tablename =
        qsp.getTableAliasOfAttribute(SaleOrderHVO.class, SaleOrderHVO.PK_ORG);
    for (String key : OrderPorfitViewMeta.HNAMES) {
      sql.append(tablename + "." + key + " " + key + ",");
    }
    tablename = subtablename;
    for (String key : OrderPorfitViewMeta.BNAMES) {
      sql.append(tablename + "." + key + " " + key + ",");
    }
    // ���ϻ�������
    if (this.groupByMaterial(groupset)) {
      tablename =
          qsp.getTableAliasOfAttribute(MaterialVO.class,
              MaterialVO.PK_MARBASCLASS);
      for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
        sql.append(tablename + "." + key + " " + key + ",");
      }
    }
    else {
      for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
        sql.append(" null as " + key + ",");
      }
    }

    // �������۷���
    if (groupset.contains(OrderProfitViewVO.PK_MARSALECLASS)) {
      String mdstr = "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass";
      tablename = qsp.getTableAliasOfAttribute(mdstr);
      for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
        sql.append(tablename + "." + key + " " + key + ",");
      }
    }
    else {
      for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
        sql.append(" null as " + key + ",");
      }
    }

    // �ͻ���������Ϳͻ���������
    if (this.groupByCust(groupset)) {
      tablename =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      for (String key : OrderPorfitViewMeta.CUSTNAMES) {
        sql.append(tablename + "." + key + " " + key + ",");
      }
    }
    else {
      for (String key : OrderPorfitViewMeta.CUSTNAMES) {
        sql.append(" null as " + key + ",");
      }
    }

    // �ͻ����۷���
    if (groupset.contains(OrderProfitViewVO.PK_CUSTSALECLASS)) {
      tablename =
          qsp.getTableAliasOfAttribute("ccustomerid.sales.pk_custsaleclass");
      for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
        sql.append(tablename + "." + key + " " + key + ",");
      }
    }
    else {
      for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
        sql.append(" null as " + key + ",");
      }
    }

  }

  private void appendFixSelect(SqlBuilder sql, String subtablename) {
    // ����������22
    String tablename = subtablename + ".";
    sql.append(tablename + "nnum nordernnum,");
    // ������˰����23
    sql.append("(case when " + tablename + "blargessflag='N'  then "
        + tablename + "nnetprice else 0 end) as nnetprice,");
    // �ۼ��ݹ�Ӧ������24
    sql.append("isnull(so_saleorder_exe.ntotalestarnum,0) as ntotalestarnum,");
    // �ۼƻس�����25�������ˣ�
    // sql.append("(select sum(quantity_de) from ar_recitem where src_itemid="
    // + tablename + "csaleorderbid) as nquantity_de,");
    // �ۼ�ȷ��Ӧ�ս��26
    sql.append("isnull(so_saleorder_exe.ntotalarnum,0) as ntotalarnum,");
    // �ۼƳɱ�����������27
    sql.append("so_saleorder_exe.ntotalcostnum ntotalcostnum,");
    // �ۼ��ݹ�Ӧ�ս��28
    sql.append("isnull(so_saleorder_exe.ntotalestarmny,0) as ntotalestarmny,");
    // �ۼƻس���29
    // sql.append("(select sum(tax_de) from ar_recitem where src_itemid="
    // + tablename + "csaleorderbid and dr=0) as ntax_de,");
    // �ۼ�ȷ��Ӧ�ս��30
    sql.append("isnull(so_saleorder_exe.ntotalarmny,0) as ntotalarmny,");

    // ��ʱ�����е��ֶ�
    sql.append("0 as nmainnum,");
    sql.append("0 as ncostprice,");
    sql.append("0 as nntaxmny,");
    sql.append("0 as nntaxprice,");
    sql.append("0 as nprofitmny,");
    sql.append("0 as ntotalcostmny,");
    sql.append("0 as ntotalreceivnum,");
    sql.append("0 as ntotalreceivmny,");
    sql.append("0 as ntotalsettlecostmny,");
    sql.append("0 as nprofitcate");

  }

  private void appendForm(QuerySchemeProcessor qsp, String subtablename) {
    // �ͻ����ϱ�
    qsp.appendFrom(" inner join so_saleorder_exe so_saleorder_exe");
    qsp.appendFrom(" on " + subtablename
        + ".csaleorderbid = so_saleorder_exe.csaleorderbid");

    //
    // qsp.appendFrom(" on  bd_customer.pk_customer = bd_custsale.pk_customer");
    // qsp.appendFrom(" and bd_customer.pk_org=so_saleorder.pk_org");
    //
    // qsp.appendFrom(" inner join bd_material bd_material ");
    // qsp.appendFrom("  on  T1.cmaterialvid = bd_material.pk_material ");
    //
    // qsp.appendFrom(" inner join bd_materialsale bd_materialsale");
    //
    // qsp.appendFrom(" on bd_material.pk_material = bd_materialsale.pk_material");
    // qsp.appendFrom(" and so_saleorder.pk_org=bd_materialsale.pk_org");
  }

  private ITableMeta createTable() {
    ProfitModelTable table =
        new ProfitModelTable(SOTable.TMP_SO_ORDERPROFIT.getName());
    OrderProfitViewVO view = new OrderProfitViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OrderPorfitViewMeta.HNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.BNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.CUSTNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.DOUSELECT) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.DOUBLEFIELDS) {
      this.addKeys(key, viewmeta, table);
    }

    return table;

  }

  private String[] getFieldNames() {
    List<String> fieldnames = new ArrayList<String>();
    for (String key : OrderPorfitViewMeta.HNAMES) {
      fieldnames.add(key);
    }

    for (String key : OrderPorfitViewMeta.BNAMES) {
      fieldnames.add(key);
    }
    for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
      fieldnames.add(key);
    }

    for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
      fieldnames.add(key);
    }

    for (String key : OrderPorfitViewMeta.CUSTNAMES) {
      fieldnames.add(key);
    }

    for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
      fieldnames.add(key);
    }

    for (String key : OrderPorfitViewMeta.DOUSELECT) {
      fieldnames.add(key);
    }

    for (String key : OrderPorfitViewMeta.DOUBLEFIELDS) {
      fieldnames.add(key);
    }

    return fieldnames.toArray(new String[fieldnames.size()]);
  }

  private void getSelectSQL(QuerySchemeProcessor qsp, SqlBuilder sql,
      String subtablename, Set<String> groupset) {
    this.appendDynaSelect(qsp, sql, subtablename, groupset);
    this.appendFixSelect(sql, subtablename);
  }

  /**
   * �Ƿ񰴿ͻ���������Ϳͻ���������Ȼ���
   * 
   * @param groupset
   * @return
   */
  private boolean groupByCust(Set<String> groupset) {
    if (groupset.contains(OrderProfitViewVO.PK_CUSTCLASS)
        || groupset.contains(OrderProfitViewVO.PK_AREACL)) {
      return true;
    }
    return false;
  }

  /**
   * �Ƿ����ϻ����������
   * 
   * @param groupset
   * @return
   */
  private boolean groupByMaterial(Set<String> groupset) {
    if (groupset.contains(OrderProfitViewVO.PK_MARBASCLASS)) {
      return true;
    }
    return false;
  }
}
