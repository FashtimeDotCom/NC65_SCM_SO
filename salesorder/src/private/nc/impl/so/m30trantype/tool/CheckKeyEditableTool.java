package nc.impl.so.m30trantype.tool;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.IVOMetaStatisticInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.MetaTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.StringUtil;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.ListUtil;

/**
 * @description
 *  У�齻������ָ�������Ƿ���޸�
 * @scene
 *  ���������޸ı���ǰ����
 * @param
 *  voClass Ҫ��ѯ�ĵ���ʵ��
 *  tranTypeVOClass ��������vo �����۶�����������vo
 *  key Ҫ��ѯ���ֶ� ������ģʽfsalemode
 * 
 * @since 6.36
 * @version 2015-1-14 ����6:26:20
 * @author wangshu6
 */
public class CheckKeyEditableTool<E extends ISuperVO, T extends ISuperVO> {

  /**
   * Ҫ��ѯ�ĵ���ʵ��Class
   */
  private Class<E> voClass;

  /**
   * 
   */
  private Class<T> tranTypeVOClass;

  /**
   * Ҫ��ѯ���ֶ�
   */
  private String key;

  public CheckKeyEditableTool(Class<E> voClass, Class<T> tranTypeVOClass,
      String key) {
    this.voClass = voClass;
    this.tranTypeVOClass = tranTypeVOClass;
    this.key = key;
  }

  /**
   * У��ָ���ֶ��Ƿ�ɱ��޸�
   * 
   * @param updateTransTypeVOs �޸ĺ�Ľ�������vo
   * @param originVOs ԭ��������vo
   */
  public void checkKeySuccessModify(T[] updateTransTypeVOs, T[] originVOs) {
    // 1. �Ƿ��е������øý�������
    boolean isReferenced = isTranTypeReferenced(originVOs);
    // 2. �������ͱ����ú��ܷ��޸�
    if (isReferenced) {
      checkSuccessModify(updateTransTypeVOs, originVOs);
    }
  }

  /**
   * �Ƿ��е������øý�������
   * 
   * @param originVOs ԭ��������vo
   * @return true �ѱ����� false δ������
   */
  private boolean isTranTypeReferenced(T[] originVOs) {
    // 1.��ѯ���øý������͵ĵ���vos
    int flag = queryBillVOsLengthByTranTypeVOs(originVOs);
    // 2.���ݲ�ѯ��������
    if (flag == 0) {
      return false;
    }
    return true;
  }

  /**
   * �������ͱ����ú��ܷ��޸�
   * 
   * @param updateTransTypeVOs �޸ĺ�Ľ�������vo
   * @param originVOs ԭ��������vo
   */
  private void checkSuccessModify(T[] updateTransTypeVOs, T[] originVOs) {
    for (int i = 0; i < originVOs.length; i++) {
      // 1. ȡ����Ҫ�жϵ�key���Ե�ԭֵ���޸�ֵ
      Object origValue = originVOs[i].getAttributeValue(this.key);
      Object updateValue = updateTransTypeVOs[i].getAttributeValue(key);
      // 2. �����Ƿ���޸ĵ��ж�
      if (StringUtil.isEmptyTrimSpace(origValue)
          || StringUtil.isEmptyTrimSpace(updateValue)) {
        continue;
      }
      // û�б仯���ж�
      if (origValue.equals(updateValue)) {
        continue;
      }
      // ��齻�����ͱ����ú��Ƿ�֧���޸�key����
      this.checkEditableForKey(origValue, updateValue);
    }
  }

  /**
   * ��ѯ�Ƿ��е��������˸ý�������
   * 
   * @param originVOs ԭ��������vo
   * @return ����У��򷵻����õĵ��ݵĸ�����û���򷵻�0
   */
  private int queryBillVOsLengthByTranTypeVOs(T[] originVOs) {
    int flag = 0;
    String[] ctrantypeidArrs = this.getCtrantypeID(originVOs);
    SqlBuilder sql = new SqlBuilder();
    ITableMeta tableName =  this.getTableName();
    sql.append(" select 1 from ");
    sql.append(tableName.toString());
    sql.append(" where dr = 0 and ");
    sql.append(SOItemKey.CTRANTYPEID, ctrantypeidArrs);
    BaseDAO dao = new BaseDAO();
    ResultSetProcessor processor = new ColumnListProcessor();
    List<Object> results  = new ArrayList<Object>();
    try {
      results = (List<Object>)dao.executeQuery(sql.toString(), processor);
    }
    catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
    if (ListUtil.isEmpty(results) && results.size() == 0){
      return flag ;
    }
    flag = results.size();
    return flag;
  }

