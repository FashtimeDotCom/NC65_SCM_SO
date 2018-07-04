package nc.vo.so.m4331.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 
 * 
 * ��������ʵ��
 *
 */
public class DeliveryBVO extends SuperVO {

 
  /**
   * ������
   */
  public static final String CMFFILEID="cmffileid";
  
  /**
   * �ͻ����ϱ���
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * ���ÿͻ����ϱ���
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(DeliveryBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCUSTMATERIALID);
  }

  /** �����˷� */
  public static final String BADVFEEFLAG = "badvfeeflag";

  /** �Ƿ��ѱ��� */
  public static final String BCHECKFLAG = "bcheckflag";

  /** �������Źر���Դ�������,���������� */
  public static final String BCLOSESRCFLAG = "bclosesrcflag";

  /** ��Ʒ */
  public static final String BLARGESSFLAG = "blargessflag";

  /** ����ر� */
  public static final String BOUTENDFLAG = "boutendflag";

  /** �������ر� */
  public static final String BBARSETTLEFLAG = "bbarsettleflag";

  /** �Ƿ��Ѿ��ʼ� */
  public static final String BQUALITYFLAG = "bqualityflag";

  /** ����ر� */
  public static final String BTRANSENDFLAG = "btransendflag";

  /**
   * ����ó��
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** �Ƿ�����ʼ������ */
  public static final String BUSECHECKFLAG = "busecheckflag";

  /** Ӧ����֯ */
  public static final String CARORGID = "carorgid";

  /** Ӧ����֯�汾 */
  public static final String CARORGVID = "carorgvid";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** �������� */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /** ˾�� */
  public static final String CCHAUFFEURID = "cchauffeurid";

  /** ��λ�� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** �������ʼ��id */
  public static final String CDELIVERYBBID = "cdeliverybbid";

  /** �������ӱ�ID */
  public static final String CDELIVERYBID = "cdeliverybid";

  /** ����������_���� */
  public static final String CDELIVERYID = "cdeliveryid";

  /** ���۲��� */
  public static final String CDEPTID = "cdeptid";

  /** ���۲��Ű汾 */
  public static final String CDEPTVID = "cdeptvid";

  /** ����ҵ��Ա */
  public static final String CEMPLOYEEID = "cemployeeid";

  /** Դͷ���ݱ���ID */
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ���ݱ�ͷID */
  public static final String CFIRSTID = "cfirstid";

  /** ɢ�� */
  public static final String CFREECUSTID = "cfreecustid";

  /** �ջ������֯ */
  public static final String CINSTOCKORGID = "cinstockorgid";

  /** �ջ������֯�汾 */
  public static final String CINSTOCKORGVID = "cinstockorgvid";

  /** �ջ��ֿ� */
  public static final String CINSTORDOCID = "cinstordocid";

  /** ��Ʊ�ͻ� */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /** ���ϰ汾 */
  public static final String CMATERIALID = "cmaterialid";

  /** ���ϱ��� */
  public static final String CMATERIALVID = "cmaterialvid";

  /** �����ͻ� */
  public static final String CORDERCUSTID = "cordercustid";

  /**
   * ԭ������
   */
  public static final String CORIGAREAID = "corigareaid";

  /**
   * ԭ����
   */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /** ԭ�� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** �۸���� */
  public static final String CPRICEFORMID = "cpriceformid";

  /** ��Ʒ�� */
  public static final String CPRODLINEID = "cprodlineid";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ���������������°汾 */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /** ������������ */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** ���۵�λ */
  public static final String CQTUNITID = "cqtunitid";

  /** �����ȼ� */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /**
   * �ջ�����/����
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** �ջ��ص� */
  public static final String CRECEIVEADDDOCID = "creceiveadddocid";

  /** �ջ���ַ */
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  /** �ջ����� */
  public static final String CRECEIVEAREAID = "creceiveareaid";

  /** �ջ��ͻ� */
  public static final String CRECEIVECUSTID = "creceivecustid";

  /** �ջ���ϵ�� */
  public static final String CRECEIVEPERSONID = "creceivepersonid";

  /** �˻�ԭ�� */
  public static final String CRETREASONID = "cretreasonid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ������֯ */
  public static final String CSALEORGID = "csaleorgid";

  /** ������֯�汾 */
  public static final String CSALEORGVID = "csaleorgvid";

  /** �����ص� */
  public static final String CSENDADDDOCID = "csendadddocid";

  /** ������ַ */
  public static final String CSENDADDRID = "csendaddrid";

  /** �������� */
  public static final String CSENDAREAID = "csendareaid";

  /**
   * ��������/����
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** ������ϵ�� */
  public static final String CSENDPERSONID = "csendpersonid";

  /** ���������֯ */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /** ���������֯�汾 */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /** �����ֿ� */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /** ���������֯ */
  public static final String CSETTLEORGID = "csettleorgid";

