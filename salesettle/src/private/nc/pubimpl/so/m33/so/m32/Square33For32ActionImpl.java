package nc.pubimpl.so.m33.so.m32;

import nc.bs.so.m33.biz.m32.action.ar.ADIncomeFor32Action;
import nc.bs.so.m33.biz.m32.action.ar.ARIncomeFor32Action;
import nc.bs.so.m33.biz.m32.action.ia.IACostFor32Action;
import nc.bs.so.m33.maintain.m32.UpdateSquare32FlagBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m33.so.m32.ISquareAcionFor32;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class Square33For32ActionImpl implements ISquareAcionFor32 {

  /**
   * ���۷�Ʊ�Զ��ɱ�����
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  @Override
  public void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }

    try {
      // �����η�Ʊ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // ��ѯ���㵥
      SquareInvVO[] sqvos = this.querySquareInvVOBy32ID(vos);

      // �Է�Ʊ�����㵥����
      tool.lockBill(sqvos);

      // ����Ƿ���Դ����
      sqvos = SquareInvVOUtils.getInstance().filterCostableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // ���۷�Ʊ�Զ��ɱ�������±�������־λ
      new UpdateSquare32FlagBP().updateSquareBFlagForAutoIACost(sqvos);

      // ���ɱ�����
      new IACostFor32Action().execCost(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * ���۷�Ʊ�Զ�Ӧ�ս���
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  @Override
  public void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // �������۷�Ʊ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // ��ѯ���㵥
      SquareInvVO[] sqvos = this.querySquareInvVOBy32ID(vos);

      // �Է�Ʊ�����㵥����
      tool.lockBill(sqvos);

      // ����Ƿ���Դ�Ӧ��
      sqvos = SquareInvVOUtils.getInstance().filterIncomeableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // ���۷�Ʊ�Զ����������±�������־λ
      new UpdateSquare32FlagBP().updateSquareBFlagForAutoARIncome(sqvos);

      // ��Ӧ�մ���
      new ARIncomeFor32Action().execIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * ���۷�Ʊ��Ӧ��
   * ���γ��ⵥ���������Զ�Ӧ�ս��㣬���۷�Ʊ���ݵ��۵Ĳ��촫���ȷ��Ӧ��
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  @Override
  public void squareAdjustIncomeSrv(AbstractBill[] vos)
      throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // �������۷�Ʊ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // ��ѯ���㵥
      SquareInvVO[] sqvos = this.querySquareInvVOBy32ID(vos);

      // �Է�Ʊ�����㵥����
      tool.lockBill(sqvos);

      // ����Ƿ���Դ�Ӧ��
      sqvos = SquareInvVOUtils.getInstance().filterIncomeableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // ���۷�Ʊ��Ӧ�ո��±�������־λ
      new UpdateSquare32FlagBP().updateSquareBFlagForAdjustIncome(sqvos);

      // ��Ӧ��
      new ADIncomeFor32Action().execIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private SquareInvVO[] querySquareInvVOBy32ID(AbstractBill[] vos) {
    SquareInvVO[] sqvos =
        new QuerySquare32VOBP().querySquareInvVOBy32ID(SoVoTools
            .getVOPKValues(vos));
    if (VOChecker.isEmpty(sqvos)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0062")/*@res "��������ȱʧ�����۷�Ʊ���δ���ɴ���������"*/);
    }
    return sqvos;
  }

}
