package nc.pf.so.function;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillCombinClient;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillToClient;
import nc.vo.pubapp.pattern.pub.Constructor;

public class SaleOrderFuncTransferTool<E extends IBill> {

  /**
   * �ͻ��㴫���ĵ���
   */
  private E[] clientBills;

  /**
   * ���ݿ��д��ڵ�ԭʼ����
   */
  private E[] originBills;

  /**
   * �Ƿ���ҪУ��ʱ���
   */
  private UFBoolean nocheckts;

  /**
   * ǰ��̨���ݴ��乤�߹��캯��
   * */
  public SaleOrderFuncTransferTool(E[] bills, UFBoolean nocheckts) {
    this.nocheckts = nocheckts;
    // Ϊ�˷�ֹ�����жϵ��µ����ظ����ӣ��˴��������ݵ����������Ѿ�����
    if ((bills[0].getPrimaryKey() == null)
        || (bills[0].getParent().getStatus() == VOStatus.NEW)) {
      this.initInserted(bills);
    }
    else {
      this.initUpdateed(bills);
    }

  }

  /**
   * ��ǰ̨����ʵ�����Ƚϣ���ȡ��Ҫ���ݵ�ǰ̨�ĵ���ʵ�����
   * 
   * @param bills �����ĵ���ʵ��
   * @return ��Ҫ���ݵ�ǰ̨�ĵ���ʵ�����
   */
  @SuppressWarnings({
    "rawtypes", "unchecked"
  })
  public E[] getBillForToClient(E[] bills) {
    ServerBillToClient clientTransfer = new ServerBillToClient();
    E[] vos = (E[]) clientTransfer.construct(this.clientBills, bills);

    return vos;
  }

  /**
   * ��ȡǰ̨����������ʵ��
   * 
   * @return ǰ̨����������ʵ��
   */
  @SuppressWarnings("unchecked")
  public E[] getClientFullInfoBill() {
    // ��¡��Ϊ�˷��㷵��ǰ̨ʱ֪����̨�������ָı���ʲô�ֶε�ֵ
    int length = this.clientBills.length;
    E[] bills =
        (E[]) Constructor.construct(this.clientBills[0].getClass(), length);
    for (int i = 0; i < length; i++) {
      bills[i] = (E) this.clientBills[i].clone();
    }
    return bills;
  }

  /**
   * ��ȡ���ݿ��д�ŵ�����ԭʼ����ʵ��
   * 
   * @return ���ݿ��д�ŵ�����ԭʼ����ʵ��
   */
  public E[] getOriginBills() {
    return this.originBills;
  }

  @SuppressWarnings("unchecked")
  private void initInserted(E[] bills) {
    int size = bills.length;
    E[] vos = (E[]) Constructor.construct(bills[0].getClass(), size);
    for (int i = 0; i < size; i++) {
      vos[i] = (E) bills[i].clone();
    }
    this.originBills = vos;
    this.clientBills = vos;
  }

  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private void initUpdateed(E[] bills) {
    BillConcurrentTool tool = new BillConcurrentTool();
    TimeLog.logStart();
    tool.lockBill(bills);
    TimeLog.info("������ͷ����������"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] ids = new String[bills.length];
    int length = bills.length;
    for (int i = 0; i < length; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    TimeLog.info("��ȡ��������"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillQuery query = new BillQuery(bills[0].getClass());
    this.originBills = (E[]) query.query(ids);
    TimeLog.info("��ѯԭʼ����VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    length = this.originBills.length;
    E[] vos = (E[]) Constructor.construct(bills[0].getClass(), length);
    for (int i = 0; i < length; i++) {
      vos[i] = (E) this.originBills[i].clone();
    }
    ServerBillCombinClient<E> combineClient = new ServerBillCombinClient<E>();
    combineClient.combine(vos, bills);
    this.clientBills = vos;
    TimeLog.info("ǰ̨����VO��������"); /*-=notranslate=-*/

    // ��������ʱ���
    if (null != this.nocheckts && this.nocheckts.booleanValue()) {
      return;
    }
    TimeLog.logStart();
    tool.checkTS(bills, this.originBills);
    TimeLog.info("���ʱ���"); /*-=notranslate=-*/
  }

}
