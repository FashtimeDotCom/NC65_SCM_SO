package nc.itf.so.m30.self;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.bind.OrderBindMatchPara;
import nc.vo.so.m30.bind.OrderBindMatchResult;
import nc.vo.so.m30.entity.SaleOrderVO;

public interface ISaleOrderBusi {

  /**
   * ���۶�����������ƥ��
   * 
   * @param bindparas
   * @return
   */
  OrderBindMatchResult[] matchBind(OrderBindMatchPara[] bindparas)
      throws BusinessException;

  /**
   * �����տ�׼��
   * 
   * @param bill ���۶���VO
   * @return AggregatedValueObject[] �տVOs
   */
  AggregatedValueObject[] prepareOrderGathering(SaleOrderVO bill)
      throws BusinessException;
}
