package nc.bs.so.m30.rule.maintaincheck;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۶���ɾ��ǰ���ݺϷ���У��
 * @scene 
 * ���۶���ɾ������ǰ
 * @param 
 * ��
 * @author zhangcheng
 *
 */
public class CheckDeletableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {

    for (SaleOrderVO saleordervo : vos) {

      // ������۶���״̬�Ƿ�ɱ�ɾ��
      this.checkEnableDeleteByStatus(saleordervo);
    }

  }

  /**
   * ������۶���״̬�Ƿ�ɱ�ɾ��
   *
   * @param svo
   */
  private void checkEnableDeleteByStatus(SaleOrderVO svo) {

   /* // �Ѿ�����������ɾ��
    String cauditorid = svo.getParentVO().getApprover();
    if (!VOChecker.isEmpty(cauditorid)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0086")@res "��������۶�������ɾ����");
    }*/

    // ֻ������״̬����������״̬��������Ϊ��ʱ�����۶�������ɾ��
    Integer fstatusflag = svo.getParentVO().getFstatusflag();
    if (!BillStatus.FREE.equalsValue(fstatusflag)
        && !BillStatus.AUDITING.equalsValue(fstatusflag)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0087")/*@res "��ǰ���۶�������ɾ����"*/);
    }

  }
}