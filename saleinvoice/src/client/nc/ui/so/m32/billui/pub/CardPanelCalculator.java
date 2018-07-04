package nc.ui.so.m32.billui.pub;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.UnitChangeRateRule;
import nc.vo.so.m32.util.CalculatorUtil;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ���۷�Ʊ��Ƭ���۽���㷨
 * 
 * @since 6.3
 * @version 2012-12-21 ����01:01:01
 * @author yaogj
 */
public class CardPanelCalculator {

  private UFBoolean isforetax;

  /**
   * �̳��������۽�����Ĺ�����Ƭģ�����ݼ�
   */
  private static class SaleInvoiceCarDataSet extends BillCardPanelDataSet {

    /**
     * SaleInvoiceCarDataSet �Ĺ�����
     * 
     * @param cardPanel
     * @param row
     * @param item
     */
    public SaleInvoiceCarDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /**
     * ��Ʊ��λ�Ҵ洢�ڱ�ͷ���ӱ�ͷ���
     */
    @Override
    public String getCcurrencyid() {

      String value = null;
      BillItem orgcurencyitem =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CCURRENCYID);
      if (null != orgcurencyitem) {
        Object objorgcurency = orgcurencyitem.getValueObject();
        value = ValueUtils.getString(objorgcurency);
      }
      return value;
    }

