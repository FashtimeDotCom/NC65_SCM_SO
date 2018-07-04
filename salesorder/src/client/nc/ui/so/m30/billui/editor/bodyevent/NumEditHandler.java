package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.rule.MatchBindLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.rule.PieceCalRule;
import nc.vo.so.m30.rule.WeightVolumeCalRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class NumEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);

    // ��������Ϊ����Ʒ�Ҹ���ʱ����������ֶ�����
    boolean blrgcashflag = m30transvo.getBlrgcashflag().booleanValue();

    if (blrgcashflag) {
      String editKey = SaleOrderBVO.NNUM;
      for (int row : rows) {
        UFDouble nnum = keyValue.getBodyUFDoubleValue(row, editKey);
        if (nnum.compareTo(UFDouble.ZERO_DBL) < 0) {
          ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0507")/*��Ʒ�Ҹ��Ķ�������������Ϊ������*/);
        }
      }
    }

    // 1.��������
    SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
    calcultor.setTranTypeVO(m30transvo);
    calcultor.calculate(rows, SaleOrderBVO.NNUM);

    // 2.ѯ��
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, SaleOrderBVO.NQTUNITNUM);

    // 3.���㵥λ�������������
    WeightVolumeCalRule calrule = new WeightVolumeCalRule(keyValue);
    calrule.calculateWeightVolume(rows);

    PieceCalRule piececalrule = new PieceCalRule(keyValue);
    piececalrule.calcPiece(rows);

    // 4.ƥ����������
    MatchBindLargessRule matchrule = new MatchBindLargessRule(cardPanel, m30transvo);
    matchrule.matchBindLargess(rows);

    // 5.��ͷ�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }

}
