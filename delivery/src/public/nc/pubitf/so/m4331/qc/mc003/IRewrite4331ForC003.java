package nc.pubitf.so.m4331.qc.mc003;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * �ʼ쵥��д������
 * 
 * @since 6.0
 * @version 2010-12-20 ����04:27:58
 * @author ף����
 */
public interface IRewrite4331ForC003 {

  /**
   * �ʼ쵥��˻�д������
   * 
   * @param vos
   * @throws BusinessException
   */
  void rewriteOnApprove(DeliveryCheckVO[] vos) throws BusinessException;

  /**
   * �ʼ쵥�����д������
   * 
   * @param vos
   * @throws BusinessException
   */
  void rewriteOnUnApprove(DeliveryCheckVO[] vos) throws BusinessException;

}
