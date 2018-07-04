package nc.vo.so.m30.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.ViewKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class SaleOrderViewCalculator {

  /**
   * �̳��������۽������VOģ�����ݼ�
   */
  private static class SaleOrderViewDataSet extends VODataSetForCal {

    private SaleOrderBVO voBody;

    private SaleOrderHVO voHead;

    public SaleOrderViewDataSet(SaleOrderHVO voHead, SaleOrderBVO voBody,
        IRelationForItems item) {
      super(voBody, item);
      this.voHead = voHead;
      this.voBody = voBody;
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getCorigcurrencyid();
    }

    @Override
    public String getPk_org() {
      String value = this.voHead.getPk_org();
      if (PubAppTool.isNull(value)) {
        value = this.voBody.getPk_org();
      }
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

  private UFBoolean forcechgprice;

  private Set<String> hsNeedCalKey;

  private Map<String, M30TranTypeVO> maptrantypevo =
      new HashMap<String, M30TranTypeVO>();

  private SaleOrderViewVO[] viewSaleOrder;

  public SaleOrderViewCalculator(SaleOrderViewVO[] viewSaleOrder) {
    super();
    this.viewSaleOrder = viewSaleOrder;

  }

  /**
   * �༭�ֶ�editkey�����rows[]���������۽��
   * 
   * @param rows
   * @param editkey
   */
  public void calculate(String editkey) {
    this.calcNumPriceMny(editkey, false);
  }

  /**
   * �༭�ֶ�editkey��ֻ����rows[]�������ֶ�
   * 
   * @param row
   * @param editkey
   */
  public void calculateOnlyNum(String editkey) {
    this.calcNumPriceMny(editkey, true);
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
  public void setChangePrice(UFBoolean bforcechgprice) {
    this.forcechgprice = bforcechgprice;
  }

  private void calcNumPriceMny(String editkey, boolean isnumonly) {
    // �����Ϸ��Լ��
    if (null == this.viewSaleOrder || this.viewSaleOrder.length == 0
        || PubAppTool.isNull(editkey)) {
      return;
    }

    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    String pk_group = BSContext.getInstance().getGroupID();
    ScaleUtils scale = new ScaleUtils(pk_group);
    Condition cond = SOCalConditionRule.getCondition();

    for (SaleOrderViewVO view : this.viewSaleOrder) {

      SaleOrderHVO voHead = view.getHead();
      SaleOrderBVO voBody = view.getBody();

      // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new SaleOrderViewDataSet(voHead, voBody, item);
      Calculator tool = new Calculator(data, scale);

      // �ɽ������ͻ�õ����ۻ��ǵ��ۿ�
      boolean isChgPriceOrDiscount = this.isChgPriceOrDiscount(view);
      cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);

      IKeyValue keyValue = new ViewKeyValue<SaleOrderViewVO>(view);
      SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(keyValue);
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(0));
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(0));
      // �Ƿ���
      SOBuysellTriaRule buselrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buselrule.isBuysellFlagOut(0));

      if (isnumonly) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }

      // ������ǰ���
      UFDouble norigtaxmny = view.getBody().getNorigtaxmny();
      UFDouble norigsubmny = view.getBody().getNorigsubmny();
      view.setAttributeValue(SaleOrderBVO.NBFORIGSUBMNY,
          MathTool.add(norigtaxmny, norigsubmny));
    }
  }

  private M30TranTypeVO getTranTypeVO(SaleOrderViewVO viewvo) {
    String trantypeid = viewvo.getHead().getCtrantypeid();
    if (this.maptrantypevo.containsKey(trantypeid)) {
      return this.maptrantypevo.get(trantypeid);
    }

    M30TranTypeVO trantypevo = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      trantypevo = service.queryTranTypeVO(trantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    this.maptrantypevo.put(trantypeid, trantypevo);
    return trantypevo;
  }

  private boolean isChgPriceOrDiscount(SaleOrderViewVO viewvo) {
    // ���ǿ�Ƶ����ۿۣ�����
    if (null != this.forcechgprice) {
      return this.forcechgprice.booleanValue();
    }
    M30TranTypeVO m30tranvo = this.getTranTypeVO(viewvo);
    // Ĭ�ϵ����ۿ�
    if (null == m30tranvo) {
      return false;
    }
    Integer fmodifymny = m30tranvo.getFmodifymny();
    return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);

  }

}
