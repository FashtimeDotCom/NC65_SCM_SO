package nc.bs.so.m4331.quality.rule.delete;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * ���۷������ʼ���Ϣɾ��ǰ����շ������ۼƺϸ��������ۼƲ��ϸ�����
 * @scene
 * ���۷������ʼ���Ϣɾ��ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2010-12-30 ����07:47:15
 * @author ף����
 */
public class Rewrite4331OnDeleteRule {
  /**
   * ����ۼƺϸ��������ۼƲ��ϸ�����
   * 
   * @param views
   */
  public void rewrite4331(DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      view.getItem().setNtotalelignum(null);
      view.getItem().setNtotalunelignum(null);
      view.getItem().setBqualityflag(UFBoolean.FALSE);
    }
    String[] names =
        new String[] {
          DeliveryBVO.NTOTALELIGNUM, DeliveryBVO.NTOTALUNELIGNUM,
          DeliveryBVO.BQUALITYFLAG
        };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
  }
}
