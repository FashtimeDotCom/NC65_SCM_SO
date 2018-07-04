package nc.ui.so.salequotation.query;

import nc.vo.price.pub.colctrl.SaleClColCtrlUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.QueryConditionVO;
import nc.vo.pub.query.QueryTempletTotalVO;

public class SalequoQueryDLGUtils {
  private SalequoQueryDLGUtils() {
    // do nothing
  }

  /**
   * ���ݿͻ������ǻ������໹�����۷������Բ�ѯģ���ϵ������ͻ������ֶε��е�һ����������
   * 
   * @param totalVO
   * @param pk_group
   */
  public static void hideCusClCondition(QueryTempletTotalVO totalVO,
      String pk_group) {
    QueryConditionVO[] conditions = totalVO.getConditionVOs();
    if (!SaleClColCtrlUtils.isCusBasClDisp(pk_group)) {
      QueryConditionVO cusBasCond =
          SalequoQueryDLGUtils.getCusBasCondition(conditions);
      if (cusBasCond != null) {
        cusBasCond.setIfUsed(UFBoolean.FALSE);
      }
    }
    else {
      QueryConditionVO cusSaleCond =
          SalequoQueryDLGUtils.getCusSaleCondition(conditions);
      if (cusSaleCond != null) {
        cusSaleCond.setIfUsed(UFBoolean.FALSE);
      }
    }
  }

  /**
   * �������Ϸ����ǻ������໹�����۷������Բ�ѯģ���ϵ��������Ϸ����ֶε��е�һ����������
   * 
   * @param totalVO
   * @param pk_group
   */
  public static void hideMarClCondition(QueryTempletTotalVO totalVO,
      String pk_group) {
    QueryConditionVO[] conditions = totalVO.getConditionVOs();
    if (!SaleClColCtrlUtils.isMarBasClDisp(pk_group)) {
      QueryConditionVO marBasCond =
          SalequoQueryDLGUtils.getMarBasCondition(conditions);
      if (marBasCond != null) {
        marBasCond.setIfUsed(UFBoolean.FALSE);
      }
    }
    else {
      QueryConditionVO marSaleCond =
          SalequoQueryDLGUtils.getMarSaleCondition(conditions);
      if (marSaleCond != null) {
        marSaleCond.setIfUsed(UFBoolean.FALSE);
      }
    }
  }

  private static QueryConditionVO getCusBasCondition(
      QueryConditionVO[] conditions) {
    for (QueryConditionVO cond : conditions) {
      if ("pk_customer.pk_custclass".equals(cond.getFieldCode())) {
        return cond;
      }
    }
    return null;
  }

  private static QueryConditionVO getCusSaleCondition(
      QueryConditionVO[] conditions) {
    for (QueryConditionVO cond : conditions) {
      if ("pk_customer.sales.pk_custsaleclass".equals(cond.getFieldCode())) {
        return cond;
      }
    }
    return null;
  }

  private static QueryConditionVO getMarBasCondition(
      QueryConditionVO[] conditions) {
    for (QueryConditionVO cond : conditions) {
      if ("salequotationdetail.pk_material_v.pk_marbasclass".equals(cond
          .getFieldCode())) {
        return cond;
      }
    }
    return null;
  }

  private static QueryConditionVO getMarSaleCondition(
      QueryConditionVO[] conditions) {
    for (QueryConditionVO cond : conditions) {
      if ("salequotationdetail.pk_material_v.materialsale.pk_marsaleclass"
          .equals(cond.getFieldCode())) {
        return cond;
      }
    }
    return null;
  }
}
