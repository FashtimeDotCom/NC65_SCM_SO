package nc.bs.so.m4331.quality.rule.insert;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * �ʼ��д�������ӱ� ��д�����������ۼƺϸ����������ϸ������Ƿ��ʼ����
 * @scene
 * �ʼ��д�������ӱ���ǰ
 * @param
 * ��
 * @author ף����
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite4331ForCheckRule {

  public void rewrite4331(DeliveryViewVO[] views, DeliveryCheckVO[] checkvos) {
    // ���ۼƺϸ��������ۼƲ��ϸ��������õ�����������
    this.setNum(checkvos, views);
    this.rewrite(views);
  }

  /*
   * ���·������ӱ����ۼƺϸ��������ۼƲ��ϸ�����
   */
  private void rewrite(DeliveryViewVO[] views) {
    String[] names =
        new String[] {
          DeliveryBVO.NTOTALELIGNUM, DeliveryBVO.NTOTALUNELIGNUM,
          DeliveryBVO.BQUALITYFLAG
        };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
  }

  /*
   * ���÷������ӱ��е��ۼƺϸ���ۼƲ��ϸ�����
   */
  private void setNum(DeliveryCheckVO[] checkvos, DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      String id = view.getItem().getCdeliverybid();
      // �ϸ�����
      UFDouble elignum = null;
      // ���ϸ�����
      UFDouble unelignum = null;
      for (DeliveryCheckVO vo : checkvos) {
        String id1 = vo.getCdeliverybid();
        if (id1.equals(id)) {
          // �Ƿ�ϸ���
          if (vo.getBeligflag().booleanValue()) {
            elignum = MathTool.add(elignum, vo.getNnum());
          }
          else {
            unelignum = MathTool.add(unelignum, vo.getNnum());
          }
        }
      }
      view.getItem().setNtotalelignum(MathTool.oppose(elignum));
      view.getItem().setNtotalunelignum(MathTool.oppose(unelignum));
      view.getItem().setBqualityflag(UFBoolean.TRUE);
    }
  }
}
