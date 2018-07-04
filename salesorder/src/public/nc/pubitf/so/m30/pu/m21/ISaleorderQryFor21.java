package nc.pubitf.so.m30.pu.m21;

import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ��ѯ����˺͹ر�״̬�����۶����ϵ���������
 * 
 * @since 6.0
 * @version 2010-12-15 ����09:01:05
 * @author ô��
 */
public interface ISaleorderQryFor21 {

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
}
