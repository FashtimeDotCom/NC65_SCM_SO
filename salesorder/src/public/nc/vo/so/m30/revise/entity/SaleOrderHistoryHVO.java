package nc.vo.so.m30.revise.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * ���۶����޶�HVO
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-8-11 ����02:03:24
 */
public class SaleOrderHistoryHVO extends SaleOrderHVO {

  /** ���۶�����ʷ������ID */
  public static final String CORDERHISTORYID = "corderhistoryid";

  /** �޶��������� */
  public static final String CHISTRANTYPEID = "chistrantypeid";

  /** �޶��������ͱ��� */
  public static final String VHISTRANTYPECODE = "vhistrantypecode";

  /**
   * ��ȡ�޶���������
   * 
   * @return �޶���������
   */
  public String getChistrantypeid() {
    return (String) this.getAttributeValue(SaleOrderHistoryHVO.CHISTRANTYPEID);
  }

  /**
   * �޶���������
   * 
   * @param chistrantypeid �޶���������
   */
  public void setChistrantypeid(String chistrantypeid) {
    this.setAttributeValue(SaleOrderHistoryHVO.CHISTRANTYPEID, chistrantypeid);
  }

  /**
   * ��ȡ�޶��������ͱ���
   * 
   * @return �޶��������ͱ���
   */
  public String getVhistrantypecode() {
    return (String) this
        .getAttributeValue(SaleOrderHistoryHVO.VHISTRANTYPECODE);
  }

  /**
   * �����޶��������ͱ���
   * 
   * @param vhistrantypecode �޶��������ͱ���
   */
  public void setVhistrantypecode(String vhistrantypecode) {
    this.setAttributeValue(SaleOrderHistoryHVO.VHISTRANTYPECODE,
        vhistrantypecode);
  }

  /**
   * 
   */
  private static final long serialVersionUID = -1317873021890748807L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.orderhistory");
    return meta;
  }

  /**
   * ����corderhistoryid��Getter����.
   * ��������:2009-06-19 08:47:30
   * 
   * @return java.lang.String
   */
  public java.lang.String getCorderhistoryid() {
    return (java.lang.String) this
        .getAttributeValue(SaleOrderHistoryHVO.CORDERHISTORYID);
  }

  /**
   * ����corderhistoryid��Setter����.
   * ��������:2009-06-19 08:47:30
   * 
   * @param newCorderhistoryid java.lang.String
   */
  public void setCorderhistoryid(java.lang.String newCorderhistoryid) {
    this.setAttributeValue(SaleOrderHistoryHVO.CORDERHISTORYID,
        newCorderhistoryid);
  }
}
