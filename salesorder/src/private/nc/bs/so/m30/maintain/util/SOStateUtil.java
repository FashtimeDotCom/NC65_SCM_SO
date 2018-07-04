package nc.bs.so.m30.maintain.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SOStateUtil {

  /**
   * �ж����۶�����ͼVO�Ƿ���й����ó�ֻ�����Ʒ�Ҹ���
   * 1.���ó���ҷ��ó�ֽ�Ϊ�ջ�0��
   * 2.��Ʒ�Ҹ�
   * true:û�н��й������������������Ҫ��д
   * false:��Ҫ��д
   * 
   * @param vo
   * @return
   */
  public static boolean isNotOffsetAndlrgcash(SaleOrderViewVO vo) {
    SaleOrderBVO bvo = vo.getBody();
    UFBoolean blrgcashflag = bvo.getBlrgcashflag();
    UFDouble norigsubmny = bvo.getNorigsubmny();
    if ((blrgcashflag != null && blrgcashflag.booleanValue())
        || (!MathTool.isZero(norigsubmny) && !blrgcashflag.booleanValue())) {
      return false;
    }
    return true;
  }

}
