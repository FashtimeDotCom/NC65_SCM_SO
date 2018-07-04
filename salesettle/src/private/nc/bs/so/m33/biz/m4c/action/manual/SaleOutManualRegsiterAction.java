package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.CancelSquareWasDetailPubFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP;
import nc.bs.so.m33.biz.m4c.action.ia.IARegisterFor4CAction;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.manual.CancelManualREGFor4CPubBP;
import nc.bs.so.m33.biz.m4c.rule.toia.CheckForCancelREG4CRule;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.ic.m4453.ICM4453ServiceUtil;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;
import nc.pubitf.so.m33.self.pub.ISquare4353Query;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class SaleOutManualRegsiterAction {

  public void manualRegister(SquareOutViewVO[] vos) {

    SquareOutVO[] svos = SquareOutVOUtils.getInstance().combineBill(vos);

    // 1.��������
    SquareOutVOUtils.getInstance().fillDataForManualSquare(vos);

    // 2.������Ʒ
    new IARegisterFor4CAction().execCost(svos);

    // ���۳��ⵥ����id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 3.����;��
    this.processWastageForManualRegister(outBids, svos);

    // 4.�������ǩ�տ�Ʊ���˻صĳ��ⵥ
    this.processReturnOutForManualRegister(outBids, svos);
  }

  public void manualUnRegister(SquareOutViewVO[] vos) {
    // ����ִ��ǰҵ�����
    AroundProcesser<SquareOutViewVO> processer =
        new AroundProcesser<SquareOutViewVO>(BPPlugInPoint.ManualREG);
    this.addManualREGBeforeRule(processer);
    processer.before(vos);

    // �����㵥
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(vos);

    // ���۳��ⵥ����id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 3.����;��
    this.processWastageForManualUnRegister(outBids);

    // 4.�������ǩ�տ�Ʊ���˻صĳ��ⵥ
    this.processReturnOutForManualUnRegister(outBids);

    // ���ⵥȡ��������Ʒ
    new CancelManualREGFor4CPubBP().unSquare(vos);
  }

  private void addManualREGBeforeRule(AroundProcesser<SquareOutViewVO> processer) {
    IRule<SquareOutViewVO> rule = new CheckForCancelREG4CRule();
    processer.addBeforeRule(rule);
  }

  /**
   * �ֹ�������Ʒ��ʱ�������ǩ�տ�Ʊ���˻صĳ��ⵥ����������Ʒ
   * 
   * @param outBids -- ��Դ���ⵥ����id
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 ����04:27:28
   */
  private void processReturnOutForManualRegister(String[] outBids,
      SquareOutVO[] svos) {
    // ��ѯ���λ���ǩ�տ�Ʊ���˻صĳ��ⵥ
    String[] retBackOutBids = null;

    try {
      retBackOutBids = ICM4CServiceUtil.queryRetBidsBySource(outBids);

      // û��ǩ�տ�Ʊ�˻صĳ�����㵥
      if (VOChecker.isEmpty(retBackOutBids)) {
        return;
      }

      // ;�𵥱���id����
      LockOperator lp = new LockOperator();
      lp.lock(
          retBackOutBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0090")/*�ֹ�������Ʒ��ʱ����ǩ�տ�Ʊ���˻صĳ��ⵥ������������*/);

      // ��ѯ��û�н����������Ʒ����ĳ�������㵥
      SquareOutViewVO[] soutvos =
          new Square434CQueryImpl()
              .querySquareOutViewVOByBIDForNoREGSquare(retBackOutBids);
      if (VOChecker.isEmpty(soutvos)) {
        return;
      }

      SquareOutViewVO[] swvos =
          SquareOutVOUtils.getInstance().filterSignReturnOut(soutvos);
      // û��ǩ�տ�Ʊ���˻صĳ��ⵥ
      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // ���¶�ȡ���۳�������㵥
      SquareOutVO[] newsvos = new QuerySquare4CVOBP().refushVO(svos);

      // ���ñ��ν�������
      this.setNthisREGNumUseMinNum(swvos, newsvos);

      // ����ǩ�տ�Ʊ���˻صĳ��ⵥ������Ʒ
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(swvos);
      new IARegisterFor4CAction().execCost(sqvos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �ֹ�ȡ��������Ʒ��ʱȡ���˻صĳ��ⵥ������Ʒ
   * 
   * @param outBids -- ���γ��ⵥBids
   */
  private void processReturnOutForManualUnRegister(String[] outBids) {
    // ��ѯ���λ���ǩ�տ�Ʊ���˻صĳ��ⵥ
    String[] retBackOutBids = null;

    try {
      retBackOutBids = ICM4CServiceUtil.queryRetBidsBySource(outBids);

      // û��ǩ�տ�Ʊ�˻صĳ�����㵥
      if (VOChecker.isEmpty(retBackOutBids)) {
        return;
      }

      // �˻س��ⵥ����id����
      LockOperator lp = new LockOperator();
      lp.lock(
          retBackOutBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0091")/*�ֹ�ȡ��������Ʒ��ʱ����ǩ�տ�Ʊ���˻صĳ��ⵥ������������*/);

      // ��ѯ����������Ʒ�ĳ�����㵥����������㵥
      SquareOutDetailVO[] sdvos =
          new Square434CQueryImpl()
              .queryREGCostSquareOutDetailVOBy4CBID(retBackOutBids);
      // û������������Ʒ
      if (VOChecker.isEmpty(sdvos)) {
        return;
      }

      SquareOutViewVO[] svvos =
          new QuerySquare4CVOBP().querySquareOutViewVOByBID(SoVoTools
              .getVOsOnlyValues(sdvos, SquareOutDetailVO.CSALESQUAREBID));

      // ȡ��������ϸ
      new CancelSquareFor4CPubBP().cancelSquare(sdvos, SquareOutVOUtils
          .getInstance().combineBill(svvos));

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �ֹ�������Ʒ��ʱ����;�𣬴�������Ʒ
   * <p>
   * 
   * @param outBids -- ��Դ���ⵥ����id
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 ����04:27:28
   */
  private void processWastageForManualRegister(String[] outBids,
      SquareOutVO[] svos) {
    String[] wasBids;
    try {
      wasBids = ICM4453ServiceUtil.queryWastageBidsBySource(outBids);

      // û��;�𵥽��㵥
      if (VOChecker.isEmpty(wasBids)) {
        return;
      }

      // ;�𵥱���id����
      LockOperator lp = new LockOperator();
      lp.lock(
          wasBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0092")/*�ֹ�������Ʒ��ʱ����;������������*/);

      // ��ѯ��û�н������;������㵥
      ISquare4353Query qry53 =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasViewVO[] swvos =
          qry53.querySquareWasViewVOByBIDForNoREGSquare(wasBids);

      // û��;������㵥
      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // ���¶�ȡ���۳�������㵥
      SquareOutVO[] newsvos = new QuerySquare4CVOBP().refushVO(svos);

      // ���ñ��ν�������
      this.setNthisREGNumUseMinNum(swvos, newsvos);

      // ;�𵥷�����Ʒ
      new SquareIARegisterFor4453BP().square(swvos);

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �ֹ�ȡ��������Ʒ��ʱȡ��;�𷢳���Ʒ
   * 
   * @param outBids -- ���γ��ⵥBids
   */
  private void processWastageForManualUnRegister(String[] outBids) {
    // ��ѯ����;��
    String[] wasBids = null;

    try {
      wasBids = ICM4453ServiceUtil.queryWastageBidsBySource(outBids);

      // û��;�𵥽��㵥
      if (VOChecker.isEmpty(wasBids)) {
        return;
      }

      // ;�𵥱���id����
      LockOperator lp = new LockOperator();
      lp.lock(
          wasBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0093")/*�ֹ�ȡ��������Ʒ��ʱ����;�𵥷�����������*/);

      // ��ѯ����������Ʒ�����;����㵥��;������㵥
      ISquare4353Query iwasSvr =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasDetailVO[] swdvos =
          iwasSvr.querySquareWasDetailVOByBIDForREGSquare(wasBids);
      // û������������Ʒ
      if (VOChecker.isEmpty(swdvos)) {
        return;
      }

      SquareWasViewVO[] swbvos =
          iwasSvr.querySquareWasViewVOByBID(SoVoTools.getVOsOnlyValues(swdvos,
              SquareWasDetailVO.CSALESQUAREBID));

      // ;����㵥ȡ������
      new CancelSquareWasDetailPubFor4453BP().cancelSquare(swdvos,
          SquareWasVOUtils.getInstance().combineBill(swbvos));

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void setNthisREGNumUseMinNum(SquareOutViewVO[] swvos,
      SquareOutVO[] svos) {
    Map<String, SquareOutBVO> map = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCsquarebillbid(), bvo);
      }
    }
    for (SquareOutViewVO view : swvos) {
      SquareOutBVO bvo = map.get(view.getItem().getCsrcbid());
      UFDouble nregnum = bvo.getNsquareregnum();
      SquareOutVOUtils.getInstance().setNthisREGNumUseMinNum(view, nregnum);
    }
  }

  private void setNthisREGNumUseMinNum(SquareWasViewVO[] swvos,
      SquareOutVO[] svos) {
    Map<String, SquareOutBVO> map = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCsquarebillbid(), bvo);
      }
    }
    for (SquareWasViewVO view : swvos) {
      SquareOutBVO bvo = map.get(view.getItem().getCsrcbid());
      UFDouble nregnum = bvo.getNsquareregnum();
      SquareWasVOUtils.getInstance().setNthisREGNumUseMinNum(view, nregnum);
    }
  }

}
