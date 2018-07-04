package nc.vo.so.m32trantype.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class M32TranTypeVO extends SuperVO {
  // ��������
  public static final String CTRANTYPEID = "ctrantypeid";

  // �������ʱӰ���ۿۻ��ǵ���
  public static final String FADJUSTER = "fadjuster";

  // ����
  public static final String PK_GROUP = "pk_group";

  // ��Ʊ������������
  public static final String PK_TRANTYPE = "pk_trantype";

  // ʱ���
  public static final String TS = "ts";

  // �������ͱ���
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = 3470876422619810194L;

  /**
   * M32TranTypeVO �Ĺ�����
   */
  public M32TranTypeVO() {
    super();
  }

  public String getCtrantypeid() {
    return (String) this.getAttributeValue(M32TranTypeVO.CTRANTYPEID);
  }

  public Integer getFadjuster() {
    return (Integer) this.getAttributeValue(M32TranTypeVO.FADJUSTER);
  }

  /**
   * ���෽����д ����Meta��
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.m32trantype");
    return meta;
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(M32TranTypeVO.PK_GROUP);
  }

  public String getPk_trantype() {
    return (String) this.getAttributeValue(M32TranTypeVO.PK_TRANTYPE);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(M32TranTypeVO.TS);
  }

  public String getVtrantypecode() {
    return (String) this.getAttributeValue(M32TranTypeVO.VTRANTYPECODE);
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(M32TranTypeVO.CTRANTYPEID, ctrantypeid);
  }

  public void setFadjuster(Integer fadjuster) {
    this.setAttributeValue(M32TranTypeVO.FADJUSTER, fadjuster);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(M32TranTypeVO.PK_GROUP, pk_group);
  }

  public void setPk_trantype(String pk_trantype) {
    this.setAttributeValue(M32TranTypeVO.PK_TRANTYPE, pk_trantype);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(M32TranTypeVO.TS, ts);
  }

  public void setVtrantypecode(String vtrantype) {
    this.setAttributeValue(M32TranTypeVO.VTRANTYPECODE, vtrantype);
  }
}
