package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.BusinessException;

public interface IRewrite4331For33 {
  /**
   * ����������������д������ȷ��Ӧ��������
   * 
   * @author ף����
   * @time 2010-9-6 ����04:49:14
   */
  void rewrite4331Arnum(RewriteArnumPara[] paras) throws BusinessException;

  /**
   * ������������:��д�������ݹ�Ӧ������
   * 
   * @author ף����
   * @time 2010-9-6 ����04:48:44
   */
  void rewrite4331Estarnum(RewriteEstarnumPara[] paras)
      throws BusinessException;

  /**
   * ����������������д�������Գ�������
   * 
   * @author ף����
   * @time 2010-9-6 ����04:48:19
   */
  void rewrite4331RushNum(RewriteRushNumPara[] paras) throws BusinessException;
}
