package nc.ui.so.mreturnpolicy.ref;

import nc.ui.ml.NCLangRes;

/**
 * �˴���������˵���� �������ڣ�(01-8-22 11:31:01)
 * 
 * @author�������� 2004-5-9 ��ɭ�� �޸�bug wsy-begin/wsy-end
 */
public class ReturncndtnRefModel extends nc.ui.bd.ref.AbstractRefModel {
  private java.lang.String pk_group;

  /**
   * AccsubjTypeRefModel ������ע�⡣
   */
  public ReturncndtnRefModel() {
    super();
  }

  /**
   * AccsubjTypeRefModel ������ע�⡣
   */
  public ReturncndtnRefModel(String strPk_Corp) {
    super();
    this.setPk_group(strPk_Corp);
  }

  /**
   * getDefaultFieldCount ����ע�⡣
   */
  @Override
  public int getDefaultFieldCount() {
    return 4;
  }

  /**
   * �������ݿ��ֶ������� �������ڣ�(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String[] getFieldCode() {
    return new String[] {
      "vconditioncode", "vconditionname", "vexpressname", "vexpressdetail"
    };
  }

  /**
   * �����ݿ��ֶ��������Ӧ�������������� �������ڣ�(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String[] getFieldName() {
    return new String[] {
      NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0115")/*�˻���������*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0116")/*�˻���������*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0119")/*�˻��������ʽ����*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0120")/*�˻���������*/
    };
  }

  /**
   * ����ʾ�ֶ��б�
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String[] getHiddenFieldCode() {
    return new String[] {
      "pk_returncndtn"
    };
  }

  /**
   * �˴����뷽��˵���� �������ڣ�(2001-7-10 13:10:42)
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getPk_corp() {
    return this.pk_group;
  }

  /**
   * Ҫ���ص������ֶ���i.e. pk_deptdoc �������ڣ�(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getPkFieldCode() {
    return "pk_returncndtn";
  }

  /**
   * ���ձ��� �������ڣ�(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getRefTitle() {
    return NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0128")/*�˻�����*/;
  }

  /**
   * �������ݿ�������ͼ�� �������ڣ�(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getTableName() {
    return "so_returncndtn";
  }

  /**
   * �˴���ӹ�˾���� �������ڣ�(2001-7-9 15:13:48)
   * 
   * @return java.lang.String
   */
  @Override
  public String getWherePart() {
    String strWherePart = super.getWherePart();
    if ((null == strWherePart) || ("".equals(strWherePart.trim()))) {
      strWherePart = " pk_group='" + this.getPk_group().trim() + "' and dr =0 ";
    }
    else {
      strWherePart +=
          " and pk_group='" + this.getPk_group().trim() + "' and dr =0 ";
    }
    return strWherePart;
  }

  /**
   * �˴����뷽��˵���� �������ڣ�(2001-7-10 13:10:42)
   * 
   * @param newPk_corp
   *          java.lang.String
   */
  @Override
  public void setPk_group(java.lang.String newPk_corp) {
    this.pk_group = newPk_corp;
  }
}
