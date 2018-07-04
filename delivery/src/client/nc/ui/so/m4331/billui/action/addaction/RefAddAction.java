package nc.ui.so.m4331.billui.action.addaction;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

import org.apache.commons.lang.StringUtils;

public class RefAddAction extends AbstractReferenceAction {
  private static final long serialVersionUID = 8278944084279171430L;

  private DeliveryEditor editor;

  private DeliveryManageModel model;

  /**
   * RefAddAction �Ĺ�����
   */
  public RefAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.model.setSourceType(this.getSourceBillType());
    // ��������ƽ̨�ṩ�Ĺ���ת������
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    DeliveryVO[] newvos = null;
    if (PfUtilClient.isCloseOK()) {
      newvos = (DeliveryVO[]) PfUtilClient.getRetVos();
      if (VOChecker.isEmpty(newvos)) {
        return;
      }
      // ������ת������ǰ
      this.beforeTranProcessor(newvos);
      // �������ݽ��洦��
      this.getTransferViewProcessor().processBillTransfer(newvos);
      this.afterTranProcessor();
    }
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  @Override
  public boolean isEnabled() {
    return this.getModel().getUiState() == UIState.NOT_EDIT;
  }

  public void setEditor(DeliveryEditor view) {
    this.editor = view;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  protected void afterTranProcessor() {
    if (this.getSourceBillType().equals(TOBillType.TransOrder.getCode())) {
      // ���Ե��������ķ��������֯������༭
      this.getEditor().getBillCardPanel()
          .getBodyItem(DeliveryBVO.CSENDSTOCKORGVID).setEdit(false);
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setEdit(false);
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setNull(true);
    }
    else {
      // �������۶����ķ��������֯����༭
      this.getEditor().getBillCardPanel()
          .getBodyItem(DeliveryBVO.CSENDSTOCKORGVID).setEdit(true);
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setEdit(true);
      // �������۶����Ŀͻ�������Ϊ��
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setNull(true);
    }
    new SOCardEditableSetter().setHeadEditForRef(this.getEditor()
        .getBillCardPanel());
  }

  protected void beforeTranProcessor(DeliveryVO[] newvos) {
    this.setDefaultDate(newvos);
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    // String billtype =
    // TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    String tranType =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtils.isBlank(tranType)) {
      context.setCurrBilltype(SOBillType.Delivery.getCode());
    }
    else {
      context.setCurrBilltype(tranType);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���
    context.setTransTypes(this.getTranstypes());
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  private void setDefaultDate(DeliveryVO[] newvos) {
    for (DeliveryVO vo : newvos) {
      DeliveryHVO hvo = vo.getParentVO();
      UFDate date = AppContext.getInstance().getBusiDate();
      hvo.setDbilldate(date);
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        bvo.setDbilldate(date);
        UFDate sendDate = bvo.getDsenddate();
        UFDate receiveDate = bvo.getDreceivedate();
        if (null == sendDate || sendDate.before(date)) {
          bvo.setDsenddate(date.asLocalEnd());
        }
        if (null == receiveDate || receiveDate.before(date)) {
          bvo.setDreceivedate(date.asLocalEnd());
        }
      }
    }
  }
}
