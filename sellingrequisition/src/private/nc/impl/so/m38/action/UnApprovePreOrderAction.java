package nc.impl.so.m38.action;

import nc.bs.pub.action.N_38_UNAPPROVE;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m38.action.rule.unapprove.UnApproveBillBeforeRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

public class UnApprovePreOrderAction {
  /**
   * ��������������Ԥ�����������ʵ�֡�
   * <p>
   * <b>����˵��</b>
   *
   * @param script
   * @return <p>
   * @author ��־ΰ
   * @time 2010-04-06 ����10:38:45
   */
  public PreOrderVO[] unApprove(N_38_UNAPPROVE script) {
    PreOrderVO[] retvos = null;
    try {
      Object[] inCurObjects = script.getPfParameterVO().m_preValueVos;
      if ((inCurObjects == null) || (inCurObjects.length == 0)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0028")/*@res "������ϣ�������Ԥ����û������"*/);
      }
      if (!(inCurObjects instanceof PreOrderVO[])) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0029")/*@res "������ϣ�������Ԥ�������Ͳ�ƥ��"*/);
      }
      if (inCurObjects != null) {
        PreOrderVO[] inCurVOs = new PreOrderVO[inCurObjects.length];
        for (int i = 0; i < inCurObjects.length; i++) {
          inCurVOs[i] = (PreOrderVO) inCurObjects[i];
        }
        TimeLog.logStart();
        BillTransferTool<PreOrderVO> transferTool =
            new BillTransferTool<PreOrderVO>(inCurVOs);
        inCurVOs = transferTool.getClientFullInfoBill();
        TimeLog.info("��ȫǰ̨VO�����������ʱ���"); /*-=notranslate=-*/

        AroundProcesser<PreOrderVO> processer =
            new AroundProcesser<PreOrderVO>(ActionPlugInPoint.UnApproveAction);

        this.addRule(processer);

        TimeLog.logStart();
        processer.before(inCurVOs);
        TimeLog.info("����������ǰִ��ҵ�����"); /*-=notranslate=-*/

        TimeLog.logStart();
        script.procUnApproveFlow(script.getPfParameterVO());
        TimeLog.info("�����������������˴�����������޸�"); /*-=notranslate=-*/

        TimeLog.logStart();
        processer.after(inCurVOs);
        TimeLog.info("������������ִ��ҵ�����"); /*-=notranslate=-*/

        TimeLog.logStart();
        retvos = this.queryNewVO(inCurVOs);
        retvos = transferTool.getBillForToClient(retvos);
        TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retvos;
  }

  /**
   * ������������������������
   * <p>
   * <b>����˵��</b>
   *
   * @param processer
   *          <p>
   * @author ��־ΰ
   * @time 2010-04-06 ����10:38:45
   */
  private void addRule(AroundProcesser<PreOrderVO> processer) {

    // ���Ԥ������ǰ״̬�Ƿ������
    IRule<PreOrderVO> rule = new UnApproveBillBeforeRule();
    processer.addBeforeRule(rule);

  }

  /**
   * ����������������ѯ���������Ԥ����VO��
   * <p>
   * <b>����˵��</b>
   *
   * @param bills
   * @return <p>
   * @author ��־ΰ
   * @time 2010-04-06 ����10:38:45
   */
  private PreOrderVO[] queryNewVO(PreOrderVO[] bills) {
    String[] ids = new String[bills.length];
    for (int i = 0; i < bills.length; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<PreOrderVO> query = new BillQuery<PreOrderVO>(PreOrderVO.class);
    return query.query(ids);

  }
}