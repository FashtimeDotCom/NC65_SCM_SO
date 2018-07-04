package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.scmpub.goldtax.TransGoldTaxDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.goldtax.GoldTaxBodyVO;
import nc.vo.scmpub.goldtax.GoldTaxHeadVO;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ��Ʊ����˰
 * 
 * @since 6.0
 * @version 2011-11-16 ����04:18:53
 * @author ô��
 */
public class ExportGoldTaxAction extends NCAction {

  /**
     *
     */
  private static final long serialVersionUID = 5739522957642379189L;

  private AbstractAppModel model;
  
  //== lijj ��ӱ�������ó�ֲġ������ ��������ҵ����==
  //== Ĭ��ֵΪ0Ϊ��׼����˰�� 1Ϊ������ 2Ϊ��ó�ֲġ�3Ϊ����� ==
  private String busiType = "0";
  
  public String getBusiType() {
	return busiType;
  }

  public void setBusiType(String busiType) {
	this.busiType = busiType;
	
    //����ҵ����İ�ť����
	switch (busiType) {
		case "1":
			setBtnName("��������˰");
			break;
		case "2":
			setBtnName("����ó�ֲĽ�˰");
			break;
		case "3":
			setBtnName("������ѽ�˰");
			break;
		default:
			break;
	}
    
  }
  //== lijj ��ӱ�������ó�ֲġ������ ��������ҵ����  Ĭ��ֵΪ0 Ϊ��׼����˰==
  

  public ExportGoldTaxAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ѡ�з�ƱVOs
    BillManageModel billmodel = (BillManageModel) this.getModel();
    Object[] selectDatas = billmodel.getSelectedOperaDatas();

