package nc.bs.so.m30.rule.approve;

import nc.bs.so.m30.maintain.util.ApproveStateUtil;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * ��������ͨ������¶�����Ӧ��״̬(���������⡢��Ʊ������)
 * @scene 
 * ���۶�������ͨ����
 * @param 
 * ��
 * @since 6.0
 * @version 2012-4-23 ����01:20:49
 * @author ô��
 */
public class ApproveStateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 1.SaleOrderVO[] -> SaleOrderViewVO[]
    SaleOrderViewVO[] views = this.billToViewConvertor(vos);
    if(views == null || views.length == 0){
    	return;
    }
    ApproveStateUtil approvestate = new ApproveStateUtil();
    // 2.������״̬
    approvestate.processSendState(views);
    // 3.�������״̬
    approvestate.processOutState(views);
    // 4.����Ʊ״̬
    approvestate.processInvoiceState(views);
    // 5.����������Ʒ���Զ�Ӧ�ս���ر�
    approvestate.processARSettleCloseState(views);
    // 6.������������������Զ��ɱ�����ر�
    approvestate.processCostSettleCloseState(views);
  }

  protected SaleOrderViewVO[] billToViewConvertor(SaleOrderVO[] vos) {
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    return convert.convert(vos);
  }

}
