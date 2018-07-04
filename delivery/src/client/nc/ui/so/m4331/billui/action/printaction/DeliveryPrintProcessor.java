package nc.ui.so.m4331.billui.action.printaction;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.so.m4331.billui.util.DeliveryPrintScale;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * ��ӡǰ���ȴ���
 * 
 * @since 6.0
 * @version 2010-11-4 ����04:13:03
 * @author ף����
 */
public class DeliveryPrintProcessor implements IBeforePrintDataProcess {
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
    if (null == datas || datas.length == 0) {
      return null;
    }
    DeliveryVO[] vos = new DeliveryVO[datas.length];
    int i = 0;
    for (Object obj : datas) {
      vos[i] = (DeliveryVO) obj;
    }
    // ���ȴ���
    DeliveryPrintScale handler =
        new DeliveryPrintScale(this.getModel().getContext().getPk_group(), vos);
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
