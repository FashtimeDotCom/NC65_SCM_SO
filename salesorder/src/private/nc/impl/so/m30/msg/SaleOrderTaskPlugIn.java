package nc.impl.so.m30.msg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.pubapp.pub.rule.CreateBillCodeRule;
import nc.bs.trade.business.HYPubBO;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.uif.pub.exception.UifException;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.lm.pgjdcght.AggCgag000002HVO;
import nc.vo.lm.pgjdcght.Cgag000002HVO;
import nc.vo.org.OrgVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pp.m28.entity.PriceAuditViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author wangzym
 * @version 2017��4��14�� ����2:21:31 ���۶����Ķ�ʱ���� ��ʱȥ�м��ȡ�����һ�д���� ���ν��۸���������mrid��Ƴ������
 *          ���ν��۸���������mrlineid��Ƴ������к� ��pk_group���Ϊ��ֵ
 * 
 */
public class SaleOrderTaskPlugIn implements IBackgroundWorkPlugin {

	public List<Cgag000002HVO> matchvo = new ArrayList<Cgag000002HVO>();
	private String returnMsg = "";

	/**
	 * 
	 */
	public SaleOrderTaskPlugIn() {
		// TODO �Զ����ɵĹ��캯�����
	}

	/*
	 * ���� Javadoc��
	 * 
	 * @see
	 * nc.bs.pub.taskcenter.IBackgroundWorkPlugin#executeTask(nc.bs.pub.taskcenter
	 * .BgWorkingContext)
	 */
	@Override
	public PreAlertObject executeTask(BgWorkingContext paramBgWorkingContext)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		PreAlertObject preobj = new PreAlertObject();
		preobj.setReturnType(PreAlertReturnType.RETURNMESSAGE);

		// ҵ����
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

		Cgag000002HVO[] hvos = lhvo.toArray(new Cgag000002HVO[lhvo.size()]);

		// ���ݺ�ͬ�ź������ж��Ƿ񱾴κ�ͬ����ȫ��������(�ֶ�Ϊ��ͬ�� bpoid)
		HashMap<String, Integer> reCompare = new HashMap<String, Integer>();
		HashMap<String, HashMap<String, List<String>>> use = new HashMap<String, HashMap<String, List<String>>>();
		HashMap<String, Integer> compare = new HashMap<String, Integer>();
		HashMap<String, Integer> differ = new HashMap<String, Integer>();
		for (int i = 0; i < hvos.length; i++) {
			Cgag000002HVO cgag000002hvo2 = hvos[i];
			String bpoid = cgag000002hvo2.getBpoid();
			/*
			 * String mrid1 = cgag000002hvo2.getMrid(); String mrlineid =
			 * cgag000002hvo2.getMrlineid(); List<String> mrline=new
			 * ArrayList<String>();
			 */
			int num = cgag000002hvo2.getHdef2() == null ? 0 : Integer
					.parseInt(cgag000002hvo2.getHdef2());
			if (!reCompare.containsKey(bpoid)) {
				reCompare.put(bpoid, num);

			}

		}
		// ��ȡ�������������Ƿ�
		for (Entry<String, Integer> entry : reCompare.entrySet()) {
			String key = entry.getKey();
			int sum = 0;
			for (Cgag000002HVO cgag000002hvo : hvos) {
				while (cgag000002hvo.getBpoid() == null) {
					returnMsg += cgag000002hvo.getPrimaryKey()
							+ "��ͬ��Ϊ��,��ȷ�����ݺ���ִ��";
					break;
				}
				if (cgag000002hvo.getBpoid().equals(key)) {
					sum++;
				}
				compare.put(key, sum);
			}
		}
		// �����������ݺ����ݿ��и���ֵ���Աȣ�
		for (Entry<String, Integer> entry : reCompare.entrySet()) {
			String key = entry.getKey();
			int db = entry.getValue();
			for (Entry<String, Integer> entry1 : compare.entrySet()) {
				String keyNew = entry1.getKey();
				if (key.equals(keyNew)) {
					int num = entry1.getValue();
					if (db == num) {
						continue;
					} else {
						differ.put(keyNew, 1);
					}
				}
			}

		}
		// ���õı����ӱ�
		for (Entry<String, Integer> entry : differ.entrySet()) {
			String key = entry.getKey();
			reCompare.remove(key);

		}

		// ȡ�������õ�ֵ
		List<String> hths = new ArrayList<String>();

