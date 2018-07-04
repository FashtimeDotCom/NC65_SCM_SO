package nc.pubimpl.mobile.analy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pf.mobileapp.MobileAppUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOFunc;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.report.analy.entity.SaleOrderAnalyVO;

import nc.itf.scmpub.reference.uap.permission.DataPermissionService;
import nc.itf.scmpub.reference.uap.permission.FunctionPermissionPubService;
import nc.itf.scmpub.reference.uap.template.BillAccessService;

import nc.pubitf.so.m30.mobile.MyOrderParam;
import nc.pubitf.so.mobile.analy.ISaleOrderAnaly;

import nc.bs.logging.Logger;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���۶�����ѯ����ʵ����
 * 
 * @since 6.1
 * @version 2012-6-18 ����04:13:59
 * @author yixl
 */
public class SaleOrderAnalyImpl implements ISaleOrderAnaly {

  @Override
  public List<Map<String, Object>> qryDayAnalysis(String groupid, String usrid,
      String qrydate, String grouptype, String productlineid, String brandid,
      String channeltypeid, String saleorgid, String customerid) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    // ƴ��ѯ����
    SaleOrderAnalyVO para = new SaleOrderAnalyVO();
    para.setPk_group(groupid);
    para.setPk_user(usrid);
    para.setDbilldate(new UFDate(qrydate));
    para.setGroupkey(this.getGroupKeyName(grouptype));
    para.setCprodlineid(productlineid);
    para.setCbrandid(brandid);
    para.setCchanneltypeid(channeltypeid);
    para.setPk_org(saleorgid);
    para.setCcustomerid(customerid);

