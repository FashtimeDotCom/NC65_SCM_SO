package nc.impl.so.m32.action;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.so.m32.maintain.DeleteSaleInvoiceBP;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ���۷�Ʊɾ��
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:02:40
 * @author yaogj
 */
public class DeleteSaleInvoiceAction {

  /**
   * 
   * @param bills Ҫɾ�������۷�Ʊvo
   */
  public void delete(SaleInvoiceVO[] bills) {

    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.DeleteAction);

    TimeLog.logStart();
    processer.before(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0044")/*@res "����ɾ��BPǰִ��ҵ�����"*/);

    TimeLog.logStart();
    DeleteSaleInvoiceBP action = new DeleteSaleInvoiceBP();
    action.delete(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0045")/*@res "����ɾ��BP������ɾ��"*/);

    TimeLog.logStart();
    processer.after(bills);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0046")/*@res "����ɾ��BP��ִ��ҵ�����"*/);

  }
}
