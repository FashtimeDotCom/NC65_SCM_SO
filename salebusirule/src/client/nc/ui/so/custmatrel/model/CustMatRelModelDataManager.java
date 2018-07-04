package nc.ui.so.custmatrel.model;

import javax.swing.SwingUtilities;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.query2.model.IModelDataProcessor;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pubapp.pattern.exception.CarrierRuntimeException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.Variable;

public class CustMatRelModelDataManager extends ModelDataManager {

	@Override
	public void initModelByQueryScheme(IQueryScheme qryScheme) {
		this.setQueryScheme(qryScheme);
		try {
			Object[] objs = this.getQryService().queryByQueryScheme(qryScheme);
			if (objs != null && objs.length == Variable.getMaxQueryCount()) {
				// String hint =
				// "��ѯ���̫��ֻ������ " + Variable.getMaxQueryCount() +
				// " �����ݣ�����С��Χ�ٴβ�ѯ";

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						String hint = nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes()
								.getStrByID(
										"pubapp_0",
										"0pubapp-0268",
										null,
										new String[] { ""
												+ Variable.getMaxQueryCount() })/*
																				 * @
																				 * res
																				 * "��ѯ���̫��ֻ������{0}�����ݣ�����С��Χ�ٴβ�ѯ"
																				 */;
						MessageDialog.showHintDlg(getModel().getContext()
								.getEntranceUI(), null, hint);
					}
				});
			}

			if (this.getModelDataProcessor() != null) {
				objs = this.getModelDataProcessor().processModelData(qryScheme,
						objs);
			}
			String schemeName = qryScheme.getName();
			if (!StringUtil.isEmptyWithTrim(schemeName)) {
				ModelDataDescriptor modelDataDescriptor = new ModelDataDescriptor(
						schemeName/*
								 * + "(" + (objs == null ? 0 : objs.length) +
								 * ")"
								 */);
				this.getModel().initModel(objs, modelDataDescriptor);
			} else {
				ModelDataDescriptor modelDataDescriptor = new ModelDataDescriptor(
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"pubapp_0", "0pubapp-0144")/* @res "��ѯ���" */);
				this.getModel().initModel(objs, modelDataDescriptor);
			}

		} catch (CarrierRuntimeException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0007")/*
																		 * @res
																		 * "��ѯ���ݷ����쳣"
																		 */, e);
		}
	}
}
