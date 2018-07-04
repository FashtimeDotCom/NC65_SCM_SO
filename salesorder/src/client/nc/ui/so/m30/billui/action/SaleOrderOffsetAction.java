package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m35.service.so.m30.itf.IDlgArsubToM30Ctr;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.ItfParaVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;

/**
 * ���۶������ó�ֵİ�ť��Ӧ��
 * 
 * @since 6.0
 * @version 2010-12-10 ����09:53:52
 * @author ô��
 */
public class SaleOrderOffsetAction extends NCAction {

  /**
   * Version
   */
  private static final long serialVersionUID = 9162084216392130278L;

  /** �������ؼ� */
  private SaleOrderBillForm editor;

  /** ��������Ӧ��ģ�� */
  private AbstractAppModel model;

  public SaleOrderOffsetAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_OFFSET);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (null != this.editor.getM30ClientContext()
        && this.editor.getM30ClientContext().getIsCashSale()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0009")
          /*@res "���Ѿ����������������������ó�֣�"*/);
    }
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    OffsetItfVOUtil voutil = new OffsetItfVOUtil(keyValue);

    // ����Ƿ��пɳ�ֵ���
    Map<Integer, OffsetParaVO> itfvo = voutil.getinterfaceData();
    // ����Ƿ��пɳ�ֵ���
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0239", null, new String[] {})
          /*��Ʒ���ۿۺ������в�����֣����˺�û�пɳ�ֵ���,�����ֱ����Ƿ�Ϊ0*/);
    }
    // ��ȡ���۶���ԭ�ȳ�ֹ�ϵ
    OffsetTempVO tempvo = this.getEditor().getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }

    // ��ѯ��������ʾ���۷��õ�����û�ѡ�������۷��õ���ͼVO
    IDlgArsubToM30Ctr control =
        NCUILocator.getInstance().lookup(IDlgArsubToM30Ctr.class,
            NCModule.SO.getName());
    String billid = keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    String defaultwhere = sqlutil.getOrderDefaultSql(pk_group, itfvo);
    ArsubViewVO[] selectviewvos = null;
    ItfParaVO itfparavo = new ItfParaVO();
    itfparavo.setBillid(billid);
    itfparavo.setDefaultwhere(defaultwhere);
    itfparavo.setItfvomap(itfvo);
    itfparavo.setOffsettempvo(tempvo);
    try {
      control.queryAndLoadArsub(this.getEditor().getBillCardPanel(),
          this.model, itfparavo);
      selectviewvos = control.getSelectedVOs();
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    Map<Integer, OffsetParaVO> newitfvo = voutil.getinterfaceData();
    OffsetUtil offsetutil = new OffsetUtil(pk_group, newitfvo);
    // ��÷����ϵ������Ϊkey��ֽ��ΪValue

    Map<Integer, UFDouble> dismap = offsetutil.manualoffsetArsub(selectviewvos);
    voutil.distributeMapToVO(dismap);
    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doafter(changerows);
      // �õ��µĳ�ֹ�ϵ����ֹ�ϵnewhmrelation,set������
      Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
      Map<String, UFDouble> newhmrelation =
          offsetutil.getNewRelation(hmrelation);
      tempvo.setHmArsubRelation(newhmrelation);
      this.getEditor().setTempvo(tempvo);

      // Ϊ�˸���ȡ�����ó�ְ�ť�Ŀ���״̬
      AppEvent appevent =
          new AppEvent(
              "nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent");
      this.getModel().fireEvent(appevent);
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
   * @return Ӧ��ģ��
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
    UIState uistate = this.getModel().getUiState();

    if (UIState.ADD.equals(uistate) || UIState.EDIT.equals(uistate)) {
      CardKeyValue keyValue = new CardKeyValue(this.editor.getBillCardPanel());
      String tranTypeCode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      String pk_group = AppContext.getInstance().getPkGroup();
      SaleOrderClientContext cache = this.editor.getM30ClientContext();
      M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
      if (m30transvo != null) {
        if (m30transvo.getBlrgcashflag().booleanValue()) {
          return false;
        }
        else {
          return true;
        }
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  /**
   * ��һЩ����������ҵ����������������۽������ͷ��˰�ϼơ���ֽ�
   * 
   * @param changerows
   */
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
      CardEditSetter editset = new CardEditSetter(this.editor);
      editset.setEditEnable();
    }

  }

}
