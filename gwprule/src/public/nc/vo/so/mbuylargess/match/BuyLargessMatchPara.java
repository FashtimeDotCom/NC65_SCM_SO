package nc.vo.so.mbuylargess.match;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ����ƥ���㷨ʹ�õ��ڲ�������
 * 
 * @since 6.1
 * @version 2012-10-30 18:08:28
 * @author ��ӱ�
 */
public class BuyLargessMatchPara extends CircularlyAccessibleValueObject {

  /**
   * ����
   */
  public static final String CASSUNITID = "cassunitid";

  /**
   * ����
   */
  public static final String CCURRTYPEID = "ccurrtypeid";

  /**
   * �ͻ���������
   */
  public static final String CCUSTCLID = "ccustclid";

  /**
   * �ͻ�
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * �ͻ����۷���
   */
  public static final String CCUSTSALECLID = "ccustsaleclid";

  /**
   * �ϼ�����uzuzhi
   */
  public static final String CFATHERORGID = "cfatherorgid";

  /**
   * ���ϻ�������
   */
  public static final String CMARBASECLID = "cmarbaseclid";

  /**
   * �������۷���
   */
  public static final String CMARSALECLID = "cmarsaleclid";

  /**
   * ����
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * ������֯
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ��������
   */
  public static final String NBILLNUM = "nbillnum";

  /**
   * ��ֵ
   */
  public static final String NULLVALUE = "~";

  /**
   * ���к�
   */
  public static final String PARAINDEX = "paraindex";

  private static final long serialVersionUID = 2755457401914203737L;

