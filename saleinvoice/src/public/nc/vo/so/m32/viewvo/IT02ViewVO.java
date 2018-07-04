package nc.vo.so.m32.viewvo;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.it.yljsd.AggYljsd;
import nc.vo.it.yljsd.YljsdHeadVO;
import nc.vo.it.yljsd.YljsdbBodyVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

class IT02ViewVO extends AbstractDataView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -541992660490238938L;

	@Override
	public IDataViewMeta getMetaData() {
		// TODO �Զ����ɵķ������
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggYljsd.class);
	}
	public AggYljsd changeToBill() {
		AggYljsd bill = new AggYljsd();
		bill.setParent(this.getHead());
		YljsdbBodyVO[] items = new YljsdbBodyVO[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}
	
	//����
	public YljsdHeadVO getHead() {
		return (YljsdHeadVO) this.getVO(YljsdHeadVO.class);
	}

	public void setHead(YljsdHeadVO head) {
		this.setVO(head);
	}
	
	//�ӱ�
	public void setItem(YljsdbBodyVO item) {
		this.setVO(item);
	}
	
	public YljsdbBodyVO getItem() {
		return (YljsdbBodyVO) this.getVO(YljsdbBodyVO.class);
	}
	

}
