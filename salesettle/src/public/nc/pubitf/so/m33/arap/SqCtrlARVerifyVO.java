package nc.pubitf.so.m33.arap;

import nc.vo.pub.lang.UFDouble;

/**
 * ���۽��������������ӿ�ʹ�õ���vo�ṹ
 * 
 * @since 6.0
 * @version 2011-9-2 ����11:16:41
 * @author zhangcheng
 */
public class SqCtrlARVerifyVO {
  /**
   * ���۴����㵥��id
   */
  private String csalesquarebid;

  /**
   * ���۽��㵥����id�����ⵥ�����۷�Ʊ��id��
   */
  private String csquarebillbid;

  /**
   * ����������
   */
  private UFDouble npayBillmny;

  public String getCsalesquarebid() {
    return this.csalesquarebid;
  }

  public String getCsquarebillbid() {
    return this.csquarebillbid;
  }

  public UFDouble getNpayBillmny() {
    return this.npayBillmny;
  }

  public void setCsalesquarebid(String csalesquarebid) {
    this.csalesquarebid = csalesquarebid;
  }

  public void setCsquarebillbid(String csquarebillbid) {
    this.csquarebillbid = csquarebillbid;
  }

  public void setNpayBillmny(UFDouble npayBillmny) {
    this.npayBillmny = npayBillmny;
  }

}
