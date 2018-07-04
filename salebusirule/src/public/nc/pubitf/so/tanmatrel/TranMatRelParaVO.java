package nc.pubitf.so.tanmatrel;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class TranMatRelParaVO extends CircularlyAccessibleValueObject {
  // ��������
  public static final String ALLOWSALE = "allowsale";

  // �����¼�
  public static final String APPLYLOWER = "applylower";

  /** ���۶��������к� */
  public static final String CROWNO = "crowno";

  // ������
  public static final String EXCLUDE = "exclude";

  public static final String PARAINDEX = "paraindex";
  
  //������
  public static final String CPRIORITYCODE = "cprioritycode";

  // ����������֯
  public static final String PK_FATHERORG = "pk_fatherorg";

  // ����
  public static final String PK_MATERIAL = "pk_material";

  // ���ϻ�������
  public static final String PK_MATERIALBASECLASS = "pk_materialbaseclass";

  // �������۷���
  public static final String PK_MATERIALSALECLASS = "pk_materialsaleclass";

  // ������֯
  public static final String PK_ORG = "pk_org";

  // ��ʵ������
  public static final String PK_TRANMATREL_B = "pk_tranmatrel_b";

  // ��������
  public static final String TRANTYPE = "trantype";

  private static final long serialVersionUID = -8279083368487381072L;

  private Map<String, Object> mapTemp;

  public TranMatRelParaVO() {
    super();
    this.mapTemp = new HashMap<String, Object>();
  }

  public Integer getAllowsale() {
    return (Integer) this.getAttributeValue(TranMatRelParaVO.ALLOWSALE);
  }

  public UFBoolean getApplylower() {
    return (UFBoolean) this.getAttributeValue(TranMatRelParaVO.APPLYLOWER);
  }

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      TranMatRelParaVO.PK_ORG, TranMatRelParaVO.PK_MATERIAL,
      TranMatRelParaVO.PK_TRANMATREL_B, TranMatRelParaVO.TRANTYPE,
      TranMatRelParaVO.ALLOWSALE, TranMatRelParaVO.EXCLUDE,
      TranMatRelParaVO.PARAINDEX,TranMatRelParaVO.CPRIORITYCODE, TranMatRelParaVO.CROWNO,
      TranMatRelParaVO.PK_MATERIALBASECLASS,
      TranMatRelParaVO.PK_MATERIALSALECLASS, TranMatRelParaVO.APPLYLOWER,
      TranMatRelParaVO.PK_FATHERORG
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.mapTemp.get(attributeName);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(TranMatRelParaVO.CROWNO);
  }

  @Override
  public String getEntityName() {
    return null;
  }

  public UFBoolean getExclude() {
    return (UFBoolean) this.getAttributeValue(TranMatRelParaVO.EXCLUDE);
  }

  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(TranMatRelParaVO.PARAINDEX);
  }

  public String getCprioritycode() {
    return (String) this.getAttributeValue(TranMatRelParaVO.CPRIORITYCODE);
  }
  
  public String getPk_fatherorg() {
    return (String) this.getAttributeValue(TranMatRelParaVO.PK_FATHERORG);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(TranMatRelParaVO.PK_MATERIAL);
  }

  public String getPk_materialbaseclass() {
    return (String) this
        .getAttributeValue(TranMatRelParaVO.PK_MATERIALBASECLASS);
  }

  public String getPk_materialsaleclass() {
    return (String) this
        .getAttributeValue(TranMatRelParaVO.PK_MATERIALSALECLASS);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(TranMatRelParaVO.PK_ORG);
  }

  public String getPk_tranmatrel_b() {
    return (String) this.getAttributeValue(TranMatRelParaVO.PK_TRANMATREL_B);
  }

  public String getTrantype() {
    return (String) this.getAttributeValue(TranMatRelParaVO.TRANTYPE);
  }

  public void setAllowsale(Integer allowsale) {
    this.setAttributeValue(TranMatRelParaVO.ALLOWSALE, allowsale);
  }

  public void setApplylower(UFBoolean applylower) {
    this.setAttributeValue(TranMatRelParaVO.APPLYLOWER, applylower);
  }

  @Override
  public void setAttributeValue(String attributeName, Object attributeValue) {
    this.mapTemp.put(attributeName, attributeValue);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(TranMatRelParaVO.CROWNO, crowno);
  }

  public void setExclude(UFBoolean exclude) {
    this.setAttributeValue(TranMatRelParaVO.EXCLUDE, exclude);
  }

  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(TranMatRelParaVO.PARAINDEX, paraindex);
  }

  public void setCprioritycode(String code) {
    this.setAttributeValue(TranMatRelParaVO.CPRIORITYCODE, code);
  }
  
  public void setPk_fatherorg(String pk_fatherorg) {
    this.setAttributeValue(TranMatRelParaVO.PK_FATHERORG, pk_fatherorg);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(TranMatRelParaVO.PK_MATERIAL, pk_material);
  }

  public void setPk_materialbaseclass(String pk_materialbaseclass) {
    this.setAttributeValue(TranMatRelParaVO.PK_MATERIALBASECLASS,
        pk_materialbaseclass);
  }

  public void setPk_materialsaleclass(String pk_materialsaleclass) {
    this.setAttributeValue(TranMatRelParaVO.PK_MATERIALSALECLASS,
        pk_materialsaleclass);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(TranMatRelParaVO.PK_ORG, pk_org);
  }

  public void setPk_tranmatrel_b(String pk_tranmatrel_b) {
    this.setAttributeValue(TranMatRelParaVO.PK_TRANMATREL_B, pk_tranmatrel_b);
  }

  public void setTrantype(String trantype) {
    this.setAttributeValue(TranMatRelParaVO.TRANTYPE, trantype);
  }

  @Override
  public void validate() throws ValidationException {
    StringBuilder validateitem = new StringBuilder();
    if (null == this.getPk_org()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0024")/* ������֯*/);
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*��*/);
    }
    if (PubAppTool.isNull(this.getPk_material())) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0026")/* ���� */);
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*��*/);
    }
    if (PubAppTool.isNull(this.getTrantype())) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0029")/* ��������*/);
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*��*/);
    }
    if (validateitem.length() > 0) {
      validateitem.deleteCharAt(validateitem.length() - 1);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0008")/*@res "�ͻ����Ϲ�ϵ���ʱ��������Ŀ������Ϊ�գ�"*/
          + validateitem.toString());
    }
  }
}