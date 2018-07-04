package nc.vo.so.pub.keyvalue;

/**
 * ���۹����鵥��Ĭ�ϵ��ֶ�ӳ���ϵʵ��
 * 
 * @since 6.0
 * @version 2012-2-6 ����08:54:09
 * @author fengjb
 */
public class SOKeyRela implements IKeyRela {

  /** ���� */
  private String pk_group = "pk_group";

  /** ����֯ */
  private String pk_org = "pk_org";
  
  /** ����֯ */
  private String pk_org_v = "pk_org_v";

  /** ҵ������ */
  private String cbiztypeid = "cbiztypeid";

  /** �������ͱ��� */
  private String vtrantypecode = "vtrantypecode";

  /** �������� */
  private String ctrantypeid = "ctrantypeid";

  /** ���ݺ� */
  private String vbillcode = "vbillcode";

  /** �������� */
  private String dbilldate = "dbilldate";

  /** ����״̬ */
  private String fstatusflag = "fstatusflag";

  /** ������״̬ */
  private String fpfstatusflag = "fpfstatusflag";

  /** �Ƶ��� */
  private String billmaker = "billmaker";

  /** �Ƶ����� */
  private String dmakedate = "dmakedate";

  /** ������ */
  private String creator = "creator";

  /** ����ʱ�� */
  private String creationtime = "creationtime";

  /** �޸��� */
  private String modifier = "modifier";

  /** �޸�ʱ�� */
  private String modifiedtime = "modifiedtime";

  /** ������ */
  private String approver = "approver";

  /** ����ʱ�� */
  private String taudittime = "taudittime";

  /** �ϼ����� */
  private String ntotalnum = "ntotalnum";

  /** ���ϼ� */
  private String ntotalorigmny = "ntotalorigmny";

  /** ɾ����־ */
  private String dr = "dr";

  /** ԭ�� */
  private String corigcurrencyid = "corigcurrencyid";

  /** �ͻ� */
  private String ccustomerid = "ccustomerid";

  /** ��Ʊ�ͻ� */
  private String cinvoicecustid = "cinvoicecustid";

  /** ɢ�� */
  private String cfreecustid = "cfreecustid";

  /** ���� */
  private String cdeptid = "cdeptid";

  /** �������°汾 */
  private String cdeptvid = "cdeptvid";

  /** ҵ��Ա */
  private String cemployeeid = "cemployeeid";

  /** ���㷽ʽ */
  private String cbalancetypeid = "cbalancetypeid";

  /** ������������ */
  private String cchanneltypeid = "cchanneltypeid";

  /** �������� */
  private String ccustbankid = "ccustbankid";

  /** ���������˻� */
  private String ccustbankaccid = "ccustbankaccid";

  /** �տ�Э�� */
  private String cpaytermid = "cpaytermid";

  /** ���䷽ʽ */
  private String ctransporttypeid = "ctransporttypeid";

  /** �к� */
  private String crowno = "crowno";

  /** ���� */
  private String cmaterialvid = "cmaterialvid";

  /** �������°汾 */
  private String cmaterialid = "cmaterialid";

  /** ��Ʒ�� */
  private String cprodlineid = "cprodlineid";

  /** �����־ */
  private String blaborflag = "blaborflag";

  /** �ۿ۱�־ */
  private String bdiscountflag = "bdiscountflag";

  /** ��Ʒ��־ */
  private String blargessflag = "blargessflag";

  /** �������� */
  private String cproductorid = "cproductorid";

  /** ��Ŀ */
  private String cprojectid = "cprojectid";

  /** ��Ӧ�� */
  private String cvendorid = "cvendorid";

  /** ���� */
  private String cfactoryid = "cfactoryid";

  /** �����ȼ� */
  private String cqualitylevelid = "cqualitylevelid";

  /** ���ɸ�������1 */
  private String vfree1 = "vfree1";

  /** ���ɸ�������2 */
  private String vfree2 = "vfree2";

  /** ���ɸ�������3 */
  private String vfree3 = "vfree3";

  /** ���ɸ�������4 */
  private String vfree4 = "vfree4";

  /** ���ɸ�������5 */
  private String vfree5 = "vfree5";

  /** ���ɸ�������6 */
  private String vfree6 = "vfree6";

  /** ���ɸ�������7 */
  private String vfree7 = "vfree7";

  /** ���ɸ�������8 */
  private String vfree8 = "vfree8";

  /** ���ɸ�������9 */
  private String vfree9 = "vfree9";

  /** ���ɸ�������10 */
  private String vfree10 = "vfree10";

  /** ����λ */
  private String cunitid = "cunitid";

  /** ��λ */
  private String castunitid = "castunitid";

  /** ������ */
  private String vchangerate = "vchangerate";

