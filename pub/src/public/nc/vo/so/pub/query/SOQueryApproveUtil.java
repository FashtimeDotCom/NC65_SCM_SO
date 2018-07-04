package nc.vo.so.pub.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * ���۲�ѯ����"������"������
 * 
 * @since 6.0
 * @version 2011-7-19 ����09:49:41
 * @author ��־ΰ
 */
public class SOQueryApproveUtil {

  /**
   * ������(��ѯģ������"������"�ֶ�����)
   */
  public static final String BISAPPROVING = "bisapproving";

  /**
   * �Ӹ�����ҵ��VO�����й��˳������û��Ĵ�������ҵ��VO
   * 
   * @param queryScheme ��ѯ����
   * @param vos ��Ҫ���˵ľۺ�VO����
   * @param billType ��������
   * @return ���˺�ľۺ�VO����
   */
  public static <T extends AggregatedValueObject> T[] filterForApprove(
      IQueryScheme queryScheme, T[] vos, String billType, String trantypekey) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition condition =
        qrySchemeProcessor.getQueryCondition(SOQueryApproveUtil.BISAPPROVING);

    if (null == condition) {
      return vos;
    }

    Object[] values = condition.getValues();
    if (ValueUtils.getUFBoolean(values[0]).booleanValue()) {
      String userId = AppContext.getInstance().getPkUser();
      return PfServiceUtil.filterForApprove(vos, billType, userId, trantypekey);
    }

    return vos;
  }
}
