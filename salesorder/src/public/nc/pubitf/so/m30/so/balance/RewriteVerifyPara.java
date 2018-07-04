package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteVerifyPara {

  /** ���۶�������bid */
  private String csaleorderbid;

  /** ������� */
  private UFDouble nmny;

  public RewriteVerifyPara(String csaleorderbid, UFDouble nmny) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0214")/*@res "Ҫ��д���۶�������id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nmny == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0215")/*@res "�տ����Ϊ�ա�"*/);
    }
    this.nmny = nmny;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNmny() {
    return this.nmny;
  }
}
