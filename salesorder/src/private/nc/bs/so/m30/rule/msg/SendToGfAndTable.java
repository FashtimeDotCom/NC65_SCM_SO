/**
 * 
 */
package nc.bs.so.m30.rule.msg;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.agintl.entity.erpEquipment.ErpContRelat;

import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m30.opc.mecc.SaleOrderInfoVO;
import nc.vo.lm.erphtdygx.ErphtdygxHVO;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.Workbench;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.lm.IErphtdygxMaintain;
import nc.itf.so.m30.msg.ISend2Gf;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.lm.erphtdygx.AggErphtdygxHVO;
import nc.vo.lm.pgjdcght.Cgag000002HVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.gfmsg.DTCGGMCGHTREQ;
import nc.vo.so.m30.gfmsg.ErpContMain;
import nc.vo.so.m30.gfmsg.ErpContSub;

/**
 * @author wangzym
 * @version 2017��4��17�� ����1:24:34 1.post�������������⣬��Ҫ���ñ��url 2.ֻ�ܸ���Ŀǰ����֪���ݽ��и�ֵ
 */
public class SendToGfAndTable implements ISend2Gf {

	@Override
	public void process(SaleOrderVO[] vos) {
		// ���ڶԷ�ֻ��һ�ν�һ��aggvo����ֻ��forѭ��
		List<ErpContMain> aggVO = new ArrayList<ErpContMain>();
		// ���aggvo
		for (SaleOrderVO saleOrderVO : vos) {
			ErpContMain agg = sendGfData(saleOrderVO);
			aggVO.add(agg);
		}
		// ����post����
		postGfDATA(aggVO);
		// ����м����������߼�������ΪʲôҪ������֦�����м����ע��
		// fillTable(vos);

	}

	/**
	 * ����ΪʲôҪ���м����
	 * 
	 * @param vos
	 *            ����м��
	 * 
	 */
	private void fillTable(SaleOrderVO[] vos) {
		// TODO �Զ����ɵķ������
		// ÿһ���ӱ����ݶ���һ���м������
		List<Cgag000002HVO> msgvos = new ArrayList<Cgag000002HVO>();
		for (SaleOrderVO saleOrderVO : vos) {
			SaleOrderHVO hVO = saleOrderVO.getParentVO();
			SaleOrderBVO[] bvos = saleOrderVO.getChildrenVO();
			for (SaleOrderBVO saleOrderBVO : bvos) {
				Cgag000002HVO msgVO = new Cgag000002HVO();
				// ����ȡֵ
				String need = hVO.getCbiztypeid();
				// �ӱ�ȡֵ
				String mrid = (String) saleOrderBVO.getAttributeValue("mrid");
				msgVO.setMrid(mrid);
				String mrlineid = (String) saleOrderBVO
						.getAttributeValue("mrlineid");
				msgVO.setMrlineid(mrlineid);
				msgvos.add(msgVO);
			}
		}
		// �������������ǰ�ᣩ��ֱ��insert�ͺ�,�����ڴ���
		VOInsert<Cgag000002HVO> inset = new VOInsert<Cgag000002HVO>();
		inset.insert(msgvos.toArray(new Cgag000002HVO[msgvos.size()]));
	}

