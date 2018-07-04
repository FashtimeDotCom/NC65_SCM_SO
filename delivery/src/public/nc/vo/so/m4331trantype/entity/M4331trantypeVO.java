package nc.vo.so.m4331trantype.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class M4331trantypeVO extends SuperVO {
  // ֻһ�γ���
  public static final String BONCEOUTFLAG = "bonceoutflag";

  // ��������
  public static final String CTRANTYPEID = "ctrantypeid";

  // ����
  public static final String PK_GROUP = "pk_group";

  public static final String PK_TRANTYPE = "pk_trantype";

  // ʱ���
  public static final String TS = "ts";

  // �������ͱ���
  public static final String VTRANTYPECODE = "vtrantypecode";

  private static final long serialVersionUID = 1L;

  /**
   * ����Ĭ�Ϸ�ʽ����������. ��������:2010-04-08 23:28:43
   */
  public M4331trantypeVO() {
    super();
  }

  public UFBoolean getBonceoutflag() {
    return (UFBoolean) this.getAttributeValue(M4331trantypeVO.BONCEOUTFLAG);
  }

  public String getCtrantypeid() {
    return (String) this.getAttributeValue(M4331trantypeVO.CTRANTYPEID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.m4331trantype");
    return meta;
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(M4331trantypeVO.PK_GROUP);
  }

  public String getPk_trantype() {
    return (String) this.getAttributeValue(M4331trantypeVO.PK_TRANTYPE);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(M4331trantypeVO.TS);
  }

  public String getVtrantypecode() {
    return (String) this.getAttributeValue(M4331trantypeVO.VTRANTYPECODE);
  }

  public void setBonceoutflag(UFBoolean bonceoutflag) {
    this.setAttributeValue(M4331trantypeVO.BONCEOUTFLAG, bonceoutflag);
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(M4331trantypeVO.CTRANTYPEID, ctrantypeid);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(M4331trantypeVO.PK_GROUP, pk_group);
  }

  public void setPk_trantype(String pk_trantype) {
    this.setAttributeValue(M4331trantypeVO.PK_TRANTYPE, pk_trantype);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(M4331trantypeVO.TS, ts);
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(M4331trantypeVO.VTRANTYPECODE, vtrantypecode);
  }
}
