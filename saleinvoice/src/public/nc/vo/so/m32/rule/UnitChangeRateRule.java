package nc.vo.so.m32.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class UnitChangeRateRule {
  private IKeyValue keyValue;

  /**
   * UnitChangeRateHandler �Ĺ�����
   * 
   * @param keyValue
   */
  public UnitChangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ������������������ҵ��λ������λ֮�任���ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����10:31:34
   */
  public void calcAstChangeRate(int index) {
    String astChangeRate =
        this.calcChangeRate(index, SaleInvoiceBVO.CASTUNITID);
    this.keyValue
        .setBodyValue(index, SaleInvoiceBVO.VCHANGERATE, astChangeRate);
  }

  public void calcAstChangeRate(int[] indexs) {
    for (int index : indexs) {
      this.calcAstChangeRate(index);
    }

  }

  /**
   * �����������������㱨�۵�λ������λ֮�任���ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����01:22:59
   */
  public void calcQtChangeRate(int index) {
    String qtChangeRate = this.calcChangeRate(index, SaleInvoiceBVO.CQTUNITID);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.VQTUNITRATE, qtChangeRate);
  }

  public void calcQtChangeRate(int[] indexs) {
    for (int index : indexs) {
      this.calcAstChangeRate(index);
    }
  }

  /**
   * �����������������۷�Ʊ�ӱ��index�У�ҵ��λ������λ�Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����05:53:19
   */
  public boolean isAstFixedRate(int index) {
    return this.isFixedRate(index, SaleInvoiceBVO.CASTUNITID);
  }

  /**
   * �����������������۷�Ʊ�ӱ��index�У����۵�λ������λ�Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����05:53:51
   */
  public boolean isQtFixedRate(int index) {
    return this.isFixedRate(index, SaleInvoiceBVO.CQTUNITID);
  }

  /**
   * �����������������㴫��༭��λ������λ֮�任���ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����01:23:21
   */
  private String calcChangeRate(int index, String editunitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CMATERIALVID);
    String unit =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CUNITID);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);

    String changerate = null;
    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);

    return changerate;

  }

  /**
   * ���������������������۷�Ʊ��index�У����뵥λ������λ�Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param unitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����05:58:01
   */
  private boolean isFixedRate(int index, String unitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CMATERIALVID);
    String unit =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CUNITID);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;

  }
}
