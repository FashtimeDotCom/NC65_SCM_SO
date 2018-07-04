package nc.vo.so.m4331.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.ic.reserve.ReserveQueryServer;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * Ԥ�������ж�
 * 
 * @since 6.0
 * @version 2011-5-14 ����01:58:05
 * @author ף����
 */
public class ReverseConditionRule {
	// �������Ԥ���ķ�����
	private Map<String, DeliveryBVO> bvoMap;

	private DeliveryBVO[] bvos;

	private StringBuffer errMsg;

	// ���������Ƿ��ҲԤ����ʶ
	private Map<String, UFBoolean> matMap;

	private Map<String, UFBoolean> srcMap;

	public ReverseConditionRule(DeliveryBVO[] vos) {
		if (null == vos) {
			ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4006002_0", "04006002-0163")
			/* ��ѡ�б����н���Ԥ���� */);
		}
		this.bvos = vos;
		this.matMap = new HashMap<String, UFBoolean>();
		this.bvoMap = new HashMap<String, DeliveryBVO>();
		this.srcMap = new HashMap<String, UFBoolean>();
		for (DeliveryBVO bvo : this.bvos) {
			this.bvoMap.put(bvo.getCdeliverybid(), bvo);
		}
		this.initMatMap();
		this.initSrcMap();
	}

	/**
	 * �ж��Ƿ������Ԥ��
	 * 
	 * @param bvos
	 * @return
	 */
	public void checkReverse() {
		this.errMsg = new StringBuffer();
		for (DeliveryBVO bvo : this.bvos) {
			this.checkRowClose(bvo);
			this.checkNum(bvo);
			// ����Ƿ��Ԥ������ q q
			this.checkIsReserveMaterial(bvo);
			this.checkIsReserveSrc(bvo);
		}
		if (this.errMsg.length() > 0) {
			ExceptionUtils.wrappBusinessException(this.errMsg.toString());
		}
	}

	/*
	 * ��������Ƿ���Ԥ������
	 * 
	 * @param vo
	 * 
	 * @throws BusinessException
	 */
	private void checkIsReserveMaterial(DeliveryBVO bvo) {
		String bid = bvo.getCdeliverybid();
		if (!this.bvoMap.containsKey(bid)) {
			return;
		}
		String pk_material = bvo.getCmaterialid();
		UFBoolean remain = this.matMap.get(pk_material);
		if (!remain.booleanValue()) {
			this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006002_0", "04006002-0164", null,
					new String[] { bvo.getCrowno() })/*
													 * �������У�{0}�����ϲ��ǿ�Ԥ������ ������Ԥ����
													 */);
			this.errMsg.append("\n");
			this.bvoMap.remove(bvo.getCdeliverybid());
		}
	}

	private void checkIsReserveSrc(DeliveryBVO bvo) {
		String srcbid = bvo.getCsrcbid();
		UFBoolean isReverse = this.srcMap.get(srcbid);
		if (isReverse.booleanValue()) {
			this.bvoMap.remove(bvo.getCdeliverybid());
			this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006002_0", "04006002-0165", null,
					new String[] { bvo.getCrowno() })/*
													 * �������У�{0}����Դ�����Ѿ�����Ԥ��
													 * ������Ԥ����
													 */);
			this.errMsg.append("\n");
		}
	}

	private void checkNum(DeliveryBVO bvo) {
		String bid = bvo.getCdeliverybid();
		if (!this.bvoMap.containsKey(bid)) {
			return;
		}
		UFDouble nnum = bvo.getNnum();
		UFDouble outNum = bvo.getNtotaloutnum();
		UFDouble reqNum = bvo.getNreqrsnum();
		UFDouble value = MathTool.add(outNum, reqNum);
		if (MathTool.compareTo(nnum, value) < 0) {
			this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006002_0", "04006002-0166", null,
					new String[] { bvo.getCrowno() })/*
													 * �������У�{0}��������С���ۼƳ���������Ԥ������֮��
													 * ������Ԥ����
													 */);
			this.errMsg.append("\n");
			this.bvoMap.remove(bvo.getCdeliverybid());
		}
	}

	/**
	 * ������Ƿ�ر�
	 * 
	 * @param bvos
	 */
	private void checkRowClose(DeliveryBVO bvo) {
		if (!this.bvoMap.containsKey(bvo.getCdeliverybid())) {
			return;
		}
		UFBoolean flag = bvo.getBoutendflag();
		if (null == flag || !flag.booleanValue()) {
			return;
		}
		this.bvoMap.remove(bvo.getCdeliverybid());
		this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006002_0", "04006002-0167", null,
				new String[] { bvo.getCrowno() })/* ��������{0}�Ѿ��йرգ�������Ԥ�� */);
		this.errMsg.append("\n");
	}

	private void initMatMap() {
		// ���������֯��ͬһ�ŷ������������ķ��������֯��һ����
		String csendstockorgid = null;
		// ���淢������������id
		Set<String> materialSet = new HashSet<String>();
		for (DeliveryBVO bvo : this.bvos) {
			String material = bvo.getCmaterialid();
			if (null == csendstockorgid || "".equals(csendstockorgid)) {
				csendstockorgid = bvo.getCsendstockorgid();
			}
			materialSet.add(material);
		}
		String[] pk_materials = new String[materialSet.size()];
		materialSet.toArray(pk_materials);
		// ��������id ���������֯��ѯ������Ϣ
		Map<String, MaterialStockVO> stockMap = MaterialPubService
				.queryMaterialStockInfo(pk_materials, csendstockorgid,
						new String[] { MaterialStockVO.REMAIN,
								MaterialStockVO.PK_MATERIAL });
		if (null == stockMap || stockMap.size() == 0) {
			ExceptionUtils
					.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("4006002_0", "04006002-0159")/*
																	 * �����е�����û�з��䵽��Ӧ�ķ��������֯
																	 * ��
																	 */);
			return;
		}
		for (DeliveryBVO bvo : this.bvos) {
			String pk_material = bvo.getCmaterialid();
			MaterialStockVO stockvo = stockMap.get(pk_material);
			UFBoolean remain = stockvo.getRemain();
			if (null == remain || !remain.booleanValue()) {
				this.matMap.put(pk_material, UFBoolean.FALSE);
				continue;
			}
			this.matMap.put(pk_material, UFBoolean.TRUE);
		}
	}

	/*
	 * �����Դ�����Ƿ�����Ԥ�� ���ҷ���û�����ε���û������Ԥ����vo
	 */
	private void initSrcMap() {
		String srctype = this.bvos[0].getVsrctype();
		if (SOBillType.Order.getCode().equals(srctype)
				|| TOBillType.TransOrder.getCode().equals(srctype)) {
			this.initOrderReverse(srctype);
		}
	}

	/*
	 * �����Դ�����Ƿ�����Ԥ��
	 * 
	 * @param views
	 * 
	 * @return
	 */
	private void initOrderReverse(String srctype) {
		// ������Դ����id
		Set<String> idSet = new HashSet<String>();
		for (DeliveryBVO bvo : this.bvos) {
			idSet.add(bvo.getCsrcbid());
		}
		if (idSet.size() != 0) {
			String[] ids = new String[idSet.size()];
			ReserveQueryServer service = NCLocator.getInstance().lookup(
					ReserveQueryServer.class);
			try {
				this.srcMap = service.hasSrcBillReserved(srctype,
						idSet.toArray(ids));
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

}
