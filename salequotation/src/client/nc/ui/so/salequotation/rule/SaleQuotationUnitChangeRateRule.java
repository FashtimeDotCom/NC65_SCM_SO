package nc.ui.so.salequotation.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SaleQuotationUnitChangeRateRule {

  private IKeyValue keyValue;

  /**
   * UnitChangeRateHandler �Ĺ�����
   * 
   * @param keyValue
   */
  public SaleQuotationUnitChangeRateRule(IKeyValue keyValue) {
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
        this.calcChangeRate(index, SalequotationBVO.CASTUNITID);
    this.keyValue.setBodyValue(index, SalequotationBVO.NCHANGERATE,
        astChangeRate);
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
    String qtChangeRate =
        this.calcChangeRate(index, SalequotationBVO.CQTUNITID);
    this.keyValue.setBodyValue(index, SalequotationBVO.NQTCHANGERATE,
        qtChangeRate);
  }

  /**
   * ���������������ӱ��index�У�ҵ��λ������λ�Ƿ�̶������ʡ�
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
    return this.isFixedRate(index, SalequotationBVO.CASTUNITID);
  }

  /**
   * ���������������ӱ��index�У����۵�λ������λ�Ƿ�̶������ʡ�
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
    return this.isFixedRate(index, SalequotationBVO.CQTUNITID);
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
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_MATERIAL_V);
    String unit =
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_UNIT);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);

    String changerate = null;

    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);

    return changerate;

  }

  /**
   * �������������������index�У����뵥λ������λ�Ƿ�̶������ʡ�
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
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_MATERIAL_V);
    String unit =
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_UNIT);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;

  }

}