  /** ���������֯�汾 */
  public static final String CSETTLEORGVID = "csettleorgvid";

  /** ��λ */
  public static final String CSPACEID = "cspaceid";

  /** ��Դ���ݱ���ID */
  public static final String CSRCBID = "csrcbid";

  /** ��Դ���ݱ�ͷID */
  public static final String CSRCID = "csrcid";

  /** Ѻ��Ա */
  public static final String CSUPERCARGOID = "csupercargoid";

  /**
   * ˰��
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * ��˰����/����
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** ������ */
  public static final String CTRANSCUSTID = "ctranscustid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** ���� */
  public static final String CVEHICLEID = "cvehicleid";

  /** ���� */
  public static final String CVEHICLETYPEID = "cvehicletypeid";

  /** ��Ӧ�� */
  public static final String CVENDORID = "cvendorid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /** dr */
  public static final String DR = "dr";

  /** Ҫ���ջ����� */
  public static final String DRECEIVEDATE = "dreceivedate";

  /** �������� */
  public static final String DSENDDATE = "dsenddate";

  /**
   * ��������
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** ��ע */
  public static final String FROWNOTE = "frownote";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * 
   */
  public static final String MAINMETAPATH = DeliveryBVO.CDELIVERYBID + ".";

  /** ���� */
  public static final String NASTNUM = "nastnum";

  /**
   * ��˰���
   */
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

  /** ������ */
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

  /** ���� */
  public static final String NPIECE = "npiece";

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

  /** �������� */
  public static final String NQTUNITNUM = "nqtunitnum";

  /** Ԥ������ */
  public static final String NREQRSNUM = "nreqrsnum";

  /** ˰�� */
  public static final String NTAX = "ntax";

  /** ���Ҽ�˰�ϼ� */
  public static final String NTAXMNY = "ntaxmny";

  /** ��������˰���� */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** ��������˰���� */
  public static final String NTAXPRICE = "ntaxprice";

  /** ˰�� */
  public static final String NTAXRATE = "ntaxrate";

  /** �ۼ�ȷ��Ӧ������ */
  public static final String NTOTALARNUM = "ntotalarnum";

  /** �ۼƺϸ����� */
  public static final String NTOTALELIGNUM = "ntotalelignum";

  /** �ۼ��ݹ�Ӧ������ */
  public static final String NTOTALESTARNUM = "ntotalestarnum";

  /** �ۼ�Ӧ��δ�������� */
  public static final String NTOTALNOTOUTNUM = "ntotalnotoutnum";

  /** �ۼƳ������� */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /** �ۼƱ������� */
  public static final String NTOTALREPORTNUM = "ntotalreportnum";

  /** �ۼƳ���Գ����� */
  public static final String NTOTALRUSHNUM = "ntotalrushnum";

  /** �ۼ��������� */
  public static final String NTOTALTRANSNUM = "ntotaltransnum";

  /** �ۼƲ��ϸ����� */
  public static final String NTOTALUNELIGNUM = "ntotalunelignum";

  /** �ۼ�;������ */
  public static final String NTRANSLOSSNUM = "ntranslossnum";

  /** ��� */
  public static final String NVOLUME = "nvolume";

  /** ���� */
  public static final String NWEIGHT = "nweight";

  /** ���ε��� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** ������֯ */
  public static final String PK_ORG = "pk_org";

  private static final long serialVersionUID = 1L;

  /**
   * srcbts��������༭��
   */
  public static final String SRCBTS = "srcbts";

  /**
   * srcts��������༭��
   */
  public static final String SRCTS = "srcts";

  /** ʱ��� */
  public static final String TS = "ts";

  /** �������ʼ��ts */
  public static final String TTS = "tts";

  /** ���κ� */
  public static final String VBATCHCODE = "vbatchcode";

  /** �Զ�����1 */
  public static final String VBDEF1 = "vbdef1";

  /** �Զ�����10 */
  public static final String VBDEF10 = "vbdef10";

  /** �Զ�����11 */
  public static final String VBDEF11 = "vbdef11";

  /** �Զ�����12 */
  public static final String VBDEF12 = "vbdef12";

  /** �Զ�����13 */
  public static final String VBDEF13 = "vbdef13";

  /** �Զ�����14 */
  public static final String VBDEF14 = "vbdef14";

  /** �Զ�����15 */
  public static final String VBDEF15 = "vbdef15";

  /** �Զ�����16 */
  public static final String VBDEF16 = "vbdef16";

  /** �Զ�����17 */
  public static final String VBDEF17 = "vbdef17";

  /** �Զ�����18 */
  public static final String VBDEF18 = "vbdef18";

  /** �Զ�����19 */
  public static final String VBDEF19 = "vbdef19";

  /** �Զ�����2 */
  public static final String VBDEF2 = "vbdef2";

  /** �Զ�����20 */
  public static final String VBDEF20 = "vbdef20";

