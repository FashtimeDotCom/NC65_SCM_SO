package nc.itf.so.m30.ref.ic.atp;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.atp.IAtpQuery;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.ic.atp.pub.AtpQryParamVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * IC��������������
 * 
 * @since 6.0
 * @version 2011-7-5 ����09:41:12
 * @author ��־ΰ
 */
public class ICAtpServicesUtil {

  public static Map<String, AtpVO> queryAtpAndOnhandNum(
      AtpQryParamVO[] atpQueryVO) {
    Map<String, AtpVO> atps = new HashMap<String, AtpVO>();
    IAtpQuery service = NCLocator.getInstance().lookup(IAtpQuery.class);
    try {
      AtpVO[] results =
          service.queryAtpVOs(atpQueryVO);
      int materLen = atpQueryVO.length;
      for (int i = 0; i < materLen; i++) {
        atps.put(atpQueryVO[i].getCmaterialoid(), results[i]);
      }
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return atps;
  }
}
