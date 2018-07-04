package nc.bs.so.m30.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * ���۶����ύǰ��������飨��ǰ����Ա�Ƿ��п��õ�����������У�����۶����Ƿ����������������ύ
 * @scene
 * ���۶����ύǰ
 * @param
 * @author gdsjw
 */
public class CheckExistWorkflowRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO header = vo.getParentVO();
      if (header.getFstatusflag().intValue() != BillStatus.FREE.getIntValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0305", null, new String[]{header.getVbillcode()})/*{0}����Ϊ������̬���������ύ����!*/);
      }
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0306", null, new String[]{header.getVbillcode()})/*{0}����û���ҵ�ƥ����������̣������ݿ���ֱ������ͨ��!*/);
      }
    }
  }
}
