package nc.bs.so.m30.rule.maintainprocess;

import nc.bs.pub.pf.PfUtilTools;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.arap.mf2.ARmF2ServicesUtil;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

/**
 * @description
 * ���۶����������ݱ����տ��������տ������
 * @scene
 * ���۶����������޸ı���󱾴��տ��Ϊ��
 * @param 
 * ��
 * 
 * @since 6.0
 * @version 2011-5-11 ����02:53:09
 * @author ��־ΰ
 */
public class ThisGatheringRule implements IRule<SaleOrderVO> {

  /**
   * �����տ��Ϊ��ʱ������һ���տ
   */
  @Override
  public void process(SaleOrderVO[] vos) {
    UFDouble thisGatheringMny =
        (UFDouble) BSContext.getInstance().getSession(
            "cashsale.thisGatheringMny");
    if (thisGatheringMny != null
        && MathTool.absCompareTo(thisGatheringMny, UFDouble.ZERO_DBL) > 0) {
      try {
        vos[0].getParentVO().setNthisreceivemny(thisGatheringMny);
        // ����ܷ��տ�
        GatherbillUtil.getInstance().checkBeforeGathering(vos[0]);

        // ׼����VO�����Ķ���VO
        SaleOrderVO newOrdvo =
            GatherbillUtil.getInstance().prepareOrderBeforeChangeData(vos[0]);
        // �����տ���
        newOrdvo.getParentVO().setNtotalorigmny(thisGatheringMny);
        newOrdvo.getChildrenVO()[0].setNorigtaxmny(thisGatheringMny);
        new SaleOrderVOCalculator(newOrdvo).calculate(0,
            SaleOrderBVO.NORIGTAXMNY);

        // ת�����տVO
        AggregatedValueObject[] destVOs =
            PfUtilTools.runChangeDataAry(SOBillType.Order.getCode(), "D2",
                new AggregatedValueObject[] {
                  newOrdvo
                });

        ARmF2ServicesUtil.insertGatheringBill((AggGatheringBillVO[]) destVOs);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
