package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.InvalidatePreorderBP;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ����ʧЧ������
 * 
 * @since 6.0
 * @version 2011-5-7 ����02:04:55
 * @author ף����
 */
public class InvalidatePreorderAction {
  public PreOrderVO[] invalidatePreorder(PreOrderVO[] vos) {
    InvalidatePreorderBP bp = new InvalidatePreorderBP();
    return bp.invalidatePreorder(vos);
  }
}
