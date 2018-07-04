package nc.ui.so.m32.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.rule.UniteArsubRule;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.HeadSumMny;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;

/**
 * ��Ʊ��ͷ��ֽ��༭�¼�
 * 
 * @since 6.0
 * @version 2011-4-21 ����06:33:17
 * @author ô��
 */
public class TotalOrigSubMnyEditHandler {

  /**
   * ��ֽ��༭���¼�����
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e, SaleInvoiceEditor editor,
      BillManageModel model) {
    NCLangRes res = NCLangRes.getInstance();
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    SaleInvoiceEditor invoiceditor = editor;
    SaleInvoiceManageModel invoicemodel = (SaleInvoiceManageModel) model;
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();

    String title = res.getStrByID("4006008_0", "04006008-0075")/*ȷ��ȡ���ֳ��*/;
    /*ȡ���ϲ�������ǰ���еĳ�ֹ�ϵ�������ȷ��Ҫȡ�������*/
    String question = res.getStrByID("4006008_0", "04006008-0083");

    UFDouble nowtotalsubmny =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NTOTALORIGSUBMNY);
    // ���Ϊnull����0��ȡ�����
    if (MathTool.isZero(nowtotalsubmny)) {
      if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(WorkbenchEnvironment
          .getInstance().getWorkbench().getParent(), title, question)) {
        // ȡ�����ó��
        this.cancelOffset(keyValue, invoiceditor);
      }
    }
    else {
      if (null != cachevo && cachevo.getBcombinflag()) {
        keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, e.getOldValue());
        ExceptionUtils.wrappBusinessException(res.getStrByID("4006008_0",
            "04006008-0141")/*����״̬�²�������ó��*/);
        return;
      }
      // ȡ�����ó��
      this.cancelOffset(keyValue, invoiceditor);
      // ���³��
      this.redoOffset(keyValue, invoiceditor, model, nowtotalsubmny);

    }
  }

  private void cancelOffset(CardKeyValue keyValue,
      SaleInvoiceEditor invoiceeditor) {
    // �ָ���������
    List<Integer> offsetrows = this.resetBillVO(keyValue);
    this.doCancelOffsetafter(keyValue, offsetrows, invoiceeditor);
  }

  /**
   * ȡ����ֺ���
   * 
   * @param cardPanel
   * @param keyValue
   * @param offsetrows
   * @param invoiceeditor
   */
  private void doCancelOffsetafter(CardKeyValue keyValue,
      List<Integer> offsetrows, SaleInvoiceEditor invoiceeditor) {

    // �����������۽�ʼ
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }
    // ��־λ
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);

    this.processDisAfter(changerows, invoiceeditor);

    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    invoiceeditor.setTempvo(tempvo);
    // �Ƿ������ϲ���Ʊ
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);
    keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, UFDouble.ZERO_DBL);

  }

  /**
   * ��ַ����������
   * 
   * @param changerows
   * @param invoiceeditor
   * @param tempvo
   * @param interfacerule
   */
  private void doOffsetafter(Integer[] changerows,
      SaleInvoiceEditor invoiceeditor, OffsetTempVO tempvo,
      OffsetUtil interfacerule) {
    BillCardPanel cardPanel = invoiceeditor.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }
    // ���ñ�־λ
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.TRUE);
    this.processDisAfter(intchangerows, invoiceeditor);

    // �õ��µĺϲ���Ʊ��ϵ����ֹ�ϵnewhmrelation,set������
    Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
    Map<String, UFDouble> newhmrelation =
        interfacerule.getNewRelation(hmrelation);
    tempvo.setHmArsubRelation(newhmrelation);
    invoiceeditor.setTempvo(tempvo);

  }

  private void processDisAfter(int[] intchangerows,
      SaleInvoiceEditor invoiceeditor) {
    BillCardPanel cardPanel = invoiceeditor.getBillCardPanel();
    // �����������۽��
    if (!VOChecker.isEmpty(intchangerows)) {
      CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
      calculator.calculate(intchangerows, SaleInvoiceBVO.NORIGTAXMNY);
      // ����VAT��Ϣ
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      for (int row : intchangerows) {
        vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
      }
    }
    // �����ͷ��˰�ϼơ��ϲ���Ʊ���
    HeadSumMny hsmrule = new HeadSumMny();
    hsmrule.process(cardPanel);
    // ���ñ༭��
    invoiceeditor.setCardEditEnable();
  }

  private void redoOffset(CardKeyValue keyValue,
      SaleInvoiceEditor invoiceeditor, BillManageModel model,
      UFDouble nowtotalsubmny) {
    UniteArsubRule uniteRule = new UniteArsubRule(keyValue);
    // ����Ƿ��пɳ�ֵ���
    Map<Integer, OffsetParaVO> itfvo = uniteRule.getinterfaceDatas();
    // ����Ƿ��пɳ�ֵ���
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0005")/*@res "��Ʒ���ۿۺ������в�����֣����˺�û�пɳ�ֵ���"*/);
    }
    // ��ȡ���۶���ԭ�ȳ�ֹ�ϵ
    OffsetTempVO tempvo = invoiceeditor.getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }
    String pk_group = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String defaultwhere = sqlutil.getInvoiceDefaultSql(pk_group, itfvo);
    OffsetUtil interfacerule = new OffsetUtil(pk_group, itfvo);

    String billid = keyValue.getHeadStringValue(SaleInvoiceHVO.CSALEINVOICEID);
    // ������
    Map<Integer, UFDouble> dismap =
        interfacerule.autoOffsetArsub(defaultwhere, nowtotalsubmny, tempvo,
            billid);
    uniteRule.distributeMapToVO(dismap);

    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doOffsetafter(changerows, invoiceeditor, tempvo, interfacerule);
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0006")/*@res "���ó�ֳɹ�"*/, model.getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0150")/*@res "û�пɳ�ֵ����۷��õ�!"*/, model.getContext());
    }
  }

  /**
   * �ָ����ǰ����
   * 
   * @param keyValue
   * @return
   */
  private List<Integer> resetBillVO(CardKeyValue keyValue) {
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
