package nc.ui.so.tranmatrel.action;

import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane.BillTable;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.ui.pubapp.uif2app.actions.PrintDirectAction;

/**
 * �������������Ϲ�ϵ����ֱ�Ӵ�ӡ��ȥ���ϼ��в���Ӵ�ӡ����
 * 
 * @author gdsjw
 * @modifier ������
 *
 */

public class TranMatRelPrintAction extends PrintDirectAction {

  private static final long serialVersionUID = 127734615877198619L;

  @Override
  protected void processTitle() {
    String title_new =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_2",
            "2400600704-0007")/* �������������Ϲ�ϵ�����*/;
    this.setTitle(title_new);
    super.processTitle();
  }
  
  @Override
  protected void processBody() throws Exception {
    BillTable[] billTables = this.getBodyTables();
    if(billTables != null && billTables.length > 0) {
      BillModel billModel = (BillModel) billTables[0].getModel();
      billModel.setNeedCalculate(false);
    }
    super.processBody();
  } 
}