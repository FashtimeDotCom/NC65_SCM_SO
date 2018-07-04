package nc.pubitf.so.m30.mobile;

import java.util.List;
import java.util.Map;

/**
 * �ƶ�Ӧ���ҵĶ����ӿ�
 * 
 * @since 6.1
 * @version 2013-04-16 09:06:39
 * @author yixl
 */
public interface IMySaleOrderQuery {

  /**
   * ������������
   * 
   * @param groupId ���ű�ʶ
   * @param userId �û���ʶ
   * @param condition ��������
   * @param startLine ��ʼλ��
   * @param count ÿҳ��¼��
   * @return List
   */
  List<Map<String, Object>> getBillByCondition(String groupId, String userId,
      int startLine, int count, String condition);

  /**
   * ��ѯ������ϸ
   * 
   * @param groupId ���ű�ʶ
   * @param userId �û���ʶ
   * @param billId ����ID
   * @param startLine ��ʼλ��
   * @param count ÿҳ��¼��
   * @return List
   */
  List<Map<String, Object>> getBillDetail(String groupId, String userId,
      String billId, int startLine, int count);

  /**
   * ��ѯ�ͻ����������б�
   * 
   * @param groupId ���ű�ʶ
   * @param userId �û���ʶ
   * @param startLine ��ʼλ��
   * @param count ÿҳ��¼��
   * @param customerid �ͻ���ʶ
   * @return List
   */
  List<Map<String, Object>> getBillListByCustomer(String groupId,
      String userId, int startLine, int count, String customerid);

  /**
   * ��ѯ�ͻ�����
   * 
   * @param groupid
   * @param userid
   * @param pk_customer
   * @return List
   */
  List<Map<String, Object>> getCustomerDetail(String groupid, String userid,
      String pk_customer);

  /**
   * ��ѯ�ҵĶ������鷽ʽ
   * 
   * @param groupId ���ű�ʶ
   * @param userId �û���ʶ
   * @return List
   */
  List<Map<String, Object>> getMyBillGrpType(String groupId, String userId);

  /**
   * ��ѯ�ҵĶ����б�
   * 
   * @param groupId ���ű�ʶ
   * @param userId �û���ʶ
   * @param startLine ��ʼλ��
   * @param count ÿҳ��¼��
   * @param groupType ���鷽ʽ
   * @return List
   */
  List<Map<String, Object>> getMyBillList(String groupId, String userId,
      int startLine, int count, String groupType);

  /**
   * ��ѯ�ҵĿͻ��б�
   * 
   * @param pk_group ���ű�ʶ
   * @param pk_userid �û���ʶ
   * @param start
   * @param count
   * @return List
   */
  List<Map<String, Object>> getMyCustomerList(String pk_group,
      String pk_userid, int start, int count);

  /**
   * ��ѯ���۶�������������������
   * 
   * @param groupId
   * @param userId
   * @return List
   */
  List<Map<String, Object>> getSOSerchCondition(String groupId, String userId);
}
