package nc.pubimpl.so.m4331.so.m30;

import nc.pubimpl.so.m4331.pub.PubDeleteOrCloseFor305XOutClose;
import nc.pubitf.so.m4331.pub.RewritePara4331;
import nc.pubitf.so.m4331.so.m30.IDeleteOrCloseFor30OutClose;
import nc.vo.pub.BusinessException;

/**
 * ���۶������ߵ������������ر�
 * 
 * @author ף����
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class DeleteOrCloseFor30OutCloseImpl implements
    IDeleteOrCloseFor30OutClose {

  @Override
  public void rewrite(RewritePara4331[] paras) throws BusinessException {
    new PubDeleteOrCloseFor305XOutClose().rewrite(paras);
  }
}
