package nc.bs.so.m30.revise.rule;

import nc.bs.so.m30.rule.approve.ApproveStateRule;

/**
 * 
 * @description
 * ���۶����޶�������
 * @scene
 * ���۶����޶�������������ж���״̬�Ĵ������⴦��
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����1:47:43
 * @author zhangby5
 */
public class ReviseApproveStateRule extends ApproveStateRule {

/*	@Override
	protected SaleOrderViewVO[] billToViewConvertor(SaleOrderVO[] vos) {
		List<SaleOrderViewVO> viewList = new ArrayList<>();
		for(SaleOrderVO vo : vos){
			SaleOrderBVO[] bvos = vo.getChildrenVO();
			for(SaleOrderBVO bvo : bvos){
				if (VOStatus.NEW == bvo.getStatus()) {
					SaleOrderViewVO viewVO = new SaleOrderViewVO();
					viewVO.setHead(vo.getParentVO());
					viewVO.setBody(bvo);
					viewList.add(viewVO);
				}
			}
		}
		return ListUtil.toArray(viewList);
	}*/
}
