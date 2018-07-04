package nc.pubitf.so.m32.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 
 * @since 6.0
 * @version 2011-9-2 ����12:20:25
 * @author ô��
 */
public class RewritePara32For33OnVerify {

  /**
   * ���۷�Ʊbid
   */
  private String csaleinvoicebid;

  /**
   * �տ���
   */
  private UFDouble ntotalpaymny;

  public RewritePara32For33OnVerify(String csaleinvoicebid,
      UFDouble ntotalpaymny) {
    if (PubAppTool.isNull(csaleinvoicebid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0064")/*@res "���۷�Ʊ������id����Ϊ�ա�"*/);
    }
    this.csaleinvoicebid = csaleinvoicebid;

    this.ntotalpaymny = ntotalpaymny;
  }

  public String getCsaleinvoicebid() {
    return this.csaleinvoicebid;
  }

  public UFDouble getNtotalpaymny() {
    return this.ntotalpaymny;
  }

}
