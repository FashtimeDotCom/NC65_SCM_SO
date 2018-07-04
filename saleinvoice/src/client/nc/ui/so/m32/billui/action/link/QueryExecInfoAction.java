package nc.ui.so.m32.billui.action.link;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.execinfo.ExecInfoReportDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.execinfo.ExecInfoReportVO;

public class QueryExecInfoAction extends NCAction {

  /**
     *
     */
  private static final long serialVersionUID = 7256797391370669894L;

  private SaleInvoiceEditor editor;

  private SaleInvoiceManageModel model;

  public QueryExecInfoAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.model.getSelectedOperaRows().length > 1) {

      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0007")/*@res "��ѡ��һ�����ݡ�"*/);
    }
    ExecInfoReportVO[] vos = this.getInvoiceReportVO();
    ExecInfoReportDialog dialog =
        new ExecInfoReportDialog(WorkbenchEnvironment.getInstance()
            .getWorkbench().getParent(), true);
    dialog.invoiceExecReport(vos);
    //start
    //modified by liylr for ֧�ֵ��������ֹ�������С����
    dialog.setResizable(true);
    //end
    dialog.showModal();
  }

  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    SaleInvoiceVO selectVO = (SaleInvoiceVO) this.model.getSelectedData();
    boolean isEnable =
        this.model.getUiState() == UIState.NOT_EDIT && selectVO != null;
    return isEnable;
  }

  /*
   * �N�۰lƱvo�D�Q�Ɉ�����rvo
   */
  private ExecInfoReportVO[] exchangeInvoiceVO(SaleInvoiceVO[] vos) {
    SaleInvoiceHVO hvo = vos[0].getParentVO();
    SaleInvoiceBVO[] bvos = vos[0].getChildrenVO();
    ExecInfoReportVO[] reptvos = new ExecInfoReportVO[bvos.length];
    for (int i = 0; i < bvos.length; i++) {
      reptvos[i] = new ExecInfoReportVO();
      // ����
      reptvos[i].setCmaterialid(bvos[i].getCmaterialvid());
      // ����λ
      reptvos[i].setCunitid(bvos[i].getCunitid());
      // ����
      UFDouble num = this.getUFDoubleValue(bvos[i].getNnum());
      reptvos[i].setNnum(num);
      // ��������:��Ʊ���ۼ�Ӧ������
      UFDouble invoicenum = this.getUFDoubleValue(bvos[i].getNtotalincomenum());
      reptvos[i].setNtotalinvoicenum(invoicenum);
      // ������
      UFDouble invoicemny = this.getUFDoubleValue(bvos[i].getNtotalincomemny());
      reptvos[i].setTotalinvoicemoney(invoicemny);
      // �տ���
      UFDouble mny = this.getUFDoubleValue(bvos[i].getNtotalpaymny());
      reptvos[i].setTotalpaymoney(mny);
      // ���ս��
      reptvos[i].setNeedpaymoney(invoicemny.sub(mny));
      reptvos[i].setCcurrencyid(hvo.getCcurrencyid());
    }
    return reptvos;
  }

  /*
   * ������۷�Ʊִ�����vo
   */
  private ExecInfoReportVO[] getInvoiceReportVO() {
    SaleInvoiceVO vo = (SaleInvoiceVO) this.editor.getModel().getSelectedData();
    String hid = vo.getParentVO().getCsaleinvoiceid();
    StringBuffer sql = new StringBuffer();
    sql.append("select distinct(so_saleinvoice."
        + SaleInvoiceHVO.CSALEINVOICEID + ")");
    sql.append("from so_saleinvoice LEFT OUTER JOIN so_saleinvoice_b ");
    sql.append(" ON so_saleinvoice_b." + SaleInvoiceHVO.CSALEINVOICEID
        + " = so_saleinvoice_b." + SaleInvoiceBVO.CSALEINVOICEID);
    sql.append(" where so_saleinvoice." + SaleInvoiceHVO.CSALEINVOICEID
        + " = '" + hid + "'");
    sql.append(" and so_saleinvoice.dr=0");
    sql.append(" and so_saleinvoice_b.dr = 0");
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      SaleInvoiceVO[] invoiceVOS = service.querySaleInvoice(sql.toString());
      if (null == invoiceVOS) {
        return null;
      }
      return this.exchangeInvoiceVO(invoiceVOS);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private UFDouble getUFDoubleValue(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }
    return value;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SO_INVOICEQUERYEXEC);
  }
}
