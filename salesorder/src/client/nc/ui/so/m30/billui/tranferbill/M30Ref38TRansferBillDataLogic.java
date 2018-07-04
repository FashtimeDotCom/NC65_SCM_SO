package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

/**
 * ���۶�������Ԥ����ת�������ݴ�����
 * 
 * @since 6.0
 * @version 2011-7-4 ����11:48:31
 * @author fengjb
 */
public class M30Ref38TRansferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {

    // �����������ڽ�����
    super.doTransferAddLogic(selectedData);

    // ���ڽ��濨Ƭ���ֵ����
    SaleOrderBillForm billform = (SaleOrderBillForm) this.getBillForm();
    BillCardPanel cardPanel = billform.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
    int[] rows = bodycouuitl.getMarNotNullRows();
    // 1.V63 ��ӱ����¶������ ���۶���������Ҫ������ͬ
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    AssociateConstractRule ctrule =
        new AssociateConstractRule(cardPanel, m30transvo);
    ctrule.associateCT(rows);
    // 2.����ƥ��
    MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel, m30transvo);
    matchlarrule.matchLargess(rows);

    // 3.��ͷ�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }

}
