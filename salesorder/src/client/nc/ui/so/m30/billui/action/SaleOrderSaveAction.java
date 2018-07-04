/**
 *
 */
package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.so.m30.ISaleOrgPubService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶��������߼�
 * 
 * @since 6.0
 * @version 2011-12-28 ����10:26:42
 * @author fengjb
 */
public class SaleOrderSaveAction extends SaveScriptAction {

	private static final long serialVersionUID = -3977967248003982108L;

	private RefreshSingleAction refreshAction;

	/**
	 * ���췽��
	 */
	public SaleOrderSaveAction() {
		super();
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		SaleOrderVO saleorder = (SaleOrderVO) this.editor.getValue();
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		CardKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
		this.checkOverPurchase(saleorder);

		if (this.getModel().getUiState() == UIState.EDIT) {
			int index = this.getModel().findBusinessData(saleorder);
			if (index == -1) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0019")/*
														 * @res
														 * "�޸ı���ʱ����ȡǰ̨����VO����"
														 */);
			}
			// �����տ��޶��С��ʵ��Ԥ�տ���
			this.checkGathering(saleorder);
			// ������ͬ��״̬����Ϊ����̬
			this.reSetBillStatusForNoPass(saleorder);
		}
		// ǰ̨���ӱ�ͷID����ֹ���������ʱ�ظ����浥�ݵ����
		if (this.getModel().getUiState() == UIState.ADD) {
			String hID = keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
			if (PubAppTool.isNull(hID)) {
				ISaleOrgPubService service = NCLocator.getInstance().lookup(
						ISaleOrgPubService.class);
				String[] ids = service.getOIDArray(1);
				keyValue.setHeadValue(SaleOrderHVO.CSALEORDERID, ids[0]);
			}
		}
		// ��Ʒ�Ҹ����Ͳ���Ϊ��
		this.checkCarsubtypeid(saleorder, keyValue);
		super.doAction(e);
		this.doAfterAction();
	}

	/**
	 * @param saleorder
	 *            ����ǰУ��
	 */
	private void checkOverPurchase(SaleOrderVO saleorder) {
		// TODO �Զ����ɵķ������
		// TODO ������ĳ��ɺ��ٲɸ�����ʾ
		// ��ʾ��Ϣ
		String msg = "��";
		SaleOrderBVO[] bvos = saleorder.getChildrenVO();
		for (SaleOrderBVO saleOrderBVO : bvos) {
			String rowNo = saleOrderBVO.getCrowno();
			UFDouble nastNum = saleOrderBVO.getNastnum();
			if(saleOrderBVO.getAttributeValue("plan_num")==null){
				ExceptionUtils.wrappBusinessException("����������Ϣҳǩ ���ƻ�����������Ϊ��");
			}
			Integer planNum = (int)saleOrderBVO.getAttributeValue("plan_num");
			int nastnum = nastNum.intValue();
			if (planNum!=null&&nastnum != planNum.intValue()) {
				msg += "[" + rowNo + "] ";
			}
		}
		if(!msg.equals("��")){
			
		msg += "�� ���ڳ��ɻ��ٲɣ�";
		MessageDialog.showWarningDlg(WorkbenchEnvironment.getInstance()
	              .getWorkbench().getParent(), "��ʾ", msg);
		}
	}

	/**
	 * ������¼�����
	 */
	private void doAfterAction() {
		// ������mix("�����տ���"||"��˰�ϼ�")����"ʵ���տ���",�����"�����տ���"
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		CardKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
		UFDouble thisreceivemny = keyValue
				.getHeadUFDoubleValue(SaleOrderHVO.NTHISRECEIVEMNY);
		UFDouble receivedmny = keyValue
				.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
		UFDouble totalorigmny = keyValue
				.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);

		UFDouble receivedmny_new = MathTool.add(thisreceivemny, receivedmny);
		// �����տ���
		if (MathTool.absCompareTo(receivedmny_new, totalorigmny) > 0) {
			receivedmny_new = totalorigmny;
		}

		keyValue.setHeadValue(SaleOrderHVO.NRECEIVEDMNY, receivedmny_new);
		keyValue.setHeadValue(SaleOrderHVO.NTHISRECEIVEMNY, null);
		FeatureSelectUtil.clearAllRowValue(keyValue, SOConstant.AGGFFILEVO);
	}

	@Override
	public void doBeforAction() {
		super.doBeforAction();
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		if (null != billform) {
			CardKeyValue keyValue = new CardKeyValue(
					billform.getBillCardPanel());
			PfUserObject pfUserObj = this.getFlowContext().getUserObj();
			pfUserObj = pfUserObj == null ? new PfUserObject() : pfUserObj;
			SaleOrderUserObject userobj = (SaleOrderUserObject) (pfUserObj
					.getUserObject() == null ? new SaleOrderUserObject()
					: pfUserObj.getUserObject());
			// ���ó��
			OffsetTempVO tempvo = billform.getTempvo();
			userobj.setOffsetTempVO(tempvo);
			// ��������
			SoBalanceVO sobalancevo = billform.getSobalancevo();
			userobj.setSoBalanceVO(sobalancevo);
			// �����տ���
			UFDouble thisGatheringMny = billform.getThisGatheringMny();
			userobj.setThisGatheringMny(thisGatheringMny);
			Map<String, AggFFileVO> aggffilevomap = FeatureSelectUtil
					.getAllRowAggFFileVO(keyValue);
			if (aggffilevomap.size() > 0) {
				userobj.setAggffilevomap(aggffilevomap);
			}
			userobj.setIsclientsave(true);
			pfUserObj.setUserObject(userobj);
			this.getFlowContext().setUserObj(pfUserObj);
		}
	}

	private void checkGathering(SaleOrderVO saleorder) {
		SaleOrderHVO hvo = saleorder.getParentVO();
		if (null == hvo) {
			return;
		}
		// �տ��޶����Ԥ��
		UFBoolean bpreceiveflag = hvo.getBpreceiveflag();
		// �����տ��޶�
		UFDouble npreceivequota = hvo.getNpreceivequota();
		// ʵ��Ԥ�տ���
		UFDouble npreceivemny = hvo.getNpreceivemny();

		if (bpreceiveflag.booleanValue()
				&& MathTool.compareTo(npreceivequota, npreceivemny) < 0) {
			StringBuilder errMsg = new StringBuilder();
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006011_0", "04006011-0283")/* ���ݺţ� */);
			errMsg.append(hvo.getVbillcode());
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006011_0", "04006011-0468")/*
															 * �����տ��޶�С��ʵ��Ԥ�տ���,
															 * �����µ�����˰�ϼƣ�
															 */);
			ExceptionUtils.wrappBusinessException(errMsg.toString());
		}
	}

	private void checkCarsubtypeid(SaleOrderVO saleorder, CardKeyValue keyValue) {
		SaleOrderHVO hvo = saleorder.getParentVO();
		if (null == hvo) {
			return;
		}
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		String tranTypeCode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		String pk_group = AppContext.getInstance().getPkGroup();
		SaleOrderClientContext cache = billform.getM30ClientContext();
		M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
		if (m30transvo != null) {
			if (m30transvo.getBlrgcashflag().booleanValue()) {
				// ��Ʒ�Ҹ�����
				String carsubtypeid = hvo.getCarsubtypeid();
				if (PubAppTool.isNull(carsubtypeid)) {
					ExceptionUtils.wrappBusinessException(NCLangRes
							.getInstance().getStrByID("4006011_0",
									"04006011-0506")/* ��Ʒ�Ҹ����Ͳ�����Ϊ�ա� */);
				}
			}
		}
	}

	/**
	 * 
	 * @return RefreshSingleAction
	 */
	public RefreshSingleAction getreFreshAction() {
		return this.refreshAction;
	}

	/**
	 * 
	 * @param refreshAction1
	 */
	public void setRefreshAction(RefreshSingleAction refreshAction1) {
		this.refreshAction = refreshAction1;
	}

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
				getFlowContext());
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		for (Object vo : vos) {
			SaleOrderVO saleordervo = (SaleOrderVO) vo;
			SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
			if (bvos == null || bvos.length == 0) {

				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0020")/* @res "��������Ϊ�գ��������档" */);
			}
		}
		return vos;
	}

	private void reSetBillStatusForNoPass(SaleOrderVO vo) {
		if (vo.getParentVO().getFstatusflag().intValue() == BillStatus.NOPASS
				.getIntValue()) {
			vo.getParentVO().setFstatusflag(BillStatus.FREE.getIntegerValue());
			this.editor.setValue(vo);
		}
	}

}
