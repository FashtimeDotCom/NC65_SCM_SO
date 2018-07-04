package nc.impl.so.m30.revise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.scmpub.page.BillPageLazyQuery;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.itf.so.m30.revise.ISaleOrderReviseMaintainApp;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;

/**
 * ���۶����޶���ҳ��ѯʵ��
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public class SaleOrderReviseMaintainAppImpl implements
    ISaleOrderReviseMaintainApp {

  @Override
  public PageQueryVO queryM30ReviseApp(IQueryScheme scheme)
      throws BusinessException {
    SaleOrderHistoryVO[] orderhisVO = null;
    PageQueryVO page = null;
    try {
      QuerySchemeProcessor processor = new QuerySchemeProcessor(scheme);
      String headTableName = processor.getMainTableAlias();
      StringBuffer str = new StringBuffer();
      str.append(" and (");
      str.append(headTableName);
      str.append(".fstatusflag = ");
      str.append(BillStatus.AUDIT.getIntValue());
      str.append(" or ");
      str.append(headTableName);
      str.append(".fstatusflag = ");
      str.append(BillStatus.FREEZE.getIntValue());
      str.append(")");
      processor.appendWhere(str.toString());
      processor.appendCurrentGroup();
      processor.appendFuncPermissionOrgSql();
      // ����
      SqlBuilder order = new SqlBuilder();
      order.append("order by ");
      order.append(headTableName);
      order.append(".vbillcode");
      SchemeVOQuery<SaleOrderHVO> query =
          new SchemeVOQuery<SaleOrderHVO>(SaleOrderHVO.class, new String[] {
            SaleOrderHVO.CSALEORDERID
          });
      ISuperVO[] parents = query.query(scheme, order.toString());
      int length = parents.length;
      if (length == 0) {
        return new PageQueryVO(new String[0], Constructor.construct(
            SaleOrderHistoryVO.class, 0));
      }
      String[] pks = new String[length];
      for (int i = 0; i < length; i++) {
        pks[i] = parents[i].getPrimaryKey();
      }
      int recordInPage =
          ValueUtils.getInt(scheme.get(PageQueryVO.RECORD_IN_PAGE), -1);
      if (recordInPage != -1) {
        length = Math.min(recordInPage, pks.length);
      }
      else {
        length = pks.length;
      }
      String[] idsInFirstPage = new String[length];
      for (int i = 0; i < length; i++) {
        idsInFirstPage[i] = pks[i];
      }
      orderhisVO = getOrderHisVOBySaleOrder(idsInFirstPage);
      page = new PageQueryVO(pks, orderhisVO);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return page;
  }

  private SaleOrderHistoryVO[] getOrderHisVOBySaleOrder(String[] ids)
      throws BusinessException {
    BillPageLazyQuery<SaleOrderVO> query =
        new BillPageLazyQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] bills = query.queryPageBills(ids);
    if (ArrayUtil.isEmpty(ids)) {
      return null;
    }
    if (ArrayUtil.isEmpty(bills)) {
      // ��Ϣ���Ĵ򿪽ڵ��ʱids�������۶����޶�����
      BillPageLazyQuery<SaleOrderHistoryVO> queryhistorvo =
          new BillPageLazyQuery<SaleOrderHistoryVO>(SaleOrderHistoryVO.class);
      SaleOrderHistoryVO[] historyvos=  queryhistorvo.queryPageBills(ids);
      if(historyvos==null || historyvos.length==0){
        return historyvos;
      }
      return  new BillQueryOrderRevise().joinSaleOrderexe(historyvos);
    }

    // 1. ��ѯ���۶������޶����е��޶������汾��Ӧ���޶�vo����ѯ���Ľ�����Ѿ��޶����ģ�û���޶����Ĳ�ѯ������
    SaleOrderHistoryVO[] orderhisVOs = null;
    try {
      orderhisVOs = this.queryMaxVersionFromReviseHistoryBySaleOrder(ids);
    }
    catch (BusinessException ex) {
      ExceptionUtils.marsh(ex);
    }

    // 2. ���ҳ����ʾ�����۶����޶�vo
    List<SaleOrderHistoryVO> orderHislist = getHisVOToShow(bills, orderhisVOs);
    return ListUtil.toArray(orderHislist);
  }

  /**
   * �������۶�����ѯ���۶����޶����޶����°汾�ĵ��� ��û�޶������ؿ�
   * 
   * @param hid
   *          ���۶�������
   * @return
   * @throws BusinessException
   */
  private SaleOrderHistoryVO[] queryMaxVersionFromReviseHistoryBySaleOrder(
      String[] saleoderIDs) throws BusinessException {
    IDQueryBuilder sqlBuilder = new IDQueryBuilder();
    String insql =
        sqlBuilder.buildSQL("h." + SaleOrderHistoryHVO.CSALEORDERID,
            saleoderIDs);
    SaleOrderHistoryVO[] bills = null;
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select a.corderhistoryid from so_orderhistory a, ");
    sql.append(" (select csaleorderid, max(iversion) as iversion from so_orderhistory h ");
    sql.append(" where ");
    sql.append(insql);
    sql.append(" and h.dr = 0 ");
    sql.append(" group by csaleorderid) maxhs ");
    sql.append(" where a.csaleorderid = maxhs.csaleorderid ");
    sql.append(" and a.iversion = maxhs.iversion ");
    sql.append(" and a.dr = 0 ");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }
    BillQuery<SaleOrderHistoryVO> query =
        new BillQuery<SaleOrderHistoryVO>(SaleOrderHistoryVO.class);
    bills = query.query(set.toOneDimensionStringArray());
    return new BillQueryOrderRevise().joinSaleOrderexe(bills);
  }

  /**
   * ���ҳ����ʾ�����۶����޶�vo
   * 
   * @param bills
   *          ���۶���
   * @param orderhisVOs
   *          �Ѿ�������޶��汾�����۶����޶�vo
   * @return ��ʾ�ڽ�����޶�vos
   */
  private List<SaleOrderHistoryVO> getHisVOToShow(SaleOrderVO[] bills,
      SaleOrderHistoryVO[] orderhisVOs) {
    // 1. ���ÿһ�����۶�����Ӧ���޶���߰汾 ���û���޶��汾��Ϊ��

    // �����۶����޶����汾vo���һ��<���۶����޶�caleorderid,�Ѿ��޶��������汾vo>��Map
    Map<String, SaleOrderHistoryVO> saleorderIDAndHisVOMap =
        getSaleOrderIDAndMaxVersionHisVOMap(orderhisVOs);

    List<SaleOrderHistoryVO> orderHislist = new ArrayList<SaleOrderHistoryVO>();

    for (SaleOrderVO vo : bills) {
      SaleOrderHistoryVO hisVO = null;
      if (saleorderIDAndHisVOMap != null) {
        String csaleorderID = vo.getPrimaryKey();
        hisVO = saleorderIDAndHisVOMap.get(csaleorderID);
      }

      // 2. û�����°汾 modify by zhangby5 �޶����Ӧ�����޶�������ݣ������������۶���������  ���ߵ�ǰ���۶����������°汾
      if (this.isShowOrderVO(hisVO,vo)) {
        // �����۶���voת�����޶���ʷvo��ʾ
        Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
        hisVO = trans.transfer30TOOrderhisVO(vo);
      }
      orderHislist.add(hisVO);
    }
    return orderHislist;
  }

  private boolean isShowOrderVO(SaleOrderHistoryVO hisVO, SaleOrderVO vo) {
    if (null == hisVO) {
      return true;
    }
    Integer hisversion = hisVO.getParentVO().getIversion();
    Integer version = vo.getParentVO().getIversion();
    if (hisversion == null) {
      return true;
    }
    if (version == null) {
      return false;
    }
    if (hisversion.intValue() == version.intValue()) {
      return true;
    }
    return false;
  }

  /**
   * �����۶����޶����汾vo���һ��<���۶����޶�caleorderid,�Ѿ��޶��������汾vo>��Map
   * 
   * @param orderhisVOs
   *          �Ѿ������汾�ŵ��޶�vo
   * @return
   */
  private Map<String, SaleOrderHistoryVO> getSaleOrderIDAndMaxVersionHisVOMap(
      SaleOrderHistoryVO[] orderhisVOs) {
    Map<String, SaleOrderHistoryVO> saleorderIDAndHisVOMap =
        new HashMap<String, SaleOrderHistoryVO>();
    if (orderhisVOs != null) {
      for (SaleOrderHistoryVO saleOrderHistoryVO : orderhisVOs) {
        saleorderIDAndHisVOMap.put(saleOrderHistoryVO.getParentVO()
            .getCsaleorderid(), saleOrderHistoryVO);
      }
      return saleorderIDAndHisVOMap;
    }

    return null;
  }

  @Override
  public SaleOrderHistoryVO[] queryM30ReviseApp(String[] ids)
      throws BusinessException {
    return getOrderHisVOBySaleOrder(ids);
  }

}
