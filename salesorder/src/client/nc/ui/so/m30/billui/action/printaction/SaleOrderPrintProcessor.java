package nc.ui.so.m30.billui.action.printaction;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * ��ӡʱ�����ӡ����
 * 
 * @since 6.0
 * @version 2010-11-4 ����07:40:24
 * @author ף����
 */
public class SaleOrderPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model;
  private BatchBillTableModel batchmodel;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }
  
  public BatchBillTableModel getBatchmodel() {
    return this.batchmodel;
  }
  /**
   * ���෽����д
   * 
   */
  @Override
  public Object[] processData(Object[] datas) {

    // ת��Ϊ���۶���
    SaleOrderVO[] vos = new SaleOrderVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (SaleOrderVO) datas[i];
    }
    String group;
    if(this.getModel()!=null){
      group=this.getModel().getContext().getPk_group();
    }
    else{
      group=this.getBatchmodel().getContext().getPk_group();
    }
    // ���ȴ���
    SoVoPrecionScale handler =
        new SoVoPrecionScale(group, vos);
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
  public void setBatchmodel(BatchBillTableModel model) {
    this.batchmodel = model;
  }

}
