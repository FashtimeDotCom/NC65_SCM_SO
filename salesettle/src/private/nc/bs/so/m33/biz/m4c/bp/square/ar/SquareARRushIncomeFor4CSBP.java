package nc.bs.so.m33.biz.m4c.bp.square.ar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.rule.toar.GetNewARorgVidFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.ToARCheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARRushIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * ����ǩ�տ�Ʊ�˻صĳ��ⵥ���س�Ӧ��bp
 * 
 * @since 6.0
 * @version 2011-7-1 ����12:40:04
 * @author zhangcheng
 */
public class SquareARRushIncomeFor4CSBP {

  public void square(SquareOutVO[] sqvos) {
    if (sqvos == null || sqvos.length == 0) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(
            BPPlugInPoint.SquareARRushIncomeFor4CSBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    processer.before(sqvos);

    // ����س���ϸvo
    SquareOutDetailVO[] dvos = this.saveSquareOutDetail(sqvos);

    // ���س�Ӧ��
    this.toEstVerify(dvos, sqvos);

    AroundProcesser<SquareOutDetailVO> aprocesser =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.SquareARRushIncomeFor4CSDetailSBP);
    this.addAfterRule(aprocesser);
    aprocesser.after(dvos);
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

  private SquareOutDetailVO[] saveSquareOutDetail(SquareOutVO[] sqvos) {
    // �����س�������㵥VOת��Ϊ���س����������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);

    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareOutDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }
    // �س�Ӧ����ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    return bills;
  }

  private void toEstVerify(SquareOutDetailVO[] dvos, SquareOutVO[] sqvos) {
    SquareOutViewVO[] sviewvos =
        SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(sqvos);
    Map<String, SquareOutViewVO> mapsview =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO vo : sviewvos) {
      mapsview.put(vo.getItem().getCsalesquarebid(), vo);
    }

    // ��ѯ���γ��ⵥ�ݹ�Ӧ�ռ�¼
    /*    String[] outBids =
            AggVOUtil.getDistinctItemFieldArray(sqvos, SquareOutBVO.CSRCBID,
                String.class);
        Map<String, SquareOutDetailVO> outmap =
            new Square434CQueryImpl().queryETIncomeDvosBy4CBID(outBids);*/

    // ׼���س�Ӧ�սӿڲ���
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareOutDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      SquareOutViewVO view = mapsview.get(dvo.getCsalesquarebid());

      // �����γ�����㵥id�����س�Ӧ�յ�
      // SquareOutDetailVO etoutdvo = outmap.get(view.getItem().getCsrcbid());
      // (֮ǰ�ݴ��ڴ���SquareARRushIncomeFor4CSBP��SquareOutVO[]��)
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
