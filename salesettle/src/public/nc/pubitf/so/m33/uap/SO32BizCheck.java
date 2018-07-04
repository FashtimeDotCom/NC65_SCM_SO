package nc.pubitf.so.m33.uap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pf.ISaveAfterMsgDrive;
import nc.itf.scmpub.reference.uap.pf.PFConfig;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>ҵ�����������۷�Ʊ��Ϣ��������У��</b>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-9-14 ����04:02:04
 */
public class SO32BizCheck implements ISaveAfterMsgDrive {

  private boolean asdjustIncome32;

  private boolean autoCost32;

  private boolean autoCost4C;

  private boolean autoEstimate4C;

  private boolean autoIncome32;

  private boolean autoIncome4C;

  private boolean autoResigeter4C;

  // ��Ʊ���
  private boolean invFirst;

  // ҵ�����������۷�Ʊ�Ľ������ͣ�Ϊ�յĻ���ȡ��������
  private String invoiceTransType;

  // ��Ʊ����
  private boolean invOutParallel;

  private boolean manualCost4C;

  private boolean manualIncome4C;

  // ֻ�з�Ʊ
  private boolean onlyInv;

  // ֻ�г��ⵥ
  private boolean onlyOut;

  // �Ȼ���Ʊ
  private boolean outFirst;

  // ҵ�����������۳��ⵥ�Ľ������ͣ�Ϊ�յĻ���ȡ��������
  private String outTransType;

  @Override
  public void affectBusiForMsgDrive(MessagedriveVO mvo)
      throws BusinessException {
    // ���۷�Ʊ�������������۳��ⵥǩ��������Ϣ����
    if (SOBillAction.SaleInvoiceApprove.getCode().equals(mvo.getSourceaction())
        || SOBillAction.SaleOutSIGN.getCode().equals(mvo.getSourceaction())) {

      // ��ʼ��ҵ�����������۳��ⵥ�����۷�Ʊ�Ľ�������
      this.initOutInvoiceType(mvo);

      // ��ʼ����Ʊ������Ȼ���Ʊ��Ʊ�����б�־
      this.initBizFlag(mvo);

      // ��ʼ�����۷�Ʊ�����۳��ⵥ��������Ϣ����
      this.initAction(mvo);

      try {
        // ҵ��У��
        this.check();
      }
      catch (Exception e) {
        ExceptionUtils.marsh(e);
      }

    }
  }

  private void check() {
    if (this.outFirst) {
      this.checkoutFirst();
    }
    else if (this.invFirst) {
      this.checkinvFirst();
    }
    else if (this.invOutParallel) {
      this.checkinvOutParallel();
    }
    else if (this.onlyInv) {
      this.checkonlyInv();
    }
    else if (this.onlyOut) {
      this.checkonlyOut();
    }
  }

  private void checkinvFirst() {
    String msg = null;
    if (this.autoEstimate4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0067")/*@res "��Ʊ���ҵ�����۳��ⵥ���������Զ��ݹ�Ӧ��!"*/;
    }
    else if (this.autoResigeter4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0068")/*@res "��Ʊ���ҵ�����۳��ⵥ���������Զ����뷢����Ʒ!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }

    this.checkonlyInv();
    this.checkonlyOut();
    this.checkpub();
  }

  private void checkinvOutParallel() {
    String msg = null;
    if (this.autoEstimate4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0069")/*@res "��Ʊ����ҵ�����۳��ⵥ���������Զ��ݹ�Ӧ��!"*/;
    }
    else if (this.autoResigeter4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0070")/*@res "��Ʊ����ҵ�����۳��ⵥ���������Զ����뷢����Ʒ!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }

    this.checkonlyInv();
    this.checkonlyOut();
    this.checkpub();
  }

