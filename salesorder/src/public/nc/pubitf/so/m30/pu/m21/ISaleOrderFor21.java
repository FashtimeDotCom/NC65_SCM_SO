package nc.pubitf.so.m30.pu.m21;

import java.util.Map;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ���۶����ṩ���ɹ���������ӿ�
 * 
 * @since 6.0
 * @version 2011-5-11 ����02:59:33
 * @author ��־ΰ
 */
public interface ISaleOrderFor21 {
	
  /**
	 * �������۶���id��ѯ�Ƿ�ɹ�����Эͬ���ɵ�
	 * 
	 * @param ids ���۶���id[]
	 * @return Map<String, Boolean> Map<���۶���id, �Ƿ�ɹ�����Эͬ����>
	 * @throws BusinessException
	 */	
  Map<String,UFBoolean> query30IsFromCoop(String[] ids)throws BusinessException;

  /**
   * �������۶�������bid��ѯ��Դbid
   * 
   * @param ids ���۶�������id[]
   * @return Map<String, String> Map<���۶���bid, �ɹ�����bid>
   * @throws BusinessException
   */
  Map<String, String> queryCoop21Bids(String[] bids) throws BusinessException;

  /**
   * �ɹ�����Эͬ�������۶���
   * 
   * @param ids ���۶�������id[]
   * @return Map<String, String> Map<���۶���bid, �ɹ�����bid>
   * @throws BusinessException
   */
  void push21To30(OrderVO[] srcBills) throws BusinessException;

  /**
   * ��ѯ����˺͹ر�״̬�����۶����ϵ���������
   * 
   * @param cmaterialid ����ID
   * @param queryDate ��ѯ��ʼ����
   * @param queryDay ʱ��̳���
   * @param pk_group ����
   * @param pk_org ������֯
   * @return ����IDΪKEY������ΪValue��Map
   */
  Map<String, UFDouble> getSaleOrderNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org);

  /**
   * ���������Ƿ�ֱ�˲ɹ�
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ�˲ɹ�>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;
}
