package nc.itf.so.pub.ref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.reserve.AutoReserve;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class SoAutoReserve {

  private SoAutoReserve() {
    super();
  }

  /**
   * ��������������������󵥾ݵ��Զ�Ԥ���� <b>����˵��</b>
   * 
   * @param billtype
   *          ��������
   * @param vo
   *          �޸ĺ�ĵ���
   * @return <p>
   * @author ף����
   * @time 2010-5-2 ����03:48:13
   */
  public static void autoReserveForReqBill(String billtype,
      AggregatedValueObject vo) throws BusinessException {
    AutoReserve auto = NCLocator.getInstance().lookup(AutoReserve.class);
    auto.autoReserveForReqBill(billtype, vo);
  }
}
