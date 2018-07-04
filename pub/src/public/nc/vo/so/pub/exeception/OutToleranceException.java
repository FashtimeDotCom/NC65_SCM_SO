package nc.vo.so.pub.exeception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * 
 * <b>���ⵥ�쳣��</b>
 * 
 * @author ף����
 * @time 2010-8-31 ����02:39:13
 */
public class OutToleranceException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = 1L;

  public OutToleranceException(String msg) {
    super(msg);
  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.OutToleranceCheck.getCheckCode();
  }
}
