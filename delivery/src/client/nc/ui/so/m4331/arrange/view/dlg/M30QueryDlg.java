package nc.ui.so.m4331.arrange.view.dlg;


public class M30QueryDlg {
  /**
   * 
   */
  private static final long serialVersionUID = 7889038793735005609L;

  // public M30QueryDlg(LoginContext context, TemplateInfo ti) {
  //
  // super(context, ti);
  // // ��ʼ������Լ������
  // this.initRefCondition();
  // }
  //
  // /**
  // * ���������������������۶������������
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param where
  // * @return <p>
  // * @author fengjb
  // * @time 2010-5-27 ����10:54:32
  // */
  // private String getTableAlias(String where) {
  // SaleOrderBVO body = new SaleOrderBVO();
  // String table =
  // body.getMetaData().getPrimaryAttribute().getColumn().getTable()
  // .getName();
  // FromWhereParseUtil util = new FromWhereParseUtil(where);
  // return util.getTableAlias(table);
  // }
  //
  // /**
  // * ���෽����д
  // */
  // @Override
  // public String getWhereSQL() {
  //
  // String fromsql = super.getFromPart();
  // // ����ӱ����
  // String tableAlias = this.getTableAlias(fromsql);
  // fromsql +=
  // " left outer join so_saleorder_exe exe on " + tableAlias
  // + ".csaleorderbid = exe.csaleorderbid ";
  // return " from " + fromsql + " where " + super.getWherePart();
  // }
  //
  // /**
  // * ����������������ʼ������Լ��������
  // * <p>
  // * <b>����˵��</b>
  // * <p>
  // *
  // * @author fengjb
  // * @time 2010-6-30 ����10:49:43
  // */
  // private void initRefCondition() {
  // // ������������
  // QTransTypeFilter tranfilter =
  // new QTransTypeFilter(this, SOBillType.Order.getCode());
  // tranfilter.filter();
  //
  // // �����ֿ�
  // QWareHouseFilter warehouse =
  // new QWareHouseFilter(this, "so_saleorder_b.csendstockorgid",
  // "so_saleorder_b.csendstordocid");
  // warehouse.addEditorListener();
  // // �ͻ�����
  // QCustomerFilter ordercust = new QCustomerFilter(this, "ccustomerid");
  // ordercust.addEditorListener();
  // // �ͻ����۷���
  // // �ͻ���������
  // // �������۷���
  // QMarSaleClassFilter marSaleClass =
  // new QMarSaleClassFilter(this,
  // "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
  // marSaleClass.addEditorListener();
  // // ���ϻ�������
  // QMarbasclassFilter marclass =
  // new QMarbasclassFilter(this,
  // "so_saleorder_b.cmaterialvid.pk_marbasclass");
  // marclass.addEditorListener();
  // marclass.filterByGroup();
  // // ���ϱ���
  // QMarterialFilter marteral =
  // new QMarterialFilter(this, "pk_org", "so_saleorder_b.cmaterialvid");
  // marteral.addEditorListener();
  // }
}