  private void checkonlyInv() {
    String msg = null;
    if (this.autoIncome32 && this.asdjustIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0071")/*@res "���۷�Ʊ����ͬʱ�����Զ�Ӧ�ս���Ͳ�Ӧ��!"*/;
    }
    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void checkonlyOut() {
    String msg = null;
    if (this.autoCost4C && this.autoResigeter4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0072")/*@res "���۳��ⵥ����ͬʱ�����Զ��ɱ�������Զ����뷢����Ʒ!"*/;
    }
    else if (this.autoIncome4C && this.autoEstimate4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0073")/*@res "���۳��ⵥ����ͬʱ�����Զ�Ӧ�ս�����Զ��ݹ�Ӧ��!"*/;
    }
    else if (this.autoIncome4C && this.manualIncome4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0074")/*@res "���۳��ⵥ����ͬʱ�����Զ�Ӧ�ս�����ֹ�Ӧ�ս���!"*/;
    }
    else if (this.autoResigeter4C && this.manualCost4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0075")/*@res "���۳��ⵥ����ͬʱ�����Զ����뷢����Ʒ���ֹ��ɱ�����!"*/;
    }
    else if (this.manualCost4C && this.autoCost4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0076")/*@res "���۳��ⵥ����ͬʱ�����Զ��ɱ�������ֹ��ɱ�����!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void checkoutFirst() {

    this.checkonlyInv();
    this.checkonlyOut();
    this.checkpub();
  }

  private void checkpub() {
    String msg = null;
    if (this.autoIncome4C && this.autoIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0077")/*@res "���۳��ⵥ�����۷�Ʊ����ͬʱ�����Զ�Ӧ�ս���!"*/;
    }
    else if (this.autoCost4C && this.autoCost32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0078")/*@res "���۳��ⵥ�����۷�Ʊ����ͬʱ�����Զ��ɱ�����!"*/;
    }
    else if (this.manualCost4C && this.autoCost32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0079")/*@res "���۳��ⵥΪ�ֹ��ɱ�����ʱ�����۷�Ʊ����ͬʱ�����Զ��ɱ�����!"*/;
    }
    else if (this.manualIncome4C && this.autoIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0080")/*@res "���۳��ⵥΪ�ֹ�Ӧ�ս���ʱ�����۷�Ʊ����ͬʱ�����Զ�Ӧ�ս���!"*/;
    }
    else if (this.manualIncome4C && this.asdjustIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0140")/*@res "��Ӧ�ղ�֧�����۳��ⵥ�ֹ��������!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void init32Action(MessagedriveVO mvo) {
    MessagedriveVO[] md32vos =
        PfServiceScmUtil.queryAllMsgdrvVOs(this.invoiceTransType,
            mvo.getPk_sourcebusinesstype(),
            SOBillAction.SaleInvoiceApprove.getCode());

    if (VOChecker.isEmpty(md32vos)) {
      return;
    }

    for (MessagedriveVO mdvo : md32vos) {
      String actiontype = mdvo.getActiontype();
      if (!VOChecker.isEmpty(actiontype)) {
        if (SOBillAction.SaleInvoiceSQUAREINCOME.getCode().equals(actiontype)) {
          this.autoIncome32 = true;
        }
        else if (SOBillAction.SaleInvoiceSQUARECOST.getCode()
            .equals(actiontype)) {
          this.autoCost32 = true;
        }
        else if (SOBillAction.SaleInvoiceADJUSTINCOME.getCode().equals(
            actiontype)) {
          this.asdjustIncome32 = true;
        }
      }
    }
  }

  private void init4CAction(MessagedriveVO mvo) {
    MessagedriveVO[] md4Cvos =
        PfServiceScmUtil.queryAllMsgdrvVOs(this.outTransType,
            mvo.getPk_sourcebusinesstype(), SOBillAction.SaleOutSIGN.getCode());

    if (VOChecker.isEmpty(md4Cvos)) {
      return;
    }

    for (MessagedriveVO mdvo : md4Cvos) {
      String actiontype = mdvo.getActiontype();
      if (!VOChecker.isEmpty(actiontype)) {
        if (SOBillAction.SaleOutAutoAR.getCode().equals(actiontype)) {
          this.autoIncome4C = true;
        }
        else if (SOBillAction.SaleOutAutoCost.getCode().equals(actiontype)) {
          this.autoCost4C = true;
        }
        else if (SOBillAction.SaleOutAutoEsti.getCode().equals(actiontype)) {
          this.autoEstimate4C = true;
        }
        else if (SOBillAction.SaleOutAutoRegister.getCode().equals(actiontype)) {
          this.autoResigeter4C = true;
        }
        else if (SOBillAction.SaleOutManualAR.getCode().equals(actiontype)) {
          this.manualIncome4C = true;
        }
        else if (SOBillAction.SaleOutManualCost.getCode().equals(actiontype)) {
          this.manualCost4C = true;
        }
      }
    }
  }

  private void initAction(MessagedriveVO mvo) {
    if (this.outFirst || this.invFirst || this.invOutParallel) {
      this.init32Action(mvo);
      this.init4CAction(mvo);
    }
    else if (this.onlyInv) {
      this.init32Action(mvo);
    }
    else if (this.onlyOut) {
      this.init4CAction(mvo);
    }
  }

  /**
   * ����������������ʼ����Ʊ������Ȼ���Ʊ��Ʊ�����б�־
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author zhangcheng
   * @time 2010-9-14 ����04:46:36
   */
  private void initBizFlag(MessagedriveVO mvo) {
    
    String curBillType =
        PfServiceScmUtil.getBillTypeByTransType(mvo.getPk_sourcebilltype());
    // jilu for �㰲��Ŀ 20140904 ҵ��������������Ʊ��������ⵥ�����ݹ�Ӧ�գ������������Ƴ��ⵥ���������
    // �����ǰ��������Ϊ30������Ҫ�ж�����Ʊ������ֻ���Ʊ���ǻ�Ʊͬ�У�ֻ�е�ǰ��������Ϊ32����4c���жϲ�����
    if (SOBillType.Order.getCode().equals(curBillType)) {
      return;
    }

    // ��Դ
    BillbusinessVO[] beforebinessVos =
        PfServiceScmUtil.querybillSource(InvocationInfoProxy.getInstance()
            .getGroupId(), mvo.getPk_sourcebilltype(), mvo
            .getPk_sourcebusinesstype(), true);

    for (BillbusinessVO bvo : beforebinessVos) {
      // �Ȼ���Ʊ
      if (SOBillType.Invoice.getCode().equals(curBillType)
          && ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.outFirst = true;
        break;
      }
      // modify by zhangby5 �ϲ���Ŀ���� NCdp205454502 begin
      // ��Ʊ���
      if (ICBillType.SaleOut.getCode().equals(curBillType)
          && SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.invFirst = true;
        break;
      }
    }

    // Ŀ��
    BillbusinessVO[] afterbinessVos =
        PfServiceScmUtil.queryBillDest(mvo.getPk_sourcebilltype(),
            mvo.getPk_sourcebusinesstype());

    for (BillbusinessVO bvo : afterbinessVos) {
      // ��Ʊ���
      if (SOBillType.Invoice.getCode().equals(curBillType)
          && ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.invFirst = true;
        break;
      }
      // �Ȼ���Ʊ
      if (ICBillType.SaleOut.getCode().equals(curBillType)
          && SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.outFirst = true;
        break;
      }
    }

    // ��Ʊ���л�������
    if (!this.outFirst && !this.invFirst) {
      BillbusinessVO[] bvos =
          PFConfig.findBillbusinessVOs(AppContext.getInstance().getPkGroup(),
              mvo.getPk_sourcebusinesstype());

      for (BillbusinessVO bvo : bvos) {
    	//begin-ncm-chengqiang5-���ҵ�������÷������Ƴ��ⵥ���������
    	  // Ŀ��
          BillbusinessVO[] afterbinessVoss =
              PfServiceScmUtil.queryBillDest(bvo.getPk_billtype(),
                  bvo.getPk_businesstype());

          if (SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
              //�����Ʊ����û��Ŀ�ĵ��ݣ����ж�ֻ�з�Ʊ
        	  if (afterbinessVoss.length == 0) {
            	  this.onlyInv = true;
              } else {
            	  for (BillbusinessVO abvo : afterbinessVoss) {
            		  //�����Ʊ�������ǳ��ⵥ�����ж�Ϊ��Ʊ���
            		  if (ICBillType.SaleOut.getCode().equals(abvo.getPk_billtype())) {
            			  this.invFirst = true;
            			  break;
            		  }
            	  }
              }
          } else if (ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        	  //������ⵥ����û��Ŀ�ĵ��ݣ����ж�ֻ�г��ⵥ
        	  if (afterbinessVoss.length == 0) {
        		  this.onlyOut = true;
        	  } else {
        		  for (BillbusinessVO abvo : afterbinessVoss) {
        			  //������ⵥ�������Ƿ�Ʊ�����ж�Ϊ�Ȼ���Ʊ
        			  if (SOBillType.Invoice.getCode().equals(abvo.getPk_billtype())) {
        				  this.outFirst = true;
        				  break;
        			  }
        		  }
        	  }
          }//end if
          //end-ncm-chengqiang5-���ҵ�������÷������Ƴ��ⵥ���������
      }//end for 
      if (this.onlyInv && this.onlyOut) {
        this.invOutParallel = true;
        this.onlyInv = false;
        this.onlyOut = false;
      }
      //begin-ncm-chengqiang5
      //�����Ʊ������Ȼ���Ʊ��������ֻ�з�Ʊ��ֻ�г��ⵥ������
      if ((this.onlyInv || this.onlyOut) && (this.invFirst || this.outFirst)) {
          this.onlyInv = false;
          this.onlyOut = false;
      }
      //end-ncm-chengqiang5
      // modify by zhangby5 �ϲ���Ŀ���� NCdp205454502 end
    }

  }

  private void initOutInvoiceType(MessagedriveVO mvo) {
    BillbusinessVO[] bvos =
        PFConfig.findBillbusinessVOs(AppContext.getInstance().getPkGroup(),
            mvo.getPk_sourcebusinesstype());
    for (BillbusinessVO bvo : bvos) {
      if (SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.invoiceTransType =
            bvo.getTranstype() == null ? bvo.getPk_billtype() : bvo
                .getTranstype();
      }
      else if (ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.outTransType =
            bvo.getTranstype() == null ? bvo.getPk_billtype() : bvo
                .getTranstype();
      }
    }
  }

}
