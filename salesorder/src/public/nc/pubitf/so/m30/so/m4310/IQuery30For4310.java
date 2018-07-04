package nc.pubitf.so.m30.so.m4310;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * Ϊ���۵��ṩ��ѯ���۶�������
 * 
 * @since 6.1
 * @version 2013-06-04 11:05:35
 * @author yixl
 */
public interface IQuery30For4310 {

  /**
   * ��ѯ���������Ƿ��������۶���
   * 
   * @param quotationhids
   * @return Map<String, UFBoolean> key:���۵�Hid value���Ƿ������ε���
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistNextOrder(String[] quotationhids)
      throws BusinessException;
}
