package nc.vo.so.m30.entity;

import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;

/**
 * ���۶�������VO
 * 
 * @since 6.0
 * @version 2011-6-10 ����01:53:17
 * @author fengjb
 */
public class SaleOrderBVO extends SuperVO {

  /**
   * ����ǰ̨�ݴ�������ѡ��
   */
  private AggFFileVO aggffilevo;
  
  /** 
   * ������
   */
  public static final String CMFFILEID="cmffileid";
  
  /**
   * ������
   */
  public static final String NMFFILEPRICE="nmffileprice";
  
  /**
   * ��Ʒ�Ҹ���
   */
  public static final String BLRGCASHFLAG = "blrgcashflag";

  /**
   * �����˵���
   */
  public static final String NACCPRICE = "naccprice";
  /**
   * add by lyw 2017-6-12
   * ��������
   */
  public static final String JIAOHUODATE ="jiaohuodate";
  
  public static UFDate jiaohuodate;
  
  /**
   * ��ȡ�����˵���
   * 
   * @return �����˵���
   */
  public UFDouble getNaccprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NACCPRICE);
  }

  /**
   * ���������˵���
   * 
   * @param naccprice
   */
  public void setNaccprice(UFDouble naccprice) {
    this.setAttributeValue(SaleOrderBVO.NACCPRICE, naccprice);
  }

  /**
   * ��ȡ��Ʒ�Ҹ�
   * 
   * @return ��Ʒ�Ҹ�
   */
  public UFBoolean getBlrgcashflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BLRGCASHFLAG);
  }

  /**
   * ������Ʒ�Ҹ�
   * 
   * @param blrgcashflag
   */
  public void setBlrgcashflag(UFBoolean blrgcashflag) {
    this.setAttributeValue(SaleOrderBVO.BLRGCASHFLAG, blrgcashflag);
  }

  /**
   * �ͻ����ϱ���
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * ���ÿͻ����ϱ���
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SaleOrderBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCUSTMATERIALID);
  }

  /**
   * �����
   */
  public static final String CBUYLARGESSACTID = "cbuylargessactid";

  /**
   * �۸�����
   */
  public static final String CPRICEPROMTACTID = "cpricepromtactid";

  /**
   * ��������ID
   */
  public static final String CBUYLARGESSID = "cbuylargessid";

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
   * ���������������°汾
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * ������������
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
   * �ۼư��Ž��ں�ͬ������
   */
  public static final String NARRANGEITCNUM = "narrangeitcnum";

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
   * ������������
   */
  public static final String CBUYPROMOTTYPEID = "cbuypromottypeid";

  /**
   * ѯ�۴�������
   */
  public static final String CPRCPROMOTTYPEID = "cprcpromottypeid";

  /**
   * �ͻ�������
   */
  public static final String VCUSTOMBILLCODE = "vcustombillcode";
  
  /**
   * ���������������°汾
   */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /**
   * ������������
   */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";
  
  /**
   * �����۸����ID
   */
  public static final String CPROMOTPRICEID = "cpromotpriceid";
  
  
  /**
   * ���������ֶ� modified by wangzym 2017-01-18
   * */
  public static String bidding_no;
  public static String project_name;
  public static String Project_content;
  public static String supplier_requirements;
  public static UFDouble sumplannum;
  public static UFDouble sumnum;
  public static String typeplan;
  public static String typebuy;
  public static UFDouble ratereply;
  public static String bid_evaluation;
  public static String combination_standard;
  public static String procurementplan;
  public static String num_bj;
  public static String seq_bj;
  public static String offer_type;
  public static String qualification_way;
  public static String payment;
  public static String business_types;
  public static String procurement;
  public static String estimate;
  public static String delivery_term;
  public static String requirements;
  public static String supplier_code;
  public static String supplier;
  public static String no_delegate;
  public static String seq_delegate;
  public static String ccategoryid;
  public static String projectexecutor;
  public static String no_pasdoc;
  public static String customer_no;
  public static String customer_name;
  public static Integer plan_num;
  public static UFDate request_date;
  public static String host_name;
  public static String material;
  public static String rated_life;
  public static String manufacturer;
  public static UFDouble plan_pricea;
  public static UFDouble plan_priceb;
  public static UFDouble plansum_pricea;
  public static UFDouble Plansum_priceb;
  public static String factory_plan;
  public static String factory_code;
  public static String factory_name;
  public static String plan;
  public static String application_no;
  public static String application_line;
  public static String number_code;
  public static String tally;
  public static UFDate plan_time;
  public static UFDouble freight;
  public static UFDouble added_tax;
  public static UFDouble exchange_rate;
  public static String currency;
  public static String unit_leaders;
  public static String unit_sales;
  public static String unit_charge;
  public static String epein;
  public static String slysfs;
  public static String xlysfs;
  public static String ysfs;
  public static UFDate htqdsj;

  /**
   * �����������ֶ� modified by wangzym 2017-03-01
   * */
  public static  String bjwlmc;
  
  public static  String materialnamex; 
  public static  String  chand;
  public static UFDouble cgjg;
  public static Integer csjhq;

  
  
  
  
  
  
  
  
  
  
  

  private static final long serialVersionUID = -8352354807354004228L;

  /**
   * ��ȡ�����
   * 
   * @return �����
   */
  public String getCbuylargessactid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBUYLARGESSACTID);
  }

  /**
   * ��ȡ�۸�����
   * 
   * @return �۸�����
   */
  public String getCpricepromtactid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEPROMTACTID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getCbuylargessid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBUYLARGESSID);
  }

  /**
   * ��ȡ�Ƿ��Դ�������
   * 
   * @return �Ƿ��Դ�������
   */
  public UFBoolean getBarrangedflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BARRANGEDFLAG);
  }

  /**
   * ��ȡ�������ر�
   * 
   * @return �������ر�
   */
  public UFBoolean getBbarsettleflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBARSETTLEFLAG);
  }

  /**
   * ��ȡ�ɱ�����ر�
   * 
   * @return �ɱ�����ر�
   */
  public UFBoolean getBbcostsettleflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBCOSTSETTLEFLAG);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFBoolean getBbindflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBINDFLAG);
  }

  /**
   * ��ȡ��Ʊ�ر�
   * 
   * @return ��Ʊ�ر�
   */
  public UFBoolean getBbinvoicendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBINVOICENDFLAG);
  }

  /**
   * ��ȡ����ر�
   * 
   * @return ����ر�
   */
  public UFBoolean getBboutendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBOUTENDFLAG);
  }

  /**
   * ��ȡ�����ر�
   * 
   * @return �����ر�
   */
  public UFBoolean getBbsendendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBSENDENDFLAG);
  }

  /**
   * ��ȡbbsettleendflag
   * 
   * @return bbsettleendflag
   */
  public UFBoolean getBbsettleendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBSETTLEENDFLAG);
  }

  /**
   * ��ȡ�ۿ���
   * 
   * @return �ۿ���
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BDISCOUNTFLAG);
  }

  /**
   * ��ȡ���ת����
   * 
   * @return ���ת����
   */
  public UFBoolean getBjczxsflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BJCZXSFLAG);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BLABORFLAG);
  }

  /**
   * ��ȡ��Ʒ
   * 
   * @return ��Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BLARGESSFLAG);
  }

  /**
   * ��ȡbprerowcloseflag
   * 
   * @return bprerowcloseflag
   */
  public UFBoolean getBprerowcloseflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BPREROWCLOSEFLAG);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return ����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BTRIATRADEFLAG);
  }

  /**
   * ��ȡӦ����֯���°汾
   * 
   * @return Ӧ����֯���°汾
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CARORGID);
  }

  /**
   * ��ȡӦ����֯
   * 
   * @return Ӧ����֯
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CARORGVID);
  }

  /**
   * ��ȡ����Դ������
   * 
   * @return ����Դ������
   */
  public String getCarrangepersonid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CARRANGEPERSONID);
  }

  /**
   * ��ȡ��λ
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CASTUNITID);
  }

  /**
   * ��ȡ�������Ӧ��Դ������ID
   * 
   * @return �������Ӧ��Դ������ID
   */
  public String getCbindsrcid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBINDSRCID);
  }

  /**
   * ��ȡƷ��
   * 
   * @return Ʒ��
   */
  public String getCbrandid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBRANDID);
  }

  /**
   * ��ȡ��ͬ�ӱ�
   * 
   * @return ��ͬ�ӱ�
   */
  public String getCctmanagebid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCTMANAGEBID);
  }

  /**
   * ��ȡ��ͬ����
   * 
   * @return ��ͬ����
   */
  public String getCctmanageid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCTMANAGEID);
  }

  /**
   * ��ȡ��λ��
   * 
   * @return ��λ��
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCURRENCYID);
  }

  /**
   * ��ȡ�����ж�Ӧ�˻���
   * 
   * @return �����ж�Ӧ�˻���
   */
  public String getCexchangesrcretid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CEXCHANGESRCRETID);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCfactoryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CFACTORYID);
  }

  /**
   * ��ȡԴͷ�����ӱ�
   * 
   * @return Դͷ�����ӱ�
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CFIRSTBID);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CFIRSTID);
  }

  /**
   * ��ȡ��Ʒ�ж�Ӧ��Դ������ID
   * 
   * @return ��Ʒ�ж�Ӧ��Դ������ID
   */
  public String getClargesssrcid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CLARGESSSRCID);
  }

  /**
   * ��ȡ�������°汾
   * 
   * @return �������°汾
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CMATERIALID);
  }

  /**
   * ��ȡ���ϱ���
   * 
   * @return ���ϱ���
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CMATERIALVID);
  }

  /**
   * ��ȡԭ������
   * 
   * @return ԭ������
   */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CORIGAREAID);
  }

  /**
   * ��ȡԭ����
   * 
   * @return ԭ����
   */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CORIGCOUNTRYID);
  }

  /**
   * ��ȡ�۸����
   * 
   * @return �۸����
   */
  public String getCpriceformid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEFORMID);
  }

  /**
   * ��ȡ�۸���Ŀ
   * 
   * @return �۸���Ŀ
   */
  public String getCpriceitemid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEITEMID);
  }

  /**
   * ��ȡ��Ŀ��
   * 
   * @return ��Ŀ��
   */
  public String getCpriceitemtableid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEITEMTABLEID);
  }

  /**
   * ��ȡ�۸�����
   * 
   * @return �۸�����
   */
  public String getCpricepolicyid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEPOLICYID);
  }

  /**
   * ��ȡ��Ʒ��
   * 
   * @return ��Ʒ��
   */
  public String getCprodlineid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRODLINEID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRODUCTORID);
  }

  /**
   * ��ȡ���������������°汾
   * 
   * @return ���������������°汾
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROFITCENTERID);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROFITCENTERVID);
  }

  /**
   * ��ȡ��Ŀ
   * 
   * @return ��Ŀ
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROJECTID);
  }

  /**
   * ��ȡ���۵�λ
   * 
   * @return ���۵�λ
   */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CQTUNITID);
  }

  /**
   * ��ȡ�����ȼ�
   * 
   * @return �����ȼ�
   */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CQUALITYLEVELID);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return �ջ����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECECOUNTRYID);
  }

  /**
   * ��ȡ�ջ��ص�
   * 
   * @return �ջ��ص�
   */
  public String getCreceiveadddocid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVEADDDOCID);
  }

  /**
   * ��ȡ�ջ���ַ
   * 
   * @return �ջ���ַ
   */
  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVEADDRID);
  }

  /**
   * ��ȡ�ջ�����
   * 
   * @return �ջ�����
   */
  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVEAREAID);
  }

  /**
   * ��ȡ�ջ��ͻ�
   * 
   * @return �ջ��ͻ�
   */
  public String getCreceivecustid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVECUSTID);
  }

  /**
   * ��ȡ�˻�����
   * 
   * @return �˻�����
   */
  public String getCretpolicyid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRETPOLICYID);
  }

  /**
   * ��ȡ�˻�ԭ��
   * 
   * @return �˻�ԭ��
   */
  public String getCretreasonid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRETREASONID);
  }

  /**
   * ��ȡ�к�
   * 
   * @return �к�
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(SaleOrderBVO.CROWNO);
  }

  /**
   * ��ȡ���۶�������
   * 
   * @return ���۶�������
   */
  public String getCsaleorderbid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
  }

  /**
   * ��ȡ���۶�������_����
   * 
   * @return ���۶�������_����
   */
  public String getCsaleorderid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSALEORDERID);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return ��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDCOUNTRYID);
  }

  /**
   * ��ȡ���������֯���°汾
   * 
   * @return ���������֯���°汾
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGVID);
  }

  /**
   * ��ȡ�����ֿ�
   * 
   * @return �����ֿ�
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
  }

  /**
   * ��ȡ���������֯���°汾
   * 
   * @return ���������֯���°汾
   */
  public String getCsettleorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSETTLEORGVID);
  }

  /**
   * ��ȡ��Դ���ݸ���
   * 
   * @return ��Դ���ݸ���
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSRCBID);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSRCID);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTAXCODEID);
  }

  /**
   * ��ȡ��˰���Һ͵���
   * 
   * @return ��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTAXCOUNTRYID);
  }

  /**
   * ��ȡ������֯���°汾
   * 
   * @return ������֯���°汾
   */
  public String getCtrafficorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTRAFFICORGID);
  }

  /**
   * ��ȡ������֯
   * 
   * @return ������֯
   */
  public String getCtrafficorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTRAFFICORGVID);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CUNITID);
  }

  /**
   * ��ȡ��Ӧ��
   * 
   * @return ��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CVENDORID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SaleOrderBVO.DBILLDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SaleOrderHVO.DR);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(SaleOrderBVO.DRECEIVEDATE);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(SaleOrderBVO.DSENDDATE);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FBUYSELLFLAG);
  }

  /**
   * ��ȡ��Ʒ�۸��̯��ʽ
   * 
   * @return ��Ʒ�۸��̯��ʽ
   * @see Largesstype
   */
  public Integer getFlargesstypeflag() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FLARGESSTYPEFLAG);
  }

  /**
   * ��ȡ�˻������
   * 
   * @return �˻������
   * @see Fretexchange
   */
  public Integer getFretexchange() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FRETEXCHANGE);
  }

  /**
   * ��ȡ��״̬
   * 
   * @return ��״̬
   * @see BillStatus
   */
  public Integer getFrowstatus() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FROWSTATUS);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder_b");
    return meta;
  }

  /**
   * ��ȡ�ۼư���������������
   * 
   * @return �ۼư���������������
   */
  public UFDouble getNarrangemonum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEMONUM);
  }

  /**
   * ��ȡ�ۼư����빺������
   * 
   * @return �ۼư����빺������
   */
  public UFDouble getNarrangepoappnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEPOAPPNUM);
  }

  /**
   * ��ȡ�ۼư��Ųɹ���������
   * 
   * @return �ۼư��Ųɹ���������
   */
  public UFDouble getNarrangeponum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEPONUM);
  }

  /**
   * ��ȡ�ۼư���ί�ⶩ������
   * 
   * @return �ۼư���ί�ⶩ������
   */
  public UFDouble getNarrangescornum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGESCORNUM);
  }

  /**
   * ��ȡ�ۼư��ŵ�����������
   * 
   * @return �ۼư��ŵ�����������
   */
  public UFDouble getNarrangetoappnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGETOAPPNUM);
  }

  /**
   * ��ȡ�ۼư��ŵ�����������
   * 
   * @return �ۼư��ŵ�����������
   */
  public UFDouble getNarrangetoornum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGETOORNUM);
  }

  /**
   * ��ȡѯ��ԭ����˰����
   * 
   * @return ѯ��ԭ����˰����
   */
  public UFDouble getNaskqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGNETPRICE);
  }

  /**
   * ��ȡѯ��ԭ����˰����
   * 
   * @return ѯ��ԭ����˰����
   */
  public UFDouble getNaskqtorigprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGPRICE);
  }

  /**
   * ��ȡѯ��ԭ�Һ�˰����
   * 
   * @return ѯ��ԭ�Һ�˰����
   */
  public UFDouble getNaskqtorigtaxprc() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGTAXPRC);
  }

  /**
   * ��ȡѯ��ԭ�Һ�˰����
   * 
   * @return ѯ��ԭ�Һ�˰����
   */
  public UFDouble getNaskqtorigtxntprc() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGTXNTPRC);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASTNUM);
  }

  /**
   * ��ȡnbforigsubmny
   * 
   * @return nbforigsubmny
   */
  public UFDouble getNbforigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NBFORIGSUBMNY);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NCALTAXMNY);
  }

  /**
   * ��ȡ�����ۿ۶�
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NDISCOUNT);
  }

  /**
   * ��ȡ�����ۿ�
   * 
   * @return �����ۿ�
   */
  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NDISCOUNTRATE);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NEXCHANGERATE);
  }

  /**
   * ��ȡȫ�ֱ�λ�һ���
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGLOBALEXCHGRATE);
  }

  /**
   * ��ȡȫ�ֱ�����˰���
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGLOBALMNY);
  }

  /**
   * ��ȡȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @return ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGLOBALTAXMNY);
  }

  /**
   * ��ȡ���ű�λ�һ���
   * 
   * @return ���ű�λ�һ���
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGROUPEXCHGRATE);
  }

  /**
   * ��ȡ���ű�����˰���
   * 
   * @return ���ű�����˰���
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGROUPMNY);
  }

  /**
   * ��ȡ���ű��Ҽ�˰�ϼ�
   * 
   * @return ���ű��Ҽ�˰�ϼ�
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGROUPTAXMNY);
  }

  /**
   * ��ȡninvoiceauditnum
   * 
   * @return ninvoiceauditnum
   */
  public UFDouble getNinvoiceauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NINVOICEAUDITNUM);
  }

  /**
   * ��ȡninvunfinisednum
   * 
   * @return ninvunfinisednum
   */
  public UFDouble getNinvunfinisednum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NINVUNFINISEDNUM);
  }

  /**
   * ��ȡ��Ʒ�ۿ���
   * 
   * @return ��Ʒ�ۿ���
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NITEMDISCOUNTRATE);
  }

  /**
   * ��ȡ��Ʒ�۸��̯ǰ��˰���
   * 
   * @return ��Ʒ�۸��̯ǰ��˰���
   */
  public UFDouble getNlargessmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NLARGESSMNY);
  }

  /**
   * ��ȡ��Ʒ�۸��̯ǰ��˰�ϼ�
   * 
   * @return ��Ʒ�۸��̯ǰ��˰�ϼ�
   */
  public UFDouble getNlargesstaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NLARGESSTAXMNY);
  }

  /**
   * ��ȡnlossnotauditnum
   * 
   * @return nlossnotauditnum
   */
  public UFDouble getNlossnotauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NLOSSNOTAUDITNUM);
  }

  /**
   * ��ȡ������˰���
   * 
   * @return ������˰���
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NMNY);
  }

  /**
   * ��ȡ��������˰����
   * 
   * @return ��������˰����
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNETPRICE);
  }

  /**
   * ��ȡnnotarnum
   * 
   * @return nnotarnum
   */
  public UFDouble getNnotarnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNOTARNUM);
  }

  /**
   * ��ȡnnotcostnum
   * 
   * @return nnotcostnum
   */
  public UFDouble getNnotcostnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNOTCOSTNUM);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNUM);
  }

  /**
   * ��ȡ�ۿ۶�
   * 
   * @return �ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGDISCOUNT);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGMNY);
  }

  /**
   * ��ȡ����˰����
   * 
   * @return ����˰����
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGNETPRICE);
  }

  /**
   * ��ȡ����˰����
   * 
   * @return ����˰����
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGPRICE);
  }

  /**
   * ��ȡ�ۼƳ�ֽ��
   * 
   * @return �ۼƳ�ֽ��
   */
  public UFDouble getNorigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGSUBMNY);
  }

  /**
   * ��ȡ��˰�ϼ�
   * 
   * @return ��˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAXMNY);
  }

  /**
   * ��ȡ����˰����
   * 
   * @return ����˰����
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAXNETPRICE);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  // public UFDouble getNorigtax() {
  // return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAX);
  // }

  /**
   * ��ȡ����˰����
   * 
   * @return ����˰����
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAXPRICE);
  }

  /**
   * ��ȡnoutauditnum
   * 
   * @return noutauditnum
   */
  public UFDouble getNoutauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NOUTAUDITNUM);
  }

  /**
   * ��ȡnoutnotauditnum
   * 
   * @return noutnotauditnum
   */
  public UFDouble getNoutnotauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NOUTNOTAUDITNUM);
  }

  /**
   * ��ȡnoutunfinisednum
   * 
   * @return noutunfinisednum
   */
  public UFDouble getNoutunfinisednum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NOUTUNFINISEDNUM);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public UFDouble getNpiece() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NPIECE);
  }

  /**
   * ��ȡ��������˰����
   * 
   * @return ��������˰����
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NPRICE);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTNETPRICE);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return ��˰����
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGNETPRICE);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return ��˰����
   */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGPRICE);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return ��˰����
   */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGTAXNETPRC);
  }

  /**
   * ��ȡ��˰����
   * 
   * @return ��˰����
   */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGTAXPRICE);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTPRICE);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTTAXNETPRICE);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTTAXPRICE);
  }

  /**
   * ��ȡ���۵�λ����
   * 
   * @return ���۵�λ����
   */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTUNITNUM);
  }

  /**
   * ��ȡԤ������
   * 
   * @return Ԥ������
   */
  public UFDouble getNreqrsnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NREQRSNUM);
  }

  /**
   * ��ȡnsendauditnum
   * 
   * @return nsendauditnum
   */
  public UFDouble getNsendauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NSENDAUDITNUM);
  }

  /**
   * ��ȡnsendunfinisednum
   * 
   * @return nsendunfinisednum
   */
  public UFDouble getNsendunfinisednum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NSENDUNFINISEDNUM);
  }

  /**
   * ��ȡ����˰��
   * 
   * @return ����˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAX);
  }

  /**
   * ��ȡ���Ҽ�˰�ϼ�
   * 
   * @return ���Ҽ�˰�ϼ�
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXMNY);
  }

  /**
   * ��ȡ�����Һ�˰����
   * 
   * @return �����Һ�˰����
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXNETPRICE);
  }

  /**
   * ��ȡ�����Һ�˰����
   * 
   * @return �����Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXPRICE);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXRATE);
  }

  /**
   * ��ȡ�ۼ�ȷ��Ӧ�ս��
   * 
   * @return �ۼ�ȷ��Ӧ�ս��
   */
  public UFDouble getNtotalarmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALARMNY);
  }

  /**
   * ��ȡ�ۼ�ȷ��Ӧ������
   * 
   * @return �ۼ�ȷ��Ӧ������
   */
  public UFDouble getNtotalarnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALARNUM);
  }

  /**
   * ��ȡ�ۼƳɱ���������
   * 
   * @return �ۼƳɱ���������
   */
  public UFDouble getNtotalcostnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALCOSTNUM);
  }

  /**
   * ��ȡ�ۼ��ݹ�Ӧ�ս��
   * 
   * @return �ۼ��ݹ�Ӧ�ս��
   */
  public UFDouble getNtotalestarmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALESTARMNY);
  }

  /**
   * ��ȡ�ۼ��ݹ�Ӧ������
   * 
   * @return �ۼ��ݹ�Ӧ������
   */
  public UFDouble getNtotalestarnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALESTARNUM);
  }

  /**
   * ��ȡ�ۼƿ�Ʊ����
   * 
   * @return �ۼƿ�Ʊ����
   */
  public UFDouble getNtotalinvoicenum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALINVOICENUM);
  }

  /**
   * ��ȡ�ۼ�Ӧ��δ��������
   * 
   * @return �ۼ�Ӧ��δ��������
   */
  public UFDouble getNtotalnotoutnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALNOTOUTNUM);
  }

  /**
   * ��ȡ�ۼƳ�������
   * 
   * @return �ۼƳ�������
   */
  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALOUTNUM);
  }

  /**
   * ��ȡ�ۼƲ���������
   * 
   * @return �ۼƲ���������
   */
  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALPAYMNY);
  }

  /**
   * ��ȡ�ۼ����ɼƻ���������
   * 
   * @return �ۼ����ɼƻ���������
   */
  public UFDouble getNtotalplonum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALPLONUM);
  }

  /**
   * ��ȡ�ۼ��˻�����
   * 
   * @return �ۼ��˻�����
   */
  public UFDouble getNtotalreturnnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALRETURNNUM);
  }

  /**
   * ��ȡ�ۼƳ���Գ�����
   * 
   * @return �ۼƳ���Գ�����
   */
  public UFDouble getNtotalrushnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALRUSHNUM);
  }

  /**
   * ��ȡ�ۼƷ�������
   * 
   * @return �ۼƷ�������
   */
  public UFDouble getNtotalsendnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALSENDNUM);
  }

  /**
   * ��ȡ�ۼ�ǩ������
   * 
   * @return �ۼ�ǩ������
   */
  public UFDouble getNtotalsignnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALSIGNNUM);
  }

  /**
   * ��ȡ�ۼƷ�����Ʒ����
   * 
   * @return �ۼƷ�����Ʒ����
   */
  public UFDouble getNtotaltradenum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALTRADENUM);
  }

  /**
   * ��ȡ�ۼ�;������
   * 
   * @return �ۼ�;������
   */
  public UFDouble getNtranslossnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTRANSLOSSNUM);
  }

  /**
   * ��ȡ���
   * 
   * @return ���
   */
  public UFDouble getNvolume() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NVOLUME);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NWEIGHT);
  }

  /**
   * ��ȡ���ε���
   * 
   * @return ���ε���
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.PK_BATCHCODE);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SaleOrderBVO.PK_GROUP);
  }

  /**
   * ��ȡ������֯
   * 
   * @return ������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SaleOrderBVO.PK_ORG);
  }

  /**
   * ��ȡ��Դʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.SRCBTS);
  }

  /**
   * ��ȡ��Դ��֯
   * 
   * @return srcorgid
   */
  public String getSrcorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.SRCORGID);
  }

  /**
   * ��ȡ��Դʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.SRCTS);
  }

  /**
   * ��ȡ����Դ����ʱ��
   * 
   * @return ����Դ����ʱ��
   */
  public UFDateTime getTlastarrangetime() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.TLASTARRANGETIME);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.TS);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBATCHCODE);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return �Զ�����1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF1);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return �Զ�����10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return �Զ�����11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return �Զ�����12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return �Զ�����13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return �Զ�����14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return �Զ�����15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return �Զ�����16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return �Զ�����17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return �Զ�����18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return �Զ�����19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF19);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return �Զ�����2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF2);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return �Զ�����20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF20);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return �Զ�����3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return �Զ�����4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return �Զ�����5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return �Զ�����6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return �Զ�����7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return �Զ�����8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return �Զ�����9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF9);
  }

  /**
   * ��ȡ�޶�����
   * 
   * @return �޶�����
   */
  public String getVbrevisereason() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBREVISEREASON);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCHANGERATE);
  }

  /**
   * ��ȡ�ر�ԭ��
   * 
   * @return �ر�ԭ��
   */
  public String getVclosereason() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCLOSEREASON);
  }

  /**
   * ��ȡ���ۺ�ͬ��
   * 
   * @return ���ۺ�ͬ��
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCTCODE);
  }

  /**
   * ��ȡvcttype
   * 
   * @return vcttype
   */
  public String getVcttype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCTTYPE);
  }

  /**
   * ��ȡԴͷ���ݺ�
   * 
   * @return Դͷ���ݺ�
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTCODE);
  }

  /**
   * ��ȡԴͷ�����к�
   * 
   * @return Դͷ�����к�
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTROWNO);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTTRANTYPE);
  }
  
  /**
   * ��ȡ�����۸����ID
   * 
   * @return �����۸����ID
   */
  public String getCpromotpriceid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROMOTPRICEID);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTTYPE);
  }

  /**
   * ��ȡ���ɸ�������1
   * 
   * @return ���ɸ�������1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE1);
  }

  /**
   * ��ȡ���ɸ�������10
   * 
   * @return ���ɸ�������10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE10);
  }

  /**
   * ��ȡ���ɸ�������2
   * 
   * @return ���ɸ�������2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE2);
  }

  /**
   * ��ȡ���ɸ�������3
   * 
   * @return ���ɸ�������3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE3);
  }

  /**
   * ��ȡ���ɸ�������4
   * 
   * @return ���ɸ�������4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE4);
  }

  /**
   * ��ȡ���ɸ�������5
   * 
   * @return ���ɸ�������5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE5);
  }

  /**
   * ��ȡ���ɸ�������6
   * 
   * @return ���ɸ�������6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE6);
  }

  /**
   * ��ȡ���ɸ�������7
   * 
   * @return ���ɸ�������7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE7);
  }

  /**
   * ��ȡ���ɸ�������8
   * 
   * @return ���ɸ�������8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE8);
  }

  /**
   * ��ȡ���ɸ�������9
   * 
   * @return ���ɸ�������9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE9);
  }

  /**
   * ��ȡ���ۻ�����
   * 
   * @return ���ۻ�����
   */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(SaleOrderBVO.VQTUNITRATE);
  }

  /**
   * ��ȡ�˻�����
   * 
   * @return �˻�����
   */
  public String getVreturnmode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VRETURNMODE);
  }

  /**
   * ��ȡ�б�ע
   * 
   * @return �б�ע
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SaleOrderBVO.VROWNOTE);
  }

  /**
   * ��ȡ��Դ���ݺ�
   * 
   * @return ��Դ���ݺ�
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCCODE);
  }

  /**
   * ��ȡ��Դ�����к�
   * 
   * @return ��Դ�����к�
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCROWNO);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCTRANTYPE);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCTYPE);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCbuypromottypeid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBUYPROMOTTYPEID);
  }

  /**
   * ��ȡѯ�۴�������
   * 
   * @return ѯ�۴�������
   */
  public String getCprcpromottypeid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRCPROMOTTYPEID);
  }

  /**
   * ��ȡ�ͻ����ݺ�
   * 
   * @return �ͻ����ݺ�
   */
  public String getVcustombillcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCUSTOMBILLCODE);
  }

  /**
   * ��ȡ���������������°汾
   * 
   * @return ���������������°汾
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSPROFITCENTERID);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSPROFITCENTERVID);
  }

  /**
   * �����Ƿ��Դ�������
   * 
   * @param barrangedflag �Ƿ��Դ�������
   */
  public void setBarrangedflag(UFBoolean barrangedflag) {
    this.setAttributeValue(SaleOrderBVO.BARRANGEDFLAG, barrangedflag);
  }

  /**
   * �����������ر�
   * 
   * @param bbarsettleflag �������ر�
   */
  public void setBbarsettleflag(UFBoolean bbarsettleflag) {
    this.setAttributeValue(SaleOrderBVO.BBARSETTLEFLAG, bbarsettleflag);
  }

  /**
   * ���óɱ�����ر�
   * 
   * @param bbcostsettleflag �ɱ�����ر�
   */
  public void setBbcostsettleflag(UFBoolean bbcostsettleflag) {
    this.setAttributeValue(SaleOrderBVO.BBCOSTSETTLEFLAG, bbcostsettleflag);
  }

  /**
   * ����������
   * 
   * @param bbindflag ������
   */
  public void setBbindflag(UFBoolean bbindflag) {
    this.setAttributeValue(SaleOrderBVO.BBINDFLAG, bbindflag);
  }

  /**
   * ���ÿ�Ʊ�ر�
   * 
   * @param bbinvoicendflag ��Ʊ�ر�
   */
  public void setBbinvoicendflag(UFBoolean bbinvoicendflag) {
    this.setAttributeValue(SaleOrderBVO.BBINVOICENDFLAG, bbinvoicendflag);
  }

  /**
   * ���ó���ر�
   * 
   * @param bboutendflag ����ر�
   */
  public void setBboutendflag(UFBoolean bboutendflag) {
    this.setAttributeValue(SaleOrderBVO.BBOUTENDFLAG, bboutendflag);
  }

  /**
   * ���÷����ر�
   * 
   * @param bbsendendflag �����ر�
   */
  public void setBbsendendflag(UFBoolean bbsendendflag) {
    this.setAttributeValue(SaleOrderBVO.BBSENDENDFLAG, bbsendendflag);
  }

  /**
   * ����bbsettleendflag
   * 
   * @param bbsettleendflag bbsettleendflag
   */
  public void setBbsettleendflag(UFBoolean bbsettleendflag) {
    this.setAttributeValue(SaleOrderBVO.BBSETTLEENDFLAG, bbsettleendflag);
  }

  /**
   * �����ۿ���
   * 
   * @param bdiscountflag �ۿ���
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(SaleOrderBVO.BDISCOUNTFLAG, bdiscountflag);
  }

  /**
   * ���ý��ת����
   * 
   * @param bjczxsflag ���ת����
   */
  public void setBjczxsflag(UFBoolean bjczxsflag) {
    this.setAttributeValue(SaleOrderBVO.BJCZXSFLAG, bjczxsflag);
  }

  /**
   * ���÷�����
   * 
   * @param blaborflag ������
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(SaleOrderBVO.BLABORFLAG, blaborflag);
  }

  /**
   * ������Ʒ
   * 
   * @param blargessflag ��Ʒ
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SaleOrderBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * ����bprerowcloseflag
   * 
   * @param bprerowcloseflag bprerowcloseflag
   */
  public void setBprerowcloseflag(UFBoolean bprerowcloseflag) {
    this.setAttributeValue(SaleOrderBVO.BPREROWCLOSEFLAG, bprerowcloseflag);
  }

  /**
   * ���� ����ó��
   * 
   * @param btriatradeflag ����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SaleOrderBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * ����Ӧ����֯���°汾
   * 
   * @param carorgid Ӧ����֯���°汾
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SaleOrderBVO.CARORGID, carorgid);
  }

  /**
   * ����Ӧ����֯
   * 
   * @param carorgvid Ӧ����֯
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SaleOrderBVO.CARORGVID, carorgvid);
  }

  /**
   * ��������Դ������
   * 
   * @param carrangepersonid ����Դ������
   */
  public void setCarrangepersonid(String carrangepersonid) {
    this.setAttributeValue(SaleOrderBVO.CARRANGEPERSONID, carrangepersonid);
  }

  /**
   * ���õ�λ
   * 
   * @param castunitid ��λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SaleOrderBVO.CASTUNITID, castunitid);
  }

  /**
   * �����������Ӧ��Դ������ID
   * 
   * @param cbindsrcid �������Ӧ��Դ������ID
   */
  public void setCbindsrcid(String cbindsrcid) {
    this.setAttributeValue(SaleOrderBVO.CBINDSRCID, cbindsrcid);
  }

  /**
   * ����Ʒ��
   * 
   * @param cbrandid Ʒ��
   */
  public void setCbrandid(String cbrandid) {
    this.setAttributeValue(SaleOrderBVO.CBRANDID, cbrandid);
  }

  /**
   * ���ú�ͬ�ӱ�
   * 
   * @param cctmanagebid ��ͬ�ӱ�
   */
  public void setCctmanagebid(String cctmanagebid) {
    this.setAttributeValue(SaleOrderBVO.CCTMANAGEBID, cctmanagebid);
  }

  /**
   * ���ú�ͬ����
   * 
   * @param cctmanageid ��ͬ����
   */
  public void setCctmanageid(String cctmanageid) {
    this.setAttributeValue(SaleOrderBVO.CCTMANAGEID, cctmanageid);
  }

  /**
   * ���ñ�λ��
   * 
   * @param ccurrencyid ��λ��
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SaleOrderBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * ���û����ж�Ӧ�˻���
   * 
   * @param cexchangesrcretid �����ж�Ӧ�˻���
   */
  public void setCexchangesrcretid(String cexchangesrcretid) {
    this.setAttributeValue(SaleOrderBVO.CEXCHANGESRCRETID, cexchangesrcretid);
  }

  /**
   * ���ù���
   * 
   * @param cfactoryid ����
   */
  public void setCfactoryid(String cfactoryid) {
    this.setAttributeValue(SaleOrderBVO.CFACTORYID, cfactoryid);
  }

  /**
   * ����Դͷ�����ӱ�
   * 
   * @param cfirstbid Դͷ�����ӱ�
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SaleOrderBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * ����Դͷ��������
   * 
   * @param cfirstid Դͷ��������
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SaleOrderBVO.CFIRSTID, cfirstid);
  }

  /**
   * ������Ʒ�ж�Ӧ��Դ������ID
   * 
   * @param clargesssrcid ��Ʒ�ж�Ӧ��Դ������ID
   */
  public void setClargesssrcid(String clargesssrcid) {
    this.setAttributeValue(SaleOrderBVO.CLARGESSSRCID, clargesssrcid);
  }

  /**
   * �����������°汾
   * 
   * @param cmaterialid �������°汾
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SaleOrderBVO.CMATERIALID, cmaterialid);
  }

  /**
   * �������ϱ���
   * 
   * @param cmaterialvid ���ϱ���
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SaleOrderBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * ����ԭ������
   * 
   * @param corigareaid ԭ������
   */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(SaleOrderBVO.CORIGAREAID, corigareaid);
  }

  /**
   * ����ԭ����
   * 
   * @param corigcountryid ԭ����
   */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(SaleOrderBVO.CORIGCOUNTRYID, corigcountryid);
  }

  /**
   * ���ü۸����
   * 
   * @param cpriceformid �۸����
   */
  public void setCpriceformid(String cpriceformid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEFORMID, cpriceformid);
  }

  /**
   * ���ü۸���Ŀ
   * 
   * @param cpriceitemid �۸���Ŀ
   */
  public void setCpriceitemid(String cpriceitemid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEITEMID, cpriceitemid);
  }

  /**
   * ���ü�Ŀ��
   * 
   * @param cpriceitemtableid ��Ŀ��
   */
  public void setCpriceitemtableid(String cpriceitemtableid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEITEMTABLEID, cpriceitemtableid);
  }

  /**
   * ���ü۸�����
   * 
   * @param cpricepolicyid �۸�����
   */
  public void setCpricepolicyid(String cpricepolicyid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEPOLICYID, cpricepolicyid);
  }

  /**
   * ���ò�Ʒ��
   * 
   * @param cprodlineid ��Ʒ��
   */
  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(SaleOrderBVO.CPRODLINEID, cprodlineid);
  }

  /**
   * ������������
   * 
   * @param cproductorid ��������
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SaleOrderBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * ���ý��������������°汾
   * 
   * @param cprofitcenterid ���������������°汾
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SaleOrderBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * ���ý�����������
   * 
   * @param cprofitcentervid ������������
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SaleOrderBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * ������Ŀ
   * 
   * @param cprojectid ��Ŀ
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SaleOrderBVO.CPROJECTID, cprojectid);
  }

  /**
   * ���ñ��۵�λ
   * 
   * @param cqtunitid ���۵�λ
   */
  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SaleOrderBVO.CQTUNITID, cqtunitid);
  }

  /**
   * ���������ȼ�
   * 
   * @param cqualitylevelid �����ȼ�
   */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(SaleOrderBVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid �ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SaleOrderBVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * �����ջ��ص�
   * 
   * @param creceiveadddocid �ջ��ص�
   */
  public void setCreceiveadddocid(String creceiveadddocid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVEADDDOCID, creceiveadddocid);
  }

  /**
   * �����ջ���ַ
   * 
   * @param creceiveaddrid �ջ���ַ
   */
  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  /**
   * �����ջ�����
   * 
   * @param creceiveareaid �ջ�����
   */
  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVEAREAID, creceiveareaid);
  }

  /**
   * �����ջ��ͻ�
   * 
   * @param creceivecustid �ջ��ͻ�
   */
  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVECUSTID, creceivecustid);
  }

  /**
   * �����˻�����
   * 
   * @param cretpolicyid �˻�����
   */
  public void setCretpolicyid(String cretpolicyid) {
    this.setAttributeValue(SaleOrderBVO.CRETPOLICYID, cretpolicyid);
  }

  /**
   * �����˻�ԭ��
   * 
   * @param cretreasonid �˻�ԭ��
   */
  public void setCretreasonid(String cretreasonid) {
    this.setAttributeValue(SaleOrderBVO.CRETREASONID, cretreasonid);
  }

  /**
   * �����к�
   * 
   * @param crowno �к�
   */
  public void setCrowno(String crowno) {
    this.setAttributeValue(SaleOrderBVO.CROWNO, crowno);
  }

  /**
   * �������۶�������
   * 
   * @param csaleorderbid ���۶�������
   */
  public void setCsaleorderbid(String csaleorderbid) {
    this.setAttributeValue(SaleOrderBVO.CSALEORDERBID, csaleorderbid);
  }

  /**
   * �������۶�������_����
   * 
   * @param csaleorderid ���۶�������_����
   */
  public void setCsaleorderid(String csaleorderid) {
    this.setAttributeValue(SaleOrderBVO.CSALEORDERID, csaleorderid);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid ��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SaleOrderBVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * ���÷��������֯���°汾
   * 
   * @param csendstockorgid ���������֯���°汾
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * ���÷��������֯
   * 
   * @param csendstockorgvid ���������֯
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * ���÷����ֿ�
   * 
   * @param csendstordocid �����ֿ�
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SaleOrderBVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * ���ý��������֯���°汾
   * 
   * @param csettleorgid ���������֯���°汾
   */
  public void setCsettleorgid(String csettleorgid) {
    this.setAttributeValue(SaleOrderBVO.CSETTLEORGID, csettleorgid);
  }

  /**
   * ���ý��������֯
   * 
   * @param csettleorgvid ���������֯
   */
  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(SaleOrderBVO.CSETTLEORGVID, csettleorgvid);
  }

  /**
   * ������Դ���ݸ���
   * 
   * @param csrcbid ��Դ���ݸ���
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SaleOrderBVO.CSRCBID, csrcbid);
  }

  /**
   * ������Դ��������
   * 
   * @param csrcid ��Դ��������
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SaleOrderBVO.CSRCID, csrcid);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SaleOrderBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid ��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SaleOrderBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * ����������֯���°汾
   * 
   * @param ctrafficorgid ������֯���°汾
   */
  public void setCtrafficorgid(String ctrafficorgid) {
    this.setAttributeValue(SaleOrderBVO.CTRAFFICORGID, ctrafficorgid);
  }

  /**
   * ����������֯
   * 
   * @param ctrafficorgvid ������֯
   */
  public void setCtrafficorgvid(String ctrafficorgvid) {
    this.setAttributeValue(SaleOrderBVO.CTRAFFICORGVID, ctrafficorgvid);
  }

  /**
   * ��������λ
   * 
   * @param cunitid ����λ
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SaleOrderBVO.CUNITID, cunitid);
  }

  /**
   * ���ù�Ӧ��
   * 
   * @param cvendorid ��Ӧ��
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SaleOrderBVO.CVENDORID, cvendorid);
  }

  /**
   * ���õ�������
   * 
   * @param dbilldate ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SaleOrderBVO.DBILLDATE, dbilldate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SaleOrderHVO.DR, dr);
  }

  /**
   * ���õ�������
   * 
   * @param dreceivedate ��������
   */
  public void setDreceivedate(UFDate dreceivedate) {
    this.setAttributeValue(SaleOrderBVO.DRECEIVEDATE, dreceivedate);
  }

  /**
   * ���÷�������
   * 
   * @param dsenddate ��������
   */
  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(SaleOrderBVO.DSENDDATE, dsenddate);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag ��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SaleOrderBVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * ������Ʒ�۸��̯��ʽ
   * 
   * @param flargesstypeflag ��Ʒ�۸��̯��ʽ
   * @see Largesstype
   */
  public void setFlargesstypeflag(Integer flargesstypeflag) {
    this.setAttributeValue(SaleOrderBVO.FLARGESSTYPEFLAG, flargesstypeflag);
  }

  /**
   * �����˻������
   * 
   * @param fretexchange �˻������
   * @see Fretexchange
   */
  public void setFretexchange(Integer fretexchange) {
    this.setAttributeValue(SaleOrderBVO.FRETEXCHANGE, fretexchange);
  }

  /**
   * ������״̬
   * 
   * @param frowstatus ��״̬
   * @see BillStatus
   */
  public void setFrowstatus(Integer frowstatus) {
    this.setAttributeValue(SaleOrderBVO.FROWSTATUS, frowstatus);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SaleOrderBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * �����ۼư���������������
   * 
   * @param narrangemonum �ۼư���������������
   */
  public void setNarrangemonum(UFDouble narrangemonum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEMONUM, narrangemonum);
  }

  /**
   * �����ۼư����빺������
   * 
   * @param narrangepoappnum �ۼư����빺������
   */
  public void setNarrangepoappnum(UFDouble narrangepoappnum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEPOAPPNUM, narrangepoappnum);
  }

  /**
   * �����ۼư��Ųɹ���������
   * 
   * @param narrangeponum �ۼư��Ųɹ���������
   */
  public void setNarrangeponum(UFDouble narrangeponum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEPONUM, narrangeponum);
  }

  /**
   * �����ۼư���ί�ⶩ������
   * 
   * @param narrangescornum �ۼư���ί�ⶩ������
   */
  public void setNarrangescornum(UFDouble narrangescornum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGESCORNUM, narrangescornum);
  }

  /**
   * �����ۼư��ŵ�����������
   * 
   * @param narrangetoappnum �ۼư��ŵ�����������
   */
  public void setNarrangetoappnum(UFDouble narrangetoappnum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGETOAPPNUM, narrangetoappnum);
  }

  /**
   * �����ۼư��ŵ�����������
   * 
   * @param narrangetoornum �ۼư��ŵ�����������
   */
  public void setNarrangetoornum(UFDouble narrangetoornum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGETOORNUM, narrangetoornum);
  }

  /**
   * ����ѯ��ԭ����˰����
   * 
   * @param naskqtorignetprice ѯ��ԭ����˰����
   */
  public void setNaskqtorignetprice(UFDouble naskqtorignetprice) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGNETPRICE, naskqtorignetprice);
  }

  /**
   * ����ѯ��ԭ����˰����
   * 
   * @param naskqtorigprice ѯ��ԭ����˰����
   */
  public void setNaskqtorigprice(UFDouble naskqtorigprice) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGPRICE, naskqtorigprice);
  }

  /**
   * ����ѯ��ԭ�Һ�˰����
   * 
   * @param naskqtorigtaxprc ѯ��ԭ�Һ�˰����
   */
  public void setNaskqtorigtaxprc(UFDouble naskqtorigtaxprc) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGTAXPRC, naskqtorigtaxprc);
  }

  /**
   * ����ѯ��ԭ�Һ�˰����
   * 
   * @param naskqtorigtxntprc ѯ��ԭ�Һ�˰����
   */
  public void setNaskqtorigtxntprc(UFDouble naskqtorigtxntprc) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGTXNTPRC, naskqtorigtxntprc);
  }

  /**
   * ��������
   * 
   * @param nastnum ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SaleOrderBVO.NASTNUM, nastnum);
  }

  /**
   * ����nbforigsubmny
   * 
   * @param nbforigsubmny nbforigsubmny
   */
  public void setNbforigsubmny(UFDouble nbforigsubmny) {
    this.setAttributeValue(SaleOrderBVO.NBFORIGSUBMNY, nbforigsubmny);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SaleOrderBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * ���ñ����ۿ۶�
   * 
   * @param ndiscount �����ۿ۶�
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SaleOrderBVO.NDISCOUNT, ndiscount);
  }

  /**
   * ���������ۿ�
   * 
   * @param ndiscountrate �����ۿ�
   */
  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SaleOrderBVO.NDISCOUNTRATE, ndiscountrate);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SaleOrderBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * ����ȫ�ֱ�λ�һ���
   * 
   * @param nglobalexchgrate ȫ�ֱ�λ�һ���
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SaleOrderBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * ����ȫ�ֱ�����˰���
   * 
   * @param nglobalmny ȫ�ֱ�����˰���
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SaleOrderBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * ����ȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @param nglobaltaxmny ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SaleOrderBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * ���ü��ű�λ�һ���
   * 
   * @param ngroupexchgrate ���ű�λ�һ���
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SaleOrderBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * ���ü��ű�����˰���
   * 
   * @param ngroupmny ���ű�����˰���
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SaleOrderBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * ���ü��ű��Ҽ�˰�ϼ�
   * 
   * @param ngrouptaxmny ���ű��Ҽ�˰�ϼ�
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SaleOrderBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * ����ninvoiceauditnum
   * 
   * @param ninvoiceauditnum ninvoiceauditnum
   */
  public void setNinvoiceauditnum(UFDouble ninvoiceauditnum) {
    this.setAttributeValue(SaleOrderBVO.NINVOICEAUDITNUM, ninvoiceauditnum);
  }

  /**
   * ����ninvunfinisednum
   * 
   * @param ninvunfinisednum ninvunfinisednum
   */
  public void setNinvunfinisednum(UFDouble ninvunfinisednum) {
    this.setAttributeValue(SaleOrderBVO.NINVUNFINISEDNUM, ninvunfinisednum);
  }

  /**
   * ���õ�Ʒ�ۿ���
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ���
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SaleOrderBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * ������Ʒ�۸��̯ǰ��˰���
   * 
   * @param nlargessmny ��Ʒ�۸��̯ǰ��˰���
   */
  public void setNlargessmny(UFDouble nlargessmny) {
    this.setAttributeValue(SaleOrderBVO.NLARGESSMNY, nlargessmny);
  }

  /**
   * ������Ʒ�۸��̯ǰ��˰�ϼ�
   * 
   * @param nlargesstaxmny ��Ʒ�۸��̯ǰ��˰�ϼ�
   */
  public void setNlargesstaxmny(UFDouble nlargesstaxmny) {
    this.setAttributeValue(SaleOrderBVO.NLARGESSTAXMNY, nlargesstaxmny);
  }

  /**
   * ����nlossnotauditnum
   * 
   * @param nlossnotauditnum nlossnotauditnum
   */
  public void setNlossnotauditnum(UFDouble nlossnotauditnum) {
    this.setAttributeValue(SaleOrderBVO.NLOSSNOTAUDITNUM, nlossnotauditnum);
  }

  /**
   * ���ñ�����˰���
   * 
   * @param nmny ������˰���
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SaleOrderBVO.NMNY, nmny);
  }

  /**
   * ������������˰����
   * 
   * @param nnetprice ��������˰����
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SaleOrderBVO.NNETPRICE, nnetprice);
  }

  /**
   * ����nnotarnum
   * 
   * @param nnotarnum nnotarnum
   */
  public void setNnotarnum(UFDouble nnotarnum) {
    this.setAttributeValue(SaleOrderBVO.NNOTARNUM, nnotarnum);
  }

  /**
   * ����nnotcostnum
   * 
   * @param nnotcostnum nnotcostnum
   */
  public void setNnotcostnum(UFDouble nnotcostnum) {
    this.setAttributeValue(SaleOrderBVO.NNOTCOSTNUM, nnotcostnum);
  }

  /**
   * ����������
   * 
   * @param nnum ������
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SaleOrderBVO.NNUM, nnum);
  }

  /**
   * �����ۿ۶�
   * 
   * @param norigdiscount �ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SaleOrderBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * ������˰���
   * 
   * @param norigmny ��˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SaleOrderBVO.NORIGMNY, norigmny);
  }

  /**
   * ��������˰����
   * 
   * @param norignetprice ����˰����
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * ��������˰����
   * 
   * @param norigprice ����˰����
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGPRICE, norigprice);
  }

  /**
   * �����ۼƳ�ֽ��
   * 
   * @param norigsubmny �ۼƳ�ֽ��
   */
  public void setNorigsubmny(UFDouble norigsubmny) {
    this.setAttributeValue(SaleOrderBVO.NORIGSUBMNY, norigsubmny);
  }

  /**
   * ����˰��
   * 
   * @param norigtax ˰��
   */
  // public void setNorigtax(UFDouble norigtax) {
  // this.setAttributeValue(SaleOrderBVO.NORIGTAX, norigtax);
  // }

  /**
   * ���ü�˰�ϼ�
   * 
   * @param norigtaxmny ��˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SaleOrderBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��������˰����
   * 
   * @param norigtaxnetprice ����˰����
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * ��������˰����
   * 
   * @param norigtaxprice ����˰����
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * ����noutauditnum
   * 
   * @param noutauditnum noutauditnum
   */
  public void setNoutauditnum(UFDouble noutauditnum) {
    this.setAttributeValue(SaleOrderBVO.NOUTAUDITNUM, noutauditnum);
  }

  /**
   * ����noutnotauditnum
   * 
   * @param noutnotauditnum noutnotauditnum
   */
  public void setNoutnotauditnum(UFDouble noutnotauditnum) {
    this.setAttributeValue(SaleOrderBVO.NOUTNOTAUDITNUM, noutnotauditnum);
  }

  /**
   * ����noutunfinisednum
   * 
   * @param noutunfinisednum noutunfinisednum
   */
  public void setNoutunfinisednum(UFDouble noutunfinisednum) {
    this.setAttributeValue(SaleOrderBVO.NOUTUNFINISEDNUM, noutunfinisednum);
  }

  /**
   * ���ü���
   * 
   * @param npiece ����
   */
  public void setNpiece(UFDouble npiece) {
    this.setAttributeValue(SaleOrderBVO.NPIECE, npiece);
  }

  /**
   * ������������˰����
   * 
   * @param nprice ��������˰����
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SaleOrderBVO.NPRICE, nprice);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nqtnetprice ������˰����
   */
  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(SaleOrderBVO.NQTNETPRICE, nqtnetprice);
  }

  /**
   * ������˰����
   * 
   * @param nqtorignetprice ��˰����
   */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /**
   * ������˰����
   * 
   * @param nqtorigprice ��˰����
   */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGPRICE, nqtorigprice);
  }

  /**
   * ���ú�˰����
   * 
   * @param nqtorigtaxnetprc ��˰����
   */
  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  /**
   * ���ú�˰����
   * 
   * @param nqtorigtaxprice ��˰����
   */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nqtprice ������˰����
   */
  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(SaleOrderBVO.NQTPRICE, nqtprice);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param nqttaxnetprice ���Һ�˰����
   */
  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(SaleOrderBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param nqttaxprice ���Һ�˰����
   */
  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(SaleOrderBVO.NQTTAXPRICE, nqttaxprice);
  }

  /**
   * ���ñ��۵�λ����
   * 
   * @param nqtunitnum ���۵�λ����
   */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(SaleOrderBVO.NQTUNITNUM, nqtunitnum);
  }

  /**
   * ����Ԥ������
   * 
   * @param nreqrsnum Ԥ������
   */
  public void setNreqrsnum(UFDouble nreqrsnum) {
    this.setAttributeValue(SaleOrderBVO.NREQRSNUM, nreqrsnum);
  }

  /**
   * ����nsendauditnum
   * 
   * @param nsendauditnum nsendauditnum
   */
  public void setNsendauditnum(UFDouble nsendauditnum) {
    this.setAttributeValue(SaleOrderBVO.NSENDAUDITNUM, nsendauditnum);
  }

  /**
   * ����nsendunfinisednum
   * 
   * @param nsendunfinisednum nsendunfinisednum
   */
  public void setNsendunfinisednum(UFDouble nsendunfinisednum) {
    this.setAttributeValue(SaleOrderBVO.NSENDUNFINISEDNUM, nsendunfinisednum);
  }

  /**
   * ���ñ���˰��
   * 
   * @param ntax ����˰��
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SaleOrderBVO.NTAX, ntax);
  }

  /**
   * ���ñ��Ҽ�˰�ϼ�
   * 
   * @param ntaxmny ���Ҽ�˰�ϼ�
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SaleOrderBVO.NTAXMNY, ntaxmny);
  }

  /**
   * ���������Һ�˰����
   * 
   * @param ntaxnetprice �����Һ�˰����
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SaleOrderBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * ���������Һ�˰����
   * 
   * @param ntaxprice �����Һ�˰����
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SaleOrderBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SaleOrderBVO.NTAXRATE, ntaxrate);
  }

  /**
   * �����ۼ�ȷ��Ӧ�ս��
   * 
   * @param ntotalarmny �ۼ�ȷ��Ӧ�ս��
   */
  public void setNtotalarmny(UFDouble ntotalarmny) {
    this.setAttributeValue(SaleOrderBVO.NTOTALARMNY, ntotalarmny);
  }

  /**
   * �����ۼ�ȷ��Ӧ������
   * 
   * @param ntotalarnum �ۼ�ȷ��Ӧ������
   */
  public void setNtotalarnum(UFDouble ntotalarnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALARNUM, ntotalarnum);
  }

  /**
   * �����ۼƳɱ���������
   * 
   * @param ntotalcostnum �ۼƳɱ���������
   */
  public void setNtotalcostnum(UFDouble ntotalcostnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALCOSTNUM, ntotalcostnum);
  }

  /**
   * �����ۼ��ݹ�Ӧ�ս��
   * 
   * @param ntotalestarmny �ۼ��ݹ�Ӧ�ս��
   */
  public void setNtotalestarmny(UFDouble ntotalestarmny) {
    this.setAttributeValue(SaleOrderBVO.NTOTALESTARMNY, ntotalestarmny);
  }

  /**
   * �����ۼ��ݹ�Ӧ������
   * 
   * @param ntotalestarnum �ۼ��ݹ�Ӧ������
   */
  public void setNtotalestarnum(UFDouble ntotalestarnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALESTARNUM, ntotalestarnum);
  }

  /**
   * �����ۼƿ�Ʊ����
   * 
   * @param ntotalinvoicenum �ۼƿ�Ʊ����
   */
  public void setNtotalinvoicenum(UFDouble ntotalinvoicenum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALINVOICENUM, ntotalinvoicenum);
  }

  /**
   * �����ۼ�Ӧ��δ��������
   * 
   * @param ntotalnotoutnum �ۼ�Ӧ��δ��������
   */
  public void setNtotalnotoutnum(UFDouble ntotalnotoutnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALNOTOUTNUM, ntotalnotoutnum);
  }

  /**
   * �����ۼƳ�������
   * 
   * @param ntotaloutnum �ۼƳ�������
   */
  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALOUTNUM, ntotaloutnum);
  }

  /**
   * �����ۼƲ���������
   * 
   * @param ntotalpaymny �ۼƲ���������
   */
  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SaleOrderBVO.NTOTALPAYMNY, ntotalpaymny);
  }

  /**
   * �����ۼ����ɼƻ���������
   * 
   * @param ntotalplonum �ۼ����ɼƻ���������
   */
  public void setNtotalplonum(UFDouble ntotalplonum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALPLONUM, ntotalplonum);
  }

  /**
   * �����ۼ��˻�����
   * 
   * @param ntotalreturnnum �ۼ��˻�����
   */
  public void setNtotalreturnnum(UFDouble ntotalreturnnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALRETURNNUM, ntotalreturnnum);
  }

  /**
   * �����ۼƳ���Գ�����
   * 
   * @param ntotalrushnum �ۼƳ���Գ�����
   */
  public void setNtotalrushnum(UFDouble ntotalrushnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALRUSHNUM, ntotalrushnum);
  }

  /**
   * �����ۼƷ�������
   * 
   * @param ntotalsendnum �ۼƷ�������
   */
  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALSENDNUM, ntotalsendnum);
  }

  /**
   * �����ۼ�ǩ������
   * 
   * @param ntotalsignnum �ۼ�ǩ������
   */
  public void setNtotalsignnum(UFDouble ntotalsignnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALSIGNNUM, ntotalsignnum);
  }

  /**
   * �����ۼƷ�����Ʒ����
   * 
   * @param ntotaltradenum �ۼƷ�����Ʒ����
   */
  public void setNtotaltradenum(UFDouble ntotaltradenum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALTRADENUM, ntotaltradenum);
  }

  /**
   * �����ۼ�;������
   * 
   * @param ntranslossnum �ۼ�;������
   */
  public void setNtranslossnum(UFDouble ntranslossnum) {
    this.setAttributeValue(SaleOrderBVO.NTRANSLOSSNUM, ntranslossnum);
  }

  /**
   * �������
   * 
   * @param nvolume ���
   */
  public void setNvolume(UFDouble nvolume) {
    this.setAttributeValue(SaleOrderBVO.NVOLUME, nvolume);
  }

  /**
   * ��������
   * 
   * @param nweight ����
   */
  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(SaleOrderBVO.NWEIGHT, nweight);
  }

  /**
   * �������ε���
   * 
   * @param pk_batchcode ���ε���
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SaleOrderBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SaleOrderBVO.PK_GROUP, pk_group);
  }

  /**
   * ����������֯
   * 
   * @param pk_org ������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SaleOrderBVO.PK_ORG, pk_org);
  }

  /**
   * ������Դʱ���
   * 
   * @param srcbts ��Դʱ���
   * 
   */
  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(SaleOrderBVO.SRCBTS, srcbts);
  }

  /**
   * ������Դ��֯
   * 
   * @param srcorgid
   */
  public void setSrcorgid(String srcorgid) {
    this.setAttributeValue(SaleOrderBVO.SRCORGID, srcorgid);
  }

  /**
   * ������Դʱ���
   * 
   * @param srcts ��Դʱ���
   * 
   */
  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(SaleOrderBVO.SRCTS, srcts);
  }

  /**
   * ��������Դ����ʱ��
   * 
   * @param tlastarrangetime ����Դ����ʱ��
   */
  public void setTlastarrangetime(UFDateTime tlastarrangetime) {
    this.setAttributeValue(SaleOrderBVO.TLASTARRANGETIME, tlastarrangetime);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SaleOrderBVO.TS, ts);
  }

  /**
   * ��������
   * 
   * @param vbatchcode ����
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SaleOrderBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * �����Զ�����1
   * 
   * @param vbdef1 �Զ�����1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SaleOrderBVO.VBDEF1, vbdef1);
  }

  /**
   * �����Զ�����10
   * 
   * @param vbdef10 �Զ�����10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SaleOrderBVO.VBDEF10, vbdef10);
  }

  /**
   * �����Զ�����11
   * 
   * @param vbdef11 �Զ�����11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SaleOrderBVO.VBDEF11, vbdef11);
  }

  /**
   * �����Զ�����12
   * 
   * @param vbdef12 �Զ�����12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SaleOrderBVO.VBDEF12, vbdef12);
  }

  /**
   * �����Զ�����13
   * 
   * @param vbdef13 �Զ�����13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SaleOrderBVO.VBDEF13, vbdef13);
  }

  /**
   * �����Զ�����14
   * 
   * @param vbdef14 �Զ�����14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SaleOrderBVO.VBDEF14, vbdef14);
  }

  /**
   * �����Զ�����15
   * 
   * @param vbdef15 �Զ�����15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SaleOrderBVO.VBDEF15, vbdef15);
  }

  /**
   * �����Զ�����16
   * 
   * @param vbdef16 �Զ�����16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SaleOrderBVO.VBDEF16, vbdef16);
  }

  /**
   * �����Զ�����17
   * 
   * @param vbdef17 �Զ�����17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SaleOrderBVO.VBDEF17, vbdef17);
  }

  /**
   * �����Զ�����18
   * 
   * @param vbdef18 �Զ�����18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SaleOrderBVO.VBDEF18, vbdef18);
  }

  /**
   * �����Զ�����19
   * 
   * @param vbdef19 �Զ�����19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SaleOrderBVO.VBDEF19, vbdef19);
  }

  /**
   * �����Զ�����2
   * 
   * @param vbdef2 �Զ�����2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SaleOrderBVO.VBDEF2, vbdef2);
  }

  /**
   * �����Զ�����20
   * 
   * @param vbdef20 �Զ�����20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SaleOrderBVO.VBDEF20, vbdef20);
  }

  /**
   * �����Զ�����3
   * 
   * @param vbdef3 �Զ�����3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SaleOrderBVO.VBDEF3, vbdef3);
  }

  /**
   * �����Զ�����4
   * 
   * @param vbdef4 �Զ�����4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SaleOrderBVO.VBDEF4, vbdef4);
  }

  /**
   * �����Զ�����5
   * 
   * @param vbdef5 �Զ�����5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SaleOrderBVO.VBDEF5, vbdef5);
  }

  /**
   * �����Զ�����6
   * 
   * @param vbdef6 �Զ�����6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SaleOrderBVO.VBDEF6, vbdef6);
  }

  /**
   * �����Զ�����7
   * 
   * @param vbdef7 �Զ�����7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SaleOrderBVO.VBDEF7, vbdef7);
  }

  /**
   * �����Զ�����8
   * 
   * @param vbdef8 �Զ�����8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SaleOrderBVO.VBDEF8, vbdef8);
  }

  /**
   * �����Զ�����9
   * 
   * @param vbdef9 �Զ�����9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SaleOrderBVO.VBDEF9, vbdef9);
  }

  /**
   * �����޶�����
   * 
   * @param vbrevisereason �޶�����
   */
  public void setVbrevisereason(String vbrevisereason) {
    this.setAttributeValue(SaleOrderBVO.VBREVISEREASON, vbrevisereason);
  }

  /**
   * ���û�����
   * 
   * @param vchangerate ������
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SaleOrderBVO.VCHANGERATE, vchangerate);
  }

  /**
   * ���ùر�ԭ��
   * 
   * @param vclosereason �ر�ԭ��
   */
  public void setVclosereason(String vclosereason) {
    this.setAttributeValue(SaleOrderBVO.VCLOSEREASON, vclosereason);
  }

  /**
   * �������ۺ�ͬ��
   * 
   * @param vctcode ���ۺ�ͬ��
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SaleOrderBVO.VCTCODE, vctcode);
  }

  /**
   * ����vcttype
   * 
   * @param vcttype vcttype
   */
  public void setVcttype(String vcttype) {
    this.setAttributeValue(SaleOrderBVO.VCTTYPE, vcttype);
  }

  /**
   * ����Դͷ���ݺ�
   * 
   * @param vfirstcode Դͷ���ݺ�
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * ����Դͷ�����к�
   * 
   * @param vfirstrowno Դͷ�����к�
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttrantype Դͷ��������
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttype Դͷ��������
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * �������ɸ�������1
   * 
   * @param vfree1 ���ɸ�������1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SaleOrderBVO.VFREE1, vfree1);
  }

  /**
   * �������ɸ�������10
   * 
   * @param vfree10 ���ɸ�������10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SaleOrderBVO.VFREE10, vfree10);
  }

  /**
   * �������ɸ�������2
   * 
   * @param vfree2 ���ɸ�������2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SaleOrderBVO.VFREE2, vfree2);
  }

  /**
   * �������ɸ�������3
   * 
   * @param vfree3 ���ɸ�������3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SaleOrderBVO.VFREE3, vfree3);
  }

  /**
   * �������ɸ�������4
   * 
   * @param vfree4 ���ɸ�������4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SaleOrderBVO.VFREE4, vfree4);
  }

  /**
   * �������ɸ�������5
   * 
   * @param vfree5 ���ɸ�������5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SaleOrderBVO.VFREE5, vfree5);
  }

  /**
   * �������ɸ�������6
   * 
   * @param vfree6 ���ɸ�������6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SaleOrderBVO.VFREE6, vfree6);
  }

  /**
   * �������ɸ�������7
   * 
   * @param vfree7 ���ɸ�������7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SaleOrderBVO.VFREE7, vfree7);
  }

  /**
   * �������ɸ�������8
   * 
   * @param vfree8 ���ɸ�������8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SaleOrderBVO.VFREE8, vfree8);
  }

  /**
   * �������ɸ�������9
   * 
   * @param vfree9 ���ɸ�������9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SaleOrderBVO.VFREE9, vfree9);
  }

  /**
   * ���ñ��ۻ�����
   * 
   * @param vqtunitrate ���ۻ�����
   */
  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(SaleOrderBVO.VQTUNITRATE, vqtunitrate);
  }

  /**
   * �����˻����δ���ʽ
   * 
   * @param vreturnmode �˻�����
   */
  public void setVreturnmode(String vreturnmode) {
    this.setAttributeValue(SaleOrderBVO.VRETURNMODE, vreturnmode);
  }

  /**
   * �����б�ע
   * 
   * @param vrownote �б�ע
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SaleOrderBVO.VROWNOTE, vrownote);
  }

  /**
   * ������Դ���ݺ�
   * 
   * @param vsrccode ��Դ���ݺ�
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SaleOrderBVO.VSRCCODE, vsrccode);
  }

  /**
   * ������Դ�����к�
   * 
   * @param vsrcrowno ��Դ�����к�
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SaleOrderBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctrantype ��Դ��������
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SaleOrderBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctype ��Դ��������
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SaleOrderBVO.VSRCTYPE, vsrctype);
  }

  /**
   * ����������������
   * 
   * @param cbuypromottypeid ������������
   * 
   */
  public void setCbuypromottypeid(String cbuypromottypeid) {
    this.setAttributeValue(SaleOrderBVO.CBUYPROMOTTYPEID, cbuypromottypeid);
  }

  /**
   * ����ѯ�۴�������
   * 
   * @param cprcpromottypeid ѯ�۴�������
   * 
   */
  public void setCprcpromottypeid(String cprcpromottypeid) {
    this.setAttributeValue(SaleOrderBVO.CPRCPROMOTTYPEID, cprcpromottypeid);
  }

  /**
   * ���ÿͻ����ݺ�
   * 
   * @param vcustombillcode �ͻ����ݺ�
   * 
   */
  public void setVcustombillcode(String vcustombillcode) {
    this.setAttributeValue(SaleOrderBVO.VCUSTOMBILLCODE, vcustombillcode);
  }

  /**
   * �����
   * 
   * @param cbuylargessactid �����
   * 
   */
  public void setCbuylargessactid(String cbuylargessactid) {
    this.setAttributeValue(SaleOrderBVO.CBUYLARGESSACTID, cbuylargessactid);
  }

  /**
   * �۸�����
   * 
   * @param cpricepromtactid �۸�����
   * 
   */
  public void setCpricepromtactid(String cpricepromtactid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEPROMTACTID, cpricepromtactid);
  }

  /**
   * ��������
   * 
   * @param cbuylargessid ��������
   * 
   */
  public void setCbuylargessid(String cbuylargessid) {
    this.setAttributeValue(SaleOrderBVO.CBUYLARGESSID, cbuylargessid);
  }

  /**
   * ��ȡ�ۼư��Ž��ں�ͬ������
   * 
   * @return �ۼư��Ž��ں�ͬ������
   */
  public UFDouble getNarrangeitcnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEITCNUM);
  }

  /**
   * �����ۼư��Ž��ں�ͬ������
   * 
   * @param narrangeitcnum �ۼư��Ž��ں�ͬ������
   */
  public void setNarrangeitcnum(UFDouble narrangeitcnum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEITCNUM, narrangeitcnum);
  }

  /**
   * ���÷��������������°汾
   * 
   * @param csprofitcenterid ���������������°汾
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SaleOrderBVO.CSPROFITCENTERID, csprofitcenterid);
  }

  /**
   * ���÷�����������
   * 
   * @param csprofitcentervid ������������
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SaleOrderBVO.CSPROFITCENTERVID, csprofitcentervid);
  }
  
  /**
   * ���ô����۸����ID
   * 
   * @param cpromotpriceid �����۸����ID
   */
  public void setCPromotpriceid(String cpromotpriceid) {
    this.setAttributeValue(SaleOrderBVO.CPROMOTPRICEID, cpromotpriceid);
  }
  
  /**
   * ��ȡ������ѡ���ݴ�VO
   * 
   * @return ������ѡ���ݴ�VO
   */
  public AggFFileVO getAggffilevo() {
	return aggffilevo;
  }
  /**
   * ����������ѡ���ݴ�VO
   * 
   * @param aggffilevo ������ѡ���ݴ�VO
   */
  public void setAggffilevo(AggFFileVO aggffilevo) {
	this.aggffilevo = aggffilevo;
  }

