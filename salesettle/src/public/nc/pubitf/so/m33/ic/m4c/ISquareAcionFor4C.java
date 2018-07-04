package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * <p>
 * <b>���۳��ⵥ����ӿ�</b>
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
 * @time 2010-7-30 ����09:36:41
 */
public interface ISquareAcionFor4C {

  /**
   * �Զ��ɱ����㶯��
   * 
   * @param vos
   */
  void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * �Զ��ݹ����붯��
   * 
   * @param vos
   */
  void autoSquareEstimateSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * �Զ�Ӧ�ս��㶯��
   * 
   * @param vos
   */
  void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * �Զ����뷢����Ʒ
   * 
   * @param vos
   */
  void autoSquareRegisterSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * �ֹ��ɱ����㶯��
   * 
   * @param vos
   */
  void manualSquareCostSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * �ֹ�Ӧ�ս��㶯��
   * 
   * @param vos
   */
  void manualSquareIncomeSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * ����Գ�
   * 
   * @param bluebids -- �������۳��ⵥbid
   * @param redbids -- �������۳��ⵥbid
   */
  UFDouble outRush(String[] bluebids, String[] redbids)
      throws BusinessException;

}
