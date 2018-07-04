package nc.bs.so.m33.maintain.m4c;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

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
public class UpdateSquare4CFieldsBP {

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
  public void updateFields(SquareOutVO[] sqvos, String[] hFlagKeys,
      String[] bFlagKeys) {

    if ((sqvos == null) || (sqvos.length <= 0)) {
      return;
    }

    if ((hFlagKeys != null) && (hFlagKeys.length > 0)) {
      VOUpdate<SquareOutHVO> bo = new VOUpdate<SquareOutHVO>();
      bo.update(SquareOutVOUtils.getInstance().getSquareOutHVO(sqvos),
          hFlagKeys);
    }

    if ((bFlagKeys != null) && (bFlagKeys.length > 0)) {
      VOUpdate<SquareOutBVO> bbo = new VOUpdate<SquareOutBVO>();
      bbo.update(SquareOutVOUtils.getInstance().getSquareOutBVO(sqvos),
          bFlagKeys);
    }

  }

}
