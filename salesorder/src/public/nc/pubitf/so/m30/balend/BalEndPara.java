package nc.pubitf.so.m30.balend;

import nc.vo.so.m30.balend.enumeration.BalEndTrigger;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������رղ���
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-7-14 ����09:17:29
 */
public class BalEndPara {

  String[] orderbids;

  BalEndTrigger trigger;

  /**
   * SaleOrderBalEndPara �Ĺ�����
   * 
   * @param orderbids
   * @param trigger
   */
  public BalEndPara(String[] orderbids, BalEndTrigger trigger) {
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
  public BalEndTrigger getTrigger() {
    return this.trigger;
  }
}
