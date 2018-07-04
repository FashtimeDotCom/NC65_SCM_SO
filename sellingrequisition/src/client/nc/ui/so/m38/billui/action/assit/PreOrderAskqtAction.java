package nc.ui.so.m38.billui.action.assit;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.scmpub.res.SCMActionCode;

public class PreOrderAskqtAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1494194173207238731L;

  private PreOrderEditor editor;

  // private CardKeyValue keyValue;

  private AbstractAppModel model;

  public PreOrderAskqtAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ASKPRICE);
  }

  @Override
  public void doAction(ActionEvent e) {
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    int[] rows = cardPanel.getBillTable().getSelectedRows();
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.forceFindPrice(rows);
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    if (rows.length > 1) {
      return true;
    }
    if (this.editor.getBillCardPanel().getBillTable().getSelectedRow() == -1) {
      return false;
    }
    // ͨ���Ƿ����ѯ�������ư�ťռʱȥ������Ϊ�������ǽ���������չ��Ϊ��
    // if (!this.isFindPrice()) {
    // return false;
    // }

    return true;
  }

  /**
   * tool -- �Ƿ���Ҫѯ�ۣ����ݽ�������"ѯ�۹���"
   */
  // private boolean isFindPrice() {
  // this.keyValue = new CardKeyValue(this.editor.getBillCardPanel());
  // M38TranTypeTool m38TranTypeTool = new M38TranTypeTool(this.keyValue);
  // return m38TranTypeTool.isFindPrice();
  // }

}
