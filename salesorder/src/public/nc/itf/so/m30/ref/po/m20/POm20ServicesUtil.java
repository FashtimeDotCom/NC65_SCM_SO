package nc.itf.so.m30.ref.po.m20;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.so.m30.IDelete20For30;
import nc.pubitf.pu.m20.so.m30.IPushSave20For30;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>�ɹ������빺���ṩ�ķ���ӿڣ�</b>
 * 
 * <ul>
 * <li>���۶�����ʽ�����빺��
 * <li>���۶�������ɾ������̬�빺��
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-9-1 ����03:30:49
 */
public class POm20ServicesUtil {

  /**
   * ������������Ϊ"ֱ�˲ɹ�"&&��Ϣ����PUSH5AOR20���ɵ����۶�������ɾ����������̬�빺��
   * 
   * @version 6.0
   * @author ��־ΰ
   * @time 2010-9-1 ����03:30:49
   */
  public static void delete20ByPo(String[] orderIDs) throws BusinessException {
    try {
      IDelete20For30 service =
          NCLocator.getInstance().lookup(IDelete20For30.class);
      service.deleteBills(orderIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * ���۶�����ʽ�����빺��
   * 
   * @version 6.0
   * @author ��־ΰ
   * @time 2010-9-1 ����03:30:49
   */
  public static void push20ByPo(PraybillVO[] bills) throws BusinessException {
    try {
      IPushSave20For30 service =
          NCLocator.getInstance().lookup(IPushSave20For30.class);
      service.pushSaveBills(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }
}
