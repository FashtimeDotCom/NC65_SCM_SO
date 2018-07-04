package nc.vo.so.m30.sobalance.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SoBalanceBVO extends SuperVO {

  public static final String ENTITYNAME = "so.so_balance_b";

  // ����
  public static final String CARORIGCURRENCYID = "carorigcurrencyid";

  // �տ��ʵ��
  public static final String CPAYBILLID = "cpaybillid";

  // �տ��ʵ��
  public static final String CPAYBILLROWID = "cpaybillrowid";

  // ��Ʒ��
  public static final String CPRODLINEID = "cprodlineid";

  // �����տ������ʵ��
  public static final String CSOBALANCEBID = "csobalancebid";

  // �����տ������ʵ��_����
  public static final String CSOBALANCEID = "csobalanceid";

  // ��������
  public static final String DARBALANCEDATE = "darbalancedate";

  // ��������
  public static final String DARBILLDATE = "darbilldate";

  // dr
  public static final String DR = "dr";

  // ��������
  public static final String FIBALTYPE = "fibaltype";

  // �տ�Ѳ���������
  public static final String NORIGACCBALMNY = "norigaccbalmny";

  // �����������
  public static final String NORIGORDBALMNY = "norigordbalmny";

  // ���ζ����������
  public static final String NORIGTHISBALMNY = "norigthisbalmny";

  // �����н��
  public static final String NORIGARMNY = "norigarmny";

  // �����������
  public static final String NORIGOTHERBALMNY = "norigotherbalmny";

  // ������֯
  public static final String PK_ORG = "pk_org";

  // ʱ���
  public static final String TS = "ts";

  // ���ݺ�
  public static final String VARBILLCODE = "varbillcode";

  // �տ��޶����Ԥ��
  public static final String BPRECEIVEFLAG = "bpreceiveflag";

  private static final long serialVersionUID = 9004142056883934589L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SoBalanceBVO.ENTITYNAME);
    return meta;
  }

  public String getCarorigcurrencyid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CARORIGCURRENCYID);
  }

  public void setCarorigcurrencyid(String carorigcurrencyid) {
    this.setAttributeValue(SoBalanceBVO.CARORIGCURRENCYID, carorigcurrencyid);
  }

  public String getCpaybillid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CPAYBILLID);
  }

  public void setCpaybillid(String cpaybillid) {
    this.setAttributeValue(SoBalanceBVO.CPAYBILLID, cpaybillid);
  }

  public String getCpaybillrowid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CPAYBILLROWID);
  }

  public void setCpaybillrowid(String cpaybillrowid) {
    this.setAttributeValue(SoBalanceBVO.CPAYBILLROWID, cpaybillrowid);
  }

  public String getCprodlineid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CPRODLINEID);
  }

  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(SoBalanceBVO.CPRODLINEID, cprodlineid);
  }

  public String getCsobalancebid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CSOBALANCEBID);
  }

  public void setCsobalancebid(String csobalancebid) {
    this.setAttributeValue(SoBalanceBVO.CSOBALANCEBID, csobalancebid);
  }

  public String getCsobalanceid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CSOBALANCEID);
  }

  public void setCsobalanceid(String csobalanceid) {
    this.setAttributeValue(SoBalanceBVO.CSOBALANCEID, csobalanceid);
  }

  public UFDate getDarbalancedate() {
    return (UFDate) this.getAttributeValue(SoBalanceBVO.DARBALANCEDATE);
  }

  public void setDarbalancedate(UFDate darbalancedate) {
    this.setAttributeValue(SoBalanceBVO.DARBALANCEDATE, darbalancedate);
  }

  public UFDate getDarbilldate() {
    return (UFDate) this.getAttributeValue(SoBalanceBVO.DARBILLDATE);
  }

  public void setDarbilldate(UFDate darbilldate) {
    this.setAttributeValue(SoBalanceBVO.DARBILLDATE, darbilldate);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SoBalanceBVO.DR);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SoBalanceBVO.DR, dr);
  }

  public Integer getFibaltype() {
    return (Integer) this.getAttributeValue(SoBalanceBVO.FIBALTYPE);
  }

  public void setFibaltype(Integer fibaltype) {
    this.setAttributeValue(SoBalanceBVO.FIBALTYPE, fibaltype);
  }

  public UFDouble getNorigaccbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGACCBALMNY);
  }

  public void setNorigaccbalmny(UFDouble norigaccbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGACCBALMNY, norigaccbalmny);
  }

  public UFDouble getNorigordbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGORDBALMNY);
  }

  public void setNorigordbalmny(UFDouble norigordbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGORDBALMNY, norigordbalmny);
  }

  public UFDouble getNorigthisbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGTHISBALMNY);
  }

  public void setNorigthisbalmny(UFDouble norigthisbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGTHISBALMNY, norigthisbalmny);
  }

  public UFDouble getNorigarmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGARMNY);
  }

  public void setNorigarmny(UFDouble norigarmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGARMNY, norigarmny);
  }

  public UFDouble getNorigotherbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGOTHERBALMNY);
  }

  public void setNorigotherbalmny(UFDouble norigotherbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGOTHERBALMNY, norigotherbalmny);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SoBalanceBVO.PK_ORG);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SoBalanceBVO.PK_ORG, pk_org);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SoBalanceBVO.TS);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SoBalanceBVO.TS, ts);
  }

  public String getVarbillcode() {
    return (String) this.getAttributeValue(SoBalanceBVO.VARBILLCODE);
  }

  public void setVarbillcode(String varbillcode) {
    this.setAttributeValue(SoBalanceBVO.VARBILLCODE, varbillcode);
  }

  public UFBoolean getBpreceiveflag() {
    return (UFBoolean) this.getAttributeValue(SoBalanceBVO.BPRECEIVEFLAG);
  }

  public void setBpreceiveflag(UFBoolean bpreceiveflag) {
    this.setAttributeValue(SoBalanceBVO.BPRECEIVEFLAG, bpreceiveflag);
  }

}
