package nc.impl.so.ordersummary.processer;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.ordersummary.OrderSummaryViewMeta;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;
import nc.vo.so.report.reportpub.ReportContext;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.db.DBPage;
import nc.impl.so.ordersummary.temp.OrderSummaryModelTable;
import nc.impl.so.ordersummary.temp.OrderSummaryOrRowSetMap;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ��ҳ��ѯ���۶�������
 * 
 * @since 6.3
 * @version 2012-09-04 13:43:42
 * @author ������
 */
public class OrderSummaryOrProcesser implements IPage<OrderSummaryViewVO> {

  private IPage<OrderSummaryViewVO> ds;

  private final static int FETCH_SIZE = 10000;

  /**
   * ���췽��
   * 
   * @param userobj
   */
  public OrderSummaryOrProcesser(ReportUserObject userobj) {

    // 1.��ò�ѯ���۷�Ʊ��SQL
    String queryInvSql = this.getQueryOrderSql(userobj);
    // 2.��ö���ӳ��
    IRowSetMap<OrderSummaryViewVO> rowsetMap = new OrderSummaryOrRowSetMap();
    // 3.�����ʱ��
    ITableMeta table = this.createTable();
    // 4.��ҳ��ѯ���ⵥ��Ϣ
    this.ds =
        new DBPage<OrderSummaryViewVO>(queryInvSql, rowsetMap, table,
            OrderSummaryOrProcesser.FETCH_SIZE);
  }

