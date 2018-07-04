package nc.bs.so.m30.rule.reserve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * ���۶����޶���
 * @scene
 * ���۶����޶������Ԥ��
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����3:01:41
 * @author zhangby5
 */
public class ReserveReviseRule implements IRule<SaleOrderVO> {
  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      for (SaleOrderVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenRevise(SOBillType.Order.getCode(), vo,
            false);
      }
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }
}
