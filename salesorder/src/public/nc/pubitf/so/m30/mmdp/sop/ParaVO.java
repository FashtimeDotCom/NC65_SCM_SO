package nc.pubitf.so.m30.mmdp.sop;

import java.io.Serializable;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 ����02:52:18
 * @author ô��
 */
public class ParaVO implements Serializable {

  private static final long serialVersionUID = -2171443999763018214L;

  /**
   * ����ԭʼIDS
   */
  private String[] cmaterialids;

  /**
   * �����֯ID
   */
  private String csendstockorgid;

  public String[] getCmaterialids() {
    return this.cmaterialids;
  }

  public String getCsendstockorgid() {
    return this.csendstockorgid;
  }

  public void setCmaterialids(String[] cmaterialids) {
    this.cmaterialids = cmaterialids;
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.csendstockorgid = csendstockorgid;
  }

}
