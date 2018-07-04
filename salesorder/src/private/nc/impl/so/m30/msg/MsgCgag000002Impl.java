package nc.impl.so.m30.msg;

import nc.impl.pubapp.pattern.data.bill.BillInsert;   
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.so.m30.msg.IMsgCgag000002;

import java.util.ArrayList;
import java.util.List;


import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pf.PfUtilTools;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pp.m28.entity.PriceAuditViewVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.lm.pgjdcght.AggCgag000002HVO;
import nc.vo.lm.pgjdcght.Cgag000002HVO;

/**
 * @author wangzym
 * @version 2017��4��10�� ����9:19:13
 * ���ν��۸���������vbdef1��Ƴ������
 * ���ν��۸���������vbdef2��Ƴ������к�
 * ����vbdef1��vbdefû�����̵�
 * ��pk_group���Ϊ��ֵ
 * 
 */
public class MsgCgag000002Impl implements IMsgCgag000002 {

	/*
	 * ���� Javadoc��
	 * 
	 * @see
	 * nc.itf.so.m30.msg.IMsgCgag000002#Cgag000002RequiresNew(nc.vo.lm.pgjdcght
	 * .Cgag000002HVO)
	 * ����Ҫ��������û��Ҫ
	 */
	@Override
	public void Cgag000002RequiresNew(Cgag000002HVO cgag000002hvo) {
		// TODO �Զ����ɵķ������
		List<Cgag000002HVO> lhvo = new ArrayList<Cgag000002HVO>();
		try {
			NCObject[] ncObjects = MDPersistenceService
					.lookupPersistenceQueryService().queryBillOfNCObjectByCond(
							Cgag000002HVO.class, "msgflag='0'", false);
			for (NCObject ncObject : ncObjects) {
				AggCgag000002HVO aggvo = (AggCgag000002HVO) ncObject
						.getContainmentObject();
				Cgag000002HVO hvo = aggvo.getParentVO();
				lhvo.add(hvo);
			}
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		/*
		 * VOQuery<Cgag000002HVO> query = new VOQuery<Cgag000002HVO>(
		 * Cgag000002HVO.class); SqlBuilder sb = new SqlBuilder();
		 * //sb.append("and dr<>'1'"); sb.append("and  msgflag='0'");
		 * Cgag000002HVO[] hvos = query.query(sb.toString(), null);
		 */
		Cgag000002HVO[] hvos = lhvo.toArray(new Cgag000002HVO[lhvo.size()]);
		/*
		 * List<Cgag000002HVO> hvos1 = new ArrayList<Cgag000002HVO>(); for (int
		 * i = 0; i < hvos.length; i++) {
		 * 
		 * if (!hvos1.contains(hvos[i])) { hvos1.add(hvos[i]); } }
		 * Cgag000002HVO[] hvo = hvos1.toArray(new Cgag000002HVO[hvos1.size()]);
		 */
		// ���������Ҫ����������Ƶ���Ҳ���Ƕ��ٸ��۸���������aggvo��Ҫ����
		List<String> mrid = new ArrayList<String>();
		for (int i = 0; i < hvos.length; i++) {
			Cgag000002HVO cgag000002hvo2 = hvos[i];
			String mri = cgag000002hvo2.getMrid();
			if (!mrid.contains(mri)) {
				mrid.add(mri);
			}
		}
		// �����Ӧ�����������ļ���
		List<String> bpks = queryPriceAuditVO(mrid);

		// ����ۺ�vo
		PriceAuditVO[] agg = queryPriceAuditAggVO(bpks);
		// �Լ۸����������и���ֵ�������м�����ݶ�Ӧ
		PriceAuditVO[] aggVOS = updatePriceAudit(agg, hvos);
		// �����Ƶ�����
		pushToM30(aggVOS,hvos);

	}

	/**
	 * @param hvos
	 * @param agg
	 * @return
	 * 
	 */
	private PriceAuditVO[] updatePriceAudit(PriceAuditVO[] agg,
			Cgag000002HVO[] hvos) {
		// TODO �Զ����ɵķ������
		// һ��aggvo��Ӧ���hvos�������к�ȡ��
		// �������1��agg��Ӧ��Cgag000002HVO
		List<Cgag000002HVO> matchvo = new ArrayList<Cgag000002HVO>();

		for (PriceAuditVO priceAuditVO : agg) {

			// �ٶ��Զ�����1�������
			String mrid = priceAuditVO.getChildrenVO()[0].getVbdef1();
			for (int i = 0; i < hvos.length; i++) {
				if ((hvos[i].getMrid()).equals(mrid)) {
					matchvo.add(hvos[i]);
				}
			}
			Cgag000002HVO[] matchvos = matchvo
					.toArray(new Cgag000002HVO[matchvo.size()]);
			PriceAuditItemVO[] bvos = priceAuditVO.getChildrenVO();
			for (PriceAuditItemVO priceAuditItemVO : bvos) {
				// �ٶ��Զ�����2���к�
				String mrlineid = priceAuditItemVO.getVbdef2();
				for (int i = 0; i < matchvos.length; i++) {
					if (matchvos[i].getMrlineid().equals(mrlineid)) {
						// ����
						UFDouble bpoamt = matchvos[i].getBpoamt();
						// ˰��
						UFDouble taxrate = matchvos[i].getTaxrate();
						// ����˰����
						UFDouble bpoprice = matchvos[i].getBpoprice();
						// ��ͬ�ܽ���˰
						UFDouble bposum = matchvos[i].getBposum();
						// ���۸���������ֵ
						priceAuditItemVO.setNnum(bpoamt);
						priceAuditItemVO.setNtaxrate(taxrate);
						priceAuditItemVO.setNorigprice(bpoprice);
						priceAuditItemVO.setPlan_priceb(bposum.toString());

					}

				}
			}
		}
		return agg;

	}

	public List<String> queryPriceAuditVO(List<String> list) {
		String[] mrid = list.toArray(new String[list.size()]);
		List<String> hpks = new ArrayList<String>();
		List<String> bpks = new ArrayList<String>();

		for (int i = 0; i < mrid.length; i++) {
			String mri = mrid[i];
			NCObject[] ncObjects = null;
			try {
				// �ٶ������ =vbdef1
				ncObjects = MDPersistenceService
						.lookupPersistenceQueryService()
						.queryBillOfNCObjectByCond(PriceAuditItemVO.class,
								"vbdef1 = '" + mri + "' and dr='0'", false);
			} catch (MetaDataException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			if (ncObjects != null) {

				PriceAuditItemVO bvo = (PriceAuditItemVO) ncObjects[0]
						.getContainmentObject();
				String hpk = bvo.getPk_priceaudit();
				if (!hpks.contains(hpk)) {
					hpks.add(hpk);
				}
				for (int j = 0; j < ncObjects.length; j++) {
					PriceAuditItemVO bvos = (PriceAuditItemVO) ncObjects[j]
							.getContainmentObject();
					String pk_PriceAudit_b = bvos.getPk_priceaudit_b();
					if (!bpks.contains(pk_PriceAudit_b)) {
						bpks.add(pk_PriceAudit_b);
					}
				}
			}
		}
		// view��ѯҪʹ���ӱ���������һ��һ���������ֹʹ����������
		return bpks;

	}

	// PriceAuditVO[] pavo = queryPriceAuditAggVO(hpks);

	public void pushToM30(PriceAuditVO[] aggVOS,Cgag000002HVO[] hvos) {
		//����û���½ӹ���������
		if (aggVOS.length==0) {
			return;
		}
		// ���õ�ǰ������pk_group
		InvocationInfoProxy.getInstance().setGroupId("0001N610000000000IT0");
		for (int i = 0; i < aggVOS.length; i++) {
			PriceAuditVO priceAuditVO = aggVOS[i];
			//���ξۺ�vo��Ӧ���м���е����ݣ���Ҫ���л�д�������ɹ�����ʧ�ܱ�־��ԭ��
			List<Cgag000002HVO> lMatchvo=new ArrayList<Cgag000002HVO>();
			for (Cgag000002HVO cgag000002hvo : hvos) {
				PriceAuditItemVO[]  bvos=priceAuditVO.getChildrenVO();
				String mrid =bvos[0].getVbdef1();
				if (cgag000002hvo.getMrid().equals(mrid)) {
					lMatchvo.add(cgag000002hvo);
				}
			}

			try {
				SaleOrderVO saleordervo = (SaleOrderVO) PfUtilTools
						.runChangeData("28-Cxx-03", "30", priceAuditVO);
				NCObject objs = NCObject.newInstance(saleordervo);// AGGVO����
				saleordervo.getParentVO().setTs(
						AppContext.getInstance().getServerTime());
				SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
				for (SaleOrderBVO saleOrderBVO : bvos) {
					//����������ts
					saleOrderBVO
							.setTs(AppContext.getInstance().getServerTime());
				}
				VORowNoUtils.setVOsRowNoByRule(new SaleOrderVO[] { saleordervo });
				BillInsert<SaleOrderVO> bo = new BillInsert<SaleOrderVO>();
				// ���뵽���ݿ������µ���VO������ʱ��������µ�
				SaleOrderVO[] vos = bo
						.insert(new SaleOrderVO[] { saleordervo });
				//���뵽���ݿ��Ҫ�ѱ���ɹ������ݻ�д��ûд��
				Cgag000002HVO[] matchvo=lMatchvo.toArray(new Cgag000002HVO[lMatchvo.size()]);
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					//����ɹ�
					cgag000002hvo.setMsgflag("1");
				}
				VOUpdate<Cgag000002HVO> update=new VOUpdate<Cgag000002HVO>();
				update.update(matchvo);
				
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				// e.printStackTrace();
				// ��д�м�������־
				Cgag000002HVO[] matchvo=lMatchvo.toArray(new Cgag000002HVO[lMatchvo.size()]);
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					//����ʧ��
					cgag000002hvo.setMsgflag("2");
					cgag000002hvo.setMsginfo(e.getMessage());
					
				}
				VOUpdate<Cgag000002HVO> update=new VOUpdate<Cgag000002HVO>();
				update.update(matchvo);
				e.getMessage();
				ExceptionUtils.wrappBusinessException("����ʧ��");
			}
		}

	}

	/**
	 * @param hpks
	 */
	private PriceAuditVO[] queryPriceAuditAggVO(List<String> hpks) {
		// TODO �Զ����ɵķ������
		PriceAuditVO[] rets = new PriceAuditVO[hpks.size()];
		ViewQuery<PriceAuditViewVO> query = new ViewQuery<PriceAuditViewVO>(
				PriceAuditViewVO.class);
		PriceAuditViewVO[] views = query.query(hpks.toArray(new String[hpks
				.size()]));

		if (null != views && views.length > 0) {
			int len = views.length;
			PriceAuditVO[] bills = new PriceAuditVO[len];
			for (int i = 0; i < len; i++) {
				bills[i] = (views[i]).changeToBill();
			}
			CombineBill<PriceAuditVO> combine = new CombineBill<PriceAuditVO>();
			IVOMeta headMeta = bills[0].getMetaData().getParent();
			String headItemKey = headMeta.getPrimaryAttribute().getName();
			combine.appendKey(headItemKey);
			rets = combine.combine(bills);
		}
		return rets;

	}
	/**
	 * ���ɵ��ݺ�
	 */

}
