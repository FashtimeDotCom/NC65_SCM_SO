package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.bs.so.m33.biz.m4c.rule.toia.FillDataForToREGCredit;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIABizFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIACheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIARegsiterFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

/**
 * ���۳��ⵥ��������Ʒ����
 * 
 * @since 6.0
 * @version 2011-7-21 ����09:29:53
 * @author zhangcheng
 */
public abstract class AbstractSquareIARegisterCreditFor4CBP {

  /**
   * ��������Ʒ����
   * 
   * @param bluevos
   * @param redvos
   */
  public void square(SquareOutVO[] sqvos) {
    AroundProcesser<SquareOutVO> bprocesser =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareToIARegisterCreditBy4C);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(bprocesser);

    AroundProcesser<SquareOutDetailVO> aprocesser =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareToIARegisterCreditBy4CDetail);

    // ����ִ�к�ҵ�����
    this.addAfterRule(aprocesser);

    // ���۳��ⵥ��������Ʒǰִ��ҵ�����
    bprocesser.before(sqvos);

    // ����������Ʒ���㵥VOת��Ϊ��������Ʒ������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForREGCredit(sqvos);

    // ������Ʒ��ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // ����vo����
    I5BillVO[] i5vos =
        (I5BillVO[]) PfServiceScmUtil.executeVOChange(
            SOBillType.SquareOut.getCode(), IABillType.XSCBJZ.getCode(), sqvos);

    // ��������Ʒ����
    this.toIA(i5vos);

    aprocesser.after(bills);
  }

  /**
   * ��������Ʒ������Ŀǰ��Ϊ��������������Գ塢����ر�
   * 
   * @param i5vos
   */
  protected abstract void toIA(I5BillVO[] i5vos);

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    // ��д�ۼƳɱ���������
    IRule<SquareOutDetailVO> rule = new RewriteIARegsiterFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // ���ô��������ݲ���
    IRule<SquareOutVO> rule = new FillDataForToREGCredit();
    processer.addBeforeRule(rule);

    // ���ɱ���
    rule = new ToIACheckFor4CRule();
    processer.addBeforeRule(rule);

    // �������������ҵ����
    rule = new ToIABizFor4CRule();
    processer.addBeforeRule(rule);
  }

}
