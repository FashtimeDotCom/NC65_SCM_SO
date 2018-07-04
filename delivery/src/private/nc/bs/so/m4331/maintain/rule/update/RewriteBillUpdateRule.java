package nc.bs.so.m4331.maintain.rule.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m4331.maintain.rule.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description <p>
 *              <b>������Ҫ������¹��ܣ�</b>
 *              <ul>
 *              <li>�������޸ı����д��Դ����
 * @scene ���۷������޸ı����
 * @param ��
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ף����
 * @time 2010-1-21 ����04:57:49
 */
public class RewriteBillUpdateRule implements ICompareRule<DeliveryVO> {

	@Override
	public void process(DeliveryVO[] vos, DeliveryVO[] originVOs) {
		// ��д��Դ����
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool.splitForUpdate(
				vos, originVOs);

		Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
		for (DeliveryVO vo : vos) {
			DeliveryBVO[] bvos = vo.getChildrenVO();
			for (DeliveryBVO bvo : bvos) {
				map.put(bvo.getCdeliverybid(), bvo.getBclosesrcflag());
			}
		}
		// add by zhangby5 ��������ƽ̨���˷�������д���۶�����ҵ������д
		List<RewritePara> srcTranOut = srcParaIndex.get(TOBillType.TransOrder
				.getCode());
		if (!VOChecker.isEmpty(srcTranOut)) {
			rewriteUtil.reWriteSrc5X(srcTranOut, map);
		}

		List<RewritePara> src = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(src)) {
			rewriteUtil.reWriteSrc30(src, map);

		}

	}
}
