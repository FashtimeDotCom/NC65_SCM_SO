package nc.ui.so.m30.sobalance.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;

/**
 * ������������༭ǰʱ���ɷ���
 * 
 * @since 6.0
 * @version 2011-7-10 ����11:56:22
 * @author ��־ΰ
 */
public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    // Ĭ��Ϊ�ɱ༭
    e.setReturnValue(Boolean.TRUE);
    String editKey = e.getKey();

    // �տ���ݺ�
    if (SoBalanceBVO.VARBILLCODE.equals(editKey)) {
      GatheringEditHandler handler = new GatheringEditHandler();
      handler.beforeEdit(e);
    }
    // �����տ���
    else if (SoBalanceBVO.NORIGTHISBALMNY.equals(editKey)) {
      ThisBalEditHandler handler = new ThisBalEditHandler();
      handler.beforeEdit(e);
    }
  }

}
