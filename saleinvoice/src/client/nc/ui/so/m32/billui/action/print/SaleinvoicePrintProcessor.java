package nc.ui.so.m32.billui.action.print;

import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.price.adjustprice.entity.AdjustPriceHVO;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * ��ӡʱ�����ӡ����
 * 
 * @since 6.0
 * @version 2010-11-4 ����07:40:24
 * @author ף����
 */
public class SaleinvoicePrintProcessor
    implements
    nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess {

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
    // ת��Ϊ���۷�Ʊvo
    SaleInvoiceVO[] vos = new SaleInvoiceVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (SaleInvoiceVO) datas[i];
    }

    // Ȩ��У��
    PowerCheckUtils.checkHasPermission(vos, SOBillType.Invoice.getCode(),
        PowerActionEnum.PRINT.getActioncode(), AdjustPriceHVO.VBILLCODE);

    // vos = (SaleInvoiceVO[]) datas;
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
