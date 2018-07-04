package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.rule.sobalance.arengross.RewriteD2WhenDeleteRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * �տ������ϵɾ������for���۶����������Զ�ɾ��
 * 
 * @since 6.0
 * @version 2011-8-19 ����03:23:12
 * @author ��־ΰ
 */
public class DeleteBPForSoAuto extends AbstractDeleteBP {

  @Override
  protected void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
    //
  }

  @Override
  protected void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;
    rule = new RewriteD2WhenDeleteRule();
    processer.addAfterRule(rule);
  }
}
