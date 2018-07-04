package nc.bs.so.m33.pub;

import java.util.HashMap;
import java.util.Map;

import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class QuerySaleOrderEndInfoBP {

  /**
   * ��ѯ���۶�������ر���Ϣ
   * 
   * @param ordbids
   * @return <����bid,[0]�Ƿ�Ӧ�ս���رա�[1]�Ƿ�ɱ�����ر�>
   */
  public Map<String, UFBoolean[]> querySaleOrderEndInfo(String[] ordbids) {
    Map<String, UFBoolean[]> map = new HashMap<String, UFBoolean[]>();
    try {
      SaleOrderViewVO[] views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(ordbids, new String[] {
            SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBARSETTLEFLAG,
            SaleOrderBVO.BBCOSTSETTLEFLAG
          });
      for (SaleOrderViewVO view : views) {
        String bid = view.getBody().getCsaleorderbid();
        UFBoolean arflag =
            ValueUtils.getUFBoolean(view.getBody().getBbarsettleflag());
        UFBoolean costflag =
            ValueUtils.getUFBoolean(view.getBody().getBbcostsettleflag());
        UFBoolean[] flag = new UFBoolean[] {
          arflag, costflag
        };
        map.put(bid, flag);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return map;
  }
}
