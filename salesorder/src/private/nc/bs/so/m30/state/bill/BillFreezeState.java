package nc.bs.so.m30.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.state.BillStateUtil;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ������������
 */
public class BillFreezeState extends AbstractBillState<SaleOrderVO> {
  public BillFreezeState() {
    super(SaleOrderHVO.FSTATUSFLAG, BillStatus.FREEZE.value());
  }

  @Override
  public boolean isAutoTransitable(SaleOrderVO vo) {
    return false;
  }

  @Override
  public boolean isPrevStateValid(SaleOrderVO vo) {
    // ֻ��������״̬�Լ�֮�������״̬�ſ�������״̬�Ĳ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<SaleOrderVO>> next() {
    List<IState<SaleOrderVO>> list = new ArrayList<IState<SaleOrderVO>>();
    return list;
  }

  @Override
  public void setState(SaleOrderVO[] bills) {
    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(StatePlugInPoint.BillFreezeState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderVO[] vos = processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0132")/*@res "���ö���ǰ״̬�����"*/); /* -=notranslate=- */

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0133")/*@res "�޸ı�ͷ״̬Ϊ��������"*/); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0134")/*@res "���ö����״̬�����"*/); /* -=notranslate=- */
  }

  private void addRule(AroundProcesser<SaleOrderVO> processer) {
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ���������ǰ����
      processer.addBeforeRule(new SaleOrderVOATPBeforeRule());

      // ��������������
      processer.addAfterRule(new SaleOrderVOATPAfterRule());
    }

  }
}
