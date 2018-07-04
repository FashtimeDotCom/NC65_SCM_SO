package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleinvoiceUserObject;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.pub.util.ListUtil;

import nc.itf.pubapp.pub.exception.IResumeException;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;

/**
 * ���۷�Ʊ���水ť��Ӧ��
 * 
 * @since 6.0
 * @version 2011-8-20 ����04:41:43
 * @author ô��
 */
public class SaleInvoiceSaveAction extends SaveScriptAction {

  /** Version */
  private static final long serialVersionUID = 1545675826711527861L;

  /** ��Ƭ�ϵ�vo----�ϲ��༭��ʱ��ʹ�� */
  private SaleInvoiceVO editvo;

  /** Ҫɾ����bids ----�ϲ��༭��ʱ��ʹ�� */
  private Set<String> setDelbids = new HashSet<String>();

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    AppUiState oldstate = invoicemodel.getAppUiState();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    if (cachevo.getBcombinflag()) {
      /**
       * Ϊ���ܶ��ֶα�������У�飬�ڱ���֮ǰ����ϸVO�����˿�Ƭ��
       * ������������׳��쳣��Ҫ ��ԭ���ĺϲ�VO���·��ڿ�Ƭ������(��̨�쳣)
       */
      try {
        super.doAction(e);
      }
      catch (Exception ex) {
        invoicemodel.setAppUiState(oldstate);
        this.editor.setValue(this.editvo);
        throw ex;
      }
    }
    else {
      super.doAction(e);
    }

  }

  @Override
  public void doBeforAction() {

    // ����Ʊ�ϲ���ʾ
    this.processCombinShow();
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (!cachevo.getBcombinflag()) {
      super.doBeforAction();
    }
    //���ô��ݵ���̨������
    this.setUserObj();
  }

  @Override
  protected void fillUpContext(PFlowContext context) {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    if (cachevo.getBcombinflag()) {
      /**
       * Ϊ���ܶ��ֶα�������У�飬�ڱ���֮ǰ����ϸVO�����˿�Ƭ��
       * ������������׳��쳣��Ҫ ��ԭ���ĺϲ�VO���·��ڿ�Ƭ������
       */
      try {
        super.fillUpContext(context);
      }
      catch (Exception e) {
        this.editor.setValue(this.editvo);
      }
    }
    else {
      super.fillUpContext(context);
    }

  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
  }

  @Override
  protected void processReturnObj(Object[] pretObj) throws Exception {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (cachevo.getBcombinflag()) {
      SaleInvoiceVO[] retdetailvos = (SaleInvoiceVO[]) pretObj;
      this.updateTSAndVOState(retdetailvos, cachevo.getCombinRela());
      SaleInvoiceVO comInvoicevo = this.editvo;

      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      SaleInvoiceVO detainvo =
          combin.splitEditSaleInvoice(comInvoicevo, cachevo.getCombinRela());

      if (this.setDelbids.size() > 0) {
        List<SaleInvoiceBVO> bvolist = new ArrayList<SaleInvoiceBVO>();
        SaleInvoiceBVO[] bvos =
            (SaleInvoiceBVO[]) detainvo.getChildren(SaleInvoiceBVO.class);
        for (SaleInvoiceBVO bvo : bvos) {
          if (!this.setDelbids.contains(bvo.getCsaleinvoicebid())) {
            bvolist.add(bvo);
          }
        }
        detainvo.setChildrenVO(ListUtil.toArray(bvolist));
      }
      SaleInvoiceVO[] uidetailvos = new SaleInvoiceVO[] {
        detainvo
      };
      new ClientBillCombinServer<SaleInvoiceVO>().combine(uidetailvos,
          retdetailvos);
      SaleInvoiceBVO[] newbvos = uidetailvos[0].getChildrenVO();

      SaleInvoiceVO[] oldcomvos = new SaleInvoiceVO[] {
        this.editvo
      };
      SaleInvoiceBVO[] oldcombvos = oldcomvos[0].getChildrenVO();
      combin.updateEditCombinRela(oldcombvos, newbvos, cachevo.getCombinRela(),
          this.setDelbids);

      SaleInvoiceVO newcombinvo =
          (SaleInvoiceVO) this.getCombinNewVO(uidetailvos[0], cachevo);

      // ��ȫ��ͷ���ݣ�����ʹ�ò���vo����ֱ�Ӱɱ�ͷ���ݸ���newcombinvo��
      // this.processHeadVO(newcombinvo, newdetailvos);
      // newcombinvo.setParentVO(uidetailvos[0].getParentVO());
      this.editor.setValue(newcombinvo);
      this.setFullOldVOs(new SaleInvoiceVO[] {
        newcombinvo
      });
      super.processReturnObj(new SaleInvoiceVO[] {
        newcombinvo
      });
    }
    else {
      super.processReturnObj(pretObj);
    }
  }

  private AggregatedValueObject getCombinNewVO(AggregatedValueObject newVO,
      CombinCacheVO cachevo) {
    SaleInvoiceVO[] invoicevo = new SaleInvoiceVO[] {
      (SaleInvoiceVO) newVO
    };

    SaleInvoiceBVO[] bvos = invoicevo[0].getChildrenVO();
    for (SaleInvoiceBVO bvo : bvos) {
      cachevo.getCombinRela().remove(bvo.getCsaleinvoicebid());
    }

    SaleInvoiceCombin combinutil = new SaleInvoiceCombin();
    CombinResultVO combinres =
        combinutil.combinSaleInvoices(invoicevo, cachevo);

    return combinres.getCombinvos()[0];
  }

  @Override
  protected AbstractBill[] produceLightVO(AbstractBill[] newVO) {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (cachevo.getBcombinflag()) {
      if (!this.isLightBillUsed()) {
        return newVO;
      }
      ClientBillToServer<IBill> tool = new ClientBillToServer<IBill>();
      AbstractBill[] oldVO = newVO;
      IBill[] lightVOs = null;
      if (AppUiState.EDIT == this.model.getAppUiState()) {
        oldVO = new AbstractBill[] {
          (AbstractBill) this.model.getSelectedData()
        };
        SaleInvoiceCombin combin = new SaleInvoiceCombin();
        SaleInvoiceVO detainvo =
            combin.splitEditSaleInvoice((SaleInvoiceVO) oldVO[0],
                cachevo.getCombinRela());
        // SaleInvoiceBVO[] bvos =
        // (SaleInvoiceBVO[]) detainvo.getChildren(SaleInvoiceBVO.class);
        // Set<String> delbids = new HashSet<String>();
        // for (SaleInvoiceBVO bvo : bvos) {
        // if (VOStatus.DELETED == bvo.getStatus()) {
        // delbids.add(bvo.getCsaleinvoicebid());
        // }
        // }
        if (this.setDelbids.size() > 0) {
          SaleInvoiceBVO[] newbvos =
              (SaleInvoiceBVO[]) ((SaleInvoiceVO) newVO[0])
                  .getChildren(SaleInvoiceBVO.class);
          List<SaleInvoiceBVO> bvolist = new ArrayList<SaleInvoiceBVO>();
          for (SaleInvoiceBVO bvo : newbvos) {
            if (!this.setDelbids.contains(bvo.getCsaleinvoicebid())) {
              bvolist.add(bvo);
            }
          }
          newVO[0].setChildren(SaleInvoiceBVO.class, ListUtil.toArray(bvolist));
        }
        lightVOs = tool.construct(new SaleInvoiceVO[] {
          detainvo
        }, newVO);
      }
      else {
        lightVOs = tool.construct(oldVO, newVO);
      }

      // ����󲹳������ֶ�
      this.fillInfoAfterLight((AbstractBill[]) lightVOs);
      return (AbstractBill[]) lightVOs;
    }
    return super.produceLightVO(newVO);
  }

  private void processCombinShow() {

    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (cachevo.getBcombinflag()) {
      SaleInvoiceVO comInvoicevo = (SaleInvoiceVO) this.editor.getValue();
      this.editvo = comInvoicevo;
      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      SaleInvoiceVO detainvo =
          combin.splitEditSaleInvoice(comInvoicevo, cachevo.getCombinRela());
      for (SaleInvoiceBVO det : detainvo.getChildrenVO()) {
        if (VOStatus.DELETED == det.getStatus()) {
          this.setDelbids.add(det.getPrimaryKey());
        }
      }
      this.editor.setValue(detainvo);
      this.getFlowContext().setBillVo(detainvo);
    }

  }


  private void setUserObj() {
    SaleInvoiceEditor billform = (SaleInvoiceEditor) this.editor;
    PfUserObject userObj = this.getFlowContext().getUserObj();
    userObj = userObj == null ? new PfUserObject() : userObj;
 
    SaleinvoiceUserObject invoiceuserobj=new SaleinvoiceUserObject();
    invoiceuserobj.setTempvo(billform.getTempvo());
    invoiceuserobj.setIsclientsave(true);
    
    userObj.setUserObject(invoiceuserobj);
    this.getFlowContext().setUserObj(userObj);
  }

  /**
   * ����VO״̬�� ts Ϊ���ڱ���֮��ֱ������������
   * 
   * @param retdetailvos
   * @param combinRela
   */
  private void updateTSAndVOState(SaleInvoiceVO[] retdetailvos,
      MapList<String, SaleInvoiceBVO> combinRela) {
    Map<String, SaleInvoiceBVO> bidtsmap =
        new HashMap<String, SaleInvoiceBVO>();
    for (SaleInvoiceVO vo : retdetailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        bidtsmap.put(bvo.getCsaleinvoicebid(), bvo);
      }
    }
    Set<String> keys = combinRela.keySet();
    for (String key : keys) {
      List<SaleInvoiceBVO> bvolist = combinRela.get(key);
      for (SaleInvoiceBVO bvo : bvolist) {
        SaleInvoiceBVO retbvo = bidtsmap.get(bvo.getCsaleinvoicebid());

        if (null == retbvo) {
          continue;
        }
        Set<String> attrs = retbvo.usedAttributeNames();
        for (String attr : attrs) {
          bvo.setAttributeValue(attr, retbvo.getAttributeValue(attr));
        }
        bvo.setStatus(VOStatus.UNCHANGED);
      }
    }
  }
}
