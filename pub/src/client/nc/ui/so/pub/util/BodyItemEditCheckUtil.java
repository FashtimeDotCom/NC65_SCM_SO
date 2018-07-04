package nc.ui.so.pub.util;

import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���۶����жϱ���༭�ԵĹ�����
 * @author zhangby5
 *
 */
public class BodyItemEditCheckUtil {
  
  /**
   * �ж����۶�����Ƭ����۸��ܷ�༭
   * @param billform 
   * @param selectRow ��ǰ��
   * @return
   */
  public static boolean checkPriceEditable(SaleOrderBillForm billform, int selectRow){
    IKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(billform.getBillCardPanel(),
            m30transvo);
    // 2.ѯ�۲���,�ж��Ƿ�ѯ��
    Integer askrule = config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return true;
    }
    // 3.��edit=�۸�ʱ���ж��Ƿ�ɱ༭
    Boolean bmodifyaskedqt = config.isModifyAskSucess();
    Boolean bmodifyunaskedqt = config.isModifyAskFail();

    // 4.ȡ����ǰ��˰���۵�ֵ���ж��Ƿ�ѯ�۳ɹ�
    // --��ǰ��Ϊ�գ�Ӧ����ѯ��ʧ��
    if (MathTool.isZero(keyValue.getBodyUFDoubleValue(selectRow, SaleOrderBVO.NQTORIGTAXNETPRC))) {
      // ѯ��ʧ���Ƿ���޸�
      if (bmodifyunaskedqt) {
        return true;
      }
    }else {
      // ѯ�۳ɹ��Ƿ�ɸ�
      if (bmodifyaskedqt || bmodifyunaskedqt) {
        return true;
      }
    }
    return false;
    
  }

}
