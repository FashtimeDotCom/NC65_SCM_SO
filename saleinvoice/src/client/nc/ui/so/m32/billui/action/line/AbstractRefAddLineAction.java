package nc.ui.so.m32.billui.action.line;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.IScmpubMaintain;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.AddLineUtil;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ���۷�Ʊ�������л���
 * �����˺ϲ��༭�²������еĴ���
 * 
 * @since 6.0
 * @version 2011-8-20 ����07:32:00
 * @author ô��
 */
public abstract class AbstractRefAddLineAction extends AbstractReferenceAction {

  /**
     * 
     */
  private static final long serialVersionUID = -6778240291241731465L;

  private SaleInvoiceEditor editor;

  private AbstractAppModel model;

  /**
   * ���췽��
   */
  public AbstractRefAddLineAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.editor.getValue();
    SaleInvoiceVO oldVO = (SaleInvoiceVO) obj;
    List<String> busitypes = new ArrayList<String>();
    busitypes.add(oldVO.getParentVO().getCbiztypeid());
    RefAddLineParaVO userobj = this.getUserOjb(oldVO);
    this.setBusitypes(busitypes);
    PfButtonClickContext context = this.createPfButtonClickContext(userobj);
    PfUtilClient.childButtonClickedNew(context);
    SaleInvoiceVO[] newvos = null;
    if (PfUtilClient.isCloseOK()) {
      newvos = (SaleInvoiceVO[]) PfUtilClient.getRetVos();
      if (VOChecker.isEmpty(newvos)) {
        return;
      }

      AddLineUtil util = new AddLineUtil();
      SaleInvoiceManageModel invoicemodel =
          (SaleInvoiceManageModel) this.getModel();
      CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
      // �ϲ���ʾ
      if (null != cachevo && cachevo.getBcombinflag()) {
        // ԭ�����ϵ
        MapList<String, SaleInvoiceBVO> oldmap = cachevo.getCombinRela();
        SaleInvoiceCombin combin = new SaleInvoiceCombin();

        SaleInvoiceVO detainvos = combin.splitEditSaleInvoice(oldVO, oldmap);
        util.checkCombinVO(detainvos, newvos);

        this.addLineCombinVO(newvos, cachevo);

        // �ϲ��༭�²�������
        SaleInvoiceVO newvo =
            combin.getCombinVOByRefAndLine(detainvos, newvos, cachevo);
        newvos = new SaleInvoiceVO[] {
          newvo
        };

      }
      else {
        util.checkCombinVO(oldVO, newvos);
        SaleInvoiceVO newvo = util.getCombin(oldVO, newvos);
        newvos = new SaleInvoiceVO[] {
          newvo
        };
      }
      this.afterTranProcessor(newvos);
      this.editor.setValue(newvos[0]);

    }
  }

  /**
   * 
   * @return ��Ʊ��Ƭ
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 
   * @return ��Ʊmodel
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public boolean isEnabled() {
    return this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD;
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
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  /**
   * 
   * @param vo
   * @return �Զ������
   */
  protected abstract RefAddLineParaVO getUserOjb(SaleInvoiceVO vo);

  /**
   * �ϲ��༭�����������ӱ�����
   * 
   * @param newvos ���й�����VOS
   * @param cachevo ��Ʊ�ϲ��Ļ������
   */
  private void addLineCombinVO(SaleInvoiceVO[] newvos, CombinCacheVO cachevo) {
    List<SaleInvoiceBVO> alNewBodys = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceVO vo : newvos) {
      for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {
        alNewBodys.add(bvo);
      }
    }
    IScmpubMaintain srv = NCLocator.getInstance().lookup(IScmpubMaintain.class);

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
    CombinResultVO combinres = combinutil.combinSaleInvoices(newvos, cachevo);
    MapList<String, SaleInvoiceBVO> newmap = combinres.getCombinRela();
    Set<String> newkeys = newmap.keySet();
    MapList<String, SaleInvoiceBVO> oldmap = cachevo.getCombinRela();
    for (String key : newkeys) {
      oldmap.putAll(key, newmap.get(key));
    }

  }

  private void afterTranProcessor(SaleInvoiceVO[] newvos) {

    // �к�����
    for (SaleInvoiceVO newvo : newvos) {
      VORowNoUtils.setVOsRowNoByRule(newvo.getAllChildrenVO(),
          SaleInvoiceBVO.CROWNO);
    }

    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(newvos);

  }

  private PfButtonClickContext createPfButtonClickContext(
      RefAddLineParaVO userobj) {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    context.setCurrBilltype(SOBillType.Invoice.getCode());
    context.setUserObj(userobj);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���
    context.setTransTypes(this.getTranstypes());
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }
}
