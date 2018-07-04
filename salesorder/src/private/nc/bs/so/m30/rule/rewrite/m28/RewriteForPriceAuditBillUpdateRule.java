/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.m28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.arap.pub.UFDoubleTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VOSortUtils.VOComparator;

/**
 * @author wangzym
 * @version 2017��5��5�� ����6:09:09
 */
public class RewriteForPriceAuditBillUpdateRule implements
		ICompareRule<SaleOrderVO> {

	public RewriteForPriceAuditBillUpdateRule() {
		// TODO �Զ����ɵĹ��캯�����
	}

	// ɾ������Դ��������
	// private List<String> delIndex = new ArrayList<String>();
	private HashMap<String, UFDouble> delIndex = new HashMap<String, UFDouble>();
	private HashMap<String, UFDouble> numMoreIndex = new HashMap<String, UFDouble>();
	private HashMap<String, UFDouble> numLessIndex = new HashMap<String, UFDouble>();

	/*
	 * ���� Javadoc��
	 * 
	 * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(java.lang.Object[],
	 * java.lang.Object[])
	 */
	@Override
	public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
		// TODO ֻ��Ա���
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcid")) == null
					|| !"28".equals(SaleOrderBVO.getAttributeValue("vsrctype"))) {
				// ����Ҫ��дû��ȡ�����ε���Դ���ݻ�����Դ���Ǽ۸�������
				return;
			}
		}

		SaleOrderVO vo = vos[0];
		SaleOrderVO originVO = originVOs[0];
		compareAgg(vo, originVO);
		// delIndexȥ��

		reWriteDel(delIndex);
		reWriteForLessNum(numLessIndex);
		rewriteForMoreNum(numMoreIndex);

	}

	/**
	 * @param numMoreIndex2
	 */
	private void rewriteForMoreNum(HashMap<String, UFDouble> numMoreIndex2) {
		// TODO �Զ����ɵķ������
		for (Entry<String, UFDouble> map : numMoreIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("hasnordastnum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nordastnum");
			UFDouble num = sum;
			if (has == null) {
			} else {
				num = sum.sub(has);

			}
			// UFDouble num = sum.sub(has);
			int i = num.compareTo(map.getValue());
			if (i >= 0) {
				// û���⻹����
				UFDouble newHas = UFDoubleTool.sum(has, value); // has.add(value.intValue());
				PriceAuditItemVO vo = new PriceAuditItemVO();
				vo.setPrimaryKey(newpk);
				vo.setAttributeValue("hasnordastnum", newHas);
				// ��д����ֱ�Ӹ���
				VOUpdate<PriceAuditItemVO> update = new VOUpdate<PriceAuditItemVO>();
				update.update(new PriceAuditItemVO[] { vo },
						new String[] { "hasnordastnum" });

			} else {
				// ������
				String pk1 = map.getKey();
				String rowNum = pk1.substring(21);
				String message = "��" + rowNum + "�������ѳ������ο�����";
				ExceptionUtils.wrappBusinessException(message);
			}
		}

	}

	/**
	 * @param numLessIndex2
	 */
	private void reWriteForLessNum(HashMap<String, UFDouble> numLessIndex2) {
		// ��Ϊ�Ǽ��������������Ѳ�����Ӧ�ü��٣�����ҪУ��
		for (Entry<String, UFDouble> map : numLessIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("hasnordastnum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nordastnum");

			// ��Ϊ�Ǽ��������������Ѳ�����Ӧ�ü��٣�����ҪУ��
			UFDouble newHas = has.sub(value.intValue());
			PriceAuditItemVO vo = new PriceAuditItemVO();
			vo.setPrimaryKey(newpk);
			vo.setAttributeValue("hasnordastnum", newHas);
			// ��д����ֱ�Ӹ���
			VOUpdate<PriceAuditItemVO> update = new VOUpdate<PriceAuditItemVO>();
			update.update(new PriceAuditItemVO[] { vo },
					new String[] { "hasnordastnum" });
		}
		// TODO �Զ����ɵķ������

	}

	/**
	 * @param delIndex2
	 */
	private void reWriteDel(HashMap<String, UFDouble> delIndex2) {
		// TODO �Զ����ɵķ������
		// û��ɾ���еĲ���
		if (delIndex2.size() == 0) {
			return;
		} else {

			List<PriceAuditItemVO> bvosL = new ArrayList<PriceAuditItemVO>();

			for (Map.Entry<String, UFDouble> entry : delIndex2.entrySet()) {
				VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
						PriceAuditItemVO.class);
				PriceAuditItemVO[] bvo = query.query(new String[] { entry
						.getKey() });
				UFDouble oldNum = (UFDouble) bvo[0]
						.getAttributeValue("hasnordastnum");
				UFDouble newNum = oldNum.sub(entry.getValue());
				PriceAuditItemVO priceaudititemvo = new PriceAuditItemVO();
				priceaudititemvo.setPrimaryKey(entry.getKey());
				// �ͷ���������
				priceaudititemvo.setAttributeValue("hasnordastnum", newNum);
				bvosL.add(priceaudititemvo);
			}

			VOUpdate<PriceAuditItemVO> update = new VOUpdate<PriceAuditItemVO>();
			String[] names = new String[] { "hasnordastnum" };
			update.update(bvosL.toArray(new PriceAuditItemVO[bvosL.size()]),
					names);
		}

	}

	/**
	 * @param vo
	 * @param originVO
	 */
	private void compareAgg(nc.vo.so.m30.entity.SaleOrderVO vo,
			SaleOrderVO originVO) {
		// TODO �Զ����ɵķ������
		// �Ƚ�����vo�ı��峤���Ƿ�һ��
		if (vo.getChildrenVO().length == originVO.getChildrenVO().length) {
			// ���һ����ֱ�ӱȽ�
			SaleOrderBVO[] bvos = (SaleOrderBVO[]) vo.getChildrenVO();
			SaleOrderBVO[] oriBvos = (SaleOrderBVO[]) originVO.getChildrenVO();
			HashMap<String, Integer> res = new HashMap<String, Integer>();
			for (int i = 0; i < bvos.length; i++) {
				SaleOrderBVO SaleOrderBVO = bvos[i];
				SaleOrderBVO oriSaleOrderBVO = oriBvos[i];
				this.caculateNum(SaleOrderBVO, oriSaleOrderBVO);
			}

		}// �����ֶβ�һ����˵����ɾ��
		else {
			SaleOrderBVO[] bvos = (SaleOrderBVO[]) vo.getChildrenVO();
			// ���ݿ���ߵ����ݶ���ɾ��
			List<String> temp = new ArrayList<String>();
			// ��ɾ�����У���ֵ����δ�����չ�
			for (SaleOrderBVO SaleOrderBVO : bvos) {
				temp.add(SaleOrderBVO.getPrimaryKey());
			}

			ArrayList<SaleOrderBVO> oriBvosL = new ArrayList<SaleOrderBVO>();
			SaleOrderBVO[] oriBvos = (SaleOrderBVO[]) originVO.getChildrenVO();
			for (SaleOrderBVO SaleOrderBVO : oriBvos) {
				oriBvosL.add(SaleOrderBVO);
			}
			ArrayList<SaleOrderBVO> oriBvosLC = (ArrayList<SaleOrderBVO>) oriBvosL
					.clone();
			for (int i = 0; i < oriBvosL.size(); i++) {
				SaleOrderBVO SaleOrderBVO = oriBvosL.get(i);
				if (temp.contains(SaleOrderBVO.getPrimaryKey())) {
					continue;
				} else {
					delIndex.put(((String) SaleOrderBVO
							.getAttributeValue("csrcbid")),
							(UFDouble) SaleOrderBVO
									.getAttributeValue("nastnum"));
					oriBvosLC.remove(SaleOrderBVO);

				}

			}
			SaleOrderBVO[] newbvos = oriBvosLC
					.toArray(new SaleOrderBVO[oriBvosLC.size()]);
			for (int i = 0; i < newbvos.length; i++) {
				SaleOrderBVO saleOrderBVO = newbvos[i];
				this.caculateNum(bvos[i], saleOrderBVO);

			}

		}

	}

	/**
	 * @param SaleOrderBVO
	 * @param oriSaleOrderBVO
	 */
	private void caculateNum(SaleOrderBVO SaleOrderBVO,
			SaleOrderBVO oriSaleOrderBVO) {
		// TODO �Զ����ɵķ������
		// �������������ж�
		VOComparator<SaleOrderBVO> compare = new VOComparator<SaleOrderBVO>(
				new String[] { "nastnum" });
		int res1 = compare.compare(SaleOrderBVO, oriSaleOrderBVO);
		if (res1 == 0) {
			// ����0����д
		} else if (res1 == 1) {
			// ��ǰ��������
			String rowNum = (String) SaleOrderBVO.getAttributeValue("crowno");
			String pk = (String) SaleOrderBVO.getAttributeValue("csrcbid");
			UFDouble num = ((UFDouble) SaleOrderBVO
					.getAttributeValue("nastnum"))
					.sub((UFDouble) oriSaleOrderBVO
							.getAttributeValue("nastnum"));
			pk = pk + "_" + rowNum;
			numMoreIndex.put(pk, num);

		} else if (res1 == -1) {
			// ��ǰ�޸ĵ�����������
			String pk = (String) SaleOrderBVO.getAttributeValue("csrcbid");
			String rowNum = (String) SaleOrderBVO.getAttributeValue("crowno");
			UFDouble num = ((UFDouble) oriSaleOrderBVO
					.getAttributeValue("nastnum")).sub((UFDouble) SaleOrderBVO
					.getAttributeValue("nastnum"));
			pk = pk + "_" + rowNum;
			numLessIndex.put(pk, num);
		}
	}

}
