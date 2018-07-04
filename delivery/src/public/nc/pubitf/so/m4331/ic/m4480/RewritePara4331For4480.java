package nc.pubitf.so.m4331.ic.m4480;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * Ԥ����д������������
 *
 * @since 6.0
 * @version 2011-3-25 ����03:52:57
 * @author ף����
 */
public class RewritePara4331For4480 {
  /** ���۶�������id */
  private String cdeliverybid;

  /** Ԥ���������仯���� */
  private UFDouble nreqrsnum;

  public RewritePara4331For4480(String cdeliverybid, UFDouble nreqrsnum) {
    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    if (nreqrsnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0103")/*@res "Ԥ����������Ϊ�ա�"*/);
    }
    this.nreqrsnum = nreqrsnum;
  }

  public String getCdeliverBid() {
    return this.cdeliverybid;
  }

  public UFDouble getNreqrsnum() {
    return this.nreqrsnum;
  }
}