  /** �Զ�����3 */
  public static final String VBDEF3 = "vbdef3";

  /** �Զ�����4 */
  public static final String VBDEF4 = "vbdef4";

  /** �Զ�����5 */
  public static final String VBDEF5 = "vbdef5";

  /** �Զ�����6 */
  public static final String VBDEF6 = "vbdef6";

  /** �Զ�����7 */
  public static final String VBDEF7 = "vbdef7";

  /** �Զ�����8 */
  public static final String VBDEF8 = "vbdef8";

  /** �Զ�����9 */
  public static final String VBDEF9 = "vbdef9";

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /** Դͷ��Դ���� */
  public static final String VFIRSTBILLDATE = "vfirstbilldate";

  /** Դͷ���ݺ� */
  public static final String VFIRSTCODE = "vfirstcode";

  /** Դͷ�����к� */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** Դͷ�������� */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /** Դͷ�������� */
  public static final String VFIRSTTYPE = "vfirsttype";

  /** ���ɸ�������1 */
  public static final String VFREE1 = "vfree1";

  /** ���ɸ�������10 */
  public static final String VFREE10 = "vfree10";

  /** ���ɸ�������2 */
  public static final String VFREE2 = "vfree2";

  /** ���ɸ�������3 */
  public static final String VFREE3 = "vfree3";

  /** ���ɸ�������4 */
  public static final String VFREE4 = "vfree4";

  /** ���ɸ�������5 */
  public static final String VFREE5 = "vfree5";

  /** ���ɸ�������6 */
  public static final String VFREE6 = "vfree6";

  /** ���ɸ�������7 */
  public static final String VFREE7 = "vfree7";

  /** ���ɸ�������8 */
  public static final String VFREE8 = "vfree8";

  /** ���ɸ�������9 */
  public static final String VFREE9 = "vfree9";

  /** ���ۻ����� */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** �ջ���ϵ�绰 */
  public static final String VRECEIVETEL = "vreceivetel";

  /** �˻����δ���ʽ */
  public static final String VRETURNMODE = "vreturnmode";

  /** ������ϵ�绰 */
  public static final String VSENDTEL = "vsendtel";

  /** ��Դ���ݺ� */
  public static final String VSRCCODE = "vsrccode";

  /** ��Դ�����к� */
  public static final String VSRCROWNO = "vsrcrowno";

  /** ��Դ�������� */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /** ��Դ�������� */
  public static final String VSRCTYPE = "vsrctype";

  /** ������������ */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";

  /** ���������������°汾 */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /** �ջ��������� */
  public static final String CRPROFITCENTERVID = "crprofitcentervid";

  /** �ջ������������°汾 */
  public static final String CRPROFITCENTERID = "crprofitcenterid";

