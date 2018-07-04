package nc.bs.so.m33.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * ���۽���ҵ����Ĵ����߼��Ķ��ο�������㶨��
 * 
 * @author zc
 * 
 *         2009-12-19 ����02:47:34
 */
public enum BPPlugInPoint implements IPluginPoint {
  /**
   * ȡ�����ݹ�Ӧ��
   */
  CancelETIncomeFor4CBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelETIncomeFor4CBP"),
      
  /**
   * ȡ�����ݹ�Ӧ��
   */
  CancelETIncomeFor4CDetailBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelETIncomeFor4CBP.cancelDetail"),

  /**
   * ȡ�����۷�Ʊ���ɱ�Ӧ��
   */
  CancelIACostFor32BP("nc.bs.so.m33.biz.bp.cancelia.CancelIACostFor32BP"),
  
  /**
   * ȡ�����۷�Ʊ��ϸ���ɱ�Ӧ��
   */
  CancelIACostFor32DetailBP("nc.bs.so.m33.biz.bp.cancelia.CancelIACostFor32BP.cancelDetail"),

  /**
   * ȡ�����۷�Ʊ���ɱ�Ӧ��
   */
  CancelIACostFor4CBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIACostFor4CBP"),
      
  /**
   * ȡ�����۷�Ʊ��ϸ���ɱ�Ӧ��
   */
  CancelIACostFor4CDetailBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIACostFor4CBP.cancelDetail"),

  /**
   * ȡ��;������㵥���ɱ�Ӧ��
   */
  CancelIACostFor4453BP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIACostFor4453BP"),

  /**
   * ȡ��;������㵥��ϸ���ɱ�Ӧ��
   */
  CancelIACostFor4453DetailBP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIACostFor4453BP.cancelDetail"),
                  

  /**
   * ȡ�����۷�Ʊ��������Ʒ
   */
  CancelIARegisterFor32BP(
      "nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterFor32BP"),
      
  /**
   * ȡ�����۷�Ʊ��������Ʒ
   */
  CancelIARegisterFor32DetailBP(
      "nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterFor32BP.cancelDetail"),

  /**
   * ȡ��;������㵥��������Ʒ
   */
  CancelIARegisterFor4453BP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIARegisterFor4453BP"),
      
  /**
   * ȡ��;������㵥��ϸ��������Ʒ
   */
  CancelIARegisterFor4453DetailBP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIARegisterFor4453BP.cancelDetail"),
      
  /**
   * ȡ�����۳��ⵥ��������Ʒ
   */
  CancelIARegisterFor4CBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIARegisterDebitFor4CBP"),
      
