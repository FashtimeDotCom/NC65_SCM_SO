package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletLinkEvent;
import nc.funcnode.ui.FuncletLinkListener;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.cashsale.CashSaleData;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;
import nc.vo.so.m30.util.CashSaleUtil;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.rule.OffsetUtil;

public class SaleOrderCashSaleAction extends NCAction {

  private class CashSaleLinkListener implements FuncletLinkListener {

    public CashSaleLinkListener() {
      // TODO Auto-generated constructor stub
    }

    /**
     * ����������������ĺ�������
     */
    @Override
    public void dealLinkEvent(FuncletLinkEvent event) {
      SaleOrderUserObject userObject =
          (SaleOrderUserObject) event.getUserObject();
      SaleOrderCashSaleAction.this.setCashSaleResult(userObject);
    }
  }

  private static final long serialVersionUID = 6584570365797299304L;

  private SaleOrderBillForm editor;

  private AbstractAppModel model;

  private OffsetItfVOUtil offsetItfUtil;

  private OffsetUtil offsetUtil;

  public SaleOrderCashSaleAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CASHPROCESS);
  }

  /**
   * ׼������������Ҫ������
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
	  
    if (!SysInitGroupQuery.isAREnabled()) {
	         ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
		          "4006005_0", "04006005-0028")/*Ӧ��ģ��δ���ã�*/);
    }
	  
    
    CardPanelValueUtils util = new CardPanelValueUtils(this.getCardPanel());
    CardKeyValue keyValue = new CardKeyValue(this.getCardPanel());
    String tranTypeCode =
            keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (PubAppTool.isNull(tranTypeCode)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0029")/*@res "����¼�뽻������"*/);
    }
    
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    UFBoolean blrgcashflag = trantypevo.getBlrgcashflag();
    UFBoolean offsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    UFDouble receivedmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
    if ((offsetflag != null && offsetflag.booleanValue())
        || MathTool.compareTo(receivedmny, UFDouble.ZERO_DBL) > 0
    		||(blrgcashflag!=null&&blrgcashflag.booleanValue())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0008")
      /*@res "���۶�����������Ϊ��Ʒ�Ҹ��������������ó�ֻ��߶����տ������������������������!"*/);
    }

    SaleOrderVO bill = util.getBillValueVO(SaleOrderVO.class);
    this.offsetItfUtil = new OffsetItfVOUtil(keyValue);
    Map<Integer, OffsetParaVO> offsetparamap =
        this.offsetItfUtil.getinterfaceData();
    this.offsetUtil =
        new OffsetUtil(bill.getParentVO().getPk_group(), offsetparamap);
    GatherbillUtil.getInstance().checkBeforeGathering(bill);
    if (null == bill.getChildrenVO() || bill.getChildrenVO().length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0364")/*����¼�����۶���û�б�������*/);
    }
    UFBoolean ufbSO30 =
        SOSysParaInitUtil.getSO30(bill.getChildrenVO()[0].getCarorgid());
    boolean bSO30 = ufbSO30 == null ? false : ufbSO30.booleanValue();
    // "�������Ƿ񵯳���ϸ��������"Ϊ��
    if (bSO30) {
      this.openCashSaleFuncNodeDialog(offsetparamap, bill);
    }
    // "�������Ƿ񵯳���ϸ��������"Ϊ��
    else {
      this.processCashSaleKeepIn(offsetparamap, bill);
    }
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0014")/*@res "�����ɹ�"*/, this.getModel().getContext());
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public OffsetItfVOUtil getOffsetItfUtil() {
    return this.offsetItfUtil;
  }

  public OffsetUtil getOffsetUtil() {
    return this.offsetUtil;
  }

  public void setCashSaleResult(SaleOrderUserObject userObject) {
    CardKeyValue keyValue =
        new CardKeyValue(this.getEditor().getBillCardPanel());
    /**
     * �������ó�ֺͷ��ó�ִ���һ������Ҫ��!!!!!
     * */
    // 1.���ó��:���۽����㡢���ý�����
    ArsubViewVO[] arsubViews = userObject.getArsubViews();
    if (arsubViews != null && arsubViews.length > 0) {
      Map<Integer, UFDouble> dismap =
          this.getOffsetUtil().manualoffsetArsub(arsubViews);
      this.getOffsetItfUtil().distributeMapToVO(dismap);
      if (null != dismap && dismap.size() > 0) {
        Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
        this.doafter(changerows);
        // �õ��µĳ�ֹ�ϵ����ֹ�ϵnewhmrelation,set������
        Map<String, UFDouble> newhmrelation =
            this.getOffsetUtil().getNewRelation(null);
        OffsetTempVO tempvo = new OffsetTempVO();
        tempvo.setHmArsubRelation(newhmrelation);
        this.getEditor().setTempvo(tempvo);
      }
    }
    // 2.�տ�����������տ���������á����ý�����
    SoBalanceVO soBalanceVO = userObject.getSoBalanceVO();
    if (soBalanceVO != null) {
      UFDouble receivemny = UFDouble.ZERO_DBL;
      SoBalanceUtil sobalanceutil = SoBalanceUtil.getInstance();
      receivemny =
          sobalanceutil.caculateSoBalanceTotalBodymny(soBalanceVO,
              SoBalanceBVO.NORIGTHISBALMNY);
      keyValue
          .setHeadValue(SaleOrderHVO.NRECEIVEDMNY, MathTool.add(
              keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY),
              receivemny));
      this.getEditor().setSobalancevo(soBalanceVO);
    }
    // 3.�����տ���ö��������տ�����ý�����
    UFDouble thisGatheringMny = userObject.getThisGatheringMny();
    if (thisGatheringMny != null
        && MathTool.absCompareTo(thisGatheringMny, UFDouble.ZERO_DBL) > 0) {
      keyValue.setHeadValue(SaleOrderHVO.NTHISRECEIVEMNY, thisGatheringMny);
      this.getEditor().setThisGatheringMny(thisGatheringMny);
    }
    // 4.������������������"���ó��"���ƿ�����
    this.getEditor().setIsCashSale(true);
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setOffsetItfUtil(OffsetItfVOUtil offsetItfUtil) {
    this.offsetItfUtil = offsetItfUtil;
  }

  public void setOffsetUtil(OffsetUtil offsetUtil) {
    this.offsetUtil = offsetUtil;
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.ADD
        || this.model.getUiState() == UIState.EDIT;
  }

  private void doafter(Integer[] changerows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // �����������۽�ʼ
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }

    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    SaleOrderCalculator calcutor = new SaleOrderCalculator(cardPanel);
    calcutor.setTranTypeVO(trantypevo);
    calcutor.calculate(intchangerows, SaleOrderBVO.NORIGTAXMNY);

    // �����������۽�����

    // �����ͷ��˰�ϼơ���ֽ��
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // ���ñ༭��
    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {

      CardEditSetter editset = new CardEditSetter(this.getEditor());
      editset.setEditEnable();
    }

  }

  private BillCardPanel getCardPanel() {
    return this.getEditor().getBillCardPanel();
  }

  private IFuncRegisterQueryService getFuncRegisterQueryService() {
    return NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
  }

  private void openCashSaleFuncNodeDialog(
      Map<Integer, OffsetParaVO> offsetparamap, SaleOrderVO bill) {
    FuncRegisterVO frVO = null;
    try {
      frVO = this.getFuncRegisterQueryService().queryFunctionByCode("40060399");
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    Dimension d = new Dimension(800, 600);
    FuncletInitData initData = new FuncletInitData();
    // initData.setInitType(168);
    CashSaleData initdata = new CashSaleData();
    initdata.setOrdervo(bill);
    // initdata.setSobalancevo(sobalancevo);
    initData.setInitData(initdata);
    initdata.setOffparamap(offsetparamap);

    CashSaleLinkListener linkListener = new CashSaleLinkListener();
    FuncletWindowLauncher.openFuncNodeDialog(this.getEditor(), frVO, initData,
        linkListener, true, false, d);
  }

  private void processCashSaleKeepIn(Map<Integer, OffsetParaVO> offsetparamap,
      SaleOrderVO bill) {
    CashSaleUtil cashSaleUtil = new CashSaleUtil();
    SaleOrderUserObject userOject =
        cashSaleUtil.processCashSaleKeepIn(offsetparamap, bill);
    this.setCashSaleResult(userOject);
  }

}
