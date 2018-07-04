package nc.vo.so.m33.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * ���۷�Ʊ�������嵥��ʵ��
 * 
 * @since 6.0
 * @version 2012-1-5 ����08:52:46
 * @author fengjb
 */
public class SquareInvHVO extends SuperVO {

  private static final long serialVersionUID = -1617457479336555778L;
  
  /** begin ���㷽ʽ ������ V63 �µ��� */
  public static String CBALANCETYPEID = "cbalancetypeid";

  /**
   * 
   * ��ȡ���㷽ʽ
   * 
   * @return ���㷽ʽ
   */
  public String getCbalancetypeid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CBALANCETYPEID);
  }

  /**
   * ���ý��㷽ʽ
   * 
   * @param cbalancetypeid
   */
  public void setCbalancetypeid(String cbalancetypeid) {
    this.setAttributeValue(SaleInvoiceHVO.CBALANCETYPEID, cbalancetypeid);
  }

  /** end ���㷽ʽ ������ V63 �µ��� */

  // ���۷�Ʊ�������嵥��ʵ��·��
  public static final String ENTITYNAME = "so.SquareInvHVO";

  // ɾ����־dr
  public static final String DR = "dr";

  /**
   * �Ƿ��Զ��ɱ�����
   */
  public static final String BAUTOSQUARECOST = "bautosquarecost";

  /**
   * �Ƿ��Զ��������
   */
  public static final String BAUTOSQUAREINCOME = "bautosquareincome";

  /**
   * ����ó��(V61)
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * ���������˻�
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";

  /**
   * ��Ʊ�ͻ�
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * ��Ʊ�ո���Э��
   */
  public static final String CPAYTERMID = "cpaytermid";

  /**
   * ���۷�Ʊ���㵥��ʵ��
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * ���۷�Ʊ��ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ���۷�Ʊ��������ʵ��
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ���۷�Ʊ�������
   */
  public static final String DBILLAPPROVEDATE = "dbillapprovedate";

  /**
   * ���۷�Ʊ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * ���������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * ���������֯�汾
   */
  public static final String PK_ORG_V = "pk_org_v";

  /**
   * ʱ���
   */
  public static final String TS = "ts";

  /**
   * ���۷�Ʊ���ݺ�
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * �Զ�����1
   */
  public static final String VDEF1 = "vdef1";

  /**
   * �Զ�����10
   */
  public static final String VDEF10 = "vdef10";

  /**
   * �Զ�����11
   */
  public static final String VDEF11 = "vdef11";

  /**
   * �Զ�����12
   */
  public static final String VDEF12 = "vdef12";

  /**
   * �Զ�����13
   */
  public static final String VDEF13 = "vdef13";

  /**
   * �Զ�����14
   */
  public static final String VDEF14 = "vdef14";

  /**
   * �Զ�����15
   */
  public static final String VDEF15 = "vdef15";

  /**
   * �Զ�����16
   */
  public static final String VDEF16 = "vdef16";

  /**
   * �Զ�����17
   */
  public static final String VDEF17 = "vdef17";

  /**
   * �Զ�����18
   */
  public static final String VDEF18 = "vdef18";

  /**
   * �Զ�����19
   */
  public static final String VDEF19 = "vdef19";

  /**
   * �Զ�����2
   */
  public static final String VDEF2 = "vdef2";

  /**
   * �Զ�����20
   */
  public static final String VDEF20 = "vdef20";

  /**
   * �Զ�����3
   */
  public static final String VDEF3 = "vdef3";

  /**
   * �Զ�����4
   */
  public static final String VDEF4 = "vdef4";

  /**
   * �Զ�����5
   */
  public static final String VDEF5 = "vdef5";

  /**
   * �Զ�����6
   */
  public static final String VDEF6 = "vdef6";

  /**
   * �Զ�����7
   */
  public static final String VDEF7 = "vdef7";

  /**
   * �Զ�����8
   */
  public static final String VDEF8 = "vdef8";

  /**
   * �Զ�����9
   */
  public static final String VDEF9 = "vdef9";

  /**
   * ��ע
   */
  public static final String VNOTE = "vnote";

  /**
   * ���۷�Ʊ��������
   */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /******* V61�����ֶ� *******/
  /**
   * �ջ�����/����
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /**
   * ��������/����
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * ��˰����/����
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * ��������
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /**
   * VATע����
   */
  public static final String VVATCODE = "vvatcode";

  /**
   * �ͻ�VATע����
   */
  public static final String VCUSTVATCODE = "vcustvatcode";

  /**
   * ��ȡ�Ƿ��Զ��ɱ�����
   * 
   * @return �Ƿ��Զ��ɱ�����
   */
  public UFBoolean getBautosquarecost() {
    return (UFBoolean) this.getAttributeValue(SquareInvHVO.BAUTOSQUARECOST);
  }

  /**
   * �����Ƿ��Զ��ɱ�����
   * 
   * @param bautosquarecost �Ƿ��Զ��ɱ�����
   */
  public void setBautosquarecost(UFBoolean bautosquarecost) {
    this.setAttributeValue(SquareInvHVO.BAUTOSQUARECOST, bautosquarecost);
  }

  /**
   * ��ȡ�Ƿ��Զ��������
   * 
   * @return �Ƿ��Զ��������
   */
  public UFBoolean getBautosquareincome() {
    return (UFBoolean) this.getAttributeValue(SquareInvHVO.BAUTOSQUAREINCOME);
  }

  /**
   * �����Ƿ��Զ��������
   * 
   * @param bautosquareincome �Ƿ��Զ��������
   */
  public void setBautosquareincome(UFBoolean bautosquareincome) {
    this.setAttributeValue(SquareInvHVO.BAUTOSQUAREINCOME, bautosquareincome);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return ����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvHVO.BTRIATRADEFLAG);
  }

  /**
   * ��������ó��
   * 
   * @param btriatradeflag ����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SquareInvHVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * ��ȡҵ������
   * 
   * @return ҵ������
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(SquareInvHVO.CBIZTYPEID);
  }

  /**
   * ����ҵ������
   * 
   * @param cbiztypeid ҵ������
   */
  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(SquareInvHVO.CBIZTYPEID, cbiztypeid);
  }

  /**
   * ��ȡ���������˻�
   * 
   * @return ���������˻�
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(SquareInvHVO.CCUSTBANKACCID);
  }

  /**
   * ���ÿ��������˻�
   * 
   * @param ccustbankaccid ���������˻�
   */
  public void setCcustbankaccid(String ccustbankaccid) {
    this.setAttributeValue(SquareInvHVO.CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * ��ȡ��Ʊ�ͻ�
   * 
   * @return ��Ʊ�ͻ�
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SquareInvHVO.CINVOICECUSTID);
  }

  /**
   * ���ÿ�Ʊ�ͻ�
   * 
   * @param cinvoicecustid ��Ʊ�ͻ�
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SquareInvHVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * ��ȡ��Ʊ�ո���Э��
   * 
   * @return ��Ʊ�ո���Э��
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(SquareInvHVO.CPAYTERMID);
  }

  /**
   * ���÷�Ʊ�ո���Э��
   * 
   * @param cpaytermid ��Ʊ�ո���Э��
   */
  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SquareInvHVO.CPAYTERMID, cpaytermid);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return �ջ�����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SquareInvHVO.CRECECOUNTRYID);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid �ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SquareInvHVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * ��ȡ���۷�Ʊ���㵥��ʵ��
   * 
   * @return ���۷�Ʊ���㵥��ʵ��
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareInvHVO.CSALESQUAREID);
  }

  /**
   * �������۷�Ʊ���㵥��ʵ��
   * 
   * @param csalesquareid ���۷�Ʊ���㵥��ʵ��
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareInvHVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return ��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SquareInvHVO.CSENDCOUNTRYID);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid ��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SquareInvHVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * ��ȡ���۷�Ʊ��ʵ��
   * 
   * @return ���۷�Ʊ��ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareInvHVO.CSQUAREBILLID);
  }

  /**
   * �������۷�Ʊ��ʵ��
   * 
   * @param csquarebillid ���۷�Ʊ��ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareInvHVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ��˰����/����
   * 
   * @return ��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SquareInvHVO.CTAXCOUNTRYID);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid ��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SquareInvHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * ��ȡ���۷�Ʊ��������ʵ��
   * 
   * @return ���۷�Ʊ��������ʵ��
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SquareInvHVO.CTRANTYPEID);
  }

  /**
   * �������۷�Ʊ��������ʵ��
   * 
   * @param ctrantypeid ���۷�Ʊ��������ʵ��
   */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SquareInvHVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * ��ȡ���۷�Ʊ�������
   * 
   * @return ���۷�Ʊ�������
   */
  public UFDate getDbillapprovedate() {
    return (UFDate) this.getAttributeValue(SquareInvHVO.DBILLAPPROVEDATE);
  }

  /**
   * �������۷�Ʊ�������
   * 
   * @param dbillapprovedate ���۷�Ʊ�������
   */
  public void setDbillapprovedate(UFDate dbillapprovedate) {
    this.setAttributeValue(SquareInvHVO.DBILLAPPROVEDATE, dbillapprovedate);
  }

  /**
   * ��ȡ���۷�Ʊ��������
   * 
   * @return ���۷�Ʊ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareInvHVO.DBILLDATE);
  }

  /**
   * �������۷�Ʊ��������
   * 
   * @param dbilldate ���۷�Ʊ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareInvHVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SquareInvHVO.FBUYSELLFLAG);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag ��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SquareInvHVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareInvHVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareInvHVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareInvHVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareInvHVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡ���������֯�汾
   * 
   * @return ���������֯�汾
   */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SquareInvHVO.PK_ORG_V);
  }

  /**
   * ���ý��������֯�汾
   * 
   * @param pk_org_v ���������֯�汾
   */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SquareInvHVO.PK_ORG_V, pk_org_v);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareInvHVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareInvHVO.TS, ts);
  }

  /**
   * ��ȡ���۷�Ʊ���ݺ�
   * 
   * @return ���۷�Ʊ���ݺ�
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(SquareInvHVO.VBILLCODE);
  }

  /**
   * �������۷�Ʊ���ݺ�
   * 
   * @param vbillcode ���۷�Ʊ���ݺ�
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SquareInvHVO.VBILLCODE, vbillcode);
  }

  /**
   * ��ȡ�ͻ�VATע����
   * 
   * @return �ͻ�VATע����
   */
  public String getVcustvatcode() {
    return (String) this.getAttributeValue(SquareInvHVO.VCUSTVATCODE);
  }

  /**
   * ���ÿͻ�VATע����
   * 
   * @param vcustvatcode �ͻ�VATע����
   */
  public void setVcustvatcode(String vcustvatcode) {
    this.setAttributeValue(SquareInvHVO.VCUSTVATCODE, vcustvatcode);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return �Զ�����1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF1);
  }

  /**
   * �����Զ�����1
   * 
   * @param vdef1 �Զ�����1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(SquareInvHVO.VDEF1, vdef1);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return �Զ�����10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF10);
  }

  /**
   * �����Զ�����10
   * 
   * @param vdef10 �Զ�����10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(SquareInvHVO.VDEF10, vdef10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return �Զ�����11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF11);
  }

  /**
   * �����Զ�����11
   * 
   * @param vdef11 �Զ�����11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(SquareInvHVO.VDEF11, vdef11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return �Զ�����12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF12);
  }

  /**
   * �����Զ�����12
   * 
   * @param vdef12 �Զ�����12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(SquareInvHVO.VDEF12, vdef12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return �Զ�����13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF13);
  }

  /**
   * �����Զ�����13
   * 
   * @param vdef13 �Զ�����13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(SquareInvHVO.VDEF13, vdef13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return �Զ�����14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF14);
  }

  /**
   * �����Զ�����14
   * 
   * @param vdef14 �Զ�����14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(SquareInvHVO.VDEF14, vdef14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return �Զ�����15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF15);
  }

  /**
   * �����Զ�����15
   * 
   * @param vdef15 �Զ�����15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(SquareInvHVO.VDEF15, vdef15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return �Զ�����16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF16);
  }

  /**
   * �����Զ�����16
   * 
   * @param vdef16 �Զ�����16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(SquareInvHVO.VDEF16, vdef16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return �Զ�����17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF17);
  }

  /**
   * �����Զ�����17
   * 
   * @param vdef17 �Զ�����17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(SquareInvHVO.VDEF17, vdef17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return �Զ�����18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF18);
  }

  /**
   * �����Զ�����18
   * 
   * @param vdef18 �Զ�����18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(SquareInvHVO.VDEF18, vdef18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return �Զ�����19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF19);
  }

  /**
   * �����Զ�����19
   * 
   * @param vdef19 �Զ�����19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(SquareInvHVO.VDEF19, vdef19);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return �Զ�����2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF2);
  }

  /**
   * �����Զ�����2
   * 
   * @param vdef2 �Զ�����2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(SquareInvHVO.VDEF2, vdef2);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return �Զ�����20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF20);
  }

  /**
   * �����Զ�����20
   * 
   * @param vdef20 �Զ�����20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(SquareInvHVO.VDEF20, vdef20);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return �Զ�����3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF3);
  }

  /**
   * �����Զ�����3
   * 
   * @param vdef3 �Զ�����3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(SquareInvHVO.VDEF3, vdef3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return �Զ�����4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF4);
  }

  /**
   * �����Զ�����4
   * 
   * @param vdef4 �Զ�����4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(SquareInvHVO.VDEF4, vdef4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return �Զ�����5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF5);
  }

  /**
   * �����Զ�����5
   * 
   * @param vdef5 �Զ�����5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(SquareInvHVO.VDEF5, vdef5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return �Զ�����6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF6);
  }

  /**
   * �����Զ�����6
   * 
   * @param vdef6 �Զ�����6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(SquareInvHVO.VDEF6, vdef6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return �Զ�����7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF7);
  }

  /**
   * �����Զ�����7
   * 
   * @param vdef7 �Զ�����7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(SquareInvHVO.VDEF7, vdef7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return �Զ�����8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF8);
  }

  /**
   * �����Զ�����8
   * 
   * @param vdef8 �Զ�����8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(SquareInvHVO.VDEF8, vdef8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return �Զ�����9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF9);
  }

  /**
   * �����Զ�����9
   * 
   * @param vdef9 �Զ�����9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(SquareInvHVO.VDEF9, vdef9);
  }

  /**
   * ��ȡ��ע
   * 
   * @return ��ע
   */
  public String getVnote() {
    return (String) this.getAttributeValue(SquareInvHVO.VNOTE);
  }

  /**
   * ���ñ�ע
   * 
   * @param vnote ��ע
   */
  public void setVnote(String vnote) {
    this.setAttributeValue(SquareInvHVO.VNOTE, vnote);
  }

  /**
   * ��ȡ���۷�Ʊ��������
   * 
   * @return ���۷�Ʊ��������
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SquareInvHVO.VTRANTYPECODE);
  }

  /**
   * �������۷�Ʊ��������
   * 
   * @param vtrantypecode ���۷�Ʊ��������
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SquareInvHVO.VTRANTYPECODE, vtrantypecode);
  }

  /**
   * ��ȡVATע����
   * 
   * @return VATע����
   */
  public String getVvatcode() {
    return (String) this.getAttributeValue(SquareInvHVO.VVATCODE);
  }

  /**
   * ����VATע����
   * 
   * @param vvatcode VATע����
   */
  public void setVvatcode(String vvatcode) {
    this.setAttributeValue(SquareInvHVO.VVATCODE, vvatcode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareInvHVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvHVO.DR);
  }
}
