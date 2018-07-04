package nc.vo.so.m30.util;

import nc.itf.scmpub.reference.uap.bd.measuredoc.MeasureDocService;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * ���۶��������������㾫�ȴ�������
 * @author zhangby5
 *
 */
public class SaleOrderNumScaleUtil {
  
  /**
   * ���ص�ǰ����VO�ĸ���λ��Ӧ�ľ��ȣ����þ���Ϊ�գ���ȡ���������ľ���
   * @param body ���۶�������VO
   * @return
   */
  public static int getNumPower(SaleOrderBVO body) {

    Integer[] scale = MeasureDocService.getMeasPrecision(new String[] {
      body.getCastunitid()
    });
    if (scale != null && scale.length > 0) {
      return scale[0].intValue();
    }

    return body.getNqtunitnum().getPower();
  }
  
  /**
   * ��ȡ���۵�λ�����ľ��ȣ������۵�λ����Ϊ����ȡ������������
   * @param body
   * @return
   */
  public static int getNqtunitnumPower(SaleOrderBVO body) {

    Integer[] scale = MeasureDocService.getMeasPrecision(new String[] {
      body.getCqtunitid()
    });
    if (scale != null && scale.length > 0) {
      return scale[0].intValue();
    }
    return body.getNqtunitnum().getPower();
  }
  
}
