package nc.impl.so.m4331.action.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * ���۷������ύǰ��������飨��ǰ����Ա�Ƿ��п��õ���������
 * @scene
 * ���۷������ύǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-3-22 ����06:35:50
 * @author ô��
 */
public class CheckExistWorkflowRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryHVO header = vo.getParentVO();
      if (header.getFstatusflag().intValue() != BillStatus.FREE.getIntValue()) {
        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0064")/*@res "����Ϊ������̬���������ύ����!"*/);
      }
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {

        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0065")/*@res "����û���ҵ�ƥ����������̣������ݿ���ֱ������ͨ��!"*/);
      }
    }
  }

}