package nc.ui.so.m30.sobalance.action;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.so.m30.sobalance.view.SobalancePrecision;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * ��ӡʱ�����ӡ����
 * 
 * @since 6.0
 * @version 2010-11-4 ����07:40:24
 * @author ף����
 */
public class SobalancePrintProcessor implements IBeforePrintDataProcess {

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

    // ת��ΪSoBalanceVO
    SoBalanceVO[] vos = new SoBalanceVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (SoBalanceVO) datas[i];
    }
    // ���ȴ���
    SobalancePrecision handler =SobalancePrecision.getInstance();
    BillScaleProcessor scale =new BillVOScaleProcessor(this.getModel().getContext().getPk_group(), vos);
        //new SobalancePrecision(this.getModel().getContext().getPk_group(), vos);
    handler.setBillPrecision(scale);
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
