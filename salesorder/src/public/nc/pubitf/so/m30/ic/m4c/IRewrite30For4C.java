package nc.pubitf.so.m30.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * ���۳��ⵥ��д���۶�������ӿڡ�
 * ���۳��ⵥ��д���ⵥӦ����ʵ���������Ƿ�ǿ���г���رա�
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public interface IRewrite30For4C {

  /**
   * ���۳��ⵥ��д���۶����ۼ�Ӧ����ʵ��
   * 
   * @param paras Rewrite4CPara[]
   * @throws BusinessException
   */
  void rewrite30NumFor4C(Rewrite4CPara[] paras) throws BusinessException;
}
