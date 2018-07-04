package nc.impl.so.m38.migrate.action;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m38.migrate.constant.SQLCenter;
import nc.vo.pub.BusinessException;

/**
 * Ԥ����Ǩ����ɺ���Ҫ����Ӱ�쵽�����ε���
 * @author liylr
 *
 */
public class PreOrderDestBillUpdateAction{
	public void update(Map<String, String> oldNewTrantypeIdMap) throws BusinessException{
		SQLCenter center = new SQLCenter();
		List<String> list = center.getUpdateSqls(oldNewTrantypeIdMap);
		
		for(String sql : list){			
			DataAccessUtils dau = new DataAccessUtils();
			dau.update(sql);
		}
	}
}
