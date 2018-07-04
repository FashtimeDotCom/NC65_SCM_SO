package nc.impl.so.custmatrel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.so.custmatrel.action.DeleteAction;
import nc.impl.so.custmatrel.action.InsertAction;
import nc.impl.so.custmatrel.action.UpdateAction;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.so.custmatrel.ICustMatRelMaintain;
import nc.jdbc.framework.SQLParameter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.scmpub.util.ListUtil;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.custmatrel.paravo.CompareParaVO;

public class CustMatRelMaintainImpl implements ICustMatRelMaintain {

  @Override
  public CustMatRelVO update(CustMatRelVO bill) throws BusinessException {
    UpdateAction action = new UpdateAction();
    return action.update(bill);
  }

  @Override
  public CustMatRelVO delete(CustMatRelVO bill) throws BusinessException {
    DeleteAction action = new DeleteAction();
    return action.delete(bill);
  }

  @Override
  public CustMatRelVO insert(CustMatRelVO bill) throws BusinessException {
    InsertAction action = new InsertAction();
    return action.insert(bill);
  }

  @Override
  public CustMatRelVO queryByOrg(String pk_org) throws BusinessException {
    CustMatRelVO bill = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      StringBuffer whereSql = new StringBuffer();
      whereSql.append("so_custmatrel.pk_org = '" + pk_org + "' ").append(
          "and so_custmatrel.dr = 0 ");
      String sql = "select pk_custmatrel from so_custmatrel where " + whereSql;
      IRowSet rowset = utils.query(sql);
      String[] cbillids = rowset.toOneDimensionStringArray();

      // ����id��ѯVO
      BillQuery<CustMatRelVO> query =
          new BillQuery<CustMatRelVO>(CustMatRelVO.class);
      CustMatRelVO[] bills = query.query(cbillids);
      if ((bills != null) && (bills.length > 0)) {
        bill = bills[0];
      }

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bill;
  }

