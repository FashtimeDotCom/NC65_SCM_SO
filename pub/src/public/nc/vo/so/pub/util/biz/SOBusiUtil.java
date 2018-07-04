package nc.vo.so.pub.util.biz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;

import nc.bs.pubapp.AppBsContext;

import nc.impl.pubapp.env.BSContext;

public class SOBusiUtil {

  // �Ƿ��з�Ʊ
  private boolean ifHasInv;

  // �Ƿ��г���
  private boolean ifHasOut;

  /**
   * ����ҵ������ID��ѯҵ�����������е�������
   * 
   * @param busiID -- ҵ������id
   * @return Set<��������>
   */
  public Set<String> queryAllBillType(String busiID) {
    Set<String> retSet = new HashSet<String>();
    String pk_group = BSContext.getInstance().getGroupID();
    BillbusinessVO[] vos = null;
    vos = PfServiceScmUtil.findBillbusinessVOs(busiID, pk_group);
    if (vos != null && vos.length > 0) {
      for (BillbusinessVO vo : vos) {
        retSet.add(vo.getPk_billtype());
      }
    }
    return retSet;
  }

  /**
   * ����ҵ������IDs��ѯҵ�����������е�������
   * 
   * @param busiIDs -- ҵ������ids
   * @return Map<ҵ������id, Set<��������>>
   */
  public Map<String, Set<String>> queryAllBillType(String[] busiIDs) {
    Map<String, Set<String>> retMap = new HashMap<String, Set<String>>();
    for (String busiID : busiIDs) {
      Set<String> typeSet = new HashSet<String>();
      String pk_group = BSContext.getInstance().getGroupID();
      BillbusinessVO[] vos = null;
      vos = PfServiceScmUtil.findBillbusinessVOs(busiID, pk_group);
      if (vos != null && vos.length > 0) {
        for (BillbusinessVO vo : vos) {
          typeSet.add(vo.getPk_billtype());
        }
      }
      retMap.put(busiID, typeSet);
    }
    return retMap;
  }

  /**
   * ����ҵ������ID��ѯ����ҵ����������
   * 
   * @param busiIDs -- ҵ������id����
   * @return <ҵ������id,SOBusiMDEnum>
   */
  public Map<SOBusiPara, SOBusiMDEnum> querySOBusiType(SOBusiPara[] paras) {
    if (VOChecker.isEmpty(paras)) {
      ExceptionUtils.unSupported();
    }
    Map<SOBusiPara, SOBusiMDEnum> map = new HashMap<SOBusiPara, SOBusiMDEnum>();
    for (SOBusiPara para : paras) {
      this.initBizType(para, map);
    }

    if (VOChecker.isEmpty(map)) {
      ExceptionUtils.unSupported();
    }

    return map;
  }

  /**
   * ����ҵ������ID��ѯ����ҵ����������
   * 
   * @param busiIDs -- ҵ������id����
   * @return <ҵ������id,SOBusiMDEnum>
   */
  public Map<String, SOBusiMDEnum> querySOBusiType(String[] bizs) {
    if (VOChecker.isEmpty(bizs)) {
      ExceptionUtils.unSupported();
    }
    Map<String, SOBusiMDEnum> map = new HashMap<String, SOBusiMDEnum>();
    for (String para : bizs) {
      this.initBizType(para, map);
    }

    if (VOChecker.isEmpty(map)) {
      ExceptionUtils.unSupported();
    }

    return map;
  }

