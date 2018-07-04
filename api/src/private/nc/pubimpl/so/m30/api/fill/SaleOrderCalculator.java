package nc.pubimpl.so.m30.api.fill;

import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.fill.pricemny.ICalculator;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;

/**
 * @description
 * ���۶����������������ۡ����ӿ�ʵ��
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-12-10 ����1:35:35
 * @author ����
 */
public class SaleOrderCalculator implements ICalculator {

  @Override
  public void calculator(IBill bill, int row, String editkey) {
    SaleOrderVOCalculator vocalcultor =
        new SaleOrderVOCalculator((SaleOrderVO) bill);
    vocalcultor.calculate(row, editkey);
  }
}
