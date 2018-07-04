package nc.pubitf.so.split;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * ���۶���ֵ������ӿ�
 * 
 * @since 6.0
 * @version 2011-7-11 ����03:03:43
 * @author ô��
 */
public interface ISplitBillByFreeCust {
  /**
   * 
   * @param vo �ۺ�VO
   * @param keys 0: ���۶���ID
   * @return
   * @throws BusinessException
   */
  public List<String> splitByFreeCust(AggregatedValueObject vo, String[] keys)
      throws BusinessException;
}