  /**
   * ��ѯҵ�������У����۳��ⵥ�����۷�Ʊ�Ƿ������㣨Ӧ�ջ��߳ɱ���
   * 
   * @param bizs
   * @return <ҵ������,[0]���۳��ⵥ�Ƿ������� [1]���۷�Ʊ�Ƿ�������>
   */
  public Map<String, String[]> querySquareBusiBillType(String[] bizs) {
    // <ҵ������,[0]���۳��ⵥ�������� [1]���۷�Ʊ��������>
    Map<String, String[]> mbizbilltype = new HashMap<String, String[]>();
    for (String busiID : bizs) {
      String pk_group = AppBsContext.getInstance().getPkGroup();
      BillbusinessVO[] vos = null;
      vos = PfServiceScmUtil.findBillbusinessVOs(busiID, pk_group);
      if (vos != null && vos.length > 0) {
        String outTransType = null;
        String invoiceTransType = null;
        for (BillbusinessVO vo : vos) {
          String billType = vo.getPk_billtype();
          if (ICBillType.SaleOut.getCode().equals(billType)) {
            outTransType = vo.getTranstype();
          }
          if (SOBillType.Invoice.getCode().equals(billType)) {
            invoiceTransType = vo.getTranstype();
          }
        }
        mbizbilltype.put(busiID, new String[] {
          outTransType, invoiceTransType
        });
      }
    }
    return this.querySquareBusiBillType(mbizbilltype);
  }

  private String checkInvoiceIFSquareAction(String sourceTransType,
      String sourceBusiType) {
    MessagedriveVO[] mvos =
        this.queryMessagedrive(sourceTransType, SOBillType.Invoice.getCode(),
            sourceBusiType, SOBillAction.SaleInvoiceApprove.getCode());
    String flag = "N";
    if (VOChecker.isEmpty(mvos)) {
      return flag;
    }
    for (MessagedriveVO mvo : mvos) {
      String act = mvo.getActiontype();
      if (!PubAppTool.isNull(act)
          && (SOBillAction.SaleInvoiceSQUAREINCOME.getCode().equals(act) || SOBillAction.SaleInvoiceSQUARECOST
              .getCode().equals(act))) {
        flag = "Y";
        break;
      }
    }
    return flag;
  }

  private String checkOutIFSquareAction(String sourceTransType,
      String sourceBusiType) {
    MessagedriveVO[] mvos =
        this.queryMessagedrive(sourceTransType, ICBillType.SaleOut.getCode(),
            sourceBusiType, SOBillAction.SaleOutSIGN.getCode());
    String flag = "N";
    if (VOChecker.isEmpty(mvos)) {
      return flag;
    }
    for (MessagedriveVO mvo : mvos) {
      String act = mvo.getActiontype();
      if (!PubAppTool.isNull(act)
          && (SOBillAction.SaleOutAutoAR.getCode().equals(act)
              || SOBillAction.SaleOutAutoCost.getCode().equals(act)
              // 2013-5-27 cheney����ȷ�ϣ������������ֹ�����ҲҪӰ��ر�״̬��fengjb��yixl��tianft��
              || SOBillAction.SaleOutManualAR.getCode().equals(act) || SOBillAction.SaleOutManualCost
              .getCode().equals(act))) {
        flag = "Y";
        break;
      }
    }
    return flag;
  }

  /**
   * ����ҵ�����������۶����Ľ�������
   * 
   * @param biz
   * @return
   */
  private String getM30TransTypeCode(String biz) {
    String pk_group = AppContext.getInstance().getPkGroup();
    BillbusinessVO[] vos = PfServiceScmUtil.findBillbusinessVOs(biz, pk_group);
    BillbusinessVO m30billvo = null;
    for (BillbusinessVO vo : vos) {
      if (SOBillType.Order.getCode().equals(vo.getPk_billtype())) {
        m30billvo = vo;
        break;
      }
    }

    String m30transTypeCode = null;
    if (null == m30billvo) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006004_0", "04006004-0012")/*@res "ҵ��������û�����۶���"*/);
    }
    else {
      // �������ͱ���
      m30transTypeCode = m30billvo.getTranstype();
    }

