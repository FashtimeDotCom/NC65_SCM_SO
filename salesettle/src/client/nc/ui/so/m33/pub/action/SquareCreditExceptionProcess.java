package nc.ui.so.m33.pub.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;

/**
 * ���ü���쳣��������
 * 
 * �ֹ����㡢�ݹ�������Գ����Ҫ���ã��Խ�����������쳣�����⴦��
 * 
 * @since 6.0
 * @version 2011-9-1 ����10:49:47
 * @author zhangcheng
 */
public class SquareCreditExceptionProcess {

  public void processCreditCheckException(CreditCheckException creditex) {
    ICreditCheckMessageService service =
        NCUILocator.getInstance().lookup(ICreditCheckMessageService.class,
            NCModule.CREDIT);
    try {
      service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
          .getParent(), creditex);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
