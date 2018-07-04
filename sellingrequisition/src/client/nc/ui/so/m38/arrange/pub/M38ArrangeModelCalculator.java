package nc.ui.so.m38.arrange.pub;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

/**
 * <p>
 * <b>Ԥ���������������۽����㹤����</b>
 * 
 * <ul>
 * <li>
 * <li>
 * <li>...
 * </ul>
 * 
 * @since 6.0
 * @author ��־ΰ
 * @time 2010-7-3 ����10:23:08
 */
public class M38ArrangeModelCalculator {

  private BillListPanel listPanel;

  private Set<String> hsNeedCalKey;

  public M38ArrangeModelCalculator(BillListPanel listPanel) {
    this.listPanel = listPanel;
  }

  public void calculate(int[] rows, String editkey) {
    this.calculate(rows, editkey, false);
  }

  public void calculateOnlyNumAssNumQtNum(int[] rows, String editkey) {
    this.calculate(rows, editkey, true);
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
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    // �������� cond Ϊ����ʱ�Ĳ�������
    BillModel billmodel = this.listPanel.getBodyBillModel();
    for (int row : rows) {
      IKeyValue keyValue =
          new ListKeyValue(this.listPanel, row, ListTemplateType.SUB);
      IDataSetForCal data = new BillModelDataSet(billmodel, row, item);
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
}
