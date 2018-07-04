package nc.bs.so.m32.maintain.rule.insert;

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
 * @description <p>
 *              <b>������Ҫ������¹��ܣ�</b>
 *              <ul>
 *              <li>���۷�Ʊ����������д��Դ����
 *              </ul>
 *              <p>
 * @scene ���۷�Ʊ���������
 * @param ��
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-1-21 ����04:57:49
 */
public class RewriteBillInsertRule implements IRule<SaleInvoiceVO> {

	@Override
	public void process(SaleInvoiceVO[] vos) {

		// ��д��Դ����
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		// ������֯��Ϣ
		rewriteUtil.catcheOrg(vos);

		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool
				.splitForInsert(vos);

		List<RewritePara> srcSaleOut = srcParaIndex.get(ICBillType.SaleOut
				.getCode());
		List<RewritePara> srcSaleOrder = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(srcSaleOut)) {
			rewriteUtil.reWriteSrc4C(srcSaleOut);
		}
		if (!VOChecker.isEmpty(srcSaleOrder)) {
			rewriteUtil.reWriteSrc30(srcSaleOrder);
		}
	}

}
