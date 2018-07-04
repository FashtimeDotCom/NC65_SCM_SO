package nc.pubitf.so.m30.api;

import java.io.Serializable;

/**
 * @description
 * ���۶�����ѯAPI�������쳣����
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 ����10:58:15
 * @author ����
 */
public interface ISaleOrderVO extends Serializable {
  
  /**
   * ���۶�����ʵ��
   */
  public static final String CSALEORDERID = "csaleorderid";
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
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * �������ͱ���
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * ��Ʒ�Ҹ�����
   */
  public static final String CARSUBTYPEID = "carsubtypeid";
  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";
  /**
   * ���ݺ�
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * ��������
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * �ͻ�
   */
  public static final String CCUSTOMERID = "ccustomerid";
  /**
   * �Ƿ�ɢ��
   */
  public static final String BFREECUSTFLAG = "bfreecustflag";
  /**
   * ɢ��
   */
  public static final String CFREECUSTID = "cfreecustid";
  /**
   * ����
   */
  public static final String CDEPTVID = "cdeptvid";
  /**
   * ����
   */
  public static final String CDEPTID = "cdeptid";
  /**
   * ҵ��Ա
   */
  public static final String CEMPLOYEEID = "cemployeeid";
  /**
   * ԭ��
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";
  /**
   * ��Ʊ�ͻ�
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";
  /**
   * ��������
   */
  public static final String CCUSTBANKID = "ccustbankid";
  /**
   * ���������˻�
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";
  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";
  /**
   * �տ�Э��
   */
  public static final String CPAYTERMID = "cpaytermid";
  /**
   * ���䷽ʽ
   */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";
  /**
   * ó������
   */
  public static final String CTRADEWORDID = "ctradewordid";
  /**
   * �����ۿ�
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";
  /**
   * ����֤��
   */
  public static final String VCREDITNUM = "vcreditnum";
  /**
   * ���㷽ʽ
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";
  /**
   * �����˷�
   */
  public static final String BADVFEEFLAG = "badvfeeflag";
  /**
   * �����տ����
   */
  public static final String NPRECEIVERATE = "npreceiverate";
  /**
   * �����տ��޶�
   */
  public static final String NPRECEIVEQUOTA = "npreceivequota";
  /**
   * �տ��޶����Ԥ��
   */
  public static final String BPRECEIVEFLAG = "bpreceiveflag";
  /**
   * ʵ��Ԥ�տ���
   */
  public static final String NPRECEIVEMNY = "npreceivemny";
  /**
   * ʵ���տ���
   */
  public static final String NRECEIVEDMNY = "nreceivedmny";
  /**
   * �����տ���
   */
  public static final String NTHISRECEIVEMNY = "nthisreceivemny";
  /**
   * ������
   */
  public static final String NTOTALNUM = "ntotalnum";
  /**
   * �ϼ�����
   */
  public static final String NTOTALWEIGHT = "ntotalweight";
  /**
   * �ϼ����
   */
  public static final String NTOTALVOLUME = "ntotalvolume";
  /**
   * �ܼ���
   */
  public static final String NTOTALPIECE = "ntotalpiece";
  /**
   * ��˰�ϼ�
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  /**
   * ��Ʒ��˰�ϼ�
   */
  public static final String NLRGTOTALORIGMNY = "nlrgtotalorigmny";
  /**
   * ���ǰ���
   */
  public static final String NTOTALMNY = "ntotalmny";
  /**
   * ���ó�ֽ��
   */
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";
  /**
   * �Ƿ���
   */
  public static final String BOFFSETFLAG = "boffsetflag";
  /**
   * �Է�������
   */
  public static final String VCOOPPOHCODE = "vcooppohcode";
  /**
   * �ɲɹ�����Эͬ����
   */
  public static final String BPOCOOPTOMEFLAG = "bpocooptomeflag";
  /**
   * ����Ŀ�ĵ�������֯
   */
  public static final String DEST_PK_ORG = "dest_pk_org";
  /**
   * ��Эͬ���ɲɹ�����
   */
  public static final String BCOOPTOPOFLAG = "bcooptopoflag";
  /**
   * ����״̬
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * ������״̬
   */
  public static final String FPFSTATUSFLAG = "fpfstatusflag";
  /**
   * ��ע
   */
  public static final String VNOTE = "vnote";
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
   * �Ƶ���
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * �Ƶ�����
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * ������
   */
  public static final String CREATOR = "creator";
  /**
   * ����ʱ��
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * �޸���
   */
  public static final String MODIFIER = "modifier";
  /**
   * �޸�ʱ��
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
   * �޶���
   */
  public static final String CREVISERID = "creviserid";
  /**
   * �޶��汾��
   */
  public static final String IVERSION = "iversion";
  /**
   * �޶�����
   */
  public static final String VREVISEREASON = "vrevisereason";
  /**
   * �޶�ʱ��
   */
  public static final String TREVISETIME = "trevisetime";
  /**
   * �����ر�
   */
  public static final String BSENDENDFLAG = "bsendendflag";
  /**
   * ����ر�
   */
  public static final String BOUTENDFLAG = "boutendflag";
  /**
   * ��Ʊ�ر�
   */
  public static final String BINVOICENDFLAG = "binvoicendflag";
  /**
   * �ɱ�����ر�
   */
  public static final String BCOSTSETTLEFLAG = "bcostsettleflag";
  /**
   * �������ر�
   */
  public static final String BARSETTLEFLAG = "barsettleflag";
  /**
   * ��ӡ����
   */
  public static final String IPRINTCOUNT = "iprintcount";
  /**
   * ������Դ��������
   */
  public static final String VBILLSRCTYPE = "vbillsrctype";
  /**
   * ������Դ����ID
   */
  public static final String CBILLSRCID = "cbillsrcid";
  /**
   * �ջ��ͻ�
   */
  public static final String CHRECEIVECUSTID = "chreceivecustid";
  /**
   * �ջ���ַ
   */
  public static final String CHRECEIVEADDID = "chreceiveaddid";
  /**
   * ������
   */
  public static final String BC_03 = "bc_03";
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
   * ���۶�����ʵ��.���۶�����ʵ��
   */
  public static final String SO_SALEORDER_B_CSALEORDERBID = "so_saleorder_b.csaleorderbid";
  /**
   * ���۶�����ʵ��.����
   */
  public static final String SO_SALEORDER_B_PK_GROUP = "so_saleorder_b.pk_group";
  /**
   * ���۶�����ʵ��.������֯
   */
  public static final String SO_SALEORDER_B_PK_ORG = "so_saleorder_b.pk_org";
  /**
   * ���۶�����ʵ��.��������
   */
  public static final String SO_SALEORDER_B_DBILLDATE = "so_saleorder_b.dbilldate";
  /**
   * ���۶�����ʵ��.�к�
   */
  public static final String SO_SALEORDER_B_CROWNO = "so_saleorder_b.crowno";
  /**
   * ���۶�����ʵ��.�ͻ�������
   */
  public static final String SO_SALEORDER_B_CCUSTMATERIALID = "so_saleorder_b.ccustmaterialid";
  /**
   * ���۶�����ʵ��.���ϱ���
   */
  public static final String SO_SALEORDER_B_CMATERIALVID = "so_saleorder_b.cmaterialvid";
  /**
   * ���۶�����ʵ��.����
   */
  public static final String SO_SALEORDER_B_CMATERIALID = "so_saleorder_b.cmaterialid";
  /**
   * ���۶�����ʵ��.��Ӧ��
   */
  public static final String SO_SALEORDER_B_CVENDORID = "so_saleorder_b.cvendorid";
  /**
   * ���۶�����ʵ��.��Ŀ
   */
  public static final String SO_SALEORDER_B_CPROJECTID = "so_saleorder_b.cprojectid";
  /**
   * ���۶�����ʵ��.��������
   */
  public static final String SO_SALEORDER_B_CPRODUCTORID = "so_saleorder_b.cproductorid";
  /**
   * ���۶�����ʵ��.����
   */
  public static final String SO_SALEORDER_B_CFACTORYID = "so_saleorder_b.cfactoryid";
  /**
   * ���۶�����ʵ��.�����ȼ�
   */
  public static final String SO_SALEORDER_B_CQUALITYLEVELID = "so_saleorder_b.cqualitylevelid";
  /**
   * ���۶�����ʵ��.ԭ����
   */
  public static final String SO_SALEORDER_B_CORIGCOUNTRYID = "so_saleorder_b.corigcountryid";
  /**
   * ���۶�����ʵ��.ԭ������
   */
  public static final String SO_SALEORDER_B_CORIGAREAID = "so_saleorder_b.corigareaid";
  /**
   * ���۶�����ʵ��.����λ
   */
  public static final String SO_SALEORDER_B_CUNITID = "so_saleorder_b.cunitid";
  /**
   * ���۶�����ʵ��.��λ
   */
  public static final String SO_SALEORDER_B_CASTUNITID = "so_saleorder_b.castunitid";
  /**
   * ���۶�����ʵ��.������
   */
  public static final String SO_SALEORDER_B_NNUM = "so_saleorder_b.nnum";
  /**
   * ���۶�����ʵ��.����
   */
  public static final String SO_SALEORDER_B_NASTNUM = "so_saleorder_b.nastnum";
  /**
   * ���۶�����ʵ��.������
   */
  public static final String SO_SALEORDER_B_VCHANGERATE = "so_saleorder_b.vchangerate";
  /**
   * ���۶�����ʵ��.���۵�λ
   */
  public static final String SO_SALEORDER_B_CQTUNITID = "so_saleorder_b.cqtunitid";
  /**
   * ���۶�����ʵ��.���۵�λ����
   */
  public static final String SO_SALEORDER_B_NQTUNITNUM = "so_saleorder_b.nqtunitnum";
  /**
   * ���۶�����ʵ��.���ۻ�����
   */
  public static final String SO_SALEORDER_B_VQTUNITRATE = "so_saleorder_b.vqtunitrate";
  /**
   * ���۶�����ʵ��.�����ۿ�
   */
  public static final String SO_SALEORDER_B_NDISCOUNTRATE = "so_saleorder_b.ndiscountrate";
  /**
   * ���۶�����ʵ��.��Ʒ�ۿ�
   */
  public static final String SO_SALEORDER_B_NITEMDISCOUNTRATE = "so_saleorder_b.nitemdiscountrate";
  /**
   * ���۶�����ʵ��.˰��
   */
  public static final String SO_SALEORDER_B_CTAXCODEID = "so_saleorder_b.ctaxcodeid";
  /**
   * ���۶�����ʵ��.˰��
   */
  public static final String SO_SALEORDER_B_NTAXRATE = "so_saleorder_b.ntaxrate";
  /**
   * ���۶�����ʵ��.��˰���
   */
  public static final String SO_SALEORDER_B_FTAXTYPEFLAG = "so_saleorder_b.ftaxtypeflag";
  /**
   * ���۶�����ʵ��.��λ��
   */
  public static final String SO_SALEORDER_B_CCURRENCYID = "so_saleorder_b.ccurrencyid";
  /**
   * ���۶�����ʵ��.�۱�����
   */
  public static final String SO_SALEORDER_B_NEXCHANGERATE = "so_saleorder_b.nexchangerate";
  /**
   * ���۶�����ʵ��.��˰����
   */
  public static final String SO_SALEORDER_B_NQTORIGTAXPRICE = "so_saleorder_b.nqtorigtaxprice";
  /**
   * ���۶�����ʵ��.��˰����
   */
  public static final String SO_SALEORDER_B_NQTORIGPRICE = "so_saleorder_b.nqtorigprice";
  /**
   * ���۶�����ʵ��.��˰����
   */
  public static final String SO_SALEORDER_B_NQTORIGTAXNETPRC = "so_saleorder_b.nqtorigtaxnetprc";
  /**
   * ���۶�����ʵ��.��˰����
   */
  public static final String SO_SALEORDER_B_NQTORIGNETPRICE = "so_saleorder_b.nqtorignetprice";
  /**
   * ���۶�����ʵ��.����˰����
   */
  public static final String SO_SALEORDER_B_NORIGTAXPRICE = "so_saleorder_b.norigtaxprice";
  /**
   * ���۶�����ʵ��.����˰����
   */
  public static final String SO_SALEORDER_B_NORIGPRICE = "so_saleorder_b.norigprice";
  /**
   * ���۶�����ʵ��.����˰����
   */
  public static final String SO_SALEORDER_B_NORIGTAXNETPRICE = "so_saleorder_b.norigtaxnetprice";
  /**
   * ���۶�����ʵ��.����˰����
   */
  public static final String SO_SALEORDER_B_NORIGNETPRICE = "so_saleorder_b.norignetprice";
  /**
   * ���۶�����ʵ��.˰��
   */
  public static final String SO_SALEORDER_B_NTAX = "so_saleorder_b.ntax";
  /**
   * ���۶�����ʵ��.��˰���
   */
  public static final String SO_SALEORDER_B_NCALTAXMNY = "so_saleorder_b.ncaltaxmny";
  /**
   * ���۶�����ʵ��.��˰���
   */
  public static final String SO_SALEORDER_B_NORIGMNY = "so_saleorder_b.norigmny";
  /**
   * ���۶�����ʵ��.��˰�ϼ�
   */
  public static final String SO_SALEORDER_B_NORIGTAXMNY = "so_saleorder_b.norigtaxmny";
  /**
   * ���۶�����ʵ��.�ۿ۶�
   */
  public static final String SO_SALEORDER_B_NORIGDISCOUNT = "so_saleorder_b.norigdiscount";
  /**
   * ���۶�����ʵ��.���ǰ���
   */
  public static final String SO_SALEORDER_B_NBFORIGSUBMNY = "so_saleorder_b.nbforigsubmny";
  /**
   * ���۶�����ʵ��.���Һ�˰����
   */
  public static final String SO_SALEORDER_B_NQTTAXPRICE = "so_saleorder_b.nqttaxprice";
  /**
   * ���۶�����ʵ��.������˰����
   */
  public static final String SO_SALEORDER_B_NQTPRICE = "so_saleorder_b.nqtprice";
  /**
   * ���۶�����ʵ��.���Һ�˰����
   */
  public static final String SO_SALEORDER_B_NQTTAXNETPRICE = "so_saleorder_b.nqttaxnetprice";
  /**
   * ���۶�����ʵ��.������˰����
   */
  public static final String SO_SALEORDER_B_NQTNETPRICE = "so_saleorder_b.nqtnetprice";
  /**
   * ���۶�����ʵ��.�����Һ�˰����
   */
  public static final String SO_SALEORDER_B_NTAXPRICE = "so_saleorder_b.ntaxprice";
  /**
   * ���۶�����ʵ��.��������˰����
   */
  public static final String SO_SALEORDER_B_NPRICE = "so_saleorder_b.nprice";
  /**
   * ���۶�����ʵ��.�����Һ�˰����
   */
  public static final String SO_SALEORDER_B_NTAXNETPRICE = "so_saleorder_b.ntaxnetprice";
  /**
   * ���۶�����ʵ��.��������˰����
   */
  public static final String SO_SALEORDER_B_NNETPRICE = "so_saleorder_b.nnetprice";
  /**
   * ���۶�����ʵ��.������˰���
   */
  public static final String SO_SALEORDER_B_NMNY = "so_saleorder_b.nmny";
  /**
   * ���۶�����ʵ��.���Ҽ�˰�ϼ�
   */
  public static final String SO_SALEORDER_B_NTAXMNY = "so_saleorder_b.ntaxmny";
  /**
   * ���۶�����ʵ��.�����ۿ۶�
   */
  public static final String SO_SALEORDER_B_NDISCOUNT = "so_saleorder_b.ndiscount";
  /**
   * ���۶�����ʵ��.���ű�λ�һ���
   */
  public static final String SO_SALEORDER_B_NGROUPEXCHGRATE = "so_saleorder_b.ngroupexchgrate";
  /**
   * ���۶�����ʵ��.���ű�����˰���
   */
  public static final String SO_SALEORDER_B_NGROUPMNY = "so_saleorder_b.ngroupmny";
  /**
   * ���۶�����ʵ��.���ű��Ҽ�˰�ϼ�
   */
  public static final String SO_SALEORDER_B_NGROUPTAXMNY = "so_saleorder_b.ngrouptaxmny";
  /**
   * ���۶�����ʵ��.ȫ�ֱ�λ�һ���
   */
  public static final String SO_SALEORDER_B_NGLOBALEXCHGRATE = "so_saleorder_b.nglobalexchgrate";
  /**
   * ���۶�����ʵ��.ȫ�ֱ�����˰���
   */
  public static final String SO_SALEORDER_B_NGLOBALMNY = "so_saleorder_b.nglobalmny";
  /**
   * ���۶�����ʵ��.ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public static final String SO_SALEORDER_B_NGLOBALTAXMNY = "so_saleorder_b.nglobaltaxmny";
  /**
   * ���۶�����ʵ��.ѯ��ԭ�Һ�˰����
   */
  public static final String SO_SALEORDER_B_NASKQTORIGTAXPRC = "so_saleorder_b.naskqtorigtaxprc";
  /**
   * ���۶�����ʵ��.ѯ��ԭ����˰����
   */
  public static final String SO_SALEORDER_B_NASKQTORIGPRICE = "so_saleorder_b.naskqtorigprice";
  /**
   * ���۶�����ʵ��.ѯ��ԭ�Һ�˰����
   */
  public static final String SO_SALEORDER_B_NASKQTORIGTXNTPRC = "so_saleorder_b.naskqtorigtxntprc";
  /**
   * ���۶�����ʵ��.ѯ��ԭ����˰����
   */
  public static final String SO_SALEORDER_B_NASKQTORIGNETPRICE = "so_saleorder_b.naskqtorignetprice";
  /**
   * ���۶�����ʵ��.����
   */
  public static final String SO_SALEORDER_B_NWEIGHT = "so_saleorder_b.nweight";
  /**
   * ���۶�����ʵ��.���
   */
  public static final String SO_SALEORDER_B_NVOLUME = "so_saleorder_b.nvolume";
  /**
   * ���۶�����ʵ��.����
   */
  public static final String SO_SALEORDER_B_NPIECE = "so_saleorder_b.npiece";
  /**
   * ���۶�����ʵ��.��Ʒ��
   */
  public static final String SO_SALEORDER_B_CPRODLINEID = "so_saleorder_b.cprodlineid";
  /**
   * ���۶�����ʵ��.���κ�
   */
  public static final String SO_SALEORDER_B_VBATCHCODE = "so_saleorder_b.vbatchcode";
  /**
   * ���۶�����ʵ��.���ε���
   */
  public static final String SO_SALEORDER_B_PK_BATCHCODE = "so_saleorder_b.pk_batchcode";
  /**
   * ���۶�����ʵ��.������
   */
  public static final String SO_SALEORDER_B_BLABORFLAG = "so_saleorder_b.blaborflag";
  /**
   * ���۶�����ʵ��.�ۿ���
   */
  public static final String SO_SALEORDER_B_BDISCOUNTFLAG = "so_saleorder_b.bdiscountflag";
  /**
   * ���۶�����ʵ��.��Ʒ
   */
  public static final String SO_SALEORDER_B_BLARGESSFLAG = "so_saleorder_b.blargessflag";
  /**
   * ���۶�����ʵ��.������
   */
  public static final String SO_SALEORDER_B_BBINDFLAG = "so_saleorder_b.bbindflag";
  /**
   * ���۶�����ʵ��.��������
   */
  public static final String SO_SALEORDER_B_DSENDDATE = "so_saleorder_b.dsenddate";
  /**
   * ���۶�����ʵ��.��������
   */
  public static final String SO_SALEORDER_B_DRECEIVEDATE = "so_saleorder_b.dreceivedate";
  /**
   * ���۶�����ʵ��.�ջ��ͻ�
   */
  public static final String SO_SALEORDER_B_CRECEIVECUSTID = "so_saleorder_b.creceivecustid";
  /**
   * ���۶�����ʵ��.�ջ�����
   */
  public static final String SO_SALEORDER_B_CRECEIVEAREAID = "so_saleorder_b.creceiveareaid";
  /**
   * ���۶�����ʵ��.�ջ���ַ
   */
  public static final String SO_SALEORDER_B_CRECEIVEADDRID = "so_saleorder_b.creceiveaddrid";
  /**
   * ���۶�����ʵ��.�ջ��ص�
   */
  public static final String SO_SALEORDER_B_CRECEIVEADDDOCID = "so_saleorder_b.creceiveadddocid";
  /**
   * ���۶�����ʵ��.���������֯
   */
  public static final String SO_SALEORDER_B_CSENDSTOCKORGVID = "so_saleorder_b.csendstockorgvid";
  /**
   * ���۶�����ʵ��.���������֯���°汾
   */
  public static final String SO_SALEORDER_B_CSENDSTOCKORGID = "so_saleorder_b.csendstockorgid";
  /**
   * ���۶�����ʵ��.�����ֿ�
   */
  public static final String SO_SALEORDER_B_CSENDSTORDOCID = "so_saleorder_b.csendstordocid";
  /**
   * ���۶�����ʵ��.������֯
   */
  public static final String SO_SALEORDER_B_CTRAFFICORGVID = "so_saleorder_b.ctrafficorgvid";
  /**
   * ���۶�����ʵ��.������֯
   */
  public static final String SO_SALEORDER_B_CTRAFFICORGID = "so_saleorder_b.ctrafficorgid";
  /**
   * ���۶�����ʵ��.���������֯
   */
  public static final String SO_SALEORDER_B_CSETTLEORGVID = "so_saleorder_b.csettleorgvid";
  /**
   * ���۶�����ʵ��.���������֯
   */
  public static final String SO_SALEORDER_B_CSETTLEORGID = "so_saleorder_b.csettleorgid";
  /**
   * ���۶�����ʵ��.�ջ�����/����
   */
  public static final String SO_SALEORDER_B_CRECECOUNTRYID = "so_saleorder_b.crececountryid";
  /**
   * ���۶�����ʵ��.��������/����
   */
  public static final String SO_SALEORDER_B_CSENDCOUNTRYID = "so_saleorder_b.csendcountryid";
  /**
   * ���۶�����ʵ��.��˰����/����
   */
  public static final String SO_SALEORDER_B_CTAXCOUNTRYID = "so_saleorder_b.ctaxcountryid";
  /**
   * ���۶�����ʵ��.��������
   */
  public static final String SO_SALEORDER_B_FBUYSELLFLAG = "so_saleorder_b.fbuysellflag";
  /**
   * ���۶�����ʵ��.����ó��
   */
  public static final String SO_SALEORDER_B_BTRIATRADEFLAG = "so_saleorder_b.btriatradeflag";
  /**
   * ���۶�����ʵ��.Ӧ����֯
   */
  public static final String SO_SALEORDER_B_CARORGVID = "so_saleorder_b.carorgvid";
  /**
   * ���۶�����ʵ��.Ӧ����֯���°汾
   */
  public static final String SO_SALEORDER_B_CARORGID = "so_saleorder_b.carorgid";
  /**
   * ���۶�����ʵ��.������������
   */
  public static final String SO_SALEORDER_B_CPROFITCENTERVID = "so_saleorder_b.cprofitcentervid";
  /**
   * ���۶�����ʵ��.���������������°汾
   */
  public static final String SO_SALEORDER_B_CPROFITCENTERID = "so_saleorder_b.cprofitcenterid";
  /**
   * ���۶�����ʵ��.�޶�����
   */
  public static final String SO_SALEORDER_B_VBREVISEREASON = "so_saleorder_b.vbrevisereason";
  /**
   * ���۶�����ʵ��.��״̬
   */
  public static final String SO_SALEORDER_B_FROWSTATUS = "so_saleorder_b.frowstatus";
  /**
   * ���۶�����ʵ��.�б�ע
   */
  public static final String SO_SALEORDER_B_VROWNOTE = "so_saleorder_b.vrownote";
  /**
   * ���۶�����ʵ��.�۸����
   */
  public static final String SO_SALEORDER_B_CPRICEFORMID = "so_saleorder_b.cpriceformid";
  /**
   * ���۶�����ʵ��.�۸�����
   */
  public static final String SO_SALEORDER_B_CPRICEPOLICYID = "so_saleorder_b.cpricepolicyid";
  /**
   * ���۶�����ʵ��.�۸���Ŀ
   */
  public static final String SO_SALEORDER_B_CPRICEITEMID = "so_saleorder_b.cpriceitemid";
  /**
   * ���۶�����ʵ��.��Ŀ��
   */
  public static final String SO_SALEORDER_B_CPRICEITEMTABLEID = "so_saleorder_b.cpriceitemtableid";
  /**
   * ���۶�����ʵ��.���ת����
   */
  public static final String SO_SALEORDER_B_BJCZXSFLAG = "so_saleorder_b.bjczxsflag";
  /**
   * ���۶�����ʵ��.��ͬ����
   */
  public static final String SO_SALEORDER_B_VCTTYPE = "so_saleorder_b.vcttype";
  /**
   * ���۶�����ʵ��.���ۺ�ͬ��
   */
  public static final String SO_SALEORDER_B_VCTCODE = "so_saleorder_b.vctcode";
  /**
   * ���۶�����ʵ��.��ͬ����
   */
  public static final String SO_SALEORDER_B_CCTMANAGEID = "so_saleorder_b.cctmanageid";
  /**
   * ���۶�����ʵ��.��ͬ�ӱ�
   */
  public static final String SO_SALEORDER_B_CCTMANAGEBID = "so_saleorder_b.cctmanagebid";
  /**
   * ���۶�����ʵ��.��Դ��������
   */
  public static final String SO_SALEORDER_B_VSRCTYPE = "so_saleorder_b.vsrctype";
  /**
   * ���۶�����ʵ��.��Դ��������
   */
  public static final String SO_SALEORDER_B_VSRCTRANTYPE = "so_saleorder_b.vsrctrantype";
  /**
   * ���۶�����ʵ��.��Դ���ݺ�
   */
  public static final String SO_SALEORDER_B_VSRCCODE = "so_saleorder_b.vsrccode";
  /**
   * ���۶�����ʵ��.��Դ�����к�
   */
  public static final String SO_SALEORDER_B_VSRCROWNO = "so_saleorder_b.vsrcrowno";
  /**
   * ���۶�����ʵ��.��Դ��������
   */
  public static final String SO_SALEORDER_B_CSRCID = "so_saleorder_b.csrcid";
  /**
   * ���۶�����ʵ��.��Դ���ݸ���
   */
  public static final String SO_SALEORDER_B_CSRCBID = "so_saleorder_b.csrcbid";
  /**
   * ���۶�����ʵ��.Դͷ��������
   */
  public static final String SO_SALEORDER_B_VFIRSTTYPE = "so_saleorder_b.vfirsttype";
  /**
   * ���۶�����ʵ��.Դͷ��������
   */
  public static final String SO_SALEORDER_B_VFIRSTTRANTYPE = "so_saleorder_b.vfirsttrantype";
  /**
   * ���۶�����ʵ��.Դͷ���ݺ�
   */
  public static final String SO_SALEORDER_B_VFIRSTCODE = "so_saleorder_b.vfirstcode";
  /**
   * ���۶�����ʵ��.Դͷ��������
   */
  public static final String SO_SALEORDER_B_CFIRSTID = "so_saleorder_b.cfirstid";
  /**
   * ���۶�����ʵ��.Դͷ�����ӱ�
   */
  public static final String SO_SALEORDER_B_CFIRSTBID = "so_saleorder_b.cfirstbid";
  /**
   * ���۶�����ʵ��.Դͷ�����к�
   */
  public static final String SO_SALEORDER_B_VFIRSTROWNO = "so_saleorder_b.vfirstrowno";
  /**
   * ���۶�����ʵ��.�˻�ԭ��
   */
  public static final String SO_SALEORDER_B_CRETREASONID = "so_saleorder_b.cretreasonid";
  /**
   * ���۶�����ʵ��.�˻�����
   */
  public static final String SO_SALEORDER_B_CRETPOLICYID = "so_saleorder_b.cretpolicyid";
  /**
   * ���۶�����ʵ��.�˻����δ���ʽ
   */
  public static final String SO_SALEORDER_B_VRETURNMODE = "so_saleorder_b.vreturnmode";
  /**
   * ���۶�����ʵ��.�˻������
   */
  public static final String SO_SALEORDER_B_FRETEXCHANGE = "so_saleorder_b.fretexchange";
  /**
   * ���۶�����ʵ��.�����ж�Ӧ�˻���
   */
  public static final String SO_SALEORDER_B_CEXCHANGESRCRETID = "so_saleorder_b.cexchangesrcretid";
  /**
   * ���۶�����ʵ��.��Ʒ�ж�Ӧ��Դ������ID
   */
  public static final String SO_SALEORDER_B_CLARGESSSRCID = "so_saleorder_b.clargesssrcid";
  /**
   * ���۶�����ʵ��.�������Ӧ��Դ������ID
   */
  public static final String SO_SALEORDER_B_CBINDSRCID = "so_saleorder_b.cbindsrcid";
  /**
   * ���۶�����ʵ��.��Ʒ�۸��̯��ʽ
   */
  public static final String SO_SALEORDER_B_FLARGESSTYPEFLAG = "so_saleorder_b.flargesstypeflag";
  /**
   * ���۶�����ʵ��.��Ʒ�۸��̯ǰ��˰���
   */
  public static final String SO_SALEORDER_B_NLARGESSMNY = "so_saleorder_b.nlargessmny";
  /**
   * ���۶�����ʵ��.��Ʒ�۸��̯ǰ��˰�ϼ�
   */
  public static final String SO_SALEORDER_B_NLARGESSTAXMNY = "so_saleorder_b.nlargesstaxmny";
  /**
   * ���۶�����ʵ��.Ԥ�����йر�
   */
  public static final String SO_SALEORDER_B_BPREROWCLOSEFLAG = "so_saleorder_b.bprerowcloseflag";
  /**
   * ���۶�����ʵ��.���ɸ�������1
   */
  public static final String SO_SALEORDER_B_VFREE1 = "so_saleorder_b.vfree1";
  /**
   * ���۶�����ʵ��.���ɸ�������2
   */
  public static final String SO_SALEORDER_B_VFREE2 = "so_saleorder_b.vfree2";
  /**
   * ���۶�����ʵ��.���ɸ�������3
   */
  public static final String SO_SALEORDER_B_VFREE3 = "so_saleorder_b.vfree3";
  /**
   * ���۶�����ʵ��.���ɸ�������4
   */
  public static final String SO_SALEORDER_B_VFREE4 = "so_saleorder_b.vfree4";
  /**
   * ���۶�����ʵ��.���ɸ�������5
   */
  public static final String SO_SALEORDER_B_VFREE5 = "so_saleorder_b.vfree5";
  /**
   * ���۶�����ʵ��.���ɸ�������6
   */
  public static final String SO_SALEORDER_B_VFREE6 = "so_saleorder_b.vfree6";
  /**
   * ���۶�����ʵ��.���ɸ�������7
   */
  public static final String SO_SALEORDER_B_VFREE7 = "so_saleorder_b.vfree7";
  /**
   * ���۶�����ʵ��.���ɸ�������8
   */
  public static final String SO_SALEORDER_B_VFREE8 = "so_saleorder_b.vfree8";
  /**
   * ���۶�����ʵ��.���ɸ�������9
   */
  public static final String SO_SALEORDER_B_VFREE9 = "so_saleorder_b.vfree9";
  /**
   * ���۶�����ʵ��.���ɸ�������10
   */
  public static final String SO_SALEORDER_B_VFREE10 = "so_saleorder_b.vfree10";
  /**
   * ���۶�����ʵ��.�Զ�����1
   */
  public static final String SO_SALEORDER_B_VBDEF1 = "so_saleorder_b.vbdef1";
  /**
   * ���۶�����ʵ��.�Զ�����2
   */
  public static final String SO_SALEORDER_B_VBDEF2 = "so_saleorder_b.vbdef2";
  /**
   * ���۶�����ʵ��.�Զ�����3
   */
  public static final String SO_SALEORDER_B_VBDEF3 = "so_saleorder_b.vbdef3";
  /**
   * ���۶�����ʵ��.�Զ�����4
   */
  public static final String SO_SALEORDER_B_VBDEF4 = "so_saleorder_b.vbdef4";
  /**
   * ���۶�����ʵ��.�Զ�����5
   */
  public static final String SO_SALEORDER_B_VBDEF5 = "so_saleorder_b.vbdef5";
  /**
   * ���۶�����ʵ��.�Զ�����6
   */
  public static final String SO_SALEORDER_B_VBDEF6 = "so_saleorder_b.vbdef6";
  /**
   * ���۶�����ʵ��.�Զ�����7
   */
  public static final String SO_SALEORDER_B_VBDEF7 = "so_saleorder_b.vbdef7";
  /**
   * ���۶�����ʵ��.�Զ�����8
   */
  public static final String SO_SALEORDER_B_VBDEF8 = "so_saleorder_b.vbdef8";
  /**
   * ���۶�����ʵ��.�Զ�����9
   */
  public static final String SO_SALEORDER_B_VBDEF9 = "so_saleorder_b.vbdef9";
  /**
   * ���۶�����ʵ��.�Զ�����10
   */
  public static final String SO_SALEORDER_B_VBDEF10 = "so_saleorder_b.vbdef10";
  /**
   * ���۶�����ʵ��.�Զ�����11
   */
  public static final String SO_SALEORDER_B_VBDEF11 = "so_saleorder_b.vbdef11";
  /**
   * ���۶�����ʵ��.�Զ�����12
   */
  public static final String SO_SALEORDER_B_VBDEF12 = "so_saleorder_b.vbdef12";
  /**
   * ���۶�����ʵ��.�Զ�����13
   */
  public static final String SO_SALEORDER_B_VBDEF13 = "so_saleorder_b.vbdef13";
  /**
   * ���۶�����ʵ��.�Զ�����14
   */
  public static final String SO_SALEORDER_B_VBDEF14 = "so_saleorder_b.vbdef14";
  /**
   * ���۶�����ʵ��.�Զ�����15
   */
  public static final String SO_SALEORDER_B_VBDEF15 = "so_saleorder_b.vbdef15";
  /**
   * ���۶�����ʵ��.�Զ�����16
   */
  public static final String SO_SALEORDER_B_VBDEF16 = "so_saleorder_b.vbdef16";
  /**
   * ���۶�����ʵ��.�Զ�����17
   */
  public static final String SO_SALEORDER_B_VBDEF17 = "so_saleorder_b.vbdef17";
  /**
   * ���۶�����ʵ��.�Զ�����18
   */
  public static final String SO_SALEORDER_B_VBDEF18 = "so_saleorder_b.vbdef18";
  /**
   * ���۶�����ʵ��.�Զ�����19
   */
  public static final String SO_SALEORDER_B_VBDEF19 = "so_saleorder_b.vbdef19";
  /**
   * ���۶�����ʵ��.�Զ�����20
   */
  public static final String SO_SALEORDER_B_VBDEF20 = "so_saleorder_b.vbdef20";
  /**
   * ���۶�����ʵ��.�����ر�
   */
  public static final String SO_SALEORDER_B_BBSENDENDFLAG = "so_saleorder_b.bbsendendflag";
  /**
   * ���۶�����ʵ��.��Ʊ�ر�
   */
  public static final String SO_SALEORDER_B_BBINVOICENDFLAG = "so_saleorder_b.bbinvoicendflag";
  /**
   * ���۶�����ʵ��.����ر�
   */
  public static final String SO_SALEORDER_B_BBOUTENDFLAG = "so_saleorder_b.bboutendflag";
  /**
   * ���۶�����ʵ��.�ɱ�����ر�
   */
  public static final String SO_SALEORDER_B_BBCOSTSETTLEFLAG = "so_saleorder_b.bbcostsettleflag";
  /**
   * ���۶�����ʵ��.�������ر�
   */
  public static final String SO_SALEORDER_B_BBARSETTLEFLAG = "so_saleorder_b.bbarsettleflag";
  /**
   * ���۶�����ʵ��.�ر�/��ԭ��
   */
  public static final String SO_SALEORDER_B_VCLOSEREASON = "so_saleorder_b.vclosereason";
  /**
   * ���۶�����ʵ��.��������������
   */
  public static final String SO_SALEORDER_B_NSENDAUDITNUM = "so_saleorder_b.nsendauditnum";
  /**
   * ���۶�����ʵ��.��������������
   */
  public static final String SO_SALEORDER_B_NOUTAUDITNUM = "so_saleorder_b.noutauditnum";
  /**
   * ���۶�����ʵ��.��Ʊ����������
   */
  public static final String SO_SALEORDER_B_NINVOICEAUDITNUM = "so_saleorder_b.ninvoiceauditnum";
  /**
   * ���۶�����ʵ��.����δǩ��������
   */
  public static final String SO_SALEORDER_B_NOUTNOTAUDITNUM = "so_saleorder_b.noutnotauditnum";
  /**
   * ���۶�����ʵ��.;��δ���������
   */
  public static final String SO_SALEORDER_B_NLOSSNOTAUDITNUM = "so_saleorder_b.nlossnotauditnum";
  /**
   * ���۶�����ʵ��.�ۼƷ���������
   */
  public static final String SO_SALEORDER_B_NTOTALSENDNUM = "so_saleorder_b.ntotalsendnum";
  /**
   * ���۶�����ʵ��.�ۼƿ�Ʊ������
   */
  public static final String SO_SALEORDER_B_NTOTALINVOICENUM = "so_saleorder_b.ntotalinvoicenum";
  /**
   * ���۶�����ʵ��.�ۼƳ���������
   */
  public static final String SO_SALEORDER_B_NTOTALOUTNUM = "so_saleorder_b.ntotaloutnum";
  /**
   * ���۶�����ʵ��.�ۼ�Ӧ��δ����������
   */
  public static final String SO_SALEORDER_B_NTOTALNOTOUTNUM = "so_saleorder_b.ntotalnotoutnum";
  /**
   * ���۶�����ʵ��.�ۼ�ǩ��������
   */
  public static final String SO_SALEORDER_B_NTOTALSIGNNUM = "so_saleorder_b.ntotalsignnum";
  /**
   * ���۶�����ʵ��.�ۼ�;��������
   */
  public static final String SO_SALEORDER_B_NTRANSLOSSNUM = "so_saleorder_b.ntranslossnum";
  /**
   * ���۶�����ʵ��.�ۼƳ���Գ�������
   */
  public static final String SO_SALEORDER_B_NTOTALRUSHNUM = "so_saleorder_b.ntotalrushnum";
  /**
   * ���۶�����ʵ��.�ۼ��ݹ�Ӧ��������
   */
  public static final String SO_SALEORDER_B_NTOTALESTARNUM = "so_saleorder_b.ntotalestarnum";
  /**
   * ���۶�����ʵ��.�ۼ�ȷ��Ӧ��������
   */
  public static final String SO_SALEORDER_B_NTOTALARNUM = "so_saleorder_b.ntotalarnum";
  /**
   * ���۶�����ʵ��.�ۼƳɱ�����������
   */
  public static final String SO_SALEORDER_B_NTOTALCOSTNUM = "so_saleorder_b.ntotalcostnum";
  /**
   * ���۶�����ʵ��.�ۼ��ݹ�Ӧ�ս��
   */
  public static final String SO_SALEORDER_B_NTOTALESTARMNY = "so_saleorder_b.ntotalestarmny";
  /**
   * ���۶�����ʵ��.�ۼ�ȷ��Ӧ�ս��
   */
  public static final String SO_SALEORDER_B_NTOTALARMNY = "so_saleorder_b.ntotalarmny";
  /**
   * ���۶�����ʵ��.�ۼƲ���������
   */
  public static final String SO_SALEORDER_B_NTOTALPAYMNY = "so_saleorder_b.ntotalpaymny";
  /**
   * ���۶�����ʵ��.�ۼƳ�ֽ��
   */
  public static final String SO_SALEORDER_B_NORIGSUBMNY = "so_saleorder_b.norigsubmny";
  /**
   * ���۶�����ʵ��.�ۼư���ί�ⶩ��������
   */
  public static final String SO_SALEORDER_B_NARRANGESCORNUM = "so_saleorder_b.narrangescornum";
  /**
   * ���۶�����ʵ��.�ۼư����빺��������
   */
  public static final String SO_SALEORDER_B_NARRANGEPOAPPNUM = "so_saleorder_b.narrangepoappnum";
  /**
   * ���۶�����ʵ��.�ۼư��ŵ�������������
   */
  public static final String SO_SALEORDER_B_NARRANGETOORNUM = "so_saleorder_b.narrangetoornum";
  /**
   * ���۶�����ʵ��.�ۼư��ŵ�������������
   */
  public static final String SO_SALEORDER_B_NARRANGETOAPPNUM = "so_saleorder_b.narrangetoappnum";
  /**
   * ���۶�����ʵ��.�ۼư�����������������
   */
  public static final String SO_SALEORDER_B_NARRANGEMONUM = "so_saleorder_b.narrangemonum";
  /**
   * ���۶�����ʵ��.�ۼư��Ųɹ�����������
   */
  public static final String SO_SALEORDER_B_NARRANGEPONUM = "so_saleorder_b.narrangeponum";
  /**
   * ���۶�����ʵ��.�ۼ����ɼƻ�����������
   */
  public static final String SO_SALEORDER_B_NTOTALPLONUM = "so_saleorder_b.ntotalplonum";
  /**
   * ���۶�����ʵ��.Ԥ��������
   */
  public static final String SO_SALEORDER_B_NREQRSNUM = "so_saleorder_b.nreqrsnum";
  /**
   * ���۶�����ʵ��.�ۼ��˻�������
   */
  public static final String SO_SALEORDER_B_NTOTALRETURNNUM = "so_saleorder_b.ntotalreturnnum";
  /**
   * ���۶�����ʵ��.�ۼƷ�����Ʒ������
   */
  public static final String SO_SALEORDER_B_NTOTALTRADENUM = "so_saleorder_b.ntotaltradenum";
  /**
   * ���۶�����ʵ��.�Ƿ��Դ�������
   */
  public static final String SO_SALEORDER_B_BARRANGEDFLAG = "so_saleorder_b.barrangedflag";
  /**
   * ���۶�����ʵ��.����Դ������
   */
  public static final String SO_SALEORDER_B_CARRANGEPERSONID = "so_saleorder_b.carrangepersonid";
  /**
   * ���۶�����ʵ��.����Դ����ʱ��
   */
  public static final String SO_SALEORDER_B_TLASTARRANGETIME = "so_saleorder_b.tlastarrangetime";
  /**
   * ���۶�����ʵ��.����δ�����
   */
  public static final String SO_SALEORDER_B_NSENDUNFINISEDNUM = "so_saleorder_b.nsendunfinisednum";
  /**
   * ���۶�����ʵ��.����δ�����
   */
  public static final String SO_SALEORDER_B_NOUTUNFINISEDNUM = "so_saleorder_b.noutunfinisednum";
  /**
   * ���۶�����ʵ��.��Ʊδ�����
   */
  public static final String SO_SALEORDER_B_NINVUNFINISEDNUM = "so_saleorder_b.ninvunfinisednum";
  /**
   * ���۶�����ʵ��.δ��ȷ��Ӧ��������
   */
  public static final String SO_SALEORDER_B_NNOTARNUM = "so_saleorder_b.nnotarnum";
  /**
   * ���۶�����ʵ��.δ���������������
   */
  public static final String SO_SALEORDER_B_NNOTCOSTNUM = "so_saleorder_b.nnotcostnum";
  /**
   * ���۶�����ʵ��.����ر�
   */
  public static final String SO_SALEORDER_B_BBSETTLEENDFLAG = "so_saleorder_b.bbsettleendflag";
  /**
   * ���۶�����ʵ��.��Դ���ݱ�ͷʱ���
   */
  public static final String SO_SALEORDER_B_SRCTS = "so_saleorder_b.srcts";
  /**
   * ���۶�����ʵ��.��Դ���ݱ���ʱ���
   */
  public static final String SO_SALEORDER_B_SRCBTS = "so_saleorder_b.srcbts";
  /**
   * ���۶�����ʵ��.��Դ��֯
   */
  public static final String SO_SALEORDER_B_SRCORGID = "so_saleorder_b.srcorgid";
  /**
   * ���۶�����ʵ��.������������
   */
  public static final String SO_SALEORDER_B_CBUYPROMOTTYPEID = "so_saleorder_b.cbuypromottypeid";
  /**
   * ���۶�����ʵ��.ѯ�۴�������
   */
  public static final String SO_SALEORDER_B_CPRCPROMOTTYPEID = "so_saleorder_b.cprcpromottypeid";
  /**
   * ���۶�����ʵ��.�ͻ�������
   */
  public static final String SO_SALEORDER_B_VCUSTOMBILLCODE = "so_saleorder_b.vcustombillcode";
  /**
   * ���۶�����ʵ��.�����
   */
  public static final String SO_SALEORDER_B_CBUYLARGESSACTID = "so_saleorder_b.cbuylargessactid";
  /**
   * ���۶�����ʵ��.�۸�����
   */
  public static final String SO_SALEORDER_B_CPRICEPROMTACTID = "so_saleorder_b.cpricepromtactid";
  /**
   * ���۶�����ʵ��.��������
   */
  public static final String SO_SALEORDER_B_CBUYLARGESSID = "so_saleorder_b.cbuylargessid";
  /**
   * ���۶�����ʵ��.�ۼư��Ž��ں�ͬ������
   */
  public static final String SO_SALEORDER_B_NARRANGEITCNUM = "so_saleorder_b.narrangeitcnum";
  /**
   * ���۶�����ʵ��.������������
   */
  public static final String SO_SALEORDER_B_CSPROFITCENTERVID = "so_saleorder_b.csprofitcentervid";
  /**
   * ���۶�����ʵ��.���������������°汾
   */
  public static final String SO_SALEORDER_B_CSPROFITCENTERID = "so_saleorder_b.csprofitcenterid";
  /**
   * ���۶�����ʵ��.��Ʒ�Ҹ�
   */
  public static final String SO_SALEORDER_B_BLRGCASHFLAG = "so_saleorder_b.blrgcashflag";
  /**
   * ���۶�����ʵ��.�����˵���
   */
  public static final String SO_SALEORDER_B_NACCPRICE = "so_saleorder_b.naccprice";
  /**
   * ���۶�����ʵ��.�����۸����
   */
  public static final String SO_SALEORDER_B_CPROMOTPRICEID = "so_saleorder_b.cpromotpriceid";
  /**
   * ���۶�����ʵ��.������
   */
  public static final String SO_SALEORDER_B_CMFFILEID = "so_saleorder_b.cmffileid";
  /**
   * ���۶�����ʵ��.������
   */
  public static final String SO_SALEORDER_B_NMFFILEPRICE = "so_saleorder_b.nmffileprice";
  /**
   * ���۶�����ʵ��.vostatus
   */
  public static final String SO_SALEORDER_B_STATUS = "so_saleorder_b.status";
  /**
   * ���۶�����ʵ��.dr
   */
  public static final String SO_SALEORDER_B_DR = "so_saleorder_b.dr";
  /**
   * ���۶�����ʵ��.ts
   */
  public static final String SO_SALEORDER_B_TS = "so_saleorder_b.ts";
}


