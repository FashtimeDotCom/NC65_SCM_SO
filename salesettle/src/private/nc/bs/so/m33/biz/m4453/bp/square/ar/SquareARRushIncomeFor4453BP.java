package nc.bs.so.m33.biz.m4453.bp.square.ar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4453.rule.ar.GetNewARorgVidFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.ar.ToARCheckFor4453Rule;
import nc.bs.so.m33.maintain.m4453.InsertSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteARRushIncomeFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���س�Ӧ��BP
 * 
 * @author zhangcheng
 * 
 */
public class SquareARRushIncomeFor4453BP {

  public void square(List<SquareWasViewVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }
    SquareWasViewVO[] sqvvos = list.toArray(new SquareWasViewVO[0]);
    this.square(sqvvos);
  }

  public void square(SquareWasViewVO[] sqvvos) {
    if (VOChecker.isEmpty(sqvvos)) {
      return;
    }
    SquareWasVO[] sqvos = SquareWasVOUtils.getInstance().combineBill(sqvvos);
    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(
            BPPlugInPoint.SquareARRushIncomeFor4453);
    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);
    processer.before(sqvos);

    // ����س���ϸvo
    SquareWasDetailVO[] dvos = this.saveSquareWasDetail(sqvos);

    // ���س�Ӧ��
    this.toEstVerify(dvos, sqvvos);

    processer.after(sqvos);
  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {
    // Ӧ�ս���ǰ��ȡӦ����֯������֯�汾
    IRule<SquareWasVO> rule = new GetNewARorgVidFor4453Rule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor4453Rule();
    processer.addBeforeRule(rule);
  }

  private SquareWasDetailVO[] saveSquareWasDetail(SquareWasVO[] sqvos) {
    // �����س�������㵥VOת��Ϊ���س����������ϸVO
    SquareWasDetailVO[] bills =
        SquareWasVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);

    // �ý�����ϸid���ûس����κ�
    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareWasDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }

    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(
            BPPlugInPoint.SquareARRushIncomeFor4453Detail);

    // �س�Ӧ����ϸ����BP
    new InsertSquareWasDetailBP().insert(sqvos, bills);

    IRule<SquareWasDetailVO> rule = null;
    // ��д�ۼ�Ӧ�ս�������
    rule = new RewriteARRushIncomeFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(bills);

    return bills;
  }

  private void toEstVerify(SquareWasDetailVO[] dvos, SquareWasViewVO[] sviewvos) {
    Map<String, SquareWasViewVO> mapsview =
        new HashMap<String, SquareWasViewVO>();
    for (SquareWasViewVO vo : sviewvos) {
      mapsview.put(vo.getItem().getCsalesquarebid(), vo);
    }

    // ׼���س�Ӧ�սӿڲ���
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareWasDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      SquareWasViewVO view = mapsview.get(dvo.getCsalesquarebid());

      // �����ݹ����۳��ⵥ������ϸid�����س�Ӧ�յ�
      // (֮ǰ�ݴ��ڴ���SquareARRushIncomeFor4453BP��SquareWasVO[]��)
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
