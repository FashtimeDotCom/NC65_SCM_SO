package nc.pubitf.so.m30.mmdp.aid;

import java.io.Serializable;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.pub.enumeration.BillStatusEnum;

/**
 * ȡ��ָ�����������֯+����״̬+����+��������+��������+����޸����ڵ����۶�����ϸ�Ľӿڲ���
 * 
 * @since 6.0
 * @version 2011-12-5 ����03:22:00
 * @author ô��
 */
public class ParaVO implements Serializable {

  private static final long serialVersionUID = 1849993298414951727L;
  
  /**
   * ���������б��ɿ�
   */
  private Set<String> cbilltranstypeids;

  /**
   * ���۶�����,�ɿգ��ϸ�ƥ��
   */
  private String vbillcode;

  /**
   * �ͻ������루V63������
   */
  private Set<String> ccustmaterialids;

  /**
   * ����ر�,�ɿ�,�մ���ȫ��
   */
  private UFBoolean bboutendflag;

  /**
   * �����ͻ�
   */
  private Set<String> ccustomerids;

  /**
   * ����
   */
  private Set<String> cmaterialids;

  /**
   * ��Ʒ��
   */
  private Set<String> cproductorids;

  /**
   * ��Ŀ
   */
  private Set<String> cprojectids;

  /**
   * �����֯
   */
  private Set<String> csendstockorgids;

  /**
   * ��Ӧ��
   */
  private Set<String> cvendorids;

  /**
   * �������ڿ�ʼʱ��
   */
  private UFDate dbeginbilldate;

  /**
   * ����޸�ʱ�� --��ʼʱ��
   */
  private UFDate dbeginmodifieddate;

  /**
   * ��������--��������
   */
  private UFDate dendbilldate;

  /**
   * ����޸�ʱ��---��������
   */
  private UFDate dendmodifieddate;

  /**
   * ����״̬
   */
  private Set<BillStatusEnum> fstatusflags;

  private Set<String> vfree10s;

  private Set<String> vfree1s;

  private Set<String> vfree2s;

  private Set<String> vfree3s;

  private Set<String> vfree4s;

  private Set<String> vfree5s;

  private Set<String> vfree6s;

  private Set<String> vfree7s;

  private Set<String> vfree8s;

  private Set<String> vfree9s;

  /**
   * �����루V636������
   */
  private Set<String> cmffileid;
  
  /**
   * 
   * @return ������������
   */
  public Set<String> getCbilltranstypeids() {
    return this.cbilltranstypeids;
  }
  
  /**
   * 
   * @param cbilltranstypeids
   */
  public void setCbilltranstypeids(Set<String> cbilltranstypeids) {
    this.cbilltranstypeids = cbilltranstypeids;
  }
  
  /**
   * 
   * 
   * @return d
   */
  public Set<String> getCcustmaterialids() {
    return this.ccustmaterialids;
  }

  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

  public String getVbillcode() {
    return this.vbillcode;
  }

  public Set<String> getCcustomerids() {
    return this.ccustomerids;
  }

  public Set<String> getCmaterialids() {
    return this.cmaterialids;
  }

  public Set<String> getCproductorids() {
    return this.cproductorids;
  }

  public Set<String> getCprojectids() {
    return this.cprojectids;
  }

  public Set<String> getCsendstockorgids() {
    return this.csendstockorgids;
  }

  public Set<String> getCvendorids() {
    return this.cvendorids;
  }

  public UFDate getDbeginbilldate() {
    return this.dbeginbilldate;
  }

  public UFDate getDbeginmodifieddate() {
    return this.dbeginmodifieddate;
  }

  public UFDate getDendbilldate() {
    return this.dendbilldate;
  }

  public UFDate getDendmodifieddate() {
    return this.dendmodifieddate;
  }

  public Set<BillStatusEnum> getFstatusflags() {
    return this.fstatusflags;
  }

  public Set<String> getVfree10s() {
    return this.vfree10s;
  }

  public Set<String> getVfree1s() {
    return this.vfree1s;
  }

  public Set<String> getVfree2s() {
    return this.vfree2s;
  }

  public Set<String> getVfree3s() {
    return this.vfree3s;
  }

  public Set<String> getVfree4s() {
    return this.vfree4s;
  }

  public Set<String> getVfree5s() {
    return this.vfree5s;
  }

  public Set<String> getVfree6s() {
    return this.vfree6s;
  }

  public Set<String> getVfree7s() {
    return this.vfree7s;
  }

  public Set<String> getVfree8s() {
    return this.vfree8s;
  }

  public Set<String> getVfree9s() {
    return this.vfree9s;
  }

  /**
   * 
   * 
   * @param ccustmaterialids
   */
  public void setCcustmaterialids(Set<String> ccustmaterialids) {
    this.ccustmaterialids = ccustmaterialids;
  }

  public void setBboutendflag(UFBoolean bboutendflag) {
    this.bboutendflag = bboutendflag;
  }

  public void setCcustomerids(Set<String> ccustomerids) {
    this.ccustomerids = ccustomerids;
  }

  public void setCmaterialids(Set<String> cmaterialids) {
    this.cmaterialids = cmaterialids;
  }

  public void setCproductorids(Set<String> cproductorids) {
    this.cproductorids = cproductorids;
  }

  public void setCprojectids(Set<String> cprojectids) {
    this.cprojectids = cprojectids;
  }

  public void setCsendstockorgids(Set<String> csendstockorgids) {
    this.csendstockorgids = csendstockorgids;
  }

  public void setCvendorids(Set<String> cvendorids) {
    this.cvendorids = cvendorids;
  }

  public void setDbeginbilldate(UFDate dbeginbilldate) {
    this.dbeginbilldate = dbeginbilldate;
  }

  public void setVbillcode(String vbillcode) {
    this.vbillcode = vbillcode;
  }

  public void setDbeginmodifieddate(UFDate dbeginmodifieddate) {
    this.dbeginmodifieddate = dbeginmodifieddate;
  }

  public void setDendbilldate(UFDate dendbilldate) {
    this.dendbilldate = dendbilldate;
  }

  public void setDendmodifieddate(UFDate dendmodifieddate) {
    this.dendmodifieddate = dendmodifieddate;
  }

  public void setFstatusflags(Set<BillStatusEnum> fstatusflags) {
    this.fstatusflags = fstatusflags;
  }

  public void setVfree10s(Set<String> vfree10s) {
    this.vfree10s = vfree10s;
  }

  public void setVfree1s(Set<String> vfree1s) {
    this.vfree1s = vfree1s;
  }

  public void setVfree2s(Set<String> vfree2s) {
    this.vfree2s = vfree2s;
  }

  public void setVfree3s(Set<String> vfree3s) {
    this.vfree3s = vfree3s;
  }

  public void setVfree4s(Set<String> vfree4s) {
    this.vfree4s = vfree4s;
  }

  public void setVfree5s(Set<String> vfree5s) {
    this.vfree5s = vfree5s;
  }

  public void setVfree6s(Set<String> vfree6s) {
    this.vfree6s = vfree6s;
  }

  public void setVfree7s(Set<String> vfree7s) {
    this.vfree7s = vfree7s;
  }

  public void setVfree8s(Set<String> vfree8s) {
    this.vfree8s = vfree8s;
  }

  public void setVfree9s(Set<String> vfree9s) {
    this.vfree9s = vfree9s;
  }

  
  public Set<String> getCmffileid() {
    return this.cmffileid;
  }
  
  public void setCmffileid(Set<String> cmffileid) {
    this.cmffileid = cmffileid;
  }

}
