package nc.itf.so.m33.ref.ia.mi5;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4CIntransit;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4COutrush;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4CSettle;
import nc.pubitf.ia.mi5.so.IIAI5ForSOSquareEnd;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class IAI5For4CServicesUtil {

  private IAI5For4CServicesUtil() {
    super();
  }

  /**
   * �����������������۳��ⵥȡ��������Ʒ
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 ����02:26:47
   */
  public static void deleteI5ForSO4CUnIntransit(String[] outids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO4CIntransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CIntransit.class);
    bo.deleteI5ForSO4CUnIntransit(outids, csettledetailids);
  }

  /**
   * �����������������۳��ⵥȡ���ɱ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 ����02:26:47
   */
  public static void deleteI5ForSO4CUnSettle(String[] outids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO4CSettle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CSettle.class);
    bo.deleteI5ForSO4CUnSettle(outids, csettledetailids);
  }

  /**
   * ȡ�����۳������ر�ɾ��������㵥��
   * 
   * @param csrcids ��Դ����ID(������۳��ⵥID)
   * @param csrcbids ��Դ������ID(���۽�����ϸID)
   * @throws BusinessException
   */
  public static void deleteI5ForSOUnSquareEnd(String[] csrcids,
      String[] csrcbids) {
    IIAI5ForSOSquareEnd bo =
        NCLocator.getInstance().lookup(IIAI5ForSOSquareEnd.class);
    try {
      bo.deleteI5ForSOUnSquareEnd(csrcids, csrcbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������������(���۳��ⵥ)���۽��㵥��������Ʒ�跽
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 ����02:26:47
   */
  public static void insertI5ForSO4CIntransit(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO4CIntransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CIntransit.class);
    bo.insertI5ForSO4CIntransit(i5vos);
  }

  /**
   * ��������������(���۳��ⵥ)���۽��㵥��������Ʒ����������Գ壩
   * 
   * @param i5vos
   * @throws BusinessException
   */
  public static void insertI5ForSO4CIntransitForOutrush(I5BillVO[] i5vos) {
    IIAI5ForSO4COutrush bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4COutrush.class);
    try {
      bo.insertI5ForSO4COutrush(i5vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������������ȡ��(���۳��ⵥ)���۽��㵥��������Ʒ����������Գ壩
   * 
   * @param i5vos
   * @throws BusinessException
   */
  public static void insertI5ForSO4CIUnntransitForOutrush(String[] outids,
      String[] csettledetailids) {
    IIAI5ForSO4COutrush bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4COutrush.class);
    try {
      bo.deleteI5ForSO4CUnOutrush(outids, csettledetailids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������������(���۳��ⵥ)���۽��㵥���ɱ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 ����02:26:47
   */
  public static void insertI5ForSO4CSettle(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO4CSettle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CSettle.class);
    bo.insertI5ForSO4CSettle(i5vos);
  }

  /**
   * ���۳������ر��γɴ�����㵥��
   * 
   * @param bills ����������۳ɱ���ת������
   * @throws BusinessException
   */
  public static void insertI5ForSOSquareEnd(I5BillVO[] bills) {
    IIAI5ForSOSquareEnd bo =
        NCLocator.getInstance().lookup(IIAI5ForSOSquareEnd.class);
    try {
      bo.insertI5ForSOSquareEnd(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ����������ϸ��ѯ��Ӧ���ֽ跽������δ�س������
   * 
   * @param csrcids ��Դ����ID���飨���۳��ⵥ��ͷID��
   * @param csrcbids ��Դ������ID���飨���۳�������㵥��ϸID��
   * @return MAP
   *         <li> key : ���۳�������㵥��ϸID
   *         <li> value: ʣ����Ҫ��������Ʒ����������
   */
  public static Map<String, UFDouble> querySaleOutRegNotAllSquare(
      String[] csrcids, String[] csrcbids) {
    // Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
    IIAI5ForSOSquareEnd bo =
        NCLocator.getInstance().lookup(IIAI5ForSOSquareEnd.class);
    try {
      Map<String, UFDouble> ret =
          bo.querySaleOutRegNotAllSquare(csrcids, csrcbids);
      return ret;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
