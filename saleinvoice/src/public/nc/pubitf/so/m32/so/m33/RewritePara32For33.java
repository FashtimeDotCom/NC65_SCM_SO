package nc.pubitf.so.m32.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewritePara32For33 {

  /**
   * ���۷�Ʊbid
   */
  private String csaleinvoicebid;

  /**
   * �ۼƳɱ���������
   */
  private UFDouble ntotalcostnum;

  /**
   * �ۼ�ȷ��Ӧ�ս��
   */
  private UFDouble ntotalincomemny;

  /**
   * �ۼ�ȷ��Ӧ������
   */
  private UFDouble ntotalincomenum;

  public RewritePara32For33(String csaleinvoicebid, UFDouble ntotalincomenum,
      UFDouble ntotalincomemny, UFDouble ntotalcostnum) {
    if (PubAppTool.isNull(csaleinvoicebid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0064")/*@res "���۷�Ʊ������id����Ϊ�ա�"*/);
    }
    this.csaleinvoicebid = csaleinvoicebid;

    this.ntotalincomenum = ntotalincomenum;
    this.ntotalincomemny = ntotalincomemny;
    this.ntotalcostnum = ntotalcostnum;
  }

  public String getCsaleinvoicebid() {
    return this.csaleinvoicebid;
  }

  public UFDouble getNtotalCostNum() {
    return this.ntotalcostnum;
  }

  public UFDouble getNtotalIncomeMny() {
    return this.ntotalincomemny;
  }

  public UFDouble getNtotalIncomeNum() {
    return this.ntotalincomenum;
  }

}
