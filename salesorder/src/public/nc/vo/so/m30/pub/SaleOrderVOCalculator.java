package nc.vo.so.m30.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.AmountCalculator;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.CacheDataSet;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.calculator.formula.DiscountMnyFormula;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class SaleOrderVOCalculator {

  /**
   * �̳��������۽������VOģ�����ݼ�
   */
  private static class SaleOrderVODataSet extends VODataSetForCal {

    private SaleOrderHVO voHead;

    public SaleOrderVODataSet(SaleOrderHVO voHead, SaleOrderBVO currVO,
        IRelationForItems item) {
      super(currVO, item);
      this.voHead = voHead;
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getCorigcurrencyid();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (SaleOrderHVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  private UFBoolean forcechgprice;

  private UFBoolean forcefixunitrate;

  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  private SaleOrderVO voSaleOrder;

  public SaleOrderVOCalculator(SaleOrderVO voSaleOrder) {
    super();
    this.voSaleOrder = voSaleOrder;
    this.keyValue = new VOKeyValue<SaleOrderVO>(voSaleOrder);

  }

  /**
   * �༭�ֶ�editkey�����row���������۽��
   * 
   * @param row
   * @param editkey
   */
  public void calculate(int row, String editkey) {
    int[] rows = new int[] {
      row
    };
    this.calcNumPriceMny(rows, editkey, false);
  }

  /**
   * �༭�ֶ�editkey�����rows[]���������۽��
   * 
   * @param rows
   * @param editkey
   */
  public void calculate(int[] rows, String editkey) {
    this.calcNumPriceMny(rows, editkey, false);
  }

  /**
   * �༭�ֶ�editkey��ֻ����row�������ֶ�
   * 
   * @param row
   * @param editkey
   */
  public void calculateOnlyNum(int row, String editkey) {
    int[] rows = new int[] {
      row
    };
    this.calcNumPriceMny(rows, editkey, true);
  }

  /**
   * �༭�ֶ�editkey��ֻ����rows[]�������ֶ�
   * 
   * @param row
   * @param editkey
   */
  public void calculateOnlyNum(int[] rows, String editkey) {
    this.calcNumPriceMny(rows, editkey, true);
  }

  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  /**
   * ����ǿ�Ƶ����ۿ�
   * 
   * @param forcechgdis
   */
  public void setChangePrice(UFBoolean forcechgprice) {
    this.forcechgprice = forcechgprice;
  }

  /**
   * ����ǿ�ƹ̶�������
   * 
   * @param forcefix
   */
  public void setForceFixUnitRate(UFBoolean forcefix) {
    this.forcefixunitrate = forcefix;
  }

  /**
   * ���û���Ľ�������VO
   * 
   * @param trantypevo
   */
  public void setTranTypeVO(M30TranTypeVO trantypevo) {
    this.trantypevo = trantypevo;
  }

  private void calcNumPriceMny(int[] rows, String editkey, boolean isnumonly) {
    // �����Ϸ��Լ��
    if (null == rows || rows.length == 0 || PubAppTool.isNull(editkey)) {
      return;
    }
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    String pk_group = AppContext.getInstance().getPkGroup();
    ScaleUtils scale = new ScaleUtils(pk_group);

    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();

    // �ɽ������ͻ�õ����ۻ��ǵ��ۿ�
    boolean isChgPriceOrDiscount = this.isChgPriceOrDiscount();
    cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);

    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    // �����Ż���������ȡ��λ�������Ƿ��ǹ̶������� add by zhangby5 start
    Map<String, UFBoolean> fixedMap = unitrule.isAstAndQtFixedRate(rows);
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    SaleOrderHVO voHead = this.voSaleOrder.getParentVO();
    SaleOrderBVO[] voBodys = this.voSaleOrder.getChildrenVO();
    for (int row : rows) {

      SaleOrderBVO voBody = voBodys[row];
      // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new SaleOrderVODataSet(voHead, voBody, item);
      Calculator tool = new Calculator(data, scale);

      // �����Ƿ��������
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      if (null != this.forcefixunitrate) {
        // �����Ƿ�̶���λ������
        cond.setIsFixNchangerate(this.forcefixunitrate.booleanValue());
        // �Ƿ�̶����۵�λ������
        cond.setIsFixNqtunitrate(this.forcefixunitrate.booleanValue());
      }
      else {
        String cmaterialvid =
            keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
        String castunitid =
            keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
        String cqtunitid =
            keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
        String keycastunitid = cmaterialvid + castunitid;
        String keycqtunitid = cmaterialvid + cqtunitid;
        // �����Ƿ�̶���λ������
        cond.setIsFixNchangerate(fixedMap.get(keycastunitid).booleanValue());
        // �Ƿ�̶����۵�λ������
        cond.setIsFixNqtunitrate(fixedMap.get(keycqtunitid).booleanValue());

      }
      if (isnumonly) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }

      // ������ǰ���
      UFDouble norigtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      UFDouble norigsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
          MathTool.add(norigtaxmny, norigsubmny));
    }

    // �������ϼ���Ϣ
    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();

  }

  /**
   * ֻ����ԭ���ۿ۶� �����������۷�Ʊ�����ⵥ��ʱ�� ��Ϊ���ⵥû���ۿ۶�û�а취β��
   * �ٴ���Ϊ��֮�䴦�����Ǵ���ģ���˰�ϼƲ��ԣ�
   * ������Ҫ�������Ĳ��ڵ�������һ���ۿ۶�
   * 
   * @param rows
   * @param editKey
   */
  public void calculateDiscountmny(int[] rows, String editkey) {
    // �����Ϸ��Լ��
    if (null == rows || rows.length == 0 || PubAppTool.isNull(editkey)) {
      return;
    }
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    IRelationForItems item = new RelationItemForCal();
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = null;
    SaleOrderHVO voHead = this.voSaleOrder.getParentVO();
    SaleOrderBVO[] voBodys = this.voSaleOrder.getChildrenVO();
    String pk_group = voHead.getPk_group();
    ScaleUtils scale = new ScaleUtils(pk_group);

    String taxPriceKey = item.getNqtorigtaxpriceKey();
    String numKey = item.getNqtunitnumKey();
    String summnyKey = item.getNorigtaxmnyKey();
    String discountMnyKey = item.getNorigdiscountKey();
    String curridKey = item.getCorigcurrencyidKey();

    // ԭ��
    String origcur = voHead.getCorigcurrencyid();
    UFDate billdate = voHead.getDbilldate();
    for (int row : rows) {
      SaleOrderBVO voBody = voBodys[row];
      data = new SaleOrderVODataSet(voHead, voBody, item);
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
      formula.setTaxNetPriceKey(item.getNqtorigtaxnetprcKey());
      formula.calculateDiscountMny();
      cachedata.commit();

      // ����
      String pk_currency = voBody.getCcurrencyid();
      UFDouble norigdiscount = voBody.getNorigdiscount();
      UFDouble nexchangerate = voBody.getNexchangerate();
      if (null != norigdiscount) {
        // �ñ��Ҽ�˰�ϼƵľ���
        UFDouble ndiscount =
            AmountCalculator.convertLocalCurreny(norigdiscount, origcur,
                pk_currency, pk_group, billdate, nexchangerate);
        voBody.setNdiscount(ndiscount);
      }
    }
  }

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.trantypevo) {
      String trantypeid =
          this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);

      if (PubAppTool.isNull(trantypeid)) {
        return null;
      }
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      try {
        this.trantypevo = service.queryTranTypeVO(trantypeid);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.trantypevo;
  }

  private boolean isChgPriceOrDiscount() {
    // ���ǿ�Ƶ����ۿۣ�����
    if (null != this.forcechgprice) {
      return this.forcechgprice.booleanValue();
    }
    M30TranTypeVO m30tranvo = this.getTranTypeVO();
    // Ĭ�ϵ����ۿ�
    if (null == m30tranvo) {
      return false;
    }
    Integer fmodifymny = m30tranvo.getFmodifymny();
    return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);

  }

}
