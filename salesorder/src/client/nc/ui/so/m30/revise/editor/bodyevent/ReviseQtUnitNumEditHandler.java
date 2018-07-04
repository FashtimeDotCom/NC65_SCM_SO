package nc.ui.so.m30.revise.editor.bodyevent;

import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.m30.revise.rule.CheckNumValidityRule;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.rule.PieceCalRule;
import nc.vo.so.m30.rule.WeightVolumeCalRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���۶����޶����������༭�¼�
 * 
 * @since 6.0
 * @version 2011-8-4 ����07:20:53
 * @author ��־ΰ
 */
public class ReviseQtUnitNumEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    UFDouble old_qtunitnum = (UFDouble) e.getOldValue();
    UFDouble old_num = keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
    // 1.��������
    SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
    calcultor.calculate(row, SaleOrderBVO.NQTUNITNUM);

    // 2. ���༭���������Ƿ�Ϸ�
    CheckNumValidityRule rule = new CheckNumValidityRule();
    try {
      rule.check(cardPanel, keyValue, row);
    }
    catch (Exception ex) {
      // -- ���Ϸ���ָ�ԭ����
      keyValue.setBodyValue(row, SaleOrderBVO.NQTUNITNUM, old_qtunitnum);
      calcultor.calculate(row, SaleOrderBVO.NQTUNITNUM);
      ExceptionUtils.wrappException(ex);
    }
    // ������δ�仯
    UFDouble new_num = keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
    if (MathTool.equals(old_num, new_num)) {
      return;
    }
    // 2.ѯ��
    M30TranTypeVO m30transvo =
        M30TranTypeUtil.getInstance().queryTranTypeVO(
            keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID));
    UFBoolean reviseprice = m30transvo.getBreviseprice();
    if (null != reviseprice && reviseprice.booleanValue()) {
      SaleOrderFindPriceConfig config =
          new SaleOrderFindPriceConfig(cardPanel, m30transvo);
      FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
      int[] rows = new int[] {
        row
      };
      findPrice.findPriceAfterEdit(rows, SaleOrderBVO.NQTUNITNUM);
    }
    // 3.���㵥λ�������������
    WeightVolumeCalRule calrule = new WeightVolumeCalRule(keyValue);
    calrule.calculateWeightVolume(row);

    PieceCalRule piececalrule = new PieceCalRule(keyValue);
    piececalrule.calcPiece(row);
    // 4.�޶���ƥ����������
    // MatchBindLargessRule matchrule = new MatchBindLargessRule(cardPanel);
    // matchrule.matchBindLargess(rows);
    // 5.��ͷ�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }
}
