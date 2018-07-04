package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteArnumPara {
  /** ȷ��Ӧ���������仯���� */
  private UFDouble arnum;

  /** ����������id */
  private String cdeliverybid;

  /**
   * sendnum �Ĺ�����
   * 
   * @param csaleorderbid
   *          Ҫ��д���۶��������е�id
   * @param arnum
   *          ȷ��Ӧ�������仯��
   */
  public RewriteArnumPara(String cdeliverybid, UFDouble arnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.arnum = arnum;
  }

  public UFDouble getArnum() {
    return this.arnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public void setArnum(UFDouble arnum) {
    this.arnum = arnum;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }
}
