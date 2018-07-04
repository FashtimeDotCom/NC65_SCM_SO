package nc.pubitf.so.m30.ic.m4c;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���۳��ⵥ��д���۶����Ļ�д������
 *
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite4CPara {

  /** �Ƿ�ǿ���г���رգ�Ĭ��false */
  private UFBoolean bclosed;

  /** �Ƿ��˻س��ⵥ(���˻����ⵥ)��Ĭ��false */
  private UFBoolean breturnfalg;

  /** ���۶�������id */
  private String csaleorderbid;

  /** ���۳���ʵ���������仯���� */
  private UFDouble nchangenum;

  /** ���۳���Ӧ���������仯���� */
  private UFDouble nchangenotoutnum;

  /**
   * Rewrite4CPara �Ĺ�����
   *
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nchangenotoutnum ���۳���Ӧ�������仯��
   * @param nchangenum ���۳���ʵ�������仯��
   * @param bclosed �Ƿ�ǿ���г���ر�
   * @param bclosed �Ƿ��˻س��ⵥ
   */
  public Rewrite4CPara(String csaleorderbid, UFDouble nchangenotoutnum,
      UFDouble nchangenum, UFBoolean bclosed, UFBoolean breturnfalg) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nchangenum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0212")/*@res "���۳���ʵ����������Ϊ�ա�"*/);
    }
    this.nchangenum = nchangenum;

    if (nchangenotoutnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0213")/*@res "���۳���Ӧ����������Ϊ�ա�"*/);
    }
    this.nchangenotoutnum = nchangenotoutnum;

    this.bclosed = (bclosed == null) ? UFBoolean.FALSE : bclosed;

    this.breturnfalg = (breturnfalg == null) ? UFBoolean.FALSE : breturnfalg;
  }

  public UFBoolean getBclosed() {
    return this.bclosed;
  }

  public UFBoolean getBreturnfalg() {
    return this.breturnfalg;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

  public UFDouble getNchangenotoutnum() {
    return this.nchangenotoutnum;
  }
}