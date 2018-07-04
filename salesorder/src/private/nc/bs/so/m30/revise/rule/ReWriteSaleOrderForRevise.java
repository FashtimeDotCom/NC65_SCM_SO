/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��6��6�� ����10:26:52 
 * @version: V6.5   
 */
package nc.bs.so.m30.revise.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��6��6�� ����10:26:52
 */
public class ReWriteSaleOrderForRevise implements IRule<SaleOrderVO> {
	// ���۶����޶�ɾ�������Ӷ����۶����Ļ�д
	@Override
	public void process(SaleOrderVO[] vos) {
		Map<String, String> data = new HashMap<String, String>();
		for (SaleOrderVO saleOrderVO : vos) {
			SaleOrderHistoryVO his = (SaleOrderHistoryVO) saleOrderVO;
			// ������۶���������
			String csaleorderid = his.getParentVO().getCsaleorderid();
			// ��ѯ��һ���汾�����۶����޶���¼������ȡ�䵥�ݺ�
			Integer version = saleOrderVO.getParentVO().getIversion() - 1;
			if (version < 0) {
				ExceptionUtils
						.wrappBusinessException("��ǰ���ݴ��󣬵����޷����ϸ��汾�����۶����Ż�д�����۶������޷�ɾ��");
			}
			String SaleOrderNo = getLastSaleOrderNo(csaleorderid, version);
			// ˵��û���ҵ���һ���汾
			if (SaleOrderNo == null) {
				ExceptionUtils.wrappBusinessException("��һ���汾�����۶����޶��� ���۶�����Ϊ��");
			}
			data.put(csaleorderid, SaleOrderNo);
		}
		updateSaleOrderBillNo(data);
	}

	/**
	 * @Title: updateSaleOrderBillNo
	 * @Description: �������۶����ĵ��ݺţ�Ҳ���ǻع�����һ���汾�ĵ��ݺ�
	 * @param data
	 * @return: void
	 */
	private void updateSaleOrderBillNo(Map<String, String> data) {
		// TODO �Զ����ɵķ������
		List<SaleOrderHVO> vos = new ArrayList<SaleOrderHVO>();
		for (Map.Entry<String, String> entry : data.entrySet()) {
			SaleOrderHVO vo = new SaleOrderHVO();

			vo.setPrimaryKey(entry.getKey());
			vo.setVbillcode(entry.getValue());
			vos.add(vo);
		}
		VOUpdate<SaleOrderHVO> update = new VOUpdate<SaleOrderHVO>();
		update.update(vos.toArray(new SaleOrderHVO[vos.size()]),
				new String[] { "vbillcode" });

	}

	private String getLastSaleOrderNo(String csaleorderid, Integer version) {
		// TODO �Զ����ɵķ������
		SqlBuilder sql = new SqlBuilder();
		sql.append("select vbillcode from so_orderhistory where csaleorderid ='");
		sql.append(csaleorderid);
		sql.append("' and iversion ='");
		sql.append(version);
		sql.append("' and dr<>1");
		DataAccessUtils dao = new DataAccessUtils();
		IRowSet set = dao.query(sql.toString());
		if (set.size() == 0) {
			return null;
		}
		String[] ids = set.toOneDimensionStringArray();
		return ids[0];
	}

}
