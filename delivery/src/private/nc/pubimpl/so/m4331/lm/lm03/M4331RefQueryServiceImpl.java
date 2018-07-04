package nc.pubimpl.so.m4331.lm.lm03;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m4331.lm.lm03.IM4331RefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * <p>������Ҫʵ�ֹ��ܣ�</p>
 *
 * <li>���� ����</li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017��3��1�� ����8:53:00
 */
public class M4331RefQueryServiceImpl implements IM4331RefQueryService {

	/* ���� Javadoc��
	 * @see nc.pubitf.so.m4331.lm.lm03.IM4331RefQueryService#queryM4331ForLM03(nc.ui.querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public DeliveryVO[] queryM4331ForLM03(IQueryScheme queryScheme)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		//���˵�������ͨ������Ϣ
		//ֻ������ͨ��������ݿ���������
		//ͬʱ��Ҫ�˵��Ѿ���ɵ����� ������ͨ�������������ͬʱ��Ҫ�˵��Ѿ���ɵ����� =��Ҫ�� �����������
		String sql = queryScheme.getTableJoinFromWhereSQL().getWhere();//.getWhereSQLOnly();
		sql = sql + " and fstatusflag = 2 and dr = 0 ";

		NCObject[] obs = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DeliveryHVO.class, sql, false);
		if(obs == null) return null;
		DeliveryVO[] bills = new DeliveryVO[obs.length];
		for(int i= 0; i<obs.length;i++){
			bills[i] = (DeliveryVO)obs[i].getContainmentObject();
		}
		
		return bills;

		
/*		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		String maintablename = processor.getMainTableAlias();
		SqlBuilder sqlbuild = new SqlBuilder();
		//ƴ��whereSql��Ҫ����
		sqlbuild.append(" and ");
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		sqlbuild.append(maintablename + ".pk_group",pk_group);
		//���ε��ݷ�����-����״̬Ϊ����ͨ��
		sqlbuild.append(" and ");
		sqlbuild.append(maintablename + ".fstatusflag",2);
*/		
		//String chidtable = processor.getTableAliasOfAttribute("et_ckspzbb.cmaterialvid");
		//ʹ�ù�������
		//sqlbuild.append(" and ");
		//cktzdnum(�ݶ�),���ε��� ����������Ԫ������δ���
		//sqlbuild.append(" abs(isnull(" + chidtable +".outnum,0)) > abs(isnull(" + chidtable +".cktzdnum,0))");
		//processor.appendWhere(sqlbuild.toString());
		//processor.appendRefTrantypeWhere("4331", "LM03", "tran_code");
		//ƴ������sql
/*		String ordersql = this.createOrderSql(queryScheme);
		SchemeViewQuery<DeliveryViewVO> query =
				new SchemeViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
		nc.vo.so.m4331.entity.DeliveryViewVO[] views = query.query(queryScheme, ordersql);
		if (ArrayUtils.isEmpty(views)) {
			return null;
		}
		for (DeliveryViewVO view : views) {
			DeliveryHVO headvo = view.getHead();
			DeliveryBVO bodyvo = view.getItem();
			String pk_org = (String) bodyvo.getAttributeValue("pk_org");
			String pk_org_v = (String) bodyvo.getAttributeValue("pk_org_v");
			headvo.setAttributeValue("pk_org", pk_org);
			headvo.setAttributeValue("pk_org_v", pk_org_v);
		}
		
		DeliveryVO[] queryVos = 
				new CombineViewToAggUtil<DeliveryVO>(
						DeliveryVO.class,DeliveryHVO.class,DeliveryBVO.class)
						.combineViewToAgg(views, "cdeliveryid");
		return queryVos;
*/	}
	
	/**
	 * ƴ������sqlĬ�Ϸ��������ݺţ��к�����
	 * @param queryScheme
	 * @return
	 */
	private String createOrderSql(IQueryScheme queryScheme) {
		// TODO �Զ����ɵķ������
		//���ݵ��ݺš��к�����
		SqlBuilder order = new SqlBuilder();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		order.append(" order by ");
		String tableName = processor.getTableAliasOfAttribute(DeliveryHVO.class,"vbillcode");
		order.append(tableName);
		order.append(".");
		order.append("vbillcode");
		order.append(",");
		tableName = processor.getTableAliasOfAttribute(DeliveryBVO.class,"crowno");
		order.append(tableName);
		order.append(".");
		order.append("crowno");
		return order.toString();
	}

}