  /** ���۵�λ */
  private String cqtunitid = "cqtunitid";

  /** ���ۻ����� */
  private String vqtunitrate = "vqtunitrate";

  /** ������ */
  private String nnum = "nnum";

  /** ���� */
  private String nastnum = "nastnum";

  /** ���۵�λ���� */
  private String nqtunitnum = "nqtunitnum";

  /** ˰�� */
  private String ntaxrate = "ntaxrate";

  /** �����ۿ� */
  private String ndiscountrate = "ndiscountrate";

  /** ��Ʒ�ۿ� */
  private String nitemdiscountrate = "nitemdiscountrate";

  /** ��λ�� */
  private String ccurrencyid = "ccurrencyid";

  /** �۱����� */
  private String nexchangerate = "nexchangerate";

  /** ��˰���� */
  private String nqtorigtaxprice = "nqtorigtaxprice";

  /** ��˰���� */
  private String nqtorigprice = "nqtorigprice";

  /** ��˰���� */
  private String nqtorigtaxnetprc = "nqtorigtaxnetprc";

  /** ��˰���� */
  private String nqtorignetprice = "nqtorignetprice";

  /** ����λ��˰���� */
  private String norigtaxprice = "norigtaxprice";

  /** ����λ��˰���� */
  private String norigprice = "norigprice";

  /** ����λ��˰���� */
  private String norigtaxnetprice = "norigtaxnetprice";

  /** ����λ��˰���� */
  private String norignetprice = "norignetprice";

  /** ��˰��� */
  private String norigmny = "norigmny";

  /** ��˰�ϼ� */
  private String norigtaxmny = "norigtaxmny";

  /** �ۿ۶� */
  private String norigdiscount = "norigdiscount";

  /** ���Һ�˰���� */
  private String nqttaxprice = "nqttaxprice";

  /** ������˰���� */
  private String nqtprice = "nqtprice";

  /** ���Һ�˰���� */
  private String nqttaxnetprice = "nqttaxnetprice";

  /** ������˰���� */
  private String nqtnetprice = "nqtnetprice";

  /** �����Һ�˰���� */
  private String ntaxprice = "ntaxprice";

  /** ��������˰���� */
  private String nprice = "nprice";

  /** �����Һ�˰���� */
  private String ntaxnetprice = "ntaxnetprice";

  /** ��������˰���� */
  private String nnetprice = "nnetprice";

  /** ����˰�� */
  private String ntax = "ntax";

  /** ������˰��� */
  private String nmny = "nmny";

  /** ���Ҽ�˰�ϼ� */
  private String ntaxmny = "ntaxmny";

  /** �����ۿ۶� */
  private String ndiscount = "ndiscount";

  /** ȫ���۱����� */
  private String nglobalexchgrate = "nglobalexchgrate";

  /** ȫ�ֱ�����˰��� */
  private String nglobalmny = "nglobalmny";

  /** ȫ�ֱ��Ҽ�˰�ϼ� */
  private String nglobaltaxmny = "nglobaltaxmny";

  /** �����۱����� */
  private String ngroupexchgrate = "ngroupexchgrate";

  /** ���ű�����˰��� */
  private String ngroupmny = "ngroupmny";

  /** ���ű��Ҽ�˰�ϼ� */
  private String ngrouptaxmny = "ngrouptaxmny";

  /** ѯ��ԭ�Һ�˰���� */
  private String naskqtorigtaxprc = "naskqtorigtaxprc";

  /** ѯ��ԭ�Һ�˰���� */
  private String naskqtorigtxntprc = "naskqtorigtxntprc";

  /** ѯ��ԭ����˰���� */
  private String naskqtorignetprice = "naskqtorignetprice";

  /** ѯ��ԭ����˰���� */
  private String naskqtorigprice = "naskqtorigprice";

  /** �۸���� */
  private String cpriceformid = "cpriceformid";

  /** �۸��� */
  private String cpriceitemid = "cpriceitemid";

  /** ��Ŀ�� */
  private String cpriceitemtableid = "cpriceitemtableid";

  /** ���۲��� */
  private String cpricepolicyid = "cpricepolicyid";

  /** �������� */
  private String dreceivedate = "dreceivedate";

  /** �������� */
  private String dsenddate = "dsenddate";

  /** �ջ��ͻ� */
  private String creceivecustid = "creceivecustid";

  /** �ջ��ص� */
  private String creceiveadddocid = "creceiveadddocid";

  /** �ջ���ַ */
  private String creceiveaddrid = "creceiveaddrid";

  /** �ջ����� */
  private String creceiveareaid = "creceiveareaid";

  /** ������֯���°汾 */
  private String ctrafficorgid = "ctrafficorgid";

  /** ������֯ */
  private String ctrafficorgvid = "ctrafficorgvid";
  
