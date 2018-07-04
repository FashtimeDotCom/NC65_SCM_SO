package nc.vo.so.m33.pub.util;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.SuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.calculator.SOVODataSetForCal;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.util.CirVOKeyValue;

public class SquareCalculatorForVO {

  private Set<String> hsNeedCalKey;

  public <T extends SuperVO> void calculate(T[] bvos, String calkey) {
    this.calculate(bvos, calkey, false);
  }

  public <T extends SuperVO> void calculateOnlyNumAssNumQtNum(T[] bvos,
      String calkey) {
    this.calculate(bvos, calkey, true);
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

  private <T extends SuperVO> void calculate(T[] bvos, String editkey,
      boolean isonlynum) {

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
    String pk_group = AppContext.getInstance().getPkGroup();
    ScaleUtils scale = new ScaleUtils(pk_group);
    IKeyValue keyValue = new CirVOKeyValue<T>(bvos);
    int row = 0;
    for (T bvo : bvos) {

      IDataSetForCal data = new SOVODataSetForCal(null, bvo, item);
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
      row++;
    }
  }
}
