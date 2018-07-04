package nc.vo.so.m30.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * SaleOrderExternalVO<br>
 * ���ӱ�ۺ�VO<br>
 * <b>�����۶���VO�ṹһ��</b>
 * 
 * 
 * @since:2013-05-25 08:39:32
 * 
 * @author dongli2
 * @version 6.3
 */

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m30.entity.SaleOrderExternalHVO")
public class SaleOrderExternalVO extends AbstractBill {

  /**
   * ���л�
   */
  private static final long serialVersionUID = 5427476164524405831L;

  /* SaleOrder�������� */
  public static final String SaleOrderTable = "so_saleorder";

  /* SaleOrder�ӱ����� */
  public static final String SaleOrderTable_B = "so_saleorder_b";

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getBillMeta()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.so_saleorder");
    return billMeta;
  }

  /***
   * ��ȡ��ͷVO
   * 
   * @return��ͷVO
   */
  public SaleOrderExternalHVO getParentVO() {
    return (SaleOrderExternalHVO) super.getParentVO();
  }

  /**
   * ���ñ�ͷVO
   * 
   * @param headVO
   */
  public void setParentVO(SaleOrderExternalHVO headVO) {
    super.setParentVO(headVO);
  }

  /**
   * ��ȡ����VO
   * 
   * @return����VO
   */
  public SaleOrderExternalBVO[] getChildrenVO() {
    return (SaleOrderExternalBVO[]) super.getChildrenVO();
  }

  /**
   * ���ñ���VO
   * 
   * @param bodyVO
   */
  public void setChildrenVO(SaleOrderExternalBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }

}
