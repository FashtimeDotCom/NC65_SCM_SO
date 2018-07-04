package nc.impl.so.m4331.action.maintain.rule.pushwrite;

import nc.bs.so.m4331.maintain.rule.insert.RewriteBillInsertRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class RewriteSrcRule {
  public void rewriteSrc(DeliveryViewVO[] views) {
    DeliveryVO[] vos = new DeliveryVO[views.length];
    for (int i = 0; i < vos.length; i++) {
      vos[i] = new DeliveryVO();
      // ��ͼVO��Ԫ����Ϊ�ӱ����������ֶλ����ӱ���ֵ����Ҫ���ӱ��ֵ��������
      DeliveryHVO head = views[i].getHead();
      DeliveryBVO body = views[i].getItem();
      head.setPk_org(body.getPk_org());
      head.setDbilldate(body.getDbilldate());
      vos[i].setParent(head);
      vos[i].setChildrenVO(new DeliveryBVO[] {
        body
      });
    }
    IRule<DeliveryVO> rule = new RewriteBillInsertRule();
    rule.process(vos);
  }
}