  /**
   * ����VOQuery ����voclass �õ�����
   * 
   * @return ��
   */
  private ITableMeta getTableName() {
    IVOMeta voMeta = MetaTool.getVOMeta(this.voClass);
    IVOMetaStatisticInfo statisticInfo = voMeta.getStatisticInfo();
    ITableMeta[] tables = statisticInfo.getTables();
    return tables[0];
  }

  /**
   * ���ݽ�������vo��ȡ�������ͱ���
   * 
   * @param originVOs vos
   * @return �������ͱ�������
   */
  private String[] getCtrantypeID(T[] originVOs) {
    List<String> ctrantypeidList = new ArrayList<String>();
    for (T originVO : originVOs) {
      String ctrantypeid = (String) originVO.getAttributeValue(SOItemKey.CTRANTYPEID);
      ctrantypeidList.add(ctrantypeid);
    }
    return ListUtil.toArray(ctrantypeidList);
  }

  /**
   * �����Ѿ�Ԥ�úõķ�Χ �ж�����ģʽ�Ƿ���޸�;
   * 
   * @param origValue
   * @param updateValue
   * @return �Ƿ���޸�
   */
  private void checkEditableForKey(Object origValue, Object updateValue) {
    SaleModeFactoryStrage parseServise = new SaleModeFactoryStrage();
    // 1. �����޸�ǰ������ֵ ��ѯ�Ƿ��ڿ��޸ķ�Χ��
    ISaleModeStrategy saleMode = parseServise.creatSaleModeStrategy(origValue);
    // Ϊ�գ���˵��û�п��޸ĵķ�Χ
    if (saleMode == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006004_0", "04006004-0011")
      /*@res "���������Ѿ����������ã���ֹ�޸ģ�"*/);

    }
    if (saleMode != null) {
      // 2. ȡ���޸ĵ�����Χ
      SaleMode[] editableSaleModes = saleMode.getParseSaleMode();
      // 3. �ж�updateValue�Ƿ�������ķ�Χ��
      boolean isEditable =
          isEditableForSaleMode(editableSaleModes, updateValue);
      // 4. �����޸ĵ�����Χ�� ������ʾ��Ϣ�������޸ĵķ�Χ��ʾ���û�
      if (!isEditable) {
        createErrMsg(editableSaleModes);
      }

    }
  }

  /**
   * ���������ʾ��Ϣ�������޸ĵķ�Χ��ʾ���û�
   * 
   * @param editableSaleModes ���޸ĵ�����Χ
   */
  private void createErrMsg(SaleMode[] editableSaleModes) {
    StringBuilder errMsg = new StringBuilder();
    List<String> listValiField = new ArrayList<String>();
    // ���޸ĵ�����Χ��ÿ��ö������
    for (SaleMode editableSaleMode : editableSaleModes) {
      listValiField.add(editableSaleMode.getName());
    }

    if (listValiField.size() > 0) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0505")/*���������Ѿ����������ã�����ģʽֻ�����޸ĳɣ�\n*/);
      for (String field : listValiField) {
        errMsg
            .append("[")
            .append(field)
            .append("]")
            .append(
                NCLangResOnserver.getInstance().getStrByID("4006011_0",
                    "04006011-0284")/* �� */);
      }
      errMsg.deleteCharAt(errMsg.length() - 1);
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  /**
   * �����޸�ǰ�Ľ���ģʽȡ��Ӧ�Ŀ��޸ĵ�Ŀ�Ľ���ģʽ�����ҳ���ϵĽ���ģʽ�ڷ�Χ�ڣ����޸ģ�
   * 
   * @param arrays
   * @param updateValue
   * @return
   */
  private boolean isEditableForSaleMode(SaleMode[] editableSaleModes,
      Object updateValue) {

    for (SaleMode editableSaleMode : editableSaleModes) {
      // ����޸ĺ������ģʽ��������ķ�Χ�ڣ��������޸�
      if (updateValue.equals(editableSaleMode.getIntValue())) {
        return true;
      }
    }
    return false;
  }

}
