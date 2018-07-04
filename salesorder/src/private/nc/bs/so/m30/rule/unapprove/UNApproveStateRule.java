package nc.bs.so.m30.rule.unapprove;

import nc.bs.so.m30.maintain.util.ApproveStateUtil;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * ����ȡ����������¶�����Ӧ��״̬(���������⡢��Ʊ������)
 * @scene 
 * ���۶���ȡ��������
 * @param 
 * ��
 * @since 6.0
 * @version 2012-4-23 ����01:20:49
 * @author ô��
 */
public class UNApproveStateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 1.SaleOrderVO[] -> SaleOrderViewVO[]
    SaleOrderViewVO[] views = this.billToViewConvertor(vos);

    ApproveStateUtil approvestate = new ApproveStateUtil();
    // 2.������״̬
    approvestate.processSendState(views);
    // 3.�������״̬
    approvestate.processOutState(views);
    // 4.����Ʊ״̬
    approvestate.processInvoiceState(views);
    // 5.����������Ʒ���Զ�Ӧ�ս���ر�
    approvestate.processARSettleOpenState(views);
    // 6.������������������Զ��ɱ�����ر�
    approvestate.processCostSettleOpenState(views);
  }

  private SaleOrderViewVO[] billToViewConvertor(SaleOrderVO[] vos) {
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    return convert.convert(vos);
  }

}
