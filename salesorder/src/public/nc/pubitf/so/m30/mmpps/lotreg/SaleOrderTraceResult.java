package nc.pubitf.so.m30.mmpps.lotreg;

import java.io.Serializable;

import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

import nc.pubitf.mmpub.sdmanage.IBillTraceResult;

/**
 * ���۶����ṩ��"����Ԥ��ά��"�ڵ�ĵ���׷�ݷ�����Ϣ
 * 
 * @since 6.3.1
 * @version 2013-08-20 19:47:32
 * @author ���Ʒ�
 * 
 */
public class SaleOrderTraceResult implements IBillTraceResult, Serializable {

  private static final long serialVersionUID = -4075742684603094254L;

  /**
   * ��Դ��from��
   */
  private String fromSql;

  /**
   * ��ѯ��Դ������ѯ���
   */
  private String whereSql;

  /**
   * ������Դ��from��
   * 
   * @param fromSql from������from�ؼ���
   */
  public void setFromSql(String fromSql) {
    this.fromSql = fromSql;
  }

  /**
   * ���ò�ѯ����
   * 
   * @param whereSql where������where�ؼ���
   */
  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

  @Override
  public String getFrom() {
    return this.fromSql;
  }

  @Override
  public String getWhere() {
    return this.whereSql;
  }

  @Override
  public String getSrcBid() {
    return "so_saleorder_b." + SaleOrderBVO.CSRCBID;
  }

  @Override
  public String getSrcId() {
    return "so_saleorder_b." + SaleOrderBVO.CSRCID;
  }

  @Override
  public String getSrcType() {
    return "so_saleorder_b." + SaleOrderBVO.VSRCTYPE;
  }

  @Override
  public String getBillBid() {
    return "so_saleorder_b." + SaleOrderBVO.CSALEORDERBID;
  }

  @Override
  public String getBillId() {
    return "so_saleorder." + SaleOrderHVO.CSALEORDERID;
  }

  @Override
  public String getBillType() {
    return SOBillType.Order.getCode();
  }

}
