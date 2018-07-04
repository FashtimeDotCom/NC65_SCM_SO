package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.rule.UniteArsubRule;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.ItfParaVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.HeadSumMny;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.m35.service.so.m32.itf.IDlgArsubToM32Ctr;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ���۷�Ʊ�ϲ���Ʊ����ʵ��
 * 
 * @since 6.0
 * @version 2010-12-10 ����02:34:10
 * @author ô��
 */
public class UniteArsubAction extends NCAction {

  /** Version */
  private static final long serialVersionUID = -5212633567393359840L;

  /** ȡ���ϲ���ť */
  private CancelUniteAction cancelUniteBtn;

  /** ��Ʊ���ؼ� */
  private SaleInvoiceEditor editor;

  /** ��Ʊ����Ӧ��ģ�� */
  private AbstractAppModel model;

  /**
   * ���췽��
   */
  public UniteArsubAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UniteArsubRule uniteRule = new UniteArsubRule(keyValue);

    // ����Ƿ��пɳ�ֵ���
    Map<Integer, OffsetParaVO> itfvo = uniteRule.getinterfaceDatas();
    // ����Ƿ��пɳ�ֵ���
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0005")/*@res "��Ʒ���ۿۺ������в�����֣����˺�û�пɳ�ֵ���"*/);
    }

    // ��ȡ���۶���ԭ�Ⱥϲ���Ʊ��ϵ
    OffsetTempVO tempvo = this.getEditor().getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }
    // ��ѯ��������ʾ���۷��õ�
    IDlgArsubToM32Ctr control =
        NCUILocator.getInstance().lookup(IDlgArsubToM32Ctr.class,
            NCModule.SO.getName());
    String pk_group = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    String billid = keyValue.getHeadStringValue(SaleInvoiceHVO.CSALEINVOICEID);

    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String defaultwhere = sqlutil.getInvoiceDefaultSql(pk_group, itfvo);

    ItfParaVO itfparavo = new ItfParaVO();
    itfparavo.setBillid(billid);
    itfparavo.setDefaultwhere(defaultwhere);
    itfparavo.setItfvomap(itfvo);
    itfparavo.setOffsettempvo(tempvo);
    control.queryAndLoadArsub(this.getEditor().getBillCardPanel(),
        this.getModel(), itfparavo);
    ArsubViewVO[] selectviewvos = control.getSelectedVOs();
    Map<Integer, OffsetParaVO> newitfvo = uniteRule.getinterfaceDatas();
    OffsetUtil interfacerule = new OffsetUtil(pk_group, newitfvo);
    // ������
    Map<Integer, UFDouble> dismap =
        interfacerule.manualoffsetArsub(selectviewvos);
    uniteRule.distributeMapToVO(dismap);

    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doafter(changerows);
      // �õ��µĺϲ���Ʊ��ϵ����ֹ�ϵnewhmrelation,set������
      Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
      Map<String, UFDouble> newhmrelation =
          interfacerule.getNewRelation(hmrelation);
      tempvo.setHmArsubRelation(newhmrelation);
      this.getEditor().setTempvo(tempvo);
    }

    this.getCancelUniteBtn().setEnabled(true);
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
            "04006008-0006")/*@res "���ó�ֳɹ�"*/, this.getModel().getContext());
  }

  /**
   * 
   * ȡ���ϲ���Ʊ�İ�ť
   * 
   * @return ȡ���ϲ���Ʊ�İ�ť
   */
  public CancelUniteAction getCancelUniteBtn() {
    return this.cancelUniteBtn;
  }

  /**
   * ��Ʊ���ؼ�
   * 
   * @return ��Ʊ���ؼ�
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * Ӧ��ģ��
   * 
   * @return Ӧ��ģ��
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ȡ����ְ�ť
   * 
   * @param cancelUniteBtn ȡ����ְ�ť
   */
  public void setCancelUniteBtn(CancelUniteAction cancelUniteBtn) {
    this.cancelUniteBtn = cancelUniteBtn;
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
   * Ӧ�й���ģ��
   * 
   * @param model Ӧ�й���ģ��
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // û�б����ʱ���ô���
    if (keyValue.getBodyCount() == 0) {
      return false;
    }

    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    if (null != cachevo && cachevo.getBcombinflag()) {
      return false;
    }
    SaleInvoiceVO selInvoice = (SaleInvoiceVO) this.editor.getValue();
    if (null != selInvoice) {
      // �Գ����ɲ���
      if (OpposeFlag.GENERAL.equalsValue(selInvoice.getParentVO()
          .getFopposeflag())) {
        return false;
      }

    }
    return super.isActionEnable();
  }

  private void doafter(Integer[] changerows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }
    if (!VOChecker.isEmpty(intchangerows)) {
      // �����������۽��
      CardPanelCalculator calculator =
          new CardPanelCalculator(this.getEditor().getBillCardPanel());
      calculator.calculate(intchangerows, SaleInvoiceBVO.NORIGTAXMNY);
      // ���¼���VAT��Ϣ
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      for (int row : intchangerows) {
        vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
      }
    }

    // �����ͷ��˰�ϼơ��ϲ���Ʊ���
    HeadSumMny hsmrule = new HeadSumMny();
    hsmrule.process(cardPanel);

    // ���ñ�־λ
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.TRUE);
    // ���ñ༭��
    this.editor.setCardEditEnable();

  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_OFFSET);
  }

}
