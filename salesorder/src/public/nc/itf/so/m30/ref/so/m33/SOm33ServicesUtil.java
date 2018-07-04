package nc.itf.so.m30.ref.so.m33;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m33.so.m30.IProcess4CAdjustFor30BalEnd;
import nc.pubitf.so.m33.so.m30.IQuerySquareBillFor30SqEnd;

/**
 * SO�������۽������
 * 
 * @since 6.0
 * @version 2011-8-4 ����08:22:42
 * @author ��־ΰ
 */
public class SOm33ServicesUtil {

  /**
   * ����������������������رպ���ⵥ�����ݹ�Ӧ����Ҫ���ɶ�Ӧ�Ļس�Ӧ�յ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ�����ݹ�Ӧ�յĶ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  public static void process4CAdjust(String[] ordBids) {
    // �ݹ�Ӧ�ս���رպ����ɻس�Ӧ�յ�
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.process4CAdjust(ordBids);
  }

  /**
   * ����������������������رպ���ⵥ����������Ʒ��Ҫ���ɴ���������Ʒ
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ����������Ʒ�Ķ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  public static void process4CReg(String[] ordBids) {
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.process4CReg(ordBids);
  }

  /**
   * ����������������������򿪺���ⵥ�����ݹ�Ӧ�գ������ɶ�Ӧ�Ļس�Ӧ�յ�ȡ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ�����ݹ�Ӧ�յĶ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  public static void unProcess4CAdjust(String[] ordBids) {
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.unProcess4CAdjust(ordBids);
  }

  /**
   * ����������������������򿪺���ⵥ����������Ʒ�������ɶ�Ӧ�Ĵ���������Ʒȡ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ����������Ʒ�Ķ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  public static void unProcess4CReg(String[] ordBids) {
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.unProcess4CReg(ordBids);
  }

  /**
   * ��ѯ��ǰҵ�������н��㵥�����ͣ����۳��ⵥ����������۷�Ʊ����
   * 
   * @param orderbids ���۶�����id(�����ظ�)
   * @param busiids ҵ������id (�����ظ�)
   * @return (ҵ������ID,���㵥������[]{0 Ӧ�ս��㵥������ 1 �ɱ����㵥������} )
   *         ���û�в��������Ӧ��ֵΪnull
   */
  public static Map<String, String[]> querySquareBillFor30SqEnd(
      String[] orderbids, String[] busiids) {
    IQuerySquareBillFor30SqEnd service =
        NCLocator.getInstance().lookup(IQuerySquareBillFor30SqEnd.class);
    return service.querySquareBillFor30SqEnd(orderbids, busiids);
  }
}
