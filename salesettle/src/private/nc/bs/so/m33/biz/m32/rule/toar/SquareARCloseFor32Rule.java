package nc.bs.so.m33.biz.m32.rule.toar;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * ���۷�ƱӦ�ս���رմ������
 * �˹���һ�����ڽ��������ã��������λ�д���Ѿ���ɣ�
 * @scene
 * ���۷�ƱӦ�ս���رպ�
 * @param
 * ��
 * @since 6.0
 * @version 2011-7-23 ����01:11:53
 * @author zhangcheng
 */
public class SquareARCloseFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    // Դͷ���۶�������id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CFIRSTBID,
            String.class);
    BalEndTrigger trigger = BalEndTrigger.VOICE_INCOME;
    BalEndPara para = new BalEndPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
