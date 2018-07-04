package nc.pubitf.so.m4310.api;

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
 * @version 2015-11-12 ����10:57:40
 * @author ����
 */
public interface IAggSalequotationHVO extends Serializable {
  
  /**
   * ���۱��۵���ʵ��
   */
  public static final String PK_SALEQUOTATION = "pk_salequotation";
  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * ������֯�汾
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * ������֯
   */
  public static final String PK_ORG = "pk_org";
  /**
   * ���۵���
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * ���۵����ͱ���
   */
  public static final String VTRANTYPE = "vtrantype";
  /**
   * ���۵�����
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * ��������
   */
  public static final String DQUOTEDATE = "dquotedate";
  /**
   * ʧЧ����
   */
  public static final String DENDDATE = "denddate";
  /**
   * �ͻ�
   */
  public static final String PK_CUSTOMER = "pk_customer";
  /**
   * ��������
   */
  public static final String PK_CHANNELTYPE = "pk_channeltype";
  /**
   * ҵ��Ա
   */
  public static final String CEMPLOYEEID = "cemployeeid";
  /**
   * ����
   */
  public static final String PK_DEPT_V = "pk_dept_v";
  /**
   * ����
   */
  public static final String PK_DEPT = "pk_dept";
  /**
   * �տ�Э��
   */
  public static final String PK_PAYTERM = "pk_payterm";
  /**
   * ���㷽ʽ
   */
  public static final String PK_BALATYPE = "pk_balatype";
  /**
   * �����ۿ�(%)
   */
  public static final String NDISCOUNT = "ndiscount";
  /**
   * ����
   */
  public static final String PK_CURRTYPE = "pk_currtype";
  /**
   * ���䷽ʽ
   */
  public static final String CSENDTYPEID = "csendtypeid";
  /**
   * ������
   */
  public static final String NTOTALNUM = "ntotalnum";
  /**
   * ��˰�ϼ�
   */
  public static final String NTOTALMNY = "ntotalmny";
  /**
   * ����״̬
   */
  public static final String FSTATUSFLAG = "fstatusflag";
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
   * ������
   */
  public static final String CREATOR = "creator";
  /**
   * �Ƶ���
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * �Ƶ�ʱ��
   */
  public static final String DBILLDATE = "dbilldate";
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
   * ����ʱ��
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * �Ƶ�����
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * ������Դ��������
   */
  public static final String VBILLSRCTYPE = "vbillsrctype";
  /**
   * ������Դ����ID
   */
  public static final String CBILLSRCID = "cbillsrcid";
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
   * ���۵��ӱ�.���۱��۵���ʵ��
   */
  public static final String SALEQUOTATIONDETAIL_PK_SALEQUOTATION_B = "salequotationdetail.pk_salequotation_b";
  /**
   * ���۵��ӱ�.����
   */
  public static final String SALEQUOTATIONDETAIL_PK_GROUP = "salequotationdetail.pk_group";
  /**
   * ���۵��ӱ�.������֯�汾��Ϣ
   */
  public static final String SALEQUOTATIONDETAIL_PK_ORG_V = "salequotationdetail.pk_org_v";
  /**
   * ���۵��ӱ�.������֯
   */
  public static final String SALEQUOTATIONDETAIL_PK_ORG = "salequotationdetail.pk_org";
  /**
   * ���۵��ӱ�.�к�
   */
  public static final String SALEQUOTATIONDETAIL_CROWNO = "salequotationdetail.crowno";
  /**
   * ���۵��ӱ�.�ͻ�������
   */
  public static final String SALEQUOTATIONDETAIL_CCUSTMATERIALID = "salequotationdetail.ccustmaterialid";
  /**
   * ���۵��ӱ�.���ϱ���
   */
  public static final String SALEQUOTATIONDETAIL_PK_MATERIAL_V = "salequotationdetail.pk_material_v";
  /**
   * ���۵��ӱ�.����
   */
  public static final String SALEQUOTATIONDETAIL_PK_MATERIAL = "salequotationdetail.pk_material";
  /**
   * ���۵��ӱ�.�����ȼ�
   */
  public static final String SALEQUOTATIONDETAIL_PK_QUALITYLEVEL = "salequotationdetail.pk_qualitylevel";
  /**
   * ���۵��ӱ�.��Ŀ
   */
  public static final String SALEQUOTATIONDETAIL_PK_PROJECT = "salequotationdetail.pk_project";
  /**
   * ���۵��ӱ�.��������
   */
  public static final String SALEQUOTATIONDETAIL_PK_PRODUCTOR = "salequotationdetail.pk_productor";
  /**
   * ���۵��ӱ�.��Ӧ��
   */
  public static final String SALEQUOTATIONDETAIL_PK_SUPPLIER = "salequotationdetail.pk_supplier";
  /**
   * ���۵��ӱ�.��λ
   */
  public static final String SALEQUOTATIONDETAIL_CASTUNITID = "salequotationdetail.castunitid";
  /**
   * ���۵��ӱ�.����
   */
  public static final String SALEQUOTATIONDETAIL_NASSISTNUM = "salequotationdetail.nassistnum";
  /**
   * ���۵��ӱ�.������
   */
  public static final String SALEQUOTATIONDETAIL_NCHANGERATE = "salequotationdetail.nchangerate";
  /**
   * ���۵��ӱ�.����λ
   */
  public static final String SALEQUOTATIONDETAIL_PK_UNIT = "salequotationdetail.pk_unit";
  /**
   * ���۵��ӱ�.������
   */
  public static final String SALEQUOTATIONDETAIL_NNUM = "salequotationdetail.nnum";
  /**
   * ���۵��ӱ�.���۵�λ
   */
  public static final String SALEQUOTATIONDETAIL_CQTUNITID = "salequotationdetail.cqtunitid";
  /**
   * ���۵��ӱ�.��������
   */
  public static final String SALEQUOTATIONDETAIL_NQTNUM = "salequotationdetail.nqtnum";
  /**
   * ���۵��ӱ�.���ۻ�����
   */
  public static final String SALEQUOTATIONDETAIL_NQTCHANGERATE = "salequotationdetail.nqtchangerate";
  /**
   * ���۵��ӱ�.�ջ�����
   */
  public static final String SALEQUOTATIONDETAIL_PK_AREACL = "salequotationdetail.pk_areacl";
  /**
   * ���۵��ӱ�.���ɸ�������1
   */
  public static final String SALEQUOTATIONDETAIL_VFREE1 = "salequotationdetail.vfree1";
  /**
   * ���۵��ӱ�.���ɸ�������2
   */
  public static final String SALEQUOTATIONDETAIL_VFREE2 = "salequotationdetail.vfree2";
  /**
   * ���۵��ӱ�.���ɸ�������3
   */
  public static final String SALEQUOTATIONDETAIL_VFREE3 = "salequotationdetail.vfree3";
  /**
   * ���۵��ӱ�.���ɸ�������4
   */
  public static final String SALEQUOTATIONDETAIL_VFREE4 = "salequotationdetail.vfree4";
  /**
   * ���۵��ӱ�.���ɸ�������5
   */
  public static final String SALEQUOTATIONDETAIL_VFREE5 = "salequotationdetail.vfree5";
  /**
   * ���۵��ӱ�.���ɸ�������6
   */
  public static final String SALEQUOTATIONDETAIL_VFREE6 = "salequotationdetail.vfree6";
  /**
   * ���۵��ӱ�.���ɸ�������7
   */
  public static final String SALEQUOTATIONDETAIL_VFREE7 = "salequotationdetail.vfree7";
  /**
   * ���۵��ӱ�.���ɸ�������8
   */
  public static final String SALEQUOTATIONDETAIL_VFREE8 = "salequotationdetail.vfree8";
  /**
   * ���۵��ӱ�.���ɸ�������9
   */
  public static final String SALEQUOTATIONDETAIL_VFREE9 = "salequotationdetail.vfree9";
  /**
   * ���۵��ӱ�.���ɸ�������10
   */
  public static final String SALEQUOTATIONDETAIL_VFREE10 = "salequotationdetail.vfree10";
  /**
   * ���۵��ӱ�.��˰����
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGPRICE = "salequotationdetail.nqtorigprice";
  /**
   * ���۵��ӱ�.˰��
   */
  public static final String SALEQUOTATIONDETAIL_CTAXCODEID = "salequotationdetail.ctaxcodeid";
  /**
   * ���۵��ӱ�.˰��(%)
   */
  public static final String SALEQUOTATIONDETAIL_NTAXRATE = "salequotationdetail.ntaxrate";
  /**
   * ���۵��ӱ�.��˰���
   */
  public static final String SALEQUOTATIONDETAIL_FTAXTYPEFLAG = "salequotationdetail.ftaxtypeflag";
  /**
   * ���۵��ӱ�.��˰����
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGTAXPRICE = "salequotationdetail.nqtorigtaxprice";
  /**
   * ���۵��ӱ�.�����ۿ�(%)
   */
  public static final String SALEQUOTATIONDETAIL_NDISCOUNTRATE = "salequotationdetail.ndiscountrate";
  /**
   * ���۵��ӱ�.��Ʒ�ۿ�(%)
   */
  public static final String SALEQUOTATIONDETAIL_NITEMDISCOUNTRATE = "salequotationdetail.nitemdiscountrate";
  /**
   * ���۵��ӱ�.��˰����
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGNETPRICE = "salequotationdetail.nqtorignetprice";
  /**
   * ���۵��ӱ�.��˰����
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGTAXNETPRC = "salequotationdetail.nqtorigtaxnetprc";
  /**
   * ���۵��ӱ�.��˰���
   */
  public static final String SALEQUOTATIONDETAIL_NORIGMNY = "salequotationdetail.norigmny";
  /**
   * ���۵��ӱ�.��˰�ϼ�
   */
  public static final String SALEQUOTATIONDETAIL_NORIGTAXMNY = "salequotationdetail.norigtaxmny";
  /**
   * ���۵��ӱ�.�ۿ۶�
   */
  public static final String SALEQUOTATIONDETAIL_NORIGDISCOUNT = "salequotationdetail.norigdiscount";
  /**
   * ���۵��ӱ�.��Ʒ
   */
  public static final String SALEQUOTATIONDETAIL_BLARGESSFLAG = "salequotationdetail.blargessflag";
  /**
   * ���۵��ӱ�.���۲���
   */
  public static final String SALEQUOTATIONDETAIL_PK_PRICEPOLICY = "salequotationdetail.pk_pricepolicy";
  /**
   * ���۵��ӱ�.��Ŀ��
   */
  public static final String SALEQUOTATIONDETAIL_PK_TARIFFDEF = "salequotationdetail.pk_tariffdef";
  /**
   * ���۵��ӱ�.�۸���
   */
  public static final String SALEQUOTATIONDETAIL_PK_PRICETYPE = "salequotationdetail.pk_pricetype";
  /**
   * ���۵��ӱ�.�۸����
   */
  public static final String SALEQUOTATIONDETAIL_VPRICEDETAIL = "salequotationdetail.vpricedetail";
  /**
   * ���۵��ӱ�.�ջ�����/����
   */
  public static final String SALEQUOTATIONDETAIL_CRECECOUNTRYID = "salequotationdetail.crececountryid";
  /**
   * ���۵��ӱ�.��������/����
   */
  public static final String SALEQUOTATIONDETAIL_CSENDCOUNTRYID = "salequotationdetail.csendcountryid";
  /**
   * ���۵��ӱ�.��˰����/����
   */
  public static final String SALEQUOTATIONDETAIL_CTAXCOUNTRYID = "salequotationdetail.ctaxcountryid";
  /**
   * ���۵��ӱ�.��������
   */
  public static final String SALEQUOTATIONDETAIL_FBUYSELLFLAG = "salequotationdetail.fbuysellflag";
  /**
   * ���۵��ӱ�.����ó��
   */
  public static final String SALEQUOTATIONDETAIL_BTRIATRADEFLAG = "salequotationdetail.btriatradeflag";
  /**
   * ���۵��ӱ�.�ۼ����ɶ���������
   */
  public static final String SALEQUOTATIONDETAIL_NORDERNUM = "salequotationdetail.nordernum";
  /**
   * ���۵��ӱ�.�ۼ����ɺ�ͬ������
   */
  public static final String SALEQUOTATIONDETAIL_NCONTRACTNUM = "salequotationdetail.ncontractnum";
  /**
   * ���۵��ӱ�.��ע
   */
  public static final String SALEQUOTATIONDETAIL_VBNOTE = "salequotationdetail.vbnote";
  /**
   * ���۵��ӱ�.����˰����
   */
  public static final String SALEQUOTATIONDETAIL_NORIGPRICE = "salequotationdetail.norigprice";
  /**
   * ���۵��ӱ�.����˰����
   */
  public static final String SALEQUOTATIONDETAIL_NORIGTAXPRICE = "salequotationdetail.norigtaxprice";
  /**
   * ���۵��ӱ�.����˰����
   */
  public static final String SALEQUOTATIONDETAIL_NORIGNETPRICE = "salequotationdetail.norignetprice";
  /**
   * ���۵��ӱ�.����˰����
   */
  public static final String SALEQUOTATIONDETAIL_NORIGTAXNETPRICE = "salequotationdetail.norigtaxnetprice";
  /**
   * ���۵��ӱ�.�Զ�����1
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF1 = "salequotationdetail.vbdef1";
  /**
   * ���۵��ӱ�.�Զ�����2
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF2 = "salequotationdetail.vbdef2";
  /**
   * ���۵��ӱ�.�Զ�����3
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF3 = "salequotationdetail.vbdef3";
  /**
   * ���۵��ӱ�.�Զ�����4
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF4 = "salequotationdetail.vbdef4";
  /**
   * ���۵��ӱ�.�Զ�����5
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF5 = "salequotationdetail.vbdef5";
  /**
   * ���۵��ӱ�.�Զ�����6
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF6 = "salequotationdetail.vbdef6";
  /**
   * ���۵��ӱ�.�Զ�����7
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF7 = "salequotationdetail.vbdef7";
  /**
   * ���۵��ӱ�.�Զ�����8
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF8 = "salequotationdetail.vbdef8";
  /**
   * ���۵��ӱ�.�Զ�����9
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF9 = "salequotationdetail.vbdef9";
  /**
   * ���۵��ӱ�.�Զ�����10
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF10 = "salequotationdetail.vbdef10";
  /**
   * ���۵��ӱ�.�Զ�����11
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF11 = "salequotationdetail.vbdef11";
  /**
   * ���۵��ӱ�.�Զ�����12
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF12 = "salequotationdetail.vbdef12";
  /**
   * ���۵��ӱ�.�Զ�����13
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF13 = "salequotationdetail.vbdef13";
  /**
   * ���۵��ӱ�.�Զ�����14
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF14 = "salequotationdetail.vbdef14";
  /**
   * ���۵��ӱ�.�Զ�����15
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF15 = "salequotationdetail.vbdef15";
  /**
   * ���۵��ӱ�.�Զ�����16
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF16 = "salequotationdetail.vbdef16";
  /**
   * ���۵��ӱ�.�Զ�����17
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF17 = "salequotationdetail.vbdef17";
  /**
   * ���۵��ӱ�.�Զ�����18
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF18 = "salequotationdetail.vbdef18";
  /**
   * ���۵��ӱ�.�Զ�����19
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF19 = "salequotationdetail.vbdef19";
  /**
   * ���۵��ӱ�.�Զ�����20
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF20 = "salequotationdetail.vbdef20";
  /**
   * ���۵��ӱ�.��Դ��������
   */
  public static final String SALEQUOTATIONDETAIL_VSRCTYPE = "salequotationdetail.vsrctype";
  /**
   * ���۵��ӱ�.��Դ��������
   */
  public static final String SALEQUOTATIONDETAIL_VSRCTRANTYPE = "salequotationdetail.vsrctrantype";
  /**
   * ���۵��ӱ�.��Դ���ݺ�
   */
  public static final String SALEQUOTATIONDETAIL_VSRCCODE = "salequotationdetail.vsrccode";
  /**
   * ���۵��ӱ�.��Դ�����к�
   */
  public static final String SALEQUOTATIONDETAIL_VSRCROWNO = "salequotationdetail.vsrcrowno";
  /**
   * ���۵��ӱ�.��Դ��������
   */
  public static final String SALEQUOTATIONDETAIL_CSRCID = "salequotationdetail.csrcid";
  /**
   * ���۵��ӱ�.��Դ���ݸ���
   */
  public static final String SALEQUOTATIONDETAIL_CSRCBID = "salequotationdetail.csrcbid";
  /**
   * ���۵��ӱ�.Դͷ��������
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTTYPE = "salequotationdetail.vfirsttype";
  /**
   * ���۵��ӱ�.Դͷ��������
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTTRANTYPE = "salequotationdetail.vfirsttrantype";
  /**
   * ���۵��ӱ�.Դͷ���ݺ�
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTCODE = "salequotationdetail.vfirstcode";
  /**
   * ���۵��ӱ�.Դͷ��������
   */
  public static final String SALEQUOTATIONDETAIL_CFIRSTID = "salequotationdetail.cfirstid";
  /**
   * ���۵��ӱ�.Դͷ���ݸ���
   */
  public static final String SALEQUOTATIONDETAIL_CFIRSTBID = "salequotationdetail.cfirstbid";
  /**
   * ���۵��ӱ�.Դͷ�����к�
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTROWNO = "salequotationdetail.vfirstrowno";
  /**
   * ���۵��ӱ�.��Դ���ݱ�ͷʱ���
   */
  public static final String SALEQUOTATIONDETAIL_SRCTS = "salequotationdetail.srcts";
  /**
   * ���۵��ӱ�.��Դ���ݱ���ʱ���
   */
  public static final String SALEQUOTATIONDETAIL_SRCBTS = "salequotationdetail.srcbts";
  /**
   * ���۵��ӱ�.vostatus
   */
  public static final String SALEQUOTATIONDETAIL_STATUS = "salequotationdetail.status";
  /**
   * ���۵��ӱ�.dr
   */
  public static final String SALEQUOTATIONDETAIL_DR = "salequotationdetail.dr";
  /**
   * ���۵��ӱ�.ts
   */
  public static final String SALEQUOTATIONDETAIL_TS = "salequotationdetail.ts";
}


