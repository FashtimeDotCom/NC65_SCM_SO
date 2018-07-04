package nc.bs.so.salequotation.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.util.VOFieldLengthChecker;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.util.SOMathUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description ���۱��۵�����ǰ���ݼ��
 * @scene ���۱��۵�����ǰ
 * @param m_transType
 *            ���۱��۵���������<���۱��۵���������code,���۱��۵���������vo> tranTypeService ���۱��۵��������ͷ���ӿ�
 */
public class SavedDataCheckRule implements IRule<AggSalequotationHVO> {

	// ���۱��۵���������<���۱��۵���������code,���۱��۵���������vo>
	Map<String, M4310TranTypeVO> m_transType = new HashMap<String, M4310TranTypeVO>();

	public void initTransType(AggSalequotationHVO[] aggvos) {
		Set<String> ctrantypeIds = new HashSet<String>();
		for (AggSalequotationHVO vo : aggvos) {
			ctrantypeIds.add(vo.getParentVO().getCtrantypeid());
		}
		M4310TranTypeVO[] trantypevos = null;
		VOQuery<M4310TranTypeVO> query = new VOQuery<M4310TranTypeVO>(
				M4310TranTypeVO.class);
		IDQueryBuilder idb = new IDQueryBuilder();
		String cond = idb.buildSQL(M4310TranTypeVO.CTRANTYPEID,
				ctrantypeIds.toArray(new String[0]));
		trantypevos = query.query(" and " + cond, null);
		for (M4310TranTypeVO vo : trantypevos) {
			m_transType.put(vo.getCtrantypeid(), vo);
		}
	}

	@Override
	public void process(AggSalequotationHVO[] vos) {
		initTransType(vos);
		this.checkSavedData(vos);
	}

