package nc.ui.so.salequotation.findprice;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.price.pricepolicy.entity.PricePolicyVO;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.FindPriceTrigRule;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.FindPriceParaFactory;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.pub.SalequoCalculator;

/**
 * �۸���� ѯ��
 * 
 * @author chen y yb
 * @since 6.0
 * @time 2010-8-2 ����10:45:43
 */
public class ManagementPriceFinder extends AbstractPriceFinder {

  private static String[] keys = new String[] {
    SalequotationHVO.DQUOTEDATE, SalequotationBVO.PK_QUALITYLEVEL,
    SalequotationBVO.PK_MATERIAL_V, SalequotationBVO.PK_AREACL,
    SalequotationBVO.CASTUNITID, SalequotationBVO.CQTUNITID,
    SalequotationBVO.NASSISTNUM, SalequotationBVO.NNUM,
    SalequotationBVO.NQTNUM, SalequotationBVO.PK_PRICETYPE,
    SalequotationHVO.PK_CURRTYPE, SalequotationHVO.PK_CUSTOMER,
    SalequotationHVO.PK_BALATYPE, SalequotationHVO.CTRANTYPEID,
    SalequotationHVO.PK_CHANNELTYPE, SalequotationHVO.CSENDTYPEID,
    SalequotationBVO.VFREE1, SalequotationBVO.VFREE2, SalequotationBVO.VFREE3,
    SalequotationBVO.VFREE4, SalequotationBVO.VFREE5, SalequotationBVO.VFREE6,
    SalequotationBVO.VFREE7, SalequotationBVO.VFREE8, SalequotationBVO.VFREE9,
    SalequotationBVO.VFREE10
  };

  private IKeyValue keyValue;

  private final M4310TranTypeVO tranTypeVO;

  public ManagementPriceFinder(M4310TranTypeVO tranTypeVO) {
    super();
    this.tranTypeVO = tranTypeVO;
  }

