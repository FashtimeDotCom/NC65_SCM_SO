/**
 * $�ļ�˵��$
 *
 * @author ô��
 * @version 6.0
 * @see
 * @since
 * @time 2010-11-4 ����07:10:09
 */
package nc.pubitf.so.m32.pu.m21;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ����Ʊ��Ʊ�ṩ���ɹ������Ĳ�ѯ���ܽӿ�
 * 
 * @since 6.0
 * @version 2010-12-15 ����09:05:20
 * @author ô��
 */
public interface ISaleInvoiceQryFor21 {

  /**
   * ��ѯ���ϵ�������
   * 
   * @param cmaterialid ����ID
   * @param queryDate ��ѯ��ʼ����
   * @param queryDay ʱ��̳���
   * @param pk_group ����
   * @param saleorg ������֯
   * @return ����IDΪKEY������ΪValue��Map
   * @throws BusinessException
   */
  Map<String, UFDouble> getInvInvoiceNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String saleorg)
      throws BusinessException;
}
