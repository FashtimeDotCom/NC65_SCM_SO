package nc.vo.so.m30.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.scale.SaleOrderScaleProcessor;

/**
 * @description
 * ���۶�������ǰ���ȼ��
 * @scene
 * ���۶����������޸ı���ǰ
 * @param 
 * ��
 * @since 6.3
 * @version 2013-6-14 ����10:49:23
 * @author tianft
 */
public class SaleOrderScaleCheckRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    new SaleOrderScaleProcessor().checkBillPrecision(vos);
  }
}