/**
   * ��ȡ������
   * 
   * @return ������
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CMFFILEID);
  }

  /**
   * ����������
   * 
   * @param ������
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SaleOrderBVO.CMFFILEID, cmffileid);
  }
  
  
  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFDouble  getNmffileprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NMFFILEPRICE);
  }

  /**
   * ����������
   * 
   * @param ������
   */
  public void setNmffileprice(UFDouble nmffileprice) {
    this.setAttributeValue(SaleOrderBVO.NMFFILEPRICE, nmffileprice);
  }

  
  /**
   * add by wangzym  2017-01-18  ��չ�ӱ��ֶε� getter setter����
   * */
public static String getBidding_no() {
	return bidding_no;
}

public static void setBidding_no(String bidding_no) {
	SaleOrderBVO.bidding_no = bidding_no;
}

public static String getProject_name() {
	return project_name;
}

public static void setProject_name(String project_name) {
	SaleOrderBVO.project_name = project_name;
}

public static String getProject_content() {
	return Project_content;
}

public static void setProject_content(String project_content) {
	Project_content = project_content;
}

public static String getSupplier_requirements() {
	return supplier_requirements;
}

public static void setSupplier_requirements(String supplier_requirements) {
	SaleOrderBVO.supplier_requirements = supplier_requirements;
}

