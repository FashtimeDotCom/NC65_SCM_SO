package nc.bs.so.m4331.assist.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331trantype.IM4331TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ������״̬ԾǨ�жϹ���
 * 
 * @author ף����
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public class StateCalculateUtil {

  /**
   * ����������������÷������������͵������Ƿ�һ�γ���ر�
   * 
   * @author ף����
   * @time 2010-9-29 ����11:43:57
   */
  public Map<String, UFBoolean> getIsClosedMap(DeliveryViewVO[] views) {
    String pk_group = views[0].getHead().getPk_group();
    List<String> list = new ArrayList<String>();
    for (DeliveryViewVO view : views) {
      String billtype = view.getHead().getVtrantypecode();
      if ((list.size() == 0) || !list.contains(billtype)) {
        list.add(billtype);
      }
    }
    String[] billtypes = new String[list.size()];
    billtypes = list.toArray(billtypes);
    IM4331TranTypeService service =
        NCLocator.getInstance().lookup(IM4331TranTypeService.class);
    Map<String, UFBoolean> map;
    try {
      map = service.queryTranTypes(pk_group, billtypes);
      return map;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

  /**
   * ��������������������״̬�ж��Ƿ���������رա� <li>�����йرգ��������ر�(��ͷת�ر�״̬) ��return true <li>
   * ��һ�д򿪣���������(��ͷת����״̬) ��return false
   * 
   * @param vo
   *          ������ĵ���VO
   * @return �Ƿ��Զ��ر�
   * @author ף����
   * @time 2010-04-09 ����02:43:18
   */
  public boolean isAutoBillCloseByRowState(DeliveryVO vo) {
    DeliveryBVO[] items = vo.getChildrenVO();
    for (DeliveryBVO item : items) {
      boolean blineclose = item.getBoutendflag().booleanValue();
      if (!blineclose) {
        return false;
      }
    }
    return true;
  }

  /**
   * ���������������ж��Ƿ��ܹ��Զ�����ر�
   * 
   * @author ף����
   * @time 2010-6-17 ����03:04:03
   */
  public UFBoolean isAutoOutCloseByNumber(DeliveryViewVO vo) {
    DeliveryBVO body = vo.getItem();
    UFDouble nnum = body.getNnum();
    UFDouble ntotaloutnum = body.getNtotaloutnum();
    if (MathTool.compareTo(nnum, ntotaloutnum) <= 0) {
      return UFBoolean.TRUE;
    }
    return UFBoolean.FALSE;
  }

  /**
   * �����������������ݵ���״̬�ж��Ƿ�����Զ��йرա� <b>����˵��</b>
   * 
   * @param views
   *          ������ĵ���views
   * @return �Ƿ��Զ��ر�
   * @author ף����
   * @time 2010-04-09 ����02:43:18
   */
  public boolean isAutoRowsCloseByBillState(DeliveryViewVO views) {
    return BillStatus.CLOSED.equalsValue(views.getHead().getFstatusflag());
  }

  /**
   * �����������������ݵ���״̬�ж��Ƿ�����Զ��д򿪡�
   * 
   * @param views
   *          ������ĵ���views
   * @return �Ƿ��Զ��ر�
   * @author ף����
   * @time 2010-04-09 ����02:43:18
   */
  public boolean isAutoRowsOpenByBillState(DeliveryViewVO views) {
    return BillStatus.AUDIT.equalsValue(views.getHead().getFstatusflag());
  }
}
