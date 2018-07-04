package nc.vo.so.m30.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * ���۶�������VO
 * 
 * @since 6.3
 * @version 2013-5-24 ����09:55:17
 * @author dongli2
 */
public class SaleOrderExternalBVO extends SuperVO {

  /**
   * ���л�
   */
  private static final long serialVersionUID = -8535812509240954618L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder_b");
    return meta;
  }

  /**
   * �ͻ����ϱ���
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * �Ƿ��Դ�������
   */
  public static final String BARRANGEDFLAG = "barrangedflag";

  /**
   * �������ر�
   */
  public static final String BBARSETTLEFLAG = "bbarsettleflag";

  /**
   * �ɱ�����ر�
   */
  public static final String BBCOSTSETTLEFLAG = "bbcostsettleflag";

  /**
   * ������
   */
  public static final String BBINDFLAG = "bbindflag";

  /**
   * ��Ʊ�ر�
   */
  public static final String BBINVOICENDFLAG = "bbinvoicendflag";

  /**
   * ����ر�
   */
  public static final String BBOUTENDFLAG = "bboutendflag";

  /**
   * �����ر�
   */
  public static final String BBSENDENDFLAG = "bbsendendflag";

  /**
   * bbsettleendflag
   */
  public static final String BBSETTLEENDFLAG = "bbsettleendflag";

  /**
   * �ۿ���
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * ���ת����
   */
  public static final String BJCZXSFLAG = "bjczxsflag";

  /**
   * ������
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * ��Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * bprerowcloseflag
   */
  public static final String BPREROWCLOSEFLAG = "bprerowcloseflag";

  /**
   * ����ó��
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * Ӧ����֯���°汾
   */
  public static final String CARORGID = "carorgid";

  /**
   * Ӧ����֯
   */
  public static final String CARORGVID = "carorgvid";

  /**
   * ����Դ������
   */
  public static final String CARRANGEPERSONID = "carrangepersonid";

  /**
   * ��λ
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * �������Ӧ��Դ������ID
   */
  public static final String CBINDSRCID = "cbindsrcid";

  /**
   * Ʒ��
   */
  public static final String CBRANDID = "cbrandid";

  /**
   * ��ͬ�ӱ�
   */
  public static final String CCTMANAGEBID = "cctmanagebid";

  /**
   * ��ͬ����
   */
  public static final String CCTMANAGEID = "cctmanageid";

  /**
   * ��λ��
   */
  public static final String CCURRENCYID = "ccurrencyid";

  /**
   * �����ж�Ӧ�˻���
   */
  public static final String CEXCHANGESRCRETID = "cexchangesrcretid";

  /**
   * ����
   */
  public static final String CFACTORYID = "cfactoryid";

  /**
   * Դͷ�����ӱ�
   */
  public static final String CFIRSTBID = "cfirstbid";

  /**
   * Դͷ��������
   */
  public static final String CFIRSTID = "cfirstid";

  /**
   * ��Ʒ�ж�Ӧ��Դ������ID
   */
  public static final String CLARGESSSRCID = "clargesssrcid";

  /**
   * �������°汾
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * ���ϱ���
   */
  public static final String CMATERIALVID = "cmaterialvid";

  /**
   * ԭ������
   */
  public static final String CORIGAREAID = "corigareaid";

  /**
   * ԭ����
   */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /**
   * �۸����
   */
  public static final String CPRICEFORMID = "cpriceformid";

  /**
   * �۸���Ŀ
   */
  public static final String CPRICEITEMID = "cpriceitemid";

  /**
   * ��Ŀ��
   */
  public static final String CPRICEITEMTABLEID = "cpriceitemtableid";

  /**
   * �۸�����
   */
  public static final String CPRICEPOLICYID = "cpricepolicyid";

  /**
   * ��Ʒ��
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * ��������
   */
  public static final String CPRODUCTORID = "cproductorid";

  /**
   * �����������°汾
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * ��������
   */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /**
   * ��Ŀ
   */
  public static final String CPROJECTID = "cprojectid";

  /**
   * ���۵�λ
   */
  public static final String CQTUNITID = "cqtunitid";

  /**
   * �����ȼ�
   */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /**
   * �ջ�����/����
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /**
   * �ջ��ص�
   */
  public static final String CRECEIVEADDDOCID = "creceiveadddocid";

  /**
   * �ջ���ַ
   */
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  /**
   * �ջ�����
   */
  public static final String CRECEIVEAREAID = "creceiveareaid";

  /**
   * �ջ��ͻ�
   */
  public static final String CRECEIVECUSTID = "creceivecustid";

  /**
   * �˻�����
   */
  public static final String CRETPOLICYID = "cretpolicyid";

  /**
   * �˻�ԭ��
   */
  public static final String CRETREASONID = "cretreasonid";

  /**
   * �к�
   */
  public static final String CROWNO = "crowno";

  /**
   * ���۶�������
   */
  public static final String CSALEORDERBID = "csaleorderbid";

  /**
   * ���۶�������_����
   */
  public static final String CSALEORDERID = "csaleorderid";

  /**
   * ��������/����
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * ���������֯���°汾
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * ���������֯
   */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /**
   * �����ֿ�
   */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /**
   * ���������֯���°汾
   */
  public static final String CSETTLEORGID = "csettleorgid";

  /**
   * ���������֯
   */
  public static final String CSETTLEORGVID = "csettleorgvid";

  /**
   * ��Դ���ݸ���
   */
  public static final String CSRCBID = "csrcbid";

  /**
   * ��Դ��������
   */
  public static final String CSRCID = "csrcid";

  /**
   * ˰��
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * ��˰����/����
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * ������֯���°汾
   */
  public static final String CTRAFFICORGID = "ctrafficorgid";

  /**
   * ������֯
   */
  public static final String CTRAFFICORGVID = "ctrafficorgvid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ��Ӧ��
   */
  public static final String CVENDORID = "cvendorid";

  /**
   * ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * dr
   */
  public static final String DR = "dr";

  /**
   * ��������
   */
  public static final String DRECEIVEDATE = "dreceivedate";

  /**
   * ��������
   */
  public static final String DSENDDATE = "dsenddate";

  /**
   * ��������
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /**
   * ��Ʒ�۸��̯��ʽ
   */
  public static final String FLARGESSTYPEFLAG = "flargesstypeflag";

  /**
   * �˻������
   */
  public static final String FRETEXCHANGE = "fretexchange";

  /**
   * ��״̬
   */
  public static final String FROWSTATUS = "frowstatus";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  public static final String METAPATH = "so_saleorder_b.";

  /**
   * �ۼư���������������
   */
  public static final String NARRANGEMONUM = "narrangemonum";

  /**
   * �ۼư����빺������
   */
  public static final String NARRANGEPOAPPNUM = "narrangepoappnum";

  /**
   * �ۼư��Ųɹ���������
   */
  public static final String NARRANGEPONUM = "narrangeponum";

  /**
   * �ۼư���ί�ⶩ������
   */
  public static final String NARRANGESCORNUM = "narrangescornum";

  /**
   * �ۼư��ŵ�����������
   */
  public static final String NARRANGETOAPPNUM = "narrangetoappnum";

  /**
   * �ۼư��ŵ�����������
   */
  public static final String NARRANGETOORNUM = "narrangetoornum";

  /**
   * ѯ��ԭ����˰����
   */
  public static final String NASKQTORIGNETPRICE = "naskqtorignetprice";

  /**
   * ѯ��ԭ����˰����
   */
  public static final String NASKQTORIGPRICE = "naskqtorigprice";

  /**
   * ѯ��ԭ�Һ�˰����
   */
  public static final String NASKQTORIGTAXPRC = "naskqtorigtaxprc";

  /**
   * ѯ��ԭ�Һ�˰����
   */
  public static final String NASKQTORIGTXNTPRC = "naskqtorigtxntprc";

  /**
   * ����
   */
  public static final String NASTNUM = "nastnum";

  /**
   * ���ǰ���
   */
  public static final String NBFORIGSUBMNY = "nbforigsubmny";

  /**
   * ��˰���
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /**
   * �����ۿ۶�
   */
  public static final String NDISCOUNT = "ndiscount";

  /**
   * �����ۿ�
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /**
   * �۱�����
   */
  public static final String NEXCHANGERATE = "nexchangerate";

  /**
   * ȫ�ֱ�λ�һ���
   */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /**
   * ȫ�ֱ�����˰���
   */
  public static final String NGLOBALMNY = "nglobalmny";

  /**
   * ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /**
   * ���ű�λ�һ���
   */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /**
   * ���ű�����˰���
   */
  public static final String NGROUPMNY = "ngroupmny";

  /**
   * ���ű��Ҽ�˰�ϼ�
   */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /**
   * ninvoiceauditnum
   */
  public static final String NINVOICEAUDITNUM = "ninvoiceauditnum";

  /**
   * ninvunfinisednum
   */
  public static final String NINVUNFINISEDNUM = "ninvunfinisednum";

  /**
   * ��Ʒ�ۿ���
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * ��Ʒ�۸��̯ǰ��˰���
   */
  public static final String NLARGESSMNY = "nlargessmny";

  /**
   * ��Ʒ�۸��̯ǰ��˰�ϼ�
   */
  public static final String NLARGESSTAXMNY = "nlargesstaxmny";

  /**
   * nlossnotauditnum
   */
  public static final String NLOSSNOTAUDITNUM = "nlossnotauditnum";

  /**
   * ������˰���
   */
  public static final String NMNY = "nmny";

  /**
   * ��������˰����
   */
  public static final String NNETPRICE = "nnetprice";

  /**
   * nnotarnum
   */
  public static final String NNOTARNUM = "nnotarnum";

  /**
   * nnotcostnum
   */
  public static final String NNOTCOSTNUM = "nnotcostnum";

  /**
   * ������
   */
  public static final String NNUM = "nnum";

  /**
   * �ۿ۶�
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * ��˰���
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * ����˰����
   */
  public static final String NORIGNETPRICE = "norignetprice";

  /**
   * ����˰����
   */
  public static final String NORIGPRICE = "norigprice";

  /**
   * �ۼƳ�ֽ��
   */
  public static final String NORIGSUBMNY = "norigsubmny";

  /**
   * ��˰�ϼ�
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * ˰��
   */
  // public static final String NORIGTAX = "norigtax";

  /**
   * ����˰����
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * ����˰����
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * noutauditnum
   */
  public static final String NOUTAUDITNUM = "noutauditnum";

  /**
   * noutnotauditnum
   */
  public static final String NOUTNOTAUDITNUM = "noutnotauditnum";

  /**
   * noutunfinisednum
   */
  public static final String NOUTUNFINISEDNUM = "noutunfinisednum";

  /**
   * ����
   */
  public static final String NPIECE = "npiece";

  /**
   * ��������˰����
   */
  public static final String NPRICE = "nprice";

  /**
   * ������˰����
   */
  public static final String NQTNETPRICE = "nqtnetprice";

  /**
   * ��˰����
   */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /**
   * ��˰����
   */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /**
   * ��˰����
   */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /**
   * ��˰����
   */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  /**
   * ������˰����
   */
  public static final String NQTPRICE = "nqtprice";

  /**
   * ���Һ�˰����
   */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /**
   * ���Һ�˰����
   */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /**
   * ���۵�λ����
   */
  public static final String NQTUNITNUM = "nqtunitnum";

  /**
   * Ԥ������
   */
  public static final String NREQRSNUM = "nreqrsnum";

  /**
   * nsendauditnum
   */
  public static final String NSENDAUDITNUM = "nsendauditnum";

  /**
   * nsendunfinisednum
   */
  public static final String NSENDUNFINISEDNUM = "nsendunfinisednum";

  /**
   * ����˰��
   */
  public static final String NTAX = "ntax";

  /**
   * ���Ҽ�˰�ϼ�
   */
  public static final String NTAXMNY = "ntaxmny";

  /**
   * �����Һ�˰����
   */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /**
   * �����Һ�˰����
   */
  public static final String NTAXPRICE = "ntaxprice";

  /**
   * ˰��
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * �ۼ�ȷ��Ӧ�ս��
   */
  public static final String NTOTALARMNY = "ntotalarmny";

  /**
   * �ۼ�ȷ��Ӧ������
   */
  public static final String NTOTALARNUM = "ntotalarnum";

  /**
   * �ۼƳɱ���������
   */
  public static final String NTOTALCOSTNUM = "ntotalcostnum";

  /**
   * �ۼ��ݹ�Ӧ�ս��
   */
  public static final String NTOTALESTARMNY = "ntotalestarmny";

  /**
   * �ۼ��ݹ�Ӧ������
   */
  public static final String NTOTALESTARNUM = "ntotalestarnum";

  /**
   * �ۼƿ�Ʊ����
   */
  public static final String NTOTALINVOICENUM = "ntotalinvoicenum";

  /**
   * �ۼ�Ӧ��δ��������
   */
  public static final String NTOTALNOTOUTNUM = "ntotalnotoutnum";

  /**
   * �ۼƳ�������
   */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /**
   * �ۼƲ���������
   */
  public static final String NTOTALPAYMNY = "ntotalpaymny";

  /**
   * �ۼ����ɼƻ���������
   */
  public static final String NTOTALPLONUM = "ntotalplonum";

  /**
   * �ۼ��˻�����
   */
  public static final String NTOTALRETURNNUM = "ntotalreturnnum";

  /**
   * �ۼƳ���Գ�����
   */
  public static final String NTOTALRUSHNUM = "ntotalrushnum";

  /**
   * �ۼƷ�������
   */
  public static final String NTOTALSENDNUM = "ntotalsendnum";

  /**
   * �ۼ�ǩ������
   */
  public static final String NTOTALSIGNNUM = "ntotalsignnum";

  /**
   * �ۼƷ�����Ʒ����
   */
  public static final String NTOTALTRADENUM = "ntotaltradenum";

  /**
   * �ۼ�;������
   */
  public static final String NTRANSLOSSNUM = "ntranslossnum";

  /**
   * ���
   */
  public static final String NVOLUME = "nvolume";

  /**
   * ����
   */
  public static final String NWEIGHT = "nweight";

  /**
   * ���ε���
   */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * ������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * srcbts��������༭��
   */
  public static final String SRCBTS = "srcbts";

  /**
   * srcorgid��������༭��
   */
  public static final String SRCORGID = "srcorgid";

  /**
   * srcts��������༭��
   */
  public static final String SRCTS = "srcts";

  /**
   * ����Դ����ʱ��
   */
  public static final String TLASTARRANGETIME = "tlastarrangetime";

  /**
   * ʱ���
   */
  public static final String TS = "ts";

  /**
   * ����
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * �Զ�����1
   */
  public static final String VBDEF1 = "vbdef1";

  /**
   * �Զ�����10
   */
  public static final String VBDEF10 = "vbdef10";

  /**
   * �Զ�����11
   */
  public static final String VBDEF11 = "vbdef11";

  /**
   * �Զ�����12
   */
  public static final String VBDEF12 = "vbdef12";

  /**
   * �Զ�����13
   */
  public static final String VBDEF13 = "vbdef13";

  /**
   * �Զ�����14
   */
  public static final String VBDEF14 = "vbdef14";

  /**
   * �Զ�����15
   */
  public static final String VBDEF15 = "vbdef15";

  /**
   * �Զ�����16
   */
  public static final String VBDEF16 = "vbdef16";

  /**
   * �Զ�����17
   */
  public static final String VBDEF17 = "vbdef17";

  /**
   * �Զ�����18
   */
  public static final String VBDEF18 = "vbdef18";

  /**
   * �Զ�����19
   */
  public static final String VBDEF19 = "vbdef19";

  /**
   * �Զ�����2
   */
  public static final String VBDEF2 = "vbdef2";

  /**
   * �Զ�����20
   */
  public static final String VBDEF20 = "vbdef20";

  /**
   * �Զ�����3
   */
  public static final String VBDEF3 = "vbdef3";

  /**
   * �Զ�����4
   */
  public static final String VBDEF4 = "vbdef4";

  /**
   * �Զ�����5
   */
  public static final String VBDEF5 = "vbdef5";

  /**
   * �Զ�����6
   */
  public static final String VBDEF6 = "vbdef6";

  /**
   * �Զ�����7
   */
  public static final String VBDEF7 = "vbdef7";

  /**
   * �Զ�����8
   */
  public static final String VBDEF8 = "vbdef8";

  /**
   * �Զ�����9
   */
  public static final String VBDEF9 = "vbdef9";

  /**
   * �޶�����
   */
  public static final String VBREVISEREASON = "vbrevisereason";

  /**
   * ������
   */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * �ر�ԭ��
   */
  public static final String VCLOSEREASON = "vclosereason";

  /**
   * ���ۺ�ͬ��
   */
  public static final String VCTCODE = "vctcode";

  /**
   * vcttype
   */
  public static final String VCTTYPE = "vcttype";

  /**
   * Դͷ���ݺ�
   */
  public static final String VFIRSTCODE = "vfirstcode";

  /**
   * Դͷ�����к�
   */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /**
   * Դͷ��������
   */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /**
   * Դͷ��������
   */
  public static final String VFIRSTTYPE = "vfirsttype";

  /**
   * ���ɸ�������1
   */
  public static final String VFREE1 = "vfree1";

  /**
   * ���ɸ�������10
   */
  public static final String VFREE10 = "vfree10";

  /**
   * ���ɸ�������2
   */
  public static final String VFREE2 = "vfree2";

  /**
   * ���ɸ�������3
   */
  public static final String VFREE3 = "vfree3";

  /**
   * ���ɸ�������4
   */
  public static final String VFREE4 = "vfree4";

  /**
   * ���ɸ�������5
   */
  public static final String VFREE5 = "vfree5";

  /**
   * ���ɸ�������6
   */
  public static final String VFREE6 = "vfree6";

  /**
   * ���ɸ�������7
   */
  public static final String VFREE7 = "vfree7";

  /**
   * ���ɸ�������8
   */
  public static final String VFREE8 = "vfree8";

  /**
   * ���ɸ�������9
   */
  public static final String VFREE9 = "vfree9";

  /**
   * ���ۻ�����
   */
  public static final String VQTUNITRATE = "vqtunitrate";

  /**
   * �˻����δ���ʽ
   */
  public static final String VRETURNMODE = "vreturnmode";

  /**
   * �б�ע
   */
  public static final String VROWNOTE = "vrownote";

  /**
   * ��Դ���ݺ�
   */
  public static final String VSRCCODE = "vsrccode";

  /**
   * ��Դ�����к�
   */
  public static final String VSRCROWNO = "vsrcrowno";

  /**
   * ��Դ��������
   */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /**
   * ��Դ��������
   */
  public static final String VSRCTYPE = "vsrctype";

  /**
   * ��ȡ�к�
   * 
   * @return�к�
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(CROWNO);
  }

  /**
   * �����к�
   * 
   * @param crowno�к�
   */
  public void setCrowno(final String crowno) {
    this.setAttributeValue(CROWNO, crowno);
  }

  /**
   * ��ȡ���ϱ���
   * 
   * @return���ϱ���
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(CMATERIALVID);
  }

  /**
   * �������ϱ���
   * 
   * @param cmaterialvid ���ϱ���
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(CMATERIALVID, cmaterialvid);
  }

  /**
   * ��ȡ��Ӧ��
   * 
   * @return��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(CVENDORID);
  }

  /**
   * ���ù�Ӧ��
   * 
   * @param cvendorid��Ӧ��
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(CVENDORID, cvendorid);
  }

  /**
   * ��ȡ��Ŀ
   * 
   * @return��Ŀ
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(CPROJECTID);
  }

  /**
   * ������Ŀ
   * 
   * @param cprojectid
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(CPROJECTID, cprojectid);
  }

  /**
   * ��ȡ����
   * 
   * @return����
   */
  public String getCfactoryid() {
    return (String) this.getAttributeValue(CFACTORYID);
  }

  /**
   * ���ù���
   * 
   * @param cfactoryid
   */
  public void setCfactoryid(String cfactoryid) {
    this.setAttributeValue(CFACTORYID, cfactoryid);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(CPRODUCTORID);
  }

  /**
   * ������������
   * 
   * @param cproductorid��������
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(CPRODUCTORID, cproductorid);
  }

  /**
   * ��ȡ�����ȼ�
   * 
   * @return�����ȼ�
   */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(CQUALITYLEVELID);
  }

  /**
   * ���������ȼ�
   * 
   * @param cqualitylevelid�����ȼ�
   */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * ��ȡԭ����
   * 
   * @returnԭ����
   */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(CORIGCOUNTRYID);
  }

  /**
   * ����ԭ����
   * 
   * @param corigcountryid
   */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(CORIGCOUNTRYID, corigcountryid);
  }

  /**
   * ��ȡԭ������
   * 
   * @returnԭ������
   */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(CORIGAREAID);
  }

  /**
   * ����ԭ������
   * 
   * @param corigareaidԭ������
   */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(CORIGAREAID, corigareaid);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return�۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(NEXCHANGERATE);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(NEXCHANGERATE, nexchangerate);
  }

  /**
   * ��ȡ���κ�
   * 
   * @return���κ�
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(VBATCHCODE);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode���κ�
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(VBATCHCODE, vbatchcode);
  }

  /**
   * ��ȡ���ε���
   * 
   * @return���ε���
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(PK_BATCHCODE);
  }

  /**
   * �������ε���
   * 
   * @param pk_batchcode���ε���
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(PK_BATCHCODE, pk_batchcode);
  }

  /**
   * ��ȡ��Ʒ
   * 
   * @return��Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(BLARGESSFLAG);
  }

  /**
   * ������Ʒ
   * 
   * @param blargessflag��Ʒ
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(BLARGESSFLAG, blargessflag);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(DSENDDATE);
  }

  /**
   * ���÷�������
   * 
   * @param dsenddate��������
   */
  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(DSENDDATE, dsenddate);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(DRECEIVEDATE);
  }

  /**
   * ���õ�������
   * 
   * @param dreceivedate��������
   */
  public void setDreceivedate(UFDate dreceivedate) {
    this.setAttributeValue(DRECEIVEDATE, dreceivedate);
  }

  /**
   * ��ȡ�ջ��ͻ�
   * 
   * @return�ջ��ͻ�
   */
  public String getCreceivecustid() {
    return (String) this.getAttributeValue(CRECEIVECUSTID);
  }

  /**
   * �����ջ��ͻ�
   * 
   * @param creceivecustid�ջ��ͻ�
   */
  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(CRECEIVECUSTID, creceivecustid);
  }

  /**
   * ��ȡ�ջ�����
   * 
   * @return�ջ�����
   */
  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(CRECEIVEAREAID);
  }

  /**
   * �����ջ�����
   * 
   * @param creceiveareaid�ջ�����
   */
  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(CRECEIVEAREAID, creceiveareaid);
  }

  /**
   * ��ȡ�ջ��ص�
   * 
   * @return�ջ��ص�
   */
  public String getCreceiveadddocid() {
    return (String) this.getAttributeValue(CRECEIVEADDDOCID);
  }

  /**
   * �����ջ��ص�
   * 
   * @param creceiveadddocid�ջ��ص�
   */
  public void setCreceiveadddocid(String creceiveadddocid) {
    this.setAttributeValue(CRECEIVEADDDOCID, creceiveadddocid);
  }

  /**
   * ��ȡ�ջ���ַ
   * 
   * @return�ջ���ַ
   */
  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(CRECEIVEADDRID);
  }

  /**
   * �����ջ���ַ
   * 
   * @param creceiveaddrid�ջ���ַ
   */
  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(CRECEIVEADDRID, creceiveaddrid);
  }

  /**
   * ���÷��������֯
   * 
   * @return���������֯
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(CSENDSTOCKORGVID);
  }

  /**
   * ���÷��������֯
   * 
   * @param csendstockorgvid���������֯
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * ��ȡ�����ֿ�
   * 
   * @return�����ֿ�
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(CSENDSTORDOCID);
  }

  /**
   * ���÷����ֿ�
   * 
   * @param csendstordocid�����ֿ�
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(CSENDSTORDOCID, csendstordocid);
  }

  /**
   * ��ȡ������֯
   * 
   * @return������֯
   */
  public String getCtrafficorgvid() {
    return (String) this.getAttributeValue(CTRAFFICORGVID);
  }

  /**
   * ����������֯
   * 
   * @param ctrafficorgvid������֯
   */
  public void setCtrafficorgvid(String ctrafficorgvid) {
    this.setAttributeValue(CTRAFFICORGVID, ctrafficorgvid);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return���������֯
   */
  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(CSETTLEORGVID);
  }

  /**
   * ���ý��������֯
   * 
   * @param csettleorgvid���������֯
   */
  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(CSETTLEORGVID, csettleorgvid);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return�ջ�����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(CRECECOUNTRYID);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid�ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(CRECECOUNTRYID, crececountryid);
  }

  /**
   * ��ȡ��˰����/����
   * 
   * @return��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(CTAXCOUNTRYID);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(CSENDCOUNTRYID);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(FBUYSELLFLAG);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(BTRIATRADEFLAG);
  }

  /**
   * ��������ó��
   * 
   * @param btriatradeflag����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * ��ȡӦ����֯
   * 
   * @returnӦ����֯
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(CARORGVID);
  }

  /**
   * ����Ӧ����֯
   * 
   * @param carorgvidӦ����֯
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(CARORGVID, carorgvid);
  }

  /**
   * ��ȡ��������
   * 
   * @return��������
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(CPROFITCENTERVID);
  }

  /**
   * ������������
   * 
   * @param cprofitcentervid��������
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * ��ȡ�˻�ԭ��
   * 
   * @return�˻�ԭ��
   */
  public String getCretreasonid() {
    return (String) this.getAttributeValue(CRETREASONID);
  }

  /**
   * �����˻�ԭ��
   * 
   * @param cretreasonid �˻�ԭ��
   */
  public void setCretreasonid(final String cretreasonid) {
    this.setAttributeValue(CRETREASONID, cretreasonid);
  }

  /**
   * ��ȡ�˻����δ���ʽ
   * 
   * @return�˻����δ���ʽ
   */
  public String getVreturnmode() {
    return (String) this.getAttributeValue(VRETURNMODE);
  }

  /**
   * �����˻����δ���ʽ
   * 
   * @param vreturnmode�˻����δ���ʽ
   */
  public void setVreturnmode(final String vreturnmode) {
    this.setAttributeValue(VRETURNMODE, vreturnmode);
  }

  /**
   * ��ȡ�˻�����
   * 
   * @return�˻�����
   */
  public String getCretpolicyid() {
    return (String) this.getAttributeValue(CRETPOLICYID);
  }

  /**
   * �����˻�����
   * 
   * @param cretpolicyid�˻�����
   */
  public void setCretpolicyid(final String cretpolicyid) {
    this.setAttributeValue(CRETPOLICYID, cretpolicyid);
  }

  /**
   * ��ȡ���ۺ�ͬ��
   * 
   * @return ���ۺ�ͬ��
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(VCTCODE);
  }

  /**
   * �������ۺ�ͬ��
   * 
   * @param vctcode���ۺ�ͬ��
   */
  public void setVctcode(final String vctcode) {
    this.setAttributeValue(VCTCODE, vctcode);
  }

  /**
   * ��ȡ��ͬ����
   * 
   * @return��ͬ����
   */
  public String getCctmanageid() {
    return (String) this.getAttributeValue(CCTMANAGEID);
  }

  /**
   * ���ú�ͬ����
   * 
   * @param cctmanageid��ͬ����
   */
  public void setCctmanageid(final String cctmanageid) {
    this.setAttributeValue(CCTMANAGEID, cctmanageid);
  }

  /**
   * ��ȡ��ͬ�ӱ�
   * 
   * @return��ͬ�ӱ�
   */
  public String getCctmanagebid() {
    return (String) this.getAttributeValue(CCTMANAGEBID);
  }

  /**
   * ���ú�ͬ�ӱ�
   * 
   * @param cctmanageid��ͬ�ӱ�
   */
  public void setCctmanagebid(final String cctmanagebid) {
    this.setAttributeValue(CCTMANAGEBID, cctmanagebid);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return��Դ��������
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(VSRCTYPE);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctype��Դ��������
   */
  public void setVsrctype(final String vsrctype) {
    this.setAttributeValue(VSRCTYPE, vsrctype);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return��Դ��������
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(VSRCTRANTYPE);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctrantype��Դ��������
   */
  public void setVsrctrantype(final String vsrctrantype) {
    this.setAttributeValue(VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return��Դ��������
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(CSRCID);
  }

  /**
   * ������Դ��������
   * 
   * @param csrcid��Դ��������
   */
  public void setCsrcid(final String csrcid) {
    this.setAttributeValue(CSRCID, csrcid);
  }

  /**
   * ��ȡ��Դ���ݸ���
   * 
   * @return��Դ���ݸ���
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(CSRCBID);
  }

  /**
   * ������Դ���ݸ���
   * 
   * @param csrcbid��Դ���ݸ���
   */
  public void setCsrcbid(final String csrcbid) {
    this.setAttributeValue(CSRCBID, csrcbid);
  }

  /**
   * ��ȡ��Դ���ݺ�
   * 
   * @return��Դ���ݺ�
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(VSRCCODE);
  }

  /**
   * ������Դ���ݺ�
   * 
   * @param vsrccode��Դ���ݺ�
   */
  public void setVsrccode(final String vsrccode) {
    this.setAttributeValue(VSRCCODE, vsrccode);
  }

  /**
   * ������Դ���ݺ�
   * 
   * @return��Դ���ݺ�
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(VSRCROWNO);
  }

  /**
   * ��ȡ��Դ���ݺ�
   * 
   * @param vsrcrowno��Դ���ݺ�
   */
  public void setVsrcrowno(final String vsrcrowno) {
    this.setAttributeValue(VSRCROWNO, vsrcrowno);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @returnԴͷ��������
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(VFIRSTTYPE);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttypeԴͷ��������
   */
  public void setVfirsttype(final String vfirsttype) {
    this.setAttributeValue(VFIRSTTYPE, vfirsttype);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @returnԴͷ��������
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(VFIRSTTRANTYPE);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttrantypeԴͷ��������
   */
  public void setVfirsttrantype(final String vfirsttrantype) {
    this.setAttributeValue(VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * ��ȡԴͷ���ݺ�
   * 
   * @returnԴͷ���ݺ�
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(VFIRSTCODE);
  }

  /**
   * ����Դͷ���ݺ�
   * 
   * @param vfirstcodeԴͷ���ݺ�
   */
  public void setVfirstcode(final String vfirstcode) {
    this.setAttributeValue(VFIRSTCODE, vfirstcode);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @returnԴͷ��������
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(CFIRSTID);
  }

  /**
   * ����Դͷ��������
   * 
   * @param cfirstidԴͷ��������
   */
  public void setCfirstid(final String cfirstid) {
    this.setAttributeValue(CFIRSTID, cfirstid);
  }

  /**
   * Դͷ�����ӱ�
   * 
   * @returnԴͷ�����ӱ�
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(CFIRSTBID);
  }

  /**
   * Դͷ�����ӱ�
   * 
   * @param cfirstbidԴͷ�����ӱ�
   */
  public void setCfirstbid(final String cfirstbid) {
    this.setAttributeValue(CFIRSTBID, cfirstbid);
  }

  /**
   * Դͷ�����к�
   * 
   * @returnԴͷ�����к�
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(VFIRSTROWNO);
  }

  /**
   * Դͷ�����к�
   * 
   * @param vfirstrownoԴͷ�����к�
   */
  public void setVfirstrowno(final String vfirstrowno) {
    this.setAttributeValue(VFIRSTROWNO, vfirstrowno);
  }

  /**
   * ��ȡ���ɸ�������1
   * 
   * @return���ɸ�������1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(VFREE1);
  }

  /**
   * �������ɸ�������1
   * 
   * @return���ɸ�������2
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(VFREE1, vfree1);
  }

  /**
   * ��ȡ���ɸ�������2
   * 
   * @return���ɸ�������3
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(VFREE2);
  }

  /**
   * �������ɸ�������2
   * 
   * @return���ɸ�������2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(VFREE2, vfree2);
  }

  /**
   * ��ȡ���ɸ�������3
   * 
   * @return���ɸ�������3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(VFREE3);
  }

  /**
   * �������ɸ�������3
   * 
   * @param vfree3���ɸ�������3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(VFREE3, vfree3);
  }

  /**
   * ��ȡ���ɸ�������4
   * 
   * @return���ɸ�������4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(VFREE4);
  }

  /**
   * �������ɸ�������4
   * 
   * @param vfree3���ɸ�������4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(VFREE4, vfree4);
  }

  /**
   * ��ȡ���ɸ�������5
   * 
   * @return���ɸ�������5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(VFREE5);
  }

  /**
   * �������ɸ�������5
   * 
   * @param vfree3���ɸ�������5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(VFREE5, vfree5);
  }

  /**
   * ��ȡ���ɸ�������6
   * 
   * @return���ɸ�������6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(VFREE6);
  }

  /**
   * �������ɸ�������6
   * 
   * @param vfree6���ɸ�������6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(VFREE6, vfree6);
  }

  /**
   * ��ȡ���ɸ�������7
   * 
   * @return���ɸ�������7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(VFREE7);
  }

  /**
   * �������ɸ�������7
   * 
   * @param vfree7���ɸ�������7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(VFREE7, vfree7);
  }

  /**
   * ��ȡ���ɸ�������8
   * 
   * @return���ɸ�������8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(VFREE8);
  }

  /**
   * �������ɸ�������8
   * 
   * @param vfree8���ɸ�������8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(VFREE8, vfree8);
  }

  /**
   * ��ȡ���ɸ�������9
   * 
   * @return���ɸ�������9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(VFREE9);
  }

  /**
   * �������ɸ�������9
   * 
   * @param vfree9���ɸ�������9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(VFREE9, vfree9);
  }

  /**
   * ��ȡ���ɸ�������10
   * 
   * @return���ɸ�������10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(VFREE10);
  }

  /**
   * �������ɸ�������10
   * 
   * @param vfree10���ɸ�������10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(VFREE10, vfree10);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return�Զ�����1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(VBDEF1);
  }

  /**
   * �����Զ�����1
   * 
   * @param vbdef1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(VBDEF1, vbdef1);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return�Զ�����2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(VBDEF2);
  }

  /**
   * �����Զ�����2
   * 
   * @param vbdef2�Զ�����2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(VBDEF2, vbdef2);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return�Զ�����3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(VBDEF3);
  }

  /**
   * �����Զ�����3
   * 
   * @param vbdef3�Զ�����3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(VBDEF3, vbdef3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return�Զ�����4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(VBDEF4);
  }

  /**
   * �����Զ�����4
   * 
   * @param vbdef4�Զ�����4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(VBDEF4, vbdef4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return�Զ�����5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(VBDEF5);
  }

  /**
   * �����Զ�����5
   * 
   * @param vbdef5�Զ�����5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(VBDEF5, vbdef5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return�Զ�����6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(VBDEF6);
  }

  /**
   * �����Զ�����6
   * 
   * @param vbdef6�Զ�����6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(VBDEF6, vbdef6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return�Զ�����7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(VBDEF7);
  }

  /**
   * �����Զ�����7
   * 
   * @param vbdef7�Զ�����7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(VBDEF7, vbdef7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return�Զ�����8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(VBDEF8);
  }

  /**
   * �����Զ�����8
   * 
   * @param vbdef8�Զ�����8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(VBDEF8, vbdef8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return�Զ�����9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(VBDEF9);
  }

  /**
   * �����Զ�����9
   * 
   * @param vbdef9�Զ�����9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(VBDEF9, vbdef9);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return�Զ�����10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(VBDEF10);
  }

  /**
   * �����Զ�����10
   * 
   * @param vbdef10�Զ�����10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(VBDEF10, vbdef10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return�Զ�����11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(VBDEF11);
  }

  /**
   * �����Զ�����11
   * 
   * @param vbdef11�Զ�����11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(VBDEF11, vbdef11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return�Զ�����12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(VBDEF12);
  }

  /**
   * �����Զ�����12
   * 
   * @param vbdef12�Զ�����12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(VBDEF12, vbdef12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return�Զ�����13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(VBDEF13);
  }

  /**
   * �����Զ�����13
   * 
   * @param vbdef13�Զ�����13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(VBDEF13, vbdef13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return�Զ�����14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(VBDEF14);
  }

  /**
   * �����Զ�����14
   * 
   * @param vbdef14�Զ�����14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(VBDEF14, vbdef14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return�Զ�����15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(VBDEF15);
  }

  /**
   * �����Զ�����15
   * 
   * @param vbdef15�Զ�����15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(VBDEF15, vbdef15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return�Զ�����16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(VBDEF16);
  }

  /**
   * �����Զ�����16
   * 
   * @param vbdef16�Զ�����16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(VBDEF16, vbdef16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return�Զ�����17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(VBDEF17);
  }

  /**
   * �����Զ�����17
   * 
   * @param vbdef17�Զ�����17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(VBDEF17, vbdef17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return�Զ�����18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(VBDEF18);
  }

  /**
   * �����Զ�����18
   * 
   * @param vbdef18�Զ�����18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(VBDEF18, vbdef18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return�Զ�����19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(VBDEF19);
  }

  /**
   * �����Զ�����19
   * 
   * @param vbdef19�Զ�����19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(VBDEF19, vbdef19);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return�Զ�����20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(VBDEF20);
  }

  /**
   * �����Զ�����20
   * 
   * @param vbdef20�Զ�����20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(VBDEF20, vbdef20);
  }

  /**
   * ��ȡ�б�ע
   * 
   * @return�б�ע
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(VROWNOTE);
  }

  /**
   * �����б�ע
   * 
   * @param vrownote�б�ע
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(VROWNOTE, vrownote);
  }

  /**
   * ��ȡ�ͻ�������
   * 
   * @return�ͻ�������
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(CCUSTMATERIALID);
  }

  /**
   * ���ÿͻ�������
   * 
   * @param ccustmaterialid�ͻ�������
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ��λ
   * 
   * @return��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(CASTUNITID);
  }

  /**
   * ���õ�λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(CASTUNITID, castunitid);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(NNUM);
  }

  /**
   * ����������
   * 
   * @param nnum ������
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(NNUM, nnum);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(NASTNUM);
  }

  /**
   * ��������
   * 
   * @param nastnum ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(NASTNUM, nastnum);
  }

  /**
   * ��ȡ��Ʒ�ۿ�
   * 
   * @return ��Ʒ�ۿ�
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(NITEMDISCOUNTRATE);
  }

  /**
   * ���õ�Ʒ�ۿ�
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ�
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(NTAXRATE);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNatxrate(UFDouble ntaxrate) {
    this.setAttributeValue(NTAXRATE, ntaxrate);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return��˰����
   */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(NQTORIGTAXPRICE);
  }

  /**
   * ���ú�˰����
   * 
   * @param nqtorigtaxprice��˰����
   */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return��˰����
   */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(NQTORIGPRICE);
  }

  /**
   * ������˰����
   * 
   * @param nqtorigprice��˰����
   */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(NQTORIGPRICE, nqtorigprice);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return��˰����
   */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(NQTORIGTAXNETPRC);
  }

  /**
   * ���ú�˰����
   * 
   * @param nqtorigtaxnetprc��˰����
   */
  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return��˰����
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(NQTORIGNETPRICE);
  }

  /**
   * ������˰����
   * 
   * @param nqtorignetprice��˰����
   */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(NQTORIGNETPRICE, nqtorignetprice);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return��˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(NORIGMNY);
  }

  /**
   * ������˰���
   * 
   * @param norigmny��˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(NORIGMNY, norigmny);
  }

  /**
   * ��ȡ��˰�ϼ�
   * 
   * @return��˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(NORIGTAXMNY);
  }

  /**
   * ���ü�˰�ϼ�
   * 
   * @param norigtaxmny��˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��ȡ�ۿ۶�
   * 
   * @return�ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(NORIGDISCOUNT);
  }

  /**
   * �����ۿ۶�
   * 
   * @param norigdiscount�ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(NORIGDISCOUNT, norigdiscount);
  }

}
