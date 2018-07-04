package nc.vo.so.m38.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 
 * Ԥ�����ۺ�VO
 *
 * ��������:2010-03-31 13:38:44
 * @author ��־ΰ
 * @version NCPrj 6.0
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m38.entity.PreOrderHVO")
public class PreOrderVO extends AbstractBill {

  private static final long serialVersionUID = 6739698624966642225L;

  /**
   * ���Ԥ�����ӱ��VO����
   */
  @Override
  public PreOrderBVO[] getChildrenVO() {
    return (PreOrderBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta meta = BillMetaFactory.getInstance().getBillMeta("so.preorder");
    return meta;
  }

  /**
   * ���Ԥ���������VO
   */
  @Override
  public PreOrderHVO getParentVO() {
    return (PreOrderHVO) super.getParentVO();
  }

}
