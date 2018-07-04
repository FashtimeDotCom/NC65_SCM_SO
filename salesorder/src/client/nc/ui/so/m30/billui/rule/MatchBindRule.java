package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderBusi;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.sale.MaterialBindleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.bind.OrderBindMatchPara;
import nc.vo.so.m30.bind.OrderBindMatchResult;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SOUnitDefaultRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

/**
 * ƥ����������
 * 
 * @since 6.0
 * @version 2011-6-9 ����03:44:48
 * @author fengjb
 */

public class MatchBindRule {

  private static final int BIND_PRICETYPE = 0;

  private static final int FIND_PRICETYPE = 1;

  private IKeyValue keyValue;

  private BillCardPanel cardPanel;

  public MatchBindRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
  }

  public void matchBind(int[] rows) {
    // 1.������Ҫ�������
    int[] bindrows = this.getNeedBindRows(rows);
    if (bindrows.length == 0) {
      return;
    }
    // 2.ɾ��ԭ������,��������������λ��
    bindrows = this.processOldBind(bindrows);
    // 3.��֯���ݺ�̨��ѯ
    OrderBindMatchPara[] bindparas = this.getBindMatchPara(bindrows);
    // 4.���ýӿں�̨��ѯ������Ϣ
    ISaleOrderBusi bussrv =
        NCLocator.getInstance().lookup(ISaleOrderBusi.class);
    OrderBindMatchResult[] matchresults = null;
    try {
      matchresults = bussrv.matchBind(bindparas);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    // 6.�����ƥ���ϵģ�����ʾ��Ϣ
    if (this.isCanMatch(matchresults)
        && UIDialog.ID_OK == MessageDialog
        .showOkCancelDlg(this.cardPanel, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0010")/*��ʾ*/, NCLangRes
            .getInstance().getStrByID("4006011_0", "04006011-0259")/*�Ƿ��������ϣ�*/)) {
      // 7.��������ID
      FillBodyIDRule fbir = new FillBodyIDRule(this.keyValue);
      fbir.fillBodyId(bindrows);
      // 8.����������Ϣ������
      this.addBindResultToUI(bindrows, matchresults);
    }
  }

  private boolean isCanMatch(OrderBindMatchResult[] matchresults) {
    boolean ismatch = false;
    if (null != matchresults) {
      for (OrderBindMatchResult result : matchresults) {
        if (null != result.getMatchBindleVOs()
            && result.getMatchBindleVOs().length > 0) {
          ismatch = true;
          break;
        }
      }
    }
    return ismatch;
  }

  private void addBindResultToUI(int[] bindrows,
      OrderBindMatchResult[] matchresults) {
    for (int i = 0; i < matchresults.length; i++) {
      MaterialBindleVO[] bindles = matchresults[i].getMatchBindleVOs();
      if (null == bindles || bindles.length == 0) {
        continue;
      }
      // �²�����
      int[] insertrows = new int[bindles.length];
      // �۸��ȡ��ʽ
      int[] pricetype = new int[bindles.length];
      int j = 0;

      int currrow = bindrows[i];
      String csrcorderbid =
          this.keyValue.getBodyStringValue(currrow, SaleOrderBVO.CSALEORDERBID);
      UFDouble nnum =
          this.keyValue.getBodyUFDoubleValue(currrow, SaleOrderBVO.NNUM);
      for (MaterialBindleVO bindle : bindles) {
        int insertrow = currrow + 1 + j;
        SaleOrderBVO orderbvo =
            this.chgBindleToOrder(csrcorderbid, nnum, bindle);
        if (insertrow >= this.keyValue.getBodyCount()) {
          this.cardPanel.addLine();
          this.cardPanel.getBillModel().setBodyRowVO(orderbvo, insertrow);
          BillRowNoUtils.addLineRowNos(this.cardPanel, SaleOrderBVO.CROWNO, 1);
        }
        else {
          this.cardPanel.getBillModel().insertRow(insertrow);
          this.cardPanel.getBillModel().setBodyRowVO(orderbvo, insertrow);
          BillRowNoUtils.insertLineRowNos(this.cardPanel, SaleOrderBVO.CROWNO,
              insertrow + 1, 1);
        }

        insertrows[j] = insertrow;
        pricetype[j] = bindle.getPricetype().intValue();
        j++;
      }
      // �����н������ݴ���
      this.updateUIData(insertrows, pricetype);
      
      //TODO ���� ����������û�е�λ������£���������������������������ʱ�򾫶ȴ��󡣲�ȷ����uap���⣬����ʱ��ô����
      //nc.ui.pub.bill.BillModel.setBillItemDecimalByRow(BillItem item, int row)
      for(int row:insertrows){
        this.keyValue.setBodyValue(row, SOItemKey.NNUM,
            this.keyValue.getBodyValue(row, SOItemKey.NNUM));
        this.keyValue.setBodyValue(row, SOItemKey.NQTUNITNUM,
            this.keyValue.getBodyValue(row, SOItemKey.NQTUNITNUM));
        this.keyValue.setBodyValue(row, SOItemKey.NASTNUM,
            this.keyValue.getBodyValue(row, SOItemKey.NASTNUM));
      }
      
      // ����ԭʼƥ��������
      this.updateBindRow(bindrows, i + 1, insertrows.length);
    }
  }

  private SaleOrderBVO chgBindleToOrder(String csrcorderbid, UFDouble nnum,
      MaterialBindleVO bindle) {
    SaleOrderBVO orderbvo = new SaleOrderBVO();
    orderbvo.setCbindsrcid(csrcorderbid);
    orderbvo.setBbindflag(UFBoolean.TRUE);
    orderbvo.setCmaterialvid(bindle.getPk_bindle());
    UFDouble bindnum = new UFDouble(bindle.getBindlenum().intValue());
    orderbvo.setNnum(nnum.multiply(bindnum));
    // ������ȷ�ϵ���Ĭ�Ϸ��ں���������
    orderbvo.setNorigtaxprice(bindle.getPrice());
    return orderbvo;
  }

  private void updateBindRow(int[] bindrows, int startindex, int insertlinesize) {
    for (int i = startindex; i < bindrows.length; i++) {
      bindrows[i] = bindrows[i] + insertlinesize;
    }
  }

  private void updateUIData(int[] insertrows, int[] pricetype) {

    CardPanelValueUtils util = new CardPanelValueUtils(this.cardPanel);
    for (int row : insertrows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      util.setBodyValueForEdit(marvid, row, SaleOrderBVO.CMATERIALVID);
    }
    this.keyValue.getBodyStringValue(1, SaleOrderBVO.NITEMDISCOUNTRATE);
    // �����е�Ĭ��ֵ
    BodyDefaultValueRule defvaluerule = new BodyDefaultValueRule(this.keyValue);
    defvaluerule.setBodyDefValue(insertrows);
    // ����Ĭ���ջ��ͻ�
    SOCustRelaDefValueRule custrefrule =
        new SOCustRelaDefValueRule(this.keyValue);
    custrefrule.setRelaReceiveCust(insertrows);

    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(this.keyValue);
    defaddrule.setCustDefaultAddress(insertrows);
    // Ĭ��ҵ��λ�����쵥λ
    SOUnitDefaultRule unitrule = new SOUnitDefaultRule(this.keyValue);
    unitrule.setDefaultSaleUnit(insertrows);

    // ������
    SOUnitChangeRateRule changeraterule =
        new SOUnitChangeRateRule(this.keyValue);
    //�����Ż����������� add by zhangby5
    changeraterule.calcAstAndQtChangeRate(insertrows);
    // ��������
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.calculateOnlyNum(insertrows, SaleOrderBVO.NNUM);

    // �������������֯
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(this.keyValue);
    orgrelarule.setSendStockOrg(insertrows);

    DirectStoreRule dirstorerule = new DirectStoreRule(this.keyValue);
    dirstorerule.setSendStordoc(insertrows);

    orgrelarule.setTrafficOrg(insertrows);
    // ��������Ӧ�ա���������
    orgrelarule.setFinanceOrg(insertrows);

    // ���ù�����Ϣ
    SOCountryInfoRule countryrule = new SOCountryInfoRule(this.keyValue);
    countryrule.setCountryInfo(insertrows);
    // ��������
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(this.keyValue);
    buyflgrule.setBuysellAndTriaFlag(insertrows);

    // ���ﲻ��Ҫ���� ������������������������������
    // int[] buychgrows = buyflgrule.getBuysellChgRow();
    // calcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(this.keyValue);
    taxInfo.setTaxInfoByBodyPos(insertrows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    calcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);

    // ��֯��λ��
    SOCurrencyRule currule = new SOCurrencyRule(this.keyValue);
    currule.setCurrency(insertrows);

    SOExchangeRateRule exraterule = new SOExchangeRateRule(this.keyValue);
    exraterule.calcBodyExchangeRates(insertrows);
    // 7.ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalraterule =
        new SOGlobalExchangeRate(this.keyValue);
    globalraterule.calcGlobalExchangeRate(insertrows);

    // 8.���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(this.keyValue);
    groupraterule.calcGroupExchangeRate(insertrows);

    int[] findpricerows = this.getFindPriceRow(insertrows, pricetype);
    if (findpricerows.length > 0) {
      SaleOrderFindPriceConfig config =
          new SaleOrderFindPriceConfig(this.cardPanel);
      FindSalePrice findprice = new FindSalePrice(this.cardPanel, config);
      findprice.forceFindPrice(findpricerows);
    }
    int[] bindpricerows = this.getBindPriceRow(insertrows, pricetype);
    if (bindpricerows.length > 0) {
      // ���۽�����
      calcultor.calculate(bindpricerows, SaleOrderBVO.NORIGTAXPRICE);
    }
    // this.cardPanel.getBillModel().loadLoadRelationItemValue(insertrows);

    // Ϊ���������ÿͻ�������
    SOCustMaterialInfoRule socustmar =
        new SOCustMaterialInfoRule(this.keyValue);
    socustmar.setCustMaterial(insertrows);
  }

  private int[] getBindPriceRow(int[] insertrows, int[] pricetype) {

    List<Integer> listbindprice = new ArrayList<Integer>();
    int i = 0;
    for (int row : insertrows) {
      if (MatchBindRule.BIND_PRICETYPE == pricetype[i]) {
        listbindprice.add(Integer.valueOf(row));
      }
      i++;
    }
    int[] bindpricerows = new int[listbindprice.size()];
    i = 0;
    for (Integer row : listbindprice) {
      bindpricerows[i] = row.intValue();
      i++;
    }

    return bindpricerows;

  }

  private int[] getFindPriceRow(int[] insertrows, int[] pricetype) {

    List<Integer> listfindprice = new ArrayList<Integer>();
    int i = 0;
    for (int row : insertrows) {
      if (MatchBindRule.FIND_PRICETYPE == pricetype[i]) {
        listfindprice.add(Integer.valueOf(row));
      }
      i++;
    }
    int[] findpricerows = new int[listfindprice.size()];
    i = 0;
    for (Integer row : listfindprice) {
      findpricerows[i] = row.intValue();
      i++;
    }

    return findpricerows;
  }

  private int[] processOldBind(int[] bindrows) {
    Set<String> setsrcbid = new HashSet<String>();
    for (int bindrow : bindrows) {
      String bid =
          this.keyValue.getBodyStringValue(bindrow, SaleOrderBVO.CSALEORDERBID);
      if (!PubAppTool.isNull(bid)) {
        setsrcbid.add(bid);
      }
    }
    this.deleteOldBindRows(setsrcbid);
    int j = 0;
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      String bid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CSALEORDERBID);
      if (setsrcbid.contains(bid)) {
        bindrows[j] = i;
        j++;
      }
    }
    return bindrows;
  }

  private OrderBindMatchPara[] getBindMatchPara(int[] bindrows) {

    OrderBindMatchPara[] matchparas = new OrderBindMatchPara[bindrows.length];
    int i = 0;
    String csaleorgid = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    for (int row : bindrows) {
      String cmarterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      UFDouble nnum =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      matchparas[i] =
          new OrderBindMatchPara(csaleorgid, cmarterialvid, nnum, dbilldate);
      i++;
    }
    return matchparas;
  }

  private void deleteOldBindRows(Set<String> setsrcbid) {
    List<Integer> listdelete = new ArrayList<Integer>();
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      String bindsrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CBINDSRCID);
      if (!PubAppTool.isNull(bindsrcid) && setsrcbid.contains(bindsrcid)) {
        listdelete.add(Integer.valueOf(i));
      }
    }
    if (listdelete.size() > 0) {
      int[] delrows = new int[listdelete.size()];
      int i = 0;
      for (Integer row : listdelete) {
        delrows[i] = row.intValue();
        i++;
      }
      this.cardPanel.getBillModel().delLine(delrows);
    }
  }

  private int[] getNeedBindRows(int[] rows) {

    int[] bindrows = null;
    List<Integer> listneedrow = new ArrayList<Integer>();
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    // ����Ϊ�ն�����ƥ��
    if (null == dbilldate) {
      bindrows = new int[0];
      return bindrows;
    }
    for (int row : rows) {
      // ��Ʒ�в�����
      UFBoolean largess =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largess && largess.booleanValue()) {
        continue;
      }
      // ����Ϊ���в�����
      String cmarterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      if (PubAppTool.isNull(cmarterialvid)) {
        continue;
      }
      // ����Ϊ�ջ��ߺ��ֲ�����----------2012.04.13 �������ĳɸ��Ļ���0 ��Ҫɾ��ԭ�����У�ô�󾴣�
      // UFDouble nnum =
      // this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      // if (null == nnum || nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
      // continue;
      // }
      // ������ز�ƥ��
      UFBoolean bindflag =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BBINDFLAG);
      if (null != bindflag && bindflag.booleanValue()) {
        continue;
      }
      // �˻����в�����
      Integer retexchange =
          this.keyValue.getBodyIntegerValue(row, SaleOrderBVO.FRETEXCHANGE);
      if (Fretexchange.EXCHANGE.equalsValue(retexchange)
          || Fretexchange.WITHDRAW.equalsValue(retexchange)) {
        continue;
      }
      UFDouble  nnum= this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      UFDouble  nastnum= this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NASTNUM);
      if(MathTool.isZero(nnum)||MathTool.isZero(nastnum)){
        continue;
      }
      listneedrow.add(Integer.valueOf(row));
    }
    bindrows = new int[listneedrow.size()];
    int i = 0;
    for (Integer needrow : listneedrow) {
      bindrows[i] = needrow.intValue();
      i++;
    }
    return bindrows;
  }
}
