package nc.ui.so.m30.arrange.push;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.billref.push.ISourceVOStrategy;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �����������۶���ˢ�����ֲ���
 * 
 * @since 6.0
 * @version 2011-8-9 ����10:21:40
 * @author ��־ΰ
 */
public class SourceVOStrategy implements ISourceVOStrategy {

  /**
   * ��Ҫ���µ��ݣ�ɾ���й��˵�
   */
  @Override
  public Object[] getUpdateVO(Object[] sourceVOs) {
    List<SaleOrderVO> newbillList = new ArrayList<SaleOrderVO>();
    for (Object obj : sourceVOs) {
      SaleOrderVO bill = (SaleOrderVO) obj;
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      List<SaleOrderBVO> newBodyList = new ArrayList<SaleOrderBVO>();
      for (SaleOrderBVO body : bodys) {
        newBodyList.add(body);
      }
      if (newBodyList.size() > 0) {
        SaleOrderBVO[] newBodys =
            newBodyList.toArray(new SaleOrderBVO[newBodyList.size()]);
        SaleOrderHVO newHead = (SaleOrderHVO) bill.getParentVO().clone();
        SaleOrderVO newBill = new SaleOrderVO();
        newBill.setParentVO(newHead);
        newBill.setChildrenVO(newBodys);
        newbillList.add(newBill);
      }
    }
    return newbillList.toArray(new SaleOrderVO[newbillList.size()]);
  }

  /**
   * ��Ҫɾ�����ݣ������ж������㰲�������ĵ���
   * modify by zhangby5 ƽ̨ɾ������ʱmodel�������������⣬���º����е����ݶ�û���ˣ�
   * �˴���Ϊ��ɾ��model�е�����
   */
  @Override
  public Object[] getDeleteVO(Object[] sourceVOs) {
    return new SaleOrderVO[0];
  }

}
