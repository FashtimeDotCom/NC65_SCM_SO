package nc.vo.so.report.outprofit;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * ���۳���ë��������ͼVO
 * 
 * @since 6.0
 * @version 2012-7-27 ����8:55:39
 * @author ������
 */
public class OutProfitViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -2120362819295999764L;

  /**
   * ��Ʒ��
   */
  public static final String FLARGESS = "flargess";

  /**
   * ���۳��ⵥ��ʵ��
   */
  public static final String CGENERALBID = "cgeneralbid";

  /**
   * ���۳��ⵥ��ʵ��
   */

  public static final String CGENERALHID = "cgeneralhid";

  /**
   * ������֯
   */
  public static final String CSALEORGOID = "csaleorgoid";

  /**
   * ���۲���
   */
  public static final String CDPTID = "cdptid";

  /**
   * ����ҵ��Ա
   */
  public static final String CBIZID = "cbizid";

  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * �ͻ���������
   */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /**
   * �ͻ����۷���
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /**
   * ��������
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * �����ͻ�
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * ���ϻ�������
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /**
   * �������۷���
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /**
   * ����
   */
  public static final String CMATERIALOID = "cmaterialoid";

  /**
   * �������°汾id
   */
  public static final String CMATERIALVID = "cmaterialvid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ���κ�
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ���ⵥ��
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * ����
   */
  public static final String CORIGCURRENCYID = "ccurrencyid";

  /**
   * ��Ʒ��
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * ������
   */
  public static final String NMAINNUM = "nmainnum";

  /**
   * ����������
   */
  public static final String NNUM = "nnum";

  /**
   * ��˰����
   */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /**
   * Ӧ��������
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * Ӧ����˰���
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * ��˰���
   */
  public static final String NNOTAXMNY = "nnotaxmny";

  /**
   * ��˰����
   */
  public static final String NNOTAXPRICE = "nnotaxprice";

  /**
   * �ɱ�����������
   */
  public static final String NCOSTNUM = "ncostnum";

  /**
   * �ɱ�����ɱ����
   */
  public static final String NCOSTMNY = "ncostmny";

  /**
   * �ɱ����
   */
  public static final String NTOTALCOSTMNY = "ntotalcostmny";

  /**
   * �ɱ�����
   */
  public static final String NCOSTPRICE = "ncostprice";

  /**
   * ë��
   */
  public static final String NPROFITMNY = "nprofitmny";

  /**
   * ë����
   */
  public static final String NPROFITRATE = "nprofitrate";

  /**
   * �ѳɱ�����Ľ��
   * 
   */
  public static final String NCOST = "ncost";

  /**
   * 
   * @return �ѳɱ�����Ľ��
   */
  public UFDouble getNcost() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOST);
  }

  /**
   * �����ѳɱ�����Ľ��
   * 
   * @param ncost
   */
  public void setNcost(UFDouble ncost) {
    super.setAttributeValue(OutProfitViewVO.NCOST, ncost);
  }

  /**
   * δ�ɱ����������
   */
  public static final String NOCOSTNUM = "nocostnum";

  /**
   * 
   * @return δ�ɱ����������
   */
  public UFDouble getNocostnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NOCOSTNUM);
  }

  /**
   * ����δ�ɱ����������
   * 
   * @param nocostnum
   */
  public void setNocostnum(UFDouble nocostnum) {
    super.setAttributeValue(OutProfitViewVO.NOCOSTNUM, nocostnum);
  }

  /**
   * ������֯
   */
  public static final String CFANACEORGOID = "cfanaceorgoid";

  /**
   * ��ȡ������֯
   * 
   * @return ������֯
   */
  public String getCfanaceorgoid() {
    return (String) this.getAttributeValue(OutProfitViewVO.CFANACEORGOID);
  }

  /**
   * ���ò�����֯
   * 
   * @param cfanaceorgoid
   */
  public void setCfanaceorgoid(String cfanaceorgoid) {
    this.setAttributeValue(OutProfitViewVO.CFANACEORGOID, cfanaceorgoid);
  }

  /**
   * �ֿ���֯
   * 
   */
  public static final String CBODYWAREHOUSEID = "cbodywarehouseid";

  /**
   * �ֿ���֯
   * 
   * @return �ֿ���֯
   */
  public String getCbodywarehouseid() {
    return (String) this.getAttributeValue(OutProfitViewVO.CBODYWAREHOUSEID);
  }

  /**
   * ���òֿ���֯
   * 
   * @param cbodywarehouseid
   */
  public void setCbodywarehouseid(String cbodywarehouseid) {
    this.setAttributeValue(OutProfitViewVO.CBODYWAREHOUSEID, cbodywarehouseid);
  }

  /**
   * �����֯������֯��
   */
  public static final String PK_ORG = "pk_org";

  /**
   * ��ȡ�����֯
   * 
   * @return �����֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(OutProfitViewVO.PK_ORG);
  }

  /**
   * ���ÿ����֯
   * 
   * @param pk_org �����֯
   */
  public void sePk_org(String pk_org) {
    this.setAttributeValue(OutProfitViewVO.PK_ORG, pk_org);
  }

  /**
   * �Զ�����1
   */
  public static final String VDEF1 = "vdef1";

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
   * �Զ�����2
   */
  public static final String VDEF2 = "vdef2";

  /**
   * �Զ�����20
   */
  public static final String VDEF20 = "vdef20";

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

  @Override
  public IDataViewMeta getMetaData() {
    // IDataViewMeta��Ҫ������ǰ��ͼ�����ʵ��
    IDataViewMeta viewMeta =
    // ��ͼ�����ഴ���࣬��������ȡԪ����
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OutProfitViewMeta.class);
    return viewMeta;
  }

  /**
   * 
   * @return ��˰����
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NQTORIGNETPRICE);
  }

  /**
   * ������˰����
   * 
   * @param nqtorignetprice
   * 
   */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    super.setAttributeValue(OutProfitViewVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /**
   * 
   * @return ������
   */
  public UFDouble getNmainnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NMAINNUM);
  }

  /**
   * ����������
   * 
   * @param nmainnum
   */
  public void setNmainnum(UFDouble nmainnum) {
    super.setAttributeValue(OutProfitViewVO.NMAINNUM, nmainnum);
  }

  /**
   * 
   * @return Ӧ��������
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * ����Ӧ��������
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super.setAttributeValue(OutProfitViewVO.NSHOULDRECEIVNUM, nshouldreceivnum);
  }

  /**
   * 
   * @return ��˰���
   */
  public UFDouble getNnotaxmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NNOTAXMNY);
  }

  /**
   * ������˰���
   * 
   * @param nnotaxmny
   * 
   */
  public void setNnotaxmny(UFDouble nnotaxmny) {
    super.setAttributeValue(OutProfitViewVO.NNOTAXMNY, nnotaxmny);
  }

  /**
   * 
   * @return ��˰����
   */
  public UFDouble getNnotaxprice() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NNOTAXPRICE);
  }

  /**
   * ������˰����
   * 
   * @param nnotaxprice
   * 
   */
  public void setNnotaxprice(UFDouble nnotaxprice) {
    super.setAttributeValue(OutProfitViewVO.NNOTAXPRICE, nnotaxprice);
  }

  /**
   * 
   * @return �ɱ�����������
   */
  public UFDouble getNcostnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOSTNUM);
  }

  /**
   * ���óɱ�����������
   * 
   * @param ncostnum
   * 
   */
  public void setNcostnum(UFDouble ncostnum) {
    super.setAttributeValue(OutProfitViewVO.NCOSTNUM, ncostnum);
  }

  /**
   * 
   * @return �ɱ�����ɱ����
   */
  public UFDouble getNcostmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOSTMNY);
  }

  /**
   * ���óɱ�����ɱ����
   * 
   * @param ncostmny
   * 
   */
  public void setNcostmny(UFDouble ncostmny) {
    super.setAttributeValue(OutProfitViewVO.NCOSTMNY, ncostmny);
  }

  /**
   * 
   * @return ҵ��Ա
   */
  public String getCbizid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CBIZID);
  }

  /**
   * 
   * @return �����ͻ�
   */
  public String getCcustomerid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CCUSTOMERID);
  }

  /**
   * 
   * @return ����
   */
  public String getCdptid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CDPTID);
  }

  /**
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCgeneralbid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CGENERALBID);
  }

  /**
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCgeneralhid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CGENERALHID);
  }

  /**
   * 
   * @return ����
   */
  public String getCmaterialoid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CMATERIALOID);
  }

  /**
   * 
   * @return ����VID
   */
  public String getCmaterialvid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CMATERIALVID);
  }

  /**
   * 
   * @return ����
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CORIGCURRENCYID);
  }

  /**
   * 
   * @return ��Ʒ��
   */
  public String getCprodlineid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CPRODLINEID);
  }

  /**
   * 
   * @return ������֯
   */
  public String getCsaleorgoid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CSALEORGOID);
  }

  /**
   * 
   * @return ��������
   */
  public String getCtrantypeid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CTRANTYPEID);
  }

  /**
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CUNITID);
  }

  /**
   * 
   * @return �ɱ�����
   */
  public UFDouble getNcostprice() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOSTPRICE);
  }

  /**
   * 
   * @return ����������
   */
  public UFDouble getNnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NNUM);
  }

  /**
   * 
   * @return ë��
   */
  public UFDouble getNprofitmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NPROFITMNY);
  }

  /**
   * 
   * @return ë����
   */
  public UFDouble getNprofitrate() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NPROFITRATE);
  }

  /**
   * 
   * @return �ɱ����
   */
  public UFDouble getNtotalcostmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NTOTALCOSTMNY);
  }

  /**
   * 
   * @return Ӧ����˰���
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return ��������
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_AREACL);
  }

  /**
   * 
   * @return �ͻ���������
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return �ͻ����۷���
   */
  public String getPk_custsaleclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return ���ϻ�������
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return �������۷���
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_MARSALECLASS);
  }

  /**
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(OutProfitViewVO.VBATCHCODE);
  }

  /**
   * 
   * @return ���ⵥ��
   */
  public String getVbillcode() {
    return (String) super.getAttributeValue(OutProfitViewVO.VBILLCODE);
  }

  /**
   * ����ҵ��Ա
   * 
   * @param cbizid
   */
  public void setCbizid(String cbizid) {
    super.setAttributeValue(OutProfitViewVO.CBIZID, cbizid);
  }

  /**
   * ���ö����ͻ�
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    super.setAttributeValue(OutProfitViewVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * ���ò���
   * 
   * @param cdptid
   */
  public void setCdptid(String cdptid) {
    super.setAttributeValue(OutProfitViewVO.CDPTID, cdptid);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param cgeneralbid
   */
  public void setCgeneralbid(String cgeneralbid) {
    super.setAttributeValue(OutProfitViewVO.CGENERALBID, cgeneralbid);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param cgeneralhid
   */
  public void setCgeneralhid(String cgeneralhid) {
    super.setAttributeValue(OutProfitViewVO.CGENERALHID, cgeneralhid);
  }

  /**
   * ��������
   * 
   * @param cmaterialoid
   */
  public void setCmaterialoid(String cmaterialoid) {
    super.setAttributeValue(OutProfitViewVO.CMATERIALOID, cmaterialoid);
  }

  /**
   * ��������
   * 
   * @param cmaterialvid
   */
  public void setCmaterialvid(String cmaterialvid) {
    super.setAttributeValue(OutProfitViewVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * ���ñ���
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(OutProfitViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ���ò�Ʒ��
   * 
   * @param cprodlineid
   */
  public void setCprodlineid(String cprodlineid) {
    super.setAttributeValue(OutProfitViewVO.CPRODLINEID, cprodlineid);
  }

  /**
   * ����������֯
   * 
   * @param csaleorgoid
   */
  public void setCsaleorgoid(String csaleorgoid) {
    super.setAttributeValue(OutProfitViewVO.CSALEORGOID, csaleorgoid);
  }

  /**
   * ���ö�������
   * 
   * @param ctrantypeid
   */
  public void setCtrantypeid(String ctrantypeid) {
    super.setAttributeValue(OutProfitViewVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * ��������λ
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(OutProfitViewVO.CUNITID, cunitid);
  }

  /**
   * ���óɱ�����
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    super.setAttributeValue(OutProfitViewVO.NCOSTPRICE, ncostprice);
  }

  /**
   * ���ó���������
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    super.setAttributeValue(OutProfitViewVO.NNUM, nnum);
  }

  /**
   * ����ë��
   * 
   * @param nprofitmny
   */
  public void setNprofitmny(UFDouble nprofitmny) {
    super.setAttributeValue(OutProfitViewVO.NPROFITMNY, nprofitmny);
  }

  /**
   * ����ë����
   * 
   * @param nprofitrate
   */
  public void setNprofitrate(UFDouble nprofitrate) {
    super.setAttributeValue(OutProfitViewVO.NPROFITRATE, nprofitrate);
  }

  /**
   * ���óɱ����
   * 
   * @param ntotalcostmny
   */
  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    super.setAttributeValue(OutProfitViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  /**
   * ����Ӧ����˰���
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super.setAttributeValue(OutProfitViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  /**
   * ���õ�������
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(OutProfitViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * ���ÿͻ���������
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(OutProfitViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * ���ÿͻ����۷���
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super.setAttributeValue(OutProfitViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  /**
   * �������ϻ�������
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(OutProfitViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * �����������۷���
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(OutProfitViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(OutProfitViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * ���ó��ⵥ��
   * 
   * @param vbillcode
   */
  public void setVbillcode(String vbillcode) {
    super.setAttributeValue(OutProfitViewVO.VBILLCODE, vbillcode);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return �Զ�����1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF1);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return �Զ�����10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return �Զ�����11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return �Զ�����12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return �Զ�����13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return �Զ�����14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return �Զ�����15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return �Զ�����16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return �Զ�����17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return �Զ�����18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return �Զ�����19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF19);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return �Զ�����2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF2);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return �Զ�����20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF20);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return �Զ�����3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return �Զ�����4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return �Զ�����5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return �Զ�����6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return �Զ�����7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return �Զ�����8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return �Զ�����9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF9);
  }

  /**
   * �����Զ�����1
   * 
   * @param vdef1 �Զ�����1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(OutProfitViewVO.VDEF1, vdef1);
  }

  /**
   * �����Զ�����10
   * 
   * @param vdef10 �Զ�����10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(OutProfitViewVO.VDEF10, vdef10);
  }

  /**
   * �����Զ�����11
   * 
   * @param vdef11 �Զ�����11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(OutProfitViewVO.VDEF11, vdef11);
  }

  /**
   * �����Զ�����12
   * 
   * @param vdef12 �Զ�����12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(OutProfitViewVO.VDEF12, vdef12);
  }

  /**
   * �����Զ�����13
   * 
   * @param vdef13 �Զ�����13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(OutProfitViewVO.VDEF13, vdef13);
  }

  /**
   * �����Զ�����14
   * 
   * @param vdef14 �Զ�����14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(OutProfitViewVO.VDEF14, vdef14);
  }

  /**
   * �����Զ�����15
   * 
   * @param vdef15 �Զ�����15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(OutProfitViewVO.VDEF15, vdef15);
  }

  /**
   * �����Զ�����16
   * 
   * @param vdef16 �Զ�����16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(OutProfitViewVO.VDEF16, vdef16);
  }

  /**
   * �����Զ�����17
   * 
   * @param vdef17 �Զ�����17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(OutProfitViewVO.VDEF17, vdef17);
  }

  /**
   * �����Զ�����18
   * 
   * @param vdef18 �Զ�����18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(OutProfitViewVO.VDEF18, vdef18);
  }

  /**
   * �����Զ�����19
   * 
   * @param vdef19 �Զ�����19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(OutProfitViewVO.VDEF19, vdef19);
  }

  /**
   * �����Զ�����2
   * 
   * @param vdef2 �Զ�����2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(OutProfitViewVO.VDEF2, vdef2);
  }

  /**
   * �����Զ�����20
   * 
   * @param vdef20 �Զ�����20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(OutProfitViewVO.VDEF20, vdef20);
  }

  /**
   * �����Զ�����3
   * 
   * @param vdef3 �Զ�����3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(OutProfitViewVO.VDEF3, vdef3);
  }

  /**
   * �����Զ�����4
   * 
   * @param vdef4 �Զ�����4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(OutProfitViewVO.VDEF4, vdef4);
  }

  /**
   * �����Զ�����5
   * 
   * @param vdef5 �Զ�����5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(OutProfitViewVO.VDEF5, vdef5);
  }

  /**
   * �����Զ�����6
   * 
   * @param vdef6 �Զ�����6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(OutProfitViewVO.VDEF6, vdef6);
  }

  /**
   * �����Զ�����7
   * 
   * @param vdef7 �Զ�����7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(OutProfitViewVO.VDEF7, vdef7);
  }

  /**
   * �����Զ�����8
   * 
   * @param vdef8 �Զ�����8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(OutProfitViewVO.VDEF8, vdef8);
  }

  /**
   * �����Զ�����9
   * 
   * @param vdef9 �Զ�����9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(OutProfitViewVO.VDEF9, vdef9);
  }

  /**
   * ��ȡ�Զ�����1
   * 
   * @return �Զ�����1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF1);
  }

  /**
   * ��ȡ�Զ�����10
   * 
   * @return �Զ�����10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF10);
  }

  /**
   * ��ȡ�Զ�����11
   * 
   * @return �Զ�����11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF11);
  }

  /**
   * ��ȡ�Զ�����12
   * 
   * @return �Զ�����12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF12);
  }

  /**
   * ��ȡ�Զ�����13
   * 
   * @return �Զ�����13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF13);
  }

  /**
   * ��ȡ�Զ�����14
   * 
   * @return �Զ�����14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF14);
  }

  /**
   * ��ȡ�Զ�����15
   * 
   * @return �Զ�����15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF15);
  }

  /**
   * ��ȡ�Զ�����16
   * 
   * @return �Զ�����16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF16);
  }

  /**
   * ��ȡ�Զ�����17
   * 
   * @return �Զ�����17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF17);
  }

  /**
   * ��ȡ�Զ�����18
   * 
   * @return �Զ�����18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF18);
  }

  /**
   * ��ȡ�Զ�����19
   * 
   * @return �Զ�����19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF19);
  }

  /**
   * ��ȡ�Զ�����2
   * 
   * @return �Զ�����2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF2);
  }

  /**
   * ��ȡ�Զ�����20
   * 
   * @return �Զ�����20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF20);
  }

  /**
   * ��ȡ�Զ�����3
   * 
   * @return �Զ�����3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF3);
  }

  /**
   * ��ȡ�Զ�����4
   * 
   * @return �Զ�����4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF4);
  }

  /**
   * ��ȡ�Զ�����5
   * 
   * @return �Զ�����5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF5);
  }

  /**
   * ��ȡ�Զ�����6
   * 
   * @return �Զ�����6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF6);
  }

  /**
   * ��ȡ�Զ�����7
   * 
   * @return �Զ�����7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF7);
  }

  /**
   * ��ȡ�Զ�����8
   * 
   * @return �Զ�����8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF8);
  }

  /**
   * ��ȡ�Զ�����9
   * 
   * @return �Զ�����9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF9);
  }

  /**
   * �����Զ�����1
   * 
   * @param vbdef1 �Զ�����1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(OutProfitViewVO.VBDEF1, vbdef1);
  }

  /**
   * �����Զ�����10
   * 
   * @param vbdef10 �Զ�����10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(OutProfitViewVO.VBDEF10, vbdef10);
  }

  /**
   * �����Զ�����11
   * 
   * @param vbdef11 �Զ�����11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(OutProfitViewVO.VBDEF11, vbdef11);
  }

  /**
   * �����Զ�����12
   * 
   * @param vbdef12 �Զ�����12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(OutProfitViewVO.VBDEF12, vbdef12);
  }

  /**
   * �����Զ�����13
   * 
   * @param vbdef13 �Զ�����13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(OutProfitViewVO.VBDEF13, vbdef13);
  }

  /**
   * �����Զ�����14
   * 
   * @param vbdef14 �Զ�����14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(OutProfitViewVO.VBDEF14, vbdef14);
  }

  /**
   * �����Զ�����15
   * 
   * @param vbdef15 �Զ�����15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(OutProfitViewVO.VBDEF15, vbdef15);
  }

  /**
   * �����Զ�����16
   * 
   * @param vbdef16 �Զ�����16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(OutProfitViewVO.VBDEF16, vbdef16);
  }

  /**
   * �����Զ�����17
   * 
   * @param vbdef17 �Զ�����17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(OutProfitViewVO.VBDEF17, vbdef17);
  }

  /**
   * �����Զ�����18
   * 
   * @param vbdef18 �Զ�����18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(OutProfitViewVO.VBDEF18, vbdef18);
  }

  /**
   * �����Զ�����19
   * 
   * @param vbdef19 �Զ�����19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(OutProfitViewVO.VBDEF19, vbdef19);
  }

  /**
   * �����Զ�����2
   * 
   * @param vbdef2 �Զ�����2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(OutProfitViewVO.VBDEF2, vbdef2);
  }

  /**
   * �����Զ�����20
   * 
   * @param vbdef20 �Զ�����20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(OutProfitViewVO.VBDEF20, vbdef20);
  }

  /**
   * �����Զ�����3
   * 
   * @param vbdef3 �Զ�����3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(OutProfitViewVO.VBDEF3, vbdef3);
  }

  /**
   * �����Զ�����4
   * 
   * @param vbdef4 �Զ�����4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(OutProfitViewVO.VBDEF4, vbdef4);
  }

  /**
   * �����Զ�����5
   * 
   * @param vbdef5 �Զ�����5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(OutProfitViewVO.VBDEF5, vbdef5);
  }

  /**
   * �����Զ�����6
   * 
   * @param vbdef6 �Զ�����6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(OutProfitViewVO.VBDEF6, vbdef6);
  }

  /**
   * �����Զ�����7
   * 
   * @param vbdef7 �Զ�����7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(OutProfitViewVO.VBDEF7, vbdef7);
  }

  /**
   * �����Զ�����8
   * 
   * @param vbdef8 �Զ�����8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(OutProfitViewVO.VBDEF8, vbdef8);
  }

  /**
   * �����Զ�����9
   * 
   * @param vbdef9 �Զ�����9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(OutProfitViewVO.VBDEF9, vbdef9);
  }

  /**
   * ������������
   * 
   * @return ������������id
   */
  public String getCchanneltypeid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CCHANNELTYPEID);
  }

  /**
   * ����������������
   * 
   * @param cchanneltypeid
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    super.setAttributeValue(OutProfitViewVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * ������Ʒ
   * 
   * @param flargess ��Ʒ
   */
  public void seFlargess(UFBoolean flargess) {
    this.setAttributeValue(OutProfitViewVO.FLARGESS, flargess);
  }

  /**
   * 
   * @return �ѳɱ�����Ľ��
   */
  public UFBoolean getFlargess() {
    return (UFBoolean) super.getAttributeValue(OutProfitViewVO.FLARGESS);
  }
}
