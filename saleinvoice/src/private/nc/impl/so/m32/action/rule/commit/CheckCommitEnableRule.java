package nc.impl.so.m32.action.rule.commit;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * ���۷�Ʊ����ǰ�� <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Ƿ���������Ĺ���
 * </ul>
 * <p>
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-5-21 ����03:25:37
 */
public class CheckCommitEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    NCLangResOnserver resonserver = NCLangResOnserver.getInstance();
    for (SaleInvoiceVO invoicevo : vos) {
      // ����״̬��������ͨ��������������
      // modify by wangshu6 for 636 ���۷�Ʊ��������������ͨ�������ٴ��ύ 20150408
      if (!BillStatus.FREE
          .equalsValue(invoicevo.getParentVO().getFstatusflag())
          && !BillStatus.NOPASS.equalsValue(invoicevo.getParentVO()
              .getFstatusflag())) {

        ExceptionUtils.wrappBusinessException(resonserver.getStrByID(
            "4006008_0", "04006008-0031")/*@res "��ǰ��Ʊ����״̬�����ɽ�������"*/);
      }
      SaleInvoiceHVO header = invoicevo.getParentVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(resonserver.getStrByID(
            "4006008_0", "04006008-0103", null, new String[] {
              header.getVbillcode()
            })/*{0}����û���ҵ�ƥ����������̣������ݿ���ֱ������ͨ��!!*/);
      }
    }

  }

}
