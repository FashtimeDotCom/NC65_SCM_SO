package nc.impl.so.m4331.pf;

import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.impl.pubapp.bill.rewrite.BillActionClassRunListener;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOConstant;

/**
 * 
 * @description
 *              ����������ƽ̨��д��չ��
 * @scene
 *        �����������ű�����
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-11-5 ����8:57:56
 * @author zhangby5
 */
public class DeliveryActionClassRunListener extends BillActionClassRunListener {

  @Override
  public void beforeActionExecute(PfParameterVO pfVo) throws BusinessException {
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable && SOConstant.DELETE.equalsIgnoreCase(pfVo.m_actionName)) {
      // ɾ������ʱ����������ǰ��Ҫ�ڶ���ǰ����
      DeliveryVOATPBeforeRule beforerule = new DeliveryVOATPBeforeRule();
      beforerule.process((DeliveryVO[]) pfVo.m_preValueVos);
    }
    super.beforeActionExecute(pfVo);
  }

  @Override
  public void afterActionExecute(PfParameterVO pfVo) throws BusinessException {
    super.afterActionExecute(pfVo);
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (!icEnable) {
      return;
    }
    if (SOConstant.WRITE.equalsIgnoreCase(pfVo.m_actionName)
        || SOConstant.DELETE.equalsIgnoreCase(pfVo.m_actionName)) {
      // �޸ı���ɾ������ʱ���������º���Ҫ�ڶ�������
      DeliveryVOATPAfterRule rule = new DeliveryVOATPAfterRule();
      rule.process((DeliveryVO[]) pfVo.m_preValueVos);
    }
  }

}
