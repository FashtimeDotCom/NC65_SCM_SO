package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.CancelSquareWasDetailPubFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP;
import nc.bs.so.m33.biz.m4c.action.ar.ARIncomeFor4CAction;
import nc.bs.so.m33.biz.m4c.action.ia.IACostFor4CAction;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.manual.CancelManualSquareFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.pub.ProcessWC;
import nc.bs.so.m33.biz.m4c.rule.manual.ProcessEditTaxNetPriceRule;
import nc.bs.so.m33.biz.m4c.rule.pub.PubCheckFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.ic.m4453.ICM4453ServiceUtil;
import nc.md.model.impl.MDEnum;
import nc.pubitf.so.m33.self.pub.ISquare4353Query;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class SaleOutManualSquareAction {

  public SquareOutViewVO[] manualSquare(SquareOutViewVO[] vos) {
    // 1.��������
    SquareOutVOUtils.getInstance().fillDataForManualSquare(vos);

    // ����ִ��ǰҵ�����
    AroundProcesser<SquareOutViewVO> processer =
        new AroundProcesser<SquareOutViewVO>(BPPlugInPoint.ManualSquare);
    this.addManualSquareBeforeRule(processer);
    processer.before(vos);

    // 2.�����������ݷ���
    Map<SquareType, List<SquareOutViewVO>> mlist =
        SquareOutVOUtils.getInstance().splitViewVOBySquareTypeForManual(vos);

    // 3.Ӧ�ս���
    this.manualSquareAR(mlist.get(SquareType.SQUARETYPE_AR));

    // 4.�ɱ�����
    this.manualSquareCost(mlist.get(SquareType.SQUARETYPE_IA));

    // ���۳��ⵥ����id
    String[] outBids = this.getOutBids(vos);

    // 5.����;��
    this.processWastageForManualSquare(outBids);

    // 6.���÷���ֵ
    return this.setReturnDataForManualSquare(vos);
  }

  /**
   * �ֹ�ȡ������
   * 
   * @param vos
   *          <p>��ȡ����������۳��������view������һһ��Ӧ��ϸ��¼��view
   *          <p>����Ҫ�ٴβ�ѯ��ϸ��¼�󣬲�����ϸdid����֯һһ��Ӧ��ϸ��¼��view
   * 
   */
  public void manualUnSquare(SquareOutViewVO[] vos) {

    // ���۳��ⵥ����id
    String[] outBids = this.getOutBids(vos);

    // 1.����;��
    this.processWastageForManualUnSquare(outBids);

    // 2.���ⵥȡ������
    new CancelManualSquareFor4CPubBP().unSquare(vos);
  }

  private void addManualSquareBeforeRule(
      AroundProcesser<SquareOutViewVO> processer) {
    IRule<SquareOutViewVO> rule = new PubCheckFor4CRule();
    processer.addBeforeRule(rule);

    // ���ǰ̨�����ֹ��޸Ĺ���˰���ۣ���Ҫͬ�����´����㵥
    rule = new ProcessEditTaxNetPriceRule();
    processer.addBeforeRule(rule);
  }

  /**
   * ���۳��ⵥ�ֹ�����󣬹��췵��ֵ
   * 
   * @param rethvo
   * @param retbvo
   * @param view -- �Ѿ��������vo
   * @return
   */
  private SquareOutViewVO buildReturnVO(SquareOutHVO rethvo,
      SquareOutBVO retbvo, SquareOutViewVO view) {
    boolean manualar = !view.getHead().getBautosquareincome().booleanValue();
    boolean manualia = !view.getHead().getBautosquarecost().booleanValue();
    SquareType iaKey =
        MDEnum.valueOf(SquareType.class, view.getItem().getFpreiatype());
    SquareType arKey =
        MDEnum.valueOf(SquareType.class, view.getItem().getFpreartype());

    // UFDouble arnum = null;
    // UFDouble ianum = null;
    UFDouble nsquaremanualnum = null;
    if (manualar
        && SquareType.SQUARETYPE_AR.getIntValue() == arKey.getIntValue()
        && manualia
        && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.getIntValue()) {
      if (MathTool.absCompareTo(view.getItem().getNsquarearnum(), view
          .getItem().getNsquareianum()) > 0) {
        nsquaremanualnum = MathTool.nvl(view.getItem().getNsquarearnum());
      }
      else {
        nsquaremanualnum = MathTool.nvl(view.getItem().getNsquareianum());
      }
    }
    else if (manualar
        && SquareType.SQUARETYPE_AR.getIntValue() == arKey.getIntValue()) {
      nsquaremanualnum = MathTool.nvl(view.getItem().getNsquarearnum());
    }
    else if (manualia
        && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.getIntValue()) {
      nsquaremanualnum = MathTool.nvl(view.getItem().getNsquareianum());
    }

    // UFDouble nsquaremanualnum = arnum;
    // if (MathTool.greaterThan(ianum, nsquaremanualnum)) {
    // nsquaremanualnum = ianum;
    // }

    SquareOutViewVO retview = null;
    if (MathTool.absCompareTo(view.getItem().getNnum(), nsquaremanualnum) > 0) {
      view.setNthisnumForManualSquare();
      rethvo.setCsalesquareid(view.getHead().getCsalesquareid());
      rethvo.setTs(view.getHead().getTs());
      retbvo.setCsalesquarebid(view.getItem().getCsalesquarebid());
      retbvo.setCsalesquareid(view.getHead().getCsalesquareid());
      retbvo.setTs(view.getItem().getTs());
      retbvo.setNsquarearnum(view.getItem().getNsquarearnum());
      retbvo.setNsquareianum(view.getItem().getNsquareianum());
      retbvo.setNtotalsquarenum(view.getItem().getNtotalsquarenum());
      // �����µĴ���������,�۸�,�Ա��������¼�����
      retbvo.setNthisnum(view.getItem().getNthisnum());
      retbvo.setNastnum(view.getItem().getNastnum());
      retbvo.setVchangerate(view.getItem().getVchangerate());
      retbvo.setCunitid(view.getItem().getCunitid());
      retbvo.setCastunitid(view.getItem().getCastunitid());
      retbvo.setNorigtaxnetprice(view.getItem().getNorigtaxnetprice());
      retbvo.setNorigtaxprice(view.getItem().getNorigtaxprice());
      retbvo.setNorignetprice(view.getItem().getNorignetprice());
      retbvo.setNorigprice(view.getItem().getNorigprice());
      retbvo.setNitemdiscountrate(view.getItem().getNitemdiscountrate());
      retbvo.setNexchangerate(view.getItem().getNexchangerate());
      retbvo.setNgroupexchgrate(view.getItem().getNgroupexchgrate());
      retbvo.setNglobalexchgrate(view.getItem().getNglobalexchgrate());
      retbvo.setCorigcurrencyid(view.getItem().getCorigcurrencyid());
      retbvo.setCcurrencyid(view.getItem().getCcurrencyid());
      retbvo.setPk_org(view.getItem().getPk_org());
      retbvo.setPk_group(view.getItem().getPk_group());
      rethvo.setPk_org(view.getHead().getPk_org());
      rethvo.setPk_group(view.getHead().getPk_group());

      retview = new SquareOutViewVO();
      retview.setHead(rethvo);
      retview.setItem(retbvo);
    }
    return retview;
  }

  private void countMny(SquareOutViewVO[] retsvos) {
    SquareOutBVO[] bvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(retsvos);
    new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
  }

  private String[] getOutBids(SquareOutViewVO[] vos) {
    Set<String> sbids = new HashSet<String>();
    for (SquareOutViewVO svo : vos) {
      String bid = svo.getItem().getCsquarebillbid();
      if (!sbids.contains(bid)) {
        sbids.add(bid);
      }
    }
    // ���۳��ⵥ����id
    String[] outBids = sbids.toArray(new String[sbids.size()]);
    return outBids;
  }

  /**
   * ��������������Ӧ�ս���
   */
  private void manualSquareAR(List<SquareOutViewVO> list) {
    if (list != null) {
      SquareOutVO[] vos =
          SquareOutVOUtils.getInstance().combineBill(
              list.toArray(new SquareOutViewVO[0]));
      new ARIncomeFor4CAction().execIncome(vos);

      // ��Ӧ�ս����did���
      for (SquareOutVO svo : vos) {
        for (SquareOutBVO bvo : svo.getChildrenVO()) {
          bvo.setCsalesquaredid(null);
        }
      }

    }
  }

  /**
   * ���������������ɱ�����
   */
  private void manualSquareCost(List<SquareOutViewVO> list) {
    if (list != null) {
      SquareOutVO[] vos =
          SquareOutVOUtils.getInstance().combineBill(
              list.toArray(new SquareOutViewVO[0]));
      new IACostFor4CAction().execCost(vos);
    }
  }

  private void processWastageForManualSquare(String[] outBids) {
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
              "04006010-0094")/*�ֹ������ʱ����;������������*/);

      // ��ѯ��û�н������;������㵥
      ISquare4353Query qry53 =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasViewVO[] swvos =
          qry53.querySquareWasDetailVOByBIDForNoSquare(wasBids);

      if (!VOChecker.isEmpty(swvos)) {
        // �����������ݷ���
        Map<SquareType, List<SquareWasViewVO>> mlist =
            SquareWasVOUtils.getInstance().splitViewVOBySquareType(swvos);

        // ;�𵥽���
        new SquareARIncomeFor4453BP().square(mlist
            .get(SquareType.SQUARETYPE_AR));
        new SquareIACostFor4453BP().square(mlist.get(SquareType.SQUARETYPE_IA));
      }

    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

  }

  /**
   * �ֹ�ȡ�������ʱȡ��;�𵥽���
   * 
   * @param outBids -- ���γ��ⵥBids
   */
  private void processWastageForManualUnSquare(String[] outBids) {

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
              "04006010-0095")/*�ֹ�ȡ�������ʱ����;�𵥷�����������*/);

      // ��ѯ����������Ʒ�����;����㵥��;������㵥
      ISquare4353Query iwasSvr =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasDetailVO[] swdvos =
          iwasSvr.querySquareWasDetailVOByBIDForSquare(wasBids);

      if (!VOChecker.isEmpty(swdvos)) {
        SquareWasViewVO[] swbvos =
            iwasSvr.querySquareWasViewVOByBID(SoVoTools.getVOsOnlyValues(
                swdvos, SquareWasDetailVO.CSALESQUAREBID));

        // ;����㵥ȡ������
        new CancelSquareWasDetailPubFor4453BP().cancelSquare(swdvos,
            SquareWasVOUtils.getInstance().combineBill(swbvos));
      }

    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

  }

  private SquareOutViewVO[] setReturnDataForManualSquare(SquareOutViewVO[] vos) {
    Map<String, SquareOutHVO> mHIDvo = new HashMap<String, SquareOutHVO>();
    List<SquareOutViewVO> retList = new ArrayList<SquareOutViewVO>();
    // ���´ӿ��в�ѯˢ��
    String[] bids =
        SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);
    SquareOutViewVO[] refreashsvos =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class).query(bids);

    for (SquareOutViewVO view : refreashsvos) {
      SquareOutHVO rethvo = mHIDvo.get(view.getHead().getCsalesquareid());
      if (VOChecker.isEmpty(rethvo)) {
        rethvo = new SquareOutHVO();
        mHIDvo.put(view.getHead().getCsalesquareid(), rethvo);
      }
      SquareOutBVO retbvo = new SquareOutBVO();
      // ��������TS,�ۼ�Ӧ�ա��ɱ�����
      SquareOutViewVO retview = this.buildReturnVO(rethvo, retbvo, view);
      if (!VOChecker.isEmpty(retview)) {
        retList.add(retview);
      }
    }
    SquareOutViewVO[] retsvos = null;
    if (retList.size() > 0) {
      retsvos = retList.toArray(new SquareOutViewVO[retList.size()]);
      // ���¼����������
      this.countMny(retsvos);
      // ����β��
      new ProcessWC().processMnyForWC(retsvos);
    }
    return retsvos;
  }

}
