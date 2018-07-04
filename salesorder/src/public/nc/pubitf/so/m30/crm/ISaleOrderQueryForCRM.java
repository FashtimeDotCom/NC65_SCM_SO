package nc.pubitf.so.m30.crm;

import java.util.Map;

import nc.pubitf.so.m4310.crm.CRMQueryPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ΪCRM�ṩ���۶�����ѯ�ӿ�
 * 
 * @since 6.3.1
 * @version 2013-08-06 20:03:48
 * @author ���Ʒ�
 */
public interface ISaleOrderQueryForCRM {

  /**
   * ����CRM���������ѯ���۶���
   * 
   * @param queryPara CRM��������
   * 
   * @return SaleOrderHVO[]���۶�����ͷVO
   * @throws BusinessException
   */
  public SaleOrderHVO[] querySaleOrder(CRMQueryPara queryPara)
      throws BusinessException;

  /**
   * ����������ѯ���۶���
   * 
   * @param id ���۶�������ID
   * 
   * @return ���۶�������VO
   * @throws BusinessException
   */
  public SaleOrderBVO[] querySaleOrderById(String id) throws BusinessException;

  /**
   * ������ѯ</br>
   * 
   * ���ڡ���������򡾼۸���������ѯ���۶���ֻ��һ��ƥ�伴�ɣ�ֻ��������ͨ���Ķ����С�
   * 
   * @param cmarketactid Ӫ���id
   * @param fieldnames Ҫ��ѯ���ֶ�
   * @return ���۶�������VO
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBodyVOsByActID(String cmarketactid,
      String[] fieldnames) throws BusinessException;

  /**
   * 
   * �������ýӿ�</br>
   * 
   * ���ڡ����������ѯ���۶���(����������Ʒ��)��ֻ��������ͨ���Ķ����С�
   * 
   * @param cmarketactid Ӫ���id
   * @param fieldnames Ҫ��ѯ���ֶ�
   * @return ���۶�������VO
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBodyVOsByLargessActID(String cmarketactid,
      String[] fieldnames) throws BusinessException;

  /**
   * �۸�������ýӿ�</br>
   * 
   * ���ڡ��۸���������ѯ���۶�����ֻ��������ͨ���Ķ�����
   * 
   * @param cmarketactid Ӫ���id
   * @param fieldnames Ҫ��ѯ���ֶ�
   * @return ���۶�������VO
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBodyVOsPriceActID(String cmarketactid,
      String[] fieldnames) throws BusinessException;

  /**
   * ���ݻ��ѯ��������PK</br>
   * 
   * ���ݻidƥ�����۶���������ʹ����۸���ֻҪ��һ��ƥ�䵽�򷵻ض�����ͷpk,�����𵥾�״̬��
   * 
   * @param cmarketactid Ӫ���id
   * @param orderbyFileds �����ֶΣ���������
   * @return ������ͷPK����
   * @throws BusinessException
   */
  String[] querySaleOrderIDsByActID(String cmarketactid, String[] orderbyFileds)
      throws BusinessException;

  /**
   * �������۶�����ͷPK��ѯ������ͷVO(����)
   * 
   * @param pk_saleorders ���۶�������PK����
   * @return ���۶�����ͷVO
   * @throws BusinessException
   */
  SaleOrderHVO[] querySaleOrderHeadVOsByOrderIDs(String[] pk_saleorders)
      throws BusinessException;

  /**
   * ��Ͷ���PK��ѯ�������ӱ�</br>
   * 
   * ���ض���������ƥ��ı����У��������������۸�������</br>
   * ֻ��һ��ƥ�伴���ء�����ƥ�䲻�ϵı����в����ء�
   * 
   * @param pk_saleorder ���۶�������PK
   * @param cmarketactid Ӫ���id
   * @return ���۶����ۺ�VO
   * @throws BusinessException
   */
  SaleOrderVO querySaleOrderVOByActIDAndOrderID(String pk_saleorder,
      String cmarketactid) throws BusinessException;
  
	/**
	 * ���ݲ�ѯ������Ԫ����·��Map��ѯ���۶����ӱ�VO��ֻ����ָ���ֶ�����
	 * 
	 * @param columnMapping
	 *            Ԫ����·��Map���磺columnMapping.put("brandid",
	 *            "cinventoryvid.pk_brand.pk_brand");
	 * @param queryScheme
	 *            ��ѯ��������Ҫ��queryCondition
	 * @param fieldnames
	 *            ��Ҫ��ѯ���ֶΣ�ʹ����ƴװ
	 * @return SaleOrderBVO
	 * @throws BusinessException
	 */
	public SaleOrderBVO[] querySaleOrderBVOsByQueryScheme(
			Map<String, String> columnMapping,
			QueryCondition[] queryConditions, String[] fieldnames)
			throws BusinessException;

}
