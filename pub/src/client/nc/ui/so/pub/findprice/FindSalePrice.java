package nc.ui.so.pub.findprice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.pub.service.IFindSalePrice;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.price.pub.PriceSysParamContext;
import nc.vo.price.pub.entity.FindPPLimitPriceResultVO;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.price.pub.entity.PromoteLimitPara;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.FretexchangeRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ���۹���ѯ�۴�����
 * 
 * @since 6.0
 * @version 2011-6-13 ����02:14:11
 * @author fengjb
 */
public class FindSalePrice {

  // ��Ƭģ��
  private BillCardPanel cardPanel;

  // �����ļ�
  private IFindPriceConfig config;

  // jilu for �Ƿ����۶���ѯ��
  private boolean isSaleorder = false;

  // ѯ��ʧ����
  private int[] failrows;

  /***
   * ��Ʒ��̯��
   */
  private int[] flargessrows;

  // ѯ�۳ɹ���
  private Map<Integer, FindPriceResultVO> hmSucess =
      new HashMap<Integer, FindPriceResultVO>();

  private IKeyValue keyValue;

  /**
   * ������
   * 
   * @param billCardPanel
   * @param config
   */
  public FindSalePrice(BillCardPanel billCardPanel, IFindPriceConfig config) {
    this.cardPanel = billCardPanel;
    this.keyValue = new CardKeyValue(this.cardPanel);
    this.config = config;
  }

