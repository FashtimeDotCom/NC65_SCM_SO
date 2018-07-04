package nc.bs.so.billinformation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.so.billinformation.AggBillInforMationVO;

/**
 * ��׼������˵�BP
 */
public class AceBillinformationApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggBillInforMationVO[] approve(AggBillInforMationVO[] clientBills,
			AggBillInforMationVO[] originBills) {
		for (AggBillInforMationVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBillInforMationVO> update = new BillUpdate<AggBillInforMationVO>();
		AggBillInforMationVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
