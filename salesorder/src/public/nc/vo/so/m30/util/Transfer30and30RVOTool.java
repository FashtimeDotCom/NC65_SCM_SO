package nc.vo.so.m30.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @description ���۶����޶�vo ���۶���voת������
 * @since 6.36
 * @version 2014-12-26 ����11:29:01
 * @author wangshu6
 */
public class Transfer30and30RVOTool {

	/**
	 * �����۶�������ת�������۶����޶�VO
	 * 
	 * @param vos
	 *            ���۶���
	 * @return ���۶����޶�vos
	 */
	public SaleOrderHistoryVO[] transfer30TOOrderhisVO(SaleOrderVO[] vos) {
		List<SaleOrderHistoryVO> list = new ArrayList<SaleOrderHistoryVO>();
		for (SaleOrderVO vo : vos) {

			SaleOrderHistoryVO orderVO = transfer30TOOrderhisVO(vo);
			list.add(orderVO);
		}
		SaleOrderHistoryVO[] arrs = list.toArray(new SaleOrderHistoryVO[0]);
		return arrs;
	}

	/**
	 * �����۶���ת�������۶����޶�VO
	 * 
	 * @param vo
	 * @return ���۶����޶�vos
	 */
	public SaleOrderHistoryVO transfer30TOOrderhisVO(SaleOrderVO vo) {
		SaleOrderBVO[] bvo = vo.getChildrenVO();
		SaleOrderHVO hvo = vo.getParentVO();

		SaleOrderHistoryBVO[] orderbVO = transferVOS(bvo,
				SaleOrderHistoryBVO.class);
		SaleOrderHistoryHVO orderHisVO = transferVOS(hvo,
				SaleOrderHistoryHVO.class);

		SaleOrderHistoryVO orderVO = new SaleOrderHistoryVO();
		orderVO.setChildrenVO(orderbVO);
		orderVO.setParentVO(orderHisVO);
		return orderVO;
	}

	/**
	 * �����۶����޶�VO�������۶���VO
	 * 
	 * @param vos
	 * @return
	 */
	public SaleOrderVO[] transferOrderhisBVOTO30VO(SaleOrderHistoryVO[] vos) {
		List<SaleOrderVO> list = new ArrayList<SaleOrderVO>();
		for (SaleOrderHistoryVO vo : vos) {
			SaleOrderHistoryBVO[] bvo = vo.getChildrenVO();
			SaleOrderHistoryHVO hvo = vo.getParentVO();
			SaleOrderBVO[] orderbVO = transferVOS(bvo, SaleOrderBVO.class);
			SaleOrderHVO orderHVO = transferVOS(hvo, SaleOrderHVO.class);
			SaleOrderVO saleorderVO = new SaleOrderVO();
			saleorderVO.setChildrenVO(orderbVO);
			saleorderVO.setParentVO(orderHVO);
			list.add(saleorderVO);
		}
		SaleOrderVO[] arrs = list.toArray(new SaleOrderVO[0]);

		return arrs;
	}

	/**
	 * ����ת��vo
	 * 
	 * @param vos
	 *            ��ת��vo
	 * @param descClazz
	 *            Ŀ������
	 * @return ת�����
	 */
	@SuppressWarnings("unchecked")
	public <T extends SuperVO, E extends SuperVO> E[] transferVOS(T[] vos,
			Class<? extends SuperVO> descClazz) {

		if (ArrayUtil.isEmpty(vos)) {
			return null;
		}

		E[] result = (E[]) Constructor.declareArray(descClazz, vos.length);

		for (int i = 0; i < vos.length; i++) {
			result[i] = (E) Constructor.construct(descClazz);
			tranferVO(vos[i], result[i]);
		}
		return result;
	}

	public <T extends SuperVO, E extends SuperVO> E transferVOS(T vos,
			Class<? extends SuperVO> descClazz) {

		@SuppressWarnings("unchecked")
		E result = (E) Constructor.construct(descClazz);
		tranferVO(vos, result);
		return result;
	}

	/**
	 * ת�����Ĵ��룬���Եĸ���
	 * 
	 * @param ��ת��vo
	 * @param Ŀ��vo
	 */
	private void tranferVO(SuperVO originVO, SuperVO newVO) {
		String attributes[] = newVO.getAttributeNames();
		for (String att : attributes) {
			Object value = originVO.getAttributeValue(att);
			newVO.setAttributeValue(att, value);
		}
		// ���۶���ת���۶����޶�ʱ�� ���޶��������ͱ��븳ֵ
		this.doAfterSaleOrderVOToOrderHistoryVO(newVO, originVO);
	}

	private void doAfterSaleOrderVOToOrderHistoryVO(SuperVO newVO,
			SuperVO originVO) {
		if (newVO.getClass().equals(SaleOrderHistoryHVO.class)) {
			newVO.setAttributeValue("vhistrantypecode", "30R");
			newVO.setAttributeValue("chistrantypeid", "30R");
			Integer iVersion = (Integer) newVO.getAttributeValue("iversion");
			// ����ܲ 2018-06-23�޸ĸ�ֵ��corderhistoryid������ֵ
			String corderhistoryid = queryCorderHistoryidWithIdAndVersion(
					originVO.getPrimaryKey(), iVersion);
			newVO.setAttributeValue("corderhistoryid", corderhistoryid);
		} else {
			newVO.setAttributeValue("corderhistorybid",
					originVO.getPrimaryKey());
			newVO.setAttributeValue("corderhistoryid",
					originVO.getAttributeValue("csaleorderid"));
		}
	}

	/**
	 * @Title: �����ݿ��ѯ���������۶����޶�����ID
	 * @Description: TODO
	 * @param primaryKey
	 *            ���۶�������ID
	 * @param iVersion
	 *            ���۶����޶��汾
	 * @return ���۶����޶���������
	 */
	private String queryCorderHistoryidWithIdAndVersion(String primaryKey,
			Integer iVersion) {
		// select corderhistoryid from so_orderhistory where csaleorderid =''
		// and iversion ='' and dr<> 1
		SqlBuilder sql = new SqlBuilder();

		sql.append(" select corderhistoryid from so_orderhistory where ");
		sql.append(" csaleorderid ", primaryKey);
		sql.append(" and ");
		sql.append(" iversion ", iVersion);
		sql.append(" and dr<>1");
		DataAccessUtils utils = new DataAccessUtils();
		IRowSet rs = utils.query(sql.toString());
		String[] oneDimensionStringArray = rs.toOneDimensionStringArray();
		return oneDimensionStringArray.length == 0 ? null
				: oneDimensionStringArray[0];
	}
}
