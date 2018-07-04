package nc.ui.so.m4331.billui.action.assitfunc;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ic.pub.ReserveUIService;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.rule.ReverseConditionRule;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * ������Ԥ��
 * 
 * @since 6.0
 * @version 2011-1-21 ����03:50:30
 * @author ף����
 */
@SuppressWarnings("restriction")
public class DeliveryReverseAction extends NCAction {
  private static final long serialVersionUID = -4490586267001887381L;

  private DeliveryEditor editor;

  private BillManageModel model;

  private RefreshSingleAction refreshAction;

  public DeliveryReverseAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
	if(!SysInitGroupQuery.isICEnabled()) {
	  return;
	}
    DeliveryBVO[] bvos = this.getReverseVO();
    ReverseConditionRule rule = new ReverseConditionRule(bvos);
    rule.checkReverse();
    int flag = this.reverse(bvos);
    if (flag == 1) {
      this.refreshAction.doAction(e);
    }
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setRefreshAction(RefreshSingleAction refreshAction1) {
    this.refreshAction = refreshAction1;
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());
    if (isEnable) {
      // ��ѡ����
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      // ѡ�е���û�йرտ��Խ���Ԥ��
      DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      isEnable =
          ((null != selectedRows) && (selectedRows.length > 1))
              || !BillStatus.CLOSED.equalsValue(billstatus);
    }
    return isEnable;
  }

  /*
   * ��鵥��״̬���Ѿ������رյĵ��ݲ��ܽ���Ԥ��
   */
  private void checkBillState(DeliveryHVO hvo) {
    Integer billstatus = hvo.getFstatusflag();
    if (BillStatus.CLOSED.equalsValue(billstatus)) {
      // �����رյķ���
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006002_0", "04006002-0003")
      /*@res "�����Ѿ������رգ�������Ԥ���� "*/);

    }
  }

  /*
   *  ���ҪԤ���ķ�����vo
   * @return
   */
  private DeliveryBVO[] getReverseVO() {
    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    if (rows.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0004")/*@res "��ѡ�б����С�"*/);
    }
    DeliveryHVO hvo =
        (DeliveryHVO) this.editor.getBillCardPanel().getBillData()
            .getHeaderValueVO(DeliveryHVO.class.getName());
    this.checkBillState(hvo);
    DeliveryBVO[] bvos = new DeliveryBVO[rows.length];
    // ���ѡ�еķ�����������vo
    for (int i = 0; i < rows.length; i++) {
      bvos[i] =
          (DeliveryBVO) this.editor.getBillCardPanel().getBillModel()
              .getBodyValueRowVO(rows[i], DeliveryBVO.class.getName());
    }
    return bvos;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_RESERVE);
  }

  /*
   * Ԥ��
   */
  private int reverse(DeliveryBVO[] bvos) {

    ReserveUIService service = new ReserveUIService(this.editor);
    DeliveryHVO hvo =
        (DeliveryHVO) this.editor.getBillCardPanel().getBillData()
            .getHeaderValueVO(DeliveryHVO.class.getName());
    // �������۶����ۺ�vo
    DeliveryVO[] vos = (DeliveryVO[]) AggVOUtil.createBillVO(new DeliveryHVO[] {
      hvo
    }, bvos, DeliveryVO.class);
    int flag = service.reserveBill(SOBillType.Delivery.getCode(), vos);
    return flag;
  }
}