	private void checkMainPrice(SalequotationBVO bvo) {
		if (bvo.getNorigprice() != null
				&& bvo.getNorigprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0028")/* @res "����˰���۲���Ϊ���Ҳ���С����!" */);
		}
		if (bvo.getNorigtaxprice() != null
				&& bvo.getNorigtaxprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0029")/* @res "����˰���۲���Ϊ���Ҳ���С����!" */);
		}
		if (bvo.getNorignetprice() != null
				&& bvo.getNorignetprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0030")/* @res "����˰���۲���Ϊ���Ҳ���С����!" */);
		}
		if (bvo.getNorigtaxnetprice() != null
				&& bvo.getNorigtaxnetprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0031")/* @res "����˰���۲���Ϊ���Ҳ���С����!" */);
		}

		UFDouble price = bvo.getNorigtaxprice();
		if (MathTool.compareTo(price, UFDouble.ZERO_DBL) <= 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0032")/* @res "�۸���Ϊ���Ҳ���С�ڵ���0" */);
		}
		price = bvo.getNqtorigtaxprice();
		if (MathTool.compareTo(price, UFDouble.ZERO_DBL) <= 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0032")/* @res "�۸���Ϊ���Ҳ���С�ڵ���0" */);
		}
		if (bvo.getNorigmny() == null) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0074")/* @res "��˰����Ϊ�ա�" */);
		}
		if (bvo.getNorigtaxmny() == null) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0075")/* @res "��˰�ϼƲ���Ϊ�ա�" */);
		}

	}	

	private void checkNumber(SalequotationBVO bvo) {
		if (SOMathUtil.isZero(bvo.getNnum()) || bvo.getNnum().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0034")/* @res "����������Ϊ���Ҳ���С����!" */);
		}
		if (SOMathUtil.isZero(bvo.getNassistnum())
				|| bvo.getNassistnum().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0035")/* @res "��������Ϊ���Ҳ���С����!" */);
		}
	}


	private void checkRowCountLimit(AggSalequotationHVO vo) {
		Object pk_org = vo.getParentVO().getPk_org();
		int rowlimit = 0;

		rowlimit = SOSysParaInitUtil.getSO29(pk_org.toString()) == null ? 0
				: SOSysParaInitUtil.getSO29(pk_org.toString()).intValue();

		int rowCount = vo.getChildrenVO().length;
		if (rowlimit > 0 && rowCount > rowlimit) {
			ExceptionUtils
					.wrappBusinessException(NCLangResOnserver
							.getInstance()
							.getStrByID("4006009_0", "04006009-0067", null,
									new String[] { Integer.toString(rowlimit) })/*
																				 * �����۵���������
																				 * !
																				 * [
																				 * ����SO29��������
																				 * :
																				 * {
																				 * 0
																				 * }
																				 * ]
																				 */);
		}
	}

	private void checkSavedData(AggSalequotationHVO aggVO) {
		VOFieldLengthChecker.checkVOFieldsLength(aggVO);
		if (aggVO.getChildrenVO() != null && aggVO.getChildrenVO().length != 0) {
			SalequotationBVO[] bvos = aggVO.getChildrenVO();
			for (int i = 0; i < bvos.length; i++) {
				this.validateNonNegative(bvos[i]);
			}
		}
	}

	private void checkSavedData(AggSalequotationHVO[] vos) {
		for (AggSalequotationHVO vo : vos) {
			this.checkSavedData(vo);
			// this.checkMaterielMutil(vo);
			this.checkRowCountLimit(vo);
			this.checkMaterielMutil(vo);
		}
	}

	/**
	 * ���������Ƿ���ظ�(liylr 2015-04-22)
	 * 
	 * @param bill
	 */
	private void checkMaterielMutil(AggSalequotationHVO bill) {

		SalequotationHVO header = bill.getParentVO();
		UFBoolean bmorerows = m_transType.get(header.getCtrantypeid())
				.getBmorerows();
		if (bmorerows != null && bmorerows.booleanValue()) {
			return;
		}
		Set<String> sinvo = new HashSet<String>();
		Set<String> sinvv = new HashSet<String>();
		for (SalequotationBVO bvo : bill.getChildrenVO()) {
			// ɾ����
			if (VOStatus.DELETED == bvo.getStatus()) {
				continue;
			}
			// ��Ʒ��
			if (null != bvo.getBlargessflag()
					&& bvo.getBlargessflag().booleanValue()) {
				continue;
			}
			String materieloid = bvo.getPk_material();
			String materielvid = bvo.getPk_material_v();
			if (sinvo.contains(materieloid) && sinvv.contains(materielvid)) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0087")/*
														 * @res
														 * "���۵����Ϳ���ͬһ���ﲻ���ж��У�"
														 */);
			} else {
				sinvo.add(materieloid);
				sinvv.add(materielvid);
			}
		}

	}

	private void validateNonNegative(SalequotationBVO bvo) {
		if (bvo != null) {
			this.checkBodyValidity(bvo);
			this.checkNumber(bvo);
			UFBoolean blargess = bvo.getBlargessflag();
			if (!blargess.booleanValue()) {
				// this.checkPrice(bvo);
				this.checkMainPrice(bvo);
			}
			if (bvo.getNtaxrate() != null
					&& bvo.getNtaxrate().doubleValue() < 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0036")/* @res "˰�ʲ���Ϊ����!" */);
			}
			if (bvo.getNitemdiscountrate() != null
					&& bvo.getNitemdiscountrate().doubleValue() < 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0037")/* @res "��Ʒ�ۿ۲���Ϊ����!" */);
			}
			// if (bvo.getNchangerate() != null
			// && bvo.getNchangerate().doubleValue() < 0) {
			// ExceptionUtils
			// .wrappBusinessException("�����ʲ���Ϊ����!");
			// }
			if (bvo.getNqtnum() != null && bvo.getNqtnum().doubleValue() < 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0038")/* @res "���ۻ����ʲ���Ϊ����!" */);
			}
		}
	}

	private void checkBodyValidity(SalequotationBVO bvo) {
		// ����ɾ���� ע�͵�unchanged
		if (VOStatus.DELETED == bvo.getStatus()
				|| VOStatus.UNCHANGED == bvo.getStatus()) {
			return;
		}
		// ���Ϸ��ֶ�
		List<String> listValiField = new ArrayList<String>();

		String cmaterialid = bvo.getPk_material_v();
		if (PubAppTool.isNull(cmaterialid)) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0307")/* ���� */);
		}

		String castunitid = bvo.getCastunitid();
		if (PubAppTool.isNull(castunitid)) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0308")/* ��λ */);
		}

		UFDouble ndiscountrate = (UFDouble) bvo
				.getAttributeValue(SalequotationBVO.NDISCOUNTRATE);
		if (null == ndiscountrate) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0309")/* �����ۿ� */);
		}
		UFDouble nitemdiscount = bvo.getNitemdiscountrate();
		if (null == nitemdiscount) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0310")/* ��Ʒ�ۿ� */);
		}
		UFDouble ntaxrate = bvo.getNtaxrate();
		if (null == ntaxrate) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0311")/* ˰�� */);
		}

		if (null == bvo.getCtaxcodeid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0439")/* ˰�� */);
		}

		if (null == bvo.getFtaxtypeflag()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0440")/* ��˰��� */);
		}

		if (null == bvo.getCrececountryid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0442")/* �ջ�����/���� */);
		}

		if (null == bvo.getCsendcountryid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0443")/* ��������/���� */);
		}

		if (null == bvo.getCtaxcountryid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0444")/* ��˰����/���� */);
		}

		if (null == bvo.getFbuysellflag()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0445")/* �������� */);
		}

		if (null == bvo.getBtriatradeflag()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0446")/* ����ó�� */);
		}
		StringBuilder errMsg = new StringBuilder();
		if (listValiField.size() > 0) {
			String crowno = bvo.getCrowno();
			errMsg.append(NCLangResOnserver.getInstance()
					.getStrByID("4006011_0", "04006011-0327", null,
							new String[] { crowno })/* ��[{0}]�У� */);
			for (String field : listValiField) {
				errMsg.append("[")
						.append(field)
						.append("]")
						.append(NCLangResOnserver.getInstance().getStrByID(
								"4006011_0", "04006011-0284")/* �� */);
			}
			errMsg.deleteCharAt(errMsg.length() - 1);
			errMsg.append("\n");
		}
		if (errMsg.length() > 0) {
			ExceptionUtils.wrappBusinessException(NCLangResOnserver
					.getInstance().getStrByID("4006011_0", "04006011-0328",
							null, new String[] { errMsg.toString() })/*
																	 * �����ֶ�ֵ����Ϊ�ջ�Ϊ0:
																	 * \n{0}
																	 */);
		}
	}
}
