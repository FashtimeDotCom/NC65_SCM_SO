package nc.pubitf.so.m30.pu.m21;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * �����ֹʹ�� ʹ�ã�nc.pubitf.so.m30.pu.m21.ISaleOrderFor21.push21To30������
 * 
 * @since 6.3
 * @version 2013-1-25 ����04:27:42
 * @author ף����
 */
@Deprecated
public interface IPush21To30 {

  /**
   * 
   * @param srcBills
   * @throws BusinessException
   */
  void push21To30(OrderVO[] srcBills) throws BusinessException;
}
