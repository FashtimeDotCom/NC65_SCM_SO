package nc.vo.so.m4331.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class DeliveryCheckVO extends SuperVO {

  /** �ʼ챨���Ƿ����� */
  public static final String BCHECKINFLAG = "bcheckinflag";

  /** �Ƿ�ϸ� */
  public static final String BELIGFLAG = "beligflag";

  /** ��Ʒ */
  public static final String BLARGESSFLAG = "blargessflag";

  /** ����ر� */
  public static final String BOUTENDFLAG = "boutendflag";

  /** �Ƿ������� */
  public static final String BPRICECHGFLAG = "bpricechgflag";

  /** ����ر� */
  public static final String BTRANSENDFLAG = "btransendflag";

  /** ����ó�� */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** �ʼ�״̬ */
  public static final String CCHECKSTATEBID = "ccheckstatebid";

  /** ��λ�� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ���鴦��ʽ */
  public static final String CDEFECTPROCESSID = "cdefectprocessid";

  /** �������ӱ�ID */
  public static final String CDELIVERYBID = "cdeliverybid";

  /** �������ʼ��ID */
  public static final String CDELIVERYCID = "cdeliverycid";

  /** ���� */
  public static final String CMATERIALID = "cmaterialid";

  /** ���ϰ汾 */
  public static final String CMATERIALVID = "cmaterialvid";

  /** ԭ������ */
  public static final String CORIGAREAID = "corigareaid";

  /** ԭ���� */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /** ԭ�� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** ���۵�λ */
  public static final String CQTUNITID = "cqtunitid";

  /** �ջ�����/���� */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ������/���� */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** �ʼ쵥��ͷid */
  public static final String CSRCID = "csrcid";

  /** ˰�� */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** ��˰��/���� */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** ��Ӧ�� */
  public static final String CVENDORID = "cvendorid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /** �ʼ����� */
  public static final String DCHECKDATE = "dcheckdate";

  /** �������� */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** ��˰��� */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** �ʼ����� */
  public static final String NASTNUM = "nastnum";

  /** ��˰��� */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** �����ۿ۶� */
  public static final String NDISCOUNT = "ndiscount";

  /** �����ۿ� */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /** �۱����� */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** ȫ�ֱ�λ�һ��� */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /** ȫ�ֱ�����˰��� */
  public static final String NGLOBALMNY = "nglobalmny";

  /** ȫ�ֱ��Ҽ�˰�ϼ� */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /** ���ű�λ�һ��� */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /** ���ű�����˰��� */
  public static final String NGROUPMNY = "ngroupmny";

  /** ���ű��Ҽ�˰�ϼ� */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /** ��Ʒ�ۿ� */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /** ������˰��� */
  public static final String NMNY = "nmny";

  /** ��������˰���� */
  public static final String NNETPRICE = "nnetprice";

  /** �ʼ������� */
  public static final String NNUM = "nnum";

  /** �ۿ۶� */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /** ��˰��� */
  public static final String NORIGMNY = "norigmny";

  /** ����˰���� */
  public static final String NORIGNETPRICE = "norignetprice";

  /** ����˰���� */
  public static final String NORIGPRICE = "norigprice";

  /** ��˰�ϼ� */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** ����˰���� */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** ����˰���� */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** ��������˰���� */
  public static final String NPRICE = "nprice";

  /** ������˰���� */
  public static final String NQTNETPRICE = "nqtnetprice";

  /** ��˰���� */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /** ��˰���� */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /** ��˰���� */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /** ��˰���� */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  /** ������˰���� */
  public static final String NQTPRICE = "nqtprice";

  /** ���Һ�˰���� */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /** ���Һ�˰���� */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /** �ʼ챨������ */
  public static final String NQTUNITNUM = "nqtunitnum";

  /** ����˰�� */
  public static final String NTAX = "ntax";

  /** ���Ҽ�˰�ϼ� */
  public static final String NTAXMNY = "ntaxmny";

  /** ��������˰���� */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** ��������˰���� */
  public static final String NTAXPRICE = "ntaxprice";

  /** ˰�� */
  public static final String NTAXRATE = "ntaxrate";

  /** �ۼ�Ӧ��δ���������� */
  public static final String NTOTALNOTOUTNUM = "ntotalnotoutnum";

  /** �ۼƳ������� */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /** �ۼ��������� */
  public static final String NTOTALTRANSNUM = "ntotaltransnum";

  /** ���ε��� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  public static final String PK_ORG = "pk_org";

  /** ʱ��� */
  public static final String TS = "ts";

  /** ���κ� */
  public static final String VBATCHCODE = "vbatchcode";

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /** �ʼ쵥�ݺ� */
  public static final String VCHECKCODE = "vcheckcode";

  /** ������1 */
  public static final String VFREE1 = "vfree1";

  /** ������10 */
  public static final String VFREE10 = "vfree10";

  /** ������2 */
  public static final String VFREE2 = "vfree2";

  /** ������3 */
  public static final String VFREE3 = "vfree3";

  /** ������4 */
  public static final String VFREE4 = "vfree4";

  /** ������5 */
  public static final String VFREE5 = "vfree5";

  /** ������6 */
  public static final String VFREE6 = "vfree6";

  /** ������7 */
  public static final String VFREE7 = "vfree7";

  /** ������8 */
  public static final String VFREE8 = "vfree8";

  /** ������9 */
  public static final String VFREE9 = "vfree9";

  public static final String VQTUNITRATE = "vqtunitrate";

  /** ��ע */
  public static final String VROWNOTE = "vrownote";

  /** ���յ��к�(�����������к�) */
  public static final String VSRCROWNO = "vsrcrowno";

  private static final long serialVersionUID = 1L;

  public DeliveryCheckVO() {
    super();
  }

  public UFBoolean getBcheckinflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryCheckVO.BCHECKINFLAG);
  }

  public UFBoolean getBeligflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryCheckVO.BELIGFLAG);
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryCheckVO.BLARGESSFLAG);
  }

  public UFBoolean getBoutendflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryCheckVO.BOUTENDFLAG);
  }

  public UFBoolean getBpricechgflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryCheckVO.BPRICECHGFLAG);
  }

  public UFBoolean getBtransendflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryCheckVO.BTRANSENDFLAG);
  }

  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BTRIATRADEFLAG);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CASTUNITID);
  }

  public String getCcheckstatebid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CCHECKSTATEBID);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CCURRENCYID);
  }

  public String getCdefectprocessid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CDEFECTPROCESSID);
  }

  public String getCdeliverybid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CDELIVERYBID);
  }

  public String getCdeliverycid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CDELIVERYCID);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CMATERIALID);
  }

  public String getCmaterialvid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CMATERIALVID);
  }

  public String getCorigareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGAREAID);
  }

  public String getCorigcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGCOUNTRYID);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CORIGCURRENCYID);
  }

  public String getCproductorid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CPRODUCTORID);
  }

  public String getCprojectid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CPROJECTID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CQTUNITID);
  }

  public String getCrececountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECECOUNTRYID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CROWNO);
  }

  public String getCsendcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDCOUNTRYID);
  }

  public String getCsrcid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CSRCID);
  }

  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTAXCODEID);
  }

  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTAXCOUNTRYID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CUNITID);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(DeliveryCheckVO.CVENDORID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(DeliveryCheckVO.DBILLDATE);
  }

  public UFDate getDcheckdate() {
    return (UFDate) this.getAttributeValue(DeliveryCheckVO.DCHECKDATE);
  }

  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(DeliveryBVO.FBUYSELLFLAG);
  }

  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(DeliveryBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.delivery_check");
    return meta;
  }

  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NASTNUM);
  }

  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NCALTAXMNY);
  }

  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NDISCOUNT);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NDISCOUNTRATE);
  }

  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NEXCHANGERATE);
  }

  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALEXCHGRATE);
  }

  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALMNY);
  }

  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALTAXMNY);
  }

  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPEXCHGRATE);
  }

  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPMNY);
  }

  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPTAXMNY);
  }

  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NITEMDISCOUNTRATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NMNY);
  }

  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NNETPRICE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NNUM);
  }

  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGDISCOUNT);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGMNY);
  }

  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGNETPRICE);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGPRICE);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGTAXNETPRICE);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NORIGTAXPRICE);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NPRICE);
  }

  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTNETPRICE);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTORIGNETPRICE);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTORIGTAXNETPRC);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTORIGTAXPRICE);
  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTPRICE);
  }

  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTTAXNETPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTTAXPRICE);
  }

  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NQTUNITNUM);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTAXMNY);
  }

  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTAXNETPRICE);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTAXRATE);
  }

  public UFDouble getNtotalnotoutnum() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTOTALNOTOUTNUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotaltransnum() {
    return (UFDouble) this.getAttributeValue(DeliveryCheckVO.NTOTALTRANSNUM);
  }

  public String getPk_batchcode() {
    return (String) this.getAttributeValue(DeliveryCheckVO.PK_BATCHCODE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(DeliveryCheckVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DeliveryCheckVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(DeliveryCheckVO.TS);
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VBATCHCODE);
  }

  public String getVchangerate() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VCHANGERATE);
  }

  public String getVcheckcode() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VCHECKCODE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VFREE9);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VQTUNITRATE);
  }

  public String getVrownote() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VROWNOTE);
  }

  public String getVsrcrowno() {
    return (String) this.getAttributeValue(DeliveryCheckVO.VSRCROWNO);
  }

  public void setBcheckinflag(UFBoolean bcheckinflag) {
    this.setAttributeValue(DeliveryCheckVO.BCHECKINFLAG, bcheckinflag);
  }

  public void setBeligflag(UFBoolean beligflag) {
    this.setAttributeValue(DeliveryCheckVO.BELIGFLAG, beligflag);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(DeliveryCheckVO.BLARGESSFLAG, blargessflag);
  }

  public void setBoutendflag(UFBoolean boutendflag) {
    this.setAttributeValue(DeliveryCheckVO.BOUTENDFLAG, boutendflag);
  }

  public void setBpricechgflag(UFBoolean bpricechgflag) {
    this.setAttributeValue(DeliveryCheckVO.BPRICECHGFLAG, bpricechgflag);
  }

  public void setBtransendflag(UFBoolean btransendflag) {
    this.setAttributeValue(DeliveryCheckVO.BTRANSENDFLAG, btransendflag);
  }

  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(DeliveryBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(DeliveryCheckVO.CASTUNITID, castunitid);
  }

  public void setCcheckstatebid(String ccheckstatebid) {
    this.setAttributeValue(DeliveryCheckVO.CCHECKSTATEBID, ccheckstatebid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(DeliveryCheckVO.CCURRENCYID, ccurrencyid);
  }

  public void setCdefectprocessid(String cdefectprocessid) {
    this.setAttributeValue(DeliveryCheckVO.CDEFECTPROCESSID, cdefectprocessid);
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.setAttributeValue(DeliveryCheckVO.CDELIVERYBID, cdeliverybid);
  }

  public void setCdeliverycid(String cdeliverycid) {
    this.setAttributeValue(DeliveryCheckVO.CDELIVERYCID, cdeliverycid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(DeliveryCheckVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(DeliveryCheckVO.CMATERIALVID, cmaterialvid);
  }

  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(DeliveryBVO.CORIGAREAID, corigareaid);
  }

  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(DeliveryBVO.CORIGCOUNTRYID, corigcountryid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(DeliveryCheckVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(DeliveryCheckVO.CPRODUCTORID, cproductorid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(DeliveryCheckVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(DeliveryCheckVO.CQTUNITID, cqtunitid);
  }

  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(DeliveryBVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(DeliveryCheckVO.CROWNO, crowno);
  }

  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(DeliveryBVO.CSENDCOUNTRYID, csendcountryid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(DeliveryCheckVO.CSRCID, csrcid);
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(DeliveryBVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(DeliveryBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(DeliveryCheckVO.CUNITID, cunitid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(DeliveryCheckVO.CVENDORID, cvendorid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(DeliveryCheckVO.DBILLDATE, dbilldate);
  }

  public void setDcheckdate(UFDate dcheckdate) {
    this.setAttributeValue(DeliveryCheckVO.DCHECKDATE, dcheckdate);
  }

  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(DeliveryBVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(DeliveryBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(DeliveryCheckVO.NASTNUM, nastnum);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(DeliveryBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(DeliveryCheckVO.NDISCOUNT, ndiscount);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(DeliveryCheckVO.NDISCOUNTRATE, ndiscountrate);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(DeliveryCheckVO.NEXCHANGERATE, nexchangerate);
  }

  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(DeliveryBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(DeliveryBVO.NGLOBALMNY, nglobalmny);
  }

  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(DeliveryBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(DeliveryBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(DeliveryBVO.NGROUPMNY, ngroupmny);
  }

  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(DeliveryBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(DeliveryCheckVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(DeliveryCheckVO.NMNY, nmny);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(DeliveryCheckVO.NNETPRICE, nnetprice);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(DeliveryCheckVO.NNUM, nnum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(DeliveryCheckVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(DeliveryCheckVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(DeliveryCheckVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(DeliveryCheckVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(DeliveryCheckVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(DeliveryCheckVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(DeliveryCheckVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(DeliveryCheckVO.NPRICE, nprice);
  }

  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTNETPRICE, nqtnetprice);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(DeliveryCheckVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(DeliveryCheckVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(DeliveryCheckVO.NQTUNITNUM, nqtunitnum);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(DeliveryCheckVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(DeliveryCheckVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(DeliveryCheckVO.NTAXNETPRICE, ntaxnetprice);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(DeliveryCheckVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(DeliveryCheckVO.NTAXRATE, ntaxrate);
  }

  public void setNtotalnotoutnum(UFDouble ntotalnotoutnum) {
    this.setAttributeValue(DeliveryCheckVO.NTOTALNOTOUTNUM, ntotalnotoutnum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(DeliveryCheckVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotaltransnum(UFDouble ntotaltransnum) {
    this.setAttributeValue(DeliveryCheckVO.NTOTALTRANSNUM, ntotaltransnum);
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(DeliveryCheckVO.PK_BATCHCODE, pk_batchcode);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(DeliveryCheckVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DeliveryCheckVO.PK_ORG, pk_org);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(DeliveryCheckVO.TS, ts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(DeliveryCheckVO.VBATCHCODE, vbatchcode);
  }

  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(DeliveryCheckVO.VCHANGERATE, vchangerate);
  }

  public void setVcheckcode(String vcheckcode) {
    this.setAttributeValue(DeliveryCheckVO.VCHECKCODE, vcheckcode);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(DeliveryCheckVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(DeliveryCheckVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(DeliveryCheckVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(DeliveryCheckVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(DeliveryCheckVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(DeliveryCheckVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(DeliveryCheckVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(DeliveryCheckVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(DeliveryCheckVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(DeliveryCheckVO.VFREE9, vfree9);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(DeliveryCheckVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVrownote(String vrownote) {
    this.setAttributeValue(DeliveryCheckVO.VROWNOTE, vrownote);
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(DeliveryCheckVO.VSRCROWNO, vsrcrowno);
  }
}
