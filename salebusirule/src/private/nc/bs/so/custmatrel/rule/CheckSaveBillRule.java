package nc.bs.so.custmatrel.rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.custmatrel.paravo.CompareParaVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * �ͻ����Ϲ�ϵ����У�����
 * @scene
 * �ͻ����Ϲ�ϵ����ǰ��ȫVO��У��ǰ̨�����EXCL�ļ��е������Ƿ����ظ���У�����ݿ��е������Ƿ����ظ�
 * @param
 * ��
 * @since 6.5
 * @version 2015-10-19 ����9:24:33
 * @author gdsjw
 */
public class CheckSaveBillRule implements IRule<CustMatRelVO> {

  public CheckSaveBillRule() {
    //
  }

  @Override
  public void process(CustMatRelVO[] vos) {
    for (CustMatRelVO vo : vos) {
      // ����ǲ�ȫVO��У��ʱ������Ҫ������״̬
      this.checkNotNull(vo);
      // this.checkUnique(vo);
      setPk_org(vo);
      try {
        // У��ǰ̨�����EXCL�ļ��е������Ƿ����ظ�
        this.checkVODataUnique(vo);
        // У�����ݿ��е������Ƿ����ظ�
        this.checkDBUnique(vo);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  private void checkVODataUnique(CustMatRelVO vo) {
      Set<String> relCollections = new HashSet<String>();
      CustMatRelBVO[] items = vo.getChildrenVO();
      if (items != null && items.length > 0) {
        String nullstring = "NULL";
        StringBuilder sbd = new StringBuilder();
        List<CustMatRelBVO> repeatBVOList = new ArrayList<>();
        for (CustMatRelBVO item : items) {
          int vostatus = item.getStatus();
          if (vostatus == VOStatus.DELETED) {
            // �����ɾ������
            continue;
          }
          String pk_org = item.getPk_org();
          if (PubAppTool.isNull(pk_org)) {
            pk_org = nullstring;
          }
          String materialbaseclass = item.getPk_materialbaseclass();
          if (PubAppTool.isNull(materialbaseclass)) {
            materialbaseclass = nullstring;
          }
          String materialsaleclass = item.getPk_materialsaleclass();
          if (PubAppTool.isNull(materialsaleclass)) {
            materialsaleclass = nullstring;
          }
          String material_v = item.getPk_material_v();
          if (PubAppTool.isNull(material_v)) {
            material_v = nullstring;
          }
          String custbaseclass = item.getPk_custbaseclass();
          if (PubAppTool.isNull(custbaseclass)) {
            custbaseclass = nullstring;
          }
          String custsaleclass = item.getPk_custsaleclass();
          if (PubAppTool.isNull(custsaleclass)) {
            custsaleclass = nullstring;
          }
          String customer = item.getPk_customer();
          if (PubAppTool.isNull(customer)) {
            customer = nullstring;
          }
          sbd.delete(0, sbd.length());
          sbd.append(pk_org).append(materialbaseclass).append(materialsaleclass)
              .append(material_v).append(custbaseclass).append(custsaleclass)
              .append(customer);
          if (relCollections.contains(sbd.toString())) {
            repeatBVOList.add(item);
          }
          else {
            relCollections.add(sbd.toString());
          }
        }
        if(repeatBVOList.size()>0){
          showMessage(ListUtil.toArray(repeatBVOList), true);
        }
    }
    
  }

  private void setPk_org(CustMatRelVO vo) {
    String pk_group = AppContext.getInstance().getPkGroup();
    CustMatRelBVO[] custmatbvos = vo.getChildrenVO();
    if (custmatbvos == null) {
      return;
    }
    for (CustMatRelBVO item : custmatbvos) {
      if (vo.getParentVO() != null) {
        item.setPk_org(vo.getParentVO().getPk_org());
        item.setPk_group(pk_group);
      }
    }
  }

  private void checkNotNull(CustMatRelVO bill) {
    CustMatRelHVO header = bill.getParentVO();
    Integer allowsale = header.getAllowsale();
    if (VOChecker.isEmpty(allowsale)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0004")/* @res "��������/��ֹ���۲���Ϊ�ա�" */);
    }
    String pk_org = header.getPk_org();
    if (VOChecker.isEmpty(pk_org)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0005")/* @res "������֯����Ϊ�ա�" */);
    }

    CustMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      for (CustMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if ((vostatus == VOStatus.DELETED)) {
          // �����ɾ����û�仯����
          continue;
        }
        String materialbaseclass = item.getPk_materialbaseclass();
        boolean ismaterialbaseclassnull = false;
        if (PubAppTool.isNull(materialbaseclass)) {
          ismaterialbaseclassnull = true;
        }

        String materialsaleclass = item.getPk_materialsaleclass();
        boolean ismaterialsaleclassnull = false;
        if (PubAppTool.isNull(materialsaleclass)) {
          ismaterialsaleclassnull = true;
        }

        String material_v = item.getPk_material_v();
        boolean ismaterial_vnull = false;
        if (PubAppTool.isNull(material_v)) {
          ismaterial_vnull = true;
        }
        if (ismaterialbaseclassnull && ismaterialsaleclassnull
            && ismaterial_vnull) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006007_0", "04006007-0006")/*
                                                                      * @res
                                                                      * "���Ϸ��������ϲ���ȫ��Ϊ�ա�"
                                                                      */);
        }

      }
    }
  }

