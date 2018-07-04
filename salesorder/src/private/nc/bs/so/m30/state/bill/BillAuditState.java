package nc.bs.so.m30.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶�������
 *
 * @author
 *         2009-12-17 ����06:50:08
 */
public class BillAuditState extends AbstractBillState<SaleOrderVO> {
  public BillAuditState() {
    super(SaleOrderHVO.FSTATUSFLAG, BillStatus.AUDIT.value());
  }

  @Override
  public boolean isAutoTransitable(SaleOrderVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isPrevStateValid(SaleOrderVO vo) {
    // ֻ����������״̬֮ǰ������״̬�ſ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  public boolean isThisState(SaleOrderViewVO view) {
    Integer value = view.getHead().getFstatusflag();
    return BillStatus.AUDIT.equalsValue(value);
  }

  @Override
  public List<IState<SaleOrderVO>> next() {
    List<IState<SaleOrderVO>> list = new ArrayList<IState<SaleOrderVO>>();
    return list;
  }

  @Override
  public void setState(SaleOrderVO[] bills) {
    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(StatePlugInPoint.BillAuditState);

    TimeLog.logStart();
    SaleOrderVO[] vos = processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0127")/*@res "����ǰִ��ҵ�����"*/); /* -=notranslate=- */

    TimeLog.logStart();
    this.save(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0128")/*@res "�޸ı�ͷ״̬Ϊ����"*/); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0129")/*@res "������ִ��ҵ�����"*/); /* -=notranslate=- */
  }

  private void save(SaleOrderVO[] bills) {
    super.setState(bills);
    List<SaleOrderBVO> list = new ArrayList<SaleOrderBVO>();
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] items = bill.getChildrenVO();
      for (SaleOrderBVO item : items) {
        list.add(item);
      }
    }
    if (list.size() == 0) {
      return;
    }
    String[] names = new String[] {
      SaleOrderBVO.FROWSTATUS
    };
    SaleOrderBVO[] items = new SaleOrderBVO[list.size()];
    items = list.toArray(items);
    VOUpdate<SaleOrderBVO> bo = new VOUpdate<SaleOrderBVO>();
    bo.update(items, names);
  }

}