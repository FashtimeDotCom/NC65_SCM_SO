package nc.pubimpl.so.rule;

import nc.pubitf.so.m30.ReturnAssignMatchVO;

/**
 * ���������Ψһ��ʶ
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:56:35
 * @author ף����
 */
public class ParaFillIndexRule {

  /**
   * ���������Ψһ��ʶ
   * 
   * @param matchparas
   */
  public void fillIndex(ReturnAssignMatchVO[] matchparas) {
    int i = 0;
    for (ReturnAssignMatchVO para : matchparas) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
