package nc.ui.so.pub.keyvalue;

import nc.ui.pub.bill.BillListPanel;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.AbstractKeyValue;

/**
 * <p>
 * <b>���е�ֵ�����б��ͷȡֵ</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-6-29 ����09:16:36
 */
public class ListKeyValue extends AbstractKeyValue {

  // ��ǰ�����б��ͷ����������
  private int curHeadRow;

  private BillListPanel list;

  private ListPanelValueUtils valueUtil;

  /**
   * �����Ҫ���ĵ�ǰ�п�ʹ��setRow()
   */
  public ListKeyValue(BillListPanel list, int row, ListTemplateType type) {
    this.list = list;
    this.valueUtil = new ListPanelValueUtils(this.list, type);
    this.curHeadRow = row;
  }

  public ListKeyValue(BillListPanel list, ListTemplateType type) {
    this.list = list;
    this.valueUtil = new ListPanelValueUtils(this.list, type);
  }

  @Override
  public int getBodyCount() {
    return this.valueUtil.getRowCount();
  }

  @Override
  public Object getBodyValue(int row, String strKey) {
    return this.valueUtil.getBodyValueAt(row, strKey);
  }

  @Override
  public Object getHeadValue(String strKey) {
    return this.valueUtil.getHeadValueAt(this.getRow(), strKey);
  }

  public BillListPanel getList() {
    return this.list;
  }

  public int getRow() {
    return this.curHeadRow;
  }

  @Override
  public RowStatus getRowStatus(int row) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setBodyValue(int row, String strKey, Object aValue) {
    this.valueUtil.setBodyValueAt(aValue, row, strKey);
  }

  @Override
  public void setHeadValue(String strKey, Object value) {
    this.valueUtil.setHeadValueAt(value, this.getRow(), strKey);
  }

  public void setRow(int row) {
    this.curHeadRow = row;
  }

}
