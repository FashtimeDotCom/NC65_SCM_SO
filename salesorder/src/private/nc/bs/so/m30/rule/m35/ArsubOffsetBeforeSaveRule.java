package nc.bs.so.m30.rule.m35;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * ���۶�������ʱ
 * @scene
 * ��Ʒ�Ҹ����õ���ֱ���ǰ����
 * @param
 * ��
 *
 * @since 6.35
 * @version 2013-11-28 ����03:51:53
 * @author dongli2
 */
public class ArsubOffsetBeforeSaveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO bill : vos) {
      SaleOrderHVO header = bill.getParentVO();
      String arsubtypeid = header.getCarsubtypeid();
      if (null != arsubtypeid) {
        SaleOrderBVO[] bodys = bill.getChildrenVO();
        for (SaleOrderBVO bvo : bodys) {
          this.changeOrderbody(bvo);
        }
      }
    }
  }

  /**
   * ������Ʒ����Ʒ�Ҹ����
   * 
   * @param thissub
   * @param rowindex
   */
  private void changeOrderbody(SaleOrderBVO bvo) {
    // ������Ʒ����Ʒ�Ҹ���־
    bvo.setBlargessflag(UFBoolean.TRUE);
    bvo.setBlrgcashflag(UFBoolean.TRUE);
  }

}
