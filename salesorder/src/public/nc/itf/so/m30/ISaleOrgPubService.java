package nc.itf.so.m30;

import java.util.List;
import java.util.Map;

import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;

/**
 * ������֯ ��ѯ����ӿ�
 */
public interface ISaleOrgPubService {

  /**
   * 
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param count
   * @return
   * @throws BusinessException
   *           <p>
   * @author gdsjw
   * @time 2010-6-18 ����12:17:45
   */
  String[] getOIDArray(int count) throws BusinessException;

  /**
   * ���ݿͻ�ID��������֯ID������ID,�õ�Ĭ�Ͻ��������֯VO��Ӧ����֯VO����������VO��VO������Ϣ��ID����֯���롢��֯���ơ���֯�汾 �����ţ�
   * �߼���
   * 1������������֯�ӿͻ�������ȡ�ý��������֯��Ӧ����֯���������ģ�
   * 2���ͻ�������δָ���̶����������֯������������֯������������Ʒ�ߡ������֯ƥ������ҵ��ί�й�ϵ��
   * ȡ�ý��������֯��Ӧ����֯���������ģ�
   * 3�������2��δ�ҵ�����������ί�й�ϵ������������֯��Ϊָ����֯ID����Ʒ��Ϊ�յ�ҵ��ί�й�ϵƥ�䣬
   * ȡ�ý��������֯��Ӧ����֯���������ģ�
   * 4�����������������󣬲�����֯��Ӧ����֯��Ϊ�գ�ͬʱ������֯���ǲ�����֯����ӦΪ�յĲ�����֯ȡ������֯ID��
   * �����������Ϊ�գ���Ӧ����֯���������ģ�����������ȡӦ����֯��
   */
  Map<String, List<OrgVO>> queryDefaultOtherOrgIDsFromSaleOrgRel(
      String customerID, String saleorgID, String[] materialids)
      throws BusinessException;

  /**
   * ����������֯ID������ID,�õ�Ĭ�Ϸ��������֯VO��VO������Ϣ��ID����֯���롢��֯���ơ���֯�汾 ������
   * �߼���
   * 1�����ݡ�������֯+����������Ʒ�ߡ�Ψһȷ��һ��Ĭ�Ϸ��������֯��
   * 2�����������������Ʒ�ߡ�δָ������ݵ�1��û���ҵ������֯��
   * ����ݡ�������֯��Ϊָ����֯ID����Ʒ��Ϊ�յ�ҵ��ί�й�ϵȷ��Ĭ�Ϸ��������֯��
   * 3�������û�ҵ�Ĭ�Ϸ��������֯��ͬʱ������֯�־��п����֯���ԣ���ȡ������֯ΪĬ�Ϸ��������֯��
   */
  Map<String, OrgVO> queryDefaultStockOrgIDFromSaleOrgRel(String saleorgID,
      String[] materialids) throws BusinessException;

  /**
   * ����������֯ID������ID,�õ��������Ŀ����֯VO[]��VO������Ϣ��ID����֯���롢��֯���ơ���֯�汾 ������
   * �߼���
   * 1�����ݡ�������֯+����������Ʒ�ߡ�ƥ������ҵ��ί�й�ϵ��ƥ���ϵĿ����֯��������
   * 2��ƥ�䡰������֯��Ϊָ����֯ID����Ʒ��Ϊ�յ�ҵ��ί�й�ϵ��ƥ���ϵĿ����֯��������
   * 3��������֯�־��п����֯���ԣ���������֯��Ϊ���������֯��������
   * ˵�������ص�ID��Ӧ�����ظ�
   */
  Map<String, OrgVO[]> queryStockOrgIDsFromSaleOrgRel(String saleorgID,
      String[] materialids) throws BusinessException;

}
