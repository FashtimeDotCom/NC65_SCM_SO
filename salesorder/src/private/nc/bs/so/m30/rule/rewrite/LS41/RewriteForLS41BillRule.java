package nc.bs.so.m30.rule.rewrite.LS41;

import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.exception.DbException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 
 * @author wangzym
 */

public class RewriteForLS41BillRule implements IRule<SaleOrderVO> {

	@Override
	public void process(nc.vo.so.m30.entity.SaleOrderVO[] vos) {
		// TODO �Զ����ɵķ������
		// ֻ��һ��Aggvo
		// ��Ҫ�ж��Ƿ���Դ��������Ϊ�۸�������
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcid")) == null
					|| !"LS41".equals(SaleOrderBVO.getAttributeValue("vsrctype"))) {
				// ����Ҫ��дû��ȡ�����ε���Դ����
				return;
			}
			String csrcbid = SaleOrderBVO.getAttributeValue("csrcbid")
					.toString();
			srcPk[i] = csrcbid;

		}
		this.rewrite(srcPk);

	}

	/**
	 * @Title: rewrite
	 * @Description: TODO
	 * @param srcPk
	 * @return: void
	 */
	private void rewrite(String[] srcPk) {
		// TODO �Զ����ɵķ������
		if (srcPk.length == 0) {
			return;
		}
		String[] names = new String[] { "bdef1" };
		LsxywtbBVO[] vos = new LsxywtbBVO[srcPk.length];
		for (int i = 0; i < srcPk.length; i++) {
			String bpk = srcPk[i];
			LsxywtbBVO bvo = new LsxywtbBVO();
			bvo.setPrimaryKey(bpk);
			// ��дΪ0 �Ա��´λ�������
			bvo.setAttributeValue("bdef1", 1);
			vos[i] = bvo;
		}
		VOUpdate<LsxywtbBVO> bo = new VOUpdate<LsxywtbBVO>();

		LsxywtbBVO[] newvo = bo.update(vos, names);
		TimeLog.info("�������ݿ�"); /* -=notranslate=- */

	}
	
}
