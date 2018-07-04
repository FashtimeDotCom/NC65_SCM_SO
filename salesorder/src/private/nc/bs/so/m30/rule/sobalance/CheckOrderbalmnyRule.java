package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;

/**
 * @description
 * �տ������ϵ���ݼ�����
 * 1�������տ��������Լ��
 * @scene
 * ���۶��������տ������ϵǰ
 * @param
 * ��
 */
public class CheckOrderbalmnyRule implements IRule<SoBalanceVO> {
  public CheckOrderbalmnyRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      this.checkNorigordbalmny(bill);
    }
  }

  /**
   * 1������������ϵ����Ϊ0
   * 2������������ϵ����С���Ѳ��������ͬ���򣬾���ֵ�Ƚϣ�
   * 3��ͬ�������տ��ϵ���ϼ�С�ڶ�����˰�ϼƣ�ͬ���򣬾���ֵ�Ƚϣ�
   *
   * @param bill
   */
  private void checkNorigordbalmny(SoBalanceVO bill) {
    SoBalanceHVO headvo = bill.getParentVO();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    UFDouble ntotalorigtaxmny = headvo.getNtotalorigtaxmny();
    for (SoBalanceBVO bodyvo : bodyvos) {
      if (bodyvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      UFDouble norigordbalmny = bodyvo.getNorigordbalmny();
      UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        if ((norigordbalmny == null) || MathTool.isZero(norigordbalmny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0122")/*@res "�������������������Ϊ0�Ķ���������ϵ��"*/);
        }
        if (!(((MathTool.compareTo(norigordbalmny, UFDouble.ZERO_DBL) <= 0) && (MathTool
            .compareTo(norigaccbalmny, UFDouble.ZERO_DBL) <= 0)) || ((MathTool
            .compareTo(norigordbalmny, UFDouble.ZERO_DBL) >= 0) && (MathTool
            .compareTo(norigaccbalmny, UFDouble.ZERO_DBL) >= 0)))) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0123")/*@res "��������������Ѳ�������������һ�¡�"*/);
        }
        if (MathTool.absCompareTo(norigordbalmny, norigaccbalmny) < 0) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0124")/*@res "��������������С���Ѳ��������"*/);
        }
      }
    }
    UFDouble totalbodymny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymny(bill,
            SoBalanceBVO.NORIGORDBALMNY);
    if (!(((MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) <= 0) && (MathTool
        .compareTo(totalbodymny, UFDouble.ZERO_DBL) <= 0)) || ((MathTool
        .compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) >= 0) && (MathTool
        .compareTo(totalbodymny, UFDouble.ZERO_DBL) >= 0)))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0109")/*@res "�����տ��ϵ���ϼ��붩����˰�ϼƷ������һ�¡�"*/);
    }
    
    Boolean listenerflag = bill.getListenerflag() == null ? false : bill.getListenerflag().booleanValue();
    if (!listenerflag && MathTool.absCompareTo(ntotalorigtaxmny, totalbodymny) < 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0110")/*@res "�����տ��ϵ���ϼƲ�������ڶ�����˰�ϼơ�"*/);
    }
  }
}