package nc.ui.so.m30.billui.funclink;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletLinkEvent;
import nc.funcnode.ui.FuncletLinkListener;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �տ�������
 * <p>���������۶���VOˢ��model������</p>
 * 
 * @since 6.0
 * @version 2011-5-30 ����02:15:06
 * @author ��־ΰ
 */
public class SaleOrderGatheringLinkListener implements FuncletLinkListener {

  private BillManageModel model;

  @Override
  public void dealLinkEvent(FuncletLinkEvent event) {
    AggGatheringBillVO bill = (AggGatheringBillVO) event.getUserObject();

    ISaleOrderMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
    SaleOrderVO[] newBills = null;
    // ����Ӷ������ӵ��տ���� ������ ����տ�붩��û�й�ϵ ����Ҫ�������κδ���
    if (bill.getBodyVOs()[0].getSrc_billid() != null
        && !bill.getBodyVOs()[0].getSrc_billid().isEmpty()) {
      try {
        newBills = service.querySaleorder(new String[] {
          bill.getBodyVOs()[0].getSrc_billid()
        });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      this.getModel().directlyUpdate(newBills);
    }
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
