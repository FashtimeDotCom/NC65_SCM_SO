package nc.pubitf.so.mobile.analy;

/**
 * �ҵĶ������鷽ʽö��
 * 
 * @since 6.1
 * @version 2013-06-08 09:46:45
 * @author yixl
 */
public enum MyOrderGrpType {

  /**
   * �ҵĶ������鷽ʽ
   */

  GROUPDATE("1", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006005_0", "04006005-0027"/*����*/));

  private String funccode;

  private String funcname;

  private MyOrderGrpType(String funccode, String funcname) {
    this.funccode = funccode;
    this.funcname = funcname;
  }

  /**
   * ��÷��鷽ʽ����
   * 
   * @return ���鷽ʽ����
   */
  public String getFuncCode() {
    return this.funccode;
  }

  /**
   * ��÷��鷽ʽ����
   * 
   * @return ���鷽ʽ����
   */
  public String getFuncName() {
    return this.funcname;
  }
}
