package nc.impl.so.m32.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.itf.scmpub.reference.uap.md.MDQueryFacade;

import nc.md.model.IBean;

import nc.bs.busilog.util.BusinessLogServiceUtil;
import nc.bs.busilog.vo.BusinessLogContext;
import nc.bs.logging.Logger;
import nc.bs.sm.busilog.util.LogConfigServiceFacade;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷�Ʊ����ǰ��¼ҵ����־����
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 * @since 6.1
 * @version 2012-12-21 ����09:20:35
 * @author yaogj
 */
public class BusiLog implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    List<BusinessLogContext> list = new ArrayList<BusinessLogContext>();
    IVOMeta meta = vos[0].getParentVO().getMetaData();
    String beanname = meta.getEntityName();
    IBean bean = MDQueryFacade.getBeanByFullName(beanname);
    for (SaleInvoiceVO vo : vos) {
      BusinessLogContext context = new BusinessLogContext();
      context.setPk_busiobj(vo.getPrimaryKey());
      context.setBusiobjcode(vo.getParentVO().getVbillcode());
      // context.setBusiobjname(aggCtSaleVO.getParentVO()..getCtname());
      context.setTypepk_busiobj(bean.getID());
      context.setPk_operation("a83d665b-4ba1-4d36-9b7a-56e281149408");
      context.setHasjudged(true);
      // context.setOldbusiobjvo(vo);
      context.setOrgpk_busiobj(vos[0].getParentVO().getPk_org());
      if (this.isNeedBusiLog(AppContext.getInstance().getPkGroup(),
          bean.getID(), context.getPk_operation())) {
        list.add(context);
      }
    }
    try {
      BusinessLogServiceUtil.insertBatchBusilogInTransaction(list);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private boolean isNeedBusiLog(String grouppk, String metaid, String busiopid) {
    try {
      return LogConfigServiceFacade.getInstance().isOperNeedLog(grouppk,
          metaid, busiopid);
    }
    catch (BusinessException e) {
      Logger.error(e.getMessage(), e);
      return false;
    }
  }
}
