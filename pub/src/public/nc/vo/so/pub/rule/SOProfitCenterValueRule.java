package nc.vo.so.pub.rule;

import nc.itf.scmpub.reference.uap.org.LiabilityDefaultValueService;
import nc.itf.scmpub.reference.uap.org.parameter.LiabilityByWarehouseParam;
import nc.vo.org.LiabilityCenterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;


public class SOProfitCenterValueRule {

  private IKeyValue keyValue;

  private String stordocKey = SOItemKey.CSENDSTORDOCID;

  private String stockorgKey = SOItemKey.CSENDSTOCKORGID;

  /**
   * Ĭ���÷����ֿ�ͷ��������֯ȥ��֯��ѯ�������Ĳ���
   * 
   * @param keyValue
   */
  public SOProfitCenterValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �ô�����ֶ�ֵȥ��֯��ѯ�������ĵĲ���
   * 
   * @param keyValue
   * @param stordocKey
   * @param stockorgKey
   */
  public SOProfitCenterValueRule(IKeyValue keyValue, String stordocKey,
      String stockorgKey) {
    this.keyValue = keyValue;
    this.stordocKey = stordocKey;
    this.stockorgKey = stockorgKey;
  }


  /**
   * ���������ĸ�ֵ
   * @param profitVidKey Ҫ�ı���������ĵ�VID
   * @param profitOidKey Ҫ�ı���������ĵ�OID
   * @param rows
   */
  public void setProfitCenterValue(String profitVidKey, String profitOidKey,
      int[] rows) {
    LiabilityByWarehouseParam[] warehouseParams = this.getWarehouseParams(rows);
    LiabilityCenterVO[] profitcenterVO = null;
    try {
      profitcenterVO =
          LiabilityDefaultValueService
          .getLiabilityBywarehouseAndOrg(warehouseParams);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (ArrayUtil.isEmpty(profitcenterVO)) {
      return;
    }

    int length = rows.length;
    for (int index = 0; index < length; index++) {

      if (null == profitcenterVO[index]) {
        String profitcenterVid =
            this.keyValue.getBodyStringValue(rows[index],
                SOItemKey.CPROFITCENTERVID);
        String profitcenterid =
            this.keyValue.getBodyStringValue(rows[index],
                SOItemKey.CPROFITCENTERID);

        this.keyValue.setBodyValue(rows[index], profitVidKey, profitcenterVid);
        this.keyValue.setBodyValue(rows[index], profitOidKey, profitcenterid);
        continue;
      }

      this.keyValue.setBodyValue(rows[index], profitVidKey,
          profitcenterVO[index].getPk_vid());
      this.keyValue.setBodyValue(rows[index], profitOidKey,
          profitcenterVO[index].getPk_liabilitycenter());
    }

  }

  private LiabilityByWarehouseParam[] getWarehouseParams(int[] rows) {
    int length = rows.length;
    LiabilityByWarehouseParam[] params = new LiabilityByWarehouseParam[length];
    for (int index = 0; index < length; index++) {
      LiabilityByWarehouseParam param = new LiabilityByWarehouseParam();
      param.setCwarehouseid(this.keyValue.getBodyStringValue(rows[index],
          this.stordocKey));
      param.setPk_org(this.keyValue.getBodyStringValue(rows[index],
          this.stockorgKey));
      params[index] = param;
    }
    return params;
  }

  public void setProfitCenterValue(String profitVidKey, String profitOidKey) {
    int[] rows = new int[this.keyValue.getBodyCount()];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = i;
    }
    this.setProfitCenterValue(profitVidKey, profitOidKey, rows);
  }
}
