package nc.pubitf.so.m4331.opc.mecc1;

/**
 * IQuerySendInfo.query�ӿڷ���ֵ�ṹ
 * ����������������id��������ϵ��id��������ϵ�绰��Ҫ���ջ�����
 * 
 * @since 6.0
 * @version 2011-12-28 ����02:34:00
 * @author zhangcheng
 */

public class ReturnSendInfoVO {

  /** �������ӱ�ID */
  public String cdeliverybid;

  /** ������ϵ�� */
  public String csendpersonid;

  /** Ҫ���ջ����� */
  public String dreceivedate;

  /** ������ϵ�绰 */
  public String vsendtel;

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public String getCsendpersonid() {
    return this.csendpersonid;
  }

  public String getDreceivedate() {
    return this.dreceivedate;
  }

  public String getVsendtel() {
    return this.vsendtel;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }

  public void setCsendpersonid(String csendpersonid) {
    this.csendpersonid = csendpersonid;
  }

  public void setDreceivedate(String dreceivedate) {
    this.dreceivedate = dreceivedate;
  }

  public void setVsendtel(String vsendtel) {
    this.vsendtel = vsendtel;
  }

}
