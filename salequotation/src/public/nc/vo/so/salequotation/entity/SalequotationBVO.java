package nc.vo.so.salequotation.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �ڴ˴���Ҫ��������Ĺ��� </b>
 * <p>
 * �ڴ˴���Ӵ����������Ϣ
 * </p>
 * ��������:2009-10-17 12:34:25
 * 
 * @author
 * @version NCPrj ??
 */
/**
 * 
 * @since 6.1
 * @version 2013-09-24 15:42:19
 * @author zhangyfr
 */
/**
 * 
 * @since 6.1
 * @version 2013-09-24 15:42:37
 * @author zhangyfr
 */
public class SalequotationBVO extends SuperVO {

  /**
   * �ͻ����ϱ���
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * ���ÿͻ����ϱ���
   * 
   * @param ccustmaterialid
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SalequotationBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SalequotationBVO.CCUSTMATERIALID);
  }

  /**
   * ��Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * ����ó��
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * ��λ
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * ���۵�λ
   */
  public static final String CQTUNITID = "cqtunitid";

  /**
   * �ջ�����/����
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /**
   * �к�
   */
  public static final String CROWNO = "crowno";

  /**
   * ��������/����
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * ˰��
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * ��˰����/����
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * dr
   */
  public static final String DR = "dr";

  /**
   * ��������
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** SalequotationBVOԪ����·��(Ԫ���������ϵ��ӱ�ۺ�����) */
  public static final String METAPATH = "salequotationdetail.";

  /**
   * ����
   */
  public static final String NASSISTNUM = "nassistnum";

  /**
   * ������
   */
  public static final String NCHANGERATE = "nchangerate";

  /**
   * �ۼ����ɺ�ͬ������
   */
  public static final String NCONTRACTNUM = "ncontractnum";

  /**
   * �����ۿ�(%)
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /**
   * ��Ʒ�ۿ�(%)
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * ������
   */
  public static final String NNUM = "nnum";

  /**
   * �ۼ����ɶ���������
   */
  public static final String NORDERNUM = "nordernum";

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
   * ��˰�ϼ�
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * ����˰����
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * ����˰����
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * ���ۻ�����
   */
  public static final String NQTCHANGERATE = "nqtchangerate";

  /**
   * ��������
   */
  public static final String NQTNUM = "nqtnum";

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
   * ˰��(%)
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * �ջ�����
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * �������°汾
   */
  public static final String PK_MATERIAL = "pk_material";

  /**
   * ���ϱ���
   */
  public static final String PK_MATERIAL_V = "pk_material_v";

  /**
   * ������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * ������֯�汾��Ϣ
   */
  public static final String PK_ORG_V = "pk_org_v";

  /**
   * ���۲���
   */
  public static final String PK_PRICEPOLICY = "pk_pricepolicy";

  /**
   * �۸���
   */
  public static final String PK_PRICETYPE = "pk_pricetype";

  /**
   * ��������
   */
  public static final String PK_PRODUCTOR = "pk_productor";

  /**
   * ��Ŀ
   */
  public static final String PK_PROJECT = "pk_project";

  /**
   * �����ȼ�
   */
  public static final String PK_QUALITYLEVEL = "pk_qualitylevel";

  /**
   * ���۵���ͷ_����
   */
  public static final String PK_SALEQUOTATION = "pk_salequotation";

  /**
   * ���۱��۵��ӱ�����
   */
  public static final String PK_SALEQUOTATION_B = "pk_salequotation_b";

  /**
   * ��Ӧ��
   */
  public static final String PK_SUPPLIER = "pk_supplier";

  /**
   * ��Ŀ��
   */
  public static final String PK_TARIFFDEF = "pk_tariffdef";

  /**
   * ����λ
   */
  public static final String PK_UNIT = "pk_unit";

  private static final long serialVersionUID = 567895286598696462L;

  /**
   * ʱ���
   */
  public static final String TS = "ts";

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
   * �Զ����� 4
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
   * ��ע
   */
  public static final String VBNOTE = "vbnote";

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
   * ��Դ���ݸ���
   */
  public static final String CSRCBID = "csrcbid";

  /**
   * ��Դ��������
   */
  public static final String CSRCID = "csrcid";

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
   * Դͷ�����ӱ�
   */
  public static final String CFIRSTBID = "cfirstbid";

  /**
   * Դͷ��������
   */
  public static final String CFIRSTID = "cfirstid";

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
   * srcbts��������༭��
   */
  public static final String SRCBTS = "srcbts";