public static UFDouble getSumplannum() {
	return sumplannum;
}

public static void setSumplannum(UFDouble sumplannum) {
	SaleOrderBVO.sumplannum = sumplannum;
}

public static UFDouble getSumnum() {
	return sumnum;
}

public static void setSumnum(UFDouble sumnum) {
	SaleOrderBVO.sumnum = sumnum;
}

public static String getTypeplan() {
	return typeplan;
}

public static void setTypeplan(String typeplan) {
	SaleOrderBVO.typeplan = typeplan;
}

public static String getTypebuy() {
	return typebuy;
}

public static void setTypebuy(String typebuy) {
	SaleOrderBVO.typebuy = typebuy;
}

public static UFDouble getRatereply() {
	return ratereply;
}

public static void setRatereply(UFDouble ratereply) {
	SaleOrderBVO.ratereply = ratereply;
}

public static String getBid_evaluation() {
	return bid_evaluation;
}

public static void setBid_evaluation(String bid_evaluation) {
	SaleOrderBVO.bid_evaluation = bid_evaluation;
}

public static String getCombination_standard() {
	return combination_standard;
}

public static void setCombination_standard(String combination_standard) {
	SaleOrderBVO.combination_standard = combination_standard;
}

public static String getProcurementplan() {
	return procurementplan;
}

