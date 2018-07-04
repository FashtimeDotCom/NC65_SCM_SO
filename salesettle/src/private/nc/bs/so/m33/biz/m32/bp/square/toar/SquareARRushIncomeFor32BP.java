package nc.bs.so.m33.biz.m32.bp.square.toar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.biz.m32.rule.toar.GetNewARorgVidFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.ToARCheckFor32Rule;
import nc.bs.so.m33.maintain.m32.InsertSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteARRushIncomeFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;

/**
 * ���س�Ӧ��BP
 * 
 * @author zhangcheng
 * 
 */
public class SquareARRushIncomeFor32BP {

  public void square(SquareInvVO[] sqvos) {
    if (sqvos == null || sqvos.length == 0) {
      return;
    }
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(BPPlugInPoint.SquareRushARIncome);
    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);
    processer.before(sqvos);

    // ����س���ϸvo
    SquareInvDetailVO[] dvos = this.saveSquare32Detail(sqvos);

    // ���س�Ӧ��
    this.toEstVerify(dvos, sqvos);

    processer.after(sqvos);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // Ӧ�ս���ǰ��ȡӦ����֯������֯�汾
    IRule<SquareInvVO> rule = new GetNewARorgVidFor32Rule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor32Rule();
    processer.addBeforeRule(rule);
  }

  private SquareInvDetailVO[] saveSquare32Detail(SquareInvVO[] sqvos) {
    // �����س�������㵥VOת��Ϊ���س����������ϸVO
    SquareInvDetailVO[] bills =
        SquareInvVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);

    // �ý�����ϸid���ûس����κ�
    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareInvDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }

    // ���¸��ݻس������������Ϊ����ȡ�������㣩
    PriceNumMnyCalculatorForVO cal = new PriceNumMnyCalculatorForVO();
    Condition cond = new Condition();
    cond.setUnitPriorType(Condition.MAIN_PRIOR);
    cal.setCondition(cond);
    cal.calculate(bills, SquareInvDetailVO.NNUM);

    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.SquareRushARIncomeDetail);

    // �س�Ӧ����ϸ����BP
    new InsertSquare32DetailBP().insert(sqvos, bills);

    // ��д�ۼ�Ӧ�ս�������
    IRule<SquareInvDetailVO> rule = new RewriteARRushIncomeFor32Rule();
    processer.addAfterRule(rule);
    processer.after(bills);

    return bills;
  }

  private void toEstVerify(SquareInvDetailVO[] dvos, SquareInvVO[] sqvos) {
    SquareInvViewVO[] sviewvos =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(sqvos);
    Map<String, SquareInvViewVO> mapsview =
        new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO vo : sviewvos) {
      mapsview.put(vo.getItem().getCsalesquarebid(), vo);
    }

    // ׼���س�Ӧ�սӿڲ���
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareInvDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      SquareInvViewVO view = mapsview.get(dvo.getCsalesquarebid());

      // �����ݹ����۳��ⵥ������ϸid�����س�Ӧ�յ�
      // (֮ǰ�ݴ��ڴ���SquareARRushIncomeFor32BP��SquareInvVO[]��)
      vos[i].setEst_top_itemid(view.getItem().getProcesseid());

      // �س������ϸid�����س�Ӧ�յ�
      vos[i].setTop_itemid(dvo.getCsalesquaredid());

      // �س����κţ��س�Ӧ�յ�ʹ��
      // TODO ��ʵ���˻س������ϸid�����س�Ӧ�յ����س����κ�Ҳû�����ˣ�ȡ��ʱֱ���ûس������ϸid
      vos[i].setClbh(dvo.getProcesseid());

      // �س�����(Ӧ��Ӧ��Ҫ���������ź��ݹ�����һ��,�����۽���洢�������ݹ������෴,����ȡ��)
      vos[i].setQuantity(MathTool.oppose(view.getItem().getNthisnum()));
      i++;
    }

    // �س�Ӧ�մ�����
    ArapServicesForSOUtil.estVerify(vos);
  }

}
