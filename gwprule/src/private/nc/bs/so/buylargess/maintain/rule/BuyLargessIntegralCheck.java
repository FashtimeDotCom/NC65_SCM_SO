package nc.bs.so.buylargess.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description 
 * ��������������У��
 * @scene 
 * �������� ����ǰ����У��
 * @param
 * ��
 * @since 6.3
 * @author ף����[ף����@ufida.com.cn]
 * @version 1.0
 */
public class BuyLargessIntegralCheck implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] bills) {
    for (BuyLargessVO bill : bills) {
      // ��������ʱ�������ݲ���Ϊ��
      if (VOChecker.isEmpty(bill.getChildrenVO())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0","04006003-0003")/*@res "�����岻��Ϊ�ա�"*/);
      }
    }

  }
}