package nc.bs.so.m33.biz.m4c.bp.outrush;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CRushBP;
import nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterCreditFor4COutRushBP;
import nc.bs.so.m33.biz.m4c.rule.outrush.CheckOutRush4CRule;
import nc.bs.so.m33.biz.m4c.rule.outrush.CountOutRush4CNumRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteOutRush4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.util.CirVOUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * @since 6.0
 * @version 2012-8-15 ����05:11:03
 * @author ô��
 */
public class OutRushFor4CBP {

  /**
   * ���۳��ⵥ����Գ�
   * 
   * @param sqvos
   */
  public UFDouble square(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {

    CompareAroundProcesser<SquareOutViewVO> processer =
        new CompareAroundProcesser<SquareOutViewVO>(BPPlugInPoint.SquareOutRushByViewVO);

    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bluevos, redvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0020")/* @res "����Գ�ǰִ��ҵ�����-ҵ�������" */);

    // ��������Գ����VOת��Ϊ����Գ������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForOUTRUSH(bluevos,
            redvos);

    // �ϲ�vo
    List<SquareOutViewVO> lview = CirVOUtil.combineBill(bluevos, redvos);
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            lview.toArray(new SquareOutViewVO[0]));

    // ����Գ���ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // ����Գ�
    this.processARRush(bluevos, redvos);
    // ��������Ʒ
    this.processREGCost(bluevos, redvos);

