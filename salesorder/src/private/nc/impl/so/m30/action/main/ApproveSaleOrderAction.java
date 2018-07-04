package nc.impl.so.m30.action.main;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.action.N_30_APPROVE;
import nc.bs.so.m30.maintain.rule.delete.RewritePromotePriceDeleteRule;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.approve.ApproveStateRule;
import nc.bs.so.m30.rule.approve.BusiLog;
import nc.bs.so.m30.rule.approve.CheckApprovableRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetAfterApproveRule;
import nc.bs.so.m30.rule.me.SaleOrderVOApproveAfterRule;
import nc.bs.so.m30.rule.msg.SendToGfAndTable;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * ���۶�����������
 * 
 * @since 6.0
 * @version 2012-2-7 ����01:09:56
 * @author fengjb
 * @modify ����ܲ wangzym ����������Ļ����м��Ͱ��ֹɷ� 2017-04-17
 */
public class ApproveSaleOrderAction {

	/**
	 * ��������
	 * 
	 * @param bills
	 * @param script
	 * @return ����������
	 */
	public Object approve(SaleOrderVO[] bills, N_30_APPROVE script) {

		Object ret = null;
		try {

			// ע���
			AroundProcesser<SaleOrderVO> processer = new AroundProcesser<SaleOrderVO>(
					Action30PlugInPoint.ApproveAction);

			TimeLog.logStart();
			this.addBeforeRule(processer);
			processer.before(bills);
			TimeLog.info("��������ǰ���������"); /* -=notranslate=- */

			/************* �����Ϊ�������������������ܽ����޸� *********************/
			ret = script.procActionFlow(script.getPfParameterVO());
			/************** ���ؽ�� *************************************************/

			// ת������ƽ̨������״̬��ҵ�񵥾�״̬�����־û�
			SaleOrderVO[] newbills = script.getVos();
			this.updateNewBillStatus(newbills);
			TimeLog.logStart();

			Integer newbillstatus = newbills[0].getParentVO().getFstatusflag();
			String vtranstype = newbills[0].getParentVO().getVtrantypecode();
			// �������������xxxx����Ҫ���ɷ����ݿ����Ҫ�浽�м��

			this.addAfterRule(processer, newbillstatus);
			//add by wangzy 2017-04-17
			//��Ҫ���ɷݵĽ�������
			//ȥ���������Ƶ�������������ť
		/*	if ("30-Cxx-22".equals(vtranstype) ) {
				//���ɷ����ݿ�
				processer.addAfterRule(new SendToGfAndTable());
			}*/
			//end
			processer.after(newbills);
			TimeLog.info("������������������"); /* -=notranslate=- */

			// ����ͨ��ʱ������ƽ̨���صĲ���Ϊnull,��ʱ��Ҫ�������µ����ݣ����������(������)ֻ�ܷ���null,���������������
			if (null == ret) {
				ret = newbills;
			}
		} catch (Exception ex) {
			ExceptionUtils.wrappException(ex);
		}
		return ret;
	}

	private void updateNewBillStatus(SaleOrderVO[] newbills) {

		SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
		SaleOrderHVO[] updateheads = new SaleOrderHVO[newbills.length];
		List<SaleOrderBVO> listbody = new ArrayList<SaleOrderBVO>();
		int i = 0;
		for (SaleOrderVO ordervo : newbills) {
			statuschgrule.changePfToBillStatus(ordervo);
			updateheads[i++] = ordervo.getParentVO();
			for (SaleOrderBVO bvo : ordervo.getChildrenVO()) {
				listbody.add(bvo);
			}
		}
		String[] headupname = new String[] { SaleOrderHVO.FSTATUSFLAG };
		VOUpdate<SaleOrderHVO> headupsrv = new VOUpdate<SaleOrderHVO>();
		headupsrv.update(updateheads, headupname);

		String[] bodyupname = new String[] { SaleOrderBVO.FROWSTATUS };
		VOUpdate<SaleOrderBVO> bodyupsrv = new VOUpdate<SaleOrderBVO>();
		SaleOrderBVO[] updatebodys = listbody.toArray(new SaleOrderBVO[listbody
				.size()]);
		bodyupsrv.update(updatebodys, bodyupname);
	}

	private void addAfterRule(AroundProcesser<SaleOrderVO> processer,
			Integer newbillstatus) {

		IRule<SaleOrderVO> rule = null;

		// ���ü��
		if (SysInitGroupQuery.isCREDITEnabled()) {
			rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Approve);
			processer.addAfterRule(rule);
		}
		boolean icEnable = SysInitGroupQuery.isICEnabled();
		if (icEnable) {
			// ������
			rule = new SaleOrderVOATPAfterRule();
			processer.addAfterRule(rule);
		}

		// ��ܽ���ã���������Ʒ�Ҹ�
		rule = new ArsubOffsetAfterApproveRule();
		processer.addAfterRule(rule);

		if (BillStatus.AUDIT.equalsValue(newbillstatus)) {
			// ���������״̬�Ĵ���
			rule = new ApproveStateRule();
			processer.addAfterRule(rule);
		}

		if (BillStatus.NOPASS.equalsValue(newbillstatus)) {
			// ��д�����۸�� zhangby5 for �㰲��������
			if (SysInitGroupQuery.isPRICEEnabled()) {
				rule = new RewritePromotePriceDeleteRule();
				processer.addAfterRule(rule);
			}
		}
		// ���������ɶ������������
		rule = new SaleOrderVOApproveAfterRule();
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
		// ��鵥���Ƿ��������
		IRule<SaleOrderVO> rule = new CheckApprovableRule();
		processer.addBeforeRule(rule);
		// ���ü��
		if (SysInitGroupQuery.isCREDITEnabled()) {
			rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Approve);
			processer.addBeforeRule(rule);
		}
		boolean icEnable = SysInitGroupQuery.isICEnabled();
		if (icEnable) {
			// ������
			rule = new SaleOrderVOATPBeforeRule();
			processer.addBeforeRule(rule);
		}

		rule = new BusiLog();
		processer.addBeforeRule(rule);
	}
}
