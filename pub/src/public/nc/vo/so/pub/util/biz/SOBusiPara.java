package nc.vo.so.pub.util.biz;

import nc.vo.pub.SuperVO;

/**
 * ���۶���ҵ��������Ϣ
 * 
 * @since 6.0
 * @version 2011-7-14 ����08:45:34
 * @author zhangcheng
 */
public class SOBusiPara extends SuperVO {

  private static final long serialVersionUID = -4383773034940885503L;

  private String[] attributes = new String[] {
    this.getBusitype(), this.getM30transType()
  };

  private String busitype;

  private String m30transTypeCode;

  /**
   * 
   * @param m30transTypeCode ���۶����������ͱ���
   * @param busitype ҵ������ID
   */
  public SOBusiPara(String m30transTypeCode, String busitype) {
    this.m30transTypeCode = m30transTypeCode;
    this.busitype = busitype;
  }

  @Override
  public boolean equals(Object paravo) {
    if (paravo instanceof SOBusiPara) {
      return super.equalsContent((SOBusiPara) paravo, this.attributes);
    }
    return false;
  }

  public String getBusitype() {
    return this.busitype;
  }

  public String getM30transType() {
    return this.m30transTypeCode;
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (String attr : this.attributes) {
      if (this.getAttributeValue(attr) == null) {
        continue;
      }
      result += this.getAttributeValue(attr).hashCode();
    }
    return result;
  }

}
