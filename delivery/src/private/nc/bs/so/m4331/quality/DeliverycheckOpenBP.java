package nc.bs.so.m4331.quality;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * �������ʼ���Ϣ��
 * 
 * @since 6.0
 * @version 2011-3-2 ����10:17:25
 * @author ף����
 */
public class DeliverycheckOpenBP {
  public void open(DeliveryCheckVO[] vos) {
    if (null == vos) {
      return;
    }
    this.setState(vos);
    this.updateDB(vos);
  }

  private void updateDB(DeliveryCheckVO[] vos) {
    VOUpdate<DeliveryCheckVO> update = new VOUpdate<DeliveryCheckVO>();
    String[] names = new String[] {
      DeliveryCheckVO.BOUTENDFLAG, DeliveryCheckVO.NTOTALOUTNUM
    };
    update.update(vos, names);
  }

  private void setState(DeliveryCheckVO[] vos) {
    for (DeliveryCheckVO vo : vos) {
      vo.setBoutendflag(UFBoolean.FALSE);
    }
  }
}
