package nc.pubimpl.so.m4331.ic.m4c;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m30.so.m4331.ISaleOrderFor4331;
import nc.pubitf.so.m4331.ic.m4c.IDeliveryFor4C;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class DeliveryFor4CImpl implements IDeliveryFor4C {

  @Override
  public Map<String, UFBoolean> getReserveInfo(String[] bids)
      throws BusinessException {
    // ���ݷ���������id��ѯVO
    VOQuery<DeliveryBVO> query = new VOQuery<DeliveryBVO>(DeliveryBVO.class);
    DeliveryBVO[] bvos = query.query(bids);

    // �������۶�������id
    Set<String> idSet = new HashSet<String>();
    // ����ֵ
    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    // delivery�ж�Ӧ����ԴsaleOrder��
    Map<String, String> deliveryVSo = new HashMap<String, String>();
    // saleOrder��Ԥ��
    // Map<String, UFBoolean> soReserved=new HashMap<String, UFBoolean>();
    for (DeliveryBVO bvo : bvos) {
      idSet.add(bvo.getCsrcbid());
      deliveryVSo.put(bvo.getCdeliverybid(), bvo.getCsrcbid());
    }
    // ��������������۶�������ѯ���۶����Ƿ�����Ԥ��
    if (idSet.size() != 0) {
      String[] ids = new String[idSet.size()];
      ISaleOrderFor4331 service =
          NCLocator.getInstance().lookup(ISaleOrderFor4331.class);
      try {
        Map<String, UFBoolean> soReserved =
            service.queryIsReserved(idSet.toArray(ids));
        Set<Entry<String, UFBoolean>> entrys = soReserved.entrySet();
        for (Entry<String, UFBoolean> entry : entrys) {
          String key = entry.getKey();
          UFBoolean srcReserved = soReserved.get(deliveryVSo.get(key));
          result.put(key, srcReserved);
        }
        // for (String key : deliveryVSo.keySet()) {
        // UFBoolean srcReserved = soReserved.get(deliveryVSo.get(key));
        // result.put(key, srcReserved);
        // }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return result;
  }

}
