package nc.vo.so.m30.vochange;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

public class M4HToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected boolean isProcessMargin() {
    return false;
  }

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {
    super.fillRefAddValue(vos);

    for (SaleOrderVO billvo : vos) {

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
      
      // ���������֯ 
      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
      
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
      int[] rows = bodycouuitl.getMarNotNullRows();
      orgrelrule.setTrafficOrg(rows);
      
      String customer = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
      if (PubAppTool.isNull(customer)) {
        continue;
      }

      // ���ݿͻ��Զ������ͻ�����������֯ҳǩ��Ĭ�����۱��֣����δ�ҵ�����������ű�λ�ҡ�
      SOCustRelaDefValueRule custrule = new SOCustRelaDefValueRule(keyValue);
      custrule.setCustRelaCurrency();
      // 2. �ͻ���ص�Ĭ��ֵ
      SOCustRelaDefValueRule defrule = new SOCustRelaDefValueRule(keyValue);
      defrule.setCustRelaDefValue();

      // (1)������֯��������֯���
//      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
//      int[] rows = bodycouuitl.getMarNotNullRows();

      // modify by wangshu6 for �ͻ�Ϊ��ʱ ������֯ҲҪ����ֵ 20150330
//      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);

//      orgrelrule.setTrafficOrg(rows);
      orgrelrule.setFinanceOrg(rows);
      

      // add by zhangby5 for ��������ȡֵ����
      SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID);

      // (2).ֱ�˲�
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);

      // (3)��֯�������
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(rows);

      SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
      exrule.calcBodyExchangeRates(rows);

      // (4)���š�ȫ�ֻ��ʼ���
      SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
      globalraterule.calcGlobalExchangeRate(rows);

      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      groupraterule.calcGroupExchangeRate(rows);
      /******* ���ù��ҡ��������ͺ�����ó��\ѯ˰(61) ********/
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      countryrule.setCountryInfo(rows);
      // ��������
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(rows);
      SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);
      int[] buychgrows = buyflgrule.getBuysellChgRow();
      vocalcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
      // ѯ˰
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(rows);
      int[] taxchgrows = taxInfo.getTaxChangeRows();
      vocalcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
      /******* ���ù��ҡ��������ͺ�����ó��\ѯ˰(61) ********/
    }
  }

  @Override
  protected String getSrcBillTypeCode() {
    return ICBillType.BorrowOut.getCode();
  }

}
