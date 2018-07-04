package nc.vo.so.pub.exeception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * ���۶����ݲ��쳣
 * 
 * @author ף����
 * @time 2010-8-13 ����01:47:06
 */
public class OrderToleranceException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = 1L;

  public OrderToleranceException(String msg) {
    super(msg);
  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.OrderToleranceCheck.getCheckCode();
  }
}