  /**
   * srcts��������༭��
   */
  public static final String SRCTS = "srcts";

  // �۸����
  public static final String VPRICEDETAIL = "vpricedetail";

  /**
   * ����Ĭ�Ϸ�ʽ����������.
   * 
   * ��������:2009-10-17 12:34:25
   */
  public SalequotationBVO() {
    super();
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SalequotationBVO.BLARGESSFLAG);
  }

  /**
   * ��ȡ����ó��
   * 
   * @return ����ó��
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SalequotationBVO.BTRIATRADEFLAG);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(SalequotationBVO.CASTUNITID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(SalequotationBVO.CQTUNITID);
  }

  /**
   * ��ȡ�ջ�����/����
   * 
   * @return �ջ����/����
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SalequotationBVO.CRECECOUNTRYID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SalequotationBVO.CROWNO);
  }

  /**
   * ��ȡ��������/����
   * 
   * @return ��������/����
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SalequotationBVO.CSENDCOUNTRYID);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SalequotationBVO.CTAXCODEID);
  }

  /**
   * ��ȡ��˰���Һ͵���
   * 
   * @return ��˰����/����
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SalequotationBVO.CTAXCOUNTRYID);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SalequotationBVO.DR);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SalequotationBVO.FBUYSELLFLAG);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SalequotationBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.SalequotationBVO");
    return meta;
  }

  public UFDouble getNassistnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NASSISTNUM);
  }

  public String getNchangerate() {
    return (String) this.getAttributeValue(SalequotationBVO.NCHANGERATE);
  }

  public UFDouble getNcontractnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NCONTRACTNUM);
  }

  // /**
  // * ����bcloseflag��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFBoolean
  // */
  // public nc.vo.pub.lang.UFBoolean getBcloseflag() {
  // return (UFBoolean) this.getAttributeValue("bcloseflag");
  // }

  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SalequotationBVO.NITEMDISCOUNTRATE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NNUM);
  }

  public UFDouble getNordernum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORDERNUM);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NDISCOUNTRATE);
  }

  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGDISCOUNT);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGMNY);
  }

  // /**
  // * ����fhslcaltypeflag��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return java.lang.Integer
  // */
  // public Integer getFhslcaltypeflag() {
  // return (Integer) this.getAttributeValue("fhslcaltypeflag");
  // }

  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGNETPRICE);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGPRICE);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGTAXNETPRICE);
  }

  // /**
  // * ����ndiscount��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNdiscount() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("ndiscount");
  // }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGTAXPRICE);
  }

  public String getNqtchangerate() {
    return (String) this.getAttributeValue(SalequotationBVO.NQTCHANGERATE);
  }

  // /**
  // * ����nmny��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNmny() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("nmny");
  // }

  // /**
  // * ����nnetprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNnetprice() {
  // return (UFDouble) this.getAttributeValue("nnetprice");
  // }

  public UFDouble getNqtnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTNUM);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGNETPRICE);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGTAXNETPRC);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NTAXRATE);
  }

  /**
   * <p>
   * ȡ�ø�VO�����ֶ�.
   * <p>
   * ��������:2009-10-17 12:34:25
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getParentPKFieldName() {
    return "pk_salequotation";
  }

  public String getPk_areacl() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_AREACL);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_GROUP);
  }

  // /**
  // * ����nprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNprice() {
  // return (UFDouble) this.getAttributeValue("nprice");
  // }

  public String getPk_material() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_MATERIAL);
  }

  // /**
  // * ����nqtnetprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqtnetprice() {
  // return (UFDouble) this.getAttributeValue("nqtnetprice");
  // }

  public String getPk_material_v() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_MATERIAL_V);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_ORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_ORG_V);
  }

  public String getPk_pricepolicy() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PRICEPOLICY);
  }

  public String getPk_pricetype() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PRICETYPE);
  }

  // /**
  // * ����nqtprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqtprice() {
  // return (UFDouble) this.getAttributeValue("nqtprice");
  // }

  // /**
  // * ����nqttaxnetprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqttaxnetprice() {
  // return (UFDouble) this.getAttributeValue("nqttaxnetprice");
  // }

  // /**
  // * ����nqttaxprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqttaxprice() {
  // return (UFDouble) this.getAttributeValue("nqttaxprice");
  // }
  //
  // /**
  // * ����ntax��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNtax() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("ntax");
  // }
  //
  // /**
  // * ����ntaxmny��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNtaxmny() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("ntaxmny");
  // }
  //
  // /**
  // * ����ntaxnetprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNtaxnetprice() {
  // return (UFDouble) this.getAttributeValue("ntaxnetprice");
  // }
  //
  // /**
  // * ����ntaxprice��Getter����. ��������:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNtaxprice() {
  // return (UFDouble) this.getAttributeValue("ntaxprice");
  // }

  public String getPk_productor() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PRODUCTOR);
  }

  public String getPk_project() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PROJECT);
  }

  public String getPk_qualitylevel() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_QUALITYLEVEL);
  }

  public String getPk_salequotation() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_SALEQUOTATION);
  }

  public String getPk_salequotation_b() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_SALEQUOTATION_B);
  }

  public String getPk_supplier() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_SUPPLIER);
  }

  public String getPk_tariffdef() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_TARIFFDEF);
  }

  public String getPk_unit() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_UNIT);
  }

  /**
   * <p>
   * ȡ�ñ�����.
   * <p>
   * ��������:2009-10-17 12:34:25
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getPKFieldName() {
    return "pk_salequotation_b";
  }

  /**
   * <p>
   * ���ر�����.
   * <p>
   * ��������:2009-10-17 12:34:25
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getTableName() {
    return "so_salequotation_b";
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SalequotationBVO.TS);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF9);
  }

  public String getVbnote() {
    return (String) this.getAttributeValue(SalequotationBVO.VBNOTE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE9);
  }

  public String getVpricedetail() {
    return (String) this.getAttributeValue(SalequotationBVO.VPRICEDETAIL);
  }

  /**
   * ��ȡԴͷ�����ӱ�
   * 
   * @return Դͷ�����ӱ�
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SalequotationBVO.CFIRSTBID);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SalequotationBVO.CFIRSTID);
  }

  /**
   * ��ȡԴͷ���ݺ�
   * 
   * @return Դͷ���ݺ�
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTCODE);
  }

  /**
   * ��ȡԴͷ�����к�
   * 
   * @return Դͷ�����к�
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTROWNO);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTTRANTYPE);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTTYPE);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SalequotationBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * ��ȡ��Դ���ݺ�
   * 
   * @return ��Դ���ݺ�
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCCODE);
  }

  /**
   * ��ȡ��Դ�����к�
   * 
   * @return ��Դ�����к�
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCROWNO);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCTRANTYPE);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCTYPE);
  }

  /**
   * ��ȡ��Դ���ݸ���
   * 
   * @return ��Դ���ݸ���
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SalequotationBVO.CSRCBID);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SalequotationBVO.CSRCID);
  }

  /**
   * ��ȡ��Դʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(SalequotationBVO.SRCBTS);
  }

  /**
   * ��ȡ��Դʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(SalequotationBVO.SRCTS);
  }

  /**
   * ���� ����ó��
   * 
   * @param btriatradeflag ����ó��
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SalequotationBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SalequotationBVO.CASTUNITID, castunitid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SalequotationBVO.CQTUNITID, cqtunitid);
  }

  /**
   * �����ջ�����/����
   * 
   * @param crececountryid �ջ�����/����
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SalequotationBVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SalequotationBVO.CROWNO, crowno);
  }

  /**
   * ���÷�������/����
   * 
   * @param csendcountryid ��������/����
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SalequotationBVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SalequotationBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ���ñ�˰����/����
   * 
   * @param ctaxcountryid ��˰����/����
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SalequotationBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  // /**
  // * ����bcloseflag��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newBcloseflag
  // * nc.vo.pub.lang.UFBoolean
  // */
  // public void setBcloseflag(nc.vo.pub.lang.UFBoolean newBcloseflag) {
  // this.setAttributeValue("bcloseflag", newBcloseflag);
  // }

  public void setDr(Integer dr) {
    this.setAttributeValue(SalequotationBVO.DR, dr);
  }

  /**
   * ���ù�������
   * 
   * @param fbuysellflag ��������
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SalequotationBVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SalequotationBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNassistnum(UFDouble nassistnum) {
    this.setAttributeValue(SalequotationBVO.NASSISTNUM, nassistnum);
  }

  // /**
  // * ����fhslcaltypeflag��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newFhslcaltypeflag
  // * java.lang.Integer
  // */
  // public void setFhslcaltypeflag(Integer newFhslcaltypeflag) {
  // this.setAttributeValue("fhslcaltypeflag", newFhslcaltypeflag);
  // }

  public void setNchangerate(String nchangerate) {
    this.setAttributeValue(SalequotationBVO.NCHANGERATE, nchangerate);
  }

  public void setNcontractnum(UFDouble ncontractnum) {
    this.setAttributeValue(SalequotationBVO.NCONTRACTNUM, ncontractnum);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SalequotationBVO.NDISCOUNTRATE, ndiscountrate);
  }

  // /**
  // * ����ndiscount��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNdiscount
  // * java.lang.String
  // */
  // public void setNdiscount(nc.vo.pub.lang.UFDouble newNdiscount) {
  // this.setAttributeValue("ndiscount", newNdiscount);
  // }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SalequotationBVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SalequotationBVO.NNUM, nnum);
  }

  // /**
  // * ����nmny��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNmny
  // * java.lang.String
  // */
  // public void setNmny(nc.vo.pub.lang.UFDouble newNmny) {
  // this.setAttributeValue("nmny", newNmny);
  // }
  //
  // /**
  // * ����nnetprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNnetprice(nc.vo.pub.lang.UFDouble newNnetprice) {
  // this.setAttributeValue("nnetprice", newNnetprice);
  // }

  public void setNordernum(UFDouble nordernum) {
    this.setAttributeValue(SalequotationBVO.NORDERNUM, nordernum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SalequotationBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SalequotationBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SalequotationBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SalequotationBVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SalequotationBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SalequotationBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SalequotationBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNqtchangerate(String nqtchangerate) {
    this.setAttributeValue(SalequotationBVO.NQTCHANGERATE, nqtchangerate);
  }

  public void setNqtnum(UFDouble nqtnum) {
    this.setAttributeValue(SalequotationBVO.NQTNUM, nqtnum);
  }

  // /**
  // * ����nprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNprice(nc.vo.pub.lang.UFDouble newNprice) {
  // this.setAttributeValue("nprice", newNprice);
  // }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(SalequotationBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  // /**
  // * ����nqtnetprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNqtnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqtnetprice(nc.vo.pub.lang.UFDouble newNqtnetprice) {
  // this.setAttributeValue("nqtnetprice", newNqtnetprice);
  // }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SalequotationBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(SalequotationBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SalequotationBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SalequotationBVO.NTAXRATE, ntaxrate);
  }

  // /**
  // * ����nqtprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNqtprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqtprice(nc.vo.pub.lang.UFDouble newNqtprice) {
  // this.setAttributeValue("nqtprice", newNqtprice);
  // }
  //
  // /**
  // * ����nqttaxnetprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNqttaxnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqttaxnetprice(nc.vo.pub.lang.UFDouble newNqttaxnetprice) {
  // this.setAttributeValue("nqttaxnetprice", newNqttaxnetprice);
  // }
  //
  // /**
  // * ����nqttaxprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNqttaxprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqttaxprice(nc.vo.pub.lang.UFDouble newNqttaxprice) {
  // this.setAttributeValue("nqttaxprice", newNqttaxprice);
  // }
  //
  // /**
  // * ����ntax��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNtax
  // * java.lang.String
  // */
  // public void setNtax(nc.vo.pub.lang.UFDouble newNtax) {
  // this.setAttributeValue("ntax", newNtax);
  // }
  //
  // /**
  // * ����ntaxmny��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNtaxmny
  // * java.lang.String
  // */
  // public void setNtaxmny(nc.vo.pub.lang.UFDouble newNtaxmny) {
  // this.setAttributeValue("ntaxmny", newNtaxmny);
  // }
  //
  // /**
  // * ����ntaxnetprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNtaxnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNtaxnetprice(nc.vo.pub.lang.UFDouble newNtaxnetprice) {
  // this.setAttributeValue("ntaxnetprice", newNtaxnetprice);
  // }
  //
  // /**
  // * ����ntaxprice��Setter����. ��������:2009-10-17 12:34:25
  // *
  // * @param newNtaxprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNtaxprice(nc.vo.pub.lang.UFDouble newNtaxprice) {
  // this.setAttributeValue("ntaxprice", newNtaxprice);
  // }

  public void setPk_areacl(String pk_areacl) {
    this.setAttributeValue(SalequotationBVO.PK_AREACL, pk_areacl);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SalequotationBVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(SalequotationBVO.PK_MATERIAL, pk_material);
  }

  public void setPk_material_v(String pk_material_v) {
    this.setAttributeValue(SalequotationBVO.PK_MATERIAL_V, pk_material_v);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SalequotationBVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SalequotationBVO.PK_ORG_V, pk_org_v);
  }

  public void setPk_pricepolicy(String pk_pricepolicy) {
    this.setAttributeValue(SalequotationBVO.PK_PRICEPOLICY, pk_pricepolicy);
  }

  public void setPk_pricetype(String pk_pricetype) {
    this.setAttributeValue(SalequotationBVO.PK_PRICETYPE, pk_pricetype);
  }

  public void setPk_productor(String pk_productor) {
    this.setAttributeValue(SalequotationBVO.PK_PRODUCTOR, pk_productor);
  }

  public void setPk_project(String pk_project) {
    this.setAttributeValue(SalequotationBVO.PK_PROJECT, pk_project);
  }

  public void setPk_qualitylevel(String pk_qualitylevel) {
    this.setAttributeValue(SalequotationBVO.PK_QUALITYLEVEL, pk_qualitylevel);
  }

  public void setPk_salequotation(String pk_salequotation) {
    this.setAttributeValue(SalequotationBVO.PK_SALEQUOTATION, pk_salequotation);
  }

  public void setPk_salequotation_b(String pk_salequotation_b) {
    this.setAttributeValue(SalequotationBVO.PK_SALEQUOTATION_B,
        pk_salequotation_b);
  }

  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SalequotationBVO.PK_SUPPLIER, pk_supplier);
  }

  public void setPk_tariffdef(String pk_tariffdef) {
    this.setAttributeValue(SalequotationBVO.PK_TARIFFDEF, pk_tariffdef);
  }

  public void setPk_unit(String pk_unit) {
    this.setAttributeValue(SalequotationBVO.PK_UNIT, pk_unit);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SalequotationBVO.TS, ts);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SalequotationBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SalequotationBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SalequotationBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SalequotationBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SalequotationBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SalequotationBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SalequotationBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SalequotationBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SalequotationBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SalequotationBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SalequotationBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SalequotationBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SalequotationBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SalequotationBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SalequotationBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SalequotationBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SalequotationBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SalequotationBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SalequotationBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SalequotationBVO.VBDEF9, vbdef9);
  }

  public void setVbnote(String vbnote) {
    this.setAttributeValue(SalequotationBVO.VBNOTE, vbnote);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(SalequotationBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(SalequotationBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(SalequotationBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(SalequotationBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(SalequotationBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(SalequotationBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(SalequotationBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(SalequotationBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(SalequotationBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(SalequotationBVO.VFREE9, vfree9);
  }

  public void setVpricedetail(String vpricedetail) {
    this.setAttributeValue(SalequotationBVO.VPRICEDETAIL, vpricedetail);
  }

  /**
   * ������Դ���ݺ�
   * 
   * @param vsrccode ��Դ���ݺ�
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SalequotationBVO.VSRCCODE, vsrccode);
  }

  /**
   * ������Դ�����к�
   * 
   * @param vsrcrowno ��Դ�����к�
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SalequotationBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctrantype ��Դ��������
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SalequotationBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctype ��Դ��������
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SalequotationBVO.VSRCTYPE, vsrctype);
  }

  /**
   * ����Դͷ���ݺ�
   * 
   * @param vfirstcode Դͷ���ݺ�
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SalequotationBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * ����Դͷ�����к�
   * 
   * @param vfirstrowno Դͷ�����к�
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SalequotationBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttrantype Դͷ��������
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SalequotationBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttype Դͷ��������
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SalequotationBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * ����Դͷ�����ӱ�
   * 
   * @param cfirstbid Դͷ�����ӱ�
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SalequotationBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * ����Դͷ��������
   * 
   * @param cfirstid Դͷ��������
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SalequotationBVO.CFIRSTID, cfirstid);
  }

  /**
   * ������Դ���ݸ���
   * 
   * @param csrcbid ��Դ���ݸ���
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SalequotationBVO.CSRCBID, csrcbid);
  }

  /**
   * ������Դ��������
   * 
   * @param csrcid ��Դ��������
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SalequotationBVO.CSRCID, csrcid);
  }

  /**
   * ������Դʱ���
   * 
   * @param ts ʱ���
   */
  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(SalequotationBVO.SRCBTS, srcbts);
  }

  /**
   * ������Դʱ���
   * 
   * @param ts ʱ���
   */
  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(SalequotationBVO.SRCTS, srcts);
  }

}
