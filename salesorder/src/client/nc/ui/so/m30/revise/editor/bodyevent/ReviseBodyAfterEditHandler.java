package nc.ui.so.m30.revise.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * �����޶�����༭���ɷ���
 * 
 * @since 6.0
 * @version 2011-8-4 ����06:46:23
 * @author ��־ΰ
 */
public class ReviseBodyAfterEditHandler extends BodyAfterEditHandler {

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    // 1.�޶��߼�
    String editKey = e.getKey();
    // ����
    if (SaleOrderBVO.NASTNUM.equals(editKey)) {
      ReviseAstNumEditHandler handler = new ReviseAstNumEditHandler();
      handler.afterEdit(e);
    }
    // ������
    else if (SaleOrderBVO.NNUM.equals(editKey)) {
      ReviseNumEditHandler handler = new ReviseNumEditHandler();
      handler.afterEdit(e);
    }
    // ���۵�λ����
    else if (SaleOrderBVO.NQTUNITNUM.equals(editKey)) {
      ReviseQtUnitNumEditHandler handler = new ReviseQtUnitNumEditHandler();
      handler.afterEdit(e);
    }
    else {
      // 2.�������۶����߼�
      super.handleAppEvent(e);
    }
  }
}
