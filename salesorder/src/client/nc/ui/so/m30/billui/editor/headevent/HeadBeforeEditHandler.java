package nc.ui.so.m30.billui.editor.headevent;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.billui.rule.IsEditableCheckRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ������ͷ�༭ǰ�¼��ɷ���
 * 
 * @since 6.0
 * @version 2011-6-8 ����11:19:58
 * @author fengjb
 */
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {
  
  private SaleOrderBillForm billform;

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }
  

  private final static Set<String> bodyfiedl = new HashSet<String>();

  static {
    for (String key : CardEditSetter.HEADNOTEDITFIELDS) {
      bodyfiedl.add(key);
    }
  }

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {

    String editkey = e.getKey();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String trantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(trantypeid)
        && SaleOrderHVO.CORIGCURRENCYID.equals(editkey)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0029")/*@res "����¼�뽻������"*/);
    }
    // �༭�Կ���
    notEdit(e);

    // ��������
    if (SaleOrderHVO.CTRANTYPEID.equals(editkey)) {
      TranTypeEditHandler handler = new TranTypeEditHandler();
      handler.beforeEdit(e);
    }
    // ��Ʒ�Ҹ�����
    else if (SaleOrderHVO.CARSUBTYPEID.equals(editkey)) {
      ArsubTypeEditHandler handler = new ArsubTypeEditHandler();
      handler.beforeEdit(e,this.billform);
    }
    // �ͻ�
    else if (SaleOrderHVO.CCUSTOMERID.equals(editkey)) {
      CustomerEditHandler handler = new CustomerEditHandler();
      handler.beforeEdit(e);
    }

    // �ͻ��ջ���ַ( by liylr)
    else if (SaleOrderHVO.CHRECEIVEADDID.equals(editkey)) {
      HeadReceiveaddrEditHandler handler = new HeadReceiveaddrEditHandler();
      handler.beforeEdit(e);
    }

    // ҵ��Ա
    else if (SaleOrderHVO.CEMPLOYEEID.equals(editkey)) {
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEdit(e);
    }
    else if (SaleOrderHVO.CDEPTVID.equals(editkey)) {
      DeptEdithandler handler = new DeptEdithandler();
      handler.beforeEdit(e);
    }
    // ����
    else if (SaleOrderHVO.CORIGCURRENCYID.equals(editkey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.beforeEdit(e);
    }
    // ɢ��
    else if (SaleOrderHVO.CFREECUSTID.equals(editkey)) {
      FreeCustEditHandler handler = new FreeCustEditHandler();
      handler.beforeEdit(e);
    }
    // ���������ʻ�
    else if (SaleOrderHVO.CCUSTBANKACCID.equals(editkey)) {
      CustBankAccEditHandler handler = new CustBankAccEditHandler();
      handler.beforeEdit(e);
    }
    // �տ�Э��
    else if (SaleOrderHVO.CPAYTERMID.equals(editkey)) {
      PayTermEditHandler handler = new PayTermEditHandler();
      handler.beforeEdit(e);
    }
    else if (SaleOrderHVO.CINVOICECUSTID.equals(editkey)) {
      InvoiceCustEditHandler handler = new InvoiceCustEditHandler();
      handler.beforeEdit(e);
    }

  }

  private void notEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer status = keyValue.getHeadIntegerValue(SaleOrderHVO.FSTATUSFLAG);

    // ����״̬�༭�Կ�
    boolean isEditable =
        new IsEditableCheckRule().check(e.getBillCardPanel(), -1, e.getKey());
    if (BillStatus.AUDITING.equalsValue(status) && !isEditable) {
      e.setReturnValue(Boolean.FALSE);
    }

    // �տ�󣬲��ܱ༭���ֶ�
    UFDouble nreceivedmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
    if (!MathTool.isZero(nreceivedmny) && bodyfiedl.contains(e.getKey())) {
      e.setReturnValue(Boolean.FALSE);
    }
  }

}
