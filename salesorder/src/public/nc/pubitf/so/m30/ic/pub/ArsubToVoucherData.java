package nc.pubitf.so.m30.ic.pub;

import nc.vo.pub.lang.UFDouble;

/**
 * ��浥����˻�����ʱ��ͻ����õ���ƾ֤������ṩ����
 * 
 * @since 6.3
 * @version 2014-07-09 11:16:48
 * @author ����
 */
public class ArsubToVoucherData {

  /**
   * ������id
   */
  private String saleorderbid;

  /**
   * ���γ������������߱���ǩ��������
   */
  private UFDouble nnum;

  /**
   * ��ȡ���۶�����id
   * 
   * @return ���۶�����id
   */
  public String getSaleorderbid() {
    return this.saleorderbid;
  }

  /**
   * 
   * �������۶�����id
   * 
   * @param saleorderbid
   */
  public void setSaleorderbid(String saleorderbid) {
    this.saleorderbid = saleorderbid;
  }

  /**
   * 
   * ��ȡ���γ������������߱���ǩ��������
   * 
   * @return UFDouble
   * 
   */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /**
   * ���ñ��γ������������߱���ǩ��������
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

}
