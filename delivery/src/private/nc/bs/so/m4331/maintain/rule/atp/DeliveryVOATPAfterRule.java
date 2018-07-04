package nc.bs.so.m4331.maintain.rule.atp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 *              ���۷��������桢ɾ������������
 * @scene
 *        ����������ģ�������۷������������桢ɾ����
 * @param ��
 */
public class DeliveryVOATPAfterRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    try {
      if (SysInitGroupQuery.isICEnabled()) {
        SOATPprocess.modifyATPAfter(SOBillType.Delivery.getCode(), vos);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
