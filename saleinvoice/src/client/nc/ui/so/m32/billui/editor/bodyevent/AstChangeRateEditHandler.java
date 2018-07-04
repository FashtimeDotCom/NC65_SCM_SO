package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.rule.UnitChangeRateRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ʱ༭�¼�
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-26 ����06:05:40
 */
public class AstChangeRateEditHandler {
  /**
   * ����������������λ������λ�����ʱ༭ǰ�¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����06:08:51
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UnitChangeRateRule changraterule = new UnitChangeRateRule(keyValue);
    // �����Ƿ�̶����������ñ༭��
    boolean value = changraterule.isAstFixedRate(e.getRow());
    e.setReturnValue(Boolean.valueOf(!value));
  }
}
