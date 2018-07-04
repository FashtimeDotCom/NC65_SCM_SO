package nc.pubimpl.so.m30.so.balance.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

/**
 * 
 * @description
 * �տ������д���۶���ʵ���տ���
 * @scene
 * ����Ƿ���Զ����տ�
 * @param
 * ��
 *
 * @since 6.0
 * @version 2011-6-7 ����10:25:07
 * @author ��־ΰ
 */
public class CheckGatheringRule implements IRule<SaleOrderHVO> {

  @Override
  public void process(SaleOrderHVO[] heads) {
    /*�տ��޶����Ԥ�ա��򹴡�ʱ��������ͷ�����տ��޶�=ʵ��Ԥ�տ���ʱ�����ٴ��տ�
            �տ��޶����Ԥ�ա����򹴡�ʱ��������ͷ�ļ�˰�ϼ� = ʵ���տ���ʱ�����ٴ��տ�*/
    GatherbillUtil.getInstance().checkCanGathering(heads);
  }
}