		for (Entry<String, Integer> entry : reCompare.entrySet()) {
			String key = entry.getKey();
			hths.add(key);
		}
		// �ɽ����Ƶ������ĺ�ͬ��
		/**
		 * ���������ά������ϲ�㣬��Ҳ��֪������ôд�ġ�����������ҡ�
		 */
		String[] PK = hths.toArray(new String[hths.size()]);
		for (int i = 0; i < PK.length; i++) {
			String string = PK[i];// use
			HashMap<String, List<String>> temp = new HashMap<String, List<String>>();
			String mrid1 = null;
			for (int j = 0; j < hvos.length; j++) {
				List<String> temp1 = new ArrayList<String>();
				Cgag000002HVO cgag000002hvo2 = hvos[j];
				String bpoid = cgag000002hvo2.getBpoid();
				mrid1 = cgag000002hvo2.getMrid();
				if (cgag000002hvo2.getBpoid() != string) {
					continue;
				}
				if (reCompare.containsKey(bpoid)) {
					// ��Ӧ��ͬ���µ�����ź������к��б�
					for (int k = 0; k < hvos.length; k++) {
						Cgag000002HVO cgag000002hvo3 = hvos[k];
						if (cgag000002hvo3.getMrid() == mrid1) {
							String mrlineid = cgag000002hvo3.getMrlineid();

							temp1.add(mrlineid);
						}
					}

					temp.put(mrid1, temp1);
				}

			}
			use.put(string, temp);

		}
		/**
		 * 2017-05-31 �޸ĵ�����
		 */

		// ����ۺ�vo
		PriceAuditVO[] agg = this.getNeesPushAgg(use);

		// �Լ۸����������и���ֵ�������м�����ݶ�Ӧ
		PriceAuditVO[] aggVOS = updatePriceAudit(agg, hvos);
		// �����Ƶ�����

		pushToM30(aggVOS, matchvo.toArray(new Cgag000002HVO[matchvo.size()]));
		preobj.setReturnObj(returnMsg);

