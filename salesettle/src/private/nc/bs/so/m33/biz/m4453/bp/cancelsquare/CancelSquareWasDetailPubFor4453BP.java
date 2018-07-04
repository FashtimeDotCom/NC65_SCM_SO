package nc.bs.so.m33.biz.m4453.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;

public class CancelSquareWasDetailPubFor4453BP {

  /**
   * ��������������;��ȡ�����㹫��BP
   * 
   * @param sqdvos
   *          -- ȡ�������;����㵥
   * @param vos
   *          ----- ȡ�������;������㵥
   *          <p>
   * @author zhangcheng
   * @time 2010-9-28 ����10:13:59
   */
  public void cancelSquare(SquareWasDetailVO[] sqdvos, SquareWasVO[] vos) {
    // ���ڽ��㵥(��ϸ����)
    if (sqdvos != null) {
      // ����
      new VOConcurrentTool().lock(sqdvos);

      // ���ñ���ȡ����������
      SquareWasVOUtils.getInstance().setNthisnumForCancelSquare(sqdvos, vos);

      // ��;�𵥽��㵥����
      BSContext.getInstance().setSession(SquareWasVO.class.getName(), vos);

      AbstractCancelSquareDetail<SquareWasDetailVO> caction =
          new CancelSquareWasDetailBP();
      caction.cancelSquare(sqdvos, SquareWasDetailVO.FSQUARETYPE);

      // �ͷŻ���
      BSContext.getInstance().removeSession(SquareWasVO.class.getName());
    }

  }

}
