package nc.impl.so.m30trantype.tool;

import nc.vo.so.m30trantype.enumeration.SaleMode;

/**
 * ����ģʽ���Դӡ��˻����޸ĳɡ���ͨ+�˻���
 * 
 * @since 6.36
 * @version 2015-1-15 ����2:55:03
 * @author wangshu6
 */
public class ReturnSaleModeStrategyImpl implements ISaleModeStrategy {

  /**
   * �������ģʽΪ���˻��������޸ĳɡ���ͨ+�˻���
   */
  @Override
  public SaleMode[] getParseSaleMode() {
    return new SaleMode[]{SaleMode.MODE_COMMONRETURN};

  }

}
