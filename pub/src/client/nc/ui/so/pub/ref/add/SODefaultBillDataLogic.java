package nc.ui.so.pub.ref.add;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.editable.SOCardEditableSetter;

/**
 * ���۹�������ת����Ĭ�ϴ���
 * 
 * @since 6.0
 * @version 2011-10-19 ����03:54:22
 * @author ô��
 */
public class SODefaultBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {
    super.doTransferAddLogic(selectedData);

    // ת������ƽ��潻�����ͱ༭��
    new SOCardEditableSetter().setHeadEditForRef(this.getBillForm()
        .getBillCardPanel());
  }

}
