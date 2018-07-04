package nc.ui.so.m33.pub;

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
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;

public class SquareModelCalculator {

  private BillListPanel listPanel;

  private Set<String> hsNeedCalKey;

  public SquareModelCalculator(BillListPanel listPanel) {
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
      this.hsNeedCalKey.add(SquareOutBVO.NTHISNUM);
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
    item.setnumKey(SquareOutBVO.NTHISNUM);
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    // ���ü�������λ����
    cond.setUnitPriorType(Condition.MAIN_PRIOR);
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
      cond.setIsFixNchangerate(true);
      cond.setIsFixNqtunitrate(true);
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
