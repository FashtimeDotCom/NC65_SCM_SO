package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.entry.SaleInvoiceBVOCode;

public enum SOInvoiceCombindefrule {

  VBDEF1(SaleInvoiceBVOCode.VBDEF1, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0061")/*�Զ�����1*/),
  VBDEF2(SaleInvoiceBVOCode.VBDEF2, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0072")/*�Զ�����2*/),
  VBDEF3(SaleInvoiceBVOCode.VBDEF3, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0074")/*�Զ�����3*/),
  VBDEF4(SaleInvoiceBVOCode.VBDEF4, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0075")/*�Զ�����4*/),
  VBDEF5(SaleInvoiceBVOCode.VBDEF5, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0076")/*�Զ�����5*/),
  VBDEF6(SaleInvoiceBVOCode.VBDEF6, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0077")/*�Զ�����6*/),
  VBDEF7(SaleInvoiceBVOCode.VBDEF7, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0078")/*�Զ�����7*/),
  VBDEF8(SaleInvoiceBVOCode.VBDEF8, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0079")/*�Զ�����8*/),
  VBDEF9(SaleInvoiceBVOCode.VBDEF9, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0225")/*�Զ�����19*/),
  VBDEF10(SaleInvoiceBVOCode.VBDEF10, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0062")/*�Զ�����10*/),
  VBDEF11(SaleInvoiceBVOCode.VBDEF11, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0063")/*�Զ�����11*/),
  VBDEF12(SaleInvoiceBVOCode.VBDEF12, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0064")/*�Զ�����12*/),

  VBDEF13(SaleInvoiceBVOCode.VBDEF13, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0065")/*�Զ�����13*/),
  VBDEF14(SaleInvoiceBVOCode.VBDEF14, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0066")/*�Զ�����14*/),
  VBDEF15(SaleInvoiceBVOCode.VBDEF15, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0067")/*�Զ�����15*/),
  VBDEF16(SaleInvoiceBVOCode.VBDEF16, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0068")/*�Զ�����16*/),
  VBDEF17(SaleInvoiceBVOCode.VBDEF17, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0069")/*�Զ�����17*/),
  VBDEF18(SaleInvoiceBVOCode.VBDEF18, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0070")/*�Զ�����18*/),
  VBDEF19(SaleInvoiceBVOCode.VBDEF19, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0071")/*�Զ�����19*/),

  VBDEF20(SaleInvoiceBVOCode.VBDEF20, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0073")/*�Զ�����20*/);

  // ��������
  private String key;

  // ��������
  private String name;

  private SOInvoiceCombindefrule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

}
