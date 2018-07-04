package nc.vo.so.pub.calculator;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class PriceNumMnyCalculator {

  private UFBoolean bPriceDiscount = UFBoolean.FALSE;

  private Condition cond;

  private boolean ifGroupNull;

  // ��ѡ����
  private UFBoolean isTaxPrior;

  // ���봫�ݵĲ���
  private IKeyValue keyValue;

  private SOUnitChangeRateRule sounitrule;

  private SOBuysellTriaRule buysellrule;

  private ScaleUtils scale;

  public PriceNumMnyCalculator(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.ifGroupNull = PubAppTool.isNull(this.getGroup());
    this.sounitrule = new SOUnitChangeRateRule(this.keyValue);
    this.buysellrule = new SOBuysellTriaRule(this.keyValue);
  }

  public void calculate(int[] rows, String editKey, IDataSetForCal[] datas) {
    this.preCondition();
    for (int i = 0; i < rows.length; i++) {
      this.preConditionByRow(rows[i]);
      Calculator tool = new Calculator(datas[i], this.scale);
      tool.calculate(this.getCondition(), editKey);
    }
  }

  public void calculate(int[] rows, String editKey, IDataSetForCal[] datas,
      ScaleUtils scale1) {
    this.scale = scale1;
    this.calculate(rows, editKey, datas);
  }

  public void calculateAll(String editKey, IDataSetForCal[] datas) {
    int len = this.keyValue.getBodyCount();
    int[] editRows = new int[this.keyValue.getBodyCount()];
    for (int i = 0; i < len; i++) {
      editRows[i] = i;
    }
    this.calculate(editRows, editKey, datas);
  }

  /**
   * ���㱾�ҽ�� ����ȫ�ֱ�λ�ҽ�� ���㼯�ű�λ�ҽ��
   * 
   * @param rows
   * @param editKey
   * @param datas
   */
  public void calculateLocal(int[] rows, IDataSetForCal[] datas) {
    this.preCondition();
    for (int i = 0; i < rows.length; i++) {
      this.preConditionByRow(rows[i]);
      Calculator tool = new Calculator(datas[i], this.scale);
      tool.calculateLocalCurrenyMny(this.getCondition());
      tool.calculateGroupMny(this.getCondition());
      tool.calculateGlobalMny(this.getCondition());
    }
  }

  public Condition getCondition() {
    if (this.cond == null) {
      this.cond = new Condition();
    }
    return this.cond;
  }

  public void setbPriceDiscount(UFBoolean bPriceDiscount1) {
    this.bPriceDiscount = bPriceDiscount1;
  }

  public void setCondition(Condition cond1) {
    this.cond = cond1;
  }

  private UFBoolean getbPriceDiscount() {
    return this.bPriceDiscount;
  }

  private String getGroup() {
    String pk_group = this.keyValue.getHeadStringValue(SOItemKey.PK_GROUP);
    return pk_group;
  }

  private String getGroup(int row) {
    String pk_group = this.keyValue.getBodyStringValue(row, SOItemKey.PK_GROUP);
    if (pk_group == null) {
      pk_group = this.keyValue.getHeadStringValue(SOItemKey.PK_GROUP);
    }
    return pk_group;
  }

  private UFBoolean getIsTaxPrior() {
    if (this.isTaxPrior == null) {
      UFBoolean sO23 = SOSysParaInitUtil.getSO23(this.getGroup());
      return sO23 == null ? UFBoolean.FALSE : sO23;
    }
    return this.isTaxPrior;
  }

  /**
   * ׼������ȫ����������ÿһ�У������ģ�
   */
  private void preCondition() {
    if (this.scale == null) {
      this.scale = ScaleUtils.getScaleUtilAtBS();
    }
    // �����Ƿ���б��һ���
    this.getCondition().setIsCalLocalCurr(true);
    // ���ú�˰���Ȼ�����˰����
    if (!this.ifGroupNull) {
      this.getCondition().setIsTaxOrNet(this.getIsTaxPrior().booleanValue());
      // NC001��NC002��������
      this.getCondition().setGroupLocalCurrencyEnable(
          SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(
              this.getGroup()));
      this.getCondition().setOrigCurToGroupMoney(
          SOPubParaUtil.getInstance().isOrigCurToGroupMoney(this.getGroup()));
      this.getCondition().setGlobalLocalCurrencyEnable(
          SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable());
      this.getCondition().setOrigCurToGlobalMoney(
          SOPubParaUtil.getInstance().isOrigCurToGlobalMoney());
    }
    // ���õ����۷�ʽ���ۿ�
    this.getCondition().setIsChgPriceOrDiscount(
        this.getbPriceDiscount().booleanValue());
  }

  /**
   * ׼������������ÿһ�У�
   */
  private void preConditionByRow(int row) {
    if (this.ifGroupNull) {
      // �����Ƿ���б��һ���
      this.getCondition().setIsCalLocalCurr(true);
      this.getCondition().setIsTaxOrNet(this.getIsTaxPrior().booleanValue());
      // NC001��NC002��������
      this.getCondition().setGroupLocalCurrencyEnable(
          SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(
              this.getGroup(row)));
      this.getCondition()
          .setOrigCurToGroupMoney(
              SOPubParaUtil.getInstance().isOrigCurToGroupMoney(
                  this.getGroup(row)));
      this.getCondition().setGlobalLocalCurrencyEnable(
          SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable());
      this.getCondition().setOrigCurToGlobalMoney(
          SOPubParaUtil.getInstance().isOrigCurToGlobalMoney());
    }
    // ����ҵ��λ�Ƿ�̶�������
    this.getCondition()
        .setIsFixNchangerate(this.sounitrule.isAstFixedRate(row));
    // ���ñ��۵�λ�Ƿ�̶�������
    this.getCondition().setIsFixNqtunitrate(this.sounitrule.isQtFixedRate(row));
    // �Ƿ��������
    this.getCondition()
        .setInternational(this.buysellrule.isBuysellFlagOut(row));
  }

}
