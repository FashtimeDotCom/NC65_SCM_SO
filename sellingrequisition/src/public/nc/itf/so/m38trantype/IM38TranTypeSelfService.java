package nc.itf.so.m38trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

/**
 * �����Լ��Ľ����ѯ�༭
 * 
 * @since 6.0
 * @version 2012-3-15 ����03:44:18
 * @author ô��
 */
public interface IM38TranTypeSelfService {

  /**
   * ���ݽ�������IDs��ѯ��������VOs
   * 
   * @param pk_group ����
   * @param pk_billtypecodes ��������[]
   * @return M38TranTypeVO[]
   */
  M38TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ���ݽ�������ID��ѯ��������VO
   * 
   * @param ctrantypeid
   * @return
   * @throws BusinessException
   */
  M38TranTypeVO queryTranTypeVO(String ctrantypeid) throws BusinessException;

}
