package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * Ԥ������ͷ�༭���¼��ɷ���
 * 
 * @since 6.0
 * @version 2011-6-8 ����02:13:06
 * @author fengjb
 */
public class HeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    String editkey = e.getKey();
    // ����
    if (PreOrderHVO.CORIGCURRENCYID.equals(editkey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.afteEdit(e);
    }
    // ��������
    else if (PreOrderHVO.DBILLDATE.equals(editkey)) {
      BillDateEditHandler hanlder = new BillDateEditHandler();
      hanlder.afterEdit(e);
    }
    // �����ۿ�
    else if (PreOrderHVO.NDISCOUNTRATE.equals(editkey)) {
      DiscountRateEditHandler handler = new DiscountRateEditHandler();
      handler.afterEdit(e);
    }
    // �ͻ�
    else if (PreOrderHVO.CCUSTOMERID.equals(editkey)) {
      CustomerEditHandler handler = new CustomerEditHandler();
      handler.afterEdit(e);
    }
    // ������������
    else if (PreOrderHVO.CCHANNELTYPEID.equals(editkey)) {
      ChannelTypeEditHandler handler = new ChannelTypeEditHandler();
      handler.afterEdit(e);
    }
    // ���䷽ʽ
    else if (PreOrderHVO.CTRANSPORTTYPEID.equals(editkey)) {
      TransportTypeEditHandler handler = new TransportTypeEditHandler();
      handler.afterEdit(e);
    }
  }
}
