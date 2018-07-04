package nc.pubitf.so.m32.ic.m4c;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�������۳��ⵥ��д��Ʊ�ۼƳ�����������
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-3-23 ����06:36:26
 */
public class RewritePara32For4C {
  /**
   * ���۷�Ʊbid
   */
  private String csaleinvoicebid;

  /**
   * �ۼ�Ӧ��δ����仯��
   */
  private UFDouble nshouldnum;

  /**
   * �ۼƳ�������
   */
  private UFDouble ntotaloutnum;

  public RewritePara32For4C(String saleinvoicebid, UFDouble nshouldnum,
      UFDouble ntotaloutnum) {
    if (PubAppTool.isNull(saleinvoicebid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0063")/*@res "���۷�Ʊ������ID����Ϊ�ա�"*/);
    }
    this.csaleinvoicebid = saleinvoicebid;

    this.nshouldnum = nshouldnum;
    this.ntotaloutnum = ntotaloutnum;
  }

  /**
   * �����������������ط�Ʊ����ID��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-3-23 ����06:37:16
   */
  public String getCsaleinvoicebid() {
    return this.csaleinvoicebid;
  }

  /**
   * ���������������������۳��ⵥʵ�������仯ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-3-23 ����06:37:52
   */
  public UFDouble getNoutnum() {
    return this.ntotaloutnum;
  }

  /**
   * ���������������������۳��ⵥӦ�������仯ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-3-23 ����06:37:31
   */
  public UFDouble getNshouldoutnum() {
    return this.nshouldnum;
  }
}
