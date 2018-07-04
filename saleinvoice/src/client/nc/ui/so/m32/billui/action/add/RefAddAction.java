package nc.ui.so.m32.billui.action.add;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.IScmpubMaintain;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.UIState;

/**
 * ���۷�Ʊ��������
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:52:30
 * @author yaogj
 */
public class RefAddAction extends AbstractReferenceAction {

  /**
     * 
     */
  private static final long serialVersionUID = 8278944084279171430L;

  private SaleInvoiceEditor editor;

  private SaleInvoiceManageModel model;

  /**
   * RefAddAction �Ĺ�����
   */
  public RefAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PfButtonClickContext context = this.createPfButtonClickContext();
    PfUtilClient.childButtonClickedNew(context);

    SaleInvoiceVO[] newvos = null;
    if (PfUtilClient.isCloseOK()) {
      newvos = (SaleInvoiceVO[]) PfUtilClient.getRetVos();
      if (VOChecker.isEmpty(newvos)) {
        return;
      }
      // ������ת������ǰ
      this.beforeTranProcessor(newvos);
      // ������Ų����ǻ�����ʾ�Ļ�
      CombinResultVO combinres = this.combinSaleInvoices(newvos);
      if (combinres.getBcombinflag()) {
        newvos = combinres.getCombinvos();
      }
      // ������ܽ��
      this.getModel().setCombinCacheVO(combinres.getCacheVO());
      // �������ݽ��洦��
      this.getTransferViewProcessor().processBillTransfer(newvos);
    }
  }

  /**
   * ���������������������۷�ƱEditor��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-29 ����07:24:42
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * ���������������������۷�Ʊ��model��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-29 ����07:25:28
   */
  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  /**
   * ��ť��������
   */
  @Override
  public boolean isEnabled() {
    return this.getModel().getUiState() == UIState.NOT_EDIT;
  }

  /**
   * 
   * @param view ��Ʊ��Ƭ
   */
  public void setEditor(SaleInvoiceEditor view) {
    this.editor = view;
  }

  /**
   * 
   * @param model ��Ʊmodel
   */
  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  /**
   * �����������������ù���ת��������ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param newvos
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-29 ����07:28:04
   */
  protected void beforeTranProcessor(SaleInvoiceVO[] newvos) {
    if (newvos != null && newvos.length > 0) {
      // �к�����
      for (SaleInvoiceVO newvo : newvos) {
        VORowNoUtils.setVOsRowNoByRule(newvo.getAllChildrenVO(),
            SaleInvoiceBVO.CROWNO);
      }
      // �����ͷ�ϼ���ֵ
      HeadTotalCalcUtil.getInstance().calcHeadTotalValue(newvos);
    }
  }

  protected CombinResultVO combinSaleInvoices(SaleInvoiceVO[] newvos) {

    CombinResultVO combinres = null;
    CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
    if (cachevo.getBcombinflag()) {
      List<SaleInvoiceBVO> alNewBodys = new ArrayList<SaleInvoiceBVO>();
      List<SaleInvoiceVO> alNewHeads = new ArrayList<SaleInvoiceVO>();
      for (SaleInvoiceVO vo : newvos) {
        alNewHeads.add(vo);
        for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {
          alNewBodys.add(bvo);
        }
      }
      IScmpubMaintain srv =
          NCLocator.getInstance().lookup(IScmpubMaintain.class);

      /**
       * ����ֻ�����ӱ�ID ԭ����Ҫ������id�ж����������޸�
       * ����ID�ŵ�����ǰ��������
       */
      String[] ids = null;
      try {
        ids = srv.getIDs(alNewBodys.size());
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      if (null != ids) {
        int i = 0;
        for (SaleInvoiceVO vo : newvos) {
          vo.getParentVO().setStatus(VOStatus.NEW);
          for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {
            bvo.setPrimaryKey(ids[i]);
            bvo.setStatus(VOStatus.NEW);
            i++;
          }
        }
      }

      SaleInvoiceCombin combinutil = new SaleInvoiceCombin();
      combinres = combinutil.combinSaleInvoices(newvos, cachevo);
    }
    else {
      combinres = new CombinResultVO(false);
    }
    return combinres;
  }

  public PfButtonClickContext createPfButtonClickContext() {

    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    context.setCurrBilltype(SOBillType.Invoice.getCode());
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���
    context.setTransTypes(this.getTranstypes());
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);

    if (SysInitGroupQuery.isICEnabled()) {
      try {
        RefAddLineParaVO userobj = new RefAddLineParaVO();
        String trantype =
            TrantypeFuncUtils.getTrantype(this.getModel().getContext());
        PfAddInfo[] vosInfoAdd =
            PfUtilClient.retAddInfo(SOBillType.Invoice.getCode(), trantype,
                this.getModel().getContext().getPk_group(), this.getModel()
                    .getContext().getPk_loginUser(), true);
        // û�г��⵽��Ʊ�İ�ť
        if (null == vosInfoAdd) {
          return context;
        }
        for (PfAddInfo voInfoAdd : vosInfoAdd) {
          if (ICBillType.SaleOut.getCode().equals(voInfoAdd.getSrc_billtype())) {
            List<String> busitypes = voInfoAdd.getBusitypes();
            userobj
                .setBusitypes(busitypes.toArray(new String[busitypes.size()]));
          }
        }
        context.setUserObj(userobj);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return context;
  }

  /**
   * �޸�ҵ������
   * 
   * @param pbillVOs
   */
  // private void setDefaultValue(SaleInvoiceVO[] pbillVOs) {
  // SaleInvoiceVO[] vos = pbillVOs;
  // UFDate date = AppContext.getInstance().getBusiDate();
  // for (SaleInvoiceVO vo : vos) {
  // vo.getParentVO().setDbilldate(date);
  // SaleInvoiceBVO[] bvos = vo.getChildrenVO();
  // for (SaleInvoiceBVO bvo : bvos) {
  // bvo.setDbilldate(date);
  // }
  // }
  // }
}
