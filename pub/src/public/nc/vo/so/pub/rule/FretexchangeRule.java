package nc.vo.so.pub.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * �˻�������жϹ���(ѯ�����ۡ�������̨����ȵط�ʹ��)
 * 
 * @since 6.36
 * @version 2015-5-26 ����2:56:22
 * @author ����
 */
public class FretexchangeRule {

  private IKeyValue keyValue;

  /**
   * ���췽��
   * 
   * @param keyValue
   */
  public FretexchangeRule(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ��ȡ�˻������
   * 
   * @param row ������
   * @return
   */
  public Integer getFretexchange(int row) {
    Integer ret = null;
    UFBoolean bdiscountflag =
        keyValue.getBodyUFBooleanValue(row, SOItemKey.BDISCOUNTFLAG);
    UFDouble nastnum = keyValue.getBodyUFDoubleValue(row, SOItemKey.NASTNUM);

    //ǰ̨��ȷ������ģʽ��̨����Ԥ
    Integer fretexchange = keyValue.getBodyIntegerValue(row, "fretexchange");
    if (fretexchange != null) {
      return fretexchange;
    }

    // ����Ϊ����
    if (MathTool.greaterThan(UFDouble.ZERO_DBL, nastnum)) {
      if (bdiscountflag != null && bdiscountflag.booleanValue()) {
        // �����ۿ������϶��ԣ�����Ǹ���������Ϊ����������636����
        ret = Fretexchange.COMMON.getIntegerValue();
      }
      else {
        ret = Fretexchange.WITHDRAW.getIntegerValue();
      }
    }
    // ����Ϊ����
    else if (MathTool.lessThan(UFDouble.ZERO_DBL, nastnum)) {
      if (bdiscountflag != null && bdiscountflag.booleanValue()) {
        // �����ۿ������϶��ԣ����������������Ϊ�����˻���636����
        ret = Fretexchange.WITHDRAW.getIntegerValue();
      }
      else {
        // �������ǻ��� ���Ƿ��˻���
        if (Fretexchange.EXCHANGE.equalsValue(fretexchange)) {
          ret = fretexchange;
        }
        else {
          ret = Fretexchange.COMMON.getIntegerValue();
        }
      }
    }
    // ����Ϊ���Ƿ��˻���
    else {
      ret = Fretexchange.COMMON.getIntegerValue();
    }
    return ret;
  }
}
