package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.arrange.util.M30ArrangeUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ֱ�˰��� ��ť
 *
 * @version 6.0
 * @since 6.0
 * @author ��־ΰ
 * @time 2010-9-15 ����09:37:39
 */
public class SaleOrderZYArrangeAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 2999584822238025922L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  private IM30TranTypeService tranTypeService;

  public SaleOrderZYArrangeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ZYARRANGE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objects = this.model.getSelectedOperaDatas();

    this.checkActionEnable(objects);
    // ���˿��԰��ŵ�
    SaleOrderVO[] newBills = new M30ArrangeUtil().filterSrcVOs(objects);

    // ��ʼ����
    FuncletInitData initData = new FuncletInitData();
    initData.setInitData(newBills);
    initData.setInitType(BillPushConst.BILL_PUSH);
    // �򿪷������Žڵ�
    IFuncRegisterQueryService funcService =
        NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
    FuncRegisterVO funvo = funcService.queryFunctionByCode("40060403");
    int screenWidth =
        Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight =
        Toolkit.getDefaultToolkit().getScreenSize().height - 1;
    FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
        .getEntranceUI(), funvo, initData, null, true, new Dimension(
        screenWidth, screenHeight),true);
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    Object[] objects = this.model.getSelectedOperaDatas();
    if (objects == null) {
      return false;
    }
    // �����б��ѡ��ť����(���Ƶ�ֱ�˰��Ž���������˿ɰ���������)
    if (objects.length > 1) {
      return true;
    }

    // ֻҪһ�����㰴ť���ã���������δ���Źر�(���Ƶ�ֱ�˰��Ž���������˿ɰ���������)
    boolean isZYArrange = false;
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
        if (bodys != null) {
          for (SaleOrderBVO body : bodys) {
            if (!body.getBarrangedflag().booleanValue()) {
              isZYArrange = true;
              break;
            }
          }
        }
      }
    }
    return isZYArrange;
  }

  private void checkActionEnable(Object[] objects) {
    // ֻҪһ�����㰴ť���ã���������δ���Źر�(���Ƶ�ֱ�˰��Ž���������˿ɰ���������)
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      int directtype = this.getDirecttype(vo);
      int directtran_to = DirectType.DIRECTTRAN_TO.getIntValue();
      int directtran_po = DirectType.DIRECTTRAN_PO.getIntValue();

      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())
          && (directtran_to == directtype || directtran_po == directtype)) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
          // 2014-01-16 dongli2
          // ֱ�˰���֮ǰ��bug���������а��Ź��Ͳ��ܶ������а��ţ�����isBarranged�ж��Ƿ���δ���ŵ���
          boolean isBarranged = true;
          for (SaleOrderBVO body : bodys) {
            if (!body.getBarrangedflag().booleanValue()) {
              isBarranged = false;
            }
          }
          if (isBarranged) {
            ExceptionUtils
                .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4006011_0", "04006011-0023")/*@res "���飺���۶����Ƿ��Ѿ����Źر�"*/);
          }
        }
        else {
          ExceptionUtils
              .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                  "4006011_0", "04006011-0248", null, new String[] {})/*���飺1.���۶����Ƿ��Ѿ�����  2.����������ֱ�����ͱ���Ƿ���ֱ�˵�������ֱ�˲ɹ�*/);
        }
      }

    }

  private int getDirecttype(SaleOrderVO svo) {
    int flag = 0;
    M30TranTypeVO m30type =
        this.getEditor()
            .getM30ClientContext()
            .getTransType(svo.getParentVO().getVtrantypecode(),
                svo.getParentVO().getPk_group());
    if (!VOChecker.isEmpty(m30type)) {
      flag = m30type.getFdirecttype().intValue();
    }
    else {
      try {
        m30type =
            this.getTranTypeService().queryTranTypeVO(
                svo.getParentVO().getCtrantypeid());
        this.getEditor().getM30ClientContext()
            .setTransType(svo.getParentVO().getVtrantypecode(), m30type);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
      flag = m30type.getFdirecttype().intValue();
    }
    return flag;
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }
}