		return preobj;
	}

	/**
	 * @param use
	 * @return
	 * @throws BusinessException
	 */
	private PriceAuditVO[] getNeesPushAgg(
			HashMap<String, HashMap<String, List<String>>> use)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		// ���ݺ�ͬ�żƻ����кţ��ҳ���Ӧ���빺����
		int num = 0;
		List<PriceAuditVO> sum = new ArrayList<PriceAuditVO>();
		HashMap<Integer, List<PriceAuditVO>> map = new HashMap<Integer, List<PriceAuditVO>>();
		for (Entry<String, HashMap<String, List<String>>> entry : use
				.entrySet()) {
			num += 1;
			List<PriceAuditVO> sumTemp = new ArrayList<PriceAuditVO>();
			/**
			 * keyΪ��ͬ�ţ�һ����ͬ�Ŵ�����һ���Ƶ���2017-06-06�����̹�ǿ����Խ���޸ģ�һ����ͬ�Ŵ���һ���۸���������
			 * Ҳ�������ڸ��ݺ�ͬ�Ų�����������Ҫ�ϳ�һ����һ��ѭ��Ϊһ����ĺϳɵļ۸���������
			 */
			HashMap<String, List<String>> mrid1 = entry.getValue();
			String mrid11 = null;
			String mrlineid = "";
			for (Entry<String, List<String>> entry1 : mrid1.entrySet()) {
				mrlineid = "";
				mrid11 = entry1.getKey();
				List<String> list = entry1.getValue();
				for (Iterator<String> iterator = list.iterator(); iterator
						.hasNext();) {
					String string = "'" + (String) iterator.next() + "',";
					mrlineid += string;
				}
				mrlineid = mrlineid.substring(0, mrlineid.length() - 1);
				// ���ܲ��ܹ���
				String sql = "select purp_priceaudit_b.pk_priceaudit_b from purp_priceaudit_b where  border='Y' and purp_priceaudit_b.mrid='"
						+ mrid11
						+ "'and purp_priceaudit_b.mrlineid in ("
						+ mrlineid + ")";
				PriceAuditVO[] rets = null;
				try {
					DataAccessUtils utils = new DataAccessUtils();
					IRowSet set = utils.query(sql);
					if (set.size() == 0) {
						continue;
						// sum.add(new PriceAuditVO());
					} else {
						String[] ids = set.toOneDimensionStringArray();
						ViewQuery<PriceAuditViewVO> query = new ViewQuery<PriceAuditViewVO>(
								PriceAuditViewVO.class);
						PriceAuditViewVO[] views = query.query(ids);
						if (null != views && views.length > 0) {
							int len = views.length;
							PriceAuditVO[] bills = new PriceAuditVO[len];
							for (int i = 0; i < len; i++) {
								bills[i] = views[i].changeToBill();
							}
							CombineBill<PriceAuditVO> combine = new CombineBill<PriceAuditVO>();
							IVOMeta headMeta = bills[0].getMetaData()
									.getParent();
							String headItemKey = headMeta.getPrimaryAttribute()
									.getName();
							combine.appendKey(headItemKey);
							rets = combine.combine(bills);
							sum.add(rets[0]);
							sumTemp.add(rets[0]);
						}
					}
				} catch (Exception e) {
					ExceptionUtils.marsh(e);
				}
			}
			map.put(num, sumTemp);

		}
		// map��������ָ�ľ�����Ҫ�γɼ��󵥵�����
		List<PriceAuditVO> ret = new ArrayList<PriceAuditVO>();
		for (Entry<Integer, List<PriceAuditVO>> entry2 : map.entrySet()) {
			List<PriceAuditItemVO> item = new ArrayList<PriceAuditItemVO>();
			List<PriceAuditVO> li = entry2.getValue();
			PriceAuditVO[] vo = li.toArray(new PriceAuditVO[li.size()]);
			PriceAuditVO agg = new PriceAuditVO();
			for (int i = 0; i < vo.length; i++) {
				PriceAuditVO priceAuditVO = vo[i];
				agg.setParentVO(vo[0].getParentVO());
				PriceAuditItemVO[] child = priceAuditVO.getChildrenVO();
				item.addAll(Arrays.asList(child));

			}
			agg.setChildrenVO(item.toArray(new PriceAuditItemVO[item.size()]));
			ret.add(agg);
		}

		return ret.size() == 0 ? new PriceAuditVO[] { new PriceAuditVO() }
				: ret.toArray(new PriceAuditVO[ret.size()]);
		/*
		 * return sum.size() == 0 ? new PriceAuditVO[] { new PriceAuditVO() } :
		 * sum.toArray(new PriceAuditVO[sum.size()]);
		 */
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

		for (PriceAuditVO priceAuditVO : agg) {

			if (priceAuditVO.getParent() == null) {
				continue;
			}

			PriceAuditItemVO[] bvos = priceAuditVO.getChildrenVO();
			for (PriceAuditItemVO priceAuditItemVO : bvos) {
				// �ٶ��Զ�����2���к�
				String mrlineid = (String) priceAuditItemVO
						.getAttributeValue("mrlineid");
				String mrid = (String) priceAuditItemVO
						.getAttributeValue("mrid");
				VOQuery<Cgag000002HVO> query = new VOQuery<Cgag000002HVO>(
						Cgag000002HVO.class);
				Cgag000002HVO[] dbVO = query.query("and mrid='" + mrid
						+ "' and mrlineid='" + mrlineid + "'", null);
				// ����
				UFDouble bpoamt = dbVO[0].getBpoamt();
				// ˰��
				UFDouble taxrate = dbVO[0].getTaxrate();
				// ����˰����
				UFDouble bpoprice = dbVO[0].getBpoprice();
				// ��ͬ�ܽ���˰
				UFDouble bposum = dbVO[0].getBposum();
				// ���۸���������ֵ
				priceAuditItemVO.setNnum(bpoamt);
				priceAuditItemVO.setNtaxrate(taxrate);
				priceAuditItemVO.setNorigprice(bpoprice);
				if (bposum == null) {
					priceAuditItemVO.setPlan_priceb(null);
				} else {
					priceAuditItemVO.setPlan_priceb(bposum.toString());

				}

			}
		}
		return agg;

	}

	/**
	 * �Ѳ���ļ۸����������Ѿ�ɸѡ���ж�����־�ģ������Ƶ�
	 * 
	 * @param aggVOS
	 * @param hvos
	 */
	public void pushToM30(PriceAuditVO[] aggVOS, Cgag000002HVO[] hvos) {
		// ����û���½ӹ���������
		if (aggVOS.length == 0) {
			return;
		}
		// ���õ�ǰ������pk_group
		InvocationInfoProxy.getInstance().setGroupId("0001N610000000000IT0");
		for (int i = 0; i < aggVOS.length; i++) {
			PriceAuditVO priceAuditVO = aggVOS[i];
			if (priceAuditVO.getParent() == null) {
				continue;
			}
			// ���ξۺ�vo��Ӧ���м���е����ݣ���Ҫ���л�д�������ɹ�����ʧ�ܱ�־��ԭ��
			List<Cgag000002HVO> dbl = new ArrayList<Cgag000002HVO>();

			try {
				// ִ�е���ת������
				SaleOrderVO saleordervo = (SaleOrderVO) PfUtilTools
						.runChangeData("28-Cxx-05", "30-Cxx-05", priceAuditVO);
				saleordervo.getParentVO().setTs(
						AppContext.getInstance().getServerTime());
				// �����̹�ǿ2017-06-22��Ҫ������Ҫ�޸����۶���Ϊ��ͨ
				// golble ��ͨ��pk 1002Z83000000000FC60
				saleordervo.getParentVO()
						.setCtrantypeid("0001N6100000000023ME");
				;
				// ��ȡ�ͻ�id�����帳ֵ
				String customerid = saleordervo.getParentVO().getCcustomerid();
				String pk_org = saleordervo.getParentVO().getPk_org();
				// ���ɵ��ݺ�
				CreateBillCodeRule rule = new CreateBillCodeRule();
				rule.setCbilltype("30");
				rule.setGroupItem("pk_group");
				rule.setCodeItem("vbillcode");
				rule.setOrgItem("pk_org");
				rule.process(new SaleOrderVO[] { saleordervo });

				SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
				// ��Ӧ�����ݿ�vo���浵Ϊ�˻�д��
				for (SaleOrderBVO saleOrderBVO : bvos) {
					// ����������ts

					saleOrderBVO
							.setTs(AppContext.getInstance().getServerTime());
					// �����̹�ǿ2017-06-26

					// ���ÿͻ�
					saleOrderBVO.setCcustmaterialid(customerid);
					//����pk_org
					saleOrderBVO.setPk_org(pk_org);
					/*****2017-05-26*****/
					String mrid = (String) saleOrderBVO
							.getAttributeValue("mrid");
					String mrlineid = (String) saleOrderBVO
							.getAttributeValue("mrlineid");
					// ��ѯ��Ӧ�����ݿ���Դ����
					VOQuery<Cgag000002HVO> query = new VOQuery<Cgag000002HVO>(
							Cgag000002HVO.class);
					Cgag000002HVO[] dbVO = query.query("and mrid='" + mrid
							+ "' and mrlineid='" + mrlineid + "'", null);
					dbl.add(dbVO[0]);
					// �����Ӧ��ֵ�����ˣ�Ȼ����ĸ�ֵ��
					// �ɹ���ͬ��----�����Զ���2

					String bpoid = dbVO[0].getBpoid();
					// �ɹ���ͬ�к�----�����Զ���3
					String bpolineid = dbVO[0].getBpolineid();
					// ����----����
					UFDouble bpoamt = dbVO[0].getBpoamt();
					// ˰��--------˰��
					UFDouble taxrate = dbVO[0].getTaxrate();
					// ����˰����---��˰����
					UFDouble bpoprice = dbVO[0].getBpoprice();
					// ��ͬ�ܽ���˰----��˰���
					UFDouble bposum = dbVO[0].getBposum();
					// ������ʼ����----�ƻ���������
					UFDate deliverystartdate = getUFdate(dbVO[0]
							.getDeliverystartdate());
					// ������ֹ����-----Ҫ���ջ�����
					UFDate deliverystopdate = getUFdate(dbVO[0]
							.getDeliverystopdate());
					// ��˾����-------�ͻ��Ϳ�Ʊ�ͻ�--------��ͷ
					String companyname = dbVO[0].getCompanyname();
					// ��ͬǩ��ʱ��------��������------��ͷ
					UFDate signedtime = getUFdate(dbVO[0].getSignedtime());
					UFDouble percent = new UFDouble(100);
					// ��˰���ۣ��򵥼��㣩
					UFDouble hsdj = bpoprice.add(bpoprice.multiply(taxrate
							.div(percent)));
					// ��˰�ϼƣ���˰����*������
					UFDouble jshj = hsdj.multiply(bpoamt);
					// ˰��
					UFDouble se = jshj.sub(bposum);
					/****************************** ��ֵ **************************************/
					// 2017-06-16����һЩ�����ֶθ�ֵ
					String custmer=getCompanyPK(companyname);
					saleOrderBVO.setVbdef2(bpoid);
					saleOrderBVO.setVbdef3(bpolineid);
					saleOrderBVO.setNastnum(bpoamt);
					saleOrderBVO.setNtaxrate(taxrate);
					// ��˰����
					saleOrderBVO.setNqtorigprice(bpoprice);
					// ��˰����
					saleOrderBVO.setNqtorigtaxprice(hsdj);
					// ��˰����˰����*������
					saleOrderBVO.setNorigmny(bposum);
					// ��˰����˰����*������
					saleOrderBVO.setNorigtaxmny(jshj);
					saleOrderBVO.setNtax(se);

					saleOrderBVO.setDsenddate(deliverystartdate);
					saleOrderBVO.setDreceivedate(deliverystopdate);
					
					//2017-07-06 �����ջ��ͻ��ͽ��������֯�ĸ�ֵ
					//���������֯
					saleOrderBVO.setCsettleorgid(getOrg());
					//�ջ��ͻ�
					saleOrderBVO.setCreceivecustid(custmer);
					saleordervo.getParentVO().setDbilldate(signedtime);
					saleordervo.getParentVO().setCinvoicecustid(
							getCompanyPK(custmer));
					saleordervo.getParentVO().setCcustomerid(
							getCompanyPK(custmer));

				}

				VORowNoUtils
						.setVOsRowNoByRule(new SaleOrderVO[] { saleordervo });
				BillInsert<SaleOrderVO> bo = new BillInsert<SaleOrderVO>();
				// ���뵽���ݿ������µ���VO������ʱ��������µ�
				SaleOrderVO[] vos = bo
						.insert(new SaleOrderVO[] { saleordervo });
				// ���뵽���ݿ��Ҫ�ѱ���ɹ������ݻ�д
				Cgag000002HVO[] matchvo = dbl.toArray(new Cgag000002HVO[dbl
						.size()]);
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					// ����ɹ�
					cgag000002hvo.setMsgflag("1");
				}
				VOUpdate<Cgag000002HVO> update = new VOUpdate<Cgag000002HVO>();
				update.update(matchvo);

			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				// e.printStackTrace();
				// ��д�м�������־
				Cgag000002HVO[] matchvo = dbl.toArray(new Cgag000002HVO[dbl
						.size()]);
				VOUpdate<Cgag000002HVO> update = new VOUpdate<Cgag000002HVO>();
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					// ����ʧ��
					cgag000002hvo.setMsgflag("2");
					cgag000002hvo.setMsginfo("���������Ƿ���ȷ");

					update.update(new Cgag000002HVO[] { cgag000002hvo });
					cgag000002hvo.getPrimaryKey();

				}

			}
		}

	}

	private UFDate getUFdate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if ("".equals(date) || date == null || " ".equals(date)) {
			return new UFDate();
		}
		Date newdate = format.parse(date);
		return UFDate.getDate(newdate);
	}

	// �����̹�ǿ2017-06-13 ��˵���Ѵ����Ƹ�Ϊ�������ˣ���Ӧ�ͻ�
	private String getCompanyPK(String name) {
		List<String> list = new ArrayList<String>();
		try {
			// ���ҿ��̵�����def2Ϊ���ض�Ӧ�ı���
			NCObject[] ncObjects = MDPersistenceService
					.lookupPersistenceQueryService().queryBillOfNCObjectByCond(
							CustomerVO.class, "def2='" + name + "'", false);
			if (ncObjects == null) {
				returnMsg += name + "û�ж�Ӧ�Ŀͻ�.";
				return "δ�пͻ���Ӧ";
			}
			for (NCObject ncObject : ncObjects) {
				CustomerVO hvo = (CustomerVO) ncObject.getContainmentObject();
				String pk = hvo.getPrimaryKey();
				list.add(pk);
			}
		} catch (MetaDataException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	public String getOrg(){
		HYPubBO bo=new HYPubBO();
		nc.vo.org.OrgVO[] vos = null;
		try {
			vos=(OrgVO[]) bo.queryByCondition(nc.vo.org.OrgVO.class, " name like '���ֹ�ó��֦�����޹�˾'");
	
		} catch (UifException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return vos.length==0?null:vos[0].getPrimaryKey();
		
	}

}
