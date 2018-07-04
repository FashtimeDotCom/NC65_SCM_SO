package nc.ui.so.m38.billui.view;

import java.util.List;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.m38.billui.pub.PreOrderCardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * Ԥ��������Ƭ�༭����
 * 
 * @author ��־ΰ
 * 
 */
public class PreOrderEditor extends ShowUpableBillForm implements
    OnhandPanelSrc {
  // ���岻����༭�ֶ�
  private static final String[] BODY_NOEDIT = new String[] {
    // �����ۿ�
    PreOrderBVO.NDISCOUNTRATE,
    // ����λ
    PreOrderBVO.CUNITID,
    // ����
    PreOrderBVO.CCURRENCYID,
    // ���ҵ���
    PreOrderBVO.NQTTAXPRICE,
    PreOrderBVO.NQTPRICE,
    PreOrderBVO.NQTTAXNETPRICE,
    PreOrderBVO.NQTNETPRICE,
    // �����ҵ���
    PreOrderBVO.NTAXPRICE,
    PreOrderBVO.NPRICE,
    PreOrderBVO.NTAXNETPRICE,
    PreOrderBVO.NNETPRICE,
    // ���ҽ��
    PreOrderBVO.NDISCOUNT,

    // ԭ���ۿ۶�
    PreOrderBVO.NORIGDISCOUNT,

    // ���ű���
    PreOrderBVO.NGROUPMNY,
    PreOrderBVO.NGROUPTAXMNY,
    // ȫ�ֱ���
    PreOrderBVO.NGLOBALTAXMNY,
    PreOrderBVO.NGLOBALMNY,
    // �۸����ֶ�
    PreOrderBVO.CPRICEFORMID,
    PreOrderBVO.CPRICEITEMTABLEID,
    PreOrderBVO.CPRICEPOLICYID,
    // ѯ���ֶ�
    PreOrderBVO.NASKQTORIGTAXPRC, PreOrderBVO.NASKQTORIGPRICE,
    PreOrderBVO.NASKQTORIGTXNTPRC,
    PreOrderBVO.NASKQTORIGNETPRICE,
    // �����ֶ�
    PreOrderBVO.NARRNUM,
    PreOrderBVO.CARRANGEID,
    PreOrderBVO.DARRDATE,
    // ��״̬
    PreOrderBVO.FROWSTATUS,

    // �������OID
    PreOrderBVO.CSETTLEORGID, PreOrderBVO.CARORGID,
    PreOrderBVO.CPROFITCENTERID, PreOrderBVO.CSENDSTOCKORGID,
    PreOrderBVO.CTRAFFICORGID,
    // �������͡�����ó��
    PreOrderBVO.FBUYSELLFLAG, PreOrderBVO.BTRIATRADEFLAG,
    // ���Ҽ�˰�ϼơ�������˰���
    PreOrderBVO.NTAXMNY, PreOrderBVO.NMNY
  };

  private static final int DEFDABATEDATE = 3;

  // ��ͷ������༭�ֶ�
  private static final String[] HEAD_NOEDIT = new String[] {
    PreOrderHVO.VBILLCODE, PreOrderHVO.NTOTALNUM, PreOrderHVO.NHEADSUMMNY,
    PreOrderHVO.FSTATUSFLAG, PreOrderHVO.CDEPTID
  };

  private static final long serialVersionUID = -4607945892107326567L;

  /**
   * �û��ִ������
   */
  private OnhandPanelAdaptor adaptor;

  private List<String> clearHyperlink;

  private PreOrderCardEditSetter editsetter;

  @Override
  public void addCardBodyRowChangedEvent(
      IAppEventHandler<CardBodyRowChangedEvent> l) {
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBodyRowChangedEvent.class, l);
  }

  public List<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  public OnhandPanelAdaptor getExtendedPanel() {
    return this.adaptor;
  }

  @Override
  public OnhandDimParamVO getQryOnhandDim(int row) {
    OnhandDimParamVO paravo = null;
    IKeyValue keyValue = new CardKeyValue(this.getBillCardPanel());
    String marterialvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    if (PubAppTool.isNull(marterialvid)) {
      return paravo;
    }

    paravo = this.getOnhandDimParamVO(keyValue, row);
    return paravo;
  }

  @Override
  public void initUI() {
    super.initUI();
    // ��ʼ������༭��
    this.initEditEnable();
    if (SysInitGroupQuery.isICEnabled()) {
      ScmBatchAdaptor scmbach =
          new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
      UIRefPane uiref = scmbach.getRefPane();

      this.getBillCardPanel().getBodyItem(PreOrderBVO.VBATCHCODE)
          .setComponent(uiref);
    }
    // �����Ƭ������
    this.clearHyperlink();
    // ���չ��˳�ʼ��
    this.initRefCondition();

    // �༭�Կ���
    this.editsetter = new PreOrderCardEditSetter();
    this.editsetter.cacheEditEnable(this.getBillCardPanel());
    // �ϼ���
    BillCardPanel cardPanel = this.getBillCardPanel();
    cardPanel.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    cardPanel.getBillModel().addTotalListener(totallis);
    // ��ʼ�����������ֶ�
    this.initFillEnabled(cardPanel);
  }

  /**
   * ��ʼ������ı༭��
   * 
   * @param cardPanel
   */
  private void initFillEnabled(BillCardPanel cardPanel) {
    BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
    util.disableItemsFill();
    util.enableItemsFill(SOConstant.FILLENABLEDKEY);
    // �Զ�����������༭
    for (int i = 1; i < 21; i++) {
      BillItem bodyitem =
          this.getBillCardPanel().getBodyItem(SOConstant.VBDEF + i);
      bodyitem.setFillEnabled(true);
    }
  }

  public void setCardEdit() {
    this.editsetter.setEditEnable(this.getBillCardPanel());
  }

  public void setClearHyperlink(List<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
    this.adaptor = adaptor;
  }

  public void setReviseEdit() {
    this.editsetter.setReviseCardEdit(this.getBillCardPanel());
  }

  @Override
  protected void onAdd() {
    super.onAdd();
    if (this.isEditable()) {
      this.setCardEdit();
    }
  }

  @Override
  protected void setDefaultValue() {

    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    int irowcount = this.billCardPanel.getRowCount();
    // -- ��ͷ ----------
    // ������֯
    String pk_org = this.getModel().getContext().getPk_org();
    if (!PubAppTool.isNull(pk_org)) {
      keyValue.setHeadValue(PreOrderHVO.PK_ORG, pk_org);
      this.billCardPanel.getBillData().loadEditHeadRelation(PreOrderHVO.PK_ORG);
      // ����
      String pk_group = AppContext.getInstance().getPkGroup();
      keyValue.setHeadValue(PreOrderHVO.PK_GROUP, pk_group);
      // ��������
      UFDate busidate = AppContext.getInstance().getBusiDate();
      keyValue.setHeadValue(PreOrderHVO.DBILLDATE, busidate);
      // ʧЧ����
      keyValue.setHeadValue(PreOrderHVO.DABATEDATE,
          busidate.getDateAfter(PreOrderEditor.DEFDABATEDATE).asLocalEnd());

      // �������ջ�����
      UFDate localend = busidate.asLocalEnd();
      // -- ���� ----------
      for (int i = 0; i < irowcount; i++) {
        // ����
        keyValue.setBodyValue(i, PreOrderBVO.PK_GROUP, pk_group);
        // ������֯
        keyValue.setBodyValue(i, PreOrderBVO.PK_ORG, pk_org);
        // ��������
        keyValue.setBodyValue(i, PreOrderBVO.DBILLDATE, busidate);
        // ��Ʒ�ۿ�
        keyValue.setBodyValue(i, PreOrderBVO.NITEMDISCOUNTRATE, new UFDouble(
            100));
        // �ƻ��������ڡ�Ҫ���ջ�����
        keyValue.setBodyValue(i, PreOrderBVO.DSENDDATE, localend);
        keyValue.setBodyValue(i, PreOrderBVO.DRECEIVEDATE, localend);
      }
    }
  }

  private void clearHyperlink() {
    for (String key : this.getClearHyperlink()) {
      BillItem item = this.getBillCardPanel().getBillData().getHeadItem(key);
      item.getCaptionLabel().setHyperlinkLabel(false);
    }
  }

  private OnhandDimParamVO getOnhandDimParamVO(IKeyValue keyValue, int row) {
    OnhandDimParamVO paravo = new OnhandDimParamVO();
    // ����
    String pk_group = AppContext.getInstance().getPkGroup();
    paravo.setPk_group(pk_group);

    // ����
    String cmarterialvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    paravo.setCmaterialvid(cmarterialvid);
    String cmarterialid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALID);
    paravo.setCmaterialoid(cmarterialid);
    // ҵ��λ
    String castunitid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CASTUNITID);
    paravo.setCastunitid(castunitid);

    // ������
    String vchangerate =
        keyValue.getBodyStringValue(row, PreOrderBVO.VCHANGERATE);
    paravo.setVchangerate(vchangerate);
    // ��������
    String cproductorid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CPRODUCTORID);
    paravo.setCproductorid(cproductorid);
    // ��Ŀ
    String cprojectid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CPROJECTID);
    paravo.setCprojectid(cprojectid);
    // ��Ӧ��
    String cvendorid = keyValue.getBodyStringValue(row, PreOrderBVO.CVENDORID);
    paravo.setCvendorid(cvendorid);

    // ���κ�
    String vbatchcode =
        keyValue.getBodyStringValue(row, PreOrderBVO.VBATCHCODE);
    paravo.setVbatchcode(vbatchcode);

    // �����֯
    String csendstockorgid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTOCKORGID);
    paravo.setPk_org(csendstockorgid);

    // �����֯�汾
    String csendstockorgvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTOCKORGVID);
    paravo.setPk_org_v(csendstockorgvid);

    // �ֿ�
    String cwarehouseid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTORDOCID);
    paravo.setCwarehouseid(cwarehouseid);

    // ������
    String vfree1 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE1);
    paravo.setVfree1(vfree1);

    String vfree2 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE2);
    paravo.setVfree1(vfree2);

    String vfree3 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE3);
    paravo.setVfree1(vfree3);

    String vfree4 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE4);
    paravo.setVfree1(vfree4);

    String vfree5 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE5);
    paravo.setVfree1(vfree5);

    String vfree6 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE6);
    paravo.setVfree1(vfree6);

    String vfree7 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE7);
    paravo.setVfree1(vfree7);

    String vfree8 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE8);
    paravo.setVfree1(vfree8);

    String vfree9 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE9);
    paravo.setVfree1(vfree9);

    String vfree10 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE10);
    paravo.setVfree1(vfree10);

    return paravo;
  }

  /**
   * ��Ƭ�����ʼ���ܷ�༭����
   */
  private void initEditEnable() {
    // ��ͷ���ɱ༭��
    for (String key : PreOrderEditor.HEAD_NOEDIT) {
      BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // ���岻�ɱ༭��
    for (String key : PreOrderEditor.BODY_NOEDIT) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }

  }

  private void initRefCondition() {
    // �����ͻ�
    BillItem customeritem =
        this.getBillCardPanel().getHeadTailItem(PreOrderHVO.CCUSTOMERID);
    FilterCustomerRefUtils filterutils =
        new FilterCustomerRefUtils(customeritem);
    filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
    // ��Ʊ�ͻ�
    BillItem invoicecustomeritem =
        this.getBillCardPanel().getHeadTailItem(PreOrderHVO.CINVOICECUSTID);
    FilterCustomerRefUtils invoicefilterutils =
        new FilterCustomerRefUtils(invoicecustomeritem);
    invoicefilterutils.filterRefByFrozenFlag(UFBoolean.FALSE);

  }
}
