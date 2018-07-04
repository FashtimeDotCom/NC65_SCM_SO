package nc.vo.so.salequotation.entity;

//import nc.vo.price.pub.entity.FindPriceParaVO;

import nc.vo.pub.lang.UFDateTime;

import java.io.Serializable;

/**
 * ѯ��ʷ�۲���VO
 * 
 * @since 6.0
 * @version 2011-9-14 ����07:44:41
 * @author ������
 */

public class FindHistoryPriceParameter implements Serializable {

  private static final long serialVersionUID = 9049576358980885193L;
   
  
  //ҵ��Ա
  private String cemployeeid;
  
  //���䷽ʽ
  private String csendtypeid;
  
  //�������°汾
  private String pk_dept;
  
  //�տ�Э��
  private String pk_payterm;
  
  //����
  private String pk_material;  
  
  //����
  private String pk_group; 
  
  //������֯
  private String pk_org;
  
  //ѯ������
  private UFDateTime tpricedate;
  
  //��������
  private String vtrantype;
  
  //����
  private String pk_currtype;
  
  //���۵�λ
  private String cqtunitid;
  
  //�ջ�����
  private String pk_areacl;
  
  //���㷽ʽ
  private String pk_balatype;
  
  //��������
  private String pk_channeltype;
  
  //�ͻ�
  private String pk_customer;
  
  //�����ȼ�
  private String pk_qualitylevel;

  public void setCsendtypeid(String csendtypeid) {
    this.csendtypeid = csendtypeid;
  }
  
  public void setPk_dept(String pk_dept) {
    this.pk_dept = pk_dept;
  }
  
  public void setPk_payterm(String pk_payterm) {
    this.pk_payterm = pk_payterm;
  }
  
  public void setPk_material(String pk_material){
    this.pk_material = pk_material;
  }
  
  public void setPk_group(String pk_group){
    this.pk_group = pk_group;
  }
  
  public void setPk_org(String pk_org){
    this.pk_org = pk_org;
  }
  
  public void setTpricedate(UFDateTime tpricedate){
    this.tpricedate = tpricedate;
  }
  
  public void setVtrantype(String vtrantype) {
    this.vtrantype = vtrantype;
  }
  
  public void setPk_currtype(String pk_currtype) {
    this.pk_currtype = pk_currtype;
  }
  
  public void setCqtunitid(String cqtunitid) {
    this.cqtunitid = cqtunitid;
  }
  
  public void setPk_areacl(String pk_areacl) {
    this.pk_areacl = pk_areacl;
  } 
  
  public void setPk_balatype(String pk_balatype) {
    this.pk_balatype = pk_balatype;
  } 
  
  public void setPk_channeltype(String pk_channeltype) {
    this.pk_channeltype = pk_channeltype;
  } 
  
  public void setPk_customer(String pk_customer) {
    this.pk_customer = pk_customer;
  }
  
  public void setCemployeeid(String cemployeeid) {
    this.cemployeeid = cemployeeid;
  }
  
  public void setPk_qualitylevel(String pk_qualitylevel) {
    this.pk_qualitylevel = pk_qualitylevel;
  }

  public String getCemployeeid() {
    return this.cemployeeid;
  }
  
  public String getCsendtypeid() {
    return this.csendtypeid;
  }
  
  public String getPk_dept() {
    return this.pk_dept;
  }
  
  public String getPk_payterm() {
    return this.pk_payterm;
  }
  
  public String getPk_material() {
    return this.pk_material;
  }
  
  public String getPk_org() {
    return this.pk_org;
  }
  
  public String getPk_group() {
    return this.pk_group;
  }
  
  public UFDateTime getTpricedate() {
    return this.tpricedate;
  }
  
  public String getVtrantype() {
    return this.vtrantype;
  }
  
  public String getPk_currtype() {
    return this.pk_currtype;
  }
  
  public String getCqtunitid() {
    return this.cqtunitid;
  }  
  public String getPk_areacl() {
    return this.pk_areacl;
  }
  
  public String getPk_balatype() {
    return this.pk_balatype;
  }
  
  public String getPk_channeltype() {
    return this.pk_channeltype;
  }
  
  public String getPk_customer() {
    return this.pk_customer;
  }
  
  public String getPk_qualitylevel() {
    return this.pk_qualitylevel;
  }
}
