package nc.itf.so.custmatrel;

import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * @author quyt
 * 
 */
public interface ICustMatRelQueryResult {
	/**
	 * ����ѯ������ֵ����ͷ��Ӧ�ֶ�
	 * 
	 * @return
	 */
	String custMatRelQueryResult(String[] queryConditionValue);
}
