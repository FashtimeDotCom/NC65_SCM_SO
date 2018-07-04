package nc.ui.so.m38.billui.model;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ�����򿪽ڵ����
 * 
 * @since 6.0
 * @version 2011-1-5 ����10:10:31
 * @author ô��
 */
public class PreOrderDataProcessor implements IInitDataProcessor {

  /** Ԥ������Ƭ */
  private PreOrderEditor editor;

  /** ����ģ�� */
  private AbstractAppModel model;

  /**
   * ��Ƭ getter
   * 
   * @return ��Ƭ
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  /**
   * ����ģ��
   * 
   * @return ����ģ��
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    if (101 == data.getInitType()) {
      PreOrderVO[] preorders = (PreOrderVO[]) data.getInitData();
      // ���þ���
      PreOrderPrecision precision = new PreOrderPrecision();
      String pk_group = ClientContext.getInstance().getPk_group();
      BillCardPanel cardpanel = this.getEditor().getBillCardPanel();
      precision.setCardPrecision(pk_group, cardpanel);
      // ��������
      this.getModel().initModel(preorders);
      this.getModel().setUiState(UIState.NOT_EDIT);
    }
  }

  /**
   * ��Ƭ
   * 
   * @param editor ��Ƭ
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  /**
   * ����ģ��
   * 
   * @param model ����ģ��
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

}
