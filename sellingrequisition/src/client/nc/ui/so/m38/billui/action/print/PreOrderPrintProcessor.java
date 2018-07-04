package nc.ui.so.m38.billui.action.print;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.
              IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * ��ӡʱ�����ӡ����
 * @since 6.0
 * @version 2010-11-4 ����07:40:24
 * @author ף����
 */
public class PreOrderPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ���෽����д
   * 
   */
  @Override
  public Object[] processData(Object[] datas) {
    // ת��ΪԤ����
    //PreOrderVO[] vos = (PreOrderVO[]) datas;
	PreOrderVO[] vos = new PreOrderVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (PreOrderVO) datas[i];
    }
    // ���ȴ���
    SoVoPrecionScale handler =
        new SoVoPrecionScale(this.getModel().getContext().getPk_group(), vos);
    handler.setScale();
    return vos;

  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

}
