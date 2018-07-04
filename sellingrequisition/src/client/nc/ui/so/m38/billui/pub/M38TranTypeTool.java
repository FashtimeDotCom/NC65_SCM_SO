package nc.ui.so.m38.billui.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.enumeration.PriceDiscountType;

/**
 * Ԥ�����������ͷ��񹤾�
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�������Ӱ���ۿۻ��ǵ���
 * <li>ѯ�۹���
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-7-12 ����11:16:46
 */
public class M38TranTypeTool {

  private CardKeyValue keyValue;

  public M38TranTypeTool(
      CardKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public IM38TranTypeService getService() {
    return NCLocator.getInstance().lookup(IM38TranTypeService.class);
  }

  /**
   * �Ƿ���Ҫѯ��
   * 
   * @author ��־ΰ
   * @time 2010-7-12 ����11:16:46
   */
  public boolean isFindPrice() {
    boolean bFindPrice = false;
    String ctrantypeid =
        this.keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    try {
      Integer faskqtrule =
          null == this.getService().queryTranTypeVO(ctrantypeid).getFaskqtrule() ? Integer
              .valueOf(0) : this.getService().queryTranTypeVO(ctrantypeid)
              .getFaskqtrule();

      if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(faskqtrule)
          || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(faskqtrule)) {
        bFindPrice = true;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return bFindPrice;
  }

  /**
   * �Ƿ�������Ӱ�쵥��
   * 
   * @author ��־ΰ
   * @time 2010-7-12 ����11:16:46
   */
  public boolean isModifymny() {
    boolean bmodifymny = false;
    String ctrantypeid =
        this.keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    try {
      Integer fmodifymny =
          null == this.getService().queryTranTypeVO(ctrantypeid).getFmodifymny() ? Integer
              .valueOf(0) : this.getService().queryTranTypeVO(ctrantypeid)
              .getFmodifymny();

      if (PriceDiscountType.PRICETYPE.equalsValue(fmodifymny)) {
        bmodifymny = true;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return bmodifymny;
  }
}