  /**
   * ����������Ƿ�����ݿ������������Ƿ���ͬ����ͬ��������
   * 
   * @param bill
   *          ���浥��
   * 
   */
  private void checkDBUnique(CustMatRelVO bill) throws BusinessException {
    CompareParaVO compareParaVO = this.combinParaByCusVO(bill);
    if (ArrayUtil.isEmpty(compareParaVO.getOrgList())) {
      return;
    }

    custMatRelDBUnique(bill, compareParaVO);
  }

  /**
   * ȡ�������VO�е��ֶ�
   * 
   * @param bill
   * @return
   */
  private CompareParaVO combinParaByCusVO(CustMatRelVO bill) {
    // ����������Ҫ�ֶε����飬������Ų�ͬ����VO����Ӧ�ֶε�ֵ
    // ������֯������
    List<String> orgList = new ArrayList<String>();
    // �������ϻ������������
    List<String> matBase = new ArrayList<String>();
    // �����������۷��������
    List<String> matSale = new ArrayList<String>();
    // �������ϵ�����
    List<String> material = new ArrayList<String>();
    // �����ͻ��������������
    List<String> custBase = new ArrayList<String>();
    // �����ͻ����۷��������
    List<String> custSale = new ArrayList<String>();
    // �����ͻ�������
    List<String> custom = new ArrayList<String>();
    // �����Ƿ����������
    List<String> exclude = new ArrayList<String>();
    // ������ע������
    List<String> vnote = new ArrayList<String>();
    Set<String> custmatelbidSet = new HashSet<>();
    CustMatRelHVO hvoItem = bill.getParentVO();
    CustMatRelBVO[] items = bill.getChildrenVO();
    if (items == null || items.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006007_0", "04006007-0038")/*���岻��Ϊ�գ�*/);
    }
    CompareParaVO compareParaVO = new CompareParaVO();
    if ((items != null) && (items.length > 0)) {
      for (CustMatRelBVO item : items) {
        orgList.add(getvalue(hvoItem.getPk_org()));
        // ȡ������vo��Pk_custbaseclass
        custBase.add(getvalue(item.getPk_custbaseclass()));
        // ȡ������vo��Pk_custsaleclass
        custSale.add(getvalue(item.getPk_custsaleclass()));
        // ȡ������vo��Pk_customer
        custom.add(getvalue(item.getPk_customer()));
        // ȡ������vo��Pk_materialbaseclass
        matBase.add(getvalue(item.getPk_materialbaseclass()));
        // ȡ������vo��Pk_materialsaleclass
        matSale.add(getvalue(item.getPk_materialsaleclass()));
        // ȡ������vo��Pk_material_v
        material.add(getvalue(item.getPk_material_v()));
        // ȡ������vo��exclude
        exclude.add(item.getExclude() == null ? "N" : item.getExclude()
            .toString());
        // ȡ������vo��vnote
        vnote.add(item.getVnote());
        if(!PubAppTool.isNull(item.getPk_custmatrel_b())){
          custmatelbidSet.add(item.getPk_custmatrel_b());
        }
      }
    }
    return compareParaVO.getCompareParaVO(
        orgList.toArray(new String[orgList.size()]),
        matBase.toArray(new String[matBase.size()]),
        matSale.toArray(new String[matSale.size()]),
        material.toArray(new String[material.size()]),
        custBase.toArray(new String[custBase.size()]),
        custSale.toArray(new String[custSale.size()]),
        custom.toArray(new String[custom.size()]),
        exclude.toArray(new String[exclude.size()]),
        vnote.toArray(new String[vnote.size()]),
        custmatelbidSet.toArray(new String[custmatelbidSet.size()]));
  }

