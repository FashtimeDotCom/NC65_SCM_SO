package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�ӱ�Ĭ��ֵ���ù�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-23 ����03:56:56
 */
public class BodyDefaultValue {

  private final UFDouble hundred = new UFDouble(100);

  private IKeyValue keyValue;

  /**
   * BodyDefaultValue �Ĺ�����
   * 
   * @param keyValue
   */
  public BodyDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �������������������۷�Ʊ�ӱ�����������Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author ��ӱ�
   * @time 2010-4-26 ����05:03:44
   */
  public void setAllDefautValue() {
    int rowcount = this.keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      this.setDefaultValue(i);
    }
  }

  /**
   * �������������������۷�Ʊ�ӱ��index������Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-23 ����03:57:16
   */
  public void setDefaultValue(int index) {
    // ��Ʊ��֯��
    Object pk_org = this.keyValue.getHeadValue(SaleInvoiceHVO.PK_ORG);
    Object pk_group = this.keyValue.getHeadValue(SaleInvoiceHVO.PK_GROUP);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.PK_ORG, pk_org);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.PK_GROUP, pk_group);
    // ��������
    Object billdate = this.keyValue.getHeadValue(SaleInvoiceHVO.DBILLDATE);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.DBILLDATE, billdate);
    // �����ۿۡ���Ʒ�ۿ�
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NDISCOUNTRATE,
        this.hundred);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NITEMDISCOUNTRATE,
        this.hundred);
    // ��Ʊ�ۿ�
    Object hinvoicedisrate =
        this.keyValue.getHeadValue(SaleInvoiceHVO.NHVOICEDISRATE);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NINVOICEDISRATE,
        hinvoicedisrate);
    // ���ó�ֽ��
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NORIGSUBMNY,
        UFDouble.ZERO_DBL);
  }

  /**
   * ��������������������ķ�Ʊ�ӱ�VO����Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param newbvo
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-23 ����04:37:19
   */
  public void setVODefaultValue(SaleInvoiceBVO newbvo) {
    // ��Ʊ��֯��
    String pk_org = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String pk_group = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    if (null == newbvo.getCarorgid()) {
      newbvo.setCarorgid(pk_org);
      newbvo.setCarorgvid(this.keyValue
          .getHeadStringValue(SaleInvoiceHVO.PK_ORG_V));
    }
    newbvo.setPk_org(pk_org);
    newbvo.setPk_group(pk_group);
    // ��������
    UFDate billdate =
        this.keyValue.getHeadUFDateValue(SaleInvoiceHVO.DBILLDATE);
    newbvo.setDbilldate(billdate);
    // �����ۿۡ���Ʒ�ۿ�
    newbvo.setNdiscountrate(this.hundred);
    newbvo.setNitemdiscountrate(this.hundred);
    // ��Ʊ�ۿ�
    UFDouble hinvoicedisrate =
        this.keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NHVOICEDISRATE);
    newbvo.setNinvoicedisrate(hinvoicedisrate);
    // ���ó�ֽ��
    newbvo.setNorigsubmny(new UFDouble(0));
  }
}
