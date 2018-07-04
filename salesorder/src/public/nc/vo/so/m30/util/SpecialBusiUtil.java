package nc.vo.so.m30.util;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pubitf.org.IDeptPubService;
import nc.pubitf.rbac.IUserPubService;
import nc.pubitf.uapbd.IPsndocPubService;
import nc.vo.org.DeptVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

/**
 * ��������ҵ�񹤾���
 *
 */
public class SpecialBusiUtil {

	public boolean isTheDept(String[] deptids){
		if(deptids != null && deptids.length > 0){
			IDeptPubService deptSer = NCLocator.getInstance().lookup(IDeptPubService.class);
			DeptVO[] deptvos = null;
			try {
				deptvos = deptSer.queryDeptVOsByPKS(deptids);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
			}
			
			//���ݲ��ű����ж��Ƿ��� �Ǹֲ�Ʒó�ײ�
			if(deptvos != null && deptvos.length > 0){
				for(DeptVO deptvo : deptvos){
					String code = deptvo.getCode();
					if("00067420".equals(code)){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	   * �жϵ�ǰ��¼���Ƿ��� �Ǹֲ�Ʒó�ײ�  
	   */
	public boolean isTheDept(){

		//1:��ȡ��ǰ��½�û�
		String pk_user = AppContext.getInstance().getPkUser();
		
		try {
			//2:�����û���ȡ��Ա
			IUserPubService userSer = NCLocator.getInstance().lookup(IUserPubService.class);
			String psndocid = userSer.queryPsndocByUserid(pk_user);
			
			if(psndocid != null){
				
				//3:������Ա��ȡ����
				IPsndocPubService psndocSer = NCLocator.getInstance().lookup(IPsndocPubService.class);
				Map<String, List<String>> psndocInfoMap = psndocSer.queryDeptIDByPsndocIDs(new String[]{psndocid});
			
				if(psndocInfoMap != null && psndocInfoMap.get(psndocid) != null && psndocInfoMap.get(psndocid).size() > 0){
					//����ids
					List<String> deptids = psndocInfoMap.get(psndocid);
					
					if(deptids != null && deptids.size() > 0){
						IDeptPubService deptSer = NCLocator.getInstance().lookup(IDeptPubService.class);
						DeptVO[] deptvos = deptSer.queryDeptVOsByPKS(deptids.toArray(new String[]{}));
						
						//���ݲ��ű����ж��Ƿ��� �Ǹֲ�Ʒó�ײ�
						if(deptvos != null && deptvos.length > 0){
							for(DeptVO deptvo : deptvos){
								String code = deptvo.getCode();
								if("00067420".equals(code)){
									return true;
								}
							}
							
						}
					}
				}
			}
		} catch (BusinessException e1) {
			Logger.error(e1.getMessage(), e1);
		}
		
		
		return false;
	}
	
	  /**
	   * 
	   * TODO ��ѯ���۶����Ƿ��������ν��ں�ͬ
	   */
	public boolean hasLowerBill(String saleorderId){
		
		if(saleorderId != null){
			String sql = " select pk_contract from it_contract_b "
					+ " where csrcid = '" + saleorderId + "' and dr = 0 ";
			
			List<?> queryDatas = null;
			IUAPQueryBS uapquery = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			try {
	    		queryDatas = (List<?>) uapquery.executeQuery(sql, new ArrayListProcessor());
	    		
	    		if(queryDatas != null && queryDatas.size() > 0){
	    			return true;
	    		}
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
			}
		}
		return false;
		  
	}
	
	
}
