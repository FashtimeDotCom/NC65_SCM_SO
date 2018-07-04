package nc.ui.so.m30.revise.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * ���۶����޶�ʱ����ճ������Ҫ���һЩ��Ϣ
 * 
 * @since 6.33
 * @version 2014-9-9 ����6:11:26
 * @author �ű���
 */
public class RevisePasteLineRule {

  private static final String[] CLEARITEMS = new String[] {
    // TS������ID
    SaleOrderBVO.TS,
    SaleOrderBVO.CSALEORDERBID,

    SaleOrderBVO.FROWSTATUS,
    SaleOrderBVO.VBREVISEREASON,
    // ��־
    SaleOrderBVO.BBOUTENDFLAG,
    SaleOrderBVO.BBSENDENDFLAG,
    SaleOrderBVO.BBINVOICENDFLAG,
    SaleOrderBVO.BBCOSTSETTLEFLAG,
    SaleOrderBVO.BBARSETTLEFLAG,
    SaleOrderBVO.BARRANGEDFLAG,
    SaleOrderBVO.CARRANGEPERSONID,
    SaleOrderBVO.TLASTARRANGETIME,
    SaleOrderBVO.FRETEXCHANGE,
    SaleOrderBVO.CEXCHANGESRCRETID,
    // �����ۼ�����
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALNOTOUTNUM,
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM,
    SaleOrderBVO.NTOTALRUSHNUM, SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARMNY, SaleOrderBVO.NTOTALARMNY,
    SaleOrderBVO.NTOTALPAYMNY, SaleOrderBVO.NTOTALPLONUM,
    SaleOrderBVO.NARRANGESCORNUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    SaleOrderBVO.NARRANGETOORNUM, SaleOrderBVO.NARRANGETOAPPNUM,
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPONUM,
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALTRADENUM,
    SaleOrderBVO.NREQRSNUM,

    SaleOrderBVO.BBINDFLAG, SaleOrderBVO.CBINDSRCID,
    SaleOrderBVO.CLARGESSSRCID, SaleOrderBVO.VCLOSEREASON
  };

  private static final String[] OFFSETCLEARITEMS = new String[] {
    // ����
    SaleOrderBVO.NNUM,
    SaleOrderBVO.NASTNUM,
    SaleOrderBVO.NQTUNITNUM,
    // ԭ�ҵ���
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // ��ԭ�ҵ���
    SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE,
    // ���
    SaleOrderBVO.NORIGMNY,
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT,
    SaleOrderBVO.NCALTAXMNY,
    // ���ҵ���
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NQTTAXPRICE,
    SaleOrderBVO.NQTPRICE,
    // �����ҵ���
    SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
    SaleOrderBVO.NTAXNETPRICE,
    // ���ҽ��
    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NDISCOUNT,
    // ���Ž��
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
    // ȫ�ֽ��
    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY,
    // ��ֽ��
    SaleOrderBVO.NORIGSUBMNY, SaleOrderBVO.NBFORIGSUBMNY
  };

  public List<String> getClearItems() {
    List<String> list = new ArrayList<String>();
    for (String key : RevisePasteLineRule.CLEARITEMS) {
      list.add(key);
    }
    return list;
  }

  public List<String> getClearItemsWhenOffSet() {
    List<String> list = new ArrayList<String>();
    for (String key : RevisePasteLineRule.OFFSETCLEARITEMS) {
      list.add(key);
    }
    return list;
  }

}
