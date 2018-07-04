package nc.impl.so.m32.action.rule.uncommit;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۷�Ʊ�ջ�ǰ��������ֶ�ֵ
 * @scene
 * ���۷�Ʊ�ջ�ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-2-22 ����11:01:45
 * @author ô��
 */
public class SetUnCommitValueRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO voInvoice : vos) {
      voInvoice.getParentVO().setStatus(VOStatus.UPDATED);
      voInvoice.getParentVO().setFstatusflag((Integer) BillStatus.FREE.value());
    }
  }

}
