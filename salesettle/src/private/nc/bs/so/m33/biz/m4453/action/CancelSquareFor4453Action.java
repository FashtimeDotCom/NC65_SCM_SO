package nc.bs.so.m33.biz.m4453.action;

import nc.bs.so.m33.biz.m4453.bp.cancelsquare.CancelSquareWasFor4453BP;
import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.ic.m4453.entity.WastageVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelSquareFor4453Action {

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-27 ����03:23:10
   */
  public void cancelSoSquare(WastageVO[] wasvos) {

    // ��ѯ�����㵥����
    SquareWasVO[] vos =
        new QuerySquare4453VOBP().querySquareWasVOBy4453ID(SoVoTools
            .getVOPKValues(wasvos));
    if (vos == null || vos.length == 0) {
      return;
    }

    // ����
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(vos);

    // ������ϸ����
    SquareWasDetailVO[] sqdvos =
        new QuerySquare4453VOBP().querySquareWasDetailVOBySQHID(AggVOUtil
            .getDistinctItemFieldArray(vos, SquareWasDetailVO.CSALESQUAREID,
                String.class));

    // ȡ������
    new CancelSquareWasFor4453BP().cancelSquare(sqdvos, vos);

    // 5.;������Ӱ�충������ر�
    this.processOrderSquareOpen(vos);
  }

  /**
   * ;������Ӱ�충������ر�
   * 
   * @param swvos
   */
  private void processOrderSquareOpen(SquareWasVO[] swvos) {
    // Դͷ���۶�������id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(swvos, SquareWasBVO.CFIRSTBID,
            String.class);
    BalOpenTrigger trigger = BalOpenTrigger.WAST_UNAUDIT;
    BalOpenPara para = new BalOpenPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalOpen(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
