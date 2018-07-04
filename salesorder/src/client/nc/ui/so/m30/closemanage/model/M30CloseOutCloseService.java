package nc.ui.so.m30.closemanage.model;

import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.scmpub.model.SCMResumeExceptionInvoker;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class M30CloseOutCloseService implements
    ISingleBillService<SaleOrderViewVO> {

  @Override
  public SaleOrderViewVO operateBill(SaleOrderViewVO bill) throws Exception {
    SaleOrderViewVO[] ret = null;
    SCMResumeExceptionInvoker invoker = new SCMResumeExceptionInvoker();
    ISaleOrderCloseManageMaintain service = invoker.getService(ISaleOrderCloseManageMaintain.class);//IMyServiceΪ����õķ���
    ret = service.closeSaleOrderOut(new SaleOrderVO[] {
        bill.changeToSaleOrderVO()
      });
    return null == ret ? null : ret[0];
  }

}
