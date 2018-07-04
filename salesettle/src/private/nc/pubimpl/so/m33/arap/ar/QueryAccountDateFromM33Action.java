package nc.pubimpl.so.m33.arap.ar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m32.SOSaleInvoiceServicesUtil;
import nc.pubitf.so.m33.arap.AccountDateType;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.util.StringUtil;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * ��ѯ������Ч��
 * 
 * @since 6.3
 * @version 2013-01-30 13:07:41
 * @author ��ӱ�
 */
public class QueryAccountDateFromM33Action {

  // <���۷�Ʊbid,���۷�Ʊ����>
  private Map<String, UFDate[]> m32BID_32dates;

  // <���۷�Ʊbid,���۳��ⵥ����>
  private Map<String, UFDate[]> m32BID_4Cdates;

  // <���۳��ⵥbid,���۷�Ʊ����>
  private Map<String, UFDate[]> m4CBID_32dates;

  // <���۳��ⵥbid,���۳��ⵥ����>
  private Map<String, UFDate[]> m4CBID_4Cdates;

  // <������ϸID,���۷�Ʊ�������>
  private Map<String, UFDate> mDID_32AppDate = new HashMap<String, UFDate>();

  // <������ϸID,���۷�Ʊ��������>
  private Map<String, UFDate> mDID_32BillDate = new HashMap<String, UFDate>();

  // <������ϸID,���۳����������>
  private Map<String, UFDate> mDID_4CAppDate = new HashMap<String, UFDate>();

  // <������ϸID,���۳��ⵥ��ҵ������>
  private Map<String, UFDate> mDID_4CBillDate = new HashMap<String, UFDate>();

  /**
   * ��ѯ�������漰�����۷�Ʊ������ϸ����
   */
  private SquareInvDetailVO[] sqinvdvos;

  /**
   * ��ѯ�������漰�����۷�Ʊ�����㵥
   */
  private SquareInvViewVO[] sqinvviews;

  /**
   * ��ѯ�������漰�����۳��������ϸ����
   */
  private SquareOutDetailVO[] sqoutdvos;

  /**
   * ��ѯ�������漰�����۳�������㵥
   */
  private SquareOutViewVO[] sqoutviews;

  /**
   * ��ѯ������Ч��
   * 
   * @param map
   * @param billType
   * @return Map
   */
  public Map<String, UFDate[]> queryAccountDate(
      Map<String, List<AccountDateType>> map, String billType) {
    Map<String, UFDate[]> mapret = this.queryAccountDateInner(map, billType);
    return mapret;
  }

  /**
   * ����<��������,<������ϸID,��Ӧ����>>
   * 
   * @param map
   * @return
   */
  private Map<Integer, Map<String, UFDate>> getAccountDate(
      Map<String, List<AccountDateType>> map) {
    // <��������,������ϸID����>
    MapList<Integer, String> maccountids = this.transAccountDate(map);
    // <������ϸID,���۳���ҵ������>
    Map<String, UFDate> mID_outdate = new HashMap<String, UFDate>();
    // <������ϸID,���۳���ǩ������>
    Map<String, UFDate> mID_outsigndate = new HashMap<String, UFDate>();
    // <������ϸID,���ۺ�ͬ��Ч����>
    Map<String, UFDate> mID_contractdate = new HashMap<String, UFDate>();
    // <������ϸID,���۷�Ʊ���� ����>
    Map<String, UFDate> mID_invioceapprovedate = new HashMap<String, UFDate>();
    // <������ϸID,���۷�Ʊ��������>
    Map<String, UFDate> mID_inviocebilldate = new HashMap<String, UFDate>();
    // <������ϸID,���۶�����������>
    Map<String, UFDate> mID_orderdate = new HashMap<String, UFDate>();
    for (Entry<Integer, List<String>> entry : maccountids.entrySet()) {
      if (AccountDateType.OUT_STORE_DATE.getCode() == entry.getKey().intValue()) {
        mID_outdate = this.getEntranceOutDate(entry.getValue());
      }
      else if (AccountDateType.OUTSTORE_SIGNATURE_DATE.getCode() == entry
          .getKey().intValue()) {
        mID_outsigndate = this.getEntranceOutSignDate(entry.getValue());
      }
      else if (AccountDateType.SALE_CONTRACT_EFFECTIVE_DATE.getCode() == entry
          .getKey().intValue()) {
        mID_contractdate =
            this.getEntranceContractEffectiveDate(entry.getValue());
      }
      else if (AccountDateType.SALE_INVOICE_APPROVE_DATE.getCode() == entry
          .getKey().intValue()) {
        mID_invioceapprovedate =
            this.getEntranceInvoiceApproveDate(entry.getValue());
      }
      else if (AccountDateType.SALE_MAKE_BILL_DATE.getCode() == entry.getKey()
          .intValue()) {
        mID_inviocebilldate = this.getEntranceInvoiceBillDate(entry.getValue());
      }
      else if (AccountDateType.SALE_ORDER_DATE.getCode() == entry.getKey()
          .intValue()) {
        mID_orderdate = this.getEntranceSaleOrderDate(entry.getValue());
      }
    }
    Map<Integer, Map<String, UFDate>> mapret =
        new HashMap<Integer, Map<String, UFDate>>();
    mapret.put(Integer.valueOf(AccountDateType.OUT_STORE_DATE.getCode()),
        mID_outdate);
    mapret.put(
        Integer.valueOf(AccountDateType.OUTSTORE_SIGNATURE_DATE.getCode()),
        mID_outsigndate);
    mapret
        .put(Integer.valueOf(AccountDateType.SALE_CONTRACT_EFFECTIVE_DATE
            .getCode()), mID_contractdate);
    mapret.put(
        Integer.valueOf(AccountDateType.SALE_INVOICE_APPROVE_DATE.getCode()),
        mID_invioceapprovedate);
    mapret.put(Integer.valueOf(AccountDateType.SALE_MAKE_BILL_DATE.getCode()),
        mID_inviocebilldate);
    mapret.put(Integer.valueOf(AccountDateType.SALE_ORDER_DATE.getCode()),
        mID_orderdate);
    return mapret;
  }

