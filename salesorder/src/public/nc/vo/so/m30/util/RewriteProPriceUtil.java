package nc.vo.so.m30.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.opc.mc1.ICustomerpoQueryForB2B;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���۶������棬�޸ģ�ɾ��������/�����ر�ʱ��д�����۸��ʱ
 * ���ڸ���д��������Դ���Ը�ֵ
 * 
 * @since 6.5
 * @version 2014-04-14 13:29:19
 * @author zhangyfr
 */
public class RewriteProPriceUtil {

  /**
   * ������Դ��ID�����Դ���Ӷ����Ŀ⻧�������۸�ID��
   * ���д�����۸��Ļ�д������
   * 
   * @param map
   * @return paras
   * @throws BusinessException
   */
  public List<SOUpdatePPLimitExePara> setSrcParas(
      Map<String, SOUpdatePPLimitExePara> map) {
    List<SOUpdatePPLimitExePara> paras =
        new ArrayList<SOUpdatePPLimitExePara>();
    
    if (!SysInitGroupQuery.isOPCEnabled()) {
      return paras;
    }
    ICustomerpoQueryForB2B b2bQuery =
        NCLocator.getInstance().lookup(ICustomerpoQueryForB2B.class);
    Map<String, String> b2bMap = new HashMap<>();
    try {
      b2bMap =
          b2bQuery.queryPromoInfoByCustomerbid(map.keySet().toArray(
              new String[map.keySet().size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    for (String key : map.keySet()) {
      String value = b2bMap.get(key);
      if (null != value) {
        String[] values = value.split("-");
        SOUpdatePPLimitExePara para = map.get(key);
        para.setSrcbillcustomerid(values[0]);
        para.setSrcrowprmtpriceid(values[1]);
        paras.add(para);
      }
    }
    return paras;

  }
}
