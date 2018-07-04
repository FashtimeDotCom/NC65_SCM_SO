package nc.bs.so.m30.plugin;

import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ���۶����ĺ�̨����Ķ��ο�������㶨��
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public enum ServicePlugInPoint implements IPluginPoint {
  /**
   * ���۽����д���۶���ȷ��Ӧ������
   */
  rewrite30ARFor33(
      "nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite30ARFor33"),

  /**
   * �빺����д���۶��������빺������
   */
  rewrite30ArrangeNumFor20(
      "nc.pubitf.so.m30arrange.po.m20.IRewrite30For20.rewrite30ArrangeNumFor20"),

  /**
   * �ɹ�������д���۶������Ųɹ���������
   */
  rewrite30ArrangeNumFor21(
      "nc.pubitf.so.m30arrange.po.m21.IRewrite30For21.rewrite30ArrangeNumFor21"),

  /**
   * ���������д���۶������ŵ�����������
   */
  rewrite30ArrangeNumFor5A(
      "nc.pubitf.so.m30arrange.to.m5a.IRewrite30For5A.rewrite30ArrangeNumFor5A"),

  /**
   * ����������д���۶������ŵ�����������
   */
  rewrite30ArrangeNumFor5X(
      "nc.pubitf.so.m30arrange.to.m5x.IRewrite30For5X.rewrite30ArrangeNumFor5X"),

  /**
   * ί�ⶩ����д���۶�������ί�ⶩ������
   */
  rewrite30ArrangeNumFor61(
      "nc.pubitf.so.m30arrange.sc.m61.IRewrite30For61.rewrite30ArrangeNumFor61"),

  /**
   * ����������д���۶�������������������
   */
  rewrite30ArrangeNumForA2(
      "nc.pubitf.so.m30arrange.mo.ma2.IRewrite30ForA2.rewrite30ArrangeNumForA2"),
      
  /**
   * ���۽����д���۶����ۼƳ���Գ�����
   */
  rewrite30OutRushFor33(
      "nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite30OutRushFor33"),    

  /**
   * ���۽����д���۶����ݹ�Ӧ������
   */
  rewrite30ETFor33(
      "nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite30ETFor33"),

  /**
   * �ƻ�������д���۶����ۼ����ɼƻ���������
   */
  rewrite30npolnumFor55B4(
      "nc.pubitf.so.m30arrange.mmpps.plo.IRewrite30For55B4.rewrite30npolnumFor55B4"),

  /**
   * ���۷�Ʊ��д���۶�����Ʊ����
   */
  rewrite30NumFor32(
      "nc.pubitf.so.m30.so.m32.IRewriteSaleOrderBySaleInvoice.rewrite30NumFor32"),

  /**
   * ���۽����д���۶�������
   */
  rewrite30NumFor33("nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite"),

  /**
   * ;�𵥻�д���۶���ǩ��������;������
   */
  rewrite30NumFor4453(
      "nc.pubitf.so.m30.ic.m4453.IRewrite30For4453.rewrite30NumFor4453"),

  /**
   * ���۳��ⵥ��д���۶�����������
   */
  rewrite30NumFor4C(
      "nc.pubitf.so.m30.ic.m4c.IRewriteSaleOrderBySaleOut.rewrite30NumFor4C"),

  /**
   * �����˻�������д���۶����˻�����
   */
  rewrite30NumForWithdraw(
      "nc.pubitf.so.m30.so.withdraw.IRewriteSaleOrderByWithdraw."
          + "rewrite30NumForWithdraw"),

  /**
   * �տ������д���۶���ʵ���տ���
   */
  rewrite30ReceivedMnyForBalance(
      "nc.pubitf.so.m30.so.balance.IRewrite30ForBalance"
          + ".rewrite30ReceivedMnyForBalance"),

  /**
   * Ԥ����д���۶���Ԥ������
   */
  rewrite30ReqrsNumFor4480(
      "nc.pubitf.so.m30.ic.m4480.IRewrite30For4480.rewrite30ReqrsNumFor4480"),

  /**
   * ��������д���۶�����������
   */
  rewrite30SendNumFor4331(
      "nc.pubitf.so.m30.so.m4331.IRewrite30For4331.rewrite30SendNumFor4331"),
  /**
   * ���ۺ�ͬ��д���۶����ۼư��Ž��ں�ͬ����
   */
  rewrite30ArrangeItcNumFor5801(
      "nc.pubitf.so.m30.it.m5801.IRewrite30For5801.rewriteNarrangeItcNumFor5801"),

  /**
   * �տ������������ʱ��д���۶������۲���������
   */
  rewrite30TotalPayMnyForVerifyListener(
      "nc.pubitf.so.m30.so.balance.IRewrite30ForVerify"
          + ".rewrite30TotalPayMnyVerifyListener");

  // �����
  private String point;

  private ServicePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return SOComponent.Order.getComponent();
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
