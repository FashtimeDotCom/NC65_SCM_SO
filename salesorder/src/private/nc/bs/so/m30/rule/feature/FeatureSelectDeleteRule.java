package nc.bs.so.m30.rule.feature;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.bd.feature.ffile.IPubFFileBusiService;
import nc.vo.bd.feature.ffile.param.FFilleResetSrcParam;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.util.ListUtil;

/**
 * 
 * @description
 * ���۶���ɾ�������
 * @scene
 * ���۶���ɾ������ձ����Ӧ�����뵵������Դ��Ϣ
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����3:16:14
 * @author zhangby5
 */
public class FeatureSelectDeleteRule implements IRule<SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {

		FFilleResetSrcParam[] paramList = this.getResetSrcParams(vos);
		if (paramList == null || paramList.length == 0) {
			return;
		}

		IPubFFileBusiService ffileService = NCLocator.getInstance().lookup(
				IPubFFileBusiService.class);
		try {
			ffileService.resetSrcAggFFileVO2(paramList);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappBusinessException(ex.getMessage());
		}

	}

	/**
	 * ��ȡ�������зǿ�������
	 * 
	 * @param vos
	 * @return
	 */
	private FFilleResetSrcParam[] getResetSrcParams(SaleOrderVO[] vos) {
		List<FFilleResetSrcParam> billIdList = new ArrayList<>();
		for (SaleOrderVO vo : vos) {
			SaleOrderBVO[] bvos = vo.getChildrenVO();
			for (SaleOrderBVO bvo : bvos) {
				String cmffileid = bvo.getCmffileid();
				if (PubAppTool.isNull(cmffileid)) {
					continue;
				}
				FFilleResetSrcParam param = new FFilleResetSrcParam();
				param.setSrcBid(bvo.getCsaleorderbid());
				param.setSrcId(bvo.getCsaleorderid());
				param.setSrcType(cmffileid);
				billIdList.add(param);
			}
		}
		return ListUtil.toArray(billIdList);
	}

}
