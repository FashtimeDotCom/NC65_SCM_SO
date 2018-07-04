package nc.impl.so.salequotation.service.so;

import nc.bs.so.salequotation.bp.RewriteBillOperateBP;
import nc.bs.so.salequotation.bp.RewriteSaleOrderNumBP;
import nc.pubitf.so.salequotation.so.ISaleOrderCallBack;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * ���۶������ձ��۵�ʱ��д�������۶�����������,�ṩ�����۶����ķ���
 * 
 * @author chenyyb
 * 
 */
public class SaleOrderCallBackService implements ISaleOrderCallBack {

  @Override
  public void saleOrderCallBack(QuatationRewritePara[] vos) {
    new RewriteSaleOrderNumBP().rewriteSaleOrderNum(vos);
    new RewriteBillOperateBP().operateBill(vos);
  }

}
