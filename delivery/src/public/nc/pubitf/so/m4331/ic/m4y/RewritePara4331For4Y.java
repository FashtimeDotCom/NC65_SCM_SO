package nc.pubitf.so.m4331.ic.m4y;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <li>���������ε������ⵥ��д�������ۼƳ�������
 *
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ף����
 * @time 2010-3-23 ����06:36:26
 */
public class RewritePara4331For4Y {

  /** ����������id */
  private String cdeliverybid;

  /** �����������仯���� */
  private UFDouble outnum;

  /** Ӧ��δ���������仯�� */
  private UFDouble noOutnum;

  /**
   * sendnum �Ĺ�����
   *
   * @param csaleorderbid
   *          Ҫ��д���۶��������е�id
   * @param sendnum
   *          ���������仯��
   */
  public RewritePara4331For4Y(String cdeliverybid, UFDouble outnum,
      UFDouble noOutnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.outnum = outnum;
    this.noOutnum = noOutnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getOutnum() {
    return this.outnum;
  }

  public UFDouble getNoOutnum() {
    return this.noOutnum;
  }

}