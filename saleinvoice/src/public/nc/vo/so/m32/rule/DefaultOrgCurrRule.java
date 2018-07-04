package nc.vo.so.m32.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class DefaultOrgCurrRule {

  private IKeyValue keyValue;

  /**
   * DefaultOrgCurrValue �Ĺ�����
   * 
   * @param keyValue
   */
  public DefaultOrgCurrRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setDefautOrgCurrByPk() {
    // ��Ʊ��֯
    String pk_org = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    // ���ÿ�Ʊ��֯Ĭ�ϱ�λ�ҡ�
    String curr = null;
    curr = OrgUnitPubService.queryOrgCurrByPk(pk_org);

    this.keyValue.setHeadValue(SaleInvoiceHVO.CORIGCURRENCYID, curr);
    this.keyValue.setHeadValue(SaleInvoiceHVO.CCURRENCYID, curr);
  }
}
