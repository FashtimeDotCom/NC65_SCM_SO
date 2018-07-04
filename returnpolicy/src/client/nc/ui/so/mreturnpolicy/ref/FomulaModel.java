package nc.ui.so.mreturnpolicy.ref;

import nc.vo.uif2.LoginContext;

import nc.ui.ml.NCLangRes;

/**
 * �˴���������˵���� �������ڣ�(2004-3-11 9:54:53)
 * 
 * @author����С��
 */
public class FomulaModel extends nc.ui.pub.formulaset.FormulaEditorModel {

  private ReturncndtnRefPane m_RefPane;

  /**
   * FomulaModel ������ע�⡣
   */
  public FomulaModel(LoginContext context) {
    super();
    this.initModel(context);
  }

  /**
   * �˴����뷽��˵���� �������ڣ�(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public ReturncndtnRefPane getRefPane(LoginContext context) {
    if (this.m_RefPane == null) {
      this.m_RefPane = new ReturncndtnRefPane(context);
    }
    return this.m_RefPane;
  }

  /**
   * �˴����뷽��˵���� �������ڣ�(2004-3-11 11:15:15)
   * 
   * @param newRefPane
   *          nc.ui.so.so141.ReturncndtnRefPane
   */
  public void setRefPane(ReturncndtnRefPane newRefPane) {
    this.m_RefPane = newRefPane;
  }

  /**
   * �˴����뷽��˵���� �������ڣ�(2002-3-26 16:03:41)
   */
  private void initModel(LoginContext context) {
    this.setBusinessFunc(new Object[][] {
      {
        "judge()",
        this.getRefPane(context),
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0110")/*�˻���������*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0111")
      /*�ж��˻�����*/
      }
    });
    this.setInputControl(false);
  }
}
