package nc.pubitf.so.m30.balend;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������ر��ṩ�Ľӿڷ�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-7-14 ����08:43:08
 */
public interface ISaleOrderBalEndSrv {
  /**
   * ��������������У�鶩�����Ƿ�ɱ�����رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param saleorderbids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 ����08:49:36
   */
  UFBoolean[] isCostBalEnd(String[] saleorderbids) throws BusinessException;

  /**
   * ��������������У�鶩�����Ƿ�Ӧ�ս���رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param saleordbids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 ����08:47:13
   */
  UFBoolean[] isIncomeBalEnd(String[] saleorderbids) throws BusinessException;

  /**
   * �����������������������Զ�����ر�,�����Զ�Ӧ�ս���رպ��Զ��ɱ�����رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param saleorderbids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 ����08:51:30
   */
  void processAutoBalEnd(BalEndPara para) throws BusinessException;

  /**
   * �����������������������Զ�����򿪣������Զ�Ӧ�ս���򿪺��Զ��ɱ�����򿪡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param saleorderbids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 ����09:10:13
   */
  void processAutoBalOpen(BalOpenPara para) throws BusinessException;
}
