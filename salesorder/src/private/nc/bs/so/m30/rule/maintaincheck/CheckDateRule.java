package nc.bs.so.m30.rule.maintaincheck;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.AppTool;

/**
 * @description �������ں͵�������У��
 * @scene ���۶����������޸ı���ǰ
 * @param ��
 * 
 * 
 * @since 6.3
 * @version 2013-8-19 ����03:04:26
 * @author tianft
 */
public class CheckDateRule implements IRule<SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		for (SaleOrderVO vo : vos) {
			this.checkDate(vo);
		}
	}

	private void checkDate(SaleOrderVO bill) {
		SaleOrderHVO header = bill.getParentVO();
		UFDate dbilldate = header.getDbilldate();
		SaleOrderBVO[] items = bill.getChildrenVO();
		// ��ͬʵ����Ч���� add by quyt 20150127
		UFDate billBeginDate = null;

		Set<String> csrcIds = new HashSet<String>();
		for (SaleOrderBVO item : items) {
			int vostatus = item.getStatus();
			if (vostatus == VOStatus.DELETED || vostatus == VOStatus.UNCHANGED) {
				// �����ɾ����û�仯���У�
				continue;
			}
			// �ƻ������ա��ƻ�������
			UFDate dsenddate = item.getDsenddate();
			UFDate dreceivedate = item.getDreceivedate();
			if (AppTool.getInstance().compareDate(dsenddate, dbilldate) < 0) {

				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0088")/*
														 * @res "��������Ӧ���ڵ��ڵ�������!"
														 */);
			}
			if (AppTool.getInstance().compareDate(dreceivedate, dsenddate) < 0) {

				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0089")/*
														 * @res
														 * "Ҫ���ջ����ڲ���С�ڼƻ���������!"
														 */);
			}
			// begin ��������Ӧ����Ч������ add by quyt 20141216
			String fromtype = item.getVsrctype();
			if (PubAppTool.isEqual(CTBillType.SaleDaily.getCode(), fromtype)) {
				String csrcId = item.getCsrcid();
				csrcIds.add(csrcId);
			}
		}
		// add by wangshu6 2015-1-28 �п�
		if(csrcIds.size()==0){
		  return;
		}
		// end 
		// ���ڿ���ͬʱ��Ӵ������ݣ�����ʹ����ʱ��
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" select max(a.ACTUALVALIDATE) from CT_SALE a where a.dr = 0 and ");
		String name = "a.pk_ct_sale";
		String[] ids = csrcIds.toArray(new String[csrcIds.size()]);
		IDQueryBuilder inSql = new IDQueryBuilder();
		String sql = inSql.buildSQL(name, ids);
		whereSql.append(sql);
		DataAccessUtils utils = new DataAccessUtils();
		IRowSet rs = utils.query(whereSql.toString());
		String[][] result = rs.toTwoDimensionStringArray();
		if(result != null && result[0][0] != null){			
			// ��ȡ���ʵ����Ч����
			billBeginDate = new UFDate(result[0][0]);
		}else{
			ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0495")/*��ȡ��ͬ���ʵ����Ч����ʱʧ�ܣ����ܵ�ԭ���ǣ�ͨ����ͬ����ʱ�����˲����������²�ѯ��ͬ��Ϣ��*/);
		}

		if (null != billBeginDate
				&& AppTool.getInstance().compareDate(dbilldate, billBeginDate) < 0) {
			ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0496", null, new String[]{dbilldate.toString().substring(0,10),billBeginDate.toString().substring(0,10)})/* ��ǰ�������ڡ� {0} �����������ͬ��Ч���ڡ�{1}��֮�� */);
		}
		// end

	}
}
