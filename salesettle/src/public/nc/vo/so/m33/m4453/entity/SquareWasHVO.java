package nc.vo.so.m33.m4453.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SquareWasHVO extends SuperVO {

  private static final long serialVersionUID = -1617457479336555778L;

  // ;�𵥴������嵥��ʵ��·��
  public static final String ENTITYNAME = "so.SquareWasHVO";

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
   * ����ó��
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * ;����㵥��ʵ��
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * �����֯���°汾
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * �����֯�汾
   */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /**
   * �ֿ�
   */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /**
   * ;����ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ;�𵥽�������ʵ��
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ���۳��ⵥ���Ա
   */
  public static final String CWHSMANAGERID = "cwhsmanagerid";

  /**
   * ;�𵥵�������
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
   * ;�𵥵��ݺ�
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
   * ;�𵥽�������
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
   * ��ȡ�Ƿ��Զ��ɱ�����
   * 
   * @return �Ƿ��Զ��ɱ�����
   */
  public UFBoolean getBautosquarecost() {
    return (UFBoolean) this.getAttributeValue(SquareWasHVO.BAUTOSQUARECOST);
  }

  /**
   * �����Ƿ��Զ��ɱ�����
   * 
   * @param bautosquarecost �Ƿ��Զ��ɱ�����
   */
  public void setBautosquarecost(UFBoolean bautosquarecost) {
    this.setAttributeValue(SquareWasHVO.BAUTOSQUARECOST, bautosquarecost);
  }

  /**
   * ��ȡ�Ƿ��Զ��������
   * 
   * @return �Ƿ��Զ��������
   */
  public UFBoolean getBautosquareincome() {
    return (UFBoolean) this.getAttributeValue(SquareWasHVO.BAUTOSQUAREINCOME);
  }

  /**
   * �����Ƿ��Զ��������
   * 
   * @param bautosquareincome �Ƿ��Զ��������
   */
  public void setBautosquareincome(UFBoolean bautosquareincome) {
    this.setAttributeValue(SquareWasHVO.BAUTOSQUAREINCOME, bautosquareincome);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return ����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SquareWasHVO.BTRIATRADEFLAG);
  }

  /**
   * ��������ó��
   * 
   * @param btriatradeflag ����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SquareWasHVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * ��ȡҵ������
   * 
   * @return ҵ������
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(SquareWasHVO.CBIZTYPEID);
  }

  /**
   * ����ҵ������
   * 
   * @param cbiztypeid ҵ������
   */
  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(SquareWasHVO.CBIZTYPEID, cbiztypeid);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return �ջ�����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SquareWasHVO.CRECECOUNTRYID);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid �ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SquareWasHVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * ��ȡ;����㵥��ʵ��
   * 
   * @return ;����㵥��ʵ��
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSALESQUAREID);
  }

  /**
   * ����;����㵥��ʵ��
   * 
   * @param csalesquareid ;����㵥��ʵ��
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareWasHVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return ��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDCOUNTRYID);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid ��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SquareWasHVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * ��ȡ�����֯���°汾
   * 
   * @return �����֯���°汾
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDSTOCKORGID);
  }

  /**
   * ���ÿ����֯���°汾
   * 
   * @param csendstockorgid �����֯���°汾
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SquareWasHVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * ��ȡ�����֯�汾
   * 
   * @return �����֯�汾
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDSTOCKORGVID);
  }

  /**
   * ���ÿ����֯�汾
   * 
   * @param csendstockorgvid �����֯�汾
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SquareWasHVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * ��ȡ�ֿ�
   * 
   * @return �ֿ�
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDSTORDOCID);
  }

  /**
   * ���òֿ�
   * 
   * @param csendstordocid �ֿ�
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SquareWasHVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * ��ȡ;����ʵ��
   * 
   * @return ;����ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSQUAREBILLID);
  }

  /**
   * ����;����ʵ��
   * 
   * @param csquarebillid ;����ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareWasHVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ��˰����/����
   * 
   * @return ��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SquareWasHVO.CTAXCOUNTRYID);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid ��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SquareWasHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * ��ȡ;�𵥽�������ʵ��
   * 
   * @return ;�𵥽�������ʵ��
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SquareWasHVO.CTRANTYPEID);
  }

  /**
   * ����;�𵥽�������ʵ��
   * 
   * @param ctrantypeid ;�𵥽�������ʵ��
   */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SquareWasHVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * ��ȡ���۳��ⵥ���Ա
   * 
   * @return ���۳��ⵥ���Ա
   */
  public String getCwhsmanagerid() {
    return (String) this.getAttributeValue(SquareWasHVO.CWHSMANAGERID);
  }

  /**
   * �������۳��ⵥ���Ա
   * 
   * @param cwhsmanagerid ���۳��ⵥ���Ա
   */
  public void setCwhsmanagerid(String cwhsmanagerid) {
    this.setAttributeValue(SquareWasHVO.CWHSMANAGERID, cwhsmanagerid);
  }

  /**
   * ��ȡ;�𵥵�������
   * 
   * @return ;�𵥵�������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareWasHVO.DBILLDATE);
  }

  /**
   * ����;�𵥵�������
   * 
   * @param dbilldate ;�𵥵�������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareWasHVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SquareWasHVO.FBUYSELLFLAG);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag ��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SquareWasHVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareWasHVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareWasHVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareWasHVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareWasHVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡ���������֯�汾
   * 
   * @return ���������֯�汾
   */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SquareWasHVO.PK_ORG_V);
  }

  /**
   * ���ý��������֯�汾
   * 
   * @param pk_org_v ���������֯�汾
   */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SquareWasHVO.PK_ORG_V, pk_org_v);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareWasHVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareWasHVO.TS, ts);
  }

  /**
   * ��ȡ;�𵥵��ݺ�
   * 
   * @return ;�𵥵��ݺ�
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(SquareWasHVO.VBILLCODE);
  }

  /**
   * ����;�𵥵��ݺ�
   * 
   * @param vbillcode ;�𵥵��ݺ�
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SquareWasHVO.VBILLCODE, vbillcode);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return �Զ�����1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF1);
  }

  /**
   * �����Զ�����1
   * 
   * @param vdef1 �Զ�����1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(SquareWasHVO.VDEF1, vdef1);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return �Զ�����10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF10);
  }

  /**
   * �����Զ�����10
   * 
   * @param vdef10 �Զ�����10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(SquareWasHVO.VDEF10, vdef10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return �Զ�����11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF11);
  }

  /**
   * �����Զ�����11
   * 
   * @param vdef11 �Զ�����11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(SquareWasHVO.VDEF11, vdef11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return �Զ�����12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF12);
  }

  /**
   * �����Զ�����12
   * 
   * @param vdef12 �Զ�����12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(SquareWasHVO.VDEF12, vdef12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return �Զ�����13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF13);
  }

  /**
   * �����Զ�����13
   * 
   * @param vdef13 �Զ�����13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(SquareWasHVO.VDEF13, vdef13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return �Զ�����14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF14);
  }

  /**
   * �����Զ�����14
   * 
   * @param vdef14 �Զ�����14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(SquareWasHVO.VDEF14, vdef14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return �Զ�����15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF15);
  }

  /**
   * �����Զ�����15
   * 
   * @param vdef15 �Զ�����15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(SquareWasHVO.VDEF15, vdef15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return �Զ�����16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF16);
  }

  /**
   * �����Զ�����16
   * 
   * @param vdef16 �Զ�����16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(SquareWasHVO.VDEF16, vdef16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return �Զ�����17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF17);
  }

  /**
   * �����Զ�����17
   * 
   * @param vdef17 �Զ�����17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(SquareWasHVO.VDEF17, vdef17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return �Զ�����18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF18);
  }

  /**
   * �����Զ�����18
   * 
   * @param vdef18 �Զ�����18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(SquareWasHVO.VDEF18, vdef18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return �Զ�����19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF19);
  }

  /**
   * �����Զ�����19
   * 
   * @param vdef19 �Զ�����19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(SquareWasHVO.VDEF19, vdef19);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return �Զ�����2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF2);
  }

  /**
   * �����Զ�����2
   * 
   * @param vdef2 �Զ�����2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(SquareWasHVO.VDEF2, vdef2);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return �Զ�����20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF20);
  }

  /**
   * �����Զ�����20
   * 
   * @param vdef20 �Զ�����20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(SquareWasHVO.VDEF20, vdef20);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return �Զ�����3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF3);
  }

  /**
   * �����Զ�����3
   * 
   * @param vdef3 �Զ�����3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(SquareWasHVO.VDEF3, vdef3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return �Զ�����4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF4);
  }

  /**
   * �����Զ�����4
   * 
   * @param vdef4 �Զ�����4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(SquareWasHVO.VDEF4, vdef4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return �Զ�����5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF5);
  }

  /**
   * �����Զ�����5
   * 
   * @param vdef5 �Զ�����5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(SquareWasHVO.VDEF5, vdef5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return �Զ�����6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF6);
  }

  /**
   * �����Զ�����6
   * 
   * @param vdef6 �Զ�����6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(SquareWasHVO.VDEF6, vdef6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return �Զ�����7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF7);
  }

  /**
   * �����Զ�����7
   * 
   * @param vdef7 �Զ�����7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(SquareWasHVO.VDEF7, vdef7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return �Զ�����8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF8);
  }

  /**
   * �����Զ�����8
   * 
   * @param vdef8 �Զ�����8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(SquareWasHVO.VDEF8, vdef8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return �Զ�����9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF9);
  }

  /**
   * �����Զ�����9
   * 
   * @param vdef9 �Զ�����9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(SquareWasHVO.VDEF9, vdef9);
  }

  /**
   * ��ȡ��ע
   * 
   * @return ��ע
   */
  public String getVnote() {
    return (String) this.getAttributeValue(SquareWasHVO.VNOTE);
  }

  /**
   * ���ñ�ע
   * 
   * @param vnote ��ע
   */
  public void setVnote(String vnote) {
    this.setAttributeValue(SquareWasHVO.VNOTE, vnote);
  }

  /**
   * ��ȡ;�𵥽�������
   * 
   * @return ;�𵥽�������
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SquareWasHVO.VTRANTYPECODE);
  }

  /**
   * ����;�𵥽�������
   * 
   * @param vtrantypecode ;�𵥽�������
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SquareWasHVO.VTRANTYPECODE, vtrantypecode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareWasHVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareWasHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareWasHVO.DR);
  }
}
