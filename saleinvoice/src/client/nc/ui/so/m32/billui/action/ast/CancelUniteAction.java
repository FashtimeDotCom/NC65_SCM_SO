package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.HeadSumMny;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ȡ����ְ�ť��Ӧ��
 * 
 * @since 6.0
 * @version 2010-12-10 ����02:38:09
 * @author ô��
 */
public class CancelUniteAction extends NCAction {

  /**
   * VersionUID
   */
  private static final long serialVersionUID = -3447700512314859547L;

  /** ��Ʊ���ؼ� */
  private SaleInvoiceEditor editor;

  /** ��Ʊ����Ӧ��ģ�� */
  private AbstractAppModel model;

  /**
   * CancelUniteAction �Ĺ�����
   */
  public CancelUniteAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    NCLangRes nclangres = NCLangRes.getInstance();
    String title = nclangres.getStrByID("4006008_0", "04006008-0075")/*ȷ��ȡ���ֳ��*/;
    /*ȡ�����ó�ֽ�����ǰ���еĳ�ֹ�ϵ�������ȷ��Ҫȡ�������*/
    String question = nclangres.getStrByID("4006008_0", "04006008-0076");
    if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(this.getModel()
        .getContext().getEntranceUI(), title, question, UIDialog.ID_NO)) {
      BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
      IKeyValue keyValue = new CardKeyValue(cardPanel);
      // �ָ���������
      List<Integer> offsetrows = this.resetBillVO();

      this.doafter(offsetrows);

      OffsetTempVO tempvo = new OffsetTempVO();
      tempvo.setIsCancelOffset(true);
      this.getEditor().setTempvo(tempvo);
      // �Ƿ������ϲ���Ʊ
      keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);
      keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, UFDouble.ZERO_DBL);

      this.setEnabled(false);
    }
  }

  /**
   * ���ؼ�
   * 
   * @return ���ؼ�
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * ������������������model���ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-6-21 ����09:46:05
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ���ؼ�
   * 
   * @param editor ���ؼ�
   */
  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  /**
   * ������������������model���ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param model
   *          <p>
   * @author fengjb
   * @time 2010-6-21 ����09:46:19
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // �ϲ��༭�²����������ó��
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    if (null != cachevo && cachevo.getBcombinflag()) {
      return false;
    }

    // ѡ�е����۷�Ʊ
    SaleInvoiceVO selInvoice = (SaleInvoiceVO) this.editor.getValue();
    UFBoolean isUnit = null;

    if (null != selInvoice) {
      isUnit = selInvoice.getParentVO().getBsubunitflag();
      // �Գ����ɲ���
      if (OpposeFlag.GENERAL.equalsValue(selInvoice.getParentVO()
          .getFopposeflag())) {
        return false;
      }

    }
    return isUnit == null ? false : isUnit.booleanValue();
  }

  private void doafter(List<Integer> offsetrows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // �����������۽�ʼ
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }
    CardPanelCalculator calculator =
        new CardPanelCalculator(this.getEditor().getBillCardPanel());
    calculator.calculate(changerows, SaleInvoiceBVO.NORIGTAXMNY);
    // �����������۽�����

    // ���¼���VAT��Ϣ��ʼ
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    for (int row : changerows) {
      vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
    }
    // ���¼���VAT��Ϣ����

    // �����ͷ��˰�ϼơ��ϲ���Ʊ���
    HeadSumMny hsmrule = new HeadSumMny();
    hsmrule.process(cardPanel);

    // ��־λ
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);
    // ���ñ༭��
    this.editor.setCardEditEnable();

  }

  /**
   * ����������������ʼ����ȡ���ϲ�����ť��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-6-21 ����09:32:44
   */
  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_UNOFFSET);
  }

  private List<Integer> resetBillVO() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ԭ��ͷ�ϲ���Ʊ�ܽ��
    UFDouble ntotalorigsubmny =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NTOTALORIGSUBMNY);
    // ��˰�ϼ�
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NTOTALORIGMNY);
    ntotalorigmny = MathTool.add(ntotalorigmny, ntotalorigsubmny);
    keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGMNY, ntotalorigmny);
    keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGMNY, null);
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);

    List<Integer> offsetrows = new ArrayList<Integer>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // ��˰�ϼ�
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
      // �ϲ���Ʊ���
      UFDouble submny =
          keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGSUBMNY);
      if (null == submny || submny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      origtaxmny = MathTool.add(origtaxmny, submny);
      keyValue.setBodyValue(i, SaleInvoiceBVO.NORIGTAXMNY, origtaxmny);
      keyValue.setBodyValue(i, SaleInvoiceBVO.NORIGSUBMNY, null);

      offsetrows.add(Integer.valueOf(i));
    }
    return offsetrows;
  }

}
