package nc.bs.so.m33.biz.m32.action.ar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.biz.m32.bp.square.toar.SquareARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.square.toar.SquareARRushIncomeFor32BP;
import nc.bs.so.m33.biz.m32.rule.toar.FillNewChangeRateFor32Rule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class ARIncomeFor32Action {

  /**
   * Ӧ�ս��㣨������ⵥ�����ݹ�Ӧ�գ���Ҫ�ȴ��س�Ӧ�գ�
   * 
   * @param vos
   */
  public void execIncome(SquareInvVO[] vos) {

    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(ActionPlugInPoint.ARIncomeFor32);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0011")/*@res "����ǰִ��ҵ�����"*/);

    // ��ȡ�س�Ӧ������
    SquareInvVO[] et_svos = this.filterSquareInvVO(vos);

    // ���س�Ӧ�գ�����ȡ���������ȷ��Ӧ�գ�
    this.processARRushData(et_svos);

    // ���γ��ⵥ�����ݹ�Ӧ�գ���Ʊ���س�Ӧ��
    new SquareARRushIncomeFor32BP().square(et_svos);

    // ��ȷ��Ӧ�մ���
    new SquareARIncomeFor32BP().square(vos);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // ���۷�ƱӦ�ս���ǰ���ʴ���
    IRule<SquareInvVO> rule = new FillNewChangeRateFor32Rule();
    processer.addBeforeRule(rule);
  }

  /**
   * �������γ��ⵥ���Ƿ�������ݹ�Ӧ�ս�������VO�ֳ��س�Ӧ������
   * 
   * @param vos
   * @return SquareInvVO[] -- �س�Ӧ��vo
   * 
   */
  private SquareInvVO[] filterSquareInvVO(SquareInvVO[] vos) {

    // �س�Ӧ������
    SquareInvVO[] etvos = null;

    // ���۳�������ѯ�ӿ�
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);

    // ��ѯ���γ��ⵥ�ݹ�Ӧ�ռ�¼
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    Map<String, SquareOutDetailVO> outmap =
        square4cQry.queryETIncomeDvosBy4CBID(outBids);

    if (outmap.size() > 0) {
      // �س�Ӧ��
      List<SquareInvViewVO> l_viewvo_et = new ArrayList<SquareInvViewVO>();
      SquareInvViewVO[] sviewvos =
          SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
      for (SquareInvViewVO svo : sviewvos) {
        String srcbid = svo.getItem().getCsrcbid();
        SquareOutDetailVO dvo = outmap.get(srcbid);
        // ���γ��ⵥ�����س�Ӧ��
        if (dvo != null) {
          SquareInvViewVO rushsvo = (SquareInvViewVO) svo.clone();
          // �ûس����������κ��ݴ����γ�����㵥id,Ϊ�Ժ󴫻س�Ӧ��ʹ��
          rushsvo.getItem().setProcesseid(dvo.getCsalesquaredid());
          l_viewvo_et.add(rushsvo);
        }

      }
      etvos =
          SquareInvVOUtils.getInstance().combineBill(
              l_viewvo_et.toArray(new SquareInvViewVO[0]));
    }

    return etvos;
  }

  /**
   * ���س�Ӧ�գ�����ȡ���������ȷ��Ӧ�գ�
   * 
   * @param et_svos
   */
  private void processARRushData(SquareInvVO[] et_svos) {
    if (VOChecker.isEmpty(et_svos)) {
      return;
    }
    for (SquareInvVO svo : et_svos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        bvo.setNthisnum(MathTool.oppose(bvo.getNthisnum()));
      }
    }
  }

}
