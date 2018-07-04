package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.rule.FillNmffilePriceRule;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * ���۶����������ۺ�ͬת����ǰ̨���ݴ�����
 * 
 * @since 6.0
 * @version 2011-6-30 ����11:14:02
 * @author fengjb
 */
public class M30RefZ3TransferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {

    // ���������õ�������
    super.doTransferAddLogic(selectedData);
    // ���ڽ��濨Ƭ���ֵ����
    BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.����������
    FillNmffilePriceRule nmffileRule = new FillNmffilePriceRule(keyValue);
    nmffileRule.setNmffilePrice();
    // 2.��ͷ�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }

}
