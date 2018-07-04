package nc.bs.so.m30.rule.rewrite.m28;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 
 * @author wangzym
 * @version 2017-06-07 ���εĶ���ִ������û�иĶ�
 */

public class RewriteForPriceAuditBillRule implements IRule<SaleOrderVO> {

	@Override
	public void process(nc.vo.so.m30.entity.SaleOrderVO[] vos) {
		// TODO �Զ����ɵķ������
		// ֻ��һ��Aggvo
		// ��Ҫ�ж��Ƿ���Դ��������Ϊ�۸�������
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		UFDouble[] nums = new UFDouble[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcid")) == null
					|| !"28".equals(SaleOrderBVO.getAttributeValue("vsrctype"))) {
				// ����Ҫ��дû��ȡ�����ε���Դ����
				return;
			}
			UFDouble num = (UFDouble) SaleOrderBVO.getAttributeValue("nastnum");
			String csrcbid = SaleOrderBVO.getAttributeValue("csrcbid")
					.toString();
			srcPk[i] = csrcbid;
			nums[i] = num;

		}
		this.rewrite(srcPk, nums);

	}

	/**
	 * ��д����
	 * 
	 * @param nums
	 * 
	 * @param paras
	 *            ��ǰ����Դ�����ֶ�����
	 */
	private void rewrite(String[] srcPk, UFDouble[] nums) {
		if (srcPk.length == 0) {
			return;
		}
		String[] names = new String[] { "hasnordastnum" };
		VOUpdate<PriceAuditItemVO> bo = new VOUpdate<PriceAuditItemVO>();
		PriceAuditItemVO[] vos = new PriceAuditItemVO[srcPk.length];

		for (int i = 0; i < vos.length; i++) {
			PriceAuditItemVO PriceAuditItemVO = new PriceAuditItemVO();
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] old = query.query(new String[] { srcPk[i] });
			UFDouble oldnum = (UFDouble) old[0]
					.getAttributeValue("hasnordastnum");

			/**
			 * 2017-08-18 oldsum �ɹ��������� ��Ϊnordastnum�����������Զ�������Ϊ׼����׼У��
			 */
			UFDouble oldsum = (UFDouble) old[0].getAttributeValue("nordastnum");

			UFDouble num = null;
			if (oldnum == null) {
				num = nums[i];
			} else {

				num = nums[i].add(oldnum);
			}
			if (oldsum.doubleValue() < num.doubleValue()) {
				ExceptionUtils.wrappBusinessException("�޸ĺ����������������������");
			} else {
				PriceAuditItemVO.setPrimaryKey(old[0].getPrimaryKey());
				PriceAuditItemVO.setAttributeValue("hasnordastnum", num);

				vos[i] = PriceAuditItemVO;
			}
		}
		PriceAuditItemVO[] newvo = bo.update(vos, names);
		TimeLog.info("�������ݿ�"); /* -=notranslate=- */

	}

}
