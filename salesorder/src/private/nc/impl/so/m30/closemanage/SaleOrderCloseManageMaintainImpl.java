package nc.impl.so.m30.closemanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.bill.BillAuditState;
import nc.bs.so.m30.state.bill.BillCloseState;
import nc.bs.so.m30.state.bill.BillFreezeState;
import nc.bs.so.m30.state.bill.BillOpenState;
import nc.bs.so.m30.state.row.ArSettleCloseState;
import nc.bs.so.m30.state.row.ArSettleOpenState;
import nc.bs.so.m30.state.row.CostSettleCloseState;
import nc.bs.so.m30.state.row.CostSettleOpenState;
import nc.bs.so.m30.state.row.InvoiceCloseState;
import nc.bs.so.m30.state.row.InvoiceOpenState;
import nc.bs.so.m30.state.row.OutCloseState;
import nc.bs.so.m30.state.row.OutOpenState;
import nc.bs.so.m30.state.row.RowCloseState;
import nc.bs.so.m30.state.row.RowOpenState;
import nc.bs.so.m30.state.row.SendCloseState;
import nc.bs.so.m30.state.row.SendOpenState;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.itf.so.m30.ref.ic.m4453.ICm4453ServicesUtil;
import nc.itf.so.m30.ref.ic.m4c.ICm4CServicesUtil;
import nc.itf.so.m30.ref.so.m32.SOm32ServicesUtil;
import nc.itf.so.m30.ref.so.m33.SOm33ServicesUtil;
import nc.itf.so.m30.ref.so.m4331.SOm4331ServicesUtil;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.ic.m4c.m30.ICSaleOutNumInfoVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.ViewVOUtil;
import nc.vo.so.pub.util.biz.SOBusiUtil;
import nc.vo.so.pub.votools.SoVoTools;

