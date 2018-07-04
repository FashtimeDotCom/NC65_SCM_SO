package nc.ui.so.m4331.arrange.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.ExAppEventConst;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.pubapp.util.RefListPanelValueUtils;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterRackRefUtils;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.so.m4331.arrange.pub.DeliveryModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class EditEventHandler implements AppEventListener, IBillPush {

  //modify by jilu for EHP1���̵�633 20140703
  private Map<String, String> cachematerial;

  private BillContext context;

  /**
   * ��ȡ���������
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getCachematerial() {
    return this.cachematerial;
  }

  /**
   * ���û��������
   * 
   * @param cachematerial
   */
  public void setCachematerial(Map<String, String> cachematerial) {
    this.cachematerial = cachematerial;
  }

  private void beforeAstUnitidEdit(PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    BillListPanel listPanel = this.getBillContext().getListPanel();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    String material =
        keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALVID);
    if (!PubAppTool.isNull(material)) {
      // ���ϲ�Ϊ�գ�ֻ�ܲ������϶�Ӧ�ļ�����λ
      BillItem astunititem = listPanel.getBodyItem(DeliveryBVO.CASTUNITID);
      FilterMeasdocRefUtils astunitFilter =
          new FilterMeasdocRefUtils(astunititem);
      astunitFilter.setMaterialPk(material);
      e.setEditable(true);
    }
    else {
      e.setEditable(false);
    }

  }

  /*
   * ���κű༭ǰ�¼�����
   */
  private void beforeBacthcode(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String material =
        valueUtil.getStringValueAt(iRow, DeliveryBVO.CMATERIALVID);
    String sendstock =
        valueUtil.getStringValueAt(iRow, DeliveryBVO.CSENDSTOCKORGID);
    String[] wholeflag = new String[] {
      MaterialStockVO.WHOLEMANAFLAG
    };
    Map<String, MaterialStockVO> map =
        MaterialPubService.queryMaterialStockInfo(new String[] {
          material
        }, sendstock, wholeflag);
    MaterialStockVO vo = map.get(material);
    UFBoolean flag = UFBoolean.FALSE;
    if (null != vo) {
      flag = vo.getWholemanaflag();
    }
    // BillItem item = reflist.getBodyItem(DeliveryBVO.VBATCHCODE);
    if (null == flag || !flag.booleanValue()) {
      e.setEditable(false);
      return;
    }
    e.setEditable(true);
  }

  private void beforeCchauffeurid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    BillItem item = reflist.getBodyItem(DeliveryBVO.CCHAUFFEURID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }

  /*
   * ��λ�༭ǰ�¼�
   */
  private void beforeCspaceid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    BillItem item = reflist.getBodyItem(DeliveryBVO.CSPACEID);
    String cwarehouseid =
        valueUtil.getStringValueAt(iRow, DeliveryBVO.CSENDSTORDOCID);
    if (null == cwarehouseid) {
      item.setEdit(false);
      return;
    }
    StordocVO[] vos = StordocPubService.queryStordocByPks(new String[] {
      cwarehouseid
    }, new String[] {
      StordocVO.CSFLAG
    });
    UFBoolean flag = vos[0].getCsflag();
    if (null == flag || !flag.booleanValue()) {
      item.setEdit(false);
      return;
    }
    FilterRackRefUtils filter =
        new FilterRackRefUtils((UIRefPane) item.getComponent());
    filter.filterByWarehouse(cwarehouseid);
  }

  private void beforeCsupercargoid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);

    BillItem item = reflist.getBodyItem(DeliveryBVO.CSUPERCARGOID);
    FilterPsndocRefUtils utils =FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfSO((UIRefPane) item.getComponent());
    utils.filterItemRefByOrg(pk_org);
  }

  private void beforeCvehicleid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    BillItem item = reflist.getBodyItem(DeliveryBVO.CVEHICLEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }

  private void beforeCvehicletypeid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    BillItem item = reflist.getBodyItem(DeliveryBVO.CVEHICLETYPEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }

  /*
   * �����ֿ�༭ǰ����
   */
  private void beforeCwarehouseid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    BillItem item = reflist.getBodyItem(DeliveryBVO.CSENDSTORDOCID);
    // ���������֯
    String sendstock =
        valueUtil.getStringValueAt(iRow, DeliveryBVO.CSENDSTOCKORGID);
    FilterWareHouseRefUtils filter =
        new FilterWareHouseRefUtils((UIRefPane) item.getComponent());
    filter.filterItemRefByOrg(sendstock);
    filter.filterDirectStorc();
  }

  private void beforeDeptEdit(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    BillItem item = reflist.getBodyItem(DeliveryHVO.CSENDDEPTVID);
    UIRefPane deptrefpanel = (UIRefPane) item.getComponent();
    FilterDeptRefUtils utils = FilterDeptRefUtils
        .createFilterDeptRefUtilsOfSO(deptrefpanel);
    // ���ò��շ�Χ֧��ȫ��֯
    utils.filterItemRefByOrg(pk_org);
  }

  private void beforeEmployeeEdit(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    BillItem item = reflist.getBodyItem(DeliveryHVO.CSENDEMPLOYEEID);
    UIRefPane deptrefpanel = (UIRefPane) item.getComponent();
    FilterPsndocRefUtils utils = FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfSO(deptrefpanel);
    utils.filterItemRefByOrg(pk_org);
    // ����Զ���λ������
    String pk_dept = valueUtil.getStringValueAt(iRow, DeliveryHVO.CSENDDEPTID);
    utils.fixFocusByPK(pk_dept);
  }

  /*
   * ���������֯�༭ǰ����
   */
  private void beforeSendStock(PushBillEvent e) {
    // int iRow = e.getEditEvent().getRow();
    // RefListPanel reflist = this.getBillContext().getListPanel();
    // RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    // String srcbilltype = valueUtil.getStringValueAt(iRow,
    // DeliveryBVO.VSRCTYPE);
    // BillItem item = reflist.getBodyItem(DeliveryBVO.CSENDSTOCKORGVID);
    // if (!SOBillType.Order.getCode().equals(srcbilltype)) {
    // item.setEdit(false);
    // return;
    // }
    // String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    // FilterStockOrgRefUtils filter = new FilterStockOrgRefUtils(item);
    // filter.setOrg(pk_org);

    int row = e.getEditEvent().getRow();
    RefListPanel listPanel = this.getBillContext().getListPanel();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    String pk_org = keyValue.getBodyStringValue(row, DeliveryBVO.CSALEORGID);
    String cmaterialid =
        keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALID);
    // ���������֯VIDs
    String[] csendstockorgvids = null;
    if (cmaterialid == null || cmaterialid.trim().length() == 0) {
      return;
    }
    try {
      String[] csendstockorgids =
          SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(pk_org,
              cmaterialid);
      csendstockorgvids = this.getSendStockOrgVIDs(csendstockorgids);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }

    if (!VOChecker.isEmpty(csendstockorgvids)) {
      BillItem billItem = listPanel.getBodyItem(DeliveryBVO.CSENDSTOCKORGVID);
      UIRefPane sendStockOrgRefPane = (UIRefPane) billItem.getComponent();
      AbstractRefModel model = sendStockOrgRefPane.getRefModel();
      model.setFilterPks(csendstockorgvids);
    }
  }

  private String[] getSendStockOrgVIDs(String[] csendstockorgids)
      throws BusinessException {
    String[] csendstockorgvids = null;
    if (null == csendstockorgids || csendstockorgids.length == 0) {
      return csendstockorgvids;
    }

    // תVID
    Map<String, String> hmSendStockOrgIDs =
        OrgUnitPubService.getNewVIDSByOrgIDS(csendstockorgids);

    if (hmSendStockOrgIDs != null) {
      List<String> alSendStockOrgVIDs = new ArrayList<String>();
      for (String value : hmSendStockOrgIDs.values()) {
        if ((value != null) && (value.length() > 0)) {
          alSendStockOrgVIDs.add(value);
        }
      }
      if (alSendStockOrgVIDs.size() > 0) {
        csendstockorgvids = alSendStockOrgVIDs.toArray(new String[0]);
      }
    }
    return csendstockorgvids;
  }

  private void beforeTranSportid(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    BillItem item = reflist.getBodyItem(DeliveryHVO.CTRANSPORTROUTEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }

  /**
   * ���������������������������ͱ༭ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-6-1 ����02:33:15
   */
  private void beforeTranTypeEdit(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String pk_org = valueUtil.getStringValueAt(iRow, DeliveryHVO.PK_ORG);
    // ���˽�������
    BillItem trantype = reflist.getBodyItem(DeliveryHVO.CTRANTYPEID);
    FilterTransTypeRefUtils refUtil =
        new FilterTransTypeRefUtils(trantype, pk_org);
    refUtil.filterTranType(new String[] {
      SOBillType.Delivery.getCode()
    });
  }

  private void calculateNumPriceMny(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    String key = e.getEditEvent().getKey();
    if (DeliveryBVO.CASTUNITID.equals(key)) {
      this.setHsl(e);
      key = DeliveryBVO.VCHANGERATE;
    }

    DeliveryModelCalculator calculator =
        new DeliveryModelCalculator(this.getBillContext().getListPanel());
    calculator.calculate(new int[] {
      iRow
    }, key);
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    UFDouble totalNum =
        (UFDouble) valueUtil.getValueAt(iRow, DeliveryBVO.NASTNUM);
    valueUtil.setValueAt(iRow, DeliveryHVO.NTOTALASTNUM, totalNum);
  }

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  @Override
  public void handleEvent(AppEvent event) {
    PushBillEvent e = null;
    if (event instanceof PushBillEvent) {
      e = (PushBillEvent) event;
    }
    else {
      return;
    }
    // Ŀ�굥�ݱ༭ǰ�¼�
    if (ExAppEventConst.BILL_PUSH_BEFOREDIT == e.getType()) {
      // ��������
      if (DeliveryHVO.CTRANTYPEID.equals(e.getEditEvent().getKey())) {
        this.beforeTranTypeEdit(e);
      }
      // ��λ
      else if (DeliveryBVO.CASTUNITID.equals(e.getEditEvent().getKey())) {
        this.beforeAstUnitidEdit(e);
      }
      // ��������
      else if (DeliveryHVO.CSENDDEPTVID.equals(e.getEditEvent().getKey())) {
        this.beforeDeptEdit(e);
      }
      // ����ҵ��Ա
      else if (DeliveryHVO.CSENDEMPLOYEEID.equals(e.getEditEvent().getKey())) {
        this.beforeEmployeeEdit(e);
      }
      // ���������֯�༭�¼�
      else if (DeliveryBVO.CSENDSTOCKORGVID.equals(e.getEditEvent().getKey())) {
        this.beforeSendStock(e);
      }
      else if (DeliveryBVO.VBATCHCODE.equals(e.getEditEvent().getKey())) {
        this.beforeBacthcode(e);
      }
      else if (DeliveryBVO.CSENDSTORDOCID.equals(e.getEditEvent().getKey())) {
        this.beforeCwarehouseid(e);
      }
      else if (DeliveryBVO.CSPACEID.equals(e.getEditEvent().getKey())) {
        this.beforeCspaceid(e);
      }
      // ����·��
      else if (DeliveryHVO.CTRANSPORTROUTEID.equals(e.getEditEvent().getKey())) {
        this.beforeTranSportid(e);
      }
      // ����
      else if (DeliveryBVO.CVEHICLETYPEID.equals(e.getEditEvent().getKey())) {
        this.beforeCvehicletypeid(e);
      }
      // ����
      else if (DeliveryBVO.CVEHICLEID.equals(e.getEditEvent().getKey())) {
        this.beforeCvehicleid(e);
      }
      // ˾��
      else if (DeliveryBVO.CCHAUFFEURID.equals(e.getEditEvent().getKey())) {
        this.beforeCchauffeurid(e);
      }
      // Ѻ��Ա
      else if (DeliveryBVO.CSUPERCARGOID.equals(e.getEditEvent().getKey())) {
        this.beforeCsupercargoid(e);
      }
      // modify by jilu for EHP1���̵�633 20140703
      // ����
      else if (DeliveryBVO.CMATERIALVID.equals(e.getEditEvent().getKey())) {
        this.beforematerial(e);
      }

    }
    // Ŀ�굥�ݱ༭���¼�
    else if (e.getType() == ExAppEventConst.BILL_PUSH_AFTEREDIT) {
      this.calculateNumPriceMny(e);

    }
    else if (e.getType() == ExAppEventConst.BILL_PUSH_ROWCHANGE
        || e.getType() == ExAppEventConst.SELECTION_CHANGED) {
      if (e.getEditEvent() != null && e.getEditEvent().getRow() > -1) {
        OnRowChangeEventHandler handler = new OnRowChangeEventHandler();
        handler.afterEdit(e, this.context);
      }
    }
  }
  
  private void beforematerial(PushBillEvent e) {
    /*�¶���   �Ϸ���ԪNC������Ŀ ���������������ϣ������޸�Ϊ�滻�����ϡ��޸��滻���󣬷����������ε��ݹ�ϵ���ֲ���*/
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    int iRow = e.getEditEvent().getRow();
    UIRefPane refPane =
        (UIRefPane) reflist.getBodyItem(DeliveryBVO.CMATERIALVID)
            .getComponent();
    String srcmaterialid =
        valueUtil.getStringValueAt(iRow, DeliveryBVO.CMATERIALVID);
    String scrbid = valueUtil.getStringValueAt(iRow, DeliveryBVO.CSRCBID);

    if (this.getCachematerial() == null) {
      this.setCachematerial(new HashMap<String, String>());
    }
    String oldmaterid = this.getCachematerial().get(scrbid);
    if (PubAppTool.isNull(oldmaterid)) {
      this.getCachematerial().put(scrbid, srcmaterialid);
    }
    SqlBuilder sqlwhere = new SqlBuilder();
    sqlwhere.append(" enablestate =2 ");
    sqlwhere.append(" and ");
    String newmaterialid = this.getCachematerial().get(scrbid);
    if (!PubAppTool.isNull(newmaterialid)) {
      sqlwhere.append(" pk_material", newmaterialid);
      sqlwhere.append(" or ");
      sqlwhere
          .append(" pk_material in (select pk_replace from bd_materialrepl where pk_material='"
              + newmaterialid + "' )");
    }
    else {
      sqlwhere.append(" 1=1 ");
    }
    refPane.setWhereString(sqlwhere.toString());
  }

  @Override
  public void setBillContext(BillContext context1) {
    this.context = context1;
  }

  private void setHsl(PushBillEvent e) {
    int iRow = e.getEditEvent().getRow();
    RefListPanel reflist = this.getBillContext().getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    String material =
        valueUtil.getStringValueAt(iRow, DeliveryBVO.CMATERIALVID);
    String unit = valueUtil.getStringValueAt(iRow, DeliveryBVO.CUNITID);
    String editunit = valueUtil.getStringValueAt(iRow, DeliveryBVO.CASTUNITID);
    String changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);
    valueUtil.setValueAt(iRow, DeliveryBVO.VCHANGERATE, changerate);
  }
}
