package nc.pubitf.so.m32.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�ƱΪ���۳��ⵥ�ṩ�Ļ�д��
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-3-23 ����06:38:24
 */
public interface IRewrite32For4C {
  /**
   * ����������������д���۷�Ʊ�ϵ��ۼƳ��������ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @author ��ӱ�
   * @time 2010-3-23 ����06:38:40
   */
  void rewrite32OutNumFor4C(RewritePara32For4C[] paras)
      throws BusinessException;
}
