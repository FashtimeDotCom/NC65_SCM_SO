package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ����Ĭ��ֵ���ù�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-23 ����03:56:31
 */
public class HeadDefaultValue {

  private final UFDouble hundred = new UFDouble(100);

  private IKeyValue keyValue;

  private LoginContext logctx;

  /**
   * HeadDefaultValue �Ĺ�����
   * 
   * @param keyValue
   * @param logctx
   */
  public HeadDefaultValue(IKeyValue keyValue, LoginContext logctx) {
    this.keyValue = keyValue;
    this.logctx = logctx;
  }

  /**
   * ���������������������۷�Ʊ����Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author ��ӱ�
   * @time 2010-4-23 ����03:32:16
   */
  public void setDefaultValue() {
    // ����
    this.keyValue.setHeadValue(SaleInvoiceHVO.PK_GROUP,
        this.logctx.getPk_group());
    // ��Ʊ�ۿ� 100
    this.keyValue.setHeadValue(SaleInvoiceHVO.NHVOICEDISRATE, this.hundred);
    // ����״̬
    this.keyValue.setHeadValue(SaleInvoiceHVO.FSTATUSFLAG,
        BillStatus.FREE.value());
    // ��ӡ����
    this.keyValue.setHeadValue(SaleInvoiceHVO.IPRINTCOUNT, Integer.valueOf(0));
//    // ������
//    this.keyValue.setHeadValue(SaleInvoiceHVO.CREATOR,
//        this.logctx.getPk_loginUser());
//    // �Ƶ���
//    this.keyValue.setHeadValue(SaleInvoiceHVO.BILLMAKER,
//        this.logctx.getPk_loginUser());
  }
}
