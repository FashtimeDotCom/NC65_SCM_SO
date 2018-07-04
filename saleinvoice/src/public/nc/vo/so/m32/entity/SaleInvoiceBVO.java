package nc.vo.so.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�ӱ�VO
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾�� V60
 * @since ��һ�汾��
 * @author fengjb
 * @time 2009-7-3 ����10:33:05
 */
public class SaleInvoiceBVO extends SuperVO {
  
  
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
    this.setAttributeValue(SaleInvoiceBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCUSTMATERIALID);
  }

  // �ۿ���
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  // ɢ����־
  public static final String BFREECUSTFLAG = "bfreecustflag";

  // ������
  public static final String BLABORFLAG = "blaborflag";

  // ��Ʒ
  public static final String BLARGESSFLAG = "blargessflag";

  // Ӧ����֯���°汾
  public static final String CARORGID = "carorgid";

  // Ӧ����֯
  public static final String CARORGVID = "carorgvid";

  // ��λ
  public static final String CASTUNITID = "castunitid";

  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  // ��֧��Ŀ
  public static final String CCOSTSUBJID = "ccostsubjid";

  // ��ͬ��
  public static final String CCTMANAGEID = "cctmanageid";

  // ���۲������°汾
  public static final String CDEPTID = "cdeptid";

  // ���۲���
  public static final String CDEPTVID = "cdeptvid";

  // ����ҵ��Ա
  public static final String CEMPLOYEEID = "cemployeeid";

  // Դͷ�����ӱ�(������༭)
  public static final String CFIRSTBID = "cfirstbid";

  // Դͷ��������(������༭)
  public static final String CFIRSTID = "cfirstid";

  // ɢ��
  public static final String CFREECUSTID = "cfreecustid";

  // ���ϻ�������(������༭)
  public static final String CMARBASCALSSID = "cmarbascalssid";

  // ���ϵ���
  public static final String CMATERIALID = "cmaterialid";

  // ���ϱ���
  public static final String CMATERIALVID = "cmaterialvid";

  // �Գ���Դ�ӱ�
  public static final String COPPOSESRCBID = "copposesrcbid";

  // �����ͻ�
  public static final String CORDERCUSTID = "cordercustid";

  // ��Ʒ��
  public static final String CPRODLINEID = "cprodlineid";

  // ��������
  public static final String CPRODUCTORID = "cproductorid";

  // ���������������°汾
  public static final String CPROFITCENTERID = "cprofitcenterid";

  // ������������
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  // ��Ŀ
  public static final String CPROJECTID = "cprojectid";

  // ���۵�λ
  public static final String CQTUNITID = "cqtunitid";

  // �ջ���ַ
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  // �ջ��ͻ�
  public static final String CRECEIVECUSTID = "creceivecustid";

  // �к�
  public static final String CROWNO = "crowno";

  // ��Ʊ��ʵ�壨������༭��
  public static final String CSALEINVOICEBID = "csaleinvoicebid";

  // ���۷�Ʊ��ʵ��_������������༭��
  public static final String CSALEINVOICEID = "csaleinvoiceid";

  // ������֯���°汾��������༭��
  public static final String CSALEORGID = "csaleorgid";

  // ������֯
  public static final String CSALEORGVID = "csaleorgvid";

  // �����֯���°汾��������༭��
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  // �����֯
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  // �ֿ⣨������༭��
  public static final String CSENDSTORDOCID = "csendstordocid";

  // ��Դ�����ӱ�������༭��
  public static final String CSRCBID = "csrcbid";

  // ��Դ��������������༭��
  public static final String CSRCID = "csrcid";

  // ���Ļ���������������༭��
  public static final String CSUMID = "csumid";

  /**
   * ˰��(v61)
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  // ���䷽ʽ
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";

  // ����λ��������༭��
  public static final String CUNITID = "cunitid";

  // ��Ӧ��
  public static final String CVENDORID = "cvendorid";

  // �Ĵ湩Ӧ�̣�������༭��
  public static final String CVMIVENDERID = "cvmivenderid";

  // ��Ʊ���ڣ�������༭��
  public static final String DBILLDATE = "dbilldate";

  // dr
  public static final String DR = "dr";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** SaleInvoiceBVOԪ����·��(Ԫ���������ϵ��ӱ�ۺ�����) */
  public static final String MAINMETAPATH = "csaleinvoicebid.";

  // ����
  public static final String NASTNUM = "nastnum";

  /**
   * ��˰���
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  // ncanoutnum
  public static final String NCANOUTNUM = "ncanoutnum";

  // �����ۿ۶������༭��
  public static final String NDISCOUNT = "ndiscount";

  // �����ۿ�
  public static final String NDISCOUNTRATE = "ndiscountrate";

  // ȫ�ֱ�����˰��������༭��
  public static final String NGLOBALMNY = "nglobalmny";

  // ȫ�ֱ��Ҽ�˰�ϼƣ�������༭��
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  // ���ű�����˰��������༭��
  public static final String NGROUPMNY = "ngroupmny";

  // ���ű��Ҽ�˰�ϼƣ�������༭��
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  // ��Ʊ�ۿ�
  public static final String NINVOICEDISRATE = "ninvoicedisrate";

  // ���ǰ��������༭��
  // public static final String NBFORIGSUBMNY = "nbforigsubmny";

  // ��Ʒ�ۿ�
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  // ������˰��������༭��
  public static final String NMNY = "nmny";

  // ��������˰���ۣ�������༭��
  public static final String NNETPRICE = "nnetprice";

  // ������
  public static final String NNUM = "nnum";

  // �ۿ۶�
  public static final String NORIGDISCOUNT = "norigdiscount";

  // ��˰���
  public static final String NORIGMNY = "norigmny";

  // ����˰����
  public static final String NORIGNETPRICE = "norignetprice";

  // ����˰����
  public static final String NORIGPRICE = "norigprice";

  // ���ó�ֽ�������༭��
  public static final String NORIGSUBMNY = "norigsubmny";

  // ��˰�ϼ�
  public static final String NORIGTAXMNY = "norigtaxmny";

  // ����˰����
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  // ����˰����
  public static final String NORIGTAXPRICE = "norigtaxprice";

  // ˰��
  // public static final String NORIGTAX = "norigtax";

  // ��������˰���ۣ�������༭��
  public static final String NPRICE = "nprice";

  // ������˰���ۣ�������༭��
  public static final String NQTNETPRICE = "nqtnetprice";

  // ��˰����
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  // ��˰����
  public static final String NQTORIGPRICE = "nqtorigprice";

  // ��˰����
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  // ��˰����
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  // ������˰���ۣ�������༭��
  public static final String NQTPRICE = "nqtprice";

  // ���Һ�˰���ۣ�������༭��
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  // ���Һ�˰���ۣ�������༭��
  public static final String NQTTAXPRICE = "nqttaxprice";

  // ��������
  public static final String NQTUNITNUM = "nqtunitnum";

  // �ۼ�Ӧ��δ����������������༭��
  public static final String NSHOULDOUTNUM = "nshouldoutnum";

  // nsrcnum
  public static final String NSRCNUM = "nsrcnum";

  // ����˰�������༭��
  public static final String NTAX = "ntax";

  // ���Ҽ�˰�ϼƣ�������༭��
  public static final String NTAXMNY = "ntaxmny";

  // �����Һ�˰���ۣ�������༭��
  public static final String NTAXNETPRICE = "ntaxnetprice";

  // �����Һ�˰���ۣ�������༭��
  public static final String NTAXPRICE = "ntaxprice";

  // ˰��
  public static final String NTAXRATE = "ntaxrate";

  // �ۼƳɱ�����������������༭��
  public static final String NTOTALCOSTNUM = "ntotalcostnum";

  // �ۼ�ȷ��Ӧ�ս�������༭��
  public static final String NTOTALINCOMEMNY = "ntotalincomemny";

  // �ۼ�ȷ��Ӧ��������������༭��
  public static final String NTOTALINCOMENUM = "ntotalincomenum";

  // �ۼƳ���������������༭��
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  // �ۼ��տ��������༭��
  public static final String NTOTALPAYMNY = "ntotalpaymny";

  // ���ε���
  public static final String PK_BATCHCODE = "pk_batchcode";

  // ����
  public static final String PK_GROUP = "pk_group";

  // ��Ʊ��֯
  public static final String PK_ORG = "pk_org";

  // srcbts��������༭��
  public static final String SRCBTS = "srcbts";

  // srcts��������༭��
  public static final String SRCTS = "srcts";

  // ʱ�����������༭��
  public static final String TS = "ts";

  // ���κ�
  public static final String VBATCHCODE = "vbatchcode";

  // �Զ�����1
  public static final String VBDEF1 = "vbdef1";

  // �Զ�����10
  public static final String VBDEF10 = "vbdef10";

  // �Զ�����11
  public static final String VBDEF11 = "vbdef11";

  // �Զ�����12
  public static final String VBDEF12 = "vbdef12";

  // �Զ�����13
  public static final String VBDEF13 = "vbdef13";

  // �Զ�����14
  public static final String VBDEF14 = "vbdef14";

  // �Զ�����15
  public static final String VBDEF15 = "vbdef15";

  // �Զ�����16
  public static final String VBDEF16 = "vbdef16";

  // �Զ�����17
  public static final String VBDEF17 = "vbdef17";

  // �Զ�����18
  public static final String VBDEF18 = "vbdef18";

  // �Զ�����19
  public static final String VBDEF19 = "vbdef19";

  // �Զ�����2
  public static final String VBDEF2 = "vbdef2";

  // �Զ�����20
  public static final String VBDEF20 = "vbdef20";

  // �Զ�����3
  public static final String VBDEF3 = "vbdef3";

  // �Զ�����4
  public static final String VBDEF4 = "vbdef4";

  // �Զ�����5
  public static final String VBDEF5 = "vbdef5";

  // �Զ�����6
  public static final String VBDEF6 = "vbdef6";

  // �Զ�����7
  public static final String VBDEF7 = "vbdef7";

  // �Զ�����8
  public static final String VBDEF8 = "vbdef8";

  // �Զ�����9
  public static final String VBDEF9 = "vbdef9";

  // ������
  public static final String VCHANGERATE = "vchangerate";

  // Դͷ���ݺţ�������༭��
  public static final String VFIRSTCODE = "vfirstcode";

  // Դͷ�����кţ�������༭��
  public static final String VFIRSTROWNO = "vfirstrowno";

  // Դͷ��������
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  // Դͷ��������
  public static final String VFIRSTTYPE = "vfirsttype";

  // ���ϸ�������1
  public static final String VFREE1 = "vfree1";

  // ���ϸ�������10
  public static final String VFREE10 = "vfree10";

  // ���ϸ�������2
  public static final String VFREE2 = "vfree2";

  // ���ϸ�������3
  public static final String VFREE3 = "vfree3";

  // ���ϸ�������4
  public static final String VFREE4 = "vfree4";

  // ���ϸ�������5
  public static final String VFREE5 = "vfree5";

  // ���ϸ�������6
  public static final String VFREE6 = "vfree6";

  // ���ϸ�������7
  public static final String VFREE7 = "vfree7";

  // ���ϸ�������8
  public static final String VFREE8 = "vfree8";

  // ���ϸ�������9
  public static final String VFREE9 = "vfree9";

  // ���۵�λ������
  public static final String VQTUNITRATE = "vqtunitrate";

  // ��ע
  public static final String VROWNOTE = "vrownote";

  // ��Դ���ݺţ�������༭��
  public static final String VSRCCODE = "vsrccode";

  // ��Դ�����кţ�������༭��
  public static final String VSRCROWNO = "vsrcrowno";

  // ��Դ��������
  public static final String VSRCTRANTYPE = "vsrctrantype";

  // ��Դ��������
  public static final String VSRCTYPE = "vsrctype";

  // ���Ļ��ܺ�
  public static final String VSUMCODE = "vsumcode";

  /** ������������ */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";

  /** �����������İ汾 */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /**
   * 
   */
  private static final long serialVersionUID = -5360485037679893882L;

  /**
   * SaleInvoiceBVO �Ĺ�����
   */
  public SaleInvoiceBVO() {
    super();
  }

  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BDISCOUNTFLAG);
  }

  public UFBoolean getBfreecustflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BFREECUSTFLAG);
  }

  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BLABORFLAG);
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BLARGESSFLAG);
  }

  public String getCarorgid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CARORGID);
  }

  public String getCarorgvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CARORGVID);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CASTUNITID);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCHANNELTYPEID);
  }

  public String getCcostsubjid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCOSTSUBJID);
  }

  public String getCctmanageid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCTMANAGEID);
  }

  public String getCdeptid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CDEPTID);
  }

  public String getCdeptvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CDEPTVID);
  }

  public String getCemployeeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CEMPLOYEEID);
  }

  public String getCfirstbid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CFIRSTBID);
  }

  public String getCfirstid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CFIRSTID);
  }

  public String getCfreecustid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CFREECUSTID);
  }

  public String getCmarbascalssid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMARBASCALSSID);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMATERIALID);
  }

  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMATERIALVID);
  }

  public String getCopposesrcbid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.COPPOSESRCBID);
  }

  public String getCordercustid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CORDERCUSTID);
  }

  public String getCprodlineid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPRODLINEID);
  }

  public String getCproductorid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPRODUCTORID);
  }

  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPROFITCENTERID);
  }

  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPROFITCENTERVID);
  }

  public String getCprojectid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPROJECTID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CQTUNITID);
  }

  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CRECEIVEADDRID);
  }

  public String getCreceivecustid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CRECEIVECUSTID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CROWNO);
  }

  public String getCsaleinvoicebid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEINVOICEBID);
  }

  public String getCsaleinvoiceid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEINVOICEID);
  }

  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEORGID);
  }

  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEORGVID);
  }

  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGID);
  }

  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGVID);
  }

  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSENDSTORDOCID);
  }

  public String getCsrcbid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSRCBID);
  }

  public String getCsrcid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSRCID);
  }

  public String getCsumid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSUMID);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CTAXCODEID);
  }

  public String getCtransporttypeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CTRANSPORTTYPEID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CUNITID);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CVENDORID);
  }

  public String getCvmivenderid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CVMIVENDERID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SaleInvoiceBVO.DBILLDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SaleInvoiceBVO.DR);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SaleInvoiceBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.saleinvoice_b");
    return meta;
  }

  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NASTNUM);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NCALTAXMNY);
  }

  public UFDouble getNcanoutnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NCANOUTNUM);
  }

  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NDISCOUNT);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NDISCOUNTRATE);
  }

  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGLOBALMNY);
  }

  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGLOBALTAXMNY);
  }

  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGROUPMNY);
  }

  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGROUPTAXMNY);
  }

  public UFDouble getNinvoicedisrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NINVOICEDISRATE);
  }

  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NMNY);
  }

  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NNETPRICE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NNUM);
  }

  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGDISCOUNT);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGMNY);
  }

  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGNETPRICE);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGPRICE);
  }

  public UFDouble getNorigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGSUBMNY);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAXNETPRICE);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAXPRICE);
  }

  // public UFDouble getNorigtax() {
  // return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAX);
  // }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NPRICE);
  }

  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTNETPRICE);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGNETPRICE);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGTAXNETPRC);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGTAXPRICE);
  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTPRICE);
  }

  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTTAXNETPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTTAXPRICE);
  }

  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTUNITNUM);
  }

  public UFDouble getNshouldoutnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NSHOULDOUTNUM);
  }

  public UFDouble getNsrcnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NSRCNUM);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXMNY);
  }

  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXNETPRICE);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXRATE);
  }

  public UFDouble getNtotalcostnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALCOSTNUM);
  }

  public UFDouble getNtotalincomemny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALINCOMEMNY);
  }

  public UFDouble getNtotalincomenum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALINCOMENUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALPAYMNY);
  }

  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.PK_BATCHCODE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.PK_ORG);
  }

  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceBVO.SRCBTS);
  }

  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceBVO.SRCTS);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceBVO.TS);
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBATCHCODE);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF9);
  }

  public String getVchangerate() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VCHANGERATE);
  }

  public String getVfirstcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTCODE);
  }

  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTROWNO);
  }

  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTTRANTYPE);
  }

  public String getVfirsttype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTTYPE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE9);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE);
  }

  public String getVrownote() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VROWNOTE);
  }

  public String getVsrccode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCCODE);
  }

  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCROWNO);
  }

  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCTRANTYPE);
  }

  public String getVsrctype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCTYPE);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSPROFITCENTERVID);
  }

  /**
   * ��ȡ�����������İ汾
   * 
   * @return �����������İ汾
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSPROFITCENTERID);
  }

  public String getVsumcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSUMCODE);
  }

  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(SaleInvoiceBVO.BDISCOUNTFLAG, bdiscountflag);
  }

  public void setBfreecustflag(UFBoolean bfreecustflag) {
    this.setAttributeValue(SaleInvoiceBVO.BFREECUSTFLAG, bfreecustflag);
  }

  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(SaleInvoiceBVO.BLABORFLAG, blaborflag);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SaleInvoiceBVO.BLARGESSFLAG, blargessflag);
  }

  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SaleInvoiceBVO.CARORGID, carorgid);
  }

  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SaleInvoiceBVO.CARORGVID, carorgvid);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SaleInvoiceBVO.CASTUNITID, castunitid);
  }

  /**
   * ����������������
   * 
   * @param cchanneltypeid ������������
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SaleInvoiceBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  public void setCcostsubjid(String ccostsubjid) {
    this.setAttributeValue(SaleInvoiceBVO.CCOSTSUBJID, ccostsubjid);
  }

  public void setCctmanageid(String cctmanageid) {
    this.setAttributeValue(SaleInvoiceBVO.CCTMANAGEID, cctmanageid);
  }

  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SaleInvoiceBVO.CDEPTID, cdeptid);
  }

  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SaleInvoiceBVO.CDEPTVID, cdeptvid);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SaleInvoiceBVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SaleInvoiceBVO.CFIRSTBID, cfirstbid);
  }

  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SaleInvoiceBVO.CFIRSTID, cfirstid);
  }

  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SaleInvoiceBVO.CFREECUSTID, cfreecustid);
  }

  public void setCmarbascalssid(String cmarbascalssid) {
    this.setAttributeValue(SaleInvoiceBVO.CMARBASCALSSID, cmarbascalssid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SaleInvoiceBVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SaleInvoiceBVO.CMATERIALVID, cmaterialvid);
  }

  public void setCopposesrcbid(String copposesrcbid) {
    this.setAttributeValue(SaleInvoiceBVO.COPPOSESRCBID, copposesrcbid);
  }

  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SaleInvoiceBVO.CORDERCUSTID, cordercustid);
  }

  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(SaleInvoiceBVO.CPRODLINEID, cprodlineid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SaleInvoiceBVO.CPRODUCTORID, cproductorid);
  }

  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SaleInvoiceBVO.CPROFITCENTERID, cprofitcenterid);
  }

  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SaleInvoiceBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SaleInvoiceBVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SaleInvoiceBVO.CQTUNITID, cqtunitid);
  }

  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(SaleInvoiceBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(SaleInvoiceBVO.CRECEIVECUSTID, creceivecustid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SaleInvoiceBVO.CROWNO, crowno);
  }

  public void setCsaleinvoicebid(String csaleinvoicebid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEINVOICEBID, csaleinvoicebid);
  }

  public void setCsaleinvoiceid(String csaleinvoiceid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEINVOICEID, csaleinvoiceid);
  }

  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEORGID, csaleorgid);
  }

  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEORGVID, csaleorgvid);
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SaleInvoiceBVO.CSENDSTORDOCID, csendstordocid);
  }

  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SaleInvoiceBVO.CSRCBID, csrcbid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SaleInvoiceBVO.CSRCID, csrcid);
  }

  public void setCsumid(String csumid) {
    this.setAttributeValue(SaleInvoiceBVO.CSUMID, csumid);
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SaleInvoiceBVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCtransporttypeid(String ctransporttypeid) {
    this.setAttributeValue(SaleInvoiceBVO.CTRANSPORTTYPEID, ctransporttypeid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(SaleInvoiceBVO.CUNITID, cunitid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SaleInvoiceBVO.CVENDORID, cvendorid);
  }

  public void setCvmivenderid(String cvmivenderid) {
    this.setAttributeValue(SaleInvoiceBVO.CVMIVENDERID, cvmivenderid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SaleInvoiceBVO.DBILLDATE, dbilldate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SaleInvoiceBVO.DR, dr);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SaleInvoiceBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SaleInvoiceBVO.NASTNUM, nastnum);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNcanoutnum(UFDouble ncanoutnum) {
    this.setAttributeValue(SaleInvoiceBVO.NCANOUTNUM, ncanoutnum);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SaleInvoiceBVO.NDISCOUNT, ndiscount);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SaleInvoiceBVO.NDISCOUNTRATE, ndiscountrate);
  }

  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGLOBALMNY, nglobalmny);
  }

  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGROUPMNY, ngroupmny);
  }

  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  public void setNinvoicedisrate(UFDouble ninvoicedisrate) {
    this.setAttributeValue(SaleInvoiceBVO.NINVOICEDISRATE, ninvoicedisrate);
  }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SaleInvoiceBVO.NMNY, nmny);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NNETPRICE, nnetprice);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SaleInvoiceBVO.NNUM, nnum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGPRICE, norigprice);
  }

  public void setNorigsubmny(UFDouble norigsubmny) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGSUBMNY, norigsubmny);
  }

  // public void setNorigtax(UFDouble norigtax) {
  // this.setAttributeValue(SaleInvoiceBVO.NORIGTAX, norigtax);
  // }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SaleInvoiceBVO.NPRICE, nprice);
  }

  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTNETPRICE, nqtnetprice);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(SaleInvoiceBVO.NQTUNITNUM, nqtunitnum);
  }

  public void setNshouldoutnum(UFDouble nshouldoutnum) {
    this.setAttributeValue(SaleInvoiceBVO.NSHOULDOUTNUM, nshouldoutnum);
  }

  public void setNsrcnum(UFDouble nsrcnum) {
    this.setAttributeValue(SaleInvoiceBVO.NSRCNUM, nsrcnum);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SaleInvoiceBVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXNETPRICE, ntaxnetprice);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXRATE, ntaxrate);
  }

  public void setNtotalcostnum(UFDouble ntotalcostnum) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALCOSTNUM, ntotalcostnum);
  }

  public void setNtotalincomemny(UFDouble ntotalincomemny) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALINCOMEMNY, ntotalincomemny);
  }

  public void setNtotalincomenum(UFDouble ntotalincomenum) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALINCOMENUM, ntotalincomenum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALPAYMNY, ntotalpaymny);
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SaleInvoiceBVO.PK_BATCHCODE, pk_batchcode);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SaleInvoiceBVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SaleInvoiceBVO.PK_ORG, pk_org);
  }

  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(SaleInvoiceBVO.SRCBTS, srcbts);
  }

  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(SaleInvoiceBVO.SRCTS, srcts);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SaleInvoiceBVO.TS, ts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SaleInvoiceBVO.VBATCHCODE, vbatchcode);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF9, vbdef9);
  }

  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SaleInvoiceBVO.VCHANGERATE, vchangerate);
  }

  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTCODE, vfirstcode);
  }

  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTROWNO, vfirstrowno);
  }

  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTTYPE, vfirsttype);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE9, vfree9);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(SaleInvoiceBVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVrownote(String vrownote) {
    this.setAttributeValue(SaleInvoiceBVO.VROWNOTE, vrownote);
  }

  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCCODE, vsrccode);
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCROWNO, vsrcrowno);
  }

  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCTRANTYPE, vsrctrantype);
  }

  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCTYPE, vsrctype);
  }

  public void setVsumcode(String vsumcode) {
    this.setAttributeValue(SaleInvoiceBVO.VSUMCODE, vsumcode);
  }

  /**
   * ���÷�����������
   * 
   * @param csprofitcentervid ������������
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SaleInvoiceBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * ���÷����������İ汾
   * 
   * @param csprofitcenterid �����������İ汾
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SaleInvoiceBVO.CSPROFITCENTERID, csprofitcenterid);
  }
  
  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMFFILEID);
  }

  /**
   * ����������
   * 
   * @param ������
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SaleInvoiceBVO.CMFFILEID, cmffileid);
  }
  
}
