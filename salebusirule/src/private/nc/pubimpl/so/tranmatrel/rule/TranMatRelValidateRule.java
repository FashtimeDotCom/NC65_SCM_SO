package nc.pubimpl.so.tranmatrel.rule;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �������������Ϲ�ϵ���壺�������ݵĺϷ��Լ��
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:37:53
 * @author ף����
 */
public class TranMatRelValidateRule {

  /**
   * �������ݺϷ��Լ��
   * 
   * @param csaleorgid
   * @param paravos
   */
  public void validate(TranMatRelParaVO[] paravos) {
    for (TranMatRelParaVO para : paravos) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
