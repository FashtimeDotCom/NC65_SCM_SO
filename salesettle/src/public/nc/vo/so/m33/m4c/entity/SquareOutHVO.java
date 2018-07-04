package nc.vo.so.m33.m4c.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.m32.entity.SquareInvBVO;

/**
 * ���۳��ⵥ��ʵ��
 * 
 * @since 6.1
 * @version 2013-03-22 15:17:23
 * @author yixl
 */
public class SquareOutHVO extends SuperVO {

  private static final long serialVersionUID = -1617457479336555778L;

  /**
   * ���۳��ⵥ�������嵥��ʵ��
   */
  public static final String ENTITYNAME = "so.SquareOutHVO";

  /**
   * ɾ����־dr
   */
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
   * ����ǩ�տ�Ʊ���˻س��ⵥ��־
   */
  public static final String BRETURNOUTSTOCK = "breturnoutstock";

  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * ���۳�����㵥��ʵ��
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
   * ���۳��ⵥ��ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ���۳��ⵥ��������ʵ��
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ���۳��ⵥ���Ա
   */
  public static final String CWHSMANAGERID = "cwhsmanagerid";

  /**
   * ���۳��ⵥ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ���۳��ⵥǩ������
   */
  public static final String DBILLSIGNDATE = "dbillsigndate";

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
   * ���۳��ⵥ���ݺ�
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * ��ͷ�Զ�����1
   */
  public static final String VDEF1 = "vdef1";

  /**
   * ��ͷ�Զ�����10
   */
  public static final String VDEF10 = "vdef10";

  /**
   * ��ͷ�Զ�����11
   */
  public static final String VDEF11 = "vdef11";

  /**
   * ��ͷ�Զ�����12
   */
  public static final String VDEF12 = "vdef12";

  /**
   * ��ͷ�Զ�����13
   */
  public static final String VDEF13 = "vdef13";

  /**
   * ��ͷ�Զ�����14
   */
  public static final String VDEF14 = "vdef14";

  /**
   * ��ͷ�Զ�����15
   */
  public static final String VDEF15 = "vdef15";

  /**
   * ��ͷ�Զ�����16
   */
  public static final String VDEF16 = "vdef16";

  /**
   * ��ͷ�Զ�����17
   */
  public static final String VDEF17 = "vdef17";

  /**
   * ��ͷ�Զ�����18
   */
  public static final String VDEF18 = "vdef18";

  /**
   * ��ͷ�Զ�����19
   */
  public static final String VDEF19 = "vdef19";

  /**
   * ��ͷ�Զ�����2
   */
  public static final String VDEF2 = "vdef2";

  /**
   * ��ͷ�Զ�����20
   */
  public static final String VDEF20 = "vdef20";

  /**
   * ��ͷ�Զ�����3
   */
  public static final String VDEF3 = "vdef3";

  /**
   * ��ͷ�Զ�����4
   */
  public static final String VDEF4 = "vdef4";

  /**
   * ��ͷ�Զ�����5
   */
  public static final String VDEF5 = "vdef5";

  /**
   * ��ͷ�Զ�����6
   */
  public static final String VDEF6 = "vdef6";

  /**
   * ��ͷ�Զ�����7
   */
  public static final String VDEF7 = "vdef7";

  /**
   * ��ͷ�Զ�����8
   */
  public static final String VDEF8 = "vdef8";

  /**
   * ��ͷ�Զ�����9
   */
  public static final String VDEF9 = "vdef9";

  /**
   * ��ע
   */
  public static final String VNOTE = "vnote";

  /**
   * ���۳��ⵥ��������
   */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /******* V61�����ֶ� *******/
  /**
   * ����ó��
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

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
    return (UFBoolean) this.getAttributeValue(SquareOutHVO.BAUTOSQUARECOST);
  }

  /**
   * �����Ƿ��Զ��ɱ�����
   * 
   * @param bautosquarecost �Ƿ��Զ��ɱ�����
   */
  public void setBautosquarecost(UFBoolean bautosquarecost) {
    this.setAttributeValue(SquareOutHVO.BAUTOSQUARECOST, bautosquarecost);
  }

  /**
   * ��ȡ�Ƿ��Զ��������
   * 
   * @return �Ƿ��Զ��������
   */
  public UFBoolean getBautosquareincome() {
    return (UFBoolean) this.getAttributeValue(SquareOutHVO.BAUTOSQUAREINCOME);
  }

