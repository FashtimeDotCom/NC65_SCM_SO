package nc.vo.so.iufo;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.SuperVO;

public class UFOFuncParaVO extends SuperVO {
  private static final long serialVersionUID = -5941234142559413815L;

  // ����
  public static final String PK_GROUP = "pk_group";

  // ����֯ID
  public static final String PK_ORG = "pk_org";

  // ��֯����
  public static final String ORGCODE = "orgcode";

  // �ͻ�ID
  public static final String CCUSTOMERID = "ccustomerid";

  // �ͻ�����
  public static final String CUSTOMERCODE = "customercode";

  // �ͻ���������ID
  public static final String CCUSTBASCLID = "ccustbasclid";

  // �ͻ������������
  public static final String CUSTBASCLCODE = "custbasclcode";

  // ����ID
  public static final String CMATERIALID = "cmaterialid";

  // ���ϱ���
  public static final String MATERIALCODE = "materialcode";

  // ���ϻ�������ID
  public static final String CMARBASCLID = "cmarbasclid";

  // ���ϻ����������
  public static final String MARBASCLCODE = "marbasclcode";

  // ����ID
  public static final String CDEPTID = "cdeptid";

  // ���ű���
  public static final String DEPTCODE = "deptcode";

  // ��ʼ����
  public static final String BEGINDATE = "begindate";

  // ��������
  public static final String ENDDATE = "enddate";

  // ѡ���ֶ�
  public static final String FUNCNAME = "funcname";

  // ģ������
  public static final String ModuleName = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0017")/*���۹���*/;

  // ģ������
  public static final String ModuleDesc = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0017")/*���۹���*/;

  // ��������
  public static final String[] FuncName = new String[] {
    "SOInvoiceSumNum", "SOInvoiceSumMny", "SOInvoiceSumTaxMny"
  };

