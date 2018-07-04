package nc.impl.so.m32.pf;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊʵ�ֵ�����ƽ̨��������д����״̬�Ľӿ�
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-20 ����09:27:23
 */
public class SaleInvoiceCheckStatus extends PfBeforeAndAfterAction implements
    IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
    ICheckStatusCallback {

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    SaleInvoiceVO bill = (SaleInvoiceVO) cscc.getBillVo();
    SaleInvoiceHVO head = bill.getParentVO();
    // ���±�ͷ
    String[] names =
        new String[] {
          SaleInvoiceHVO.FSTATUSFLAG, SaleInvoiceHVO.APPROVER,
          SaleInvoiceHVO.TAUDITTIME
        };
    VOUpdate<SaleInvoiceHVO> bo = new VOUpdate<SaleInvoiceHVO>();
    bo.update(new SaleInvoiceHVO[] {
      head
    }, names);
  }

  @Override
  public SourceBillInfo[] findSourceBill(String pk_srcBilltype,
      Object billEntity) throws BusinessException {
    return null;
  }

  @Override
  public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
      String srcAction, String destBilltype, String drivedAction)
      throws BusinessException {
    return true;
  }

  @Override
  public boolean isEnableDrive(String srcBilltype,
      AggregatedValueObject srcBillVO, String srcAction, String destBillType,
      String beDrivedActionName) throws BusinessException {
    return true;
  }

}
