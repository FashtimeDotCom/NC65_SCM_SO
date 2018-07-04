package nc.vo.so.m30.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.bd.feature.ffile.IPubFFileQueryService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * @description
 *              ���۶�������������������۸�ֵ 
 *              ����������ȷ�ϣ�Ŀǰֻ���������ۣ�����������Ӱ�충�����۵��߼�
 * @scene
 * 
 * @param ��
 * 
 * @since 6.5
 * @version 2015-10-29 ����2:38:42
 * @author zhangby5
 */
public class FillNmffilePriceRule {

  private IKeyValue keyValue;

  public FillNmffilePriceRule(IKeyValue keyValue) {
    super();
    this.keyValue = keyValue;
  }

  public void setNmffilePrice() {
    Map<String, UFDouble> nmffilepriceMap = this.queryFfileprice(keyValue);
    int rowcount = keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      String ctFfileId = keyValue.getBodyStringValue(i, SaleOrderBVO.CMFFILEID);
      if (PubAppTool.isNull(ctFfileId)) {
        continue;
      }
      keyValue.setBodyValue(i, SaleOrderBVO.NMFFILEPRICE,
          nmffilepriceMap.get(ctFfileId));
    }
  }

  private Map<String, UFDouble> queryFfileprice(IKeyValue keyValue) {
    Set<String> cmffileSet = new HashSet<>();
    int rowcount = keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      String ctFfileId = keyValue.getBodyStringValue(i, SaleOrderBVO.CMFFILEID);
      if (PubAppTool.isNull(ctFfileId)) {
        continue;
      }
      cmffileSet.add(ctFfileId);
    }
    String corigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    Map<String, UFDouble> nmffilepriceMap = new HashMap<>();
    if (cmffileSet.size() > 0) {
      // ���ݱ��ֺ������뵽ƥ��������Ŀ��õ�������
      IPubFFileQueryService ffileQueryService =
          NCLocator.getInstance().lookup(IPubFFileQueryService.class);
      try {
        nmffilepriceMap =
            ffileQueryService.queryPriceByPK(
                cmffileSet.toArray(new String[cmffileSet.size()]),
                corigcurrencyid);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
    return nmffilepriceMap;
  }

}
