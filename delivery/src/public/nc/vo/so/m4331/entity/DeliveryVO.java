package nc.vo.so.m4331.entity;

import nc.vo.annotation.AggVoInfo;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@AggVoInfo(parentVO = "nc.vo.so.m4331.entity.DeliveryHVO")
public class DeliveryVO extends AbstractBill {

  private static final long serialVersionUID = 266232438347908890L;

  /**
   * ����ӱ��VO����
   */
  @Override
  public DeliveryBVO[] getChildrenVO() {
    return (DeliveryBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.delivery");
    return billMeta;
  }

  /**
   * ��������VO
   */
  @Override
  public DeliveryHVO getParentVO() {
    return (DeliveryHVO) super.getParent();
  }
}
