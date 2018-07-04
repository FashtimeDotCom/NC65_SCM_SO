package nc.pubitf.so.m30.split;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * ���۶���ҵ��ί�й�ϵת���ֵ�����
 * 
 * @since 6.0
 * @version 2011-6-30 ����01:47:09
 * @author fengjb
 */
public interface ISaleOrderOrgRelaSplit {
  /**
   * ����ҵ��ί�й�ϵ�Ŀ����֯�ֵ�
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitBySendStockOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  /**
   * ����ҵ��ί�й�ϵ��������֯�ֵ�
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitByTrafficOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  /**
   * ����ҵ��ί�й�ϵ�Ľ��������֯�ֵ�
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitBySettleOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  /**
   * ����ҵ��ί�й�ϵ��Ӧ����֯�ֵ�
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitByArOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  
}
