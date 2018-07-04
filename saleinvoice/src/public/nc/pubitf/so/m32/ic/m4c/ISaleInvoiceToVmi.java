package nc.pubitf.so.m32.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�ƱΪ���ⵥ���Ļ����ṩ�ķ���ӿ�
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-7-27 ����10:19:21
 */
public interface ISaleInvoiceToVmi {

  /**
   * ���������������������Ļ���ȡ��ʱ������۷�Ʊ�����Ļ���ID��
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicebids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-8 ����04:25:31
   */
  void clearM32SumID(String[] invoicebids) throws BusinessException;

  /**
   * �������������������ѯ�������飬���ط��ϼ�¼�����۷�Ʊ��ͼVO
   * <p>
   * <b>����˵��</b>
   * 
   * @param fromwhereSql
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-8 ����04:17:18
   */
  SaleInvoiceViewVO[] queryConsumeInvoice(String sql) throws BusinessException;

  /**
   * ���������������������۷�Ʊ�ӱ�ID��ѯ���۷�Ʊ��ͼVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicebids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-27 ����06:59:11
   */
  SaleInvoiceViewVO[] queryInvoiceBybids(String[] invoicebids)
      throws BusinessException;

  /**
   * ���������������������Ļ���ID��ѯ���۷�Ʊ��ͼVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sumids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-27 ����06:43:45
   */
  SaleInvoiceViewVO[] queryInvoiceBySumids(String[] sumids)
      throws BusinessException;

  /**
   * 
   * �����������������մ���ı�ͷ�ͱ����ֶΣ������۷�Ʊ���л��ܺ󷵻ء�
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicebids
   * @param headkeys
   * @param bodykeys
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-9-13 ����09:24:25
   */
  SaleInvoiceViewVO[] queryVmiSumInvoice(String[] invoicebids,
      String[] headkeys, String[] bodykeys) throws BusinessException;

  /**
   * ��������������������ܻ�д���۷�Ʊ�����Ļ���ID��
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicebids
   * @param sumids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-8 ����04:23:43
   */
  void rewriteM32SumID(String[] invoicebids, String[] sumids)
      throws BusinessException;

  /**
   * 
   * ��������������������Դ����ID��ѯ��Ʊ������Ϣ��
   * 
   * @param srchids ��Դ��������ID����
   * @param srcbids ��Դ�����ӱ�ID����
   * @param qrykeys ��ѯ�ֶ�
   * @return
   */
  SaleInvoiceBVO[] queryInvoiceBodyBySrc(String[] srchids, String[] srcbids,
      String[] qrykeys) throws BusinessException;
}
