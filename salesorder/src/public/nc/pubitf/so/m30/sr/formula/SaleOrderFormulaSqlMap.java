package nc.pubitf.so.m30.sr.formula;

import java.io.Serializable;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���۶�����������sql�ֶ�ӳ����
 * 
 * @since 6.1
 * @version 2012-11-27 15:43:23
 * @author ��ӱ�
 */
public class SaleOrderFormulaSqlMap implements ISRFormulaSqlMap, Serializable {

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
  public SaleOrderFormulaSqlMap(String sumkey, ISRFormulaSqlPara para) {
    this.sumkey = sumkey;
    this.para = para;
  }

  @Override
  public String getFrom() {
    return "so_saleorder inner join so_saleorder_b "
        + "on so_saleorder.csaleorderid = so_saleorder_b.csaleorderid";
  }

  @Override
  public String getWhere() {
    SqlBuilder where = new SqlBuilder();
    this.appendParaWhere(where);
    this.appendFixWhere(where);
    return where.toString();
  }

  private void appendFixWhere(SqlBuilder where) {
    // ֻ����ˡ��ر�̬����
    BillStatus[] status = new BillStatus[] {
      BillStatus.AUDIT, BillStatus.CLOSED
    };
    where.append(" and ");
    where.append("so_saleorder.fstatusflag", status);

    // ����Ʒ��
    where.append(" and ");
    where.append(" isnull(so_saleorder_b.blargessflag, 'N') = 'N' ");

    String pk_group = AppContext.getInstance().getPkGroup();
    where.append(" and ");
    where.append(" so_saleorder.pk_group", pk_group);
    where.append(" and ");
    where.append(" so_saleorder_b.pk_group", pk_group);
    where.append(" and ");
    where.append(" so_saleorder.dr ", 0);
    where.append(" and ");
    where.append(" so_saleorder_b.dr ", 0);

  }

  private void appendParaWhere(SqlBuilder where) {
    IDExQueryBuilder iq =
        new IDExQueryBuilder(SOTable.TMP_SO_SALEORG.getName());
    String[] saleorg = this.para.getSaleorg();
    where.append(iq.buildSQL("so_saleorder.pk_org", saleorg));
    where.append(" and ");
    where.append(iq.buildSQL("so_saleorder_b.pk_org", saleorg));
    // ��ʼ����
    String begindate = this.para.getDbegindate().toString();
    where.append(" and ");
    where.append("so_saleorder.dbilldate", ">=", begindate);
    where.append(" and ");
    where.append("so_saleorder_b.dbilldate", ">=", begindate);
    // ��������
    String enddate = this.para.getDenddate().toString();
    where.append(" and ");
    where.append("so_saleorder.dbilldate", "<=", enddate);
    where.append(" and ");
    where.append("so_saleorder_b.dbilldate", "<=", enddate);
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
      where.append(iq.buildSQL("so_saleorder.ccustomerid", ordercust));
    }
    // ��Ʊ�ͻ�
    String[] invoicecust = this.para.getInvoicecust();
    if (null != invoicecust && invoicecust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_INVCUST.getName());
      where.append(iq.buildSQL("so_saleorder.cinvoicecustid", invoicecust));
    }
    // ����
    String currency = this.para.getCcurrencyid();
    where.append(" and ");
    where.append("so_saleorder.corigcurrencyid", currency);
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
    return "so_saleorder.pk_org";
  }

  @Override
  public String getSettleOrgKey() {
    return "so_saleorder_b.csettleorgid";
  }

  @Override
  public String getOrderCustKey() {
    return "so_saleorder.ccustomerid";
  }

  @Override
  public String getInvoiceCustKey() {
    return "so_saleorder.cinvoicecustid";
  }

  @Override
  public String getMaterialOIDKey() {
    return "so_saleorder_b.cmaterialid";
  }
}
