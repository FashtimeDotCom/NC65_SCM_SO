package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

public enum InvoiceCombindefrule {

  VBDEF1(SaleInvoiceBVO.VBDEF1, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0061")/*�Զ�����1*/),
  VBDEF2(SaleInvoiceBVO.VBDEF2, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0072")/*�Զ�����2*/),
  VBDEF3(SaleInvoiceBVO.VBDEF3, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0074")/*�Զ�����3*/),
  VBDEF4(SaleInvoiceBVO.VBDEF4, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0075")/*�Զ�����4*/),
  VBDEF5(SaleInvoiceBVO.VBDEF5, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0076")/*�Զ�����5*/),
  VBDEF6(SaleInvoiceBVO.VBDEF6, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0077")/*�Զ�����6*/),
  VBDEF7(SaleInvoiceBVO.VBDEF7, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0078")/*�Զ�����7*/),
  VBDEF8(SaleInvoiceBVO.VBDEF8, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0079")/*�Զ�����8*/),
  VBDEF9(SaleInvoiceBVO.VBDEF9, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0225")/*�Զ�����19*/),
  VBDEF10(SaleInvoiceBVO.VBDEF10, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0062")/*�Զ�����10*/),
  VBDEF11(SaleInvoiceBVO.VBDEF11, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0063")/*�Զ�����11*/),
  VBDEF12(SaleInvoiceBVO.VBDEF12, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0064")/*�Զ�����12*/),

  VBDEF13(SaleInvoiceBVO.VBDEF13, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0065")/*�Զ�����13*/),
  VBDEF14(SaleInvoiceBVO.VBDEF14, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0066")/*�Զ�����14*/),
  VBDEF15(SaleInvoiceBVO.VBDEF15, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0067")/*�Զ�����15*/),
  VBDEF16(SaleInvoiceBVO.VBDEF16, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0068")/*�Զ�����16*/),
  VBDEF17(SaleInvoiceBVO.VBDEF17, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0069")/*�Զ�����17*/),
  VBDEF18(SaleInvoiceBVO.VBDEF18, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0070")/*�Զ�����18*/),
  VBDEF19(SaleInvoiceBVO.VBDEF19, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0071")/*�Զ�����19*/),

  VBDEF20(SaleInvoiceBVO.VBDEF20, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0073")/*�Զ�����20*/);

  // ��������
  private String key;

  // ��������
  private String name;

  private InvoiceCombindefrule(String key, String name) {
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
