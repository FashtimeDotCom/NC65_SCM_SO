package nc.pubitf.so.m4331.api;

import java.io.Serializable;

/**
 * @description
 * ��������ѯAPI�������쳣����
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 ����10:57:20
 * @author ����
 */
public interface IDeliveryVO extends Serializable {
  
  /**
   * ��������ʵ��
   */
  public static final String CDELIVERYID = "cdeliveryid";
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
   * ���ݺ�
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * ҵ������
   */
  public static final String CBIZTYPEID = "cbiztypeid";
  /**
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * �������ͱ���
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * ��������
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * �����ƻ�Ա
   */
  public static final String CSENDEMPLOYEEID = "csendemployeeid";
  /**
   * �����������°汾
   */
  public static final String CSENDDEPTID = "csenddeptid";
  /**
   * ��������
   */
  public static final String CSENDDEPTVID = "csenddeptvid";
  /**
   * ���䷽ʽ
   */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";
  /**
   * ó������
   */
  public static final String CTRADEWORDID = "ctradewordid";
  /**
   * ����·��
   */
  public static final String CTRANSPORTROUTEID = "ctransportrouteid";
  /**
   * ������
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * ������
   */
  public static final String NTOTALWEIGHT = "ntotalweight";
  /**
   * �����
   */
  public static final String NTOTALVOLUME = "ntotalvolume";
  /**
   * �ܼ���
   */
  public static final String NTOTALPIECE = "ntotalpiece";
  /**
   * ����״̬
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * ��ע
   */
  public static final String VNOTE = "vnote";
  /**
   * ������
   */
  public static final String CREATOR = "creator";
  /**
   * �Ƶ���
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * ����ʱ��
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * ������
   */
  public static final String APPROVER = "approver";
  /**
   * �������
   */
  public static final String TAUDITTIME = "taudittime";
  /**
   * ����޸���
   */
  public static final String MODIFIER = "modifier";
  /**
   * ����޸�ʱ��
   */
  public static final String MODIFIEDTIME = "modifiedtime";
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
   * �Ƶ�����
   */
  public static final String DMAKEDATE = "dmakedate";
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
   * ����������vo.��������ʵ��
   */
  public static final String CDELIVERYBID_CDELIVERYBID = "cdeliverybid.cdeliverybid";
  /**
   * ����������vo.������֯
   */
  public static final String CDELIVERYBID_PK_ORG = "cdeliverybid.pk_org";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_DBILLDATE = "cdeliverybid.dbilldate";
  /**
   * ����������vo.�к�
   */
  public static final String CDELIVERYBID_CROWNO = "cdeliverybid.crowno";
  /**
   * ����������vo.�ͻ�������
   */
  public static final String CDELIVERYBID_CCUSTMATERIALID = "cdeliverybid.ccustmaterialid";
  /**
   * ����������vo.�ͻ�����
   */
  public static final String CDELIVERYBID_CORDERCUSTID = "cdeliverybid.cordercustid";
  /**
   * ����������vo.ɢ��
   */
  public static final String CDELIVERYBID_CFREECUSTID = "cdeliverybid.cfreecustid";
  /**
   * ����������vo.��Ʊ�ͻ�
   */
  public static final String CDELIVERYBID_CINVOICECUSTID = "cdeliverybid.cinvoicecustid";
  /**
   * ����������vo.���ϵ���
   */
  public static final String CDELIVERYBID_CMATERIALID = "cdeliverybid.cmaterialid";
  /**
   * ����������vo.���ϱ���
   */
  public static final String CDELIVERYBID_CMATERIALVID = "cdeliverybid.cmaterialvid";
  /**
   * ����������vo.��Ӧ��
   */
  public static final String CDELIVERYBID_CVENDORID = "cdeliverybid.cvendorid";
  /**
   * ����������vo.��Ŀ
   */
  public static final String CDELIVERYBID_CPROJECTID = "cdeliverybid.cprojectid";
  /**
   * ����������vo.�����ȼ�
   */
  public static final String CDELIVERYBID_CQUALITYLEVELID = "cdeliverybid.cqualitylevelid";
  /**
   * ����������vo.ԭ����
   */
  public static final String CDELIVERYBID_CORIGCOUNTRYID = "cdeliverybid.corigcountryid";
  /**
   * ����������vo.ԭ������
   */
  public static final String CDELIVERYBID_CORIGAREAID = "cdeliverybid.corigareaid";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_CPRODUCTORID = "cdeliverybid.cproductorid";
  /**
   * ����������vo.��λ
   */
  public static final String CDELIVERYBID_CASTUNITID = "cdeliverybid.castunitid";
  /**
   * ����������vo.����
   */
  public static final String CDELIVERYBID_NASTNUM = "cdeliverybid.nastnum";
  /**
   * ����������vo.����λ
   */
  public static final String CDELIVERYBID_CUNITID = "cdeliverybid.cunitid";
  /**
   * ����������vo.������
   */
  public static final String CDELIVERYBID_NNUM = "cdeliverybid.nnum";
  /**
   * ����������vo.������
   */
  public static final String CDELIVERYBID_VCHANGERATE = "cdeliverybid.vchangerate";
  /**
   * ����������vo.���۵�λ
   */
  public static final String CDELIVERYBID_CQTUNITID = "cdeliverybid.cqtunitid";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_NQTUNITNUM = "cdeliverybid.nqtunitnum";
  /**
   * ����������vo.���ۻ�����
   */
  public static final String CDELIVERYBID_VQTUNITRATE = "cdeliverybid.vqtunitrate";
  /**
   * ����������vo.�Ƿ��ѱ���
   */
  public static final String CDELIVERYBID_BCHECKFLAG = "cdeliverybid.bcheckflag";
  /**
   * ����������vo.�Ƿ�����ʼ������
   */
  public static final String CDELIVERYBID_BUSECHECKFLAG = "cdeliverybid.busecheckflag";
  /**
   * ����������vo.�ۼƱ�������
   */
  public static final String CDELIVERYBID_NTOTALREPORTNUM = "cdeliverybid.ntotalreportnum";
  /**
   * ����������vo.�ۼƺϸ�����
   */
  public static final String CDELIVERYBID_NTOTALELIGNUM = "cdeliverybid.ntotalelignum";
  /**
   * ����������vo.�ۼƲ��ϸ�����
   */
  public static final String CDELIVERYBID_NTOTALUNELIGNUM = "cdeliverybid.ntotalunelignum";
  /**
   * ����������vo.����
   */
  public static final String CDELIVERYBID_NWEIGHT = "cdeliverybid.nweight";
  /**
   * ����������vo.���
   */
  public static final String CDELIVERYBID_NVOLUME = "cdeliverybid.nvolume";
  /**
   * ����������vo.����
   */
  public static final String CDELIVERYBID_NPIECE = "cdeliverybid.npiece";
  /**
   * ����������vo.��Ʒ
   */
  public static final String CDELIVERYBID_BLARGESSFLAG = "cdeliverybid.blargessflag";
  /**
   * ����������vo.���ε���
   */
  public static final String CDELIVERYBID_PK_BATCHCODE = "cdeliverybid.pk_batchcode";
  /**
   * ����������vo.���κ�
   */
  public static final String CDELIVERYBID_VBATCHCODE = "cdeliverybid.vbatchcode";
  /**
   * ����������vo.ԭ��
   */
  public static final String CDELIVERYBID_CORIGCURRENCYID = "cdeliverybid.corigcurrencyid";
  /**
   * ����������vo.�۱�����
   */
  public static final String CDELIVERYBID_NEXCHANGERATE = "cdeliverybid.nexchangerate";
  /**
   * ����������vo.��λ��
   */
  public static final String CDELIVERYBID_CCURRENCYID = "cdeliverybid.ccurrencyid";
  /**
   * ����������vo.˰��
   */
  public static final String CDELIVERYBID_CTAXCODEID = "cdeliverybid.ctaxcodeid";
  /**
   * ����������vo.˰��
   */
  public static final String CDELIVERYBID_NTAXRATE = "cdeliverybid.ntaxrate";
  /**
   * ����������vo.��˰���
   */
  public static final String CDELIVERYBID_NCALTAXMNY = "cdeliverybid.ncaltaxmny";
  /**
   * ����������vo.��˰���
   */
  public static final String CDELIVERYBID_FTAXTYPEFLAG = "cdeliverybid.ftaxtypeflag";
  /**
   * ����������vo.�����ۿ�
   */
  public static final String CDELIVERYBID_NDISCOUNTRATE = "cdeliverybid.ndiscountrate";
  /**
   * ����������vo.��Ʒ�ۿ�
   */
  public static final String CDELIVERYBID_NITEMDISCOUNTRATE = "cdeliverybid.nitemdiscountrate";
  /**
   * ����������vo.����˰����
   */
  public static final String CDELIVERYBID_NORIGTAXPRICE = "cdeliverybid.norigtaxprice";
  /**
   * ����������vo.����˰����
   */
  public static final String CDELIVERYBID_NORIGPRICE = "cdeliverybid.norigprice";
  /**
   * ����������vo.����˰����
   */
  public static final String CDELIVERYBID_NORIGTAXNETPRICE = "cdeliverybid.norigtaxnetprice";
  /**
   * ����������vo.����˰����
   */
  public static final String CDELIVERYBID_NORIGNETPRICE = "cdeliverybid.norignetprice";
  /**
   * ����������vo.��˰����
   */
  public static final String CDELIVERYBID_NQTORIGTAXPRICE = "cdeliverybid.nqtorigtaxprice";
  /**
   * ����������vo.��˰����
   */
  public static final String CDELIVERYBID_NQTORIGPRICE = "cdeliverybid.nqtorigprice";
  /**
   * ����������vo.��˰����
   */
  public static final String CDELIVERYBID_NQTORIGTAXNETPRC = "cdeliverybid.nqtorigtaxnetprc";
  /**
   * ����������vo.��˰����
   */
  public static final String CDELIVERYBID_NQTORIGNETPRICE = "cdeliverybid.nqtorignetprice";
  /**
   * ����������vo.��˰���
   */
  public static final String CDELIVERYBID_NORIGMNY = "cdeliverybid.norigmny";
  /**
   * ����������vo.��˰�ϼ�
   */
  public static final String CDELIVERYBID_NORIGTAXMNY = "cdeliverybid.norigtaxmny";
  /**
   * ����������vo.�ۿ۶�
   */
  public static final String CDELIVERYBID_NORIGDISCOUNT = "cdeliverybid.norigdiscount";
  /**
   * ����������vo.��������˰����
   */
  public static final String CDELIVERYBID_NTAXPRICE = "cdeliverybid.ntaxprice";
  /**
   * ����������vo.��������˰����
   */
  public static final String CDELIVERYBID_NPRICE = "cdeliverybid.nprice";
  /**
   * ����������vo.��������˰����
   */
  public static final String CDELIVERYBID_NTAXNETPRICE = "cdeliverybid.ntaxnetprice";
  /**
   * ����������vo.��������˰����
   */
  public static final String CDELIVERYBID_NNETPRICE = "cdeliverybid.nnetprice";
  /**
   * ����������vo.���Һ�˰����
   */
  public static final String CDELIVERYBID_NQTTAXPRICE = "cdeliverybid.nqttaxprice";
  /**
   * ����������vo.������˰����
   */
  public static final String CDELIVERYBID_NQTPRICE = "cdeliverybid.nqtprice";
  /**
   * ����������vo.���Һ�˰����
   */
  public static final String CDELIVERYBID_NQTTAXNETPRICE = "cdeliverybid.nqttaxnetprice";
  /**
   * ����������vo.������˰����
   */
  public static final String CDELIVERYBID_NQTNETPRICE = "cdeliverybid.nqtnetprice";
  /**
   * ����������vo.˰��
   */
  public static final String CDELIVERYBID_NTAX = "cdeliverybid.ntax";
  /**
   * ����������vo.������˰���
   */
  public static final String CDELIVERYBID_NMNY = "cdeliverybid.nmny";
  /**
   * ����������vo.���Ҽ�˰�ϼ�
   */
  public static final String CDELIVERYBID_NTAXMNY = "cdeliverybid.ntaxmny";
  /**
   * ����������vo.�����ۿ۶�
   */
  public static final String CDELIVERYBID_NDISCOUNT = "cdeliverybid.ndiscount";
  /**
   * ����������vo.Դͷ��������
   */
  public static final String CDELIVERYBID_VFIRSTTYPE = "cdeliverybid.vfirsttype";
  /**
   * ����������vo.Դͷ���ݺ�
   */
  public static final String CDELIVERYBID_VFIRSTCODE = "cdeliverybid.vfirstcode";
  /**
   * ����������vo.Դͷ��������
   */
  public static final String CDELIVERYBID_VFIRSTTRANTYPE = "cdeliverybid.vfirsttrantype";
  /**
   * ����������vo.Դͷ�����к�
   */
  public static final String CDELIVERYBID_VFIRSTROWNO = "cdeliverybid.vfirstrowno";
  /**
   * ����������vo.Դͷ���ݱ�ͷID
   */
  public static final String CDELIVERYBID_CFIRSTID = "cdeliverybid.cfirstid";
  /**
   * ����������vo.Դͷ���ݱ���ID
   */
  public static final String CDELIVERYBID_CFIRSTBID = "cdeliverybid.cfirstbid";
  /**
   * ����������vo.��Դ��������
   */
  public static final String CDELIVERYBID_VSRCTYPE = "cdeliverybid.vsrctype";
  /**
   * ����������vo.��Դ���ݺ�
   */
  public static final String CDELIVERYBID_VSRCCODE = "cdeliverybid.vsrccode";
  /**
   * ����������vo.��Դ��������
   */
  public static final String CDELIVERYBID_VSRCTRANTYPE = "cdeliverybid.vsrctrantype";
  /**
   * ����������vo.��Դ�����к�
   */
  public static final String CDELIVERYBID_VSRCROWNO = "cdeliverybid.vsrcrowno";
  /**
   * ����������vo.��Դ���ݱ�ͷID
   */
  public static final String CDELIVERYBID_CSRCID = "cdeliverybid.csrcid";
  /**
   * ����������vo.��Դ���ݱ���ID
   */
  public static final String CDELIVERYBID_CSRCBID = "cdeliverybid.csrcbid";
  /**
   * ����������vo.������֯
   */
  public static final String CDELIVERYBID_CSALEORGID = "cdeliverybid.csaleorgid";
  /**
   * ����������vo.������֯
   */
  public static final String CDELIVERYBID_CSALEORGVID = "cdeliverybid.csaleorgvid";
  /**
   * ����������vo.���������֯
   */
  public static final String CDELIVERYBID_CSENDSTOCKORGID = "cdeliverybid.csendstockorgid";
  /**
   * ����������vo.���������֯
   */
  public static final String CDELIVERYBID_CSENDSTOCKORGVID = "cdeliverybid.csendstockorgvid";
  /**
   * ����������vo.�����ֿ�
   */
  public static final String CDELIVERYBID_CSENDSTORDOCID = "cdeliverybid.csendstordocid";
  /**
   * ����������vo.�ջ��ͻ�
   */
  public static final String CDELIVERYBID_CRECEIVECUSTID = "cdeliverybid.creceivecustid";
  /**
   * ����������vo.�ջ�����
   */
  public static final String CDELIVERYBID_CRECEIVEAREAID = "cdeliverybid.creceiveareaid";
  /**
   * ����������vo.�ջ���ַ
   */
  public static final String CDELIVERYBID_CRECEIVEADDRID = "cdeliverybid.creceiveaddrid";
  /**
   * ����������vo.�ջ������֯
   */
  public static final String CDELIVERYBID_CINSTOCKORGID = "cdeliverybid.cinstockorgid";
  /**
   * ����������vo.�ջ������֯
   */
  public static final String CDELIVERYBID_CINSTOCKORGVID = "cdeliverybid.cinstockorgvid";
  /**
   * ����������vo.�ջ��ֿ�
   */
  public static final String CDELIVERYBID_CINSTORDOCID = "cdeliverybid.cinstordocid";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_DSENDDATE = "cdeliverybid.dsenddate";
  /**
   * ����������vo.Ҫ���ջ�����
   */
  public static final String CDELIVERYBID_DRECEIVEDATE = "cdeliverybid.dreceivedate";
  /**
   * ����������vo.Ѻ��Ա
   */
  public static final String CDELIVERYBID_CSUPERCARGOID = "cdeliverybid.csupercargoid";
  /**
   * ����������vo.������
   */
  public static final String CDELIVERYBID_CTRANSCUSTID = "cdeliverybid.ctranscustid";
  /**
   * ����������vo.����
   */
  public static final String CDELIVERYBID_CVEHICLETYPEID = "cdeliverybid.cvehicletypeid";
  /**
   * ����������vo.����
   */
  public static final String CDELIVERYBID_CVEHICLEID = "cdeliverybid.cvehicleid";
  /**
   * ����������vo.˾��
   */
  public static final String CDELIVERYBID_CCHAUFFEURID = "cdeliverybid.cchauffeurid";
  /**
   * ����������vo.��λ
   */
  public static final String CDELIVERYBID_CSPACEID = "cdeliverybid.cspaceid";
  /**
   * ����������vo.��Ʒ��
   */
  public static final String CDELIVERYBID_CPRODLINEID = "cdeliverybid.cprodlineid";
  /**
   * ����������vo.�ۼ���������
   */
  public static final String CDELIVERYBID_NTOTALTRANSNUM = "cdeliverybid.ntotaltransnum";
  /**
   * ����������vo.����ر�
   */
  public static final String CDELIVERYBID_BTRANSENDFLAG = "cdeliverybid.btransendflag";
  /**
   * ����������vo.�ۼƳ�������
   */
  public static final String CDELIVERYBID_NTOTALOUTNUM = "cdeliverybid.ntotaloutnum";
  /**
   * ����������vo.����ر�
   */
  public static final String CDELIVERYBID_BOUTENDFLAG = "cdeliverybid.boutendflag";
  /**
   * ����������vo.��ע
   */
  public static final String CDELIVERYBID_FROWNOTE = "cdeliverybid.frownote";
  /**
   * ����������vo.���ɸ�������1
   */
  public static final String CDELIVERYBID_VFREE1 = "cdeliverybid.vfree1";
  /**
   * ����������vo.���ɸ�������2
   */
  public static final String CDELIVERYBID_VFREE2 = "cdeliverybid.vfree2";
  /**
   * ����������vo.���ɸ�������3
   */
  public static final String CDELIVERYBID_VFREE3 = "cdeliverybid.vfree3";
  /**
   * ����������vo.���ɸ�������4
   */
  public static final String CDELIVERYBID_VFREE4 = "cdeliverybid.vfree4";
  /**
   * ����������vo.���ɸ�������5
   */
  public static final String CDELIVERYBID_VFREE5 = "cdeliverybid.vfree5";
  /**
   * ����������vo.���ɸ�������6
   */
  public static final String CDELIVERYBID_VFREE6 = "cdeliverybid.vfree6";
  /**
   * ����������vo.���ɸ�������7
   */
  public static final String CDELIVERYBID_VFREE7 = "cdeliverybid.vfree7";
  /**
   * ����������vo.���ɸ�������8
   */
  public static final String CDELIVERYBID_VFREE8 = "cdeliverybid.vfree8";
  /**
   * ����������vo.���ɸ�������9
   */
  public static final String CDELIVERYBID_VFREE9 = "cdeliverybid.vfree9";
  /**
   * ����������vo.���ɸ�������10
   */
  public static final String CDELIVERYBID_VFREE10 = "cdeliverybid.vfree10";
  /**
   * ����������vo.�Զ�����1
   */
  public static final String CDELIVERYBID_VBDEF1 = "cdeliverybid.vbdef1";
  /**
   * ����������vo.�Զ�����2
   */
  public static final String CDELIVERYBID_VBDEF2 = "cdeliverybid.vbdef2";
  /**
   * ����������vo.�Զ�����3
   */
  public static final String CDELIVERYBID_VBDEF3 = "cdeliverybid.vbdef3";
  /**
   * ����������vo.�Զ�����4
   */
  public static final String CDELIVERYBID_VBDEF4 = "cdeliverybid.vbdef4";
  /**
   * ����������vo.�Զ�����5
   */
  public static final String CDELIVERYBID_VBDEF5 = "cdeliverybid.vbdef5";
  /**
   * ����������vo.�Զ�����6
   */
  public static final String CDELIVERYBID_VBDEF6 = "cdeliverybid.vbdef6";
  /**
   * ����������vo.�Զ�����7
   */
  public static final String CDELIVERYBID_VBDEF7 = "cdeliverybid.vbdef7";
  /**
   * ����������vo.�Զ�����8
   */
  public static final String CDELIVERYBID_VBDEF8 = "cdeliverybid.vbdef8";
  /**
   * ����������vo.�Զ�����9
   */
  public static final String CDELIVERYBID_VBDEF9 = "cdeliverybid.vbdef9";
  /**
   * ����������vo.�Զ�����10
   */
  public static final String CDELIVERYBID_VBDEF10 = "cdeliverybid.vbdef10";
  /**
   * ����������vo.�Զ�����11
   */
  public static final String CDELIVERYBID_VBDEF11 = "cdeliverybid.vbdef11";
  /**
   * ����������vo.�Զ�����12
   */
  public static final String CDELIVERYBID_VBDEF12 = "cdeliverybid.vbdef12";
  /**
   * ����������vo.�Զ�����13
   */
  public static final String CDELIVERYBID_VBDEF13 = "cdeliverybid.vbdef13";
  /**
   * ����������vo.�Զ�����14
   */
  public static final String CDELIVERYBID_VBDEF14 = "cdeliverybid.vbdef14";
  /**
   * ����������vo.�Զ�����15
   */
  public static final String CDELIVERYBID_VBDEF15 = "cdeliverybid.vbdef15";
  /**
   * ����������vo.�Զ�����16
   */
  public static final String CDELIVERYBID_VBDEF16 = "cdeliverybid.vbdef16";
  /**
   * ����������vo.�Զ�����17
   */
  public static final String CDELIVERYBID_VBDEF17 = "cdeliverybid.vbdef17";
  /**
   * ����������vo.�Զ�����18
   */
  public static final String CDELIVERYBID_VBDEF18 = "cdeliverybid.vbdef18";
  /**
   * ����������vo.�Զ�����19
   */
  public static final String CDELIVERYBID_VBDEF19 = "cdeliverybid.vbdef19";
  /**
   * ����������vo.�Զ�����20
   */
  public static final String CDELIVERYBID_VBDEF20 = "cdeliverybid.vbdef20";
  /**
   * ����������vo.������ϵ��
   */
  public static final String CDELIVERYBID_CSENDPERSONID = "cdeliverybid.csendpersonid";
  /**
   * ����������vo.������ϵ�绰
   */
  public static final String CDELIVERYBID_VSENDTEL = "cdeliverybid.vsendtel";
  /**
   * ����������vo.�ջ���ϵ��
   */
  public static final String CDELIVERYBID_CRECEIVEPERSONID = "cdeliverybid.creceivepersonid";
  /**
   * ����������vo.�ջ���ϵ�绰
   */
  public static final String CDELIVERYBID_VRECEIVETEL = "cdeliverybid.vreceivetel";
  /**
   * ����������vo.�ջ��ص�
   */
  public static final String CDELIVERYBID_CRECEIVEADDDOCID = "cdeliverybid.creceiveadddocid";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_CSENDAREAID = "cdeliverybid.csendareaid";
  /**
   * ����������vo.�����ص�
   */
  public static final String CDELIVERYBID_CSENDADDDOCID = "cdeliverybid.csendadddocid";
  /**
   * ����������vo.������ַ
   */
  public static final String CDELIVERYBID_CSENDADDRID = "cdeliverybid.csendaddrid";
  /**
   * ����������vo.���������ر�
   */
  public static final String CDELIVERYBID_BCLOSESRCFLAG = "cdeliverybid.bclosesrcflag";
  /**
   * ����������vo.���������������°汾
   */
  public static final String CDELIVERYBID_CPROFITCENTERID = "cdeliverybid.cprofitcenterid";
  /**
   * ����������vo.������������
   */
  public static final String CDELIVERYBID_CPROFITCENTERVID = "cdeliverybid.cprofitcentervid";
  /**
   * ����������vo.Ӧ����֯���°汾
   */
  public static final String CDELIVERYBID_CARORGID = "cdeliverybid.carorgid";
  /**
   * ����������vo.Ӧ����֯
   */
  public static final String CDELIVERYBID_CARORGVID = "cdeliverybid.carorgvid";
  /**
   * ����������vo.���������֯
   */
  public static final String CDELIVERYBID_CSETTLEORGID = "cdeliverybid.csettleorgid";
  /**
   * ����������vo.���۲������°汾
   */
  public static final String CDELIVERYBID_CDEPTID = "cdeliverybid.cdeptid";
  /**
   * ����������vo.���۲���
   */
  public static final String CDELIVERYBID_CDEPTVID = "cdeliverybid.cdeptvid";
  /**
   * ����������vo.����ҵ��Ա
   */
  public static final String CDELIVERYBID_CEMPLOYEEID = "cdeliverybid.cemployeeid";
  /**
   * ����������vo.���������֯
   */
  public static final String CDELIVERYBID_CSETTLEORGVID = "cdeliverybid.csettleorgvid";
  /**
   * ����������vo.�ջ�����/����
   */
  public static final String CDELIVERYBID_CRECECOUNTRYID = "cdeliverybid.crececountryid";
  /**
   * ����������vo.��������/����
   */
  public static final String CDELIVERYBID_CSENDCOUNTRYID = "cdeliverybid.csendcountryid";
  /**
   * ����������vo.��˰����/����
   */
  public static final String CDELIVERYBID_CTAXCOUNTRYID = "cdeliverybid.ctaxcountryid";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_FBUYSELLFLAG = "cdeliverybid.fbuysellflag";
  /**
   * ����������vo.����ó��
   */
  public static final String CDELIVERYBID_BTRIATRADEFLAG = "cdeliverybid.btriatradeflag";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_CCHANNELTYPEID = "cdeliverybid.cchanneltypeid";
  /**
   * ����������vo.Դͷ��������
   */
  public static final String CDELIVERYBID_VFIRSTBILLDATE = "cdeliverybid.vfirstbilldate";
  /**
   * ����������vo.�ۼ�;������
   */
  public static final String CDELIVERYBID_NTRANSLOSSNUM = "cdeliverybid.ntranslossnum";
  /**
   * ����������vo.�ۼƳ���Գ�����
   */
  public static final String CDELIVERYBID_NTOTALRUSHNUM = "cdeliverybid.ntotalrushnum";
  /**
   * ����������vo.�ۼ��ݹ�Ӧ������
   */
  public static final String CDELIVERYBID_NTOTALESTARNUM = "cdeliverybid.ntotalestarnum";
  /**
   * ����������vo.�ۼ�ȷ��Ӧ������
   */
  public static final String CDELIVERYBID_NTOTALARNUM = "cdeliverybid.ntotalarnum";
  /**
   * ����������vo.Ԥ������
   */
  public static final String CDELIVERYBID_NREQRSNUM = "cdeliverybid.nreqrsnum";
  /**
   * ����������vo.�Ƿ����ʼ�
   */
  public static final String CDELIVERYBID_BQUALITYFLAG = "cdeliverybid.bqualityflag";
  /**
   * ����������vo.�����˷�
   */
  public static final String CDELIVERYBID_BADVFEEFLAG = "cdeliverybid.badvfeeflag";
  /**
   * ����������vo.��������
   */
  public static final String CDELIVERYBID_PK_GROUP = "cdeliverybid.pk_group";
  /**
   * ����������vo.�۸����
   */
  public static final String CDELIVERYBID_CPRICEFORMID = "cdeliverybid.cpriceformid";
  /**
   * ����������vo.�˻�ԭ��
   */
  public static final String CDELIVERYBID_CRETREASONID = "cdeliverybid.cretreasonid";
  /**
   * ����������vo.�˻����δ���ʽ
   */
  public static final String CDELIVERYBID_VRETURNMODE = "cdeliverybid.vreturnmode";
  /**
   * ����������vo.�ۼ�Ӧ��δ����������
   */
  public static final String CDELIVERYBID_NTOTALNOTOUTNUM = "cdeliverybid.ntotalnotoutnum";
  /**
   * ����������vo.ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public static final String CDELIVERYBID_NGLOBALTAXMNY = "cdeliverybid.nglobaltaxmny";
  /**
   * ����������vo.ȫ�ֱ�����˰���
   */
  public static final String CDELIVERYBID_NGLOBALMNY = "cdeliverybid.nglobalmny";
  /**
   * ����������vo.ȫ�ֱ�λ�һ���
   */
  public static final String CDELIVERYBID_NGLOBALEXCHGRATE = "cdeliverybid.nglobalexchgrate";
  /**
   * ����������vo.���ű��Ҽ�˰�ϼ�
   */
  public static final String CDELIVERYBID_NGROUPTAXMNY = "cdeliverybid.ngrouptaxmny";
  /**
   * ����������vo.���ű�����˰���
   */
  public static final String CDELIVERYBID_NGROUPMNY = "cdeliverybid.ngroupmny";
  /**
   * ����������vo.���ű�λ�һ���
   */
  public static final String CDELIVERYBID_NGROUPEXCHGRATE = "cdeliverybid.ngroupexchgrate";
  /**
   * ����������vo.�������ʼ��id
   */
  public static final String CDELIVERYBID_CDELIVERYBBID = "cdeliverybid.cdeliverybbid";
  /**
   * ����������vo.�������ʼ��ts
   */
  public static final String CDELIVERYBID_TTS = "cdeliverybid.tts";
  /**
   * ����������vo.��Դ���ݱ�ͷʱ���
   */
  public static final String CDELIVERYBID_SRCTS = "cdeliverybid.srcts";
  /**
   * ����������vo.��Դ���ݱ���ʱ���
   */
  public static final String CDELIVERYBID_SRCBTS = "cdeliverybid.srcbts";
  /**
   * ����������vo.�������ر�
   */
  public static final String CDELIVERYBID_BBARSETTLEFLAG = "cdeliverybid.bbarsettleflag";
  /**
   * ����������vo.������������
   */
  public static final String CDELIVERYBID_CSPROFITCENTERVID = "cdeliverybid.csprofitcentervid";
  /**
   * ����������vo.���������������°汾
   */
  public static final String CDELIVERYBID_CSPROFITCENTERID = "cdeliverybid.csprofitcenterid";
  /**
   * ����������vo.�ջ���������
   */
  public static final String CDELIVERYBID_CRPROFITCENTERVID = "cdeliverybid.crprofitcentervid";
  /**
   * ����������vo.�ջ������������°汾
   */
  public static final String CDELIVERYBID_CRPROFITCENTERID = "cdeliverybid.crprofitcenterid";
  /**
   * ����������vo.������
   */
  public static final String CDELIVERYBID_CMFFILEID = "cdeliverybid.cmffileid";
  /**
   * ����������vo.vostatus
   */
  public static final String CDELIVERYBID_STATUS = "cdeliverybid.status";
  /**
   * ����������vo.dr
   */
  public static final String CDELIVERYBID_DR = "cdeliverybid.dr";
  /**
   * ����������vo.ts
   */
  public static final String CDELIVERYBID_TS = "cdeliverybid.ts";
  /**
   * ����������vo.�������ʼ��vo.�������ʼ�ʵ��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CDELIVERYCID = "cdeliverybid.cdeliverycid.cdeliverycid";
  /**
   * ����������vo.�������ʼ��vo.�ʼ�����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CROWNO = "cdeliverybid.cdeliverycid.crowno";
  /**
   * ����������vo.�������ʼ��vo.���ϰ汾
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CMATERIALID = "cdeliverybid.cdeliverycid.cmaterialid";
  /**
   * ����������vo.�������ʼ��vo.���ϱ���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CMATERIALVID = "cdeliverybid.cdeliverycid.cmaterialvid";
  /**
   * ����������vo.�������ʼ��vo.��Ӧ��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CVENDORID = "cdeliverybid.cdeliverycid.cvendorid";
  /**
   * ����������vo.�������ʼ��vo.��Ŀ
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CPROJECTID = "cdeliverybid.cdeliverycid.cprojectid";
  /**
   * ����������vo.�������ʼ��vo.�����ȼ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CQUALITYLEVELID = "cdeliverybid.cdeliverycid.cqualitylevelid";
  /**
   * ����������vo.�������ʼ��vo.��������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CPRODUCTORID = "cdeliverybid.cdeliverycid.cproductorid";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������1
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE1 = "cdeliverybid.cdeliverycid.vfree1";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������2
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE2 = "cdeliverybid.cdeliverycid.vfree2";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������3
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE3 = "cdeliverybid.cdeliverycid.vfree3";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������4
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE4 = "cdeliverybid.cdeliverycid.vfree4";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������5
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE5 = "cdeliverybid.cdeliverycid.vfree5";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������6
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE6 = "cdeliverybid.cdeliverycid.vfree6";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������7
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE7 = "cdeliverybid.cdeliverycid.vfree7";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������8
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE8 = "cdeliverybid.cdeliverycid.vfree8";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������9
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE9 = "cdeliverybid.cdeliverycid.vfree9";
  /**
   * ����������vo.�������ʼ��vo.���ɸ�������10
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE10 = "cdeliverybid.cdeliverycid.vfree10";
  /**
   * ����������vo.�������ʼ��vo.���ε���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_PK_BATCHCODE = "cdeliverybid.cdeliverycid.pk_batchcode";
  /**
   * ����������vo.�������ʼ��vo.���κ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VBATCHCODE = "cdeliverybid.cdeliverycid.vbatchcode";
  /**
   * ����������vo.�������ʼ��vo.��λ
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CASTUNITID = "cdeliverybid.cdeliverycid.castunitid";
  /**
   * ����������vo.�������ʼ��vo.����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NASTNUM = "cdeliverybid.cdeliverycid.nastnum";
  /**
   * ����������vo.�������ʼ��vo.����λ
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CUNITID = "cdeliverybid.cdeliverycid.cunitid";
  /**
   * ����������vo.�������ʼ��vo.������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NNUM = "cdeliverybid.cdeliverycid.nnum";
  /**
   * ����������vo.�������ʼ��vo.������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VCHANGERATE = "cdeliverybid.cdeliverycid.vchangerate";
  /**
   * ����������vo.�������ʼ��vo.���۵�λ
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CQTUNITID = "cdeliverybid.cdeliverycid.cqtunitid";
  /**
   * ����������vo.�������ʼ��vo.�ʼ챨������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTUNITNUM = "cdeliverybid.cdeliverycid.nqtunitnum";
  /**
   * ����������vo.�������ʼ��vo.���ۻ�����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VQTUNITRATE = "cdeliverybid.cdeliverycid.vqtunitrate";
  /**
   * ����������vo.�������ʼ��vo.�ʼ쵥�ݺ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VCHECKCODE = "cdeliverybid.cdeliverycid.vcheckcode";
  /**
   * ����������vo.�������ʼ��vo.�ʼ�����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_DCHECKDATE = "cdeliverybid.cdeliverycid.dcheckdate";
  /**
   * ����������vo.�������ʼ��vo.����״̬
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CCHECKSTATEBID = "cdeliverybid.cdeliverycid.ccheckstatebid";
  /**
   * ����������vo.�������ʼ��vo.���鴦��ʽ
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CDEFECTPROCESSID = "cdeliverybid.cdeliverycid.cdefectprocessid";
  /**
   * ����������vo.�������ʼ��vo.�Ƿ�ϸ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BELIGFLAG = "cdeliverybid.cdeliverycid.beligflag";
  /**
   * ����������vo.�������ʼ��vo.�ʼ챨���Ƿ�����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BCHECKINFLAG = "cdeliverybid.cdeliverycid.bcheckinflag";
  /**
   * ����������vo.�������ʼ��vo.��Ʒ
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BLARGESSFLAG = "cdeliverybid.cdeliverycid.blargessflag";
  /**
   * ����������vo.�������ʼ��vo.���ϸ���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BPRICECHGFLAG = "cdeliverybid.cdeliverycid.bpricechgflag";
  /**
   * ����������vo.�������ʼ��vo.˰��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXRATE = "cdeliverybid.cdeliverycid.ntaxrate";
  /**
   * ����������vo.�������ʼ��vo.�����ۿ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NDISCOUNTRATE = "cdeliverybid.cdeliverycid.ndiscountrate";
  /**
   * ����������vo.�������ʼ��vo.��Ʒ�ۿ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NITEMDISCOUNTRATE = "cdeliverybid.cdeliverycid.nitemdiscountrate";
  /**
   * ����������vo.�������ʼ��vo.����˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGTAXPRICE = "cdeliverybid.cdeliverycid.norigtaxprice";
  /**
   * ����������vo.�������ʼ��vo.����˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGPRICE = "cdeliverybid.cdeliverycid.norigprice";
  /**
   * ����������vo.�������ʼ��vo.����˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGTAXNETPRICE = "cdeliverybid.cdeliverycid.norigtaxnetprice";
  /**
   * ����������vo.�������ʼ��vo.����˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGNETPRICE = "cdeliverybid.cdeliverycid.norignetprice";
  /**
   * ����������vo.�������ʼ��vo.��˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGTAXPRICE = "cdeliverybid.cdeliverycid.nqtorigtaxprice";
  /**
   * ����������vo.�������ʼ��vo.��˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGPRICE = "cdeliverybid.cdeliverycid.nqtorigprice";
  /**
   * ����������vo.�������ʼ��vo.��˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGTAXNETPRC = "cdeliverybid.cdeliverycid.nqtorigtaxnetprc";
  /**
   * ����������vo.�������ʼ��vo.��˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGNETPRICE = "cdeliverybid.cdeliverycid.nqtorignetprice";
  /**
   * ����������vo.�������ʼ��vo.��˰���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGMNY = "cdeliverybid.cdeliverycid.norigmny";
  /**
   * ����������vo.�������ʼ��vo.��˰�ϼ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGTAXMNY = "cdeliverybid.cdeliverycid.norigtaxmny";
  /**
   * ����������vo.�������ʼ��vo.�ۿ۶�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGDISCOUNT = "cdeliverybid.cdeliverycid.norigdiscount";
  /**
   * ����������vo.�������ʼ��vo.��������˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXPRICE = "cdeliverybid.cdeliverycid.ntaxprice";
  /**
   * ����������vo.�������ʼ��vo.��������˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NPRICE = "cdeliverybid.cdeliverycid.nprice";
  /**
   * ����������vo.�������ʼ��vo.��������˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXNETPRICE = "cdeliverybid.cdeliverycid.ntaxnetprice";
  /**
   * ����������vo.�������ʼ��vo.��������˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NNETPRICE = "cdeliverybid.cdeliverycid.nnetprice";
  /**
   * ����������vo.�������ʼ��vo.���Һ�˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTTAXPRICE = "cdeliverybid.cdeliverycid.nqttaxprice";
  /**
   * ����������vo.�������ʼ��vo.������˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTPRICE = "cdeliverybid.cdeliverycid.nqtprice";
  /**
   * ����������vo.�������ʼ��vo.���Һ�˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTTAXNETPRICE = "cdeliverybid.cdeliverycid.nqttaxnetprice";
  /**
   * ����������vo.�������ʼ��vo.������˰����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTNETPRICE = "cdeliverybid.cdeliverycid.nqtnetprice";
  /**
   * ����������vo.�������ʼ��vo.����˰��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAX = "cdeliverybid.cdeliverycid.ntax";
  /**
   * ����������vo.�������ʼ��vo.������˰���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NMNY = "cdeliverybid.cdeliverycid.nmny";
  /**
   * ����������vo.�������ʼ��vo.���Ҽ�˰�ϼ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXMNY = "cdeliverybid.cdeliverycid.ntaxmny";
  /**
   * ����������vo.�������ʼ��vo.�����ۿ۶�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NDISCOUNT = "cdeliverybid.cdeliverycid.ndiscount";
  /**
   * ����������vo.�������ʼ��vo.ԭ��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CORIGCURRENCYID = "cdeliverybid.cdeliverycid.corigcurrencyid";
  /**
   * ����������vo.�������ʼ��vo.�۱�����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NEXCHANGERATE = "cdeliverybid.cdeliverycid.nexchangerate";
  /**
   * ����������vo.�������ʼ��vo.��λ��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CCURRENCYID = "cdeliverybid.cdeliverycid.ccurrencyid";
  /**
   * ����������vo.�������ʼ��vo.�ۼ���������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTOTALTRANSNUM = "cdeliverybid.cdeliverycid.ntotaltransnum";
  /**
   * ����������vo.�������ʼ��vo.����ر�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BTRANSENDFLAG = "cdeliverybid.cdeliverycid.btransendflag";
  /**
   * ����������vo.�������ʼ��vo.�ۼƳ�������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTOTALOUTNUM = "cdeliverybid.cdeliverycid.ntotaloutnum";
  /**
   * ����������vo.�������ʼ��vo.����ر�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BOUTENDFLAG = "cdeliverybid.cdeliverycid.boutendflag";
  /**
   * ����������vo.�������ʼ��vo.��ע
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VROWNOTE = "cdeliverybid.cdeliverycid.vrownote";
  /**
   * ����������vo.�������ʼ��vo.���յ��к�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VSRCROWNO = "cdeliverybid.cdeliverycid.vsrcrowno";
  /**
   * ����������vo.�������ʼ��vo.������֯
   */
  public static final String CDELIVERYBID_CDELIVERYCID_PK_ORG = "cdeliverybid.cdeliverycid.pk_org";
  /**
   * ����������vo.�������ʼ��vo.��������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_PK_GROUP = "cdeliverybid.cdeliverycid.pk_group";
  /**
   * ����������vo.�������ʼ��vo.�ۼ�Ӧ��δ����������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTOTALNOTOUTNUM = "cdeliverybid.cdeliverycid.ntotalnotoutnum";
  /**
   * ����������vo.�������ʼ��vo.��Դ����id
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CSRCID = "cdeliverybid.cdeliverycid.csrcid";
  /**
   * ����������vo.�������ʼ��vo.ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGLOBALTAXMNY = "cdeliverybid.cdeliverycid.nglobaltaxmny";
  /**
   * ����������vo.�������ʼ��vo.ȫ�ֱ�����˰���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGLOBALMNY = "cdeliverybid.cdeliverycid.nglobalmny";
  /**
   * ����������vo.�������ʼ��vo.ȫ�ֱ�λ�һ���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGLOBALEXCHGRATE = "cdeliverybid.cdeliverycid.nglobalexchgrate";
  /**
   * ����������vo.�������ʼ��vo.���ű��Ҽ�˰�ϼ�
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGROUPTAXMNY = "cdeliverybid.cdeliverycid.ngrouptaxmny";
  /**
   * ����������vo.�������ʼ��vo.���ű�����˰���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGROUPMNY = "cdeliverybid.cdeliverycid.ngroupmny";
  /**
   * ����������vo.�������ʼ��vo.���ű�λ�һ���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGROUPEXCHGRATE = "cdeliverybid.cdeliverycid.ngroupexchgrate";
  /**
   * ����������vo.�������ʼ��vo.�ջ�����/����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CRECECOUNTRYID = "cdeliverybid.cdeliverycid.crececountryid";
  /**
   * ����������vo.�������ʼ��vo.������/����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CSENDCOUNTRYID = "cdeliverybid.cdeliverycid.csendcountryid";
  /**
   * ����������vo.�������ʼ��vo.��˰��/����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CTAXCOUNTRYID = "cdeliverybid.cdeliverycid.ctaxcountryid";
  /**
   * ����������vo.�������ʼ��vo.��������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_FBUYSELLFLAG = "cdeliverybid.cdeliverycid.fbuysellflag";
  /**
   * ����������vo.�������ʼ��vo.����ó��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BTRIATRADEFLAG = "cdeliverybid.cdeliverycid.btriatradeflag";
  /**
   * ����������vo.�������ʼ��vo.˰��
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CTAXCODEID = "cdeliverybid.cdeliverycid.ctaxcodeid";
  /**
   * ����������vo.�������ʼ��vo.��˰���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_FTAXTYPEFLAG = "cdeliverybid.cdeliverycid.ftaxtypeflag";
  /**
   * ����������vo.�������ʼ��vo.ԭ����
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CORIGCOUNTRYID = "cdeliverybid.cdeliverycid.corigcountryid";
  /**
   * ����������vo.�������ʼ��vo.ԭ������
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CORIGAREAID = "cdeliverybid.cdeliverycid.corigareaid";
  /**
   * ����������vo.�������ʼ��vo.��˰���
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NCALTAXMNY = "cdeliverybid.cdeliverycid.ncaltaxmny";
  /**
   * ����������vo.�������ʼ��vo.vostatus
   */
  public static final String CDELIVERYBID_CDELIVERYCID_STATUS = "cdeliverybid.cdeliverycid.status";
  /**
   * ����������vo.�������ʼ��vo.dr
   */
  public static final String CDELIVERYBID_CDELIVERYCID_DR = "cdeliverybid.cdeliverycid.dr";
  /**
   * ����������vo.�������ʼ��vo.ts
   */
  public static final String CDELIVERYBID_CDELIVERYCID_TS = "cdeliverybid.cdeliverycid.ts";
}


