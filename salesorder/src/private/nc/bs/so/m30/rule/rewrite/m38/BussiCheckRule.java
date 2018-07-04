package nc.bs.so.m30.rule.rewrite.m38;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
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
      else if (key
          .equals(BusinessCheck.CreditOverPeriodDayCheck.getCheckCode())) {
        this.setCreditOverPeriodDay(flag);
      }
      else if (key.equals(BusinessCheck.CreditOverPeriodInnerDayCheck
          .getCheckCode())) {
        this.setCreditOverPeriodInnerDay(flag);
      }
      else if (key.equals(BusinessCheck.CreditOverPeriodMoneyCheck
          .getCheckCode())) {
        this.setCreditOverPeriodMoney(flag);
      }
      else if (key.equals(BusinessCheck.PreOrderToleranceCheck.getCheckCode())) {
        this.setPreorderFlag(flag);
      }
    }
  }

  private void setPreorderFlag(boolean flag) {
    if (!flag) {
      IAbandonToleranceCheck service =
          NCLocator.getInstance().lookup(IAbandonToleranceCheck.class);
      service.abandonPreOrderToleranceCheck();
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

  /**
   * �������������
   * 
   * @param userObj
   */
  private void setCreditOverPeriodDay(boolean flag) {
    if (!flag) {
      // �����������������
      IIgnoreAccountCheck service =
          NCLocator.getInstance().lookup(IIgnoreAccountCheck.class);
      try {
        service.ignoreOverPeriodDayCheck();
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /*
   * ���ڿ������������
   * 
   * @param userObj
   */
  private void setCreditOverPeriodInnerDay(boolean flag) {
    if (!flag) {
      // �����������������
      IIgnoreAccountCheck service =
          NCLocator.getInstance().lookup(IIgnoreAccountCheck.class);
      try {
        service.ignoreOverPeriodInnerDayCheck();
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * �����ڽ����
   * 
   * @param userObj
   */
  private void setCreditOverPeriodMoney(boolean flag) {
    if (!flag) {
      // ���������ڽ����
      IIgnoreAccountCheck service =
          NCLocator.getInstance().lookup(IIgnoreAccountCheck.class);
      try {
        service.ignoreOverPeriodMoneyCheck();
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
