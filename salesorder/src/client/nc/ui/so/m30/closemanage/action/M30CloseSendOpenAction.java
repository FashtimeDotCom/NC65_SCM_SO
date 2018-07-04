package nc.ui.so.m30.closemanage.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.biz.SOBusiUtil;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.pub.task.IMultiReturnObjProcessor;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.pub.task.MultiBillTaskRunner;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.components.progress.ProgressActionInterface;
import nc.ui.uif2.components.progress.TPAProgressUtil;

public class M30CloseSendOpenAction extends NCAction implements
    IMultiReturnObjProcessor, ProgressActionInterface {

  private static final long serialVersionUID = 4289655279950904369L;

  protected BatchBillTableModel model;

  private BatchBillTable billTable;

  private MultiBillTaskRunner multiBillTaskRunner;

  private ISingleBillService<SaleOrderViewVO> singleBillService;

  public M30CloseSendOpenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_SENDOPEN);
  }

  private DefaultRefreshAction refreshAction;

  /**
   * ���ˢ��action
   * 
   * @return DefaultRefreshAction
   */
  public DefaultRefreshAction getRefreshAction() {
    return this.refreshAction;
  }

  /**
   * ����ˢ��action
   * 
   * @param refreshAction
   */
  public void setRefreshAction(DefaultRefreshAction refreshAction) {
    this.refreshAction = refreshAction;
  }

  private TPAProgressUtil tpaProgressUtil;

  @Override
  public TPAProgressUtil getTpaProgressUtil() {
    if (this.tpaProgressUtil == null) {
      this.tpaProgressUtil = new TPAProgressUtil();
      this.tpaProgressUtil.setContext(this.getModel().getContext());
    }
    return this.tpaProgressUtil;
  }

  @Override
  public void setTpaProgressUtil(TPAProgressUtil tpaProgressUtil) {
    if (this.multiBillTaskRunner != null) {
      this.multiBillTaskRunner.setTpaProgressUtil(tpaProgressUtil);
    }
    this.tpaProgressUtil = tpaProgressUtil;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    String sReason =
        (String) MessageDialog
            .showInputDlg(this.getModel().getContext().getEntranceUI(),
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0233")/*�ر�/��ԭ��*/,
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0234")/*������ر�/��ԭ��*/,
                null, 120);
    // ȡ����ر�ԭ��򷵻صĶ�Ϊnull,�����ر�/�򿪴���
    if (sReason==null) {
      return;
    }
    if(PubAppTool.isNull(sReason)){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
          .getStrByID("4006011_0", "04006011-0544")/*����ʧ�ܣ��رջ�򿪵�ԭ����Ϊ�ա�*/);
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    int len = objs.length;
    SaleOrderViewVO[] bills = new SaleOrderViewVO[len];
    for (int i = 0; i < len; i++) {
      bills[i] = (SaleOrderViewVO) objs[i];
    }
    // ���ùر�/��ԭ��
    this.setCloseOpenReason(bills, sReason);

    // �������̼���Ƿ���Է�����
    if (this.checkBusiSendOpen(bills)) {
      // ������
      SaleOrderViewVO[] ret = null;
      if (this.getSingleBillService() != null) {
        if (bills.length > 1) {
          this.getMultiBillTaskRunner().setOperateObjs(bills);
          // this.getMultiBillTaskRunner().setParent(
          // WorkbenchEnvironment.getInstance().getWorkbench());
          this.getMultiBillTaskRunner()
              .setTitle(
                  NCLangRes.getInstance().getStrByID("4006011_0",
                      "04006011-0384")/*������*/);
          this.getMultiBillTaskRunner().setMultiReturnObjProcessor(this);
          this.getMultiBillTaskRunner().runTask();
        }
        else if (bills.length == 1) {
          ret = new SaleOrderViewVO[] {
            this.getSingleBillService().operateBill(bills[0])
          };
          this.processReturnObjs(ret);
        }
      }
    }
    this.getRefreshAction().doAction(e);
  }

  public BatchBillTable getBillTable() {
    return this.billTable;
  }

  public BatchBillTableModel getModel() {
    return this.model;
  }

  public MultiBillTaskRunner getMultiBillTaskRunner() {
    if (this.multiBillTaskRunner == null) {
      this.multiBillTaskRunner =
          new MultiBillTaskRunner(this.getSingleBillService());
    }
    this.multiBillTaskRunner.setTpaProgressUtil(this.getTpaProgressUtil());
    return this.multiBillTaskRunner;
  }

  public ISingleBillService<SaleOrderViewVO> getSingleBillService() {

    return this.singleBillService;
  }

  @Override
  public void processReturnObjs(Object[] returnObj) {
    if (ArrayUtil.isEmptyOrNull(returnObj)) {
      return;
    }
    BatchOperateVO vo = new BatchOperateVO();
    vo.setUpdObjs(returnObj);
    try {
      this.model.directSave(vo);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    this.getBillTable().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
  }

  public void setBillTable(BatchBillTable billTable) {
    this.billTable = billTable;
  }

  public void setModel(BatchBillTableModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setSingleBillService(
      ISingleBillService<SaleOrderViewVO> singleBillService) {
    this.singleBillService = singleBillService;
  }

  @Override
  protected boolean isActionEnable() {
    // û��ѡ�е��ݣ��û�
    if (this.model.getSelectedData() == null) {
      return false;
    }
    // ��ѡʲô״̬��������
    if (this.model.getSelectedOperaRows().length > 1) {
      return true;
    }
    SaleOrderViewVO vo = (SaleOrderViewVO) this.getModel().getSelectedData();
    SaleOrderBVO body = vo.getBody();
    Integer fstatusflag = vo.getHead().getFstatusflag();
    if (!(BillStatus.AUDIT.equalsValue(fstatusflag) || BillStatus.CLOSED
        .equalsValue(fstatusflag))) {
      return false;
    }

    // �����ۿ��з����������ֹ���
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    UFBoolean bsendendflag = body.getBbsendendflag();
    if (!bsendendflag.booleanValue() || laborflag || discountflag) {
      return false;
    }

    return true;
  }

  private boolean checkBusiSendOpen(SaleOrderViewVO[] views) {
    StringBuilder errMsg = new StringBuilder();
    Set<String> bizSet = new HashSet<String>();
    Map<String, String> codeMap = new HashMap<String, String>();
    for (SaleOrderViewVO view : views) {
      String biz = view.getHead().getCbiztypeid();
      bizSet.add(biz);
      codeMap.put(biz, view.getHead().getVbillcode());
    }
    if (bizSet.size() > 0) {
      String[] cbiztypeids = bizSet.toArray(new String[bizSet.size()]);
      Map<String, Set<String>> busiTypeMap =
          new SOBusiUtil().queryAllBillType(cbiztypeids);
      for (int i = 0; i < cbiztypeids.length; i++) {
        Set<String> typeSet = busiTypeMap.get(cbiztypeids[i]);
        if (!typeSet.contains(SOBillType.Delivery.getCode())) {
          if (errMsg.length() == 0) {
            errMsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
                "04006011-0380")/*��ѡ���ݺ��У�*/);
          }
          errMsg.append(codeMap.get(cbiztypeids[i]));
          errMsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
              "04006011-0284")/*��*/);
        }
      }
      if (errMsg.length() > 0) {
        errMsg.deleteCharAt(errMsg.length() - 1);
        String errMsgForTranslate =
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0385",
                null, new String[] {
                  errMsg.toString()
                })/*{0}��ҵ�������в����������������ܷ����򿪣�������ѡ��*/;
        MessageDialog
            .showErrorDlg(null,
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0010")/*��ʾ*/,
                errMsgForTranslate);
        return false;
      }
    }
    return true;
  }

  private void setCloseOpenReason(SaleOrderViewVO[] views, String sReason) {
    for (SaleOrderViewVO view : views) {
      view.getBody().setVclosereason(sReason);
    }
  }

}
