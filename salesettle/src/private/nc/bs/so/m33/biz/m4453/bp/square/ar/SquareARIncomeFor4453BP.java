package nc.bs.so.m33.biz.m4453.bp.square.ar;

import java.util.List;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.so.m33.biz.m4453.rule.ar.FillNewChangeRateFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.ar.GetNewARorgVidFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.ar.ToARCheckFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.push.CheckBeforeARSquareWasRule;
import nc.bs.so.m33.maintain.m4453.InsertSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteARIncomeFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.pub.exchange.ExchangeBillUtils;
import nc.vo.so.m33.pub.util.ARBillUtil;
import nc.vo.so.pub.util.ListUtil;

public class SquareARIncomeFor4453BP {

  public void square(List<SquareWasViewVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }
    SquareWasViewVO[] sqvvos = list.toArray(new SquareWasViewVO[0]);
    this.square(sqvvos);
  }

  public void square(SquareWasViewVO[] sqvvos) {
    SquareWasVO[] sqvos = SquareWasVOUtils.getInstance().combineBill(sqvvos);

    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(BPPlugInPoint.SquareARIncomeFor4453);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    // this.addAfterRule(processer);

    // ����ȷ��������㵥VOת��Ϊ��ȷ�����������ϸVO
    SquareWasDetailVO[] bills =
        SquareWasVOUtils.getInstance().changeSQVOtoSQDVOForAR(sqvos);

    processer.before(sqvos);

    this.saveDetail(sqvos, bills);

    // ��Ӧ��
    this.toAR(sqvos);

    processer.after(sqvos);

  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {
    // ;��Ӧ�ս���ʱ�ж��Ƿ�Դͷ������Ӧ�ս���ر�
    IRule<SquareWasVO> rule = new CheckBeforeARSquareWasRule();
    processer.addBeforeRule(rule);

    // Ӧ�ս���ǰ��ȡӦ����֯������֯�汾
    rule = new GetNewARorgVidFor4453Rule();
    processer.addBeforeRule(rule);

    // ����;��Ӧ�ս���ǰ���ʴ���
    rule = new FillNewChangeRateFor4453Rule();
    processer.addBeforeRule(rule);

    // ����У��
    rule = new ToARCheckFor4453Rule();
    processer.addBeforeRule(rule);

  }

  private void saveDetail(SquareWasVO[] sqvos, SquareWasDetailVO[] bills) {
    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(
            BPPlugInPoint.SquareARIncomeFor4453Detail);

    // ȷ��������ϸ����BP
    new InsertSquareWasDetailBP().insert(sqvos, bills);

    IRule<SquareWasDetailVO> rule = null;
    // ��д�ۼ�Ӧ�ս�������
    rule = new RewriteARIncomeFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

  private void toAR(SquareWasVO[] sqvos) {
    // �����ι�ϵ�ӿڶ�����ȡӦ�յ��������ͣ�;�𵥴�Ӧ����32��F0��
    String srcBillType = ICBillType.WastageBill.getCode();
    String destBillType = IBillFieldGet.F0;
    String squareBillType = SOBillType.SquareWas.getCode();
    AggReceivableBillVO[] arapvos =
        new ExchangeBillUtils<SquareWasVO, AggReceivableBillVO>(
            SquareWasVO.class).exchangeBill(sqvos, squareBillType, srcBillType,
                destBillType);
    MapList<String, AggReceivableBillVO> arapvoMapList =
        ARBillUtil.getInstance().splitArapVO(arapvos);
    for (String pk_org : arapvoMapList.keySet()) {
      // �����ո����ݱ���ű�
      PfServiceScmUtil.processBatch(
          ArapFlowUtil.getCommitActionCode(pk_org, destBillType), destBillType,
          ListUtil.toArray(arapvoMapList.get(pk_org)), null, null);
    }
  }

}
