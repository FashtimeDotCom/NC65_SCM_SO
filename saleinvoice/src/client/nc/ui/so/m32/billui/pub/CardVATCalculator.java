package nc.ui.so.m32.billui.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.scmpub.vattax.vo.CalVatParam;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.itf.scmpub.vattax.ICalculateVat;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.vattax.calvat.CalculatorVatClient;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ǰ̨��VAT�ϲ����������
 * 
 * @since 6.1
 * @version 2012-11-20 17:51:28
 * @author ��ӱ�
 */
public class CardVATCalculator {

  private BillCardPanel cardPanel;

  private Map<String, UFBoolean> isvatcalmap;

  private Map<String, UFBoolean> istaxpriormap;

  private IKeyValue keyvalue;

  /**
   * ������
   * 
   * @param cardPanel
   */
  public CardVATCalculator(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    this.keyvalue = new CardKeyValue(this.cardPanel);
    // ��ʼ������(Ч���Ż�)
    String pk_group = AppContext.getInstance().getPkGroup();
    if (istaxpriormap != null && istaxpriormap.containsKey(pk_group)) {
      return;
    }
    UFBoolean scm13 = SCMParameterUtils.getSCM13(pk_group);
    this.istaxpriormap = new HashMap<String, UFBoolean>();
    this.istaxpriormap.put(pk_group, scm13);

    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    if (isvatcalmap != null && isvatcalmap.containsKey(pk_org)) {
      return;
    }
    UFBoolean so31 = SOSysParaInitUtil.getSO31(pk_org);
    this.isvatcalmap = new HashMap<String, UFBoolean>();
    this.isvatcalmap.put(pk_org, so31);
  }

  /**
   * �༭��VAT�ֶκ�����˰��
   * 
   * @param row
   * @param editkey
   */
  public void calculateVatWhenEditNoVat(int row, String editkey) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(editkey);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatAddOrUpdateNoVat(row, SaleInvoiceBVO.class);
    }
  }

  /**
   * �����к�����˰��
   * 
   * @param row
   */
  public void calculateVatWhenAddLine(int row) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatAddOrUpdateNoVat(row, SaleInvoiceBVO.class);
    }
  }

  /**
   * �༭VAT�ֶκ�����˰��
   * 
   * @param row
   * @param oldValue
   */
  public void calculateVatWhenEditVat(int row, CalVatFieldValues oldValue) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatUpdateVat(row, oldValue, SaleInvoiceBVO.class);
    }
  }

  /**
   * ���¼��������е�VAT��Ϣ
   */
  public void calVatAll() {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      ICalculateVat<SaleInvoiceBVO> config =
          new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatAll(SaleInvoiceBVO.class);
    }
  }

  /**
   * ɾ��ʱ���¼���VAT�ϲ���Ϣ
   * 
   * @param oldValue
   */
  public void calVatWhenDeleteLine(CalVatFieldValues oldValue) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatDelete(oldValue, SaleInvoiceBVO.class);
    }
  }

  /**
   * ���ָ���е�Ŀǰ�����ϵ�VAT�ֶ���Ϣ����Ҫ�Լ�����Ӧ�ֶε�oldvalue
   * 
   * @param row
   * @return CalVatFieldValues
   */
  public CalVatFieldValues getVatFieldValues(int row) {
    CalVatFieldValues vatvalue = new CalVatFieldValues();
    IKeyValue keyvalue = new CardKeyValue(this.cardPanel);
    // ��Ʒ
    UFBoolean largess =
        keyvalue.getBodyUFBooleanValue(row, SaleInvoiceBVO.BLARGESSFLAG);
    vatvalue.setBlargessflag(largess);
    // ˰��
    String taxcode =
        keyvalue.getBodyStringValue(row, SaleInvoiceBVO.CTAXCODEID);
    vatvalue.setCtaxcodeid(taxcode);

    // ��������
    // Integer buysellflag =
    // keyvalue.getHeadIntegerValue(SaleInvoiceHVO.FBUYSELLFLAG);
    // vatvalue.setFbuysellflag(buysellflag);

    // ��˰���
    Integer taxtypeflag =
        keyvalue.getBodyIntegerValue(row, SaleInvoiceBVO.FTAXTYPEFLAG);
    vatvalue.setFtaxtypeflag(taxtypeflag);
    // ˰��
    UFDouble taxrate =
        keyvalue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NTAXRATE);
    vatvalue.setNtaxrate(taxrate);

    return vatvalue;
  }

  private CalVatParam getVatParam(String editkey) {
    IRelationForItems item = new RelationItemForCal();
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean istaxfirst = istaxpriormap.get(pk_org);
    CalVatParam vatpara =
        new CalVatParam(item, editkey,
            istaxfirst == null ? UFBoolean.FALSE.booleanValue()
                : istaxfirst.booleanValue());
    String[] groupkeys =
        new String[] {
          SaleInvoiceBVO.CTAXCODEID, SaleInvoiceBVO.NTAXRATE,
          SaleInvoiceBVO.FTAXTYPEFLAG, SaleInvoiceBVO.BLARGESSFLAG
        };
    vatpara.addGroupings(groupkeys);
    return vatpara;
  }

}
