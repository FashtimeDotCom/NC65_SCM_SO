package nc.ui.so.pub.calculator.data;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class SOCardPanelDataSet extends BillCardPanelDataSet {

  public SOCardPanelDataSet(
      BillCardPanel cardPanel, int row, IRelationForItems item) {
    super(cardPanel, row, item);
  }

  /**
   * ���Ŵӱ�ͷ��ȡ
   */
  /*
  public String getPk_group() {
   String value = null;
   BillItem groupitem = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.PK_GROUP);
   if (null != groupitem) {
       Object objorgcurency = groupitem.getValueObject();
       value = ValueUtils.getInstance().getString(objorgcurency);
   }
   return value;
  }
  */

  /**
   * �۱����ʴӱ�ͷ��ȡ
   */
  /*
  public UFDouble getNexchangerate() {
   Object obj = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.NEXCHANGERATE).getValueObject();
   UFDouble value = ValueUtils.getInstance().getUFDouble(obj);
   return value;
  }
  */

  /**
   * ȫ���۱����ʴӱ�ͷ��ȡ
   */
  /*
  public UFDouble getNglobalexchgrate() {
   Object obj = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.NGLOBALEXCHGRATE).getValueObject();
   UFDouble value = ValueUtils.getInstance().getUFDouble(obj);
   return value;
  }
  */

  /**
   * ȫ���۱����ʴӱ�ͷ��ȡ
   */
  /*
  public UFDouble getNgroupexchgrate() {
    Object obj = getBillCardPanel().getHeadItem(
             SaleInvoiceHVO.NGROUPEXCHGRATE).getValueObject();
    UFDouble value = ValueUtils.getInstance().getUFDouble(obj);
    return value;
  }
  */

  /**
   * ��Ʊ�����ۿ� = ��Ʊ�����ۿ�*��Ʒ�ۿ�/100
   */
  /*
  public UFDouble getNdiscountrate() {
   UFDouble disrate = super.getNdiscountrate();

   UFDouble itemdisrate = ValueUtils.getInstance().getUFDouble(
           getAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE));
   itemdisrate = itemdisrate == null ? UFDouble.ZERO_DBL : itemdisrate;
   disrate = disrate.multiply(itemdisrate).div(new UFDouble(100));
   return disrate;
  }

  */

  /**
   * ���ڷ�Ʊ�����������ۿۡ���Ʒ�ۿ�,�ʲ������������ۿ�ֵ
   */
  /*
  public void setNdiscountrate(UFDouble value) {
  }

  */

  @Override
  public String getCorigcurrencyid() {
    String value = null;
    BillItem orgcurencyitem =
        this.getBillCardPanel().getHeadItem(SaleOrderHVO.CORIGCURRENCYID);
    if (null != orgcurencyitem) {
      value = (String) orgcurencyitem.getValueObject();
    }
    return value;
  }

  /**
   * ��Ʊ��λ�Ҵ洢�ڱ�ͷ���ӱ�ͷ���
   */
  /*
  public String getCcurrencyid() {

   String value = null;
   BillItem orgcurencyitem = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.CCURRENCYID);
   if (null != orgcurencyitem) {
       Object objorgcurency = orgcurencyitem.getValueObject();
       value = ValueUtils.getInstance().getString(objorgcurency);
   }
   return value;
  }

  */

  /**
   * ��Ʊ���۱��ֵ���ԭ�ұ���
   */
  /*
  public String getCqtcurrencyid() {
   return this.getCorigcurrencyid();
  }
  
  */

  /**
   * ����Ҫ�ж��Ƿ��ڱ�ͷ��Ҫ�ж��Ƿ��ڱ���
   */
  /*
  public boolean hasItemKey(String key) {
   if(getAtHeadKey().contains(key)){
       BillItem headItem = getBillCardPanel().getHeadItem(key);
       return headItem != null;
   }
   return super.hasItemKey(key);
  }
  
  */

  /**
   * 
   * ����������������ʼ���ڱ�ͷ���ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @author ��ӱ�
   * @time 2010-5-5 ����02:17:37
   */
  /*
  public HashSet<String> getAtHeadKey(){
   if(null == hsAtHeadKey){
       hsAtHeadKey = new HashSet<String>();
       for(String key:strAtHeadKey){
           hsAtHeadKey.add(key);
       }
   }
   return hsAtHeadKey;
  }
  
  */

  /**
   * ��ģ���ͷ���ֶ�
   */
  /*
  private static final String[] strAtHeadKey = new String[]{
  SaleInvoiceHVO.NEXCHANGERATE,SaleInvoiceHVO.NGROUPEXCHGRATE,
  SaleInvoiceHVO.NGLOBALEXCHGRATE
  };

  private HashSet<String> hsAtHeadKey = null;*/

}