  /**
   * ѯ�����˵���
   * 
   * @param rows
   * @param editkey
   */
  public void forceFindAccPrice(int[] rows, String editkey) {
    // �ж��۸��Ƿ�����
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }
    // �����Ϸ��Լ��
    if (null == rows || rows.length == 0) {
      return;
    }
    // 1.ѯ�۲���,�ж��Ƿ�ѯ��
    Integer askrule = this.config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return;
    }
    // 2.�༭�ֶ��Ƿ񴥷�ѯ��
    if (!this.isTrigFindPrice(editkey)) {
      return;
    }
    // 3.���˳�ѯ����
    int[] askrows = this.filterFindRows(rows);
    if (askrows.length == 0) {
      return;
    }
    // 4.��֯ѯ��VOs
    FindPriceParaVO[] paraVOs = this.getFindPriceParaVOs(askrows);

    // 5.ѯ��
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    FindPriceResultVO[] resultVOs = this.getFindPriceResultVOs(paraVOs, pk_org);
    if (null == resultVOs || resultVOs.length == 0) {
      return;
    }
    // 6.����ѯ�۳ɹ�����ʧ����
    this.splitFindResult(resultVOs, askrows);
    // 7.�ɹ��У���ֵ�����۽�����
    this.setSucessResult();
    // 10.ʧ���У��״�����ʾ��Ϣ
    this.showFailMsg();
  }

  private void setSucessResult() {
    for (Entry<Integer, FindPriceResultVO> entry : this.hmSucess.entrySet()) {
      int row = entry.getKey().intValue();
      FindPriceResultVO resultVO = entry.getValue();

      UFDouble price = resultVO.getPrice();
      String vchangerate =
          this.keyValue.getBodyStringValue(row, SOItemKey.VCHANGERATE);
      // ������=������*������
      UFDouble naccprice = HslParseUtil.hslMultiplyUFDouble(vchangerate, price);
      // �����˵���
      this.keyValue.setBodyValue(row, SOItemKey.NACCPRICE, naccprice);
    }
  }

  /**
   * �༭�ֶκ�ѯ���㷨
   * 
   * @param row
   * @param editkey
   */
  public void findPriceAfterEdit(int row, String editkey) {
    int[] rows = new int[] {
      row
    };
    this.findPrice(rows, editkey, false);
  }

  /**
   * �༭�ֶκ�ѯ���㷨
   * 
   * @param rows
   * @param editkey
   */
  public void findPriceAfterEdit(int[] rows, String editkey) {
    this.findPrice(rows, editkey, false);
  }

  /**
   * ���ѯ�۰�ť��ǿ��ѯ�۷���
   * 
   * @param rows
   */
  public void forceFindPrice(int[] rows) {
    this.findPrice(rows, null, true);
  }

  /**
   * �۸���ɱ༭��������ѯ��resultVO��ֵ�����۽�����
   * 
   * @param row
   *          ѯ�۽��������
   * @param resultVO
   *          ѯ�۽��VO
   * 
   */
  public void setResultAfterPriceFormEdit(int row, FindPriceResultVO resultVO) {
    // 1.���óɹ���
    this.hmSucess.put(Integer.valueOf(row), resultVO);
    // 2.��Ƭ��ֵ
    this.setSucessResult(true);
  }

  private void clearFailRow() {

    String[] clearitems =
        new String[] {
          SOItemKey.NORIGTAXPRICE, SOItemKey.NORIGTAXNETPRICE,
          SOItemKey.NORIGPRICE, SOItemKey.NORIGNETPRICE, SOItemKey.NORIGTAX,
          SOItemKey.NORIGTAXMNY, SOItemKey.NORIGMNY, SOItemKey.NORIGDISCOUNT,

          SOItemKey.NTAXPRICE, SOItemKey.NTAXNETPRICE, SOItemKey.NPRICE,
          SOItemKey.NNETPRICE, SOItemKey.NTAX, SOItemKey.NTAXMNY,
          SOItemKey.NMNY, SOItemKey.NDISCOUNT,

          SOItemKey.NQTORIGTAXPRICE, SOItemKey.NQTORIGTAXNETPRC,
          SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGNETPRICE,

          SOItemKey.NQTTAXPRICE, SOItemKey.NQTTAXNETPRICE, SOItemKey.NQTPRICE,
          SOItemKey.NQTNETPRICE,

          SOItemKey.NASKQTORIGPRICE, SOItemKey.NASKQTORIGNETPRICE,
          SOItemKey.NASKQTORIGTAXPRC, SOItemKey.NASKQTORIGTXNTPRC,
          SOItemKey.NGLOBALMNY, SOItemKey.NGLOBALTAXMNY, SOItemKey.NGROUPMNY,
          SOItemKey.NGROUPTAXMNY,

          SOItemKey.CPRICEPOLICYID, SOItemKey.CPRICEITEMID,
          SOItemKey.CPRICEITEMTABLEID, SOItemKey.CPRICEFORMID,
          SOItemKey.NCALTAXMNY

        };
    for (int row : this.failrows) {
      for (String key : clearitems) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }

    this.config.processAskFailRows(this.failrows);

  }

  /**
   * ������Ҫѯ����
   * 
   * @param rows
   * @return
   */
  private int[] filterFindRows(int[] rows) {
    boolean isLarAsk = this.config.isLargessAskPrice();
    List<Integer> alfindrow = new ArrayList<Integer>();
    List<Integer> flargessrowlist = new ArrayList<Integer>();
    for (int row : rows) {
      // --���Ͽղ�ѯ��
      String marid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(marid)) {
        continue;
      }
      // --���ݲ�����Ʒ�Ƿ�ѯ�۹���
      UFBoolean blargess =
          this.keyValue.getBodyUFBooleanValue(row, SOItemKey.BLARGESSFLAG);
      if (null != blargess && blargess.booleanValue() && !isLarAsk) {
        continue;
      }
      // --��Ʒ��̯�Ĳ�ѯ��
      Integer flargesstypeflag =
          this.keyValue.getBodyIntegerValue(row, SOItemKey.FLARGESSTYPEFLAG);
      if (flargesstypeflag != null
          && (Largesstype.APPORTIONMATERIAL.getIntegerValue().equals(
              flargesstypeflag) || Largesstype.APPORTIONLARGESS
              .getIntegerValue().equals(flargesstypeflag))) {
        flargessrowlist.add(Integer.valueOf(row));
        continue;
      }
      alfindrow.add(Integer.valueOf(row));
    }
    this.flargessrows = this.changeIntegerListToIntArray(flargessrowlist);
    return changeIntegerListToIntArray(alfindrow);
  }

  /**
   * 
   * 
   * @param rows
   * @param editkey
   */
  private void findPrice(int[] rows, String editkey, boolean isforce) {
    // �ж��۸��Ƿ�����
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }
    // �����Ϸ��Լ��
    if (null == rows || rows.length == 0) {
      return;
    }
    // 1.ѯ�۲���,�ж��Ƿ�ѯ��
    Integer askrule = this.config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return;
    }
    // 2.�༭�ֶ��Ƿ񴥷�ѯ��
    if (!isforce && !this.isTrigFindPrice(editkey)) {
      return;
    }
    // 3.���˳�ѯ����
    int[] askrows = this.filterFindRows(rows);
    if (askrows.length == 0) {
      this.showFlargessMsg();
      return;
    }
    // 4.��֯ѯ��VOs
    FindPriceParaVO[] paraVOs = this.getFindPriceParaVOs(askrows);

    // ��������ѯ�۲��� jilu for �㰲
    PromoteLimitPara[] promotelimitparas = this.getPromotelimitparas(askrows);
    // 5.ѯ��
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    FindPPLimitPriceResultVO resultVO =
        this.getFindPriceResultVOs(paraVOs, promotelimitparas, pk_org);
    if (null == resultVO.getResultvos() || resultVO.getResultvos().length == 0) {
      return;
    }
    // 6.����ѯ�۳ɹ�����ʧ����
    // this.splitFindResult(resultVOs, askrows);
    this.splitFindResult(resultVO.getResultvos(), askrows);
    // 7.�ɹ��У���ֵ�����۽�����
    this.setSucessResult(false);
    // 7.1�ɹ��У���ʾ��Ϣ jilu for �㰲
    this.showSucessMsg(resultVO);
    // 8.ʧ���У���յ��۽���ֶ�
    this.clearFailRow();
    // 9.ѯ�ۺ���ݽ��������������ñ༭��
    this.setFindRowEdit();
    // 10.ʧ���У��״�����ʾ��Ϣ
    this.showFailMsg();
  }

  private void showFlargessMsg() {
    if (this.flargessrows == null || flargessrows.length == 0) {
      return;
    }
    StringBuilder flargessMsg = new StringBuilder();
    for (int row : this.flargessrows) {
      String crowno = this.keyValue.getBodyStringValue(row, SOItemKey.CROWNO);
      flargessMsg.append("[");
      flargessMsg.append(crowno);
      flargessMsg.append("]");
      flargessMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0206")/* �� */);
    }
    flargessMsg.deleteCharAt(flargessMsg.length() - 1);
    String flargessMsgForTranslate =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0243", null,
            new String[] {
              flargessMsg.toString()
            })/* �� {0} ��������Ʒ��̯�����ܽ���ѯ�۲����� */;
    MessageDialog.showHintDlg(this.cardPanel, NCLangRes.getInstance()
        .getStrByID("4006004_0", "04006004-0019")/* ��ʾ */,
        flargessMsgForTranslate);

  }

  private void showSucessMsg(FindPPLimitPriceResultVO resultVO) {
    if (!this.isSaleorder) {
      return;
    }
    UFBoolean Errorflag = resultVO.getHasErrorMsg();
    if (Errorflag != null && Errorflag.booleanValue()) {
      MessageDialog.showHintDlg(this.cardPanel, NCLangRes.getInstance()
          .getStrByID("4006004_0", "04006004-0019")/* ��ʾ */, resultVO
          .getErrorMsg());
    }
  }

  private PromoteLimitPara[] getPromotelimitparas(int[] askrows) {
    PromoteLimitPara[] promotelimitparas = new PromoteLimitPara[askrows.length];
    int i = 0;
    for (int row : askrows) {
      promotelimitparas[i] = new PromoteLimitPara();
      // ѯ����ID
      String saleorderbid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSALEORDERBID);
      promotelimitparas[i].setFindpricebillbid(saleorderbid);
      // �������ͱ���
      promotelimitparas[i].setBilltypecode(SOBillType.Order.getCode());
      // ѯ���к�
      String rowno = this.keyValue.getBodyStringValue(row, SaleOrderBVO.CROWNO);
      promotelimitparas[i].setFindpricerowNo(rowno);
      // ѯ���е���Դ��id
      String srcbid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSRCBID);
      promotelimitparas[i].setFindpricesrcbillbid(srcbid);
      // ѯ���е���Դ�������ͱ���
      String srctype =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
      promotelimitparas[i].setSrcbilltypecode(srctype);
      i++;
    }

    return promotelimitparas;
  }

  /**
   * �������������������Ҫѯ�۲���VOs
   * 
   * @param askrows
   * @author ��־ΰ
   * @time 2010-5-31 ����01:42:30
   */
  private FindPriceParaVO[] getFindPriceParaVOs(int[] askrows) {

    FindPriceParaVO[] paraVOs = new FindPriceParaVO[askrows.length];
    // ����
    String pk_group = AppUiContext.getInstance().getPkGroup();
    // ������֯
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    // �ͻ�
    String customer = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    // ���㷽ʽ
    String balancetype =
        this.keyValue.getHeadStringValue(SOItemKey.CBALANCETYPEID);
    // ��������
    String channeltype =
        this.keyValue.getHeadStringValue(SOItemKey.CCHANNELTYPEID);
    // ԭ�ұ���
    String currtype =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    // ��������
    UFDate billdate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
    UFDateTime datetime = null;
    if (null != billdate) {
      datetime = new UFDateTime(billdate.toString());
    }
    // �Ƿ�ѯ������
    UFBoolean ispromote = this.isFindPromotePrice();
    // ���䷽ʽ
    String tranporttype =
        this.keyValue.getHeadStringValue(SOItemKey.CTRANSPORTTYPEID);
    // ��������
    String trantypeid = this.keyValue.getHeadStringValue(SOItemKey.CTRANTYPEID);

    // jilu for �㰲
    String trantypecode =
        this.keyValue.getHeadStringValue(SOItemKey.VTRANTYPECODE);
    String[] billtype = trantypecode.split("-");
    if (SOBillType.Order.getCode().equals(billtype[0])) {
      this.isSaleorder = true;
    }
    // end

    UFBoolean bSA09 = this.getPriceSysPara(saleorg);
    AggregatedValueObject bill = null;
    if (bSA09.booleanValue()) {
      bill = this.config.getBillVO();
    }
    int i = 0;
    for (int row : askrows) {
      paraVOs[i] = new FindPriceParaVO();
      // רΪ����SA09�����ݵ����������������ѯ�ۣ�ʹ�õ��ֶΣ����ο�����ʹ������ֶδ��ݵ���
      if (bSA09.booleanValue()) {
        paraVOs[i].setBill(bill);
      }
      paraVOs[i].setPk_group(pk_group);
      paraVOs[i].setPk_org(saleorg);
      paraVOs[i].setPk_customer(customer);
      paraVOs[i].setPk_balatype(balancetype);
      paraVOs[i].setPk_channeltype(channeltype);
      paraVOs[i].setPk_currtype(currtype);
      paraVOs[i].setTpricedate(datetime);

      UFDouble num =
          this.keyValue.getBodyUFDoubleValue(row, SOItemKey.NQTUNITNUM);
      paraVOs[i].setNnum(null == num ? UFDouble.ONE_DBL : num);

      String materialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      paraVOs[i].setPk_material(materialid);

      String unit = this.keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      paraVOs[i].setPk_unit(unit);
      // ����ѯ�ۼ�ѯ������
      paraVOs[i].setIsFindPromotePrice(ispromote);

      // �Ƿ�ѯ����������
      paraVOs[i].setIsPromoteLimit(isPromoteLimit(row));

      // ���䷽ʽ
      paraVOs[i].setPk_sendtype(tranporttype);
      // ������������
      paraVOs[i].setVsaleorgtype(trantypeid);
      // �����ȼ�
      String qualityleve =
          this.keyValue.getBodyStringValue(row, SOItemKey.CQUALITYLEVELID);
      paraVOs[i].setPk_qualitylevel(qualityleve);
      // �ջ�����
      String receivearea =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVEAREAID);
      paraVOs[i].setPk_areacl(receivearea);
      // �۸���
      String priceitem =
          this.keyValue.getBodyStringValue(row, SOItemKey.CPRICEITEMID);
      paraVOs[i].setPk_pricetype(priceitem);

      // ���ɸ�������
      String vfree1 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE1);
      paraVOs[i].setVfree1(vfree1);
      String vfree2 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE2);
      paraVOs[i].setVfree2(vfree2);
      String vfree3 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE3);
      paraVOs[i].setVfree3(vfree3);
      String vfree4 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE4);
      paraVOs[i].setVfree4(vfree4);
      String vfree5 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE5);
      paraVOs[i].setVfree5(vfree5);
      String vfree6 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE6);
      paraVOs[i].setVfree6(vfree6);
      String vfree7 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE7);
      paraVOs[i].setVfree7(vfree7);
      String vfree8 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE8);
      paraVOs[i].setVfree8(vfree8);
      String vfree9 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE9);
      paraVOs[i].setVfree9(vfree9);
      String vfree10 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE10);
      paraVOs[i].setVfree10(vfree10);
      i++;
    }

    return paraVOs;
  }

  private UFBoolean isPromoteLimit(int row) {
    // ����������۶�����ѯ��������
    if (!this.isSaleorder) {
      return UFBoolean.FALSE;
    }
    UFBoolean blargessflag =
        this.keyValue.getBodyUFBooleanValue(row, SOItemKey.BLARGESSFLAG);
    // ��ȡ�˻������
    FretexchangeRule fretexrule = new FretexchangeRule(this.keyValue);
    Integer fretexchange = fretexrule.getFretexchange(row);
    // ��Ʒ�� �˻���������������������Ϊ��Ʒ�Ҹ�������������������ģʽΪ�˻���
    if ((blargessflag != null && blargessflag.booleanValue())
        || Fretexchange.WITHDRAW.equalsValue(fretexchange)
        || Fretexchange.EXCHANGE.equalsValue(fretexchange)
        ||this.config.isblrgcashflag()||this.config.getSalemode()!=1) {
      return UFBoolean.FALSE;
    }
    return UFBoolean.TRUE;
  }

  private FindPriceResultVO[] getFindPriceResultVOs(FindPriceParaVO[] paraVOs,
      String saleOrg) {

    IFindSalePrice findpricesrv =
        NCLocator.getInstance().lookup(IFindSalePrice.class);
    FindPriceResultVO[] result = null;

    try {
      result = findpricesrv.findPrice(paraVOs, saleOrg);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
    /**
     * PluginExecutor<ISOFindPrice> executor = new
     * PluginExecutor<ISOFindPrice>(ISOFindPrice.class);
     * FindPricePluginExecDelegate delegate = new
     * FindPricePluginExecDelegate(paraVOs, saleOrg); try {
     * executor.exec(delegate); } catch (Exception e) {
     * ExceptionUtils.wrappException(e); } return
     * delegate.getFindPriceResultVOs();
     **/
  }

  private FindPPLimitPriceResultVO getFindPriceResultVOs(
      FindPriceParaVO[] paraVOs, PromoteLimitPara[] promotelimitparas,
      String saleOrg) {

    IFindSalePrice findpricesrv =
        NCLocator.getInstance().lookup(IFindSalePrice.class);
    FindPPLimitPriceResultVO ppLimitresult = null;

    try {
      if (this.isSaleorder) {
        ppLimitresult =
            findpricesrv.findPriceForSO(paraVOs, promotelimitparas, saleOrg);
      }
      else {
        FindPriceResultVO[] results = findpricesrv.findPrice(paraVOs, saleOrg);
        ppLimitresult = new FindPPLimitPriceResultVO();
        ppLimitresult.setResultvos(results);
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ppLimitresult;
  }

  private boolean getIsTax() {

    String pk_group = AppUiContext.getInstance().getPkGroup();
    UFBoolean so23 = null;

    so23 = SCMParameterUtils.getSCM13(pk_group);

    if (null == so23) {
      return false;
    }

    return so23.booleanValue();
  }

  private UFBoolean getPriceSysPara(String saleorg) {
    UFBoolean bSA09 = PriceSysParamContext.getSA09(saleorg);
    return bSA09 == null ? UFBoolean.FALSE : bSA09;
  }

  private int[] getSuccessRows() {
    int[] sucessrows = new int[this.hmSucess.size()];
    int i = 0;
    for (Entry<Integer, FindPriceResultVO> entry : this.hmSucess.entrySet()) {
      sucessrows[i] = entry.getKey().intValue();
      i++;
    }

    return sucessrows;
  }

  private UFBoolean isFindPromotePrice() {

    Integer askqtrule = this.config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(askqtrule)) {
      return UFBoolean.TRUE;
    }
    return UFBoolean.FALSE;
  }

  private boolean isTrigFindPrice(String editkey) {
    // �ж�����Ǽ۸��� �ʹ���ѯ��
    if (editkey.equals(SOItemKey.CPRICEITEMID)) {
      return true;
    }
    // ����ѯ�۴����������ж��Ƿ�ѯ��
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String[] so21 = null;

    so21 = SOSysParaInitUtil.getSO21(pk_org);

    // ѯ�۴�������Ϊ��
    if (null == so21 || so21.length == 0) {
      return false;
    }
    for (String condition : so21) {
      if (editkey.equals(condition)) {
        return true;
      }
    }
    return false;
  }

  private void setFindRowEdit() {
    SOCardEditableSetter cardeditseter = new SOCardEditableSetter();
    int[] successRows = this.getSuccessRows();
    if (!this.config.isModifyAskSucess()) {
      cardeditseter.setBodyPriceMnyEdit(this.cardPanel, successRows, false);
    }
    if (!this.config.isModifyAskFail()) {
      cardeditseter.setBodyPriceMnyEdit(this.cardPanel, this.failrows, false);
    }

  }

  /**
   * ��ѯ�������ۼ۸����õ���Ƭ��,������
   * <ul>
   * <li>��˰����
   * <li>��Ʒ�ۿ�
   * <li>���۲���
   * <li>�۸���
   * <li>��Ŀ��
   * <li>�۸����
   * <li>�۸��������
   * </ul>
   * 
   * @author ��־ΰ
   * @time 2010-5-31 ����01:42:30
   */
  private void setSucessResult(boolean ispriceformedit) {
    // ѯ�۳ɹ������ò����㵥�۽��
    boolean istax = this.getIsTax();
    // ��˰/��˰����
    String pricekey =
        istax ? SOItemKey.NQTORIGTAXPRICE : SOItemKey.NQTORIGPRICE;
    String askpricekey =
        istax ? SOItemKey.NASKQTORIGTAXPRC : SOItemKey.NASKQTORIGPRICE;

    // ��˰/��˰����
    String netpricekey =
        istax ? SOItemKey.NQTORIGTAXNETPRC : SOItemKey.NQTORIGNETPRICE;
    String asknetpricekey =
        istax ? SOItemKey.NASKQTORIGTXNTPRC : SOItemKey.NASKQTORIGNETPRICE;

    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String corigcurrencyid =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    for (Entry<Integer, FindPriceResultVO> entry : this.hmSucess.entrySet()) {
      int row = entry.getKey().intValue();
      FindPriceResultVO resultVO = entry.getValue();
      UFDouble dPrice = resultVO.getPrice();
      // ���ǵ������ۿ�Ӱ�� ���۵�λ���� = ѯ�۵õ����� * �����ۿ�
      UFDouble netprice = resultVO.getNetPrice();
      UFDouble discountrate =
          this.keyValue.getBodyUFDoubleValue(row, SOItemKey.NDISCOUNTRATE);
      if (null != netprice) {
        if (null == discountrate) {
          discountrate = SOConstant.ONEHUNDRED;
        }
      }
      UFDouble nmffileprice =
          this.keyValue.getBodyUFDoubleValue(row, SOItemKey.NMFFILEPRICE);
      netprice = netprice.multiply(discountrate).div(SOConstant.ONEHUNDRED);
      netprice = MathTool.add(netprice, nmffileprice);
      netprice = scale.adjustSoPuPriceScale(netprice, corigcurrencyid);
      this.keyValue.setBodyValue(row, netpricekey, netprice);

      dPrice = scale.adjustSoPuPriceScale(dPrice, corigcurrencyid);
      this.keyValue.setBodyValue(row, pricekey, dPrice);
      // �ֹ��༭�۸���ɲ��ı�ѯ����Ϣ
      if (!ispriceformedit) {

        // ѯ��ԭ�Һ�˰/��˰����
        dPrice = scale.adjustSoPuPriceScale(dPrice, corigcurrencyid);
        this.keyValue.setBodyValue(row, askpricekey, dPrice);
        // ѯ��ԭ�Һ�˰/��˰����
        this.keyValue.setBodyValue(row, asknetpricekey, netprice);
        // ��Ʒ�ۿ�
        this.keyValue.setBodyValue(row, SOItemKey.NITEMDISCOUNTRATE,
            resultVO.getDiscount());
        // ���۲���
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEPOLICYID,
            resultVO.getPricePolicy());
        // �۸���
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEITEMID,
            resultVO.getPriceType());
        // ��Ŀ��
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEITEMTABLEID,
            resultVO.getTariff());
        // �۸����
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEFORMID,
            resultVO.getPk_priceform());
        // �۸�������� jilu for �㰲
        if (this.isSaleorder) {
          // �۸��������
          this.keyValue.setBodyValue(row, SaleOrderBVO.CPRCPROMOTTYPEID,
              resultVO.getPromottype());
          // �����۸����ID
          this.keyValue.setBodyValue(row, SaleOrderBVO.CPROMOTPRICEID,
              resultVO.getPromotePriceID());
        }
        // end
        // �۸�����
        this.keyValue.setBodyValue(row, SaleOrderBVO.CPRICEPROMTACTID,
            resultVO.getMarketact());
      }
    }
    int[] sucessrows = this.getSuccessRows();
    this.config.processAskSucessRows(sucessrows, netpricekey);
  }

  /**
   * �Ƿ���ѯ��ʧ����
   * 
   * @author ��־ΰ
   * @time 2010-5-31 ����01:42:30
   */
  private void showFailMsg() {
    if (!this.config.isShowMsgAskFail() || this.failrows.length == 0) {
      return;
    }

    StringBuilder failMsg = new StringBuilder();

    for (int row : this.failrows) {
      String crowno = this.keyValue.getBodyStringValue(row, SOItemKey.CROWNO);
      failMsg.append("[");
      failMsg.append(crowno);
      failMsg.append("]");
      failMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0206")/* �� */);
    }
    failMsg.deleteCharAt(failMsg.length() - 1);
    String failMsgForTranslate =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0018", null,
            new String[] {
              failMsg.toString()
            })/* �� {0} ��ѯ��ʧ�ܣ� */;
    MessageDialog
        .showHintDlg(
            this.cardPanel,
            NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0019")/* ��ʾ */,
            failMsgForTranslate);

  }

  /**
   * ����ѯ�۽��VO����
   * <ul>
   * <li>VO != null : �ɹ���
   * <li>VO = null : ʧ����
   * </ul>
   * <p>
   * <b>ǰ��SCMѯ�۹��������ѱ�֤ѯ�۵õ�VOs˳��Ϊ����ѯ��VOs˳����ͬ��ѯ�ۺ����ý��VO���к�Ϊ��Ӧƥ�����</b>
   * </p>
   * 
   * @param resultVOs
   * @param rows
   * @author ��־ΰ
   * @time 2010-5-31 ����01:42:30
   */
  private void splitFindResult(FindPriceResultVO[] resultVOs, int[] rows) {
    int i = 0;
    ArrayList<Integer> alFailRow = new ArrayList<Integer>();
    for (int row : rows) {
      if (null == resultVOs[i]) {
        alFailRow.add(Integer.valueOf(row));
      }
      else {
        this.hmSucess.put(Integer.valueOf(row), resultVOs[i]);
      }
      i++;
    }
    this.failrows = this.changeIntegerListToIntArray(alFailRow);
  }

  /**
   * ��Integer��listת��int����
   * 
   * @param integerList
   * @return
   */
  private int[] changeIntegerListToIntArray(List<Integer> integerList) {
    int[] rows = new int[integerList.size()];
    int i = 0;
    for (Integer row : integerList) {
      rows[i] = row.intValue();
      i++;
    }
    return rows;
  }
}
