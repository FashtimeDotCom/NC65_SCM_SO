package nc.pubitf.so.m30.sr.formula;

import java.io.Serializable;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOTable;

import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * �����տ����Ϊ�����ṩ�ĺ������ȡ������ӳ��
 * 
 * @since 6.1
 * @version 2012-11-27 16:59:29
 * @author ��ӱ�
 */
public class SOBalanceFormulaSqlMap implements ISRFormulaSqlMap, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8686729709825558923L;

  private ISRFormulaSqlPara para;

  /**
   * ������
   * 
   * @param para
   */
  public SOBalanceFormulaSqlMap(ISRFormulaSqlPara para) {
    this.para = para;
  }

  @Override
  public String getFrom() {
    SqlBuilder from = new SqlBuilder();
    from.append("so_balance");
    String[] transtype = this.para.getOrdertype();
    String[] baltype = this.para.getBaltype();
    // ��������ID����
    // String[] prcPromotTypeIDs = this.para.getCprcpromottypeid();
    if (null != transtype && transtype.length > 0 || null != baltype
        && baltype.length > 0) {
      from.append(" inner join so_saleorder on ");
      from.append("so_balance.csaleorderid = so_saleorder.csaleorderid ");
    }
    // if ((prcPromotTypeIDs != null && prcPromotTypeIDs.length > 0)) {
    // from.append(" inner join so_saleorder_b on ");
    // from.append("so_balance.csaleorderid = so_saleorder_b.csaleorderid ");
    // }
    return from.toString();
  }

  @Override
  public String getWhere() {
    SqlBuilder where = new SqlBuilder();
    this.appendParaWhere(where);
    this.appendFixWhere(where);
    return where.toString();
  }

  private void appendFixWhere(SqlBuilder where) {

    String pk_group = AppContext.getInstance().getPkGroup();
    where.append(" and ");
    where.append(" so_balance.pk_group", pk_group);
    where.append(" and ");
    where.append(" so_balance.dr ", 0);

  }

  private void appendParaWhere(SqlBuilder where) {
    IDExQueryBuilder iq =
        new IDExQueryBuilder(SOTable.TMP_SO_SALEORG.getName());
    String[] saleorg = this.para.getSaleorg();
    where.append(iq.buildSQL("so_balance.pk_org", saleorg));
    // ��ʼ����
    String begindate = this.para.getDbegindate().toString();
    where.append(" and ");
    where.append("so_balance.dbilldate", ">=", begindate);

    // ��������
    String enddate = this.para.getDenddate().toString();
    where.append(" and ");
    where.append("so_balance.dbilldate", "<=", enddate);

    // ��������
    String[] ordertype = this.para.getOrdertype();
    if (null != ordertype && ordertype.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ORDERTYPE.getName());
      where.append(iq.buildSQL("so_saleorder.ctrantypeid", ordertype));
    }
    // �����ͻ�
    String[] ordercust = this.para.getOrdercust();
    if (null != ordercust && ordercust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ORDERCUST.getName());
      where.append(iq.buildSQL("so_balance.ccustomerid", ordercust));
    }
    // ��Ʊ�ͻ�
    String[] invoicecust = this.para.getInvoicecust();
    if (null != invoicecust && invoicecust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_INVCUST.getName());
      where.append(iq.buildSQL("so_balance.cinvoicecustid", invoicecust));
    }
    // ����
    String currency = this.para.getCcurrencyid();
    where.append(" and ");
    where.append("so_balance.corigcurrencyid", currency);
    // ���㷽ʽ
    String[] baltype = this.para.getBaltype();
    if (null != baltype && baltype.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_BALTYPE.getName());
      where.append(iq.buildSQL("so_saleorder.cbalancetypeid", baltype));
    }
    // // ��������ID��T��
    // String[] prcPromotTypeIDs = this.para.getCprcpromottypeid();
    // // ��������ID���鲻Ϊ�յ�ʱ����й���
    // if (null != prcPromotTypeIDs && prcPromotTypeIDs.length > 0) {
    // where.append(" and ");
    // iq = new IDExQueryBuilder(SOTable.TMP_SO_PRCPROMTYPE.getName());
    // where.append(iq.buildSQL("so_saleorder_b.cprcpromottypeid",
    // prcPromotTypeIDs));
    // }
  }

  @Override
  public String getSumKey() {
    return "so_balance.ntotalpaymny";
  }

  @Override
  public String getSaleOrgKey() {
    return "so_balance.pk_org";
  }

  @Override
  public String getSettleOrgKey() {
    return "so_balance.carorgid";
  }

  @Override
  public String getOrderCustKey() {
    return "so_balance.ccustomerid";
  }

  @Override
  public String getInvoiceCustKey() {
    return "so_balance.cinvoicecustid";
  }

  @Override
  public String getMaterialOIDKey() {
    return null;
  }
}
