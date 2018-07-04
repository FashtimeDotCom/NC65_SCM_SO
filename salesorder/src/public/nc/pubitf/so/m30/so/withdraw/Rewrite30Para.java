package nc.pubitf.so.m30.so.withdraw;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class Rewrite30Para {

  /** ���۶�������id */
  private String csaleorderbid;

  /** ���۶����˻����仯���� */
  private UFDouble nnum;

  public Rewrite30Para(String csaleorderbid, UFDouble nnum) {

    if (PubAppTool.isNull(csaleorderbid)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nnum == null) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0221")/*@res "���۶����˻�����Ϊ�ա�"*/);
    }
    this.nnum = nnum;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

}