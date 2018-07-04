package nc.vo.so.m32.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.AmountCalculator;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.CacheDataSet;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.calculator.formula.DiscountMnyFormula;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.UnitChangeRateRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;

/**
 * 
 * @since 6.0
 * @version 2011-10-8 ����04:40:00
 * @author ô��
 */
public class SaleInvoiceVOCalculator {

  /**
   * �̳��������۽�����Ĺ�����Ƭģ�����ݼ�
   */
  private static class SaleInvoiceVODataSet extends VODataSetForCal {

    private SaleInvoiceHVO voHead;

    /**
     * SaleInvoiceCarDataSet �Ĺ�����
     * 
     * @param cardPanel
     * @param row
     * @param item
     */
    public SaleInvoiceVODataSet(SaleInvoiceHVO voHead, SaleInvoiceBVO currVO,
        IRelationForItems item) {
      super(currVO, item);
      this.voHead = voHead;
    }

    /**
     * ��Ʊ��λ�Ҵ洢�ڱ�ͷ���ӱ�ͷ���
     */
    @Override
    public String getCcurrencyid() {
      return this.voHead.getCcurrencyid();
    }

    /**
     * ��Ʊԭ�Ҵ洢�ڱ�ͷ���ӱ�ͷ���
     */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getCorigcurrencyid();
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
      // NCM_Begin_quyt_����ҩҵ_���ﷵ�ص��ǿգ����ｫ��ȡֵΪ0_20150928
      UFDouble disrate =
          super.getNdiscountrate() == null ? UFDouble.ZERO_DBL : super
              .getNdiscountrate();
      // END

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
      return this.voHead.getNexchangerate();
    }

    /**
     * ȫ���۱����ʴӱ�ͷ��ȡ
     */
    @Override
    public UFDouble getNglobalexchgrate() {
      return this.voHead.getNglobalexchgrate();
    }

    /**
     * ȫ���۱����ʴӱ�ͷ��ȡ
     */
    @Override
    public UFDouble getNgroupexchgrate() {
      return this.voHead.getNgroupexchgrate();
    }

    /**
     * ���Ŵӱ�ͷ��ȡ
     */
    @Override
    public String getPk_group() {
      return this.voHead.getPk_group();
    }

    /**
     * ����Ҫ�ж��Ƿ��ڱ�ͷ��Ҫ�ж��Ƿ��ڱ���
     */
    @Override
    public boolean hasItemKey(String key) {
      if (CalculatorUtil.getInstance().getAtHeadKey().contains(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }

    /**
     * ���õ�Ʒ�ۿ�ֵ
     */
    @Override
    public void setNdiscountrate(UFDouble value) {
      // ���ڷ�Ʊ�����������ۿۡ���Ʒ�ۿ�,�ʲ������������ۿ�ֵ
    }
  }

  private UFBoolean isforetax;

  // �Ƿ�̶��������Ƿ�������ϲ���
  private UFBoolean forcefixunitrate;

  private IRelationForItems item;

  // ��ƱVO
  private SaleInvoiceVO voInvoice;

  public SaleInvoiceVOCalculator() {
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    this.item = new RelationItemForCal();

    // ��Ʒ�ۿ��ֶ���Ϊ����Ʊ�ۿۡ�
    this.item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);
  }

