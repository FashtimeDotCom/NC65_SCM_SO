package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.QueryPreOrderBP;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * ����sql��ѯԤ������Ϣ
 * 
 * @since 6.0
 * @version 2011-5-7 ����11:32:32
 * @author ף����
 */
public class QueryPreOrderAction {
  public PreOrderVO[] queryPreOrder(String sql) {
    QueryPreOrderBP query = new QueryPreOrderBP();
    return query.queryPreOrder(sql);
  }
}
