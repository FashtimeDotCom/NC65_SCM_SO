package nc.pubimpl.so.m4310.pf;

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
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ���۱��۵�ʵ�ֵ�����ƽ̨�������ӿڣ��־û���������ֶ���Ϣ
 * 
 * @since 6.0
 * @version 2012-5-4 ����
 * @author buxh
 */
public class SaleQuoCheckStatus extends PfBeforeAndAfterAction
	implements IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
	ICheckStatusCallback {

	@Override
	public void callCheckStatus(CheckStatusCallbackContext cscc)
			throws BusinessException {
		AggSalequotationHVO billvo = (AggSalequotationHVO) cscc.getBillVo();
	    SalequotationHVO headvo = billvo.getParentVO();

	    String[] headnames =
	        new String[] {
	    		SalequotationHVO.FSTATUSFLAG, SalequotationHVO.APPROVER,
	    		SalequotationHVO.TAUDITTIME
	        };
	    // ���±�ͷ
	    VOUpdate<SalequotationHVO> updatesrv = new VOUpdate<SalequotationHVO>();
	    updatesrv.update(new SalequotationHVO[] {
	      headvo
	    }, headnames);

	}

	@Override
	public SourceBillInfo[] findSourceBill(String pk_srcBilltype,
			Object billEntity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
			String srcAction, String destBilltype, String drivedAction)
			throws BusinessException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnableDrive(String srcBilltype,
			AggregatedValueObject srcBillVO, String srcAction,
			String destBillType, String beDrivedActionName)
			throws BusinessException {
		// TODO Auto-generated method stub
		return true;
	}

}
