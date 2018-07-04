package nc.impl.so.m4331;

import nc.bs.scmpub.page.ApproveBillFilter;
import nc.bs.scmpub.page.BillPageLazyQuery;
import nc.bs.scmpub.page.IBillFilter;
import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.bs.scmpub.tool.SchemeFixCondition;
import nc.itf.so.m4331.IDeliveryMaintainApp;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.query.SOQueryApproveUtil;

/**
 * ��������ҳ��ѯ����ʵ��1
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-11
 */
public class DeliveryMaintainAppImpl implements IDeliveryMaintainApp {

  @Override
  public PageQueryVO queryM4331App(IQueryScheme scheme)
      throws BusinessException {
    SchemeAppendCondition condition = new SchemeAppendCondition(scheme);
    SchemeFixCondition tool = new SchemeFixCondition(scheme);
    boolean flag = tool.getBoolean("waitapprove");
    IBillFilter filter = null;
    if (flag) {
      // ��������ͨ���ĵ���
      condition.appendHeadNot(DeliveryHVO.FSTATUSFLAG, BillStatus.I_AUDIT);
      condition.appendHeadNot(DeliveryHVO.FSTATUSFLAG, BillStatus.I_CLOSED);
      // ���Ӵ��������ݵĹ���
      filter = new ApproveBillFilter(DeliveryVO.class, SOBillType.Delivery);
    }
    // ���Ӽ��ź�����֯����
    condition.appendPermission();
    BillPageLazyQuery<DeliveryVO> query =
        new BillPageLazyQuery<DeliveryVO>(DeliveryVO.class, filter);
    PageQueryVO page = null;
    try {
      query.addHeadOrder(DeliveryHVO.VBILLCODE);
      page = query.query(scheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return page;
  }

  @Override
  public DeliveryVO[] queryM4331App(String[] ids) throws BusinessException {
    BillPageLazyQuery<DeliveryVO> query =
        new BillPageLazyQuery<DeliveryVO>(DeliveryVO.class);
    return query.queryPageBills(ids);
  }

}
