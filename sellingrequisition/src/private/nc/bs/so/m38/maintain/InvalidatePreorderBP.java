package nc.bs.so.m38.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.VOStatus;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * Ԥ����ʧЧBP
 * 
 * @since 6.0
 * @version 2011-5-7 ����02:06:05
 * @author ף����
 */
public class InvalidatePreorderBP {
  public PreOrderVO[] invalidatePreorder(PreOrderVO[] vos) {
    if (vos == null) {
      return null;
    }
    BillUpdate<PreOrderVO> updateAction = new BillUpdate<PreOrderVO>();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(vos);
    PreOrderVO[] originBills = transferTool.getOriginBills();
    PreOrderVO[] fullBills = transferTool.getClientFullInfoBill();

    for (PreOrderVO bill : fullBills) {
      PreOrderHVO hvo = bill.getParentVO();
      // ֻ������״̬�ſ��Ա��ʧЧ״̬
      Integer status = hvo.getFstatusflag();
      if (BillStatus.FREE.equalsValue(status)) {
        hvo.setFstatusflag(BillStatus.INVALIDATE.getIntegerValue());
        hvo.setStatus(VOStatus.UPDATED);
      }
    }
    PreOrderVO[] newbills = updateAction.update(fullBills, originBills);
    // ��ǰ̨����ֻ�����˸ı������vo
    return transferTool.getBillForToClient(newbills);
  }
}
