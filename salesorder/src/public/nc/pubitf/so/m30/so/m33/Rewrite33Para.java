package nc.pubitf.so.m30.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���۽����д���۶����Ļ�д������
 *
 * @author zhangcheng
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite33Para {

  /** ���۶�������id */
  private String csaleorderbid;

  /** ���۽�����仯���� */
  private UFDouble narmny;

  /** ���۽����������仯���� */
  private UFDouble narnum;

  public Rewrite33Para(String csaleorderbid, UFDouble narnum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (narnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0217")/*@res "���۽�����������Ϊ�ա�"*/);
    }
    this.narnum = narnum;
  }

  /**
   * narnum �Ĺ�����
   *
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nnum ����Ӧ�ս��������仯��
   */
  public Rewrite33Para(String csaleorderbid, UFDouble narnum, UFDouble narmny) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    this.narnum = narnum;

    if (narmny == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0218")/*@res "���۽������Ϊ�ա�"*/);
    }
    this.narmny = narmny;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNarmny() {
    return this.narmny;
  }

  public UFDouble getNarnum() {
    return this.narnum;
  }

  public void setNarmny(UFDouble narmny) {
    this.narmny = narmny;
  }

  public void setNarnum(UFDouble narnum) {
    this.narnum = narnum;
  }

}