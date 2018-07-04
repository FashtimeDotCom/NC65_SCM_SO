package nc.vo.so.m4331.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.m4331.entity.DeliveryBVO;
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
   * �������������������ҵ��λ������λ֮�任���ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:25:00
   */
  public void calcAstChangeRate(int index) {
    String astChangeRate = this.calcChangeRate(index, DeliveryBVO.CASTUNITID);
    this.keyValue.setBodyValue(index, DeliveryBVO.VCHANGERATE, astChangeRate);
  }

  /**
   * �����������������㱨�۵�λ������λ֮�任���ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:25:51
   */
  public void calcQTChangeRate(int index) {
    String qtChangeRate = this.calcChangeRate(index, DeliveryBVO.CQTUNITID);
    this.keyValue.setBodyValue(index, DeliveryBVO.VQTUNITRATE, qtChangeRate);
  }

  /**
   * ��������������ҵ��λ������λ�Ƿ�̶������ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:27:07
   */
  public boolean isAstFixedRate(int index) {
    return this.isFixedRate(index, DeliveryBVO.CASTUNITID);
  }

  /**
   * �����������������۵�λ������λ�Ƿ�̶������ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:27:48
   */
  public boolean isQtFixedRate(int index) {
    return this.isFixedRate(index, DeliveryBVO.CQTUNITID);
  }

  /**
   * �����������������㴫��༭��λ������λ֮�任���ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:26:19
   */
  private String calcChangeRate(int index, String editunitkey) {
    String material =
        this.keyValue.getBodyStringValue(index, DeliveryBVO.CMATERIALVID);
    String unit = this.keyValue.getBodyStringValue(index, DeliveryBVO.CUNITID);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);
    String changerate = null;
    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);
    return changerate;

  }

  /**
   * �����������������뵥λ������λ�Ƿ�̶������ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:28:27
   */
  private boolean isFixedRate(int index, String unitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, DeliveryBVO.CMATERIALVID);
    String unit = this.keyValue.getBodyStringValue(index, DeliveryBVO.CUNITID);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;
  }
}
