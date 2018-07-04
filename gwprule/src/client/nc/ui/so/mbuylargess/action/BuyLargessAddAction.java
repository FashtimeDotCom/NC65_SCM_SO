package nc.ui.so.mbuylargess.action;

import java.awt.event.ActionEvent;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.so.mbuylargess.view.BuyLargessEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ������������
 * 
 * @since 6.3
 * @version 2013-02-21 14:08:03
 * @author ������
 */
public class BuyLargessAddAction extends AddAction {

  private static final long serialVersionUID = 1L;

  private BuyLargessEditor billForm;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    // ������֯ѡ���������ʱû�з��¼�,���±�ͷ����֯û������,���ﲹ�϶���֯������
    BillCardPanel cardPanel = this.billForm.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(BuyLargessHVO.PK_ORG);
    if (PubAppTool.isNull(pk_org)) {
      pk_org = this.getModel().getContext().getPk_org();
      keyValue.setHeadValue(BuyLargessHVO.PK_ORG, pk_org);
      cardPanel.getBillData().loadEditHeadRelation(BuyLargessHVO.PK_ORG);
    }
  }

  /**
   * 
   * 
   * @param billForm
   */
  public void setBillForm(BuyLargessEditor billForm) {
    this.billForm = billForm;
  }

  /**
   * 
   * 
   * @return billForm
   */
  public BuyLargessEditor getBillForm() {
    return this.billForm;
  }

}
