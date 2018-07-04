package nc.vo.so.m4331.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.scale.DeliveryScaleProcessor;

/**
 * @description
 * ����������ǰ���ȼ�����
 * @scene
 * ���۷������������޸ı���ǰ
 * @param
 * ��
 * @since 6.3
 * @version 2013-6-18 ����03:58:28
 * @author tianft
 */
public class DeliveryScaleCheckRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    new DeliveryScaleProcessor().checkBillPrecision(vos);
  }

}
