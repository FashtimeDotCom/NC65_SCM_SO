package nc.pubitf.so.m30.it.m5801;

import nc.vo.pub.BusinessException;

/**
 * ���ں�ͬ�����۶�����д�ӿ�
 * 
 * @since JCK 6.31
 * @version 2014-03-18 10:18:39
 * @author zhangyfr
 */
public interface IRewrite30For5801 {

  /**
   * ���ں�ͬ�������޸ģ�ɾ�����޶���д���۶����ۼư��Ž��ں�ͬ����
   * 
   * @param paras
   * @throws BusinessException
   */
  void rewriteNarrangeItcNumFor5801(Rewrite5801Para[] paras)
      throws BusinessException;
}
