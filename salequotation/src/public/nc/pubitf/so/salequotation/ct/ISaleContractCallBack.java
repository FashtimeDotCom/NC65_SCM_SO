package nc.pubitf.so.salequotation.ct;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * ���ۺ�ͬ���ձ��۵�ʱ��д�������۶������������ṩ�����ۺ�ͬ�ķ���
 * 
 * @author chenyyb
 * 
 */
public interface ISaleContractCallBack {

  void saleContractCallBack(QuatationRewritePara[] vos)
      throws BusinessException;
}
