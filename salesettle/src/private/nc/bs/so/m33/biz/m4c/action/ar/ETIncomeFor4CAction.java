package nc.bs.so.m33.biz.m4c.action.ar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareETIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.rule.toar.FillNewChangeRateFor4CRule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class ETIncomeFor4CAction {
  /**
   * �ݹ�Ӧ��
   *
   * @param sqvos
   */
  public void execIncome(SquareOutVO[] svos) {
    // ����Ƿ���Դ�Ӧ��
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().filterIncomeableVO(svos);
    if (sqvos == null) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(ActionPlugInPoint.ETIncomeFor4C);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(sqvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "����ǰִ��ҵ�����"*/);

    // ���˻���ǩ�տ�Ʊ���˻س��ⵥ����ͨ���ⵥ
    List<SquareOutVO[]> retlist = this.filterSquareOutVO(sqvos);

    // ���س�Ӧ��--����ǩ�տ�Ʊ���˻س��ⵥ���ݹ�Ӧ��
    new SquareARRushIncomeFor4CSBP().square(retlist.get(1));

    // ���ݹ�Ӧ��
    new SquareETIncomeFor4CBP().square(retlist.get(0));

  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {

    // ���۳��ⵥӦ�ս���ǰ���ʴ���
    IRule<SquareOutVO> rule = new FillNewChangeRateFor4CRule();
    processer.addBeforeRule(rule);

  }

  /**
   * ���˻���ǩ�տ�Ʊ���˻س��ⵥ����ͨ���ⵥ
   *
   * @param vos
   * @return List<SquareOutVO[]>[0] -- �����ĳ�������㵥
   *         List<SquareOutVO[]>[1] -- ����ǩ�տ�Ʊ�ĳ�������㵥
   */
  private List<SquareOutVO[]> filterSquareOutVO(SquareOutVO[] vos) {
    List<SquareOutVO> lreturnsvo = new ArrayList<SquareOutVO>();
    List<SquareOutVO> lsvo = new ArrayList<SquareOutVO>();
    for (SquareOutVO svo : vos) {
      UFBoolean flag = svo.getParentVO().getBreturnoutstock();
      if (!VOChecker.isEmpty(flag) && flag.booleanValue()) {
        lreturnsvo.add(svo);
      }
      else {
        lsvo.add(svo);
      }
    }
    SquareOutVO[] retRushVos = null;
    if (lreturnsvo.size() > 0) {
      retRushVos = lreturnsvo.toArray(new SquareOutVO[lreturnsvo.size()]);

      // ��ѯ���γ��ⵥ�ݹ�Ӧ�ռ�¼
      String[] outBids =
          AggVOUtil.getDistinctItemFieldArray(vos, SquareOutBVO.CSRCBID,
              String.class);
      Map<String, SquareOutDetailVO> outmap =
          new Square434CQueryImpl().queryETIncomeDvosBy4CBID(outBids);
      for (SquareOutVO svo : retRushVos) {
        for (SquareOutBVO bvo : svo.getChildrenVO()) {
          String srcbid = bvo.getCsrcbid();
          SquareOutDetailVO dvo = outmap.get(srcbid);
          // ���γ��ⵥ�����س�Ӧ��
          if (dvo != null) {
            // �ûس����������κ��ݴ����γ�����㵥id,Ϊ�Ժ󴫻س�Ӧ��ʹ��
            bvo.setProcesseid(dvo.getCsalesquaredid());
          }
        }
      }
    }

    SquareOutVO[] sVos = null;
    if (lsvo.size() > 0) {
      sVos = lsvo.toArray(new SquareOutVO[lsvo.size()]);
    }
    List<SquareOutVO[]> retlist = new ArrayList<SquareOutVO[]>();
    retlist.add(sVos);
    retlist.add(retRushVos);
    return retlist;
  }
}