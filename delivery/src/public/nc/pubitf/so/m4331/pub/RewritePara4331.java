package nc.pubitf.so.m4331.pub;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <li>�����۶������ߵ������������رգ�ɾ����������̬�ķ��������ر��������״̬�ķ�����
 *
 * @author ף����
 * @time 2010-3-23 ����06:36:26
 */
public class RewritePara4331 {

  /** ���۶������ߵ�����������id */
  private String csrcbid;

  public RewritePara4331(String csrcbid) {
    if (PubAppTool.isNull(csrcbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0104")/*@res "��Դ���ݱ���id����Ϊ�գ�"*/);
    }
    this.csrcbid = csrcbid;
  }

  public String getCsrcbid() {
    return this.csrcbid;
  }
}