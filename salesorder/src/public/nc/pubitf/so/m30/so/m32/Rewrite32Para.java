package nc.pubitf.so.m30.so.m32;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���۷�Ʊ��д���۶����Ļ�д������
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite32Para {

  /** ���۶�������id */
  private String csaleorderbid;

  /** ���۷�Ʊ�������仯���� */
  private UFDouble nchangenum;

  /**
   * Rewrite32Para �Ĺ�����
   * 
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nchangenum ���۳���Ӧ�������仯��
   */
  public Rewrite32Para(String csaleorderbid, UFDouble nchangenum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    /**
     * ���У�鲻��Ҫ�� �ۿ�����������null
     * */
    // if (nchangenum == null) {
    // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0216")/*@res
    // "��д���ۿ�Ʊ��������Ϊ�ա�"*/);
    // }
    this.nchangenum = nchangenum;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

}
