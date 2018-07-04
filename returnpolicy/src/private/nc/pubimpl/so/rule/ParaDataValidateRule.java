package nc.pubimpl.so.rule;

import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * �������ݵĺϷ��Լ��
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:37:53
 * @author ף����
 */
public class ParaDataValidateRule {

  /**
   * �������ݺϷ��Լ��
   * 
   * @param csaleorgid
   * @param matchparas
   */
  public void validate(String csaleorgid, ReturnAssignMatchVO[] matchparas) {
    if (PubAppTool.isNull(csaleorgid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0036")
      /*@res"�˻����߼��ʱ, ������֯����Ϊ�ա�"*/);
    }
    for (ReturnAssignMatchVO para : matchparas) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
