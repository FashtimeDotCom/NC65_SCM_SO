package nc.pubitf.so.m33.so.m32;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * <p>
 * <b>���۷�Ʊ����ӿ�</b>
 * 
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-7-30 ����09:37:14
 */
public interface ISquareAcionFor32 {

  /**
   * �Զ��ɱ����㶯��
   * 
   * @param vos
   */
  void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * �Զ�Ӧ�ս��㶯��
   * 
   * @param vos
   */
  void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * ��Ӧ��
   * 
   * @param vos
   */
  void squareAdjustIncomeSrv(AbstractBill[] vos)
      throws BusinessException;

}
