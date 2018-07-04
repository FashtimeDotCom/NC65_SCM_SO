package nc.pubimpl.so.custmatrel.rule;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ������ֵת������
 * 
 * @since 6.0
 * @version 2011-4-14 ����08:15:52
 * @author ף����
 */
public class CustMatRelNullValueChgRule {

  private static final String NULLVALUE = "~";

  /**
   * ��ֵת��
   * 
   * @param extendparas
   */
  public void changeNullValue(CustMatRelParaVO[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          CustMatRelParaVO.PK_MATERIAL, CustMatRelParaVO.PK_ORG,
          CustMatRelParaVO.PK_MATERIALBASECLASS,
          CustMatRelParaVO.PK_MATERIALSALECLASS, CustMatRelParaVO.PK_CUSTOMER,
          CustMatRelParaVO.PK_CUSTBASECLASS, CustMatRelParaVO.PK_CUSTSALECLASS,
        };
    for (CustMatRelParaVO para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, CustMatRelNullValueChgRule.NULLVALUE);
        }
      }
    }
  }
}
