package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.CancelSquareWasDetailPubFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.manual.CancelManualETFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareETIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.rule.toar.CheckForCancelET4CRule;
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

public class SaleOutManualEstimateAction {

  public void manualEstimate(SquareOutViewVO[] vos) {

    SquareOutVO[] svos = SquareOutVOUtils.getInstance().combineBill(vos);

    // 1.��������
    SquareOutVOUtils.getInstance().fillDataForManualSquare(vos);

    // 2.�ݹ�Ӧ��
    new SquareETIncomeFor4CBP().square(svos);

    // ���۳��ⵥ����id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // ����ˢ�����۳�������㵥
    SquareOutVO[] newsvos = new QuerySquare4CVOBP().refushVO(svos);

    // 3.����;��
    this.processWastageForManualEstimate(outBids, newsvos);

    // 4.�������ǩ�տ�Ʊ���˻صĳ��ⵥ
    this.processReturnOutForManualEstimate(outBids, newsvos);

  }

  public void manualUnEstimate(SquareOutViewVO[] vos) {
    // ����ִ��ǰҵ�����
    AroundProcesser<SquareOutViewVO> processer =
        new AroundProcesser<SquareOutViewVO>(BPPlugInPoint.ManualET);
    this.addManualETBeforeRule(processer);
    processer.before(vos);

    // �����㵥
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(vos);

    // ���۳��ⵥ����id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 3.����;��
    this.processWastageForManualUnEstimate(outBids);

    // 4.�������ǩ�տ�Ʊ���˻صĳ��ⵥ
    this.processReturnOutForManualUnEstimate(outBids);

    // ���ⵥȡ���ݹ�Ӧ��
    new CancelManualETFor4CPubBP().unSquare(vos);
  }

  private void addManualETBeforeRule(AroundProcesser<SquareOutViewVO> processer) {
    IRule<SquareOutViewVO> rule = new CheckForCancelET4CRule();
    processer.addBeforeRule(rule);
  }

  /**
   * �ֹ��ݹ�Ӧ�յ�ʱ�������ǩ�տ�Ʊ���˻صĳ��ⵥ�����س�Ӧ��
   * 
   * @param outBids -- ��Դ���ⵥ����id
   * @param svos -- �˻صĳ����������۳��ⵥ�����㵥
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 ����04:27:28
   */
  private void processReturnOutForManualEstimate(String[] outBids,
      SquareOutVO[] svos) {
    // ��ѯ���λ���ǩ�տ�Ʊ���˻صĳ��ⵥ
    String[] retBackOutBids = null;

    try {
      retBackOutBids = ICM4CServiceUtil.queryRetBidsBySource(outBids);

      // û��ǩ�տ�Ʊ�˻صĳ�����㵥
      if (VOChecker.isEmpty(retBackOutBids)) {
        return;
      }

      // �˻صĳ��ⵥ����id����
      LockOperator lp = new LockOperator();
      lp.lock(
          retBackOutBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0086")/*�ֹ��ݹ�Ӧ�յ�ʱ����ǩ�տ�Ʊ���˻صĳ��ⵥ������������*/);

      // ��ѯ��û�д��س�Ӧ�յĳ�������㵥
      SquareOutViewVO[] soutvos =
          new Square434CQueryImpl()
              .querySquareOutViewVOByBIDForNoETRushSquare(retBackOutBids);
      if (VOChecker.isEmpty(soutvos)) {
        return;
      }

      SquareOutViewVO[] swvos =
          SquareOutVOUtils.getInstance().filterSignReturnOut(soutvos);
      // û��ǩ�տ�Ʊ���˻صĳ��ⵥ
      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // ���ñ��ν����������������۳��ⵥ������ϸid
      this.setDataForARRush(swvos, svos);

      // ����ǩ�տ�Ʊ���˻صĳ��ⵥ���س�Ӧ��
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(swvos);
      new SquareARRushIncomeFor4CSBP().square(sqvos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �ֹ�ȡ���ݹ�Ӧ�յ�ʱȡ���˻صĳ��ⵥ�ݹ�Ӧ��
   * 
   * @param outBids -- ���γ��ⵥBids
   */
  private void processReturnOutForManualUnEstimate(String[] outBids) {
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
              "04006010-0087")/*�ֹ�ȡ���ݹ�Ӧ�յ�ʱ����ǩ�տ�Ʊ���˻صĳ��ⵥ������������*/);

      // ��ѯ����������Ʒ�ĳ�����㵥����������㵥
      SquareOutDetailVO[] sdvos =
          new Square434CQueryImpl()
              .queryETIncomeSquareOutDetailVOBy4CBID(retBackOutBids);

      // û�������ݹ�Ӧ��
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
   * �ֹ��ݹ�Ӧ�յ�ʱ����;�𣬴��س�Ӧ��
   * 
   * @param outBids -- ��Դ���ⵥ����id
   * @param svos -- ;���������۳��ⵥ�����㵥
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 ����04:27:28
   */
  private void processWastageForManualEstimate(String[] outBids,
      SquareOutVO[] svos) {
    // ��ѯ���ε�;��
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
              "04006010-0088")/*�ֹ��ݹ�Ӧ�յ�ʱ����;������������*/);

      // ��ѯ��û�лس�Ӧ�ս������;������㵥
      ISquare4353Query qry53 =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasViewVO[] swvos =
          qry53.querySquareWasViewVOByBIDForNoETRushSquare(wasBids);

      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // ���ñ��ν����������������۳��ⵥ������ϸid
      this.setDataForARRush(swvos, svos);

      // ;�𵥴��س�Ӧ��
      new SquareARRushIncomeFor4453BP().square(swvos);

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }

  /**
   * �ֹ�ȡ���ݹ�Ӧ�յ�ʱȡ��;���ݹ�Ӧ��
   * 
   * @param outBids -- ���γ��ⵥBids
   */
  private void processWastageForManualUnEstimate(String[] outBids) {

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
              "04006010-0089")/*�ֹ�ȡ���ݹ�Ӧ�յ�ʱ����;�𵥷�����������*/);

