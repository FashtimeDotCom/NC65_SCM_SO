package nc.vo.so.m32.rule;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * VAT�ֶ�Ĭ��ֵ���� ����ͻ�VAT
 * 
 * @since 6.0
 * @version 2012-2-22 ����04:35:12
 * @author ô��
 */
public class VATDefaultRule {

  private IKeyValue keyValue;

  public VATDefaultRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ��������VAT�ֶ�
   */
  public void setVatCodeValue() {
    this.setCustvatCode();
    this.setVATCode();
  }

  /**
   * ���ÿͻ�VAT��
   */
  public void setCustvatCode() {
    String custid =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    String creccountryid =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CRECECOUNTRYID);

    CustSuppVATCodeQueryVO vo =
        new CustSuppVATCodeQueryVO(custid, creccountryid);
    Map<CustSuppVATCodeQueryVO, String> custvatcodes =
        VATBDService.queryCustVATCodeM(new CustSuppVATCodeQueryVO[] {
          vo
        });

    this.keyValue.setHeadValue(SaleInvoiceHVO.VCUSTVATCODE,
        custvatcodes.get(vo));
  }

  /**
   * ���ù���VAT�ֶ�
   */
  private void setVATCode() {
    String pk_prg = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String ctaxcountryid =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CTAXCOUNTRYID);
    OrgVATCodeQueryVO vo = new OrgVATCodeQueryVO(pk_prg, ctaxcountryid);
    Map<OrgVATCodeQueryVO, String> custvatcodes =
        VATBDService.queryOrgVATCodeM(new OrgVATCodeQueryVO[] {
          vo
        });

    this.keyValue.setHeadValue(SaleInvoiceHVO.VVATCODE, custvatcodes.get(vo));
  }
}
