package nc.bs.so.m4331.maintain.rule.reverse;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;

import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷������޸ı�������Ԥ��
 * @scene
 * ���۷������޸ı����
 * @param
 * ��
 * @since 6.1
 * @version 2013-05-13 15:08:12
 * @author yixl
 */
public class ReserveUpdateRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    try {
      for (DeliveryVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenEdit(SOBillType.Delivery.getCode(),
            vo, true);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
