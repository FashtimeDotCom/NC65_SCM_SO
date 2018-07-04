package nc.vo.so.m32.util;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.TotalCalcateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>VO���������ͷ�ϼ����������Ĺ�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-22 ����08:43:07
 */
public class HeadTotalCalcUtil {

  private static HeadTotalCalcUtil instancce = new HeadTotalCalcUtil();

  /**
   * VOTotalCalculator �Ĺ�����
   */
  private HeadTotalCalcUtil() {
    // ȱʡ���췽��
  }

  /**
   * �����������������ص���ģʽʵ����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-9 ����03:42:48
   */
  public static HeadTotalCalcUtil getInstance() {
    return HeadTotalCalcUtil.instancce;
  }

  public void calcHeadTotalValue(IKeyValue keyvalue) {

    TotalCalcateRule calcrule = null;
    calcrule = new TotalCalcateRule(keyvalue);
    calcrule.calcHeadTotal();

  }

  /**
   * �������������������ͷ�ϼ�ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoices
   *          <p>
   * @author fengjb
   * @time 2010-8-9 ����03:43:08
   */
  public void calcHeadTotalValue(SaleInvoiceVO[] voInvoices) {

    IKeyValue vokeyvalue = null;
    TotalCalcateRule calcrule = null;
    for (SaleInvoiceVO invoicevo : voInvoices) {
      vokeyvalue = new VOKeyValue<SaleInvoiceVO>(invoicevo);
      calcrule = new TotalCalcateRule(vokeyvalue);
      calcrule.calcHeadTotal();
    }
  }
}
