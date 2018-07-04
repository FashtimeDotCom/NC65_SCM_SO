package nc.ui.so.m32.billui.action.add;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintainApp;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.trade.checkrule.VOChecker;

public class RefJkyltestAddAction extends RefAddAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1434602744856357561L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		PfButtonClickContext context = this.createPfButtonClickContext();
		PfUtilClient.childButtonClickedNew(context);

		SaleInvoiceVO[] newvos = null;
		if (PfUtilClient.isCloseOK()) {
			newvos = (SaleInvoiceVO[]) PfUtilClient.getRetVos();
			if (VOChecker.isEmpty(newvos)) {
				return;
			}

			for (SaleInvoiceVO vo : newvos) {
				SaleInvoiceBVO[] sbvo = (SaleInvoiceBVO[])vo.getChildrenVO();
				String csrcid = sbvo[0].getAttributeValue("csrcid").toString();
				 
				ISaleInvoiceMaintainApp is = NCLocator.getInstance().lookup(
						ISaleInvoiceMaintainApp.class);
				if (!is.searchDownData(csrcid)) {
					ExceptionUtils.wrappBusinessException("�����Ѿ������������ظ�����!");
					return;
				}

			}

			// ������ת������ǰ
			this.beforeTranProcessor(newvos);
			// ������Ų����ǻ�����ʾ�Ļ�
			CombinResultVO combinres = this.combinSaleInvoices(newvos);
			if (combinres.getBcombinflag()) {
				newvos = combinres.getCombinvos();
			}
			// ������ܽ��
			this.getModel().setCombinCacheVO(combinres.getCacheVO());
			// �������ݽ��洦��
			this.getTransferViewProcessor().processBillTransfer(newvos);
		}
	}

}
