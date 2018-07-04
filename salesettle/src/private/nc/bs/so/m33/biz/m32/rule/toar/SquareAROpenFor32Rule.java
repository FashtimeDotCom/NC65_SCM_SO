package nc.bs.so.m33.biz.m32.rule.toar;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * ȡ����ȷ��Ӧ�պ󶩵�����򿪵���
 * @scene
 * ���۷�Ʊȡ����ȷ��Ӧ�պ�
 * @param
 * ��
 */
public class SquareAROpenFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    // Դͷ���۶�������id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CFIRSTBID,
            String.class);
    BalOpenTrigger trigger = BalOpenTrigger.VOICE_NOINCOME;
    BalOpenPara para = new BalOpenPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalOpen(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
