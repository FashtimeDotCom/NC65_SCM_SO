package nc.pubitf.so.mbuylagress.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * ���������������ò�ѯ�ӿ�
 * 
 * @since 6.3
 * @version 2012-10-22 ����01:18:00
 * @author ������
 */
public interface IBuylargessForMecc {
  /**
   * ���ݼ��š�������֯��ѯ��������
   * 
   * @param saleorerhids ����ID����
   * @param saleorerbids ������֯ID����
   * @return ��������VO����
   * @throws BusinessException
   * 
   */
  BuyLargessVO[] queryBuyLargessVO(String[] pk_groups, String[] pk_orgs)
      throws BusinessException;
}
