package nc.vo.so.m33.m32.entity;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class SquareInvViewVO extends AbstractDataView {

  private static final long serialVersionUID = -680949127956266342L;

  /**
   * @return ת��Ϊ�ۺ�VO
   */
  public SquareInvVO changeToSquareInvVO() {
    SquareInvVO bill = new SquareInvVO();
    bill.setParent(this.getHead());
    SquareInvBVO[] items = new SquareInvBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  public SquareInvHVO getHead() {
    return (SquareInvHVO) this.getVO(SquareInvHVO.class);
  }

  public SquareInvBVO getItem() {
    return (SquareInvBVO) this.getVO(SquareInvBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(SquareInvVO.class);
  }

  /**
   * ���������������Ƿ����ȡ������
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-6-17 ����04:54:41
   */
  public boolean ifCanCancelSquare() {
    UFDouble nsquareianum; 
    UFDouble nsquarearnum;
    nsquareianum = this.getItem().getNsquareianum();
    nsquarearnum = this.getItem().getNsquarearnum();
    boolean isEnable = false;
    if (((nsquareianum != null) 
        && (nsquareianum.compareTo(UFDouble.ZERO_DBL) != 0))
        || ((nsquarearnum != null) && (nsquarearnum
            .compareTo(UFDouble.ZERO_DBL) != 0))) {
      isEnable = true;
    }
    return isEnable;
  }

  /**
   * ���������������Ƿ���Խ���
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-6-17 ����04:54:41
   */
  public boolean ifCanSquare() {
    UFDouble thisnum = this.getItem().getNthisnum();
    boolean isEnable = false;
    if ((thisnum != null) && (thisnum.compareTo(UFDouble.ZERO_DBL) != 0)) {
      isEnable = true;
    }
    return isEnable;
  }

  public void setHead(SquareInvHVO head) {
    this.setVO(head);
  }

  public void setItem(SquareInvBVO item) {
    this.setVO(item);
  }

  /**
   * �����������������ñ��οɽ�������
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author zhangcheng
   * @time 2010-6-17 ����06:26:02
   */
  public void setNthisnum() {
    UFDouble nsquareianum = this.getItem().getNsquareianum();
    UFDouble nsquarearnum = this.getItem().getNsquarearnum();
    UFDouble nnum = this.getItem().getNnum();
    if (nsquareianum != null) {
      this.getItem().setNthisnum(nnum.sub(nsquareianum));
    }
    else if (nsquarearnum != null) {
      this.getItem().setNthisnum(nnum.sub(nsquarearnum));
    }
    else {
      this.getItem().setNthisnum(nnum);
    }
  }

}
