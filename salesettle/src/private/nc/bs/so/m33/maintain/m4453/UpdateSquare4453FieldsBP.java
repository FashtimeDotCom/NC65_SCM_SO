package nc.bs.so.m33.maintain.m4453;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�������۽��㵥��ͷ���߱���ָ���ֶ�
 * <li>
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:37:12
 */
public class UpdateSquare4453FieldsBP {

  /**
   * �����������������½��㵥��ͷ����ָ���ֶ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sqvos
   * @param hFlagKeys
   * @param bFlagKeys
   *          <p>
   * @author zhangcheng
   * @time 2010-5-28 ����10:42:16
   */
  public void updateFields(SquareWasVO[] sqvos, String[] hFlagKeys,
      String[] bFlagKeys) {

    if ((sqvos == null) || (sqvos.length <= 0)) {
      return;
    }

    if ((hFlagKeys != null) && (hFlagKeys.length > 0)) {
      VOUpdate<SquareWasHVO> bo = new VOUpdate<SquareWasHVO>();
      bo.update(SquareWasVOUtils.getInstance().getSquareWasHVO(sqvos),
          hFlagKeys);
    }

    if ((bFlagKeys != null) && (bFlagKeys.length > 0)) {
      VOUpdate<SquareWasBVO> bbo = new VOUpdate<SquareWasBVO>();
      bbo.update(SquareWasVOUtils.getInstance().getSquareWasBVO(sqvos),
          bFlagKeys);
    }

  }

}
