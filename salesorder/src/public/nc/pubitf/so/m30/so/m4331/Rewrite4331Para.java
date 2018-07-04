package nc.pubitf.so.m30.so.m4331;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ��������д���ε��ݲ���
 * 
 * @since 6.1
 * @version 2013-05-20 15:58:46
 * @author yixl
 */
public class Rewrite4331Para {

  /** �Ƿ�ǿ���з����رգ�ȡ��������bclosesrcflag��ǣ���Ĭ��false������������ */
  private UFBoolean bclosed = UFBoolean.FALSE;

  /** ���۶�������id */
  private String csaleorderbid;

  /** �����������仯���� */
  private UFDouble nchangenum;

  /**
   * ����ر�״̬
   */
  private UFBoolean bboutendflag;

  /**
   * Rewrite4331Para �Ĺ�����
   * 
   * @param csaleorderbid Ҫ��д���۶��������е�id
   * @param nchangenum ���������仯��
   * @param bclosed
   * @param bboutendflag ����ر�
   */
  public Rewrite4331Para(String csaleorderbid, UFDouble nchangenum,
      UFBoolean bclosed, UFBoolean bboutendflag) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0219")/*@res "Ҫ��д�����������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nchangenum == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0220")/*@res "������������Ϊ�ա�"*/);
    }
    this.nchangenum = nchangenum;

    if (bclosed != null) {
      this.bclosed = bclosed;
    }

    this.bboutendflag = bboutendflag;
  }

  /**
   * �Ƿ�ǿ���з����ر�
   * 
   * @return UFBoolean
   */
  public UFBoolean getBclosed() {
    return this.bclosed;
  }

  /**
   * ��ö�������ID
   * 
   * @return ���۶�������ID
   */
  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  /**
   * ��÷�������
   * 
   * @return ��������
   */
  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

  /**
   * ��ó���ر�
   * 
   * @return ����ر�
   */
  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

}
