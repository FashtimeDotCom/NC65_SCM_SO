package nc.pubitf.so.m33.so.m30;

import java.util.Map;

/**
 * ���۽����ṩ�����۶�������رղ�ѯ�ӿ�
 * 
 * @since 6.0
 * @version 2011-8-4 ����10:58:17
 * @author zhangcheng
 */
public interface IQuerySquareBillFor30SqEnd {

  /**
   * ��ѯ��ǰҵ�������н��㵥�����ͣ����۳��ⵥ����������۷�Ʊ����
   * 
   * @param orderbids ���۶�����id(�����ظ�)
   * @param busiids ҵ������id (�����ظ�)
   * @return (ҵ������ID,���㵥������[]{0 Ӧ�ս��㵥������ 1 �ɱ����㵥������} )
   *         ���û�в��������Ӧ��ֵΪnull
   */
  Map<String, String[]> querySquareBillFor30SqEnd(String[] orderbids,
      String[] busiids);

}
