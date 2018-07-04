package nc.pubitf.so.m30;

import java.util.HashMap;
import java.util.Map;

import nc.vo.bank_cvp.compile.datastruct.CI;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class ReturnAssignMatchVO extends CircularlyAccessibleValueObject
    implements CI {

  /**
   * ��Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * Դͷ��������
   */
  public static final String CFIRSTID = "cfirstid";

  /**
   * �˻�ԭ��
   */
  public static final String CRETREASONID = "cretreasonid";
  
  /**
   * �˻�����
   */
  public static final String CRETPOLICYID = "cretpolicyid";

  /** ���۶��������к� */
  public static final String CROWNO = "crowno";

  /**
   * ���۶�������
   */
  public static final String CSALEORDERBID = "csaleorderbid";

  /** ���۶�����Դ�������� */
  public static final String CSRCID = "csrcid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ������
   */
  public static final String NNUM = "nnum";

  /**
   * ��˰�ϼ�
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  public static final String PARAINDEX = "paraindex";

  /** �ͻ������������ */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /** �ͻ����� */
  public static final String PK_CUSTOMER = "pk_customer";

  /** �ͻ����۷������ */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /** ���ϻ���������� */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /** �������۷������ */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /** ���ϱ��� */
  public static final String PK_MATERIAL = "pk_material";

  /** ��Ʒ�߱��� */
  public static final String PK_PRODUCTLINE = "pk_productline";

  /** �˻����߷������� */
  public static final String PK_RETURNASSIGN = "pk_returnassign";

  /** �˻������������� */
  public static final String PK_RETURNPOLICY = "pk_returnpolicy";

  /** ������֯ */
  public static final String PK_SALEORG = "pk_saleorg";
  
  /** ����*/
  public static final String PK_GROUP="pk_group";

  /**
   * Դͷ��������
   */
  public static final String VFIRSTTYPE = "vfirsttype";

  /**
   * ��Դ��������
   */
  public static final String VSRCTYPE = "vsrctype";

  private static final long serialVersionUID = 2755457401914203737L;

  private Map<String, Object> mapTemp;

  public ReturnAssignMatchVO() {
    this.mapTemp = new HashMap<String, Object>();
  }

  @Override
  public String getAlias() {
    return "para";
  }

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      ReturnAssignMatchVO.PK_SALEORG, ReturnAssignMatchVO.PK_MATERIAL,
      ReturnAssignMatchVO.PK_MARBASCLASS, ReturnAssignMatchVO.PK_MARSALECLASS,
      ReturnAssignMatchVO.PK_CUSTOMER, ReturnAssignMatchVO.PK_CUSTCLASS,
      ReturnAssignMatchVO.PK_CUSTSALECLASS, ReturnAssignMatchVO.PK_PRODUCTLINE,
      ReturnAssignMatchVO.PARAINDEX, ReturnAssignMatchVO.CROWNO,
      ReturnAssignMatchVO.PK_RETURNASSIGN, ReturnAssignMatchVO.CFIRSTID,
      ReturnAssignMatchVO.CSALEORDERBID, ReturnAssignMatchVO.CSRCID,
      ReturnAssignMatchVO.DBILLDATE, ReturnAssignMatchVO.VFIRSTTYPE,
      ReturnAssignMatchVO.VSRCTYPE, ReturnAssignMatchVO.CRETREASONID,ReturnAssignMatchVO.CRETPOLICYID,
      ReturnAssignMatchVO.NORIGTAXMNY, ReturnAssignMatchVO.NNUM,
      ReturnAssignMatchVO.BLARGESSFLAG,ReturnAssignMatchVO.PK_GROUP
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.mapTemp.get(attributeName);
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(ReturnAssignMatchVO.BLARGESSFLAG);
  }

  public String getCfirstid() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.CFIRSTID);
  }
  
  public String getCretreasonid() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.CRETREASONID);
  }
  
  public String getCretpolicyid() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.CRETPOLICYID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.CROWNO);
  }

  public String getCsaleorderbid() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.CSALEORDERBID);
  }

  public String getCsrcid() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.CSRCID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ReturnAssignMatchVO.DBILLDATE);
  }

  @Override
  public String getEntityName() {
    return null;
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ReturnAssignMatchVO.NNUM);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(ReturnAssignMatchVO.NORIGTAXMNY);
  }

  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(ReturnAssignMatchVO.PARAINDEX);
  }

  public String getPk_custclass() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_CUSTCLASS);
  }

  public String getPk_customer() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_CUSTOMER);
  }

  public String getPk_custsaleclass() {
    return (String) this
        .getAttributeValue(ReturnAssignMatchVO.PK_CUSTSALECLASS);
  }

  public String getPk_marbasclass() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_MARBASCLASS);
  }

  public String getPk_marsaleclass() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_MARSALECLASS);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_MATERIAL);
  }

  public String getPk_productline() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_PRODUCTLINE);
  }

  public String getPk_returnassign() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_RETURNASSIGN);
  }

  public String getPk_returnpolicy() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_RETURNPOLICY);
  }

  public String getPk_saleorg() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_SALEORG);
  }
  
  public String getPk_group() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.PK_GROUP);
  }

  public String getVfirsttype() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.VFIRSTTYPE);
  }

  public String getVsrctype() {
    return (String) this.getAttributeValue(ReturnAssignMatchVO.VSRCTYPE);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    this.mapTemp.put(name, value);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(ReturnAssignMatchVO.BLARGESSFLAG, blargessflag);
  }

  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(ReturnAssignMatchVO.CFIRSTID, cfirstid);
  }

  public void setCretreasonid(String cretreasonid) {
    this.setAttributeValue(ReturnAssignMatchVO.CRETREASONID, cretreasonid);
  }
  
  public void setCretpolicyid(String cretpolicyid) {
    this.setAttributeValue(ReturnAssignMatchVO.CRETPOLICYID, cretpolicyid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(ReturnAssignMatchVO.CROWNO, crowno);
  }

  public void setCsaleorderbid(String csaleorderbid) {
    this.setAttributeValue(ReturnAssignMatchVO.CSALEORDERBID, csaleorderbid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(ReturnAssignMatchVO.CSRCID, csrcid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(ReturnAssignMatchVO.DBILLDATE, dbilldate);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(ReturnAssignMatchVO.NNUM, nnum);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(ReturnAssignMatchVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(ReturnAssignMatchVO.PARAINDEX, paraindex);
  }

  public void setPk_custclass(String pk_custclass) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_CUSTCLASS, pk_custclass);
  }

  public void setPk_customer(String pk_customer) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_CUSTOMER, pk_customer);
  }

  public void setPk_custsaleclass(String pk_custsaleclass) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_CUSTSALECLASS,
        pk_custsaleclass);
  }

  public void setPk_marbasclass(String pk_marbasclass) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_MARBASCLASS, pk_marbasclass);
  }

  public void setPk_marsaleclass(String pk_marsaleclass) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_MATERIAL, pk_material);
  }

  public void setPk_productline(String pk_productline) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_PRODUCTLINE, pk_productline);
  }

  public void setPk_returnassign(String pk_returnassign) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_RETURNASSIGN, pk_returnassign);
  }

  public void setPk_returnpolicy(String pk_returnpolicy) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_RETURNPOLICY, pk_returnpolicy);
  }

  public void setPk_saleorg(String pk_saleorg) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_SALEORG, pk_saleorg);
  }
  
  public void setPk_group(String pk_group) {
    this.setAttributeValue(ReturnAssignMatchVO.PK_GROUP, pk_group);
  }

  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(ReturnAssignMatchVO.VFIRSTTYPE, vfirsttype);
  }

  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(ReturnAssignMatchVO.VSRCTYPE, vsrctype);
  }

  @Override
  public void validate() throws ValidationException {
    StringBuilder validateitem = new StringBuilder();
    if (PubAppTool.isNull(this.getPk_material())) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006006_0", "04006006-0125")/* ���� ��*/);
    }
    if (PubAppTool.isNull(this.getPk_customer())) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006006_0", "04006006-0126")/* �ͻ�*/);
    }
    if (validateitem.length() > 0) {
      validateitem.deleteCharAt(validateitem.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006006_0", "04006006-0127", null, new String[] {
            validateitem.toString()
          })/*�˻����ʱ������Ŀ������Ϊ�գ�{0}*/);
    }
  }

}
