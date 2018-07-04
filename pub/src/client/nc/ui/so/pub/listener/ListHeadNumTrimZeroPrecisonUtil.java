package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.scmpub.util.StringUtil;

/**
 * �б�����б�ͷ�����ľ��ȴ�����Ҫ����ȥ�㴦�����Щ�ֶΣ�
 * 
 * @author jilu
 * 
 */
public class ListHeadNumTrimZeroPrecisonUtil {

	/**
	 * ���ֻ��billmodel��keyҲ��������
	 * 
	 * @param billModel
	 * @param headKeys
	 */
	public static void addHeadNumTrimZeroPrecisonListener(BillModel billModel,
			String[] headKeys) {
		if (billModel == null || ArrayUtil.isEmpty(headKeys)) {
			return;
		}

		BillItem[] items = new BillItem[headKeys.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = billModel.getItemByKey(headKeys[i]);
		}

		addHeadNumTrimZeroPrecisonListener(items);
	}

	/**
	 * ��һ��billitem���Ӿ��ȼ�����ע�������itemӦ�����б����ı�ͷ�е�item�����ͷ����item����Ϊһ����������ߵ��ǵ�λ���ȣ�
	 * ����Ƭ�����ͷ�Ѿ�ʹ��conver������
	 * 
	 * @param items
	 */
	public static void addHeadNumTrimZeroPrecisonListener(BillItem[] items) {
		if (ArrayUtil.isEmpty(items)) {
			return;
		}

		for (int i = 0; i < items.length; i++) {
			String key = items[i].getKey();
			if (StringUtil.isEmptyTrimSpace(key)) {
				continue;
			}

			SOHeadNumTrimZeroListener listener = new SOHeadNumTrimZeroListener(
					key);
			items[i].addDecimalListener(listener);
		}
	}

}