    try {
      result = this.queryDayAnalyData(para);
      resultList.add(result);
      return resultList;
    }
    catch (Exception e) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
      Logger.error(e);
      resultList.add(result);
      return resultList;
    }
  }

  @Override
  public List<Map<String, Object>> qryDayReport(String groupid, String usrid,
      String qrydate, String currid, String grouptype, String bizmanid,
      String customerid, String invid) {
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    Map<String, Object> result = new HashMap<String, Object>();
    // ƴ��ѯ����
    SaleOrderAnalyVO para = new SaleOrderAnalyVO();
    para.setPk_group(groupid);
    para.setPk_user(usrid);
    para.setDbilldate(new UFDate(qrydate));
    para.setGroupkey(this.getGroupKeyNameForReport(grouptype));
    para.setCemployeeid(bizmanid);
    para.setCcustomerid(customerid);
    para.setCmaterialid(invid);
    para.setCorigcurrencyid(currid);
    try {
      result = this.queryAnalyData(para);
      resultList.add(result);
      return resultList;
    }
    catch (Exception e) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
      Logger.error(e);
      resultList.add(result);
      return resultList;
    }
  }

  @Override
  public List<Map<String, Object>> qrySOList(String groupid, String usrid,
      String qrydate, String currid, String bizmanid, String customerid,
      String invid, int startline, int count) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    // ƴ��ѯ����
    SaleOrderAnalyVO para = new SaleOrderAnalyVO();
    para.setPk_group(groupid);
    para.setPk_user(usrid);
    para.setDbilldate(new UFDate(qrydate));
    para.setCemployeeid(bizmanid);
    para.setCcustomerid(customerid);
    para.setCmaterialid(invid);
    para.setCorigcurrencyid(currid);

    if (PubAppTool.isNull(bizmanid) && PubAppTool.isNull(customerid)
        && PubAppTool.isNull(invid)) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result
          .put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0456")/*@res "ҵ��Ա���ͻ�������������������ͬʱΪ��!"*/);
      resultList.add(result);
      return resultList;
    }

    try {
      String sql = this.buildQuerySqlForSOList(para);
      List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
      retList = this.mySOListQueryPage(sql, startline, count);

      result.put(OrderAnalyParam.BILLDATALIST, retList);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.RETTURN_MSG, "");
      resultList.add(result);
      return resultList;
    }
    catch (Exception e) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
      Logger.error(e);
      resultList.add(result);
      return resultList;
    }
  }

  /**
   * ƴ��ѯsql
   * 
   * @param para
   * @return
   */
  private String buildQuerySql(SaleOrderAnalyVO para) {
    SqlBuilder querySql = new SqlBuilder();

    // ƴSql����select����
    querySql.append("select ");
    querySql.append(para.getGroupkey() + ",");
    querySql.append("sum(" + SaleOrderAnalyVO.NNUM + "),");
    querySql.append("sum(" + SaleOrderAnalyVO.NORIGTAXMNY + ") totalmny");
    querySql.append(" from so_mb_orderanaly som");

    this.buildWhereSql(querySql, para);

    return querySql.toString();
  }

  private String buildQuerySqlForDayAnaly(SaleOrderAnalyVO para) {
    SqlBuilder querySql = new SqlBuilder();
    // ȡ���ű�λ���Ƿ����ò���
    boolean isGroup = SOCurrencyUtil.getInstance().isGroupCurrencyEnable();

    String qryMoney = "";
    if (isGroup) {
      qryMoney = SaleOrderAnalyVO.NGROUPTAXMNY;
    }
    else {
      qryMoney = SaleOrderAnalyVO.NTAXMNY;
    }
    // ƴSql����select����
    querySql.append("select ");
    querySql.append(para.getGroupkey() + ",");
    querySql.append("sum(" + SaleOrderAnalyVO.NNUM + "),");
    querySql.append("sum(" + qryMoney + ") totalmny");
    querySql.append(" from so_mb_orderanaly som");
    this.buildWhereSql(querySql, para);
    return querySql.toString();
  }

  /**
   * ƴ�����б�Ĳ�ѯsql
   * 
   * @param para
   * @return
   */
  private String buildQuerySqlForSOList(SaleOrderAnalyVO para) {
    SqlBuilder querySql = new SqlBuilder();

    // ƴSql����select����
    querySql.append("select ");
    querySql.append(SaleOrderAnalyVO.VBILLCODE + ",");
    querySql.append(SaleOrderAnalyVO.CSALEORDERID + ",");
    querySql.append("sum(" + SaleOrderAnalyVO.NNUM + "),");
    querySql.append("sum(" + SaleOrderAnalyVO.NORIGTAXMNY + ") totalmny");
    querySql.append(" from so_mb_orderanaly som");

    // ƴsql��where����
    UFDate queryDate = para.getDbilldate();
    querySql.append(" where");
    querySql.append(" som.dbilldate ", " >=", queryDate.asBegin().toString());
    querySql.append(" and ");
    querySql.append(" som.dbilldate ", " <=", queryDate.asEnd().toString());

    String pkUser = para.getPk_user();
    String pkGroup = para.getPk_group();

    // ����֯Ȩ��
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(pkUser,
            SOFunc.A03002.getCode(), pkGroup);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("som.pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // ҵ��ԱȨ��
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(pkUser,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("som.cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    // ҵ��Ա
    if (!PubAppTool.isNull(para.getCemployeeid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CEMPLOYEEID, para.getCemployeeid());
    }
    // �ͻ�
    if (!PubAppTool.isNull(para.getCcustomerid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CCUSTOMERID, para.getCcustomerid());
    }
    // ����
    if (!PubAppTool.isNull(para.getCmaterialid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CMATERIALID, para.getCmaterialid());
    }
    // ����
    if (!PubAppTool.isNull(para.getCorigcurrencyid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CORIGCURRENCYID,
          para.getCorigcurrencyid());
    }

    // ����
    querySql.append("group by " + SaleOrderAnalyVO.VBILLCODE + " ,"
        + SaleOrderAnalyVO.CSALEORDERID);

    // ����
    querySql.append(" order by totalmny desc");

    return querySql.toString();

  }

  /**
   * ƴSql���Ĳ�ѯ��������
   * 
   * @param querySql
   * @param para
   *          ��ѯ����
   */
  private void buildWhereSql(SqlBuilder querySql, SaleOrderAnalyVO para) {
    // ƴsql��where����
    UFDate queryDate = para.getDbilldate();
    querySql.append(" where");
    querySql.append(" som.dbilldate ", " >=", queryDate.asBegin().toString());
    querySql.append(" and ");
    querySql.append(" som.dbilldate ", " <=", queryDate.asEnd().toString());

    String pkUser = para.getPk_user();
    String pkGroup = para.getPk_group();

    // ����֯Ȩ��
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(pkUser,
            SOFunc.A03002.getCode(), pkGroup);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("som.pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // ҵ��ԱȨ��
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(pkUser,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("som.cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    // ��Ʒ��
    if (!PubAppTool.isNull(para.getCprodlineid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CPRODLINEID, para.getCprodlineid());
    }
    // Ʒ��
    if (!PubAppTool.isNull(para.getCbrandid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CBRANDID, para.getCbrandid());
    }
    // ������������
    if (!PubAppTool.isNull(para.getCchanneltypeid())) {
      querySql.append(" and ");
      querySql
          .append(SaleOrderAnalyVO.CCHANNELTYPEID, para.getCchanneltypeid());
    }
    // ������֯
    if (!PubAppTool.isNull(para.getPk_org())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.PK_ORG, para.getPk_org());
    }
    // ҵ��Ա
    if (!PubAppTool.isNull(para.getCemployeeid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CEMPLOYEEID, para.getCemployeeid());
    }
    // �ͻ�
    if (!PubAppTool.isNull(para.getCcustomerid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CCUSTOMERID, para.getCcustomerid());
    }
    // ����
    if (!PubAppTool.isNull(para.getCmaterialid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CMATERIALID, para.getCmaterialid());
    }
    // ����
    if (!PubAppTool.isNull(para.getCorigcurrencyid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CORIGCURRENCYID,
          para.getCorigcurrencyid());
    }

    // ����
    querySql.append("group by " + para.getGroupkey());

    // ����
    querySql.append(" order by totalmny desc");

  }

  private Map<String, String> getbillMapValue(
      List<Map<String, String>> detailList, String groupKey) {
    Map<String, String> retMap = new HashMap<String, String>();
    if (SaleOrderAnalyVO.CPRODLINEID.equals(groupKey)) {
      String cprodlineId =
          detailList.get(6).get(groupKey + OrderAnalyParam.GROUP_ID);
      // ��Ʒ��Ϊ�մ�~
      if (!PubAppTool.isNull(cprodlineId)) {
        retMap.put(OrderAnalyParam.ITEMID, cprodlineId);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(6).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0457")/*@res "��Ʒ�߿�"*/);
      }

    }
    else if (SaleOrderAnalyVO.CBRANDID.equals(groupKey)) {
      String cbrandId =
          detailList.get(7).get(groupKey + OrderAnalyParam.GROUP_ID);
      // Ʒ��Ϊ�մ�~
      if (!PubAppTool.isNull(cbrandId)) {
        retMap.put(OrderAnalyParam.ITEMID, cbrandId);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(7).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0460")/*@res "Ʒ�ƿ�"*/);
      }

    }
    else if (SaleOrderAnalyVO.CCHANNELTYPEID.equals(groupKey)) {
      String cchanneltypeid =
          detailList.get(4).get(groupKey + OrderAnalyParam.GROUP_ID);
      // ��������Ϊ�մ�~
      if (!PubAppTool.isNull(cchanneltypeid)) {
        retMap.put(OrderAnalyParam.ITEMID, cchanneltypeid);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(4).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0459")/*@res "�������Ϳ�"*/);
      }

    }
    else if (SaleOrderAnalyVO.PK_ORG.equals(groupKey)) {
      retMap.put(OrderAnalyParam.ITEMID,
          detailList.get(0).get(groupKey + OrderAnalyParam.GROUP_ID));
      retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(0).get(groupKey));
    }
    else if (SaleOrderAnalyVO.CCUSTOMERID.equals(groupKey)) {
      retMap.put(OrderAnalyParam.ITEMID,
          detailList.get(2).get(groupKey + OrderAnalyParam.GROUP_ID));
      retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(2).get(groupKey));
    }
    else if (SaleOrderAnalyVO.CEMPLOYEEID.equals(groupKey)) {
      String cemployeeid =
          detailList.get(3).get(groupKey + OrderAnalyParam.GROUP_ID);
      // ҵ��ԱΪ�մ�~
      if (!PubAppTool.isNull(cemployeeid)) {
        retMap.put(OrderAnalyParam.ITEMID, cemployeeid);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(3).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0458")/*@res "ҵ��Ա��"*/);
      }
    }
    else if (SaleOrderAnalyVO.CMATERIALID.equals(groupKey)) {
      retMap.put(OrderAnalyParam.ITEMID,
          detailList.get(5).get(groupKey + OrderAnalyParam.GROUP_ID));
      retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(5).get(groupKey));
    }
    return retMap;
  }

  /**
   * ���۷����ӿڽ����������ת������
   * 
   * @param grouptype
   * @return
   */
  private String getGroupKeyName(String grouptype) {
    if (OrderAnalyParam.ANALY_1.equals(grouptype)) {
      return SaleOrderAnalyVO.CPRODLINEID;
    }
    else if (OrderAnalyParam.ANALY_2.equals(grouptype)) {
      return SaleOrderAnalyVO.CBRANDID;
    }
    else if (OrderAnalyParam.ANALY_3.equals(grouptype)) {
      return SaleOrderAnalyVO.CCHANNELTYPEID;
    }
    else if (OrderAnalyParam.ANALY_4.equals(grouptype)) {
      return SaleOrderAnalyVO.PK_ORG;
    }
    else if (OrderAnalyParam.ANALY_5.equals(grouptype)) {
      return SaleOrderAnalyVO.CCUSTOMERID;
    }
    else {
      return "";
    }
  }

  /**
   * �����ձ��ӿڽ����������ת������
   * 
   * @param grouptype
   * @return
   */
  private String getGroupKeyNameForReport(String grouptype) {
    if (OrderAnalyParam.ANALY_1.equals(grouptype)) {
      return SaleOrderAnalyVO.CEMPLOYEEID;
    }
    else if (OrderAnalyParam.ANALY_2.equals(grouptype)) {
      return SaleOrderAnalyVO.CCUSTOMERID;
    }
    else if (OrderAnalyParam.ANALY_3.equals(grouptype)) {
      return SaleOrderAnalyVO.CMATERIALID;
    }
    else {
      return "";
    }
  }

  /**
   * �ҵĶ����б��ҳ
   * 
   * @param rowset
   * @param start
   * @param count
   * @return
   */
  private List<Map<String, String>> mySOListQueryPage(String sql, int start,
      int count) {
    DataAccessUtils utils = new DataAccessUtils();
    utils.setMaxRows(start + count + 1);
    IRowSet rowset = utils.query(sql);
    int size = rowset.size();

    // �ƶ�Ӧ��Ҫ��ʼλ�ô�1��ʼ
    int newstart = start - 1;
    if (size <= 0 || newstart > size) {
      return null;
    }
    for (int i = 0; i < newstart; i++) {
      rowset.next();
    }
    // ת��Map����
    List<Map<String, String>> billList = new ArrayList<Map<String, String>>();

    int cursor = 0;
    // ���ȡcount������
    while (cursor < count && rowset.next()) {
      Map<String, String> map = new HashMap<String, String>();
      SaleOrderAnalyVO retVO = new SaleOrderAnalyVO();

      retVO.setVbillcode(rowset.getString(0));
      retVO.setCsaleorderid(rowset.getString(1));
      retVO.setNnum(rowset.getUFDouble(2));
      retVO.setNorigtaxmny(rowset.getUFDouble(3));
      // ����map
      map = this.resolveBillMapForSOList(retVO);
      billList.add(map);
      cursor++;
    }
    return billList;
  }

  private Map<String, Object> queryAnalyData(SaleOrderAnalyVO para) {
    List<Map<String, String>> retDesList = new ArrayList<Map<String, String>>();
    Map<String, Object> result = new HashMap<String, Object>();
    String sql = this.buildQuerySql(para);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    SaleOrderAnalyVO[] retSViewVO =
        this.turnToSaleOrderAnalyVO(para.getGroupkey(), rowset);

    if (null == retSViewVO || retSViewVO.length <= 0
        || PubAppTool.isNull(para.getCorigcurrencyid())) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0454")/*@res "û�в鵽��Ч����!"*/);
      return result;
    }
    Map<String, String> retDesMap = null;
    for (SaleOrderAnalyVO saleAanalyVO : retSViewVO) {
      retDesMap = new HashMap<String, String>();
      if (null != saleAanalyVO) {
        retDesMap = this.resolveBillMap(saleAanalyVO, para.getGroupkey());
        retDesList.add(retDesMap);
      }

    }

    result.put(OrderAnalyParam.SALEDATALIST, retDesList);
    result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
    result.put(MyOrderParam.RETTURN_MSG, "");

    return result;
  }

  private Map<String, Object> queryDayAnalyData(SaleOrderAnalyVO para) {
    List<Map<String, String>> retDesList = new ArrayList<Map<String, String>>();
    Map<String, Object> result = new HashMap<String, Object>();
    String sql = this.buildQuerySqlForDayAnaly(para);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);

    SaleOrderAnalyVO[] retSViewVO =
        this.turnToSaleOrderAnalyVO(para.getGroupkey(), rowset);

    if (null == retSViewVO || retSViewVO.length <= 0) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0454")/*@res "û�в鵽��Ч����!"*/);
      return result;
    }
    Map<String, String> retDesMap = null;
    for (SaleOrderAnalyVO saleAanalyVO : retSViewVO) {
      if (null != saleAanalyVO) {
        retDesMap = new HashMap<String, String>();
        retDesMap = this.resolveBillMap(saleAanalyVO, para.getGroupkey());
        retDesList.add(retDesMap);
      }
    }

    result.put(OrderAnalyParam.SALEDATALIST, retDesList);
    result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
    result.put(MyOrderParam.RETTURN_MSG, "");
    return result;
  }

  /**
   * ����Uap���ص�Map������µ�map����
   * 
   * @param saleAanalyVO
   * @param groupKey
   * @return
   */
  @SuppressWarnings("unchecked")
  private Map<String, String> resolveBillMap(SaleOrderAnalyVO saleAanalyVO,
      String groupKey) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    Map<String, List<Map<String, Object>>> map =
        new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();

    map =
        BillAccessService.saleorderToMap(OrderAnalyParam.PK_BILLTEMPLET,
            saleAanalyVO);

    headList = map.get(OrderAnalyParam.HEAD);
    detail =
        (Map<String, Object>) headList.get(0).get(OrderAnalyParam.TABCONTENT);

    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(OrderAnalyParam.BILLITEMDATA);

    retDesMap = this.getbillMapValue(detailList, groupKey);
    String amout = String.valueOf(detailList.get(8).get(SaleOrderAnalyVO.NNUM));
    if (!PubAppTool.isNull(amout)) {
      retDesMap.put(OrderAnalyParam.AMOUNT,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(amout))));
    }
    else {
      retDesMap.put(OrderAnalyParam.AMOUNT, "");
    }
    String money =
        String.valueOf(detailList.get(9).get(SaleOrderAnalyVO.NORIGTAXMNY));
    if (!PubAppTool.isNull(money) && !"null".equals(money)) {
      retDesMap.put(OrderAnalyParam.MONEY,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(money))));
    }
    else {
      retDesMap.put(OrderAnalyParam.MONEY, "");
    }

    return retDesMap;
  }

  /**
   * ����Uap���ص�Map������µ�map���ع������б�ʹ��
   * 
   * @param saleAanalyVO
   * @return
   */
  @SuppressWarnings("unchecked")
  private Map<String, String> resolveBillMapForSOList(
      SaleOrderAnalyVO saleAanalyVO) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    Map<String, List<Map<String, Object>>> map =
        new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();
    map =
        BillAccessService.saleorderToMap(OrderAnalyParam.PK_BILLTEMPLET,
            saleAanalyVO);

    headList = map.get(OrderAnalyParam.HEAD);
    detail =
        (Map<String, Object>) headList.get(0).get(OrderAnalyParam.TABCONTENT);
    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(OrderAnalyParam.BILLITEMDATA);

    retDesMap.put(OrderAnalyParam.BILLID,
        detailList.get(10).get(SaleOrderAnalyVO.CSALEORDERID));
    retDesMap.put(OrderAnalyParam.BILLNO,
        detailList.get(1).get(SaleOrderAnalyVO.VBILLCODE));
    String amout =
        String.valueOf(String.valueOf(detailList.get(8).get(
            SaleOrderAnalyVO.NNUM)));
    if (!PubAppTool.isNull(amout)) {
      retDesMap.put(OrderAnalyParam.AMOUNT,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(amout))));
    }
    else {
      retDesMap.put(OrderAnalyParam.AMOUNT, "");
    }
    String money =
        String.valueOf(detailList.get(9).get(SaleOrderAnalyVO.NORIGTAXMNY));
    if (!PubAppTool.isNull(amout)) {
      retDesMap.put(OrderAnalyParam.MONEY,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(money))));
    }
    else {
      retDesMap.put(OrderAnalyParam.MONEY, "");
    }
    return retDesMap;
  }

  /**
   * ��Sql�Ĳ�ѯ���ת��SaleOrderAnalyVOʵ��
   * 
   * @param groupkey
   * @param rowset
   * @return
   */
  private SaleOrderAnalyVO[] turnToSaleOrderAnalyVO(String groupkey,
      IRowSet rowset) {
    SaleOrderAnalyVO[] retVO = null;
    if (rowset.size() > 0) {
      if (rowset.size() < 10) {
        retVO = new SaleOrderAnalyVO[rowset.size()];
      }
      else {
        retVO = new SaleOrderAnalyVO[10];
      }
    }
    else {
      return retVO;
    }
    int cursor = 0;
    while (rowset.next()) {
      if (cursor < 10) {
        retVO[cursor] = new SaleOrderAnalyVO();
        String attribute = rowset.getString(0);
        retVO[cursor].setAttributeValue(groupkey, attribute);
        retVO[cursor].setNnum(rowset.getUFDouble(1));
        retVO[cursor].setNorigtaxmny(rowset.getUFDouble(2));
      }
      else {
        break;
      }
      cursor++;
    }
    return retVO;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Map<String, Object>> qryCurAndReport(String groupid,
      String usrid, String qrydate, String grouptype, String bizmanid,
      String customerid, String invid) {
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    Map<String, Object> result = new HashMap<String, Object>();
    SqlBuilder querySql = new SqlBuilder();
    querySql.append("select ");
    querySql.append("distinct " + SaleOrderAnalyVO.CORIGCURRENCYID);
    querySql.append(" from so_mb_orderanaly som");

    UFDate queryDate = new UFDate(qrydate);
    querySql.append(" where");
    querySql.append(" som.dbilldate ", " >=", queryDate.asBegin().toString());
    querySql.append(" and ");
    querySql.append(" som.dbilldate ", " <=", queryDate.asEnd().toString());

    // ����֯Ȩ��
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(usrid,
            SOFunc.A03002.getCode(), groupid);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("som.pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // ҵ��ԱȨ��
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(usrid,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("som.cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    // ȡ��������
    querySql.append(" order by " + SaleOrderAnalyVO.CORIGCURRENCYID);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(querySql.toString());
    String firstCorcurrency = "";
    List<Map<String, String>> retDesList = new ArrayList<Map<String, String>>();
    Map<String, String> retMap = null;
    int cursor = 0;
    while (rowset.next()) {
      if (0 == cursor) {
        firstCorcurrency = rowset.getString(0);
      }
      retMap = new LinkedHashMap<String, String>();
      SaleOrderAnalyVO retVO = new SaleOrderAnalyVO();
      retVO = new SaleOrderAnalyVO();
      retVO.setCorigcurrencyid(rowset.getString(0));
      retMap = this.resolveMapForCurrList(retVO);
      retDesList.add(retMap);
      cursor++;
    }
    if (PubAppTool.isNull(firstCorcurrency)) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0454")/*@res "û�в鵽��Ч����!"*/);
      resultList.add(result);
      return resultList;
    }
    List<Map<String, Object>> DataList =
        this.qryDayReport(groupid, usrid, qrydate, firstCorcurrency, grouptype,
            bizmanid, customerid, invid);
    List<Map<String, String>> analyData = null;
    if (null != DataList && DataList.size() > 0) {
      Map<String, Object> dataMap = DataList.get(0);
      analyData =
          null == dataMap ? null : (List<Map<String, String>>) dataMap
              .get(OrderAnalyParam.SALEDATALIST);
    }
    result.put(OrderAnalyParam.CURRLIST, retDesList);
    result.put(OrderAnalyParam.SALEDATALIST, analyData);
    result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
    result.put(MyOrderParam.RETTURN_MSG, "");
    resultList.add(result);
    return resultList;
  }

  @SuppressWarnings("unchecked")
  private Map<String, String> resolveMapForCurrList(SaleOrderAnalyVO saleAnalyVO) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    Map<String, List<Map<String, Object>>> map =
        new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();

    map =
        BillAccessService.saleorderToMap(OrderAnalyParam.PK_BILLTEMPLET_CUR,
            saleAnalyVO);

    headList = map.get(OrderAnalyParam.HEAD);
    detail =
        (Map<String, Object>) headList.get(0).get(OrderAnalyParam.TABCONTENT);
    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(OrderAnalyParam.BILLITEMDATA);
    retDesMap.put(OrderAnalyParam.CURRNAME,
        detailList.get(0).get(SaleOrderAnalyVO.CORIGCURRENCYID));
    retDesMap.put(
        OrderAnalyParam.CURRID,
        detailList.get(0).get(
            SaleOrderAnalyVO.CORIGCURRENCYID + OrderAnalyParam.GROUP_ID));
    retDesMap.put(OrderAnalyParam.CSYMBOL,
        detailList.get(1).get(OrderAnalyParam.CURRTYPESIGN));
    return retDesMap;
  }
}
