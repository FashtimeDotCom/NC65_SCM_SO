package nc.bs.so.m32.maintain.rule.insert;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.pubitf.ic.m4c.m32.IParameter4CFor32;

/**
 * ���۷�Ʊ��д���۳��ⵥ�Ĳ���
 * 
 * @since 6.3
 * @version 2012-12-21 ����08:59:50
 * @author yaogj
 */
public class Rewrite4CFor32Para implements IParameter4CFor32 {

  // ���۳��ⵥ����ID
  private String bid;

  // ���۳��ⵥ��ͷID
  private String hid;

  // ��Ʊ����
  private UFDouble nnum;

  // �����ⵥ��Ʊ�ݲ���Ʒ�ʽ
  private String so08;

  // ���۷�Ʊ����ID
  private String voicebid;

  /**
   * ���췽��
   * 
   * @param voicebid ��Ʊ����id
   * @param hid ���ⵥ��ͷid
   * @param bid ���ⵥ����id
   * @param nnum ����
   */
  public Rewrite4CFor32Para(String voicebid, String hid, String bid,
      UFDouble nnum) {

    this.voicebid = voicebid;

    if (PubAppTool.isNull(hid)) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0015")/*@res "Ҫ��д���۳��ⵥ����id����Ϊ�ա�"*/);
    }
    this.hid = hid;
    if (PubAppTool.isNull(bid)) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0016")/*@res "Ҫ��д���۳��ⵥ������id����Ϊ�ա�"*/);
    }
    this.bid = bid;

    if (nnum == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0017")/*@res "���۷�Ʊ��������Ϊ�ա�"*/);
    }
    this.nnum = nnum;
  }

  @Override
  public String getBid() {
    return this.bid;
  }

  @Override
  public String getHid() {
    return this.hid;
  }

  @Override
  public UFDouble getNinnum() {
    return this.nnum;
  }

  @Override
  public String getSO08() {
    return this.so08;
  }

  /**
   * 
   * @return ��Ʊ����id
   */
  public String getVoicebid() {
    return this.voicebid;
  }

  /**
   * 
   * @param newso08 �����ݲ����
   */
  public void setSO08(String newso08) {
    this.so08 = newso08;
  }

}
