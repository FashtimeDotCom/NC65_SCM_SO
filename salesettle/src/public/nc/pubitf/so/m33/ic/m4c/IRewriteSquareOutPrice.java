package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * �����۳��ⵥ�۸��޸ĵ�ʱ��ͬʱ�޸���֮��Ӧ�����۴����㵥�ļ۸�
 * ͬ�����۳��ⵥ�����۳�������㵥
 * @since 6.0
 * @version 2011-9-26 ����07:57:29
 * @author zhangcheng
 */
public interface IRewriteSquareOutPrice {
  
  /**
   * �����۳��ⵥ�۸��޸ĵ�ʱ��ͬʱ�޸���֮��Ӧ�����۴����㵥�ļ۸�
   * @param RewritePara33For4C ������4��ԭ�Ҽ۸�4�����Ҽ۸����ȫ����д 
   * @throws BusinessException
   */
  void rewriteSquareOutPrice(RewritePara33For4C[] paras) throws BusinessException;
  
}
