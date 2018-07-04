package nc.ui.so.m32.billui.action.line;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOMathUtil;

/**
 * ��Ʊɾ�п���
 * 
 * ���Ѿ��г�ֽ����в���ɾ��
 * 
 * @since 6.0
 * @version 2011-9-8 ����06:47:56
 * @author ô��
 */
public class InvoiceBodyDelLineAction extends BodyDelLineAction {

  private static final long serialVersionUID = -6452641588755419635L;

  @Override
  protected boolean doBeforeAction(ActionEvent e) {
    BillCardPanel cardPanel = this.getCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] srcrows = cardPanel.getBodyPanel().getTable().getSelectedRows();
    // �޶�ʱ������Ƿ�������֡�������ֲ�����ɾ��
    if (!this.checkCanDelOffsetRows(srcrows, keyValue)) {
      return false;
    }
    return super.doBeforeAction(e);
  }

  @Override
  public void doAction() {
    // ��Ҫ���¼���VAT��Ϣ
    BillCardPanel cardPanel = this.getCardPanel();
    int[] selrows = cardPanel.getBodyPanel().getTable().getSelectedRows();
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues[] oldvalues = new CalVatFieldValues[selrows.length];
    int i = 0;
    for (int row : selrows) {
      oldvalues[i] = vatcal.getVatFieldValues(row);
      i++;
    }
    super.doAction();
    for (CalVatFieldValues oldvalue : oldvalues) {
      vatcal.calVatWhenDeleteLine(oldvalue);
    }
    // �޸Ĵ���zhangby5 δ���м�˰�ϼ�
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(keyValue);
  }

  /**
   * �Ѿ�������ֵ��в�����ɾ��
   * 
   * @param srcrows
   * @param keyValue
   * @return
   */
  private boolean checkCanDelOffsetRows(int[] srcrows, IKeyValue keyValue) {
    if (null == srcrows || srcrows.length == 0) {
      return true;
    }

    boolean combindelrow = false;
    for (int row : srcrows) {
      String csaleinvoicebid =
          keyValue.getBodyStringValue(row, SaleInvoiceBVO.CSALEINVOICEBID);
      if ("isnull".equals(csaleinvoicebid)) {
        combindelrow = true;
        break;
      }
    }
    if (combindelrow) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0117")/*��ĩ���������в�����ɾ��*/);
    }

    List<String> offsetnotdelrow = new ArrayList<String>();
    for (int row : srcrows) {
      String rowno = keyValue.getBodyStringValue(row, SaleInvoiceBVO.CROWNO);
      UFDouble norigsubmny =
          keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
      if (!SOMathUtil.isZero(norigsubmny)) {
        offsetnotdelrow.add(rowno);
        continue;
      }

    }
    if (offsetnotdelrow.size() == 0) {
      return true;
    }
    StringBuilder offseterrrows = new StringBuilder();

    for (String rowno : offsetnotdelrow) {
      offseterrrows.append("[" + rowno + "]��");/*-=notranslate=-*/
    }
    offseterrrows.deleteCharAt(offseterrrows.length() - 1);

    // fengjb 2012.03.05 UE��ʾ�淶
    ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
        "4006008_0", "04006008-0129", null, new String[] {
            offseterrrows.toString()
        })/*���۷�Ʊ������:{0}��������֣�����ɾ��*/);

    return false;
  }
}
