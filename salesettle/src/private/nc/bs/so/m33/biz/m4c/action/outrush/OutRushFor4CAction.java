package nc.bs.so.m33.biz.m4c.action.outrush;

import nc.bs.so.m33.biz.m4c.bp.outrush.OutRushFor4CBP;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.ArrayUtil;

/**
 * 
 * @since 6.0
 * @version 2012-8-15 ����05:10:48
 * @author ô��
 */
public class OutRushFor4CAction {

  public UFDouble execOutRush(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // ���ó���Գ��־,�˱�Ǻ����ᴫ�ݵ�������ϸvo��
    this.setOutRushFlag(bluevos, redvos);

    // ����Գ�
    UFDouble totalRushNum = new OutRushFor4CBP().square(bluevos, redvos);

    return totalRushNum;

  }

  private void setOutRushFlag(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    SquareOutViewVO[] views = ArrayUtil.combinArrays(bluevos, redvos);
    SquareOutVOUtils.getInstance().setOutRushFlag(views);
  }

}