  /** ������֯���°汾 */
  private String csaleorgid = "csaleorgid";

  /** ������֯ */
  private String csaleorgvid = "csaleorgvid";

  /** ���������֯���°汾 */
  private String csendstockorgid = "csendstockorgid";

  /** ���������֯ */
  private String csendstockorgvid = "csendstockorgvid";

  /** �����ֿ� */
  private String csendstordocid = "csendstordocid";

  /** ���������֯���°汾 */
  private String csettleorgid = "csettleorgid";

  /** ���������֯ */
  private String csettleorgvid = "csettleorgvid";

  /** Ӧ����֯ ���°汾 */
  private String carorgid = "carorgid";

  /** Ӧ����֯ */
  private String carorgvid = "carorgvid";

  /** �����������°汾 */
  private String cprofitcenterid = "cprofitcenterid";

  /** �������� */
  private String cprofitcentervid = "cprofitcentervid";

  /** Դͷ������id */
  private String cfirstbid = "cfirstbid";

  /** ��״̬ */
  private String frowstatus = "frowstatus";

  /** ��Ʒ�۸��̯��ʽ */
  private String flargesstypeflag = "flargesstypeflag";

  /** �ջ�����/���� */
  private String crececountryid = "crececountryid";

  /** ��������/���� */
  private String csendcountryid = "csendcountryid";

  /** ��˰����/���� */
  private String ctaxcountryid = "ctaxcountryid";

  /** ����ó�� */
  private String btriatradeflag = "btriatradeflag";

  /** ԭ������ */
  private String corigareaid = "corigareaid";

  /** ԭ���� */
  private String corigcountryid = "corigcountryid";

  /** ó������ */
  private String ctradewordid = "ctradewordid";

  /** ˰�� */
  private String ctaxcodeid = "ctaxcodeid";

  /** ��˰��� */
  private String ftaxtypeflag = "ftaxtypeflag";

  /** �������� */
  private String fbuysellflag = "fbuysellflag";

  /** ��˰��� */
  private String ncaltaxmny = "ncaltaxmny";

  @Override
  public String getPk_groupKey() {
    return this.pk_group;
  }

  @Override
  public void setPk_groupKey(String key) {
    this.pk_group = key;
  }

  @Override
  public String getPk_orgKey() {
    return this.pk_org;
  }

  @Override
  public void setPk_orgKey(String key) {
    this.pk_org = key;
  }

  @Override
  public String getCbiztypeidKey() {
    return this.cbiztypeid;
  }

  @Override
  public void setCbiztypeidKey(String key) {
    this.cbiztypeid = key;
  }

  @Override
  public String getVtrantypecodeKey() {
    return this.vtrantypecode;
  }

  @Override
  public void setVtrantypecodeKey(String key) {
    this.vtrantypecode = key;
  }

  @Override
  public String getCtrantypeidKey() {
    return this.ctrantypeid;
  }

  @Override
  public void setCtrantypeidKey(String key) {
    this.ctrantypeid = key;
  }

  @Override
  public String getVbillcodeKey() {
    return this.vbillcode;
  }

  @Override
  public void setVbillcodeKey(String key) {
    this.vbillcode = key;
  }

  @Override
  public String getDbilldateKey() {
    return this.dbilldate;
  }

  @Override
  public void setDbilldateKey(String key) {
    this.dbilldate = key;
  }

  @Override
  public String getFstatusflagKey() {
    return this.fstatusflag;
  }

  @Override
  public void setFstatusflagKey(String key) {
    this.fstatusflag = key;
  }

  @Override
  public String getFpfstatusflagKey() {
    return this.fpfstatusflag;
  }

  @Override
  public void setFpfstatusflagKey(String key) {
    this.fpfstatusflag = key;
  }

  @Override
  public String getBillmakerKey() {
    return this.billmaker;
  }

  @Override
  public void setBillmakerKey(String key) {
    this.billmaker = key;
  }

  @Override
  public String getDmakedateKey() {
    return this.dmakedate;
  }

  @Override
  public void setDmakedateKey(String key) {
    this.dmakedate = key;
  }

  @Override
  public String getCreatorKey() {
    return this.creator;
  }

  @Override
  public void setCreatorKey(String key) {
    this.creator = key;
  }

  @Override
  public String getCreationtimeKey() {
    return this.creationtime;
  }

  @Override
  public void setCreationtimeKey(String key) {
    this.creationtime = key;
  }

  @Override
  public String getModifierKey() {
    return this.modifier;
  }

  @Override
  public void setModifierKey(String key) {
    this.modifier = key;
  }

  @Override
  public String getModifiedtimeKey() {
    return this.modifiedtime;
  }

  @Override
  public void setModifiedtimeKey(String key) {
    this.modifiedtime = key;
  }

