package nc.impl.so.m30.self;

import nc.bs.scmpub.page.ApproveBillFilter;
import nc.bs.scmpub.page.BillPageLazyQuery;
import nc.bs.scmpub.page.IBillFilter;
import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.bs.scmpub.tool.SchemeFixCondition;
import nc.bs.so.pub.SORowNoOrderConvert;
import nc.itf.so.m30.self.ISaleOrderMaintainApp;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.query.SOQueryApproveUtil;

/**
 * ���۶�����ҳ��ѯ����ʵ��
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public class SaleOrderMaintainAppImpl implements ISaleOrderMaintainApp {

  @Override
  public PageQueryVO queryM30App(IQueryScheme scheme) throws BusinessException {
    SchemeAppendCondition condition = new SchemeAppendCondition(scheme);
    SchemeFixCondition tool = new SchemeFixCondition(scheme);
    boolean flag = tool.getBoolean(SOQueryApproveUtil.BISAPPROVING);
    IBillFilter filter = null;
    if (flag) {
      // ��������ͨ���ĵ���
      // modify by wangweir ���˴���������������״̬����
      // Integer value = (Integer) BillStatus.AUDIT.value();
      condition.appendHeadNot(SaleOrderHVO.FPFSTATUSFLAG,
          IPfRetCheckInfo.PASSING);
      // ���Ӵ��������ݵĹ���
      filter = new ApproveBillFilter(SaleOrderVO.class, SOBillType.Order);
    }
    // ���Ӽ��ź�����֯����
    condition.appendPermission();
    BillPageLazyQuery<SaleOrderVO> query =
        new BillPageLazyQuery<SaleOrderVO>(SaleOrderVO.class, filter);
    PageQueryVO page = null;
    try {
      query.addHeadOrder(SaleOrderHVO.VBILLCODE);
      query.addChildOrder(SaleOrderBVO.class, SaleOrderBVO.CROWNO,
          new SORowNoOrderConvert());
      page = query.query(scheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return page;
  }

  @Override
  public SaleOrderVO[] queryM30App(String[] ids) throws BusinessException {
    BillPageLazyQuery<SaleOrderVO> query =
        new BillPageLazyQuery<SaleOrderVO>(SaleOrderVO.class);
    query.addChildOrder(SaleOrderBVO.class, SaleOrderBVO.CROWNO,
          new SORowNoOrderConvert());
    return query.queryPageBills(ids);
  }
}
