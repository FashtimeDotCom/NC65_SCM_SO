/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * ��������BP
 * 
 * @author gdsjw
 */
public abstract class AbstractInsertBP implements IInsertBP {
  @Override
  public SoBalanceVO[] insert(SoBalanceVO[] bills) {
    AroundProcesser<SoBalanceVO> processer =
        new AroundProcesser<SoBalanceVO>(BP30PlugInPoint.SOBalanceInsertBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("������������ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<SoBalanceVO> bo = new BillInsert<SoBalanceVO>();
    SoBalanceVO[] vos = bo.insert(bills);

    TimeLog.info("���浥�ݵ����ݿ�"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info("�������������BP�����"); /*-=notranslate=-*/

    return vos;
  }

  protected abstract void addBeforeRule(AroundProcesser<SoBalanceVO> processer);

  protected abstract void addAfterRule(AroundProcesser<SoBalanceVO> processer);

}
