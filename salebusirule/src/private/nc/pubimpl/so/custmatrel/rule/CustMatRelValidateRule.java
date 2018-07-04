package nc.pubimpl.so.custmatrel.rule;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * �ͻ������Ϲ�ϵ�������ݵĺϷ��Լ��
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:37:53
 * @author ף����
 */
public class CustMatRelValidateRule {

  /**
   * �������ݺϷ��Լ��
   * 
   * @param paravos
   * @return
   */
  public UFBoolean[] validate(CustMatRelParaVO[] paravos) {

    UFBoolean[] validateNull = new UFBoolean[paravos.length];

    for (CustMatRelParaVO para : paravos) {

      if (PubAppTool.isNull(para.getPk_org())
          || PubAppTool.isNull(para.getPk_material())
          || PubAppTool.isNull(para.getPk_customer())) {
        validateNull[para.getParaindex().intValue()] = UFBoolean.FALSE;
      }
      else {
        validateNull[para.getParaindex().intValue()] = UFBoolean.TRUE;
      }
    }
    return validateNull;
  }
}
