package nc.vo.so.m32.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��ͼVO
 * </ul>
 * 
 * @version ���汾��6.0
 * @author fengjb
 * @time 2010-1-19 ����09:06:47
 */
public class SaleInvoiceViewVO extends AbstractDataView {
  /**
   * 
   */
  private static final long serialVersionUID = 2117249433614968884L;

  public SaleInvoiceVO changeToBill() {
    SaleInvoiceVO bill = new SaleInvoiceVO();
    bill.setParent(this.getHead());
    SaleInvoiceBVO[] items = new SaleInvoiceBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  public SaleInvoiceHVO getHead() {
    return (SaleInvoiceHVO) this.getVO(SaleInvoiceHVO.class);
  }

  public SaleInvoiceBVO getItem() {
    return (SaleInvoiceBVO) this.getVO(SaleInvoiceBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewmeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            SaleInvoiceViewMeta.class);
    return viewmeta;
  }

  public void setHead(SaleInvoiceHVO head) {
    this.setVO(head);
  }

  public void setItem(SaleInvoiceBVO item) {
    this.setVO(item);
  }
}