public static void setProcurementplan(String procurementplan) {
	SaleOrderBVO.procurementplan = procurementplan;
}

public static String getNum_bj() {
	return num_bj;
}

public static void setNum_bj(String num_bj) {
	SaleOrderBVO.num_bj = num_bj;
}

public static String getSeq_bj() {
	return seq_bj;
}

public static void setSeq_bj(String seq_bj) {
	SaleOrderBVO.seq_bj = seq_bj;
}

public static String getOffer_type() {
	return offer_type;
}

public static void setOffer_type(String offer_type) {
	SaleOrderBVO.offer_type = offer_type;
}

public static String getQualification_way() {
	return qualification_way;
}

public static void setQualification_way(String qualification_way) {
	SaleOrderBVO.qualification_way = qualification_way;
}

public static String getPayment() {
	return payment;
}

public static void setPayment(String payment) {
	SaleOrderBVO.payment = payment;
}

public static String getBusiness_types() {
	return business_types;
}

public static void setBusiness_types(String business_types) {
	SaleOrderBVO.business_types = business_types;
}

public static String getProcurement() {
	return procurement;
}

public static void setProcurement(String procurement) {
	SaleOrderBVO.procurement = procurement;
}

public static String getEstimate() {
	return estimate;
}

public static void setEstimate(String estimate) {
	SaleOrderBVO.estimate = estimate;
}

