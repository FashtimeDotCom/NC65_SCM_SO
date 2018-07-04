package nc.ui.so.m30.revise.editor.headevent;

import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.revise.rule.IsEditableCheckRule;

/**
 * ������ͷ�༭ǰ�¼��ɷ���
 * 
 * @since 6.0
 * @version 2011-6-8 ����11:19:58
 * @author fengjb
 */
public class ReviseHeadBeforeEditHandler extends
    nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    // 1.�޶��߼�
    // --�Ƿ���Ա༭
    boolean isEditable =
        new IsEditableCheckRule().check(e.getBillCardPanel(), -1, e.getKey());
    if (!isEditable) {
      e.setReturnValue(Boolean.valueOf(isEditable));
      return;
    }

    // 2.�������۶����߼�
    super.handleAppEvent(e);
  }

}
