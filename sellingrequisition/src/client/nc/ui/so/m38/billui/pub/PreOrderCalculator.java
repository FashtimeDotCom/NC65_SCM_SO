package nc.ui.so.m38.billui.pub;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.rule.HeadTotalCalculateRule;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ����Ԥ����ǰ̨�������۽�������
 * 
 * @since 6.0
 * @version 2011-6-13 ����02:04:15
 * @author fengjb
 */
public class PreOrderCalculator {
  /**
   * �̳��������۽�����Ĺ�����Ƭģ�����ݼ�
   */
  private static class PreOrderCarDataSet extends BillCardPanelDataSet {
    public PreOrderCarDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(PreOrderHVO.CORIGCURRENCYID).getValueObject();
      return value;
    }

    /** ��ü��� */
    @Override
    public String getPk_group() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(PreOrderHVO.PK_GROUP).getValueObject();
      return value;
    }

    @Override
    public boolean hasItemKey(String key) {
      if (PreOrderHVO.CORIGCURRENCYID.equals(key)
          || PreOrderHVO.PK_GROUP.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  private static final String[] STRNEEDCALKEY = new String[] {
    // ��������������������
    PreOrderBVO.NASTNUM,
    PreOrderBVO.NNUM,
    PreOrderBVO.VCHANGERATE,
    // ��λ
    PreOrderBVO.CASTUNITID,
    // ���۵�λ���������ۻ����ʡ�˰��
    PreOrderBVO.NQTUNITNUM,
    PreOrderBVO.VQTUNITRATE,
    PreOrderBVO.NTAXRATE,
    // �����ۿۡ���Ʒ�ۿۡ�����˰���ۡ�����˰����
    PreOrderBVO.NDISCOUNTRATE, PreOrderBVO.NITEMDISCOUNTRATE,
    PreOrderBVO.NORIGTAXPRICE,
    PreOrderBVO.NORIGPRICE,
    // ����˰���ۡ�����˰���ۡ���˰����
    PreOrderBVO.NORIGTAXNETPRICE, PreOrderBVO.NORIGNETPRICE,
    PreOrderBVO.NQTORIGTAXPRICE,
    // ��˰���ۡ���˰���ۡ���˰����
    PreOrderBVO.NQTORIGPRICE, PreOrderBVO.NQTORIGTAXNETPRC,
    PreOrderBVO.NQTORIGNETPRICE,

    // TODO ���� 2012-2-17 ɾ��ԭ��˰���ֶ�
    // PreOrderBVO.NORIGTAX

    // ˰�������˰�����Ҽ�˰�ϼ�
    PreOrderBVO.NTAX, PreOrderBVO.NMNY, PreOrderBVO.NTAXMNY,
    // ��˰���
    PreOrderBVO.NCALTAXMNY,
    // ��˰����˰�ϼ�
    PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGTAXMNY,
    // �ۿ۶�۱�����
    PreOrderBVO.NORIGDISCOUNT, PreOrderBVO.NEXCHANGERATE,
    // ���ű�λ�һ��ʡ�ȫ�ֱ�λ�һ���
    PreOrderBVO.NGROUPEXCHGRATE, PreOrderBVO.NGLOBALEXCHGRATE
  };

  private BillCardPanel cardpanel;

  private UFBoolean forechgprice;

  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private M38TranTypeVO trantypevo;

  /**
   * PreOrderCalculator �Ĺ�����
   * 
   * @param cardpanel
   */
  public PreOrderCalculator(BillCardPanel cardpanel) {
    this.cardpanel = cardpanel;
    this.keyValue = new CardKeyValue(cardpanel);
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

  /**
   * ���������ͷ����仯�󴥷����۽���㷨
   * 
   * @param rows �������ͱ仯���к�����
   */
  public void calculatorBuysell(int[] rows) {
    if (null != rows && rows.length > 0) {
      if (this.isTaxPrior()) {
        this.calculate(rows, PreOrderBVO.NQTORIGTAXPRICE);
      }
      else {
        this.calculate(rows, PreOrderBVO.NQTORIGPRICE);
      }
    }
  }

  /**
   * ����ǿ�Ƶ����ۿ�
   * 
   * @param forcechgdis
   */
  public void setChangePrice(UFBoolean forechgprice) {
    this.forechgprice = forechgprice;
  }

  /**
   * ���û���Ľ�������VO
   * 
   * @param trantypevo
   */
  public void setTranTypeVO(M38TranTypeVO trantypevo) {
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

    // 2. ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();

    // �ɽ������ͻ�õ����ۻ��ǵ��ۿ�
    boolean isChgPriceOrDiscount = this.isChgPriceOrDiscount();
    cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);

    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    for (int row : rows) {
      // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new PreOrderCarDataSet(this.cardpanel, row, item);
      Calculator tool = new Calculator(data, scale);

      // �����Ƿ���
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));
      if (isnumonly) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }
    }

    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();
  }

  private Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : PreOrderCalculator.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private M38TranTypeVO getTranTypeVO() {

    if (null == this.trantypevo) {
      String trantypeid =
          this.keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
      if (PubAppTool.isNull(trantypeid)) {
        return null;
      }
      IM38TranTypeService srv =
          NCLocator.getInstance().lookup(IM38TranTypeService.class);
      try {
        this.trantypevo = srv.queryTranTypeVO(trantypeid);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.trantypevo;
  }

  private boolean isChgPriceOrDiscount() {

    // �������õ�ǿ�Ƶ�����
    if (null != this.forechgprice) {
      return this.forechgprice.booleanValue();
    }
    M38TranTypeVO m38tranvo = this.getTranTypeVO();
    // Ĭ�ϵ����ۿ�
    if (null == m38tranvo) {
      return false;
    }
    Integer fmodifymny = m38tranvo.getFmodifymny();
    return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);
  }

  /**
   * �жϺ�˰���ۡ���˰���۵����ȼ����Ա㴥�����۽���㷨
   */
  private boolean isTaxPrior() {
    String pk_group = AppUiContext.getInstance().getPkGroup();
    UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }
}
