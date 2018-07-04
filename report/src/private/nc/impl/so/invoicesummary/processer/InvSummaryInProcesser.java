package nc.impl.so.invoicesummary.processer;

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
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.invoicesummary.InvSummaryViewMeta;
import nc.vo.so.report.invoicesummary.InvSummaryViewVO;
import nc.vo.so.report.reportpub.ReportContext;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.db.DBPage;
import nc.impl.so.invoicesummary.temp.InvSummaryInRowSetMap;
import nc.impl.so.invoicesummary.temp.InvSummaryModelTable;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ��ҳ��ѯ���۷�Ʊ����
 * 
 * @since 6.3
 * @version 2012-09-04 13:43:42
 * @author ������
 */
public class InvSummaryInProcesser implements IPage<InvSummaryViewVO> {

  private IPage<InvSummaryViewVO> ds;

  private final static int FETCH_SIZE = 10000;

  /**
   * ���췽��
   * 
   * @param userobj
   */
  public InvSummaryInProcesser(ReportUserObject userobj) {
    // 1.��ò�ѯ���۷�Ʊ��SQL
    String queryInvSql = this.getQueryInvSql(userobj);
    // 2.��ö���ӳ��
    IRowSetMap<InvSummaryViewVO> rowsetMap = new InvSummaryInRowSetMap();
    // 3.�����ʱ��
    ITableMeta table = this.createTable();
    // 4.��ҳ��ѯ���ⵥ��Ϣ
    this.ds =
        new DBPage<InvSummaryViewVO>(queryInvSql, rowsetMap, table,
            InvSummaryInProcesser.FETCH_SIZE);
  }

  private ITableMeta createTable() {
    InvSummaryModelTable table =
        new InvSummaryModelTable(SOTable.TMP_SO_INVSUMPAGE.getName());
    InvSummaryViewVO view = new InvSummaryViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : InvSummaryViewMeta.SALEINV_HKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : InvSummaryViewMeta.SALEINV_BKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : InvSummaryViewMeta.EXTEND_STRKEYS) {
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

  private String getQueryInvSql(ReportUserObject userobj) {
    IQueryScheme queryScheme = userobj.getIQueryScheme();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    Set<String> groupkeys = userobj.getSummaryKeys();
    SqlBuilder qrysql = new SqlBuilder();
    qrysql.append("select  distinct  ");
    this.getSelect(qrysql, qsp, groupkeys);
    qsp.appendCurrentGroup();
    this.appendWhere(qsp, userobj.getIQueryScheme());
    qrysql.append(qsp.getFinalFromWhere());
    String sql = qrysql.toString();
    return sql.replace("inner join bd_custsale", "left join bd_custsale");
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
          qsp.getTableAliasOfAttribute(SaleInvoiceBVO.class,
              SaleInvoiceBVO.PK_ORG); // ����ӱ����
      qsp.appendWhere(" and " + subtablename + "."
          + InvSummaryViewVO.BLABORFLAG + " = 'N' "); // ������
      qsp.appendWhere(" and " + subtablename + "."
          + InvSummaryViewVO.BDISCOUNTFLAG + " = 'N' ");// �ۿ���
    }
  }

  private void getSelect(SqlBuilder qrysql, QuerySchemeProcessor qsp,
      Set<String> groupkeys) {
    this.appendFixSelect(qrysql, qsp);
    this.appendDynaSelect(qrysql, groupkeys, qsp);
  }

  private void appendDynaSelect(SqlBuilder qrysql, Set<String> groupkeys,
      QuerySchemeProcessor qsp) {

    if (groupkeys.contains(InvSummaryViewVO.PK_CUSTCLASS)) {
      String custtable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this.getTableKey(custtable, InvSummaryViewVO.PK_CUSTCLASS));
    }
    else {
      qrysql.append(" null as " + InvSummaryViewVO.PK_CUSTCLASS + ",");
    }

