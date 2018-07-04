package nc.bs.so.m4331.maintain.rule.insert;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷���������ǰ���ݷǿ�У��
 * @scene
 * ���۷���������ǰ
 * @param
 * ��
 */
public class CheckNewNullRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0040")/*@res "�������浥�ݲ���Ϊ�ա�"*/);
    }
    for (DeliveryVO vo : vos) {
      if (VOChecker.isEmpty(vo) || VOChecker.isEmpty(vo.getChildrenVO())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0117")/*�������浥���岻��Ϊ��*/);
      }
    }
  }
}