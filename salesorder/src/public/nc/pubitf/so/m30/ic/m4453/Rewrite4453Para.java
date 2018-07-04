package nc.pubitf.so.m30.ic.m4453;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ;�𵥻�д���۶�������ӿڲ�����
 *
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-9-7 ����09:44:34
 */
public class Rewrite4453Para {
  /** ���۶�������id */
  private String csaleorderbid;

  /** ǩ���������仯���� */
  private UFDouble nsignnum;

  /** ;���������仯���� */
  private UFDouble ntranslossnum;

  /**
   * Rewrite4453Para �Ĺ�����
   *
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nsignnum ǩ���������仯����
   * @param ntranslossnum ;���������仯����
   */
  public Rewrite4453Para(String csaleorderbid, UFDouble nsignnum,
      UFDouble ntranslossnum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nsignnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0209")/*@res "ǩ����������Ϊ�ա�"*/);
    }
    this.nsignnum = nsignnum;

    if (ntranslossnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0210")/*@res ";����������Ϊ�ա�"*/);
    }
    this.ntranslossnum = ntranslossnum;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNsignnum() {
    return this.nsignnum;
  }

  public UFDouble getNtranslossnum() {
    return this.ntranslossnum;
  }

}