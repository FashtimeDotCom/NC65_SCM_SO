package nc.bs.so.m30.rule.reserve;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * ���۶����޸ı�������Ԥ������
 * @scene
 * ���۶����޸ı����
 * @param 
 * ��
 */
public class ReserveUpdateRule implements IRule<SaleOrderVO> {
  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      // Ԥ������û�ȷ�ϱ�־
      UFBoolean reserveCheckPassed =
          (UFBoolean) BSContext.getInstance().getSession(
              BusinessCheck.ReserveCheck.getCheckCode());
      for (SaleOrderVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenEdit(SOBillType.Order.getCode(), vo,
            UFBoolean.FALSE.equals(reserveCheckPassed));
      }
    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
  }
}
