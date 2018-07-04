package nc.impl.so.m30.revise;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m30.revise.action.ReviseSaveSaleOrderAction;
import nc.itf.so.m30.revise.IM30ReviseMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

public class M30ReviseMaintainImpl implements IM30ReviseMaintain {

	@Override
	public SaleOrderVO[] reviseSave(SaleOrderVO[] bills)
			throws BusinessException {
		SaleOrderVO[] ret = null;
		// SaleOrderVO[] bills = new SaleOrderVO[] {
		// bill
		// };
		// try {
		// ReviseSaveSaleOrderAction action = new ReviseSaveSaleOrderAction();
		// ret = action.reviseSave(bills);
		// }
		// catch (Exception ex) {
		// ExceptionUtils.marsh(ex);
		// }
		return ret;
	}

	/**
	 * add by wangshu6 for ���۶����޶�֧�������� ����������������޶����� �����vo�������۶����޶���ʷvo
	 */
	@Override
	public SaleOrderHistoryVO[] reviseOrderHisVOSave(SaleOrderHistoryVO[] bills)
			throws BusinessException {
		SaleOrderHistoryVO[] ret = null;
		for (SaleOrderHistoryVO hisVO : bills) {
			// �޶�����һ�Σ��������°汾���޶�vo
			hisVO.getParentVO().setCorderhistoryid(null);
			hisVO.getParentVO().setStatus(VOStatus.NEW);
			// ����ܲ����agdef1Ϊ��ť�Ĳ�����ť�������޸Ļ����޶���
			hisVO.getParentVO().setAttributeValue("agdef1", null);
		}
		try {
			ReviseSaveSaleOrderAction action = new ReviseSaveSaleOrderAction();
			ret = action.reviseSave(bills);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
		return ret;
	}

	// end

	@Override
	public SaleOrderVO[] queryReviseHistory(String hid)
			throws BusinessException {
		SaleOrderHistoryVO[] bills = null;
		SqlBuilder sql = new SqlBuilder();
		sql.append("select corderhistoryid ");
		sql.append("from so_orderhistory where csaleorderid = '");
		sql.append(hid);
		sql.append("'");
		sql.append(" and dr = 0");
		sql.append(" order by iversion desc");

		DataAccessUtils utils = new DataAccessUtils();
		IRowSet set = utils.query(sql.toString());
		if (set.size() == 0) {
			return new SaleOrderHistoryVO[0];
		}

		BillQuery<SaleOrderHistoryVO> query = new BillQuery<SaleOrderHistoryVO>(
				SaleOrderHistoryVO.class);
		bills = query.query(set.toOneDimensionStringArray());
		return bills;
	}

}
