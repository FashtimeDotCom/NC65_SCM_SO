package nc.vo.so.m30.api.rest;

import java.io.Serializable;

/**
 * @description
 * ���۶��������ѯAPI����
 * 
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-13 ����3:28:25
 * @author ����
 */
public class SaleOrderQueryBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3272849931175957833L;

  /**
   *���ݺ�
   */
  private String billcode;

  /**
   * ��ȡ���ݺ�
   * 
   * @return ���ݺ�
   */
  public String getBillcode() {
    return billcode;
  }

  /**
   * ���õ��ݺ�
   * 
   * @param billcode ���ݺ�
   */
  public void setBillcode(String billcode) {
    this.billcode = billcode;
  }

}
