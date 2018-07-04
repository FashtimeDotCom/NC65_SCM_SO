package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import nc.vo.ftpub.res.FTActionCode;
import nc.vo.it.m5801.enumeration.BillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.util.StringUtil;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30.revise.ISaleOrderReviseMaintainApp;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.bs.framework.common.NCLocator;
import nc.ui.ftpub.action.FTActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ת���޶�
 * 
 * @since 6.5
 * @version 2018-03-29 ����15:47:55
 * @author ����ܲ
 */
public class ToReviseM30Action extends NCAction {

	/**
	 * ���۶����޶��ڵ�
	 */
	public static final String CTREVISE_FUNCODE = "40060302";

	private AbstractAppModel model;

	private SaleOrderBillForm editor;

	/**
	 * ������
	 */
	public ToReviseM30Action() {
		this.setBtnName("�޶�����");
		this.setCode("reviseapprove");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		try {
			SaleOrderVO ctVO = (SaleOrderVO) this.model.getSelectedData();
			// 1.׼��VOʵ�ַ���
			ISaleOrderReviseMaintainApp service = NCLocator.getInstance()
					.lookup(ISaleOrderReviseMaintainApp.class);
			SaleOrderHistoryVO[] destVO = service
					.queryM30ReviseApp(new String[] { ctVO.getParentVO()
							.getPrimaryKey() });

			FuncletInitData initData = new FuncletInitData();
			initData.setInitType(0);
			initData.setInitData(destVO[0]);
			// �޶��ڵ�
			IFuncRegisterQueryService funQuery = NCLocator.getInstance()
					.lookup(IFuncRegisterQueryService.class);
			FuncRegisterVO funvo = funQuery
					.queryFunctionByCode(ToReviseM30Action.CTREVISE_FUNCODE);
			// ���޶��ڵ�
			Dimension size = null;
			FuncletWindowLauncher.openFuncNodeDialog(this.editor, funvo,
					initData, null, true, false, size, true);
		} catch (BusinessException be) {
			ExceptionUtils.wrappException(be);
		}
	}

	/**
	 * ����billForm
	 * 
	 * @return ContractEditor
	 */
	public SaleOrderBillForm getEditor() {
		return this.editor;
	}

	/**
	 * ���model
	 * 
	 * @return BillManageModel
	 */
	public AbstractAppModel getModel() {
		return this.model;
	}

	/**
	 * ���billForm
	 * 
	 * @param editor
	 */
	public void setEditor(SaleOrderBillForm editor) {
		this.editor = editor;
	}

	/**
	 * ����model
	 * 
	 * @param model
	 */
	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		boolean iseidtable = (this.model.getUiState() == UIState.NOT_EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {
			SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
			// ����̬�Ŀ����޸�
			// modify by wangshu6 for ���۶����޶�֧�������� ���޶�δ�ύʱ ����״̬Ϊ���ɡ������У������ٴ��޶�
			// Integer fstatusflag = vo.getParentVO().getFstatusflag();
			// if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
			// return false;
			// }
			// add by wangshu6 �Ѿ��������ó�ֻ���Ʒ�Ҹ������۶�������ֹ�޶���2015-01-05
			if (!StringUtil
					.isEmptyTrimSpace(vo.getParentVO().getCarsubtypeid())) {
				iseidtable = false;
			} else if (!MathTool.isZero(vo.getParentVO().getNtotalorigsubmny())) {
				iseidtable = false;
			}
			if (vo.getParentVO().getFstatusflag() == 2
					|| vo.getParentVO().getFstatusflag() == 3) {
				// �����޶���ʷ�Ĳ�ѯ���������������ͨ����2���Ͷ�������ã������Ĳ�����
				iseidtable = true;
			} else {
				iseidtable = false;
			}
			// end
		}
		return iseidtable;
	}

	/**
	 * 
	 * @param fstatusflag
	 * @return b
	 */
	protected boolean tryMakeFlow(Integer fstatusflag) {
		return fstatusflag == null
				|| BillStatusEnum.APPROVED.equalsValue(fstatusflag);
	}
}
