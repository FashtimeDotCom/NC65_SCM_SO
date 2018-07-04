package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteRushNumPara {
  /** ����������id */
  private String cdeliverybid;

  /** �Գ��������仯���� */
  private UFDouble rushnum;

  /**
   * sendnum �Ĺ�����
   * 
   * @param csaleorderbid
   *          Ҫ��д���۶��������е�id
   * @param sendnum
   *          �Գ������仯��
   */
  public RewriteRushNumPara(String cdeliverybid, UFDouble rushnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.rushnum = rushnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getRushnum() {
    return this.rushnum;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }

  public void setRushnum(UFDouble rushnum) {
    this.rushnum = rushnum;
  }
}
