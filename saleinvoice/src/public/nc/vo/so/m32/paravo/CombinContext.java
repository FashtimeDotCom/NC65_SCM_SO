package nc.vo.so.m32.paravo;

import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * �ϲ���ʾ����
 * 
 * @since 6.0
 * @version 2011-12-7 ����09:26:07
 * @author ô��
 */
public class CombinContext {
  /**
   * ��Ʒ�ۿۡ���Ʊ�ۿۡ�˰��
   * ����˰���ۡ�����˰���ۡ�����˰���ۡ�����˰����
   * �����Һ�˰���ۡ���������˰���ۡ�������˰���ۡ�������˰���ۡ����Һ�˰���ۡ����Һ�˰����
   * ��˰���ۡ���˰���ۡ���˰���ۡ���˰����
   */
  public static final String[] COMBIN_AVERAG = new String[] {
    SaleInvoiceBVO.NITEMDISCOUNTRATE, SaleInvoiceBVO.NINVOICEDISRATE,
    SaleInvoiceBVO.NTAXRATE, SaleInvoiceBVO.NORIGTAXPRICE,
    SaleInvoiceBVO.NORIGPRICE, SaleInvoiceBVO.NORIGTAXNETPRICE,
    SaleInvoiceBVO.NORIGNETPRICE, SaleInvoiceBVO.NTAXPRICE,
    SaleInvoiceBVO.NPRICE, SaleInvoiceBVO.NTAXNETPRICE,
    SaleInvoiceBVO.NNETPRICE, SaleInvoiceBVO.NQTNETPRICE,
    SaleInvoiceBVO.NQTPRICE, SaleInvoiceBVO.NQTTAXNETPRICE,
    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTORIGNETPRICE,
    SaleInvoiceBVO.NQTORIGPRICE, SaleInvoiceBVO.NQTORIGTAXNETPRC,
    SaleInvoiceBVO.NQTORIGTAXPRICE
  };

  /**
   * ������������������������˰���˰���
   * ��˰�ϼơ�����˰�������˰�����Ҽ�˰�ϼ�
   * �����ۿ۶���ǰ�����ó�ֽ��
   * �ɱ������ű�����˰�����ű��Ҽ�˰�ϼ�
   * ȫ�ֱ�����˰��ȫ�ֱ��Ҽ�˰�ϼ�\�����ۿ۶�ۿ۶�
   */
  public static final String[] COMBIN_SUMKEYS = new String[] {
    SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NASTNUM, SaleInvoiceBVO.NQTUNITNUM,
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NTAX,
    SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NDISCOUNT,
    SaleInvoiceBVO.NORIGSUBMNY, SaleInvoiceBVO.NGROUPMNY,
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGLOBALMNY,
    SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NORIGDISCOUNT,
    SaleInvoiceBVO.NCALTAXMNY
  };
}
