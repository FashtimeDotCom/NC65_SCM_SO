package nc.bs.so.m30.rule.sobalance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

/**
 * @description
 * �տ������ϵ���ݼ�����
 * 1����Ӧ�Ķ���ֻ��һ��Ӧ����֯
 * 2������������ϵ�붩�����ݵ�һ����
 * @scene
 * ���۶��������տ������ϵǰ
 * @param
 * gatherUtil �տ������ϵ���ݼ�鹤����
 */
public class CheckSaleOrderRule implements IRule<SoBalanceVO> {
  private GatherbillUtil gatherUtil;

  public CheckSaleOrderRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    // ����׼������������ѯ
    Set<String> csaleorderidSet = new HashSet<String>();
    for (SoBalanceVO vo : vos) {
      csaleorderidSet.add(vo.getParentVO().getCsaleorderid());
    }
    Map<String, SaleOrderVO> soBillMap = new HashMap<String, SaleOrderVO>();
    if (csaleorderidSet.size() > 0) {
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] soBills =
          query
              .query(csaleorderidSet.toArray(new String[csaleorderidSet.size()]));
      if (soBills != null && soBills.length > 0) {
        for (SaleOrderVO soBill : soBills) {
          soBillMap.put(soBill.getPrimaryKey(), soBill);
        }
      }
    }
    // ���
    for (SoBalanceVO sbBill : vos) {
      String csaleorderid = sbBill.getParentVO().getCsaleorderid();
      SaleOrderVO soBill = soBillMap.get(csaleorderid);
      if (soBill == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0117")/*@res "���۶����ѱ�ɾ������ˢ����������ҵ��"*/);
      }
      else {
        // ������֯�Ƿ�Ψһ
        this.checkArorg(sbBill, soBill);
        // �����Ϣ
        this.getGatherbillUtil().checkSoBalanceAndSaleorderConsistent(sbBill,
            soBill);
      }
    }
  }

  private void checkArorg(SoBalanceVO bill, SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String carorgid = sobalancehvo.getCarorgid();
    SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
    boolean existOrderbal = false;
    for (SoBalanceBVO bodyvo : sobalancebvos) {
      int vostatus = bodyvo.getStatus();
      // ɾ�����в�����������Ϣ
      if (vostatus != VOStatus.DELETED) {
        int fibaltype = bodyvo.getFibaltype().intValue();
        if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
          existOrderbal = true;
        }
      }
    }

    if (existOrderbal) {
      SaleOrderBVO[] bodyvos = saleordervo.getChildrenVO();
      String carorg = saleordervo.getChildrenVO()[0].getCarorgid();
      if (!(carorgid.equals(carorg))) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0108")/*@res "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��"*/);
      }
      for (SaleOrderBVO bodyvo : bodyvos) {
        if (!(carorg.equals(bodyvo.getCarorgid()))) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006011_0", "04006011-0107")/*@res "���۶���������ڶ��Ӧ����֯�����������տ"*/);
        }
      }
    }
  }

  private GatherbillUtil getGatherbillUtil() {
    if (this.gatherUtil == null) {
      this.gatherUtil = GatherbillUtil.getInstance();
    }
    return this.gatherUtil;
  }
}