public static String getDelivery_term() {
	return delivery_term;
}

public static void setDelivery_term(String delivery_term) {
	SaleOrderBVO.delivery_term = delivery_term;
}

public static String getRequirements() {
	return requirements;
}

public static void setRequirements(String requirements) {
	SaleOrderBVO.requirements = requirements;
}

public static String getSupplier_code() {
	return supplier_code;
}

public static void setSupplier_code(String supplier_code) {
	SaleOrderBVO.supplier_code = supplier_code;
}

public static String getSupplier() {
	return supplier;
}

public static void setSupplier(String supplier) {
	SaleOrderBVO.supplier = supplier;
}

public static String getNo_delegate() {
	return no_delegate;
}

public static void setNo_delegate(String no_delegate) {
	SaleOrderBVO.no_delegate = no_delegate;
}

public static String getSeq_delegate() {
	return seq_delegate;
}

public static void setSeq_delegate(String seq_delegate) {
	SaleOrderBVO.seq_delegate = seq_delegate;
}

public static String getCcategoryid() {
	return ccategoryid;
}

public static void setCcategoryid(String ccategoryid) {
	SaleOrderBVO.ccategoryid = ccategoryid;
}

public static String getProjectexecutor() {
	return projectexecutor;
}

public static void setProjectexecutor(String projectexecutor) {
	SaleOrderBVO.projectexecutor = projectexecutor;
}

