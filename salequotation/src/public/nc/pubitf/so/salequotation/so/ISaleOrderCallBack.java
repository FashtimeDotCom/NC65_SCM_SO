package nc.pubitf.so.salequotation.so;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * ���۶������ձ��۵�ʱ��д�������۶�����������,�ṩ�����۶����ķ���
 * 
 * @author chenyyb
 * 
 */
public interface ISaleOrderCallBack {

  void saleOrderCallBack(QuatationRewritePara[] vos) throws BusinessException;
}
