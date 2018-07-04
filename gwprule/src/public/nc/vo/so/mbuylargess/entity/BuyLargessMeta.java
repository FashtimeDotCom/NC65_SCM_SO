package nc.vo.so.mbuylargess.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * ��������Meta��
 */

public class BuyLargessMeta extends AbstractBillMeta {
  /**
   * BuylargessMeta �Ĺ�����
   */
  public BuyLargessMeta() {
    this.init();
  }

  /**
   * ���������������������þۺ�VOMeta���ʼ���� <b>����˵��</b>
   * 
   * @author fengjb
   * @time 2009-6-3 ����01:57:54
   */
  private void init() {
    this.setParent(BuyLargessHVO.class);
    this.addChildren(BuyLargessBVO.class);
  }

}
