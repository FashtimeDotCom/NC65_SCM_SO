package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ȡ�����ó�ֵİ�ť��Ӧ��
 * �������е�Map��գ������Ƿ�ȡ�����ó��,������һ���������۽������Ϳ��ƽ���༭��
 * 
 * @since 6.0
 * @version 2010-12-10 ����11:42:13
 * @author ô��
 */
public class SaleOrderUnOffsetAction extends NCAction {

  /**
   * Version
   */
  private static final long serialVersionUID = 6323774075578177967L;

  /** �������ؼ� */
  private SaleOrderBillForm editor;

  /** ��������Ӧ��ģ�� */
  private AbstractAppModel model;

  public SaleOrderUnOffsetAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_UNOFFSET);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    boolean flag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG).booleanValue();
    if (!flag) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0022")/*@res "����δ�������ó��"*/);
    }
    String title =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0246")/*ȷ��ȡ�����*/;
    String question =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0247")
    /*��ȷ���Ƿ�Ҫȡ����ǰ���ݳ�������*/;
    if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(this.getModel()
        .getContext().getEntranceUI(), title, question, UIDialog.ID_NO)) {
      // �ָ���������
      List<Integer> offsetrows = this.resetBillVO();

      this.doafter(offsetrows);

      OffsetTempVO tempvo = new OffsetTempVO();
      tempvo.setIsCancelOffset(true);
      this.getEditor().setTempvo(tempvo);
      // �Ƿ��������
      keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.FALSE);
    }
  }

  /**
   * �������ؼ�getter
   * 
   * @return �������ؼ�
   */
  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  /**
   * ����Ӧ��ģ��getter
   * 
   * @return Ӧ�ù�ϵģ��
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * �������ؼ�setter
   * 
   * @param editor ���ؼ�
   */
  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  /**
   * �������ؼ�setter
   * 
   * @param model Ӧ�й���ģ��
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // ѡ�е����۶���
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UFBoolean isoffset =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    if (null == isoffset) {
      isoffset = UFBoolean.FALSE;
    }
    return isoffset.booleanValue();
  }

  /**
   * ��һЩ����������ҵ����������������۽������ͷ��˰�ϼơ���ֽ����ƽ���༭��
   * 
   * @param offsetrows
   */
  private void doafter(List<Integer> offsetrows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // �����������۽�ʼ
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    SaleOrderCalculator calcutor = new SaleOrderCalculator(cardPanel);
    calcutor.setTranTypeVO(trantypevo);
    calcutor.calculate(changerows, SaleOrderBVO.NORIGTAXMNY);

    // �����������۽�����

    // �����ͷ��˰�ϼơ���ֽ��
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // ���ñ༭��
    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {
      CardEditSetter editset = new CardEditSetter(this.editor);
      editset.setOriginalEdit();

    }
  }

  /**
   * ȡ�����ó�ָֻ��������ݡ�
   * 
   * @return �仯������������
   */
  private List<Integer> resetBillVO() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ԭ��ͷ����ܽ��
    UFDouble ntotalorigsubmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGSUBMNY);
    // ��˰�ϼ�
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    ntotalorigmny = MathTool.add(ntotalorigmny, ntotalorigsubmny);
    keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGMNY, ntotalorigmny);
    keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGMNY, null);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.FALSE);

    List<Integer> offsetrows = new ArrayList<Integer>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // ��˰�ϼ�
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGTAXMNY);
      // ��ֽ��
      UFDouble submny =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGSUBMNY);
      if (null == submny || submny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      origtaxmny = MathTool.add(origtaxmny, submny);
      keyValue.setBodyValue(i, SaleOrderBVO.NORIGTAXMNY, origtaxmny);
      keyValue.setBodyValue(i, SaleOrderBVO.NORIGSUBMNY, null);

      offsetrows.add(Integer.valueOf(i));
    }
    return offsetrows;
  }
}
