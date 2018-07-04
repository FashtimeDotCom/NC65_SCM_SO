package nc.bs.so.m32.maintain.rule.delete;

import java.util.List;
import java.util.Map;

import nc.bs.so.m32.maintain.rule.util.RewriteBillUtil;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description ���۷�Ʊɾ��������Ļ�д���� ������д��Դ��Դͷ
 * @scene ���۷�Ʊɾ�������
 * @param ��
 * @since 6.3
 * @version 2012-12-21 ����09:04:31
 * @author yaogj
 */
public class RewriteBillDeleteRule implements IRule<SaleInvoiceVO> {

	@Override
	public void process(SaleInvoiceVO[] vos) {

		// ��д��Դ����
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		// ������֯��Ϣ
		rewriteUtil.catcheOrg(vos);

		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool
				.splitForDelete(vos);

		List<RewritePara> srcSaleOut = srcParaIndex.get(ICBillType.SaleOut
				.getCode());
		List<RewritePara> srcSaleOrder = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(srcSaleOut)) {
			rewriteUtil.reWriteSrc4C(srcSaleOut);
			// wangzy add���Ӷ������۶�����������д
			rewriteUtil.reWriteSrc30(srcSaleOut);
		}
		if (!VOChecker.isEmpty(srcSaleOrder)) {
			rewriteUtil.reWriteSrc30(srcSaleOrder);
		}

	}

}
