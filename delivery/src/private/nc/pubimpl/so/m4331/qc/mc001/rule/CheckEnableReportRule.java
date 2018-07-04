package nc.pubimpl.so.m4331.qc.mc001.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ����ܷ񱨼�
 * 
 * @since 6.0
 * @version 2011-5-12 ����07:49:47
 * @author ף����
 */
public class CheckEnableReportRule {

  public void checkEnable(DeliveryViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (DeliveryViewVO view : views) {
      UFDouble value = view.getItem().getNtotaloutnum();
      if (MathTool.compareTo(value, UFDouble.ZERO_DBL) != 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0154", null, new String[]{view.getItem().getCrowno()})/*��������:{0}�Ѿ����⣬���ܱ��졣*/);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

}
