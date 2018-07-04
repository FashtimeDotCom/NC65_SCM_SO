package nc.bs.so.m4331.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۷������ջز���ǰ��鵥���Ƿ���ջ�
 * @scene
 * ���۷������ջز���ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-2-22 ����10:58:10
 * @author ô��
 */
public class CheckUnSendEnableRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryHVO header = vo.getParentVO();
      // ������״̬����������Ϊ�յĵ��������ջ�
      if (!BillStatus.AUDITING.equalsValue(header.getFstatusflag())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0042")/*@res "��ǰ���ݵĵ���״̬�����ɽ����ջء�"*/);
      }
      else {
        if (null != header.getApprover()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0043")/*@res "��ǰ�����Ѿ���������ͨ�������ɽ����ջء�"*/);
        }
      }

    }
  }
}