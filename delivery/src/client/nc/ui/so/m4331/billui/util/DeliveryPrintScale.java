package nc.ui.so.m4331.billui.util;

import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

public class DeliveryPrintScale extends SoVoPrecionScale {
  public DeliveryPrintScale(String pk_group, DeliveryVO[] bills) {
    super(pk_group, bills);
  }

  /**
   * ����vo����
   */
  @Override
  public void setScale() {
    this.setPriceScale();
    this.setNumScale();
    this.setMoneyScale();
    this.setRateScale();
    // ���м���
    this.getScale().process();
    this.setHeadDataScale();
  }
}
