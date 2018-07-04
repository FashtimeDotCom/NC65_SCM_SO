package nc.impl.so.m38.listener;

import java.util.Map;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.impl.so.m38.migrate.action.PreOrderDestBillUpdateAction;
import nc.vo.pub.BusinessException;

public class DestBillUpdateAfterListener implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    if (("1065".equals(event.getEventType()))){      
      Object object = ((BusinessEvent.BusinessUserObj)event.getUserObject()).getUserObj();
      Map<String, String> oldNewTrantypeIdMap = (Map<String, String>)object;
      // ����Ԥ���������ε��ݽ����ε��ݵ���Դ���������Լ���Դ�������͸���Ϊ��������Ԥ�����ĵ������ͺͽ�������
      PreOrderDestBillUpdateAction pdbuAction = new PreOrderDestBillUpdateAction();
      pdbuAction.update(oldNewTrantypeIdMap);
    }
  }
}
