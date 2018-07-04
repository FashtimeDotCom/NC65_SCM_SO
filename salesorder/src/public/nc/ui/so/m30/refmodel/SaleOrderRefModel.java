package nc.ui.so.m30.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class SaleOrderRefModel extends AbstractRefModel {
	

	public SaleOrderRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("���۶���");
		setRefTitle("���۶���");
		setFieldCode(new String[] {
				//"csaleorderid",
				"vbillcode",
				//"pk_org",
				//"pk_org_v",
				"orgname",
				//"cdeptid",
				//"cdeptvid",
				"deptname",
				//"ccustomerid",
				"customername",
				//"pk_currencyid",
				"currtypename"
				});
		setFieldName(new String[] {
				//"����",
				"���۶�����",
				//"������֯id",
				//"������֯�汾id",
				"������֯",
				//"����id",
				//"���Ű汾id",
				"����",
				//"�ͻ�id",
				"�ͻ�",
				//"����id",
				"����"
				});
		setHiddenFieldCode(new String[] {
				"csaleorderid",
				"pk_org",
				"pk_org_v",
				"cdeptid",
				"cdeptvid",
				"ccustomerid",
				"pk_currencyid"
			});
		setPkFieldCode("csaleorderid");
		setWherePart("1=1");
		setTableName("v_so_saleorder");
		setRefCodeField("vbillcode");
		setRefNameField("vbillcode");
	
	}
	
}
