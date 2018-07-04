/**
 * 
 */
package nc.ui.so.m30.billui.billevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

/**
 * PUBAPP��֧�ֶ�ѡ�¼�����д������ṩ��ѡ������Ϣ
 * ��Ҫ�����¼��ɷ���ı仯�¼�����
 * 
 * @author gdsjw
 *
 */
public class MultiSelectCardBodyAfterEditEvent extends CardBodyAfterEditEvent {

  private int[] editrows;

  public MultiSelectCardBodyAfterEditEvent(
      CardBodyAfterEditEvent cardbodyaftereditevent) {
    super(cardbodyaftereditevent.getBillCardPanel(), cardbodyaftereditevent
        .getTableCode(), cardbodyaftereditevent.getRow(),
        cardbodyaftereditevent.getKey(), cardbodyaftereditevent.getValue(),
        cardbodyaftereditevent.getOldValue());
    this.setContext(cardbodyaftereditevent.getContext());
    this.setSource(cardbodyaftereditevent.getSource());
    this.setContextObject(cardbodyaftereditevent.getContextObject());
    this.setEditrows(new int[] {
      cardbodyaftereditevent.getRow()
    });
  }

  public int[] getEditrows() {
    return this.editrows;
  }

  public void setEditrows(int[] editrows) {
    this.editrows = editrows;
  }

}
