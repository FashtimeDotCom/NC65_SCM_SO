package nc.pubitf.so.m30.mmdp.aid;

import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶����ṩ��ʵ�ʶ�������Ľӿ�
 * 
 * @since 6.0
 * @version 2011-12-5 ����03:21:36
 * @author ô��
 */
public interface ISaleOrderForAid {

  /**
   * �������۶�������PK��ѯ���۶�����Ϣ
   * 
   * @param csaleorderbids ����ID
   * @return SaleOrderVO �����ۺ�VO
   * @throws BusinessException
   * @author ������
   */
  SaleOrderVO[] queryOrderByBid(String[] csaleorderbids)
      throws BusinessException;

  /**
   * ʵ�ʶ����������֧�ֲ�ѯ����ָ�����������Ϸ��ࡢ����״̬���ͻ���
   * ����޸����ڵȲ�ѯ����������Ԥ���������۶����ĵ�����Ϣ��ָ������ƥ������Ԥ������
   * ���۶����ļƻ�������֯
   * 
   * @param para
   *          para����ʼ��������dendbilldate,
   *          ��ֹ��������dbeginbilldate,
   *          ���۶���������֯�б� csendstockorgids,
   *          ����ID�б� cmaterialids,
   *          ����״̬�б� fstatusflags���Ա���
   * @return ���VO
   * @throws BusinessException
   */
  List<ResultVO> queryOrderDetails(ParaVO para) throws BusinessException;
}
