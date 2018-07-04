package nc.ui.so.m30.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

/**
 * ���۶����������۽�������
 * 
 * @since 6.0
 * @version 2011-6-13 ����02:36:21
 * @author fengjb
 */
public class SaleOrderCalculator {

  /**
   * �̳��������۽�����Ĺ�����Ƭģ�����ݼ�
   */
  private static class SaleOrderBillCardPanelDataSet extends
      BillCardPanelDataSet {

    public SaleOrderBillCardPanelDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(SaleOrderHVO.CORIGCURRENCYID).getValueObject();
      return value;
    }

    @Override
    public boolean hasItemKey(String key) {
      if (SaleOrderHVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  private BillCardPanel cardPanel;

  private UFBoolean forcechgprice;

  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  public SaleOrderCalculator(BillCardPanel cardPanel) {
    super();
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);

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
    ScaleUtils scale = UIScaleUtils.getScaleUtils();

    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    // �ɽ������ͻ�õ����ۻ��ǵ��ۿ�
    //����ܲ �޸�For���� û�е��ۿ۵��������ֱ�ӵ�����
    boolean isChgPriceOrDiscount = Boolean.TRUE;//this.isChgPriceOrDiscount();
    cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    //
    //����ܲ ��2018-06-22 ��˰���� ���ݷǸ��������Ҫ��
    //���ݰ��ֹ�ó����Ҫ������λԭ����˰���ۡ�����λԭ����˰���ۡ�����λԭ����˰����
    cond.setIsTaxOrNet(false);

    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    // �����Ż���������ȡ��λ�������Ƿ��ǹ̶������� add by zhangby5 start
    Map<String, UFBoolean> fixedMap = unitrule.isAstAndQtFixedRate(rows);
    
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    for (int row : rows) {
      // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data =
          new SaleOrderBillCardPanelDataSet(this.cardPanel, row, item);
      Calculator tool = new Calculator(data, scale);
      // �����Ƿ��������
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      String cmaterialvid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String castunitid =
          keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
      String cqtunitid = keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      String keycastunitid = cmaterialvid + castunitid;
      String keycqtunitid = cmaterialvid + cqtunitid;
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(fixedMap.get(keycastunitid).booleanValue());
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(fixedMap.get(keycqtunitid).booleanValue());
      
      // zhangby5 end
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

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.trantypevo) {
      String trantypecode =
          this.keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      String pk_group = AppContext.getInstance().getPkGroup();
      if (PubAppTool.isNull(trantypecode)) {
        return null;
      }
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      try {
        this.trantypevo = service.queryTranType(pk_group, trantypecode);
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
