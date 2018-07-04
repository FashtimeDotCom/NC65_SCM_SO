package nc.vo.so.m4331.pub;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.calculator.SOVODataSetForCal;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.CirVOKeyValue;

public class DeliveryVOCalculator {

  private CircularlyAccessibleValueObject[] currvos;

  /**
   * DeliveryVOCalculator �Ĺ�����
   * 
   * @param cardpanel
   */
  public DeliveryVOCalculator(CircularlyAccessibleValueObject[] vos) {
    this.currvos = vos;
  }

  private Set<String> hsNeedCalKey;

  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private void calculate(int[] rows, String editkey, boolean isonlynum) {

    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    String pk_group = AppContext.getInstance().getPkGroup();
    ScaleUtils scale = new ScaleUtils(pk_group);
    IKeyValue keyValue =
        new CirVOKeyValue<CircularlyAccessibleValueObject>(this.currvos);

    for (int row : rows) {
      CircularlyAccessibleValueObject bvo = this.currvos[row];
      IDataSetForCal data = new SOVODataSetForCal(null, bvo, item);
      // �����Ƿ��������
      SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // �����Ƿ�̶���λ������
      SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(keyValue);
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));

      Calculator tool = new Calculator(data, scale);
      // �������� cond Ϊ����ʱ�Ĳ�������
      if (isonlynum) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }
    }
  }

  /**
   * �����������������۷�����VO�������۽�����㡣
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
  public void calculate(int[] rows, String editKey) {
    this.calculate(rows, editKey, false);
  }
}
