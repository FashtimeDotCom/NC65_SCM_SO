package nc.pubimpl.so.sobalance.arap.listener;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ������-�����տ��������
 * 
 * <p>ע�᣺����ƽ̨-�������ù���-ҵ����ע��-Ӧ�չ���-������ǰ-������</p>
 * <p>��Ӧ��pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 ����04:37:49
 * @author ��־ΰ
 */
public class VerifyAfterListener implements IBusinessListener {
  
  /**
   * �����к�����¼�����۶���ID���飬����������ӦSoBalanceVO[]���в�������
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try{
      new VerifyAfterListenerAction().doAction(event);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
  }
  
}
