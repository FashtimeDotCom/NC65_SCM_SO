package nc.vo.so.pub.rule;


/**
 * �ɱ����ѯ����
 * 
 * @since 6.5
 * @version 2015-6-13 ����02:36:21
 * @author ����
 */
public class CostRegionPara {
  
  /**
   * ���������֯old
   */
  private String stockorgid;

  /**
  * �ֿ�id
  */
  private String stordocid;

  /**
   * ������������
   */
  private String ordertantype;

  /**
   * �������oid
   */
  private String cinfinanceorgid;
  
  /**
   * ����oid
   */
  private String Cmaterialid;
  
  
  /**
   * �ɱ���id(�˲������ô���)
   */
  private String  ccostorgid;
  
  public String getCcostorgid() {
    return ccostorgid;
  }

  public void setCcostorgid(String ccostorgid) {
    this.ccostorgid = ccostorgid;
  }



  public String getStockorgid() {
    return stockorgid;
  }

  public void setStockorgid(String stockorgid) {
    this.stockorgid = stockorgid;
  }

  public String getStordocid() {
    return stordocid;
  }

  public void setStordocid(String stordocid) {
    this.stordocid = stordocid;
  }

  public String getOrdertantype() {
    return ordertantype;
  }

  public void setOrdertantype(String ordertantype) {
    this.ordertantype = ordertantype;
  }

  public String getCinfinanceorgid() {
    return cinfinanceorgid;
  }

  public void setCinfinanceorgid(String cinfinanceorgid) {
    this.cinfinanceorgid = cinfinanceorgid;
  }

  public String getCmaterialid() {
    return Cmaterialid;
  }

  public void setCmaterialid(String cmaterialid) {
    Cmaterialid = cmaterialid;
  }


}
