/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.LS41;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.lm.lsdlxy.BaseWriteBackVO;
import nc.vo.lm.lsdlxy.LsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pub.JavaType;
import nc.vo.pub.VOStatus;

/**
 * @author wangzym
 * @version 2017��5��5�� ����6:09:09
 */
public class RewriteForLS41BillUpdateRule implements ICompareRule<SaleOrderVO> {

	public RewriteForLS41BillUpdateRule() {
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void process(SaleOrderVO[] vo, SaleOrderVO[] originvo) {
		// TODO ֻ��Ա���
		// ���û�д��ֱ�ӽ��л�д����
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vo[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO saleOrderBVO = bvos[i];
			if ((saleOrderBVO.getAttributeValue("csrcbid")) == null
					|| !"LS41".equals(saleOrderBVO
							.getAttributeValue("vsrctype"))) {
				// ����Ҫ��дû��ȡ�����ε���Դ����
				// ����ȡ������Դ�������Ͳ��Ǽ���
				return;
			}
		}
		Map<String, List<RewritePara>> rwParaMap = null;

		BillRewriter tool = new BillRewriter(this.getItemKeyMapping());

		tool.addSRCHeadClazz("LS41", LsxywtHVO.class);
		tool.addSRCItemClazz("LS41", LsxywtbBVO.class);

		if (vo != null && originvo != null) {
			rwParaMap = tool.splitForUpdate(vo, originvo);
		} else if (vo != null && null == originvo) {
			rwParaMap = tool.splitForInsert(vo);
		} else if (originvo != null && null == vo) {
			rwParaMap = tool.splitForDelete(originvo);
		}
		if (null == rwParaMap) {
			return;
		}

		// ����Mapʱ��ʹ�ø�Ч��entrySet()�����������������
		for (Map.Entry<String, List<RewritePara>> en : rwParaMap.entrySet()) {
			if (en.getValue().isEmpty()) {
				continue;
			}
			// ��дLS41
			writeBack(en.getValue());
		}

	}

	/**
	 * @Title:��ʽ��д����
	 * @Description: TODO
	 * @param value
	 * @return: void
	 */
	private void writeBack(List<RewritePara> paraList) {
		// TODO �Զ����ɵķ������
		List<nc.vo.lm.lsdlxy.BaseWriteBackVO> writeBackList = new ArrayList<nc.vo.lm.lsdlxy.BaseWriteBackVO>();
		for (RewritePara para : paraList) {
			this.setWriteBackVO(para, writeBackList);
		}

		if (0 == writeBackList.size()) {
			return;
		}
		nc.vo.lm.lsdlxy.BaseWriteBackVO[] bvos = writeBackList
				.toArray(new nc.vo.lm.lsdlxy.BaseWriteBackVO[writeBackList
						.size()]);
		this.backWrite(bvos);

	}

	private ItemKeyMapping getItemKeyMapping() {
		ItemKeyMapping mapping = new ItemKeyMapping();
		// ��Դ��������
		mapping.setVsrctypeKey(SaleOrderBVO.VSRCTYPE);
		// ��Դ������������
		mapping.setCsrcidKey(SaleOrderBVO.CSRCID);
		// ��Դ���ݱ�������
		mapping.setCsrcbidKey(SaleOrderBVO.CSRCBID);
		return mapping;
	}

	// ����д��vo��ֵ
	private void setWriteBackVO(RewritePara para,
			List<nc.vo.lm.lsdlxy.BaseWriteBackVO> writeBackList) {
		nc.vo.lm.lsdlxy.BaseWriteBackVO vo = new nc.vo.lm.lsdlxy.BaseWriteBackVO();
		if (para.getStatus() == VOStatus.DELETED) {
			vo.setOperateFlag("DELETE");
		} else if (para.getStatus() == VOStatus.NEW) {
			vo.setOperateFlag("ADD");
		}
		vo.setPk_lsdlxy(para.getCsrcid());
		vo.setPk_lsdlxy_b(para.getCsrcbid());
		writeBackList.add(vo);

	}

	public void backWrite(BaseWriteBackVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		List<List<Object>> adddata = new ArrayList<List<Object>>();
		List<List<Object>> deldata = new ArrayList<List<Object>>();
		HashSet<String> headPks = new HashSet<String>();
		for (BaseWriteBackVO vo : vos) {
			LsxywtbBVO item = new LsxywtbBVO();
			item.setPrimaryKey(vo.getPk_lsdlxy_b());

			if (!headPks.contains(vo.getPk_lsdlxy())
					&& ("ADD" == vo.getOperateFlag() || "DELETE" == vo
							.getOperateFlag())) {
				LsxywtHVO head = new LsxywtHVO();
				head.setPrimaryKey(vo.getPk_lsdlxy());
			}

			List<Object> rowData = new ArrayList<Object>();
			rowData.add(vo.getPk_lsdlxy_b());

			if ("ADD" == vo.getOperateFlag()) {
				adddata.add(rowData);
			}
			if ("DELETE" == vo.getOperateFlag()) {
				deldata.add(rowData);
			}
		}
		if (adddata.size() > 0) {
			// this.upAdd(adddata);
		}
		if (deldata.size() > 0) {
			this.upDel(deldata);
		}

	}

	private void upDel(List<List<Object>> deldata) {
		if (deldata.size() > 0) {
			String delsql = "update lm_lsdlxyb set bdef1=0 WHERE pk_lsdlxy_b = ?  ";
			new DataAccessUtils().update(delsql,
					new JavaType[] { JavaType.String }, deldata);
		}
	}

}
