package nc.bs.so.m32.maintain.rule.delete;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷�Ʊɾ������ǰ��ɾ��У�����(����״̬)
 * @scene
 * ���۷�Ʊɾ������ǰ
 * @param
 * ��
 * @since 6.3
 * @version 2012-12-21 ����09:04:01
 * @author yaogj
 */
public class CheckEnableDeleteRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {

    // ������۷�Ʊ״̬�Ƿ�ɱ�ɾ����
    for (SaleInvoiceVO invoicevo : vos) {
      Integer fstatusflag = invoicevo.getParentVO().getFstatusflag();
      String cauditorid = invoicevo.getParentVO().getApprover();
      if (!BillStatus.FREE.equalsValue(fstatusflag)
          && !(BillStatus.AUDITING.equalsValue(fstatusflag) && VOChecker
              .isEmpty(cauditorid))) {

        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006008_0", "04006008-0013")/*@res "��ǰ��Ʊ����״̬����ɾ����"*/);
      }
    }

  }

}
