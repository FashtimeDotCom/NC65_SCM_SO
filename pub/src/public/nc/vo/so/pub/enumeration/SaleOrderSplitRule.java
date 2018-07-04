package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.pub.SOItemKey;

/**
 * ���۶����ֵ���ӡ����
 * 
 * @since 6.0
 * @version 2011-1-6 ����06:46:36
 * @author ף����
 */

public enum SaleOrderSplitRule {
  CRECEIVEADDDOCID(SOItemKey.CRECEIVEADDDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0049")/*�ջ��ص�*/),
  CRECEIVEAREAID(SOItemKey.CRECEIVEAREAID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0050")/*�ջ�����*/),
  CRECEIVECUSTID(SOItemKey.CRECEIVECUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*�ջ��ͻ�*/),
  CSENDSTOCKORGID(SOItemKey.CSENDSTOCKORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0052")/*���������֯*/),
  CSENDSTORDOCID(SOItemKey.CSENDSTORDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0053")/*�����ֿ�*/),
  DSENDDATE(SOItemKey.DSENDDATE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0059")/*��������*/), CROWNO(
			SOItemKey.CROWNO, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0240")/*�к�*/);
  // ��������
  private String key;

  // ��������
  private String name;

  private SaleOrderSplitRule(String key, String name) {
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