    /**
     * ��Ʊԭ�Ҵ洢�ڱ�ͷ���ӱ�ͷ���
     */
    @Override
    public String getCorigcurrencyid() {
      String value = null;
      BillItem orgcurencyitem =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CORIGCURRENCYID);
      if (null != orgcurencyitem) {
        Object objorgcurency = orgcurencyitem.getValueObject();
        value = ValueUtils.getString(objorgcurency);
      }
      return value;
    }

    /**
     * ��Ʊ���۱��ֵ���ԭ�ұ���
     */
    @Override
    public String getCqtcurrencyid() {
      return this.getCorigcurrencyid();
    }

    /**
     * ��Ʊ�����ۿ� = ��Ʊ�����ۿ�*��Ʒ�ۿ�/100
     */
    @Override
    public UFDouble getNdiscountrate() {
      UFDouble disrate = super.getNdiscountrate();

      UFDouble itemdisrate =
          ValueUtils.getUFDouble(this
              .getAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE));
      itemdisrate = itemdisrate == null ? UFDouble.ZERO_DBL : itemdisrate;
      disrate = disrate.multiply(itemdisrate).div(SOConstant.ONEHUNDRED);
      return disrate;
    }

    /**
     * �۱����ʴӱ�ͷ��ȡ
     */
    @Override
    public UFDouble getNexchangerate() {
      Object obj =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NEXCHANGERATE)
              .getValueObject();
      UFDouble value = ValueUtils.getUFDouble(obj);
      return value;
    }

    /**
     * ȫ���۱����ʴӱ�ͷ��ȡ
     */
    @Override
    public UFDouble getNglobalexchgrate() {
      Object obj =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGLOBALEXCHGRATE)
              .getValueObject();
      UFDouble value = ValueUtils.getUFDouble(obj);
      return value;
    }

    /**
     * ȫ���۱����ʴӱ�ͷ��ȡ
     */
    @Override
    public UFDouble getNgroupexchgrate() {
      Object obj =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGROUPEXCHGRATE)
              .getValueObject();
      UFDouble value = ValueUtils.getUFDouble(obj);
      return value;
    }

    /**
     * ���Ŵӱ�ͷ��ȡ
     */
    @Override
    public String getPk_group() {
      String value = null;
      BillItem groupitem =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.PK_GROUP);
      if (null != groupitem) {
        Object objorgcurency = groupitem.getValueObject();
        value = ValueUtils.getString(objorgcurency);
      }
      return value;
    }

    /**
     * ����Ҫ�ж��Ƿ��ڱ�ͷ��Ҫ�ж��Ƿ��ڱ���
     */
    @Override
    public boolean hasItemKey(String key) {
      if (CalculatorUtil.getInstance().getAtHeadKey().contains(key)) {
        BillItem headItem = this.getBillCardPanel().getHeadItem(key);
        return headItem != null;
      }
      return super.hasItemKey(key);
    }

    /**
     * ���������ۿ�ֵ
     */
    @Override
    public void setNdiscountrate(UFDouble value) {
      // ���ڷ�Ʊ�����������ۿۡ���Ʒ�ۿ�,�ʲ������������ۿ�ֵ
    }
  }

  private BillCardPanel cardpanel;

  /**
   * SaleInvoiceCalculator �Ĺ�����
   * 
   * @param cardpanel
   */
  public CardPanelCalculator(BillCardPanel cardpanel) {
    this.cardpanel = cardpanel;
  }

  /**
   * ����
   * 
   * @param row ��
   * @param editKey �޸ĵ��ֶ�
   */
  public void calculate(int row, String editKey) {
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // ��Ʒ�ۿ��ֶ���Ϊ����Ʊ�ۿۡ�
    item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);

    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = null;
    Calculator tool = null;
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    CardKeyValue keyValue = new CardKeyValue(this.cardpanel);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    // ���õ����۷�ʽ���ۿ�
    String trantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    boolean ischgprice =
        CalculatorUtil.getInstance().getChgPriceOrDiscount(pk_group,
            trantypecode);
    cond.setIsChgPriceOrDiscount(ischgprice);

    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
    cond.setInternational(buysellrule.isHeadBuysellFlagOut());
    // 3.����ÿ������
    UnitChangeRateRule rule = new UnitChangeRateRule(keyValue);

    // ����ҵ��λ�Ƿ�̶�������
    cond.setIsFixNchangerate(rule.isAstFixedRate(row));
    // ���ñ��۵�λ�Ƿ�̶�������
    cond.setIsFixNqtunitrate(rule.isQtFixedRate(row));

    data = new SaleInvoiceCarDataSet(this.cardpanel, row, item);
    tool = new Calculator(data, scale);
    // �������� cond Ϊ����ʱ�Ĳ�������
    tool.calculate(cond, editKey);

    // �������۽��༭���ؽ�ǰ����
    // ���ǰ��� = ��˰�ϼ� + ���ó�ֽ��
    UFDouble taxmny =
        keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);

    UFDouble origarsubmny =
        keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
    UFDouble nbfsubmny = MathTool.add(taxmny, origarsubmny);
    keyValue.setBodyValue(row, "nbforigsubmny", nbfsubmny);

  }

  /**
   * ���۷�Ʊ��Ƭ�����������۽������
   * 
   * @param rows
   * @param editKey
   */
  public void calculate(int[] rows, String editKey) {
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // ��Ʒ�ۿ��ֶ���Ϊ����Ʊ�ۿۡ�
    item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);

    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = null;
    Calculator tool = null;
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    CardKeyValue keyValue = new CardKeyValue(this.cardpanel);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    if (null != this.isforetax) {
      cond.setIsTaxOrNet(this.isforetax.booleanValue());
    }
    // ���õ����۷�ʽ���ۿ�
    String trantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    boolean ischgprice =
        CalculatorUtil.getInstance().getChgPriceOrDiscount(pk_group,
            trantypecode);
    cond.setIsChgPriceOrDiscount(ischgprice);

    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
    cond.setInternational(buysellrule.isHeadBuysellFlagOut());
    // 3.����ÿ������
    UnitChangeRateRule rule = new UnitChangeRateRule(keyValue);
    for (int row : rows) {
      // ����ҵ��λ�Ƿ�̶�������
      cond.setIsFixNchangerate(rule.isAstFixedRate(row));
      // ���ñ��۵�λ�Ƿ�̶�������
      cond.setIsFixNqtunitrate(rule.isQtFixedRate(row));

      data = new SaleInvoiceCarDataSet(this.cardpanel, row, item);
      tool = new Calculator(data, scale);
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, editKey);

      // �������۽��༭���ؽ�ǰ����
      // ���ǰ��� = ��˰�ϼ� + ���ó�ֽ��
      UFDouble taxmny =
          keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);

      UFDouble origarsubmny =
          keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
      UFDouble nbfsubmny = MathTool.add(taxmny, origarsubmny);
      keyValue.setBodyValue(row, "nbforigsubmny", nbfsubmny);
    }
  }

  /**
   * ��������������������������С�
   * <p>
   * <b>����˵��</b>
   * 
   * @param editKey
   *          <p>
   * @author ��ӱ�
   * @time 2010-5-6 ����08:26:38
   */
  public void calculateAll(String editKey) {
    // ��������
    int rowCount = this.cardpanel.getRowCount();

    int[] editRows = new int[rowCount];
    for (int i = 0; i < rowCount; i++) {
      editRows[i] = i;
    }

    this.calculate(editRows, editKey);
  }

  /**
   * ����ǿ�ƺ�˰���ȱ�־
   * 
   * @param forcetax
   */
  public void setForceTaxPrior(UFBoolean forcetax) {
    this.isforetax = forcetax;
  }
}
