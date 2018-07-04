package nc.itf.so.salequotation;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

/**
 * 
 * @author chenyyb
 * 
 */
public interface ISalequotationQry {

  AggSalequotationHVO[] queryAllInvalidateBill(String pk_org) throws Exception;

  SalequotationBVO[] queryBodyByHeadPk(String headPk) throws Exception;

  AggSalequotationHVO queryByPk(String pk) throws Exception;

  AggSalequotationHVO[] queryByPks(String[] pks) throws Exception;

  /**
   * ���۶������ձ��۵����ݲ�ѯ������������
   * 
   * @param querySheme ��ѯ����
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] queryByQuerySchemeFor30(IQueryScheme querySheme)
      throws Exception;

  /**
   * ���ݲ�ѯ������������
   * 
   * @param querySheme ��ѯ����
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] queryByQuerySchemeForZ3(IQueryScheme querySheme)
      throws Exception;

  /**
   * ����sql��������
   * 
   * @param sql
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] queryVOsBySql(String sql) throws Exception;
}
