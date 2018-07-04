package nc.pubitf.so.tbb.detail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.scmpub.fetchfunc.IFuncParaDescribe;
import nc.itf.scmpub.fetchfunc.IFuncParaValue;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.ITable;
import nc.md.model.MetaDataException;
import nc.md.model.type.ICollectionType;
import nc.md.model.type.IRefType;
import nc.md.model.type.IType;
import nc.md.model.type.impl.RefType;
import nc.md.util.MDUtil;
import nc.ui.querytemplate.querytree.IQuerySQLGenerator;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeUtils;
import nc.vo.scmpub.fetchfunc.LevelTempTableFactory;
import nc.vo.scmpub.fetchfunc.split.ParaSplitKey;

/**
 * ������ѯʱSQL��乹���ͽ��������
 * 
 * @since 6.0
 * @version 2011-4-11 ����10:35:57
 * @author ��ӱ�
 */
public class TbbSqlBuilder {
  // ����ȡ����ʵ����
  private Class<? extends ISuperVO> entityClass;

  private SqlBuilder extendFromSql = new SqlBuilder();

  private SqlBuilder extendWhereSql = new SqlBuilder();

  // ����SQL��FROM����
  private SqlBuilder fromPart = new SqlBuilder();

  // ��������ֵ�б�
  private List<IFuncParaValue> listParaValue;

  private IBean mainBean;

  // ���������������ֶ�ӳ��
  private Map<String, String> mapParaGroupAlias = new HashMap<String, String>();

  // ��������Ԫ����·��ӳ��
  private Map<String, String> mapParaMetaPath;

  // �������������ֶ�ӳ��
  private MapList<String, String> mapParaReduncy;

  // ��������
  private Map<String, String> mapTableAlias = new HashMap<String, String>();

  // ������ʱ����Ϣ
  private Map<String, String> mapTempTable = new HashMap<String, String>();

  // ��������KEY
  private ParaSplitKey paraSplitKey;

  // ����SQL��WHERE����
  private SqlBuilder wherePart = new SqlBuilder();

  public TbbSqlBuilder(ParaSplitKey splitkey, List<IFuncParaValue> listpara,
      IFuncParaDescribe desb, Class<? extends ISuperVO> entityclass) {
    this.paraSplitKey = splitkey;
    this.listParaValue = listpara;
    this.mapParaReduncy = desb.getParaRedundancyMap();
    this.mapParaMetaPath = desb.getParaMetaPathMap();
    this.entityClass = entityclass;
    this.mapTableAlias.put(".", this.getMainBean().getTable().getName());
    this.initQuerySql();
  }

  /**
   * ƴ����չ��FROM���
   * 
   * @param fromsql
   */
  public void appendExtendFromSql(String fromsql) {
    this.extendFromSql.append(fromsql);
  }

  public ParaSplitKey getParaSplitKey() {
    return this.paraSplitKey;
  }

