package nc.bs.so.m4331.quality;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * �ʼ���ѯ��
 * 
 * @since 6.0
 * @version 2010-12-28 ����10:47:16
 * @author ף����
 */
public class QueryDeliveryCheckBP {
  public DeliveryCheckVO[] queryDeliveryCheckVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return null;
    }
    String[] ids = rowset.toOneDimensionStringArray();
    VOQuery<DeliveryCheckVO> query =
        new VOQuery<DeliveryCheckVO>(DeliveryCheckVO.class);
    return query.query(ids);
  }
}
