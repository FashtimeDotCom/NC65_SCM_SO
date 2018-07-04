package nc.bs.so.m4331.extend.backgroud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.lm.erpsbbjjhjk.ErpsbbjjhjkHVO;
import nc.vo.pub.BusinessException;

/**
 * <p>������Ҫʵ�ֹ��ܣ�</p>
 *
 * <li>����������</li>
 * <li></li>
 * <li></li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017��7��5������2:15:20
 */
public abstract class M4331ToSaleoutBgPlugin implements IBackgroundWorkPlugin {

	/* ���� Javadoc��
	 * @see nc.bs.pub.taskcenter.IBackgroundWorkPlugin#executeTask(nc.bs.pub.taskcenter.BgWorkingContext)
	 */
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		PreAlertObject retObj = new PreAlertObject();
		retObj.setReturnType(PreAlertReturnType.RETURNMESSAGE);
		retObj.setMsgTitle("ERP�豸������ϸ�������۳��ⵥ��̨������������");
		StringBuffer retinfo = new StringBuffer();
		Map<String, ArrayList<ErpsbbjjhjkHVO>> mapvos = getDestTableInfo();
		Map<String, List<String[]>> map_mess = genSaleouttBillInfo(mapvos);
		this.updateErpData(map_mess);
		if (map_mess != null && map_mess.size() > 0) {
			List<String[]> error = map_mess.get("error");
			List<String[]> success = map_mess.get("success");
			if (error != null && error.size() > 0) {
				for (int i = 0; i < error.size(); i++) {
					String str[] = error.get(i);
					retinfo.append(str[1]);
				}
			} 
			if (success != null && success.size() > 0 ) {
				for (int i = 0; i < success.size(); i++) {
					String str[] = success.get(i);
					retinfo.append(str[1]);
				}
			}
		} else {
			retinfo.append("���账������յ��ݣ�");
		}
		retObj.setReturnObj(retinfo.toString());
		return retObj;
	}

	/**
	 * ���±������յ�������״̬��������Ϣ
	 * ����״̬��hdef1 0-δ����1-����ɹ���2-����ʧ��
	 * ��������Ϣ��hdef2
	 * @param map_mess
	 * @throws DAOException 
	 */
	private void updateErpData(Map<String, List<String[]>> map_mess) throws DAOException {
		// TODO �Զ����ɵķ������
		String upstr = null;
		if (map_mess != null && map_mess.size() > 0) {
			List<String[]> error = map_mess.get("error");
			List<String[]> success = map_mess.get("success");
			if (error != null && error.size() > 0) {
				for (int i = 0; i < error.size(); i++) {
					String str[] = error.get(i);
					upstr = "update MSG_ERPSBBJJHJK set hdef1='2',hdef2='����ʧ��' where ysd = '" + str[0] +"'";
					getBD().executeUpdate(upstr);
				}
			}
			if (success != null && success.size() > 0 ) {
				for (int i = 0; i < success.size(); i++) {
					String str[] = success.get(i);
					upstr = "update MSG_ERPSBBJJHJK set hdef1='1',hdef2='����ɹ�' where ysd = '" + str[0] +"'";
					getBD().executeUpdate(upstr);
				}
			}
		}
	}

	/**
	 * �������۳��ⵥ
	 * @param Map<String, ArrayList<ErpsbbjjhjkHVO>> vos
	 * @return Map<String, List<String[]>> ���ݴ�����Ϣ
	 * @throws BusinessException 
	 * @throws DAOException 
	 */
	public abstract Map<String, List<String[]>> genSaleouttBillInfo(Map<String, ArrayList<ErpsbbjjhjkHVO>> mapvos) throws DAOException, BusinessException;

	/**
	 * ��ȡĿ������,��ȡδ��������յ���Ϣ
	 * @return SuperVO[]
	 * @throws DAOException 
	 */
	private Map<String, ArrayList<ErpsbbjjhjkHVO>> getDestTableInfo() throws DAOException {
		// TODO �Զ����ɵķ������
		/**
		 * ��ȡδ��������յ��ţ���Ϊ�������۳��ⵥ�����ݣ�ÿ�����յ��Ŷ�Ӧһ�����۳��ⵥ
		 */
		StringBuffer recenosql = new StringBuffer();
		recenosql.append("select distinct ysd from msg_erpsbbjjhjk");
		recenosql.append(" where dr = 0 and hdef1 = 0 ");
		ArrayList<String> receno = (ArrayList<String>) getBD().executeQuery(recenosql.toString(), new ResultSetProcessor() {
			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				// TODO �Զ����ɵķ������
				ArrayList<String>  list = new ArrayList<String>();
				while (rs.next()) {
					list.add(rs.getString(1));
				}
				return list;
			}
		});
		/**
		 * ����δ��������յ����ţ���ȡ���յ���Ϣ
		 */
		Map<String,ArrayList<ErpsbbjjhjkHVO>> mapvos = new HashMap<String,ArrayList<ErpsbbjjhjkHVO>>();
		for (int i=0; i<receno.size();i++) {
			StringBuffer querysql = new StringBuffer();
			querysql.append( "select * from msg_erpsbbjjhjk ");
			querysql.append(" where dr = 0 and hdef1 = 0 and ysd='");
			querysql.append( receno.get(i).toString());
			querysql.append("'");
			ArrayList<ErpsbbjjhjkHVO> vos = (ArrayList<ErpsbbjjhjkHVO>) getBD().executeQuery(querysql.toString(), new BeanListProcessor(ErpsbbjjhjkHVO.class));
			mapvos.put( receno.get(i).toString(), vos);
		}
		return mapvos;
	}
	BaseDAO bd = null;
	public BaseDAO getBD() {
		if (bd == null) {
			bd = new BaseDAO();
		}
		return bd;
		
	}
}
