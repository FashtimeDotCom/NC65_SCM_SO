package nc.impl.so.m30.action.main;

import nc.bs.so.m30.maintain.UpdateSaleOrderBP;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.maintainprocess.FillupDataWhenUpdateRule;
import nc.bs.so.m30.rule.maintainprocess.NullRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateSaleOrderAction {

  public SaleOrderVO[] update(SaleOrderVO[] bills, SaleOrderVO[] originBills) {

    CompareAroundProcesser<SaleOrderVO> processer =
        new CompareAroundProcesser<SaleOrderVO>(
            Action30PlugInPoint.UpdateAction);
    // ����ҵ�����
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills, originBills);

    TimeLog.info("�����޸ı���ǰ���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    UpdateSaleOrderBP action = new UpdateSaleOrderBP();
    SaleOrderVO[] vos = action.update(bills, originBills);

    TimeLog.info("�����޸ı���BP�����б���"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos, originBills);

    TimeLog.info("�����޸ı������������"); /*-=notranslate=-*/
    return vos;
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
   * @time 2010-9-10 ����04:08:51
   */
  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> processer) {
    processer.addAfterRule(new NullRule<SaleOrderVO>());
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
   * @time 2010-9-10 ����04:08:54
   */
  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;

    rule = new FillupDataWhenUpdateRule();
    processer.addBeforeRule(rule);

  }

}