  @Override
  public String getApproverKey() {
    return this.approver;
  }

  @Override
  public void setApproverKey(String key) {
    this.approver = key;
  }

  @Override
  public String getTaudittimeKey() {
    return this.taudittime;
  }

  @Override
  public void setTaudittimeKey(String key) {
    this.taudittime = key;
  }

  @Override
  public String getNtotalnumKey() {
    return this.ntotalnum;
  }

  @Override
  public void setNtotalnumKey(String key) {
    this.ntotalnum = key;
  }

  @Override
  public String getNtotalorigmnyKey() {
    return this.ntotalorigmny;
  }

  @Override
  public void setNtotalorigmnyKey(String key) {
    this.ntotalorigmny = key;
  }

  @Override
  public String getDrKey() {
    return this.dr;
  }

  @Override
  public void setDrKey(String key) {
    this.dr = key;
  }

  @Override
  public String getCorigcurrencyidKey() {
    return this.corigcurrencyid;
  }

  @Override
  public void setCorigcurrencyidKey(String key) {
    this.corigcurrencyid = key;
  }

  @Override
  public String getCcustomeridKey() {
    return this.ccustomerid;
  }

  @Override
  public void setCcustomeridKey(String key) {
    this.ccustomerid = key;
  }

  @Override
  public String getCinvoicecustidKey() {
    return this.cinvoicecustid;
  }

  @Override
  public void setCinvoicecustidKey(String key) {
    this.cinvoicecustid = key;
  }

  @Override
  public String getCfreecustidKey() {
    return this.cfreecustid;
  }

  @Override
  public void setCfreecustidKey(String key) {
    this.cfreecustid = key;
  }

  @Override
  public String getCdeptidKey() {
    return this.cdeptid;
  }

  @Override
  public void setCdeptidKey(String key) {
    this.cdeptid = key;
  }

  @Override
  public String getCdeptvidKey() {
    return this.cdeptvid;
  }

  @Override
  public void setCdeptvidKey(String key) {
    this.cdeptvid = key;
  }

  @Override
  public String getCemployeeidKey() {
    return this.cemployeeid;
  }

  @Override
  public void setCemployeeidKey(String key) {
    this.cemployeeid = key;
  }

  @Override
  public String getCbalancetypeidKey() {
    return this.cbalancetypeid;
  }

  @Override
  public void setCbalancetypeidKey(String key) {
    this.cbalancetypeid = key;
  }

  @Override
  public String getCchanneltypeidKey() {
    return this.cchanneltypeid;
  }

  @Override
  public void setCchanneltypeidKey(String key) {
    this.cchanneltypeid = key;
  }

  @Override
  public String getCcustbankidKey() {
    return this.ccustbankid;
  }

  @Override
  public void setCcustbankidKey(String key) {
    this.ccustbankid = key;
  }

  @Override
  public String getCcustbankaccidKey() {
    return this.ccustbankaccid;
  }

  @Override
  public void setCcustbankaccidKey(String key) {
    this.ccustbankaccid = key;
  }

  @Override
  public String getCpaytermidKey() {
    return this.cpaytermid;
  }

  @Override
  public void setCpaytermidKey(String key) {
    this.cpaytermid = key;
  }

  @Override
  public String getCtransporttypeidKey() {
    return this.ctransporttypeid;
  }

  @Override
  public void setCtransporttypeidKey(String key) {
    this.ctransporttypeid = key;
  }

  @Override
  public String getCrownoKey() {
    return this.crowno;
  }

  @Override
  public void setCrownoKey(String key) {
    this.crowno = key;
  }

  @Override
  public String getCmaterialvidKey() {
    return this.cmaterialvid;
  }

  @Override
  public void setCmaterialvidKey(String key) {
    this.cmaterialvid = key;
  }

  @Override
  public String getCmaterialidKey() {
    return this.cmaterialid;
  }

  @Override
  public void setCmaterialidKey(String key) {
    this.cmaterialid = key;
  }

  @Override
  public String getCprodlineidKey() {
    return this.cprodlineid;
  }

  @Override
  public void setCprodlineidKey(String key) {
    this.cprodlineid = key;
  }

  @Override
  public String getBlaborflagKey() {
    return this.blaborflag;
  }

  @Override
  public void setBlaborflagKey(String key) {
    this.blaborflag = key;
  }

  @Override
  public String getBdiscountflagKey() {
    return this.bdiscountflag;
  }

  @Override
  public void setBdiscountflagKey(String key) {
    this.bdiscountflag = key;
  }

  @Override
  public String getBlargessflagKey() {
    return this.blargessflag;
  }

  @Override
  public void setBlargessflagKey(String key) {
    this.blargessflag = key;
  }

  @Override
  public String getCproductoridKey() {
    return this.cproductorid;
  }