  private Map<String, Object> mapTemp = new HashMap<String, Object>();

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      BuyLargessMatchPara.CSALEORGID, BuyLargessMatchPara.CMATERIALID,
      BuyLargessMatchPara.CMARBASECLID, BuyLargessMatchPara.CMARSALECLID,
      BuyLargessMatchPara.CCUSTOMERID, BuyLargessMatchPara.CCUSTCLID,
      BuyLargessMatchPara.CCUSTSALECLID, BuyLargessMatchPara.CASSUNITID,
      BuyLargessMatchPara.CCURRTYPEID, BuyLargessMatchPara.NBILLNUM,
      BuyLargessMatchPara.DBILLDATE, BuyLargessMatchPara.PARAINDEX
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.mapTemp.get(attributeName);
  }

  /**
   * ��õ�λ
   * 
   * @return assunitid
   */
  public String getCassunitid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CASSUNITID);
  }

  /**
   * ��ñ���
   * 
   * @return currtypeid
   */
  public String getCcurrtypeid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCURRTYPEID);
  }

  /**
   * ��ÿͻ���������
   * 
   * @return custclid
   */
  public String getCcustclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCUSTCLID);
  }

  /**
   * ��ÿͻ�
   * 
   * @return customerid
   */
  public String getCcustomerid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCUSTOMERID);
  }

  /**
   * ��ÿͻ����۷���
   * 
   * @return custsaleclid
   */
  public String getCcustsaleclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCUSTSALECLID);
  }

  /**
   * ����ϼ�������֯
   * 
   * @return fatherorgid
   */
  public String getCfatherorgid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CFATHERORGID);
  }

  /**
   * ������ϻ�������
   * 
   * @return marbaseclid
   */
  public String getCmarbaseclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CMARBASECLID);
  }

  /**
   * ����������۷���
   * 
   * @return marsaleclid
   */
  public String getCmarsaleclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CMARSALECLID);
  }

  /**
   * �������
   * 
   * @return materialid
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CMATERIALID);
  }

  /**
   * ���������֯
   * 
   * @return saleorgid
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CSALEORGID);
  }

  /**
   * ���ƥ������
   * 
   * @return billdate
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(BuyLargessMatchPara.DBILLDATE);
  }

  @Override
  public String getEntityName() {
    return null;
  }

  /**
   * �����������
   * 
   * @return billnum
   */
  public UFDouble getNbillnum() {
    return (UFDouble) this.getAttributeValue(BuyLargessMatchPara.NBILLNUM);
  }

  /**
   * ������к�
   * 
   * @return Paraindex
   */
  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(BuyLargessMatchPara.PARAINDEX);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    this.mapTemp.put(name, value);
  }

  /**
   * �������ϵ�λ
   * 
   * @param cassunitid
   */
  public void setCassunitid(String cassunitid) {
    this.setAttributeValue(BuyLargessMatchPara.CASSUNITID, cassunitid);
  }

  /**
   * ���ñ���
   * 
   * @param ccurrtypeid
   */
  public void setCcurrtypeid(String ccurrtypeid) {
    this.setAttributeValue(BuyLargessMatchPara.CCURRTYPEID, ccurrtypeid);
  }

  /**
   * ���ÿͻ���������
   * 
   * @param ccustclid
   */
  public void setCcustclid(String ccustclid) {
    this.setAttributeValue(BuyLargessMatchPara.CCUSTCLID, ccustclid);
  }

  /**
   * ���ÿͻ�
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    this.setAttributeValue(BuyLargessMatchPara.CCUSTOMERID, ccustomerid);
  }

  /**
   * ���ÿͻ����۷���
   * 
   * @param ccustsaleclid
   */
  public void setCcustsaleclid(String ccustsaleclid) {
    this.setAttributeValue(BuyLargessMatchPara.CCUSTSALECLID, ccustsaleclid);
  }

  /**
   * �����ϼ�������֯
   * 
   * @param cfatherorgid
   */
  public void setCfatherorgid(String cfatherorgid) {
    this.setAttributeValue(BuyLargessMatchPara.CFATHERORGID, cfatherorgid);
  }

  /**
   * �������ϻ�������
   * 
   * @param cmarbaseclid
   */
  public void setCmarbaseclid(String cmarbaseclid) {
    this.setAttributeValue(BuyLargessMatchPara.CMARBASECLID, cmarbaseclid);
  }

  /**
   * �����������۷���
   * 
   * @param cmarsaleclid
   */
  public void setCmarsaleclid(String cmarsaleclid) {
    this.setAttributeValue(BuyLargessMatchPara.CMARSALECLID, cmarsaleclid);
  }

  /**
   * ��������
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(BuyLargessMatchPara.CMATERIALID, cmaterialid);
  }

  /**
   * ����������֯
   * 
   * @param csaleorgid
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(BuyLargessMatchPara.CSALEORGID, csaleorgid);
  }

  /**
   * ��������ƥ������
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(BuyLargessMatchPara.DBILLDATE, dbilldate);
  }

  /**
   * ������������
   * 
   * @param nbillnum
   */
  public void setNbillnum(UFDouble nbillnum) {
    this.setAttributeValue(BuyLargessMatchPara.NBILLNUM, nbillnum);
  }

  /**
   * �������к�
   * 
   * @param paraindex
   */
  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(BuyLargessMatchPara.PARAINDEX, paraindex);
  }

  @Override
  public void validate() throws ValidationException {
    StringBuilder errmsg = new StringBuilder();
    if (PubAppTool.isNull(this.getCmaterialid())
        && PubAppTool.isNull(this.getCmarbaseclid())
        && PubAppTool.isNull(this.getCmarsaleclid())) {
      errmsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0",
          "04006003-0027")/* ƥ������ʱ���ϡ����Ϸ��಻��ͬʱΪ��*/);
      errmsg.append("/n");
    }
    if (PubAppTool.isNull(this.getCcustomerid())
        && PubAppTool.isNull(this.getCcustclid())
        && PubAppTool.isNull(this.getCcustsaleclid())) {
      errmsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0",
          "04006003-0028")/* ƥ������ʱ�ͻ����ͻ����಻��ͬʱΪ��*/);
      errmsg.append("/n");
    }
    StringBuilder validateitem = new StringBuilder();
    if (null == this.getCsaleorgid()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006003_0", "04006003-0029")/*������֯��*/);
    }
    if (null == this.getDbilldate()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006003_0", "04006003-0030")/*�������ڡ�*/);
    }
    if (validateitem.length() > 0) {
      errmsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0",
          "04006003-0032")/*ƥ������ʱ������Ŀ������Ϊ�գ�*/);
      validateitem.deleteCharAt(validateitem.length() - 1);
      errmsg.append(validateitem);
    }
    if (errmsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errmsg.toString());
    }

  }

}
