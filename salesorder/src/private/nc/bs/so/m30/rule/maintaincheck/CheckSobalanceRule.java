package nc.bs.so.m30.rule.maintaincheck;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;

/**
 * 
 * @description
 * �տ������ϵ���ݼ�����
 * @scene
 * 1����Ӧ�Ķ���ֻ��һ��Ӧ����֯
 * 2������������ϵ�붩�����ݵ�һ����
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����3:04:11
 * @author zhangby5
 */
public class CheckSobalanceRule implements IRule<SaleOrderVO> {
  public CheckSobalanceRule() {
    //
  }

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO saleordervo : vos) {
      ISOBalanceQuery queryservice =
          NCLocator.getInstance().lookup(ISOBalanceQuery.class);
      SoBalanceVO[] sobalancevos = null;
      try {
        sobalancevos =
            queryservice.querySoBalanceVOBySaleOrderIDs(new String[] {
              saleordervo.getParentVO().getPrimaryKey()
            });
      }
      catch (BusinessException e) {

        ExceptionUtils.wrappBusinessException(e.getMessage(), e);
      }
      if (sobalancevos != null && sobalancevos.length > 0) {
        this.checkArorg(sobalancevos[0], saleordervo);
        this.checkSoBalanceAndSaleorderConsistent(sobalancevos[0], saleordervo);
        this.checkNorigordbalmny(sobalancevos[0], saleordervo);
      }
    }
  }

  private void checkArorg(SoBalanceVO bill, SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String carorgid = sobalancehvo.getCarorgid();
    SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
    boolean existOrderbal = false;
    for (SoBalanceBVO bodyvo : sobalancebvos) {
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        existOrderbal = true;
      }
    }

    if (!existOrderbal) {
      return;
    }
    SaleOrderBVO[] bodyvos = saleordervo.getChildrenVO();
    String carorg = null;
    for (SaleOrderBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // ɾ�����в�����������Ϣ
      if (vostatus != VOStatus.DELETED) {
        if (carorg == null) {
          carorg = bodyvo.getCarorgid();
        }
        else if (!(carorg.equals(bodyvo.getCarorgid()))) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0107")/*@res "���۶���������ڶ��Ӧ����֯�����������տ"*/);
        }
      }
    }
    if ((carorg != null) && !(carorgid.equals(carorg))) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0108")/*@res "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��"*/);
    }
  }

  private void checkNorigordbalmny(SoBalanceVO bill, SaleOrderVO saleordervo) {
    UFDouble ntotalorigtaxmny = saleordervo.getParentVO().getNtotalorigmny();
    UFDouble totalbodymny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymny(bill,
            SoBalanceBVO.NORIGORDBALMNY);
    boolean issamedirect = false;

    if (((MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) <= 0) && (MathTool
        .compareTo(totalbodymny, UFDouble.ZERO_DBL) <= 0))
        || ((MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) >= 0) && (MathTool
            .compareTo(totalbodymny, UFDouble.ZERO_DBL) >= 0))) {
      issamedirect = true;
    }
    if (!issamedirect) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0109")/*@res "�����տ��ϵ���ϼ��붩����˰�ϼƷ������һ�¡�"*/);
    }

    if (MathTool.absCompareTo(ntotalorigtaxmny, totalbodymny) < 0) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0110")/*@res "�����տ��ϵ���ϼƲ�������ڶ�����˰�ϼơ�"*/);
    }
  }

  private void checkSoBalanceAndSaleorderConsistent(SoBalanceVO bill,
      SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String cinvoicecustid = sobalancehvo.getCinvoicecustid();
    String corigcurrencyid = sobalancehvo.getCorigcurrencyid();

    if (!(corigcurrencyid
        .equals(saleordervo.getParentVO().getCorigcurrencyid()))) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0108")/*@res "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��"*/);
    }
    if (!(cinvoicecustid.equals(saleordervo.getParentVO().getCinvoicecustid()))) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0108")/*@res "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��"*/);
    }
  }
}