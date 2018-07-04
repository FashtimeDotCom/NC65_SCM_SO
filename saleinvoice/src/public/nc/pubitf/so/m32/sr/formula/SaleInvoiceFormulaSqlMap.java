package nc.pubitf.so.m32.sr.formula;

import java.io.Serializable;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���۷�Ʊ��������sql�ֶ�ӳ����
 * 
 * @since 6.1
 * @version 2012-11-27 15:43:23
 * @author ��ӱ�
 */
public class SaleInvoiceFormulaSqlMap implements ISRFormulaSqlMap, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 3526353467562739827L;

  private String sumkey;

  private ISRFormulaSqlPara para;

  /**
   * ������
   * 
   * @param sumkey
   * @param para
   */
  public SaleInvoiceFormulaSqlMap(String sumkey, ISRFormulaSqlPara para) {
    this.sumkey = sumkey;
    this.para = para;
  }

  @Override
  public String getFrom() {
    SqlBuilder from = new SqlBuilder();
    from.append("so_saleinvoice inner join so_saleinvoice_b ");
    from.append("on so_saleinvoice.csaleinvoiceid = so_saleinvoice_b.csaleinvoiceid");
    // ���㷽ʽ
    String[] baltype = this.para.getBaltype();
    // �۸���
    String[] priceitem = this.para.getPriceitem();
    // ��������
    String[] prcPromotTypeIDs = this.para.getCprcpromottypeid();
    if (null != priceitem && priceitem.length > 0 || null != prcPromotTypeIDs
        && prcPromotTypeIDs.length > 0) {
      from.append(" inner join so_saleorder on ");
      from.append("so_saleinvoice_b.cfirstid = so_saleorder.csaleorderid");
      from.append(" inner join so_saleorder_b on ");
      from.append("so_saleinvoice_b.cfirstbid = so_saleorder_b.csaleorderbid ");
    }
    else if (null != baltype && baltype.length > 0) {
      from.append(" inner join so_saleorder on ");
      from.append("so_saleinvoice_b.cfirstid = so_saleorder.csaleorderid");
    }
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

    // ֻ�����̬
    where.append(" and ");
    where.append("so_saleinvoice.fstatusflag", BillStatus.AUDIT);
    // ����Ʒ
    where.append(" and ");
    where.append(" isnull(so_saleinvoice_b.blargessflag, 'N') = 'N' ");

    String pk_group = AppContext.getInstance().getPkGroup();
    where.append(" and ");
    where.append(" so_saleinvoice.pk_group", pk_group);
    where.append(" and ");
    where.append(" so_saleinvoice_b.pk_group", pk_group);
    where.append(" and ");
    where.append(" so_saleinvoice.dr ", 0);
    where.append(" and ");
    where.append(" so_saleinvoice_b.dr ", 0);

  }

  private void appendParaWhere(SqlBuilder where) {
    // ������֯
    String[] saleorg = this.para.getSaleorg();
    IDExQueryBuilder iq =
        new IDExQueryBuilder(SOTable.TMP_SO_SALEORG.getName());
    where.append(iq.buildSQL("so_saleinvoice_b.csaleorgid", saleorg));
    // ��ʼ����
    String begindate = this.para.getDbegindate().toString();
    where.append(" and ");
    where.append("so_saleinvoice.dbilldate", ">=", begindate);
    where.append(" and ");
    where.append("so_saleinvoice_b.dbilldate", ">=", begindate);
    // ��������
    String enddate = this.para.getDenddate().toString();
    where.append(" and ");
    where.append("so_saleinvoice.dbilldate", "<=", enddate);
    where.append(" and ");
    where.append("so_saleinvoice_b.dbilldate", "<=", enddate);
    // ��������
    String[] ordertype = this.para.getOrdertype();
    if (null != ordertype && ordertype.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ORDERTYPE.getName());
      where.append(iq.buildSQL("so_saleinvoice_b.vfirsttrantype", ordertype));
    }
    // �����ͻ�
    String[] ordercust = this.para.getOrdercust();
    if (null != ordercust && ordercust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ORDERCUST.getName());
      where.append(iq.buildSQL("so_saleinvoice_b.cordercustid", ordercust));
    }
    // ��Ʊ�ͻ�
    String[] invoicecust = this.para.getInvoicecust();
    if (null != invoicecust && invoicecust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_INVCUST.getName());
      where.append(iq.buildSQL("so_saleinvoice.cinvoicecustid", invoicecust));
    }
    // ����
    String currency = this.para.getCcurrencyid();
    where.append(" and ");
    where.append("so_saleinvoice.corigcurrencyid", currency);
    // ���㷽ʽ
    String[] baltype = this.para.getBaltype();
    if (null != baltype && baltype.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_BALTYPE.getName());
      where.append(iq.buildSQL("so_saleorder.cbalancetypeid", baltype));
    }
    // �۸���
    String[] priceitem = this.para.getPriceitem();
    if (null != priceitem && priceitem.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_PRCITEM.getName());
      where.append(iq.buildSQL("so_saleorder_b.cpriceitemid", priceitem));
    }
    // ��������ID����
    String[] prcPromotTypeIDs = this.para.getCprcpromottypeid();
    // ��������ID���鲻Ϊ�յ�ʱ����й���
    if (null != prcPromotTypeIDs && prcPromotTypeIDs.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_PRCPROMTYPE.getName());
      where.append(iq.buildSQL("so_saleorder_b.cprcpromottypeid",
          prcPromotTypeIDs));
    }

  }

  @Override
  public String getSumKey() {
    return this.sumkey;
  }

  @Override
  public String getSaleOrgKey() {
    return "so_saleinvoice_b.csaleorgid";
  }

  @Override
  public String getSettleOrgKey() {
    return "so_saleinvoice.pk_org";
  }

  @Override
  public String getOrderCustKey() {
    return "so_saleinvoice_b.cordercustid";
  }

  @Override
  public String getInvoiceCustKey() {
    return "so_saleinvoice.cinvoicecustid";
  }

  @Override
  public String getMaterialOIDKey() {
    return "so_saleinvoice_b.cmaterialid";
  }
}