  private String getvalue(String str) {
    if (PubAppTool.isNull(str)) {
      return "~";
    }
    else {
      return str;
    }
  }

  /**
   * 
   * �����ظ�У��
   * 
   * @param bill
   * @param compareParaVO
   *          �����Ҫ�Ƚϵ��ֶ�ֵ
   * @throws BusinessException
   */
  private void custMatRelDBUnique(CustMatRelVO bill, CompareParaVO compareParaVO)
      throws BusinessException {

    // 1.��ȡ��̨�ظ���VO(excel�������̨���ݱȽ�)
    CustMatRelBVO[] dbbvos = getDBUnique(bill, compareParaVO);

    // 2.��ʾ������Ϣ
    if (dbbvos != null && dbbvos.length > 0) {
      showMessage(dbbvos, false);
    }
  }

  private CustMatRelBVO[] getDBUnique(CustMatRelVO bill,
      CompareParaVO compareParaVO) {
    // ������ʱ�����sql�Ա�
    String tabelname = createTempTable(compareParaVO);
    SqlBuilder compareSql = new SqlBuilder();
    compareSql.append("select b.pk_custmatrel_b from " + tabelname);
    compareSql.append(" a,so_custmatrel_b b");
    compareSql.append(" where b.dr = 0");
    compareSql.append(" and a.pk_org = b.pk_org");
    compareSql.append(" and a.pk_custbaseclass = b.pk_custbaseclass");
    compareSql.append(" and a.pk_custsaleclass = b.pk_custsaleclass");
    compareSql.append(" and a.pk_customer = b.pk_customer");
    compareSql.append(" and a.pk_materialbaseclass = b.pk_materialbaseclass");
    compareSql.append(" and a.pk_materialsaleclass = b.pk_materialsaleclass");
    compareSql.append(" and a.pk_material_v = b.pk_material_v");
    if(!ArrayUtil.isEmpty(compareParaVO.getCustmatelbid())){
      compareSql.append(this.buildNotInSQL(compareParaVO.getCustmatelbid()));
    }
    String[] pks = this.queryPKBySql(compareSql);
    if (ArrayUtil.isEmpty(pks)) {
      return new CustMatRelBVO[0];
    }
    VOQuery<CustMatRelBVO> qrysrv =
        new VOQuery<CustMatRelBVO>(CustMatRelBVO.class);
    CustMatRelBVO[] retvos = qrysrv.query(pks);

    return retvos;
  }

  private String buildNotInSQL(String[] custmatelbid) {
    if(custmatelbid.length == 1){
      SqlBuilder sqlBuilder = new SqlBuilder();
      sqlBuilder.append(" and ");
      sqlBuilder.append(" b.pk_custmatrel_b <> '");
      sqlBuilder.append(custmatelbid[0]);
      sqlBuilder.append("'");
      return sqlBuilder.toString();
    }
    IDQueryBuilder idBuilder = new IDQueryBuilder();
    return idBuilder.buildSQL(" and b.pk_custmatrel_b not ", custmatelbid);
  }

