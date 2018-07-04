package nc.ui.so.m30.revise.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;


public class ReviseSProfitCenterHandler {

  //�ۼ���������
  private static final String[] TOTALNUMKEY = new String[] {
    // �ۼƷ����������ۼƿ�Ʊ����
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
    // �ۼƳ��������� �ۼ�Ӧ��δ��������
    SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALNOTOUTNUM,
    // �ۼ�ǩ�������� �ۼ�;������
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM,
    // �ۼƳ���Գ��������ۼ��ݹ�Ӧ������
    SaleOrderBVO.NTOTALRUSHNUM, SaleOrderBVO.NTOTALESTARNUM,
    // �ۼ�ȷ��Ӧ���������ۼƳɱ���������
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    // �ۼ��ݹ�Ӧ�ս� �ۼ�ȷ��Ӧ�ս��
    SaleOrderBVO.NTOTALESTARMNY, SaleOrderBVO.NTOTALARMNY,
    // �ۼư���ί�ⶩ���������ۼư����빺������
    SaleOrderBVO.NARRANGESCORNUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    // �ۼư��ŵ��������������ۼư��ŵ�����������
    SaleOrderBVO.NARRANGETOORNUM, SaleOrderBVO.NARRANGETOAPPNUM,
    // �ۼư������������������ۼư��Ųɹ���������
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPONUM,
    // �ۼƷ�����Ʒ�� �ۼ��˻�����
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALTRADENUM
  };

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UFDouble value = null;
    int length = ReviseSProfitCenterHandler.TOTALNUMKEY.length;
    for (int i = 0; i < length; i++) {
      String key = ReviseSProfitCenterHandler.TOTALNUMKEY[i];
      value = keyValue.getBodyUFDoubleValue(e.getRow(), key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        e.setReturnValue(Boolean.FALSE);
      }
    }
    e.setReturnValue(Boolean.TRUE);
  }
}
