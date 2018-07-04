package nc.ui.so.pub.actions;

import nc.ui.pubapp.uif2app.actions.FileDocManageAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;

/**
 * ���۹�����������Ӧ��
 * 
 * @since 6.0
 * @version 2011-2-24 ����02:35:02
 * @author ô��
 */
public class SOManageDocumentAction extends FileDocManageAction {

  /** VersionUID */
  private static final long serialVersionUID = 5140670340573968728L;

  /**
   * ���캯��
   */
  public SOManageDocumentAction() {
    super();
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel somodel = (BillManageModel) super.getModel();

    return somodel.getSelectedData() != null
        && somodel.getSelectedOperaDatas() != null
        && somodel.getSelectedOperaDatas().length == 1;
  }

}