  /**
   * ȡ�����۳�������㵥������Ʒ����
   */
  CancelIARegisterCreditFor4C(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.AbstractCancelIARegisterCreditFor4CBP"),

  /**
   * ȡ������Գ�
   */
  CancelOutRush(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelOutRushIncomeFor4CBP"),

  /**
   * ȡ�����۷�Ʊ��ȷ��Ӧ��
   */
  CancelARIncomeFor32BP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP"),

  /**
   * ȡ�����۷�Ʊ��ϸ��ȷ��Ӧ��
   */
  CancelARIncomeFor32DetailBP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP.cancelDetail"),

  /**
   * ȡ�����۷�Ʊ���س�Ӧ��
   */
  CancelARRushIncomeFor32BP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP"),

  /**
   * ȡ�����۷�Ʊ��ϸ���س�Ӧ��
   */
  CancelARRushIncomeFor32DetailBP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP.cancelDetail"),

  /**
   * ȡ��;������㵥��ȷ��Ӧ��
   */
  CancelARIncomeFor4453BP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARIncomeFor4453BP"),

  /**
   * ȡ��;������㵥��ϸ��ȷ��Ӧ��
   */
  CancelARIncomeFor4453DetailBP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARIncomeFor4453BP.cancelDetail"),

  /**
   * ȡ��;������㵥���س�Ӧ��
   */
  CancelARRushIncomeFor4453BP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARRushIncomeFor4453BP"),

  /**
   * ȡ��;������㵥��ϸ���س�Ӧ��
   */
  CancelARRushIncomeFor4453DetailBP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARRushIncomeFor4453BP.cancelDetail"),

  /**
   * ȡ�����۳��⴫ȷ��Ӧ��
   */
  CancelARIncomeFor4CBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARIncomeFor4CBP"),

  /**
   * ȡ�����۳�����ϸ��ȷ��Ӧ��
   */
  CancelARIncomeFor4CDetailBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARIncomeFor4CBP.cancelDetail"),

  /**
   * ȡ�����۳��⴫�س�Ӧ��
   */
  CancelARRushIncomeFor4CBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP"),

  /**
   * ȡ�����۳�����ϸ���س�Ӧ��
   */
  CancelARRushIncomeFor4CDetailBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP.cancelDetail"),

  /**
   * ���۽�����ϸ��ɾ��
   */
  DeleteDetailBP("nc.bs.so.m33.service.DeleteSquareDetailBP"),

  /**
   * ���۷�Ʊ���㵥��������
   */
  InsertSquare32BP("nc.bs.so.m33.maintain.m32.InsertSquare32BP"),
  
  /**
   * ����;��������㵥��������
   */
  InsertSquare4453BP("nc.bs.so.m33.maintain.m4453.InsertSquare4453BP"),
  
  /**
   * ���۳�������㵥��������
   */
  InsertSquare4CBP("nc.bs.so.m33.maintain.m4c.InsertSquare4CBP"),

  /**
   * ���۽�����ϸ����������
   */
  InsertDetailBP("nc.bs.so.m33.service.InsertSquareDetailBP"),

  /**
   * ���۳��ⵥ�ֹ��ݹ�Ӧ��
   */
  ManualET("nc.impl.so.m33.m4c.SaleOutManualEstimateAction"),

  /**
   *  ���۳��ⵥ�ֹ�������Ʒ
   */
  ManualREG("nc.impl.so.m33.m4c.SaleOutManualRegsiterAction"),

  /**
   * ���۳��ⵥ�ֹ�����
   */
  ManualSquare("nc.impl.so.m33.m4c.SaleOutSettleMaintainImpl"),

  /**
   * ��ȷ��Ӧ��
   */
  SquareARIncomeFor4C("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARIncomeFor4CBP"),
  
  /**
   * ��ȷ��Ӧ����ϸ
   */
  SquareARIncomeFor4CDetail("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARIncomeFor4CBP.saveDetail"),
  
  /**
   * ���۳��⴫�س�Ӧ��
   */
  SquareARRushIncomeFor4C("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CBP"),
  
  /**
   * ���۳��⴫�س�Ӧ��
   */
  SquareARRushIncomeFor4CDetail("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CBP.saveDetail"),
  
  /**
   * ���۳���Գ崫�س�Ӧ��
   */
  SquareARRushIncomeFor4CRushBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CRushBP"),
  
  /**
   * ���۳���Գ崫�س�Ӧ��
   */
  SquareARRushIncomeFor4CDetailRushBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CRushBP.saveDetail"),
  
  /**
   * ����ǩ�տ�Ʊ�˻صĳ��ⵥ���س�Ӧ��
   */
  SquareARRushIncomeFor4CSBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP"),
  
  /**
   * ����ǩ�տ�Ʊ�˻صĳ��ⵥ���س�Ӧ��
   */
  SquareARRushIncomeFor4CSDetailSBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP.saveDetail"),
  
  
  
  /**
   * ;������㵥��ȷ��Ӧ��
   */
  SquareARIncomeFor4453("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP"),
  
  /**
   * ;������㵥��ϸ��ȷ��Ӧ��
   */
  SquareARIncomeFor4453Detail("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP.saveDetail"),

  /**
   * ;������㵥���س�Ӧ��
   */
  SquareARRushIncomeFor4453("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP"),
  
  /**
   * ;������㵥��ϸ���س�Ӧ��
   */
  SquareARRushIncomeFor4453Detail("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP.saveDetail"),


  /**
   * ���س�Ӧ��
   */
  SquareRushARIncome("nc.bs.so.m33.biz.m32.bp.square.toar.SquareARRushIncomeFor32BP"),
  
  /**
   * ����Ӧ�ս��㵥
   */
  SquareARIncome("nc.bs.so.m33.biz.bp.toar.SquareARIncomeBP"),
  
  /**
   * ����Ӧ�ս��㵥
   */
  SquareARIncomeDetail("nc.bs.so.m33.biz.bp.toar.SquareARIncomeBP.saveDetail"),
  
  /**
   * ����س�Ӧ�ս��㵥
   */
  SquareRushARIncomeDetail("nc.bs.so.m33.biz.m32.bp.square.toar.SquareARRushIncomeFor32BP.saveDetail"),
  
  /**
   * �ݹ�Ӧ����ϸ����
   */
  SquareETIncome("nc.bs.so.m33.biz.bp.toar.SquareETIncomeBP"),
  
  /**
   * �ݹ�Ӧ����ϸ����
   */
  SquareETIncomeDetail("nc.bs.so.m33.biz.bp.toar.SquareETIncomeBP.saveDetail"),

  /**
   * ����Գ�
   */
  SquareOutRushByViewVO("nc.bs.so.m33.biz.m4c.bp.outrush.OutRushBP.byViewVO"),
  
  /**
   * ����Գ�
   */
  SquareOutRushByDetailVO("nc.bs.so.m33.biz.m4c.bp.outrush.OutRushBP.byDetailVO"),

  /**
   * ���۷�Ʊ���ɱ�
   */
  SquareToIABy32("nc.bs.so.m33.biz.bp.toia.SquareIACostFor32BP"),
  
  /**
   * ���۷�Ʊ��������Ʒ
   */
  SquareToIARegisterCreditBy32("nc.bs.so.m33.biz.m32.bp.square.toia.SquareIARegisterCreditFor32BP"),

  /**
   * ���۷�Ʊ���ɱ� ��ϸ����
   */
  SquareToIABy32Detail(
      "nc.bs.so.m33.biz.bp.toia.SquareIACostFor32BP.saveDetail"),
      
  /**
   * ���۷�Ʊ��������Ʒ��ϸ����
   */
  SquareToIARegisterCreditBy32Detail(
      "nc.bs.so.m33.biz.m32.bp.square.toia.SquareIARegisterCreditFor32BP.saveDetail"),
      
  /**
   * ���۳��⴫������Ʒ��ϸ����
   */
  SquareToIARegisterDebitBy4C(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterDebitFor4CBP"),
      
  /**
   * ���۷�Ʊ��������Ʒ��ϸ����
   */
  SquareToIARegisterDebitBy4CDetail(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterDebitFor4CBP.saveDetail"),

  /**
   * ���۳��⴫������Ʒ��ϸ����
   */
  SquareToIARegisterCreditBy4CDetail(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.AbstractSquareIARegisterCreditFor4CBP.saveDetail"),
      
  /**
   * ���۳��⴫������Ʒ����
   */
  SquareToIARegisterCreditBy4C(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.AbstractSquareIARegisterCreditFor4CBP"),
      
  /**
   * ;������㵥��������Ʒ����
   */
  SquareToRegisterBy4453(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP"),

  /**
   * ;������㵥��ϸ��������Ʒ����
   */
  SquareToRegisterBy4453Detail(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP.saveDetail"),
      
  /**
   * ;������㵥���ɱ�
   */
  SquareToIABy4453(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP"),

  /**
   * ;������㵥���ɱ�
   */
  SquareToIABy4453Detail(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP.saveDetail"),


  /**
   * ���۳��ⵥ���ɱ�
   */
  SquareToIABy4C("nc.bs.so.m33.biz.bp.toia.SquareIACostFor4CBP"),
  
  /**
   * ���۳��ⵥ��ϸ���ɱ�
   */
  SquareToIABy4CDetail("nc.bs.so.m33.biz.bp.toia.SquareIACostFor4CBP.saveDetail");

  // �����
  private String point;

  private BPPlugInPoint(String point2) {
    this.point = point2;
  }

  @Override
  public String getComponent() {
    return "m33";
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
