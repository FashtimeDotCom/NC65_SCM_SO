package nc.vo.so.m30.vochange;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.rule.PayTermRule;
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
import nc.vo.so.pub.util.ArrayUtil;

/**
 * �������Ķ���ת���۶���VO���պ���������
 * 
 * @since 6.1
 * @version 2012-2-22 ����14:11:13
 * @author ����
 */
public class OPCToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {

    super.fillRefAddValue(vos);

    for (SaleOrderVO billvo : vos) {

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
      SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);

      // ���������֯��������֯��������֯���
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
      int[] sendstockrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      orgrelrule.setSendStockOrg(sendstockrows);

      int[] trafficrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
      orgrelrule.setTrafficOrg(trafficrows);

      int[] finacerows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
      orgrelrule.setFinanceOrg(finacerows);
      
      
      // ��������������ֵʱ�������������Ĳ��Զ���ֵ
      int[] profitrows =
          bodycouuitl.getFieldNotNullRows(SaleOrderBVO.CPROFITCENTERID);
      SOProfitCenterValueRule profitRule =
          new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID, profitrows);
      
      
      // ��֯��λ��
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(finacerows);
      // �۱�����
      SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
      exrule.calcBodyExchangeRates(finacerows);
      int[] changerows = exrule.getRateChangeRow();
      vocalcultor.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);

      int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      countryrule.setCountryInfo(needchangerows);
      // ��������
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(needchangerows);
     
      int[] buychgrows = buyflgrule.getBuysellChgRow();
      vocalcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
      // ѯ˰
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(needchangerows);
      int[] taxchgrows = taxInfo.getTaxChangeRows();
      vocalcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
      // ��Ʊ�ͻ����
      String invoicecust =
          keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
      if (PubAppTool.isNull(invoicecust)) {
        SOCustRelaDefValueRule custrelarule =
            new SOCustRelaDefValueRule(keyValue);
        custrelarule.setCustRelaInvoiceCust();
      }
      // �����տ�Э����Ϣ
      PayTermRule payTermRule = new PayTermRule(keyValue);
      payTermRule.setPayTermInfo();

      int[] rows = bodycouuitl.getMarNotNullRows();
      // (2)ֱ�˲�
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);
      // (3)���š�ȫ�ֻ��ʼ���
      SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
      globalraterule.calcGlobalExchangeRate(rows);

      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      groupraterule.calcGroupExchangeRate(rows);
    }
  }

  @Override
  protected String getSrcBillTypeCode() {
    return OPCBillType.OPCORDER.getCode();
  }
}
