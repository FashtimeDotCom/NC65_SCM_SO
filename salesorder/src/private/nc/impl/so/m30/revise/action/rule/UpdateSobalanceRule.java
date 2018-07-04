package nc.impl.so.m30.revise.action.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * ���۶����޶�������˰�ϼƱ仯ʱ�����տ������ϵ
 * @scene
 * ���۶����޶������
 * @param
 * ��
 * @since 6.0
 * @version 2011-8-26 ����09:38:33
 * @author ��־ΰ
 */
public class UpdateSobalanceRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    Map<String, SaleOrderVO> saleOrderMap = new HashMap<String, SaleOrderVO>();
    for (SaleOrderVO bill : bills) {
      saleOrderMap.put(bill.getParentVO().getCsaleorderid(), bill);
    }
    ISOBalanceQuery queryservice =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceVO[] sobalancevos = null;
    try {
      sobalancevos =
          queryservice.querySoBalanceVOBySaleOrderIDs(saleOrderMap.keySet()
              .toArray(new String[saleOrderMap.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (sobalancevos != null && sobalancevos.length > 0) {
      SoBalanceHVO[] soBalanceHVOs = new SoBalanceHVO[sobalancevos.length];
      for (int i = 0; i < sobalancevos.length; i++) {
        SoBalanceHVO head = sobalancevos[i].getParentVO();
        UFDouble totalorigmny =
            saleOrderMap.get(head.getCsaleorderid()).getParentVO()
                .getNtotalorigmny();
        if (MathTool.absCompareTo(totalorigmny, head.getNtotalorigtaxmny()) < 0) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0418")/*@res "�޶����۶�����˰�ϼƲ���С�ڶ����տ���,����ɾ������������ϵ��"*/);
        }
        head.setNtotalorigtaxmny(totalorigmny);
        soBalanceHVOs[i] = head;
      }

      String[] names = new String[] {
        SoBalanceHVO.NTOTALORIGTAXMNY
      };
      VOUpdate<SoBalanceHVO> voUpate = new VOUpdate<SoBalanceHVO>();
      voUpate.update(soBalanceHVOs, names);
    }

  }
}
