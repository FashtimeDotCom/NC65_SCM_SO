package nc.ui.so.pub.closingcheck;

import nc.vo.ia.mi4.entity.I4HeadVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.sc.m62.entity.AdjustHeaderVO;
import nc.vo.scmpub.parameter.ia.QueryParaVO;
import nc.vo.scmpub.parameter.ic.QueryTypeEnumForIA;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.to.report.ia.entity.PreSettleViewVO;
import nc.vo.to.report.ia.entity.SettleListViewVO;

import nc.pub.templet.converter.util.helper.ExceptionUtils;

import nc.pubitf.ia.mi4.ia.IIAI4ForCLSCheck;
import nc.pubitf.ic.general.ia.IQueryBeforeCloseAccountDataForIA;
import nc.pubitf.sc.m62.ia.ISCAdjustForIa;
import nc.pubitf.so.m33.ia.ISaleSettleQueryForIA;
import nc.pubitf.to.ia.ISettleQueryForIA;

import nc.bs.framework.common.NCLocator;

import nc.pubift.pu.m25.ia.IInvoiceQueryForIAClosingCheck;

/**
 * @description ���ʼ���ұ����ݲ�ѯ
 * 
 * @since 6.3
 * @version 2014-11-17
 * @author ��־��
 * 
 */
public class ClosingCheckModelService {

  /**
   * ��ȡ��������޵���������ⵥVO
   * 
   * @param pk_org
   * @param caccountperiod
   * @return I4HeadVO[]
   */
  public I4HeadVO[] getI4HeadVO(String[] pk_org, String caccountperiod) {
    IIAI4ForCLSCheck service =
        NCLocator.getInstance().lookup(IIAI4ForCLSCheck.class);
    I4HeadVO[] VOs = null;
    try {
      VOs = service.queryI4(pk_org, caccountperiod);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return VOs;
  }

  /**
   * ��ȡδ�����ɹ���ƱVO
   * 
   * @param paraVo
   * @return InvoiceHeaderVO[]
   */
  public InvoiceHeaderVO[] getUnapprovePoInvoiceVO(QueryParaVO paraVo) {
    IInvoiceQueryForIAClosingCheck service =
        NCLocator.getInstance().lookup(IInvoiceQueryForIAClosingCheck.class);
    InvoiceHeaderVO[] VOs = null;
    try {
      VOs = service.queryUnapprovedInvoice(paraVo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return VOs;

  }

  /**
   * ��ȡδ����ɹ���ƱVO
   * 
   * @param paraVo
   * @return InvoiceHeaderVO[]
   */
  public InvoiceHeaderVO[] getUnsettledPoInvoiceVO(QueryParaVO paraVo) {
    IInvoiceQueryForIAClosingCheck service =
        NCLocator.getInstance().lookup(IInvoiceQueryForIAClosingCheck.class);
    InvoiceHeaderVO[] VOs = null;
    try {
      VOs = service.queryUnsettledInvoice(paraVo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return VOs;
  }

  /**
   * ��ȡδ����ɹ���ⵥ,δǩ�ֵĿ�浥��,δ����ǩ��;��,δ����ί��ӹ���ⵥVO
   * 
   * @param paraVo
   * @param queryType
   * @return SuperVO[]
   */
  public SuperVO[] getUnsettledPoGeneralInVO(QueryParaVO paraVo,
      QueryTypeEnumForIA queryType) {
    IQueryBeforeCloseAccountDataForIA service =
        NCLocator.getInstance().lookup(IQueryBeforeCloseAccountDataForIA.class);
    SuperVO[] VOs = null;
    try {
      VOs = service.queryDataBeforeCloseAccountForIA(paraVo, queryType);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return VOs;

  }

  /**
   * ��ȡδ�������۷�ƱVO
   * 
   * @param paraVo
   * @return SaleInvoiceHVO[]
   */
  public SaleInvoiceHVO[] getUnapproveSoInvoiceVO(QueryParaVO paraVo) {

    ISaleSettleQueryForIA service =
        NCLocator.getInstance().lookup(ISaleSettleQueryForIA.class);
    SaleInvoiceHVO[] VOs = null;
    try {
      VOs = service.queryFreeSaleInvoiceHVO(paraVo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return VOs;
  }

  /**
   * ��ȡδ�������۳��ⵥVO
   * 
   * @param paraVo
   * @return I4HeadVO[]
   */
  public SaleOutHeadVO[] getUnsettledSaleOut(QueryParaVO paraVo) {

    ISaleSettleQueryForIA service =
        NCLocator.getInstance().lookup(ISaleSettleQueryForIA.class);
    SaleOutHeadVO[] VOs = null;
    try {
      VOs = service.queryUnSquareSaleOutHeadVO(paraVo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return VOs;

  }

  /**
   * ��ȡ�ڲ�����δ����VO
   * 
   * @param paraVo
   * @return I4HeadVO[]
   */
  public PreSettleViewVO[] getUnsettledTOVO(QueryParaVO paraVo) {
    ISettleQueryForIA service =
        NCLocator.getInstance().lookup(ISettleQueryForIA.class);
    PreSettleViewVO[] VOs = service.queryUnSettleSettleList(paraVo);
    return VOs;

  }

  /**
   * ��ȡ�ڲ�����δ����VO
   * 
   * @param paraVo
   * @return I4HeadVO[]
   */
  public SettleListViewVO[] getUnapproveTOVO(QueryParaVO paraVo) {
    ISettleQueryForIA service =
        NCLocator.getInstance().lookup(ISettleQueryForIA.class);
    SettleListViewVO[] VOs = service.queryUnApproveSettleList(paraVo);
    return VOs;
  }

  /**
   * ��ȡ�ڲ�����δת����VO
   * 
   * @param paraVo
   * @return SettleListViewVO[]
   */
  public SettleListViewVO[] getUnfinancTOVO(QueryParaVO paraVo) {
    ISettleQueryForIA service =
        NCLocator.getInstance().lookup(ISettleQueryForIA.class);
    SettleListViewVO[] VOs = service.queryUnpushIASettleList(paraVo);
    return VOs;
  }

  /**
   * ��ȡδ����ί�������VO
   * 
   * @param paraVo
   * @return AdjustBillVO[]
   */
  public AdjustHeaderVO[] getUnapproveSCAdjustVO(QueryParaVO paraVo) {
    ISCAdjustForIa service =
        NCLocator.getInstance().lookup(ISCAdjustForIa.class);
    AdjustHeaderVO[] VOs = service.queryUnAppoveAdjustForIa(paraVo);
    return VOs;

  }
}
