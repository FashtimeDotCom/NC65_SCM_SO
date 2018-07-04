package nc.itf.so.m4331;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;

public interface IDeliveryScriptMaintain {

  /**
   * ���������������������޸ı��湦�ܡ�
   */
  DeliveryVO[] deliveryWrite(DeliveryVO[] vos, PfUserObject userObj,
      DeliveryVO[] originBills) throws BusinessException;

  /**
   * ��������������������ɾ�����ܡ�
   */
  void deliveryDelete(DeliveryVO[] vos, PfUserObject userObj)
      throws BusinessException;

}
