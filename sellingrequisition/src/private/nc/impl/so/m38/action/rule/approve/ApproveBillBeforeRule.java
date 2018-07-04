package nc.impl.so.m38.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���Ԥ������ǰ״̬�Ƿ�����
 * @scene
 * ����Ԥ��������ǰ
 * @param
 * ��
 */
public class ApproveBillBeforeRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO prevo : vos) {
      if (!(BillStatus.FREE.equalsValue(prevo.getParentVO().getFstatusflag())
          || BillStatus.AUDITING
            .equalsValue(prevo.getParentVO().getFstatusflag()))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0024")/*@res "��ǰԤ��������״̬�����ɽ���������"*/);
      }
    }
  }

}