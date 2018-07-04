package nc.bs.so.m4331.quality.rule.insert;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷������ʼ���Ϣ����ǰУ��ǿ�����
 * @scene
 * ���۷������ʼ���Ϣ����ǰ
 * @param
 * ��
 */
public class CheckNewNullRule {
  public void process(DeliveryCheckVO[] vos) {
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0055")/*@res "�ʼ���˻�д����������Ϊ�ա�"*/);
    }
    for (DeliveryCheckVO vo : vos) {
      if (VOChecker.isEmpty(vo.getCdeliverybid())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0056")/*@res "�ʼ쵥��˻�д������������������id����Ϊ�ա�"*/);
      }
      if (VOChecker.isEmpty(vo.getVcheckcode())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0057")/*@res "�ʼ쵥��˻�д���������ʼ쵥�Ų���Ϊ�ա�"*/);
      }
    }
  }
}