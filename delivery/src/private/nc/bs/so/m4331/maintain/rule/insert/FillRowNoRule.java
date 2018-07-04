package nc.bs.so.m4331.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

public class FillRowNoRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO bill : vos) {
      // ����ǲ�ȫVO������ʱ��Ҫ������״̬
      this.fillupRowNo(bill);
    }
  }

  private void fillupRowNo(DeliveryVO bill) {

    // Ϊ�к�Ϊ�յ��в����кš�
    DeliveryBVO[] items = bill.getChildrenVO();
    List<DeliveryBVO> bvos = new ArrayList<DeliveryBVO>();
    for (DeliveryBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // ������ɾ������
        continue;
      }
      bvos.add(item);
    }
    items = bvos.toArray(new DeliveryBVO[0]);
    VORowNoUtils.setVOsRowNoByRule(items, DeliveryBVO.CROWNO);

  }

}
