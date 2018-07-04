package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * �������ֵ���ӡ����
 * 
 * @since 6.0
 * @version 2011-1-6 ����06:46:36
 * @author ף����
 */
public enum DeliverySplitRule {

  CINSTOCKORGID(DeliveryBVO.CINSTOCKORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0046")/*�ջ������֯*/),
  CINSTORDOCID(DeliveryBVO.CINSTORDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0047")/*�ջ��ֿ�*/),
  CORDERCUSTID(DeliveryBVO.CORDERCUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0048")/*�ͻ�*/),
  CRECEIVEADDDOCID(DeliveryBVO.CRECEIVEADDDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0049")/*�ջ��ص�*/),
  CRECEIVEAREAID(DeliveryBVO.CRECEIVEAREAID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0050")/*�ջ�����*/),
  CRECEIVECUSTID(DeliveryBVO.CRECEIVECUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*�ջ��ͻ�*/),
  CSENDSTOCKORGID(DeliveryBVO.CSENDSTOCKORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0052")/*���������֯*/),
  CSENDSTORDOCID(DeliveryBVO.CSENDSTORDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0053")/*�����ֿ�*/),
  CSUPERCARGOID(DeliveryBVO.CSUPERCARGOID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0054")/*Ѻ��Ա*/),
  CTRANSCUSTID(DeliveryBVO.CTRANSCUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0055")/*������*/),
  CVEHICLEID(DeliveryBVO.CVEHICLEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0056")/*����*/),
  CVEHICLETYPEID(DeliveryBVO.CVEHICLETYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0057")/*����*/),
  VBILLCODE(DeliveryHVO.VBILLCODE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0058")/*��������*/),
  DSENDDATE(DeliveryBVO.DSENDDATE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0059")/*��������*/),
  CTRANTYPEID(DeliveryHVO.CTRANTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0060")/*��������*/),
  CROWNO(DeliveryBVO.CROWNO, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0240")/*�к�*/);
  // ��������
  private String key;

  // ��������
  private String name;

  private DeliverySplitRule(String key, String name) {
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