  @Override
  public void setCproductoridKey(String key) {
    this.cproductorid = key;
  }

  @Override
  public String getCprojectidKey() {
    return this.cprojectid;
  }

  @Override
  public void setCprojectidKey(String key) {
    this.cprojectid = key;
  }

  @Override
  public String getCvendoridKey() {
    return this.cvendorid;
  }

  @Override
  public void setCvendoridKey(String key) {
    this.cvendorid = key;
  }

  @Override
  public String getCfactoryidKey() {
    return this.cfactoryid;
  }

  @Override
  public void setCfactoryidKey(String key) {
    this.cfactoryid = key;
  }

  @Override
  public String getCqualitylevelidKey() {
    return this.cqualitylevelid;
  }

  @Override
  public void setCqualitylevelidKey(String key) {
    this.cqualitylevelid = key;
  }

  @Override
  public String getVfree1Key() {
    return this.vfree1;
  }

  @Override
  public void setVfree1Key(String key) {
    this.vfree1 = key;
  }

  @Override
  public String getVfree2Key() {
    return this.vfree2;
  }

  @Override
  public void setVfree2Key(String key) {
    this.vfree2 = key;
  }

  @Override
  public String getVfree3Key() {
    return this.vfree3;
  }

  @Override
  public void setVfree3Key(String key) {
    this.vfree3 = key;
  }

  @Override
  public String getVfree4Key() {
    return this.vfree4;
  }

  @Override
  public void setVfree4Key(String key) {
    this.vfree4 = key;
  }

  @Override
  public String getVfree5Key() {
    return this.vfree5;
  }

  @Override
  public void setVfree5Key(String key) {
    this.vfree5 = key;
  }

  @Override
  public String getVfree6Key() {
    return this.vfree6;
  }

  @Override
  public void setVfree6Key(String key) {
    this.vfree6 = key;
  }

  @Override
  public String getVfree7Key() {
    return this.vfree7;
  }

  @Override
  public void setVfree7Key(String key) {
    this.vfree7 = key;
  }

  @Override
  public String getVfree8Key() {
    return this.vfree8;
  }

  @Override
  public void setVfree8Key(String key) {
    this.vfree8 = key;
  }

  @Override
  public String getVfree9Key() {
    return this.vfree9;
  }

  @Override
  public void setVfree9Key(String key) {
    this.vfree9 = key;
  }

  @Override
  public String getVfree10Key() {
    return this.vfree10;
  }

  @Override
  public void setVfree10Key(String key) {
    this.vfree10 = key;
  }

  @Override
  public String getCunitidKey() {
    return this.cunitid;
  }

  @Override
  public void setCunitidKey(String key) {
    this.cunitid = key;
  }

  @Override
  public String getCastunitidKey() {
    return this.castunitid;
  }

  @Override
  public void setCastunitidKey(String key) {
    this.castunitid = key;
  }

  @Override
  public String getVchangerateKey() {
    return this.vchangerate;
  }

  @Override
  public void setVchangerateKey(String key) {
    this.vchangerate = key;
  }

  @Override
  public String getCqtunitidKey() {
    return this.cqtunitid;
  }

  @Override
  public void setCqtunitidKey(String key) {
    this.cqtunitid = key;
  }

  @Override
  public String getVqtunitrateKey() {
    return this.vqtunitrate;
  }

  @Override
  public void setVqtunitrateKey(String key) {
    this.vqtunitrate = key;
  }

  @Override
  public String getNnumKey() {
    return this.nnum;
  }

  @Override
  public void setNnumKey(String key) {
    this.nnum = key;
  }

  @Override
  public String getNastnumKey() {
    return this.nastnum;
  }

  @Override
  public void setNastnumKey(String key) {
    this.nastnum = key;
  }

  @Override
  public String getNqtunitnumKey() {
    return this.nqtunitnum;
  }

  @Override
  public void setNqtunitnumKey(String key) {
    this.nqtunitnum = key;
  }

  @Override
  public String getNtaxrateKey() {
    return this.ntaxrate;
  }

  @Override
  public void setNtaxrateKey(String key) {
    this.ntaxrate = key;
  }

  @Override
  public String getNdiscountrateKey() {
    return this.ndiscountrate;
  }

  @Override
  public void setNdiscountrateKey(String key) {
    this.ndiscountrate = key;
  }

  @Override
  public String getNitemdiscountrateKey() {
    return this.nitemdiscountrate;
  }

  @Override
  public void setNitemdiscountrateKey(String key) {
    this.nitemdiscountrate = key;
  }

  @Override
  public String getCcurrencyidKey() {
    return this.ccurrencyid;
  }

  @Override
  public void setCcurrencyidKey(String key) {
    this.ccurrencyid = key;
  }

  @Override
  public String getNexchangerateKey() {
    return this.nexchangerate;
  }

