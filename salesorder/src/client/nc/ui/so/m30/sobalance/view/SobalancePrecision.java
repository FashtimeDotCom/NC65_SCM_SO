package nc.ui.so.m30.sobalance.view;

import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;

/**
 * �տ�������ȴ�������
 * 
 * @since 6.0
 * @version 2011-5-24 ����11:19:06
 * @author ô��
 */
public class SobalancePrecision {

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEYS = new String[] {
    SoBalanceBVO.NORIGACCBALMNY, SoBalanceBVO.NORIGORDBALMNY,
    SoBalanceBVO.NORIGTHISBALMNY, SoBalanceBVO.NORIGARMNY,
    // �����������(����������)
    "norigotherbalmny"
  };

  // ԭ�ҽ���ͷ��
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SoBalanceHVO.NTOTALORIGBALMNY, SoBalanceHVO.NTOTALORIGTAXMNY,
    SoBalanceHVO.NTOTALPAYMNY,
  };

  private static SobalancePrecision precision = new SobalancePrecision();

  /**
   * 
   * PreOrderPrecision �Ĺ�����
   */
  private SobalancePrecision() {
    //
  }

  public static SobalancePrecision getInstance() {
    return SobalancePrecision.precision;
  }

  /**
   * �ṩ�����۶��������ã���Ϊ�����ֶ��ڱ���
   * 
   * @param scaleprocess
   */
  public void setCashSalePrecision(BillScaleProcessor scaleprocess) {

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYS, PosEnum.body,
        null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.body, null);
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYSHEAD,
        PosEnum.body, null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.body, null);
    scaleprocess.process();
  }

  public void setBillPrecision(BillScaleProcessor scaleprocess) {

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYS, PosEnum.body,
        null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYSHEAD,
        PosEnum.head, null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.head, null);

    scaleprocess.process();

  }

  /**
   * 
   * �����������������ÿ�Ƭ���澫�ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param cardpanel
   *          <p>
   * @author fengjb
   * @time 2010-5-26 ����04:55:35
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);

    this.setBillPrecision(scaleprocess);
  }

  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);

    this.setBillPrecision(scaleprocess);
  }

  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYS,
        SoBalanceHVO.CORIGCURRENCYID);

    scaleprocess.process();
  }

}
