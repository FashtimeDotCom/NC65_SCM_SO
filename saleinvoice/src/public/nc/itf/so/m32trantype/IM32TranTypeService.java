package nc.itf.so.m32trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32trantype.entity.M32TranTypeVO;

/**
 * ���۷�Ʊ�������Ͷ����ṩ����ӿ�
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:10:50
 * @author yaogj
 */
public interface IM32TranTypeService {

  /**
   * ��ѯ���۷�Ʊ��������vo����
   * 
   * @param pk_group ����
   * @param vtrantype ��������
   * @return ��Ʊ��������vo
   * @throws BusinessException
   */
  M32TranTypeVO queryTranType(String pk_group, String vtrantype)
      throws BusinessException;

}
