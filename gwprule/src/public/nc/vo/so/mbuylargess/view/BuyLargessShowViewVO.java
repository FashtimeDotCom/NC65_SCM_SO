package nc.vo.so.mbuylargess.view;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * ��������չʾ����ͼVO
 * 
 * @since 6.1
 * @version 2012-10-30 16:02:06
 * @author ��ӱ�
 */
public class BuyLargessShowViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 9220656358685217471L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewmeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            BuyLargessShowViewMeta.class);
    return viewmeta;

  }

  /**
   * �������������ʵ��VO
   * 
   * @return BuyLargessHVO
   */
  public BuyLargessHVO getHead() {
    return (BuyLargessHVO) this.getVO(BuyLargessHVO.class);
  }

  /**
   * ��������������ʵ��VO
   * 
   * @param head
   */
  public void setHead(BuyLargessHVO head) {
    this.setVO(head);
  }

  /**
   * �������������ʵ��VO
   * 
   * @return BuyLargessBVO
   */
  public BuyLargessBVO getBody() {
    return (BuyLargessBVO) this.getVO(BuyLargessBVO.class);
  }

  /**
   * ��������������ʵ��VO
   * 
   * @param body
   */
  public void setBody(BuyLargessBVO body) {
    this.setVO(body);
  }

  /**
   * ����ͼVOת��Ϊ����VO
   * 
   * @return BuyLargessVO
   */
  public BuyLargessVO changeToBuyLargessVO() {
    BuyLargessVO billvo = new BuyLargessVO();
    billvo.setParent(this.getHead());
    BuyLargessBVO[] bodys = new BuyLargessBVO[] {
      this.getBody()
    };
    billvo.setChildrenVO(bodys);
    return billvo;
  }

  /**
   * �������к�
   * 
   * @param paraindex
   */
  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(BuyLargessMatchViewMeta.PARAINDEX, paraindex);
  }

  /**
   * ������к�
   * 
   * @return paraindex
   */
  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(BuyLargessMatchViewMeta.PARAINDEX);
  }
}
