package nc.pubitf.so.m33.ia;

import nc.vo.pub.BusinessException;
import nc.vo.so.m33.para.ReWrite4CParaForIA;

/**
 * �ṩ����������д���۳��ⵥ�ɱ����ͳɱ����۵Ľӿ�
 * 
 * @since 6.0
 * @version 2011-6-1 ����01:20:29
 * @author ô��
 */
public interface IRewriteCostPriceSOForIA {
  /**
   * ��д���۳��ⵥ�ɱ�
   * 
   * @param paras
   */
  void set4CCostPrice(ReWrite4CParaForIA[] paras) throws BusinessException;

}
