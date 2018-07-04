package nc.bs.so.m32.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description ���۷�Ʊ�޸ı���ǰУ�鲢���µ���״̬��������̬�ĵ��ݲ������޸ģ�
 * @scene ���۷�Ʊ�޸ı���ǰ
 * @param ��
 * @since 6.0
 * @version 2011-5-19 ����02:44:10
 * @author ô��
 */
public class CheckBillState implements IRule<SaleInvoiceVO> {

	@Override
	public void process(SaleInvoiceVO[] invoices) {

		for (SaleInvoiceVO invoicevo : invoices) {
			// ��ͷ�Ϸ���У��
			this.checkHeadValidity(invoicevo.getParentVO());
			// �Ϸ���У�����Ҫ����������ͨ���ĵ���״̬ add by zhangby5
			this.reSetBillStatusForNoPass(invoicevo);
		}

	}

	/**
	 * У�鵥��״̬
	 * 
	 * @param head
	 */
	private void checkHeadValidity(SaleInvoiceHVO head) {

		// modify by wangshu6 for 636 2015-01-19 ���۷�Ʊ������֧���޸�
		if (!BillStatus.FREE.getIntegerValue().equals(head.getFstatusflag())
				&& !BillStatus.NOPASS.getIntegerValue().equals(
						head.getFstatusflag())
				&& !BillStatus.AUDITING.getIntegerValue().equals(
						head.getFstatusflag())) {

			ExceptionUtils
					.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("4006008_0", "04006008-0018")/*
																	 * @res
																	 * "������̬��������ͨ���ĵ��ݲ������޸�"
																	 */);
		}
	}

	private void reSetBillStatusForNoPass(SaleInvoiceVO vo) {
		if (vo.getParentVO().getFstatusflag().intValue() == BillStatus.NOPASS
				.getIntValue()) {
			vo.getParentVO().setFstatusflag(BillStatus.FREE.getIntegerValue());
		}
	}
}
