package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteEstarnumPara {
  /** ����������id */
  private String cdeliverybid;

  /** �ݹ��������仯���� */
  private UFDouble estarnum;

  /**
   * sendnum �Ĺ�����
   * 
   * @param csaleorderbid
   *          Ҫ��д���۶��������е�id
   * @param estarnum
   *          �ݹ������仯��
   */
  public RewriteEstarnumPara(String cdeliverybid, UFDouble estarnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.estarnum = estarnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getEstarnum() {
    return this.estarnum;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }

  public void setEstarnum(UFDouble estarnum) {
    this.estarnum = estarnum;
  }
}
