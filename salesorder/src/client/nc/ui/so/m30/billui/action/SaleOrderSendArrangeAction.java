package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.scmpub.reference.uap.pf.PFConfig;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۶����������� ��ť
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 *
 * <ul>
 * <li>�򿪷������Žڵ�
 * <li>...
 * </ul>
 *
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-9-14 ����11:25:27
 */
public class SaleOrderSendArrangeAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 6552018706837538053L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  public SaleOrderSendArrangeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_SENDARRANGE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO vo = (SaleOrderVO) this.model.getSelectedData();
    SaleOrderVO newvo = this.filtervo(vo);
    if (null == newvo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0021")/*@res "ѡ�еĵ���û�����÷������̣����ܽ��з������š�"*/);
    }
    // billתView
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convertor =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convertor.convert(new SaleOrderVO[] {
      vo
    });
    // ��ʼ����
    FuncletInitData initData = new FuncletInitData();
    initData.setInitData(views);
    initData.setInitType(BillPushConst.BILL_PUSH);
    // �򿪷������Žڵ�
    IFuncRegisterQueryService funcService =
        NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
    FuncRegisterVO funvo = funcService.queryFunctionByCode("40060401");
    int screenWidth =
        Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight =
        Toolkit.getDefaultToolkit().getScreenSize().height -1;
    FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
        .getEntranceUI(), funvo, initData, null, true, new Dimension(
        screenWidth, screenHeight),true);
  }

  private SaleOrderVO filtervo(SaleOrderVO vo) {
    String[] busi = this.getBusi();
    if (null == busi || busi.length == 0) {
      return null;
    }
    Set<String> bizSet = new HashSet<String>();
    for (String biz : busi) {
      bizSet.add(biz);
    }
    String cbiztype = vo.getParentVO().getCbiztypeid();
    if (bizSet.contains(cbiztype)) {
      return vo;
    }
    return null;
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
    // �����б��ѡ��ť����(�ɺ�����˿ɰ�������)
    if (this.model.getSelectedOperaDatas() != null
        && this.model.getSelectedOperaDatas().length > 1) {
      return true;
    }

    // ���ڿ�Ƭ����������������һ��������֯�ǿ���δ�����ر�(���Ƶ��������Ž���������˿ɰ���������)
    if (this.model.getSelectedData() == null) {
      return false;
    }
    SaleOrderVO vo = (SaleOrderVO) this.model.getSelectedData();
    if (!BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
      return false;
    }
    boolean isSendArrange = false;
    SaleOrderBVO[] bodys = vo.getChildrenVO();
    if (!VOChecker.isEmpty(bodys)) {
      for (SaleOrderBVO body : bodys) {
        if (body.getCtrafficorgid() != null
            && !body.getBbsendendflag().booleanValue()) {
          isSendArrange = true;
          break;
        }
      }
    }

    return isSendArrange;
  }

  private String[] getBusi() {
    String pk_group = ClientContext.getInstance().getPk_group();
    String[] busi =
        PFConfig.queryBusiTypePks(pk_group, SOBillType.Delivery.getCode());
    return busi;
  }
}