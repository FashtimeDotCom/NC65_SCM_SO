package nc.pubitf.so.m30.it.m5801;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ���ں�ͬ��д���ε��ݲ���
 * 
 * @since JCK 6.31
 * @version 2014-03-19 14:35:01
 * @author zhangyfr
 */
public class Rewrite5801Para {

  /** ���۶�������id */
  private String csaleorderbid;

  /** ���ں�ͬ�������仯���� */
  private UFDouble nchangenum;

  /**
   * Rewrite5801Para ������
   * 
   * @param csaleorderbid ���۶�������id
   * @param nchangenum ���ں�ͬ�������仯����
   */
  public Rewrite5801Para(String csaleorderbid, UFDouble nchangenum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0481")/*@res "Ҫ��д���۶��������е�id����Ϊ�ա�"*/);
    }
    this.csaleorderbid = csaleorderbid;
    if (null == nchangenum) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0482")/*@res "���ں�ͬ��������Ϊ�ա�"*/);
    }
    this.nchangenum = nchangenum;
  }

  /**
   * ���Ҫ��д���۶��������е�id
   * 
   * @return Ҫ��д���۶��������е�id
   */
  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  /**
   * ��ý��ں�ͬ����
   * 
   * @return ���ں�ͬ����
   */
  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

}