public static String getNo_pasdoc() {
	return no_pasdoc;
}

public static void setNo_pasdoc(String no_pasdoc) {
	SaleOrderBVO.no_pasdoc = no_pasdoc;
}

public static String getCustomer_no() {
	return customer_no;
}

public static void setCustomer_no(String customer_no) {
	SaleOrderBVO.customer_no = customer_no;
}

public static String getCustomer_name() {
	return customer_name;
}

public static void setCustomer_name(String customer_name) {
	SaleOrderBVO.customer_name = customer_name;
}

public static Integer getPlan_num() {
	return plan_num;
}

public static void setPlan_num(Integer plan_num) {
	SaleOrderBVO.plan_num = plan_num;
}

public static UFDate getRequest_date() {
	return request_date;
}

public static void setRequest_date(UFDate request_date) {
	SaleOrderBVO.request_date = request_date;
}

public static String getHost_name() {
	return host_name;
}

public static void setHost_name(String host_name) {
	SaleOrderBVO.host_name = host_name;
}

public static String getMaterial() {
	return material;
}

public static void setMaterial(String material) {
	SaleOrderBVO.material = material;
}

public static String getRated_life() {
	return rated_life;
}

public static void setRated_life(String rated_life) {
	SaleOrderBVO.rated_life = rated_life;
}

