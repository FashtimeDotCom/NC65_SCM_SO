package nc.itf.so.m30.self;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶������� ����ҵ����־���õĽӿ�
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�������޸ġ�ɾ��������
 * </ul>
 * 
 * <p>
 * 
 * @author buxh
 * 
 */

public interface ISaleOrderScriptMaintain {

  /**
   * �����������������۶����޸ı��湦�ܡ�
   */
  SaleOrderVO[] saleOrderUpdate(SaleOrderVO[] vos, PfUserObject userObj,
      SaleOrderVO[] originBills) throws BusinessException;

  /**
   * �����������������۶����������湦�ܡ�
   */
  SaleOrderVO[] saleOrderInsert(SaleOrderVO[] vos, PfUserObject userObj)
      throws BusinessException;

  /**
   * �����������������۶�����ɾ�����ܡ�
   */
  SaleOrderVO[] saleOrderDelete(SaleOrderVO[] vos, PfUserObject userObj)
      throws BusinessException;

}
