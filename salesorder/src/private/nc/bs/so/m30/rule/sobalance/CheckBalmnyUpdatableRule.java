package nc.bs.so.m30.rule.sobalance;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * �տ������ϵ���ݼ�����
 * �����رպ�
 * 1�������������Ӷ���������ϵ 2��������༭����������ϵ 3������ɾ������������ϵ
 * ���ֶ����Ƿ���Զ����տ��ܽ������͵Ĳ������ƣ�
 * @scene
 * ���۶��������տ������ϵǰ
 * @param
 * ��
 */
public class CheckBalmnyUpdatableRule implements IRule<SoBalanceVO> {
  public CheckBalmnyUpdatableRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      SoBalanceHVO headvo = bill.getParentVO();
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] saleordervos = query.query(new String[] {
        headvo.getCsaleorderid()
      });
      if ((saleordervos == null) || (saleordervos.length == 0)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0117")/*@res "���۶����ѱ�ɾ������ˢ����������ҵ��"*/);
      }
      else {
        SaleOrderVO saleordervo = saleordervos[0];

        this.checkSaleOrderClose(bill, saleordervo);
        this.checkRedOrder(bill, saleordervo);
      }
    }
  }

  private void checkRedOrder(SoBalanceVO bill, SaleOrderVO saleordervo) {
    SaleOrderHVO header = saleordervo.getParentVO();
    M30TranTypeVO trantype = null;
    try {
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantype = service.queryTranTypeVO(header.getCtrantypeid());
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (trantype == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0102")/*@res "��ѯ�������ͳ���"*/);
      return;
    }
    UFBoolean bredorderpay = trantype.getBredorderpay();
    if ((bredorderpay != null) && (bredorderpay.booleanValue())) {
      return;
    }
    UFDouble ntotalorigmny = header.getNtotalorigmny();
    if (MathTool.compareTo(ntotalorigmny, UFDouble.ZERO_DBL) >= 0) {
      return;
    }
    SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : sobalancebvos) {
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        int vostatus = bodyvo.getStatus();
        //
        if (vostatus == VOStatus.NEW) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0118")/*@res "���۶�����֧�ֺ����տ�������������տ������ϵ��"*/);
        }
        else if (vostatus == VOStatus.UPDATED) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0119")/*@res "���۶�����֧�ֺ����տ�������޸Ķ����տ������ϵ��"*/);
        }
      }
    }

  }

  private void checkSaleOrderClose(SoBalanceVO bill, SaleOrderVO saleordervo) {
    Integer fstatusflag = saleordervo.getParentVO().getFstatusflag();
    if (fstatusflag.intValue() == BillStatus.CLOSED.getIntValue()) {
      SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
      for (SoBalanceBVO bodyvo : sobalancebvos) {
        int fibaltype = bodyvo.getFibaltype().intValue();
        if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() != fibaltype) {
          continue;
        }
        int vostatus = bodyvo.getStatus();
        //
        if (vostatus == VOStatus.NEW) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0120")/*@res "���۶����Ѿ��رգ��������������տ������ϵ��"*/);
        }
        else if (vostatus == VOStatus.UPDATED) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0121")/*@res "���۶����Ѿ��رգ��������޸Ķ����տ������ϵ��"*/);
        }
      }
    }

  }

}