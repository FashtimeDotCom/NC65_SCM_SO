package nc.pubitf.so.custmatrel.opc.mecc;

/**
 * ICustMatRelForOPC.filterData����ڼ��������ݽṹ
 * 
 * �ͻ�������OID��������֯OID
 * 
 * @since 6.0
 * @version 2011-12-28 ����03:39:45
 * @author zhangcheng
 */

public class CustMatRelParaForOPCVO {

  /**
   * �ͻ�
   */
  private String pk_customer;

  /**
   * ����OID
   */
  private String pk_material;

  /**
   * ������֯OID
   */
  private String pk_org;

  public String getPk_customer() {
    return this.pk_customer;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public void setPk_customer(String pk_customer) {
    this.pk_customer = pk_customer;
  }

  public void setPk_material(String pk_material) {
    this.pk_material = pk_material;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }
}
