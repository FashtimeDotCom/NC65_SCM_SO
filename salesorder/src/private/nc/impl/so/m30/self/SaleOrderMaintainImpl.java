package nc.impl.so.m30.self;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m30.maintain.QuerySaleorderForCoopBP;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.so.m30.action.main.QuerySaleorderAction;
import nc.impl.so.m30.revise.BillQueryOrderRevise;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.pubitf.org.IDeptPubService;
import nc.pubitf.rbac.IUserPubService;
import nc.pubitf.scmf.pu.ordertranstype.pu.IOrderTranstypeFilter;
import nc.pubitf.scmf.pu.ordertranstype.pu.OrderTranstypeFilterVO;
import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.pubitf.uapbd.IPsndocPubService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.org.DeptVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.SaleOrderQuerySchemeUtils;
import nc.vo.so.m30.util.SpecialBusiUtil;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.comparator.RowNoComparator;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author gdsjw
 */

public class SaleOrderMaintainImpl implements ISaleOrderMaintain {

  @Override
  public SaleOrderVO[] querySaleorder(String[] hids) throws BusinessException {
    SaleOrderVO[] bills = null;
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    bills = query.query(hids);
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrder(String sql) throws BusinessException {
    QuerySaleorderAction query = new QuerySaleorderAction();
    return query.querySaleorder(sql);
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor21(IQueryScheme queryScheme)
      throws BusinessException {
    this.createRefSqlByQuerySchemeFor21(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      // �������βɹ���������֯
      if (bills != null && bills.length > 0) {
        QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
        String dest_pk_org =
            qsp.getQueryCondition(SaleOrderHVO.DEST_PK_ORG).getValues()[0];
        for (SaleOrderVO bill : bills) {
          bill.getParentVO().setDest_pk_org(dest_pk_org);
        }
      }
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor30Return(IQueryScheme queryScheme)
      throws BusinessException {
    this.createRefSqlByQuerySchemeFor30Return(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor32(IQueryScheme queryScheme)
      throws BusinessException {
    // ����Ĭ������
    this.createRefSqlByQuerySchemeFor32(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor4331(IQueryScheme queryScheme)
      throws BusinessException {
    this.createRefSqlByQuerySchemeFor4331(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor4816(IQueryScheme queryScheme)
      throws BusinessException {
    this.createRefSqlByQuerySchemeFor4816(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor4C(IQueryScheme queryScheme)
      throws BusinessException {
    this.createRefSqlByQuerySchemeFor4C(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrderForCoop(IQueryScheme queryScheme)
      throws BusinessException {
    return null;
  }

  @Override
  public SaleOrderVO[] querySaleOrderForCoop(IQueryScheme queryScheme,
      String pk_puorg) throws BusinessException {
    // ConditionVO[] conditionvos = queryCondition.getLogicalConds();
    // String pk_puorg = null;
    // if (null != conditionvos && conditionvos.length > 0) {
    // pk_puorg = conditionvos[0].getValue();
    // }
    String sql = this.createRefSqlByQuerySchemeForCoop(queryScheme);
    QuerySaleorderForCoopBP query = new QuerySaleorderForCoopBP();
    return query.querySaleorderForCoop(sql, pk_puorg);
  }

  @Override
  public SaleOrderViewVO[] querySaleorderForTbb(String sql)
      throws BusinessException {
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql).toOneDimensionStringArray();
    SaleOrderViewVO[] views =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    return views;
  }

  /**
   * ƴ������sql Ĭ�Ϸ��������ݺš��к�����
   * 
   * @param queryScheme
   * @return
   */
  private String createOrderSql(IQueryScheme queryScheme) {
    // ���ݵ��ݺš��к�����
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(SaleOrderHVO.class,
            SaleOrderHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(SaleOrderHVO.VBILLCODE);
    order.append(",");
    tableName =
        processor.getTableAliasOfAttribute(SaleOrderBVO.class,
            SaleOrderBVO.CROWNO);
    order.append(tableName);
    order.append(".");
    order.append(SaleOrderBVO.CROWNO);
    return order.toString();

  }

  private void createRefSqlByQuerySchemeFor21(IQueryScheme queryScheme) {
    // ���ֱ�����͵����н�������
    String[] ctrantypeids =
        M30TranTypeUtil.getInstance().queryDirectTypeAllBillTypeCode();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    queryutil.appendFixedWhr(qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();
    // �õ����ӱ����
    String mainTable = qsp.getMainTableAlias();
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.CSETTLEORGID);
    // ƴ��From
    String exeTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.NARRANGEPOAPPNUM);
    // ƴ��whereSql
    SqlBuilder whereSql = new SqlBuilder();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    whereSql.append(" and ");
    whereSql.append(exeTable + ".dr", 0);
    whereSql.append(" and " + mainTable + ".fstatusflag",
        BillStatus.AUDIT.getIntValue());
    whereSql.append(" and (" + subTable + ".bdiscountflag='N' and " + subTable
        + ".blaborflag='N') ");
    if (ctrantypeids != null && ctrantypeids.length > 0) {
      whereSql.append(" and "
          + iq.buildSQL(mainTable + ".ctrantypeid", ctrantypeids));
    }

    whereSql.append(" and abs(isnull(" + subTable + ".nnum,0) - isnull("
        + exeTable + ".narrangepoappnum,0) " + "- isnull(" + exeTable
        + ".narrangeponum,0)) > 0 ");
    OrderTranstypeFilterVO filterParamVO = new OrderTranstypeFilterVO();
    filterParamVO.setQueryScheme(queryScheme);
    // ��ѯ�����вɹ���֯��ֵ
    String[] pk_purorgs = qsp.getLogicalCondition("dest_pk_org").getValues();
    filterParamVO.setPk_purorgs(pk_purorgs);
    filterParamVO.setSrcBilltypeCode(SOBillType.Order.getCode());
    // ����
    filterParamVO.setSrcMarOidField(subTable + "." + SaleOrderBVO.CMATERIALID);
    // ��������
    filterParamVO.setSrcTrantypeField(mainTable + "."
        + SaleOrderHVO.VTRANTYPECODE);
    // ֱ�����۶����Ƿ��������֯
    filterParamVO.setSrcStockOrgField(mainTable + "."
        + SaleOrderBVO.CSENDSTOCKORGID);
    IOrderTranstypeFilter service =
        NCLocator.getInstance().lookup(IOrderTranstypeFilter.class);
    try {
      String filtersql = service.orderTranstypeFilter(filterParamVO);
      // ƴ�����β�ѯsql��
      whereSql.append(filtersql);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    // ��������where����
    qsp.appendWhere(whereSql.toString());

    queryutil.appendBusitype(queryScheme, qsp);
  }

  private void createRefSqlByQuerySchemeFor30Return(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    queryutil.appendFixedWhr(qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    // String subTable =
    // qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
    // + SaleOrderBVO.BBOUTENDFLAG);
    //
    // // from
    // qsp.appendFrom(" inner join so_saleorder_exe  ");
    // qsp.appendFrom(" on so_saleorder_b.csaleorderbid"
    // + " = so_saleorder_exe.csaleorderbid");
    qsp.appendFrom(" left outer join so_m30trantype ");
    // jilu for 633 �ϲ�BUG �����˻�������ά���������۶����˻������ս��涩�����������ݷ���
    // qsp.appendFrom(" on so_saleorder.vtrantypecode"
    // + " = so_m30trantype.vtrantypecode ");
    qsp.appendFrom(" on so_saleorder.CTRANTYPEID"
        + " = so_m30trantype.CTRANTYPEID ");
    qsp.appendFrom(" and so_saleorder.pk_group = so_m30trantype.pk_group ");
    // end

    SqlBuilder where = new SqlBuilder();
    where.append(" and so_m30trantype.fsalemode in ("
        + SaleMode.MODE_COMMON.value() + ","
        + SaleMode.MODE_COMMONRETURN.value() + " ,"
        + SaleMode.MODE_COMMONRETURNCHANGE.value() + " ,"
        + SaleMode.MODE_RETURNCHANGE.value() + ") ");
    // ֻ�ܲ�ѯ(�ۼ�ʵ������-�ۼ��˻�����>0)�����۶���
    // �����߽�������Ϊ"ֱ�˲ɹ�"&&(�ۼƳ�������=0)&&(������-�ۼ��˻�����>0)���۶���
    // �ٽ�һ��ͬ������Ľ������ͣ��������۶����˻��ͻ��ѯ���ظ��Ķ�����
    where.append(" and isnull(so_m30trantype.dr,0)=0 ");
    where
        .append(" and ( isnull(ntotaloutnum,0) > isnull(ntotalreturnnum,0)"
            + " or (so_m30trantype.fdirecttype ="
            + DirectType.DIRECTTRAN_PO.getIntValue()
            + " and isnull(ntotaloutnum,0) = 0 and isnull(nnum,0) > isnull(ntotalreturnnum,0)))");
    // ��������where����
    qsp.appendWhere(where.toString());

    queryutil.appendBusitype(queryScheme, qsp);
  }

  /**
   * ��Scheme �м���ת��Ĭ������ �����š�dr������״̬�ȣ� �����۷�Ʊ�ṩ��
   * 
   * @param queryScheme
   */
  private void createRefSqlByQuerySchemeFor32(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    queryutil.appendFixedWhr(qsp);
    queryutil.appendRemoveIDSWhr(queryScheme, qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.class,
            SaleOrderBVO.BBOUTENDFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.BINVOICENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BBINVOICENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    // ��������where����
    qsp.appendWhere(where.toString());

    queryutil.appendBusitype(queryScheme, qsp);
  }

  private void createRefSqlByQuerySchemeFor4331(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    queryutil.appendFixedWhr(qsp);
    queryutil.appendRemoveIDSWhr(queryScheme, qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.BBOUTENDFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BLABORFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BDISCOUNTFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.BSENDENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BBSENDENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    // ��������where����
    qsp.appendWhere(where.toString());

    queryutil.appendBusitype(queryScheme, qsp);
  }

  private void createRefSqlByQuerySchemeFor4816(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    // queryutil.appendFixedWhr(qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.appendIDIsNotNull(SaleOrderHVO.APPROVER);
    where.append(") ");

    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.BADVFEEFLAG, UFBoolean.TRUE.toString());
    where.append(") ");

    // ��������where����
    qsp.appendWhere(where.toString());
    queryutil.appendBusitype(queryScheme, qsp);
  }

  private void createRefSqlByQuerySchemeFor4C(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    queryutil.appendFixedWhr(qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.BBOUTENDFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(mainTableAlias + "." + SaleOrderHVO.BOUTENDFLAG,
        UFBoolean.FALSE);

    where.append(" and ");
    where.append(subTable + "." + SaleOrderBVO.BBOUTENDFLAG, UFBoolean.FALSE);

    where.append(" and ");
    where.append("abs(isnull(" + SaleOrderBVO.NTOTALNOTOUTNUM + ",0)) ");
    where.append(" < ");
    where.append("abs(" + subTable + "." + SaleOrderBVO.NNUM + ") ");

    qsp.appendFrom(" inner join so_m30trantype on " + mainTableAlias
        + ".ctrantypeid = so_m30trantype.ctrantypeid");

    where.append(" and ");

    Integer[] dirtypes =
        new Integer[] {
          DirectType.DIRECTTRAN_NO.getIntegerValue(),
          DirectType.DIRECTTRAN_PO.getIntegerValue()
        };
    where.append("so_m30trantype." + M30TranTypeVO.FDIRECTTYPE, dirtypes);
    where.append(" and (");
    where.append("so_m30trantype." + M30TranTypeVO.BARRANGEOUT,
        UFBoolean.FALSE);
    where.append(" or (");
    where.append("so_m30trantype." + M30TranTypeVO.BARRANGEOUT, UFBoolean.TRUE);
    where.append(" and ");
    where.append(" isnull(" + SaleOrderBVO.NTOTALNOTOUTNUM + ",0)");
    where.append(" = 0 ");
    where.append("))");
    // ��������where����
    qsp.appendWhere(where.toString());

    queryutil.appendBusitype(queryScheme, qsp);
  }

  private String createRefSqlByQuerySchemeForCoop(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();
    // �õ����ӱ����
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.BBOUTENDFLAG);
    String mainTableAlias = qsp.getMainTableAlias();
    qsp.appendFrom(" inner join so_saleorder_exe  ");
    qsp.appendFrom(" on " + subTable + ".csaleorderbid"
        + " = so_saleorder_exe.csaleorderbid");
    SqlBuilder where = new SqlBuilder();
    where.append(" and isnull(nnum,0) - isnull(narrangepoappnum,0)");
    where.append("- isnull(narrangeponum,0) > 0");

    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.FSTATUSFLAG, BillStatus.AUDIT.getIntegerValue());
    where.append(") ");

    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.BCOOPTOPOFLAG, UFBoolean.FALSE);
    where.append(") ");

    // �����������Է���������null ����˵Эͬpo���ɵ�so �����ٱ�poЭͬ���յ�
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.VCOOPPOHCODE);
    where.append(" is null ) ");

    // ��������where����
    qsp.appendWhere(where.toString());

    String select = "select distinct(" + mainTableAlias + ".csaleorderid) ";
    // sql���
    String sql = select + qsp.getFinalFromWhere();

    return sql.toString();
  }

  /**
   * 
   * @param scheme
   *          ��ѯsql
   * @param order
   *          ����sql
   * @return
   */
  private SaleOrderVO[] querySaleOrderVOForSource(IQueryScheme scheme,
      String order) {
    SchemeViewQuery<SaleOrderViewVO> query =
        new SchemeViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] views = query.query(scheme, order);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    for (SaleOrderViewVO view : views) {
      SaleOrderHVO headvo = view.getHead();
      SaleOrderBVO bodyvo = view.getBody();
      headvo.setPk_group(bodyvo.getPk_group());
      headvo.setPk_org(bodyvo.getPk_org());
      headvo.setDbilldate(bodyvo.getDbilldate());
      headvo.setNdiscountrate(bodyvo.getNdiscountrate());
    }
    SaleOrderVO[] queryVos =
        new CombineViewToAggUtil<SaleOrderVO>(SaleOrderVO.class,
            SaleOrderHVO.class, SaleOrderBVO.class).combineViewToAgg(views,
            SaleOrderHVO.CSALEORDERID);
    // �����к�����(�����֣�
    for (SaleOrderVO vo : queryVos) {
      RowNoComparator c = new RowNoComparator(SaleOrderBVO.CROWNO);
      Arrays.sort(vo.getChildrenVO(), c);
    }
    return queryVos;
  }

  @Override
  public SaleOrderVO[] querySaleOrderFor5801(IQueryScheme queryScheme)
      throws BusinessException {

    this.createRefSqlByQuerySchemeFor5801(queryScheme);
    // ƴ������sql
    String ordersql = this.createOrderSql(queryScheme);
    SaleOrderVO[] bills = null;
    try {
      bills = this.querySaleOrderVOForSource(queryScheme, ordersql);
      // �������ν��ں�ͬ�Ķ�������֯
      if (bills != null && bills.length > 0) {
        QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
        String dest_pk_org =
            qsp.getQueryCondition(SaleOrderHVO.DEST_PK_ORG).getValues()[0];
        for (SaleOrderVO bill : bills) {
          bill.getParentVO().setDest_pk_org(dest_pk_org);
        }
      }
      return bills;

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;

  }

  private void createRefSqlByQuerySchemeFor5801(IQueryScheme queryScheme) {
    // ������з�ֱ�˽�������
    String[] ctrantypeids =
        M30TranTypeUtil.getInstance().queryNotDirectTypeAllBillTypeCode();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    SaleOrderQuerySchemeUtils queryutil = new SaleOrderQuerySchemeUtils();
    // new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    queryutil.appendFixedWhr(qsp);
    queryutil.appendRemoveIDSWhr(queryScheme, qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.class,
            SaleOrderBVO.BBOUTENDFLAG);
    String mainTable = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    if (ctrantypeids != null && ctrantypeids.length > 0) {
      where.append(" and "
          + iq.buildSQL(mainTable + ".ctrantypeid", ctrantypeids));
    }
    
    //=== lijj �Ǹֲ�Ʒó�ײ� ���۶����ͽ��ں�ͬͬ������ ������������ ȥ��������������==== 
    SpecialBusiUtil busiUtil = new SpecialBusiUtil();
    
    if(!busiUtil.isTheDept()){
    	where.append(" and " + mainTable + ".fstatusflag",
    			BillStatus.AUDIT.getIntValue());
    }
    //=== lijj �Ǹֲ�Ʒó�ײ� ���۶����ͽ��ں�ͬͬ������ ������������ ȥ��������������==== 
    
    // û����ر�
    //���ݰ�����Ŀ���ι�ǿҪ��ȥ��BBOUTENDFLAG���ж�
    /*where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BBOUTENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");*/
    // ��Դ����û���
    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BARRANGEDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    // ��������where����
    qsp.appendWhere(where.toString());

    queryutil.appendBusitype(queryScheme, qsp);

  }

  /**
   * ˢ�������޶����� pkΪ�����޶�����
   */
  @Override
  public AggregatedValueObject querySaleOrderFor30Revise(
      Class<? extends AbstractBill> c, String pk) throws BusinessException {
    IBillQueryService billQuery =
        NCLocator.getInstance().lookup(IBillQueryService.class);
    AggregatedValueObject newVO = billQuery.querySingleBillByPk(c, pk);
    SaleOrderHistoryVO[] HistoryVOs = null;
    if (newVO == null) {
      newVO = billQuery.querySingleBillByPk(SaleOrderVO.class, pk);
      if (newVO == null) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006011_0", "04006011-0527")/* ���۶����ѱ�ɾ���������б����ˢ�µ��ݡ�*/);
      }
      Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
      newVO = trans.transfer30TOOrderhisVO((SaleOrderVO) newVO);
      newVO.getParentVO().setAttributeValue(
          SaleOrderHistoryHVO.CORDERHISTORYID, pk);
      return newVO;
    }
    else {
      HistoryVOs =
          new BillQueryOrderRevise().joinSaleOrderexe(new SaleOrderHistoryVO[] {
            (SaleOrderHistoryVO) newVO
          });
    }
    if (HistoryVOs == null) {
      return null;
    }
    return HistoryVOs[0];
  }
  
}
