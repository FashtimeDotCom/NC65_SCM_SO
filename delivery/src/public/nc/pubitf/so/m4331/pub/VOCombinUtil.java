package nc.pubitf.so.m4331.pub;

import nc.pubitf.so.m4331.pub.rule.CombinToDeliveryVORule;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * vo֮��ĺϲ�
 * 
 * @since 6.0
 * @version 2011-1-10 ����02:18:48
 * @author ף����
 */
public class VOCombinUtil {
  /**
   * ���ݷ�����vo��Ϣ����ʼ���Ϣ�����ʼ�vo�е�������Ϣ
   * ���ϲ��������������ϣ����γ��µľۺ�vo
   * 
   * @param vos
   */
  public DeliveryVO[] combinTODeliveryvo(DeliveryVO[] vos) {
    CombinToDeliveryVORule combin = new CombinToDeliveryVORule();
    return combin.combin(vos);
  }
}
