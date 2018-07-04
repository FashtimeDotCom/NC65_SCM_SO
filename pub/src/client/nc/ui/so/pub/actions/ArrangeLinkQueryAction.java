package nc.ui.so.pub.actions;

import java.awt.event.ActionEvent;

import nc.scmmm.ui.uif2.actions.SCMLinkQueryAction;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billgraph.SourceBillFlowDlg;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.trade.billgraph.billflow.control.DefaultBillGraphListener;
import nc.ui.trade.billgraph.billflow.control.IBillGraphListener;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * ���Ž�����������
 * 
 * @since 6.0
 * @version 2011-6-17 ����03:17:06
 * @author ô��
 */
public class ArrangeLinkQueryAction extends SCMLinkQueryAction implements
    IBillPush {

  private IBillGraphListener billGraphListener = null;

  private static final String BILL_FINDER_CLASSNAME =
      "nc.impl.pubapp.linkquery.BillTypeSetBillFinder";

  private static final long serialVersionUID = 1L;

  private BillContext context;

  @SuppressWarnings("restriction")
  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggregatedValueObject[] srcVos =
        (AggregatedValueObject[]) this.getBillContext().getModel()
            .getSelectDatas();
    boolean iscanenble =
        srcVos != null && srcVos.length == 1 && srcVos[0] != null;
    if (!iscanenble) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0247")/*
                                        * @res "ѡ��ĵ��ݲ���Ϊ�գ����������Ҳ����Ϊ���Ҳ���ѡ����ŵ��ݣ�"
                                        */);
    }
    IBillInfo info = this.getBillInfoFactory().getBillInfo(srcVos[0]);

    // ��ʼ���Ի���
    if (Thread.currentThread().getContextClassLoader() == null) {
      Thread.currentThread().setContextClassLoader(
          ArrangeLinkQueryAction.class.getClassLoader());
    }
    SourceBillFlowDlg sourceBillFlowDlg =
        new SourceBillFlowDlg(this.getBillContext().getListPanel(),
            ArrangeLinkQueryAction.BILL_FINDER_CLASSNAME, info.getBillType(),
            info.getBillId(), info.getBillCode());

    // ���ü�����
    sourceBillFlowDlg.setBillGraphListener(this.billGraphListener);
    // �򿪶Ի���
    sourceBillFlowDlg.showModal();
  }

  /**
   * ���췽��
   */
  public ArrangeLinkQueryAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.SCM_BILLLINKQUERY);
    // ����Ĭ�ϼ���
    this.billGraphListener = new DefaultBillGraphListener();
    this.setOpenMode(1);
  }

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  @Override
  public void setBillContext(BillContext context) {
    this.context = context;
  }

  @Override
  protected boolean isActionEnable() {
    boolean iscanenble = false;
    try {
      AggregatedValueObject[] srcVos =
          (AggregatedValueObject[]) this.getBillContext().getModel()
              .getSelectDatas();
      iscanenble = srcVos != null && srcVos.length == 1 && srcVos[0] != null;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return iscanenble;

  }

}
