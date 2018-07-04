package nc.vo.so.m33.m4c.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;

/**
 * ���۳����������ʵ��
 *
 */
public class SquareOutBVO extends SuperVO {
  
  private static final long serialVersionUID = -7719302456054625866L;
  
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
    this.setAttributeValue(SquareOutBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * ��ȡ�ͻ����ϱ���
   * 
   * @return �ͻ����ϱ���
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCUSTMATERIALID);
  }

  // ���۳��ⵥ�������嵥��ʵ��
  public static final String ENTITYNAME = "so.SquareOutBVO";

  // ɾ����־dr
  public static final String DR = "dr";

  // Ԫ����·��
  public static final String MAINMETAPATH = "csalesquarebid.";

  // ntotalsquarenum ģ����ʹ��
  public static final String NTOTALSQUARENUM = "ntotalsquarenum";

  /**
   * ���۳��ⵥ�����㵥voID(��������ʱ�ã�Ԫ������û��)
   * ������vo����ϸvo�Ķ�Ӧ��ϵ����Ϊ���ܴ�����vo���1�в�ɶ���SquareOutDetailVO
   * �����޷�����id��Ӧ
   */
  public static final String CSQUAREOUTBVOID = "csquareoutbvoid";

  /**
   * �Ƿ���Գɱ�����
   */
  public static final String BCOST = "bcost";

  /**
   * �Ƿ�����������
   */
  public static final String BINCOME = "bincome";

  /**
   * �Ƿ���Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * boutrushflag���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String BOUTRUSHFLAG = "boutrushflag";

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
   * ���������˻�
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";

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
   * ������Ʊ�ͻ�
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

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
   * �����ո���Э��
   */
  public static final String CPAYTERMID = "cpaytermid";

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
   * ��ĿID
   */
  public static final String CPROJECTID = "cprojectid";

  /**
   * ��Ʒ��
   */
  public static final String CPROLINEID = "cprolineid";

  /**
   * crushoutbatchid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String CRUSHOUTBATCHID = "crushoutbatchid";

  /**
   * ������֯
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * ������֯�汾
   */
  public static final String CSALEORGVID = "csaleorgvid";

  /**
   * ���۳�����㵥��ʵ��
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * csalesquaredid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * ���۳�������㵥��ʵ��_����
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * ���۳��ⵥ��ʵ��
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * ���۳��ⵥ��ʵ��
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
   * ���۳��ⵥ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ���۳��ⵥҵ������
   */
  public static final String DBIZDATE = "dbizdate";

  /**
   * Ӧ�յ���Ч����
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
   * �ۼ����η�Ʊȷ��Ӧ�ս��
   */
  public static final String NDOWNARMNY = "ndownarmny";

  /**
   * �ۼ����η�Ʊȷ��Ӧ������
   */
  public static final String NDOWNARNUM = "ndownarnum";

  /**
   * �ۼ����η�Ʊ�ɱ���������
   */
  public static final String NDOWNIANUM = "ndownianum";

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
   * �ۼƳ���Գ�����
   */
  public static final String NRUSHNUM = "nrushnum";

  /**
   * �ۼ�ȷ��Ӧ�ս��
   */
  public static final String NSQUAREARMNY = "nsquarearmny";

  /**
   * �ۼ�ȷ��Ӧ������
   */
  public static final String NSQUAREARNUM = "nsquarearnum";

  /**
   * �ۼ��ݹ�Ӧ������
   */
  public static final String NSQUAREESTNUM = "nsquareestnum";

  /**
   * �ۼƳɱ���������
   */
  public static final String NSQUAREIANUM = "nsquareianum";

  /**
   * �ۼƷ�����Ʒ����
   */
  public static final String NSQUAREREGNUM = "nsquareregnum";

  /**
   * ˰��
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
   * �ۼƳ��⼰���η�Ʊ�տ�������
   */
  public static final String NTOTALPAYMNY = "ntotalpaymny";

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
   * �����Զ�����1
   */
  public static final String VBDEF1 = "vbdef1";

  /**
   * �����Զ�����10
   */
  public static final String VBDEF10 = "vbdef10";

  /**
   * �����Զ�����11
   */
  public static final String VBDEF11 = "vbdef11";

  /**
   * �����Զ�����12
   */
  public static final String VBDEF12 = "vbdef12";

  /**
   * �����Զ�����13
   */
  public static final String VBDEF13 = "vbdef13";

  /**
   * �����Զ�����14
   */
  public static final String VBDEF14 = "vbdef14";

  /**
   * �����Զ�����15
   */
  public static final String VBDEF15 = "vbdef15";

  /**
   * �����Զ�����16
   */
  public static final String VBDEF16 = "vbdef16";

  /**
   * �����Զ�����17
   */
  public static final String VBDEF17 = "vbdef17";

  /**
   * �����Զ�����18
   */
  public static final String VBDEF18 = "vbdef18";

  /**
   * �����Զ�����19
   */
  public static final String VBDEF19 = "vbdef19";

  /**
   * �����Զ�����2
   */
  public static final String VBDEF2 = "vbdef2";

  /**
   * �����Զ�����20
   */
  public static final String VBDEF20 = "vbdef20";

  /**
   * �����Զ�����3
   */
  public static final String VBDEF3 = "vbdef3";

  /**
   * �����Զ�����4
   */
  public static final String VBDEF4 = "vbdef4";

  /**
   * �����Զ�����5
   */
  public static final String VBDEF5 = "vbdef5";

  /**
   * �����Զ�����6
   */
  public static final String VBDEF6 = "vbdef6";

  /**
   * �����Զ�����7
   */
  public static final String VBDEF7 = "vbdef7";

  /**
   * �����Զ�����8
   */
  public static final String VBDEF8 = "vbdef8";

  /**
   * �����Զ�����9
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
    return (String) this.getAttributeValue(SquareOutBVO.CSPROFITCENTERVID);
  }

  /**
   * ���÷�����������
   * 
   * @param csprofitcentervid ������������
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SquareOutBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * ��ȡ�����������İ汾
   * 
   * @return �����������İ汾
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSPROFITCENTERID);
  }

  /**
   * ���÷����������İ汾
   * 
   * @param csprofitcenterid �����������İ汾
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SquareOutBVO.CSPROFITCENTERID, csprofitcenterid);
  }

  /**
   * ��ȡ�Ƿ���Գɱ�����
   * 
   * @return �Ƿ���Գɱ�����
   */
  public UFBoolean getBcost() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BCOST);
  }

  /**
   * �����Ƿ���Գɱ�����
   * 
   * @param bcost �Ƿ���Գɱ�����
   */
  public void setBcost(UFBoolean bcost) {
    this.setAttributeValue(SquareOutBVO.BCOST, bcost);
  }

  /**
   * ��ȡ�Ƿ�����������
   * 
   * @return �Ƿ�����������
   */
  public UFBoolean getBincome() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BINCOME);
  }

  /**
   * �����Ƿ�����������
   * 
   * @param bincome �Ƿ�����������
   */
  public void setBincome(UFBoolean bincome) {
    this.setAttributeValue(SquareOutBVO.BINCOME, bincome);
  }

  /**
   * ��ȡ�Ƿ���Ʒ
   * 
   * @return �Ƿ���Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BLARGESSFLAG);
  }

  /**
   * �����Ƿ���Ʒ
   * 
   * @param blargessflag �Ƿ���Ʒ
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SquareOutBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * ��ȡboutrushflag���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return boutrushflag
   */
  public UFBoolean getBoutrushflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BOUTRUSHFLAG);
  }

  /**
   * ����boutrushflag���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param boutrushflag boutrushflag
   */
  public void setBoutrushflag(UFBoolean boutrushflag) {
    this.setAttributeValue(SquareOutBVO.BOUTRUSHFLAG, boutrushflag);
  }

  /**
   * ��ȡ�Ƿ�Ӧ�ս������
   * 
   * @return �Ƿ�Ӧ�ս������
   */
  public UFBoolean getBsquarearfinish() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BSQUAREARFINISH);
  }

  /**
   * �����Ƿ�Ӧ�ս������
   * 
   * @param bsquarearfinish �Ƿ�Ӧ�ս������
   */
  public void setBsquarearfinish(UFBoolean bsquarearfinish) {
    this.setAttributeValue(SquareOutBVO.BSQUAREARFINISH, bsquarearfinish);
  }

  /**
   * ��ȡ�Ƿ�ɱ��������
   * 
   * @return �Ƿ�ɱ��������
   */
  public UFBoolean getBsquareiafinish() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BSQUAREIAFINISH);
  }

  /**
   * �����Ƿ�ɱ��������
   * 
   * @param bsquareiafinish �Ƿ�ɱ��������
   */
  public void setBsquareiafinish(UFBoolean bsquareiafinish) {
    this.setAttributeValue(SquareOutBVO.BSQUAREIAFINISH, bsquareiafinish);
  }

  /**
   * ��ȡӦ����֯���°汾
   * 
   * @return Ӧ����֯���°汾
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SquareOutBVO.CARORGID);
  }

  /**
   * ����Ӧ����֯���°汾
   * 
   * @param carorgid Ӧ����֯���°汾
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SquareOutBVO.CARORGID, carorgid);
  }

  /**
   * ��ȡӦ����֯�汾
   * 
   * @return Ӧ����֯�汾
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CARORGVID);
  }

  /**
   * ����Ӧ����֯�汾
   * 
   * @param carorgvid Ӧ����֯�汾
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SquareOutBVO.CARORGVID, carorgvid);
  }

  /**
   * ��ȡ��λ
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareOutBVO.CASTUNITID);
  }

  /**
   * ���õ�λ
   * 
   * @param castunitid ��λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareOutBVO.CASTUNITID, castunitid);
  }

  /**
   * ��ȡ������������
   * 
   * @return ������������
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCHANNELTYPEID);
  }

  /**
   * ���ö�����������
   * 
   * @param cchanneltypeid ������������
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SquareOutBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * ��ȡ�ɱ���
   * 
   * @return �ɱ���
   */
  public String getCcostorgid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCOSTORGID);
  }

  /**
   * ���óɱ���
   * 
   * @param ccostorgid �ɱ���
   */
  public void setCcostorgid(String ccostorgid) {
    this.setAttributeValue(SquareOutBVO.CCOSTORGID, ccostorgid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param ccurrencyid ����
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareOutBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * ��ȡ���������˻�
   * 
   * @return ���������˻�
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCUSTBANKACCID);
  }

  /**
   * ���ÿ��������˻�
   * 
   * @param ccustbankaccid ���������˻�
   */
  public void setCcustbankaccid(String ccustbankaccid) {
    this.setAttributeValue(SquareOutBVO.CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * ��ȡ���۲������°汾
   * 
   * @return ���۲������°汾
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(SquareOutBVO.CDEPTID);
  }

  /**
   * �������۲������°汾
   * 
   * @param cdeptid ���۲������°汾
   */
  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SquareOutBVO.CDEPTID, cdeptid);
  }

  /**
   * ��ȡ���۲��Ű汾
   * 
   * @return ���۲��Ű汾
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CDEPTVID);
  }

  /**
   * �������۲��Ű汾
   * 
   * @param cdeptvid ���۲��Ű汾
   */
  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SquareOutBVO.CDEPTVID, cdeptvid);
  }

  /**
   * ��ȡ����ҵ��Ա
   * 
   * @return ����ҵ��Ա
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(SquareOutBVO.CEMPLOYEEID);
  }

  /**
   * ��������ҵ��Ա
   * 
   * @param cemployeeid ����ҵ��Ա
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SquareOutBVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * ��ȡԴͷ�����ӱ�
   * 
   * @return Դͷ�����ӱ�
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SquareOutBVO.CFIRSTBID);
  }

  /**
   * ����Դͷ�����ӱ�
   * 
   * @param cfirstbid Դͷ�����ӱ�
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SquareOutBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SquareOutBVO.CFIRSTID);
  }

  /**
   * ����Դͷ��������
   * 
   * @param cfirstid Դͷ��������
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SquareOutBVO.CFIRSTID, cfirstid);
  }

  /**
   * ��ȡɢ��
   * 
   * @return ɢ��
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(SquareOutBVO.CFREECUSTID);
  }

  /**
   * ����ɢ��
   * 
   * @param cfreecustid ɢ��
   */
  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SquareOutBVO.CFREECUSTID, cfreecustid);
  }

  /**
   * ��ȡ������Ʊ�ͻ�
   * 
   * @return ������Ʊ�ͻ�
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SquareOutBVO.CINVOICECUSTID);
  }

  /**
   * ���ö�����Ʊ�ͻ�
   * 
   * @param cinvoicecustid ������Ʊ�ͻ�
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SquareOutBVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SquareOutBVO.CMATERIALID);
  }

  /**
   * ��������
   * 
   * @param cmaterialid ����
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SquareOutBVO.CMATERIALID, cmaterialid);
  }

  /**
   * ��ȡ���ϰ汾
   * 
   * @return ���ϰ汾
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CMATERIALVID);
  }

  /**
   * �������ϰ汾
   * 
   * @param cmaterialvid ���ϰ汾
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SquareOutBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * ��ȡ�����ͻ�
   * 
   * @return �����ͻ�
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(SquareOutBVO.CORDERCUSTID);
  }

  /**
   * ���ö����ͻ�
   * 
   * @param cordercustid �����ͻ�
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SquareOutBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * ��ȡԭ��
   * 
   * @return ԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareOutBVO.CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyid ԭ��
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareOutBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ��ȡ�����ո���Э��
   * 
   * @return �����ո���Э��
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPAYTERMID);
  }

  /**
   * ���ö����ո���Э��
   * 
   * @param cpaytermid �����ո���Э��
   */
  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SquareOutBVO.CPAYTERMID, cpaytermid);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPRODUCTORID);
  }

  /**
   * ������������
   * 
   * @param cproductorid ��������
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SquareOutBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * ��ȡ���������������°汾
   * 
   * @return ���������������°汾
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROFITCENTERID);
  }

  /**
   * ���ý��������������°汾
   * 
   * @param cprofitcenterid ���������������°汾
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SquareOutBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * ��ȡ�����������İ汾
   * 
   * @return �����������İ汾
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROFITCENTERVID);
  }

  /**
   * ���ý����������İ汾
   * 
   * @param cprofitcentervid �����������İ汾
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SquareOutBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * ��ȡ��ĿID
   * 
   * @return ��ĿID
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROJECTID);
  }

  /**
   * ������ĿID
   * 
   * @param cprojectid ��ĿID
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SquareOutBVO.CPROJECTID, cprojectid);
  }

  /**
   * ��ȡ��Ʒ��
   * 
   * @return ��Ʒ��
   */
  public String getCprolineid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROLINEID);
  }

  /**
   * ���ò�Ʒ��
   * 
   * @param cprolineid ��Ʒ��
   */
  public void setCprolineid(String cprolineid) {
    this.setAttributeValue(SquareOutBVO.CPROLINEID, cprolineid);
  }

  /**
   * ��ȡcrushoutbatchid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return crushoutbatchid
   */
  public String getCrushoutbatchid() {
    return (String) this.getAttributeValue(SquareOutBVO.CRUSHOUTBATCHID);
  }

  /**
   * ����crushoutbatchid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param crushoutbatchid crushoutbatchid
   */
  public void setCrushoutbatchid(String crushoutbatchid) {
    this.setAttributeValue(SquareOutBVO.CRUSHOUTBATCHID, crushoutbatchid);
  }

  /**
   * ��ȡ������֯
   * 
   * @return ������֯
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALEORGID);
  }

  /**
   * ����������֯
   * 
   * @param csaleorgid ������֯
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SquareOutBVO.CSALEORGID, csaleorgid);
  }

  /**
   * ��ȡ������֯�汾
   * 
   * @return ������֯�汾
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALEORGVID);
  }

  /**
   * ����������֯�汾
   * 
   * @param csaleorgvid ������֯�汾
   */
  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SquareOutBVO.CSALEORGVID, csaleorgvid);
  }

  /**
   * ��ȡ���۳�����㵥��ʵ��
   * 
   * @return ���۳�����㵥��ʵ��
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALESQUAREBID);
  }

  /**
   * �������۳�����㵥��ʵ��
   * 
   * @param csalesquarebid ���۳�����㵥��ʵ��
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareOutBVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * ��ȡcsalesquaredid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return csalesquaredid
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALESQUAREDID);
  }

  /**
   * ����csalesquaredid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param csalesquaredid csalesquaredid
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareOutBVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * ��ȡ���۳�������㵥��ʵ��_����
   * 
   * @return ���۳�������㵥��ʵ��_����
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALESQUAREID);
  }

  /**
   * �������۳�������㵥��ʵ��_����
   * 
   * @param csalesquareid ���۳�������㵥��ʵ��_����
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareOutBVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ���۳��ⵥ��ʵ��
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSQUAREBILLBID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param csquarebillbid ���۳��ⵥ��ʵ��
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareOutBVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * ��ȡ���۳��ⵥ��ʵ��
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSQUAREBILLID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param csquarebillid ���۳��ⵥ��ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareOutBVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ��Դ�����ӱ�
   * 
   * @return ��Դ�����ӱ�
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSRCBID);
  }

  /**
   * ������Դ�����ӱ�
   * 
   * @param csrcbid ��Դ�����ӱ�
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SquareOutBVO.CSRCBID, csrcbid);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSRCID);
  }

  /**
   * ������Դ��������
   * 
   * @param csrcid ��Դ��������
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SquareOutBVO.CSRCID, csrcid);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareOutBVO.CTAXCODEID);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareOutBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareOutBVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid ����λ
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareOutBVO.CUNITID, cunitid);
  }

  /**
   * ��ȡ��Ӧ��
   * 
   * @return ��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SquareOutBVO.CVENDORID);
  }

  /**
   * ���ù�Ӧ��
   * 
   * @param cvendorid ��Ӧ��
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SquareOutBVO.CVENDORID, cvendorid);
  }

  /**
   * ��ȡ���۳��ⵥ��������
   * 
   * @return ���۳��ⵥ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareOutBVO.DBILLDATE);
  }

  /**
   * �������۳��ⵥ��������
   * 
   * @param dbilldate ���۳��ⵥ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareOutBVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ���۳��ⵥҵ������
   * 
   * @return ���۳��ⵥҵ������
   */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(SquareOutBVO.DBIZDATE);
  }

  /**
   * �������۳��ⵥҵ������
   * 
   * @param dbizdate ���۳��ⵥҵ������
   */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(SquareOutBVO.DBIZDATE, dbizdate);
  }

  /**
   * ��ȡӦ�յ���Ч����
   * 
   * @return Ӧ�յ���Ч����
   */
  public UFDate getDeffectdate() {
    return (UFDate) this.getAttributeValue(SquareOutBVO.DEFFECTDATE);
  }

  /**
   * ����Ӧ�յ���Ч����
   * 
   * @param deffectdate Ӧ�յ���Ч����
   */
  public void setDeffectdate(UFDate deffectdate) {
    this.setAttributeValue(SquareOutBVO.DEFFECTDATE, deffectdate);
  }

  /**
   * ��ȡ�������������
   * 
   * @return �������������
   * @see SquareType
   */
  public Integer getFpreartype() {
    return (Integer) this.getAttributeValue(SquareOutBVO.FPREARTYPE);
  }

  /**
   * ���ô������������
   * 
   * @param fpreartype �������������
   * @see SquareType
   */
  public void setFpreartype(Integer fpreartype) {
    this.setAttributeValue(SquareOutBVO.FPREARTYPE, fpreartype);
  }

  /**
   * ��ȡ���ɱ���������
   * 
   * @return ���ɱ���������
   * @see SquareType
   */
  public Integer getFpreiatype() {
    return (Integer) this.getAttributeValue(SquareOutBVO.FPREIATYPE);
  }

  /**
   * ���ô��ɱ���������
   * 
   * @param fpreiatype ���ɱ���������
   * @see SquareType
   */
  public void setFpreiatype(Integer fpreiatype) {
    this.setAttributeValue(SquareOutBVO.FPREIATYPE, fpreiatype);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareOutBVO.FTAXTYPEFLAG);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareOutBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * ��ȡ�ۼƻس�Ӧ������
   * 
   * @return �ۼƻس�Ӧ������
   */
  public UFDouble getNarrushnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NARRUSHNUM);
  }

  /**
   * �����ۼƻس�Ӧ������
   * 
   * @param narrushnum �ۼƻس�Ӧ������
   */
  public void setNarrushnum(UFDouble narrushnum) {
    this.setAttributeValue(SquareOutBVO.NARRUSHNUM, narrushnum);
  }

  /**
   * ��ȡ��λ����
   * 
   * @return ��λ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NASTNUM);
  }

  /**
   * ���õ�λ����
   * 
   * @param nastnum ��λ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareOutBVO.NASTNUM, nastnum);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NCALTAXMNY);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareOutBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * ��ȡ�����ۿ۶�
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDISCOUNT);
  }

  /**
   * ���ñ����ۿ۶�
   * 
   * @param ndiscount �����ۿ۶�
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareOutBVO.NDISCOUNT, ndiscount);
  }

  /**
   * ��ȡ�ۼ����η�Ʊȷ��Ӧ�ս��
   * 
   * @return �ۼ����η�Ʊȷ��Ӧ�ս��
   */
  public UFDouble getNdownarmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDOWNARMNY);
  }

  /**
   * �����ۼ����η�Ʊȷ��Ӧ�ս��
   * 
   * @param ndownarmny �ۼ����η�Ʊȷ��Ӧ�ս��
   */
  public void setNdownarmny(UFDouble ndownarmny) {
    this.setAttributeValue(SquareOutBVO.NDOWNARMNY, ndownarmny);
  }

  /**
   * ��ȡ�ۼ����η�Ʊȷ��Ӧ������
   * 
   * @return �ۼ����η�Ʊȷ��Ӧ������
   */
  public UFDouble getNdownarnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDOWNARNUM);
  }

  /**
   * �����ۼ����η�Ʊȷ��Ӧ������
   * 
   * @param ndownarnum �ۼ����η�Ʊȷ��Ӧ������
   */
  public void setNdownarnum(UFDouble ndownarnum) {
    this.setAttributeValue(SquareOutBVO.NDOWNARNUM, ndownarnum);
  }

  /**
   * ��ȡ�ۼ����η�Ʊ�ɱ���������
   * 
   * @return �ۼ����η�Ʊ�ɱ���������
   */
  public UFDouble getNdownianum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDOWNIANUM);
  }

  /**
   * �����ۼ����η�Ʊ�ɱ���������
   * 
   * @param ndownianum �ۼ����η�Ʊ�ɱ���������
   */
  public void setNdownianum(UFDouble ndownianum) {
    this.setAttributeValue(SquareOutBVO.NDOWNIANUM, ndownianum);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NEXCHANGERATE);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareOutBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * ��ȡȫ�ֱ�λ�һ���
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGLOBALEXCHGRATE);
  }

  /**
   * ����ȫ�ֱ�λ�һ���
   * 
   * @param nglobalexchgrate ȫ�ֱ�λ�һ���
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareOutBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * ��ȡȫ�ֱ�����˰���
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGLOBALMNY);
  }

  /**
   * ����ȫ�ֱ�����˰���
   * 
   * @param nglobalmny ȫ�ֱ�����˰���
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareOutBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * ��ȡȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @return ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGLOBALTAXMNY);
  }

  /**
   * ����ȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @param nglobaltaxmny ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareOutBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * ��ȡ���ű�λ�һ���
   * 
   * @return ���ű�λ�һ���
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGROUPEXCHGRATE);
  }

  /**
   * ���ü��ű�λ�һ���
   * 
   * @param ngroupexchgrate ���ű�λ�һ���
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareOutBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * ��ȡ���ű�����˰���
   * 
   * @return ���ű�����˰���
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGROUPMNY);
  }

  /**
   * ���ü��ű�����˰���
   * 
   * @param ngroupmny ���ű�����˰���
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareOutBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * ��ȡ���ű��Ҽ�˰�ϼ�
   * 
   * @return ���ű��Ҽ�˰�ϼ�
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGROUPTAXMNY);
  }

  /**
   * ���ü��ű��Ҽ�˰�ϼ�
   * 
   * @param ngrouptaxmny ���ű��Ҽ�˰�ϼ�
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareOutBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * ��ȡ��Ʒ�ۿ���
   * 
   * @return ��Ʒ�ۿ���
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NITEMDISCOUNTRATE);
  }

  /**
   * ���õ�Ʒ�ۿ���
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ���
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareOutBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * ��ȡ������˰���
   * 
   * @return ������˰���
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NMNY);
  }

  /**
   * ���ñ�����˰���
   * 
   * @param nmny ������˰���
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareOutBVO.NMNY, nmny);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NNETPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nnetprice ������˰����
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareOutBVO.NNETPRICE, nnetprice);
  }

  /**
   * ��ȡ����λ����
   * 
   * @return ����λ����
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NNUM);
  }

  /**
   * ��������λ����
   * 
   * @param nnum ����λ����
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareOutBVO.NNUM, nnum);
  }

  /**
   * ��ȡԭ���ۿ۶�
   * 
   * @return ԭ���ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGDISCOUNT);
  }

  /**
   * ����ԭ���ۿ۶�
   * 
   * @param norigdiscount ԭ���ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareOutBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * ��ȡԭ����˰���
   * 
   * @return ԭ����˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGMNY);
  }

  /**
   * ����ԭ����˰���
   * 
   * @param norigmny ԭ����˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareOutBVO.NORIGMNY, norigmny);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGNETPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norignetprice ԭ����˰����
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareOutBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norigprice ԭ����˰����
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareOutBVO.NORIGPRICE, norigprice);
  }

  /**
   * ��ȡԭ�Ҽ�˰�ϼ�
   * 
   * @return ԭ�Ҽ�˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGTAXMNY);
  }

  /**
   * ����ԭ�Ҽ�˰�ϼ�
   * 
   * @param norigtaxmny ԭ�Ҽ�˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareOutBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGTAXNETPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxnetprice ԭ�Һ�˰����
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareOutBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGTAXPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxprice ԭ�Һ�˰����
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareOutBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nprice ������˰����
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareOutBVO.NPRICE, nprice);
  }

  /**
   * ��ȡ�ۼƳ���Գ�����
   * 
   * @return �ۼƳ���Գ�����
   */
  public UFDouble getNrushnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NRUSHNUM);
  }

  /**
   * �����ۼƳ���Գ�����
   * 
   * @param nrushnum �ۼƳ���Գ�����
   */
  public void setNrushnum(UFDouble nrushnum) {
    this.setAttributeValue(SquareOutBVO.NRUSHNUM, nrushnum);
  }

  /**
   * ��ȡ�ۼ�ȷ��Ӧ�ս��
   * 
   * @return �ۼ�ȷ��Ӧ�ս��
   */
  public UFDouble getNsquarearmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREARMNY);
  }

  /**
   * �����ۼ�ȷ��Ӧ�ս��
   * 
   * @param nsquarearmny �ۼ�ȷ��Ӧ�ս��
   */
  public void setNsquarearmny(UFDouble nsquarearmny) {
    this.setAttributeValue(SquareOutBVO.NSQUAREARMNY, nsquarearmny);
  }

  /**
   * ��ȡ�ۼ�ȷ��Ӧ������
   * 
   * @return �ۼ�ȷ��Ӧ������
   */
  public UFDouble getNsquarearnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREARNUM);
  }

  /**
   * �����ۼ�ȷ��Ӧ������
   * 
   * @param nsquarearnum �ۼ�ȷ��Ӧ������
   */
  public void setNsquarearnum(UFDouble nsquarearnum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREARNUM, nsquarearnum);
  }

  /**
   * ��ȡ�ۼ��ݹ�Ӧ������
   * 
   * @return �ۼ��ݹ�Ӧ������
   */
  public UFDouble getNsquareestnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREESTNUM);
  }

  /**
   * �����ۼ��ݹ�Ӧ������
   * 
   * @param nsquareestnum �ۼ��ݹ�Ӧ������
   */
  public void setNsquareestnum(UFDouble nsquareestnum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREESTNUM, nsquareestnum);
  }

  /**
   * ��ȡ�ۼƳɱ���������
   * 
   * @return �ۼƳɱ���������
   */
  public UFDouble getNsquareianum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREIANUM);
  }

  /**
   * �����ۼƳɱ���������
   * 
   * @param nsquareianum �ۼƳɱ���������
   */
  public void setNsquareianum(UFDouble nsquareianum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREIANUM, nsquareianum);
  }

  /**
   * ��ȡ�ۼƷ�����Ʒ����
   * 
   * @return �ۼƷ�����Ʒ����
   */
  public UFDouble getNsquareregnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREREGNUM);
  }

  /**
   * �����ۼƷ�����Ʒ����
   * 
   * @param nsquareregnum �ۼƷ�����Ʒ����
   */
  public void setNsquareregnum(UFDouble nsquareregnum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREREGNUM, nsquareregnum);
  }

  /**
   * ��ȡ����˰��
   * 
   * @return ����˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAX);
  }

  /**
   * ���ñ���˰��
   * 
   * @param ntax ����˰��
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareOutBVO.NTAX, ntax);
  }

  /**
   * ��ȡ���Ҽ�˰�ϼ�
   * 
   * @return ���Ҽ�˰�ϼ�
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXMNY);
  }

  /**
   * ���ñ��Ҽ�˰�ϼ�
   * 
   * @param ntaxmny ���Ҽ�˰�ϼ�
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareOutBVO.NTAXMNY, ntaxmny);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXNETPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxnetprice ���Һ�˰����
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareOutBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxprice ���Һ�˰����
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareOutBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXRATE);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareOutBVO.NTAXRATE, ntaxrate);
  }

  /**
   * ��ȡnthisnum���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return nthisnum
   */
  public UFDouble getNthisnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTHISNUM);
  }

  /**
   * ����nthisnum���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param nthisnum nthisnum
   */
  public void setNthisnum(UFDouble nthisnum) {
    this.setAttributeValue(SquareOutBVO.NTHISNUM, nthisnum);
  }

  /**
   * ��ȡ�ۼƳ��⼰���η�Ʊ�տ�������
   * 
   * @return �ۼƳ��⼰���η�Ʊ�տ�������
   */
  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTOTALPAYMNY);
  }

  /**
   * �����ۼƳ��⼰���η�Ʊ�տ�������
   * 
   * @param ntotalpaymny �ۼƳ��⼰���η�Ʊ�տ�������
   */
  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SquareOutBVO.NTOTALPAYMNY, ntotalpaymny);
  }

  /**
   * ��ȡ���κŵ���
   * 
   * @return ���κŵ���
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SquareOutBVO.PK_BATCHCODE);
  }

  /**
   * �������κŵ���
   * 
   * @param pk_batchcode ���κŵ���
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SquareOutBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareOutBVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareOutBVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareOutBVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareOutBVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡprocesseid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return processeid
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareOutBVO.PROCESSEID);
  }

  /**
   * ����processeid���ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param processeid processeid
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareOutBVO.PROCESSEID, processeid);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareOutBVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareOutBVO.TS, ts);
  }

  /**
   * ��ȡ���κ�
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SquareOutBVO.VBATCHCODE);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode ���κ�
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SquareOutBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * ��ȡ�����Զ�����1
   * 
   * @return �����Զ�����1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF1);
  }

  /**
   * ���ñ����Զ�����1
   * 
   * @param vbdef1 �����Զ�����1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SquareOutBVO.VBDEF1, vbdef1);
  }

  /**
   * ��ȡ�����Զ�����10
   * 
   * @return �����Զ�����10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF10);
  }

  /**
   * ���ñ����Զ�����10
   * 
   * @param vbdef10 �����Զ�����10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SquareOutBVO.VBDEF10, vbdef10);
  }

  /**
   * ��ȡ�����Զ�����11
   * 
   * @return �����Զ�����11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF11);
  }

  /**
   * ���ñ����Զ�����11
   * 
   * @param vbdef11 �����Զ�����11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SquareOutBVO.VBDEF11, vbdef11);
  }

  /**
   * ��ȡ�����Զ�����12
   * 
   * @return �����Զ�����12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF12);
  }

  /**
   * ���ñ����Զ�����12
   * 
   * @param vbdef12 �����Զ�����12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SquareOutBVO.VBDEF12, vbdef12);
  }

  /**
   * ��ȡ�����Զ�����13
   * 
   * @return �����Զ�����13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF13);
  }

  /**
   * ���ñ����Զ�����13
   * 
   * @param vbdef13 �����Զ�����13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SquareOutBVO.VBDEF13, vbdef13);
  }

  /**
   * ��ȡ�����Զ�����14
   * 
   * @return �����Զ�����14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF14);
  }

  /**
   * ���ñ����Զ�����14
   * 
   * @param vbdef14 �����Զ�����14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SquareOutBVO.VBDEF14, vbdef14);
  }

  /**
   * ��ȡ�����Զ�����15
   * 
   * @return �����Զ�����15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF15);
  }

  /**
   * ���ñ����Զ�����15
   * 
   * @param vbdef15 �����Զ�����15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SquareOutBVO.VBDEF15, vbdef15);
  }

  /**
   * ��ȡ�����Զ�����16
   * 
   * @return �����Զ�����16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF16);
  }

  /**
   * ���ñ����Զ�����16
   * 
   * @param vbdef16 �����Զ�����16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SquareOutBVO.VBDEF16, vbdef16);
  }

  /**
   * ��ȡ�����Զ�����17
   * 
   * @return �����Զ�����17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF17);
  }

  /**
   * ���ñ����Զ�����17
   * 
   * @param vbdef17 �����Զ�����17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SquareOutBVO.VBDEF17, vbdef17);
  }

  /**
   * ��ȡ�����Զ�����18
   * 
   * @return �����Զ�����18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF18);
  }

  /**
   * ���ñ����Զ�����18
   * 
   * @param vbdef18 �����Զ�����18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SquareOutBVO.VBDEF18, vbdef18);
  }

  /**
   * ��ȡ�����Զ�����19
   * 
   * @return �����Զ�����19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF19);
  }

  /**
   * ���ñ����Զ�����19
   * 
   * @param vbdef19 �����Զ�����19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SquareOutBVO.VBDEF19, vbdef19);
  }

  /**
   * ��ȡ�����Զ�����2
   * 
   * @return �����Զ�����2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF2);
  }

  /**
   * ���ñ����Զ�����2
   * 
   * @param vbdef2 �����Զ�����2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SquareOutBVO.VBDEF2, vbdef2);
  }

  /**
   * ��ȡ�����Զ�����20
   * 
   * @return �����Զ�����20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF20);
  }

  /**
   * ���ñ����Զ�����20
   * 
   * @param vbdef20 �����Զ�����20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SquareOutBVO.VBDEF20, vbdef20);
  }

  /**
   * ��ȡ�����Զ�����3
   * 
   * @return �����Զ�����3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF3);
  }

  /**
   * ���ñ����Զ�����3
   * 
   * @param vbdef3 �����Զ�����3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SquareOutBVO.VBDEF3, vbdef3);
  }

  /**
   * ��ȡ�����Զ�����4
   * 
   * @return �����Զ�����4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF4);
  }

  /**
   * ���ñ����Զ�����4
   * 
   * @param vbdef4 �����Զ�����4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SquareOutBVO.VBDEF4, vbdef4);
  }

  /**
   * ��ȡ�����Զ�����5
   * 
   * @return �����Զ�����5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF5);
  }

  /**
   * ���ñ����Զ�����5
   * 
   * @param vbdef5 �����Զ�����5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SquareOutBVO.VBDEF5, vbdef5);
  }

  /**
   * ��ȡ�����Զ�����6
   * 
   * @return �����Զ�����6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF6);
  }

  /**
   * ���ñ����Զ�����6
   * 
   * @param vbdef6 �����Զ�����6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SquareOutBVO.VBDEF6, vbdef6);
  }

  /**
   * ��ȡ�����Զ�����7
   * 
   * @return �����Զ�����7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF7);
  }

  /**
   * ���ñ����Զ�����7
   * 
   * @param vbdef7 �����Զ�����7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SquareOutBVO.VBDEF7, vbdef7);
  }

  /**
   * ��ȡ�����Զ�����8
   * 
   * @return �����Զ�����8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF8);
  }

  /**
   * ���ñ����Զ�����8
   * 
   * @param vbdef8 �����Զ�����8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SquareOutBVO.VBDEF8, vbdef8);
  }

  /**
   * ��ȡ�����Զ�����9
   * 
   * @return �����Զ�����9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF9);
  }

  /**
   * ���ñ����Զ�����9
   * 
   * @param vbdef9 �����Զ�����9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SquareOutBVO.VBDEF9, vbdef9);
  }

  /**
   * ��ȡ��λ������
   * 
   * @return ��λ������
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareOutBVO.VCHANGERATE);
  }

  /**
   * ���õ�λ������
   * 
   * @param vchangerate ��λ������
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareOutBVO.VCHANGERATE, vchangerate);
  }

  /**
   * ��ȡ��ͬ��
   * 
   * @return ��ͬ��
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SquareOutBVO.VCTCODE);
  }

  /**
   * ���ú�ͬ��
   * 
   * @param vctcode ��ͬ��
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SquareOutBVO.VCTCODE, vctcode);
  }

  /**
   * ��ȡԴͷ���ݺ�
   * 
   * @return Դͷ���ݺ�
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTCODE);
  }

  /**
   * ����Դͷ���ݺ�
   * 
   * @param vfirstcode Դͷ���ݺ�
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SquareOutBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * ��ȡԴͷ�����к�
   * 
   * @return Դͷ�����к�
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTROWNO);
  }

  /**
   * ����Դͷ�����к�
   * 
   * @param vfirstrowno Դͷ�����к�
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SquareOutBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * ��ȡԴͷ���ݽ�������
   * 
   * @return Դͷ���ݽ�������
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTTRANTYPE);
  }

  /**
   * ����Դͷ���ݽ�������
   * 
   * @param vfirsttrantype Դͷ���ݽ�������
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SquareOutBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * ��ȡԴͷ��������
   * 
   * @return Դͷ��������
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTTYPE);
  }

  /**
   * ����Դͷ��������
   * 
   * @param vfirsttype Դͷ��������
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SquareOutBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * ��ȡ���ɸ�������1
   * 
   * @return ���ɸ�������1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE1);
  }

  /**
   * �������ɸ�������1
   * 
   * @param vfree1 ���ɸ�������1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SquareOutBVO.VFREE1, vfree1);
  }

  /**
   * ��ȡ���ɸ�������10
   * 
   * @return ���ɸ�������10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE10);
  }

  /**
   * �������ɸ�������10
   * 
   * @param vfree10 ���ɸ�������10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SquareOutBVO.VFREE10, vfree10);
  }

  /**
   * ��ȡ���ɸ�������2
   * 
   * @return ���ɸ�������2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE2);
  }

  /**
   * �������ɸ�������2
   * 
   * @param vfree2 ���ɸ�������2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SquareOutBVO.VFREE2, vfree2);
  }

  /**
   * ��ȡ���ɸ�������3
   * 
   * @return ���ɸ�������3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE3);
  }

  /**
   * �������ɸ�������3
   * 
   * @param vfree3 ���ɸ�������3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SquareOutBVO.VFREE3, vfree3);
  }

  /**
   * ��ȡ���ɸ�������4
   * 
   * @return ���ɸ�������4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE4);
  }

  /**
   * �������ɸ�������4
   * 
   * @param vfree4 ���ɸ�������4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SquareOutBVO.VFREE4, vfree4);
  }

  /**
   * ��ȡ���ɸ�������5
   * 
   * @return ���ɸ�������5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE5);
  }

  /**
   * �������ɸ�������5
   * 
   * @param vfree5 ���ɸ�������5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SquareOutBVO.VFREE5, vfree5);
  }

  /**
   * ��ȡ���ɸ�������6
   * 
   * @return ���ɸ�������6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE6);
  }

  /**
   * �������ɸ�������6
   * 
   * @param vfree6 ���ɸ�������6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SquareOutBVO.VFREE6, vfree6);
  }

  /**
   * ��ȡ���ɸ�������7
   * 
   * @return ���ɸ�������7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE7);
  }

  /**
   * �������ɸ�������7
   * 
   * @param vfree7 ���ɸ�������7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SquareOutBVO.VFREE7, vfree7);
  }

  /**
   * ��ȡ���ɸ�������8
   * 
   * @return ���ɸ�������8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE8);
  }

  /**
   * �������ɸ�������8
   * 
   * @param vfree8 ���ɸ�������8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SquareOutBVO.VFREE8, vfree8);
  }

  /**
   * ��ȡ���ɸ�������9
   * 
   * @return ���ɸ�������9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE9);
  }

  /**
   * �������ɸ�������9
   * 
   * @param vfree9 ���ɸ�������9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SquareOutBVO.VFREE9, vfree9);
  }

  /**
   * ��ȡ�б�ע
   * 
   * @return �б�ע
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SquareOutBVO.VROWNOTE);
  }

  /**
   * �����б�ע
   * 
   * @param vrownote �б�ע
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SquareOutBVO.VROWNOTE, vrownote);
  }

  /**
   * ��ȡ��Դ���ݺ�
   * 
   * @return ��Դ���ݺ�
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCCODE);
  }

  /**
   * ������Դ���ݺ�
   * 
   * @param vsrccode ��Դ���ݺ�
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SquareOutBVO.VSRCCODE, vsrccode);
  }

  /**
   * ��ȡ��Դ�����к�
   * 
   * @return ��Դ�����к�
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCROWNO);
  }

  /**
   * ������Դ�����к�
   * 
   * @param vsrcrowno ��Դ�����к�
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SquareOutBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * ��ȡ��Դ���ݽ�������
   * 
   * @return ��Դ���ݽ�������
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCTRANTYPE);
  }

  /**
   * ������Դ���ݽ�������
   * 
   * @param vsrctrantype ��Դ���ݽ�������
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SquareOutBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * ��ȡ��Դ��������
   * 
   * @return ��Դ��������
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCTYPE);
  }

  /**
   * ������Դ��������
   * 
   * @param vsrctype ��Դ��������
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SquareOutBVO.VSRCTYPE, vsrctype);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareOutBVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }

  public void setNtotalsquarenum(UFDouble ntotalsquarenum) {
    this.setAttributeValue(SquareOutBVO.NTOTALSQUARENUM, ntotalsquarenum);
  }

  public UFDouble getNtotalsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTOTALSQUARENUM);
  }

  public void setCsquareoutbvoid(String csquareoutbvoid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID, csquareoutbvoid);
  }

  public String getCsquareoutbvoid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID);
  }
  
  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SquareOutBVO.CMFFILEID);
  }

  /**
   * ����������
   * 
   * @param ������
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SquareOutBVO.CMFFILEID, cmffileid);
  }
}
