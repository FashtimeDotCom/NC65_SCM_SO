package nc.bs.so.m33.biz.m32.bp.square.toar;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.so.m33.biz.m32.rule.toar.GetNewARorgVidFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.SquareARCloseFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.ToARCheckFor32Rule;
import nc.bs.so.m33.maintain.m32.InsertSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteARIncomeFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.pub.exchange.ExchangeBillUtils;
import nc.vo.so.m33.pub.util.ARBillUtil;
import nc.vo.so.pub.util.ListUtil;

public class SquareARIncomeFor32BP {

  public void square(SquareInvVO[] sqvos) {
    if (sqvos == null || sqvos.length == 0) {
      return;
    }
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(BPPlugInPoint.SquareARIncome);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    // ����ȷ��������㵥VOת��Ϊ��ȷ�����������ϸVO
    SquareInvDetailVO[] bills =
        SquareInvVOUtils.getInstance().changeSQVOtoSQDVOForAR(sqvos);

    processer.before(sqvos);

    // ȷ��������ϸ����BP
    this.saveDetail(sqvos, bills);

    // ��Ӧ��
    this.toAR(sqvos);

    processer.after(sqvos);
  }

  private void addAfterRule(AroundProcesser<SquareInvVO> processer) {
    IRule<SquareInvVO> rule = new SquareARCloseFor32Rule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // Ӧ�ս���ǰ��ȡӦ����֯������֯�汾
    IRule<SquareInvVO> rule = new GetNewARorgVidFor32Rule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor32Rule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareInvVO[] sqvos, SquareInvDetailVO[] bills) {
    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.SquareARIncomeDetail);

    // ȷ��������ϸ����BP
    new InsertSquare32DetailBP().insert(sqvos, bills);

    // ��д�ۼ�Ӧ�ս�������
    IRule<SquareInvDetailVO> rule = new RewriteARIncomeFor32Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

  private void toAR(SquareInvVO[] sqvos) {
    // �����ι�ϵ�ӿڶ�����ȡӦ�յ��������ͣ����۷�Ʊ��Ӧ����32��F0��
    String srcBillType = SOBillType.Invoice.getCode();
    String destBillType = IBillFieldGet.F0;
    String squareBillType = SOBillType.SquareInvoice.getCode();
    AggReceivableBillVO[] arapvos =
        new ExchangeBillUtils<SquareInvVO, AggReceivableBillVO>(
            SquareInvVO.class).exchangeBill(sqvos, squareBillType, srcBillType,
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
