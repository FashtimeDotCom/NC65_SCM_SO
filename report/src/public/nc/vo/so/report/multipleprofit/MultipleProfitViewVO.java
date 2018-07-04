package nc.vo.so.report.multipleprofit;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * �ۺ�ë������ʵ��ViewVO
 * 
 * @since 6.3
 * @version 2012-10-18 14:56:33
 * @author zhangkai4
 */
public class MultipleProfitViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
    // ��ͼ�����ഴ���࣬��������ȡԪ����
        DataViewMetaFactory.getInstance().getDataViewMeta(
            MultipleProfitViewMeta.class);
    return viewMeta;
  }

  /**
   * ���������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * ��Դϵͳ
   */
  public static final String SOURCESYSTEM = "sourcesystem";

  /**
   * ������֯
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * ���۲���
   */
  public static final String CDPTID = "cdptid";

  /**
   * ҵ��Ա
   */
  public static final String CBIZID = "cbizid";

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
  public static final String CMATERIALID = "cmaterialid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ���κ�
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * ����
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * ��Ʒ��
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * Ӧ��������
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * Ӧ����˰���
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * Ӧ����˰����
   */
  public static final String NTOTALRECEIVEPRICE = "ntotalreceivprice";

  /**
   * �ɱ�������
   */
  public static final String NMAINNUM = "nmainnum";

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
   * 
   * @return ������
   */
  public UFDouble getNmainnum() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NMAINNUM);
  }

  /**
   * ����������
   * 
   * @param nmainnum
   */
  public void setNmainnum(UFDouble nmainnum) {
    super.setAttributeValue(MultipleProfitViewVO.NMAINNUM, nmainnum);
  }

  /**
   * 
   * @return Ӧ��������
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * ����Ӧ��������
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super.setAttributeValue(MultipleProfitViewVO.NSHOULDRECEIVNUM,
        nshouldreceivnum);
  }

  /**
   * 
   * @return Ӧ����˰����
   */
  public UFDouble getNtotalReceiveprice() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NTOTALRECEIVEPRICE);
  }

  /**
   * ����Ӧ����˰����
   * 
   * @param ntotalreceivprice
   * 
   */
  public void setNtotalReceiveprice(UFDouble ntotalreceivprice) {
    super.setAttributeValue(MultipleProfitViewVO.NTOTALRECEIVEPRICE,
        ntotalreceivprice);
  }

  /**
   * 
   * @return ҵ��Ա
   */
  public String getCbizid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CBIZID);
  }

  /**
   * 
   * @return �����ͻ�
   */
  public String getCcustomerid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CCUSTOMERID);
  }

  /**
   * 
   * @return ����
   */
  public String getCdptid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CDPTID);
  }

  /**
   * 
   * @return ����
   */
  public String getCmaterialid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CMATERIALID);
  }

  /**
   * 
   * @return ����
   */
  public String getCorigcurrencyid() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.CORIGCURRENCYID);
  }

  /**
   * 
   * @return ��Ʒ��
   */
  public String getCprodlineid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CPRODLINEID);
  }

  /**
   * 
   * @return ������֯
   */
  public String getCsaleorgid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CSALEORGID);
  }

  /**
   * 
   * @return �ɱ�����
   */
  public UFDouble getNcostprice() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NCOSTPRICE);
  }

  /**
   * 
   * @return ë��
   */
  public UFDouble getNprofitmny() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NPROFITMNY);
  }

  /**
   * 
   * @return ë����
   */
  public UFDouble getNprofitrate() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NPROFITRATE);
  }

  /**
   * 
   * @return �ɱ����
   */
  public UFDouble getNtotalcostmny() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NTOTALCOSTMNY);
  }

  /**
   * 
   * @return Ӧ����˰���
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return ��������
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.PK_AREACL);
  }

  /**
   * 
   * @return �ͻ���������
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return �ͻ����۷���
   */
  public String getPk_custsaleclass() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return ���ϻ�������
   */
  public String getPk_marbasclass() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return �������۷���
   */
  public String getPk_marsaleclass() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.PK_MARSALECLASS);
  }

  /**
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.VBATCHCODE);
  }

  /**
   * ����ҵ��Ա
   * 
   * @param cbizid
   */
  public void setCbizid(String cbizid) {
    super.setAttributeValue(MultipleProfitViewVO.CBIZID, cbizid);
  }

  /**
   * ���ö����ͻ�
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    super.setAttributeValue(MultipleProfitViewVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * ���ò���
   * 
   * @param cdptid
   */
  public void setCdptid(String cdptid) {
    super.setAttributeValue(MultipleProfitViewVO.CDPTID, cdptid);
  }

  /**
   * ��������
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    super.setAttributeValue(MultipleProfitViewVO.CMATERIALID, cmaterialid);
  }

  /**
   * ���ñ���
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(MultipleProfitViewVO.CORIGCURRENCYID,
        corigcurrencyid);
  }

  /**
   * ���ò�Ʒ��
   * 
   * @param cprodlineid
   */
  public void setCprodlineid(String cprodlineid) {
    super.setAttributeValue(MultipleProfitViewVO.CPRODLINEID, cprodlineid);
  }

  /**
   * ����������֯
   * 
   * @param csaleorgid
   */
  public void setCsaleorgid(String csaleorgid) {
    super.setAttributeValue(MultipleProfitViewVO.CSALEORGID, csaleorgid);
  }

  /**
   * ���óɱ�����
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    super.setAttributeValue(MultipleProfitViewVO.NCOSTPRICE, ncostprice);
  }

  /**
   * ����ë��
   * 
   * @param nprofitmny
   */
  public void setNprofitmny(UFDouble nprofitmny) {
    super.setAttributeValue(MultipleProfitViewVO.NPROFITMNY, nprofitmny);
  }

  /**
   * ����ë����
   * 
   * @param nprofitrate
   */
  public void setNprofitrate(UFDouble nprofitrate) {
    super.setAttributeValue(MultipleProfitViewVO.NPROFITRATE, nprofitrate);
  }

  /**
   * ���óɱ����
   * 
   * @param ntotalcostmny
   */
  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    super.setAttributeValue(MultipleProfitViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  /**
   * ����Ӧ����˰���
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super.setAttributeValue(MultipleProfitViewVO.NTOTALRECEIVMNY,
        ntotalreceivmny);
  }

  /**
   * ���õ�������
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(MultipleProfitViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * ���ÿͻ���������
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(MultipleProfitViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * ���ÿͻ����۷���
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super.setAttributeValue(MultipleProfitViewVO.PK_CUSTSALECLASS,
        pk_custsaleclass);
  }

  /**
   * �������ϻ�������
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super
        .setAttributeValue(MultipleProfitViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * �����������۷���
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(MultipleProfitViewVO.PK_MARSALECLASS,
        pk_marsaleclass);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(MultipleProfitViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(MultipleProfitViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.PK_ORG);

  }

  /**
   * ���������֯
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    super.setAttributeValue(MultipleProfitViewVO.CUNITID, pk_org);
  }

  /**
   * ��Դϵͳ
   * 
   * @return sourcesystem
   */
  public String getSourcesystem() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.SOURCESYSTEM);
  }

  /**
   * ��Դϵͳ
   * 
   * @param sourcesystem
   */
  public void setSourcesystem(String sourcesystem) {
    super.setAttributeValue(MultipleProfitViewVO.SOURCESYSTEM, sourcesystem);
  }
}