  // ��������
  public static final String[] FuncDesc = new String[] {
    NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0018")/*���۷�Ʊ��������*/, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0019")/*���۷�Ʊ��˰������*/, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0020")/*���۷�Ʊ��˰�ϼƻ���*/
  };

  // ������ʽ
  public static final String[] FuncForm =
      {
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0021")/*���۷�Ʊ�������ܣ���Ʊ��֯���ͻ����롢�ͻ��������ࡢ���ϱ��롢���ϻ������ࡢ���š���ʼ���ڡ���ֹ���ڣ�\n\n\t��Ʊ��֯��ȡҪ��ѯ��Ʊ��֯�ı���\n\t�ͻ����룺ȡ��ѯ�ͻ��ı���\n\t�ͻ��������ࣺȡ��ѯ�ͻ���������ı�������¼�\n\t���ϱ��룺ȡ��ѯ���ϵı���\n\t���ϻ������ࣺȡ��ѯ���ϻ�������ı�������¼�\n\t���ţ�ȡ��ѯ���ŵı�������¼�\n\t��ʼ���ڣ���ѯ���ڷ�Χ��ʼ����\n\t��ֹ���ڣ���ѯ���ڷ�Χ�յ�����*/,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0022")/*���۷�Ʊ��˰�����ܣ���Ʊ��֯���ͻ����롢�ͻ��������ࡢ���ϱ��롢���ϻ������ࡢ���š���ʼ���ڡ���ֹ���ڣ�\n\n\t��Ʊ��֯��ȡҪ��ѯ��Ʊ��֯�ı���\n\t�ͻ����룺ȡ��ѯ�ͻ��ı���\n\t�ͻ��������ࣺȡ��ѯ�ͻ���������ı�������¼�\n\t���ϱ��룺ȡ��ѯ���ϵı���\n\t���ϻ������ࣺȡ��ѯ���ϻ�������ı�������¼�\n\t���ţ�ȡ��ѯ���ŵı�������¼�\n\t��ʼ���ڣ���ѯ���ڷ�Χ��ʼ����\n\t��ֹ���ڣ���ѯ���ڷ�Χ�յ�����*/,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0023")/*���۷�Ʊ��˰�ϼƻ��ܣ���Ʊ��֯���ͻ����롢�ͻ��������ࡢ���ϱ��롢���ϻ������ࡢ���š���ʼ���ڡ���ֹ���ڣ�\n\n\t��Ʊ��֯��ȡҪ��ѯ��Ʊ��֯�ı���\n\t�ͻ����룺ȡ��ѯ�ͻ��ı���\n\t�ͻ��������ࣺȡ��ѯ�ͻ���������ı�������¼�\n\t���ϱ��룺ȡ��ѯ���ϵı���\n\t���ϻ������ࣺȡ��ѯ���ϻ�������ı�������¼�\n\t���ţ�ȡ��ѯ���ŵı�������¼�\n\t��ʼ���ڣ���ѯ���ڷ�Χ��ʼ����\n\t��ֹ���ڣ���ѯ���ڷ�Χ�յ�����*/
      };

  private Map<String, String[]> mapLower = new HashMap<String, String[]>();

  private Map<String, String> maptempvalue = new HashMap<String, String>();

  public void setPk_group(String pk_group) {
    this.maptempvalue.put(UFOFuncParaVO.PK_GROUP, pk_group);
  }

  public String getPk_group() {
    return this.maptempvalue.get(UFOFuncParaVO.PK_GROUP);
  }

  public void setPk_org(String pk_org) {
    this.maptempvalue.put(UFOFuncParaVO.PK_ORG, pk_org);
  }

  public String getPk_org() {
    return this.maptempvalue.get(UFOFuncParaVO.PK_ORG);

  }

  public void setOrgcode(String orgcode) {
    this.maptempvalue.put(UFOFuncParaVO.ORGCODE, orgcode);
  }

  public String getOrgcode() {
    return this.maptempvalue.get(UFOFuncParaVO.ORGCODE);

  }

  public void setCcustomerid(String ccustomerid) {
    this.maptempvalue.put(UFOFuncParaVO.CCUSTOMERID, ccustomerid);
  }

  public String getCcustomerid() {
    return this.maptempvalue.get(UFOFuncParaVO.CCUSTOMERID);

  }

  public void setCustomercode(String customercode) {
    this.maptempvalue.put(UFOFuncParaVO.CUSTOMERCODE, customercode);
  }

  public String getCustomercode() {
    return this.maptempvalue.get(UFOFuncParaVO.CUSTOMERCODE);

  }

  public void setCcustbasclid(String ccustbasclid) {
    this.maptempvalue.put(UFOFuncParaVO.CCUSTBASCLID, ccustbasclid);
  }

  public String getCcustbasclid() {
    return this.maptempvalue.get(UFOFuncParaVO.CCUSTBASCLID);

  }

  public void setCustbasclcode(String custbasclcode) {
    this.maptempvalue.put(UFOFuncParaVO.CUSTBASCLCODE, custbasclcode);
  }

  public String getCustbasclcode() {
    return this.maptempvalue.get(UFOFuncParaVO.CUSTBASCLCODE);

  }

  public void setCmaterialid(String cmaterialid) {
    this.maptempvalue.put(UFOFuncParaVO.CMATERIALID, cmaterialid);
  }

  public String getCmaterialid() {
    return this.maptempvalue.get(UFOFuncParaVO.CMATERIALID);

  }

  public void setMaterialcode(String materialcode) {
    this.maptempvalue.put(UFOFuncParaVO.MATERIALCODE, materialcode);
  }

  public String getMaterialcode() {
    return this.maptempvalue.get(UFOFuncParaVO.MATERIALCODE);

  }

  public void setCmarbasclid(String cmarbasclid) {
    this.maptempvalue.put(UFOFuncParaVO.CMARBASCLID, cmarbasclid);
  }

  public String getCmarbasclid() {
    return this.maptempvalue.get(UFOFuncParaVO.CMARBASCLID);

  }

  public void setMarbasclcode(String marbasclcode) {
    this.maptempvalue.put(UFOFuncParaVO.MARBASCLCODE, marbasclcode);
  }

  public String getMarbasclcode() {
    return this.maptempvalue.get(UFOFuncParaVO.MARBASCLCODE);

  }

  public void setCdeptid(String cdeptid) {
    this.maptempvalue.put(UFOFuncParaVO.CDEPTID, cdeptid);
  }

  public String getCdeptid() {
    return this.maptempvalue.get(UFOFuncParaVO.CDEPTID);

  }

  public void setDeptcode(String deptcode) {
    this.maptempvalue.put(UFOFuncParaVO.DEPTCODE, deptcode);
  }

  public String getDeptcode() {
    return this.maptempvalue.get(UFOFuncParaVO.DEPTCODE);

  }

  public void setBegindate(String begindate) {
    this.maptempvalue.put(UFOFuncParaVO.BEGINDATE, begindate);
  }

  public String getBegindate() {
    return this.maptempvalue.get(UFOFuncParaVO.BEGINDATE);

  }

  public void setEnddate(String enddate) {
    this.maptempvalue.put(UFOFuncParaVO.ENDDATE, enddate);
  }

  public String getEnddate() {
    return this.maptempvalue.get(UFOFuncParaVO.ENDDATE);
  }

  public void setFuncname(String funcname) {
    this.maptempvalue.put(UFOFuncParaVO.FUNCNAME, funcname);
  }

  public String getFuncname() {
    return this.maptempvalue.get(UFOFuncParaVO.FUNCNAME);

  }

  public void addLowerArray(String key, String[] lowpks) {
    this.mapLower.put(key, lowpks);
  }

  public Map<String, String[]> getLowerMap() {
    return this.mapLower;
  }

  @Override
  public Object getAttributeValue(String key) {
    return this.maptempvalue.get(key);
  }

  @Override
  public void setAttributeValue(String key, Object value) {
    this.maptempvalue.put(key, (String) value);
  }
}
