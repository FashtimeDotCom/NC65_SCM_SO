package nc.pubitf.so.m4331.dm.m4804;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���䵥��д����������
 *
 * @since 6.0
 * @version 2011-3-17 ����01:53:17
 * @author ף����
 */
public class RewritePara4331For4804 {

  /** ����������id */
  private String cdeliverybid;

  /** �����������仯���� */
  private UFDouble transnum;

  public RewritePara4331For4804(String cdeliverybid, UFDouble outnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.transnum = outnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getTransnum() {
    return this.transnum;
  }
}