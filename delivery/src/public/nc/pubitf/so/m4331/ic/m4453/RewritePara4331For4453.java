package nc.pubitf.so.m4331.ic.m4453;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewritePara4331For4453 {
  /** ����������id */
  private String cdeliverybid;

  /** ;���������仯���� */
  private UFDouble lossnum;

  /**
   * sendnum �Ĺ�����
   *
   * @param csaleorderbid
   *          Ҫ��д���۶��������е�id
   * @param lossNum
   *          ���������仯��
   */
  public RewritePara4331For4453(String cdeliverybid, UFDouble lossnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.lossnum = lossnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getLossnum() {
    return this.lossnum;
  }
}