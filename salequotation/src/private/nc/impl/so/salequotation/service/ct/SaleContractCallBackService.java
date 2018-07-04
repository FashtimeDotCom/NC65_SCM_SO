package nc.impl.so.salequotation.service.ct;

import nc.bs.so.salequotation.bp.RewriteBillOperateBP;
import nc.bs.so.salequotation.bp.RewriteNcontractNumBP;
import nc.pubitf.so.salequotation.ct.ISaleContractCallBack;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * ���ۺ�ͬ���ձ��۵�ʱ��д�������۶������������ṩ�����ۺ�ͬ�ķ���
 * 
 * @author chenyyb
 * 
 */
public class SaleContractCallBackService implements ISaleContractCallBack {

  @Override
  public void saleContractCallBack(QuatationRewritePara[] vos)
      throws BusinessException {
    if (null == vos || vos.length == 0) {
      return;
    }
    try {
      new RewriteNcontractNumBP().rewriteSaleContractNum(vos);
      new RewriteBillOperateBP().operateBill(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