public class SaleOrderCloseManageMaintainImpl implements
    ISaleOrderCloseManageMaintain {

  @Override
  public SaleOrderVO[] closeSaleOrder(SaleOrderVO[] bills)
      throws BusinessException {
    try {
      // �ֹ�������رգ���鶩�����γ��ⵥ�����۷�Ʊ��;���Ƿ��Ѿ�����
      String[] orderbids =
          AggVOUtil.getDistinctItemFieldArray(bills,
              SaleOrderBVO.CSALEORDERBID, String.class);
      String[] orderids =
          AggVOUtil.getDistinctItemFieldArray(bills, SaleOrderBVO.CSALEORDERID,
              String.class);
      this.checkOrderDownBillAllApprove(orderids, orderbids);

      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(bills);

      // ����״̬
      BillCloseState state = new BillCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addBillCloseBusiLog(bills);
      return this.setState(state, bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      InvoiceCloseState state = new InvoiceCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addInvoiceCloseBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderOut(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);
      OutCloseState state = new OutCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addOutCloseBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException {
    try {
      // �ֹ�������رգ���鶩�����γ��ⵥ�����۷�Ʊ��;���Ƿ��Ѿ�����
      String[] orderbids =
          SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
      String[] orderids =
          ViewVOUtil.getDistinctFieldArray(views, SaleOrderBVO.class,
              SaleOrderBVO.CSALEORDERID, String.class);
      this.checkOrderDownBillAllApprove(orderids, orderbids);

      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      RowCloseState state = new RowCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addBillRowClose(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      SendCloseState state = new SendCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addSendCloseBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException {
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(vos);
    // �ֹ�������رգ���鶩�����γ��ⵥ�����۷�Ʊ��;���Ƿ��Ѿ�����
    String[] orderbids =
        SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
    String[] orderids =
        ViewVOUtil.getDistinctFieldArray(views, SaleOrderBVO.class,
            SaleOrderBVO.CSALEORDERID, String.class);
    this.checkOrderDownBillAllApprove(orderids, orderbids);

    SaleOrderViewVO[] retViews = null;
    try {
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      // ǰ̨MultiBillTaskRunner���ã�����views��Ϊ����view
      if (!views[0].getBody().getBbcostsettleflag().booleanValue()) {
        CostSettleCloseState costState = new CostSettleCloseState();
        retViews = this.setState(costState, views);
      }
      if (!views[0].getBody().getBbarsettleflag().booleanValue()) {
        ArSettleCloseState arState = new ArSettleCloseState();
        if (null == retViews) {
          retViews = this.setState(arState, views);
        }
        else {
          retViews = this.setState(arState, retViews);
        }
      }

      // �ֹ�����ر������Ʊ���߳��ⵥ�������������Ӧ�Ľ���ر�
      retViews = this.processOutInvState(retViews);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    if (null == retViews) {
      return views;
    }
    // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
    // util.addSetCloseBusiLog(views);
    return retViews;
  }

  @Override
  public SaleOrderVO[] freezeSaleOrder(SaleOrderVO[] billVOs)
      throws BusinessException {
    try {
      BillFreezeState state = new BillFreezeState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addFreezeBusiLog(billVOs);
      return this.setState(state, billVOs);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SOParameterVO[] openSaleOrder(SaleOrderVO[] vos, boolean isAbandonATP)
      throws BusinessException {
    // // �Ƿ����ATP���
    // boolean isAbandonATP = false;
    // // ����paraVOs�н�����bills
    // SaleOrderVO[] bills = new SaleOrderVO[paraVOs.length];
    // for (int i = 0; i < paraVOs.length; i++) {
    // bills[i] = (SaleOrderVO) paraVOs[i].getVo();
    // Object o =
    // paraVOs[i].getBusinessCheckMap().get(
    // BusinessCheck.ATPCheck.getCheckCode());
    // if (o != null && !((Boolean) o).booleanValue()) {
    // isAbandonATP = true;
    // }
    // }
    if (isAbandonATP) {
      // ����ATP���
      SOATPprocess.abandonATPCheck();
    }
    // ���´�ԭ��
    this.updateCloseOpenReason(vos);
    // ���´�״̬
    try {
      BillOpenState state = new BillOpenState();
      SaleOrderVO[] newbills = this.setState(state, vos);
      // BillOpenState�򿪲�һ���ῪƱ�򿪣���������Ҫ��Ʊ��
      InvoiceOpenState invoiceOpen = new InvoiceOpenState();
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] newviews = convert.convert(newbills);
      List<SaleOrderViewVO> invoiceOpenlist = new ArrayList<SaleOrderViewVO>();
      for (SaleOrderViewVO viewvo : newviews) {
        if (invoiceOpen.isThisState(viewvo) || this.isLargessCanInvoice(viewvo)) {
          continue;
        }
        invoiceOpenlist.add(viewvo);
      }
      if (invoiceOpenlist.size() > 0) {
        invoiceOpen.setState(ListUtil.toArray(invoiceOpenlist));
      }
      // ��֯����vo
      SOParameterVO[] retVOs = new SOParameterVO[newbills.length];
      for (int i = 0; i < newbills.length; i++) {
        retVOs[i] = new SOParameterVO();
        retVOs[i].setVo(newbills[i]);
      }
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addBillOpenBusiLog(bills);
      return retVOs;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
  
  /**
   * ��Ʒ�Ƿ�Ʊ
   * 
   * @param view
   */
  private boolean isLargessCanInvoice(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean larflag = ValueUtils.getBoolean(body.getBlargessflag());
    String settleorgid = body.getCsettleorgid();
    boolean bSO20 =
        SOSysParaInitUtil.getSO20(settleorgid) == null ? false
            : SOSysParaInitUtil.getSO20(settleorgid).booleanValue();
    return larflag && !bSO20;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // �Ƿ�����ֹ���
      this.checkIfOpen(SOBillType.Invoice.getCode(), views);

      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      InvoiceOpenState state = new InvoiceOpenState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addInvoiceOpenBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SOParameterVO[] openSaleOrderOut(SaleOrderVO[] vos,
      boolean isAbandonATP) throws BusinessException {
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(vos);

    try {
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addOutOpenBusiLog(views);
      OutOpenState state = new OutOpenState();
      for (SaleOrderViewVO view : views) {
        if (!state.isManualOutOpen(view)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0414")/*@res "�ۿ۷��������ϣ���Ӧ�������ֹ��������"*/);
        }
      }
      SaleOrderViewVO[] newbills = this.setState(state, views);
      // ��֯����vo
      SOParameterVO[] retVOs = new SOParameterVO[newbills.length];
      for (int i = 0; i < newbills.length; i++) {
        retVOs[i] = new SOParameterVO();
        retVOs[i].setView(newbills[i]);
      }
      return retVOs;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException {
    try {
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      RowOpenState state = new RowOpenState();
      SaleOrderViewVO[] retviewvo = this.setState(state, views);
      InvoiceOpenState invoiceOpen = new InvoiceOpenState();
      List<SaleOrderViewVO> invoiceOpenlist = new ArrayList<SaleOrderViewVO>();
      for (SaleOrderViewVO viewvo : retviewvo) {
        if (invoiceOpen.isThisState(viewvo) || this.isLargessCanInvoice(viewvo)) {
          continue;
        }
        invoiceOpenlist.add(viewvo);
      }
      if (invoiceOpenlist.size() > 0) {
        invoiceOpen.setState(ListUtil.toArray(invoiceOpenlist));
      }
      return retviewvo;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      SendOpenState state = new SendOpenState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addSendOpenBusiLog(views);
      for (SaleOrderViewVO view : views) {
        if (!state.isManualSendOpen(view)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0415")/*@res "�ۿ۷��������ϣ���Ӧ�������ֹ���������"*/);
        }
      }
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException {
    SaleOrderViewVO[] retViews = null;
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(vos);
    try {
      // ���¹ر�/��ԭ��
      this.updateCloseOpenReason(views);

      // ǰ̨MultiBillTaskRunner���ã�����views��Ϊ����view
      if (views[0].getBody().getBbcostsettleflag().booleanValue()) {
        CostSettleOpenState costState = new CostSettleOpenState();
        retViews = this.setState(costState, views);
      }
      if (views[0].getBody().getBbarsettleflag().booleanValue()) {
        ArSettleOpenState arState = new ArSettleOpenState();
        if (null == retViews) {
          retViews = this.setState(arState, views);
        }
        else {
          retViews = this.setState(arState, retViews);
        }
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    if (null == retViews) {
      return views;
    }
    // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
    // util.addSetOpenBusiLog(views);
    return retViews;
  }

  @Override
  public SaleOrderViewVO[] querySaleOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException {
    // ��̨ƴ��sql
    String sql = this.createSqlByQueryScheme(queryScheme);

    DataAccessUtils utils = new DataAccessUtils();
    utils.setMaxRows(SOConstant.CLOSEMANAGEMAXROWS);
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return new SaleOrderViewVO[0];
    }
    String[] bids = set.toOneDimensionStringArray();
    ViewQuery<SaleOrderViewVO> query =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] views = query.query(bids);
    Set<String> idset = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      idset.add(view.getHead().getCsaleorderid());
    }

    // ��ѯ"��������������"
    Map<String, UFDouble> hs4331 = SOm4331ServicesUtil.queryAppNum(bids);

    // ��ѯ"��������������"��"����δǩ��������"
    Map<String, ICSaleOutNumInfoVO> hs4C =
        ICm4CServicesUtil.query4CNumInfoFor30CloseMng(bids);

    // ��ѯ"��Ʊ����������"
    Map<String, UFDouble> hs32 =
        SOm32ServicesUtil.getInvoiceApproveNum(
            idset.toArray(new String[idset.size()]), bids);

    // ��ѯ";��δ���������"
    Map<String, UFDouble> hs4453 =
        ICm4453ServicesUtil.queryUnApproveNumByFirst(bids);

    // ��ѯ"Ӧ�ս��㵥������" ,"�ɱ����㵥������"
    String[] biztypeids = this.getAllBiztypeids(views);
    Map<String, String[]> hs33 =
        SOm33ServicesUtil.querySquareBillFor30SqEnd(bids, biztypeids);

    // ����***������
    for (SaleOrderViewVO view : views) {
      Object bid = view.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
      SaleOrderHVO head = view.getHead();
      SaleOrderBVO body = view.getBody();
      // --��������������
      body.setNsendauditnum(hs4331.get(bid));
      // --"��������������"��"����δǩ��������"
      ICSaleOutNumInfoVO icVO = hs4C.get(bid);
      if (null != icVO) {
        body.setNoutauditnum(icVO.getNoutauditnum());
        body.setNoutnotauditnum(icVO.getNoutnotauditnum());
      }
      // --"��Ʊ����������"
      body.setNinvoiceauditnum(hs32.get(bid));
      // --";��δ���������"
      body.setNlossnotauditnum(hs4453.get(bid));
      // --"δ��ȷ��Ӧ��������":
      String[] squareBillType = hs33.get(head.getCbiztypeid());
      if (null == squareBillType || null == squareBillType[0]) {
        body.setNnotarnum(MathTool.sub(body.getNnum(), body.getNtotalarnum()));
      }
      // --1.���⴫Ӧ��,δ��ȷ��Ӧ�������� = �ۼƳ��������� - �ۼ�ȷ��Ӧ��������
      else if (ICBillType.SaleOut.getCode().equals(squareBillType[0])) {
        body.setNnotarnum(MathTool.sub(body.getNtotaloutnum(),
            body.getNtotalarnum()));
      }
      // --2.��Ʊ��Ӧ��,δ��ȷ��Ӧ�������� = �ۼƿ�Ʊ������ - �ۼ�ȷ��Ӧ��������
      else if (SOBillType.Invoice.getCode().equals(squareBillType[0])) {
        body.setNnotarnum(MathTool.sub(body.getNtotalinvoicenum(),
            body.getNtotalarnum()));
      }
      // --"δ���������������":
      if (null == squareBillType || null == squareBillType[1]) {
        body.setNnotcostnum(MathTool.sub(body.getNnum(),
            body.getNtotalcostnum()));
      }
      // --1.���⴫Ӧ��,δ��ȷ��Ӧ�������� = �ۼƳ��������� - �ۼƴ��������������
      else if (ICBillType.SaleOut.getCode().equals(squareBillType[1])) {
        body.setNnotcostnum(MathTool.sub(body.getNtotaloutnum(),
            body.getNtotalcostnum()));
      }
      // --2.��Ʊ��Ӧ��,δ��ȷ��Ӧ�������� = �ۼƿ�Ʊ������ - �ۼƴ��������������
      else if (SOBillType.Invoice.getCode().equals(squareBillType[1])) {
        body.setNnotcostnum(MathTool.sub(body.getNtotalinvoicenum(),
            body.getNtotalcostnum()));
      }
    }
    return views;
  }

  @Override
  public SaleOrderVO[] unFreezeSaleOrder(SaleOrderVO[] billVOs,
      Map<String, Boolean> businessCheckMap) throws BusinessException {
    try {
      this.examATPCheck(businessCheckMap);
      boolean icEnable = SysInitGroupQuery.isICEnabled();
      if (icEnable) {
        // ������ǰ
        new SaleOrderVOATPBeforeRule().process(billVOs);
      }

      BillAuditState state = new BillAuditState();
      SaleOrderVO[] rets = this.setState(state, billVOs);

      if (icEnable) {
        // ��������
        new SaleOrderVOATPAfterRule().process(billVOs);
      }
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addUnFreezeBusiLog(billVOs);
      return rets;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  private void checkIfOpen(String billType, SaleOrderViewVO[] views) {
    Set<String> bizSet = new HashSet<String>();
    Map<String, String> codeMap = new HashMap<String, String>();
    for (SaleOrderViewVO view : views) {
      String biz = view.getHead().getCbiztypeid();
      bizSet.add(biz);
      codeMap.put(biz, view.getHead().getVbillcode());
      if (this.isLargessCanInvoice(view)) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0543")/*@res "��Ʒ�������Ҳ���so20������Ʒ�����ϲ���Ʊ��������Ӧ�ֹ���"*/);
      }
    }
    if (bizSet.size() > 0) {
      String[] cbiztypeids = bizSet.toArray(new String[bizSet.size()]);
      Map<String, Set<String>> busiTypeMap =
          new SOBusiUtil().queryAllBillType(cbiztypeids);
      for (int i = 0; i < cbiztypeids.length; i++) {
        Set<String> typeSet = busiTypeMap.get(cbiztypeids[i]);
        if (!typeSet.contains(billType)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0416")/*@res "ҵ��������û�����÷��������⡢��Ʊ��������Ӧ���ֹ��ر�"*/);
        }
      }
    }
  }

  /**
   * �ֹ�������رգ���鶩�����γ��ⵥ�����۷�Ʊ��;���Ƿ��Ѿ�ȫ������
   * 
   * @param orderbids
   */
  private void checkOrderDownBillAllApprove(String[] orderids,
      String[] orderbids) {
    UFBoolean[] isAllApprove =
        SOm32ServicesUtil.isInvoiceAllApprove(orderids, orderbids);
    this.checkOrderDownBillAllApprove(orderbids, isAllApprove);
    // ���ģ�����ò��ж�
    if (SysInitGroupQuery.isICEnabled()) {
      isAllApprove = ICm4CServicesUtil.queryIsAllSigned(orderbids);
      this.checkOrderDownBillAllApprove(orderbids, isAllApprove);
      isAllApprove = ICm4453ServicesUtil.queryWastageSigned(orderbids);
      this.checkOrderDownBillAllApprove(orderbids, isAllApprove);
    }

  }

  private void checkOrderDownBillAllApprove(String[] orderbids,
      UFBoolean[] isAllApprove) {
    int iloop = orderbids.length;
    for (int i = 0; i < iloop; i++) {
      if (null == isAllApprove[i] || !isAllApprove[i].booleanValue()) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0417")/*@res "�ֹ�����رգ��������γ��ⵥ�����۷�Ʊ��;�𵥱����Ѿ�ȫ������"*/);
      }
    }
  }

  private String createSqlByQueryScheme(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String childTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.PK_ORG);
    QueryCondition qryCon_bbillendflag =
        qrySchemeProcessor.getQueryCondition("bbillendflag");
    Object[] o = null;
    if (qryCon_bbillendflag != null) {
      o = qryCon_bbillendflag.getValues();
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct(" + childTableAlias + ".csaleorderbid) ");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    sql.append(" and " + mainTableAlias + ".pk_group", BSContext.getInstance()
        .getGroupID());
    if (o != null && o.length > 0 && UFBoolean.TRUE.compareTo(o[0]) == 0) {
      sql.append(" and so_saleorder.fstatusflag",
          BillStatus.CLOSED.getIntValue());
    }
    else if (o != null && o.length > 0 && UFBoolean.FALSE.compareTo(o[0]) == 0) {
      sql.append(" and so_saleorder.fstatusflag",
          BillStatus.AUDIT.getIntValue());
    }
    else {
      sql.append(" and (so_saleorder.fstatusflag = "
          + BillStatus.AUDIT.getIntValue() + " or so_saleorder.fstatusflag ="
          + BillStatus.CLOSED.getIntValue() + ")");
    }
    return sql.toString();
  }

  private void examATPCheck(Map<String, Boolean> businessCheckMap) {
    if (null != businessCheckMap
        && null != businessCheckMap.get(BusinessCheck.ATPCheck.getCheckCode())
        && !businessCheckMap.get(BusinessCheck.ATPCheck.getCheckCode())
            .booleanValue()) {
      // ����ATP���
      SOATPprocess.abandonATPCheck();
    }
  }

  private String[] getAllBiztypeids(SaleOrderViewVO[] views) {
    Set<String> bizSet = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      bizSet.add(view.getHead().getCbiztypeid());
    }
    return bizSet.toArray(new String[bizSet.size()]);
  }

  /**
   * �ֹ�����ر������Ʊ���߳��ⵥ�������������Ӧ�Ľ���ر�
   * 
   * @param views
   * @return
   */
  private SaleOrderViewVO[] processOutInvState(SaleOrderViewVO[] views) {
    Set<String> sbiz = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      sbiz.add(view.getHead().getCbiztypeid());
    }
    Map<String, String[]> mreturn =
        new SOBusiUtil().querySquareBusiBillType(sbiz.toArray(new String[sbiz
            .size()]));
    List<SaleOrderViewVO> loutclose = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> linvoiceclose = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      String biz = view.getHead().getCbiztypeid();
      String[] flags = mreturn.get(biz);
      UFBoolean outflag =
          ValueUtils.getUFBoolean(view.getBody().getBboutendflag());
      if ("Y".equals(flags[0]) && !outflag.booleanValue()) {
        loutclose.add(view);
      }
      UFBoolean invflag =
          ValueUtils.getUFBoolean(view.getBody().getBbinvoicendflag());
      if ("Y".equals(flags[1]) && !invflag.booleanValue()) {
        linvoiceclose.add(view);
      }
    }

    // ��������ر�
    int size = loutclose.size();
    if (size > 0) {
      OutCloseState state = new OutCloseState();
      SaleOrderViewVO[] outviews = loutclose.toArray(new SaleOrderViewVO[size]);
      this.setState(state, outviews);
    }

    // ������Ʊ�ر�
    size = linvoiceclose.size();
    if (size > 0) {
      InvoiceCloseState state = new InvoiceCloseState();
      SaleOrderViewVO[] invoiceviews =
          linvoiceclose.toArray(new SaleOrderViewVO[size]);
      this.setState(state, invoiceviews);
    }

    return this.refreshView(views);
  }

  private SaleOrderViewVO[] refreshView(SaleOrderViewVO[] views) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
    SaleOrderViewVO[] rets =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    return rets;
  }

  /**
   * ͨ��SaleOrder״̬��������״̬
   * 
   * @param state
   * @param views SaleOrderViewVO
   * @param act ���ݶ���
   * @time 2010-6-24 ����10:58:57
   */
  private SaleOrderViewVO[] setState(IState<SaleOrderViewVO> state,
      SaleOrderViewVO[] views) {
    // ����У�����ts����Ϊǰ̨ѭ��ÿһ�е��ã�У��hts�ᱨ��������һ��Ӱ�쵽����״̬��
    int len = views.length;
    SaleOrderBVO[] bvos = new SaleOrderBVO[len];
    for (int i = 0; i < len; i++) {
      bvos[i] = views[i].getBody();
    }
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.checkTSWithDB(bvos);

    SaleOrderViewVO[] originViews = this.refreshView(views);
    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, originViews);

    /**
     * ������ͳһˢ��һ��VO,�ڷ���ǰ̨�������ֹ��رմ������õ�״̬����
     * ��״̬���н���رյĴ����õ��Ľӿڣ���ڲ�����id����û�з���ֵ��
     * ����VO���ܱ�ˢ��TS����ʷԭ��
     */
    return this.refreshView(originViews);
  }

  /**
   * ͨ��SaleOrder״̬��������״̬
   * 
   * @param state
   * @param views SaleOrderViewVO
   * @param act ���ݶ���
   * @time 2010-6-24 ����10:58:57
   */
  private SaleOrderVO[] setState(IState<SaleOrderVO> state, SaleOrderVO[] vos) {
    TimeLog.logStart();
    BillTransferTool<SaleOrderVO> transferTool =
        new BillTransferTool<SaleOrderVO>(vos);
    SaleOrderVO[] billVOs = transferTool.getOriginBills();
    TimeLog.info("��ȫǰ̨VO�����������ʱ���");/*-=notranslate=-*/

    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, billVOs);

    String[] bids =
        AggVOUtil.getDistinctItemFieldArray(vos, SaleOrderBVO.CSALEORDERBID,
            String.class);
    SaleOrderViewVO[] viewrets =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO> convert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    return convert.convert(viewrets);
  }

  private void updateCloseOpenReason(SaleOrderViewVO[] views) {
    String[] names = new String[] {
      SaleOrderBVO.VCLOSEREASON
    };

    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    bo.update(views, SaleOrderBVO.class, names);
  }

  private void updateCloseOpenReason(SaleOrderVO[] bills) {
    List<SaleOrderBVO> bodyList = new ArrayList<SaleOrderBVO>();
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        bodyList.add(body);
      }
    }
    String[] names = new String[] {
      SaleOrderBVO.VCLOSEREASON
    };
    SaleOrderBVO[] items = new SaleOrderBVO[bodyList.size()];
    items = bodyList.toArray(items);
    VOUpdate<SaleOrderBVO> bo = new VOUpdate<SaleOrderBVO>();
    bo.update(items, names);
  }

}
