package nc.bs.so.m30.rule.atp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * ���۶�������ִ��ǰ���Ͽ����������
 * @scene 
 * ���۶����������������޸ġ�ɾ�����涯�����������ᶯ��ǰ
 * @param 
 * ��
 */
public class SaleOrderVOATPBeforeRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      SOATPprocess.modifyATPBefore(SOBillType.Order.getCode(), vos);
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }
}
