package nc.ui.so.m32.billui.rule;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * ���۷�Ʊ�������޸Ŀɱ༭�ֶ�
 * 
 * @since 6.36
 * @version 2015-1-6 ����5:37:30
 * @author wangshu6
 */
public class EditableAndRewiteItems {

  // ע���ͷ���Ա༭���ֶ�
  public static final String[] HEADEDITABLEITEMKEY = new String[] {
    /** ---------- ��ͷ ---------- */
    SaleInvoiceHVO.VNOTE, 
    /** -------��ͷ�Զ�����------ **/
    SaleInvoiceHVO.VDEF1, SaleInvoiceHVO.VDEF2, SaleInvoiceHVO.VDEF3,
    SaleInvoiceHVO.VDEF4, SaleInvoiceHVO.VDEF5, SaleInvoiceHVO.VDEF6,
    SaleInvoiceHVO.VDEF7, SaleInvoiceHVO.VDEF8, SaleInvoiceHVO.VDEF9,
    SaleInvoiceHVO.VDEF10, SaleInvoiceHVO.VDEF11, SaleInvoiceHVO.VDEF12,
    SaleInvoiceHVO.VDEF13, SaleInvoiceHVO.VDEF14, SaleInvoiceHVO.VDEF15,
    SaleInvoiceHVO.VDEF16, SaleInvoiceHVO.VDEF17, SaleInvoiceHVO.VDEF18,
    SaleInvoiceHVO.VDEF19, SaleInvoiceHVO.VDEF20,
    
  };

  // ע������޶����Ա༭���ֶ�
  public static final String[] BODYEDITABLEITEMKEY = new String[] {

    /** ---------- ���� ---------- */
    
    // ��������������������
    SaleInvoiceBVO.NASTNUM,
    SaleInvoiceBVO.NNUM,
    SaleInvoiceBVO.VCHANGERATE,
    // ��λ
    SaleInvoiceBVO.CASTUNITID,
    // ���ۻ����ʡ�˰�ʡ���������
    SaleInvoiceBVO.VQTUNITRATE,
    SaleInvoiceBVO.NTAXRATE,
    SaleInvoiceBVO.NQTUNITNUM,
    //����˰���ۡ�����˰����  ����˰���ۡ�����˰���ۡ�
    SaleInvoiceBVO.NORIGTAXPRICE,
    SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE,
    SaleInvoiceBVO.NORIGNETPRICE,
    
    //��˰���� ��˰���ۡ���˰���ۡ���˰����
    SaleInvoiceBVO.NQTORIGTAXPRICE,
    SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC,
    SaleInvoiceBVO.NQTORIGNETPRICE,
    // ��˰����˰�ϼơ���˰���
    SaleInvoiceBVO.NORIGMNY,
    SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NCALTAXMNY,

    /** -------�����Զ�����------ **/
    SaleInvoiceBVO.VBDEF1, SaleInvoiceBVO.VBDEF2, SaleInvoiceBVO.VBDEF3,
    SaleInvoiceBVO.VBDEF4, SaleInvoiceBVO.VBDEF5, SaleInvoiceBVO.VBDEF6,
    SaleInvoiceBVO.VBDEF7, SaleInvoiceBVO.VBDEF8, SaleInvoiceBVO.VBDEF9,
    SaleInvoiceBVO.VBDEF10, SaleInvoiceBVO.VBDEF11, SaleInvoiceBVO.VBDEF12,
    SaleInvoiceBVO.VBDEF13, SaleInvoiceBVO.VBDEF14, SaleInvoiceBVO.VBDEF15,
    SaleInvoiceBVO.VBDEF16, SaleInvoiceBVO.VBDEF17, SaleInvoiceBVO.VBDEF18,
    SaleInvoiceBVO.VBDEF19, SaleInvoiceBVO.VBDEF20
  };


}
