package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.PreceiveQuotaRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * Ԥ�տ���ʱ༭�¼�
 * 
 * @since 6.0
 * @version 2011-6-9 ����09:44:24
 * @author fengjb
 */
public class PreceiveRateEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // Ԥ�տ����ݺϷ��Լ��
    UFDouble rate = keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVERATE);
    if (MathTool.compareTo(rate, UFDouble.ZERO_DBL) < 0
        || MathTool.compareTo(rate, SOConstant.ONEHUNDRED) > 0) {
      // fengjb 2012.03.05 UE��ʾ�淶
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0255")/*Ԥ�տ����ֵ��0.0~100.0֮��*/);
    }

    // �����տ�����ͼ�˰�ϼ�,�����տ��޶�
    PreceiveQuotaRule prequotarule = new PreceiveQuotaRule(keyValue);
    prequotarule.calculatePreceiveQuoTa();
  }

}
