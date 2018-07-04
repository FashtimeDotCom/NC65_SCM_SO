package nc.pubitf.so.mobile.analy;

import java.util.List;
import java.util.Map;

/**
 * �ƶ�Ӧ�ö��������ӿ�
 * 
 * @since 6.1
 * @version 2013-04-16 09:05:06
 * @author yixl
 */
public interface ISaleOrderAnaly {

  /**
   * ��ѯ�����շ�������
   * 
   * @param groupid ���ű�ʶ
   * @param usrid �û���ʶ
   * @param qrydate ��ѯ����
   * @param grouptype ���鷽ʽ
   * @param productlineid ��Ʒ��
   * @param brandid Ʒ��
   * @param channeltypeid ��������
   * @param saleorgid ������֯
   * @param customerid �ͻ�
   * @return List
   */
  List<Map<String, Object>> qryDayAnalysis(String groupid, String usrid,
      String qrydate, String grouptype, String productlineid, String brandid,
      String channeltypeid, String saleorgid, String customerid);

  /**
   * ��ѯ�����ձ�����
   * 
   * @param groupid ���ű�ʶ
   * @param usrid �û���ʶ
   * @param qrydate ��ѯ����
   * @param currid ����
   * @param grouptype ���鷽ʽ
   * @param bizmanid ҵ��Ա
   * @param customerid �ͻ�
   * @param invid ����
   * @return List
   */
  List<Map<String, Object>> qryDayReport(String groupid, String usrid,
      String qrydate, String currid, String grouptype, String bizmanid,
      String customerid, String invid);

  /**
   * ��ѯ�����б�
   * 
   * @param groupid ���ű�ʶ
   * @param usrid �û���ʶ
   * @param qrydate ��ѯ����
   * @param currid ����
   * @param bizmanid ҵ��Ա
   * @param customerid �ͻ�
   * @param invid ����
   * @param startline ��ʼλ��
   * @param count ÿҳ��¼��
   * @return List
   */
  List<Map<String, Object>> qrySOList(String groupid, String usrid,
      String qrydate, String currid, String bizmanid, String customerid,
      String invid, int startline, int count);

  /**
   * ��ѯ�����б�
   * 
   * @param groupid ���ű�ʶ
   * @param usrid �û���ʶ
   * @param qrydate ��ѯ����
   * @param grouptype ���鷽ʽ
   * @param bizmanid ҵ��Ա
   * @param customerid �ͻ�
   * @param invid ����
   * @return List
   */
  List<Map<String, Object>> qryCurAndReport(String groupid, String usrid,
      String qrydate, String grouptype, String bizmanid, String customerid,
      String invid);
}
