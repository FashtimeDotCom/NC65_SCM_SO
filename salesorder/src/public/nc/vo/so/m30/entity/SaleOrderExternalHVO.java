package nc.vo.so.m30.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * ���۶�������VO
 * 
 * @since 6.3
 * @version 2013-5-24 ����09:55:34
 * @author dongli2
 */

public class SaleOrderExternalHVO extends SuperVO {

  /**
   * ���л�
   */
  private static final long serialVersionUID = -9194238805817916447L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder");
    return meta;
  }

  /**
   * ������
   */
  public static final String APPROVER = "approver";

  /**
   * �����˷�
   */
  public static final String BADVFEEFLAG = "badvfeeflag";

  /**
   * �������ر�
   */
  public static final String BARSETTLEFLAG = "barsettleflag";

  /**
   * ��Эͬ���ɲɹ�����
   */
  public static final String BCOOPTOPOFLAG = "bcooptopoflag";

  /**
   * �ɱ�����ر�
   */
  public static final String BCOSTSETTLEFLAG = "bcostsettleflag";

  /**
   * �Ƿ�ɢ��
   */
  public static final String BFREECUSTFLAG = "bfreecustflag";

  /**
   * �Ƶ���
   */
  public static final String BILLMAKER = "billmaker";

  /**
   * ��Ʊ�ر�
   */
  public static final String BINVOICENDFLAG = "binvoicendflag";

  /**
   * �Ƿ���
   */
  public static final String BOFFSETFLAG = "boffsetflag";

  /**
   * ����ر�
   */
  public static final String BOUTENDFLAG = "boutendflag";

  /**
   * �ɲɹ�����Эͬ����
   */
  public static final String BPOCOOPTOMEFLAG = "bpocooptomeflag";

  /**
   * �տ��޶����Ԥ��
   */
  public static final String BPRECEIVEFLAG = "bpreceiveflag";

  /**
   * �����ر�
   */
  public static final String BSENDENDFLAG = "bsendendflag";

  /**
   * ���㷽ʽ
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";

  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * ���������˻�
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";

  /**
   * ��������
   */
  public static final String CCUSTBANKID = "ccustbankid";

  /**
   * �ͻ�
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * �������°汾
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * ����
   */
  public static final String CDEPTVID = "cdeptvid";

  /**
   * ҵ��Ա
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * ɢ��
   */
  public static final String CFREECUSTID = "cfreecustid";

  /**
   * ��Ʊ�ͻ�
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * ԭ��
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * �տ�Э��
   */
  public static final String CPAYTERMID = "cpaytermid";

  /**
   * ����ʱ��
   */
  public static final String CREATIONTIME = "creationtime";

  /**
   * ������
   */
  public static final String CREATOR = "creator";

  /**
   * �޶���
   */
  public static final String CREVISERID = "creviserid";

  /**
   * ��������ID
   */
  public static final String CSALEORDERID = "csaleorderid";

  /**
   * ó�����61��
   */
  public static final String CTRADEWORDID = "ctradewordid";

  /**
   * ���䷽ʽ
   */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";

  /**
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * �������ԣ�����Ŀ�ĵ�������֯
   */
  public static final String DEST_PK_ORG = "dest_pk_org";

  /**
   * �Ƶ�����
   */
  public static final String DMAKEDATE = "dmakedate";

  /**
   * dr
   */
  public static final String DR = "dr";

  /**
   * ������״̬
   */
  public static final String FPFSTATUSFLAG = "fpfstatusflag";

  /**
   * ����״̬
   */
  public static final String FSTATUSFLAG = "fstatusflag";

  /**
   * ��ӡ����
   */
  public static final String IPRINTCOUNT = "iprintcount";

  /**
   * �޶��汾��
   */
  public static final String IVERSION = "iversion";

  /**
   * �޸�ʱ��
   */
  public static final String MODIFIEDTIME = "modifiedtime";

  /**
   * �޸���
   */
  public static final String MODIFIER = "modifier";

  /**
   * �����ۿ�
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /**
   * ʵ��Ԥ�տ���
   */
  public static final String NPRECEIVEMNY = "npreceivemny";

  /**
   * �����տ��޶�
   */
  public static final String NPRECEIVEQUOTA = "npreceivequota";

  /**
   * �����տ����
   */
  public static final String NPRECEIVERATE = "npreceiverate";

  /**
   * ʵ���տ���
   */
  public static final String NRECEIVEDMNY = "nreceivedmny";

  /**
   * �������ԣ������տ���
   */
  public static final String NTHISRECEIVEMNY = "nthisreceivemny";

  /**
   * ntotalmny
   */
  public static final String NTOTALMNY = "ntotalmny";

  /**
   * �ϼ�����
   */
  public static final String NTOTALNUM = "ntotalnum";

  /**
   * ���ϼ�(��˰�ϼ�)
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /**
   * ���ó�ֽ��
   */
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";

  /**
   * �ܼ���
   */
  public static final String NTOTALPIECE = "ntotalpiece";

  /**
   * �ϼ����
   */
  public static final String NTOTALVOLUME = "ntotalvolume";

  /**
   * �ϼ�����
   */
  public static final String NTOTALWEIGHT = "ntotalweight";

  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * ������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * ������֯�汾
   */
  public static final String PK_ORG_V = "pk_org_v";

  /**
   * ����ʱ��
   */
  public static final String TAUDITTIME = "taudittime";

  /**
   * �޶�ʱ��
   */
  public static final String TREVISETIME = "trevisetime";

  /**
   * ʱ���
   */
  public static final String TS = "ts";

  /**
   * ���ݺ�
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * �Է�������
   */
  public static final String VCOOPPOHCODE = "vcooppohcode";

  /**
   * ����֤��
   */
  public static final String VCREDITNUM = "vcreditnum";

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
   * �޶�����
   */
  public static final String VREVISEREASON = "vrevisereason";

  /**
   * �������ͱ���
   */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * ��ȡ������֯
   * 
   * @return������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(PK_ORG);
  }

  /**
   * ����������֯
   * 
   * @param pk_org������֯
   */
  public void setPk_org(final String pk_org) {
    this.setAttributeValue(PK_ORG, pk_org);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(CTRANTYPEID);
  }

  /**
   * ���ý�������
   * 
   * @param ctrantypeid��������
   */
  public void setCtrantypeid(final String ctrantypeid) {
    this.setAttributeValue(CTRANTYPEID, ctrantypeid);
  }

  /**
   * ��ȡ���ݺ�
   * 
   * @return���ݺ�
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(VBILLCODE);
  }

  /**
   * ���õ��ݺ�
   * 
   * @param vbillcode���ݺ�
   */
  public void setVbillcode(final String vbillcode) {
    this.setAttributeValue(VBILLCODE, vbillcode);
  }

  /**
   * ��ȡҵ������
   * 
   * @returnҵ������
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(CBIZTYPEID);
  }

  /**
   * ����ҵ������
   * 
   * @param cbiztypeidҵ������
   */
  public void setCbiztypeid(final String cbiztypeid) {
    this.setAttributeValue(CBIZTYPEID, cbiztypeid);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(DBILLDATE);
  }

  /**
   * ���õ�������
   * 
   * @param dbilldate��������
   */
  public void setDbilldate(final UFDate dbilldate) {
    this.setAttributeValue(DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ�ͻ�
   * 
   * @return�ͻ�
   */
  public String getCcustomerid() {
    return (String) this.getAttributeValue(CCUSTOMERID);
  }

  /**
   * ���ÿͻ�
   * 
   * @param ccustomerid�ͻ�
   */
  public void setCcustomerid(final String ccustomerid) {
    this.setAttributeValue(CCUSTOMERID, ccustomerid);
  }

  /**
   * ��ȡɢ��
   * 
   * @returnɢ��
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(CFREECUSTID);
  }

  /**
   * ����ɢ��
   * 
   * @param cfreecustidɢ��
   */
  public void setCfreecustid(final String cfreecustid) {
    this.setAttributeValue(CFREECUSTID, cfreecustid);
  }

  /**
   * ��ȡ������������
   * 
   * @return������������
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(CCHANNELTYPEID);
  }

  /**
   * ����������������
   * 
   * @param cchanneltypeid������������
   */
  public void setCchanneltypeid(final String cchanneltypeid) {
    this.setAttributeValue(CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * ��ȡҵ��Ա
   * 
   * @returnҵ��Ա
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(CEMPLOYEEID);
  }

  /**
   * ����ҵ��Ա
   * 
   * @param cemployeeidҵ��Ա
   */
  public void setCemployeeid(final String cemployeeid) {
    this.setAttributeValue(CEMPLOYEEID, cemployeeid);
  }

  /**
   * ��ȡ����
   * 
   * @return����
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(CDEPTVID);
  }

  /**
   * ���ò���
   * 
   * @param cdeptvid����
   */
  public void setCdeptvid(final String cdeptvid) {
    this.setAttributeValue(CDEPTVID, cdeptvid);
  }

  /**
   * ��ȡ��Ʊ�ͻ�
   * 
   * @return��Ʊ�ͻ�
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(CINVOICECUSTID);
  }

  /**
   * ���ÿ�Ʊ�ͻ�
   * 
   * @param cinvoicecustid��Ʊ�ͻ�
   */
  public void setCinvoicecustid(final String cinvoicecustid) {
    this.setAttributeValue(CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public String getCcustbankid() {
    return (String) this.getAttributeValue(CCUSTBANKID);
  }

  /**
   * ���ÿ�������
   * 
   * @param ccustbankid��������
   */
  public void setCcustbankid(final String ccustbankid) {
    this.setAttributeValue(CCUSTBANKID, ccustbankid);
  }

  /**
   * ��ȡ���������˻�
   * 
   * @return���������˻�
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(CCUSTBANKACCID);
  }

  /**
   * ���ÿ��������˻�
   * 
   * @param ccustbankaccid���������˻�
   */
  public void setCcustbankaccid(final String ccustbankaccid) {
    this.setAttributeValue(CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * ��ȡ�տ�Э��
   * 
   * @return
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(CPAYTERMID);
  }

  /**
   * �����տ�Э��
   * 
   * @param cpaytermid
   */
  public void setCpaytermid(final String cpaytermid) {
    this.setAttributeValue(CPAYTERMID, cpaytermid);
  }

  /**
   * ��ȡԭ��
   * 
   * @returnԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyidԭ��
   */
  public void setCorigcurrencyid(final String corigcurrencyid) {
    this.setAttributeValue(CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ��ȡ���䷽ʽ
   * 
   * @return���䷽ʽ
   */
  public String getCtransporttypeid() {
    return (String) this.getAttributeValue(CTRANSPORTTYPEID);
  }

  /**
   * �������䷽ʽ
   * 
   * @param ctransporttypeid���䷽ʽ
   */
  public void setCtransporttypeid(final String ctransporttypeid) {
    this.setAttributeValue(CTRANSPORTTYPEID, ctransporttypeid);
  }

  /**
   * ��ȡó������
   * 
   * @returnó������
   */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(CTRADEWORDID);
  }

  /**
   * ����ó������
   * 
   * @param ctradewordidó������
   */
  public void setCtradewordid(final String ctradewordid) {
    this.setAttributeValue(CTRADEWORDID, ctradewordid);
  }

  /**
   * ��ȡ����֤��
   * 
   * @return����֤��
   */
  public String getVcreditnum() {
    return (String) this.getAttributeValue(VCREDITNUM);
  }

  /**
   * ���������˺�
   * 
   * @param vcreditnum����֤��
   */
  public void setVcreditnum(final String vcreditnum) {
    this.setAttributeValue(VCREDITNUM, vcreditnum);
  }

  /**
   * ��ȡ���㷽ʽ
   * 
   * @return���㷽ʽ
   */
  public String getCbalancetypeid() {
    return (String) this.getAttributeValue(CBALANCETYPEID);
  }

  /**
   * ���ý��㷽ʽ
   * 
   * @param cbalancetypeid���㷽ʽ
   */
  public void setCbalancetypeid(final String cbalancetypeid) {
    this.setAttributeValue(CBALANCETYPEID, cbalancetypeid);
  }

  /**
   * ��ȡ�����˷�
   * 
   * @return�����˷�
   */
  public String getBadvfreeflag() {
    return (String) this.getAttributeValue(BADVFEEFLAG);
  }

  /**
   * ���ô����˷�
   * 
   * @param badvfreeflag�����˷�
   */
  public void setBadvfreeflag(final String badvfeeflag) {
    this.setAttributeValue(BADVFEEFLAG, badvfeeflag);
  }

  /**
   * ��ȡ��ע
   * 
   * @return��ע
   */
  public String getVnote() {
    return (String) this.getAttributeValue(VNOTE);
  }

  /**
   * ���ñ�ע
   * 
   * @param vnote
   */
  public void setVnote(final String vnote) {
    this.setAttributeValue(VNOTE, vnote);
  }

  public String getVdef1() {
    return (String) this.getAttributeValue(VDEF1);
  }

  public void setVdef1(final String vdef1) {
    this.setAttributeValue(VDEF1, vdef1);
  }

  public String getVdef2() {
    return (String) this.getAttributeValue(VDEF2);
  }

  public void setVdef2(final String vdef2) {
    this.setAttributeValue(VDEF2, vdef2);
  }

  public String getVdef3() {
    return (String) this.getAttributeValue(VDEF3);
  }

  public void setVdef3(final String vdef3) {
    this.setAttributeValue(VDEF3, vdef3);
  }

  public String getVdef4() {
    return (String) this.getAttributeValue(VDEF4);
  }

  public void setVdef4(final String vdef4) {
    this.setAttributeValue(VDEF4, vdef4);
  }

  public String getVdef5() {
    return (String) this.getAttributeValue(VDEF5);
  }

  public void setVdef5(final String vdef5) {
    this.setAttributeValue(VDEF5, vdef5);
  }

  public String getVdef6() {
    return (String) this.getAttributeValue(VDEF6);
  }

  public void setVdef6(final String vdef6) {
    this.setAttributeValue(VDEF6, vdef6);
  }

  public String getVdef7() {
    return (String) this.getAttributeValue(VDEF7);
  }

  public void setVdef7(final String vdef7) {
    this.setAttributeValue(VDEF7, vdef7);
  }

  public String getVdef8() {
    return (String) this.getAttributeValue(VDEF8);
  }

  public void setVdef8(final String vdef8) {
    this.setAttributeValue(VDEF8, vdef8);
  }

  public String getVdef9() {
    return (String) this.getAttributeValue(VDEF9);
  }

  public void setVdef9(final String vdef9) {
    this.setAttributeValue(VDEF9, vdef9);
  }

  public String getVdef10() {
    return (String) this.getAttributeValue(VDEF10);
  }

  public void setVdef10(final String vdef10) {
    this.setAttributeValue(VDEF10, vdef10);
  }

  public String getVdef11() {
    return (String) this.getAttributeValue(VDEF11);
  }

  public void setVdef11(final String vdef11) {
    this.setAttributeValue(VDEF11, vdef11);
  }

  public String getVdef12() {
    return (String) this.getAttributeValue(VDEF12);
  }

  public void setVdef12(final String vdef12) {
    this.setAttributeValue(VDEF12, vdef12);
  }

  public String getVdef13() {
    return (String) this.getAttributeValue(VDEF13);
  }

  public void setVdef13(final String vdef13) {
    this.setAttributeValue(VDEF13, vdef13);
  }

  public String getVdef14() {
    return (String) this.getAttributeValue(VDEF14);
  }

  public void setVdef14(final String vdef14) {
    this.setAttributeValue(VDEF14, vdef14);
  }

  public String getVdef15() {
    return (String) this.getAttributeValue(VDEF15);
  }

  public void setVdef15(final String vdef15) {
    this.setAttributeValue(VDEF15, vdef15);
  }

  public String getVdef16() {
    return (String) this.getAttributeValue(VDEF16);
  }

  public void setVdef16(final String vdef16) {
    this.setAttributeValue(VDEF16, vdef16);
  }

  public String getVdef17() {
    return (String) this.getAttributeValue(VDEF17);
  }

  public void setVdef17(final String vdef17) {
    this.setAttributeValue(VDEF17, vdef17);
  }

  public String getVdef18() {
    return (String) this.getAttributeValue(VDEF18);
  }

  public void setVdef18(final String vdef18) {
    this.setAttributeValue(VDEF18, vdef18);
  }

  public String getVdef19() {
    return (String) this.getAttributeValue(VDEF19);
  }

  public void setVdef19(final String vdef19) {
    this.setAttributeValue(VDEF19, vdef19);
  }

  public String getVdef20() {
    return (String) this.getAttributeValue(VDEF20);
  }

  public void setVdef20(final String vdef20) {
    this.setAttributeValue(VDEF20, vdef20);
  }

  /**
   * ��ȡ�Ƶ���
   * 
   * @return �Ƶ���
   */
  public String getBillmaker() {
    return (String) this.getAttributeValue(BILLMAKER);
  }

  /**
   * �����Ƶ���
   * 
   * @param billmaker �Ƶ���
   */
  public void setBillmaker(final String billmaker) {
    this.setAttributeValue(BILLMAKER, billmaker);
  }

  /**
   * ��ȡ�Ƶ�����
   * 
   * @return�Ƶ�����
   */
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(DMAKEDATE);
  }

  /**
   * �����Ƶ�����
   * 
   * @param dmakedate�Ƶ�����
   */
  public void setDmakedate(final UFDate dmakedate) {
    this.setAttributeValue(DMAKEDATE, dmakedate);
  }

}
