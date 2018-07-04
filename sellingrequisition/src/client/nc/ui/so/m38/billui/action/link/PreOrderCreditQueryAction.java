package nc.ui.so.m38.billui.action.link;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.billcreditquery.IBillCreditQueryMessage;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.billcreditquery.para.BillQueryPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class PreOrderCreditQueryAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1826693120290414661L;

  private PreOrderEditor editor;

  private BillManageModel model;

  public PreOrderCreditQueryAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CREDITQUERY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*���������ù���ģ��*/);
    }
    PreOrderVO vo = (PreOrderVO) this.getModel().getSelectedData();
    AppUiState uistate = this.getModel().getAppUiState();
    if (uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD) {
      vo = (PreOrderVO) this.getEditor().getValue();
    }
    String[] cmaterialids =
        AggVOUtil.getDistinctItemFieldArray(new PreOrderVO[] {
          vo
        }, SOItemKey.CMATERIALVID, String.class);

    Map<String, MaterialVO> prodlinemaps =
        MaterialPubService.queryMaterialBaseInfo(cmaterialids, new String[] {
          MaterialVO.PK_PRODLINE
        });
    String cmaterialivid = null;
    PreOrderHVO head = null;
    PreOrderBVO[] bodys = null;

    IKeyValue keyValue = new CardKeyValue(this.getEditor().getBillCardPanel());
    int len = keyValue.getBodyCount();
    if (VOChecker.isEmpty(vo)) {
      head = new PreOrderHVO();
      head.setCchanneltypeid(keyValue
          .getHeadStringValue(PreOrderHVO.CCHANNELTYPEID));
      head.setCcustomerid(keyValue.getHeadStringValue(PreOrderHVO.CCUSTOMERID));
      head.setCemployeeid(keyValue.getHeadStringValue(PreOrderHVO.CEMPLOYEEID));
      head.setCdeptid(keyValue.getHeadStringValue(PreOrderHVO.CDEPTID));
      head.setPk_org(keyValue.getHeadStringValue(PreOrderHVO.PK_ORG));
      head.setCtrantypeid(keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID));
      if (len > 0) {
        bodys = new PreOrderBVO[len];
        for (int i = 0; i < len; i++) {
          bodys[i] = new PreOrderBVO();
          bodys[i].setCsettleorgid(keyValue.getBodyStringValue(i,
              PreOrderBVO.CSETTLEORGID));
          bodys[i].setCmaterialid(keyValue.getBodyStringValue(i,
              PreOrderBVO.CMATERIALID));
        }
      }
    }
    else {
      head = vo.getParentVO();
      bodys = vo.getChildrenVO();
    }
    int i = 0;
    // ���ݷ�װΪBillQueryPara[]
    BillQueryPara[] bqpS = new BillQueryPara[1];
    int bodysLen = bodys.length;
    if (bodysLen == 0) {
      bqpS[i] = new BillQueryPara();
      // ��������
      bqpS[i].setCchanneltypeid(head.getCchanneltypeid());
      // �ͻ�
      bqpS[i].setCcustomerid(head.getCcustomerid());
      // ����ҵ��Ա
      bqpS[i].setCemployeeid(head.getCemployeeid());
      // ���۲���
      bqpS[i].setCsaledeptid(head.getCdeptid());
      // ������֯
      bqpS[i].setCsaleorgid(head.getPk_org());
      // ��������(��������ֻ��ע���۶����������ͣ�����Ԥ����û�����۶����������ͣ�Ӧ�ò���ֵ)
      // bqpS[i].setVtrantypecode(head.getCtrantypeid());
    }
    else {
      bqpS = new BillQueryPara[bodysLen];
      for (PreOrderBVO body : bodys) {
        cmaterialivid = body.getCmaterialvid();
        bqpS[i] = new BillQueryPara();
        // ��������
        bqpS[i].setCchanneltypeid(head.getCchanneltypeid());
        // �ͻ�
        bqpS[i].setCcustomerid(head.getCcustomerid());
        // ����ҵ��Ա
        bqpS[i].setCemployeeid(head.getCemployeeid());
        // ������֯
        bqpS[i].setCfinanceorgid(body.getCsettleorgid());
        // ����
        bqpS[i].setCinvtoryid(cmaterialivid);
        bqpS[i]
            .setCprodlineid(prodlinemaps.get(cmaterialivid).getPk_prodline());// ��Ʒ��
        // ���۲���
        bqpS[i].setCsaledeptid(head.getCdeptid());
        // ������֯
        bqpS[i].setCsaleorgid(head.getPk_org());
        // ��������(��������ֻ��ע���۶����������ͣ�����Ԥ����û�����۶����������ͣ�Ӧ�ò���ֵ)
        // bqpS[i].setVtrantypecode(head.getCtrantypeid());
        i++;
      }
    }

    // ���ýӿ�
    IBillCreditQueryMessage service =
        NCUILocator.getInstance().lookup(IBillCreditQueryMessage.class,
            NCModule.CREDIT);
    // ����Ϊ��Container,billType��BillQueryPara[]
    service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
        .getParent(), "38", bqpS);

  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    AppUiState uistate = this.getModel().getAppUiState();
    if (uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD) {
      return true;
    }
    // û��ѡ�е��ݣ��û�
    if (this.model.getSelectedData() == null) {
      return false;
    }
    return true;
  }

}
