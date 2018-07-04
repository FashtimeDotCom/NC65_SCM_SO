package nc.ui.so.m30.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class TotalOrigSubMnyEditHandler {

  private SaleOrderBillForm billform;

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    String title =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0256")/*ȷ��ȡ���ֳ��*/;
    String question =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0257")/*ȡ����ֽ�����ǰ���еĳ�ֹ�ϵ�������ȷ��Ҫȡ�������*/;
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UFDouble nowtotalsubmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGSUBMNY);
    // ���Ϊnull����0��ȡ�����
    if (MathTool.isZero(nowtotalsubmny)) {
      if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(WorkbenchEnvironment
          .getInstance().getWorkbench().getParent(), title, question)) {
        // ȡ�����ó��
        this.cancelOffset(cardPanel);
      }
    }
    else {
      // ȡ�����ó��
      this.cancelOffset(cardPanel);
      // ���³��
      LoginContext contex = e.getContext();
      this.redoOffset(cardPanel, contex, nowtotalsubmny);

    }

  }

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  private void cancelOffset(BillCardPanel cardPanel) {
    // �ָ���������
    List<Integer> offsetrows = this.resetBillVO(cardPanel);
    this.doCancelOffsetafter(cardPanel, offsetrows);
  }

  /**
   * ȡ����ֺ���
   * 
   * @param cardPanel
   * @param keyValue
   * @param offsetrows
   * @param ordereditor
   */
  private void doCancelOffsetafter(BillCardPanel cardPanel,
      List<Integer> offsetrows) {

    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // �����������۽�ʼ
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }

    this.processDisAfter(cardPanel, changerows);

    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    this.billform.setTempvo(tempvo);
    // �Ƿ��������
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.FALSE);
    keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGSUBMNY, UFDouble.ZERO_DBL);

  }

  /**
   * ��ַ����������
   * 
   * @param changerows
   * @param ordereditor
   * @param tempvo
   * @param interfacerule
   */
  private void doOffsetafter(BillCardPanel cardPanel, Integer[] changerows,
      OffsetTempVO tempvo, OffsetUtil interfacerule) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }
    // ���ñ�־λ
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    this.processDisAfter(cardPanel, intchangerows);

    // �õ��µĺϲ���Ʊ��ϵ����ֹ�ϵnewhmrelation,set������
    Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
    Map<String, UFDouble> newhmrelation =
        interfacerule.getNewRelation(hmrelation);
    tempvo.setHmArsubRelation(newhmrelation);
    this.billform.setTempvo(tempvo);

  }

  private void processDisAfter(BillCardPanel cardPanel, int[] intchangerows) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // �����������۽�ʼ
    if (!VOChecker.isEmpty(intchangerows)) {
      SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
      calcultor.calculate(intchangerows, SaleOrderBVO.NORIGTAXMNY);
    }
    // �����������۽�����
    // �����ͷ��˰�ϼơ���ֽ��
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // ���ñ༭��

    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {
      CardEditSetter editset = new CardEditSetter(this.billform);
      editset.setEditEnable();
    }
  }

  private void redoOffset(BillCardPanel cardPanel, LoginContext contex,
      UFDouble nowtotalsubmny) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    OffsetItfVOUtil voutil = new OffsetItfVOUtil(keyValue);
    // ����Ƿ��пɳ�ֵ���
    Map<Integer, OffsetParaVO> itfvo = voutil.getinterfaceData();
    // ����Ƿ��пɳ�ֵ���
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0030")/*@res "��Ʒ���ۿۺ������в�����֣����˺�û�пɳ�ֵ���"*/);
    }
    // ��ȡ���۶���ԭ�ȳ�ֹ�ϵ
    OffsetTempVO tempvo = this.billform.getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }
    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String defaultwhere = sqlutil.getOrderDefaultSql(pk_group, itfvo);
    OffsetUtil interfacerule = new OffsetUtil(pk_group, itfvo);

    String billid = keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
    // ������
    Map<Integer, UFDouble> dismap =
        interfacerule.autoOffsetArsub(defaultwhere, nowtotalsubmny, tempvo,
            billid);
    voutil.distributeMapToVO(dismap);

    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doOffsetafter(cardPanel, changerows, tempvo, interfacerule);
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0031")/*@res "���ó�ֳɹ�"*/, contex);
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0469")/*@res "��ǰû�п�ƥ��ķ��ó�ֵ���"*/, contex);
    }

  }

  /**
   * �ָ����ǰ����
   * 
   * @param keyValue
   * @return
   */
  private List<Integer> resetBillVO(BillCardPanel cardPanel) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ԭ��ͷ�ϲ���Ʊ�ܽ��
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
      // �ϲ���Ʊ���
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