  /**
   * 
   * ��ѯ��ͷ������VO����װ�ɾۺ�VO
   * 
   */
  @Override
  public CustMatRelVO[] queryCustMatRel(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    // ��ȡ��ѯ����pk_org��ֵ
    String pk_org = this.getQueryValue(qrySchemeProcessor, "pk_org");
    // ���ѡ��ѡ�¼��������Ľṹ�ھͻ�����ÿͻ����༰���¼�����ȡ��ѯ����pk_custbaseclass��ֵ
    String[] pk_custbaseclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custbaseclass");
    // ��ȡ��ѯ����pk_custsaleclass��ֵ
    String[] pk_custsaleclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custsaleclass");
    // ��ȡ��ѯ����pk_customer��ֵ
    String pk_customer =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_customer");
    // ��ȡ��ѯ����pk_marbasclass��ֵ
    String[] pk_marbasclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialbaseclass");
    // ��ȡ��ѯ����pk_marsaleclass��ֵ
    String[] pk_marsaleclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialsaleclass");
    // ��ȡ��ѯ����pk_material��ֵ
    String pk_material =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_material");
    try {
      // 1������pk_org��ѯ��ͷVO��Ӧ��ֻ��һ��
      CustMatRelHVO relHVO = this.queryCustMatRelHVOByORG(pk_org);
      if (relHVO == null) {
        return null;
      }

      // 2������HVO�е�PK���ͻ����ࡢ�ͻ���Ϣ��ѯ����VO����
      CustMatRelBVO[] relBVOs =
          this.queryCustMatRelBVOsByORG(relHVO, pk_custbaseclass,
              pk_custsaleclass, pk_customer, pk_marbasclass, pk_marsaleclass,
              pk_material);

      // 3������ͷVO�ͱ���VO��װ�ɾۺ�VO��������
      CustMatRelVO aggVO = this.buildCustMatRelVO(relHVO, relBVOs);
      return new CustMatRelVO[] {
        aggVO
      };
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * ��ȡ��ѯ������ֵ
   * 
   * @param qrySchemeProcessor
   * @param code
   *          ��ѯ����
   * @return pk
   */
  private String getQueryValue(QuerySchemeProcessor qrySchemeProcessor,
      String code) {
    String[] queryConditionValue = null;
    if (qrySchemeProcessor.getQueryCondition(code) != null) {
      queryConditionValue =
          qrySchemeProcessor.getQueryCondition(code).getValues();
      // ����ÿ����ѯ������ֻ����ѡһ����ѯ����������ȡ�����ڵ�һ��ֵ
      return queryConditionValue[0];
    }
    return null;
  }

  /**
   * ��ȡ��ѯ������ֵ
   * 
   * @param qrySchemeProcessor
   * @param code
   *          ��ѯ����
   * @return pk
   */
  private String[] getCustBaseClassQueryValue(
      QuerySchemeProcessor qrySchemeProcessor, String code) {
    String[] queryConditionValue = null;
    if (qrySchemeProcessor.getQueryCondition(code) != null) {
      queryConditionValue =
          qrySchemeProcessor.getQueryCondition(code).getValues();
      // ����ÿ����ѯ������ֻ����ѡһ����ѯ����������ȡ�����ڵ�һ��ֵ
      if (queryConditionValue[0].trim().startsWith("select")) {
        String sql = queryConditionValue[0];
        DataAccessUtils utils = new DataAccessUtils();
        IRowSet results = utils.query(sql.toString());
        return results.toOneDimensionStringArray();
      }
      return queryConditionValue;
    }
    return null;
  }

  /**
   * ����ͷVO�ͱ���VO��װ�ɾۺ�VO��������
   * 
   * @param relHVO
   *          ��ͷVO
   * @param relBVO
   *          ����vo
   * @return
   */
  private CustMatRelVO buildCustMatRelVO(CustMatRelHVO relHVO,
      CustMatRelBVO[] relBVO) {
    CustMatRelVO aggVO = new CustMatRelVO();
    aggVO.setParentVO(relHVO);
    aggVO.setChildrenVO(relBVO);
    return aggVO;
  }

  /**
   * 
   * ����HVO�е�PK���ͻ����ࡢ�ͻ���Ϣ��ѯ����VO����
   * 
   * @param relHVO
   * @param pk_custbaseclass
   * @param pk_customer
   * @return
   * @throws BusinessException
   * 
   */
  private CustMatRelBVO[] queryCustMatRelBVOsByORG(CustMatRelHVO relHVO,
      String[] pk_custbaseclass, String[] pk_custsaleclass, String pk_customer,
      String[] pk_marbasclass, String[] pk_marsaleclass, String pk_material)
      throws BusinessException {
    StringBuilder querySql = new StringBuilder();
    querySql
        .append(" select distinct pk_custmatrel_b from so_custmatrel_b b where b.pk_custmatrel = '"
            + relHVO.getPk_custmatrel() + "'");
    querySql.append(" and b.dr = 0 ");

    StringBuilder inSQLALL = new StringBuilder();

    // �����и�Ǳ����pk_custbaseclass��pk_custsaleclass���ֻ����һ����ֵ����������֮��û����and����
    if (!ArrayUtil.isEmpty(pk_custbaseclass)) {
      // �����ͻ��������ࡱ����ֵ���Ҵ����¼�����ʱʹ������Ĺ���sql����
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_CUSTBASECLASS,
          pk_custbaseclass));
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      // ���ͻ����������µĿͻ�����ѯ����
      inSQLALL.append(getInSQLForCustomerBaseClass(CustomerVO.PK_CUSTCLASS,
          pk_custbaseclass));
    }
    if (!ArrayUtil.isEmpty(pk_custsaleclass)) {
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_CUSTSALECLASS,
          pk_custsaleclass));
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      // ���ͻ����۷����µĿͻ�����ѯ����
      inSQLALL.append(getInSQLForCustomerBaseClass(CustsaleVO.PK_CUSTSALECLASS,
          pk_custsaleclass));
    }
    // �����и�Ǳ����pk_marbasclass��pk_marsaleclass���ֻ����һ����ֵ����������֮��û����and����
    if (!ArrayUtil.isEmpty(pk_marbasclass)) {
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_MATERIALBASECLASS,
          pk_marbasclass));

      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getInSQLForMaterialBaseClass(MaterialVO.PK_MATERIAL,
          pk_marbasclass));
    }
    if (!ArrayUtil.isEmpty(pk_marsaleclass)) {
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_MATERIALSALECLASS,
          pk_marsaleclass));

      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      // ���������۷����µ����϶���ѯ����
      inSQLALL.append(getInSQLForMaterialBaseClass(MaterialVO.PK_MATERIAL,
          pk_marsaleclass));
    }
    StringBuilder customerSql = new StringBuilder();
    if (!PubAppTool.isNull(pk_customer)) {
      customerSql.append(" b.pk_customer = '");
      customerSql.append(pk_customer);
      customerSql.append("'");
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(customerSql);
    }
    StringBuilder materialSql = new StringBuilder();
    if (!PubAppTool.isNull(pk_material)) {
      materialSql.append(" b.pk_material = '");
      materialSql.append(pk_material);
      materialSql.append("'");

      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(materialSql);
    }

    if (inSQLALL.length() > 0) {
      querySql.append(" and (");
      querySql.append(inSQLALL);
      querySql.append(" ) ");
    }

    return queryBvosByprimarykey(querySql);
  }

  private String getSQLForClass(String classKeyCode, String[] classValues) {
    IDQueryBuilder builder = new IDQueryBuilder();
    String inSql = builder.buildSQL(classKeyCode, classValues);
    return inSql;
  }

  private String getInSQLForMaterialBaseClass(String detailKey,
      String[] classValues) {
    SqlBuilder inSql = new SqlBuilder();
    inSql.append(" select ");
    inSql.append(MaterialVO.PK_MATERIAL);
    inSql.append(" from ");
    inSql.append(MaterialVO.getDefaultTableName());
    inSql.append(" where ");
    inSql.append(MaterialVO.getDefaultTableName() + ".dr", 0);
    inSql.append(" and ");
    inSql.append(MaterialVO.PK_MARBASCLASS, classValues);

    StringBuilder sql = new StringBuilder();
    sql.append(" ");
    sql.append(detailKey);
    sql.append(" in (");
    sql.append(inSql);
    sql.append(" )");
    return sql.toString();
  }

  /**
   * 
   * 
   * @param detailKey
   * @param classValues
   * @return
   */
  private String getInSQLForCustomerBaseClass(String detailKey,
      String[] classValues) {
    SqlBuilder inSql = new SqlBuilder();
    inSql.append(" select ");
    inSql.append(CustomerVO.PK_CUSTOMER);
    inSql.append(" from ");
    inSql.append(CustomerVO.getDefaultTableName());
    inSql.append(" where ");
    inSql.append(CustomerVO.getDefaultTableName() + ".dr", 0);
    inSql.append(" and ");
    inSql.append(detailKey, classValues);

    StringBuilder sql = new StringBuilder();
    sql.append(" ");
    sql.append(CustomerVO.PK_CUSTOMER);
    sql.append(" in (");
    sql.append(inSql);
    sql.append(" )");
    return sql.toString();
  }

  /**
   * ��ѯ�����������ı��壬�������ж�
   * 
   * @param querySql
   * @return
   */
  private CustMatRelBVO[] queryBvosByprimarykey(StringBuilder querySql)
      throws BusinessException {
    // ��ѯ�����������ı�������
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    List<String> primaryKeys = new ArrayList<String>();
    for (String res : results.toOneDimensionStringArray()) {
      primaryKeys.add(res);
    }
    // ͨ����ѯ����pks������������bvos�������
    if (ListUtil.isEmpty(primaryKeys)) {
      return null;
    }
    VOQuery<CustMatRelBVO> qrybvos =
        new VOQuery<CustMatRelBVO>(CustMatRelBVO.class);
    CustMatRelBVO[] bvos = qrybvos.query(ListUtil.toArray(primaryKeys));

    // ��������������һ����ʱҪ��ʾ���������������������Ӳ�ѯ��������С��Χ��
    if (bvos.length > 10000) {
      ExceptionUtils
          .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
              "4006007_0", "04006007-0048")/*��ѯ�������ݴ���һ���У������Ӳ�ѯ�������²�ѯ��*/);
    }
    return bvos;
  }

  /**
   * ����pk_org��ѯ��ͷVO��Ӧ��ֻ��һ��
   * 
   * @param pk_org
   * @return
   */
  private CustMatRelHVO queryCustMatRelHVOByORG(String pk_org)
      throws BusinessException {
    StringBuffer whereSql = new StringBuffer();
    whereSql.append(" so_custmatrel.dr = 0 and ").append(
        "so_custmatrel.pk_org = ? ");

    // �����ѯ����
    SQLParameter params = new SQLParameter();
    if (!PubAppTool.isNull(pk_org)) {
      params.addParam(pk_org);
    }
    // ʹ��BaseDAO�е�retrieveByClause���������в�����ѯ
    BaseDAO dao = new BaseDAO();
    @SuppressWarnings("unchecked")
    List<CustMatRelHVO> hvos =
        (List<CustMatRelHVO>) dao.retrieveByClause(CustMatRelHVO.class,
            whereSql.toString(), params);
    if (hvos != null && hvos.size() > 0) {
      // �������ı�ͷֻ��һ����
      return hvos.get(0);
    }
    else {
      return null;
    }
  }

  @Override
  public void importXLS(CustMatRelVO[] bills) throws BusinessException {
    // У��bills���Ƿ���ڶ��������֯����������׳���ʾ��Ϣ
    Set<String> orgSet = new HashSet<String>();
    String pk_group = AppContext.getInstance().getPkGroup();
    for (CustMatRelVO bill : bills) {
      CustMatRelHVO hvo = bill.getParentVO();
      orgSet.add(hvo.getPk_org());
      // �жϵ����ļ�����֯
      showMessage(orgSet);
    }
    // 1.ȡ����VO�е��ֶ�
    CompareParaVO compareParaVO = combinParaByCusVO(bills);

    // ȡ������֯������PK֮���ӳ���ϵ
    Map<String, String> orgMap =
        setCodeTOPK("code", "pk_org", "org_orgs_v", compareParaVO.getOrgList());
    String org = orgMap.get(compareParaVO.getOrgList()[0]);
    // �жϵ����ļ��ġ�����/��ֹ���ۡ��ֶ�ͬ���ݿ��ڸ���֯�µġ�����/��ֹ���ۡ��ֶ��Ƿ���ͬ������ͬ�Ͳ�������
    judgeAllowSaleIsCorrect(org, bills);
    // ȡ�ͻ��������������PK֮���ӳ���ϵ
    Map<String, String> custbaseclassMap =
        setCodeTOPK("code", "pk_custclass", "bd_custclass",
            compareParaVO.getCustBase(), org, "04006007-0068");
    // ȡ�ͻ����۷��������PK֮���ӳ���ϵ
    Map<String, String> custsaleclassMap =
        setCodeTOPK("code", "pk_custsaleclass", "bd_custsaleclass",
            compareParaVO.getCustSale(), org, "04006007-0069");
    // ȡ�ͻ�������PK֮���ӳ���ϵ
    Map<String, String> customerMap =
        setCustomerCodeTOPK("code", "a.pk_customer", compareParaVO.getCustom(),
            org);
    // У�鵼���ļ��ڿͻ�����/���۷��࣬�ͻ�����ȷ��
    checkCustMessage(compareParaVO);
    // ȡ���ϻ������������PK֮���ӳ���ϵ
    Map<String, String> marbasclassMap =
        setCodeTOPK("code", "pk_marbasclass", "bd_marbasclass",
            compareParaVO.getMatBase(), org, "04006007-0071");
    // ȡ�������۷��������PK֮���ӳ���ϵ
    Map<String, String> marsaleclassMap =
        setCodeTOPK("code", "pk_marsaleclass", "bd_marsaleclass",
            compareParaVO.getMatSale(), org, "04006007-0072");
    // ȡ���ϱ�����PK֮���ӳ���ϵ
    Map<String, String> materialMap =
        setMaterialCodeTOPK("code", "a.pk_material",
            compareParaVO.getMaterial(), org);
    // У�鵼���ļ������ϻ���/���۷��࣬���ϵ���ȷ��
    checkCustMessage(compareParaVO);
    // ����һ��map�����pk_org��pk_custmatrel
    Map<String, String> pkMap = getORGAndPKMap();
    // ����pk_org��ȡpk_org_v������bills
    bills = setPkOrgVValues(bills, orgMap);
    Map<String, String> matMap =
        getmaterial(materialMap.values()
            .toArray(new String[materialMap.size()]));

    for (CustMatRelVO bill : bills) {
      CustMatRelHVO hvoItem = bill.getParentVO();
      hvoItem.setAttributeValue("pk_group", pk_group);
      // ͨ��pk_org�жϸ���֯�Ƿ�������ݣ����޸ı�ͷ״̬
      boolean flag = false;
      if (pkMap.get(hvoItem.getPk_org()) != null) {
        hvoItem.setStatus(VOStatus.UPDATED);
        flag = true;
      }
      else {
        hvoItem.setStatus(VOStatus.NEW);
      }
      CustMatRelBVO[] bvoItems = bill.getChildrenVO();
      for (int i = 0; i < bvoItems.length; i++) {
        CustMatRelBVO bvoItem = bvoItems[i];
        bvoItem.setAttributeValue("pk_custbaseclass",
            custbaseclassMap.get(bvoItem.getPk_custbaseclass()));
        bvoItem.setAttributeValue("pk_custsaleclass",
            custsaleclassMap.get(bvoItem.getPk_custsaleclass()));
        bvoItem.setAttributeValue("pk_customer",
            customerMap.get(bvoItem.getPk_customer()));
        bvoItem.setAttributeValue("pk_materialbaseclass",
            marbasclassMap.get(bvoItem.getPk_materialbaseclass()));
        bvoItem.setAttributeValue("pk_materialsaleclass",
            marsaleclassMap.get(bvoItem.getPk_materialsaleclass()));
        bvoItem.setAttributeValue("pk_material_v",
            materialMap.get(bvoItem.getPk_material_v()));
        bvoItem.setAttributeValue("pk_material",
            matMap.get(bvoItem.getPk_material_v()));
        // ����pk_material_v��ȡpk_material������bvoItem
        bvoItem.setAttributeValue("pk_custmatrel",
            pkMap.get(hvoItem.getPk_org()));
        bvoItem.setAttributeValue("pk_org", hvoItem.getPk_org());
        bvoItem.setAttributeValue("pk_group", hvoItem.getPk_group());
        bvoItem.setStatus(VOStatus.NEW);
        bvoItems[i] = bvoItem;
      }
      // ͨ��pk_org��ȡ��ͷpk����ֵ��hvoItem
      hvoItem
          .setAttributeValue("pk_custmatrel", pkMap.get(hvoItem.getPk_org()));
      // ʹ��VOQuery��ѯ�����ݿ��ڵı�ͷ
      VOQuery<CustMatRelHVO> sql =
          new VOQuery<CustMatRelHVO>(CustMatRelHVO.class);
      String[] pks = new String[] {
        hvoItem.getPk_custmatrel()
      };
      // ���hvoItem�������������ݿ��ڱ�ͷ��Ts��ֵ����
      if (hvoItem.getPk_custmatrel() != null) {
        CustMatRelHVO[] hvos = sql.query(pks);
        hvoItem.setAttributeValue("ts", hvos[0].getTs());
      }
      bill.setChildrenVO(bvoItems);
      bill.setParentVO(hvoItem);
      if (flag) {

        // ����Update��������bill���д���
        NCLocator.getInstance().lookup(ICustMatRelMaintain.class).update(bill);
      }
      else {
        // ����insert��������bill���д���
        NCLocator.getInstance().lookup(ICustMatRelMaintain.class).insert(bill);
      }
    }
  }

  /**
   * �жϵ����ļ��ġ��Ƿ������������ݿ��ڵĸ���֯�Ƿ���ͬ
   * 
   * @param orgSet
   */
  /**
   * @param orgSet ��ŵ�����֯����
   * @param orgMap <��֯���룬��֯pk>
   * @param bills �����ļ�vo
   */
  private void judgeAllowSaleIsCorrect(String org, CustMatRelVO[] bills) {
    StringBuilder whereSql = new StringBuilder();
    whereSql
        .append(" select distinct allowsale from so_custmatrel a where a.dr = 0 and a.pk_org = '");
    whereSql.append(org);
    whereSql.append("' ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(whereSql.toString());
    if (results.toOneDimensionStringArray() != null
        && results.toOneDimensionStringArray().length > 0) {
      String allowsale = results.toOneDimensionStringArray()[0];
      if (!allowsale.equals(bills[0].getParentVO().getAllowsale().toString())) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006007_0", "04006007-0049")/*�������ݺ����ݿ������ݡ���������/��ֹ���ۡ��ֶε�ֵ��һ�£���������*/);
      }
    }
  }

  /**
   * �жϵ����ļ���֯�Ƿ����룬�Ƿ���һ����֯
   * 
   * @param orgSet
   */
  private void showMessage(Set<String> orgSet) {
    String[] pk_org = orgSet.toArray(new String[orgSet.size()]);
    if (pk_org.length > 1) {
      ExceptionUtils
          .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
              "4006007_0", "04006007-0050")/*����������д��ڶ��������֯�����Ϊһ��������֯���µ���*/);
    }
    if (pk_org[0] == null || pk_org.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006007_0", "04006007-0051")/*�����������û��¼��������֯*/);
    }
  }

  /**
   * �жϴ����map�Ƿ�Ϊ�գ������3��map����ͬʱ��ֵ
   * 
   * @param map1
   * @param map2
   * @param map3
   */
  private void checkCustMessage(CompareParaVO compareParaVO) {
    StringBuilder strMes = new StringBuilder();
    for (int i = 0; i < compareParaVO.getExclude().length; i++) {
      if (isNotEmpty(compareParaVO.getCustBase()[i])
          + isNotEmpty(compareParaVO.getCustSale()[i])
          + isNotEmpty(compareParaVO.getCustom()[i]) >= 2) {
        if (!PubAppTool.isNull(compareParaVO.getCustBase()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0039", null, new String[] {
                compareParaVO.getCustBase()[i]
              })/*�ͻ��������� [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getCustSale()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0052", null, new String[] {
                compareParaVO.getCustSale()[i]
              })/*�ͻ����۷��� [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getCustom()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0053", null, new String[] {
                compareParaVO.getCustom()[i]
              })/*�ͻ� [{0}],*/);
        }
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0054")/* ����ͬʱ��ֵ��\r\n */);
        ExceptionUtils.wrappBusinessException(strMes.toString());
      }
      else if (isNotEmpty(compareParaVO.getMatBase()[i])
          + isNotEmpty(compareParaVO.getMatSale()[i])
          + isNotEmpty(compareParaVO.getMaterial()[i]) >= 2) {
        if (!PubAppTool.isNull(compareParaVO.getMatBase()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0055", null, new String[] {
                compareParaVO.getMatBase()[i]
              })/*���ϻ������� [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getMatSale()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0056", null, new String[] {
                compareParaVO.getMatSale()[i]
              })/*�������۷��� [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getMaterial()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0057", null, new String[] {
                compareParaVO.getMaterial()[i]
              })/*���� [{0}],*/);
        }
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0054")/* ����ͬʱ��ֵ��\r\n */);
        ExceptionUtils.wrappBusinessException(strMes.toString());
      }
    }
  }

  /**
   * �жϴ����map�Ƿ�Ϊ��
   * 
   * @param string
   * @return
   */
  private int isNotEmpty(String string) {
    if (string == null) {
      return 0;
    }
    return 1;
  }

  /**
   * 
   * ����pk_org��ȡpk_org_v������hvoItem
   * 
   * @param pk_org
   * @return
   */
  /**
   * ����pk_org_v
   * 
   * @param orgMap
   * 
   * @param destordervos
   */
  private CustMatRelVO[] setPkOrgVValues(CustMatRelVO[] bills,
      Map<String, String> orgMap) {
    for (int i = 0; i < bills.length; i++) {
      CustMatRelHVO hvoItem = bills[i].getParentVO();
      hvoItem.setAttributeValue("pk_org", orgMap.get(hvoItem.getPk_org()));
      bills[i].setParentVO(hvoItem);
    }

    // pk_org����
    Set<String> orgidset = new HashSet<String>();

    for (CustMatRelVO vo : bills) {
      CustMatRelHVO head = vo.getParentVO();
      orgidset.add(head.getPk_org());
    }
    Map<String, String> orgmapmap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgidset
            .toArray(new String[orgidset.size()]));

    // ����pk_org_v
    for (int i = 0; i < bills.length; i++) {
      CustMatRelHVO head = bills[i].getParentVO();
      String orgid = head.getPk_org();
      head.setPk_org_v(orgmapmap.get(orgid));
      bills[i].setParentVO(head);
    }

    return bills;
  }

  /**
   * ����pk_material_v��ȡpk_material������bvoItem
   * 
   * @param string
   */
  private Map<String, String> getmaterial(String[] pk_materials) {
    if (ArrayUtil.isEmpty(pk_materials)) {
      return new HashMap<String, String>();
    }
    IDQueryBuilder builder = new IDQueryBuilder();
    String inSQL = builder.buildSQL(MaterialVO.PK_MATERIAL, pk_materials);
    String sql =
        " select " + MaterialVO.PK_SOURCE + ", " + MaterialVO.PK_MATERIAL
            + " from " + MaterialVO.getDefaultTableName()
            + " where dr = 0 and " + inSQL;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(sql.toString());
    Map<String, String> sourceMap = new HashMap<String, String>();
    for (String[] str : results.toTwoDimensionStringArray()) {
      sourceMap.put(str[1], str[0]);
    }
    return sourceMap;
  }

  /**
   * ����һ��map�����pk_org��pk_custmatrel
   * 
   * @param pk_org
   * @param primaryKey
   * @return
   */
  private Map<String, String> getORGAndPKMap() {
    StringBuilder querySql = new StringBuilder();
    querySql
        .append(" select pk_org,pk_custmatrel from so_custmatrel where dr = 0 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    Map<String, String> pkMap = new HashMap<String, String>();
    for (String[] res : results.toTwoDimensionStringArray()) {
      pkMap.put(res[0], res[1]);
    }
    return pkMap;
  }

  /**
   * ȡ����VO�е��ֶ�
   * 
   * @param bill
   * @return
   */
  private CompareParaVO combinParaByCusVO(CustMatRelVO[] bills) {
    // ����������Ҫ�ֶε����飬������Ų�ͬ����VO����Ӧ�ֶε�ֵ
    // ������֯������
    List<String> orgList = new ArrayList<String>();
    // ������ͻ��������������
    List<String> custbaseclassList = new ArrayList<String>();
    // �����ͻ����۷��������
    List<String> custsaleclassList = new ArrayList<String>();
    // �������ϻ������������
    List<String> materialbaseclassList = new ArrayList<String>();
    // �����������۷��������
    List<String> materialsaleclassList = new ArrayList<String>();
    // �������ϵ�����
    List<String> materialList = new ArrayList<String>();
    // �����ͻ�������
    List<String> customerList = new ArrayList<String>();
    // ��������������
    List<String> exclude = new ArrayList<String>();
    // ������ע������
    List<String> vnote = new ArrayList<String>();

    CompareParaVO compareParaVO = new CompareParaVO();
    for (CustMatRelVO bill : bills) {
      CustMatRelHVO hvoItem = bill.getParentVO();
      orgList.add(hvoItem.getPk_org());
      CustMatRelBVO[] bvoItems = bill.getChildrenVO();
      if ((bvoItems != null) && bvoItems.length > 0) {
        for (CustMatRelBVO bvoItem : bvoItems) {
          // ȡ������vo��Pk_custbaseclass
          custbaseclassList.add(bvoItem.getPk_custbaseclass());
          // ȡ������vo��Pk_custsaleclass
          custsaleclassList.add(bvoItem.getPk_custsaleclass());
          // ȡ������vo��Pk_customer
          customerList.add(bvoItem.getPk_customer());
          // ȡ������vo��Pk_materialbaseclass
          materialbaseclassList.add(bvoItem.getPk_materialbaseclass());
          // ȡ������vo��Pk_materialsaleclass
          materialsaleclassList.add(bvoItem.getPk_materialsaleclass());
          // ȡ������vo��Pk_material_v
          materialList.add(bvoItem.getPk_material_v());
          // ȡ������vo��exclude
          exclude.add(bvoItem.getExclude() == null ? "N" : bvoItem.getExclude()
              .toString());
          // ȡ������vo��vnote
          vnote.add(bvoItem.getVnote());
        }
      }
    }

    return compareParaVO
        .getCompareParaVO(orgList.toArray(new String[orgList.size()]),
            materialbaseclassList.toArray(new String[materialbaseclassList
                .size()]), materialsaleclassList
                .toArray(new String[materialsaleclassList.size()]),
            materialList.toArray(new String[materialList.size()]),
            custbaseclassList.toArray(new String[custbaseclassList.size()]),
            custsaleclassList.toArray(new String[custsaleclassList.size()]),
            customerList.toArray(new String[customerList.size()]), exclude
                .toArray(new String[exclude.size()]), vnote
                .toArray(new String[vnote.size()]), null);
  }

  /**
   * �������ļ��ڵ���֯����ת��Ϊpk
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @return
   */
  private Map<String, String> setCodeTOPK(String code, String primaryKey,
      String tableName, String[] list) {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    IDQueryBuilder whereSql = new IDQueryBuilder();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" from ");
    sql.append(tableName);
    sql.append(" where isnull(dr,0) = 0 and ");
    sql.append(whereSql.buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // ȡ��һ�͵ڶ���ֵ�ֱ���Ϊ����ֵ
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }
    // ���д��ı�����󣬱�����ʾ��Ϣ
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0067")/*������֯*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0058", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*��{0}�������ڣ�*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }

    return hashMap;
  }

  /**
   * �������ļ��ڵ����ϱ���ת��Ϊpk���������һЩ�ͻ������䵽����ҵ��Ԫ�µ�������֯
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @param pk_org
   * @return
   * @throws DAOException
   */
  private Map<String, String> setMaterialCodeTOPK(String code,
      String primaryKey, String[] list, String pk_org) throws DAOException {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" from bd_material_v a inner join bd_materialsale b on a.pk_material = b.pk_material ");
    sql.append(" where a.dr = 0 and b.dr = 0 and (b.pk_org = '");
    sql.append(pk_org);
    sql.append("' or a.pk_org in ('");
    sql.append(pk_org);
    sql.append("','" + AppContext.getInstance().getPkGroup()
        + "','GLOBLE00000000000000')) and ");
    sql.append(buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // ȡ��һ�͵ڶ���ֵ�ֱ���Ϊ����ֵ
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }

    // ���д��ı�����󣬱�����ʾ��Ϣ
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0073")/*�ֶ���Ϣ*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0058", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*��{0}�������ڣ�*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
    return hashMap;
  }

  /**
   * �������ļ��ڵĿͻ�����ת��Ϊpk���������һЩ�ͻ������䵽����ҵ��Ԫ�µ�������֯
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @param pk_org
   * @return
   * @throws DAOException
   */
  private Map<String, String> setCustomerCodeTOPK(String code,
      String primaryKey, String[] list, String pk_org) throws DAOException {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" name ");
    sql.append(" from bd_customer a inner join bd_custsale b on a.pk_customer = b.pk_customer ");
    sql.append(" where a.dr = 0 and b.dr = 0 and (b.pk_org = '");
    sql.append(pk_org);
    sql.append("' or a.pk_org in ('");
    sql.append(pk_org);
    sql.append("','" + AppContext.getInstance().getPkGroup()
        + "','GLOBLE00000000000000')) and ");
    sql.append(buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // ȡ��һ�͵ڶ���ֵ�ֱ���Ϊ����ֵ
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }

    // ���д��ı�����󣬱�����ʾ��Ϣ
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0070")/*�ֶ���Ϣ*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0059", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*��{0}�������ڻ���û�з��䵽�����ļ���������֯��*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
    return hashMap;
  }

  /**
   * �������ļ��ڵı���תΪpk
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @param pk_org
   * @return
   * @throws DAOException
   */
  private Map<String, String> setCodeTOPK(String code, String primaryKey,
      String tableName, String[] list, String pk_org, String errorResid)
      throws DAOException {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" from ");
    sql.append(tableName);
    sql.append(" where isnull(dr,0) = 0 and ");
    sql.append(" pk_org in ('");
    sql.append(pk_org);
    sql.append("','" + AppContext.getInstance().getPkGroup()
        + "','GLOBLE00000000000000') and ");
    sql.append(buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // ȡ��һ�͵ڶ���ֵ�ֱ���Ϊ����ֵ
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }
    // ���д��ı�����󣬱�����ʾ��Ϣ
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          errorResid)/*�ֶ���Ϣ*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0058", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*��{0}�������ڣ�*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
    return hashMap;
  }

  /**
   * ���ݴ����IDֵ����� ѯ�����������ID��ֵ�����ظ���Ҳ����Ϊ��
   * 
   * @param name
   *          sql�ֶ���
   * @param ids
   *          Ҫ��ѯ��ID����
   * @return ���صĲ�ѯ������������ and��ʼ
   */
  public String buildSQL(String name, String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(name);
    sql.append(" in ");
    sql.startParentheses();
    sql.append(" select id1 from ");
    String temptable = get(ids);
    sql.append(temptable);
    sql.endParentheses();
    return sql.toString();
  }

  /**
   * ����һ��ID�ֶε���ʱ�������ID��ֵ�����ظ���Ҳ����Ϊ�ա�
   * 
   * @param ids
   *          ��������
   * @return һ��������ʱ��
   */
  public String get(String[] ids) {
    String tableName = this.get(ids, "TEMP_SCM_L1");
    return tableName;
  }

  /**
   * ������ʱ��
   * 
   * @param ids
   * @param tableName
   * @return
   */
  private String get(String[] ids, String tableName) {
    List<List<Object>> data = new ArrayList<List<Object>>();

    int length = ids.length;
    for (int i = 0; i < length; i++) {
      List<Object> row = new ArrayList<Object>();
      data.add(row);
      row.add(ids[i]);
    }
    String[] columns = {
      "id1"
    };
    String[] columnTypes = {
      "VARCHAR(100)"
    };
    JavaType[] types = new JavaType[] {
      JavaType.String
    };

    TempTable dao = new TempTable();
    String name =
        dao.getTempTable(tableName, columns, columnTypes, columns, types, data);
    return name;
  }

}
