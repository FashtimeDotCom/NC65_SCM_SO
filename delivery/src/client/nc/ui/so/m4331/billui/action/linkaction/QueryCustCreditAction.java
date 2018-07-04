package nc.ui.so.m4331.billui.action.linkaction;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.billcreditquery.IBillCreditQueryMessage;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.uif2.NCAction;
import nc.vo.credit.billcreditquery.para.BillQueryPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

public class QueryCustCreditAction extends NCAction {

  private static final long serialVersionUID = -7841185707254140620L;

  private DeliveryManageModel model;

  public QueryCustCreditAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*���������ù���ģ��*/);
    }
    DeliveryVO vo = (DeliveryVO) this.getModel().getSelectedData();
    DeliveryBVO[] bodys = vo.getChildrenVO();
    int i = 0;
    // ���ݷ�װΪBillQueryPara[]
    BillQueryPara[] bqpS = new BillQueryPara[bodys.length];
    for (DeliveryBVO body : bodys) {
      bqpS[i] = new BillQueryPara();
      // ��������
      bqpS[i].setCchanneltypeid(body.getCchanneltypeid());
      // �ͻ�
      bqpS[i].setCcustomerid(body.getCordercustid());
      // ����ҵ��Ա
      bqpS[i].setCemployeeid(body.getCemployeeid());
      // ������֯
      bqpS[i].setCfinanceorgid(body.getCsettleorgid());
      // ����
      bqpS[i].setCinvtoryid(body.getCmaterialid());
      // ��Ʒ��
      bqpS[i].setCprodlineid(body.getCprodlineid());
      // ���۲���
      bqpS[i].setCsaledeptid(body.getCdeptid());
      // ������֯
      bqpS[i].setCsaleorgid(body.getCsaleorgid());
      // ��������
      bqpS[i].setVtrantypecode(body.getVsrctrantype());
      i++;
    }

    // ���ýӿ�
    IBillCreditQueryMessage service =
        NCUILocator.getInstance().lookup(IBillCreditQueryMessage.class,
            NCModule.CREDIT);
    // ����Ϊ��Container,billType��BillQueryPara[]
    service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
        .getParent(), "4331", bqpS);
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    boolean isEnable = this.model.getSelectedData() != null;
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CREDITQUERY);

  }

}
