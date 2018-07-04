package nc.pubitf.so.ic.m4c;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.paravo.Para4CFor32SignBiz;

public interface ISaleFor4CParaQuery {

  /**
   * uap�ӿ���ʱ����
   * ��;�� ����������֯�õ��������Ŀ����֯ID[]�� �߼���
   * 1������������֯ƥ������ҵ��ί�й�ϵ��ƥ���ϵĿ����֯��������
   * 2��������֯�־��п����֯���ԣ���������֯��Ϊ���������֯�������� ˵�������ص�ID��Ӧ�����ظ�
   * 
   * @param saleorgID
   * @param materialID
   * @return
   * @throws BusinessException
   */
  Map<String, List<String>> getStockOrgIDSBySaleOrgID(String[] orgids)
      throws BusinessException;

  /**
   * ��ȡ������֯ �Ƿ���Ʒ��Ʊ
   * 
   * @param cfinaceorgids ������֯
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> getSO20(String[] cfinaceorgids)
      throws BusinessException;

  /**
   * ����ǩ��������Ʊ��ҵ������
   * 
   * @param pk_orgs
   * @return
   * @throws BusinessException
   */
  Para4CFor32SignBiz[] querySignNumBusitype(Para4CFor32SignBiz[] paras)
      throws BusinessException;

}