	/**
	 * @param aggVO
	 * @return
	 */
	private void postGfDATA(List<ErpContMain> aggVO) {
		// TODO �Զ����ɵķ������
		List<ErpContRelat> li = new ArrayList<ErpContRelat>();
		for (ErpContMain erpcontmain : aggVO) {

			RestTemplate rt = new RestTemplate();
			ErpContRelat[] resp = null;
			try {
				resp = rt
						.postForObject(
						// getRestURL("ErpContMain")
								"http://192.1.103.156:8083/conn-xi/controller/equipment/erpContract",
								// "http://192.1.103.32:8083/controller/equipment/erpContract",
								erpcontmain, ErpContRelat[].class);
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionUtils.wrappBusinessException("���ݴ����쳣��������������Ƿ�����");
			}
			// ����ֵΪ��
			if (resp == null) {
				ExceptionUtils.wrappBusinessException("����ֵΪ�գ����ɷ�����ʧ��");
			}
			for (ErpContRelat erpContRelat : resp) {
				li.add(erpContRelat);
			}
		}
		AggErphtdygxHVO[] agg = this.transAggvo(li);
		// �������ݵ��м��
		this.saveNcTalbe(agg);

		// ���浽�м���������쳣�����쳣�����������۶�������
		for (ErpContRelat erpContRelat : li
				.toArray(new ErpContRelat[li.size()])) {
			// ��ֵʧ�ܣ���������Ϣ��������׳�������Ϣ��ǰ̨
			if (erpContRelat.getType().equalsIgnoreCase("e")) {

				ExceptionUtils
						.wrappBusinessException(erpContRelat.getMessage());
			}
		}
		// �������۶���������
		this.updateSaleOrder(li);

	}

	/**
	 * @param li
	 */
	private void updateSaleOrder(List<ErpContRelat> li) {
		// TODO �Զ����ɵķ������
		VOQuery<SaleOrderBVO> query = new VOQuery<SaleOrderBVO>(
				SaleOrderBVO.class);
		List<SaleOrderBVO> bvosl = new ArrayList<SaleOrderBVO>();
		String pk_saleorder = null;
		SaleOrderHVO hvo = new SaleOrderHVO();
		for (int i = 0; i < li.size(); i++) {
			ErpContRelat orgvo = li.get(i);
			// ͨ������ź������кŲ�ѯ
			String banfn = orgvo.getBanfn();// ERP�ɹ������
			String bnfpo = orgvo.getBnfpo();// ERP�ɹ������к�
			// ��ֵ�ֶ�
			String ebeln = orgvo.getEbeln();
			Integer ebelp = orgvo.getEbelp();

			// ����ʲôһ�������������۶�����������
			SaleOrderBVO[] bvos = query.query("and  application_no='" + banfn
					+ "' and application_line='" + bnfpo + "'", null);
			/**==============2018-02-26======���ܲ����bvos Ϊ�գ������ᵼ�¸��²������۶��������ֲ��ܵڶ��δ��ɷݣ�����δ���======*/
			// �����۶�����ͷ��ֵ
			pk_saleorder = bvos[0].getCsaleorderid();
			hvo.setPrimaryKey(pk_saleorder);
			hvo.setAttributeValue("vcooppohcode", ebeln);

			// �����۶������帳ֵ
			bvos[0].setVbdef2(ebeln);
			bvos[0].setVbdef3(String.valueOf(ebelp));

			bvosl.add(bvos[0]);

		}

		VOUpdate<SaleOrderBVO> bUpdate = new VOUpdate<SaleOrderBVO>();
		VOUpdate<SaleOrderHVO> hUpdate = new VOUpdate<SaleOrderHVO>();
		/*
		 * �������¼��ٷ������ݿ�� ��Ҫ�����������ֶ�
		 */
		bUpdate.update(bvosl.toArray(new SaleOrderBVO[bvosl.size()]),
				new String[] { "vbdef2", "vbdef3" });
		// ���±��^��erp��̖ͬ������·��������ӵ�����
		hUpdate.update(new SaleOrderHVO[] { hvo },
				new String[] { "vcooppohcode" });

	}

	/**
	 * @param agg
	 *            ����nc�м��
	 */
	private void saveNcTalbe(AggErphtdygxHVO[] agg) {
		// TODO �Զ����ɵķ������
		// ���м��
		IErphtdygxMaintain insert = NCLocator.getInstance().lookup(
				IErphtdygxMaintain.class);

		try {
			insert.insert(agg, null);
		} catch (BusinessException e) {
			// TODO �Զ����ɵ� catch ��
			// ���м��ʧ��
			ExceptionUtils.wrappBusinessException("���浽�м����ִ��󣬱��δ��ɷ�����ʧ��");
			e.printStackTrace();
		}

	}