  /**
   * �����Ƿ��Զ��������
   * 
   * @param bautosquareincome �Ƿ��Զ��������
   */
  public void setBautosquareincome(UFBoolean bautosquareincome) {
    this.setAttributeValue(SquareOutHVO.BAUTOSQUAREINCOME, bautosquareincome);
  }

  /**
   * ��ȡ����ǩ�տ�Ʊ���˻س��ⵥ��־
   * 
   * @return ����ǩ�տ�Ʊ���˻س��ⵥ��־
   */
  public UFBoolean getBreturnoutstock() {
    return (UFBoolean) this.getAttributeValue(SquareOutHVO.BRETURNOUTSTOCK);
  }

  /**
   * ���û���ǩ�տ�Ʊ���˻س��ⵥ��־
   * 
   * @param breturnoutstock ����ǩ�տ�Ʊ���˻س��ⵥ��־
   */
  public void setBreturnoutstock(UFBoolean breturnoutstock) {
    this.setAttributeValue(SquareOutHVO.BRETURNOUTSTOCK, breturnoutstock);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return ����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutHVO.BTRIATRADEFLAG);
  }

  /**
   * ��������ó��
   * 
   * @param btriatradeflag ����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SquareOutHVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * ��ȡҵ������
   * 
   * @return ҵ������
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(SquareOutHVO.CBIZTYPEID);
  }

  /**
   * ����ҵ������
   * 
   * @param cbiztypeid ҵ������
   */
  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(SquareOutHVO.CBIZTYPEID, cbiztypeid);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return �ջ�����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SquareOutHVO.CRECECOUNTRYID);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid �ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SquareOutHVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * ��ȡ���۳�����㵥��ʵ��
   * 
   * @return ���۳�����㵥��ʵ��
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareOutHVO.CSALESQUAREID);
  }

  /**
   * �������۳�����㵥��ʵ��
   * 
   * @param csalesquareid ���۳�����㵥��ʵ��
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareOutHVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return ��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SquareOutHVO.CSENDCOUNTRYID);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid ��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SquareOutHVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * ��ȡ�����֯���°汾
   * 
   * @return �����֯���°汾
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SquareOutHVO.CSENDSTOCKORGID);
  }

  /**
   * ���ÿ����֯���°汾
   * 
   * @param csendstockorgid �����֯���°汾
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SquareOutHVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * ��ȡ�����֯�汾
   * 
   * @return �����֯�汾
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SquareOutHVO.CSENDSTOCKORGVID);
  }

  /**
   * ���ÿ����֯�汾
   * 
   * @param csendstockorgvid �����֯�汾
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SquareOutHVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * ��ȡ�ֿ�
   * 
   * @return �ֿ�
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SquareOutHVO.CSENDSTORDOCID);
  }

  /**
   * ���òֿ�
   * 
   * @param csendstordocid �ֿ�
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SquareOutHVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * ��ȡ���۳��ⵥ��ʵ��
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareOutHVO.CSQUAREBILLID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param csquarebillid ���۳��ⵥ��ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareOutHVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ��˰����/����
   * 
   * @return ��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SquareOutHVO.CTAXCOUNTRYID);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid ��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SquareOutHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * ��ȡ���۳��ⵥ��������ʵ��
   * 
   * @return ���۳��ⵥ��������ʵ��
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SquareOutHVO.CTRANTYPEID);
  }

  /**
   * �������۳��ⵥ��������ʵ��
   * 
   * @param ctrantypeid ���۳��ⵥ��������ʵ��
   */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SquareOutHVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * ��ȡ���۳��ⵥ���Ա
   * 
   * @return ���۳��ⵥ���Ա
   */
  public String getCwhsmanagerid() {
    return (String) this.getAttributeValue(SquareOutHVO.CWHSMANAGERID);
  }

  /**
   * �������۳��ⵥ���Ա
   * 
   * @param cwhsmanagerid ���۳��ⵥ���Ա
   */
  public void setCwhsmanagerid(String cwhsmanagerid) {
    this.setAttributeValue(SquareOutHVO.CWHSMANAGERID, cwhsmanagerid);
  }

  /**
   * ��ȡ���۳��ⵥ��������
   * 
   * @return ���۳��ⵥ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareOutHVO.DBILLDATE);
  }

  /**
   * �������۳��ⵥ��������
   * 
   * @param dbilldate ���۳��ⵥ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareOutHVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ���۳��ⵥǩ������
   * 
   * @return ���۳��ⵥǩ������
   */
  public UFDate getDbillsigndate() {
    return (UFDate) this.getAttributeValue(SquareOutHVO.DBILLSIGNDATE);
  }

  /**
   * �������۳��ⵥǩ������
   * 
   * @param dbillsigndate ���۳��ⵥǩ������
   */
  public void setDbillsigndate(UFDate dbillsigndate) {
    this.setAttributeValue(SquareOutHVO.DBILLSIGNDATE, dbillsigndate);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SquareOutHVO.FBUYSELLFLAG);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag ��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SquareOutHVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareOutHVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareOutHVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareOutHVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareOutHVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡ���������֯�汾
   * 
   * @return ���������֯�汾
   */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SquareOutHVO.PK_ORG_V);
  }

  /**
   * ���ý��������֯�汾
   * 
   * @param pk_org_v ���������֯�汾
   */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SquareOutHVO.PK_ORG_V, pk_org_v);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareOutHVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareOutHVO.TS, ts);
  }

  /**
   * ��ȡ���۳��ⵥ���ݺ�
   * 
   * @return ���۳��ⵥ���ݺ�
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(SquareOutHVO.VBILLCODE);
  }

  /**
   * �������۳��ⵥ���ݺ�
   * 
   * @param vbillcode ���۳��ⵥ���ݺ�
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SquareOutHVO.VBILLCODE, vbillcode);
  }

  /**
   * ��ȡ��ͷ�Զ�����1
   * 
   * @return ��ͷ�Զ�����1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF1);
  }

  /**
   * ���ñ�ͷ�Զ�����1
   * 
   * @param vdef1 ��ͷ�Զ�����1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(SquareOutHVO.VDEF1, vdef1);
  }

  /**
   * ��ȡ��ͷ�Զ�����10
   * 
   * @return ��ͷ�Զ�����10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF10);
  }

  /**
   * ���ñ�ͷ�Զ�����10
   * 
   * @param vdef10 ��ͷ�Զ�����10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(SquareOutHVO.VDEF10, vdef10);
  }

  /**
   * ��ȡ��ͷ�Զ�����11
   * 
   * @return ��ͷ�Զ�����11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF11);
  }

  /**
   * ���ñ�ͷ�Զ�����11
   * 
   * @param vdef11 ��ͷ�Զ�����11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(SquareOutHVO.VDEF11, vdef11);
  }

  /**
   * ��ȡ��ͷ�Զ�����12
   * 
   * @return ��ͷ�Զ�����12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF12);
  }

  /**
   * ���ñ�ͷ�Զ�����12
   * 
   * @param vdef12 ��ͷ�Զ�����12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(SquareOutHVO.VDEF12, vdef12);
  }

  /**
   * ��ȡ��ͷ�Զ�����13
   * 
   * @return ��ͷ�Զ�����13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF13);
  }

  /**
   * ���ñ�ͷ�Զ�����13
   * 
   * @param vdef13 ��ͷ�Զ�����13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(SquareOutHVO.VDEF13, vdef13);
  }

  /**
   * ��ȡ��ͷ�Զ�����14
   * 
   * @return ��ͷ�Զ�����14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF14);
  }

  /**
   * ���ñ�ͷ�Զ�����14
   * 
   * @param vdef14 ��ͷ�Զ�����14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(SquareOutHVO.VDEF14, vdef14);
  }

  /**
   * ��ȡ��ͷ�Զ�����15
   * 
   * @return ��ͷ�Զ�����15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF15);
  }

  /**
   * ���ñ�ͷ�Զ�����15
   * 
   * @param vdef15 ��ͷ�Զ�����15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(SquareOutHVO.VDEF15, vdef15);
  }

  /**
   * ��ȡ��ͷ�Զ�����16
   * 
   * @return ��ͷ�Զ�����16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF16);
  }

  /**
   * ���ñ�ͷ�Զ�����16
   * 
   * @param vdef16 ��ͷ�Զ�����16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(SquareOutHVO.VDEF16, vdef16);
  }

  /**
   * ��ȡ��ͷ�Զ�����17
   * 
   * @return ��ͷ�Զ�����17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF17);
  }

  /**
   * ���ñ�ͷ�Զ�����17
   * 
   * @param vdef17 ��ͷ�Զ�����17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(SquareOutHVO.VDEF17, vdef17);
  }

  /**
   * ��ȡ��ͷ�Զ�����18
   * 
   * @return ��ͷ�Զ�����18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF18);
  }

  /**
   * ���ñ�ͷ�Զ�����18
   * 
   * @param vdef18 ��ͷ�Զ�����18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(SquareOutHVO.VDEF18, vdef18);
  }

  /**
   * ��ȡ��ͷ�Զ�����19
   * 
   * @return ��ͷ�Զ�����19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF19);
  }

  /**
   * ���ñ�ͷ�Զ�����19
   * 
   * @param vdef19 ��ͷ�Զ�����19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(SquareOutHVO.VDEF19, vdef19);
  }

  /**
   * ��ȡ��ͷ�Զ�����2
   * 
   * @return ��ͷ�Զ�����2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF2);
  }

  /**
   * ���ñ�ͷ�Զ�����2
   * 
   * @param vdef2 ��ͷ�Զ�����2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(SquareOutHVO.VDEF2, vdef2);
  }

  /**
   * ��ȡ��ͷ�Զ�����20
   * 
   * @return ��ͷ�Զ�����20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF20);
  }

  /**
   * ���ñ�ͷ�Զ�����20
   * 
   * @param vdef20 ��ͷ�Զ�����20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(SquareOutHVO.VDEF20, vdef20);
  }

  /**
   * ��ȡ��ͷ�Զ�����3
   * 
   * @return ��ͷ�Զ�����3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF3);
  }

  /**
   * ���ñ�ͷ�Զ�����3
   * 
   * @param vdef3 ��ͷ�Զ�����3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(SquareOutHVO.VDEF3, vdef3);
  }

  /**
   * ��ȡ��ͷ�Զ�����4
   * 
   * @return ��ͷ�Զ�����4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF4);
  }

  /**
   * ���ñ�ͷ�Զ�����4
   * 
   * @param vdef4 ��ͷ�Զ�����4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(SquareOutHVO.VDEF4, vdef4);
  }

  /**
   * ��ȡ��ͷ�Զ�����5
   * 
   * @return ��ͷ�Զ�����5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF5);
  }

  /**
   * ���ñ�ͷ�Զ�����5
   * 
   * @param vdef5 ��ͷ�Զ�����5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(SquareOutHVO.VDEF5, vdef5);
  }

  /**
   * ��ȡ��ͷ�Զ�����6
   * 
   * @return ��ͷ�Զ�����6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF6);
  }

  /**
   * ���ñ�ͷ�Զ�����6
   * 
   * @param vdef6 ��ͷ�Զ�����6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(SquareOutHVO.VDEF6, vdef6);
  }

  /**
   * ��ȡ��ͷ�Զ�����7
   * 
   * @return ��ͷ�Զ�����7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF7);
  }

  /**
   * ���ñ�ͷ�Զ�����7
   * 
   * @param vdef7 ��ͷ�Զ�����7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(SquareOutHVO.VDEF7, vdef7);
  }

  /**
   * ��ȡ��ͷ�Զ�����8
   * 
   * @return ��ͷ�Զ�����8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF8);
  }

  /**
   * ���ñ�ͷ�Զ�����8
   * 
   * @param vdef8 ��ͷ�Զ�����8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(SquareOutHVO.VDEF8, vdef8);
  }

  /**
   * ��ȡ��ͷ�Զ�����9
   * 
   * @return ��ͷ�Զ�����9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(SquareOutHVO.VDEF9);
  }

  /**
   * ���ñ�ͷ�Զ�����9
   * 
   * @param vdef9 ��ͷ�Զ�����9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(SquareOutHVO.VDEF9, vdef9);
  }

  /**
   * ��ȡ��ע
   * 
   * @return ��ע
   */
  public String getVnote() {
    return (String) this.getAttributeValue(SquareOutHVO.VNOTE);
  }

  /**
   * ���ñ�ע
   * 
   * @param vnote ��ע
   */
  public void setVnote(String vnote) {
    this.setAttributeValue(SquareOutHVO.VNOTE, vnote);
  }

  /**
   * ��ȡ���۳��ⵥ��������
   * 
   * @return ���۳��ⵥ��������
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SquareOutHVO.VTRANTYPECODE);
  }

  /**
   * �������۳��ⵥ��������
   * 
   * @param vtrantypecode ���۳��ⵥ��������
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SquareOutHVO.VTRANTYPECODE, vtrantypecode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareOutHVO.ENTITYNAME);
    return meta;
  }

  /**
   * ����dr
   * 
   * @param dr
   */
  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  /**
   * ���dr
   * 
   * @return Integer
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }
}
