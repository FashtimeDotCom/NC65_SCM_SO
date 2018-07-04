/**
 * $�ļ�˵��$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-7 ����03:13:57
 */
package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

/**
 * ���۶����������۱��۵�ת�������ݴ�����
 * 
 * @since 6.0
 * @version 2011-7-4 ����03:06:04
 * @author fengjb
 */
public class M30Ref4310TransferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {

    // �����������ڽ�����
    super.doTransferAddLogic(selectedData);

    // ���ڽ��濨Ƭ���ֵ����
    BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 2.��ͷ�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }
}
