package nc.pubimpl.so.tranmatrel.rule;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * �������������Ϲ�ϵ���壺������ֵת������
 * 
 * @since 6.0
 * @version 2011-4-14 ����08:15:52
 * @author ף����
 */
public class TranMatRelNullValueChgRule {

  private static final String NULLVALUE = "~";

  /**
   * ��ֵת��
   * 
   * @param extendparas
   */
  public void changeNullValue(TranMatRelParaVO[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          TranMatRelParaVO.PK_MATERIAL, TranMatRelParaVO.PK_ORG,
          TranMatRelParaVO.PK_MATERIALBASECLASS,
          TranMatRelParaVO.PK_MATERIALSALECLASS, TranMatRelParaVO.PK_FATHERORG
        };
    for (TranMatRelParaVO para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, TranMatRelNullValueChgRule.NULLVALUE);
        }
      }
    }
  }
}
