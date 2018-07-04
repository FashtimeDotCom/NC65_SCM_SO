package nc.impl.so.m32.action.rule.commit;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * ���۷�Ʊ����ǰ����VO״̬
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-7-13 ����07:18:56
 * @author ô��
 */
public class SetCommitStatusRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO voInvoice : vos) {
      voInvoice.getParentVO().setStatus(VOStatus.UPDATED);
    }
  }

}
