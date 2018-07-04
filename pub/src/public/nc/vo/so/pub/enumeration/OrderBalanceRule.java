package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.pub.SOItemKey;

/**
 * �����տ��������
 * 
 * @since 6.0
 * @version 2011-1-6 ����06:46:36
 * @author ף����
 */

public enum OrderBalanceRule {
  CARORGID(SOItemKey.CARORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0041")/*Ӧ����֯*/),
  CORIGCURRENCYID(SOItemKey.CORIGCURRENCYID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0043")/*����*/),
  CINVOICECUSTID(SOItemKey.CINVOICECUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0044")/*��Ʊ�ͻ�*/),
  PK_ORG(SOItemKey.PK_ORG, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0106")/*������֯*/),
  VTRANTYPECODE(SOItemKey.VTRANTYPECODE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0174")/*���۶�������*/),
  CCUSTOMERID(SOItemKey.CCUSTOMERID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0048")/*�ͻ�*/),
  CDEPTID(SOItemKey.CDEPTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0088")/*���۲���*/),
  CEMPLOYEEID(SOItemKey.CEMPLOYEEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0089")/*����ҵ��Ա*/),
  CCHANNELTYPEID(SOItemKey.CCHANNELTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0175")/*��������*/),
  CSETTLEORGID(SOItemKey.CSETTLEORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0176")/*���������֯*/),
  CPRODLINEID(SOItemKey.CPRODLINEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0098")/*��Ʒ��*/);
  // ��������
  private String key;

  // ��������
  private String name;

  private OrderBalanceRule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

}
