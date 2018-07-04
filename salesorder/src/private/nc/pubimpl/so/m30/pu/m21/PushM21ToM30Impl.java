package nc.pubimpl.so.m30.pu.m21;

import nc.impl.so.m30.action.main.InsertSaleOrderAction;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30.ref.scmpub.CoopVOChangeUtil;
import nc.pubitf.so.m30.pu.m21.IPush21To30;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.FillNmffilePriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;

public class PushM21ToM30Impl implements IPush21To30 {

  @Override
  public void push21To30(OrderVO[] srcBills) throws BusinessException {
    SaleOrderVO[] destBills =
        PfServiceScmUtil.executeVOChange(POBillType.Order.getCode(),
            SOBillType.Order.getCode(), srcBills);
    CoopVOChangeUtil coopUtil = new CoopVOChangeUtil();
    // Эͬ���� && �ͻ�����Ĭ��ֵ
    try {
      SaleOrderVO[] vos = coopUtil.processVO(srcBills, destBills);
      
      /*
       * add by lijiep ����Эͬ�ɹ��������� jilu for 633
       */
      for (SaleOrderVO billvo : vos) {
        IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
        // ���������֯��������֯��������֯���
        BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
        int[] finacerows =
            bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
        // ��֯��λ��
        SOCurrencyRule currule = new SOCurrencyRule(keyValue);
        currule.setCurrency(finacerows);
        // �۱�����
        SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
        exrule.calcBodyExchangeRates(finacerows);

        // ���š�ȫ�ֻ��ʼ���
        SOGlobalExchangeRate globalraterule =
            new SOGlobalExchangeRate(keyValue);
        globalraterule.calcGlobalExchangeRate(finacerows);

        SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
        groupraterule.calcGroupExchangeRate(finacerows);
        

        SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);
        vocalcultor.calculate(finacerows, SaleOrderBVO.NEXCHANGERATE);
        
        // ����������
        FillNmffilePriceRule nmffileRule = new FillNmffilePriceRule(keyValue);
        nmffileRule.setNmffilePrice();
      }
      // end of add
      
      InsertSaleOrderAction action = new InsertSaleOrderAction();
      // �Ƶ���Ϊ�ɹ�����������
      for (OrderVO srcbill : srcBills) {
        String srcHid =
            (String) srcbill.getParentVO().getAttributeValue(
                OrderHeaderVO.PK_ORDER);
        String srcOperator =
            (String) srcbill.getParentVO().getAttributeValue(
                OrderHeaderVO.BILLMAKER);
        for (SaleOrderVO desbill : vos) {
          SaleOrderBVO[] bvos = desbill.getChildrenVO();
          for (SaleOrderBVO bvo : bvos) {
            if (srcHid.equals(bvo.getCsrcid())) {
              desbill.getParentVO().setBillmaker(srcOperator);
            }
          }
        }
      }
      action.insert(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
