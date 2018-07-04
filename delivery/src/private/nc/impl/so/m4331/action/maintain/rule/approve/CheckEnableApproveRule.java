package nc.impl.so.m4331.action.maintain.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۷���������ǰ��鵥���Ƿ��������
 * @scene
 * ���۷���������ǰ
 * @param
 * ��
 */
public class CheckEnableApproveRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryHVO hvo = vo.getParentVO();
      Integer status = hvo.getFstatusflag();
      boolean expr1 = BillStatus.FREE.equalsValue(status);
      boolean expr2 = BillStatus.AUDITING.equalsValue(status);
      // ���ɡ�������״̬�����������
      if (!(expr1 || expr2)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0063")/*@res "��ǰ��������״̬�����ɽ���������"*/);
      }
    }
  }
}
