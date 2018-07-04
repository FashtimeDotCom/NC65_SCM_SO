package nc.vo.so.m33.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;

/**
 * ���۷�Ʊ�������嵥��ʵ����
 * 
 * @since 6.0
 * @version 2012-1-5 ����08:50:38
 * @author fengjb
 */
public class SquareInvBVO extends SuperVO {

  private static final long serialVersionUID = -7719302456054625866L;
  
  /**
   * ������
   */
  public static final String CMFFILEID="cmffileid";

  /**
   * ��Ӧ�ս������������ɳ־û�������Ӧ��ʱ�������͵��ۻ���գ��������۷�Ʊ���㲻�ر�)
   */
  private static final String DIFFTOARSQUARENUM = "difftoarsquarenum";

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
    this.setAttributeValue(SquareInvBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCUSTMATERIALID);
  }

  /**
   * ���۷�Ʊ�������嵥�ӱ�ʵ��·��
   */
  public static final String ENTITYNAME = "so.SquareInvoiceBVO";

  /**
   * ɾ����־dr
   * 
   */
  public static final String DR = "dr";

  /**
   * �Ƿ���Գɱ�����
   */
  public static final String BCOST = "bcost";

  /**
   * �ۿ���
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * �Ƿ�����������
   */
  public static final String BINCOME = "bincome";

  /**
   * ������
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * �Ƿ���Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * �Ƿ�Ӧ�ս������
   */
  public static final String BSQUAREARFINISH = "bsquarearfinish";

  /**
   * �Ƿ�ɱ��������
   */
  public static final String BSQUAREIAFINISH = "bsquareiafinish";

  /**
   * Ӧ����֯���°汾
   */
  public static final String CARORGID = "carorgid";

  /**
   * Ӧ����֯�汾
   */
  public static final String CARORGVID = "carorgvid";

  /**
   * ��λ
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * �ɱ���
   */
  public static final String CCOSTORGID = "ccostorgid";

  /**
   * ����
   */
  public static final String CCURRENCYID = "ccurrencyid";

  /**
   * ���۲������°汾
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * ���۲��Ű汾
   */
  public static final String CDEPTVID = "cdeptvid";

  /**
   * ����ҵ��Ա
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * Դͷ�����ӱ�
   */
  public static final String CFIRSTBID = "cfirstbid";

  /**
   * Դͷ��������
   */
  public static final String CFIRSTID = "cfirstid";

  /**
   * ɢ��
   */
  public static final String CFREECUSTID = "cfreecustid";

  /**
   * ����
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * ���ϰ汾
   */
  public static final String CMATERIALVID = "cmaterialvid";

  /**
   * �����ͻ�
   */
  public static final String CORDERCUSTID = "cordercustid";

  /**
   * ԭ��
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * ��������
   */
  public static final String CPRODUCTORID = "cproductorid";

  /**
   * �����������°汾
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * �������İ汾
   */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /**
   * ��ĿID
   */
  public static final String CPROJECTID = "cprojectid";

  /**
   * ��Ʒ��
   */
  public static final String CPROLINEID = "cprolineid";

  /**
   * ������֯���°汾
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * ������֯�汾
   */
  public static final String CSALEORGVID = "csaleorgvid";

  /**
   * ���۷�Ʊ���㵥��ʵ��
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * csalesquaredid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * ���۷�Ʊ�����㵥��ʵ��_����
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * �����֯���°汾
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * �����֯�汾
   */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /**
   * �ֿ�
   */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /**
   * ���۷�Ʊ��ʵ��
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * ���۷�Ʊ��ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ��Դ�����ӱ�
   */
  public static final String CSRCBID = "csrcbid";

  /**
   * ��Դ��������
   */
  public static final String CSRCID = "csrcid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ��Ӧ��
   */
  public static final String CVENDORID = "cvendorid";

  /**
   * ���۷�Ʊ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * Ӧ�յ���ЧЧ����
   */
  public static final String DEFFECTDATE = "deffectdate";

  /**
   * �������������
   */
  public static final String FPREARTYPE = "fpreartype";

  /**
   * ���ɱ���������
   */
  public static final String FPREIATYPE = "fpreiatype";

  /**
   * �ۼƻس�Ӧ������
   */
  public static final String NARRUSHNUM = "narrushnum";

  /**
   * ��λ����
   */
  public static final String NASTNUM = "nastnum";

  /**
   * �����ۿ۶�
   */
  public static final String NDISCOUNT = "ndiscount";

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
   * ��Ʒ�ۿ���
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * ������˰���
   */
  public static final String NMNY = "nmny";

  /**
   * ������˰����
   */
  public static final String NNETPRICE = "nnetprice";

  /**
   * ����λ����
   */
  public static final String NNUM = "nnum";

  /**
   * ԭ���ۿ۶�
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * ԭ����˰���
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * ԭ����˰����
   */
  public static final String NORIGNETPRICE = "norignetprice";

  /**
   * ԭ����˰����
   */
  public static final String NORIGPRICE = "norigprice";

  /**
   * ԭ�Ҽ�˰�ϼ�
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * ԭ�Һ�˰����
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * ԭ�Һ�˰����
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * ������˰����
   */
  public static final String NPRICE = "nprice";

  /**
   * �ۼ�ȷ��Ӧ������
   */
  public static final String NSQUAREARNUM = "nsquarearnum";

  /**
   * �ۼƳɱ���������
   */
  public static final String NSQUAREIANUM = "nsquareianum";

  /**
   * �ۼƷ�����Ʒ����
   */
  public static final String NSQUAREREGNUM = "nsquareregnum";

  /**
   * ����˰��
   */
  public static final String NTAX = "ntax";

  /**
   * ���Ҽ�˰�ϼ�
   */
  public static final String NTAXMNY = "ntaxmny";

  /**
   * ���Һ�˰����
   */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /**
   * ���Һ�˰����
   */
  public static final String NTAXPRICE = "ntaxprice";

  /**
   * ˰��
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * nthisnum���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String NTHISNUM = "nthisnum";

  /**
   * ���κŵ���
   */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * ���������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * processeid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String PROCESSEID = "processeid";

  /**
   * ʱ���
   */
  public static final String TS = "ts";

  /**
   * ���κ�
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
   * ��λ������
   */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * ��ͬ��
   */
  public static final String VCTCODE = "vctcode";

  /**
   * Դͷ���ݺ�
   */
  public static final String VFIRSTCODE = "vfirstcode";

  /**
   * Դͷ�����к�
   */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /**
   * Դͷ���ݽ�������
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
   * ��Դ���ݽ�������
   */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /**
   * ��Դ��������
   */
  public static final String VSRCTYPE = "vsrctype";

  /******* V61�����ֶ� *******/
  /**
   * ˰��
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * ��˰���
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /**
   * ��˰���
   */
  public static final String CCOSTSUBJID = "ccostsubjid";

  /**
   * ��ȡ�Ƿ���Գɱ�����
   * 
   * @return �Ƿ���Գɱ�����
   */
  public UFBoolean getBcost() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BCOST);
  }

  /**
   * �����Ƿ���Գɱ�����
   * 
   * @param bcost �Ƿ���Գɱ�����
   */
  public void setBcost(UFBoolean bcost) {
    this.setAttributeValue(SquareInvBVO.BCOST, bcost);
  }

  /**
   * ��ȡ�ۿ���
   * 
   * @return �ۿ���
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BDISCOUNTFLAG);
  }

  /**
   * �����ۿ���
   * 
   * @param bdiscountflag �ۿ���
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(SquareInvBVO.BDISCOUNTFLAG, bdiscountflag);
  }

  /**
   * ��ȡ�Ƿ�����������
   * 
   * @return �Ƿ�����������
   */
  public UFBoolean getBincome() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BINCOME);
  }

  /**
   * �����Ƿ�����������
   * 
   * @param bincome �Ƿ�����������
   */
  public void setBincome(UFBoolean bincome) {
    this.setAttributeValue(SquareInvBVO.BINCOME, bincome);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BLABORFLAG);
  }

  /**
   * ���÷�����
   * 
   * @param blaborflag ������
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(SquareInvBVO.BLABORFLAG, blaborflag);
  }

  /**
   * ��ȡ�Ƿ���Ʒ
   * 
   * @return �Ƿ���Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BLARGESSFLAG);
  }

  /**
   * �����Ƿ���Ʒ
   * 
   * @param blargessflag �Ƿ���Ʒ
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SquareInvBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * ��ȡ�Ƿ�Ӧ�ս������
   * 
   * @return �Ƿ�Ӧ�ս������
   */
  public UFBoolean getBsquarearfinish() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BSQUAREARFINISH);
  }

  /**
   * �����Ƿ�Ӧ�ս������
   * 
   * @param bsquarearfinish �Ƿ�Ӧ�ս������
   */
  public void setBsquarearfinish(UFBoolean bsquarearfinish) {
    this.setAttributeValue(SquareInvBVO.BSQUAREARFINISH, bsquarearfinish);
  }

  /**
   * ��ȡ�Ƿ�ɱ��������
   * 
   * @return �Ƿ�ɱ��������
   */
  public UFBoolean getBsquareiafinish() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BSQUAREIAFINISH);
  }

  /**
   * �����Ƿ�ɱ��������
   * 
   * @param bsquareiafinish �Ƿ�ɱ��������
   */
  public void setBsquareiafinish(UFBoolean bsquareiafinish) {
    this.setAttributeValue(SquareInvBVO.BSQUAREIAFINISH, bsquareiafinish);
  }

  /**
   * ��ȡӦ����֯���°汾
   * 
   * @return Ӧ����֯���°汾
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CARORGID);
  }

  /**
   * ����Ӧ����֯���°汾
   * 
   * @param carorgid Ӧ����֯���°汾
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SquareInvBVO.CARORGID, carorgid);
  }

  /**
   * ��ȡӦ����֯�汾
   * 
   * @return Ӧ����֯�汾
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CARORGVID);
  }

  /**
   * ����Ӧ����֯�汾
   * 
   * @param carorgvid Ӧ����֯�汾
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SquareInvBVO.CARORGVID, carorgvid);
  }

  /**
   * ��ȡ��λ
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareInvBVO.CASTUNITID);
  }

  /**
   * ���õ�λ
   * 
   * @param castunitid ��λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareInvBVO.CASTUNITID, castunitid);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCHANNELTYPEID);
  }

  /**
   * ���ö�����������
   * 
   * @param cchanneltypeid ������������
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SquareInvBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * ��ȡ�ɱ���
   * 
   * @return �ɱ���
   */
  public String getCcostorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCOSTORGID);
  }

  /**
   * ���óɱ���
   * 
   * @param ccostorgid �ɱ���
   */
  public void setCcostorgid(String ccostorgid) {
    this.setAttributeValue(SquareInvBVO.CCOSTORGID, ccostorgid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param ccurrencyid ����
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareInvBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * ��ȡ���۲������°汾
   * 
   * @return ���۲������°汾
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(SquareInvBVO.CDEPTID);
  }

  /**
   * �������۲������°汾
   * 
   * @param cdeptid ���۲������°汾
   */
  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SquareInvBVO.CDEPTID, cdeptid);
  }

  /**
   * ��ȡ���۲��Ű汾
   * 
   * @return ���۲��Ű汾
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CDEPTVID);
  }

  /**
   * �������۲��Ű汾
   * 
   * @param cdeptvid ���۲��Ű汾
   */
  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SquareInvBVO.CDEPTVID, cdeptvid);
  }

  /**
   * ��ȡ����ҵ��Ա
   * 
   * @return ����ҵ��Ա
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(SquareInvBVO.CEMPLOYEEID);
  }

  /**
   * ��������ҵ��Ա
   * 
   * @param cemployeeid ����ҵ��Ա
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SquareInvBVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * ��ȡԴͷ�����ӱ�
   * 
   * @return Դͷ�����ӱ�
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SquareInvBVO.CFIRSTBID);
  }

  /**
   * ����Դͷ�����ӱ�
   * 
   * @param cfirstbid Դͷ�����ӱ�
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SquareInvBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SquareInvBVO.CFIRSTID);
  }

  /**
   * ����Դͷ��������
   * 
   * @param cfirstid Դͷ��������
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SquareInvBVO.CFIRSTID, cfirstid);
  }

  /**
   * ��ȡɢ��
   * 
   * @return ɢ��
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(SquareInvBVO.CFREECUSTID);
  }

  /**
   * ����ɢ��
   * 
   * @param cfreecustid ɢ��
   */
  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SquareInvBVO.CFREECUSTID, cfreecustid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SquareInvBVO.CMATERIALID);
  }

  /**
   * ��������
   * 
   * @param cmaterialid ����
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SquareInvBVO.CMATERIALID, cmaterialid);
  }

  /**
   * ��ȡ���ϰ汾
   * 
   * @return ���ϰ汾
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CMATERIALVID);
  }

  /**
   * �������ϰ汾
   * 
   * @param cmaterialvid ���ϰ汾
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SquareInvBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * ��ȡ�����ͻ�
   * 
   * @return �����ͻ�
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(SquareInvBVO.CORDERCUSTID);
  }

  /**
   * ���ö����ͻ�
   * 
   * @param cordercustid �����ͻ�
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SquareInvBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * ��ȡԭ��
   * 
   * @return ԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareInvBVO.CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyid ԭ��
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareInvBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPRODUCTORID);
  }

  /**
   * ������������
   * 
   * @param cproductorid ��������
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SquareInvBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * ��ȡ�����������°汾
   * 
   * @return �����������°汾
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROFITCENTERID);
  }

  /**
   * ���������������°汾
   * 
   * @param cprofitcenterid �����������°汾
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SquareInvBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * ��ȡ�������İ汾
   * 
   * @return �������İ汾
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROFITCENTERVID);
  }

  /**
   * �����������İ汾
   * 
   * @param cprofitcentervid �������İ汾
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SquareInvBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * ��ȡ��ĿID
   * 
   * @return ��ĿID
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROJECTID);
  }

  /**
   * ������ĿID
   * 
   * @param cprojectid ��ĿID
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SquareInvBVO.CPROJECTID, cprojectid);
  }

  /**
   * ��ȡ��Ʒ��
   * 
   * @return ��Ʒ��
   */
  public String getCprolineid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROLINEID);
  }

  /**
   * ���ò�Ʒ��
   * 
   * @param cprolineid ��Ʒ��
   */
  public void setCprolineid(String cprolineid) {
    this.setAttributeValue(SquareInvBVO.CPROLINEID, cprolineid);
  }

  /**
   * ��ȡ������֯���°汾
   * 
   * @return ������֯���°汾
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALEORGID);
  }

  /**
   * ����������֯���°汾
   * 
   * @param csaleorgid ������֯���°汾
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SquareInvBVO.CSALEORGID, csaleorgid);
  }

  /**
   * ��ȡ������֯�汾
   * 
   * @return ������֯�汾
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALEORGVID);
  }

  /**
   * ����������֯�汾
   * 
   * @param csaleorgvid ������֯�汾
   */
  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SquareInvBVO.CSALEORGVID, csaleorgvid);
  }

  /**
   * ��ȡ���۷�Ʊ���㵥��ʵ��
   * 
   * @return ���۷�Ʊ���㵥��ʵ��
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALESQUAREBID);
  }

  /**
   * �������۷�Ʊ���㵥��ʵ��
   * 
   * @param csalesquarebid ���۷�Ʊ���㵥��ʵ��
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareInvBVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * ��ȡcsalesquaredid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return csalesquaredid
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALESQUAREDID);
  }

  /**
   * ����csalesquaredid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param csalesquaredid csalesquaredid
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareInvBVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * ��ȡ���۷�Ʊ�����㵥��ʵ��_����
   * 
   * @return ���۷�Ʊ�����㵥��ʵ��_����
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALESQUAREID);
  }

  /**
   * �������۷�Ʊ�����㵥��ʵ��_����
   * 
   * @param csalesquareid ���۷�Ʊ�����㵥��ʵ��_����
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareInvBVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ�����֯���°汾
   * 
   * @return �����֯���°汾
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSENDSTOCKORGID);
  }

  /**
   * ���ÿ����֯���°汾
   * 
   * @param csendstockorgid �����֯���°汾
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SquareInvBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * ��ȡ�����֯�汾
   * 
   * @return �����֯�汾
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSENDSTOCKORGVID);
  }

  /**
   * ���ÿ����֯�汾
   * 
   * @param csendstockorgvid �����֯�汾
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SquareInvBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * ��ȡ�ֿ�
   * 
   * @return �ֿ�
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSENDSTORDOCID);
  }

  /**
   * ���òֿ�
   * 
   * @param csendstordocid �ֿ�
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SquareInvBVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * ��ȡ���۷�Ʊ��ʵ��
   * 
   * @return ���۷�Ʊ��ʵ��
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSQUAREBILLBID);
  }

  /**
   * �������۷�Ʊ��ʵ��
   * 
   * @param csquarebillbid ���۷�Ʊ��ʵ��
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareInvBVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * ��ȡ���۷�Ʊ��ʵ��
   * 
   * @return ���۷�Ʊ��ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSQUAREBILLID);
  }

  /**
   * �������۷�Ʊ��ʵ��
   * 
   * @param csquarebillid ���۷�Ʊ��ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareInvBVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ��Դ�����ӱ�
   * 
   * @return ��Դ�����ӱ�
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSRCBID);
  }

  /**
   * ������Դ�����ӱ�
   * 
   * @param csrcbid ��Դ�����ӱ�
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SquareInvBVO.CSRCBID, csrcbid);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSRCID);
  }

  /**
   * ������Դ��������
   * 
   * @param csrcid ��Դ��������
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SquareInvBVO.CSRCID, csrcid);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareInvBVO.CTAXCODEID);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareInvBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareInvBVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid ����λ
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareInvBVO.CUNITID, cunitid);
  }

  /**
   * ��ȡ��Ӧ��
   * 
   * @return ��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SquareInvBVO.CVENDORID);
  }

  /**
   * ���ù�Ӧ��
   * 
   * @param cvendorid ��Ӧ��
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SquareInvBVO.CVENDORID, cvendorid);
  }

  /**
   * ��ȡ���۷�Ʊ��������
   * 
   * @return ���۷�Ʊ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareInvBVO.DBILLDATE);
  }

  /**
   * �������۷�Ʊ��������
   * 
   * @param dbilldate ���۷�Ʊ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareInvBVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡӦ�յ���ЧЧ����
   * 
   * @return Ӧ�յ���ЧЧ����
   */
  public UFDate getDeffectdate() {
    return (UFDate) this.getAttributeValue(SquareInvBVO.DEFFECTDATE);
  }

  /**
   * ����Ӧ�յ���ЧЧ����
   * 
   * @param deffectdate Ӧ�յ���ЧЧ����
   */
  public void setDeffectdate(UFDate deffectdate) {
    this.setAttributeValue(SquareInvBVO.DEFFECTDATE, deffectdate);
  }

  /**
   * ��ȡ�������������
   * 
   * @return �������������
   * @see SquareType
   */
  public Integer getFpreartype() {
    return (Integer) this.getAttributeValue(SquareInvBVO.FPREARTYPE);
  }

  /**
   * ���ô������������
   * 
   * @param fpreartype �������������
   * @see SquareType
   */
  public void setFpreartype(Integer fpreartype) {
    this.setAttributeValue(SquareInvBVO.FPREARTYPE, fpreartype);
  }

  /**
   * ��ȡ���ɱ���������
   * 
   * @return ���ɱ���������
   * @see SquareType
   */
  public Integer getFpreiatype() {
    return (Integer) this.getAttributeValue(SquareInvBVO.FPREIATYPE);
  }

  /**
   * ���ô��ɱ���������
   * 
   * @param fpreiatype ���ɱ���������
   * @see SquareType
   */
  public void setFpreiatype(Integer fpreiatype) {
    this.setAttributeValue(SquareInvBVO.FPREIATYPE, fpreiatype);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareInvBVO.FTAXTYPEFLAG);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareInvBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * ��ȡ�ۼƻس�Ӧ������
   * 
   * @return �ۼƻس�Ӧ������
   */
  public UFDouble getNarrushnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NARRUSHNUM);
  }

  /**
   * �����ۼƻس�Ӧ������
   * 
   * @param narrushnum �ۼƻس�Ӧ������
   */
  public void setNarrushnum(UFDouble narrushnum) {
    this.setAttributeValue(SquareInvBVO.NARRUSHNUM, narrushnum);
  }

  /**
   * ��ȡ��λ����
   * 
   * @return ��λ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NASTNUM);
  }

  /**
   * ���õ�λ����
   * 
   * @param nastnum ��λ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareInvBVO.NASTNUM, nastnum);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NCALTAXMNY);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareInvBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * ��ȡ�����ۿ۶�
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NDISCOUNT);
  }

  /**
   * ���ñ����ۿ۶�
   * 
   * @param ndiscount �����ۿ۶�
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareInvBVO.NDISCOUNT, ndiscount);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NEXCHANGERATE);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareInvBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * ��ȡȫ�ֱ�λ�һ���
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGLOBALEXCHGRATE);
  }

  /**
   * ����ȫ�ֱ�λ�һ���
   * 
   * @param nglobalexchgrate ȫ�ֱ�λ�һ���
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareInvBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * ��ȡȫ�ֱ�����˰���
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGLOBALMNY);
  }

  /**
   * ����ȫ�ֱ�����˰���
   * 
   * @param nglobalmny ȫ�ֱ�����˰���
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareInvBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * ��ȡȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @return ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGLOBALTAXMNY);
  }

  /**
   * ����ȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @param nglobaltaxmny ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareInvBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * ��ȡ���ű�λ�һ���
   * 
   * @return ���ű�λ�һ���
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGROUPEXCHGRATE);
  }

  /**
   * ���ü��ű�λ�һ���
   * 
   * @param ngroupexchgrate ���ű�λ�һ���
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareInvBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * ��ȡ���ű�����˰���
   * 
   * @return ���ű�����˰���
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGROUPMNY);
  }

  /**
   * ���ü��ű�����˰���
   * 
   * @param ngroupmny ���ű�����˰���
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareInvBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * ��ȡ���ű��Ҽ�˰�ϼ�
   * 
   * @return ���ű��Ҽ�˰�ϼ�
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGROUPTAXMNY);
  }

  /**
   * ���ü��ű��Ҽ�˰�ϼ�
   * 
   * @param ngrouptaxmny ���ű��Ҽ�˰�ϼ�
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareInvBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * ��ȡ��Ʒ�ۿ���
   * 
   * @return ��Ʒ�ۿ���
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NITEMDISCOUNTRATE);
  }

  /**
   * ���õ�Ʒ�ۿ���
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ���
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareInvBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * ��ȡ������˰���
   * 
   * @return ������˰���
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NMNY);
  }

  /**
   * ���ñ�����˰���
   * 
   * @param nmny ������˰���
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareInvBVO.NMNY, nmny);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NNETPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nnetprice ������˰����
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareInvBVO.NNETPRICE, nnetprice);
  }

  /**
   * ��ȡ����λ����
   * 
   * @return ����λ����
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NNUM);
  }

  /**
   * ��������λ����
   * 
   * @param nnum ����λ����
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareInvBVO.NNUM, nnum);
  }

  /**
   * ��ȡԭ���ۿ۶�
   * 
   * @return ԭ���ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGDISCOUNT);
  }

  /**
   * ����ԭ���ۿ۶�
   * 
   * @param norigdiscount ԭ���ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareInvBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * ��ȡԭ����˰���
   * 
   * @return ԭ����˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGMNY);
  }

  /**
   * ����ԭ����˰���
   * 
   * @param norigmny ԭ����˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareInvBVO.NORIGMNY, norigmny);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGNETPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norignetprice ԭ����˰����
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareInvBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norigprice ԭ����˰����
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareInvBVO.NORIGPRICE, norigprice);
  }

  /**
   * ��ȡԭ�Ҽ�˰�ϼ�
   * 
   * @return ԭ�Ҽ�˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGTAXMNY);
  }

  /**
   * ����ԭ�Ҽ�˰�ϼ�
   * 
   * @param norigtaxmny ԭ�Ҽ�˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareInvBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGTAXNETPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxnetprice ԭ�Һ�˰����
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareInvBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGTAXPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxprice ԭ�Һ�˰����
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareInvBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nprice ������˰����
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareInvBVO.NPRICE, nprice);
  }

  /**
   * ��ȡ�ۼ�ȷ��Ӧ������
   * 
   * @return �ۼ�ȷ��Ӧ������
   */
  public UFDouble getNsquarearnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NSQUAREARNUM);
  }

  /**
   * �����ۼ�ȷ��Ӧ������
   * 
   * @param nsquarearnum �ۼ�ȷ��Ӧ������
   */
  public void setNsquarearnum(UFDouble nsquarearnum) {
    this.setAttributeValue(SquareInvBVO.NSQUAREARNUM, nsquarearnum);
  }

  /**
   * ��ȡ�ۼƳɱ���������
   * 
   * @return �ۼƳɱ���������
   */
  public UFDouble getNsquareianum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NSQUAREIANUM);
  }

  /**
   * �����ۼƳɱ���������
   * 
   * @param nsquareianum �ۼƳɱ���������
   */
  public void setNsquareianum(UFDouble nsquareianum) {
    this.setAttributeValue(SquareInvBVO.NSQUAREIANUM, nsquareianum);
  }

  /**
   * ��ȡ�ۼƷ�����Ʒ����
   * 
   * @return �ۼƷ�����Ʒ����
   */
  public UFDouble getNsquareregnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NSQUAREREGNUM);
  }

  /**
   * �����ۼƷ�����Ʒ����
   * 
   * @param nsquareregnum �ۼƷ�����Ʒ����
   */
  public void setNsquareregnum(UFDouble nsquareregnum) {
    this.setAttributeValue(SquareInvBVO.NSQUAREREGNUM, nsquareregnum);
  }

  /**
   * ��ȡ����˰��
   * 
   * @return ����˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAX);
  }

  /**
   * ���ñ���˰��
   * 
   * @param ntax ����˰��
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareInvBVO.NTAX, ntax);
  }

  /**
   * ��ȡ���Ҽ�˰�ϼ�
   * 
   * @return ���Ҽ�˰�ϼ�
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXMNY);
  }

  /**
   * ���ñ��Ҽ�˰�ϼ�
   * 
   * @param ntaxmny ���Ҽ�˰�ϼ�
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareInvBVO.NTAXMNY, ntaxmny);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXNETPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxnetprice ���Һ�˰����
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareInvBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxprice ���Һ�˰����
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareInvBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXRATE);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareInvBVO.NTAXRATE, ntaxrate);
  }

  /**
   * ��ȡnthisnum���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return nthisnum
   */
  public UFDouble getNthisnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTHISNUM);
  }

  /**
   * ����nthisnum���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param nthisnum nthisnum
   */
  public void setNthisnum(UFDouble nthisnum) {
    this.setAttributeValue(SquareInvBVO.NTHISNUM, nthisnum);
  }

  /**
   * ��ȡ���κŵ���
   * 
   * @return ���κŵ���
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SquareInvBVO.PK_BATCHCODE);
  }

  /**
   * �������κŵ���
   * 
   * @param pk_batchcode ���κŵ���
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SquareInvBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareInvBVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareInvBVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareInvBVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareInvBVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡprocesseid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return processeid
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareInvBVO.PROCESSEID);
  }

  /**
   * ����processeid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param processeid processeid
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareInvBVO.PROCESSEID, processeid);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareInvBVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareInvBVO.TS, ts);
  }

  /**
   * ��ȡ���κ�
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SquareInvBVO.VBATCHCODE);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode ���κ�
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SquareInvBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return �Զ�����1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF1);
  }

  /**
   * �����Զ�����1
   * 
   * @param vbdef1 �Զ�����1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SquareInvBVO.VBDEF1, vbdef1);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return �Զ�����10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF10);
  }

  /**
   * �����Զ�����10
   * 
   * @param vbdef10 �Զ�����10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SquareInvBVO.VBDEF10, vbdef10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return �Զ�����11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF11);
  }

  /**
   * �����Զ�����11
   * 
   * @param vbdef11 �Զ�����11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SquareInvBVO.VBDEF11, vbdef11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return �Զ�����12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF12);
  }

  /**
   * �����Զ�����12
   * 
   * @param vbdef12 �Զ�����12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SquareInvBVO.VBDEF12, vbdef12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return �Զ�����13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF13);
  }

  /**
   * �����Զ�����13
   * 
   * @param vbdef13 �Զ�����13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SquareInvBVO.VBDEF13, vbdef13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return �Զ�����14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF14);
  }

  /**
   * �����Զ�����14
   * 
   * @param vbdef14 �Զ�����14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SquareInvBVO.VBDEF14, vbdef14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return �Զ�����15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF15);
  }

  /**
   * �����Զ�����15
   * 
   * @param vbdef15 �Զ�����15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SquareInvBVO.VBDEF15, vbdef15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return �Զ�����16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF16);
  }

  /**
   * �����Զ�����16
   * 
   * @param vbdef16 �Զ�����16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SquareInvBVO.VBDEF16, vbdef16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return �Զ�����17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF17);
  }

  /**
   * �����Զ�����17
   * 
   * @param vbdef17 �Զ�����17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SquareInvBVO.VBDEF17, vbdef17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return �Զ�����18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF18);
  }

  /**
   * �����Զ�����18
   * 
   * @param vbdef18 �Զ�����18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SquareInvBVO.VBDEF18, vbdef18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return �Զ�����19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF19);
  }

  /**
   * �����Զ�����19
   * 
   * @param vbdef19 �Զ�����19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SquareInvBVO.VBDEF19, vbdef19);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return �Զ�����2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF2);
  }

  /**
   * �����Զ�����2
   * 
   * @param vbdef2 �Զ�����2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SquareInvBVO.VBDEF2, vbdef2);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return �Զ�����20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF20);
  }

  /**
   * �����Զ�����20
   * 
   * @param vbdef20 �Զ�����20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SquareInvBVO.VBDEF20, vbdef20);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return �Զ�����3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF3);
  }

  /**
   * �����Զ�����3
   * 
   * @param vbdef3 �Զ�����3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SquareInvBVO.VBDEF3, vbdef3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return �Զ�����4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF4);
  }

  /**
   * �����Զ�����4
   * 
   * @param vbdef4 �Զ�����4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SquareInvBVO.VBDEF4, vbdef4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return �Զ�����5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF5);
  }

  /**
   * �����Զ�����5
   * 
   * @param vbdef5 �Զ�����5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SquareInvBVO.VBDEF5, vbdef5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return �Զ�����6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF6);
  }

  /**
   * �����Զ�����6
   * 
   * @param vbdef6 �Զ�����6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SquareInvBVO.VBDEF6, vbdef6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return �Զ�����7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF7);
  }

  /**
   * �����Զ�����7
   * 
   * @param vbdef7 �Զ�����7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SquareInvBVO.VBDEF7, vbdef7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return �Զ�����8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF8);
  }

  /**
   * �����Զ�����8
   * 
   * @param vbdef8 �Զ�����8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SquareInvBVO.VBDEF8, vbdef8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return �Զ�����9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF9);
  }

  /**
   * �����Զ�����9
   * 
   * @param vbdef9 �Զ�����9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SquareInvBVO.VBDEF9, vbdef9);
  }

  /**
   * ��ȡ��λ������
   * 
   * @return ��λ������
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareInvBVO.VCHANGERATE);
  }

  /**
   * ���õ�λ������
   * 
   * @param vchangerate ��λ������
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareInvBVO.VCHANGERATE, vchangerate);
  }

  /**
   * ��ȡ��ͬ��
   * 
   * @return ��ͬ��
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SquareInvBVO.VCTCODE);
  }

  /**
   * ���ú�ͬ��
   * 
   * @param vctcode ��ͬ��
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SquareInvBVO.VCTCODE, vctcode);
  }

  /**
   * ��ȡԴͷ���ݺ�
   * 
   * @return Դͷ���ݺ�
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTCODE);
  }

  /**
   * ����Դͷ���ݺ�
   * 
   * @param vfirstcode Դͷ���ݺ�
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SquareInvBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * ��ȡԴͷ�����к�
   * 
   * @return Դͷ�����к�
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTROWNO);
  }

  /**
   * ����Դͷ�����к�
   * 
   * @param vfirstrowno Դͷ�����к�
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SquareInvBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * ��ȡԴͷ���ݽ�������
   * 
   * @return Դͷ���ݽ�������
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTTRANTYPE);
  }

  /**
   * ����Դͷ���ݽ�������
   * 
   * @param vfirsttrantype Դͷ���ݽ�������
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SquareInvBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTTYPE);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttype Դͷ��������
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SquareInvBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * ��ȡ���ɸ�������1
   * 
   * @return ���ɸ�������1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE1);
  }

  /**
   * �������ɸ�������1
   * 
   * @param vfree1 ���ɸ�������1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SquareInvBVO.VFREE1, vfree1);
  }

  /**
   * ��ȡ���ɸ�������10
   * 
   * @return ���ɸ�������10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE10);
  }

  /**
   * �������ɸ�������10
   * 
   * @param vfree10 ���ɸ�������10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SquareInvBVO.VFREE10, vfree10);
  }

  /**
   * ��ȡ���ɸ�������2
   * 
   * @return ���ɸ�������2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE2);
  }

  /**
   * �������ɸ�������2
   * 
   * @param vfree2 ���ɸ�������2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SquareInvBVO.VFREE2, vfree2);
  }

  /**
   * ��ȡ���ɸ�������3
   * 
   * @return ���ɸ�������3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE3);
  }

  /**
   * �������ɸ�������3
   * 
   * @param vfree3 ���ɸ�������3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SquareInvBVO.VFREE3, vfree3);
  }

  /**
   * ��ȡ���ɸ�������4
   * 
   * @return ���ɸ�������4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE4);
  }

  /**
   * �������ɸ�������4
   * 
   * @param vfree4 ���ɸ�������4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SquareInvBVO.VFREE4, vfree4);
  }

  /**
   * ��ȡ���ɸ�������5
   * 
   * @return ���ɸ�������5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE5);
  }

  /**
   * �������ɸ�������5
   * 
   * @param vfree5 ���ɸ�������5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SquareInvBVO.VFREE5, vfree5);
  }

  /**
   * ��ȡ���ɸ�������6
   * 
   * @return ���ɸ�������6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE6);
  }

  /**
   * �������ɸ�������6
   * 
   * @param vfree6 ���ɸ�������6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SquareInvBVO.VFREE6, vfree6);
  }

  /**
   * ��ȡ���ɸ�������7
   * 
   * @return ���ɸ�������7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE7);
  }

  /**
   * �������ɸ�������7
   * 
   * @param vfree7 ���ɸ�������7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SquareInvBVO.VFREE7, vfree7);
  }

  /**
   * ��ȡ���ɸ�������8
   * 
   * @return ���ɸ�������8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE8);
  }

  /**
   * �������ɸ�������8
   * 
   * @param vfree8 ���ɸ�������8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SquareInvBVO.VFREE8, vfree8);
  }

  /**
   * ��ȡ���ɸ�������9
   * 
   * @return ���ɸ�������9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE9);
  }

  /**
   * �������ɸ�������9
   * 
   * @param vfree9 ���ɸ�������9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SquareInvBVO.VFREE9, vfree9);
  }

  /**
   * ��ȡ�б�ע
   * 
   * @return �б�ע
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SquareInvBVO.VROWNOTE);
  }

  /**
   * �����б�ע
   * 
   * @param vrownote �б�ע
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SquareInvBVO.VROWNOTE, vrownote);
  }

  /**
   * ��ȡ��Դ���ݺ�
   * 
   * @return ��Դ���ݺ�
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCCODE);
  }

  /**
   * ������Դ���ݺ�
   * 
   * @param vsrccode ��Դ���ݺ�
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SquareInvBVO.VSRCCODE, vsrccode);
  }

  /**
   * ��ȡ��Դ�����к�
   * 
   * @return ��Դ�����к�
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCROWNO);
  }

  /**
   * ������Դ�����к�
   * 
   * @param vsrcrowno ��Դ�����к�
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SquareInvBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * ��ȡ��Դ���ݽ�������
   * 
   * @return ��Դ���ݽ�������
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCTRANTYPE);
  }

  /**
   * ������Դ���ݽ�������
   * 
   * @param vsrctrantype ��Դ���ݽ�������
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SquareInvBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCTYPE);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctype ��Դ��������
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SquareInvBVO.VSRCTYPE, vsrctype);
  }

  /**
   * ��ȡ��֧��Ŀ
   * 
   * @return ��֧��Ŀ
   */
  public String getCcostsubjid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCOSTSUBJID);
  }

  /**
   * ������֧��Ŀ
   * 
   * @param ccostsubjid ��֧��Ŀ
   */
  public void setCcostsubjid(String ccostsubjid) {
    this.setAttributeValue(SquareInvBVO.CCOSTSUBJID, ccostsubjid);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareInvBVO.ENTITYNAME);
    return meta;
  }

  /**
   * 
   * @param dr
   */
  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  /**
   * 
   * @return Integer
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }

  /**
   * ��Ӧ�ս������������ɳ־û�������Ӧ��ʱ�������͵��ۻ���գ��������۷�Ʊ���㲻�ر�)
   * 
   * @return ��Ӧ�ս������������ɳ־û���
   */
  public UFDouble getDifftoarsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.DIFFTOARSQUARENUM);
  }

  /**
   * ��Ӧ�ս������������ɳ־û�������Ӧ��ʱ�������͵��ۻ���գ��������۷�Ʊ���㲻�ر�)
   * 
   * @param difftoarsquarenum ��Ӧ�ս������������ɳ־û���
   */
  public void setDifftoarsquarenum(UFDouble difftoarsquarenum) {
    this.setAttributeValue(SquareInvBVO.DIFFTOARSQUARENUM, difftoarsquarenum);
  }
  
  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SquareInvBVO.CMFFILEID);
  }
  
  /**
   * ����������
   * 
   * @param ������
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SquareInvBVO.CMFFILEID, cmffileid);
  }
  
  /**
   * ������������
   */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";

  /**
   * �����������İ汾
   */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSPROFITCENTERVID);
  }

  /**
   * ���÷�����������
   * 
   * @param csprofitcentervid ������������
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SquareInvBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * ��ȡ�����������İ汾
   * 
   * @return �����������İ汾
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSPROFITCENTERID);
  }

  /**
   * ���÷����������İ汾
   * 
   * @param csprofitcenterid �����������İ汾
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SquareInvBVO.CSPROFITCENTERID, csprofitcenterid);
  }
}
