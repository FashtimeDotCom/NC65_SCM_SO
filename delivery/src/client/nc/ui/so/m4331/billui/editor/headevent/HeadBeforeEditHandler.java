package nc.ui.so.m4331.billui.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.so.m4331.entity.DeliveryHVO;

public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    // ����
    if (e.getKey().equals(DeliveryHVO.CSENDDEPTVID)) {
      DeptEditHandler handler = new DeptEditHandler();
      handler.beforeEdit(e);
    }
    // ҵ��Ա
    else if (e.getKey().equals(DeliveryHVO.CSENDEMPLOYEEID)) {
      EmployerEditHandler handler = new EmployerEditHandler();
      handler.beforeEmployeeEdit(e);
    }
    else if (e.getKey().equals(DeliveryHVO.CTRANTYPEID)) {
      TrantypeEditHandler handler = new TrantypeEditHandler();
      handler.beforeTrantypeEdit(e);
    }
    // ����·��
    else if (e.getKey().equals(DeliveryHVO.CTRANSPORTROUTEID)) {
      TranSportEditHandler handler = new TranSportEditHandler();
      handler.beforeEdit(e);
    }
    if (e.getReturnValue() == null) {
      e.setReturnValue(Boolean.TRUE);
    }
  }
}