  /**
   * ����ͳ�Ʒ���ͼ۸��ۿۣ�
   * �����ѯģ�����Ƿ�ѡ���ˡ�ͳ�Ʒ���ͼ۸��ۿۡ���
   * ���ѡ���ˡ��񡱣�����Ҫ��sql����������Ӧ��where������
   * ������Ҫ����
   * 
   */
  private void appendWhere(QuerySchemeProcessor qsp, IQueryScheme queryScheme) {
    /* 
     * ��ͳ�Ʒ���ͼ۸��ۿۡ���ʶ��<br>
     * <li>ͳ�Ʒ���ͼ۸��ۿ�     : 0 </li>
     * <li>��ͳ�Ʒ���ͼ۸��ۿ� : 1 </li>
     */
    int statistics = 0;
    ConditionVO[] conds =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);// ��ò�ѯģ���еķ�Ԫ����ѡ��������Ϣ
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.STATISTIC)) { // �жϰ��������ۿ۵�ѡ��
        statistics = new UFDouble(cond.getValue()).intValue(); // �Ƿ�ѡ���ˡ���=0��
      }
    }

    // ��ͳ�Ʒ���ͼ۸��ۿ�
    if (statistics == 1) {
      String subtablename =
          qsp.getTableAliasOfAttribute(SaleOrderBVO.class, SaleOrderBVO.PK_ORG); // ����ӱ����
      qsp.appendWhere(" and " + subtablename + "."
          + OrderSummaryViewVO.BLABORFLAG + " = 'N' "); // ������
      qsp.appendWhere(" and " + subtablename + "."
          + OrderSummaryViewVO.BDISCOUNTFLAG + " = 'N' ");// �ۿ���
    }
  }

  private String getQueryOrderSql(ReportUserObject userobj) {
    IQueryScheme queryScheme = userobj.getIQueryScheme();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    Set<String> groupkeys = userobj.getSummaryKeys();
    SqlBuilder qrysql = new SqlBuilder();
    qrysql.append("select  distinct  ");
    this.getSelect(qrysql, qsp, groupkeys);
    qsp.appendCurrentGroup();
    this.appendForm(qsp);
    this.appendWhere(qsp, userobj.getIQueryScheme());
    qrysql.append(qsp.getFinalFromWhere());
    String sql = qrysql.toString();
    return sql.replace("inner join bd_custsale", "left join bd_custsale");
  }

  private void appendForm(QuerySchemeProcessor qsp) {
    String subtablename =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.class, SaleOrderBVO.PK_ORG);
    qsp.appendFrom(" inner join so_saleorder_exe so_saleorder_exe");
    qsp.appendFrom(" on " + subtablename
        + ".csaleorderbid = so_saleorder_exe.csaleorderbid");
  }

  private void getSelect(SqlBuilder qrysql, QuerySchemeProcessor qsp,
      Set<String> groupkeys) {
    this.appendFixSelect(qrysql, qsp);
    this.appendDynaSelect(qrysql, groupkeys, qsp);
  }

  private void appendDynaSelect(SqlBuilder qrysql, Set<String> groupkeys,
      QuerySchemeProcessor qsp) {

    if (groupkeys.contains(OrderSummaryViewVO.PK_CUSTCLASS)) {
      String custtable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this
          .getTableKey(custtable, OrderSummaryViewVO.PK_CUSTCLASS));
    }
    else {
      qrysql.append(" null as " + OrderSummaryViewVO.PK_CUSTCLASS + ",");
    }

    if (groupkeys.contains(OrderSummaryViewVO.PK_CUSTSALECLASS)) {
      String str = "ccustomerid.sales.pk_custsaleclass";
      String salecltable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(salecltable,
          OrderSummaryViewVO.PK_CUSTSALECLASS));
    }
    else {
      qrysql.append(" null as " + OrderSummaryViewVO.PK_CUSTSALECLASS + ",");
    }

    if (groupkeys.contains(OrderSummaryViewVO.PK_AREACL)) {
      String salecltable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql
          .append(this.getTableKey(salecltable, OrderSummaryViewVO.PK_AREACL));
    }
    else {
      qrysql.append(" null as " + OrderSummaryViewVO.PK_AREACL + ",");
    }

    if (groupkeys.contains(OrderSummaryViewVO.PK_MARBASCLASS)) {
      String martable =
          qsp.getTableAliasOfAttribute(MaterialVO.class,
              MaterialVO.PK_MARBASCLASS);
      qrysql.append(this.getTableKey(martable,
          OrderSummaryViewVO.PK_MARBASCLASS));
    }
    else {
      qrysql.append(" null as " + OrderSummaryViewVO.PK_MARBASCLASS + ",");
    }

    if (groupkeys.contains(OrderSummaryViewVO.PK_MARSALECLASS)) {
      String str = "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass";
      String marsaletable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(marsaletable,
          OrderSummaryViewVO.PK_MARSALECLASS));
    }
    else {
      qrysql.append(" null as " + OrderSummaryViewVO.PK_MARSALECLASS + ",");
    }
    if (groupkeys.contains(OrderSummaryViewVO.CCHANNELTYPEID)) {
      String orderht = qsp.getMainTableAlias();
      qrysql.append(this
          .getTableKey(orderht, OrderSummaryViewVO.CCHANNELTYPEID));
    }
    else {
      qrysql.append(" null as " + OrderSummaryViewVO.CCHANNELTYPEID + ",");
    }
    qrysql.deleteLastChar();
  }

  private Set<String> getBodyGroupKeySet() {
    String[] bodykey = new String[] {
      OrderSummaryViewVO.CSENDSTOCKORGID, OrderSummaryViewVO.VBATCHCODE
    };
    Set<String> setbody = new HashSet<String>();
    for (String key : bodykey) {
      setbody.add(key);
    }
    return setbody;
  }

  private Set<String> getHeadGroupKeySet() {
    String[] headkey =
        new String[] {
          OrderSummaryViewVO.PK_ORG, OrderSummaryViewVO.VBILLCODE,
          OrderSummaryViewVO.CDEPTID, OrderSummaryViewVO.CEMPLOYEEID,
          OrderSummaryViewVO.CCUSTOMERID, OrderSummaryViewVO.CCHANNELTYPEID,
          OrderSummaryViewVO.CTRANTYPEID
        };
    Set<String> sethead = new HashSet<String>();
    for (String key : headkey) {
      sethead.add(key);
    }
    return sethead;
  }

  private void appendFixSelect(SqlBuilder sql, QuerySchemeProcessor qsp) {
    // ����̶��ֶ�
    String orderht = qsp.getMainTableAlias();
    String[] fixheads =
        new String[] {
          OrderSummaryViewVO.CSALEORDERID, OrderSummaryViewVO.PK_ORG,
          OrderSummaryViewVO.VBILLCODE, OrderSummaryViewVO.CDEPTID,
          OrderSummaryViewVO.CEMPLOYEEID, OrderSummaryViewVO.CCUSTOMERID,
          OrderSummaryViewVO.CTRANTYPEID
        };
    for (String key : fixheads) {
      sql.append(this.getTableKey(orderht, key));

    }

    // �ӱ�̶��ֶ�
    String orderbt =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.class,
            OrderSummaryViewVO.CSALEORDERBID);
    String[] fixbodys =
        new String[] {
          OrderSummaryViewVO.CSALEORDERBID, OrderSummaryViewVO.CSENDSTOCKORGID,
          OrderSummaryViewVO.CMATERIALID, OrderSummaryViewVO.CUNITID,
          OrderSummaryViewVO.VBATCHCODE, OrderSummaryViewVO.NNUM,
          OrderSummaryViewVO.NORIGMNY, OrderSummaryViewVO.NORIGTAXMNY,
          OrderSummaryViewVO.NTAX, OrderSummaryViewVO.NORIGDISCOUNT,
          OrderSummaryViewVO.BLARGESSFLAG, OrderSummaryViewVO.BLABORFLAG,
          OrderSummaryViewVO.BDISCOUNTFLAG, OrderSummaryViewVO.BBOUTENDFLAG,
          OrderSummaryViewVO.CORIGCURRENCYID, SaleOrderBVO.CSETTLEORGID,
          SaleOrderBVO.BBCOSTSETTLEFLAG, SaleOrderBVO.BBARSETTLEFLAG

        };
    for (String key : fixbodys) {
      if (OrderSummaryViewVO.NORIGMNY.equals(key)) {
        // ��˰���
        String origmnykey = orderbt + "." + OrderSummaryViewVO.NORIGMNY;
        String condition =
            orderbt + "." + OrderSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(OrderSummaryViewVO.NORIGMNY + ",");
      }
      else if (OrderSummaryViewVO.NORIGTAXMNY.equals(key)) {
        // ��˰�ϼ�
        String origmnykey = orderbt + "." + OrderSummaryViewVO.NORIGTAXMNY;
        String condition =
            orderbt + "." + OrderSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(OrderSummaryViewVO.NORIGTAXMNY + ",");
      }
      else if (OrderSummaryViewVO.NTAX.equals(key)) {
        // ˰��
        String origmnykey = orderbt + "." + OrderSummaryViewVO.NTAX;
        String condition =
            orderbt + "." + OrderSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(OrderSummaryViewVO.NTAX + ",");
      }
      else if (OrderSummaryViewVO.NORIGDISCOUNT.equals(key)) {
        // �ۿ۶�
        String origmnykey = orderbt + "." + OrderSummaryViewVO.NORIGDISCOUNT;
        String condition =
            orderbt + "." + OrderSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(OrderSummaryViewVO.NORIGDISCOUNT + ",");
      }
      else {
        sql.append(this.getTableKey(orderbt, key));
      }

    }
    // ִ�б��ֶ�
    String[] fixexes =
        new String[] {
          OrderSummaryViewVO.NOUTNUM, OrderSummaryViewVO.NOUTSIGNNUM,
          OrderSummaryViewVO.NNORWASTNUM, OrderSummaryViewVO.NINVOICENUM
        };
    String exet = "so_saleorder_exe";
    for (String key : fixexes) {
      sql.append(this.getTableKey(exet, key));

    }
  }

  private String getTableKey(String table, String key) {
    return table + "." + key + " " + key + " ,";
  }

  private ITableMeta createTable() {
    OrderSummaryModelTable table =
        new OrderSummaryModelTable(SOTable.TMP_SO_ORDERSUMPAGE.getName());
    OrderSummaryViewVO view = new OrderSummaryViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OrderSummaryViewMeta.SALEORDER_HKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OrderSummaryViewMeta.SALEORDER_BKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OrderSummaryViewMeta.SALEORDER_EXEKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OrderSummaryViewMeta.EXTEND_STRKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    return table;
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
      else if (key.equals("vbillcode") || key.equals("vbatchcode")) {
        Column column = new Column(table, key);
        column.setLength(40);
        column.setSqlType(Types.VARCHAR);
        column.setPrecision(0);
        column.setLabel(key);
        table.add(column);
      }
      else {
        Column column = new Column(table, key);
        column.setLength(20);
        column.setSqlType(Types.VARCHAR);
        column.setPrecision(0);
        column.setLabel(key);
        table.add(column);
      }
    }
  }

  @Override
  public int getMaxRowsInPage() {
    return this.ds.getMaxRowsInPage();
  }

  @Override
  public boolean hasNext() {
    return this.ds.hasNext();
  }

  @Override
  public OrderSummaryViewVO[] next() {
    return this.ds.next();
  }

}