  /**
   * SaleInvoiceCalculator �Ĺ�����
   * 
   * @param cardpanel
   */
  public SaleInvoiceVOCalculator(SaleInvoiceVO voInvoice) {
    this.voInvoice = voInvoice;
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    this.item = new RelationItemForCal();

    // ��Ʒ�ۿ��ֶ���Ϊ����Ʊ�ۿۡ�
    this.item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);
  }

  /**
   * ����row�е��������۽������
   * 
   * @param row
   * @param editKey
   */
  public void calculate(int row, String editKey) {
    Integer[] rows = new Integer[] {
      Integer.valueOf(row)
    };
    this.calculate(rows, editKey);
  }

  /**
   * �����������������۷�Ʊ��Ƭ�����������۽�����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param cardpanel
   * @param rows
   * @param editKey
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����03:14:20
   */
  public void calculate(Integer[] rows, String editKey) {
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }

    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = null;
    Calculator tool = null;
    IKeyValue keyValue = new VOKeyValue<SaleInvoiceVO>(this.voInvoice);
    String pk_group = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    ScaleUtils scale = new ScaleUtils(pk_group);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    if (null != this.isforetax) {
      cond.setIsTaxOrNet(this.isforetax.booleanValue());
    }
    // ���õ����۷�ʽ���ۿ�
    String trantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    boolean ischgprice =
        CalculatorUtil.getInstance().getChgPriceOrDiscount(pk_group,
            trantypecode);
    cond.setIsChgPriceOrDiscount(ischgprice);
    // �����Ƿ���
    SOBuysellTriaRule buyselrule = new SOBuysellTriaRule(keyValue);
    cond.setInternational(buyselrule.isHeadBuysellFlagOut());

    // 3.����ÿ������
    UnitChangeRateRule rule = new UnitChangeRateRule(keyValue);
    SaleInvoiceHVO voHead = this.voInvoice.getParentVO();
    SaleInvoiceBVO voBody = null;
    for (Integer row : rows) {
      int irow = row.intValue();
      voBody = this.voInvoice.getChildrenVO()[irow];
      if (null != this.forcefixunitrate) {
        // ����ҵ��λ�Ƿ�̶�������
        cond.setIsFixNchangerate(this.forcefixunitrate.booleanValue());
        // ���ñ��۵�λ�Ƿ�̶�������
        cond.setIsFixNqtunitrate(this.forcefixunitrate.booleanValue());
      }
      else {
        // ����ҵ��λ�Ƿ�̶�������
        cond.setIsFixNchangerate(rule.isAstFixedRate(irow));
        // ���ñ��۵�λ�Ƿ�̶�������
        cond.setIsFixNqtunitrate(rule.isQtFixedRate(irow));
      }
      data = new SaleInvoiceVODataSet(voHead, voBody, this.item);
      tool = new Calculator(data, scale);
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, editKey);

      // �������۽��༭���ؽ�ǰ����
      // ���ǰ��� = ��˰�ϼ� + �ϲ���Ʊ���
      UFDouble taxmny =
          keyValue.getBodyUFDoubleValue(irow, SaleInvoiceBVO.NORIGTAXMNY);

      UFDouble origarsubmny =
          keyValue.getBodyUFDoubleValue(irow, SaleInvoiceBVO.NORIGSUBMNY);
      UFDouble nbfsubmny = MathTool.add(taxmny, origarsubmny);
      keyValue.setBodyValue(irow, "nbforigsubmny", nbfsubmny);
    }

  }

  public void calculateAll(String editKey) {
    int bodylen = this.voInvoice.getChildrenVO().length;
    Integer[] rows = new Integer[bodylen];
    for (int i = 0; i < bodylen; i++) {
      rows[i] = Integer.valueOf(i);
    }
    this.calculate(rows, editKey);
  }

  /**
   * ֻ����ԭ���ۿ۶� �����������۷�Ʊ�����ⵥ��ʱ�� ��Ϊ���ⵥû���ۿ۶�û�а취β��
   * �ٴ���Ϊ��֮�䴦�����Ǵ���ģ���˰�ϼƲ��ԣ�
   * ������Ҫ�������Ĳ��ڵ�������һ���ۿ۶�
   * 
   * @param rows
   * @param editKey
   */
  public void calculateDiscountmny(Integer[] rows, String editKey) {
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = null;
    SaleInvoiceHVO voHead = this.voInvoice.getParentVO();
    SaleInvoiceBVO voBody = null;
    String pk_group = voHead.getPk_group();
    ScaleUtils scale = new ScaleUtils(pk_group);
    // �۱�����
    UFDouble nexchangerate = voHead.getNexchangerate();
    String taxPriceKey = this.item.getNqtorigtaxpriceKey();
    String numKey = this.item.getNqtunitnumKey();
    String summnyKey = this.item.getNorigtaxmnyKey();
    String discountMnyKey = this.item.getNorigdiscountKey();
    String curridKey = this.item.getCorigcurrencyidKey();

    String origcurr = voHead.getCorigcurrencyid();
    String pk_currency = voHead.getCcurrencyid();
    UFDate dbilldate = voHead.getDbilldate();
    for (Integer row : rows) {
      int irow = row.intValue();
      voBody = this.voInvoice.getChildrenVO()[irow];
      data = new SaleInvoiceVODataSet(voHead, voBody, this.item);

      CacheDataSet cachedata = new CacheDataSet(data);
      Object currid = cachedata.getAttributeValue(curridKey);
      boolean flag =
          data.hasItemKey(discountMnyKey) && data.hasItemKey(summnyKey)
              && data.hasItemKey(numKey) && currid != null;
      if (!flag) {
        return;
      }
      DiscountMnyFormula formula =
          new DiscountMnyFormula(cachedata, scale, taxPriceKey, numKey,
              summnyKey, discountMnyKey, curridKey);
      // �޸ļ�˰�ϼƲ���Ҫ��ԭ�Һ�˰����
      if (editKey.equals(SOItemKey.NORIGTAXMNY)) {
        formula.setTaxNetPriceKey(this.item.getNqtorigtaxnetprcKey());
      }
      formula.calculateDiscountMny();
      cachedata.commit();

      UFDouble norigdiscount = voBody.getNorigdiscount();
      if (null != norigdiscount) {
        if (origcurr.equals(pk_currency)) {
          voBody.setNdiscount(norigdiscount);
        }
        else {
          // �ñ��Ҽ�˰�ϼƵľ���
          UFDouble ndiscount =
              AmountCalculator.convertLocalCurreny(norigdiscount, origcurr,
                  pk_currency, pk_group, dbilldate, nexchangerate);
          voBody.setNdiscount(ndiscount);
        }

      }
    }
  }

  public void setForcefixunitrate(UFBoolean forcefixunitrate) {
    this.forcefixunitrate = forcefixunitrate;
  }

  public void setVoInvoice(SaleInvoiceVO voInvoice) {
    this.voInvoice = voInvoice;
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
