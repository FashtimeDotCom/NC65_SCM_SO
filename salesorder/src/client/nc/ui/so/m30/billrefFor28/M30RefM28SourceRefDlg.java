package nc.ui.so.m30.billrefFor28;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/*��Դ������ʾ��*/
public class M30RefM28SourceRefDlg extends SourceRefDlg {

	private static final long serialVersionUID = 3581432494198863324L;

	public M30RefM28SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billrefFor28/TJ01RefQg28Info.xml";
	}
}