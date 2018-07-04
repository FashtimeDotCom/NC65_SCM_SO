package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillModelDecimalListener;
import nc.vo.pub.lang.UFDouble;


/**
 * ��������������ϵ������ֶξ������ã������۶����ı�ͷ�ϼ������ֶΣ���Ҫ����ȥ�㴦������ȥ���ľ�������item�ľ��ȣ����������Ա�֤�ϼ����ϵľ�����ȷ��
 * 
 * @author jilu v65
 *
 */
public class SOHeadNumTrimZeroListener implements IBillModelDecimalListener {
	
	private String key;
	
	/**
	 * ��Ҫע���item��key
	 * 
	 * @param key
	 */
	public SOHeadNumTrimZeroListener(String key){
		this.key = key;
	}

	@Override
	public String getSource() {
		return key;
	}

	@Override
	public int getDecimalFromSource(int row, Object okValue) {
		if (okValue == null) {
			return 0;
		}
		if (okValue instanceof UFDouble) {
			UFDouble doubleVal = (UFDouble) okValue;
			// ȥ�㴦��
			doubleVal.setTrimZero(true);
			int point = doubleVal.toString().lastIndexOf(".");
			int digit = -1 == point ? 0 : doubleVal.toString().length() - 1
					- point;
			return digit;
		}

		return 0;
	}

	@Override
	public boolean isTarget(BillItem item) {
		return false;
	}

}