  private Map<String, UFDate> getApproveDateByUFDates(
      Map<String, UFDate[]> mID_dates) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    for (Entry<String, UFDate[]> entry : mID_dates.entrySet()) {
      mID_date.put(entry.getKey(), entry.getValue()[1]);
    }
    return mID_date;
  }

  private Map<String, UFDate> getBillDateByUFDates(
      Map<String, UFDate[]> mID_dates) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    for (Entry<String, UFDate[]> entry : mID_dates.entrySet()) {
      mID_date.put(entry.getKey(), entry.getValue()[0]);
    }
    return mID_date;
  }

  /**
   * ȡ���ۺ�ͬ��Ч����
   * 
   * @param lsqdids
   * @return
   */
  private Map<String, UFDate> getEntranceContractEffectiveDate(
      List<String> lsqdids) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    String[] orderbids = null;
    if (null != this.sqinvdvos) {
      SquareInvBVO[] bvos =
          SquareInvVOUtils.getInstance().getSquareInvBVO(this.sqinvviews);
      orderbids = SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CFIRSTBID);
    }
    else if (null != this.sqoutdvos) {
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(this.sqoutviews);
      orderbids = SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CFIRSTBID);
    }
    Map<String, UFDate> m30BID_date = new HashMap<String, UFDate>();
    try {
      m30BID_date = SOSaleOrderServicesUtil.getZ3BusiDateBy30Bids(orderbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (SOVOChecker.isEmpty(m30BID_date)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0047")/* @res
                                                        * "�����տ�Э������Ӧ������δȡ����ͬ���ڣ����������ô���" */);
    }

    if (null != this.sqinvdvos) {
      mID_date = this.getSaleOrderDateByInv(m30BID_date);
    }
    else if (null != this.sqoutdvos) {
      mID_date = this.getSaleOrderDateByOut(m30BID_date);
    }
    return mID_date;
  }

  /**
   * ���۷�Ʊ�������
   * 
   * @param lsqdids
   * @return
   */
  private Map<String, UFDate> getEntranceInvoiceApproveDate(List<String> lsqdids) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    String[] sqdids = lsqdids.toArray(new String[lsqdids.size()]);
    if (null != this.sqinvdvos) {
      mID_date = this.getInvoiceApproveDateByInv(sqdids);
    }
    else if (null != this.sqoutdvos) {
      mID_date = this.getInvoiceApproveDateByOut(sqdids);
    }
    return mID_date;
  }

  /**
   * ���ۿ�Ʊ����
   * 
   * @param lsqdids
   * @return
   */
  private Map<String, UFDate> getEntranceInvoiceBillDate(List<String> lsqdids) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    String[] sqdids = lsqdids.toArray(new String[lsqdids.size()]);
    if (null != this.sqinvdvos) {
      mID_date = this.getInvoiceBillDateByInv(sqdids);
    }
    else if (null != this.sqoutdvos) {
      mID_date = this.getInvoiceBillDateByOut(sqdids);
    }
    return mID_date;
  }

  /**
   * ȡ����ҵ������
   * 
   * @param lsqdids
   * @return
   */
  private Map<String, UFDate> getEntranceOutDate(List<String> lsqdids) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    String[] sqdids = lsqdids.toArray(new String[lsqdids.size()]);
    if (null != this.sqinvdvos) {
      mID_date = this.getOutBillDateByInv(sqdids);
    }
    else if (null != this.sqoutdvos) {
      mID_date = this.getOutBillDateByOut(sqdids);
    }
    return mID_date;
  }

  /**
   * ȡ����ǩ������
   * 
   * @param lsqdids
   * @return
   */
  private Map<String, UFDate> getEntranceOutSignDate(List<String> lsqdids) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    String[] sqdids = lsqdids.toArray(new String[lsqdids.size()]);
    if (null != this.sqinvdvos) {
      mID_date = this.getOutAppDateByInv(sqdids);
    }
    else if (null != this.sqoutdvos) {
      mID_date = this.getOutAppDateByOut(sqdids);
    }
    return mID_date;
  }

  /**
   * ���۶�������
   * 
   * @param lsqdids
   * @return
   */
  private Map<String, UFDate> getEntranceSaleOrderDate(List<String> lsqdids) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    String[] orderbids = null;
    if (null != this.sqinvdvos) {
      SquareInvBVO[] bvos =
          SquareInvVOUtils.getInstance().getSquareInvBVO(this.sqinvviews);
      orderbids = SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CFIRSTBID);
    }
    else if (null != this.sqoutdvos) {
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(this.sqoutviews);
      orderbids = SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CFIRSTBID);
    }
    Map<String, UFDate> m30BID_date = new HashMap<String, UFDate>();
    try {
      m30BID_date = SOSaleOrderServicesUtil.get30BusiDateBy30Bids(orderbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (SOVOChecker.isEmpty(m30BID_date)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0048")/* @res
                                                        * "�����տ�Э������Ӧ������δȡ�����۶������ڣ����������ô���" */);
    }

    if (null != this.sqinvdvos) {
      mID_date = this.getSaleOrderDateByInv(m30BID_date);
    }
    else if (null != this.sqoutdvos) {
      mID_date = this.getSaleOrderDateByOut(m30BID_date);
    }
    return mID_date;
  }

  private Map<String, UFDate> getInvAppDateBy32Bids(String[] invbids) {
    if (null == this.m32BID_32dates) {
      try {
        Map<String, UFDate[]> mID_dates =
            SOSaleInvoiceServicesUtil.getBusiDateBy32Bids(invbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0049")/* @res
                                                                           * "�����տ�Э������Ӧ������δȡ�����۷�Ʊ������ڣ����������ô���" */);
        }
        this.m32BID_32dates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getApproveDateByUFDates(this.m32BID_32dates);
  }

  private Map<String, UFDate> getInvAppDateBy4CBids(String[] outbids) {
    if (null == this.m4CBID_32dates) {
      try {
        Map<String, UFDate[]> mID_dates =
            SOSaleInvoiceServicesUtil.getBusiDateBy4CBids(outbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          // ExceptionUtils
          // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          // .getNCLangRes().getStrByID("4006010_0", "04006010-0049")/*@res
          // "��������δȡ�����۷�Ʊ������ڣ����������ô���"*/);
          mID_dates = this.getDefaultDate(outbids);
        }
        this.m4CBID_32dates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getApproveDateByUFDates(this.m4CBID_32dates);
  }

  private Map<String, UFDate> getInvBillDateBy32Bids(String[] invbids) {
    if (null == this.m32BID_32dates) {
      try {
        Map<String, UFDate[]> mID_dates =
            SOSaleInvoiceServicesUtil.getBusiDateBy32Bids(invbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0050")/* @res
                                                                           * "�����տ�Э������Ӧ������δȡ�����۷�Ʊ�������ڣ����������ô���" */);
        }
        this.m32BID_32dates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getBillDateByUFDates(this.m32BID_32dates);
  }

  private Map<String, UFDate> getInvBillDateBy4CBids(String[] outbids) {
    if (null == this.m4CBID_32dates) {
      try {
        Map<String, UFDate[]> mID_dates =
            SOSaleInvoiceServicesUtil.getBusiDateBy4CBids(outbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          // ExceptionUtils
          // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          // .getNCLangRes().getStrByID("4006010_0", "04006010-0050")/*@res
          // "��������δȡ�����۷�Ʊ�������ڣ����������ô���"*/);
          mID_dates = this.getDefaultDate(outbids);
        }
        this.m4CBID_32dates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getBillDateByUFDates(this.m4CBID_32dates);
  }

  /**
   * ���۷�Ʊ��Ӧ��ȡ��Ʊ�������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getInvoiceApproveDateByInv(String[] sqdids) {
    if (this.mDID_32AppDate.size() == 0) {
      this.initInvoiceDateByInvSQ(sqdids);
    }
    return this.mDID_32AppDate;
  }

  /**
   * ���۳��⴫Ӧ��ȡ��Ʊ�������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getInvoiceApproveDateByOut(String[] sqdids) {
    if (this.mDID_32AppDate.size() == 0) {
      this.initInvoiceDateByOutSQ(sqdids);
    }
    return this.mDID_32AppDate;
  }

  /**
   * ���۷�Ʊ��Ӧ��ȡ��Ʊ��������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getInvoiceBillDateByInv(String[] sqdids) {
    if (this.mDID_32BillDate.size() == 0) {
      this.initInvoiceDateByInvSQ(sqdids);
    }
    return this.mDID_32BillDate;
  }

  /**
   * ���۳��⴫Ӧ��ȡ��Ʊ��������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getInvoiceBillDateByOut(String[] sqdids) {
    if (this.mDID_32BillDate.size() == 0) {
      this.initInvoiceDateByOutSQ(sqdids);
    }
    return this.mDID_32BillDate;
  }

  private Map<String, UFDate> getOutAppDateBy32Bids(String[] invbids) {
    if (null == this.m32BID_4Cdates) {
      try {
        Map<String, UFDate[]> mID_dates =
            ICM4CServiceUtil.queryBizSignDateByInvoiceBids(invbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          // ExceptionUtils
          // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          // .getNCLangRes().getStrByID("4006010_0", "04006010-0051")/*@res
          // "��������δȡ�����۳��ⵥǩ�����ڣ����������ô���"*/);
          mID_dates = this.getDefaultDate(invbids);
        }
        this.m32BID_4Cdates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getApproveDateByUFDates(this.m32BID_4Cdates);
  }

  /**
   * ȡ������Ӧ����ʱ���Ĭ������
   * 
   * @param bids
   * @return
   */
  private Map<String, UFDate[]> getDefaultDate(String[] bids) {
    Map<String, UFDate[]> mID_dates = new HashMap<String, UFDate[]>();
    UFDate busidate = AppContext.getInstance().getBusiDate();
    UFDate[] defdates = new UFDate[2];
    defdates[0] = busidate;
    defdates[1] = busidate;
    for (String bid : bids) {
      mID_dates.put(bid, defdates);
    }
    return mID_dates;
  }

  private Map<String, UFDate> getOutAppDateBy4CBids(String[] outbids) {
    if (null == this.m4CBID_4Cdates) {
      try {
        Map<String, UFDate[]> mID_dates =
            ICM4CServiceUtil.queryBizSignDateByBids(outbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0051")/* @res
                                                                           * "�����տ�Э������Ӧ������δȡ�����۳��ⵥǩ�����ڣ����������ô���" */);
        }
        this.m4CBID_4Cdates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getApproveDateByUFDates(this.m4CBID_4Cdates);
  }

  /**
   * ���۷�Ʊ��Ӧ��ȡ����ǩ������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getOutAppDateByInv(String[] sqdids) {
    if (this.mDID_4CAppDate.size() == 0) {
      this.initOutDateByInvSQ(sqdids);
    }
    return this.mDID_4CAppDate;
  }

  /**
   * ���۳��⴫Ӧ��ȡ����ǩ������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getOutAppDateByOut(String[] sqdids) {
    if (this.mDID_4CAppDate.size() == 0) {
      this.initOutDateByOutSQ(sqdids);
    }
    return this.mDID_4CAppDate;
  }

  private Map<String, UFDate> getOutBillDateBy32Bids(String[] invbids) {
    if (null == this.m32BID_4Cdates) {

      try {
        Map<String, UFDate[]> mID_dates =
            ICM4CServiceUtil.queryBizSignDateByInvoiceBids(invbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          // ExceptionUtils
          // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          // .getNCLangRes().getStrByID("4006010_0", "04006010-0052")/*@res
          // "��������δȡ�����۳��ⵥҵ�����ڣ����������ô���"*/);
          mID_dates = this.getDefaultDate(invbids);
        }
        this.m32BID_4Cdates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getBillDateByUFDates(this.m32BID_4Cdates);
  }

  private Map<String, UFDate> getOutBillDateBy4CBids(String[] outbids) {
    if (null == this.m4CBID_4Cdates) {
      try {
        Map<String, UFDate[]> mID_dates =
            ICM4CServiceUtil.queryBizSignDateByBids(outbids);
        if (SOVOChecker.isEmpty(mID_dates)) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0052")/* @res
                                                                           * "�����տ�Э������Ӧ������δȡ�����۳��ⵥҵ�����ڣ����������ô���" */);
        }
        this.m4CBID_4Cdates = mID_dates;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.getBillDateByUFDates(this.m4CBID_4Cdates);
  }

  /**
   * ���۷�Ʊ��Ӧ��ȡ���ⵥ������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getOutBillDateByInv(String[] sqdids) {
    if (this.mDID_4CBillDate.size() == 0) {
      this.initOutDateByInvSQ(sqdids);
    }
    return this.mDID_4CBillDate;
  }

  /**
   * ���۳��⴫Ӧ��ȡ���ⵥ������
   * 
   * @param sqdids
   * @return
   */
  private Map<String, UFDate> getOutBillDateByOut(String[] sqdids) {
    if (this.mDID_4CBillDate.size() == 0) {
      this.initOutDateByOutSQ(sqdids);
    }
    return this.mDID_4CBillDate;
  }

  private Map<String, UFDate> getSaleOrderDateByInv(
      Map<String, UFDate> m30BID_date) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    Map<String, UFDate> msqBID_date = new HashMap<String, UFDate>();
    for (SquareInvViewVO view : this.sqinvviews) {
      SquareInvBVO bvo = view.getItem();
      String orderbid = bvo.getCfirstbid();
      msqBID_date.put(bvo.getCsalesquarebid(), m30BID_date.get(orderbid));
    }
    for (SquareInvDetailVO dvo : this.sqinvdvos) {
      String bid = dvo.getCsalesquarebid();
      mID_date.put(dvo.getCsalesquaredid(), msqBID_date.get(bid));
    }
    return mID_date;
  }

  private Map<String, UFDate> getSaleOrderDateByOut(
      Map<String, UFDate> m30BID_date) {
    Map<String, UFDate> mID_date = new HashMap<String, UFDate>();
    Map<String, UFDate> msqBID_date = new HashMap<String, UFDate>();
    for (SquareOutViewVO view : this.sqoutviews) {
      SquareOutBVO bvo = view.getItem();
      String orderbid = bvo.getCfirstbid();
      msqBID_date.put(bvo.getCsalesquarebid(), m30BID_date.get(orderbid));
    }
    for (SquareOutDetailVO dvo : this.sqoutdvos) {
      String bid = dvo.getCsalesquarebid();
      mID_date.put(dvo.getCsalesquaredid(), msqBID_date.get(bid));
    }
    return mID_date;
  }

  /**
   * ��ʼ�����۷�Ʊ����ҵ��������Ϣ
   * 
   * @param map
   */
  private void initBySquareInvoice(Map<String, List<AccountDateType>> map) {
    int len = map.size();
    String[] sqdids = map.keySet().toArray(new String[len]);
    QuerySquare32VOBP qry = new QuerySquare32VOBP();
    SquareInvDetailVO[] dvos = qry.querySquareInvDetailVOByPK(sqdids);
    this.sqinvdvos = dvos;
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareInvDetailVO.CSALESQUAREBID);
    String[] fields =
        new String[] {
          SquareInvBVO.CSALESQUAREBID, SquareInvBVO.CFIRSTBID,
          SquareInvBVO.CSQUAREBILLBID, SquareInvBVO.CSRCBID,
          SquareInvBVO.VSRCTYPE, SquareInvHVO.DBILLDATE,
          SquareInvHVO.DBILLAPPROVEDATE
        };
    EfficientViewQuery<SquareInvViewVO> viewqry =
        new EfficientViewQuery<SquareInvViewVO>(SquareInvViewVO.class, fields);
    SquareInvViewVO[] views = viewqry.query(sqbids);
    this.sqinvviews = views;
  }

  /**
   * ��ʼ�����۳��ⵥ����ҵ��������Ϣ
   * 
   * @param map
   */
  private void initBySquareOut(Map<String, List<AccountDateType>> map) {
    int len = map.size();
    String[] sqdids = map.keySet().toArray(new String[len]);
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    SquareOutDetailVO[] dvos = qry.querySquareOutDetailVOByPK(sqdids);
    this.sqoutdvos = dvos;
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    String[] fields =
        new String[] {
          SquareOutBVO.CSALESQUAREBID, SquareOutBVO.CFIRSTBID,
          SquareOutBVO.CSRCBID, SquareOutBVO.VSRCTYPE,
          SquareOutHVO.DBILLSIGNDATE, SquareOutBVO.DBIZDATE,
          SquareOutBVO.CSQUAREBILLBID
        };
    EfficientViewQuery<SquareOutViewVO> viewqry =
        new EfficientViewQuery<SquareOutViewVO>(SquareOutViewVO.class, fields);
    SquareOutViewVO[] views = viewqry.query(sqbids);
    this.sqoutviews = views;
  }

  /**
   * ��ʼ��ҵ��������Ϣ
   * 
   * @param map
   */
  private void initData(Map<String, List<AccountDateType>> map, String billType) {
    if (SOBillType.Invoice.getCode().equals(billType)) {
      this.initBySquareInvoice(map);
    }
    else if (ICBillType.SaleOut.getCode().equals(billType)) {
      this.initBySquareOut(map);
    }
    else if (ICBillType.WastageBill.getCode().equals(billType)) {
      this.initBySquareWas(map);
    }
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0053")/* @res
                                                        * "�����տ�Э������Ӧ������ȡ���ӿڱ�������Դ�� ���۳��ⵥ��;�𵥻������۷�Ʊ��Ӧ�յ�" */);
    }
  }

  // ;�𵥴�Ӧ��ת��Ϊ�����γ��ⵥ������
  private void initBySquareWas(Map<String, List<AccountDateType>> map) {

    // ��ѯ;�����γ��ⵥ��Ӧ�յĽ��㵥��ϸid<;����㵥ID,������㵥ID>
    String[] wasdetids = new String[map.size()];
    map.keySet().toArray(wasdetids);
    Map<String, SquareWasViewVO> mapout =
        this.querySquareWasViewByWasDID(wasdetids);

    int idetlen = wasdetids.length;
    SquareOutDetailVO[] dvos = new SquareOutDetailVO[idetlen];
    Set<String> setoutbid = new HashSet<String>();
    for (int i = 0; i < idetlen; i++) {
      dvos[i] = new SquareOutDetailVO();
      dvos[i].setCsalesquaredid(wasdetids[i]);
      SquareWasViewVO wasview = mapout.get(wasdetids[i]);
      dvos[i].setCsalesquarebid(wasview.getItem().getCsrcbid());
      dvos[i].setCsalesquareid(wasview.getItem().getCsrcid());
      dvos[i].setCsquarebillbid(wasview.getItem().getCsrcbid());
      dvos[i].setCsquarebillid(wasview.getItem().getCsrcid());
      setoutbid.add(wasview.getItem().getCsrcbid());
    }
    this.sqoutdvos = dvos;

    String[] outbids = new String[setoutbid.size()];
    setoutbid.toArray(outbids);
    QuerySquare4CVOBP qryout = new QuerySquare4CVOBP();
    SquareOutViewVO[] outview = qryout.querySquareOutViewVOBy4CBID(outbids);

    String[] sqoutbids =
        SoVoTools.getVOsOnlyValues(outview, SquareOutBVO.CSALESQUAREBID);
    String[] fields =
        new String[] {
          SquareOutBVO.CSALESQUAREBID, SquareOutBVO.CFIRSTBID,
          SquareOutBVO.CSRCBID, SquareOutBVO.VSRCTYPE,
          SquareOutHVO.DBILLSIGNDATE, SquareOutBVO.DBIZDATE,
          SquareOutBVO.CSQUAREBILLBID
        };
    EfficientViewQuery<SquareOutViewVO> viewqry =
        new EfficientViewQuery<SquareOutViewVO>(SquareOutViewVO.class, fields);
    SquareOutViewVO[] views = viewqry.query(sqoutbids);
    this.sqoutviews = views;
  }

  /**
   * ͨ�����۷�Ʊ�����㵥��ʼ����Ʊ����
   * 
   * @param sqdids
   */
  private void initInvoiceDateByInvSQ(String[] sqdids) {
    Map<String, UFDate> mBID_billdate = new HashMap<String, UFDate>();
    Map<String, UFDate> mBID_appdate = new HashMap<String, UFDate>();
    for (SquareInvViewVO view : this.sqinvviews) {
      SquareInvHVO hvo = view.getHead();
      SquareInvBVO bvo = view.getItem();
      mBID_billdate.put(bvo.getCsalesquarebid(), bvo.getDbilldate());
      mBID_appdate.put(bvo.getCsalesquarebid(), hvo.getDbillapprovedate());
    }
    for (SquareInvDetailVO dvo : this.sqinvdvos) {
      String bid = dvo.getCsalesquarebid();
      this.mDID_32BillDate.put(dvo.getCsalesquaredid(), mBID_billdate.get(bid));
      this.mDID_32AppDate.put(dvo.getCsalesquaredid(), mBID_appdate.get(bid));
    }
  }

  /**
   * ͨ�����۳�������㵥��ʼ����Ʊ����
   * 
   * @param sqdids
   * @return
   */
  private void initInvoiceDateByOutSQ(String[] sqdids) {
    String srctype = this.sqoutviews[0].getItem().getVsrctype();
    SquareOutBVO[] bvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(this.sqoutviews);
    Map<String, SquareOutViewVO> moutsbidview =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : this.sqoutviews) {
      moutsbidview.put(view.getItem().getCsquarebillbid(), view);
    }
    // ��Ʊ���
    if (SOBillType.Invoice.getCode().equals(srctype)) {
      String[] invbids = SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CSRCBID);
      Map<String, UFDate> m32BID_appdate = this.getInvAppDateBy32Bids(invbids);
      Map<String, UFDate> m32BID_billdate =
          this.getInvBillDateBy32Bids(invbids);
      for (SquareOutDetailVO dvo : this.sqoutdvos) {
        String bid = dvo.getCsquarebillbid();
        SquareOutViewVO view = moutsbidview.get(bid);
        UFDate date = m32BID_appdate.get(view.getItem().getCsrcbid());
        this.mDID_32AppDate.put(dvo.getCsalesquaredid(), date);
        date = m32BID_billdate.get(view.getItem().getCsrcbid());
        this.mDID_32BillDate.put(dvo.getCsalesquaredid(), date);
      }
    }
    // �Ȼ���Ʊ
    else if (SOBillType.Order.getCode().equals(srctype)
        || SOBillType.Delivery.getCode().equals(srctype)
        || ICBillType.SaleOut.getCode().equals(srctype)) {
      String[] outbids =
          SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CSQUAREBILLBID);
      Map<String, UFDate> m4CBID_appdate = this.getInvAppDateBy4CBids(outbids);
      Map<String, UFDate> m4CBID_billdate =
          this.getInvBillDateBy4CBids(outbids);
      for (SquareOutDetailVO dvo : this.sqoutdvos) {
        String bid = dvo.getCsquarebillbid();
        SquareOutViewVO view = moutsbidview.get(bid);
        UFDate date = m4CBID_appdate.get(view.getItem().getCsquarebillbid());
        this.mDID_32AppDate.put(dvo.getCsalesquaredid(), date);
        date = m4CBID_billdate.get(view.getItem().getCsquarebillbid());
        this.mDID_32BillDate.put(dvo.getCsalesquaredid(), date);
      }
    }
    // ����
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0054")/* @res
                                                        * "���۳��⴫Ӧ������ȡ��Ʊ������ڣ����������ô���" */);
    }
  }

  /**
   * ͨ�����۷�Ʊ�����㵥��ʼ����������
   * 
   * @param sqdids
   * @return
   */
  private void initOutDateByInvSQ(String[] sqdids) {
    List<String> srctypeList = new ArrayList<String>();
    for(SquareInvViewVO sqinvview : sqinvviews){
    	if(!StringUtil.isEmptyTrimSpace(sqinvview.getItem().getVsrctype())){
    		srctypeList.add(sqinvview.getItem().getVsrctype());
    	}
    }
    String srctype = null;
    if(srctypeList != null && srctypeList.size() > 0){
    	srctype = srctypeList.get(0);
    }
    SquareInvBVO[] bvos =
        SquareInvVOUtils.getInstance().getSquareInvBVO(this.sqinvviews);
    Map<String, SquareInvViewVO> mInvsbidview =
        new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO view : this.sqinvviews) {
      mInvsbidview.put(view.getItem().getCsalesquarebid(), view);
    }
    // ��Ʊ���
    if (SOBillType.Order.getCode().equals(srctype)) {
      String[] invbids =
          SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CSQUAREBILLBID);
      Map<String, UFDate> m4cBID_appdate = this.getOutAppDateBy32Bids(invbids);
      Map<String, UFDate> m4cBID_billdate =
          this.getOutBillDateBy32Bids(invbids);
      for (SquareInvDetailVO dvo : this.sqinvdvos) {
        String bid = dvo.getCsalesquarebid();
        SquareInvViewVO view = mInvsbidview.get(bid);
        UFDate date = m4cBID_appdate.get(view.getItem().getCsquarebillbid());
        this.mDID_4CAppDate.put(dvo.getCsalesquaredid(), date);
        date = m4cBID_billdate.get(view.getItem().getCsquarebillbid());
        this.mDID_4CBillDate.put(dvo.getCsalesquaredid(), date);
      }
    }
    // �Ȼ���Ʊ
    else if (ICBillType.SaleOut.getCode().equals(srctype)) {
      String[] outbids = SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CSRCBID);
      Map<String, UFDate> m4CBID_appdate = this.getOutAppDateBy4CBids(outbids);
      Map<String, UFDate> m4CBID_billdate =
          this.getOutBillDateBy4CBids(outbids);
      for (SquareInvDetailVO dvo : this.sqinvdvos) {
        String bid = dvo.getCsalesquarebid();
        SquareInvViewVO view = mInvsbidview.get(bid);
        UFDate date = m4CBID_appdate.get(view.getItem().getCsrcbid());
        this.mDID_4CAppDate.put(dvo.getCsalesquaredid(), date);
        date = m4CBID_billdate.get(view.getItem().getCsrcbid());
        this.mDID_4CBillDate.put(dvo.getCsalesquaredid(), date);
      }
    }
    // ����
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0054")/* @res
                                                        * "���۳��⴫Ӧ������ȡ��Ʊ������ڣ����������ô���" */);
    }
  }

  /**
   * ͨ�����۳�������㵥��ʼ������ҵ������
   * 
   * @param sqdids
   * @return
   */
  private void initOutDateByOutSQ(String[] sqdids) {
    Map<String, UFDate> mBID_billdate = new HashMap<String, UFDate>();
    Map<String, UFDate> mBID_appdate = new HashMap<String, UFDate>();
    for (SquareOutViewVO view : this.sqoutviews) {
      SquareOutHVO hvo = view.getHead();
      SquareOutBVO bvo = view.getItem();
      mBID_billdate.put(bvo.getCsalesquarebid(), bvo.getDbizdate());
      mBID_appdate.put(bvo.getCsalesquarebid(), hvo.getDbillsigndate());
    }
    for (SquareOutDetailVO dvo : this.sqoutdvos) {
      String bid = dvo.getCsalesquarebid();
      this.mDID_4CBillDate.put(dvo.getCsalesquaredid(), mBID_billdate.get(bid));
      this.mDID_4CAppDate.put(dvo.getCsalesquaredid(), mBID_appdate.get(bid));
    }
  }

  private Map<String, UFDate[]> queryAccountDateInner(
      Map<String, List<AccountDateType>> map, String billType) {
    if (map == null || map.size() == 0 || billType == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0046")/* @res
                                                        * "�տ�Э��Ӧ������ȡ���ӿ���ڲ���Ϊ�գ���Ӧ��Ӧ�����ӿڵ��ò�����" */);
    }
    // ��ʼ������
    this.initData(map, billType);
    // <��������,<������ϸID,��Ӧ����>>
    Map<Integer, Map<String, UFDate>> mapret = this.getAccountDate(map);
    // ���շ���ֵ
    Map<String, UFDate[]> ret = new HashMap<String, UFDate[]>();
    for (Entry<String, List<AccountDateType>> entry : map.entrySet()) {
      String sqdid = entry.getKey();
      List<AccountDateType> laccountdate = entry.getValue();
      UFDate[] date = new UFDate[laccountdate.size()];
      int i = 0;
      for (AccountDateType dt : laccountdate) {
        // ���������Ͳ���ϵͳԤ�õ��Ǽ������ͣ�arapģ����������������Ϊ�ա�
        if (dt != null) {
          date[i] = mapret.get(Integer.valueOf(dt.getCode())).get(sqdid);
        }
        i++;
      }
      ret.put(sqdid, date);
    }
    return ret;
  }

  /**
   * ��ѯ;�����γ��ⵥ��Ӧ�յĽ��㵥��ϸid<;����㵥DID,������㵥BID>
   * 
   * @param sqwasdids
   * @return
   */
  private Map<String, SquareWasViewVO> querySquareWasViewByWasDID(
      String[] sqwasdids) {
    // ��ѯ;����Ϣ
    QuerySquare4453VOBP qry = new QuerySquare4453VOBP();
    SquareWasDetailVO[] dvos = qry.querySquareWasDetailVOByPK(sqwasdids);
    // <wasbid,wasdid>
    Map<String, String> mwasbiddid = new HashMap<String, String>();
    for (SquareWasDetailVO dvo : dvos) {
      mwasbiddid.put(dvo.getCsalesquarebid(), dvo.getCsalesquaredid());
    }
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareWasDetailVO.CSALESQUAREBID);
    String[] fields = new String[] {
      SquareWasBVO.CSALESQUAREBID, SquareWasBVO.CSRCBID, SquareWasBVO.CSRCID
    };
    EfficientViewQuery<SquareWasViewVO> viewqry =
        new EfficientViewQuery<SquareWasViewVO>(SquareWasViewVO.class, fields);
    SquareWasViewVO[] views = viewqry.query(sqbids);
    // <;����ϸ��did,���ⵥ���������bid>
    Map<String, SquareWasViewVO> mapout =
        new HashMap<String, SquareWasViewVO>();
    for (SquareWasViewVO view : views) {
      SquareWasBVO bvo = view.getItem();
      String wasdid = mwasbiddid.get(bvo.getCsalesquarebid());
      mapout.put(wasdid, view);
    }
    return mapout;
  }

  /**
   * ������������ࣺ<Integer,String[]> : <��������,������ϸID����>
   * 
   * @param map
   * @return
   */
  private MapList<Integer, String> transAccountDate(
      Map<String, List<AccountDateType>> map) {
    // ������������ࣺ<Integer,String[]>
    MapList<Integer, String> maccountids = new MapList<Integer, String>();
    for (Entry<String, List<AccountDateType>> entry : map.entrySet()) {
      String sqdid = entry.getKey();
      List<AccountDateType> lads = entry.getValue();
      for (AccountDateType dt : lads) {
        // ���������Ͳ���ϵͳԤ�õ��Ǽ������ͣ�arapģ����������������Ϊ�ա�
        if (dt != null) {
          maccountids.put(Integer.valueOf(dt.getCode()), sqdid);
        }
      }
    }
    return maccountids;
  }

}