  public String getQuerySql() {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" from ");
    sql.append(this.fromPart);
    sql.append(" ");
    sql.append(this.extendFromSql);
    sql.append(" where ");
    sql.append(this.wherePart);
    sql.append(" ");
    sql.append(this.extendWhereSql);
    return sql.toString();
  }

  /**
   * ��ȡ�ֶ����ڱ�ı�����������������ʱ����鵱ǰsql���Ƿ������صı�
   * ������������Զ�����ر�ƴ��from���֣����ҷ��ر������������ֱ�ӷ��ر���
   * 
   * @param metapath Ԫ����·��
   * @return �����
   */
  public String getTableAliasByMetaPath(String metapath) {
    String tableAlias = this.findTableAlias(metapath);
    String aliasPath = null;
    if (StringUtil.isEmptyWithTrim(tableAlias)) {
      if (this.isAttrOwnToExpandTable(this.getMainBean(), metapath)) {
        int lastDotIndex = metapath.lastIndexOf(".");
        String parentAttrPath = metapath.substring(0, lastDotIndex);
        IAttribute attribute = this.getMainBean().getAttributeByPath(metapath);
        String tableName = attribute.getTable().getName();
        aliasPath = parentAttrPath + "." + tableName;
        this.joinExpandTableForicbly(aliasPath);
        tableAlias = this.fetchTableAliasByAliasPath(aliasPath);
      }
      else {
        int lastDotIndex = metapath.lastIndexOf(".");
        if (lastDotIndex < 0) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006005_0", "04006005-0007")/*@res "ϵͳ����"*/);
        }
        else {
          aliasPath = metapath.substring(0, lastDotIndex);
        }
        this.joinNormalTableForcibly(aliasPath);
        tableAlias = this.fetchTableAliasByAliasPath(aliasPath);
      }
    }
    return tableAlias;
  }

  private void addParaGroupAlias(String parakey, String paragroupalias) {
    this.mapParaGroupAlias.put(parakey, paragroupalias);
  }

  private void appendGroupWhere() {
    String mainAlias = this.getMainTableAlias();
    String groupColumnName =
        QuerySchemeUtils.getGroupColumnName(this.getMainBean());
    SqlBuilder builder = new SqlBuilder();
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    builder.append(mainAlias + "." + groupColumnName, pk_group);
    this.wherePart.append(" and ");
    this.wherePart.append(builder.toString());
  }

  private void appendLevelWhere() {

    String[] uselevelkeys = this.paraSplitKey.getUseLevelKey();
    for (String levelkey : uselevelkeys) {
      // ���û���ֶ�ӳ�䣬������
      if (!this.mapParaMetaPath.containsKey(levelkey)) {
        continue;
      }
      // ��������¼����Ҫ������ʱ��
      if (this.paraSplitKey.isIncludeLower(levelkey)) {
        String doctablename = this.getParaKeyRefDocTable(levelkey);
        String tablealias = this.getParaKeyTableAlias(levelkey);
        String doccolumn = this.getParaKeyTableColumn(levelkey);
        LevelTempTableFactory factory = new LevelTempTableFactory();
        String temptablename = null;
        if (this.mapTempTable.containsKey(levelkey)) {
          temptablename = this.mapTempTable.get(levelkey);
        }
        else {
          temptablename =
              factory.creatLevelTempTable(doctablename, levelkey,
                  this.listParaValue);
          this.mapTempTable.put(levelkey, temptablename);
        }
        String innerSql =
            this.generateInnerJoinSql(tablealias, temptablename, doccolumn,
                LevelTempTableFactory.CHILDKEY, tablealias + "."
                    + temptablename);
        this.fromPart.append(innerSql);

        String tmpparentid =
            temptablename + "." + LevelTempTableFactory.PARENTKEY;
        this.addParaGroupAlias(levelkey, tmpparentid);
      }
      // ʵ��ʹ�ò�δ�����¼�������һ�����
      else {
        this.appendWhereSql(new String[] {
          levelkey
        });
      }

    }
  }

  private void appendMainDrWhere() {
    String mainAlias = this.getMainTableAlias();
    this.wherePart.append(mainAlias + ".dr", 0);
  }

  private void appendMainTable() {
    this.fromPart.append(this.getMainTableAlias());
  }

  private void appendNormalWhereSql() {
    String[] usenormalkeys = this.paraSplitKey.getUseNormalKey();
    this.appendWhereSql(usenormalkeys);
  }

  private void appendReduncyPara(String parakey, String specialoper,
      String paravalue) {
    if (null == this.mapParaReduncy || this.mapParaReduncy.size() == 0) {
      return;
    }
    String parametapath = this.mapParaMetaPath.get(parakey);
    if (this.mapParaReduncy.containsKey(parametapath)) {
      List<String> reduncypaths = this.mapParaReduncy.get(parametapath);
      for (String metapath : reduncypaths) {
        String tablealia = this.getTableAliasByMetaPath(metapath);
        String alianame = this.getTableColumnByMetaPath(metapath);
        this.wherePart.append(" and ");
        this.wherePart.append(tablealia + "." + alianame, specialoper,
            paravalue);
      }
    }
  }

  private void appendReduncyPara(String parakey, String[] paravalues) {
    if (null == this.mapParaReduncy || this.mapParaReduncy.size() == 0) {
      return;
    }
    String parametapath = this.mapParaMetaPath.get(parakey);
    if (this.mapParaReduncy.containsKey(parametapath)) {
      List<String> reduncypaths = this.mapParaReduncy.get(parametapath);
      for (String metapath : reduncypaths) {
        String tablealia = this.getTableAliasByMetaPath(metapath);
        String alianame = this.getTableColumnByMetaPath(metapath);
        this.wherePart.append(" and ");
        this.wherePart.append(tablealia + "." + alianame, paravalues);
      }
    }
  }

  private void appendSpecialWhere() {
    String[] usespecialkeys = this.paraSplitKey.getUseSpecialKey();
    for (String specialkey : usespecialkeys) {
      // ���û���ֶ�ӳ�䣬������
      if (!this.mapParaMetaPath.containsKey(specialkey)) {
        continue;
      }
      String specialvalue = this.paraSplitKey.getSpecialParaValue(specialkey);
      String specialoper = this.paraSplitKey.getSpecialOperator(specialkey);
      String tablealia = this.getParaKeyTableAlias(specialkey);
      String alianame = this.getParaKeyTableColumn(specialkey);
      this.wherePart.append(" and ");
      this.wherePart.append(tablealia + "." + alianame, specialoper,
          specialvalue);
      this.appendReduncyPara(specialkey, specialoper, specialvalue);
    }
  }

  private void appendWhereSql(String[] parakeys) {
    for (String parakey : parakeys) {
      // ���û���ֶ�ӳ�䣬������
      if (!this.mapParaMetaPath.containsKey(parakey)) {
        continue;
      }
      String[] values = this.getParaValues(parakey);
      String tablealia = this.getParaKeyTableAlias(parakey);
      String alianame = this.getParaKeyTableColumn(parakey);
      this.wherePart.append(" and ");
      this.wherePart.append(tablealia + "." + alianame, values);
      this.appendReduncyPara(parakey, values);
      this.addParaGroupAlias(parakey, tablealia + "." + alianame);
    }
  }

  private String fetchTableAliasByAliasPath(String aliasPath) {
    return this.mapTableAlias.get(aliasPath);
  }

  private String findTableAlias(String attrMetaPath) {
    int lastDotIndex = attrMetaPath.lastIndexOf(".");
    String tableAlias = null;
    if (lastDotIndex < 0) {
      tableAlias = this.getMainTableAlias();
    }
    else {
      IAttribute attribute =
          this.getMainBean().getAttributeByPath(attrMetaPath);
      String tableOfAttr = attribute.getTable().getName();
      String aliasPath;
      if (!this.isAttrOwnToExpandTable(this.getMainBean(), attrMetaPath)) {
        aliasPath = attrMetaPath.substring(0, lastDotIndex);
      }
      else {
        aliasPath = attrMetaPath.substring(0, lastDotIndex) + "." + tableOfAttr;
      }
      tableAlias = this.fetchTableAliasByAliasPath(aliasPath);
    }
    return tableAlias;
  }

  private String generateAlias(String tableName) {
    if (this.mapTableAlias != null
        && this.mapTableAlias.containsValue(tableName)) {
      return this.generateAlias(tableName + "1");
    }
    return tableName;
  }

  private String generateInnerJoinSql(String parentAlias, String table,
      String parentLinkColumn, String linkColumn, String aliasPath) {
    String tablieAlias = this.generateAlias(table);
    StringBuffer sql = new StringBuffer();
    sql.append(" inner join ");
    sql.append(table).append(" ").append(tablieAlias);
    sql.append(" on ");
    sql.append(tablieAlias).append(".").append(linkColumn);
    sql.append(" = ");
    sql.append(parentAlias).append(".").append(parentLinkColumn);
    this.mapTableAlias.put(aliasPath, tablieAlias);
    return sql.toString();
  }

  private IBean getMainBean() {
    if (this.mainBean == null) {
      try {
        this.mainBean =
            MDBaseQueryFacade.getInstance().getBeanByFullClassName(
                this.entityClass.getName());
      }
      catch (MetaDataException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.mainBean;
  }

  private String getMainTableAlias() {
    return this.getMainBean().getTable().getName();
  }

  private String getParaKeyRefDocTable(String paracode) {
    String attrMetaPath = this.mapParaMetaPath.get(paracode);
    IAttribute attribute = this.getMainBean().getAttributeByPath(attrMetaPath);
    IType datatype = attribute.getDataType();
    if (datatype instanceof RefType) {
      RefType reftype = (RefType) datatype;
      if (null != reftype.getRefType()) {
        return reftype.getRefType().getName();
      }
    }
    return paracode;
  }

  /**
   * ��ȡ�ֶ����ڱ�ı�����������������ʱ����鵱ǰsql���Ƿ������صı�
   * ������������Զ�����ر�ƴ��from���֣����ҷ��ر������������ֱ�ӷ��ر���
   * 
   * @param paracode ��ѯģ���ж����field code
   * @return
   */
  private String getParaKeyTableAlias(String paracode) {
    String attrMetaPath = this.mapParaMetaPath.get(paracode);
    return this.getTableAliasByMetaPath(attrMetaPath);
  }

  private String getParaKeyTableColumn(String paracode) {
    String attrMetaPath = this.mapParaMetaPath.get(paracode);
    return this.getTableColumnByMetaPath(attrMetaPath);
  }

  private String[] getParaValues(String key) {
    Set<String> setValue = new HashSet<String>();
    for (IFuncParaValue para : this.listParaValue) {
      String paravalue = para.getParaValue(key);
      setValue.add(paravalue);
    }
    String[] values = new String[setValue.size()];
    setValue.toArray(values);
    return values;
  }

  private String getParentPath(String metaPath) {
    int lastDotIndex = metaPath.lastIndexOf(".");
    String parentAttrPath;
    if (lastDotIndex < 0) {
      parentAttrPath = IQuerySQLGenerator.DEFAULT_KEY;
    }
    else {
      parentAttrPath = metaPath.substring(0, lastDotIndex);
    }
    return parentAttrPath;
  }

  private ITable getParentTableByPath(String parentAttrPath) {
    ITable parentTable;
    if (IQuerySQLGenerator.DEFAULT_KEY.equals(parentAttrPath)) {
      parentTable = this.getMainBean().getTable();
    }
    else {
      IAttribute parentAttr =
          this.getMainBean().getAttributeByPath(parentAttrPath);
      IType parentAttrType = parentAttr.getDataType();
      if (MDUtil.isCollectionType(parentAttrType)) {
        IBean parentBean =
            (IBean) ((ICollectionType) parentAttrType).getElementType();
        parentTable = parentBean.getTable();
      }
      else if (MDUtil.isRefType(parentAttrType)) {
        IBean parentBean = ((IRefType) parentAttrType).getRefType();
        parentTable = parentBean.getTable();
      }
      else {
        parentTable = parentAttr.getTable();
      }
    }
    return parentTable;
  }

  private String getTableColumnByMetaPath(String metapath) {
    IAttribute attribute = this.getMainBean().getAttributeByPath(metapath);
    return attribute.getColumn().getName();
  }

  private void initQuerySql() {
    this.appendMainTable();
    this.appendMainDrWhere();
    this.appendSpecialWhere();
    this.appendNormalWhereSql();
    this.appendLevelWhere();
    this.appendGroupWhere();
  }

  private boolean isAttrOwnToExpandTable(IBean bean, String attrMetaPath) {
    IAttribute attribute = bean.getAttributeByPath(attrMetaPath);
    IType dataType = attribute.getDataType();
    if (MDUtil.isLeafType(dataType)) {
      IBean beanOfAttr = attribute.getOwnerBean();
      String tableOfBean = beanOfAttr.getTable().getName();
      String tableOfAttr = attribute.getTable().getName();
      if (tableOfBean.equals(tableOfAttr)) {
        return false;
      }
      return true;
    }
    return false;
  }

  private void joinExpandTableForicbly(String metaPath) {
    int lastDotIndex = metaPath.lastIndexOf(".");
    String parentAttrPath;
    String tableName;
    // �����ֶ�
    if (lastDotIndex < 0) {
      parentAttrPath = IQuerySQLGenerator.DEFAULT_KEY;
      tableName = metaPath;
    }
    else {
      parentAttrPath = metaPath.substring(0, lastDotIndex);
      tableName = metaPath.substring(lastDotIndex + 1, metaPath.length());
    }
    String tableOfBeanAlias = this.fetchTableAliasByAliasPath(parentAttrPath);
    if (StringUtil.isEmptyWithTrim(tableOfBeanAlias)) {
      this.joinNormalTableForcibly(parentAttrPath);
      tableOfBeanAlias = this.fetchTableAliasByAliasPath(parentAttrPath);
    }
    String linkColumn = null;
    ITable tableOfBean = null;
    if (IQuerySQLGenerator.DEFAULT_KEY.equals(parentAttrPath)) {
      tableOfBean = this.getMainBean().getTable();
    }
    else {
      tableOfBean =
          this.getMainBean().getAttributeByPath(parentAttrPath).getTable();
    }
    linkColumn = tableOfBean.getPrimaryKeyName();
    String innerSql =
        this.generateInnerJoinSql(tableOfBeanAlias, tableName, linkColumn,
            linkColumn, parentAttrPath + "." + tableName);
    this.fromPart.append(innerSql);
  }

  private void joinNormalTableForcibly(String metaPath) {
    String parentAttrPath = this.getParentPath(metaPath);
    String parentAlias = this.fetchTableAliasByAliasPath(parentAttrPath);
    if (StringUtil.isEmptyWithTrim(parentAlias)) {
      this.joinNormalTableForcibly(parentAttrPath);
      parentAlias = this.fetchTableAliasByAliasPath(parentAttrPath);
    }
    ITable parentTable = this.getParentTableByPath(parentAttrPath);
    IAttribute attribute = this.getMainBean().getAttributeByPath(metaPath);
    IType attrType = attribute.getDataType();
    ITable table = attribute.getTable();
    String linkColumn;
    String parentLinkColumn;
    if (MDUtil.isCollectionType(attrType)) {
      parentLinkColumn = parentTable.getPrimaryKeyName();
      linkColumn = attribute.getColumn().getName();
    }
    else if (MDUtil.isRefType(attrType)) {
      table = ((IRefType) attrType).getRefType().getTable();
      parentLinkColumn = attribute.getColumn().getName();
      linkColumn = table.getPrimaryKeyName();
    }
    else {
      table = attribute.getTable();
      parentLinkColumn = table.getPrimaryKeyName();
      linkColumn = parentLinkColumn;
    }
    String innerJoinSql =
        this.generateInnerJoinSql(parentAlias, table.getName(),
            parentLinkColumn, linkColumn, metaPath);
    this.fromPart.append(innerJoinSql);
  }

}
