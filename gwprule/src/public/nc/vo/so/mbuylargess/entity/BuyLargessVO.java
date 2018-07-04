package nc.vo.so.mbuylargess.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * ���ӱ�/����ͷ/������ۺ�VO ��������:2009-05-12 14:46:40
 * 
 * @author
 * @version NCPrj ??
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.mbuylargess.entity.BuyLargessHVO")

public class BuyLargessVO extends AbstractBill {

  private static final long serialVersionUID = 988247610325960075L;

  @Override
  public BuyLargessBVO[] getChildrenVO() {
    return (BuyLargessBVO[]) super.getChildrenVO();
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getBillMeta()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(BuyLargessMeta.class);
    return billMeta;
  }

  @Override
  public BuyLargessHVO getParentVO() {
    return (BuyLargessHVO) super.getParent();
  }

  public void setParentVO(BuyLargessHVO headVO) {
    this.setParent(headVO);
  }

}
