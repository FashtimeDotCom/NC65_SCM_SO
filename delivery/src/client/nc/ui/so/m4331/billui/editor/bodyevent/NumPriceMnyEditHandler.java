package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;

public class NumPriceMnyEditHandler {
  public void afterEdit(CardBodyAfterEditEvent e) {
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data =
        new DeliveryCardDataSet(e.getBillCardPanel(), e.getRow(), item);

    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(false);
    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(false);
    // ���ú�˰���Ȼ�����˰����
    cond.setIsTaxOrNet(false);
    // �������� cond Ϊ����ʱ�Ĳ�������
    tool.calculate(cond, e.getKey());
  }
}
