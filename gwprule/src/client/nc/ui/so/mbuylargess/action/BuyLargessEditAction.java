package nc.ui.so.mbuylargess.action;

import java.awt.event.ActionEvent;

import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.mbuylargess.view.BuyLargessEditor;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������޸�ʱ���ñ༭��
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾�� V60
 * @since ��һ�汾��
 * @author fengjb
 * @time 2009-6-17 ����06:08:18
 */
public class BuyLargessEditAction extends EditAction {

  // �������ͣ�������
  private static final int NOTOPLIMIT = 2;

  private static final long serialVersionUID = 1L;

  private BuyLargessEditor view;

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.EditAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // �����ֶα༭��
    super.doAction(e);
    this.setEditenable();
  }

  /**
   * 
   * 
   * @return view
   */
  public BuyLargessEditor getView() {
    return this.view;
  }

  /**
   * 
   * 
   * @param view
   */
  public void setView(BuyLargessEditor view) {
    this.view = view;
  }

  private void setCust(BillCardPanel billCardPanel) {
    // �ͻ�
    Object pk_customer =
        billCardPanel.getHeadItem("pk_customer").getValueObject();

    if (!VOChecker.isEmpty(pk_customer)) {

      billCardPanel.getHeadItem("pk_custclass").setEnabled(false);
      billCardPanel.getHeadItem("pk_custsaleclass").setEnabled(false);
    }
    // �ͻ�����
    Object pk_custclass =
        billCardPanel.getHeadItem("pk_custclass").getValueObject();

    Object pk_custsaleclass =
        billCardPanel.getHeadItem("pk_custsaleclass").getValueObject();
    if (!VOChecker.isEmpty(pk_custclass)
        || !VOChecker.isEmpty(pk_custsaleclass)) {
      billCardPanel.getHeadItem("pk_customer").setEnabled(false);
    }
  }

  /**
   * ������������������������������ֶα༭�ԡ� <b>����˵��</b>
   * 
   * @author fengjb
   * @time 2009-6-17 ����05:02:49
   */
  private void setEditenable() {
    BillCardPanel billCardPanel = this.getView().getBillCardPanel();
    this.setMaterial(billCardPanel);
    this.setCust(billCardPanel);
    // �����ֶ�
    // ��������
    int col = billCardPanel.getBillModel().getBodyColByKey("ftoplimittype");
    int length = billCardPanel.getRowCount();
    for (int i = 0; i < length; i++) {
      Object objtoplimit = billCardPanel.getBillModel().getValueAt(i, col);
      Object convervalue =
          billCardPanel.getBodyItem("ftoplimittype").converType(objtoplimit);
      int ftoplimittype = Integer.parseInt(convervalue.toString());

      if (BuyLargessEditAction.NOTOPLIMIT == ftoplimittype) {
        billCardPanel.setCellEditable(i, "ntoplimitvalue", false);
      }
      else {
        billCardPanel.setCellEditable(i, "ntoplimitvalue", true);
      }
    }
  }

  private void setMaterial(BillCardPanel billCardPanel) {
    // ��ͷ�ֶ�
    // ����
    Object pk_material =
        billCardPanel.getHeadItem("cbuymarid").getValueObject();
    if (VOChecker.isEmpty(pk_material)) {
      billCardPanel.getHeadItem("cbuymarid").setEnabled(false);
      billCardPanel.getHeadItem("pk_marbasclass").setEnabled(true);
      billCardPanel.getHeadItem("pk_marsaleclass").setEnabled(true);

    }
    else {
      billCardPanel.getHeadItem("cbuymarid").setEnabled(true);
      billCardPanel.getHeadItem("pk_marbasclass").setEnabled(false);
      billCardPanel.getHeadItem("pk_marsaleclass").setEnabled(false);
    }
  }
}
