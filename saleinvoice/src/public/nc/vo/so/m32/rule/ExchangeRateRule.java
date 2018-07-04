package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�۱�����ҵ�����ʵ�ֻ�ȡ��ǰ�۱�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-22 ����08:11:18
 */
public class ExchangeRateRule {

  private IKeyValue keyValue;

  public ExchangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ����������������õ�ǰ�������ڶ�Ӧ���۱����ʡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author ��ӱ�
   * @time 2010-4-21 ����11:48:13
   */
  public void calcExchangeRate() {
    // ��������
    UFDate billdate =
        this.keyValue.getHeadUFDateValue(SaleInvoiceHVO.DBILLDATE);
    // ԭ�ұ���
    String orgcurrency =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);
    // ��λ��
    String currency =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);

    UFDouble changestrate = null;
    UFDouble groupchangestrate = null;
    UFDouble globlchangestrate = null;
    if (!VOChecker.isEmpty(orgcurrency) && !VOChecker.isEmpty(currency)) {
      String pk_org = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
      String pk_group =
          this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
      changestrate =
          SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, orgcurrency, currency,
              billdate);
      if (this.isCurToGroupMoney(pk_group)) {
        groupchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(currency, billdate);
      }
      else if (this.isOrigCurToGroupMoney(pk_group)) {
        groupchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);
      }

      if (this.isCurToGlobalMoney()) {
        globlchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(currency, billdate);
      }
      else if (this.isOrigCurToGlobalMoney()) {
        globlchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);
      }

    }
    this.keyValue.setHeadValue(SaleInvoiceHVO.NEXCHANGERATE, changestrate);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NGLOBALEXCHGRATE,
        globlchangestrate);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NGROUPEXCHGRATE,
        groupchangestrate);
  }

  /**
   * ȫ�ֻ��ʻ�����֯��λ�Ҽ���
   * 
   * @return
   */
  private boolean isCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && !SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * ���Ż��ʻ�����֯��λ�Ҽ���
   * 
   * @return
   */
  private boolean isCurToGroupMoney(String pk_group) {
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && !SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }

  /**
   * ȫ�ֻ��ʻ�����֯ԭ�Ҽ���
   * 
   * @return
   */
  private boolean isOrigCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * ���Ż��ʻ���ԭ�Ҽ���
   * 
   * @return
   */
  private boolean isOrigCurToGroupMoney(String pk_group) {
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }
}