    return m30transTypeCode;
  }

  /**
   * ҵ���������Ƿ��г�����߷�Ʊ
   * 
   * @param biz
   */
  private void initBill(String biz) {
    BillbusinessVO[] bvos =
        PfServiceScmUtil.findBillbusinessVOs(biz, AppBsContext.getInstance()
            .getPkGroup());
    for (BillbusinessVO bvo : bvos) {
      if (SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.ifHasInv = true;
      }
      else if (ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.ifHasOut = true;
      }
    }
  }

  private void initBizType(SOBusiPara para, Map<SOBusiPara, SOBusiMDEnum> map) {
    if (VOChecker.isEmpty(para) || PubAppTool.isNull(para.getBusitype())
        || PubAppTool.isNull(para.getM30transType())) {
      ExceptionUtils.unSupported();
    }
    String biz = para.getBusitype();
    String transTypeCode = para.getM30transType();

    boolean bout = false;
    boolean binv = false;
    // ��ʽ����
    UFBoolean[] flag = this.initPullBizType(biz);
    if (!VOChecker.isEmpty(flag)) {
      bout = flag[0].booleanValue();
      binv = flag[1].booleanValue();
    }
    // ��ʽ����
    else {
      flag = this.initPushBizType(biz, transTypeCode);
      if (!VOChecker.isEmpty(flag)) {
        // ExceptionUtils
        // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        // .getStrByID("4006004_0", "04006004-0013")/*@res
        // "ҵ�����������۶���û���������ε���"*/);
        bout = flag[0].booleanValue();
        binv = flag[1].booleanValue();
      }
    }

    this.processReturnResult(bout, binv, para, map);
  }

  private void initBizType(String biz, Map<String, SOBusiMDEnum> map) {
    if (VOChecker.isEmpty(biz)) {
      ExceptionUtils.unSupported();
    }

    boolean bout = false;
    boolean binv = false;
    // ��ʽ����
    UFBoolean[] flag = this.initPullBizType(biz);
    if (!VOChecker.isEmpty(flag)) {
      bout = flag[0].booleanValue();
      binv = flag[1].booleanValue();
    }
    // ��ʽ����
    else {
      String transTypeCode = this.getM30TransTypeCode(biz);
      flag = this.initPushBizType(biz, transTypeCode);
      if (VOChecker.isEmpty(flag)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006004_0", "04006004-0013")/*@res "ҵ�����������۶���û���������ε���"*/);
      }
      bout = flag[0].booleanValue();
      binv = flag[1].booleanValue();
    }

    this.processReturnResult(bout, binv, biz, map);
  }

  /**
   * UFBoolean[0] �������δ��ڳ����־
   * UFBoolean[1] �������δ��ڷ�Ʊ��־
   * 
   * @param biz
   * @return
   */
  private UFBoolean[] initPullBizType(String biz) {
    // ҵ���������Ƿ��г�����߷�Ʊ
    this.initBill(biz);

    // ��ѯ��ҵ�����������۶���������(��ʽ����)
    BillbusinessVO[] afterbinessVos =
        PfServiceScmUtil.queryBillDest(SOBillType.Order.getCode(), biz);

    if (VOChecker.isEmpty(afterbinessVos)) {
      return null;
    }

    UFBoolean[] flag = new UFBoolean[] {
      UFBoolean.FALSE, UFBoolean.FALSE
    };
    // ��ʽ�������۶�������
    if (!VOChecker.isEmpty(afterbinessVos)) {
      for (BillbusinessVO bvo : afterbinessVos) {
        String billDestType = bvo.getPk_billtype();
        // ���������Ƿ��������߳��ⵥ
        if (SOBillType.Delivery.getCode().equals(billDestType)
            || ICBillType.SaleOut.getCode().equals(billDestType)) {
          flag[0] = UFBoolean.TRUE;
          continue;
        }
        // �������������۷�Ʊ
        if (SOBillType.Invoice.getCode().equals(billDestType)) {
          flag[1] = UFBoolean.TRUE;
          continue;
        }
      }
    }
    return flag;
  }

  /**
   * UFBoolean[0] �������δ��ڳ����־
   * UFBoolean[1] �������δ��ڷ�Ʊ��־
   * 
   * @param biz
   * @return
   */
  private UFBoolean[] initPushBizType(String biz, String transTypeCode) {
    // ҵ���������Ƿ��г�����߷�Ʊ
    this.initBill(biz);

    MessagedriveVO[] aftermessVos =
        PfServiceScmUtil.queryAllMsgdrvVOs(transTypeCode, biz,
            SOBillAction.SaleOrderApprove.getCode());
    UFBoolean[] flag = new UFBoolean[] {
      UFBoolean.FALSE, UFBoolean.FALSE
    };
    if (VOChecker.isEmpty(aftermessVos)) {
      aftermessVos =
          PfServiceScmUtil.queryAllMsgdrvVOs(SOBillType.Order.getCode(), biz,
              SOBillAction.SaleOrderApprove.getCode());
      if (VOChecker.isEmpty(aftermessVos)) {
        return null;
      }
    }
    for (MessagedriveVO mvo : aftermessVos) {
      String billDestType =
          PfServiceScmUtil.getBillTypeByTransType(mvo.getPk_billtype());
      // ���������Ƿ��������߳��ⵥ
      if (SOBillType.Delivery.getCode().equals(billDestType)
          || ICBillType.SaleOut.getCode().equals(billDestType)) {
        flag[0] = UFBoolean.TRUE;
        continue;
      }
      // �������������۷�Ʊ
      if (SOBillType.Invoice.getCode().equals(billDestType)) {
        flag[1] = UFBoolean.TRUE;
        continue;
      }
    }
    return flag;
  }

  private void processReturnResult(boolean bout, boolean binv, SOBusiPara para,
      Map<SOBusiPara, SOBusiMDEnum> map) {
    if (bout && binv) {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL);
    }
    else if (bout && !binv && this.ifHasInv) {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST);
    }
    else if (!bout && binv) {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST);
    }
    else {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_NO_BUSI);
    }
  }

  private void processReturnResult(boolean bout, boolean binv, String biz,
      Map<String, SOBusiMDEnum> map) {
    if (bout && binv) {
      map.put(biz, SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL);
    }
    else if (bout && !binv && this.ifHasInv) {
      map.put(biz, SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST);
    }
    else if (!bout && binv) {
      map.put(biz, SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST);
    }
  }

  private MessagedriveVO[] queryMessagedrive(String sourceTransType,
      String sourceBillType, String sourceBusiType, String sourceAction) {
    MessagedriveVO[] mvos =
        PfServiceScmUtil.queryAllMsgdrvVOs(sourceTransType, sourceBusiType,
            sourceAction);

    // �������Ͳ鲻�����õ������Ͳ�
    if (VOChecker.isEmpty(mvos)) {
      mvos =
          PfServiceScmUtil.queryAllMsgdrvVOs(sourceBillType, sourceBusiType,
              sourceAction);
    }
    return mvos;
  }

  /**
   * ��ѯҵ�������У����۳��ⵥ�����۷�Ʊ�Ƿ������㣨Ӧ�ջ��߳ɱ���
   * 
   * @param mbizbilltype <ҵ������,[0]���۳��ⵥ�������� [1]���۷�Ʊ��������>
   *          <p>
   *          �����������Ϊ�գ��Զ��õ������ʹ���
   * @return <ҵ������,[0]���۳��ⵥ�Ƿ������� [1]���۷�Ʊ�Ƿ�������>
   */
  private Map<String, String[]> querySquareBusiBillType(
      Map<String, String[]> mbizbilltype) {
    if (VOChecker.isEmpty(mbizbilltype)) {
      ExceptionUtils.unSupported();
    }
    Map<String, String[]> mreturn = new HashMap<String, String[]>();
    for (Entry<String, String[]> entry : mbizbilltype.entrySet()) {
      String biz = entry.getKey();
      if (!mreturn.containsKey(biz)) {
        String outTransType = entry.getValue()[0];
        if (PubAppTool.isNull(outTransType)) {
          outTransType = ICBillType.SaleOut.getCode();
        }
        String invoiceTransType = entry.getValue()[1];
        if (PubAppTool.isNull(invoiceTransType)) {
          invoiceTransType = SOBillType.Invoice.getCode();
        }
        // ��ѯ���۳��ⵥ���㶯��
        String outflag = this.checkOutIFSquareAction(outTransType, biz);
        // ��ѯ���۷�Ʊ���㶯��
        String invoiceflag =
            this.checkInvoiceIFSquareAction(invoiceTransType, biz);
        String[] flags = new String[] {
          outflag, invoiceflag
        };
        mreturn.put(biz, flags);
      }
    }
    return mreturn;
  }

}
