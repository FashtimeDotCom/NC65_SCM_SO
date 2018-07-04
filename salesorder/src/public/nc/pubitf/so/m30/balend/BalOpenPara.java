package nc.pubitf.so.m30.balend;

import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;

public class BalOpenPara {

  String[] orderbids;

  BalOpenTrigger trigger;

  /**
   * SaleOrderBalEndPara �Ĺ�����
   * 
   * @param orderbids
   * @param trigger
   */
  public BalOpenPara(String[] orderbids, BalOpenTrigger trigger) {
    this.orderbids = orderbids;
    this.trigger = trigger;
  }

  /**
   * �����������������ض�����ID���顣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 ����09:24:22
   */
  public String[] getOrderbids() {
    return this.orderbids;
  }

  /**
   * �����������������ش����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 ����09:25:12
   */
  public BalOpenTrigger getTrigger() {
    return this.trigger;
  }

}
