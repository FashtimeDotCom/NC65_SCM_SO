package nc.bs.so.pub;


/**
 * ����ģ��ǿ��������ķ���
 * 
 * @since 6.0
 * @version 2011-3-30 ����06:47:22
 * @author ô��
 */
public class SOTransaction {
  public SOTransaction() {
    super();
  }

  /**
   * ǿ�ƿ�ʼ���񡣵��˷����ĵ�����û�����������ʱ�򣬴˷�������ǿ������һ������
   * ���˷����ĵ�����������������ô�˷������̳д�����
   * ��;����ʹ����ʱ���ѯ��ʱ�����ڵ��ô˲�ѯ��������Ϊ��ѯ����������������ʱ
   * �����û������״̬������±��������ݡ���ʱ�����ݲ��ᱻ�������뵽��ʱ���У���
   * ����ʱ����ʵ�ʱ����ʱ�����ѯ���κ����ݡ�
   * 
   * @param classname
   * @param methodname
   * @param ParameterTypes
   * @param ParameterValues
   * @return
   */
  public Object mandatoryTransaction(String classname, String methodname,
      Class<?>[] ParameterTypes, Object[] ParameterValues) {
    // ServcallVO[] scd = new ServcallVO[1];
    // scd[0] = new ServcallVO();
    // scd[0].setBeanName(classname);
    // scd[0].setMethodName(methodname);
    // scd[0].setParameterTypes(ParameterTypes);
    // scd[0].setParameter(ParameterValues);
    // Object[] objects = null;
    // try {
    // objects = LocalCallService.callEJBService("so", scd);
    // }
    // catch (Exception ex) {
    // ExceptionUtils.wrappException(ex);
    // }
    // Object ret = null;
    // if (objects != null && objects.length > 0) {
    // ret = objects[0];
    // }
    // SaleOrderProfitUtil util = new SaleOrderProfitUtil();
    // Method method =
    // this.getOneBatchCalcMethod(util, methodname, ParameterTypes);
    // Object ret =
    // CMTProxySrv.delegate_RequiresNew(util, method, ParameterValues);
    // return ret;
    return null;
  }

  // private Method getOneBatchCalcMethod(Object target, String methodname,
  // Class<?>[] ParameterTypes) {
  // // Class<?>[] parameterTypes = new Class<?>[2];
  // // parameterTypes[0] = String[].class;
  // // parameterTypes[1] = Map.class;
  // Method method = null;
  // try {
  // method = target.getClass().getMethod(methodname, ParameterTypes);
  // }
  // catch (Exception e) {
  // ExceptionUtils.wrappException(e);
  // }
  // return method;
  // }

  /**
   * ����һ���µĶ������񡣵����������еķ�������ʱ���˷���ִ�й������漰��������
   * �������ύ���߻ع�����������ύ���ع����ᵼ�¸�������ύ���ع���
   * 
   * @param classname
   * @param methodname
   * @param ParameterTypes
   * @param ParameterValues
   * @return
   */
  // public Object newTransaction(String classname, String methodname,
  // Class<?>[] ParameterTypes, Object[] ParameterValues) {
  // ServcallVO[] scd = new ServcallVO[1];
  // scd[0] = new ServcallVO();
  // scd[0].setBeanName(classname);
  // scd[0].setMethodName(methodname);
  // scd[0].setParameterTypes(ParameterTypes);
  // scd[0].setParameter(ParameterValues);
  //
  // IScmEJBService bo =
  // (IScmEJBService) NCLocator.getInstance().lookup(
  // IScmEJBService.class.getName());
  //
  // Object[] objects = null;
  // try {
  // objects = bo.callEJBService_RequiresNew("so", scd);
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  // Object ret = null;
  // if (objects != null && objects.length > 0) {
  // ret = objects[0];
  // }
  // return ret;
  //
  // }

}
