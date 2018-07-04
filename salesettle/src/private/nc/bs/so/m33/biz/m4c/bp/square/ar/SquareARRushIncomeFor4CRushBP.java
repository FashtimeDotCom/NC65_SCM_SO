package nc.bs.so.m33.biz.m4c.bp.square.ar;

import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.rule.toar.GetNewARorgVidFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.ToARCheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARRushIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.CirVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * ����Գ崫�س�Ӧ��BP
 * 
 * @since 6.0
 * @version 2011-7-1 ����12:41:27
 * @author zhangcheng
 */
public class SquareARRushIncomeFor4CRushBP {

  public void square(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    // �ϲ�vo
    List<SquareOutViewVO> lview = CirVOUtil.combineBill(bluevos, redvos);
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            lview.toArray(new SquareOutViewVO[0]));
    // �����س�������㵥VOת��Ϊ���س����������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(bluevos,
            redvos);
    this.procesSquare(sqvos, bills);
  }

  public void square(SquareOutVO[] sqvos) {
    // �����س�������㵥VOת��Ϊ���س����������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);
    this.procesSquare(sqvos, bills);
  }

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    // ��д�ۼƻس��������
    IRule<SquareOutDetailVO> rule = new RewriteARRushIncomeFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // Ӧ�ս���ǰ��ȡӦ����֯������֯�汾
    IRule<SquareOutVO> rule = new GetNewARorgVidFor4CRule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor4CRule();
    processer.addBeforeRule(rule);
  }

  private void procesSquare(SquareOutVO[] sqvos, SquareOutDetailVO[] dvos) {
    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareARRushIncomeFor4CRushBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);
    processer.before(sqvos);

    // ����س���ϸvo
    SquareOutDetailVO[] dnewvos = this.saveSquareOutDetail(dvos, sqvos);

    // ���س�Ӧ��
    this.toEstVerify(dnewvos);

    AroundProcesser<SquareOutDetailVO> aprocesser =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareARRushIncomeFor4CDetailRushBP);
    this.addAfterRule(aprocesser);
    aprocesser.after(dvos);
  }

  private SquareOutDetailVO[] saveSquareOutDetail(SquareOutDetailVO[] bills,
      SquareOutVO[] sqvos) {

    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareOutDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }
    // ���¸��ݻس������������Ϊ����ȡ�������㣩
    PriceNumMnyCalculatorForVO cal = new PriceNumMnyCalculatorForVO();
    Condition cond = new Condition();
    cond.setUnitPriorType(Condition.MAIN_PRIOR);
    cal.setCondition(cond);
    cal.calculate(bills, SquareOutDetailVO.NNUM);

    // �س�Ӧ����ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    return bills;
  }

  private void toEstVerify(SquareOutDetailVO[] dvos) {
    String[] outbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSQUAREBILLBID);
    Map<String, SquareOutDetailVO> map =
        new QuerySquare4CVOBizBP().queryETSquareOutDetailVOBy4CBID(outbids);
    // û���ݹ����ݣ�˵�������쳣
    if (map.size() == 0) {
      ExceptionUtils.unSupported();
    }

    // ׼���س�Ӧ�սӿڲ���
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareOutDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      // ���۳����ݹ����㵥
      SquareOutDetailVO etvo = map.get(dvo.getCsquarebillbid());
      // ���ݹ��ĳ�����㵥id�����س�Ӧ�յ�
      vos[i].setEst_top_itemid(etvo.getCsalesquaredid());
      // �س������ϸid�����س�Ӧ�յ�
      vos[i].setTop_itemid(dvo.getCsalesquaredid());
      // �س����κţ��س�Ӧ�յ�ʹ��
      // TODO ��ʵ���˻س������ϸid�����س�Ӧ�յ����س����κ�Ҳû�����ˣ�ȡ��ʱֱ���ûس������ϸid
      vos[i].setClbh(dvo.getProcesseid());
      // �س�����(Ӧ��Ӧ��Ҫ���������ź��ݹ�����һ��,�����۽���洢�������ݹ������෴,����ȡ��)
      vos[i].setQuantity(MathTool.oppose(dvo.getNsquarenum()));
      i++;
    }

    // �س�Ӧ�մ�����
    ArapServicesForSOUtil.estVerify(vos);
  }

}
