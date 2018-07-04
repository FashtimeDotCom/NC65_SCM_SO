package nc.bs.so.m4331.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷������޸ı���ǰ���ݷǿ�У��
 * @scene
 * ���۷������޸ı���ǰ
 * @param
 * ��
 */
public class CheckUpdateNullRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {

    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0044")/*@res "�޸ı��浥�ݲ���Ϊ�ա�"*/);
    }
  }

}