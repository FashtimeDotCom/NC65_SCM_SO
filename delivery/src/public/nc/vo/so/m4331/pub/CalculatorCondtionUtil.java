package nc.vo.so.m4331.pub;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ��÷��������۽�����Ĳ���
 * 
 * @since 6.0
 * @version 2010-12-30 ����02:47:08
 * @author ף����
 */
public class CalculatorCondtionUtil {

  /**
   * ���ؼ���ʱ�õ��Ĳ���
   * 
   * @param currvo
   * @param rows
   * 
   * @return
   */
  public Condition getCalcCondition() {
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(true);
    // ���ú�˰���Ȼ�����˰����
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
    cond.setIsTaxOrNet(so23.booleanValue());
    // ���õ����۷�ʽ���ۿۣ�������Ĭ�ϵ����ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // NC001��������
    cond.setGroupLocalCurrencyEnable(SOPubParaUtil.getInstance()
        .isGroupLocalCurrencyEnable(pk_group));
    cond.setOrigCurToGroupMoney(this.isOrigCurToGroupMoney());
    // NC002��������
    cond.setGlobalLocalCurrencyEnable(SOPubParaUtil.getInstance()
        .isGlobalLocalCurrencyEnable());
    cond.setOrigCurToGlobalMoney(this.isOrigCurToGlobalMoney());
    return cond;
  }

  /**
   * ��������������ҵ��λ������λ�Ƿ�̶������ʡ�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:27:07
   */
  public boolean isAstFixedRate(CircularlyAccessibleValueObject vo) {
    String material = (String) vo.getAttributeValue(DeliveryBVO.CMATERIALVID);
    String unit = (String) vo.getAttributeValue(DeliveryBVO.CUNITID);
    String castunit = (String) vo.getAttributeValue(DeliveryBVO.CASTUNITID);
    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            castunit);
    return isfixed;
  }

  /**
   * ����λ�ͱ��۵�λ֮���Ƿ�̶����ۻ�����
   * 
   * @param index
   * @param unitkey
   * @return
   */
  public boolean isQtFixedRate(CircularlyAccessibleValueObject vo) {
    String material = (String) vo.getAttributeValue(DeliveryBVO.CMATERIALVID);
    String unit = (String) vo.getAttributeValue(DeliveryBVO.CUNITID);
    String castunit = (String) vo.getAttributeValue(DeliveryBVO.CQTUNITID);
    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            castunit);
    return isfixed;

  }

  private boolean isOrigCurToGlobalMoney() {
    return true;
  }

  private boolean isOrigCurToGroupMoney() {
    return true;
  }

  public boolean isBuysellFlagOut(CircularlyAccessibleValueObject vo) {
    Integer buysellflag =
        (Integer) vo.getAttributeValue(DeliveryBVO.FBUYSELLFLAG);
    if (BuySellFlagEnum.OUTPUT.equalsValue(buysellflag)) {
      return true;
    }
    return false;
  }
}
