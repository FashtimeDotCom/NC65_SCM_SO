package nc.bs.so.m33.biz.m4c.bp.square.ar;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.so.m33.biz.m4c.rule.toar.GetNewARorgVidFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.SquareARCloseFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.ToARCheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.pub.exchange.ExchangeBillUtils;
import nc.vo.so.m33.pub.util.ARBillUtil;
import nc.vo.so.pub.util.ListUtil;

public class SquareARIncomeFor4CBP {

  public void square(SquareOutVO[] sqvos) {

    if (sqvos == null || sqvos.length == 0) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareARIncomeFor4C);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    // ����ȷ��������㵥VOת��Ϊ��ȷ�����������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForAR(sqvos);

    processer.before(sqvos);

    this.saveDetail(sqvos, bills);

    // ��Ӧ��
    this.toAR(sqvos);

    processer.after(sqvos);
  }

  private void addAfterRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = new SquareARCloseFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // Ӧ�ս���ǰ��ȡӦ����֯������֯�汾
    IRule<SquareOutVO> rule = new GetNewARorgVidFor4CRule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor4CRule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareOutVO[] sqvos, SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.SquareARIncomeFor4CDetail);
    IRule<SquareOutDetailVO> rule = null;

    // ȷ��������ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // ��д�ۼ�Ӧ�ս�������
    rule = new RewriteARIncomeFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

  private void toAR(SquareOutVO[] sqvos) {
    // �����ι�ϵ�ӿڶ�����ȡӦ�յ��������ͣ����۷�Ʊ��Ӧ����32��F0��
    String srcBillType = ICBillType.SaleOut.getCode();
    String destBillType = IBillFieldGet.F0;
    String squareBillType = SOBillType.SquareOut.getCode();
    AggReceivableBillVO[] arapvos =
        new ExchangeBillUtils<SquareOutVO, AggReceivableBillVO>(
            SquareOutVO.class).exchangeBill(sqvos, squareBillType, srcBillType,
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