public static String getManufacturer() {
	return manufacturer;
}

public static void setManufacturer(String manufacturer) {
	SaleOrderBVO.manufacturer = manufacturer;
}

public static UFDouble getPlan_pricea() {
	return plan_pricea;
}

public static void setPlan_pricea(UFDouble plan_pricea) {
	SaleOrderBVO.plan_pricea = plan_pricea;
}

public static UFDouble getPlan_priceb() {
	return plan_priceb;
}

public static void setPlan_priceb(UFDouble plan_priceb) {
	SaleOrderBVO.plan_priceb = plan_priceb;
}

public static UFDouble getPlansum_pricea() {
	return plansum_pricea;
}

public static void setPlansum_pricea(UFDouble plansum_pricea) {
	SaleOrderBVO.plansum_pricea = plansum_pricea;
}

public static UFDouble getPlansum_priceb() {
	return Plansum_priceb;
}

public static void setPlansum_priceb(UFDouble plansum_priceb) {
	Plansum_priceb = plansum_priceb;
}

public static String getFactory_plan() {
	return factory_plan;
}

public static void setFactory_plan(String factory_plan) {
	SaleOrderBVO.factory_plan = factory_plan;
}

public static String getFactory_code() {
	return factory_code;
}

public static void setFactory_code(String factory_code) {
	SaleOrderBVO.factory_code = factory_code;
}

