package nc.pubitf.so.m30.api;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderExternalVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶������빫���ӿ�<br>
 * 
 * <b>��Ҫ���ܣ�</b>
 * <ol>
 * 
 * <li>����SaleOrderExternalVO</li>
 * <li>����SaleOrderVO</li>
 * <li>ɾ��SaleOrderVO</li>
 * </ol>
 * 
 * @since 6.3
 * @version 2013-06-06 15:40:45
 * @author ����
 */
public interface ISaleOrderExternal {

  /**
   * <b>����SaleOrderExternalVO </b><br>
   * 
   * <b>��Ҫ���ܣ�</b>
   * <ol>
   * 
   * <li>VO����У��</li>
   * <ol>
   * <li>
   * ��ͷ�����������֯PK_ORG����������CTRANTYPEID����������DBILLDATE���ͻ�CCUSTOMERID������CDEPTVID��
   * ԭ��CORIGCURRENCYID</li>
   * <li>�����������ϱ���CMATERIALVID�����������֯CSENDSTOCKORGVID�����������֯CSETTLEORGVID</li>
   * <li>������λ���䣺��λCASTUNITID������NASTNUM����˰�ϼ�NORIGTAXMNY</li>
   * </ol>
   * <li>SaleOrderExternalVOתSaleOrderVO</li> <li>���ݲ�ȫ�������������ݲ�ȫ����ID�Լ���������</li>
   * </ol>
   * 
   * @param SaleOrderExternalVO[] vos
   *          ��VO�ṹ�����۶���VOһ�£����������ȴ���SaleOrderExternalVO������������Ϊ���۶�����VO��ʽ
   * 
   * @since 6.3
   * @version 2013-06-06 15:40:45
   * @author ����
   */
  SaleOrderVO[] insertInterfaceVOForSaleOrder(SaleOrderExternalVO[] vos)
      throws BusinessException;

  /**
   * <b>����SaleOrderVO(��ȫ����) </b><br>
   * 
   * <b>��Ҫ���ܣ�</b>
   * <ol>
   * 
   * <li>VO����У��</li>
   * <ol>
   * <li>
   * ��ͷ�����������֯PK_ORG����������CTRANTYPEID����������DBILLDATE���ͻ�CCUSTOMERID������CDEPTVID��
   * ԭ��CORIGCURRENCYID</li>
   * <li>�����������ϱ���CMATERIALVID�����������֯CSENDSTOCKORGVID�����������֯CSETTLEORGVID</li>
   * <li>������λ���䣺��λCASTUNITID������NASTNUM����˰�ϼ�NORIGTAXMNY</li>
   * </ol>
   * <li>���ݲ�ȫ�������������ݲ�ȫ����ID�Լ���������</li>
   * </ol>
   * 
   * @param SaleOrderVO[] vos
   *          ���۶���VO
   * 
   * @since 6.3
   * @version 2013-08-28 15:40:45
   * @author ����
   */
  SaleOrderVO[] insertSaleOrderVOFor30(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * �������۶���VO(����ȫ����)<br>
   * 
   * <b>��Ҫ���ܣ�</b>
   * <ol>
   * <li>�����׼SaleOrderVO</li>
   * <li>�������������۶��������̨�߼�һ��</li>
   * </ol>
   * 
   * @param SaleOrderVOS(����Ϊ����VO)
   * @return
   * @throws BusinessException
   */
  SaleOrderVO[] insertSaleOrderVOForSaleOrder(SaleOrderVO[] SaleOrderVOS)
      throws BusinessException;

  /**
   * ɾ��SaleOrderVO<br>
   * 
   * <b>��Ҫ���ܣ�</b>
   * <ol>
   * <li>ɾ��SaleOrderVO</li>
   * <li>���������۶�����ʵ��csaleorderid</li>
   * <ol>
   * <li>csaleorderid</li>
   * </ol>
   * </ol>
   * 
   * @param pks
   * @return
   * @throws BusinessException
   */
  void deleteSVOForSaleOrder(String[] pks) throws BusinessException;

}