  @Override
  public void setNexchangerateKey(String key) {
    this.nexchangerate = key;
  }

  @Override
  public String getNqtorigtaxpriceKey() {
    return this.nqtorigtaxprice;
  }

  @Override
  public void setNqtorigtaxpriceKey(String key) {
    this.nqtorigtaxprice = key;
  }

  @Override
  public String getNqtorigpriceKey() {
    return this.nqtorigprice;
  }

  @Override
  public void setNqtorigpriceKey(String key) {
    this.nqtorigprice = key;
  }

  @Override
  public String getNqtorigtaxnetprcKey() {
    return this.nqtorigtaxnetprc;
  }

  @Override
  public void setNqtorigtaxnetprcKey(String key) {
    this.nqtorigtaxnetprc = key;
  }

  @Override
  public String getNqtorignetpriceKey() {
    return this.nqtorignetprice;
  }

  @Override
  public void setNqtorignetpriceKey(String key) {
    this.nqtorignetprice = key;
  }

  @Override
  public String getNorigtaxpriceKey() {
    return this.norigtaxprice;
  }

  @Override
  public void setNorigtaxpriceKey(String key) {
    this.norigtaxprice = key;
  }

  @Override
  public String getNorigpriceKey() {
    return this.norigprice;
  }

  @Override
  public void setNorigpriceKey(String key) {
    this.norigprice = key;
  }

  @Override
  public String getNorigtaxnetpriceKey() {
    return this.norigtaxnetprice;
  }

  @Override
  public void setNorigtaxnetpriceKey(String key) {
    this.norigtaxnetprice = key;
  }

  @Override
  public String getNorignetpriceKey() {
    return this.norignetprice;
  }

  @Override
  public void setNorignetpriceKey(String key) {
    this.norignetprice = key;
  }

  @Override
  public String getNorigmnyKey() {
    return this.norigmny;
  }

  @Override
  public void setNorigmnyKey(String key) {
    this.norigmny = key;
  }

  @Override
  public String getNorigtaxmnyKey() {
    return this.norigtaxmny;
  }

  @Override
  public void setNorigtaxmnyKey(String key) {
    this.norigtaxmny = key;
  }

  @Override
  public String getNorigdiscountKey() {
    return this.norigdiscount;
  }

  @Override
  public void setNorigdiscountKey(String key) {
    this.norigdiscount = key;
  }

  @Override
  public String getNqttaxpriceKey() {
    return this.nqttaxprice;
  }

  @Override
  public void setNqttaxpriceKey(String key) {
    this.nqttaxprice = key;
  }

  @Override
  public String getNqtpriceKey() {
    return this.nqtprice;
  }

  @Override
  public void setNqtpriceKey(String key) {
    this.nqtprice = key;
  }

  @Override
  public String getNqttaxnetpriceKey() {
    return this.nqttaxnetprice;
  }

  @Override
  public void setNqttaxnetpriceKey(String key) {
    this.nqttaxnetprice = key;
  }

  @Override
  public String getNqtnetpriceKey() {
    return this.nqtnetprice;
  }

  @Override
  public void setNqtnetpriceKey(String key) {
    this.nqtnetprice = key;
  }

  @Override
  public String getNtaxpriceKey() {
    return this.ntaxprice;
  }

  @Override
  public void setNtaxpriceKey(String key) {
    this.ntaxprice = key;
  }

  @Override
  public String getNpriceKey() {
    return this.nprice;
  }

  @Override
  public void setNpriceKey(String key) {
    this.nprice = key;
  }

  @Override
  public String getNtaxnetpriceKey() {
    return this.ntaxnetprice;
  }

  @Override
  public void setNtaxnetpriceKey(String key) {
    this.ntaxnetprice = key;
  }

  @Override
  public String getNnetpriceKey() {
    return this.nnetprice;
  }

  @Override
  public void setNnetpriceKey(String key) {
    this.nnetprice = key;
  }

  @Override
  public String getNtaxKey() {
    return this.ntax;
  }

  @Override
  public void setNtaxKey(String key) {
    this.ntax = key;
  }

  @Override
  public String getNmnyKey() {
    return this.nmny;
  }

  @Override
  public void setNmnyKey(String key) {
    this.nmny = key;
  }

  @Override
  public String getNtaxmnyKey() {
    return this.ntaxmny;
  }

  @Override
  public void setNtaxmnyKey(String key) {
    this.ntaxmny = key;
  }

  @Override
  public String getNdiscountKey() {
    return this.ndiscount;
  }

  @Override
  public void setNdiscountKey(String key) {
    this.ndiscount = key;
  }

  @Override
  public String getNglobalexchgrateKey() {
    return this.nglobalexchgrate;
  }

