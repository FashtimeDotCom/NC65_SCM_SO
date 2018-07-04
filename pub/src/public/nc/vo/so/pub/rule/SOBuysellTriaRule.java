package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;

/**
 * 
 * ���۹���ģ�� �������ͺ�����ó���ֶι���
 * ע�� ���չ������� �������ͷ����仯��ʱ�� Ҫ�������۽���㷨���ڵ��ô˷�����Ҫע�⿼���Ƿ���Ҫ����
 * 
 * @since 6.0
 * @version 2012-2-5 ����01:52:17
 * @author ô��
 */
public class SOBuysellTriaRule {

  private IKeyValue keyValue;

  private List<Integer> listchgrow;

  /**
   * ���췽��
   * 
   * @param keyValue
   */
  public SOBuysellTriaRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���ع������ͷ����仯���У��������ߵ����������۽���㷨
   * 
   * @return
   */
  public int[] getBuysellChgRow() {
    if (this.listchgrow.size() == 0) {
      return new int[0];
    }
    int[] chgrows = new int[this.listchgrow.size()];
    int i = 0;
    for (Integer chgrow : this.listchgrow) {
      chgrows[i] = chgrow;
      i++;
    }
    return chgrows;
  }

  /**
   * ָ���й��������Ƿ�Ϊ����
   * 
   * @param row
   * @return
   */
  public boolean isBuysellFlagOut(int row) {

    Integer buysellflag =
        this.keyValue.getBodyIntegerValue(row, SOItemKey.FBUYSELLFLAG);

    if (BuySellFlagEnum.OUTPUT.equalsValue(buysellflag)) {
      return true;
    }
    return false;
  }

  /**
   * ���������ڱ�ͷ �ж��Ƿ���
   * 
   * @return
   */
  public boolean isHeadBuysellFlagOut() {
    Integer buysellflag =
        this.keyValue.getHeadIntegerValue(SOItemKey.FBUYSELLFLAG);

    if (BuySellFlagEnum.OUTPUT.equalsValue(buysellflag)) {
      return true;
    }
    return false;
  }

  /**
   * ���ù������͡�����ó���ֶ�ֵ
   * 
   * @param rows
   */
  public void setBuysellAndTriaFlag(int[] rows) {

    this.listchgrow = new ArrayList<Integer>();
    for (int row : rows) {
      String ctaxcountryid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CTAXCOUNTRYID);
      String crececountryid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECECOUNTRYID);
      String csendcountryid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDCOUNTRYID);
      // ����ɵĹ�������
      Integer oldbuysellflag =
          this.keyValue.getBodyIntegerValue(row, SOItemKey.FBUYSELLFLAG);

      // ���ù������ͣ����洢�仯����
      Integer newbuysellflag = null;
      if (PubAppTool.isNull(ctaxcountryid) || PubAppTool.isNull(crececountryid)
          || PubAppTool.isEqual(ctaxcountryid, crececountryid)) {
        newbuysellflag = BuySellFlagEnum.NATIONAL_SELL.value();
      }
      else {
        newbuysellflag = BuySellFlagEnum.OUTPUT.value();
      }
      this.keyValue.setBodyValue(row, SOItemKey.FBUYSELLFLAG, newbuysellflag);

      if (!PubAppTool.isEqual(oldbuysellflag, newbuysellflag)) {
        // �ɹ�����Эͬ�������۶���ʱ �ɹ������������͡����ڲɹ��� �����������ۡ�����ʱҲ��Ϊ��������û�з����仯
        if (!(BuySellFlagEnum.NATIONAL_BUY.equalsValue(oldbuysellflag) && BuySellFlagEnum.NATIONAL_SELL
            .equalsValue(newbuysellflag))) {
          this.listchgrow.add(Integer.valueOf(row));
        }
      }
      // ��������ó��
      if (BuySellFlagEnum.OUTPUT.equalsValue(newbuysellflag)
          && !PubAppTool.isNull(ctaxcountryid)
          && !PubAppTool.isNull(csendcountryid)
          && !PubAppTool.isEqual(ctaxcountryid, csendcountryid)) {
        this.keyValue.setBodyValue(row, SOItemKey.BTRIATRADEFLAG,
            UFBoolean.TRUE);
      }
      else {
        this.keyValue.setBodyValue(row, SOItemKey.BTRIATRADEFLAG,
            UFBoolean.FALSE);
      }
    }
  }
}
