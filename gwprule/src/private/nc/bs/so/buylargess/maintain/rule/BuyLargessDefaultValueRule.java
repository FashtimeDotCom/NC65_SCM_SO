package nc.bs.so.buylargess.maintain.rule;

import nc.vo.pubapp.AppContext;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ����Ĭ�ϼ���
 * @scene
 * �������ñ���ǰ Ĭ�ϼ�������
 * @param
 * ��
 * @since 6.0
 * @version 2011-1-25 ����09:48:08
 * @author ף����
 */
public class BuyLargessDefaultValueRule implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] vos) {
    for (BuyLargessVO vo : vos) {
      String pk_group = AppContext.getInstance().getPkGroup();
      vo.getParentVO().setPk_group(pk_group);
      BuyLargessBVO[] bvos = vo.getChildrenVO();
      for (BuyLargessBVO bvo : bvos) {
        bvo.setPk_group(pk_group);
      }
    }
  }
}
