package nc.vo.so.m33.pub.biz.toia;

public class ProcessToIAPara {

  // �Ƿ��ۿ�
  private boolean bdiscountflag;

  // �Ƿ�����
  private boolean blaborflag;

  // ������֯ OID
  private String finorgoid;

  // ID:����Ψһ��ʶProcessToIAPara����
  private String id;

  // ���� VID
  private String materialvid;

  // ���ⵥ��������
  private String saleOutTransType;

  // �ֿ�
  private String stordocid;

  public String getFinorgoid() {
    return this.finorgoid;
  }

  public String getId() {
    return this.id;
  }

  public String getMaterialvid() {
    return this.materialvid;
  }

  public String getSaleOutTransType() {
    return this.saleOutTransType;
  }

  public String getStordocid() {
    return this.stordocid;
  }

  public boolean isBdiscountflag() {
    return this.bdiscountflag;
  }

  public boolean isBlaborflag() {
    return this.blaborflag;
  }

  public void setBdiscountflag(boolean bdiscountflag) {
    this.bdiscountflag = bdiscountflag;
  }

  public void setBlaborflag(boolean blaborflag) {
    this.blaborflag = blaborflag;
  }

  public void setFinorgoid(String finorgoid) {
    this.finorgoid = finorgoid;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMaterialvid(String materialvid) {
    this.materialvid = materialvid;
  }

  public void setSaleOutTransType(String saleOutTransType) {
    this.saleOutTransType = saleOutTransType;
  }

  public void setStordocid(String stordocid) {
    this.stordocid = stordocid;
  }

}