  /**
   * 
   * @return �����˷�
   */
  public UFBoolean getBadvfeeflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BADVFEEFLAG);
  }

  /**
   * 
   * @return �Ƿ��ѱ���
   */
  public UFBoolean getBcheckflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BCHECKFLAG);
  }

  /**
   * 
   * @return �������Źر���Դ�������
   */
  public UFBoolean getBclosesrcflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BCLOSESRCFLAG);
  }

  /**
   * �������ر�
   * 
   * @return �������ر�
   */
  public UFBoolean getBbarsettleflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BBARSETTLEFLAG);
  }

  /**
   * 
   * @return ��Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BLARGESSFLAG);
  }

  /**
   * 
   * @return ����ر�
   */
  public UFBoolean getBoutendflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BOUTENDFLAG);
  }

  /**
   * 
   * @return �Ƿ��Ѿ��ʼ�
   */
  public UFBoolean getBqualityflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BQUALITYFLAG);
  }

  /**
   * 
   * @return ����ر�
   */
  public UFBoolean getBtransendflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BTRANSENDFLAG);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return ����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BTRIATRADEFLAG);
  }

  /**
   * 
   * @return �Ƿ�����ʼ������
   */
  public UFBoolean getBusecheckflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BUSECHECKFLAG);
  }

  /**
   * 
   * @return Ӧ����֯
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CARORGID);
  }

  /**
   * 
   * @return Ӧ����֯�汾
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CARORGVID);
  }

  /**
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(DeliveryBVO.CASTUNITID);
  }

  /**
   * 
   * @return ��������
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCHANNELTYPEID);
  }

  /**
   * 
   * @return ˾��
   */
  public String getCchauffeurid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCHAUFFEURID);
  }

  /**
   * 
   * @return ��λ��
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCURRENCYID);
  }

  /**
   * 
   * @return �������ʼ��id
   */
  public String getCdeliverybbid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDELIVERYBBID);
  }

  /**
   * 
   * @return �������ӱ�ID
   */
  public String getCdeliverybid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDELIVERYBID);
  }

  /**
   * 
   * @return ����������_����
   */
  public String getCdeliveryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDELIVERYID);
  }

  /**
   * 
   * @return ���۲���
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDEPTID);
  }

  /**
   * 
   * @return ���۲��Ű汾
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDEPTVID);
  }

  /**
   * 
   * @return ����ҵ��Ա
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CEMPLOYEEID);
  }

  /**
   * 
   * @return Դͷ���ݱ���ID
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(DeliveryBVO.CFIRSTBID);
  }

  /**
   * 
   * @return Դͷ���ݱ�ͷID
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(DeliveryBVO.CFIRSTID);
  }

  /**
   * 
   * @return ɢ��
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CFREECUSTID);
  }

  /**
   * 
   * @return �ջ������֯
   */
  public String getCinstockorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINSTOCKORGID);
  }

  /**
   * 
   * @return �ջ������֯�汾
   */
  public String getCinstockorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINSTOCKORGVID);
  }

  /**
   * 
   * @return �ջ��ֿ�
   */
  public String getCinstordocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINSTORDOCID);
  }

  /**
   * 
   * @return ��Ʊ�ͻ�
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINVOICECUSTID);
  }

  /**
   * 
   * @return ���ϰ汾
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(DeliveryBVO.CMATERIALID);
  }

  /**
   * 
   * @return ���ϱ���
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CMATERIALVID);
  }

  /**
   * 
   * @return �����ͻ�
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORDERCUSTID);
  }

  /**
   * ��ȡԭ������
   * 
   * @return ԭ������
   */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGAREAID);
  }

  /**
   * ��ȡԭ����
   * 
   * @return ԭ����
   */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGCOUNTRYID);
  }

  /**
   * 
   * @return
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGCURRENCYID);
  }

  /**
   * 
   * @return
   */
  public String getCpriceformid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPRICEFORMID);
  }

  /**
   * 
   * @return ��Ʒ��
   */
  public String getCprodlineid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPRODLINEID);
  }

  /**
   * 
   * @return ��������
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPRODUCTORID);
  }

  /**
   * 
   * @return ������������
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPROFITCENTERID);
  }

  /**
   * 
   * @return �����������İ汾
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPROFITCENTERVID);
  }

  /**
   * 
   * @return ��Ŀ
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPROJECTID);
  }

  /**
   * 
   * @return ���۵�λ
   */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(DeliveryBVO.CQTUNITID);
  }

  /**
   * 
   * @return �����ȼ�
   */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(DeliveryBVO.CQUALITYLEVELID);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return �ջ����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECECOUNTRYID);
  }

  /**
   * 
   * @return �ջ��ص�
   */
  public String getCreceiveadddocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEADDDOCID);
  }

  /**
   * 
   * @return �ջ���ַ
   */
  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEADDRID);
  }

  /**
   * 
   * @return �ջ�����
   */
  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEAREAID);
  }

  /**
   * 
   * @return �ջ��ͻ�
   */
  public String getCreceivecustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVECUSTID);
  }

  /**
   * 
   * @return �ջ���ϵ��
   */
  public String getCreceivepersonid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEPERSONID);
  }

  /**
   * 
   * @return �˻�ԭ��
   */
  public String getCretreasonid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRETREASONID);
  }

  /**
   * 
   * @return �к�
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(DeliveryBVO.CROWNO);
  }

  /**
   * 
   * @return ������֯
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSALEORGID);
  }

  /**
   * 
   * @return ������֯�汾
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSALEORGVID);
  }

  /**
   * 
   * @return �����ص�
   */
  public String getCsendadddocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDADDDOCID);
  }

  /**
   * 
   * @return ������ַ
   */
  public String getCsendaddrid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDADDRID);
  }

  /**
   * 
   * @return ��������
   */
  public String getCsendareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDAREAID);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return ��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDCOUNTRYID);
  }

  /**
   * 
   * @return ������ϵ��
   */
  public String getCsendpersonid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDPERSONID);
  }

  /**
   * 
   * @return ���������֯
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDSTOCKORGID);
  }

  /**
   * 
   * @return ���������֯�汾
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDSTOCKORGVID);
  }

  /**
   * 
   * @return �����ֿ�
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDSTORDOCID);
  }

  /**
   * 
   * @return ���������֯
   */
  public String getCsettleorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSETTLEORGID);
  }

  /**
   * 
   * @return ���������֯�汾
   */
  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSETTLEORGVID);
  }

  /**
   * 
   * @return ��λ
   */
  public String getCspaceid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSPACEID);
  }

  /**
   * 
   * @return ��Դ���ݱ���ID
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSRCBID);
  }

  /**
   * 
   * @return ��Դ���ݱ�ͷID
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSRCID);
  }

  /**
   * 
   * @return Ѻ��Ա
   */
  public String getCsupercargoid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSUPERCARGOID);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTAXCODEID);
  }

  /**
   * ��ȡ��˰���Һ͵���
   * 
   * @return ��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTAXCOUNTRYID);
  }

  /**
   * 
   * @return ������
   */
  public String getCtranscustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTRANSCUSTID);
  }

  /**
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(DeliveryBVO.CUNITID);
  }

  /**
   * 
   * @return ����
   */
  public String getCvehicleid() {
    return (String) this.getAttributeValue(DeliveryBVO.CVEHICLEID);
  }

  /**
   * 
   * @return ����
   */
  public String getCvehicletypeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CVEHICLETYPEID);
  }

  /**
   * 
   * @return ��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(DeliveryBVO.CVENDORID);
  }

  /**
   * 
   * @return ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.DBILLDATE);
  }

  /**
   * 
   * @return DR
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(DeliveryBVO.DR);
  }

  /**
   * 
   * @return Ҫ���ջ�����
   */
  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.DRECEIVEDATE);
  }

  /**
   * 
   * @return ��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.DSENDDATE);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(DeliveryBVO.FBUYSELLFLAG);
  }

  /**
   * 
   * @return ��ע
   */
  public String getFrownote() {
    return (String) this.getAttributeValue(DeliveryBVO.FROWNOTE);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(DeliveryBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.delivery_b");
    return meta;
  }

  /**
   * 
   * @return
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NASTNUM);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NCALTAXMNY);
  }

  /**
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NDISCOUNT);
  }

  /**
   * 
   * @return �����ۿ�
   */
  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NDISCOUNTRATE);
  }

  /**
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NEXCHANGERATE);
  }

  /**
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALEXCHGRATE);
  }

  /**
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPEXCHGRATE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NITEMDISCOUNTRATE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NNUM);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGDISCOUNT);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGTAXNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGTAXPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNpiece() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NPIECE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGTAXNETPRC);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGTAXPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTTAXNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTTAXPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTUNITNUM);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNreqrsnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NREQRSNUM);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAX);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXRATE);
  }

  public UFDouble getNtotalarnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALARNUM);
  }

  public UFDouble getNtotalelignum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALELIGNUM);
  }

  public UFDouble getNtotalestarnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALESTARNUM);
  }

  public UFDouble getNtotalnotoutnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALNOTOUTNUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotalreportnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALREPORTNUM);
  }

  public UFDouble getNtotalrushnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALRUSHNUM);
  }

  public UFDouble getNtotaltransnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALTRANSNUM);
  }

  public UFDouble getNtotalunelignum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALUNELIGNUM);
  }

  public UFDouble getNtranslossnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTRANSLOSSNUM);
  }

  public UFDouble getNvolume() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NVOLUME);
  }

  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NWEIGHT);
  }

  public String getPk_batchcode() {
    return (String) this.getAttributeValue(DeliveryBVO.PK_BATCHCODE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(DeliveryBVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DeliveryBVO.PK_ORG);
  }

  /**
   * ��ȡ��Դʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(DeliveryBVO.SRCBTS);
  }

  /**
   * ��ȡ��Դʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(DeliveryBVO.SRCTS);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(DeliveryBVO.TS);
  }

  public UFDateTime getTts() {
    return ValueUtils.getUFDateTime(this.getAttributeValue(DeliveryBVO.TTS));
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(DeliveryBVO.VBATCHCODE);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF9);
  }

  public String getVchangerate() {
    return (String) this.getAttributeValue(DeliveryBVO.VCHANGERATE);
  }

  public UFDate getVfirstbilldate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.VFIRSTBILLDATE);
  }

  public String getVfirstcode() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTCODE);
  }

  public String getVfirstrowno() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTROWNO);
  }

  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTTRANTYPE);
  }

  public String getVfirsttype() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTTYPE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE9);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(DeliveryBVO.VQTUNITRATE);
  }

  public String getVreceivetel() {
    return (String) this.getAttributeValue(DeliveryBVO.VRECEIVETEL);
  }

  public String getVreturnmode() {
    return (String) this.getAttributeValue(DeliveryBVO.VRETURNMODE);
  }

  public String getVsendtel() {
    return (String) this.getAttributeValue(DeliveryBVO.VSENDTEL);
  }

  public String getVsrccode() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCCODE);
  }

  public String getVsrcrowno() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCROWNO);
  }

  public String getVsrctrantype() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCTRANTYPE);
  }

  public String getVsrctype() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCTYPE);
  }

  /**
   * ��ȡ���������������°汾
   * 
   * @return ���������������°汾
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSPROFITCENTERVID);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSPROFITCENTERID);
  }

  /**
   * ��ȡ�ջ������������°汾
   * 
   * @return �ջ������������°汾
   */
  public String getCrprofitcentervid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRPROFITCENTERVID);
  }

  /**
   * ��ȡ�ջ���������
   * 
   * @return �ջ���������
   */
  public String getCrprofitcenterid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRPROFITCENTERID);
  }

  public void setBadvfeeflag(UFBoolean badvfeeflag) {
    this.setAttributeValue(DeliveryBVO.BADVFEEFLAG, badvfeeflag);
  }

  public void setBcheckflag(UFBoolean bcheckflag) {
    this.setAttributeValue(DeliveryBVO.BCHECKFLAG, bcheckflag);
  }

  public void setBclosesrcflag(UFBoolean bclosesrcflag) {
    this.setAttributeValue(DeliveryBVO.BCLOSESRCFLAG, bclosesrcflag);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(DeliveryBVO.BLARGESSFLAG, blargessflag);
  }

  public void setBoutendflag(UFBoolean boutendflag) {
    this.setAttributeValue(DeliveryBVO.BOUTENDFLAG, boutendflag);
  }

  public void setBqualityflag(UFBoolean bqualityflag) {
    this.setAttributeValue(DeliveryBVO.BQUALITYFLAG, bqualityflag);
  }

  public void setBtransendflag(UFBoolean btransendflag) {
    this.setAttributeValue(DeliveryBVO.BTRANSENDFLAG, btransendflag);
  }

  /**
   * ���� ����ó��
   * 
   * @param btriatradeflag ����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(DeliveryBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setBusecheckflag(UFBoolean busecheckflag) {
    this.setAttributeValue(DeliveryBVO.BUSECHECKFLAG, busecheckflag);
  }

  public void setCarorgid(String carorgid) {
    this.setAttributeValue(DeliveryBVO.CARORGID, carorgid);
  }

  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(DeliveryBVO.CARORGVID, carorgvid);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(DeliveryBVO.CASTUNITID, castunitid);
  }

  public void setCchanneltypeid(String newCchanneltypeid) {
    this.setAttributeValue(DeliveryBVO.CCHANNELTYPEID, newCchanneltypeid);
  }

  public void setCchauffeurid(String cchauffeurid) {
    this.setAttributeValue(DeliveryBVO.CCHAUFFEURID, cchauffeurid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(DeliveryBVO.CCURRENCYID, ccurrencyid);
  }

  public void setCdeliverybbid(String cdeliverybbid) {
    this.setAttributeValue(DeliveryBVO.CDELIVERYBBID, cdeliverybbid);
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.setAttributeValue(DeliveryBVO.CDELIVERYBID, cdeliverybid);
  }

  public void setCdeliveryid(String cdeliveryid) {
    this.setAttributeValue(DeliveryBVO.CDELIVERYID, cdeliveryid);
  }

  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(DeliveryBVO.CDEPTID, cdeptid);
  }

  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(DeliveryBVO.CDEPTVID, cdeptvid);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(DeliveryBVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(DeliveryBVO.CFIRSTBID, cfirstbid);
  }

  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(DeliveryBVO.CFIRSTID, cfirstid);
  }

  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(DeliveryBVO.CFREECUSTID, cfreecustid);
  }

  public void setCinstockorgid(String cinstockorgid) {
    this.setAttributeValue(DeliveryBVO.CINSTOCKORGID, cinstockorgid);
  }

  public void setCinstockorgvid(String cinstockorgvid) {
    this.setAttributeValue(DeliveryBVO.CINSTOCKORGVID, cinstockorgvid);
  }

  public void setCinstordocid(String cinstordocid) {
    this.setAttributeValue(DeliveryBVO.CINSTORDOCID, cinstordocid);
  }

  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(DeliveryBVO.CINVOICECUSTID, cinvoicecustid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(DeliveryBVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(DeliveryBVO.CMATERIALVID, cmaterialvid);
  }

  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(DeliveryBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * ����ԭ������
   * 
   * @param corigareaid ԭ������
   */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(DeliveryBVO.CORIGAREAID, corigareaid);
  }

  /**
   * ����ԭ����
   * 
   * @param corigcountryid ԭ����
   */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(DeliveryBVO.CORIGCOUNTRYID, corigcountryid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(DeliveryBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCpriceformid(String cpriceformid) {
    this.setAttributeValue(DeliveryBVO.CPRICEFORMID, cpriceformid);
  }

  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(DeliveryBVO.CPRODLINEID, cprodlineid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(DeliveryBVO.CPRODUCTORID, cproductorid);
  }

  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(DeliveryBVO.CPROFITCENTERID, cprofitcenterid);
  }

  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(DeliveryBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(DeliveryBVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(DeliveryBVO.CQTUNITID, cqtunitid);
  }

  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(DeliveryBVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid �ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(DeliveryBVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCreceiveadddocid(String creceiveadddocid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEADDDOCID, creceiveadddocid);
  }

  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEAREAID, creceiveareaid);
  }

  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVECUSTID, creceivecustid);
  }

  public void setCreceivepersonid(String creceivepersonid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEPERSONID, creceivepersonid);
  }

  public void setCretreasonid(String cretreasonid) {
    this.setAttributeValue(DeliveryBVO.CRETREASONID, cretreasonid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(DeliveryBVO.CROWNO, crowno);
  }

  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(DeliveryBVO.CSALEORGID, csaleorgid);
  }

  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(DeliveryBVO.CSALEORGVID, csaleorgvid);
  }

  public void setCsendadddocid(String csendadddocid) {
    this.setAttributeValue(DeliveryBVO.CSENDADDDOCID, csendadddocid);
  }

  public void setCsendaddrid(String csendaddrid) {
    this.setAttributeValue(DeliveryBVO.CSENDADDRID, csendaddrid);
  }

  public void setCsendareaid(String csendareaid) {
    this.setAttributeValue(DeliveryBVO.CSENDAREAID, csendareaid);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid ��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(DeliveryBVO.CSENDCOUNTRYID, csendcountryid);
  }

  public void setCsendpersonid(String csendpersonid) {
    this.setAttributeValue(DeliveryBVO.CSENDPERSONID, csendpersonid);
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(DeliveryBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(DeliveryBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(DeliveryBVO.CSENDSTORDOCID, csendstordocid);
  }

  public void setCsettleorgid(String csettleorgid) {
    this.setAttributeValue(DeliveryBVO.CSETTLEORGID, csettleorgid);
  }

  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(DeliveryBVO.CSETTLEORGVID, csettleorgvid);
  }

  public void setCspaceid(String cspaceid) {
    this.setAttributeValue(DeliveryBVO.CSPACEID, cspaceid);
  }

  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(DeliveryBVO.CSRCBID, csrcbid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(DeliveryBVO.CSRCID, csrcid);
  }

  public void setCsupercargoid(String csupercargoid) {
    this.setAttributeValue(DeliveryBVO.CSUPERCARGOID, csupercargoid);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(DeliveryBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid ��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(DeliveryBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCtranscustid(String ctranscustid) {
    this.setAttributeValue(DeliveryBVO.CTRANSCUSTID, ctranscustid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(DeliveryBVO.CUNITID, cunitid);
  }

  public void setCvehicleid(String cvehicleid) {
    this.setAttributeValue(DeliveryBVO.CVEHICLEID, cvehicleid);
  }

  public void setCvehicletypeid(String cvehicletypeid) {
    this.setAttributeValue(DeliveryBVO.CVEHICLETYPEID, cvehicletypeid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(DeliveryBVO.CVENDORID, cvendorid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(DeliveryBVO.DBILLDATE, dbilldate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(DeliveryBVO.DR, dr);
  }

  public void setDreceivedate(UFDate receivedate) {
    this.setAttributeValue(DeliveryBVO.DRECEIVEDATE, receivedate);
  }

  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(DeliveryBVO.DSENDDATE, dsenddate);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag ��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(DeliveryBVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setFrownote(String frownote) {
    this.setAttributeValue(DeliveryBVO.FROWNOTE, frownote);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(DeliveryBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(DeliveryBVO.NASTNUM, nastnum);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(DeliveryBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(DeliveryBVO.NDISCOUNT, ndiscount);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(DeliveryBVO.NDISCOUNTRATE, ndiscountrate);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(DeliveryBVO.NEXCHANGERATE, nexchangerate);
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
    this.setAttributeValue(DeliveryBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(DeliveryBVO.NMNY, nmny);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(DeliveryBVO.NNETPRICE, nnetprice);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(DeliveryBVO.NNUM, nnum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(DeliveryBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(DeliveryBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(DeliveryBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(DeliveryBVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(DeliveryBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(DeliveryBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(DeliveryBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNpiece(UFDouble npiece) {
    this.setAttributeValue(DeliveryBVO.NPIECE, npiece);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(DeliveryBVO.NPRICE, nprice);
  }

  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(DeliveryBVO.NQTNETPRICE, nqtnetprice);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(DeliveryBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(DeliveryBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(DeliveryBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(DeliveryBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(DeliveryBVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(DeliveryBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(DeliveryBVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(DeliveryBVO.NQTUNITNUM, nqtunitnum);
  }

  public void setNreqrsnum(UFDouble nreqrsnum) {
    this.setAttributeValue(DeliveryBVO.NREQRSNUM, nreqrsnum);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(DeliveryBVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(DeliveryBVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(DeliveryBVO.NTAXNETPRICE, ntaxnetprice);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(DeliveryBVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(DeliveryBVO.NTAXRATE, ntaxrate);
  }

  public void setNtotalarnum(UFDouble ntotalarnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALARNUM, ntotalarnum);
  }

  public void setNtotalelignum(UFDouble ntotalelignum) {
    this.setAttributeValue(DeliveryBVO.NTOTALELIGNUM, ntotalelignum);
  }

  public void setNtotalestarnum(UFDouble ntotalestarnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALESTARNUM, ntotalestarnum);
  }

  public void setNtotalnotoutnum(UFDouble ntotalnotoutnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALNOTOUTNUM, ntotalnotoutnum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotalreportnum(UFDouble ntotalreportnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALREPORTNUM, ntotalreportnum);
  }

  public void setNtotalrushnum(UFDouble ntotalrushnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALRUSHNUM, ntotalrushnum);
  }

  public void setNtotaltransnum(UFDouble ntotaltransnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALTRANSNUM, ntotaltransnum);
  }

  public void setNtotalunelignum(UFDouble ntotalunelignum) {
    this.setAttributeValue(DeliveryBVO.NTOTALUNELIGNUM, ntotalunelignum);
  }

  public void setNtranslossnum(UFDouble ntranslossnum) {
    this.setAttributeValue(DeliveryBVO.NTRANSLOSSNUM, ntranslossnum);
  }

  public void setNvolume(UFDouble nvolume) {
    this.setAttributeValue(DeliveryBVO.NVOLUME, nvolume);
  }

  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(DeliveryBVO.NWEIGHT, nweight);
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(DeliveryBVO.PK_BATCHCODE, pk_batchcode);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(DeliveryBVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DeliveryBVO.PK_ORG, pk_org);
  }

  /**
   * ������Դʱ���
   * 
   * @param ts ʱ���
   */
  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(DeliveryBVO.SRCBTS, srcbts);
  }

  /**
   * ������Դʱ���
   * 
   * @param ts ʱ���
   */
  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(DeliveryBVO.SRCTS, srcts);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(DeliveryBVO.TS, ts);
  }

  public void setTts(UFDateTime tts) {
    this.setAttributeValue(DeliveryBVO.TTS, tts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(DeliveryBVO.VBATCHCODE, vbatchcode);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(DeliveryBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(DeliveryBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(DeliveryBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(DeliveryBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(DeliveryBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(DeliveryBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(DeliveryBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(DeliveryBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(DeliveryBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(DeliveryBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(DeliveryBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(DeliveryBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(DeliveryBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(DeliveryBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(DeliveryBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(DeliveryBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(DeliveryBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(DeliveryBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(DeliveryBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(DeliveryBVO.VBDEF9, vbdef9);
  }

  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(DeliveryBVO.VCHANGERATE, vchangerate);
  }

  public void setVfirstbilldate(UFDate vfirstbilldate) {
    this.setAttributeValue(DeliveryBVO.VFIRSTBILLDATE, vfirstbilldate);
  }

  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(DeliveryBVO.VFIRSTCODE, vfirstcode);
  }

  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(DeliveryBVO.VFIRSTROWNO, vfirstrowno);
  }

  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(DeliveryBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(DeliveryBVO.VFIRSTTYPE, vfirsttype);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(DeliveryBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(DeliveryBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(DeliveryBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(DeliveryBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(DeliveryBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(DeliveryBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(DeliveryBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(DeliveryBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(DeliveryBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(DeliveryBVO.VFREE9, vfree9);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(DeliveryBVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVreceivetel(String vreceivetel) {
    this.setAttributeValue(DeliveryBVO.VRECEIVETEL, vreceivetel);
  }

  public void setVreturnmode(String vreturnmode) {
    this.setAttributeValue(DeliveryBVO.VRETURNMODE, vreturnmode);
  }

  public void setVsendtel(String vsendtel) {
    this.setAttributeValue(DeliveryBVO.VSENDTEL, vsendtel);
  }

  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(DeliveryBVO.VSRCCODE, vsrccode);
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(DeliveryBVO.VSRCROWNO, vsrcrowno);
  }

  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(DeliveryBVO.VSRCTRANTYPE, vsrctrantype);
  }

  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(DeliveryBVO.VSRCTYPE, vsrctype);
  }

  /**
   * ���÷�����������
   * 
   * @param csprofitcentervid ������������
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(DeliveryBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * ���÷��������������°汾
   * 
   * @param csprofitcenterid ���������������°汾
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(DeliveryBVO.CSPROFITCENTERID, csprofitcenterid);
  }

  /**
   * �����ջ���������
   * 
   * @param crprofitcentervid �ջ���������
   */
  public void setCrprofitcentervid(String crprofitcentervid) {
    this.setAttributeValue(DeliveryBVO.CRPROFITCENTERVID, crprofitcentervid);
  }

  /**
   * �����ջ������������°汾
   * 
   * @param crprofitcenterid �ջ������������°汾
   */
  public void setCrprofitcenterid(String crprofitcenterid) {
    this.setAttributeValue(DeliveryBVO.CRPROFITCENTERID, crprofitcenterid);
  }
  
  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(DeliveryBVO.CMFFILEID);
  }

  /**
   * ����������
   * 
   * @param ������
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(DeliveryBVO.CMFFILEID, cmffileid);
  }
  
}
