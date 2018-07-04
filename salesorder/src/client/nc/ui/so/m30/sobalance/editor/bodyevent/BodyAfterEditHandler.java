package nc.ui.so.m30.sobalance.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;

/**
 * ���������༭��ʱ���ɷ���
 * 
 * @since 6.0
 * @version 2011-7-10 ����11:57:02
 * @author ��־ΰ
 */
public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    String editKey = e.getKey();
    // �տ���ݺ�
    if (SoBalanceBVO.VARBILLCODE.equals(editKey)) {
      GatheringEditHandler handler = new GatheringEditHandler();
      handler.afterEdit(e);
    }
  }
}
