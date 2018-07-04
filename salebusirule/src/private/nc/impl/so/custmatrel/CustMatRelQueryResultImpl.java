package nc.impl.so.custmatrel;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.so.custmatrel.ICustMatRelQueryResult;
import nc.vo.pubapp.pattern.data.IRowSet;

/**
 *���ͻ����Ϲ�ϵ����ڵ��ѯ����ֵ������ͷ��Ӧ�ֶ� 
 * @author quyt
 * 
 */
public class CustMatRelQueryResultImpl implements ICustMatRelQueryResult {
	@Override
	public String custMatRelQueryResult(String[] queryConditionValue) {
		DataAccessUtils utils = new DataAccessUtils();
		IRowSet results = utils.query(queryConditionValue[0].toString());
		return results.toOneDimensionStringArray()[0];
	}
}
