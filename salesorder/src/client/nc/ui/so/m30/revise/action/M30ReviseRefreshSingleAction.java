package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class M30ReviseRefreshSingleAction extends RefreshSingleAction {

	private static final long serialVersionUID = 659643482434267723L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj = this.model.getSelectedData();
		if (obj != null) {
			if (obj instanceof AbstractBill) {

				AbstractBill oldVO = (AbstractBill) obj;
				String pk = oldVO.getParentVO().getPrimaryKey();
				AggregatedValueObject newVO = NCLocator.getInstance().lookup(ISaleOrderMaintain.class).querySaleOrderFor30Revise(oldVO.getClass(),pk);
				// ���ݱ�ɾ��֮��Ӧ�ûص��б������ˢ��
				if (newVO == null) {
					// �����Ѿ���ɾ��
					throw new BusinessException(NCLangRes.getInstance()
							.getStrByID("uif2", "RefreshSingleAction-000000")/*
																			 * �����Ѿ���ɾ��
																			 * ��
																			 * �뷵���б����
																			 * ��
																			 */);
				}
				this.model.directlyUpdate(newVO);

			} else {
				Logger.debug("Ŀǰֻ֧��SuperVO�ṹ������");/* -=notranslate=- */
			}
		}
		this.showQueryInfo();
	}

	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
}
