package nc.vo.so.report.ordersummary;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * ���۶���ִ�л�����ͼMETA
 * 
 * @since 6.3
 * @version 2012-10-18 13:28:50
 * @author ������
 */
public class OrderSummaryViewMeta extends DataViewMeta {

  /**
   * ���۶�����ʵ�������ֶ���
   */
  public static final String[] SALEORDER_HKEYS = new String[] {
    OrderSummaryViewVO.CSALEORDERID, OrderSummaryViewVO.PK_ORG,
    OrderSummaryViewVO.VBILLCODE, OrderSummaryViewVO.CDEPTID,
    OrderSummaryViewVO.CEMPLOYEEID, OrderSummaryViewVO.CCUSTOMERID,
    OrderSummaryViewVO.CTRANTYPEID
  };

  /**
   * ���۶��ⵥ��ʵ�������ֶ���
   */
  public static final String[] SALEORDER_BKEYS = {
    OrderSummaryViewVO.CSALEORDERBID, OrderSummaryViewVO.CSENDSTOCKORGID,
    OrderSummaryViewVO.CMATERIALID, OrderSummaryViewVO.CUNITID,
    OrderSummaryViewVO.VBATCHCODE, OrderSummaryViewVO.NNUM,
    OrderSummaryViewVO.NORIGMNY, OrderSummaryViewVO.NORIGTAXMNY,
    OrderSummaryViewVO.NTAX, OrderSummaryViewVO.NORIGDISCOUNT,
    OrderSummaryViewVO.BLARGESSFLAG, OrderSummaryViewVO.BLABORFLAG,
    OrderSummaryViewVO.BDISCOUNTFLAG, OrderSummaryViewVO.BBOUTENDFLAG,
    OrderSummaryViewVO.CORIGCURRENCYID, SaleOrderBVO.CSETTLEORGID,
    SaleOrderBVO.BBCOSTSETTLEFLAG, SaleOrderBVO.BBARSETTLEFLAG
  };

  /**
   * ���۶��ⵥִ�б������ֶ���
   */
  public static final String[] SALEORDER_EXEKEYS = {
    OrderSummaryViewVO.NOUTNUM, OrderSummaryViewVO.NOUTSIGNNUM,
    OrderSummaryViewVO.NNORWASTNUM, OrderSummaryViewVO.NINVOICENUM
  };

  /**
   * ��չString�ֶ�
   * ��ʱ���ص�String�ֶ�
   */
  public static final String[] EXTEND_STRKEYS = {
    OrderSummaryViewVO.PK_CUSTCLASS, OrderSummaryViewVO.PK_CUSTSALECLASS,
    OrderSummaryViewVO.PK_AREACL, OrderSummaryViewVO.PK_MARBASCLASS,
    OrderSummaryViewVO.PK_MARSALECLASS, OrderSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * ��չUFDouble�ֶ�
   * ��ʱ���ص�UFDouble�ֶ�
   */
  public static final String[] EXTEND_UFKEYS = {
    OrderSummaryViewVO.NOUTNUM, OrderSummaryViewVO.NOUTSIGNNUM,
    OrderSummaryViewVO.NNORWASTNUM, OrderSummaryViewVO.NWAITOUTNUM,
    OrderSummaryViewVO.NINVOICENUM, OrderSummaryViewVO.NINVOICEORIGTAXMNY,
    OrderSummaryViewVO.NSHOULDRECEIVNUM, OrderSummaryViewVO.NSHOULDRECEIVMNY,
    OrderSummaryViewVO.NTOTALRECEIVMNY, OrderSummaryViewVO.NTOTALCOSTMNY,
    /*OrderSummaryViewVO.NCOSTPRICE,*/ SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALARNUM
  };

  /**
   * ��ʱ���ص����۶����ֶ�
   */
  public static final String[] TMPTABLE_ORDERKEYS = new String[] {
    OrderSummaryViewVO.PK_ORG, OrderSummaryViewVO.CDEPTID,
    OrderSummaryViewVO.CEMPLOYEEID, OrderSummaryViewVO.CCUSTOMERID,
    OrderSummaryViewVO.CTRANTYPEID, OrderSummaryViewVO.CSENDSTOCKORGID,
    OrderSummaryViewVO.CMATERIALID, OrderSummaryViewVO.CUNITID,
    OrderSummaryViewVO.VBATCHCODE, OrderSummaryViewVO.CORIGCURRENCYID,
    OrderSummaryViewVO.NNUM, OrderSummaryViewVO.NORIGMNY,
    OrderSummaryViewVO.NORIGTAXMNY, OrderSummaryViewVO.NTAX,
    OrderSummaryViewVO.NORIGDISCOUNT, OrderSummaryViewVO.BLARGESSFLAG,
    OrderSummaryViewVO.BLABORFLAG, OrderSummaryViewVO.BDISCOUNTFLAG
  };

  /**
   * ���췽��
   */
  public OrderSummaryViewMeta() {

    // ͨ��ʵ��VO��Class���ʵ��Ԫ���ݵ�ָ�����Ե���ͼԪ������
    super(SaleOrderHVO.class, OrderSummaryViewMeta.SALEORDER_HKEYS);
    this.add(SaleOrderBVO.class, OrderSummaryViewMeta.SALEORDER_BKEYS);
    // ָ��ʵ��Ԫ����֮��Ĺ�����ϵ
    this.addRelation(SaleOrderHVO.class, OrderSummaryViewVO.CSALEORDERID,
        SaleOrderBVO.class, OrderSummaryViewVO.CSALEORDERID);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    for (String field : OrderSummaryViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OrderSummaryViewMeta.EXTEND_UFKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

  private void addAttribute(String itemkey, JavaType type) {
    Attribute attribute = new Attribute(itemkey, null, null);
    attribute.setJavaType(type);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }
}
