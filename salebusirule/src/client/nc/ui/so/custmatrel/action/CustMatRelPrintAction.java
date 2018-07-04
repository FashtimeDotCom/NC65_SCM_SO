/**
 *
 */
package nc.ui.so.custmatrel.action;

import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane.BillTable;
import nc.vo.ml.NCLangRes4VoTransl;

import nc.ui.pubapp.uif2app.actions.PrintDirectAction;

/**
 * �ͻ������Ϲ�ϵֱ�Ӵ�ӡ��ȥ���ϼ��в����Ӵ�ӡ����
 * 
 * @since 6.0
 * @version 2011-11-11 ����09:32:40
 * @modifier ������
 */
public class CustMatRelPrintAction extends PrintDirectAction {
  
  private static final long serialVersionUID = -4473489445762830336L;

  @Override
  protected void processTitle() {
    String title_new =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_2",
            "2400600701-0002")/* �ͻ������Ϲ�ϵ*/;
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