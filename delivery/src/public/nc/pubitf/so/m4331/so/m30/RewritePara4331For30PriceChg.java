package nc.pubitf.so.m4331.so.m30;

import nc.vo.pub.lang.UFDouble;

/**
 * ���۶����۸��޶���д�������۸�
 * 
 * @since 6.0
 * @version 2011-3-25 ����11:42:06
 * @author ף����
 */
public class RewritePara4331For30PriceChg {

  /**
   * �仯���ֶ�key
   */
  private String key;

  /** ���۶�������id */
  private String srcBid;

  /** �仯��ļ۸� */
  private UFDouble price;

  /**
   * 
   * @param cdeliverybid
   * @param outnum
   * @param bclosed
   */
  public RewritePara4331For30PriceChg(String srcBid, UFDouble price, String key) {

    this.srcBid = srcBid;
    this.price = price;
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }

  public String getSrcBid() {
    return this.srcBid;
  }

  public UFDouble getPrice() {
    return this.price;
  }
}
