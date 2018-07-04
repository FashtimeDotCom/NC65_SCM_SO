package nc.pubitf.so.m30.split;
/**
 * ���۶��������ε������ҵ��ί�й�ϵ�ֶηֵ��ṩ�Ĳ����ӿ�,û����Ӧ�ֶ�ʱֱ�ӷ���NULL����
 * 
 * @since 6.0
 * @version 2011-6-30 ����01:31:45
 * @author fengjb
 */
public interface ISaleOrderSplitPara {

  /**
   * ���ر�������
   * 
   * @return
   */
  int getBodyCount();
  /**
   * ����������֯һ���Ƿֵ��������������۶������ε���������֯���ڱ�ͷ��ͳһ¼�룬
   * ��������֯�������У�ͳһ����������֯OID
   * 
   * @param row
   * @return
   */
  String getSaleOrgid();
  /**
   * ���ڶ����ͻ�һ���Ƿֵ��������������۶������ε��ݶ����ͻ����ڱ�ͷ��ͳһ¼�룬
   * �ʶ����ͻ��������У�ͳһ���ض����ͻ�ID
   * 
   * @param row
   * @return
   */
  String getCustomerid();
  /**
   * ���ص�row�е�����OID
   * 
   * @param row
   * @return
   */
  String getMaterialid(int row);
  /**
   * ���ص�row�еĿ����֯OID
   * 
   * @param row
   * @return
   */
  String  getSendStockOrgid(int row);
  /**
   * ���ص�row�е�������֯OID
   * 
   * @param row
   * @return
   */
  String  getTrafficOrgid(int row);
  /**
   * ���ص�row�еĽ��������֯OID
   * 
   * @param row
   * @return
   */
  String  getSettleOrgid(int row);
  /**
   * ���ص�row�е�Ӧ�ղ�����֯OID
   * 
   * @param row
   * @return
   */
  String  getArOrgid(int row);
  
}
