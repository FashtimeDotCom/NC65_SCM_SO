package nc.pubitf.so.m32.api;

import java.io.Serializable;

/**
 * @description
 * ���۵���ѯAPI�������쳣����
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 ����10:58:02
 * @author ����
 */
public interface ISaleInvoiceVO extends Serializable {
  
  /**
   * ��Ʊ��ʵ��
   */
  public static final String CSALEINVOICEID = "csaleinvoiceid";
  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * ��Ʊ��֯
   */
  public static final String PK_ORG = "pk_org";
  /**
   * ��Ʊ��֯�汾
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * ��Ʊ��
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";
  /**
   * ��Ʊ����
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * ��Ʊ���ͱ���
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * ��Ʊ����
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * �ͻ�����
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";
  /**
   * �ͻ���ӡ����
   */
  public static final String VPRINTCUSTNAME = "vprintcustname";
  /**
   * �ͻ���������
   */
  public static final String CCUSTBANKID = "ccustbankid";
  /**
   * �ͻ������˺�
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";
  /**
   * �տ�Э��
   */
  public static final String CPAYTERMID = "cpaytermid";
  /**
   * ����֤��
   */
  public static final String VCREDITNUM = "vcreditnum";
  /**
   * ��˰Ʊ��
   */
  public static final String VGOLDTAXCODE = "vgoldtaxcode";
  /**
   * �Ƿ��Ѿ�����˰
   */
  public static final String BTOGOLDTAXFLAG = "btogoldtaxflag";
  /**
   * ��󴫽�˰ʱ��
   */
  public static final String TGOLDTAXTIME = "tgoldtaxtime";
  /**
   * ����
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";
  /**
   * �۱�����
   */
  public static final String NEXCHANGERATE = "nexchangerate";
  /**
   * ��λ��
   */
  public static final String CCURRENCYID = "ccurrencyid";
  /**
   * ���ű�λ�һ���
   */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";
  /**
   * ȫ�ֱ�λ�һ���
   */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";
  /**
   * ��Ʊ�ۿ�
   */
  public static final String NHVOICEDISRATE = "nhvoicedisrate";
  /**
   * ������
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * ��ֽ��
   */
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";
  /**
   * ��˰�ϼ�
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  /**
   * ��������/����
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";
  /**
   * �ջ�����/����
   */
  public static final String CRECECOUNTRYID = "crececountryid";
  /**
   * ��˰����/����
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";
  /**
   * ��������
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";
  /**
   * ����ó��
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";
  /**
   * VATע����
   */
  public static final String VVATCODE = "vvatcode";
  /**
   * �ͻ�VATע����
   */
  public static final String VCUSTVATCODE = "vcustvatcode";
  /**
   * ó������
   */
  public static final String CTRADEWORDID = "ctradewordid";
  /**
   * ��ֱ��
   */
  public static final String BSUBUNITFLAG = "bsubunitflag";
  /**
   * �Գ���
   */
  public static final String FOPPOSEFLAG = "fopposeflag";
  /**
   * �Գ���Դ��Ʊ��
   */
  public static final String VOPPOSESRCCODE = "vopposesrccode";
  /**
   * �Գ���Դ����
   */
  public static final String COPPOSESRCID = "copposesrcid";
  /**
   * ��ע
   */
  public static final String VNOTE = "vnote";
  /**
   * ����״̬
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * ������
   */
  public static final String CREATOR = "creator";
  /**
   * �Ƶ���
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * �Ƶ�����
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * ����ʱ��
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * ����޸���
   */
  public static final String MODIFIER = "modifier";
  /**
   * ����޸�ʱ��
   */
  public static final String MODIFIEDTIME = "modifiedtime";
  /**
   * ������
   */
  public static final String APPROVER = "approver";
  /**
   * �������
   */
  public static final String TAUDITTIME = "taudittime";
  /**
   * ��ӡ����
   */
  public static final String IPRINTCOUNT = "iprintcount";
  /**
   * �Զ�����1
   */
  public static final String VDEF1 = "vdef1";
  /**
   * �Զ�����2
   */
  public static final String VDEF2 = "vdef2";
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
   * �Զ�����20
   */
  public static final String VDEF20 = "vdef20";
  /**
   * ���㷽ʽ
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";
  /**
   * vostatus
   */
  public static final String STATUS = "status";
  /**
   * dr
   */
  public static final String DR = "dr";
  /**
   * ts
   */
  public static final String TS = "ts";
  /**
   * ��Ʊ��ʵ��.��Ʊ��ʵ��
   */
  public static final String CSALEINVOICEBID_CSALEINVOICEBID = "csaleinvoicebid.csaleinvoicebid";
  /**
   * ��Ʊ��ʵ��.����
   */
  public static final String CSALEINVOICEBID_PK_GROUP = "csaleinvoicebid.pk_group";
  /**
   * ��Ʊ��ʵ��.��Ʊ��֯
   */
  public static final String CSALEINVOICEBID_PK_ORG = "csaleinvoicebid.pk_org";
  /**
   * ��Ʊ��ʵ��.��Ʊ����
   */
  public static final String CSALEINVOICEBID_DBILLDATE = "csaleinvoicebid.dbilldate";
  /**
   * ��Ʊ��ʵ��.�к�
   */
  public static final String CSALEINVOICEBID_CROWNO = "csaleinvoicebid.crowno";
  /**
   * ��Ʊ��ʵ��.�ͻ�������
   */
  public static final String CSALEINVOICEBID_CCUSTMATERIALID = "csaleinvoicebid.ccustmaterialid";
  /**
   * ��Ʊ��ʵ��.���ϵ���
   */
  public static final String CSALEINVOICEBID_CMATERIALID = "csaleinvoicebid.cmaterialid";
  /**
   * ��Ʊ��ʵ��.���ϱ���
   */
  public static final String CSALEINVOICEBID_CMATERIALVID = "csaleinvoicebid.cmaterialvid";
  /**
   * ��Ʊ��ʵ��.��Ӧ��
   */
  public static final String CSALEINVOICEBID_CVENDORID = "csaleinvoicebid.cvendorid";
  /**
   * ��Ʊ��ʵ��.��Ŀ
   */
  public static final String CSALEINVOICEBID_CPROJECTID = "csaleinvoicebid.cprojectid";
  /**
   * ��Ʊ��ʵ��.��������
   */
  public static final String CSALEINVOICEBID_CPRODUCTORID = "csaleinvoicebid.cproductorid";
  /**
   * ��Ʊ��ʵ��.��λ
   */
  public static final String CSALEINVOICEBID_CASTUNITID = "csaleinvoicebid.castunitid";
  /**
   * ��Ʊ��ʵ��.����
   */
  public static final String CSALEINVOICEBID_NASTNUM = "csaleinvoicebid.nastnum";
  /**
   * ��Ʊ��ʵ��.����λ
   */
  public static final String CSALEINVOICEBID_CUNITID = "csaleinvoicebid.cunitid";
  /**
   * ��Ʊ��ʵ��.������
   */
  public static final String CSALEINVOICEBID_NNUM = "csaleinvoicebid.nnum";
  /**
   * ��Ʊ��ʵ��.������
   */
  public static final String CSALEINVOICEBID_VCHANGERATE = "csaleinvoicebid.vchangerate";
  /**
   * ��Ʊ��ʵ��.���۵�λ
   */
  public static final String CSALEINVOICEBID_CQTUNITID = "csaleinvoicebid.cqtunitid";
  /**
   * ��Ʊ��ʵ��.���ۻ�����
   */
  public static final String CSALEINVOICEBID_VQTUNITRATE = "csaleinvoicebid.vqtunitrate";
  /**
   * ��Ʊ��ʵ��.��������
   */
  public static final String CSALEINVOICEBID_NQTUNITNUM = "csaleinvoicebid.nqtunitnum";
  /**
   * ��Ʊ��ʵ��.�ۿ���
   */
  public static final String CSALEINVOICEBID_BDISCOUNTFLAG = "csaleinvoicebid.bdiscountflag";
  /**
   * ��Ʊ��ʵ��.������
   */
  public static final String CSALEINVOICEBID_BLABORFLAG = "csaleinvoicebid.blaborflag";
  /**
   * ��Ʊ��ʵ��.��Ʒ
   */
  public static final String CSALEINVOICEBID_BLARGESSFLAG = "csaleinvoicebid.blargessflag";
  /**
   * ��Ʊ��ʵ��.���ε���
   */
  public static final String CSALEINVOICEBID_PK_BATCHCODE = "csaleinvoicebid.pk_batchcode";
  /**
   * ��Ʊ��ʵ��.���κ�
   */
  public static final String CSALEINVOICEBID_VBATCHCODE = "csaleinvoicebid.vbatchcode";
  /**
   * ��Ʊ��ʵ��.˰��
   */
  public static final String CSALEINVOICEBID_CTAXCODEID = "csaleinvoicebid.ctaxcodeid";
  /**
   * ��Ʊ��ʵ��.˰��
   */
  public static final String CSALEINVOICEBID_NTAXRATE = "csaleinvoicebid.ntaxrate";
  /**
   * ��Ʊ��ʵ��.��˰���
   */
  public static final String CSALEINVOICEBID_FTAXTYPEFLAG = "csaleinvoicebid.ftaxtypeflag";
  /**
   * ��Ʊ��ʵ��.�����ۿ�
   */
  public static final String CSALEINVOICEBID_NDISCOUNTRATE = "csaleinvoicebid.ndiscountrate";
  /**
   * ��Ʊ��ʵ��.��Ʒ�ۿ�
   */
  public static final String CSALEINVOICEBID_NITEMDISCOUNTRATE = "csaleinvoicebid.nitemdiscountrate";
  /**
   * ��Ʊ��ʵ��.��Ʊ�ۿ�
   */
  public static final String CSALEINVOICEBID_NINVOICEDISRATE = "csaleinvoicebid.ninvoicedisrate";
  /**
   * ��Ʊ��ʵ��.����˰����
   */
  public static final String CSALEINVOICEBID_NORIGTAXPRICE = "csaleinvoicebid.norigtaxprice";
  /**
   * ��Ʊ��ʵ��.����˰����
   */
  public static final String CSALEINVOICEBID_NORIGPRICE = "csaleinvoicebid.norigprice";
  /**
   * ��Ʊ��ʵ��.����˰����
   */
  public static final String CSALEINVOICEBID_NORIGTAXNETPRICE = "csaleinvoicebid.norigtaxnetprice";
  /**
   * ��Ʊ��ʵ��.����˰����
   */
  public static final String CSALEINVOICEBID_NORIGNETPRICE = "csaleinvoicebid.norignetprice";
  /**
   * ��Ʊ��ʵ��.��˰����
   */
  public static final String CSALEINVOICEBID_NQTORIGTAXPRICE = "csaleinvoicebid.nqtorigtaxprice";
  /**
   * ��Ʊ��ʵ��.��˰����
   */
  public static final String CSALEINVOICEBID_NQTORIGPRICE = "csaleinvoicebid.nqtorigprice";
  /**
   * ��Ʊ��ʵ��.��˰����
   */
  public static final String CSALEINVOICEBID_NQTORIGTAXNETPRC = "csaleinvoicebid.nqtorigtaxnetprc";
  /**
   * ��Ʊ��ʵ��.��˰����
   */
  public static final String CSALEINVOICEBID_NQTORIGNETPRICE = "csaleinvoicebid.nqtorignetprice";
  /**
   * ��Ʊ��ʵ��.˰��
   */
  public static final String CSALEINVOICEBID_NTAX = "csaleinvoicebid.ntax";
  /**
   * ��Ʊ��ʵ��.��˰���
   */
  public static final String CSALEINVOICEBID_NCALTAXMNY = "csaleinvoicebid.ncaltaxmny";
  /**
   * ��Ʊ��ʵ��.��˰���
   */
  public static final String CSALEINVOICEBID_NORIGMNY = "csaleinvoicebid.norigmny";
  /**
   * ��Ʊ��ʵ��.��˰�ϼ�
   */
  public static final String CSALEINVOICEBID_NORIGTAXMNY = "csaleinvoicebid.norigtaxmny";
  /**
   * ��Ʊ��ʵ��.�ۿ۶�
   */
  public static final String CSALEINVOICEBID_NORIGDISCOUNT = "csaleinvoicebid.norigdiscount";
  /**
   * ��Ʊ��ʵ��.�����Һ�˰����
   */
  public static final String CSALEINVOICEBID_NTAXPRICE = "csaleinvoicebid.ntaxprice";
  /**
   * ��Ʊ��ʵ��.��������˰����
   */
  public static final String CSALEINVOICEBID_NPRICE = "csaleinvoicebid.nprice";
  /**
   * ��Ʊ��ʵ��.�����Һ�˰����
   */
  public static final String CSALEINVOICEBID_NTAXNETPRICE = "csaleinvoicebid.ntaxnetprice";
  /**
   * ��Ʊ��ʵ��.��������˰����
   */
  public static final String CSALEINVOICEBID_NNETPRICE = "csaleinvoicebid.nnetprice";
  /**
   * ��Ʊ��ʵ��.���Һ�˰����
   */
  public static final String CSALEINVOICEBID_NQTTAXPRICE = "csaleinvoicebid.nqttaxprice";
  /**
   * ��Ʊ��ʵ��.������˰����
   */
  public static final String CSALEINVOICEBID_NQTPRICE = "csaleinvoicebid.nqtprice";
  /**
   * ��Ʊ��ʵ��.���Һ�˰����
   */
  public static final String CSALEINVOICEBID_NQTTAXNETPRICE = "csaleinvoicebid.nqttaxnetprice";
  /**
   * ��Ʊ��ʵ��.������˰����
   */
  public static final String CSALEINVOICEBID_NQTNETPRICE = "csaleinvoicebid.nqtnetprice";
  /**
   * ��Ʊ��ʵ��.������˰���
   */
  public static final String CSALEINVOICEBID_NMNY = "csaleinvoicebid.nmny";
  /**
   * ��Ʊ��ʵ��.���Ҽ�˰�ϼ�
   */
  public static final String CSALEINVOICEBID_NTAXMNY = "csaleinvoicebid.ntaxmny";
  /**
   * ��Ʊ��ʵ��.�����ۿ۶�
   */
  public static final String CSALEINVOICEBID_NDISCOUNT = "csaleinvoicebid.ndiscount";
  /**
   * ��Ʊ��ʵ��.���ó�ֽ��
   */
  public static final String CSALEINVOICEBID_NORIGSUBMNY = "csaleinvoicebid.norigsubmny";
  /**
   * ��Ʊ��ʵ��.���ű�����˰���
   */
  public static final String CSALEINVOICEBID_NGROUPMNY = "csaleinvoicebid.ngroupmny";
  /**
   * ��Ʊ��ʵ��.���ű��Ҽ�˰�ϼ�
   */
  public static final String CSALEINVOICEBID_NGROUPTAXMNY = "csaleinvoicebid.ngrouptaxmny";
  /**
   * ��Ʊ��ʵ��.ȫ�ֱ�����˰���
   */
  public static final String CSALEINVOICEBID_NGLOBALMNY = "csaleinvoicebid.nglobalmny";
  /**
   * ��Ʊ��ʵ��.ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public static final String CSALEINVOICEBID_NGLOBALTAXMNY = "csaleinvoicebid.nglobaltaxmny";
  /**
   * ��Ʊ��ʵ��.Դͷ��������
   */
  public static final String CSALEINVOICEBID_VFIRSTTYPE = "csaleinvoicebid.vfirsttype";
  /**
   * ��Ʊ��ʵ��.Դͷ���ݺ�
   */
  public static final String CSALEINVOICEBID_VFIRSTCODE = "csaleinvoicebid.vfirstcode";
  /**
   * ��Ʊ��ʵ��.Դͷ��������
   */
  public static final String CSALEINVOICEBID_VFIRSTTRANTYPE = "csaleinvoicebid.vfirsttrantype";
  /**
   * ��Ʊ��ʵ��.Դͷ�����к�
   */
  public static final String CSALEINVOICEBID_VFIRSTROWNO = "csaleinvoicebid.vfirstrowno";
  /**
   * ��Ʊ��ʵ��.Դͷ��������
   */
  public static final String CSALEINVOICEBID_CFIRSTID = "csaleinvoicebid.cfirstid";
  /**
   * ��Ʊ��ʵ��.Դͷ�����ӱ�
   */
  public static final String CSALEINVOICEBID_CFIRSTBID = "csaleinvoicebid.cfirstbid";
  /**
   * ��Ʊ��ʵ��.��Դ��������
   */
  public static final String CSALEINVOICEBID_VSRCTYPE = "csaleinvoicebid.vsrctype";
  /**
   * ��Ʊ��ʵ��.��Դ���ݺ�
   */
  public static final String CSALEINVOICEBID_VSRCCODE = "csaleinvoicebid.vsrccode";
  /**
   * ��Ʊ��ʵ��.��Դ��������
   */
  public static final String CSALEINVOICEBID_VSRCTRANTYPE = "csaleinvoicebid.vsrctrantype";
  /**
   * ��Ʊ��ʵ��.��Դ�����к�
   */
  public static final String CSALEINVOICEBID_VSRCROWNO = "csaleinvoicebid.vsrcrowno";
  /**
   * ��Ʊ��ʵ��.��Դ��������
   */
  public static final String CSALEINVOICEBID_CSRCID = "csaleinvoicebid.csrcid";
  /**
   * ��Ʊ��ʵ��.��Դ�����ӱ�
   */
  public static final String CSALEINVOICEBID_CSRCBID = "csaleinvoicebid.csrcbid";
  /**
   * ��Ʊ��ʵ��.�Գ���Դ�ӱ�
   */
  public static final String CSALEINVOICEBID_COPPOSESRCBID = "csaleinvoicebid.copposesrcbid";
  /**
   * ��Ʊ��ʵ��.������֯
   */
  public static final String CSALEINVOICEBID_CSALEORGID = "csaleinvoicebid.csaleorgid";
  /**
   * ��Ʊ��ʵ��.������֯
   */
  public static final String CSALEINVOICEBID_CSALEORGVID = "csaleinvoicebid.csaleorgvid";
  /**
   * ��Ʊ��ʵ��.���������������°汾
   */
  public static final String CSALEINVOICEBID_CPROFITCENTERID = "csaleinvoicebid.cprofitcenterid";
  /**
   * ��Ʊ��ʵ��.������������
   */
  public static final String CSALEINVOICEBID_CPROFITCENTERVID = "csaleinvoicebid.cprofitcentervid";
  /**
   * ��Ʊ��ʵ��.Ӧ����֯
   */
  public static final String CSALEINVOICEBID_CARORGID = "csaleinvoicebid.carorgid";
  /**
   * ��Ʊ��ʵ��.Ӧ����֯���°汾
   */
  public static final String CSALEINVOICEBID_CARORGVID = "csaleinvoicebid.carorgvid";
  /**
   * ��Ʊ��ʵ��.�����ͻ�
   */
  public static final String CSALEINVOICEBID_CORDERCUSTID = "csaleinvoicebid.cordercustid";
  /**
   * ��Ʊ��ʵ��.�Ƿ�ɢ��
   */
  public static final String CSALEINVOICEBID_BFREECUSTFLAG = "csaleinvoicebid.bfreecustflag";
  /**
   * ��Ʊ��ʵ��.ɢ��
   */
  public static final String CSALEINVOICEBID_CFREECUSTID = "csaleinvoicebid.cfreecustid";
  /**
   * ��Ʊ��ʵ��.���۲���
   */
  public static final String CSALEINVOICEBID_CDEPTID = "csaleinvoicebid.cdeptid";
  /**
   * ��Ʊ��ʵ��.���۲������°汾
   */
  public static final String CSALEINVOICEBID_CDEPTVID = "csaleinvoicebid.cdeptvid";
  /**
   * ��Ʊ��ʵ��.����ҵ��Ա
   */
  public static final String CSALEINVOICEBID_CEMPLOYEEID = "csaleinvoicebid.cemployeeid";
  /**
   * ��Ʊ��ʵ��.������������
   */
  public static final String CSALEINVOICEBID_CCHANNELTYPEID = "csaleinvoicebid.cchanneltypeid";
  /**
   * ��Ʊ��ʵ��.�ջ��ͻ�
   */
  public static final String CSALEINVOICEBID_CRECEIVECUSTID = "csaleinvoicebid.creceivecustid";
  /**
   * ��Ʊ��ʵ��.�ջ���ַ
   */
  public static final String CSALEINVOICEBID_CRECEIVEADDRID = "csaleinvoicebid.creceiveaddrid";
  /**
   * ��Ʊ��ʵ��.���䷽ʽ
   */
  public static final String CSALEINVOICEBID_CTRANSPORTTYPEID = "csaleinvoicebid.ctransporttypeid";
  /**
   * ��Ʊ��ʵ��.�����֯
   */
  public static final String CSALEINVOICEBID_CSENDSTOCKORGID = "csaleinvoicebid.csendstockorgid";
  /**
   * ��Ʊ��ʵ��.�����֯
   */
  public static final String CSALEINVOICEBID_CSENDSTOCKORGVID = "csaleinvoicebid.csendstockorgvid";
  /**
   * ��Ʊ��ʵ��.�ֿ�
   */
  public static final String CSALEINVOICEBID_CSENDSTORDOCID = "csaleinvoicebid.csendstordocid";
  /**
   * ��Ʊ��ʵ��.��Ʒ��
   */
  public static final String CSALEINVOICEBID_CPRODLINEID = "csaleinvoicebid.cprodlineid";
  /**
   * ��Ʊ��ʵ��.��֧��Ŀ
   */
  public static final String CSALEINVOICEBID_CCOSTSUBJID = "csaleinvoicebid.ccostsubjid";
  /**
   * ��Ʊ��ʵ��.��ͬ��
   */
  public static final String CSALEINVOICEBID_CCTMANAGEID = "csaleinvoicebid.cctmanageid";
  /**
   * ��Ʊ��ʵ��.�Ĵ湩Ӧ��
   */
  public static final String CSALEINVOICEBID_CVMIVENDERID = "csaleinvoicebid.cvmivenderid";
  /**
   * ��Ʊ��ʵ��.���Ļ��ܺ�
   */
  public static final String CSALEINVOICEBID_VSUMCODE = "csaleinvoicebid.vsumcode";
  /**
   * ��Ʊ��ʵ��.���Ļ�������
   */
  public static final String CSALEINVOICEBID_CSUMID = "csaleinvoicebid.csumid";
  /**
   * ��Ʊ��ʵ��.�ۼ�Ӧ��δ��������
   */
  public static final String CSALEINVOICEBID_NSHOULDOUTNUM = "csaleinvoicebid.nshouldoutnum";
  /**
   * ��Ʊ��ʵ��.�ۼƳ�������
   */
  public static final String CSALEINVOICEBID_NTOTALOUTNUM = "csaleinvoicebid.ntotaloutnum";
  /**
   * ��Ʊ��ʵ��.�ۼ�ȷ��Ӧ������
   */
  public static final String CSALEINVOICEBID_NTOTALINCOMENUM = "csaleinvoicebid.ntotalincomenum";
  /**
   * ��Ʊ��ʵ��.�ۼ�ȷ��Ӧ�ս��
   */
  public static final String CSALEINVOICEBID_NTOTALINCOMEMNY = "csaleinvoicebid.ntotalincomemny";
  /**
   * ��Ʊ��ʵ��.�ۼƳɱ���������
   */
  public static final String CSALEINVOICEBID_NTOTALCOSTNUM = "csaleinvoicebid.ntotalcostnum";
  /**
   * ��Ʊ��ʵ��.�ۼ��տ���
   */
  public static final String CSALEINVOICEBID_NTOTALPAYMNY = "csaleinvoicebid.ntotalpaymny";
  /**
   * ��Ʊ��ʵ��.��ע
   */
  public static final String CSALEINVOICEBID_VROWNOTE = "csaleinvoicebid.vrownote";
  /**
   * ��Ʊ��ʵ��.���ϸ�������1
   */
  public static final String CSALEINVOICEBID_VFREE1 = "csaleinvoicebid.vfree1";
  /**
   * ��Ʊ��ʵ��.���ϸ�������2
   */
  public static final String CSALEINVOICEBID_VFREE2 = "csaleinvoicebid.vfree2";
  /**
   * ��Ʊ��ʵ��.���ϸ�������3
   */
  public static final String CSALEINVOICEBID_VFREE3 = "csaleinvoicebid.vfree3";
  /**
   * ��Ʊ��ʵ��.���ϸ�������4
   */
  public static final String CSALEINVOICEBID_VFREE4 = "csaleinvoicebid.vfree4";
  /**
   * ��Ʊ��ʵ��.���ϸ�������5
   */
  public static final String CSALEINVOICEBID_VFREE5 = "csaleinvoicebid.vfree5";
  /**
   * ��Ʊ��ʵ��.���ϸ�������6
   */
  public static final String CSALEINVOICEBID_VFREE6 = "csaleinvoicebid.vfree6";
  /**
   * ��Ʊ��ʵ��.���ϸ�������7
   */
  public static final String CSALEINVOICEBID_VFREE7 = "csaleinvoicebid.vfree7";
  /**
   * ��Ʊ��ʵ��.���ϸ�������8
   */
  public static final String CSALEINVOICEBID_VFREE8 = "csaleinvoicebid.vfree8";
  /**
   * ��Ʊ��ʵ��.���ϸ�������9
   */
  public static final String CSALEINVOICEBID_VFREE9 = "csaleinvoicebid.vfree9";
  /**
   * ��Ʊ��ʵ��.���ϸ�������10
   */
  public static final String CSALEINVOICEBID_VFREE10 = "csaleinvoicebid.vfree10";
  /**
   * ��Ʊ��ʵ��.�Զ�����1
   */
  public static final String CSALEINVOICEBID_VBDEF1 = "csaleinvoicebid.vbdef1";
  /**
   * ��Ʊ��ʵ��.�Զ�����2
   */
  public static final String CSALEINVOICEBID_VBDEF2 = "csaleinvoicebid.vbdef2";
  /**
   * ��Ʊ��ʵ��.�Զ�����3
   */
  public static final String CSALEINVOICEBID_VBDEF3 = "csaleinvoicebid.vbdef3";
  /**
   * ��Ʊ��ʵ��.�Զ�����4
   */
  public static final String CSALEINVOICEBID_VBDEF4 = "csaleinvoicebid.vbdef4";
  /**
   * ��Ʊ��ʵ��.�Զ�����5
   */
  public static final String CSALEINVOICEBID_VBDEF5 = "csaleinvoicebid.vbdef5";
  /**
   * ��Ʊ��ʵ��.�Զ�����6
   */
  public static final String CSALEINVOICEBID_VBDEF6 = "csaleinvoicebid.vbdef6";
  /**
   * ��Ʊ��ʵ��.�Զ�����7
   */
  public static final String CSALEINVOICEBID_VBDEF7 = "csaleinvoicebid.vbdef7";
  /**
   * ��Ʊ��ʵ��.�Զ�����8
   */
  public static final String CSALEINVOICEBID_VBDEF8 = "csaleinvoicebid.vbdef8";
  /**
   * ��Ʊ��ʵ��.�Զ�����9
   */
  public static final String CSALEINVOICEBID_VBDEF9 = "csaleinvoicebid.vbdef9";
  /**
   * ��Ʊ��ʵ��.�Զ�����10
   */
  public static final String CSALEINVOICEBID_VBDEF10 = "csaleinvoicebid.vbdef10";
  /**
   * ��Ʊ��ʵ��.�Զ�����11
   */
  public static final String CSALEINVOICEBID_VBDEF11 = "csaleinvoicebid.vbdef11";
  /**
   * ��Ʊ��ʵ��.�Զ�����12
   */
  public static final String CSALEINVOICEBID_VBDEF12 = "csaleinvoicebid.vbdef12";
  /**
   * ��Ʊ��ʵ��.�Զ�����13
   */
  public static final String CSALEINVOICEBID_VBDEF13 = "csaleinvoicebid.vbdef13";
  /**
   * ��Ʊ��ʵ��.�Զ�����14
   */
  public static final String CSALEINVOICEBID_VBDEF14 = "csaleinvoicebid.vbdef14";
  /**
   * ��Ʊ��ʵ��.�Զ�����15
   */
  public static final String CSALEINVOICEBID_VBDEF15 = "csaleinvoicebid.vbdef15";
  /**
   * ��Ʊ��ʵ��.�Զ�����16
   */
  public static final String CSALEINVOICEBID_VBDEF16 = "csaleinvoicebid.vbdef16";
  /**
   * ��Ʊ��ʵ��.�Զ�����17
   */
  public static final String CSALEINVOICEBID_VBDEF17 = "csaleinvoicebid.vbdef17";
  /**
   * ��Ʊ��ʵ��.�Զ�����18
   */
  public static final String CSALEINVOICEBID_VBDEF18 = "csaleinvoicebid.vbdef18";
  /**
   * ��Ʊ��ʵ��.�Զ�����19
   */
  public static final String CSALEINVOICEBID_VBDEF19 = "csaleinvoicebid.vbdef19";
  /**
   * ��Ʊ��ʵ��.�Զ�����20
   */
  public static final String CSALEINVOICEBID_VBDEF20 = "csaleinvoicebid.vbdef20";
  /**
   * ��Ʊ��ʵ��.��Դ���ݱ�ͷʱ���
   */
  public static final String CSALEINVOICEBID_SRCTS = "csaleinvoicebid.srcts";
  /**
   * ��Ʊ��ʵ��.��Դ���ݱ���ʱ���
   */
  public static final String CSALEINVOICEBID_SRCBTS = "csaleinvoicebid.srcbts";
  /**
   * ��Ʊ��ʵ��.�ɳ�������
   */
  public static final String CSALEINVOICEBID_NCANOUTNUM = "csaleinvoicebid.ncanoutnum";
  /**
   * ��Ʊ��ʵ��.��Դ��������
   */
  public static final String CSALEINVOICEBID_NSRCNUM = "csaleinvoicebid.nsrcnum";
  /**
   * ��Ʊ��ʵ��.���ϻ�������
   */
  public static final String CSALEINVOICEBID_CMARBASCALSSID = "csaleinvoicebid.cmarbascalssid";
  /**
   * ��Ʊ��ʵ��.������������
   */
  public static final String CSALEINVOICEBID_CSPROFITCENTERVID = "csaleinvoicebid.csprofitcentervid";
  /**
   * ��Ʊ��ʵ��.���������������°汾
   */
  public static final String CSALEINVOICEBID_CSPROFITCENTERID = "csaleinvoicebid.csprofitcenterid";
  /**
   * ��Ʊ��ʵ��.������
   */
  public static final String CSALEINVOICEBID_CMFFILEID = "csaleinvoicebid.cmffileid";
  /**
   * ��Ʊ��ʵ��.vostatus
   */
  public static final String CSALEINVOICEBID_STATUS = "csaleinvoicebid.status";
  /**
   * ��Ʊ��ʵ��.dr
   */
  public static final String CSALEINVOICEBID_DR = "csaleinvoicebid.dr";
  /**
   * ��Ʊ��ʵ��.ts
   */
  public static final String CSALEINVOICEBID_TS = "csaleinvoicebid.ts";
}


