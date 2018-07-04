package nc.vo.so.m33.m4c.linkqryoutrush.entity;

import java.io.Serializable;

public class LinkQueryOutRushVO implements Serializable {

  private static final long serialVersionUID = -4703256727568926921L;

  /**
   * ���۳��ⵥ����VID
   */
  private String cmaterialvid;

  /**
   * ���۳��ⵥ��ID
   */
  private String outbid;

  /**
   * ���۳��ⵥ�к�
   */
  private String outRowNo;

  /**
   * 
   * @return ���۳��ⵥ����VID
   */
  public String getCmaterialvid() {
    return this.cmaterialvid;
  }

  /**
   * 
   * @return ���۳��ⵥ��ID
   */
  public String getOutbid() {
    return this.outbid;
  }

  /**
   * 
   * @return ���۳��ⵥ�к�
   */
  public String getOutRowNo() {
    return this.outRowNo;
  }

  /**
   * 
   * @param cmaterialvid ���۳��ⵥ����VID
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.cmaterialvid = cmaterialvid;
  }

  /**
   * 
   * @param outbid ���۳��ⵥ��ID
   */
  public void setOutbid(String outbid) {
    this.outbid = outbid;
  }

  /**
   * 
   * @param outRowNo ���۳��ⵥ�к�
   */
  public void setOutRowNo(String outRowNo) {
    this.outRowNo = outRowNo;
  }

}
