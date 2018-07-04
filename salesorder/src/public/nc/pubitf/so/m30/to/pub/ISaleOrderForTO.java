package nc.pubitf.so.m30.to.pub;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶����ṩ���ڲ����׹����ӿڷ���
 * 
 * @since 6.0
 * @version 2011-5-26 ����09:54:27
 * @author ��־ΰ
 */
public interface ISaleOrderForTO {

  /**
   * ��ѯ���۶���ԭ�ҵ���
   * 
   * @param bids ���۶�������ids
   * @return SaleOrderVO{�ӱ�������ԭ����˰���ۡ�ԭ�Һ�˰���ۡ�ԭ�ұ���}
   * @throws BusinessException
   */
  SaleOrderVO[] queryOrigPrice(String[] bids) throws BusinessException;

  /**
   * ���������Ƿ�ֱ�˵���
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ�˵���>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectTO(String[] ctrantypeids)
      throws BusinessException;
}
