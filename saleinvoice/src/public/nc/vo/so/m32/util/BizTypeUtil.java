package nc.vo.so.m32.util;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.scmpub.res.billaction.SOBillAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-9 ����11:21:57
 */
public class BizTypeUtil {

  private static BizTypeUtil instance = new BizTypeUtil();

  /**
   * BizTypeUtil �Ĺ�����
   */
  private BizTypeUtil() {
    // ˽�л�������
  }

  public static BizTypeUtil getInstance() {
    return BizTypeUtil.instance;
  }

  /**
   * ��������������ҵ�������ϣ����۷�Ʊ�Ƿ����ò�Ӧ�ա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param bizType
   * @return <p>
   * @author fengjb
   * @time 2010-8-5 ����02:44:41
   */
  public boolean isAdjustIncome(String bizType, String vtrantypecode) {

    MessagedriveVO[] driveVOs =
        PfServiceScmUtil.queryAllMsgdrvVOs(vtrantypecode, bizType,
            SOBillAction.SaleInvoiceApprove.getCode());

    // û��������������
    if (null == driveVOs || driveVOs.length == 0) {
      return false;
    }
    boolean isAdjustInc = false;
    for (MessagedriveVO drive : driveVOs) {
      // �����˲�Ӧ��
      if (SOBillAction.SaleInvoiceADJUSTINCOME.getCode().equals(
          drive.getActiontype())) {
        isAdjustInc = true;
        break;
      }
    }
    return isAdjustInc;
  }
}
