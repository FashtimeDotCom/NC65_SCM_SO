package nc.pubitf.so.m30.so.withdraw;

import nc.vo.pub.BusinessException;

/**
 * �����˻�������д���۶�������ӿڡ�
 * 
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public interface IRewriteSaleOrderByWithdraw {

  void rewrite30NumForWithdraw(Rewrite30Para[] paras) throws BusinessException;

}
