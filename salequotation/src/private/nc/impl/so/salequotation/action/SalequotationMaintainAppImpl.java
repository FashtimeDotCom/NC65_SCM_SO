package nc.impl.so.salequotation.action;

import nc.bs.scmpub.page.ApproveBillFilter;
import nc.bs.scmpub.page.BillPageLazyQuery;
import nc.bs.scmpub.page.IBillFilter;
import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.bs.scmpub.tool.SchemeFixCondition;
import nc.itf.so.salequotation.ISalequotationMaintainApp;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.query.SOQueryApproveUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ���۱��۵���ҳ��ѯ����ʵ��1
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-11
 */
public class SalequotationMaintainAppImpl implements ISalequotationMaintainApp {

  @Override
  public PageQueryVO queryM4310App(IQueryScheme scheme)
      throws BusinessException {
    SchemeAppendCondition condition = new SchemeAppendCondition(scheme);
    SchemeFixCondition tool = new SchemeFixCondition(scheme);
    boolean flag = tool.getBoolean(SOQueryApproveUtil.BISAPPROVING);
    IBillFilter filter = null;
    if (flag) {
      // ��������ͨ���ĵ���
      condition.appendHeadNot(SalequotationHVO.FSTATUSFLAG,
          BillStatusEnum.C_AUDIT);
      condition.appendHeadNot(SalequotationHVO.FSTATUSFLAG,
          BillStatusEnum.C_CLOSE);

      // ���Ӵ��������ݵĹ���
      filter =
          new ApproveBillFilter(AggSalequotationHVO.class,
              SOBillType.SaleQuotation, SalequotationHVO.VTRANTYPE,
              SalequotationHVO.BILLMAKER);
    }
    // ���Ӽ��ź�����֯����
    condition.appendPermission();
    BillPageLazyQuery<AggSalequotationHVO> query =
        new BillPageLazyQuery<AggSalequotationHVO>(AggSalequotationHVO.class,
            filter);
    PageQueryVO page = null;
    try {
      query.addHeadOrder(SalequotationHVO.VBILLCODE);
      page = query.query(scheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return page;
  }

  @Override
  public AggSalequotationHVO[] queryM4310App(String[] ids)
      throws BusinessException {
    BillPageLazyQuery<AggSalequotationHVO> query =
        new BillPageLazyQuery<AggSalequotationHVO>(AggSalequotationHVO.class);
    return query.queryPageBills(ids);
  }

}
