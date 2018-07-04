package nc.ui.so.m30.closemanage.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;

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

/**
 * ���۶��������ر�
 * 
 * @since 6.1
 * @version 2013-05-22 10:30:09
 * @author yixl
 */
public class M30CloseBillCloseAction extends NCAction implements
    IMultiReturnObjProcessor, ProgressActionInterface {

  private static final long serialVersionUID = -4824854025774150276L;

  private BatchBillTable billTable;

  private BatchBillTableModel model;

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

  /**
   * ����ִ����
   */
  private MultiBillTaskRunner multiBillTaskRunner;

  private ISingleBillService<SaleOrderVO> singleBillService;

  /**
   * ������
   */
  public M30CloseBillCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLCLOSE);
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
    this.multiBillTaskRunner.setTpaProgressUtil(this.getTpaProgressUtil());
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
    Set<String> hidSet = new HashSet<String>();
    for (Object obj : objs) {
      hidSet.add(((SaleOrderViewVO) obj).getHead().getCsaleorderid());
    }
    List<Object> allObjsList = this.model.getRows();
    Object[] allObjs = allObjsList.toArray(new Object[allObjsList.size()]);
    List<SaleOrderViewVO> processVOList = new ArrayList<SaleOrderViewVO>();
    for (Object obj : allObjs) {
      if (hidSet.contains(((SaleOrderViewVO) obj).getHead().getCsaleorderid())) {
        processVOList.add((SaleOrderViewVO) obj);
      }
    }
    SaleOrderViewVO[] views =
        processVOList.toArray(new SaleOrderViewVO[processVOList.size()]);

    SaleOrderVO[] bills = this.billComposite(views);
    this.setCloseOpenReason(bills, sReason);
    SaleOrderVO[] ret = null;
    if (this.getSingleBillService() != null) {
      if (bills.length > 1) {
        this.getMultiBillTaskRunner().setOperateObjs(bills);
        // this.getMultiBillTaskRunner().setParent(
        // WorkbenchEnvironment.getInstance().getWorkbench());
        this.getMultiBillTaskRunner()
            .setTitle(
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0286")/*�����ر�*/);
        this.getMultiBillTaskRunner().setMultiReturnObjProcessor(this);
        this.getMultiBillTaskRunner().runTask();
      }
      else if (bills.length == 1) {
        ret = new SaleOrderVO[] {
          this.getSingleBillService().operateBill(bills[0])
        };
        this.processReturnObjs(ret);
      }
    }
    // ˢ������
    this.getRefreshAction().doAction(e);
  }

  private void setCloseOpenReason(SaleOrderVO[] bills, String sReason) {
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        body.setVclosereason(sReason);
      }
    }
  }

  /**
   * ���billtable
   * 
   * @return BatchBillTable
   */
  public BatchBillTable getBillTable() {
    return this.billTable;
  }

  /**
   * ���model
   * 
   * @return BatchBillTableModel
   */
  public BatchBillTableModel getModel() {
    return this.model;
  }

  /**
   * �������ִ����
   * 
   * @return ����ִ����
   */
  public MultiBillTaskRunner getMultiBillTaskRunner() {
    if (this.multiBillTaskRunner == null) {
      this.multiBillTaskRunner =
          new MultiBillTaskRunner(this.getSingleBillService());
    }
    this.multiBillTaskRunner.setTpaProgressUtil(this.getTpaProgressUtil());
    return this.multiBillTaskRunner;
  }

  /**
   * ���service
   * 
   * @return ISingleBillService
   */
  public ISingleBillService<SaleOrderVO> getSingleBillService() {
    return this.singleBillService;
  }

  @Override
  public void processReturnObjs(Object[] returnObj) {
    if (ArrayUtil.isEmptyOrNull(returnObj)) {
      return;
    }
    // �Ƿ��й��������ͳһ����billתviews?
    SaleOrderVO[] bills = (SaleOrderVO[]) returnObj;
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(bills);
    // ����
    BatchOperateVO vo = new BatchOperateVO();
    vo.setUpdObjs(views);
    try {
      this.model.directSave(vo);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    this.billTable.getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
  }

  /**
   * ���� BillTable
   * 
   * @param billTable
   */
  public void setBillTable(BatchBillTable billTable) {
    this.billTable = billTable;
  }

  /**
   * ����model
   * 
   * @param model
   */
  public void setModel(BatchBillTableModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * ��ʼ��service
   * 
   * @param singleBillService
   */
  public void setSingleBillService(
      ISingleBillService<SaleOrderVO> singleBillService) {
    this.singleBillService = singleBillService;
  }

  @Override
  protected boolean isActionEnable() {
    // û��ѡ�е��ݣ��û�
    if (this.model.getSelectedData() == null) {
      return false;
    }
    SaleOrderViewVO vo = (SaleOrderViewVO) this.model.getSelectedData();
    // ������״̬�ĵ��ݣ���ť������
    Integer fstatusflag = vo.getHead().getFstatusflag();
    if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
      return false;
    }

    return true;
  }

  private SaleOrderVO[] billComposite(SaleOrderViewVO[] views) {
    if (views == null || views.length == 0) {
      return null;
    }
    List<SaleOrderHVO> heads = new ArrayList<SaleOrderHVO>();
    List<SaleOrderBVO> bodys = new ArrayList<SaleOrderBVO>();
    for (AbstractDataView view : views) {
      heads.add((SaleOrderHVO) view.getVO(SaleOrderHVO.class));
      bodys.add((SaleOrderBVO) view.getVO(SaleOrderBVO.class));
    }

    BillComposite<SaleOrderVO> bc =
        new BillComposite<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO bill = new SaleOrderVO();
    bc.append(bill.getMetaData().getParent(),
        heads.toArray(new SaleOrderHVO[heads.size()]));
    bc.append(bill.getMetaData().getVOMeta(SaleOrderBVO.class),
        bodys.toArray(new SaleOrderBVO[bodys.size()]));
    return bc.composite();
  }
}
