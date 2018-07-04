package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.so.m32.maintain.InsertSaleInvoiceBP;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ��Ʊ����action
 * 
 * @since 6.0
 * @version 2011-10-27 ����12:40:51
 * @author ô��
 */
public class InsertSaleInvoiceAction {

  /**
   * ���۷�Ʊ��������
   * 
   * @param newvos ��Ʊvo
   * @return �����ķ�Ʊvo
   */
  public SaleInvoiceVO[] insert(SaleInvoiceVO[] newvos) {
    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.InsertAction);

    TimeLog.logStart();
    processer.before(newvos);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0047")/*@res "������������BPǰִ��ҵ�����"*/);

    TimeLog.logStart();
    InsertSaleInvoiceBP action = new InsertSaleInvoiceBP();
    SaleInvoiceVO[] vos = action.insert(newvos);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0048")/*@res "������������BP�����б���"*/);

    TimeLog.logStart();
    processer.after(vos);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0049")/*@res "������������BP��ִ��ҵ�����"*/);
    return vos;
  }
}
