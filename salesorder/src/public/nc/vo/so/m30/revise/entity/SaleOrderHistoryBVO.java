package nc.vo.so.m30.revise.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * ���۶����޶�BVO
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-8-11 ����02:03:24
 */
public class SaleOrderHistoryBVO extends SaleOrderBVO {
  /**
   * �ͻ����ϱ���
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * ���ÿͻ����ϱ���
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SaleOrderHistoryBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SaleOrderHistoryBVO.CCUSTMATERIALID);
  }

  /** ���۶�����ʷ����ID */
  public static final String CORDERHISTORYBID = "corderhistorybid";

  /** ���۶�����ʷ������ID */
  public static final String CORDERHISTORYID = "corderhistoryid";

  /**
   * 
   */
  private static final long serialVersionUID = -7548909090744819708L;

  /**
   * ����corderhistorybid��Getter����.
   * ��������:2009-06-19 08:47:30
   * 
   * @return java.lang.String
   */
  public java.lang.String getCorderhistorybid() {
    return (java.lang.String) this
        .getAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYBID);
  }

  /**
   * ����corderhistoryid��Getter����.
   * ��������:2009-06-19 08:47:30
   * 
   * @return java.lang.String
   */
  public java.lang.String getCorderhistoryid() {
    return (java.lang.String) this
        .getAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.orderhistory_b");
    return meta;
  }

  /**
   * ����corderhistorybid��Setter����.
   * ��������:2009-06-19 08:47:30
   * 
   * @param newCorderhistorybid java.lang.String
   */
  public void setCorderhistorybid(java.lang.String newCorderhistorybid) {
    this.setAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYBID,
        newCorderhistorybid);
  }

  /**
   * ����corderhistoryid��Setter����.
   * ��������:2009-06-19 08:47:30
   * 
   * @param newCorderhistoryid java.lang.String
   */
  public void setCorderhistoryid(java.lang.String newCorderhistoryid) {
    this.setAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYID,
        newCorderhistoryid);
  }
}
