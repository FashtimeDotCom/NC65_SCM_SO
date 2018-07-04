package nc.itf.so.m30.ref.to.m5a;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.to.m5a.so.IMaintain5AFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.to.m5a.entity.TransInVO;

public class TOm5AServicesUtil {

  /**
   * �����������������۶�������ɾ�����εĵ�������
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param m30IDs
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-6-8 ����03:02:12
   */
  public static void delete5AByTo(String[] m30IDs) throws BusinessException {
    try {
      IMaintain5AFor30 bo =
          NCLocator.getInstance().lookup(IMaintain5AFor30.class);
      bo.delete5AFor30(m30IDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * �����������������۶�������֯����ת���������ʽ���ɵ�������ӿڵ���
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-6-8 ����11:39:17
   */
  public static void push5AByTo(TransInVO[] bills) throws BusinessException {
    try {
      IMaintain5AFor30 bo =
          NCLocator.getInstance().lookup(IMaintain5AFor30.class);
      bo.save5AFor30(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

}
