package nc.vo.so.m30.paravo;

import java.io.Serializable;

/**
 * ���ⵥ����Ʊ ��Ҫ�Ӷ����ϲ��������
 * 
 * @since 6.0
 * @version 2011-10-11 ����02:47:06
 * @author ô��
 */
public class Info30For32Para implements Serializable {
  private static final long serialVersionUID = -7534005225836085119L;

  /** �������� */
  private String cchanneltypeid;

  /** ��ͬ�������� */
  private String cctmanagebid;

  /** ��ͬ��ͷID */
  private String cctmanageid;

  /** ���������˻� */
  private String ccustbankaccid;

  /** �������� */
  private String ccustbankid;

  /** ɢ��ID */
  private String cfreecustid;

  /** �տ�Э�� */
  private String cpaytermid;

  public String getCchanneltypeid() {
    return this.cchanneltypeid;
  }

  public String getCctmanagebid() {
    return this.cctmanagebid;
  }

  public String getCctmanageid() {
    return this.cctmanageid;
  }

  public String getCcustbankaccid() {
    return this.ccustbankaccid;
  }

  public String getCcustbankid() {
    return this.ccustbankid;
  }

  public String getCfreecustid() {
    return this.cfreecustid;
  }

  public String getCpaytermid() {
    return this.cpaytermid;
  }

  public void setCchanneltypeid(String cchanneltypeid) {
    this.cchanneltypeid = cchanneltypeid;
  }

  public void setCctmanagebid(String cctmanagebid) {
    this.cctmanagebid = cctmanagebid;
  }

  public void setCctmanageid(String cctmanageid) {
    this.cctmanageid = cctmanageid;
  }

  public void setCcustbankaccid(String ccustbankaccid) {
    this.ccustbankaccid = ccustbankaccid;
  }

  public void setCcustbankid(String ccustbankid) {
    this.ccustbankid = ccustbankid;
  }

  public void setCfreecustid(String cfreecustid) {
    this.cfreecustid = cfreecustid;
  }

  public void setCpaytermid(String cpaytermid) {
    this.cpaytermid = cpaytermid;
  }

}
