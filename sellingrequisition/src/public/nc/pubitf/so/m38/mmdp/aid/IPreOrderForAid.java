package nc.pubitf.so.m38.mmdp.aid;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * Ԥ�����ṩ��ʵ�ʶ�������Ľӿ�
 * 
 * @since 6.0
 * @version 2011-12-5 ����03:21:36
 * @author ô��
 */
public interface IPreOrderForAid {

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
  List<ResultVO> queryPreOrderDetails(ParaVO para) throws BusinessException;
}
