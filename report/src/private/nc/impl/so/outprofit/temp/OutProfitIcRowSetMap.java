package nc.impl.so.outprofit.temp;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.so.report.outprofit.OutProfitViewMeta;
import nc.vo.so.report.outprofit.OutProfitViewVO;

/**
 * ��RowSet�ӹ�ΪJava����Ľӿ�ʵ��
 * 
 * @since 6.3
 * @version 2012-08-29 13:48:16
 * @author ������
 */
public class OutProfitIcRowSetMap implements IRowSetMap<OutProfitViewVO> {

  /**
   * ��rowset�ӹ�Ϊ����һ�����
   * 
   * @param rowset Ҫ�ӹ���rowset
   * @return �ӹ���Ķ�������
   */
  @Override
  public OutProfitViewVO[] convert(IRowSet rowset) {
    int size = rowset.size();
    OutProfitViewVO[] views = new OutProfitViewVO[size];
    int i = 0;
    while (rowset.next()) {
      views[i] = new OutProfitViewVO();
      int j = 0;
      for (String selkey : OutProfitViewMeta.SALEOUT_HKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : OutProfitViewMeta.SALEOUT_BKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : OutProfitViewMeta.EXTEND_STRKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      i++;
    }
    return views;
  }

}
