package nc.impl.so.m32.action;

import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;

/**
 * ���۷�Ʊ�ջع��ܺ�̨ʵ��
 * 
 * @since 6.0
 * @version 2011-2-22 ����10:46:25
 * @author ô��
 */
public class UnCommitSaleInvoiceAction {

  /**
   * ���۷�Ʊ�ջ� ��̨�����߼�
   * 
   * @param clientBills ���۷�ƱVO
   * @param originBills ���۷�ƱVO
   * @return ���۷�ƱVO
   */
  public SaleInvoiceVO[] unSend(SaleInvoiceVO[] clientBills,
      SaleInvoiceVO[] originBills) {
    // ��VO�־û������ݿ���
    BillUpdate<SaleInvoiceVO> update = new BillUpdate<SaleInvoiceVO>();
    SaleInvoiceVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
