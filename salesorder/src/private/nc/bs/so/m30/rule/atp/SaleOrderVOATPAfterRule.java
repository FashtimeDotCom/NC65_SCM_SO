package nc.bs.so.m30.rule.atp;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description ���۶������������Ͽ����������
 * @scene ���۶����������������޸ġ�ɾ�����涯�������ᶯ����
 * @param ��
 * modify by wangzy ����ܲ 2017-04-22
 */
public class SaleOrderVOATPAfterRule implements IRule<SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		try {
			// �ж��Ƿ��ʸֽӿڴ�������
			List<String> transtype = new ArrayList<String>();
			for (SaleOrderVO saleOrderVO : vos) {
				String ctranstypecode = (String) saleOrderVO.getParent()
						.getAttributeValue("vtrantypecode");
				transtype.add(ctranstypecode);
			}
			if (transtype.contains("30-Cxx-05")) {
				return;
			} else {
				SOATPprocess.modifyATPAfter(SOBillType.Order.getCode(), vos);
			}
		} catch (BusinessException e) {

			ExceptionUtils.wrappException(e);
		}
	}

}
