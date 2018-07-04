package nc.itf.so.m30trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * �����Լ��Ľ����ѯ�༭
 * 
 * @since 6.0
 * @version 2012-3-15 ����03:44:18
 * @author ô��
 */
public interface IM30TranTypeSelfService {

  /**
   * ���ݽ�������IDs��ѯ��������VOs
   * 
   * @param pk_group ����
   * @param pk_billtypecodes ��������[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ���ݽ�������ID��ѯ��������VO
   * 
   * @param ctrantypeid
   * @return
   * @throws BusinessException
   */
  M30TranTypeVO queryTranTypeVO(String ctrantypeid) throws BusinessException;

}
