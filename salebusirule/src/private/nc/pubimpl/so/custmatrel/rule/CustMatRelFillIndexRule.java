package nc.pubimpl.so.custmatrel.rule;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;

/**
 * ���������Ψһ��ʶ
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:56:35
 * @author ף����
 */
public class CustMatRelFillIndexRule {

  /**
   * ���������Ψһ��ʶ
   * 
   * @param paravos
   */
  public void fillIndex(CustMatRelParaVO[] paravos) {
    int i = 0;
    for (CustMatRelParaVO para : paravos) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
