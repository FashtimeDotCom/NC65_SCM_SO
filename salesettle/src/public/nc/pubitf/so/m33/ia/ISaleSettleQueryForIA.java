package nc.pubitf.so.m33.ia;

import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 
 * ���۽����ṩ���������Ĳ�ѯ�ӿ�
 * 
 * @since 6.36
 * @version 2014-12-3 ����2:19:10
 * @author zhangby5
 */
public interface ISaleSettleQueryForIA {

  /**
   * ��ͷ��Ʊ�����ڵ�ǰ����ڼ䣬��Ʊ��֯���ڵ�ǰ�ɱ����Ӧ�Ĳ�����֯������̬�����۷�Ʊ��ͷ
   * 
   * @param paraVO ���ʼ���ѯ����vo
   * @return ����̬�����۷�Ʊ��ͷ
   * @throws BusinessException
   */
  public SaleInvoiceHVO[] queryFreeSaleInvoiceHVO(QueryParaVO paraVO)
      throws BusinessException;

  /**
   * ��ͷ��Ʊ�����ڵ�ǰ����ڼ䣬������֯���ڵ�ǰҵ��Ԫ��������δ��������۳��ⵥ
   * 
   * @param paraVO ���ʼ���ѯ����vo
   * @return ������δ��������۳��ⵥ��ͷ
   * @throws BusinessException
   */
  public SaleOutHeadVO[] queryUnSquareSaleOutHeadVO(QueryParaVO paraVO)
      throws BusinessException;

}
