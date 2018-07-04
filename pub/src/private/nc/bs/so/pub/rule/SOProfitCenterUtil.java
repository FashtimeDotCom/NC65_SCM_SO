package nc.bs.so.pub.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.SOItemKey;

/**
 * ���۹����̨У�����۶������巢����������ֵ
 * 1�������������Ĳ�Ϊ�յ�����£������������Ĳ���Ϊ��
 * 2����Դ�ڵ��������ķ����������ջ��������ĺͷ����������ı���ͬʱ��ֵ����ͬʱΪ��
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-22
 */
public class SOProfitCenterUtil {

	public static void checkProfitCenterValue(IBill vo) {
		List<Object> errorRowno = new ArrayList<>();
		List<Object> errorRownoForDelivery = new ArrayList<>();
		CircularlyAccessibleValueObject[] bvos = ((AbstractBill) vo)
				.getChildrenVO();
		for (CircularlyAccessibleValueObject bvo : bvos) {
			String csprofitcentervid = (String) bvo
					.getAttributeValue(SOItemKey.CSPROFITCENTERVID);
			Object vsrctype = bvo.getAttributeValue(DeliveryBVO.VSRCTYPE);
			if (vsrctype != null
					&& TOBillType.TransOrder.isEqual((String) vsrctype)) {
				String crprofitcenterid = (String) bvo
						.getAttributeValue(SOItemKey.CRPROFITCENTERVID);
				if ((PubAppTool.isNull(csprofitcentervid) && !PubAppTool
						.isNull(crprofitcenterid))
						|| (PubAppTool.isNull(crprofitcenterid) && !PubAppTool
								.isNull(csprofitcentervid))) {
					errorRownoForDelivery.add(bvo
							.getAttributeValue(SOItemKey.CROWNO));
				}
				continue;

			}
			if (PubAppTool.isNull(csprofitcentervid)) {
				continue;
			}
			String cprofitcentervid = (String) bvo
					.getAttributeValue(SOItemKey.CPROFITCENTERVID);

			if (PubAppTool.isNull(cprofitcentervid)) {
				errorRowno.add((bvo.getAttributeValue(SOItemKey.CROWNO)));
			}
		}
		StringBuilder errorBuilder = new StringBuilder();
		if (errorRowno.size() > 0) {
			errorBuilder
					.append(NCLangResOnserver.getInstance().getStrByID(
							"4006004_0",
							"04006004-0241",
							null,
							new String[] {
									errorRowno.toString() })/*�кţ�{0}�� */);
			errorBuilder.append(NCLangResOnserver.getInstance().getStrByID(
					"4006004_0", "04006004-0242")/*
												 * �����������Ĳ�Ϊ�յ�����£������������Ĳ���Ϊ�գ�
												 */);
		}
		if (errorRownoForDelivery.size() > 0) {
			errorBuilder
					.append(NCLangResOnserver.getInstance().getStrByID(
							"4006004_0",
							"04006004-0241",
							null,
							new String[] {
									errorRowno.toString() })/* �кţ�{0} */);
			errorBuilder.append(NCLangResOnserver.getInstance().getStrByID(
					"4006004_0", "04006004-0246")/*
												 * ��Դ�ǵ��������ķ�������
												 * �ջ��������ĺͷ����������ı���ͬʱ��ֵ����ͬʱΪ�գ�
												 */);
		}
		if (errorBuilder.length() == 0) {
			return;
		}
		ExceptionUtils.wrappBusinessException(errorBuilder.toString());
	}
}