    if (groupkeys.contains(InvSummaryViewVO.PK_CUSTSALECLASS)) {
      String str = "csaleinvoicebid.cordercustid.sales.pk_custsaleclass";
      String salecltable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(salecltable,
          InvSummaryViewVO.PK_CUSTSALECLASS));
    }
    else {
      qrysql.append(" null as " + InvSummaryViewVO.PK_CUSTSALECLASS + ",");
    }

    if (groupkeys.contains(InvSummaryViewVO.PK_AREACL)) {
      String salecltable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this.getTableKey(salecltable, InvSummaryViewVO.PK_AREACL));
    }
    else {
      qrysql.append(" null as " + InvSummaryViewVO.PK_AREACL + ",");
    }

    if (groupkeys.contains(InvSummaryViewVO.PK_MARBASCLASS)) {
      String martable =
          qsp.getTableAliasOfAttribute(MaterialVO.class,
              MaterialVO.PK_MARBASCLASS);
      qrysql
          .append(this.getTableKey(martable, InvSummaryViewVO.PK_MARBASCLASS));
    }
    else {
      qrysql.append(" null as " + InvSummaryViewVO.PK_MARBASCLASS + ",");
    }

    if (groupkeys.contains(InvSummaryViewVO.PK_MARSALECLASS)) {
      String str = "csaleinvoicebid.cmaterialvid.materialsale.pk_marsaleclass";
      String marsaletable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(marsaletable,
          InvSummaryViewVO.PK_MARSALECLASS));
    }
    else {
      qrysql.append(" null as " + InvSummaryViewVO.PK_MARSALECLASS + ",");
    }
    if (groupkeys.contains(InvSummaryViewVO.CCHANNELTYPEID)) {
      String invbt =
          qsp.getTableAliasOfAttribute(SaleInvoiceBVO.class,
              InvSummaryViewVO.CSALEINVOICEBID);
      qrysql.append(this.getTableKey(invbt, InvSummaryViewVO.CCHANNELTYPEID));
    }
    else {
      qrysql.append(" null as " + InvSummaryViewVO.CCHANNELTYPEID + ",");
    }
    qrysql.deleteLastChar();

  }

  private Set<String> getBodyGroupKeySet() {
    String[] bodykey =
        new String[] {
          InvSummaryViewVO.CORDERCUSTID, InvSummaryViewVO.CCHANNELTYPEID,
          InvSummaryViewVO.CSALEORGID, InvSummaryViewVO.CDEPTID,
          InvSummaryViewVO.CEMPLOYEEID, InvSummaryViewVO.CSENDSTOCKORGID,
          InvSummaryViewVO.VBATCHCODE, InvSummaryViewVO.BLARGESSFLAG,
          InvSummaryViewVO.VFIRSTTRANTYPE
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
          InvSummaryViewVO.PK_ORG, InvSummaryViewVO.CINVOICECUSTID,
          InvSummaryViewVO.CTRANTYPEID
        };
    Set<String> sethead = new HashSet<String>();
    for (String key : headkey) {
      sethead.add(key);
    }
    return sethead;
  }

  private void appendFixSelect(SqlBuilder sql, QuerySchemeProcessor qsp) {
    // ����̶��ֶ�
    String invht = qsp.getMainTableAlias();
    String[] fixheads =
        new String[] {
          InvSummaryViewVO.CSALEINVOICEID, InvSummaryViewVO.PK_ORG,
          InvSummaryViewVO.DBILLDATE, InvSummaryViewVO.CINVOICECUSTID,
          InvSummaryViewVO.CORIGCURRENCYID, InvSummaryViewVO.CTRANTYPEID
        };
    for (String key : fixheads) {
      sql.append(this.getTableKey(invht, key));

    }

    // �ӱ�̶��ֶ�
    String invbt =
        qsp.getTableAliasOfAttribute(SaleInvoiceBVO.class,
            InvSummaryViewVO.CSALEINVOICEBID);
    String[] fixbodys =
        new String[] {
          InvSummaryViewVO.CSALEINVOICEBID, InvSummaryViewVO.CORDERCUSTID,
          InvSummaryViewVO.CSALEORGID, InvSummaryViewVO.CDEPTID,
          InvSummaryViewVO.CEMPLOYEEID, InvSummaryViewVO.CSENDSTOCKORGID,
          InvSummaryViewVO.CMATERIALID, InvSummaryViewVO.CUNITID,
          InvSummaryViewVO.VBATCHCODE, InvSummaryViewVO.VFIRSTTRANTYPE,
          InvSummaryViewVO.NNUM, InvSummaryViewVO.BLARGESSFLAG,
          InvSummaryViewVO.NORIGMNY, InvSummaryViewVO.NORIGTAXMNY,
          InvSummaryViewVO.NTAX, InvSummaryViewVO.NORIGDISCOUNT,
          InvSummaryViewVO.BLABORFLAG, InvSummaryViewVO.BDISCOUNTFLAG

        };
    for (String key : fixbodys) {
      if (InvSummaryViewVO.NORIGMNY.equals(key)) {
        // ��˰���
        String origmnykey = invbt + "." + InvSummaryViewVO.NORIGMNY;
        String condition =
            invbt + "." + InvSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(InvSummaryViewVO.NORIGMNY + ",");
      }
      else if (InvSummaryViewVO.NORIGTAXMNY.equals(key)) {
        // ��˰�ϼ�
        String origmnykey = invbt + "." + InvSummaryViewVO.NORIGTAXMNY;
        String condition =
            invbt + "." + InvSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(InvSummaryViewVO.NORIGTAXMNY + ",");
      }
      else if (InvSummaryViewVO.NTAX.equals(key)) {
        // ˰��
        String origmnykey = invbt + "." + InvSummaryViewVO.NTAX;
        String condition =
            invbt + "." + InvSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(InvSummaryViewVO.NTAX + ",");
      }
      else if (InvSummaryViewVO.NORIGDISCOUNT.equals(key)) {
        // �ۿ۶�
        String origmnykey = invbt + "." + InvSummaryViewVO.NORIGDISCOUNT;
        String condition =
            invbt + "." + InvSummaryViewVO.BLARGESSFLAG + "= 'Y' ";
        sql.appendCaseWhen(condition, "0", origmnykey);
        sql.append(InvSummaryViewVO.NORIGDISCOUNT + ",");
      }
      else {
        sql.append(this.getTableKey(invbt, key));
      }
    }
  }

  private String getTableKey(String table, String key) {
    return table + "." + key + " " + key + " ,";
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
  public InvSummaryViewVO[] next() {
    return this.ds.next();
  }

}
