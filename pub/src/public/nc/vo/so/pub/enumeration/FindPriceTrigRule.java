package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.pub.SOItemKey;

/**
 * SO21 ����ѯ�۴�������
 * 
 * @since 6.0
 * @version 2011-6-14 ����10:03:18
 * @author fengjb
 */
public enum FindPriceTrigRule {

  CMATERIALVID(SOItemKey.CMATERIALVID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0208")/*����*/),
  CQUALITYLEVELID(SOItemKey.CQUALITYLEVELID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0209")/*�����ȼ�*/),
  CCHANNELTYPEID(SOItemKey.CCHANNELTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0175")/*��������*/),
  CRECEIVEAREAID(SOItemKey.CRECEIVEAREAID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0050")/*�ջ�����*/),
  CCUSTOMERID(SOItemKey.CCUSTOMERID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0048")/*�ͻ�*/),
  CBALANCETYPEID(SOItemKey.CBALANCETYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0210")/*���㷽ʽ*/),
  CORIGCURRENCYID(SOItemKey.CORIGCURRENCYID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0043")/*����*/),
  CQTUNITID(SOItemKey.CQTUNITID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0211")/*������λ*/),
  NQTUNITNUM(SOItemKey.NQTUNITNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0116")/*����*/),
  CTRANSPORTTYPEID(SOItemKey.CTRANSPORTTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0113")/*���䷽ʽ*/),
  CTRANTYPEID(SOItemKey.CTRANTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0174")/*���۶�������*/),
  DBILLDATE(SOItemKey.DBILLDATE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0212")/*��������*/),
  VFREE1(SOItemKey.VFREE1, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0157")/*���ɸ�������1*/),
  VFREE2(SOItemKey.VFREE2, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0159")/*���ɸ�������2*/),
  VFREE3(SOItemKey.VFREE3, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0160")/*���ɸ�������3*/),
  VFREE4(SOItemKey.VFREE4, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0161")/*���ɸ�������4*/),
  VFREE5(SOItemKey.VFREE5, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0162")/*���ɸ�������5*/),
  VFREE6(SOItemKey.VFREE6, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0163")/*���ɸ�������6*/),
  VFREE7(SOItemKey.VFREE7, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0164")/*���ɸ�������7*/),
  VFREE8(SOItemKey.VFREE8, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0165")/*���ɸ�������8*/),
  VFREE9(SOItemKey.VFREE9, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0166")/*���ɸ�������9*/),
  VFREE10(SOItemKey.VFREE10, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0158")/*���ɸ�������10*/);
  // ��������
  private String key;

  // ��������
  private String name;

  private FindPriceTrigRule(String key, String name) {
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
