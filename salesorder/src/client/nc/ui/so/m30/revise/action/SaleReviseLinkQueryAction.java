package nc.ui.so.m30.revise.action;

import nc.ui.pubapp.uif2app.actions.LinkQueryAction;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * ���۶����޶��������⴦������û�м��޶�������
 * 
 * @since 6.36
 * @version 2015-5-22 ����2:03:10
 * @author ����
 */
public class SaleReviseLinkQueryAction extends LinkQueryAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public IBillInfoFactory<Object> getBillInfoFactory() {
   
      return new SaleOrderReviseBillInfoFactory();
  }

  /**
   * ���쵥�ݺš�����id�������������鹤��
   * 
   * @since 6.36
   * @version 2015-5-22 ����4:27:24
   * @author ����
   */
  private class SaleOrderReviseBillInfoFactory implements
      IBillInfoFactory<Object> {

    private String billCode;

    private String billId;

    private String billType;

    @Override
    public IBillInfo getBillInfo(Object o) {
      SaleOrderHistoryVO revisevo = (SaleOrderHistoryVO) o;
      this.billId = revisevo.getParentVO().getCsaleorderid();
      this.billCode = revisevo.getParentVO().getVbillcode();
      this.billType = SOBillType.Order.getCode();
      return new IBillInfo() {

        @Override
        public String getBillCode() {
          return SaleOrderReviseBillInfoFactory.this.billCode;
        }

        @Override
        public String getBillId() {
          return SaleOrderReviseBillInfoFactory.this.billId;
        }

        @Override
        public String getBillType() {
          return SaleOrderReviseBillInfoFactory.this.billType;
        }
      };
    }
  }
}
