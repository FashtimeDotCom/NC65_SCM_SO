package nc.vo.so.m30.balend.entity;

import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m33.enumeration.SquareType;

public class SaleOutBalVO extends AbstractBalVO {

  @Override
  public String getBalbilltype() {
    return ICBillType.SaleOut.getCode();
  }

  /**
   * ���������������Ƿ��Զ��ݹ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-19 ����02:25:12
   */
  public boolean isAutoEt() {
    if (SquareType.SQUARETYPE_ET.equalsValue(this.preartype)
        && this.getBautoincome().booleanValue()) {
      return true;
    }
    return false;
  }

  /**
   * ���������������Ƿ��Զ�������Ʒ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-19 ����02:25:12
   */
  public boolean isAutoReg() {
    if (SquareType.SQUARETYPE_REG_DEBIT.equalsValue(this.preiatype)
        && this.getBautocost().booleanValue()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isCostbal() {
    if (SquareType.SQUARETYPE_IA.equalsValue(this.preiatype)
    // || SquareType.SQUARETYPE_REG_DEBIT.equalsValue(this.preiatype)
    ) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isIncomebal() {
    if (SquareType.SQUARETYPE_AR.equalsValue(this.preartype)
        || SquareType.SQUARETYPE_ET.equalsValue(this.preartype)) {
      return true;
    }
    return false;
  }

  /**
   * ���������������Ƿ��ֹ��ݹ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-19 ����08:55:33
   */
  public boolean isManualEt() {
    if (SquareType.SQUARETYPE_ET.equalsValue(this.preartype)
        && !this.getBautoincome().booleanValue()) {
      return true;
    }
    return false;
  }

  /**
   * ���������������Ƿ��ֹ����뷢����Ʒ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-16 ����03:46:42
   */
  public boolean isManualReg() {
    if (SquareType.SQUARETYPE_REG_DEBIT.equalsValue(this.preiatype)
        && !this.getBautocost().booleanValue()) {
      return true;
    }
    return false;
  }
}
