package nc.pubitf.so.m4310.crm;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * CRM��ѯ��������
 * 
 * @since 6.3.1
 * @version 2013-08-06 08:44:04
 * @author ���Ʒ�
 */
public class CRMQueryPara implements Serializable {

  private static final long serialVersionUID = -4178285724050894132L;

  /**
   * �ͻ�ID
   */
  private String customerid;

  /**
   * �̻�ID
   */
  private String busid;

  /**
   * ��ʼ��������
   */
  private UFDate dfromdate;

  /**
   * ������������
   */
  private UFDate dtodate;

  /**
   * ��ʼ����
   */
  private int nstartcount;

  /**
   * ��������
   */
  private int nendcount;

  /**
   * �����������������ֵ
   * 
   * @param customerid �ͻ�ID
   * @param busid �̻�ID
   * @param dfromdate ��ʼ����
   * @param dtodate ��������
   * @param nstartcount ��ʼ����
   * @param nendcount ��������
   * 
   */
  public CRMQueryPara(String customerid, String busid, UFDate dfromdate,
      UFDate dtodate, int nstartcount, int nendcount) {
    this.customerid = customerid;
    this.busid = busid;
    this.dfromdate = dfromdate;
    this.dtodate = dtodate;
    this.nstartcount = nstartcount;
    this.nendcount = nendcount;

    this.validate();
  }

  /**
   * ��ÿͻ�ID
   * 
   * @return �ͻ�ID
   */
  public String getCustomerid() {
    return this.customerid;
  }

  /**
   * ����̻�ID
   * 
   * @return �̻�ID
   */
  public String getBusid() {
    return this.busid;
  }

  /**
   * ��ÿ�ʼ����
   * 
   * @return ��ʼ����
   */
  public UFDate getDfromdate() {
    return this.dfromdate;
  }

  /**
   * ��ý�������
   * 
   * @return ��������
   */
  public UFDate getDtodate() {
    return this.dtodate;
  }

  /**
   * ��ÿ�ʼ����
   * 
   * @return ��ʼ����
   */
  public int getNstartcount() {
    return this.nstartcount;
  }

  /**
   * ��ý�������
   * 
   * @return ��������
   */
  public int getNendcount() {
    return this.nendcount;
  }

  /**
   * У�������׼ȷ��
   * 
   * @param CRM��ѯ����
   */
  private void validate() {

    if (PubAppTool.isNull(this.getDfromdate().toString())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0080")/* @res
                                                       * "��ѯ��ʼ���ڲ���Ϊ�� !" */);
    }
    else if (PubAppTool.isNull(this.getDtodate().toString())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0081")/* @res
                                                       * "��ѯ�������ڲ���Ϊ��!" */);
    }
    else if (this.getNstartcount() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0082")/* @res
                                                       * "��ѯ��ʼ��������С��1!" */);
    }
    else if (PubAppTool.isNull(this.getCustomerid())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0083")/* @res
                                                       * "�ͻ�ID����Ϊ��!" */);
    }
    else if (this.getNstartcount() > this.getNendcount()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0084")/* @res
                                                       * "������������С�ڿ�ʼ����!" */);
    }
    else if (this.getNendcount() - this.getNstartcount() > 499) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0085")/* @res
                                                       * "�����������ֻ����500��!" */);
    }
    else if (this.getDtodate().before(this.getDfromdate())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0086")/* @res
                                                       * "�������ڲ������ڿ�ʼ����!" */);
    }
  }
}
