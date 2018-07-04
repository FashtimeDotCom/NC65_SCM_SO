package nc.pubitf.so.m38.so.m30;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���۶�����дԤ�����Ļ�д������
 *
 * @since 6.0
 * @version 2010-11-3 ����10:28:31
 * @author ��־ΰ
 */
public class Rewrite30Para {

  /** Ԥ��������id */
  private String cpreorderbid;

  /** ���۶����������仯���� */
  private UFDouble nnum;

  /**
   * Rewrite30Para �Ĺ�����
   *
   * @param cpreorderbid Ҫ��дԤ���������е�id
   * @param nnum ���۶��������������仯��
   */
  public Rewrite30Para(String cpreorderbid, UFDouble nnum) {
    if (PubAppTool.isNull(cpreorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0033")/*@res "Ҫ��дԤ���������е�id����Ϊ�ա�"*/);
    }
    this.cpreorderbid = cpreorderbid;

    if ((nnum == null) || UFDouble.ZERO_DBL.equals(nnum)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0034")/*@res "���۶�����������������Ϊ�ջ�0��"*/);
    }
    this.nnum = nnum;
  }

  public String getCpreorderbid() {
    return this.cpreorderbid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }
}