  /**
   * ��ѯ��ͬ��ʱ������ͬ���ݵı��������
   * 
   * @param compareSql
   * @return
   */
  private String[] queryPKBySql(SqlBuilder compareSql) {
    String[] pks = null;
    BaseDAO dao = new BaseDAO();
    try {
      pks =
          (String[]) dao.executeQuery(compareSql.toString(),
              new ResultSetProcessor() {

                private static final long serialVersionUID = 1L;

                @Override
                public Object handleResultSet(ResultSet rs) throws SQLException {
                  List<String> pkList = new ArrayList<String>();
                  while (rs.next()) {
                    pkList.add(rs.getString(1));
                  }
                  return pkList.toArray(new String[pkList.size()]);
                }
              });
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return pks;
  }

  /**
   * ������ʱ��
   * 
   * @param compareParaVO
   *          return ��ʱ��
   */
  private String createTempTable(CompareParaVO compareParaVO) {
    // ������ά����
    List<List<Object>> datas = new ArrayList<List<Object>>();
    for (int i = 0; i < compareParaVO.getMatBase().length; i++) {
      List<Object> tmpList = new ArrayList<Object>();
      tmpList.add(compareParaVO.getOrgList()[i]);
      // ���������������vo��Pk_custbaseclass
      tmpList.add(compareParaVO.getCustBase()[i]);
      // ���������������vo��pk_custsaleclass
      tmpList.add(compareParaVO.getCustSale()[i]);
      // ���������������vo��pk_customer
      tmpList.add(compareParaVO.getCustom()[i]);
      // ���������������vo��pk_materialbaseclass
      tmpList.add(compareParaVO.getMatBase()[i]);
      // ���������������vo��pk_materialsaleclass
      tmpList.add(compareParaVO.getMatSale()[i]);
      // ���������������vo��pk_material_v
      tmpList.add(compareParaVO.getMaterial()[i]);
      // ���������������vo��exclude
      tmpList.add(compareParaVO.getExclude()[i]);
      // ���������������vo��vnote
      tmpList.add(compareParaVO.getVnote()[i]);
      datas.add(tmpList);
    }
    // ������ʱ�������
    String[] columns =
        new String[] {
          "pk_org", "pk_custbaseclass", "pk_custsaleclass", "pk_customer",
          "pk_materialbaseclass", "pk_materialsaleclass", "pk_material_v",
          "exclude", "vnote"
        };
    // ������ʱ���������
    String[] columnTypes =
        new String[] {
          "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)", "varchar(20)", "char(1)",
          "varchar(181)"
        };

    // ������ʱ��Ҫ������ʱ������ݵ���������
    JavaType[] javaTypes =
        new JavaType[] {
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String,
          JavaType.UFBoolean, JavaType.String
        };
    // ������ʱ���Ҳ�������datas
    TempTable tmpTable = new TempTable();
    String tmpTabelName =
        tmpTable.getTempTable("temp_custmatrel_2", columns, columnTypes,
            javaTypes, datas);

    return tmpTabelName;
  }

  /**
   * 
   * ��ʾ��Ϣ
   * 
   * @param results
   * @param deleteStatusIds
   */
  private void showMessage(CustMatRelBVO[] results, boolean isexcel) {

    // ��ȡ������֯��nameֵ
    Map<String, String> pk_orgmap =
        getBillValue(results, "pk_org", "org_orgs", "pk_org");

    // ��ȡcustbaseclass��nameֵ
    Map<String, String> custbaseMap =
        getBillValue(results, "pk_custclass", "bd_custclass",
            "pk_custbaseclass");
    // ��ȡcustsaleclass��nameֵ
    Map<String, String> custsalemap =
        getBillValue(results, "pk_custsaleclass", "bd_custsaleclass",
            "pk_custsaleclass");
    // ��ȡcustomer��nameֵ
    Map<String, String> customerMap =
        getBillValue(results, "pk_customer", "bd_customer", "pk_customer");
    // ��ȡmaterialbaseclass��nameֵ
    Map<String, String> marbasMap =
        getBillValue(results, "pk_marbasclass", "bd_marbasclass",
            "pk_materialbaseclass");
    // ��ȡmaterialsaleclass��nameֵ
    Map<String, String> marsaleMap =
        getBillValue(results, "pk_marsaleclass", "bd_marsaleclass",
            "pk_materialsaleclass");
    // ��ȡmaterial��nameֵ
    Map<String, String> materialMap =
        getBillValue(results, "pk_material", "bd_material_v", "pk_material_v");

    // ͨ��map��ֵ�Եõ���nameֵ����Ϊ��ʾ��Ϣ
    StringBuilder strMes = new StringBuilder();
    for (CustMatRelBVO result : results) {

      if (isNotNull(result.getPk_org())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0060", null, new String[] {
              pk_orgmap.get(result.getPk_org())
            })/*������֯ [{0}],*/);
      }
      if (isNotNull(result.getPk_custbaseclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0039", null, new String[] {
              custbaseMap.get(result.getPk_custbaseclass())
            })/*�ͻ��������� [{0}],*/);
      }
      if (isNotNull(result.getPk_custsaleclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0040", null, new String[] {
              custsalemap.get(result.getPk_custsaleclass())
            })/*�ͻ����۷���[{0}],*/);
      }
      if (isNotNull(result.getPk_customer())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0041", null, new String[] {
              customerMap.get(result.getPk_customer())
            })/*�ͻ�[{0}],*/);
      }
      if (isNotNull(result.getPk_materialbaseclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0042", null, new String[] {
              marbasMap.get(result.getPk_materialbaseclass())
            })/*���ϻ�������[{0}],*/);
      }
      if (isNotNull(result.getPk_materialsaleclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0043", null, new String[] {
              marsaleMap.get(result.getPk_materialsaleclass())
            })/*�������۷���[{0}],*/);
      }
      if (isNotNull(result.getPk_material_v())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0044", null, new String[] {
              materialMap.get(result.getPk_material_v())
            })/*����[{0}]*/);
      }
      if (result.getExclude() != null) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0045", null, new String[] {
              result.getExclude().toString()
            })/*������[{0}]*/);
      }
      if (!PubAppTool.isNull(result.getVnote())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0046", null, new String[] {
              result.getVnote()
            })/*��ע[{0}]*/);
      }
      if (isexcel) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0063")/* ���������ظ�������֮ǰ������ȷ��û���ظ����ݣ�\r\n */);
      }
      else {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0061")/* ����������ϵͳ���Ѿ����ڣ�\r\n */);
      }
    }

    ExceptionUtils.wrappBusinessException(strMes.toString());
  }
  
  private boolean isNotNull(String para){
    if(!PubAppTool.isNull(para)&& !"~".equals(para)){
      return true;
      
    }else{
      return false;
    }
  }

  /**
   * 
   * ��ȡ��Ӧpk��nameֵ
   * 
   * @param results
   * @return map
   */
  private Map<String, String> getBillValue(CustMatRelBVO[] results,
      String primaryKey, String tableName, String field) {
    StringBuilder whereSql = new StringBuilder();
    // ������ѯ��䣬������ֶε�pk�����������ӵı�����nameֵ
    whereSql.append(" select name,");
    whereSql.append(primaryKey);
    whereSql.append(" from ");
    whereSql.append(tableName);
    whereSql.append(" where ");
    Set<String> ids = new HashSet<String>();
    for (CustMatRelBVO result : results) {
      if (result.getAttributeValue(field) != null) {
        ids.add((String) result.getAttributeValue(field));
      }
    }
    Map<String, String> billMap = new HashMap<String, String>();
    if (ids.size() == 0) {
      return billMap;
    }
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    whereSql.append(iq.buildSQL(primaryKey, ids.toArray(new String[0])));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(whereSql.toString());
    String[][] rsvalues = rs.toTwoDimensionStringArray();
    for (String[] value : rsvalues) {
      billMap.put(value[1], value[0]);
    }
    return billMap;
  }
}
