package nc.pubimpl.so;

/**
 * �˴���������˵����
 * �������ڣ�(2004-3-24 21:52:20)
 * @author����С��
 */
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.bank_cvp.compile.datastruct.IContext;

public class FunctionContex implements IContext {
  private static final long serialVersionUID = 1L;

  ReturnAssignMatchVO m_paravo;

  /**
   * FunctionContex ������ע�⡣
   */
  public FunctionContex(ReturnAssignMatchVO paravo) {
    super();
    this.m_paravo = paravo;
  }

  /**
   * �õ�ĳһ�����Ƶı�������
   * �������ڣ�(2002-4-3 9:34:03)
   * 
   * @return java.lang.Object
   * @param name java.lang.String
   * @exception java.lang.Exception �쳣˵����
   */
  @Override
  public java.lang.Object getProperty(java.lang.String name)
      throws java.lang.Exception {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    if (name.equals("para")) {
      return this.m_paravo;
    }
    throw new IllegalArgumentException();
  }

  /**
   * �õ����������ĸ���
   * �������ڣ�(2002-4-3 9:35:19)
   * 
   * @return int
   */
  @Override
  public int getSize() {
    return 2;
  }

  /**
   * ����Ƿ����ĳһ�ֱ���������
   * �������ڣ�(2002-4-3 9:34:38)
   * 
   * @return boolean
   * @param name java.lang.String
   */
  @Override
  public boolean hasProperty(String name) {
    if (name.equals("para")) {
      return true;
    }
    return false;
  }
}