  @Override
  public void setNglobalexchgrateKey(String key) {
    this.nglobalexchgrate = key;
  }

  @Override
  public String getNglobalmnyKey() {
    return this.nglobalmny;
  }

  @Override
  public void setNglobalmnyKey(String key) {
    this.nglobalmny = key;
  }

  @Override
  public String getNglobaltaxmnyKey() {
    return this.nglobaltaxmny;
  }

  @Override
  public void setNglobaltaxmnyKey(String key) {
    this.nglobaltaxmny = key;
  }

  @Override
  public String getNgroupexchgrateKey() {
    return this.ngroupexchgrate;
  }

  @Override
  public void setNgroupexchgrateKey(String key) {
    this.ngroupexchgrate = key;
  }

  @Override
  public String getNgroupmnyKey() {
    return this.ngroupmny;
  }

  @Override
  public void setNgroupmnyKey(String key) {
    this.ngroupmny = key;
  }

  @Override
  public String getNgrouptaxmnyKey() {
    return this.ngrouptaxmny;
  }

  @Override
  public void setNgrouptaxmnyKey(String key) {
    this.ngrouptaxmny = key;
  }

  @Override
  public String getNaskqtorigtaxprcKey() {
    return this.naskqtorigtaxprc;
  }

  @Override
  public void setNaskqtorigtaxprcKey(String key) {
    this.naskqtorigtaxprc = key;
  }

  @Override
  public String getNaskqtorigtxntprcKey() {
    return this.naskqtorigtxntprc;
  }

  @Override
  public void setNaskqtorigtxntprcKey(String key) {
    this.naskqtorigtxntprc = key;
  }

  @Override
  public String getNaskqtorignetpriceKey() {
    return this.naskqtorignetprice;
  }

  @Override
  public void setNaskqtorignetpriceKey(String key) {
    this.naskqtorignetprice = key;
  }

  @Override
  public String getNaskqtorigpriceKey() {
    return this.naskqtorigprice;
  }

  @Override
  public void setNaskqtorigpriceKey(String key) {
    this.naskqtorigprice = key;
  }

  @Override
  public String getCpriceformidKey() {
    return this.cpriceformid;
  }

  @Override
  public void setCpriceformidKey(String key) {
    this.cpriceformid = key;
  }

  @Override
  public String getCpriceitemidKey() {
    return this.cpriceitemid;
  }

  @Override
  public void setCpriceitemidKey(String key) {
    this.cpriceitemid = key;
  }

  @Override
  public String getCpriceitemtableidKey() {
    return this.cpriceitemtableid;
  }

  @Override
  public void setCpriceitemtableidKey(String key) {
    this.cpriceitemtableid = key;
  }

  @Override
  public String getCpricepolicyidKey() {
    return this.cpricepolicyid;
  }

  @Override
  public void setCpricepolicyidKey(String key) {
    this.cpricepolicyid = key;
  }

  @Override
  public String getDreceivedateKey() {
    return this.dreceivedate;
  }

  @Override
  public void setDreceivedateKey(String key) {
    this.dreceivedate = key;
  }

  @Override
  public String getDsenddateKey() {
    return this.dsenddate;
  }

  @Override
  public void setDsenddateKey(String key) {
    this.dsenddate = key;
  }

  @Override
  public String getCreceivecustidKey() {
    return this.creceivecustid;
  }

  @Override
  public void setCreceivecustidKey(String key) {
    this.creceivecustid = key;
  }

  @Override
  public String getCreceiveadddocidKey() {
    return this.creceiveadddocid;
  }

  @Override
  public void setCreceiveadddocidKey(String key) {
    this.creceiveadddocid = key;
  }

  @Override
  public String getCreceiveaddridKey() {
    return this.creceiveaddrid;
  }

  @Override
  public void setCreceiveaddridKey(String key) {
    this.creceiveaddrid = key;
  }

  @Override
  public String getCreceiveareaidKey() {
    return this.creceiveareaid;
  }

  @Override
  public void setCreceiveareaidKey(String key) {
    this.creceiveareaid = key;
  }

  @Override
  public String getCtrafficorgidKey() {
    return this.ctrafficorgid;
  }

  @Override
  public void setCtrafficorgidKey(String key) {
    this.ctrafficorgid = key;
  }

  @Override
  public String getCtrafficorgvidKey() {
    return this.ctrafficorgvid;
  }

  @Override
  public void setCsaleorgvidKey(String key) {
    this.csaleorgvid = key;
  }
  
  @Override
  public String getCsaleorgidKey() {
    return this.csaleorgid;
  }

  @Override
  public void setCsaleorgidKey(String key) {
    this.csaleorgid = key;
  }

  @Override
  public String getCsaleorgvidKey() {
    return this.csaleorgvid;
  }

