package nc.bs.so.m30.rule.maintainprocess;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.price.PriceServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * ����ɾ��ʱɾ���۸������Ϣ
 * @scene 
 * ���۶���ɾ���������ڼ۸����ʱ
 * @param 
 * ��
 * @since 6.0
 * @version 2011-8-22 ����02:25:22
 * @author ��־ΰ
 */
public class DeletePriceFormWhenDelRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      List<String> idList = new ArrayList<String>();
      for (SaleOrderBVO body : bodys) {
        String cpriceformid = body.getCpriceformid();
        if (cpriceformid != null) {
          idList.add(cpriceformid);
        }
      }
      if (idList.size() > 0) {
        try {
          PriceServicesUtil.deletePriceFormByBillPK(bill.getPrimaryKey());
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
  }
}
