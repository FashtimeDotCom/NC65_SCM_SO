package nc.vo.so.report.outprofit;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * ���۳���ë��������ͼMETA
 * 
 * @since 6.3
 * @version 2012-7-27 ����8:53:59
 * @author ������
 */

public class OutProfitViewMeta extends DataViewMeta {

  /**
   * ���۳��ⵥ��ʵ�������ֶ���
   */
  public static final String[] SALEOUT_HKEYS = new String[] {
    OutProfitViewVO.CGENERALHID, OutProfitViewVO.CSALEORGOID,
    OutProfitViewVO.CDPTID, OutProfitViewVO.CBIZID,
    OutProfitViewVO.CCUSTOMERID, OutProfitViewVO.VBILLCODE,
    OutProfitViewVO.CTRANTYPEID, OutProfitViewVO.VDEF1, OutProfitViewVO.VDEF2,
    OutProfitViewVO.VDEF3, OutProfitViewVO.VDEF4, OutProfitViewVO.VDEF5,
    OutProfitViewVO.VDEF6, OutProfitViewVO.VDEF7, OutProfitViewVO.VDEF8,
    OutProfitViewVO.VDEF9, OutProfitViewVO.VDEF10, OutProfitViewVO.VDEF11,
    OutProfitViewVO.VDEF12, OutProfitViewVO.VDEF13, OutProfitViewVO.VDEF14,
    OutProfitViewVO.VDEF15, OutProfitViewVO.VDEF16, OutProfitViewVO.VDEF17,
    OutProfitViewVO.VDEF18, OutProfitViewVO.VDEF19, OutProfitViewVO.VDEF20
  };

  /**
   * ���۳��ⵥ��ʵ�������ֶ���
   */
  public static final String[] SALEOUT_BKEYS = {
    OutProfitViewVO.CGENERALBID, OutProfitViewVO.CMATERIALOID,
    OutProfitViewVO.CMATERIALVID, OutProfitViewVO.CUNITID,
    OutProfitViewVO.VBATCHCODE, OutProfitViewVO.CORIGCURRENCYID,
    OutProfitViewVO.NNUM, OutProfitViewVO.NQTORIGNETPRICE,
    OutProfitViewVO.PK_ORG, OutProfitViewVO.CBODYWAREHOUSEID,
    OutProfitViewVO.CFANACEORGOID, OutProfitViewVO.VBDEF1,
    OutProfitViewVO.VBDEF2, OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
    OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6, OutProfitViewVO.VBDEF7,
    OutProfitViewVO.VBDEF8, OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
    OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12, OutProfitViewVO.VBDEF13,
    OutProfitViewVO.VBDEF14, OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
    OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18, OutProfitViewVO.VBDEF19,
    OutProfitViewVO.VBDEF20, OutProfitViewVO.FLARGESS
  };

  /**
   * ��չString�ֶ�
   * ��ʱ���ص�String�ֶ�
   */
  public static final String[] EXTEND_STRKEYS = {

    OutProfitViewVO.PK_CUSTCLASS, OutProfitViewVO.PK_CUSTSALECLASS,
    OutProfitViewVO.PK_AREACL, OutProfitViewVO.PK_MARBASCLASS,
    OutProfitViewVO.PK_MARSALECLASS, OutProfitViewVO.CCHANNELTYPEID,
  };

  /**
   * ��չUFDouble�ֶ�
   * ��ʱ���ص�UFDouble�ֶ�
   */
  public static final String[] EXTEND_UFKEYS = {
    OutProfitViewVO.NMAINNUM, OutProfitViewVO.NSHOULDRECEIVNUM,
    OutProfitViewVO.NTOTALRECEIVMNY, OutProfitViewVO.NNOTAXMNY,
    OutProfitViewVO.NNOTAXPRICE, OutProfitViewVO.NCOSTNUM,
    OutProfitViewVO.NCOSTMNY, OutProfitViewVO.NTOTALCOSTMNY,
    OutProfitViewVO.NCOSTPRICE, OutProfitViewVO.NPROFITMNY,
    OutProfitViewVO.NPROFITRATE, OutProfitViewVO.NCOST,
    OutProfitViewVO.NOCOSTNUM

  };

  /**
   * ��ʱ���صĳ��ⵥ�ֶ�
   */
  public static final String[] TMPTABLE_OUTKEYS = new String[] {
    OutProfitViewVO.CSALEORGOID, OutProfitViewVO.CDPTID,
    OutProfitViewVO.CBIZID, OutProfitViewVO.CCUSTOMERID,
    OutProfitViewVO.CMATERIALOID, OutProfitViewVO.CMATERIALVID,
    OutProfitViewVO.CUNITID, OutProfitViewVO.CTRANTYPEID,
    OutProfitViewVO.VBILLCODE, OutProfitViewVO.VBATCHCODE,
    OutProfitViewVO.CORIGCURRENCYID, OutProfitViewVO.NNUM
  };

  /**
   * ��ʱ���صĳ��ⵥ���Զ����ֶ�
   */
  public static final String[] TMPTABLE_OUTVFKEYS = new String[] {
    OutProfitViewVO.VDEF1, OutProfitViewVO.VDEF2, OutProfitViewVO.VDEF3,
    OutProfitViewVO.VDEF4, OutProfitViewVO.VDEF5, OutProfitViewVO.VDEF6,
    OutProfitViewVO.VDEF7, OutProfitViewVO.VDEF8, OutProfitViewVO.VDEF9,
    OutProfitViewVO.VDEF10, OutProfitViewVO.VDEF11, OutProfitViewVO.VDEF12,
    OutProfitViewVO.VDEF13, OutProfitViewVO.VDEF14, OutProfitViewVO.VDEF15,
    OutProfitViewVO.VDEF16, OutProfitViewVO.VDEF17, OutProfitViewVO.VDEF18,
    OutProfitViewVO.VDEF19, OutProfitViewVO.VDEF20, OutProfitViewVO.VBDEF1,
    OutProfitViewVO.VBDEF2, OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
    OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6, OutProfitViewVO.VBDEF7,
    OutProfitViewVO.VBDEF8, OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
    OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12, OutProfitViewVO.VBDEF13,
    OutProfitViewVO.VBDEF14, OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
    OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18, OutProfitViewVO.VBDEF19,
    OutProfitViewVO.VBDEF20
  };

  /**
   *
   */
  public OutProfitViewMeta() {
    // ͨ��ʵ��VO��Class���ʵ��Ԫ���ݵ�ָ�����Ե���ͼԪ������
    super.add(SaleOutHeadVO.class, OutProfitViewMeta.SALEOUT_HKEYS);
    this.add(SaleOutBodyVO.class, OutProfitViewMeta.SALEOUT_BKEYS);
    // ָ��ʵ��Ԫ����֮��Ĺ�����ϵ
    this.addRelation(SaleOutHeadVO.class, OutProfitViewVO.CGENERALHID,
        SaleOutBodyVO.class, OutProfitViewVO.CGENERALHID);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    for (String field : OutProfitViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OutProfitViewMeta.EXTEND_UFKEYS) {
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
