package nc.bs.so.m4331.maintain.rule.reverse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoAutoReserve;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * ���۷�����������Զ�Ԥ������
 * @scene
 * ����������ģ�������۷������������޸ı����
 * @param
 * ��
 * @since 6.0
 * @version 2011-1-25 ����08:03:06
 * @author ף����
 */
public class AutoReserveRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    try {
      Set<String> hidSet = new HashSet<String>();
      for (DeliveryVO vo : vos) {
        SoAutoReserve.autoReserveForReqBill(SOBillType.Delivery.getCode(), vo);
        hidSet.add(vo.getParentVO().getCdeliveryid());
      }
      String[] ids = new String[hidSet.size()];
      hidSet.toArray(ids);
      // ����id��ѯVO
      BillQuery<DeliveryVO> query = new BillQuery<DeliveryVO>(DeliveryVO.class);
      DeliveryVO[] newvos = query.query(ids);
      this.updateTsAndReqNum(newvos, vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void updateTsAndReqNum(DeliveryVO[] newvos, DeliveryVO[] vos) {
    Map<String, DeliveryHVO> hvoMap = new HashMap<String, DeliveryHVO>();
    Map<String, DeliveryBVO> bvoMap = new HashMap<String, DeliveryBVO>();
    for (DeliveryVO newvo : newvos) {
      DeliveryHVO hvo = newvo.getParentVO();
      DeliveryBVO[] bvos = newvo.getChildrenVO();
      hvoMap.put(hvo.getCdeliveryid(), hvo);
      for (DeliveryBVO bvo : bvos) {
        bvoMap.put(bvo.getCdeliverybid(), bvo);
      }
    }
    for (DeliveryVO vo : vos) {
      DeliveryHVO hvo = vo.getParentVO();
      DeliveryHVO newhvo = hvoMap.get(hvo.getCdeliveryid());
      hvo.setTs(newhvo.getTs());
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        DeliveryBVO newbvo = bvoMap.get(bvo.getCdeliverybid());
        bvo.setTs(newbvo.getTs());
        bvo.setNreqrsnum(newbvo.getNreqrsnum());
      }
    }
  }
}
