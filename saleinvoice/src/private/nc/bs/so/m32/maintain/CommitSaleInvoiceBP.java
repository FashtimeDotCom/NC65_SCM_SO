package nc.bs.so.m32.maintain;

import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;

/**
 * ������
 * 
 * @author gdsjw
 */
public class CommitSaleInvoiceBP {

  /**
   * ����
   * 
   * @param clientBills ǰ̨��������vo
   * @param originBills ���ݿ�ԭʼvo
   * @return �������vo
   */
  public SaleInvoiceVO[] sendApprove(SaleInvoiceVO[] clientBills,
      SaleInvoiceVO[] originBills) {
    // ��VO�־û������ݿ���
    BillUpdate<SaleInvoiceVO> update = new BillUpdate<SaleInvoiceVO>();
    SaleInvoiceVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
