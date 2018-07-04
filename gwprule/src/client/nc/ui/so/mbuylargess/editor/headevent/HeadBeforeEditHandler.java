package nc.ui.so.mbuylargess.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

/**
 * �������ñ�ͷ�༭ǰ�¼�
 * 
 * @since 6.0
 * @version 2013-8-27 ����04:24:01
 * @author ����
 */
@SuppressWarnings("restriction")
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    e.setReturnValue(Boolean.TRUE);

    String editkey = e.getKey();

    // ��λ
    if (BuyLargessHVO.CBUYUNITID.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
  }

}
