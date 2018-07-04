package nc.bs.so.m30.rule.atp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;

/**
 * @description
 * ���۶�������رա��򿪡������رա��򿪺���������
 * @scene
 * ���۶�������رա��򿪡������رա��򿪺�
 * @param
 * ��
 */
public class SaleOrderViewATPAfterRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] viewvos) {
    try {
      SaleOrderVO[] vos = new SaleOrderVOUtil().chgViewToBill(viewvos);
      SOATPprocess.modifyATPAfter(SOBillType.Order.getCode(), vos);
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }

}
