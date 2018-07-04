package nc.ui.so.mbuylargess.view;

import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.IRowSelectModel;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������������б����
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author fengjb
 * @time 2009-6-4 ����03:22:38
 */
public class BuyLargessList extends ShowUpableBillListView {

  private static final long serialVersionUID = 3980285733144504397L;

  @Override
  public void initUI() {
    super.initUI();
    // ���ݲ���SO79:���Ϸ��෽ʽ SO80:�ͻ����෽ʽ ���÷����ֶ���ʾ����
    String pk_group = this.getModel().getContext().getPk_group();
    boolean isbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    this.getBillListPanel().getHeadItem("pk_marbasclass").setShow(isbas);
    this.getBillListPanel().getHeadItem("pk_marsaleclass").setShow(!isbas);

    isbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    this.getBillListPanel().getHeadItem("pk_custclass").setShow(isbas);
    this.getBillListPanel().getHeadItem("pk_custsaleclass").setShow(!isbas);
    this.getBillListPanel().hideBodyTableCol("pk_marsaleclass");
    this.getBillListPanel().hideBodyTableCol("pk_marbasclass");
    this.getBillListPanel().hideBodyTableCol("pk_material.pk_marbasclass");
    this.getBillListPanel().hideBodyTableCol("pk_material.pk_measdoc");
    this.getBillListPanel().hideHeadTableCol("pk_material.pk_measdoc");
    this.getBillListPanel().setListData(
        this.getBillListPanel().getBillListData());
    BuyLargessPrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());

  }
  // add by quyt Ϊ�˴���α�����������ɾ�����������
  public void bodyRowChange(BillEditEvent e) {
    if (handlingModelEvent) {
      return;
    }
    if (e.getOldRow() != e.getRow()
        && e.getRow() < super.getModel().getRowCount()) {
      if (super.getModel() instanceof IRowSelectModel) {
        ((IRowSelectModel) super.getModel()).setSelectedRow(e.getRow());
      }
    }
    super.bodyRowChange(e);
  }
  /**
   * ͬ��ListViewģ�ͺ�BillListPanelģ��
   */
  protected void synchronizeDataFromModel() {
    super.synchronizeDataFromModel();
    this.getBillListPanel().getHeadBillModel().loadLoadRelationItemValue();
  }
}