	/**
	 * @param ������������
	 * @return ��֯�õ�nc�м����
	 */
	private AggErphtdygxHVO[] transAggvo(List<ErpContRelat> li) {
		// TODO �Զ����ɵķ������
		ErpContRelat[] org = li.toArray(new ErpContRelat[li.size()]);
		List<AggErphtdygxHVO> aggvo = new ArrayList<AggErphtdygxHVO>();
		for (int i = 0; i < org.length; i++) {
			AggErphtdygxHVO agg = new AggErphtdygxHVO();
			ErphtdygxHVO hvo = new ErphtdygxHVO();
			ErpContRelat erpContRelat = org[i];
			// ��֯
			hvo.setPk_org("GLOBLE00000000000000");
			// ����
			hvo.setPk_group(AppContext.getInstance().getPkGroup());
			// ��������
			// ��ȷ����pk���Ǳ���hvo.setBillcode("SJO1");
			hvo.setBillcode("0001ZZ1000000006DLO5");
			// ����״̬
			hvo.setBillstatus(-1);
			// ��������
			hvo.setDbilldate(AppContext.getInstance().getServerTime().getDate());
			// �Ƶ���
			// �Ƶ�����ʱΪ��
			// erp�ɹ���ͬ��
			hvo.setEbeln(erpContRelat.getEbeln());
			// erp�ɹ���ͬ�к�
			hvo.setEbelp(erpContRelat.getEbelp());
			// ��ó���ۺ�ͬ��
			hvo.setSrcordergm(erpContRelat.getSrcordergm());
			// erp�ɹ������
			hvo.setBanfn(erpContRelat.getBanfn());
			// erp�ɹ������к�
			hvo.setBnfpo(erpContRelat.getBnfpo());
			// ��Ϣ״̬
			hvo.setType(erpContRelat.getType());
			// ��Ϣ��Ϣ
			hvo.setMessage(erpContRelat.getMessage());
			agg.setParentVO(hvo);
			aggvo.add(agg);

		}

		return aggvo.toArray(new AggErphtdygxHVO[aggvo.size()]);
	}

