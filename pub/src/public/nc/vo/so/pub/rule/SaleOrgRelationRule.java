package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
import nc.itf.so.m30.IQueryRelationOrg;

import nc.bs.framework.common.NCLocator;

public class SaleOrgRelationRule {

  IKeyValue keyValue;

  public SaleOrgRelationRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setFinanceOrg(int[] rows) {

    if (null == rows || rows.length == 0) {
      return;
    }

    String[] clearkeys =
        new String[] {
          SOItemKey.CSETTLEORGID, SOItemKey.CSETTLEORGVID, SOItemKey.CARORGID,
          SOItemKey.CARORGVID, SOItemKey.CPROFITCENTERID,
          SOItemKey.CPROFITCENTERVID
        };
    this.clearBodyOldValue(clearkeys, rows);

    List<String> alMaterialid = new ArrayList<String>();
    List<String> alOrgs = new ArrayList<String>();
    List<String> alSendStockOrgIDs = new ArrayList<String>();
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);

    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      String csendstockorgid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
      alOrgs.add(pk_org);
      alSendStockOrgIDs.add(csendstockorgid);
    }
    if (alMaterialid.size() == 0) {
      return;
    }

    Map<String, String[]> hmFinanceOrgid = null;
    Map<String, String> hmFinanceOrgvid = null;

    String ccustomerid =
        this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String[] pk_orgs = new String[alOrgs.size()];
    alOrgs.toArray(pk_orgs);

    String[] csendstockorgids = new String[alSendStockOrgIDs.size()];
    alSendStockOrgIDs.toArray(csendstockorgids);

    // ��ѯ���������֯ID��Ӧ����֯ID����������ID�ͽ��������֯VID��Ӧ����֯VID����������VID
    try {
      hmFinanceOrgid =
          SaleOrgPubService
              .getDefaultFinanceOrgIDSAndReceiveOrgIDSAndLiaCenterIDS(
                  ccustomerid, pk_orgs, cmaterialids, csendstockorgids);
      hmFinanceOrgvid = this.getFinanceOrgVIDs(hmFinanceOrgid);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    // ����������ֵ
    this.setFinanceOrgValue(hmFinanceOrgid, hmFinanceOrgvid, rows);

  }

  public void setSendStockOrg(int[] rows) {

    if (null == rows || rows.length == 0) {
      return;
    }
    // ���ԭ�з��������֯
    String[] clearkey = new String[] {
      SOItemKey.CSENDSTOCKORGID, SOItemKey.CSENDSTOCKORGVID
    };
    this.clearBodyOldValue(clearkey, rows);

    List<String> alOrg = new ArrayList<String>();
    List<String> alMaterial = new ArrayList<String>();
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    for (int row : rows) {
      // ��ȡ����
      String materialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (!PubAppTool.isNull(materialid)) {
        alOrg.add(pk_org);
        alMaterial.add(materialid);
      }
    }
    if (alOrg.size() == 0 || alMaterial.size() == 0) {
      return;
    }
    String[] pk_orgs = new String[alOrg.size()];
    alOrg.toArray(pk_orgs);

    String[] materialids = new String[alMaterial.size()];
    alMaterial.toArray(materialids);

    Map<String, String> hmSendStockOrgIDs = null;
    Map<String, String> hmSendStockOrgVIDs = null;
    try {
      // ��ѯĬ�Ϸ��������֯ID�ͷ��������֯VID
      hmSendStockOrgIDs =
          SaleOrgPubService.getDefaultStockOrgIDS(pk_orgs, materialids);

      hmSendStockOrgVIDs = this.getSendStockOrgVIDs(hmSendStockOrgIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null != hmSendStockOrgIDs && null != hmSendStockOrgVIDs) {
      for (int row : rows) {
        String marvid =
            this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
        if (PubAppTool.isNull(marvid)) {
          continue;
        }
        String key = pk_org + SaleOrgPubService.SPLIT + marvid;
        String csendstockorgid = hmSendStockOrgIDs.get(key);
        this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGID,
            csendstockorgid);

        String csendstockorgvid = hmSendStockOrgVIDs.get(csendstockorgid);
        this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGVID,
            csendstockorgvid);
      }
    }

  }

  /**
   * ���ݷ��������֯������ҵ��ί�й�ϵ�в���������֯
   * <p>
   * �ֿ�ǿգ��òֿ����������֯����
   * </p>
   * <p>
   * �ֿ�Ϊ�գ��ö������������֯����
   * </p>
   */
  public void setTrafficOrg(int[] rows) {
    /* ����������
     * ������֯�ǽ��з������ź����䰲�ŵ���֯���Ƿ����������䵥������֯��
     * ϵͳ��Ϊ���������ź����䰲�ţ���������λ�ã��ڵ��ذ��š�
     * ���ղֿ����������֯�������ǰ�����Ȩ��ƥ��������֯��*/
    if (null == rows || rows.length == 0) {
      return;
    }
    String[] clearkey = new String[] {
      SOItemKey.CTRAFFICORGID, SOItemKey.CTRAFFICORGVID
    };
    this.clearBodyOldValue(clearkey, rows);
    // ��ȡ�����֯
    List<String> alsendStockOrgid = new ArrayList<String>();
    List<String> alsendstordocid = new ArrayList<String>();
    for (int row : rows) {
      // --�ֿ�ǿգ�ȡ�ֿ����������֯
      String sendstordocid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
      if (!PubAppTool.isNull(sendstordocid)) {
        alsendstordocid.add(sendstordocid);
      }
      // --�ֿ�Ϊ�գ�ȡ���������֯
      else {
        String sendStockOrgID =
            this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
        if (!PubAppTool.isNull(sendStockOrgID)) {
          alsendStockOrgid.add(sendStockOrgID);
        }
      }
    }
    // stockOrgMap<�ֿ�ID,���������֯ID>
    Map<String, String> stockOrgMap = new HashMap<String, String>();
    if (alsendstordocid.size() > 0) {
      String[] sendstordocids =
          alsendstordocid.toArray(new String[alsendstordocid.size()]);
      String[] fields = new String[] {
        StordocVO.PK_STORDOC, StordocVO.PK_ORG
      };
      StordocVO[] stordocVOs =
          StordocPubService.queryStordocByPks(sendstordocids, fields);

      if (stordocVOs != null && stordocVOs.length > 0) {
        for (StordocVO stordocVO : stordocVOs) {
          stockOrgMap.put(stordocVO.getPk_stordoc(), stordocVO.getPk_org());
        }
      }
    }

    if (alsendStockOrgid.size() == 0 && stockOrgMap.size() == 0) {
      return;
    }
    alsendStockOrgid.addAll(stockOrgMap.values());
    String[] sendStockOrgIDs = new String[alsendStockOrgid.size()];
    alsendStockOrgid.toArray(sendStockOrgIDs);

    Map<String, String> trafficOrgIDMap = null;
    Map<String, String> trafficOrgVIDMap = null;

    trafficOrgIDMap =
        TrafficOrgPubService.getTrafficOrgIDSByStockOrgIDS(sendStockOrgIDs);
    trafficOrgVIDMap = this.getTraficOrgVIDs(trafficOrgIDMap);

    if (null != trafficOrgIDMap && null != trafficOrgVIDMap) {
      for (int row : rows) {
        // �ֿ�ǿ�
        String sendstordocid =
            this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
        if (!PubAppTool.isNull(sendstordocid)) {
          String trafficOrgID =
              trafficOrgIDMap.get(stockOrgMap.get(sendstordocid));
          this.keyValue
              .setBodyValue(row, SOItemKey.CTRAFFICORGID, trafficOrgID);
          this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGVID,
              trafficOrgVIDMap.get(trafficOrgID));
        }
        // �ֿ�Ϊ��
        else {
          String sendStockOrgID =
              this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
          if (!PubAppTool.isNull(sendStockOrgID)) {
            String trafficOrgID = trafficOrgIDMap.get(sendStockOrgID);
            this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGID,
                trafficOrgID);
            this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGVID,
                trafficOrgVIDMap.get(trafficOrgID));
          }
        }
      }
    }
  }

  private void clearBodyOldValue(String[] bodykeys, int[] rows) {
    for (int row : rows) {
      for (String key : bodykeys) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }
  }

  private Map<String, String> getFinanceOrgVIDs(
      Map<String, String[]> hmFinanceOrgid) throws BusinessException {
    Map<String, String> hmFinanceOrgvid = null;
    if (hmFinanceOrgid == null || hmFinanceOrgid.size() == 0) {
      return hmFinanceOrgvid;
    }
    Set<String> hsIDs = new HashSet<String>();
    for (Entry<String, String[]> entry : hmFinanceOrgid.entrySet()) {
      String[] ids = entry.getValue();
      if (null == ids || ids.length == 0) {
        continue;
      }
      for (String id : ids) {
        if (!PubAppTool.isNull(id)) {
          hsIDs.add(id);
        }
      }
    }
    if (hsIDs.size() > 0) {
      String[] orgIDs = new String[hsIDs.size()];
      hsIDs.toArray(orgIDs);

      // ��ѯ���������֯VID��Ӧ����֯VID����������VID
      hmFinanceOrgvid = OrgUnitPubService.getNewVIDSByOrgIDS(orgIDs);
    }
    return hmFinanceOrgvid;
  }

  private Map<String, String> getSendStockOrgVIDs(
      Map<String, String> hmSendStockOrgIDs) throws BusinessException {
    Map<String, String> hmSendStockOrgVIDs = null;
    // �ǿ��ж�
    if (null == hmSendStockOrgIDs || hmSendStockOrgIDs.size() == 0) {
      return hmSendStockOrgVIDs;
    }
    String[] sendstockids = new String[hmSendStockOrgIDs.values().size()];
    hmSendStockOrgIDs.values().toArray(sendstockids);

    // ���ݷ��������֯ID��ѯ���������֯VID
    hmSendStockOrgVIDs = OrgUnitPubService.getNewVIDSByOrgIDS(sendstockids);

    return hmSendStockOrgVIDs;
  }

  private Map<String, String> getTraficOrgVIDs(
      Map<String, String> trafficOrgIDMap) {
    Map<String, String> returnMap = null;
    if (null == trafficOrgIDMap || trafficOrgIDMap.size() == 0) {
      return returnMap;
    }

    String[] trafficOrgIDs = new String[trafficOrgIDMap.values().size()];
    trafficOrgIDMap.values().toArray(trafficOrgIDs);

    returnMap = OrgUnitPubService.getNewVIDSByOrgIDS(trafficOrgIDs);

    return returnMap;
  }

  private void setFinanceOrgValue(Map<String, String[]> hmFinanceOrgIDs,
      Map<String, String> hmFinanceOrgVIDs, int[] rows) {
    if (null == hmFinanceOrgIDs || null == hmFinanceOrgVIDs) {
      return;
    }
    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      // ����Ϊ��
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      String[] orgids = hmFinanceOrgIDs.get(cmaterialid);
      String csettleorgid = orgids[0];
      String carorgid = orgids[1];
      String cprofitcenterid = orgids[2];
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGID, csettleorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CARORGID, carorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERID,
          cprofitcenterid);
      // ����VID
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGVID,
          hmFinanceOrgVIDs.get(csettleorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CARORGVID,
          hmFinanceOrgVIDs.get(carorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERVID,
          hmFinanceOrgVIDs.get(cprofitcenterid));

    }
  }

  /**
   * ���۶���
   * ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID��Ĭ��������֯ ���ݽ��������Ƿ�ֱ�˻�ȡֱ�˲�
   * 
   * @param keyValue
   * @param rows
   * @return
   */
  public Map<String, String[]> getRelationOrg(int[] rows) {

    Map<String, String[]> hmRelationOrgid = null;
    // ��֯���ͻ����������͡����ϲ���׼��
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String ccustomerid =
        this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);

    List<String> alMaterialid = new ArrayList<String>();

    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
    }
    if (alMaterialid.size() == 0) {
      return null;
    }

    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String transtypeID =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // ��ѯ���������֯ID��Ӧ����֯ID����������ID�ͽ��������֯VID��Ӧ����֯VID����������VID
    try {
      // ����������ͷǿգ����ս������ͻ�ȡֱ�˲�

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(transtypeID, ccustomerid, pk_org,
              cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }

  /**
   * �������ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID���ֿ�
   * ȡĬ��������֯Ҫ����ȡ�ֿ����
   * 
   * @throws BusinessException
   * @see
   */
  public void setFinanceStockOrg(int[] rows,
      Map<String, String[]> hmRelationOrgid) {
    if (null == rows || rows.length == 0 || null == hmRelationOrgid) {
      return;
    }

    // �������ԭʼֵ
    String[] clearkeys =
        new String[] {
          SOItemKey.CSETTLEORGID, SOItemKey.CSETTLEORGVID, SOItemKey.CARORGID,
          SOItemKey.CARORGVID, SOItemKey.CPROFITCENTERID,
          SOItemKey.CPROFITCENTERVID, SOItemKey.CSENDSTOCKORGID,
          SOItemKey.CSENDSTOCKORGVID, SOItemKey.CSENDSTORDOCID
        };
    this.clearBodyOldValue(clearkeys, rows);

    // //��ȡ������֯��VID
    Map<String, String> hmRelationOrgVid =
        this.getRelationOrgVIDs(hmRelationOrgid);

    // ����������ֵ
    this.setRelationOrgValue(hmRelationOrgid, hmRelationOrgVid, rows);
  }

  /*
   * ��ȡ������֯��VID
   */
  private Map<String, String> getRelationOrgVIDs(
      Map<String, String[]> hmRelationOrgid) {
    Map<String, String> hmRelationOrgvid = null;

    if (hmRelationOrgid == null || hmRelationOrgid.size() == 0) {
      return hmRelationOrgvid;
    }
    Set<String> hsIDs = new HashSet<String>();
    for (Entry<String, String[]> entry : hmRelationOrgid.entrySet()) {
      String[] ids = entry.getValue();
      if (null == ids || ids.length == 0) {
        continue;
      }
      for (String id : ids) {
        if (!PubAppTool.isNull(id)) {
          hsIDs.add(id);
        }
      }
    }
    if (hsIDs.size() > 0) {
      String[] orgIDs = new String[hsIDs.size()];
      hsIDs.toArray(orgIDs);
      // ��ȡ��֯VID
      hmRelationOrgvid = OrgUnitPubService.getNewVIDSByOrgIDS(orgIDs);
    }

    return hmRelationOrgvid;
  }

  /*
   * ����UI�������֯��ֱ�˲ֿ��ֵ
   */
  private void setRelationOrgValue(Map<String, String[]> hmRelationOrgIDs,
      Map<String, String> hmRelationOrgVIDs, int[] rows) {
    if (null == hmRelationOrgIDs || null == hmRelationOrgVIDs) {
      return;
    }
    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      // ����Ϊ��
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      String[] orgids = hmRelationOrgIDs.get(cmaterialid);
      String csendstockorgid = orgids[0];
      String csettleorgid = orgids[1];
      String carorgid = orgids[2];
      String cprofitcenterid = orgids[3];
      String ctrafficorgid = orgids[4];
      String dirstore = orgids[5];
      this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGID,
          csendstockorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGID, csettleorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CARORGID, carorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERID,
          cprofitcenterid);
      this.keyValue.setBodyValue(row, SOItemKey.CSENDSTORDOCID, dirstore);

      // ����VID
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGVID,
          hmRelationOrgVIDs.get(csettleorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CARORGVID,
          hmRelationOrgVIDs.get(carorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERVID,
          hmRelationOrgVIDs.get(cprofitcenterid));
      this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGVID,
          hmRelationOrgVIDs.get(csendstockorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGID, ctrafficorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGVID,
          hmRelationOrgVIDs.get(ctrafficorgid));

    }
  }

}
