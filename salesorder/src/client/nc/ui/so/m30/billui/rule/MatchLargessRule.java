package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.mbuylargess.pub.IBuyLargessMatch;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderLarPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.rule.LargessPirceRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.buylargess.OrderLargessMatchPara;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;
import nc.vo.so.mbuylargess.view.BuyLargessMatchViewVO;
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
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ƥ������
 * 
 * @since 6.0
 * @version 2011-6-8 ����06:47:41
 * @author fengjb
 */
@SuppressWarnings("javadoc")
public class MatchLargessRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  public MatchLargessRule(BillCardPanel cardPanel, M30TranTypeVO trantypevo) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
    this.trantypevo = trantypevo;
  }

  public void matchLargess(int[] rows) {
    // 1.������Ҫƥ����������
    // 1.1 ��Ʒ�Ҹ��Ĳ�����ƥ���������� add by zhangby5 for 636
    UFBoolean blrgcashflag = trantypevo.getBlrgcashflag();
    if (blrgcashflag != null && blrgcashflag.booleanValue()) {
      return;
    }
    int[] largessrows = this.getNeedLargessRows(rows);
    if (largessrows.length == 0) {
      return;
    }
    // 2.ɾ��ԭ������,����ƥ��������λ��
    largessrows = this.processOldLargess(largessrows);
    // 3.��֯���ݺ�̨��ѯ
    OrderLargessMatchPara[] largessparas =
        this.getLargessMatchPara(largessrows);
    // 4.���ýӿں�̨��ѯ������Ϣ
    IBuyLargessMatch largesssrv =
        NCLocator.getInstance().lookup(IBuyLargessMatch.class);
    BuyLargessMatchResult[] matchresults = null;
    try {
      matchresults = largesssrv.matchBuyLargessResult(largessparas);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (!this.isMatchResultsNull(matchresults)) {
      // 5.��������Դ��ID
      FillBodyIDRule fbir = new FillBodyIDRule(this.keyValue);
      fbir.fillBodyId(largessrows);
      // 6.���½���
      this.addLargessResultToUI(largessrows, matchresults);
    }
  }

  private boolean isMatchResultsNull(BuyLargessMatchResult[] matchresults) {
    if (null == matchresults || matchresults.length == 0) {
      return true;
    }

    for (BuyLargessMatchResult result : matchresults) {
      if (null != result) {
        return false;
      }
    }
    return true;
  }

  private void addLargessResultToUI(int[] largessrows,
      BuyLargessMatchResult[] matchresults) {

    for (int i = 0; i < largessrows.length; i++) {
      if (null == matchresults[i]) {
        continue;
      }
      BuyLargessMatchViewVO[] matchviews =
          matchresults[i].getBuyLargessMatchViews();
      if (null == matchviews || matchviews.length == 0) {
        continue;
      }
      String csrcbid =
          this.keyValue.getBodyStringValue(largessrows[i],
              SaleOrderBVO.CSALEORDERBID);
      // �²������к�
      int[] insertrows = new int[matchviews.length];
      int j = 0;
      int currentrow = largessrows[i];
      for (BuyLargessMatchViewVO viewvo : matchviews) {
        // ��ԭ������������������ID
        this.keyValue.setBodyValue(currentrow, SaleOrderBVO.CBUYPROMOTTYPEID,
            viewvo.getCpromottypeid());

        // ��ԭ�����������
        this.keyValue.setBodyValue(currentrow, SaleOrderBVO.CBUYLARGESSACTID,
            viewvo.getCmarketactid());
        // ��ԭ��������������ID
        this.keyValue.setBodyValue(currentrow, SaleOrderBVO.CBUYLARGESSID,
            viewvo.getPk_buylargess());

        SaleOrderBVO insertbvo = this.changeToSaleOrderbvo(csrcbid, viewvo);
        int insertrow = currentrow + 1 + j;
        if (insertrow >= this.keyValue.getBodyCount()) {
          this.cardPanel.addLine();
          this.cardPanel.getBillModel().setBodyRowVO(insertbvo, insertrow);
          BillRowNoUtils.addLineRowNos(this.cardPanel, SaleOrderBVO.CROWNO, 1);
        }
        else {
          this.cardPanel.getBillModel().insertRow(insertrow);
          this.cardPanel.getBillModel().setBodyRowVO(insertbvo, insertrow);
          BillRowNoUtils.insertLineRowNos(this.cardPanel, SaleOrderBVO.CROWNO,
              insertrow + 1, 1);
        }

        insertrows[j] = insertrow;
        j++;

        // jilu for 633 �޸�BUG������˴��������еĺ��滹δ�������������к�+1�Ļ����ͻ�����е���Ʒ����һ����
        for (int k = i + 1; k < largessrows.length; k++) {
          largessrows[k] += 1;
        }
        // end
      }

      // �����н������ݴ���
      this.updateUIData(insertrows);
      // Ϊ���������ÿͻ�������
      SOCustMaterialInfoRule socustmar =
          new SOCustMaterialInfoRule(this.keyValue);
      socustmar.setCustMaterial(insertrows);
    }

  }

  private void updateUIData(int[] insertrows) {

    CardPanelValueUtils util = new CardPanelValueUtils(this.cardPanel);
    for (int row : insertrows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      util.setBodyValueForEdit(marvid, row, SaleOrderBVO.CMATERIALVID);
      String astunitid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CASTUNITID);
      util.setBodyValueForEdit(astunitid, row, SaleOrderBVO.CASTUNITID);
      String qtunitid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CQTUNITID);
      util.setBodyValueForEdit(qtunitid, row, SaleOrderBVO.CQTUNITID);

      // �շ�������(ȡ��ʽ�Ļ�ȡ������)
      this.keyValue.setBodyValue(row, SaleOrderBVO.DSENDDATE,
          keyValue.getBodyValue(row - 1, SaleOrderBVO.DSENDDATE));
      this.keyValue.setBodyValue(row, SaleOrderBVO.DRECEIVEDATE,
          keyValue.getBodyValue(row - 1, SaleOrderBVO.DRECEIVEDATE));
    }
    // 1.�����е�Ĭ��ֵ
    BodyDefaultValueRule bodyrule = new BodyDefaultValueRule(this.keyValue);
    bodyrule.setBodyDefValue(insertrows);
    // ����Ĭ���ջ��ͻ�
    SOCustRelaDefValueRule custrefrule =
        new SOCustRelaDefValueRule(this.keyValue);
    custrefrule.setRelaReceiveCust(insertrows);

    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(this.keyValue);
    defaddrule.setCustDefaultAddress(insertrows);
    // 2.������
    SOUnitChangeRateRule chgraterule = new SOUnitChangeRateRule(this.keyValue);
    // �����Ż����������� add by zhangby5
    chgraterule.calcAstAndQtChangeRate(insertrows);

    // 3.��������
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.calculateOnlyNum(insertrows, SaleOrderBVO.NASTNUM);

    // 4.�������������֯
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(this.keyValue);
    orgrelarule.setSendStockOrg(insertrows);
    orgrelarule.setTrafficOrg(insertrows);

    DirectStoreRule dirstorerule = new DirectStoreRule(this.keyValue);
    dirstorerule.setSendStordoc(insertrows);

    // ��������Ӧ�ա���������
    orgrelarule.setFinanceOrg(insertrows);

    // ���ù�����Ϣ
    SOCountryInfoRule countryrule = new SOCountryInfoRule(this.keyValue);
    countryrule.setCountryInfo(insertrows);
    // ��������
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(this.keyValue);
    buyflgrule.setBuysellAndTriaFlag(insertrows);

    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(this.keyValue);
    taxInfo.setTaxInfoByBodyPos(insertrows);

    // 5.����ÿ����֯��λ��
    SOCurrencyRule currule = new SOCurrencyRule(this.keyValue);
    currule.setCurrency(insertrows);
    // 6.����������۱�����
    SOExchangeRateRule exraterule = new SOExchangeRateRule(this.keyValue);
    exraterule.calcBodyExchangeRates(insertrows);
    // 7.ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalraterule =
        new SOGlobalExchangeRate(this.keyValue);
    globalraterule.calcGlobalExchangeRate(insertrows);

    // 8.���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(this.keyValue);
    groupraterule.calcGroupExchangeRate(insertrows);

    // 9.�������۽������
    // ���۽�����
    String pk_group = AppContext.getInstance().getPkGroup();
    boolean istaxpr = SOSysParaInitUtil.getSO23(pk_group).booleanValue();
    String calkey = SaleOrderBVO.NORIGMNY;
    if (istaxpr) {
      calkey = SaleOrderBVO.NORIGTAXMNY;
    }
    int[] havepricerows = this.getHavePriceRows(insertrows, calkey);
    if (havepricerows.length > 0) {
      calcultor.calculate(havepricerows, calkey);
    }
    int[] pricenullrows = this.getPriceNullRows(insertrows);
    if (pricenullrows.length > 0) {
      SaleOrderLarPriceConfig config =
          new SaleOrderLarPriceConfig(this.cardPanel);
      LargessPirceRule larpricerule =
          new LargessPirceRule(this.cardPanel, config);
      larpricerule.setLargessPrice(pricenullrows);
    }
    
    // ���÷�����������
    this.setCsprofitcenterID(keyValue, insertrows);

    this.cardPanel.getBillModel().loadLoadRelationItemValue(insertrows);
  }

  private void setCsprofitcenterID(IKeyValue keyValue, int[] insertrows) {
	// ֱ��ҵ�񣬷�����������=������������
	    if (this.isDirecttype()) {
	      int length = insertrows.length;
	      for (int index = 0; index < length; index++) {
	        String cprofitcenterid =
	            keyValue.getBodyStringValue(insertrows[index],
	                SaleOrderBVO.CPROFITCENTERID);
	        String cprofitcentervid =
	            keyValue.getBodyStringValue(insertrows[index],
	                SaleOrderBVO.CPROFITCENTERVID);
	        keyValue.setBodyValue(insertrows[index], SaleOrderBVO.CSPROFITCENTERID,
	            cprofitcenterid);
	        keyValue.setBodyValue(insertrows[index], SaleOrderBVO.CSPROFITCENTERVID,
	            cprofitcentervid);
	      }
	    }
	    else {
	      // ��������ȡֵ���򣬷�ֱ��ҵ�����ȡֵ
	      SOProfitCenterValueRule profitRule =
	          new SOProfitCenterValueRule(keyValue);
	      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
	          SaleOrderBVO.CSPROFITCENTERID, insertrows);
	    }
	
  }

  private boolean isDirecttype() {
	  // ��ֱ��
	  if (DirectType.DIRECTTRAN_NO.equalsValue(trantypevo.getFdirecttype())) {
	      return false;
	  }
	  return true;
  }

  private int[] getPriceNullRows(int[] insertrows) {

    int i = 0;
    List<Integer> notpricerow = new ArrayList<Integer>();
    for (int row : insertrows) {
      UFDouble norigtaxnetprice =
          this.keyValue
              .getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXNETPRICE);
      if (null == norigtaxnetprice) {
        notpricerow.add(Integer.valueOf(row));
      }

      i++;
    }
    int[] notpricerows = new int[notpricerow.size()];
    i = 0;
    for (Integer row : notpricerow) {
      notpricerows[i] = row.intValue();
      // add by zhangby5 ƥ�䵽������������ʱ��i�����ӣ�
      i++;
    }
    return notpricerows;

  }

  private int[] getHavePriceRows(int[] insertrows, String calkey) {

    List<Integer> haveprice = new ArrayList<Integer>();
    for (int row : insertrows) {
      UFDouble norigtaxnetprice =
          this.keyValue.getBodyUFDoubleValue(row, calkey);
      if (null != norigtaxnetprice) {
        haveprice.add(Integer.valueOf(row));
      }
    }
    int[] havepricerows = new int[haveprice.size()];
    int i = 0;
    for (Integer row : haveprice) {
      havepricerows[i] = row.intValue();
      i++;

    }
    return havepricerows;
  }

  private SaleOrderBVO changeToSaleOrderbvo(String csrcbid,
      BuyLargessMatchViewVO largessviewvo) {
    SaleOrderBVO orderbvo = new SaleOrderBVO();
    orderbvo.setClargesssrcid(csrcbid);
    orderbvo.setCmaterialvid(largessviewvo.getPk_material());
    orderbvo.setCastunitid(largessviewvo.getPk_measdoc());
    orderbvo.setCqtunitid(largessviewvo.getPk_measdoc());
    orderbvo.setNastnum(largessviewvo.getNnum());
    String pk_group = AppContext.getInstance().getPkGroup();
    if (SOSysParaInitUtil.getSO23(pk_group).booleanValue()) {
      orderbvo.setNqtorigtaxnetprc(largessviewvo.getNprice());
      orderbvo.setNorigtaxmny(largessviewvo.getNmny());
    }
    else {
      orderbvo.setNqtorignetprice(largessviewvo.getNprice());
      orderbvo.setNorigmny(largessviewvo.getNmny());
    }
    // ����Ʒ�� ���������
    orderbvo.setCbuypromottypeid(largessviewvo.getCpromottypeid());
    // �����
    orderbvo.setCbuylargessactid(largessviewvo.getCmarketactid());
    orderbvo.setBlargessflag(UFBoolean.TRUE);

    return orderbvo;
  }

  private OrderLargessMatchPara[] getLargessMatchPara(int[] largessrows) {

    OrderLargessMatchPara[] matchparas =
        new OrderLargessMatchPara[largessrows.length];
    int i = 0;
    String csaleorgid = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String ccustomerid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    String corigcurrencyid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    for (int row : largessrows) {
      String corderbid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSALEORDERBID);
      String cmarterialid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALID);
      String castunitid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CASTUNITID);
      UFDouble nastnum =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NASTNUM);

      matchparas[i] = new OrderLargessMatchPara();
      matchparas[i].setCsaleorgid(csaleorgid);
      matchparas[i].setCcustomerid(ccustomerid);
      matchparas[i].setCorigcurrencyid(corigcurrencyid);
      matchparas[i].setDbilldate(dbilldate);
      matchparas[i].setCsaleorderbid(corderbid);
      matchparas[i].setCmarterialid(cmarterialid);
      matchparas[i].setNnum(nastnum);
      matchparas[i].setCastunitid(castunitid);
      i++;
    }
    return matchparas;

  }

  private int[] processOldLargess(int[] largessrows) {

    Set<String> setsrcbid = new HashSet<String>();
    for (int bindrow : largessrows) {
      String bid =
          this.keyValue.getBodyStringValue(bindrow, SaleOrderBVO.CSALEORDERBID);
      if (!PubAppTool.isNull(bid)) {
        setsrcbid.add(bid);
      }
    }
    this.deleteOldLargessRows(setsrcbid);
    int j = 0;
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      String bid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CSALEORDERBID);
      if (setsrcbid.contains(bid)) {
        largessrows[j] = i;
        j++;
      }
    }
    return largessrows;
  }

  private void deleteOldLargessRows(Set<String> setsrcbid) {

    List<Integer> listdelete = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String bindsrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CLARGESSSRCID);
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

  private int[] getNeedLargessRows(int[] rows) {

    int[] largessrows = null;
    List<Integer> listneedrow = new ArrayList<Integer>();
    String custid = this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    // �ͻ�������Ϊ�ն�����ƥ��
    if (PubAppTool.isNull(custid) || null == dbilldate) {
      largessrows = new int[0];
      return largessrows;
    }
    for (int row : rows) {
      // ��Ʒ�в�ƥ��
      UFBoolean largess =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largess && largess.booleanValue()) {
        continue;
      }
      // ����Ϊ���в�ƥ��
      String cmarterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      if (PubAppTool.isNull(cmarterialvid)) {
        continue;
      }
      // // ����Ϊ�ջ��ߺ��ֲ�ƥ�� 2012.04.13 �������ĳɸ��Ļ���0 ��Ҫɾ��ԭƥ�����Ʒ�У�ô�󾴣�
      // UFDouble nnum =
      // this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      // if (null == nnum || nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
      // continue;
      // }
      // ������Ʒ�۸�ֵ��в�ƥ��
      Integer largesstype =
          this.keyValue.getBodyIntegerValue(row, SaleOrderBVO.FLARGESSTYPEFLAG);
      if (Largesstype.APPORTIONMATERIAL.equalsValue(largesstype)
          || Largesstype.APPORTIONLARGESS.equalsValue(largesstype)) {
        continue;
      }
      // �˻����в�ƥ��
      Integer retexchange =
          this.keyValue.getBodyIntegerValue(row, SaleOrderBVO.FRETEXCHANGE);
      if (Fretexchange.EXCHANGE.equalsValue(retexchange)
          || Fretexchange.WITHDRAW.equalsValue(retexchange)) {
        continue;
      }

      listneedrow.add(Integer.valueOf(row));
    }
    largessrows = new int[listneedrow.size()];
    int i = 0;
    for (Integer needrow : listneedrow) {
      largessrows[i] = needrow.intValue();
      i++;
    }
    return largessrows;

  }
}
