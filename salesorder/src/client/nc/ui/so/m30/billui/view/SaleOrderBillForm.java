package nc.ui.so.m30.billui.view;

import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.ui.so.pub.rule.SetDeptByLoginUserRule;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���۶�����Ƭ�ؼ�
 * �༭�Կ���
 * 
 * @since 6.0
 * @version 2011-6-16 ����06:57:30
 * @author fengjb
 */
public class SaleOrderBillForm extends ShowUpableBillForm implements
    OnhandPanelSrc {

  /** ����ı༭�� */
  private Map<String, Boolean> hmEditEnable;

  /**
   * 
   * @return ��ȡ����ı༭��
   */
  public Map<String, Boolean> getHmEditEnable() {
    return this.hmEditEnable;
  }

  /**
   * ���û���ı༭��
   * 
   * @param hmEditEnable
   */
  public void setHmEditEnable(Map<String, Boolean> hmEditEnable) {
    this.hmEditEnable = hmEditEnable;
  }

  /**
   * ���۶������岻����༭�ֶ�
   */
  private static final String[] BODY_NOEDIT = new String[] {
    // �ۿ���
    SaleOrderBVO.BDISCOUNTFLAG,
    // ������
    SaleOrderBVO.BLABORFLAG,
    // �����ۿ�
    SaleOrderBVO.NDISCOUNTRATE,
    // ����λ
    SaleOrderBVO.CUNITID,
    // ����
    SaleOrderBVO.CCURRENCYID,
    // ���ҵ���
    SaleOrderBVO.NQTTAXPRICE,
    SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE,
    SaleOrderBVO.NQTNETPRICE,
    // �����ҵ���
    SaleOrderBVO.NTAXPRICE,
    SaleOrderBVO.NPRICE,
    SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE,
    // ���ҽ��
    SaleOrderBVO.NDISCOUNT,
    SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NMNY,

    // ԭʼѯ�ۼ۸�
    SaleOrderBVO.NASKQTORIGNETPRICE,
    SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTAXPRC,
    SaleOrderBVO.NASKQTORIGTXNTPRC,

    // ԭ���ۿ۶�
    SaleOrderBVO.NORIGDISCOUNT,

    // ���ű���
    SaleOrderBVO.NGROUPMNY,
    SaleOrderBVO.NGROUPTAXMNY,
    // ȫ�ֱ���
    SaleOrderBVO.NGLOBALTAXMNY,
    SaleOrderBVO.NGLOBALMNY,
    // �۸����ֶ�
    SaleOrderBVO.CPRICEFORMID,
    SaleOrderBVO.CPRICEITEMTABLEID,
    SaleOrderBVO.CPRICEPOLICYID,
    SaleOrderBVO.CPROMOTPRICEID,
    // ѯ���ֶ�
    SaleOrderBVO.NASKQTORIGTAXPRC,
    SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTXNTPRC,
    SaleOrderBVO.NASKQTORIGNETPRICE,
    // �ر��ֶ�
    SaleOrderBVO.BBARSETTLEFLAG,
    SaleOrderBVO.BBCOSTSETTLEFLAG,
    SaleOrderBVO.BBINVOICENDFLAG,
    SaleOrderBVO.BBOUTENDFLAG,
    SaleOrderBVO.BBSENDENDFLAG,
    SaleOrderBVO.BBSETTLEENDFLAG,
    // �����־
    SaleOrderBVO.BBINDFLAG,
    SaleOrderBVO.CBINDSRCID,
    // ��Դ����
    SaleOrderBVO.BARRANGEDFLAG,
    SaleOrderBVO.CARRANGEPERSONID,
    SaleOrderBVO.TLASTARRANGETIME,
    // ��ͬ
    SaleOrderBVO.CCTMANAGEBID,
    SaleOrderBVO.CCTMANAGEID,
    SaleOrderBVO.VCTCODE,
    SaleOrderBVO.VCTTYPE,
    // ������
    SaleOrderBVO.CEXCHANGESRCRETID,
    SaleOrderBVO.FRETEXCHANGE,
    // Դͷ��Ϣ
    SaleOrderBVO.CFIRSTBID,
    SaleOrderBVO.CFIRSTID,
    SaleOrderBVO.VFIRSTCODE,
    SaleOrderBVO.VFIRSTROWNO,
    SaleOrderBVO.VFIRSTTRANTYPE,
    SaleOrderBVO.VFIRSTTYPE,
    // �����ж�Ӧ��Դ��ID
    SaleOrderBVO.CLARGESSSRCID,
    // ��Դ������Ϣ
    SaleOrderBVO.CSRCBID,
    SaleOrderBVO.CSRCID,
    SaleOrderBVO.VSRCCODE,
    SaleOrderBVO.VSRCROWNO,
    SaleOrderBVO.VSRCTRANTYPE,
    SaleOrderBVO.VSRCTYPE,
    // ��Ʒ�۸��̯
    SaleOrderBVO.FLARGESSTYPEFLAG,
    SaleOrderBVO.NLARGESSMNY,
    SaleOrderBVO.NLARGESSTAXMNY,
    // ��״̬
    SaleOrderBVO.FROWSTATUS,
    // �ۼư�������
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    SaleOrderBVO.NARRANGEPONUM, SaleOrderBVO.NARRANGESCORNUM,
    SaleOrderBVO.NARRANGETOAPPNUM, SaleOrderBVO.NARRANGETOORNUM,
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALARNUM,
    SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALNOTOUTNUM,
    SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALPAYMNY,
    SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALRUSHNUM,
    SaleOrderBVO.NTOTALSENDNUM,
    SaleOrderBVO.NTOTALSIGNNUM,
    SaleOrderBVO.NTOTALTRADENUM,
    SaleOrderBVO.NTRANSLOSSNUM,

    // ���ǰ��˰�ϼ�
    SaleOrderBVO.NBFORIGSUBMNY,
    // �ۼƳ�ֽ��
    SaleOrderBVO.NORIGSUBMNY,
    // Ԥ������
    SaleOrderBVO.NREQRSNUM,
    // �˻�����
    SaleOrderBVO.CRETPOLICYID,
    // �˻����δ���ʽ
    SaleOrderBVO.VRETURNMODE,

    // �������OID
    SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CARORGID,
    SaleOrderBVO.CPROFITCENTERID, SaleOrderBVO.CSENDSTOCKORGID,
    SaleOrderBVO.CTRAFFICORGID,
    // ��Ʒ��\������������ó��
    SaleOrderBVO.CPRODLINEID,
    // ��Ʒ�Ҹ�
    SaleOrderBVO.BLRGCASHFLAG
  };

  /**
   * ���۶�����ͷ������༭��
   */
  private static final String[] HEAD_NOEDIT = new String[] {
    // �������OID
    SaleOrderHVO.CDEPTID,

    // ��ͷ�ϼ��������ܼ������������������
    SaleOrderHVO.NTOTALNUM,
    SaleOrderHVO.NTOTALPIECE,
    SaleOrderHVO.NTOTALVOLUME,
    SaleOrderHVO.NTOTALWEIGHT,
    // ����״̬
    SaleOrderHVO.FSTATUSFLAG,
    // �ر��ֶ�
    SaleOrderHVO.BARSETTLEFLAG, SaleOrderHVO.BCOSTSETTLEFLAG,
    SaleOrderHVO.BINVOICENDFLAG, SaleOrderHVO.BOUTENDFLAG,
    SaleOrderHVO.BSENDENDFLAG,
    // �Ƿ���
    SaleOrderHVO.BOFFSETFLAG,
    // �Ƿ�ɢ��
    SaleOrderHVO.BFREECUSTFLAG,
    // Эͬ�ֶ�
    SaleOrderHVO.BCOOPTOPOFLAG, SaleOrderHVO.BPOCOOPTOMEFLAG,
    // �޶��汾��
    SaleOrderHVO.IVERSION
  };

  private static final long serialVersionUID = 1L;

  List<String> clearHyperlink;

  // private CardEditSetter editsetter;

  // ���۶���ǰ̨������
  private SaleOrderClientContext m30ClientContext =
      new SaleOrderClientContext();

  @Override
  public void addCardBodyRowChangedEvent(
      IAppEventHandler<CardBodyRowChangedEvent> l) {
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBodyRowChangedEvent.class, l);
  }

  public List<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  public Map<String, CtBusinessVO> getCtMap() {
    return this.m30ClientContext.getCtMap();
  }

  public boolean getIsCashSale() {
    return this.m30ClientContext.getIsCashSale();
  }

  public SaleOrderClientContext getM30ClientContext() {
    return this.m30ClientContext;
  }

  private UFDate dbilldate;

  /**
   * ��õ�������
   * 
   * @return UFDate
   */
  public UFDate getDbilldate() {
    return this.dbilldate;
  }

  /**
   * ���õ�������
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.dbilldate = dbilldate;
  }

  @Override
  public OnhandDimParamVO getQryOnhandDim(int row) {
    IKeyValue util = new CardKeyValue(this.getBillCardPanel());
    OnhandDimParamVO paraVO = new OnhandDimParamVO();
    paraVO.setPk_group(this.getModel().getContext().getPk_group());
    paraVO.setCastunitid(util.getBodyStringValue(row, SaleOrderBVO.CASTUNITID));
    paraVO.setCmaterialoid(util.getBodyStringValue(row,
        SaleOrderBVO.CMATERIALID));
    paraVO.setCmaterialvid(util.getBodyStringValue(row,
        SaleOrderBVO.CMATERIALVID));
    paraVO.setCproductorid(util.getBodyStringValue(row,
        SaleOrderBVO.CPRODUCTORID));
    paraVO.setCprojectid(util.getBodyStringValue(row, SaleOrderBVO.CPROJECTID));
    paraVO.setCvendorid(util.getBodyStringValue(row, SaleOrderBVO.CVENDORID));
    paraVO.setCwarehouseid(util.getBodyStringValue(row,
        SaleOrderBVO.CSENDSTORDOCID));
    paraVO.setPk_batchcode(util.getBodyStringValue(row,
        SaleOrderBVO.PK_BATCHCODE));
    paraVO.setVbatchcode(util.getBodyStringValue(row, SaleOrderBVO.VBATCHCODE));
    paraVO
        .setPk_org(util.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID));
    paraVO.setPk_org_v(util.getBodyStringValue(row,
        SaleOrderBVO.CSENDSTOCKORGVID));
    paraVO.setVbatchcode(util.getBodyStringValue(row, SaleOrderBVO.VBATCHCODE));
    paraVO.setVchangerate(util
        .getBodyStringValue(row, SaleOrderBVO.VCHANGERATE));
    paraVO.setVfree1(util.getBodyStringValue(row, SaleOrderBVO.VFREE1));
    paraVO.setVfree2(util.getBodyStringValue(row, SaleOrderBVO.VFREE2));
    paraVO.setVfree3(util.getBodyStringValue(row, SaleOrderBVO.VFREE3));
    paraVO.setVfree4(util.getBodyStringValue(row, SaleOrderBVO.VFREE4));
    paraVO.setVfree5(util.getBodyStringValue(row, SaleOrderBVO.VFREE5));
    paraVO.setVfree6(util.getBodyStringValue(row, SaleOrderBVO.VFREE6));
    paraVO.setVfree7(util.getBodyStringValue(row, SaleOrderBVO.VFREE7));
    paraVO.setVfree8(util.getBodyStringValue(row, SaleOrderBVO.VFREE8));
    paraVO.setVfree9(util.getBodyStringValue(row, SaleOrderBVO.VFREE9));
    paraVO.setVfree10(util.getBodyStringValue(row, SaleOrderBVO.VFREE10));
    return paraVO;
  }

  public SoBalanceVO getSobalancevo() {
    return this.m30ClientContext.getSobalancevo();
  }

  public OffsetTempVO getTempvo() {
    return this.m30ClientContext.getOffsetTempVO();
  }

  public UFDouble getThisGatheringMny() {
    return this.m30ClientContext.getThisGatheringMny();
  }

  @Override
  public void initUI() {
    super.initUI();

    BillCardPanel cardPanel = this.getBillCardPanel();
    cardPanel.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    cardPanel.getBillModel().addTotalListener(totallis);
    // ��ʼ���༭��
    this.initEditEnable();
    if (SysInitGroupQuery.isICEnabled()) {
      ScmBatchAdaptor scmbach =
          new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
      UIRefPane uiref = scmbach.getRefPane();
      // �������β���
      // BatchRefPane batchref = new BatchRefPane();
      this.getBillCardPanel().getBodyItem(SaleOrderBVO.VBATCHCODE)
          .setComponent(uiref);
    }
    // ����༭��
    CardEditSetter editset = new CardEditSetter(this);
    editset.cacheEditEnable();

    // �����Ƭ������
    this.clearHyperlink();

    // ��ʼ��֧�����ĵ��ֶ�
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

  public void setClearHyperlink(List<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  public void setCtMap(Map<String, CtBusinessVO> ctMap) {
    this.m30ClientContext.setCtMap(ctMap);
  }

  public void setIsCashSale(boolean isCashSale) {
    this.m30ClientContext.setIsCashSale(isCashSale);
  }

  public void setM30ClientContext(SaleOrderClientContext m30ClientContext) {
    this.m30ClientContext = m30ClientContext;
  }

  public void setSobalancevo(SoBalanceVO sobalancevo) {
    this.m30ClientContext.setSobalancevo(sobalancevo);
  }

  public void setTempvo(OffsetTempVO offvo) {
    this.m30ClientContext.setOffsetTempVO(offvo);
  }

  public void setThisGatheringMny(UFDouble thisGatheringMny) {
    this.m30ClientContext.setThisGatheringMny(thisGatheringMny);
  }

  @Override
  protected void initBillCardPanel() {
    super.initBillCardPanel();

    CardEditSetter editset = new CardEditSetter(this);
    // ���ù̶��༭��
    editset.setCardFixEditFalse();
  }

  @Override
  protected void onAdd() {
    super.onAdd();
    // �������
    this.setTempvo(null);
    this.setSobalancevo(new SoBalanceVO());
    this.setThisGatheringMny(null);
    this.setIsCashSale(false);

    // ����ҵ������
    this.setBizType();
  }

  @Override
  protected void onEdit() {
    super.onEdit();
    this.setTempvo(null);
    this.setSobalancevo(new SoBalanceVO());
    this.setThisGatheringMny(null);
    this.setIsCashSale(false);
  }

  @Override
  protected void onNotEdit() {
    // ���ñ༭��
    if (this.isEditable()) {
      CardEditSetter editset = new CardEditSetter(this);
      editset.setOriginalEdit();
    }
    super.onNotEdit();

  }

  @Override
  protected void setDefaultValue() {

    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // 1.��ͷ
    // ������֯
    String pk_org = this.getModel().getContext().getPk_org();
    keyValue.setHeadValue(SaleOrderHVO.PK_ORG, pk_org);
    String pk_org_v = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG_V);
    if (PubAppTool.isNull(pk_org_v)) {
      String newpkorg_v = OrgUnitPubService.getNewVIDByOrgID(pk_org);
      keyValue.setHeadValue(SaleOrderHVO.PK_ORG_V, newpkorg_v);
    } 
    
    // ����keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG_V);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    keyValue.setHeadValue(SaleOrderHVO.PK_GROUP, pk_group);

    // ���ò�������Ա
    new SetDeptByLoginUserRule(keyValue, this.getModel().getContext(),
        SaleOrderHVO.CEMPLOYEEID, SaleOrderHVO.CDEPTID, SaleOrderHVO.CDEPTVID)
        .setPsnAndDept();

    // ��������
    UFDate busidate = AppContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busidate);
    // �����ۿ�
    keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, SOConstant.ONEHUNDRED);
    // ����״̬
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());

    UFDate localend = busidate.asLocalEnd();
    // 2.����
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // ������֯
      keyValue.setBodyValue(i, SaleOrderBVO.PK_ORG, pk_org);
      // ��������
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busidate);
      // �����ۿ�
      keyValue.setBodyValue(i, SaleOrderBVO.NDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // ��Ʒ�ۿ�
      keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // �ƻ��������ڡ�Ҫ���ջ�����
      keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, localend);
      keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, localend);
    }

  }

  private void clearHyperlink() {
    for (String key : this.getClearHyperlink()) {
      BillItem item = this.getBillCardPanel().getBillData().getHeadItem(key);
      // item.setHyperlink(false);
      item.getCaptionLabel().setHyperlinkLabel(false);
    }
  }

  /**
   * ���۷�Ʊ��Ƭ�����ʼ���ܷ�༭����
   */
  private void initEditEnable() {

    // ��ͷ���ɱ༭��
    for (String key : SaleOrderBillForm.HEAD_NOEDIT) {
      BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // ���岻�ɱ༭��
    for (String key : SaleOrderBillForm.BODY_NOEDIT) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }
  }

  /**
   * �û��ִ������
   */
  private OnhandPanelAdaptor adaptor;

  public OnhandPanelAdaptor getExtendedPanel() {
    return this.adaptor;
  }

  public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
    this.adaptor = adaptor;
  }

  private void setBizType() {

    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    String trantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    if (PubAppTool.isNull(trantypeid) || PubAppTool.isNull(pk_org)) {
      return;
    }
    this.billCardPanel.getBillData().loadEditHeadRelation(
        SaleOrderHVO.CTRANTYPEID);
    String typecode = keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    // 2.�Զ�ƥ��ҵ������
    String userid = AppContext.getInstance().getPkUser();
    String cbiztypeid = null;
    try {
      cbiztypeid =
          PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
              typecode, pk_org, userid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    // ����ҵ������
    keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
  }

/* (non Javadoc) 
 * @Title: showMeUp
 * @Description: TODO 
 * @see nc.ui.pubapp.uif2app.view.PubShowUpableBillForm#showMeUp() 
 */
@SuppressWarnings("static-access")
@Override
public void showMeUp() {
	// TODO �Զ����ɵķ������
	this.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
	try {
		Thread.currentThread().sleep(200L);
	} catch (InterruptedException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
	super.showMeUp();
	this.getBillCardPanel().getBillModel().execLoadFormula();
	//��Ϊ�е��ֶ�˫������ʾpk�ˣ�����ͳһˢ��һ��
}
}
