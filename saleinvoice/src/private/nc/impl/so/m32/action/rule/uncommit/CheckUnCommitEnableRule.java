package nc.impl.so.m32.action.rule.uncommit;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۷�Ʊ��鵥���Ƿ���ջ�
 * @scene
 * ���۷�Ʊ�ջ�ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-2-22 ����10:58:10
 * @author ô��
 */
public class CheckUnCommitEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    for (SaleInvoiceVO invoicevo : vos) {
      SaleInvoiceHVO header = invoicevo.getParentVO();
      // ������״̬����������Ϊ�յĵ��������ջ�
      if (!BillStatus.AUDITING.equalsValue(header.getFstatusflag())
          || null != header.getApprover()) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0036")/*@res "��ǰ��Ʊ����״̬�����ɽ����ջء�"*/);
      }

    }
  }

}
