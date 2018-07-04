package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * Ԥ������ͷ�༭ǰ�¼��ɷ���
 * 
 * @since 6.0
 * @version 2011-6-8 ����11:21:40
 * @author fengjb
 */
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    e.setReturnValue(Boolean.TRUE);
    String editkey = e.getKey();
    // ��������
    if (PreOrderHVO.CTRANTYPEID.equals(editkey)) {
      TranTypeEditHandler handler = new TranTypeEditHandler();
      handler.beforeEdit(e);
    }
    // ����
    else if (PreOrderHVO.CDEPTVID.equals(editkey)) {
      DeptEditHandler handler = new DeptEditHandler();
      handler.beforeEdit(e);
    }
    // ҵ��Ա
    else if (PreOrderHVO.CEMPLOYEEID.equals(editkey)) {
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEdit(e);
    }
  }
}
