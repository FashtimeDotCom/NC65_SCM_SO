package nc.pubimpl.so.tranmatrel.rule;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;

/**
 * ���������Ψһ��ʶ
 * 
 * @since 6.0
 * @version 2011-4-14 ����02:56:35
 * @author ף����
 */
public class TranMatRelFillIndexRule {

  /**
   * ���������Ψһ��ʶ
   * 
   * @param paravos
   */
  public void fillIndex(TranMatRelParaVO[] paravos) {
    int i = 0;
    for (TranMatRelParaVO para : paravos) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