  @Override
  public void setCtrafficorgvidKey(String key) {
    this.ctrafficorgvid = key;
  }

  @Override
  public String getCsendstockorgidKey() {
    return this.csendstockorgid;
  }

  @Override
  public void setCsendstockorgidKey(String key) {
    this.csendstockorgid = key;
  }

  @Override
  public String getCsendstockorgvidKey() {
    return this.csendstockorgvid;
  }

  @Override
  public void setCsendstockorgvidKey(String key) {
    this.csendstockorgvid = key;
  }

  @Override
  public String getCsendstordocidKey() {
    return this.csendstordocid;
  }

  @Override
  public void setCsendstordocidKey(String key) {
    this.csendstordocid = key;
  }

  @Override
  public String getCsettleorgidKey() {
    return this.csettleorgid;
  }

  @Override
  public void setCsettleorgidKey(String key) {
    this.csettleorgid = key;
  }

  @Override
  public String getCsettleorgvidKey() {
    return this.csettleorgvid;
  }

  @Override
  public void setCsettleorgvidKey(String key) {
    this.csettleorgvid = key;
  }

  @Override
  public String getCarorgidKey() {
    return this.carorgid;
  }

  @Override
  public void setCarorgidKey(String key) {
    this.carorgid = key;
  }

  @Override
  public String getCarorgvidKey() {
    return this.carorgvid;
  }

  @Override
  public void setCarorgvidKey(String key) {
    this.carorgvid = key;
  }

  @Override
  public String getCprofitcenteridKey() {
    return this.cprofitcenterid;
  }

  @Override
  public void setCprofitcenteridKey(String key) {
    this.cprofitcenterid = key;
  }

  @Override
  public String getCprofitcentervidKey() {
    return this.cprofitcentervid;
  }

  @Override
  public void setCprofitcentervidKey(String key) {
    this.cprofitcentervid = key;
  }

  @Override
  public String getCfirstbidKey() {
    return this.cfirstbid;
  }

  @Override
  public void setCfirstbidKey(String key) {
    this.cfirstbid = key;
  }

  @Override
  public String getFrowstatusKey() {
    return this.frowstatus;
  }

  @Override
  public void setFrowstatusKey(String key) {
    this.frowstatus = key;
  }

  @Override
  public String getFlargesstypeflagKey() {
    return this.flargesstypeflag;
  }

  @Override
  public void setFlargesstypeflagKey(String key) {
    this.flargesstypeflag = key;
  }

  @Override
  public String getCrececountryidKey() {
    return this.crececountryid;
  }

  @Override
  public void setCrececountryidKey(String key) {
    this.crececountryid = key;
  }

  @Override
  public String getCsendcountryidKey() {
    return this.csendcountryid;
  }

  @Override
  public void setCsendcountryidKey(String key) {
    this.csendcountryid = key;
  }

  @Override
  public String getCtaxcountryidKey() {
    return this.ctaxcountryid;
  }

  @Override
  public void setCtaxcountryidKey(String key) {
    this.ctaxcountryid = key;
  }

  @Override
  public String getBtriatradeflagKey() {
    return this.btriatradeflag;
  }

  @Override
  public void setBtriatradeflagKey(String key) {
    this.btriatradeflag = key;
  }

  @Override
  public String getCorigareaidKey() {
    return this.corigareaid;
  }

  @Override
  public void setCorigareaidKey(String key) {
    this.corigareaid = key;
  }

  @Override
  public String getCorigcountryidKey() {
    return this.corigcountryid;
  }

  @Override
  public void setCorigcountryidKey(String key) {
    this.corigcountryid = key;
  }

  @Override
  public String getCtradewordidKey() {
    return this.ctradewordid;
  }

  @Override
  public void setCtradewordidKey(String key) {
    this.ctradewordid = key;
  }

  @Override
  public String getCtaxcodeidKey() {
    return this.ctaxcodeid;
  }

  @Override
  public void setCtaxcodeidKey(String key) {
    this.ctaxcodeid = key;
  }

  @Override
  public String getFtaxtypeflagKey() {
    return this.ftaxtypeflag;
  }

  @Override
  public void setFtaxtypeflagKey(String key) {
    this.ftaxtypeflag = key;
  }

  @Override
  public String getFbuysellflagKey() {
    return this.fbuysellflag;
  }

  @Override
  public void setFbuysellflagKey(String key) {
    this.fbuysellflag = key;
  }

  @Override
  public String getNcaltaxmnyKey() {
    return this.ncaltaxmny;
  }

  @Override
  public void setNcaltaxmnyKey(String key) {
    this.ncaltaxmny = key;
  }

  @Override
  public String getPk_org_vKey() {
    return this.pk_org_v;
  }

  @Override
  public void setPk_org_vKey(String key) {
    this.pk_org_v=key;
  }

}