    int ilength = selectDatas.length;
    SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voInvoices[i] = (SaleInvoiceVO) selectDatas[i];
    }

    // ת���ɽ�˰VOs
    GoldTaxVO[] gtvos = this.chgSaleInvoiceToGoldtax(voInvoices);
    
    //=== lijj ����������������ҵ���ر��� ===
    resetGoldTaxVO(gtvos);
    //=== lijj ����������������ҵ���ر��� ===
    
    // ������˰�Ի���
    TransGoldTaxDlg goldtaxdlg =
        new TransGoldTaxDlg(WorkbenchEnvironment.getInstance().getWorkbench());
    goldtaxdlg.setGoldTaxVOs(gtvos);
    
    goldtaxdlg.setPkOrg(voInvoices[0].getParentVO().getPk_org());
    
    //==== lijj ��ҵ�����ʹ���dlg,�кϲ�������ҵ����Ҫ����====
    goldtaxdlg.setBusiType(busiType);
    //==== lijj ��ҵ�����ʹ���dlg,�кϲ�������ҵ����Ҫ����====

    if (UIDialog.ID_OK == goldtaxdlg.showModal()) {
      SaleInvoiceHVO[] voHeads = this.getUpdateHeadVOs(voInvoices);
      ISaleInvoiceMaintain updatesrv =
          NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);

      SaleInvoiceHVO[] retHeads = updatesrv.updateWhenExportGoldTax(voHeads);
      this.updateClientVOs(voInvoices, retHeads);

      for (SaleInvoiceVO newvo : voInvoices) {
        billmodel.directlyUpdate(newvo);
      }
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0002")/*@res "��˰�����ɹ�"*/, billmodel.getContext());
    }
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
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

  private GoldTaxVO[] chgSaleInvoiceToGoldtax(SaleInvoiceVO[] voInvoices) {
    // �ǿ�У��
    if (null == voInvoices || voInvoices.length == 0) {
      return new GoldTaxVO[0];
    }
    this.validateCheck(voInvoices);

    List<String> bidlist = new ArrayList<String>();
    for (SaleInvoiceVO vo : voInvoices) {
      bidlist.add(vo.getParentVO().getCsaleinvoiceid());
    }

    ISaleInvoiceMaintain srv =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);

    GoldTaxVO[] goldtaxvos = null;
    try {
      goldtaxvos =
          srv.executeVOChangeTogtax(bidlist.toArray(new String[bidlist.size()]));
      for (GoldTaxVO goldtaxvo : goldtaxvos) {
        // ����ʶ
        goldtaxvo.setBillIdentifier("SJJK0101");
        // ������
        goldtaxvo.setBillName(NCLangRes.getInstance().getStrByID("4006008_0",
            "04006008-0077")/*���۷�Ʊ����*/);
        // ��Դģ��
        goldtaxvo.setSourceModule(NCModule.SO.getName());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // GoldTaxVO[] goldtaxvos =
    // PfServiceScmUtil.executeVOChange(SOBillType.Invoice.getCode(), "gtax",
    // voInvoices);

    return goldtaxvos;
  }

  private SaleInvoiceHVO[] getUpdateHeadVOs(SaleInvoiceVO[] voInvoices) {
    int ilength = voInvoices.length;
    UFDateTime curtime = AppContext.getInstance().getServerTime();
    SaleInvoiceHVO[] voHeads = new SaleInvoiceHVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voHeads[i] = new SaleInvoiceHVO();
      voHeads[i].setCsaleinvoiceid(voInvoices[i].getParentVO()
          .getCsaleinvoiceid());
      // �Ƿ񴫽�˰��ʶ
      voHeads[i].setBtogoldtaxflag(UFBoolean.TRUE);
      // ��󴫽�˰ʱ��
      voHeads[i].setTgoldtaxtime(curtime);
    }
    return voHeads;
  }

  private void initializeAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.SCM_EXPORTGOLDTAX);
  }

  private void updateClientVOs(SaleInvoiceVO[] voInvoices,
      SaleInvoiceHVO[] retHeads) {

    int ilength = voInvoices.length;
    for (int i = 0; i < ilength; i++) {
      SaleInvoiceHVO orighead = voInvoices[i].getParentVO();
      orighead.setBtogoldtaxflag(retHeads[i].getBtogoldtaxflag());
      orighead.setTgoldtaxtime(retHeads[i].getTgoldtaxtime());
      orighead.setTs(retHeads[i].getTs());
    }
  }

  private void validateCheck(SaleInvoiceVO[] voInvoices) {
    // ��Ʊ��֯У��
    SaleInvoiceHVO voHead = voInvoices[0].getParentVO();
    String hid = voHead.getCsaleinvoiceid();
    String pk_org = voHead.getPk_org();
    List<String> valiBillcode = new ArrayList<String>();
    for (SaleInvoiceVO invoice : voInvoices) {
      SaleInvoiceHVO hvo = invoice.getParentVO();
      if (!pk_org.equals(hvo.getPk_org())
          && !hid.equals(hvo.getCsaleinvoiceid())) {
        valiBillcode.add(invoice.getParentVO().getVbillcode());
      }
    }
    if (valiBillcode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      StringBuilder msgCode = new StringBuilder();
      for (String code : valiBillcode) {
        msgCode.append("[" + code + "]");
      }
      msg.append(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0114", null, new String[] {
            msgCode.toString()
          })/*���е��ݣ�{0}��ѡ�е�һ�ŵ��ݿ�Ʊ��֯��ͬ������ͬʱ����˰*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }

  }
  
  /**
   * 
   * TODO ���ݰ�������Ҫ�������ҵ��Խ�˰VO����ֵ���¼ӹ�����
   */
  private void resetGoldTaxVO(GoldTaxVO[] gtvos){
	  if(gtvos == null || gtvos.length == 0){
		return;
	  }
	  
	  //1- lijj ��������ҵ�񣨺���׼����ͳһ�� 	�������������˺š�����Ϊ�ͻ�������������+�ͻ������˺�===
	  for(GoldTaxVO taxVO : gtvos){
		GoldTaxHeadVO headvo = taxVO.getParentVO();
		
		//������������
		String bankName = headvo.getBankname();
		if(bankName == null)
			bankName = "";
		
		//�����������˺� 
		String account = headvo.getAccount();
		if(account == null)
			account = "";
		
		//�����������˺� ���¸�ֵ
		headvo.setAccount(bankName + account);
	  }
	    
	  //2- lijj ����ҵ���� �ֱ�������⴦�� ====
	  switch (busiType) {
		case "1":
			//===��������ҵ���� =====
		    /*
		     * �������������ӵ�����ͺ��У�����������λ�ý��и�ֵ��������
		     */
	    	for(GoldTaxVO taxVO : gtvos){
	    		GoldTaxBodyVO[] bodyvos = taxVO.getChildrenVO();
	    		if(bodyvos != null && bodyvos.length > 0){
	    			for(GoldTaxBodyVO bodyvo : bodyvos){
	    				String invname = bodyvo.getInvname();
	    				String invspec = bodyvo.getInvspec();
	    				
	    				if(invspec == null){
	    					invspec = "";
	    				}
	    				bodyvo.setInvname("����");
	    				bodyvo.setInvspec(invspec + invname);
	    			}
	    		}
	    	}
	    	
			break;
		case "2":
			//=== ��ó�ֲ�����ҵ����  ====
			/*
		     * ���������ơ�����Ϊ���ϻ�����������+��������
			 * �������ͺš�����Ϊ�Զ�����15
		     */
			for(GoldTaxVO taxVO : gtvos){
				GoldTaxBodyVO[] bodyvos = taxVO.getChildrenVO();
	    		if(bodyvos != null && bodyvos.length > 0){
	    			for(GoldTaxBodyVO bodyvo : bodyvos){
	    				//��������
	    				String invname = bodyvo.getInvname();
	    				
	    				//��������
	    				String invclassname = bodyvo.getInvclassname();
	    				if(invclassname == null){
	    					invclassname = "";
	    				}
	    				
	    				String def15 = bodyvo.getVdef15();
	    				if(def15 == null){
	    					def15 = "";
	    				}
	    				
	    				bodyvo.setInvname(invclassname + invname);
	    				bodyvo.setInvspec(def15);
	    			}
	    		}
			}
			
			break;
		case "3":
			//=== ��ó�ֲ�����ҵ����  ====
			/*
		     * ���������ơ�Ϊ�̶�ֵ������ѡ�
		     */
			for(GoldTaxVO taxVO : gtvos){
	    		GoldTaxBodyVO[] bodyvos = taxVO.getChildrenVO();
	    		if(bodyvos != null && bodyvos.length > 0){
	    			for(GoldTaxBodyVO bodyvo : bodyvos){
	    				bodyvo.setInvname("�����");
	    				
	    				String def15 = bodyvo.getVdef15();
	    				if(def15 == null){
	    					def15 = "";
	    				}
	    				bodyvo.setInvspec(def15);
	    				
	    			}
	    		}
	    	}
			break;
		default:
			//do nothing
			break;
	}
	    
  }

}
