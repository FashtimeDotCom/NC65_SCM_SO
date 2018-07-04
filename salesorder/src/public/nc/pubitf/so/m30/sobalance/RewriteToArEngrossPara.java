package nc.pubitf.so.m30.sobalance;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteToArEngrossPara implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8304061881057221479L;

  /** �տ����id */
  private String cpaybillrowid;

  /** �տ���ռ�������仯���� */
  private UFDouble nmny;

  public RewriteToArEngrossPara(String cpaybillrowid, UFDouble nmny) {

    if (PubAppTool.isNull(cpaybillrowid)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0222")/*@res "Ҫ��д�տ����id����Ϊ�ա�"*/);
    }
    this.cpaybillrowid = cpaybillrowid;

    if (nmny == null) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0223")/*@res "�տ���ռ��������Ϊ�ա�"*/);
    }
    this.nmny = nmny;
  }

  public String getCpaybillrowid() {
    return this.cpaybillrowid;
  }

  public UFDouble getNmny() {
    return this.nmny;
  }

}