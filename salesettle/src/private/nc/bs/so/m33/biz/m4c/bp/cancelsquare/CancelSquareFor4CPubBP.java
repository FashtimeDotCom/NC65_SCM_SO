package nc.bs.so.m33.biz.m4c.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.trade.checkrule.VOChecker;

public class CancelSquareFor4CPubBP {

  public void cancelSquare(SquareOutDetailVO[] sqdvos, SquareOutVO[] sqvos) {

    if (!VOChecker.isEmpty(sqdvos)) {
      // ����
      new VOConcurrentTool().lock(sqdvos);

      // ���ñ���ȡ����������
      SquareOutVO[] nsqvos =
          SquareOutVOUtils.getInstance().setNthisnumForCancelSquare(sqdvos,
              sqvos);

      // �����۳�������㵥����
      BSContext.getInstance().setSession(SquareOutVO.class.getName(), nsqvos);

      AbstractCancelSquareDetail<SquareOutDetailVO> caction =
          new CancelSquareOutDetailBP();
      caction.cancelSquare(sqdvos, SquareOutDetailVO.FSQUARETYPE);

      // �ͷŻ���
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());

      SquareOutVOUtils.getInstance().setNewTS(nsqvos, sqvos);
    }
  }

}
