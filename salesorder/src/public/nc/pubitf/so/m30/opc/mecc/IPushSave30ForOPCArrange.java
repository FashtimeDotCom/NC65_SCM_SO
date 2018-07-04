package nc.pubitf.so.m30.opc.mecc;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶����ṩ������ͳһ���������ṩ�Ľӿ�
 * �ӿڱ����ó�����
 * ����ͳһ�������İ��Ž����������ɶ�������ť��
 * 
 * @since 6.1
 * @version 2012-2-22 ����14:11:13
 * @author ����
 */

public interface IPushSave30ForOPCArrange {

  /**
   * ��ʽ����ERP���۶���
   * 
   * @param paras ����������۶���
   * @param businessCheckMap ҵ����Map:ATP��顢 ���ü�顢�����ڽ���顢������������顢���ڿ������������
   * @throws BusinessException
   */
  void pushSave(SaleOrderVO[] paravo, Map<String, Boolean> businessCheckMap)
      throws BusinessException;

}
