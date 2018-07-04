package nc.vo.so.pub.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.SOCurrencyUtil;

/**
 * ���۹����ڽ��е��۽���㷨֮ǰ Condition �Ĵ���
 * 
 * ���õ�����ֻ�й����ĸ�����ҵ���޹ص�condition
 * 1���Ƿ���б��һ���
 * 2����˰���Ȼ�����˰����
 * 3���Ƿ����ü��ű���
 * 4���Ƿ�����ȫ�ֱ���
 * 5�������޸��Ƿ����㵽ԭ�ң�Ĭ�ϲ����㣩
 * 
 * @since 6.0
 * @version 2012-2-23 ����02:19:01
 * @author ô��
 */
public class SOCalConditionRule {

  /**
   * 
   * @return
   */
  public static Condition getCondition() {
    Condition cond = new Condition();
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(true);
    // ���ҽ���޸�����ԭ��
    cond.setCalOrigCurr(true);

    // �ɲ�����ú�˰���Ȼ�����˰����
    boolean isTaxPrior = SOCalConditionRule.isTaxPrior();
    cond.setIsTaxOrNet(isTaxPrior);

    // NC001��������
    SOCurrencyUtil currutil = SOCurrencyUtil.getInstance();
    cond.setGroupLocalCurrencyEnable(currutil.isGroupCurrencyEnable());
    cond.setOrigCurToGroupMoney(currutil.isLocalCurToGroupMoney());
    // NC002��������
    cond.setGlobalLocalCurrencyEnable(currutil.isGlobalCurrencyEnable());
    cond.setOrigCurToGlobalMoney(currutil.isLocalCurToGlobalMoney());
    return cond;
  }

  public static boolean isTaxPrior() {
    UFBoolean so23 = null;
    String pk_group = AppContext.getInstance().getPkGroup();
    so23 = SCMParameterUtils.getSCM13(pk_group);
    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }

  /**
   * ����SCM13 �����Ƿ�˰���� ���ص����ֶ�
   * ��˰����ʱ����"��˰����"�ֶΣ���˰����ʱ����"��˰����"�ֶ�
   * 
   * @return
   */
  public static String getCalPriceKey() {
    if (SOCalConditionRule.isTaxPrior()) {
      return SOItemKey.NQTORIGTAXPRICE;
    }

    return SOItemKey.NQTORIGPRICE;
  }
}
