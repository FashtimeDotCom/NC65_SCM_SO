package nc.ui.so.pub.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSalePubService;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.findprice.IFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.largessprice.ILargessPriceConfig;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class LargessPirceRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private ILargessPriceConfig config;

  /**
   * ������ѯ����Ҫ��յ��ֶ�
   */
  private static final String[] CLEANKEYS = new String[] {
    SOItemKey.CPRICEFORMID, SOItemKey.CPRICEITEMID,
    SOItemKey.CPRICEITEMTABLEID, SOItemKey.CPRICEPOLICYID
  };

  public LargessPirceRule(BillCardPanel cardPanel, ILargessPriceConfig config) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
    this.config = config;
  }

  public void setLargessPrice(int[] rows) {
    String chgkey = null;
    Integer fetchmode = this.config.getLargessPriceMode();
    // �۸�0
    if (LargessGetqtRule.ZERO_QT.equalsValue(fetchmode)) {
      this.setZeroPrice(rows);
      chgkey = SOItemKey.NQTORIGTAXNETPRC;
    }
    // �ο��ۼ�
    else if (LargessGetqtRule.MARSSORG_REQT.equalsValue(fetchmode)) {
      this.setResalePrice(rows);
      chgkey = SOItemKey.NQTORIGTAXNETPRC;
    }
    // ����ۼ�
    else if (LargessGetqtRule.MARSSORG_LOWQT.equalsValue(fetchmode)) {
      this.setMinPrice(rows);
      chgkey = SOItemKey.NQTORIGTAXNETPRC;
    }
    // �ο��ɱ�
    else if (LargessGetqtRule.MARSSORG_COSETQT.equalsValue(fetchmode)) {
      this.setCostPrice(rows);
      chgkey = SOItemKey.NQTORIGNETPRICE;
    }
    // ����ѯ��
    else if (LargessGetqtRule.ASK_SALEQT.equalsValue(fetchmode)) {
      this.setFindPrice(rows);
    }
    if (null != chgkey) {
      this.config.processAfterGetPrice(rows, chgkey);
    }
  }

  private void setFindPrice(int[] rows) {
    IFindPriceConfig findconfig = this.config.getFindPriceConfig();
    FindSalePrice findprice = new FindSalePrice(this.cardPanel, findconfig);
    findprice.forceFindPrice(rows);
  }

  private void setCostPrice(int[] rows) {
    Map<String, String> mapcostorg = this.getCostOrg(rows);
    for (int row : rows) {
      String maroid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(maroid)) {
        continue;
      }
      String sendstock =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
      if (PubAppTool.isNull(sendstock)) {
        continue;
      }
      String store =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
      String key = sendstock + store;
      String costorg = mapcostorg.get(key);
      String[] pk_materials = new String[] {
        maroid
      };
      Map<String, UFDouble> mapprice =
          MaterialPubService.queryCostPriceByPks(pk_materials, costorg);
      this.keyValue.setBodyValue(row, SOItemKey.NQTORIGNETPRICE,
          mapprice.get(maroid));
      for (String str : LargessPirceRule.CLEANKEYS) {
        this.keyValue.setBodyValue(row, str, null);
      }
    }
  }

  private Map<String, String> getCostOrg(int[] rows) {
    Map<String, String> mapCostOrg = null;
    List<String> alsendstock = new ArrayList<String>();
    List<String> alstore = new ArrayList<String>();
    for (int row : rows) {
      String maroid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(maroid)) {
        continue;
      }
      String sendstock =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
      if (PubAppTool.isNull(sendstock)) {
        continue;
      }
      alsendstock.add(sendstock);
      String store =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
      alstore.add(store);
    }
    if (alsendstock.size() == 0) {
      return mapCostOrg;
    }
    String[] stockorgids = new String[alsendstock.size()];
    alsendstock.toArray(stockorgids);

    String[] stordocids = new String[alstore.size()];
    alstore.toArray(stordocids);

    mapCostOrg =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            stockorgids, stordocids);
    return mapCostOrg;
  }

  private void setMinPrice(int[] rows) {

    String[] marvids = this.getMaterialVIDs(rows);
    if (marvids.length == 0) {
      return;
    }
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    Map<String, UFDouble> mapPrice =
        MaterialSalePubService.queryMinprice(marvids, saleorg);
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marvid)) {
        continue;
      }
      UFDouble price = mapPrice.get(marvid);
      this.keyValue.setBodyValue(row, SOItemKey.NQTORIGTAXNETPRC, price);
      for (String str : LargessPirceRule.CLEANKEYS) {
        this.keyValue.setBodyValue(row, str, null);
      }
    }

  }

  private void setResalePrice(int[] rows) {
    String[] marvids = this.getMaterialVIDs(rows);
    if (marvids.length == 0) {
      return;
    }
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    Map<String, UFDouble> mapPrice =
        MaterialSalePubService.queryResaleprice(marvids, saleorg);
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marvid)) {
        continue;
      }
      UFDouble price = mapPrice.get(marvid);
      this.keyValue.setBodyValue(row, SOItemKey.NQTORIGTAXNETPRC, price);
      for (String str : LargessPirceRule.CLEANKEYS) {
        this.keyValue.setBodyValue(row, str, null);
      }
    }
  }

  private String[] getMaterialVIDs(int[] rows) {

    Set<String> setmarvid = new HashSet<String>();
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marvid)) {
        continue;
      }
      setmarvid.add(marvid);
    }
    String[] marvids = new String[setmarvid.size()];
    setmarvid.toArray(marvids);
    return marvids;
  }

  /**
   * ������Ʒ�м۸�Ϊ0
   * 
   * @param rows
   */
  private void setZeroPrice(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.NQTORIGTAXNETPRC,
          UFDouble.ZERO_DBL);
      for (String str : LargessPirceRule.CLEANKEYS) {
        this.keyValue.setBodyValue(row, str, null);
      }
    }
  }
}
