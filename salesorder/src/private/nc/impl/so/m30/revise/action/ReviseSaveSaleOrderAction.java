package nc.impl.so.m30.revise.action;

import nc.bs.so.m30.maintain.util.VOCheckFor30R;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.revise.SaveReviseSaleOrderBP;
import nc.bs.so.m30.rule.feature.FeatureSelectSaveRule;
import nc.bs.so.m30.rule.maintaincheck.CheckNumPriceMnyRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.revise.action.rule.FillupDataRule;
import nc.impl.so.m30.revise.action.rule.OffsetCheckRule;
import nc.impl.so.m30.revise.action.rule.ReviseFeatureCheckRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * �����޶�����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>��SaleOrderVOת��ΪSaleOrderHistoryVO
 * <li>�޶�ǰ�汾VO���������۶�����ʷ��
 * <li>�޶������°汾VO���������۶�����
 * <li>...
 * </ul>
 * 
 * @version 6..0
 * @author ��־ΰ
 * @time 2010-8-11 ����01:57:10
 */
public class ReviseSaveSaleOrderAction {

  /* ��ʷ����Ԫ�������� */
  public static final String BODY_METANAME = "so.orderhistory_b";

  /* ��ʷ������Ԫ���� ���� */
  public static final String HEAD_METANAME = "so.orderhistory";

  /* ��ʷ���������� */
  public static final String TABLE_HISTORY = "so_orderhistory";

  /* ��ʷ�������� */
  public static final String TABLE_HISTORY_B = "so_orderhistory_b";

  public SaleOrderHistoryVO[] reviseSave(SaleOrderHistoryVO[] bills) throws BusinessException {
    TimeLog.logStart();
    BillTransferTool<SaleOrderHistoryVO> transferTool =
        new BillTransferTool<SaleOrderHistoryVO>(bills);
    SaleOrderHistoryVO[] fullbills = transferTool.getClientFullInfoBill();
    // add by zhangby5 ��ԭʼVO���������ԭʼVO����Ҫʹ�ø�ԭʼVO����Ҫ���²�ѯ
    TimeLog.info("��ȫǰ̨VO������޸�ǰVO"); /*-=notranslate=-*/
    // ���Ʋ��� modify by zhangby5
    VOCheckFor30R vocheck = new VOCheckFor30R();
    SaleOrderHistoryVO[] originBills = vocheck.voCheckAndQueryOriginForRevise(bills);
    CompareAroundProcesser<SaleOrderVO> compareProcesser =
        new CompareAroundProcesser<SaleOrderVO>(
            Action30PlugInPoint.ReviseSaveAction);
    // ����ִ��ǰҵ�����
    this.addBeforeRule(compareProcesser);

    TimeLog.logStart();
    compareProcesser.before(fullbills, originBills);
    TimeLog.info("�����޶�����ǰ���������"); /*-=notranslate=-*/

    SaveReviseSaleOrderBP updateBP = new SaveReviseSaleOrderBP();
    SaleOrderHistoryVO[] ret = updateBP.update(fullbills, originBills);
    TimeLog.logStart();

    this.addAfterRule(compareProcesser);
    compareProcesser.after(ret, fullbills);

    TimeLog.info("�����޶��������������"); /*-=notranslate=-*/

    TimeLog.logStart();

    ret = transferTool.getBillForToClient(ret);
    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    // TODO
    TimeLog.info("ҵ����־"); /*-=notranslate=-*/
  
    return ret;
  }
  private void addBeforeRule(
      CompareAroundProcesser<SaleOrderVO> compareProcesser) {
    IRule<SaleOrderVO> rule = new FillupDataRule();
    compareProcesser.addBeforeRule(rule);
    // �ۼƳ�ֽ��У��
    rule = new OffsetCheckRule();
    compareProcesser.addBeforeRule(rule);
    // У������ѡ��
    ICompareRule<SaleOrderVO> comparerule = new ReviseFeatureCheckRule();
    compareProcesser.addBeforeRule(comparerule);
    
    // ���۶���������ѡ�䱣��
    rule = new FeatureSelectSaveRule();
    compareProcesser.addBeforeRule(rule);
  }

  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> compareProcesser) {
    
    IRule<SaleOrderVO> rule = null;
    // �������۽�������
    rule = new CheckNumPriceMnyRule();
    compareProcesser.addAfterRule(rule);
  }
}
