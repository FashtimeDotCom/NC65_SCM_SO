package nc.itf.so.pub.ref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.reserve.ReserveAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class SoReserveAdjust {

  private SoReserveAdjust() {
    super();
  }

  /**
   * �����������������ݹر�ʱԤ������ <b>����˵��</b>
   * 
   * @param billtype
   * @param vo
   *          �رյĵ���
   * @throws BusinessException
   *           <p>
   * @author ף����
   * @time 2010-5-2 ����04:03:44
   */
  public static void adjustReserveWhenClose(String billtype,
      AggregatedValueObject vo) throws BusinessException {
    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenClose(billtype, vo);
  }

  /**
   * ������������������ɾ��ʱԤ������ <b>����˵��</b>
   * 
   * @param billtype
   * @param vo
   *          ɾ���ĵ���
   * @throws BusinessException
   *           <p>
   * @author ף����
   * @time 2010-5-2 ����04:03:44
   */
  public static void adjustReserveWhenDelete(String billtype,
      AggregatedValueObject vo) throws BusinessException {
    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenDelete(billtype, vo);
  }

  /**
   * �������������������޸�ʱԤ������ <b>����˵��</b>
   * 
   * @param billtype
   *          ��������
   * @param vo
   *          �޸ĺ�ĵ���
   * @param boolean isuserdel ���󵥾��޸ĺ�����Ԥ����һ�£����׳��쳣
   *        ѯ���Ƿ�ɾ��Ԥ��?�����û�ȷ��ɾ������Ӧ�����ñ�׼����Ϊtrue;
   * @throws BusinessException
   *           <p>
   * @author ף����
   * @time 2010-5-2 ����04:03:44
   */
  public static void adjustReserveWhenEdit(String billtype,
      AggregatedValueObject vo, boolean isuserdel) throws BusinessException {

    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenEdit(billtype, vo, isuserdel);
  }

  /**
   * �������������������޶�ʱԤ������ <b>����˵��</b>
   * 
   * @param billtype
   * @param vo
   *          �޶���ĵ���
   * @param boolean isuserdel ���󵥾��޸ĺ�����Ԥ����һ�£����׳��쳣
   *        ѯ���Ƿ�ɾ��Ԥ��?�����û�ȷ��ɾ������Ӧ�����ñ�׼����Ϊtrue;
   * @throws BusinessException
   *           <p>
   * @author ף����
   * @time 2010-5-2 ����04:03:44
   */
  public static void adjustReserveWhenRevise(String billtype,
      AggregatedValueObject vo, boolean isuserdel) throws BusinessException {
    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenRevise(billtype, vo, isuserdel);
  }
}
