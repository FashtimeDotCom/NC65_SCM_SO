/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��4��2�� ����6:52:59 
 * @version: V6.5   
 */
package nc.bs.so.m30.revise.rule;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��4��2�� ����6:52:59
 */
public class RewriteVbillcodeFor30R implements IRule<SaleOrderHistoryVO> {

	@Override
	public void process(SaleOrderHistoryVO[] vos) {
		// TODO �Զ����ɵķ������
		// �յĻ�������
		if (vos == null || vos.length == 0) {
			return;
		}
		// һ��ֻ�ܱ���һ�������һ��
		String saleOrderID = vos[0].getParentVO().getCsaleorderid();
		String vbillcode = vos[0].getParentVO().getVbillcode();
		VOUpdate<SaleOrderHVO> update = new VOUpdate<SaleOrderHVO>();
		VOQuery<SaleOrderHVO> query = new VOQuery(SaleOrderHVO.class);
		SaleOrderHVO[] hvo = query.query(new String[] { saleOrderID });
		if (hvo.length == 0) {
			ExceptionUtils.wrappBusinessException("��������۶����Ƿ���ɾ��");
		}
		SaleOrderHVO newHvo = new SaleOrderHVO();
		newHvo.setCsaleorderid(saleOrderID);
		newHvo.setVbillcode(vbillcode);
		// ����ʷ�Ĵ���Э��ż�¼��def17
		newHvo.setVdef17(hvo[0].getVbillcode());
		// �������۶���������
		update.update(new SaleOrderHVO[] { newHvo }, new String[] { "vbillcode","vdef17" });
	}

}
