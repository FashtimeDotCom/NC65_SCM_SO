package nc.impl.so.m38.action.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���Ԥ������ǰ״̬�Ƿ������
 * @scene
 * ����Ԥ��������ǰ
 * @param
 * ��
 */
public class UnApproveBillBeforeRule implements IRule<PreOrderVO> {
  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO vo : vos) {

      this.checkStatus(vo);
      this.checkArrnum(vo);

      boolean hasappper =
          BillPowerChecker.hasApproverPermission(vo,
              SOBillType.PreOrder.getCode());
      if (!hasappper) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0176")/*@res "�����������Ȩ�ޡ�"*/);
      }
    }
  }

  private void checkArrnum(PreOrderVO vo) {
    PreOrderBVO[] bvos = vo.getChildrenVO();
    for (PreOrderBVO bvo : bvos) {
      if (MathTool.compareTo(bvo.getNarrnum(), UFDouble.ZERO_DBL) > 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006012_0", "04006012-0025")/*@res "��ǰԤ�����Ѿ����۰��ţ�����ȡ������"*/);
      }
    }

  }

  private void checkStatus(PreOrderVO vo) {
    if (!BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0024")/*@res "��ǰԤ��������״̬�����ɽ���������"*/);
    }
  }
}
