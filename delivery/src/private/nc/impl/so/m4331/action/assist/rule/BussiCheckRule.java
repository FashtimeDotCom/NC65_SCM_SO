package nc.impl.so.m4331.action.assist.rule;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.pubitf.to.m5x.pub.IM5XAbandonToleranceCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.pub.tolerance.IAbandonToleranceCheck;

/**
 * ����ҵ������ʱ����м��
 * 
 * @since 6.0
 * @version 2011-6-22 ����04:19:11
 * @author ף����
 */
public class BussiCheckRule {
  public void setBusiCheckFlag(Map<String, Boolean> map) {
    if ((null == map) || (map.size() == 0)) {
      return;
    }
    Set<Entry<String, Boolean>> entrys = map.entrySet();
    for (Entry<String, Boolean> entry : entrys) {
      String key = entry.getKey();
      boolean flag = entry.getValue().booleanValue();
      if (key.equals(BusinessCheck.ATPCheck.getCheckCode())) {
        this.setAtpFlag(flag);
      }
      else if (key.equals(BusinessCheck.CreditCheck.getCheckCode())) {
        this.setCrediteFlag(flag);
      }
      else if (key.equals(BusinessCheck.OrderToleranceCheck.getCheckCode())) {
        this.setSaleorderFlag(flag);
      }
      else if (key.equals(BusinessCheck.TransDeliToleranceCheck.getCheckCode())) {
        this.setTranOrderFlag(flag);
      }
    }
  }

  /*
   * ���������
   */
  private void setAtpFlag(boolean ischeck) {
    if (!ischeck) {
      // ����ATP���
      SOATPprocess.abandonATPCheck();
    }
  }

  /*
   * ���ü��
   */
  private void setCrediteFlag(boolean flag) {
    if (!flag) {
      IIgnoreCreditCheck service =
          NCLocator.getInstance().lookup(IIgnoreCreditCheck.class);
      try {
        service.ignoreCreditCheck();
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /*
   * �����۶����������
   */
  private void setSaleorderFlag(boolean flag) {
    if (!flag) {
      // �����������������
      IAbandonToleranceCheck service =
          NCLocator.getInstance().lookup(IAbandonToleranceCheck.class);
      service.abandonOrderToleranceCheck();
    }
  }

  private void setTranOrderFlag(boolean flag) {
    if (!flag) {
      // ���������������������
      IM5XAbandonToleranceCheck service =
          NCLocator.getInstance().lookup(IM5XAbandonToleranceCheck.class);
    try {
      service.abandonTransDeliToleranceCheck();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
      //service.abandonTransDeliToleranceCheck();
    }
  }
}
