package nc.vo.so.entry;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;
import nc.vo.scmpub.payterm.recv.AbstractRecvPlanVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class RecPlanVO extends AbstractRecvPlanVO {

  /** Ԥ�ձ�� */
  public static final String BPREFLAG = "bpreflag";

  /** �տ�Э��� */
  public static final String CPAYTERMID = "cpaytermid";

  /** ���������� */
  public static final String DBEGINDATE = "dbegindate";

  /** ���ڵ����� */
  public static final String DENDDATE = "denddate";

  /** �ڿص������� */
  public static final String DINSIDEENDDATE = "dinsideenddate";

  /** dr */
  public static final String DR = "dr";

  /** ��Ч���� */
  public static final String FEFFDATETYPE = "feffdatetype";

  /** �տ��� */
  public static final String IACCOUNTTERMNO = "iaccounttermno";

  /** �������� */
  public static final String IITERMDAYS = "iitermdays";

  /** ��� */
  public static final String NORIGMNY = "norigmny";

  /** �տ���� */
  public static final String NRATE = "nrate";

  /** ���տ��� */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /**
   * ԭ��
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** ts */
  public static final String TS = "ts";

  public static final String VBILLCODE = "vbillcode";

  private static final long serialVersionUID = 4684054072960817194L;

  /** Ԥ�ձ�� getter ���� */
  public UFBoolean getBpreflag() {
    return (UFBoolean) this.getAttributeValue(RecPlanVO.BPREFLAG);
  }

  /** ���������� getter ���� */
  public UFDate getDbegindate() {
    return (UFDate) this.getAttributeValue(RecPlanVO.DBEGINDATE);
  }

  /** ���ڵ����� getter ���� */
  @Override
  public UFDate getDenddate() {
    return (UFDate) this.getAttributeValue(RecPlanVO.DENDDATE);
  }

  /** �ڿص������� getter ���� */
  public UFDate getDinsideenddate() {
    return (UFDate) this.getAttributeValue(RecPlanVO.DINSIDEENDDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(RecPlanVO.DR);
  }

  /** ��Ч���� getter ���� */
  public Integer getFeffdatetype() {
    return (Integer) this.getAttributeValue(RecPlanVO.FEFFDATETYPE);
  }

  /** �տ��� getter ���� */
  public Integer getIaccounttermno() {
    return (Integer) this.getAttributeValue(RecPlanVO.IACCOUNTTERMNO);
  }

  /** �������� getter ���� */
  public Integer getIitermdays() {
    return (Integer) this.getAttributeValue(RecPlanVO.IITERMDAYS);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(
            NCModule.SO.getName().toLowerCase() + "." + "recvplan");
    return meta;
  }

  /** ��� getter ���� */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(RecPlanVO.NORIGMNY);
  }

  /** �տ���� getter ���� */
  public UFDouble getNrate() {
    return (UFDouble) this.getAttributeValue(RecPlanVO.NRATE);
  }

  /** ���տ��� getter ���� */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(RecPlanVO.NTOTALORIGMNY);
  }

  /** �տ�Э��� */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(RecPlanVO.CPAYTERMID);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(RecPlanVO.TS);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(SaleOrderHVO.VBILLCODE);
  }

  /** Ԥ�ձ�� setter ���� */
  @Override
  public void setBpreflag(UFBoolean bpreflag) {
    this.setAttributeValue(RecPlanVO.BPREFLAG, bpreflag);
  }

  /** �к� setter ���� */
  @Override
  public void setCrowno(String crowno) {
    this.setAttributeValue(AbstractPayPlanVO.CROWNO, crowno);
  }

  /** ���������� setter ���� */
  @Override
  public void setDbegindate(UFDate dbegindate) {
    this.setAttributeValue(RecPlanVO.DBEGINDATE, dbegindate);
  }

  /** ���ڵ����� setter ���� */
  @Override
  public void setDenddate(UFDate denddate) {
    this.setAttributeValue(RecPlanVO.DENDDATE, denddate);
  }

  /** �ڿص������� setter ���� */
  @Override
  public void setDinsideenddate(UFDate dinsideenddate) {
    this.setAttributeValue(RecPlanVO.DINSIDEENDDATE, dinsideenddate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(RecPlanVO.DR, dr);
  }

  /** ��Ч���� setter ���� */
  @Override
  public void setFeffdatetype(String feffdatetype) {
    this.setAttributeValue(RecPlanVO.FEFFDATETYPE, feffdatetype);
  }

  /** �տ��� setter ���� */
  @Override
  public void setIaccounttermno(Integer iaccounttermno) {
    this.setAttributeValue(RecPlanVO.IACCOUNTTERMNO, iaccounttermno);
  }

  /** �������� setter ���� */
  @Override
  public void setIitermdays(Integer iitermdays) {
    this.setAttributeValue(RecPlanVO.IITERMDAYS, iitermdays);
  }

  /** ��� setter ���� */
  @Override
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(RecPlanVO.NORIGMNY, norigmny);
  }

  /** �տ���� setter ���� */
  @Override
  public void setNrate(UFDouble nrate) {
    this.setAttributeValue(RecPlanVO.NRATE, nrate);
  }

  /** ���տ��� setter ���� */
  @Override
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(RecPlanVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** �տ�Э��� */
  @Override
  public void setPk_payterm(String pk_paytem) {
    this.setAttributeValue(RecPlanVO.CPAYTERMID, pk_paytem);
  }

  @Override
  public void setPk_paytermch(String pk_paymentch) {
    // TODO
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(RecPlanVO.TS, ts);
  }

  /** ���ݺ� **/
  @Override
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SaleOrderHVO.VBILLCODE, vbillcode);
  }

  /**
   * ��ȡԭ��
   * 
   * @return ԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SaleOrderHVO.CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyid ԭ��
   */
  @Override
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SaleOrderHVO.CORIGCURRENCYID, corigcurrencyid);
  }
}
