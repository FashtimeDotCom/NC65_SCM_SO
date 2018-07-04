package nc.bs.so.m33.maintain.m32;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;

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
public class UpdateSquare32FieldsBP {

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
  public void updateFields(SquareInvVO[] sqvos, String[] hFlagKeys,
      String[] bFlagKeys) {

    if ((sqvos == null) || (sqvos.length <= 0)) {
      return;
    }

    if ((hFlagKeys != null) && (hFlagKeys.length > 0)) {
      VOUpdate<SquareInvHVO> bo = new VOUpdate<SquareInvHVO>();
      bo.update(SquareInvVOUtils.getInstance().getSquareInvHVO(sqvos),
          hFlagKeys);
    }

    if ((bFlagKeys != null) && (bFlagKeys.length > 0)) {
      VOUpdate<SquareInvBVO> bbo = new VOUpdate<SquareInvBVO>();
      bbo.update(SquareInvVOUtils.getInstance().getSquareInvBVO(sqvos),
          bFlagKeys);
    }

  }

}
