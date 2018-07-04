package nc.bs.so.m33.pub;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.trade.checkrule.VOChecker;

public class CheckSquareBiz {

  /**
   * �жϵ�ǰ������Դ����ĳ�����Ƿ����ý��㶯��
   * 
   * @param sourceBillType
   *          Դ���ݽ�������
   * @param sourceBusiType
   *          ҵ������
   * @param sourceAction
   *          ����
   * @return
   * @throws BusinessException
   */
  public boolean ifHasSquareAction(String sourceBillType,
      String sourceBusiType, String sourceAction) {
    boolean flag = false;
    String checkBillType = sourceBillType;
    MessagedriveVO[] mvos =
        PfServiceScmUtil.queryAllMsgdrvVOs(checkBillType, sourceBusiType,
            sourceAction);

    // �������Ͳ鲻�����õ������Ͳ�
    if (VOChecker.isEmpty(mvos)) {
      checkBillType = SOBillType.Invoice.getCode();
      mvos =
          PfServiceScmUtil.queryAllMsgdrvVOs(checkBillType, sourceBusiType,
              sourceAction);
    }

    for (MessagedriveVO mvo : mvos) {
      if (checkBillType.equals(mvo.getPk_billtype())) {
        if (SOBillAction.SaleInvoiceADJUSTINCOME.getCode().equals(
            mvo.getActiontype())
            || SOBillAction.SaleInvoiceSQUARECOST.getCode().equals(
                mvo.getActiontype())
            || SOBillAction.SaleInvoiceSQUAREINCOME.getCode().equals(
                mvo.getActiontype())) {
          flag = true;
          break;
        }

      }
    }
    return flag;
  }

}
