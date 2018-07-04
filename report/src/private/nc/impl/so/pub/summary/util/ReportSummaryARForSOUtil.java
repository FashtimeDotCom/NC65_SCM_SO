package nc.impl.so.pub.summary.util;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.vo.arap.itfvo.ReceivableBillInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * ���۶�������Ʊ���������ִ�в�ѯ���뵥������˷���
 *
 * @since 6.3
 * @version 2015-1-28 ����8:57:42
 * @author wangshu6
 */ 
public class ReportSummaryARForSOUtil {
  
  public static Map<String, ReceivableBillInfoVO> queryReceivableBillInfoByTopBill(String[] top_billids,String[] invdetailbids){
    Map<String, ReceivableBillInfoVO> invarinfo = null;
    IArapReceivableBillPubServiceForSCM arsrv =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);
    try {
      if (invdetailbids.length > 0) {
        invarinfo =
            arsrv.queryReceivableBillInfoByTopBill(
                SOBillType.Invoice.getCode(), top_billids, invdetailbids);
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return invarinfo;
  }
  
}