  private String changeKey(String condition) {
    String key = null;
    if (PubAppTool
        .isEqual(FindPriceTrigRule.CBALANCETYPEID.getKey(), condition)) {
      key = SalequotationHVO.PK_BALATYPE;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CCHANNELTYPEID.getKey(),
        condition)) {
      key = SalequotationHVO.PK_CHANNELTYPE;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CCUSTOMERID.getKey(),
        condition)) {
      key = SalequotationHVO.PK_CUSTOMER;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CMATERIALVID.getKey(),
        condition)) {
      key = SalequotationBVO.PK_MATERIAL_V;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CORIGCURRENCYID.getKey(),
        condition)) {
      key = SalequotationHVO.PK_CURRTYPE;
    }
    else if (PubAppTool
        .isEqual(FindPriceTrigRule.CQTUNITID.getKey(), condition)) {
      key = SalequotationBVO.CQTUNITID;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CQUALITYLEVELID.getKey(),
        condition)) {
      key = SalequotationBVO.PK_QUALITYLEVEL;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CRECEIVEAREAID.getKey(),
        condition)) {
      key = SalequotationBVO.PK_AREACL;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CTRANSPORTTYPEID.getKey(),
        condition)) {
      key = SalequotationHVO.CSENDTYPEID;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.CTRANTYPEID.getKey(),
        condition)) {
      key = SalequotationHVO.CTRANTYPEID;
    }
    else if (PubAppTool.isEqual(FindPriceTrigRule.NQTUNITNUM.getKey(),
        condition)) {
      key = SalequotationBVO.NQTNUM;
    }
    else {
      key = condition;
    }

    return key;
  }

  /**
   * ���ѯ�۲���
   * 
   * @param cardPanel
   * @param startRow
   * @param rows
   * @param pk_saleorg
   * @param pk_group
   * @return
   */
  private FindPriceParaVO[] createFindPriceParaVO(BillCardPanel cardPanel,
      int[] rows, String pk_saleorg, String pk_group) {
    AggSalequotationHVO data =
        (AggSalequotationHVO) cardPanel.getBillValueVO(
            AggSalequotationHVO.class.getName(),
            SalequotationHVO.class.getName(), SalequotationBVO.class.getName());
    return FindPriceParaFactory.getInstance().createFindPriceParaVO(data, rows,
        pk_saleorg, pk_group);
  }

  private Map<Integer, FindPriceParaVO> createFindPriceParaVO(
      BillCardPanel cardPanel, String pk_saleorg, String pk_group) {
    AggSalequotationHVO data =
        (AggSalequotationHVO) cardPanel.getBillValueVO(
            AggSalequotationHVO.class.getName(),
            SalequotationHVO.class.getName(), SalequotationBVO.class.getName());
    return FindPriceParaFactory.getInstance().createFindPriceParaVO(data,
        pk_saleorg, pk_group);
  }

  private void fillPriceForPanel(BillCardPanel cardPanel, int[] rows,
      FindPriceResultVO[] priceRetVOs) {
    for (int i = 0; i < rows.length; i++) {
      SalequoCalculator calculator = this.getSalequoCalculator(cardPanel);
      if (priceRetVOs[i] != null) {
        this.fillPriceForPanelWhenSuccessed(cardPanel, priceRetVOs, i, rows[i],
            calculator);
      }
      else {
        this.fillPriceForPanelWhenFailed(cardPanel, rows[i]);
      }
    }
  }

  private void fillPriceForPanelWhenFailed(BillCardPanel cardPanel, int row) {
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.PK_PRICEPOLICY);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.PK_TARIFFDEF);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.PK_PRICETYPE);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.VPRICEDETAIL);

    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGTAXPRICE);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGTAXNETPRC);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGPRICE);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGNETPRICE);

    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGTAXPRICE);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGTAXNETPRICE);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGPRICE);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGNETPRICE);

    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGTAXMNY);
    cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGMNY);
  }

  private void fillPriceForPanelWhenSuccessed(BillCardPanel cardPanel,
      FindPriceResultVO[] priceRetVOs, int i, int row,
      SalequoCalculator calculator) {
    calculator.setIsChgPriceOrDiscount(false);
    // ��˰�۸�
    if (SOSysParaInitUtil.getSO23(this.getPk_group()).booleanValue()) {

      cardPanel.setBodyValueAt(priceRetVOs[i].getPrice(), row,
          SalequotationBVO.NQTORIGTAXPRICE);
      cardPanel.setBodyValueAt(priceRetVOs[i].getNetPrice(), row,
          SalequotationBVO.NQTORIGTAXNETPRC);
      calculator.calculate(new int[] {
        row
      }, SalequotationBVO.NQTORIGTAXNETPRC);
    }
    // ��˰�۸�
    else {
      cardPanel.setBodyValueAt(priceRetVOs[i].getPrice(), row,
          SalequotationBVO.NQTORIGPRICE);
      cardPanel.setBodyValueAt(priceRetVOs[i].getNetPrice(), row,
          SalequotationBVO.NQTORIGNETPRICE);
      calculator.calculate(new int[] {
        row
      }, SalequotationBVO.NQTORIGNETPRICE);
    }
    this.keyValue = new CardKeyValue(cardPanel);
    UFDouble nitemdiscountrate =
        this.keyValue.getBodyUFDoubleValue(row,
            SalequotationBVO.NITEMDISCOUNTRATE);
    nitemdiscountrate =
        CalculatorUtil.div(nitemdiscountrate, new UFDouble(100.0));

    UFDouble ndiscountrate =
        this.keyValue.getBodyUFDoubleValue(row, SalequotationBVO.NDISCOUNTRATE);
    ndiscountrate = CalculatorUtil.div(ndiscountrate, new UFDouble(100.0));

    UFDouble discount =
        CalculatorUtil.multiply(nitemdiscountrate, ndiscountrate);
    int intZkl = MathTool.compareTo(discount, UFDouble.ZERO_DBL);
    if (intZkl != 0) {

      // ����˰����
      UFDouble norignetprice =
          this.keyValue.getBodyUFDoubleValue(row,
              SalequotationBVO.NORIGNETPRICE);
      UFDouble norigprice = CalculatorUtil.div(norignetprice, discount);
      this.keyValue.setBodyValue(row, SalequotationBVO.NORIGPRICE, norigprice);

      // ����˰����
      UFDouble norigtaxnetprice =
          this.keyValue.getBodyUFDoubleValue(row,
              SalequotationBVO.NORIGTAXNETPRICE);
      UFDouble norigtaxprice = CalculatorUtil.div(norigtaxnetprice, discount);
      this.keyValue.setBodyValue(row, SalequotationBVO.NORIGTAXPRICE,
          norigtaxprice);
    }

    PricePolicyVO policyVO = priceRetVOs[i].getPolicyVO();
    if (!VOChecker.isEmpty(policyVO)) {
      cardPanel.setBodyValueAt(policyVO.getPk_pricepolicy(), row,
          SalequotationBVO.PK_PRICEPOLICY);
      cardPanel.setBodyValueAt(policyVO.getPk_tariffdef(), row,
          SalequotationBVO.PK_TARIFFDEF);
      cardPanel.setBodyValueAt(priceRetVOs[i].getPriceType(), row,
          SalequotationBVO.PK_PRICETYPE);
      cardPanel.setBodyValueAt(priceRetVOs[i].getPk_priceform(), row,
          SalequotationBVO.VPRICEDETAIL);
    }

  }

  @Override
  public void findPrice(CardBodyAfterEditEvent e, int rows) {

    // ���û�м��ؼ۸�ģ�飬����
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }

    BillCardPanel cardPanel = e.getBillCardPanel();
    this.keyValue = new CardKeyValue(cardPanel);
    String editKey = e.getKey();

    // �༭����������������ѯ�ۣ��ñ���������Ϊ����������
    if (editKey.equals(SalequotationBVO.NASSISTNUM)
        || editKey.equals(SalequotationBVO.NNUM)) {
      editKey = SalequotationBVO.NQTNUM;
    }
    // �༭��λ����ѯ�ۣ��ñ��۵�λ��Ϊ����������
    else if (editKey.equals(SalequotationBVO.CASTUNITID)) {
      editKey = SalequotationBVO.CQTUNITID;
    }
    if (Arrays.asList(ManagementPriceFinder.keys).contains(editKey)) {
      // �Ƿ�����ѯ�۲���SO21
      boolean ifFindPricebyPara = this.isTrigFindPrice(editKey);
      if (ifFindPricebyPara && this.ifFindMangementPrice(cardPanel)) {
        int[] selectedrows = new int[rows];
        for (int i = 0; i < rows; i++) {
          selectedrows[i] = e.getRow() + i;
        }
        this.findPriceByManual(cardPanel, selectedrows, e.getContext()
            .getPk_org(), e.getContext().getPk_group());
      }
    }
  }

  @Override
  public void findPrice(CardHeadTailAfterEditEvent e) {
    if (e.getBillCardPanel().getBillModel() == null
        || e.getBillCardPanel().getBillModel().getRowCount() == 0) {
      return;
    }

    // ���û�м��ؼ۸�ģ�飬����
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }

    BillCardPanel cardPanel = e.getBillCardPanel();
    this.keyValue = new CardKeyValue(cardPanel);
    String editKey = e.getKey();

    if (Arrays.asList(ManagementPriceFinder.keys).contains(editKey)) {
      // �༭��������Ҫ����ѯ�ۣ��ñ�ͷ�ͻ���Ϊ����������
      if (editKey.equals(SalequotationHVO.CTRANTYPEID)
          || editKey.equals(SalequotationHVO.DQUOTEDATE)) {
        editKey = SalequotationHVO.PK_CUSTOMER;
      }
      // �Ƿ�����ѯ�۲���SO21
      boolean ifFindPricebyPara = this.isTrigFindPrice(editKey);
      if (ifFindPricebyPara && this.ifFindMangementPrice(cardPanel)) {
        Map<Integer, FindPriceParaVO> paraMap =
            this.createFindPriceParaVO(cardPanel, e.getContext().getPk_org(), e
                .getContext().getPk_group());
        if (paraMap.size() == 0) {
          return;
        }
        Set<Entry<Integer, FindPriceParaVO>> paraMapEntrySet =
            paraMap.entrySet();
        FindPriceParaVO[] findPriceParaVOs =
            new FindPriceParaVO[paraMap.size()];
        int[] rows = new int[paraMap.size()];
        int i = 0;
        for (Entry<Integer, FindPriceParaVO> entry : paraMapEntrySet) {
          findPriceParaVOs[i] = entry.getValue();
          rows[i++] = entry.getKey();
        }
        try {
          FindPriceResultVO[] priceRetVOs =
              this.getFindPriceService().findPrice(findPriceParaVOs,
                  e.getContext().getPk_org());
          this.fillPriceForPanel(cardPanel, rows, priceRetVOs);
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
      }
    }
  }

  public void findPriceByManual(BillCardPanel cardPanel, int[] rows,
      String pk_org, String pk_group) {
    FindPriceParaVO[] findPriceParaVOs =
        this.createFindPriceParaVO(cardPanel, rows, pk_org, pk_group);
    FindPriceResultVO[] priceRetVOs;
    try {
      priceRetVOs =
          this.getFindPriceService().findPrice(findPriceParaVOs, pk_org);
      this.fillPriceForPanel(cardPanel, rows, priceRetVOs);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }

  }

  private SalequoCalculator getSalequoCalculator(BillCardPanel cardPanel) {
    SalequoCalculator calculator = new SalequoCalculator(cardPanel);
    // ���õ����۷�ʽ
    boolean isChgPriceOrDiscount = false;
    String modifymny = this.tranTypeVO.getFmodifymny();
    if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
      isChgPriceOrDiscount = true;
    }
    calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    return calculator;
  }

  private boolean ifFindMangementPrice(BillCardPanel cardPanel) {
    boolean flag = true;
    flag &= this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.VTRANTYPE);
    flag &= this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.DQUOTEDATE);
    flag &=
        this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.PK_CUSTOMER);
    flag &=
        this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.PK_CURRTYPE);
    return flag;
  }

  private boolean isTrigFindPrice(String editkey) {
    // �ж�����Ǽ۸��� �ʹ���ѯ��
    if (editkey.equals(SalequotationBVO.PK_PRICETYPE)) {
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

    // �������е�keyת��Ϊ���۵���key
    String[] sqKeys = new String[so21.length];
    int i = 0;
    for (String condition : so21) {
      sqKeys[i] = this.changeKey(condition);
      i++;
    }

    for (String condition : sqKeys) {
      if (editkey.equals(condition)) {
        return true;
      }
    }

    return false;
  }

  public void setResultAfterPriceFormEdit(BillCardPanel cardPanel, int[] rows,
      FindPriceResultVO[] priceRetVOs) {
    this.fillPriceForPanel(cardPanel, rows, priceRetVOs);
  }

}
