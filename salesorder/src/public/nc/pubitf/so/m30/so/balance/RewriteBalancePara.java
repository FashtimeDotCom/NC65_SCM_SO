package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteBalancePara {

  /** ���۶�����ͷid */
  private String csaleorderid;

  /** ʵ���տ��� */
  private UFDouble nmny;

  /** ʵ��Ԥ�տ��� */
  private UFDouble npremny;

  public RewriteBalancePara(String csaleorderid, UFDouble nmny, UFDouble npremny) {
    if (PubAppTool.isNull(csaleorderid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0214")/*@res "Ҫ��д���۶�����ͷid����Ϊ�ա�"*/);
    }
    this.csaleorderid = csaleorderid;

    if (nmny == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0215")/*@res "�տ����Ϊ�ա�"*/);
    }
    this.nmny = nmny;
    if (npremny == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0215")/*@res "�տ����Ϊ�ա�"*/);
    }
    this.npremny = npremny;
  }

  public String getCsaleorderid() {
    return this.csaleorderid;
  }

  public UFDouble getNmny() {
    return this.nmny;
  }

  public UFDouble getNpremny() {
    return this.npremny;
  }

}