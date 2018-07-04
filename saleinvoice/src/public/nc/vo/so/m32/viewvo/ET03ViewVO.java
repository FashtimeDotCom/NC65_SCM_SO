package nc.vo.so.m32.viewvo;

import nc.vo.et.gcdljsd.AggGcdljsdHeadVO;
import nc.vo.et.gcdljsd.GcdljsdHeadVO;
import nc.vo.et.gcdljsd.GcdljsdbBodyVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

class ET03ViewVO extends AbstractDataView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4169165230020624196L;

	@Override
	public IDataViewMeta getMetaData() {
		// TODO �Զ����ɵķ������
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggGcdljsdHeadVO.class);
	}
	public AggGcdljsdHeadVO changeToBill() {
		AggGcdljsdHeadVO bill = new AggGcdljsdHeadVO();
		bill.setParent(this.getHead());
		GcdljsdbBodyVO[] items = new GcdljsdbBodyVO[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}
	
	//����
	public GcdljsdHeadVO getHead() {
		return (GcdljsdHeadVO) this.getVO(GcdljsdHeadVO.class);
	}

	public void setHead(GcdljsdHeadVO head) {
		this.setVO(head);
	}
	
	//�ӱ�
	public void setItem(GcdljsdbBodyVO item) {
		this.setVO(item);
	}
	
	public GcdljsdbBodyVO getItem() {
		return (GcdljsdbBodyVO) this.getVO(GcdljsdbBodyVO.class);
	}
	

}