    TimeLog.logStart();
    AroundProcesser<SquareOutDetailVO> afprocesser =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareOutRushByDetailVO);
    this.addAfterRule(afprocesser);
    afprocesser.after(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0021")/* @res "����Գ��ִ��ҵ�����-��д���۳��ⵥ�����㵥" */);

    return bluevos[0].getItem().getNrushnum();

  }

  private void processREGCost(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // ��������Ʒ
    SquareOutViewVO[] regbluevos =
        SquareOutVOUtils.getInstance().deepClone(bluevos);
    SquareOutViewVO[] regredvos =
        SquareOutVOUtils.getInstance().deepClone(redvos);
    // ��նԳ������ϸid
    this.setDIDNull(regbluevos);
    this.setDIDNull(regredvos);
    this.processREGCostDetail(regbluevos, regredvos);
  }

  private void processREGCostDetail(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // ���˺���
    List<SquareOutViewVO> listred = new ArrayList<SquareOutViewVO>();
    this.addREGCostVO(redvos, listred);

    // ��������
    List<SquareOutViewVO> listblue = new ArrayList<SquareOutViewVO>();
    this.addREGCostVO(bluevos, listblue);

    SquareOutViewVO[] blueview = null;
    SquareOutViewVO[] redview = null;
    if (listblue.size() > 0) {
      blueview = listblue.toArray(new SquareOutViewVO[listblue.size()]);
    }
    if (listred.size() > 0) {
      redview = listred.toArray(new SquareOutViewVO[listred.size()]);
    }

    // ���ֺ���ͬʱ��������Ʒ
    if (listred.size() > 0 && listblue.size() > 0) {
      // �����������ֳ���vo��Ҫ�����ֱ��������ͺ��ֱ���������ȣ�����=��Ӧ���ֳ��ⵥ������
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redview);
      // �ϲ�vo
      List<SquareOutViewVO> lview = CirVOUtil.combineBill(newbluevos, redview);
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(
              lview.toArray(new SquareOutViewVO[0]));
      new SquareIARegisterCreditFor4COutRushBP().square(sqvos);
    }
    // ���ִ�������Ʒ
    else if (listblue.size() > 0) {
      // �����������ֳ���vo��Ҫ�����ֱ��������ͺ��ֱ���������ȣ�����=��Ӧ���ֳ��ⵥ������
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redvos);
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(newbluevos);
      new SquareIARegisterCreditFor4COutRushBP().square(sqvos);
    }
    // ���ִ�������Ʒ
    else if (listred.size() > 0) {
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(redview);
      new SquareIARegisterCreditFor4COutRushBP().square(sqvos);
    }
  }

  private void processARRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    // ����س�
    SquareOutViewVO[] rushbluevos =
        SquareOutVOUtils.getInstance().deepClone(bluevos);
    SquareOutViewVO[] rushredvos =
        SquareOutVOUtils.getInstance().deepClone(redvos);
    // ��նԳ������ϸid
    this.setDIDNull(rushbluevos);
    this.setDIDNull(rushredvos);
    this.processARRushDetail(rushbluevos, rushredvos);
  }

  private void processARRushDetail(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // ���˺���
    List<SquareOutViewVO> listred = new ArrayList<SquareOutViewVO>();
    this.addARRoushVO(redvos, listred);

    // ��������
    List<SquareOutViewVO> listblue = new ArrayList<SquareOutViewVO>();
    this.addARRoushVO(bluevos, listblue);

    SquareOutViewVO[] blueview = null;
    SquareOutViewVO[] redview = null;
    if (listblue.size() > 0) {
      blueview = listblue.toArray(new SquareOutViewVO[listblue.size()]);
    }
    if (listred.size() > 0) {
      redview = listred.toArray(new SquareOutViewVO[listred.size()]);
    }

    // ���ֺ���ͬʱ�س�Ӧ��
    if (!VOChecker.isEmpty(blueview) && !VOChecker.isEmpty(redview)) {
      // �����������ֳ���vo��Ҫ�����ֱ��������ͺ��ֱ���������ȣ�����=��Ӧ���ֳ��ⵥ������
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redview);
      // �����س���������ȡ��
      this.setOpposeNumData(newbluevos, redview);
      new SquareARRushIncomeFor4CRushBP().square(newbluevos, redview);
    }
    // ���ֻس�Ӧ��
    else if (!VOChecker.isEmpty(blueview)) {
      // �����������ֳ���vo��Ҫ�����ֱ��������ͺ��ֱ���������ȣ�����=��Ӧ���ֳ��ⵥ������
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redvos);
      // �����س���������ȡ��
      this.setOpposeNumData(newbluevos, null);
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(newbluevos);
      new SquareARRushIncomeFor4CRushBP().square(sqvos);
    }
    // ���ֻس�Ӧ��
    else if (!VOChecker.isEmpty(redview)) {
      // �����س���������ȡ��
      this.setOpposeNumData(redview, null);
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(redview);
      new SquareARRushIncomeFor4CRushBP().square(sqvos);
    }
  }

  /**
   * ��������ȡ�������¼��㵥�����������
   * 
   * @param bluevos
   * @param redvos
   */
  private void setOpposeNumData(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    if (!VOChecker.isEmpty(bluevos)) {
      for (SquareOutViewVO view : bluevos) {
        this.setOpposeNumData(view);
      }
    }
    if (!VOChecker.isEmpty(redvos)) {
      for (SquareOutViewVO view : redvos) {
        this.setOpposeNumData(view);
      }
    }
  }

  private void setOpposeNumData(SquareOutViewVO view) {

    SquareOutBVO body = view.getItem();
    // ����������
    body.setNthisnum(MathTool.oppose(body.getNthisnum()));
    // ��������
    body.setNastnum(MathTool.oppose(body.getNastnum()));
    // ԭ�ҽ��
    body.setNorigtaxmny(MathTool.oppose(body.getNorigtaxmny()));
    body.setNorigmny(MathTool.oppose(body.getNorigmny()));
    // TODO 2012.02.16 fbinly v61ɾ��ԭ��˰���ֶ�
    // ntempnum = MathTool.oppose(view.getItem().getNorigtax());
    // view.getItem().setNorigtax(ntempnum);
    body.setNorigdiscount(MathTool.oppose(body.getNorigdiscount()));
    // ���ҽ��
    body.setNtaxmny(MathTool.oppose(body.getNtaxmny()));
    body.setNmny(MathTool.oppose(body.getNmny()));
    body.setNtax(MathTool.oppose(body.getNtax()));
    body.setNdiscount(MathTool.oppose(body.getNdiscount()));

    // 2012.02.16 fbinly v61�����ֶ�
    body.setNcaltaxmny(MathTool.oppose(body.getNcaltaxmny()));

  }

  private void addARRoushVO(SquareOutViewVO[] svvos,
      List<SquareOutViewVO> lARRoushVO) {
    for (SquareOutViewVO svo : svvos) {
      UFDouble ntotalestnum = svo.getItem().getNsquareestnum();
      // �ۼ��ݹ�Ӧ��������Ϊ0������Ҫ���س�Ӧ��
      if (!VOChecker.isEmpty(ntotalestnum)
          && ntotalestnum.compareTo(UFDouble.ZERO_DBL) != 0) {
        lARRoushVO.add(svo);
      }
    }
  }

  private SquareOutViewVO[] resetBluevoByRedvo(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // ���ݺ��ֳ��ⵥ�������ֳ��ⵥ
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    SquareOutViewVO bluevo = null;
    DeepCloneTool dct = new DeepCloneTool();
    for (SquareOutViewVO redview : redvos) {
      // ���ֿ�¡
      UFDouble nbluenum = MathTool.abs(bluevos[0].getItem().getNthisnum());
      UFDouble nrednum = MathTool.abs(redview.getItem().getNthisnum());
      // �����������ں�������
      if (MathTool.greaterThan(nbluenum, nrednum)) {
        bluevo = (SquareOutViewVO) dct.deepClone(bluevos[0]);
        bluevo.getItem().setNthisnum(nrednum);
        list.add(bluevo);
      }
    }

    SquareOutViewVO[] rets = null;
    if (list.size() > 0) {
      rets = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(rets);
      // ���¼��㵥���������
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
    }
    else {
      rets = bluevos;
    }

    return rets;
  }

  private void addREGCostVO(SquareOutViewVO[] svvos,
      List<SquareOutViewVO> lREGCostVO) {
    for (SquareOutViewVO svo : svvos) {
      UFDouble ntotalregnum = svo.getItem().getNsquareregnum();
      // �ۼƷ�����Ʒ������Ϊ0������Ҫ���෴�ķ�����Ʒ
      if (!VOChecker.isEmpty(ntotalregnum)
          && ntotalregnum.compareTo(UFDouble.ZERO_DBL) != 0) {
        lREGCostVO.add(svo);
      }
    }
  }

  /**
   * 
   * ��������ϸid��Null����Ϊ֮ǰ����Գ���ϸ�Ѿ����ɣ�
   * ���Ե�ǰ��������Ʒvo���н�����ϸid
   */
  private void setDIDNull(SquareOutViewVO[] views) {
    if (VOChecker.isEmpty(views)) {
      return;
    }
    for (SquareOutViewVO svo : views) {
      svo.getItem().setCsalesquaredid(null);
    }
  }

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    IRule<SquareOutDetailVO> rule = null;

    // ��д�ۼƶԳ�����
    rule = new RewriteOutRush4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(CompareAroundProcesser<SquareOutViewVO> processer) {
    ICompareRule<SquareOutViewVO> rule = null;

    // ������۳�������㵥�Ƿ���Բ������Գ�
    rule = new CheckOutRush4CRule();
    processer.addBeforeRule(rule);

    // ����ɳ���Գ�����
    rule = new CountOutRush4CNumRule();
    processer.addBeforeRule(rule);

  }

}
