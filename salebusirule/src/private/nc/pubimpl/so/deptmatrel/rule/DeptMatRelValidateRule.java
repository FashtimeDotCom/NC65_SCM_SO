package nc.pubimpl.so.deptmatrel.rule;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���š�ҵ��Ա�����Ϲ�ϵ�������ݵĺϷ��Լ��
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:37:53
 * @author ף����
 */
public class DeptMatRelValidateRule {

  /**
   * �������ݺϷ��Լ��
   * 
   * @param csaleorgid
   * @param paravos
   */
  public void validate(DeptMatRelParaVO[] paravos) {
    for (DeptMatRelParaVO para : paravos) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
