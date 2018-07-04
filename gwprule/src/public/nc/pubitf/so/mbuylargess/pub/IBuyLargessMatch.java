package nc.pubitf.so.mbuylargess.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;

/**
 * ���������ṩ�Ĺ���ƥ��ӿ�
 * 
 * @since 6.1
 * @version 2012-10-30 17:05:27
 * @author ��ӱ�
 */
public interface IBuyLargessMatch {

  /**
   * ƥ����������չʾ��ͼ
   * 
   * @param matchparas
   * @return ƥ�䵽����������չʾ��ͼ
   * @throws BusinessException
   */
  BuyLargessMatchResult[] matchBuyLargessView(IBuyLargessMatchPara[] matchparas)
      throws BusinessException;

  /**
   * ƥ���������ý��
   * 
   * @param matchparas
   * @return ƥ�䵽���������ý��
   * @throws BusinessException
   */
  BuyLargessMatchResult[] matchBuyLargessResult(
      IBuyLargessMatchPara[] matchparas) throws BusinessException;

}
