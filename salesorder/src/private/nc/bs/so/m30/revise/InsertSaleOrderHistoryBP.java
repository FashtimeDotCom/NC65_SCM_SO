package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * ���۶����޶����롶���۶����޶���InsertBP
 *
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-8-10 ����11:29:37
 */
public class InsertSaleOrderHistoryBP {
  public SaleOrderHistoryVO[] insert(SaleOrderHistoryVO[] bills) {
    AroundProcesser<SaleOrderHistoryVO> processer =
        new AroundProcesser<SaleOrderHistoryVO>(BP30PlugInPoint.ReviseInsertBP);

    // ע���
    TimeLog.logStart();
    // this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0066")/*@res "������������ǰBP�����"*/);

    TimeLog.logStart();
    BillInsert<SaleOrderHistoryVO> bo = new BillInsert<SaleOrderHistoryVO>();
    SaleOrderHistoryVO[] vos = bo.insert(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0067")/*@res "���浥�ݵ����ݿ�"*/);

    // ע���
    TimeLog.logStart();
    // this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0068")/*@res "�������������BP�����"*/);

    return vos;
  }

}