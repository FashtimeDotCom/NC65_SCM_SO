package nc.pubimpl.so.deptmatrel.rule;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;

/**
 * ���š�ҵ��Ա�������ʼ��ϵ������������Ψһ��ʶ
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:56:35
 * @author ף����
 */
public class DeptMatRelFillIndexRule {

  /**
   * ���������Ψһ��ʶ
   * 
   * @param paravos
   */
  public void fillIndex(DeptMatRelParaVO[] paravos) {
    int i = 0;
    for (DeptMatRelParaVO para : paravos) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
