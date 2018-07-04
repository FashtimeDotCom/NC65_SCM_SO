package nc.bs.so.m33.biz.m4c.rule.toia;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * ���۳��ⵥȡ���ɱ����㴦�����
 * @scene
 * ���۳��ⵥȡ���ɱ�����򿪺�
 * @param
 * ��
 */
public class SquareIAOpenFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {
    // Դͷ���۶�������id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareOutBVO.CFIRSTBID,
            String.class);
    BalOpenTrigger trigger = BalOpenTrigger.OUT_NOCOST;
    BalOpenPara para = new BalOpenPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalOpen(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
