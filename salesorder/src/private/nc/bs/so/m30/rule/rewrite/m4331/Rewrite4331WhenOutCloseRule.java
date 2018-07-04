package nc.bs.so.m30.rule.rewrite.m4331;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m4331.pub.RewritePara4331;
import nc.pubitf.so.m4331.so.m30.IDeleteOrCloseFor30OutClose;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * ���۶����ֹ�����رջ�д����������
 * <p>
 * <b>�������������������</b>
 * <ul>
 * <li>����ر�ʱ������״̬�ķ�������Ҫɾ��
 * <li>����ر�ʱ������״̬�ķ�������Ҫ�����ر�
 * <li>...
 * </ul>
 * @scene
 * ���۶����ֹ�����رն�����
 * @param 
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-8-17 ����03:49:11
 */
public class Rewrite4331WhenOutCloseRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] views) {
    int length = views.length;
    RewritePara4331[] paras = new RewritePara4331[length];
    for (int i = 0; i < length; i++) {
      paras[i] = new RewritePara4331(views[i].getBody().getCsaleorderbid());
    }

    IDeleteOrCloseFor30OutClose api =
        NCLocator.getInstance().lookup(IDeleteOrCloseFor30OutClose.class);
    try {
      api.rewrite(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