	/**
	 * ����ó����
	 * 
	 * @param saleOrderVO
	 * @return
	 */
	private ErpContMain sendGfData(SaleOrderVO saleOrderVO) {
		// TODO �Զ����ɵķ������
		// �ۺϵ���
		// ������Ǿۺ�
		ErpContMain agg = new ErpContMain();
		// �������
		// ErpContMain zb = new ErpContMain();
		// �ӱ����
		List<ErpContSub> mx = new ArrayList<ErpContSub>();
		SaleOrderHVO hVO = saleOrderVO.getParentVO();
		SaleOrderBVO[] bVOS = saleOrderVO.getChildrenVO();
		// ��������Ҫȡ��ֵ�����ź���֯
		String pk_group = hVO.getPk_group();
		String pk_org = hVO.getPk_org();

		String saleOrderNO = saleOrderVO.getParentVO().getVbillcode()
				.toString();

		agg.setSrcordergm(saleOrderNO);// ���ۺ�ͬ��
		agg.setLifnr("0001230000");// ��Ӧ�̱��룬�̶�Ϊ0001230000
		// agg.setBurks("BYQ05");//���޸Ĺ�˾���룬��Ҫȡ��Ҫȡ�ӱ�����һ�еĹ�˾����
		agg.setBurks(nvl(bVOS[0].getAttributeValue("code_facty")));// �ɹ��飬��Ҫȡ�ӱ�����һ�еĲɹ���
		agg.setEkgrp(nvl(bVOS[0].getAttributeValue("req_group")));// �ɹ��飬��Ҫȡ�ӱ�����һ�еĲɹ���
		agg.setEkorg(nvl(bVOS[0].getAttributeValue("vbdef4")));// �ɹ���֯����Ҫȡ�ӱ�����һ�еĲɹ���֯

		// �ӱ�����Ҫȡ��ֵ,�����ȡ
		for (SaleOrderBVO saleOrderBVO : bVOS) {
			ErpContSub item = new ErpContSub();
			// ���������Ϊ��

			// �ɹ������
			String banfn = (String) saleOrderBVO
					.getAttributeValue("application_no");
			// �ɹ������к�
			String bnfpo = (String) saleOrderBVO
					.getAttributeValue("application_line");// ��Ŀ����
															// application_line
			/**
			 * time 2017-08-23 �����������ι�ǿ�ȵ�Ҫ�� ����ȥ��������������
			 *  1.������������Ķ������������ڵ��ڼƻ�����ʱ���ƻ�����
			 *  2.������������Ķ���������С�ڼƻ�����ʱ ������������������
			 */
			// �ɹ�����(���ζ�������)
			UFDouble menge = (UFDouble) saleOrderBVO
					.getAttributeValue("nastnum");// ��Ŀ���� nastnum
			// �ƻ�����
			Integer plan_num = (Integer)saleOrderBVO.getAttributeValue("plan_num");
			if (menge!=null&&plan_num!=null&&menge.intValue() >= plan_num) {
				menge = new UFDouble(plan_num);
			}
			/*************************** end *********************/

			item.setBanfn(banfn);
			item.setBnfpo(bnfpo);
			item.setMenge(new BigDecimal(menge.getDouble()));
			// �ӱ�ĺ�ͬ��=����ĺ�ͬ��
			item.setSrcordergm(saleOrderNO);
			// ��ͬ����
			UFDouble norigprice = saleOrderBVO.getNqtorigprice();// ��˰����
			// nqtorigtaxprice
			item.setNetpr(BigDecimal.valueOf(norigprice.doubleValue())
					.setScale(2, BigDecimal.ROUND_HALF_UP));
			// �۸�λ
			String epein = nvl(saleOrderBVO.getAttributeValue("unit_pric"));
			if (epein != null) {

				item.setEpein(BigInteger.valueOf(Long.valueOf(epein)));
			}
			// ��������
			//2018-04-02 ���������Ҫ�󣬽�������requst_date��Ϊjiaohuodate�ֶ�
			String data = nvl(saleOrderBVO.getAttributeValue("jiaohuodate"));
			UFDate dat = new UFDate(data);
			if (data != null) {
				String date = dat.toStdString().substring(0, 4)
						+ dat.toStdString().substring(5, 7)
						+ dat.toStdString().substring(8, 10);
				item.setBadat(date);
			}

			// ���a���̶����ʞ�17��
			//���ݰ��������Ҫ��˰�����ڸ�Ϊ16��Ӧ˰��ΪJ7 2018-04-27
			//item.setMwskz("J1");
			item.setMwskz("J7");
			mx.add(item);
		}
		// agg.setCGHTTT(zb);
		agg.setErpContSubList(mx);
		return agg;

	}

	/**
	 * ͨ��pk_id��ȡ��Ӧ��url
	 */
	public String getRestURL(String telId) {
		BaseDAO baseDao = new BaseDAO();
		String rsSql = "SELECT URL_PATH FROM RT_BASEURL WHERE PK_ID='" + telId
				+ "'";
		String retString = null;
		try {
			retString = (String) baseDao.executeQuery(rsSql,
					new ResultSetProcessor() {
						@Override
						public String handleResultSet(ResultSet rs)
								throws SQLException {
							String url = null;
							while (rs.next()) {
								url = rs.getString(1);
							}
							return url;
						}
					});
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retString.trim();
	}

	/**
	 * �ж��Ƿ�Ϊ��
	 * 
	 * @param para
	 * @return
	 */
	private static String nvl(Object para) {
		if (para == null) {
			return null;
		} else {
			return para.toString();
		}
	}

}
