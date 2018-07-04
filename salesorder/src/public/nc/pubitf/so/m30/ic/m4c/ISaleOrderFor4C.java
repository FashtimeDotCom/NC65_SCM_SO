package nc.pubitf.so.m30.ic.m4c;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ���۶����ṩ���۳��ⵥ�ӿ�
 * 
 * @since 6.0
 * @version 2011-3-23 ����05:55:42
 * @author ��־ΰ
 */
public interface ISaleOrderFor4C {

  /**
   * ����hids�ر���Ӧ���۶���
   * 
   * @param hids ���۶�������ID
   * @throws BusinessException
   */
  void close30For4C(String[] hids) throws BusinessException;

  /**
   * �������۶�������id�������յ����βɹ������ĸ��� id �ͱ�ͷid
   * 
   * @param bids ���۶�������ID
   * @return Map<String, String[]> Map<���۶�������ID, String[]{�ɹ���������ID,�ɹ���������ID}>
   * @throws BusinessException
   */
  Map<String, String[]> queryCoopPOBid(String[] bids) throws BusinessException;

  /**
   * ����4C�������ƺ�4C����Դͷ����id���ƹ�����˵�30�ѷ�Ʊ�رչ���SQLƬ��
   * 
   * @param icbodytable ���ⵥ����ID
   * @param cfirstbid Դͷ����ID
   * @return IQueryScheme sqlƬ��
   * @throws BusinessException
   */
  IQueryScheme getInvoicEndSQL4Filter4C(String icbodytable, String cfirstbid)
      throws BusinessException;

  /**
   * ����bids��ѯ���۶���VOs
   * <p>
   * bids ->ids -> VOs(��VOs�а�������bids > ����bids)
   * </p>
   * 
   * @param bids ��������id[]
   * @return SaleOrderVO[] ���۶���VO[]
   * @throws BusinessException
   */
  SaleOrderVO[] querySaleOrderVOs(String[] bids) throws BusinessException;

  /**
   * ����bids��ѯ���۶���ViewVOsָ��ֵ
   * 
   * @param bids ��������id[]
   * @param names ��Ҫ��ѯ��ֵ
   * @return SaleOrderViewVO[] ���۶���ViewVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids, String[] names)
      throws BusinessException;

  /**
   * ���������Ƿ�ֱ�˲ɹ�
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ�˲ɹ�>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ���������Ƿ�ֱ��(����ֱ�˲ɹ�Ҳ��ֱ�˵���)
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ��>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirect(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ���������Ƿ�;�𲹻�
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�;�𲹻�>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsWastageAppend(String[] ctrantypeids)
      throws BusinessException;

  /**
   * �������۶�������id ��ѯ���η������Ƿ�����Ԥ�����
   * 
   * @param bids
   * @return �Ƿ�����Ԥ����־
   * @throws BusinessException
   */
  Map<String, UFBoolean> getReserveInfo(String[] bids) throws BusinessException;

  /**
   * �������γ��ⵥ��ͷID�ͱ���ID��ѯ���ת���۵Ķ�����Ϣ
   * 
   * @param srchids
   * @param srcbids
   * @return ���۶�����ͼVO
   * @throws BusinessException
   */
  SaleOrderViewVO[] queryJCSaleOrderViewVOs(String[] srchids, String[] srcbids)
      throws BusinessException;
}
