package nc.vo.so.m30.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Largesstype;

/**
 * 
 * @since 6.0
 * @version 2010-11-11 ����11:05:16
 * @author �ս���
 */

public class LargessProperty {

  /**
   * �ж϶������Ƿ���Ʒ
   * 
   * @param bvo
   * @return
   */
  public boolean isLargess(SaleOrderBVO bvo) {
    boolean result = false;
    UFBoolean largessflag = bvo.getBlargessflag();
    Integer largesstypeflag = bvo.getFlargesstypeflag();
    if ((largessflag != null) && (largessflag.booleanValue())) {
      result = true;
    }
    else {
      if ((largesstypeflag != null)
          && (largesstypeflag.equals(Largesstype.APPORTIONLARGESS.value()))) {
        result = true;
      }
    }

    return result;
  }

  /**
   * �ж϶������Ƿ���й���Ʒ�۸��̯
   * 
   * @param bvo
   * @return
   */
  public boolean isLargessApportion(SaleOrderBVO bvo) {
    boolean result = false;
    Integer largesstypeflag = bvo.getFlargesstypeflag();
    if (largesstypeflag == null) {
      result = false;
    }
    else if (largesstypeflag.equals(Largesstype.NOAPPORTION.value())) {
      result = false;
    }
    else {
      result = true;
    }

    return result;
  }

  /**
   * ���ݱ������ж϶����Ƿ��������Ʒ�۸��̯
   * 
   * @param saleordervo
   * @return
   */
  public boolean isSaleOrderApportion(SaleOrderVO saleordervo) {
    boolean result = false;
    SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      result = this.isLargessApportion(bvo);
      if (result) {
        break;
      }
    }
    return result;
  }
}
