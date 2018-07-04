package nc.impl.pub.ace;

import nc.bs.so.billinformation.ace.bp.AceBillinformationInsertBP;
import nc.bs.so.billinformation.ace.bp.AceBillinformationUpdateBP;
import nc.bs.so.billinformation.ace.bp.AceBillinformationDeleteBP;
import nc.bs.so.billinformation.ace.bp.AceBillinformationSendApproveBP;
import nc.bs.so.billinformation.ace.bp.AceBillinformationUnSendApproveBP;
import nc.bs.so.billinformation.ace.bp.AceBillinformationApproveBP;
import nc.bs.so.billinformation.ace.bp.AceBillinformationUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceBillinformationPubServiceImpl {
	// ����
	public AggBillInforMationVO[] pubinsertBills(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBillInforMationVO> transferTool = new BillTransferTool<AggBillInforMationVO>(
					clientFullVOs);
			// ����BP
			AceBillinformationInsertBP action = new AceBillinformationInsertBP();
			AggBillInforMationVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceBillinformationDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBillInforMationVO[] pubupdateBills(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBillInforMationVO> transferTool = new BillTransferTool<AggBillInforMationVO>(
					clientFullVOs);
			AceBillinformationUpdateBP bp = new AceBillinformationUpdateBP();
			AggBillInforMationVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBillInforMationVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBillInforMationVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBillInforMationVO> query = new BillLazyQuery<AggBillInforMationVO>(
					AggBillInforMationVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public AggBillInforMationVO[] pubsendapprovebills(
			AggBillInforMationVO[] clientFullVOs, AggBillInforMationVO[] originBills)
			throws BusinessException {
		AceBillinformationSendApproveBP bp = new AceBillinformationSendApproveBP();
		AggBillInforMationVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggBillInforMationVO[] pubunsendapprovebills(
			AggBillInforMationVO[] clientFullVOs, AggBillInforMationVO[] originBills)
			throws BusinessException {
		AceBillinformationUnSendApproveBP bp = new AceBillinformationUnSendApproveBP();
		AggBillInforMationVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggBillInforMationVO[] pubapprovebills(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBillinformationApproveBP bp = new AceBillinformationApproveBP();
		AggBillInforMationVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggBillInforMationVO[] pubunapprovebills(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBillinformationUnApproveBP bp = new AceBillinformationUnApproveBP();
		AggBillInforMationVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}