package nc.pubitf.so.m30.ic.m4480;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * Ԥ����д���۶����ӿڲ����ࡣ
 *
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-06-08 ����09:59:07
 */
public class Rewrite4480Para {
  /** ���۶�������id */
  private String csaleorderbid;

  /** Ԥ���������仯���� */
  private UFDouble nreqrsnum;

  /**
   * Rewrite4480Para �Ĺ�����
   *
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nreqrsnum Ԥ�������仯��
   */
  public Rewrite4480Para(String csaleorderbid, UFDouble nreqrsnum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nreqrsnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0211")/*@res "Ԥ����������Ϊ�ա�"*/);
    }
    this.nreqrsnum = nreqrsnum;

  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNreqrsnum() {
    return this.nreqrsnum;
  }

}