package nc.pubimpl.so.m30.mmpps.calc;

import nc.vo.pub.BusinessException;

import nc.pubitf.mmpub.sdmanage.IDemandResult;
import nc.pubitf.so.m30.mmpps.calc.ISaleOrderForMPS;
import nc.pubitf.so.m30.mmpps.calc.SaleOrderDemandMapVO;

/**
 * ���۶����ṩ��������MPS����ӿ�ʵ����
 * 
 * @since 6.3.1
 * @version 2013-08-21 09:07:49
 * @author ���Ʒ�
 * 
 */
public class SaleOrderForMPSImpl implements ISaleOrderForMPS {

  @Override
  public IDemandResult getDemandFroSaleOrder() throws BusinessException {
    SaleOrderDemandMapVO mapVo = new SaleOrderDemandMapVO();
    return mapVo;
  }

}
