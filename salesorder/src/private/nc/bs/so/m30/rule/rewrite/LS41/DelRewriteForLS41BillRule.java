/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.LS41;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * @author wangzym
 * @version 2017��06��06�� ����2:05:44 2017-05-03
 * 
 */
public class DelRewriteForLS41BillRule implements
		IRule<nc.vo.so.m30.entity.SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		// TODO �Զ����ɵķ������
		// ֻ��һ��Aggvo
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcbid")) == null
					|| !"LS41".equals(SaleOrderBVO
							.getAttributeValue("vsrctype"))) {
				// ����Ҫ��дû��ȡ�����ε���Դ����
				// ����ȡ������Դ�������Ͳ��Ǽ���
				return;
			}
			String csrcid = SaleOrderBVO.getAttributeValue("csrcbid")
					.toString();
			srcPk[i] = csrcid;

		}
		this.rewrite(srcPk);

	}

	/**
	 * @Title: rewrite
	 * @Description: TODO
	 * @param srcPk
	 * @return: void
	 */
	private void rewrite(String[] srcPk) {
		// TODO �Զ����ɵķ������
		if (srcPk.length == 0) {
			return;
		}
		LsxywtbBVO[] bvos = new LsxywtbBVO[srcPk.length];
		for (int i = 0; i < srcPk.length; i++) {
			String bpk = srcPk[i];
			LsxywtbBVO bvo = new LsxywtbBVO();
			bvo.setPrimaryKey(bpk);
			// ��дΪ0 �Ա��´λ�������
			bvo.setAttributeValue("bdef1", 0);
			bvos[i]=bvo;

		}
		VOUpdate<LsxywtbBVO> bo = new VOUpdate<LsxywtbBVO>();

		LsxywtbBVO[] newvo = bo.update(bvos, new String[] { "bdef1" });
		TimeLog.info("�������ݿ�"); /* -=notranslate=- */
	}

}
