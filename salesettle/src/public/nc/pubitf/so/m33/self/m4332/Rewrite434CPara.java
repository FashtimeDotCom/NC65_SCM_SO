package nc.pubitf.so.m33.self.m4332;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���۷�Ʊ���㵥��д���۳�������㵥�Ļ�д������
 * 
 * @author zhangcheng
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite434CPara {

  /** ���۳�������㵥����id */
  private String csaleorderbid;

  /** ���۳�������㵥�ۼ�����ȷ��Ӧ�ս��仯���� */
  private UFDouble narmny;

  /** ���۳�������㵥�ۼ�����ȷ��Ӧ���������仯���� */
  private UFDouble narnum;

  public Rewrite434CPara(String csaleorderbidt, UFDouble narnumt) {
    if (PubAppTool.isNull(csaleorderbidt)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0065")/*@res "Ҫ��д���۳�������㵥�����е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbidt;

    if (narnumt == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0066")/*@res "�ۼ�����ȷ��Ӧ����������Ϊ�ա�"*/);
    }
    this.narnum = narnumt;
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
