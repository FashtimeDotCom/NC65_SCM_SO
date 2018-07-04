package nc.bs.so.m4331.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷�����ɾ��ǰУ���ܷ�ɾ��
 * @scene
 * ���۷�����ɾ��ǰ
 * @param
 * ��
 */
public class CheckEnableDeleteRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {

    for (DeliveryVO vo : vos) {

      /*String cauditorid = vo.getParentVO().getApprover();
      if (!VOChecker.isEmpty(cauditorid)) {
        // ������״̬��������Ϊ��ʱ����ɾ�������������˷ǿ�һ������ɾ����
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0038")@res "����˷������ݲ���ɾ����");
      }*/
      Integer fstatusflag = vo.getParentVO().getFstatusflag();
      if (!BillStatus.FREE.equalsValue(fstatusflag)
          && !BillStatus.AUDITING.equalsValue(fstatusflag)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0039")/*@res "��ǰ��������״̬����ɾ����"*/);
      }
    }

  }

}