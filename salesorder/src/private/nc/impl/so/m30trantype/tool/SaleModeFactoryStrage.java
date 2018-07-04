package nc.impl.so.m30trantype.tool;

import nc.vo.so.m30trantype.enumeration.SaleMode;

/**
 * ���۶���������������ģʽ���޸ķ�Χ
 * ������µĿ��޸ķ�Χ����ֻ��Ҫ�������ж��С�ISaleModeStrategy��ʵ����ֱ���Ӽ��ɡ�
 * 
 * @since 6.36
 * @version 2015-1-15 ����5:00:24
 * @author wangshu6
 */
public class SaleModeFactoryStrage {

  /**
   * ����ԭ����ģʽ����ƥ�䣬
   * 
   * @param straregy ԭ����ģʽ
   * @return ��Ӧ������ģʽӦ���ࣨ���к��п��޸ķ�Χ���飩
   */
  public ISaleModeStrategy creatSaleModeStrategy(Object straregy) {
    // �������ͨ������ģʽ��
    if (straregy.equals(SaleMode.MODE_COMMON.getIntegerValue())) {
      return new CommonSaleModeStrategyImpl();
    }
    // ������˻�������ģʽ��
    else if (straregy.equals(SaleMode.MODE_RETURN.getIntValue())) {
      return new ReturnSaleModeStrategyImpl();
    }
    // ������˻���������ģʽ��
    else if (straregy.equals(SaleMode.MODE_RETURNCHANGE.getIntValue())) {
      return new ReturnExchangeSaleModeStrategyImpl();
    }
    return null;
  }

}