public static String getFactory_name() {
	return factory_name;
}

public static void setFactory_name(String factory_name) {
	SaleOrderBVO.factory_name = factory_name;
}

public static String getPlan() {
	return plan;
}

public static void setPlan(String plan) {
	SaleOrderBVO.plan = plan;
}

public static String getApplication_no() {
	return application_no;
}

public static void setApplication_no(String application_no) {
	SaleOrderBVO.application_no = application_no;
}

public static String getApplication_line() {
	return application_line;
}

public static void setApplication_line(String application_line) {
	SaleOrderBVO.application_line = application_line;
}

public static String getNumber_code() {
	return number_code;
}

public static void setNumber_code(String number_code) {
	SaleOrderBVO.number_code = number_code;
}

public static String getTally() {
	return tally;
}

public static void setTally(String tally) {
	SaleOrderBVO.tally = tally;
}

public static UFDate getPlan_time() {
	return plan_time;
}

public static void setPlan_time(UFDate plan_time) {
	SaleOrderBVO.plan_time = plan_time;
}

public static UFDouble getFreight() {
	return freight;
}

public static void setFreight(UFDouble freight) {
	SaleOrderBVO.freight = freight;
}

public static UFDouble getAdded_tax() {
	return added_tax;
}

public static void setAdded_tax(UFDouble added_tax) {
	SaleOrderBVO.added_tax = added_tax;
}

public static UFDouble getExchange_rate() {
	return exchange_rate;
}

public static void setExchange_rate(UFDouble exchange_rate) {
	SaleOrderBVO.exchange_rate = exchange_rate;
}

public static String getCurrency() {
	return currency;
}

public static void setCurrency(String currency) {
	SaleOrderBVO.currency = currency;
}

public static String getUnit_leaders() {
	return unit_leaders;
}

public static void setUnit_leaders(String unit_leaders) {
	SaleOrderBVO.unit_leaders = unit_leaders;
}

public static String getUnit_sales() {
	return unit_sales;
}

public static void setUnit_sales(String unit_sales) {
	SaleOrderBVO.unit_sales = unit_sales;
}

public static String getUnit_charge() {
	return unit_charge;
}

public static void setUnit_charge(String unit_charge) {
	SaleOrderBVO.unit_charge = unit_charge;
}

public static String getEpein() {
	return epein;
}

public static void setEpein(String epein) {
	SaleOrderBVO.epein = epein;
}

public static String getSlysfs() {
	return slysfs;
}

public static void setSlysfs(String slysfs) {
	SaleOrderBVO.slysfs = slysfs;
}

public static String getXlysfs() {
	return xlysfs;
}

public static void setXlysfs(String xlysfs) {
	SaleOrderBVO.xlysfs = xlysfs;
}

public static String getYsfs() {
	return ysfs;
}

public static void setYsfs(String ysfs) {
	SaleOrderBVO.ysfs = ysfs;
}

public static UFDate getHtqdsj() {
	return htqdsj;
}

public static void setHtqdsj(UFDate htqdsj) {
	SaleOrderBVO.htqdsj = htqdsj;
}


/**
 * 2017-03-01���� 
 * @return bjwlmc
 */
public static String getBjwlmc() {
	return bjwlmc;
}

/**
 * @param bjwlmc Ҫ���õ� bjwlmc
 */
public static void setBjwlmc(String bjwlmc) {
	SaleOrderBVO.bjwlmc = bjwlmc;
}

/**
 * @return materialnamex
 */
public static String getMaterialnamex() {
	return materialnamex;
}

/**
 * @param materialnamex Ҫ���õ� materialnamex
 */
public static void setMaterialnamex(String materialnamex) {
	SaleOrderBVO.materialnamex = materialnamex;
}

/**
 * @return chand
 */
public static String getChand() {
	return chand;
}

/**
 * @param chand Ҫ���õ� chand
 */
public static void setChand(String chand) {
	SaleOrderBVO.chand = chand;
}

/**
 * @return cgjg
 */
public static UFDouble getCgjg() {
	return cgjg;
}

/**
 * @param cgjg Ҫ���õ� cgjg
 */
public static void setCgjg(UFDouble cgjg) {
	SaleOrderBVO.cgjg = cgjg;
}

/**
 * @return csjhq
 */
public static Integer getCsjhq() {
	return csjhq;
}

/**
 * @param csjhq Ҫ���õ� csjhq
 */
public static void setCsjhq(Integer csjhq) {
	SaleOrderBVO.csjhq = csjhq;
}

/**
 * @return jiaohuodate
 */
public static UFDate getJiaohuodate() {
	return jiaohuodate;
}

/**
 * @param jiaohuodate Ҫ���õ� jiaohuodate
 */
public static void setJiaohuodate(UFDate jiaohuodate) {
	SaleOrderBVO.jiaohuodate = jiaohuodate;
}
}
