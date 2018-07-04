package nc.bs.so.billinformation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceBillinformationSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggBillInforMationVO[] sendApprove(AggBillInforMationVO[] clientBills,
			AggBillInforMationVO[] originBills) {
		for (AggBillInforMationVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggBillInforMationVO[] returnVos = new BillUpdate<AggBillInforMationVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
