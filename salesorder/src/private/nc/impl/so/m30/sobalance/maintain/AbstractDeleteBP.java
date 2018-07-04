package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * ɾ��BP
 * 
 * @author gdsjw
 */
public abstract class AbstractDeleteBP implements IDeleteBP {
  public AbstractDeleteBP() {
    //
  }

  @Override
  public SoBalanceVO[] delete(SoBalanceVO[] bills) {
    AroundProcesser<SoBalanceVO> processer =
        new AroundProcesser<SoBalanceVO>(BP30PlugInPoint.SOBalanceDeleteBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("����ɾ��ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<SoBalanceVO> bo = new BillDelete<SoBalanceVO>();
    bo.delete(bills);

    TimeLog.info("д���ݿ⣬ɾ������"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills);

    TimeLog.info("����ɾ����BP�����"); /*-=notranslate=-*/

    return bills;
  }

  protected abstract void addBeforeRule(AroundProcesser<SoBalanceVO> processer);

  protected abstract void addAfterRule(AroundProcesser<SoBalanceVO> processer);

}
