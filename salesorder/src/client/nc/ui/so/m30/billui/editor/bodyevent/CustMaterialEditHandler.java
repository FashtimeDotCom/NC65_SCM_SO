package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m30.IQueryRelationOrg;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.BodyDefaultValueRule;
import nc.ui.so.m30.billui.rule.ClearBodyValueRule;
import nc.ui.so.m30.billui.rule.MatchBindLargessRule;
import nc.ui.so.m30.billui.rule.RelateRowDeleteRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
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
import nc.vo.so.pub.rule.SOUnitDefaultRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.uapbd.custmaterial.CustMaterialVO;
import nc.vo.uapbd.custom.CustomVO;

/**
 * �ͻ�������༭�¼�
 * 
 * @since 6.3
 * @version 2012-12-11 13:12:14
 * @author liangjm
 */

public class CustMaterialEditHandler {

  /**
   * �ͻ�������༭��
   * 
   * @param e
   * @param billform
   * 
   */

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    // �ɵĵ�λ
    String oldmarvid = (String) e.getOldValue();
    String oldunitid = this.getMaterialUnitID(oldmarvid);
    String oldastunitid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CASTUNITID);
    String oldqtunitid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CQTUNITID);

    // ����ԭʼ��ID
    String oldbid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CSALEORDERBID);
    // ɾ������������ƥ����
    String[] srcbids = new String[] {
      oldbid
    };
    RelateRowDeleteRule delrule = new RelateRowDeleteRule(keyValue);
    int[] todelrows = delrule.getRelaDeleteRows(srcbids);
    if (todelrows.length > 0) {
      for (int delrow : todelrows) {
        if (delrow < editrow) {
          editrow--;
        }
      }
      cardPanel.getBillModel().delLine(todelrows);
    }
    // --1.��շ��������۽���ֶ���Ϣ
    Map<String, CtBusinessVO> ctMap = billform.getCtMap();
    ClearBodyValueRule clearrule = new ClearBodyValueRule(keyValue, ctMap);
    clearrule.clearBodyNoNumPriceMnyValue(editrow);
    // --���ն�ѡ����
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(cardPanel);
    int[] rows = utils.refMoreSelected(editrow, e.getKey(), true);
    editrow = editrow + rows.length - 1;

    // --2.����Ĭ�ϵ�λ,�����㻻����
    SOUnitDefaultRule unitdef = new SOUnitDefaultRule(keyValue);
    unitdef.setDefaultSaleUnit(rows);
    SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
    //�����Ż����������� add by zhangby5
  	unitrate.calcAstAndQtChangeRate(rows);

    // ��λ�Ƿ�ı�
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    boolean isunitchg =
        unitdef.isUnitChange(editrow, oldunitid, oldastunitid, oldqtunitid);
    if (isunitchg) {
      clearrule.clearBodyNumPirceMnyValue(editrow);
    }
    else {
      calculator.calculate(editrow, SaleOrderBVO.NNUM);
    }

    // --3.��������ҵ��ί�й�ϵ���Ĭ�Ϸ��������֯��ֱ�˲�
    // --4.���������֯�仯����½��������֯��Ӧ����֯����������
    // --5.��������ί�й�ϵ���Ĭ��������֯
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceStockOrg(rows,
        this.GetRelationOrg(keyValue, rows));

    // --6.������������֯��Ӧ�ı���
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // --7.������֯��λ�����¼����۱�����
    SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
    changeraterule.calcBodyExchangeRates(rows);
    // --8.���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);

    // --9.ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
    globalerate.calcGlobalExchangeRate(rows);
    calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);

    // ����Ĭ���ջ��ͻ�------Ҫ�����ù���֮ǰ
    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setRelaReceiveCust(rows);
    
    // ����Ĭ���ջ��ַ
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(rows);

    // 4.���ù���
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 5.�������ͺ�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // ���ñ���Ĭ��ֵ
    BodyDefaultValueRule defrule = new BodyDefaultValueRule(keyValue);
    defrule.setBodyDefValue(rows);

    // --11.ѯ��
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, SaleOrderBVO.CMATERIALVID);
    // --12.������ͬ
    // ��ͬ����
    List<Integer> assRowList = new ArrayList<Integer>();
    List<Integer> calRowList = new ArrayList<Integer>();
    this.filterRows(billform, rows, assRowList, calRowList);
    if (assRowList.size() > 0) {
      // --������ͬ
      Integer[] rowsTemp = assRowList.toArray(new Integer[assRowList.size()]);
      int[] assRows = new int[rowsTemp.length];
      for (int i = 0; i < rowsTemp.length; i++) {
        assRows[i] = rowsTemp[i].intValue();
      }
      AssociateConstractRule asctrule =
          new AssociateConstractRule(cardPanel, m30transvo);
      asctrule.associateCT(assRows);
    }
    if (calRowList.size() > 0) {
      // --û�й���������Ҫ�ò��պ�ͬ�����������������е��۽�����
      Integer[] rowsTemp = calRowList.toArray(new Integer[calRowList.size()]);
      int[] calRows = new int[rowsTemp.length];
      for (int i = 0; i < rowsTemp.length; i++) {
        calRows[i] = rowsTemp[i].intValue();
      }
      calculator.calculate(calRows, SaleOrderBVO.NNUM);
    }
    // 13.ƥ������������
    if (!isunitchg) {
      int[] matchrows = new int[] {
        editrow
      };
      MatchBindLargessRule matchrule = new MatchBindLargessRule(cardPanel, m30transvo);
      matchrule.matchBindLargess(matchrows);
    }
    // --13.����ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // �༭�ͻ������������������̡���Ӧ��
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setMaterials(rows);
    
    // �������ֿ��߼���ֱ��ҵ��ȡ���ֿ� add by jilu for EHP1�ϵ�633 20140703
    if (!this.isDirecttype(keyValue, billform)) {
      // ִ�з��������֯�༭��ʽ��Ϊ�˸��ݷ��������֯�������ֿ�
			this.execEditFormulas(cardPanel,
					new String[] { SaleOrderBVO.CSENDSTOCKORGID }, rows);
    }
    // --14.���ݲֿ����������֯���߷��������֯��ѯ����ί�й�ϵ���Ĭ��������֯
    orgrelarule.setTrafficOrg(rows);
    
    // ���÷�����������
 	this.setCsprofitcenterID(billform, keyValue, rows);

    this.freshQueryOnHandInfoPanel(cardPanel, billform);
  }
  
  /**
	 * ���÷�����������
	 * 
	 * @param billform
	 * @param keyValue
	 * @param rows
	 */
	private void setCsprofitcenterID(SaleOrderBillForm billform,
			IKeyValue keyValue, int[] rows) {
		// ֱ��ҵ�񣬷�����������=������������
		if (this.isDirecttype(keyValue, billform)) {
			int length = rows.length;
			for (int index = 0; index < length; index++) {
				String cprofitcenterid = keyValue.getBodyStringValue(
						rows[index], SaleOrderBVO.CPROFITCENTERID);
				String cprofitcentervid = keyValue.getBodyStringValue(
						rows[index], SaleOrderBVO.CPROFITCENTERVID);
				keyValue.setBodyValue(rows[index],
						SaleOrderBVO.CSPROFITCENTERID, cprofitcenterid);
				keyValue.setBodyValue(rows[index],
						SaleOrderBVO.CSPROFITCENTERVID, cprofitcentervid);
			}
		} else {
			// ��������ȡֵ���򣬷�ֱ��ҵ�����ȡֵ
			SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(
					keyValue);
			profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
					SaleOrderBVO.CSPROFITCENTERID, rows);
		}
	}
  
  /**
   * ִ�����ϱ༭��ʽ dongli2 2013.07.17
   * 
   * @param cardPanel
   * @param rows
   */
  private void execEditFormulas(BillCardPanel cardPanel, String[] key,
			int[] rows) {
		if (rows.length == 1) {
			for (String str : key) {
				// ִ�б༭��ʽ
				cardPanel.getBillModel().execEditFormulaByKey(rows[0], str);
			}
		} else {
			cardPanel.getBillModel().execEditFormulas(-1);
		}
	}
  
  /**
   * �Ƿ�ֱ��ҵ��
   */
  private boolean isDirecttype(IKeyValue keyValue, SaleOrderBillForm billform) {
    String vtrantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    M30TranTypeVO m30trantypevo =
        billform.getM30ClientContext().getTransType(vtrantypecode,
            AppUiContext.getInstance().getPkGroup());
    // ��ֱ��
    if (DirectType.DIRECTTRAN_NO.equalsValue(m30trantypevo.getFdirecttype())) {
      return false;
    }
    return true;
  }

  private void cacheCtMap(SaleOrderBillForm billForm,
      Map<String, CtBusinessVO> mapCtInfo) {
    Map<String, CtBusinessVO> ctMap = billForm.getCtMap();
    if (ctMap == null) {
      billForm.setCtMap(mapCtInfo);
    }
    // ���Ƶ�ǰmap��Ϣ��ctMap
    else {
      ctMap.putAll(mapCtInfo);
    }
  }

  /**
   * @assRowList ��Ҫ������ͬ��
   * @calRowList ��Ҫ���������۽�������
   */
  private void filterRows(SaleOrderBillForm billform, int[] rows,
      List<Integer> assRowList, List<Integer> calRowList) {
    BillCardPanel billCardPanel = billform.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(billCardPanel);
    Map<String, CtBusinessVO> ctMap = billform.getCtMap();
    int length = rows.length;
    String cctmanagebid = null;
    String vsrctype = null;
    for (int i = 0; i < length; i++) {
      vsrctype = keyValue.getBodyStringValue(rows[i], SaleOrderBVO.VSRCTYPE);
      cctmanagebid =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCTMANAGEBID);
      // -- ��Դ��ͬ��
      if (vsrctype != null && CTBillType.SaleDaily.getCode().equals(vsrctype)) {
        if (ctMap != null && ctMap.containsKey(cctmanagebid)) {
          CtBusinessVO busiVO = ctMap.get(cctmanagebid);
          // -- ��Դ��ͬ�������Ͽ����������¹�����ͬ
          if (busiVO != null
              && Ninvctlstyle.MATERIAL.value().equals(busiVO.getNinvctlstyle())) {
            assRowList.add(Integer.valueOf(rows[i]));
          }
          // -- ��Դ��ͬ�������ϻ���������ơ������Ƶģ������϶������������ͬ����Ҫ������������
          else {
            calRowList.add(Integer.valueOf(rows[i]));
          }
        }
        else {
          calRowList.add(Integer.valueOf(rows[i]));
        }
      }
      // -- ��Դ���Ǻ�ͬ�ģ������Ϲ�����ͬ
      else {
        assRowList.add(Integer.valueOf(rows[i]));
      }
    }
  }

  private void freshQueryOnHandInfoPanel(BillCardPanel cardPanel,
      SaleOrderBillForm billform) {

    OnhandPanelAdaptor adaptor = billform.getExtendedPanel();
    if (null == adaptor) {
      return;
    }

    if (!adaptor.isComponentDisplayable()) {
      return;
    }
    int row = cardPanel.getBillTable().getSelectedRow();
    if (row < 0) {
      adaptor.clearData();
      return;
    }
    adaptor.freshData(row);
  }

  private String getMaterialUnitID(String materialvid) {
    if (PubAppTool.isNull(materialvid)) {
      return null;
    }
    String[] pks = new String[] {
      materialvid
    };
    Map<String, String> mapmeas = MaterialPubService.queryMaterialMeasdoc(pks);
    return mapmeas.get(materialvid);
  }

  /**
   * ��ѯ���������֯�����������֯ID��Ӧ����֯ID����������ID��Ĭ��������֯��ֱ�˲�
   * 
   * @param keyValue
   * @param rows
   * @return
   */
  private Map<String, String[]> GetRelationOrg(IKeyValue keyValue, int[] rows) {

    Map<String, String[]> hmRelationOrgid = null;
    // ��֯���ͻ����������͡����ϲ���׼��
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String ccustomerid = keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);

    List<String> alMaterialid = new ArrayList<String>();

    for (int row : rows) {
      String cmaterialid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
    }
    if (alMaterialid.size() == 0) {
      return null;
    }

    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String transtypeID = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // ��ѯ���������֯ID��Ӧ����֯ID����������ID�ͽ��������֯VID��Ӧ����֯VID����������VID
    try {
      // ����������ͷǿգ����ս������ͻ�ȡֱ�˲�

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(transtypeID, ccustomerid, pk_org,
              cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }

  /**
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // ���ÿ��ö�ѡ
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BillItem item = cardPanel.getBodyItem(SOItemKey.CCUSTMATERIALID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    uirefpane.setMultiSelectedEnabled(true);
    // ���տͻ�����
    String customer = keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_CUSTOMER + " ='" + customer + "' or "
        + CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_CUSTOMER + " in  (select " + CustomVO.MAINCUSTOM
        + " from " + CustomVO.getDefaultTableName() + " where "
        + CustomVO.SUBCUSTOM + " = '" + customer + "')");
    // ������֯����
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String pk_group = keyValue.getHeadStringValue(SOItemKey.PK_GROUP);
    wheresql.append(" and ");
    wheresql.append(CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_ORG, new String[] {
      pk_org, pk_group
    });
    uirefpane.setWhereString(wheresql.toString());
  }
}
