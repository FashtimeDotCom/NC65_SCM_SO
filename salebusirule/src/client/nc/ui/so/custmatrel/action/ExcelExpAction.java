package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.ml.NCLangRes;
import nc.ui.so.custmatrel.view.CardForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.excelimport.ExportAction;

/**
 * 
 * �ͻ����Ϲ�ϵExcel����
 * 
 * @since 6.3
 * @version 2013-05-16 08:57:18
 * @author liujingn
 */
public class ExcelExpAction extends ExportAction {

	private static final long serialVersionUID = 4649127813490533159L;

	private CardForm cardform;

	private boolean isUidialogok;

	/**
	 * 
	 * @return CardForm
	 */
	public CardForm getCardform() {
		return this.cardform;
	}

	/**
	 * 
	 * @param cardform
	 */
	public void setCardform(CardForm cardform) {
		this.cardform = cardform;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// �¶�������á����� 2014.1.7 ����Excel���û�б�������ô����Ҳ�����б����У���˼Ӵ����ơ�
		IKeyValue keyvalue = new CardKeyValue(this.cardform.getBillCardPanel());
		if (keyvalue.getBodyCount() == 0) {
			ExceptionUtils
					.wrappBusinessException(NCLangRes.getInstance().getStrByID(
							"4006007_0", "04006007-0033")/* ��ά��һ�б����ٵ���Excel�� */);
		}
		CustMaterExcelImporter ei = new CustMaterExcelImporter();
		ei.setFuncode(this.getModel().getContext().getFuncInfo().getFuncode());
		ei.exportToExcel(this.getImportableEditor());
		isUidialogok = ei.isUidialogok();

	}

	@Override
	public void doAfterSuccess(ActionEvent actionEvent) {
		if (isUidialogok) {
			super.doAfterSuccess(actionEvent);
		}
	}

	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)
			throws Exception {
		return true;
	}
}
