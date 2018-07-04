package nc.ui.so.m30.billui.cash.editor.listener;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.cashsale.CashSaleData;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.m30.util.CashSaleUtil;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;

import nc.funcnode.ui.FuncletInitData;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;

import nc.ui.so.m30.billui.cash.model.CashSaleArsubModel;
import nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel;
import nc.ui.so.m30.billui.cash.view.CashSaleTopPanel;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.ui.uif2.UIState;

/**
 * �����������򿪼���
 * <p>
 * �����Զ�������ó�֡��տ�����������տ��ģ����չ�ִ�����
 * </p>
 * 
 * @since 6.0
 * @version 2011-4-21 ����03:24:53
 * @author ��־ΰ
 */
public class FuncNodeInitDataHandler implements IFuncNodeInitDataListener {

  private CashSaleArsubModel arsubmodel;

  private CashSaleSobalanceModel sobalancemodel;

  private CashSaleTopPanel toppanel;

  private CashSaleUtil cashSaleUtil;

  @Override
  public void initData(FuncletInitData data) {
    CashSaleData initdata = (CashSaleData) data.getInitData();
    if (initdata == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0028")/*@res "�ڵ��ʼ�����ݳ���"*/);
      return;
    }
    SaleOrderVO ordervo = initdata.getOrdervo();
    if (ordervo == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0028")/*@res "�ڵ��ʼ�����ݳ���"*/);
      return;
    }

    UFDouble totalOrigMny = ordervo.getParentVO().getNtotalorigmny();
    this.getToppanel().getOrgsummnyTextField()
        .setNumPoint(totalOrigMny.getPower());
    this.getToppanel().getOrgsummnyTextField().setValue(totalOrigMny);
    // 1.����ɳ�ֽ��
    String pk_group = ordervo.getParentVO().getPk_group();
    UFDouble sumSubMny =
        this.processOffset(pk_group, initdata.getOffparamap(),
            totalOrigMny.getPower());

    // 2.������տ�������
    UFDouble canGatheringVerifyMny = MathTool.sub(totalOrigMny, sumSubMny);
    if (MathTool.compareTo(canGatheringVerifyMny, UFDouble.ZERO_DBL) == 0) {
      return;
    }
    UFDouble sumGatheringVerifyMny =
        this.processGatheringVerify(canGatheringVerifyMny, ordervo,
            totalOrigMny.getPower());

    // 3.ʣ�����Ϊ���ζ����տ���
    UFDouble canOrderGatheringMny =
        MathTool.sub(canGatheringVerifyMny, sumGatheringVerifyMny);
    this.getToppanel().getThisreceivemnyTextField()
        .setNumPoint(totalOrigMny.getPower());
    this.getToppanel().getThisreceivemnyTextField()
        .setValue(canOrderGatheringMny);
  }

  /**
   * �����տ�����������տ�ĺ�����ϵ������һ��
   */
  private SoBalanceViewVO[] convertBillToView(SoBalanceVO newsobalancevo) {
    if (newsobalancevo == null) {
      return null;
    }
    // ���岹ȫ������֯
    SoBalanceHVO hvo = newsobalancevo.getParentVO();
    SoBalanceBVO[] bvos = newsobalancevo.getChildrenVO();
    for (SoBalanceBVO bvo : bvos) {
      bvo.setPk_org(hvo.getPk_org());
    }
    BillToViewConvertor<SoBalanceVO, SoBalanceViewVO> billtoview =
        new BillToViewConvertor<SoBalanceVO, SoBalanceViewVO>(
            SoBalanceViewVO.class);
    SoBalanceViewVO[] balanceviewvos = billtoview.convert(new SoBalanceVO[] {
      newsobalancevo
    });
    return balanceviewvos;
  }

  private UFDouble processGatheringVerify(UFDouble canGatheringVerifyMny,
      SaleOrderVO ordervo, int NumPoint) {
    SaleOrderUserObject userObject = new SaleOrderUserObject();
    UFDouble sumGatheringVerifyMny =
        this.getCashSaleUtil().processGatheringVerify(canGatheringVerifyMny,
            ordervo, userObject);
    SoBalanceVO newsobalancevo = userObject.getSoBalanceVO();
    // -- ת����viewVO
    SoBalanceViewVO[] newSoBalanceViews =
        this.convertBillToView(newsobalancevo);

    // --��ʼ����
    this.getSobalancemodel().initModel(newSoBalanceViews);
    this.getSobalancemodel().setUiState(UIState.EDIT);
    this.getToppanel().getHxmnyTextField().setNumPoint(NumPoint);
    this.getToppanel().getHxmnyTextField().setValue(sumGatheringVerifyMny);
    return sumGatheringVerifyMny;
  }

  private UFDouble processOffset(String pk_group,
      Map<Integer, OffsetParaVO> offparamap, int numpoint) {
    SaleOrderUserObject userObject = new SaleOrderUserObject();
    UFDouble sumSubMny =
        this.getCashSaleUtil().processOffset(pk_group, offparamap, userObject);
    ArsubViewVO[] arsubviewvos = userObject.getArsubViews();
    // --��ʼ����
    this.getArsubmodel().initModel(arsubviewvos);
    this.getArsubmodel().setUiState(UIState.EDIT);
    // --ͬʱ�ѳ������Ҳ��������
    this.getArsubmodel().setOffsetParaMap(offparamap);
    this.getToppanel().getSummnyTextField().setNumPoint(numpoint);
    this.getToppanel().getSummnyTextField().setValue(sumSubMny);
    return sumSubMny;
  }

  public CashSaleArsubModel getArsubmodel() {
    return this.arsubmodel;
  }

  public CashSaleUtil getCashSaleUtil() {
    if (this.cashSaleUtil == null) {
      this.cashSaleUtil = new CashSaleUtil();
    }
    return this.cashSaleUtil;
  }

  public CashSaleSobalanceModel getSobalancemodel() {
    return this.sobalancemodel;
  }

  public CashSaleTopPanel getToppanel() {
    return this.toppanel;
  }

  public void setArsubmodel(CashSaleArsubModel arsubmodel) {
    this.arsubmodel = arsubmodel;
  }

  public void setSobalancemodel(CashSaleSobalanceModel sobalancemodel) {
    this.sobalancemodel = sobalancemodel;
  }

  public void setToppanel(CashSaleTopPanel toppanel) {
    this.toppanel = toppanel;
  }
}
