package nc.impl.so.m30.action.main;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.bs.so.m30.maintain.DeleteSaleOrderBP;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.maintainprocess.NullRule;

import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ɾ������
 * 
 * @author gdsjw
 */
public class DeleteSaleOrderAction {

  /**
   * ���۶���ɾ��
   * 
   * @param bills
   * @return ����
   */
  public SaleOrderVO[] delete(SaleOrderVO[] bills) {

    SaleOrderVO[] delbills = bills;

    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(Action30PlugInPoint.DeleteAction);
    // ����ҵ�����
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(delbills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0165")/*@res "����ɾ��ǰ���������"*/); /* -=notranslate=- */

    TimeLog.logStart();
    DeleteSaleOrderBP action = new DeleteSaleOrderBP();
    action.delete(delbills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0166")/*@res "����ɾ��BP������ɾ��"*/); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(delbills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0167")/*@res "����ɾ������������"*/); /* -=notranslate=- */

    return delbills;

  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author zhangcheng
   * @time 2010-9-10 ����04:08:24
   */
  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    processer.addBeforeRule(new NullRule<SaleOrderVO>());
  }
}
