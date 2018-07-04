package nc.ui.so.mbuylargess.view;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

public class BuyLargessPrecision {

  /**
   * ��������������
   */
  private static final String[] BODYNUMKEYS = new String[] {
    BuyLargessBVO.NNUM
  };

  /**
   * �������ͽ��
   */
  private static final String[] BODYORIGMNYKEYS = new String[] {
    BuyLargessBVO.NMNY
  };

  /**
   * ���嵥��
   */
  private static final String[] BODYPRICEKEYS = new String[] {
    BuyLargessBVO.NPRICE
  };

  /**
   * ��ͷ����������
   */
  private static final String[] HEADNUMKEYS = new String[] {
    BuyLargessHVO.NBUYNUM
  };

  private static BuyLargessPrecision precision = new BuyLargessPrecision();

  private BuyLargessPrecision() {
    //
  }

  public static BuyLargessPrecision getInstance() {
    return BuyLargessPrecision.precision;
  }

  /**
   * 
   * �����������������ÿ�Ƭ���澫�ȡ�
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
    // ����ֵ
    BillItem curitem = cardpanel.getHeadItem(BuyLargessHVO.PK_CURRINFO);
    BillModel model = cardpanel.getBillModel();
    TopLimitValueDecimalAdapter adpter =
        new TopLimitValueDecimalAdapter(model, curitem);
    cardpanel.getBodyItem(BuyLargessBVO.NTOPLIMITVALUE).addDecimalListener(
        adpter);
  }

  /**
   * 
   * �������������������б���澫�ȡ�
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    this.setBillPrecision(scaleprocess);
    // ����ֵ
    BillItem curitem =
        listpanel.getHeadBillModel().getItemByKey(BuyLargessHVO.PK_CURRINFO);
    BillModel model = listpanel.getBodyBillModel();
    TopLimitValueDecimalAdapter adpter =
        new TopLimitValueDecimalAdapter(model, curitem);
    listpanel.getBodyItem(BuyLargessBVO.NTOPLIMITVALUE).addDecimalListener(
        adpter);
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // ���嵥��
    scaleprocess.setPriceCtlInfo(BuyLargessPrecision.BODYPRICEKEYS,
        PosEnum.body, null,BuyLargessHVO.PK_CURRINFO,PosEnum.head,null);

    // ��������������
    scaleprocess.setNumCtlInfo(BuyLargessPrecision.BODYNUMKEYS, PosEnum.body,
        null, BuyLargessBVO.PK_MEASDOC, PosEnum.body, null);

    // ��ͷ����������
    scaleprocess.setNumCtlInfo(BuyLargessPrecision.HEADNUMKEYS, PosEnum.head,
        null, BuyLargessHVO.CBUYUNITID, PosEnum.head, null);

    // �������ͽ��
    scaleprocess.setMnyCtlInfo(BuyLargessPrecision.BODYORIGMNYKEYS,
        PosEnum.body, null, BuyLargessHVO.PK_CURRINFO, PosEnum.head, null);

    scaleprocess.process();

  }

}
