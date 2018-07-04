package nc.impl.so.pub.profit;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.ia.ICostPriceQuery;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.so.pub.profit.IQueryCostPrice;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class QueryCostPriceImpl implements IQueryCostPrice {

  /**
   * ��������������ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param cstockorgids �����֯ID����
   * @param cstordocids �ֿ�ID����
   * @param cmaterialids ����ID����
   * @param vbatchs ��������
   * @return Map<Key�������֯+�ֿ�+����+���Σ�Value���ɱ�����>
   * @since 6.0
   * @author ������
   * @throws BusinessException
   * @time 2011-7-12 ����18:54:31
   */
  @Override
  public Map<String, UFDouble> queryCostPrice(String[] cstockorgids,
      String[] cstordocids, String[] cmaterialids, String[] vbatchs)
      throws BusinessException {
    Map<String, UFDouble> costpricemap = new HashMap<String, UFDouble>();
    ICostPriceQuery control =
        NCLocator.getInstance().lookup(ICostPriceQuery.class);
    // ��ȡ��ʼ�����鳤��
    int intCount = cmaterialids.length;
    // ��ʼ���ɱ������Ϻ����β�������
    String[] pk_orgs = new String[intCount];
    // ���ݿ����֯�Ͳֿ�������ȡ�ɱ���
    Map<String, String> costmap =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            cstockorgids, cstordocids);
    // ��ʼ���ɱ�������
    for (int i = 0; i < intCount; i++) {
      pk_orgs[i] = costmap.get(cstockorgids[i] + cstordocids[i]);
    }

    /*
     * // ���û�ȡ�ɱ����۽ӿ�
     * UFDouble[] icprice = control.getCostPrice(pk_orgs, cmaterialids,
     * vbatchs);
     * for (int i = 0; i < intCount; i++) {
     * String strKey =
     * cstockorgids[i] + cstordocids[i] + cmaterialids[i] + vbatchs[i];
     * if (!costpricemap.containsKey(strKey)) {
     * costpricemap.put(strKey, icprice[i]);
     * }
     * }
     */

    // ���û�ȡ�ɱ����۽ӿ�-

    Map<String, UFDouble> icpricemap =
        control.getCostPriceMap(pk_orgs, cmaterialids, vbatchs);
    for (int i = 0; i < intCount; i++) {
      String strKey =
          cstockorgids[i] + cstordocids[i] + cmaterialids[i] + vbatchs[i];
      UFDouble costprice =
          icpricemap.get(costmap.get(cstockorgids[i] + cstordocids[i]) + "|"
              + cmaterialids[i] + "|" + vbatchs[i]);
      if (!costpricemap.containsKey(strKey)) {
        costpricemap.put(strKey, costprice);
      }
    }

    return costpricemap;
  }
}
