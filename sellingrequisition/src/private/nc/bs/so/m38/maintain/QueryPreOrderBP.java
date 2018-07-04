package nc.bs.so.m38.maintain;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * ����sql��ѯԤ������Ϣ
 * 
 * @since 6.0
 * @version 2011-5-7 ����11:31:04
 * @author ף����
 */
public class QueryPreOrderBP {
  public PreOrderVO[] queryPreOrder(String sql) {
    PreOrderVO[] bills = null;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] cbillids = rowset.toOneDimensionStringArray();

    // ����id��ѯVO
    BillQuery<PreOrderVO> query = new BillQuery<PreOrderVO>(PreOrderVO.class);
    bills = query.query(cbillids);
    return bills;
  }
}
