package nc.bs.so.pub;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;

/**
 * Ԥ���������۶��������۵�����Ҫ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID��������/�㷨����
 * <ol>
 * <li>���ù��������ȡ���������֯
 * <li>���ù��������ȡ���������֯ID��Ӧ����֯ID����������ID
 * <li>--���ù��������ȡĬ��������֯-- modify by zhangby5 ȡĬ��������֯Ҫ����ȡ�ֿ����
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-28 ����15:05:00
 * @author ������
 * @see
 */
public class SORelationOrgQuery {

  /*
   * �ͻ�
   */
  private String customerID;

  public String getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  /*
   * ������֯
   */
  private String saleOrg;

  public String getSaleOrg() {
    return this.saleOrg;
  }

  public void setSaleOrg(String saleOrg) {
    this.saleOrg = saleOrg;
  }

  /*
   * ��������
   */
  private String transtypeID;

  public String getTranstypeID() {
    return this.transtypeID;
  }

  public void setTranstypeID(String transtypeID) {
    this.transtypeID = transtypeID;
  }

  /*
   * ��������
   */
  private String[] materialIDS;

  public String[] getMaterialIDS() {
    return this.materialIDS;
  }

  public void setMaterialIDS(String[] materialIDS) {
    this.materialIDS = materialIDS;
  }

  /*
   * ���������֯ID
   */
  private String[] sendStockOrgIDs;

  /*
   * ���������֯+ֱ�˲ֿ��ֵ��
   */
  private Map<String, String> sendStordocidMap;

  /*
   * ȱʡ���캯��
   */
  public SORelationOrgQuery() {
    /*
     * ȱʡ���캯��
     */
  }

  /**
   * @param customerID �ͻ�ID
   * @param saleOrg ������֯
   * @param materialIDS ����ID����
   */
  public SORelationOrgQuery(String customerID, String saleOrg,
      String[] materialIDS) {
    this.customerID = customerID;
    this.saleOrg = saleOrg;
    this.materialIDS = materialIDS;
  }

  /**
   * ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID
   * modify by zhangby5 ȡĬ��������֯Ҫ����ȡ�ֿ���������˴�û��ȡ��ֱ�˲ֿ⣬��ô��Ҫ���շ��������֯ȡ��
   * �����ݷ��������֯�ܴ����ֿ⣬��������֯��Ҫ���ղֿ�Ŀ����֯���ˣ�����ì�ܣ����Ҫ����ȡ�ֿ����  --- ���Բ�������⣺NCdp205432523
   * @param customerID �ͻ�ID
   * @param saleOrg ������֯
   * @param materialIDS ����ID����
   * @return Map<Key�����ϣ�Value��String[]{���������֯�����������֯ID��Ӧ����֯ID����������ID}>
   * @throws BusinessException
   * @see
   */

  public Map<String, String[]> querySaleRelationOrg() {

    // ��ʼ�����ز���
    Map<String, String[]> returnOrgMap = new HashMap<String, String[]>();
    // ������������
    int intLength = this.materialIDS.length;
    // ������֯����
    String[] saleOrgs = new String[intLength];
    for (int i = 0; i < intLength; i++) {
      saleOrgs[i] = this.saleOrg;
    }

    // ��ѯĬ�Ϸ��������֯ID
    Map<String, String> hmSendStockOrgIDs =
        SaleOrgPubService.getDefaultStockOrgIDS(saleOrgs, this.materialIDS);
    this.sendStockOrgIDs = new String[intLength];

    // ���÷��ؿ����֯
    if (null != hmSendStockOrgIDs && hmSendStockOrgIDs.size() > 0) {
      for (int i = 0; i < intLength; i++) {
        String stockOrgkey =
            this.saleOrg + SaleOrgPubService.SPLIT + this.materialIDS[i];
        this.sendStockOrgIDs[i] = hmSendStockOrgIDs.get(stockOrgkey);
      }
    }

    // ��ȡֱ�˲�
    this.setSendStordoc();

    // ��ѯ���������֯ID��Ӧ����֯ID����������ID
    Map<String, String[]> hmFinanceOrgid =
        SaleOrgPubService
            .getDefaultFinanceOrgIDSAndReceiveOrgIDSAndLiaCenterIDS(
                this.customerID, saleOrgs, this.materialIDS,
                this.sendStockOrgIDs);

    // ���÷��ؽ��
    for (int i = 0; i < intLength; i++) {
      String strKey = this.materialIDS[i];
      String[] returnOrgs = new String[6];
      // Ĭ�Ϸ��������֯ID
      returnOrgs[0] = this.sendStockOrgIDs[i];

      if (null != hmFinanceOrgid && hmFinanceOrgid.size() > 0) {
        String[] financeOrgs = hmFinanceOrgid.get(strKey);
        if (null != financeOrgs && financeOrgs.length == 3) {
          // ������֯ID��Ӧ����֯ID����������ID
          returnOrgs[1] = financeOrgs[0];
          returnOrgs[2] = financeOrgs[1];
          returnOrgs[3] = financeOrgs[2];
        }
      }
      if (null != this.sendStordocidMap && this.sendStordocidMap.size() > 0) {
        // �ֿ�
        returnOrgs[5] = this.sendStordocidMap.get(this.sendStockOrgIDs[i]);
      }
      returnOrgMap.put(strKey, returnOrgs);
    }

    return returnOrgMap;
  }

  /**
   * ���ݽ��������Ƿ�ֱ�����òֿ�ֵ,�������ֱ�˵����ԭ�вֿ�ֵ����������Ϊֱ�˲�
   * 
   * @param transtypeID ��������ID
   * @param stockorgs �����֯����
   * @return Map<�����֯ID, ֱ�˲ֿ�ID>
   */
  public void setSendStordoc() {
    this.sendStordocidMap = new HashMap<String, String>();
    // ��������˽������ͣ���ȡֱ�˲�
    if (null != this.transtypeID) {
      SaleOrderTranTypeUtil dirutil = new SaleOrderTranTypeUtil();
      if (dirutil.isDirectTran(this.transtypeID)
          && null != this.sendStockOrgIDs && this.sendStockOrgIDs.length != 0) {
        for (String sendstockorg : this.sendStockOrgIDs) {
          if (PubAppTool.isNull(sendstockorg)) {
            continue;
          }
          String direcstore = StordocPubService.getDirectstore(sendstockorg);
          this.sendStordocidMap.put(sendstockorg, direcstore);
        }

      }
    }
  }
}
