package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.so.m32.maintain.UpdateSaleInvoiceBP;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;

/**
 * ���۷�Ʊ�޸ı������ʵ��
 * 
 * @since 6.0
 * @version 2010-1-22 ����12:43:19
 * @author ��ӱ�
 */
public class UpdateSaleInvoiceAction {

  /**
   * 
   * @param fullbills ǰ̨��������ȫ���vo
   * @param originBills ԭʼvo
   * @return ���º��vo
   */
  public SaleInvoiceVO[] update(SaleInvoiceVO[] fullbills,
      SaleInvoiceVO[] originBills) {

    CompareAroundProcesser<SaleInvoiceVO> compareProcesser =
        new CompareAroundProcesser<SaleInvoiceVO>(
            Action32PlugInPoint.UpdateAction);

    TimeLog.logStart();

    compareProcesser.before(fullbills, originBills);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0052")/*@res "���ø��±���BPǰִ��ҵ�����"*/);

    UpdateSaleInvoiceBP action = new UpdateSaleInvoiceBP();
    SaleInvoiceVO[] ret = action.update(fullbills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0053")/*@res "���ø��±���BP��ִ��ҵ�����"*/);

    TimeLog.logStart();
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0042")/*@res "ҵ����־"*/);

    return ret;

  }

}
