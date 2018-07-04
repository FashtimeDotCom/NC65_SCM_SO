package nc.pubimpl.so.deptmatrel.rule;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���š�ҵ��Ա�����Ϲ�ϵ���壺������ֵת������
 * 
 * @since 6.0
 * @version 2011-4-14 ����08:15:52
 * @author ף����
 */
public class DeptMatRelNullValueChgRule {

  private static final String NULLVALUE = "~";

  /**
   * ��ֵת��
   * 
   * @param extendparas
   */
  public void changeNullValue(DeptMatRelParaVO[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          DeptMatRelParaVO.PK_MATERIAL, DeptMatRelParaVO.PK_ORG,
          DeptMatRelParaVO.PK_MATERIALBASECLASS,
          DeptMatRelParaVO.PK_MATERIALSALECLASS, DeptMatRelParaVO.PK_DEPT,
          DeptMatRelParaVO.CEMPLOYEEID
        };
    for (DeptMatRelParaVO para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, DeptMatRelNullValueChgRule.NULLVALUE);
        }
      }
    }
  }
}
