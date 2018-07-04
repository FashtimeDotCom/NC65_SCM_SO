package nc.pubitf.so.m30arrange;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ����/�������ε��ݻ�д���۶����Ļ�дͳһ�����ࣺ
 * <P>
 * �빺�����ɹ��������������롢����������ί�ⶩ���������������ƻ�����
 * </p>
 * 
 * @author ��־ΰ
 * @modifier ������
 * @since 6.0
 * @time 2011-12-12 10:05
 */
public class Rewrite30ArrangePara implements Serializable {
  private static final long serialVersionUID = -4853816405706383644L;

  /** �������� */
  private String billtype;

  /** ����Դ������ */
  private String carrangepersonid;

  /** ���۶�������id */
  private String csaleorderbid;

  /** �����������仯���� */
  private UFDouble nnum;

  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * Rewrite30ArrangePara �Ĺ�����
   * 
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nnum �����������仯����
   * @param carrangepersonid ������
   * @param billtype ��������
   */
  public Rewrite30ArrangePara(String csaleorderbid, UFDouble nnum,
      String carrangepersonid, String billtype) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0208")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nnum == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0224")/*@res "��д�İ�����������Ϊ�ա�"*/);
    }
    this.nnum = nnum;

    if (PubAppTool.isNull(carrangepersonid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0225")/*@res "���۶�����Դ�����˲���Ϊ�ա�"*/);
    }
    this.carrangepersonid = carrangepersonid;

    if (PubAppTool.isNull(billtype)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0226")/*@res "���ε������Ͳ���Ϊ��"*/);
    }
    this.billtype = billtype;
  }

  public String getBilltype() {
    return this.billtype;
  }

  public String getCarrangepersonid() {
    return this.carrangepersonid;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

}