      // ��ѯ�����ݹ�Ӧ�յ�;����㵥��;������㵥
      ISquare4353Query iwasSvr =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasDetailVO[] swdvos =
          iwasSvr.querySquareWasDetailVOByBIDForETRushSquare(wasBids);
      // û�������ݹ�Ӧ��
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

  /**
   * ���ñ��ν����������������۳��ⵥ���㵥id
   * ��ǩ�տ�Ʊ���˻صĳ��ⵥ�س�ӿ�ʹ�ã�
   * 
   * @param wasvos
   * @param svos
   */
  private void setDataForARRush(SquareOutViewVO[] outvos, SquareOutVO[] svos) {
    Map<String, SquareOutBVO> micbid = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        micbid.put(bvo.getCsquarebillbid(), bvo);
      }
    }

    // ���ñ��ν��������������ݹ����۳��ⵥ������ϸid��;��س�ӿ�ʹ�ã�
    for (SquareOutViewVO view : outvos) {
      String icbid = view.getItem().getCsrcbid();
      view.getItem().setProcesseid(micbid.get(icbid).getCsalesquaredid());
      SquareOutBVO bvo = micbid.get(icbid);
      UFDouble netnum = bvo.getNsquareestnum();
      SquareOutVOUtils.getInstance().setNthisETRushNumUseMinNum(view, netnum);

    }
  }

  /**
   * ���ñ��ν����������������۳��ⵥ���㵥id��;��س�ӿ�ʹ�ã�
   * 
   * @param wasvos
   * @param svos
   */
  private void setDataForARRush(SquareWasViewVO[] wasvos, SquareOutVO[] svos) {
    Map<String, SquareOutBVO> micbid = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        micbid.put(bvo.getCsquarebillbid(), bvo);
      }
    }

    // ���ñ��ν��������������ݹ����۳��ⵥ������ϸid��;��س�ӿ�ʹ�ã�
    for (SquareWasViewVO view : wasvos) {
      String icbid = view.getItem().getCsrcbid();
      view.getItem().setProcesseid(micbid.get(icbid).getCsalesquaredid());
      SquareOutBVO bvo = micbid.get(icbid);
      UFDouble netnum = bvo.getNsquareestnum();
      SquareWasVOUtils.getInstance().setNthisETRushNumUseMinNum(view, netnum);
    }
  }

}
