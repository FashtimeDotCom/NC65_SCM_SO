package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;

/**
 * bomչ������ ��2012.3.27�����������Ǳ߰ѽӿ�ȥ���� ��ʱȥ���˹��ܣ������н��֮���ٴ���
 * 
 * @since 6.0
 * @version 2012-3-27 ����01:32:01
 * @author ô��
 */
public class SaleOrderBomAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 507050761471827717L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  @Override
  public void doAction(ActionEvent arg0) throws Exception {

    // int selectRow =
    // this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    // if (selectRow == -1) {
    // ExceptionUtils
    // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006011_0", "04006011-0003")/*@res "����ѡ������С�"*/);
    // }
    // // ����VO����
    // SaleOrderBVO bvo =
    // (SaleOrderBVO) this.editor.getBillCardPanel().getBillModel()
    // .getBodyValueRowVO(selectRow, SaleOrderBVO.class.getName());
    // SaleOrderHVO hvo =
    // (SaleOrderHVO) this.editor.getBillCardPanel().getBillData()
    // .getHeaderValueVO(SaleOrderHVO.class.getName());
    //
    // // ���Ϸ���
    // this.checkOrderBomValidate(hvo, bvo);
    //
    // // ��ѯ��þۺ�VO
    // OrderBomVO[] initboms = this.getOrderBom(hvo, bvo);
    //
    // // ���µĹ��ܽڵ�
    // FuncletInitData initData = new FuncletInitData();
    // initData.setInitType(ILinkType.LINK_TYPE_ADD);
    // initData.setInitData(initboms);
    // // �������۽ڵ��
    // String nodecode = PfDataCache.getBillType("3M").getNodecode();
    // IFuncRegisterQueryService funcsrv =
    // NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
    //
    // FuncRegisterVO funvo = funcsrv.queryFunctionByCode(nodecode);
    // // ���ؼ����ӿ�
    // OrderBomLink linklisten = new OrderBomLink();
    // linklisten.setEditor(this.editor);
    // linklisten.setSelectRow(selectRow);
    // // ���������۽ڵ�
    // FuncletWindowLauncher.openFuncNodeDialog(this.getEditor(), funvo,
    // initData,
    // linklisten, true, false);

  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    int selectRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();

    boolean isEnable =
        this.model.getUiState() == UIState.EDIT && selectRow > -1;

    return isEnable;
  }

  // private OrderBomBVO[] buildOrderBomBVOs(SaleOrderHVO orderhead,
  // CfgBomDataViewVO[] bomconfigs) {
  //
  // OrderBomBVO[] bombodys = new OrderBomBVO[bomconfigs.length];
  // int i = 0;
  // for (CfgBomDataViewVO view : bomconfigs) {
  // bombodys[i] = new OrderBomBVO();
  // bombodys[i].setBselectflag(UFBoolean.FALSE);
  // bombodys[i].setPk_org(orderhead.getPk_org());
  // bombodys[i].setDbilldate(new UFDate());
  // // ����bom����
  // CfgBomVO cfghead = (CfgBomVO) view.getVO(CfgBomVO.class);
  // bombodys[i].setChmaterailid(cfghead.getCmaterialid());
  // bombodys[i].setChmaterialvid(cfghead.getCmaterialvid());
  // // ����bom�ӱ�
  // CfgBomItemVO cfgbody = (CfgBomItemVO) view.getVO(CfgBomItemVO.class);
  // bombodys[i].setCbmaterialid(cfgbody.getCmaterialid());
  // bombodys[i].setCbmaterialvid(cfgbody.getCmaterialvid());
  // bombodys[i].setCunitid(cfgbody.getCmeasureid());
  // bombodys[i].setNminnum(cfgbody.getNlowlimitnum());
  // bombodys[i].setNmaxnum(cfgbody.getNupperlimitnum());
  // i++;
  // }
  //
  // return bombodys;
  // }
  //
  // private OrderBomVO buildOrderBomByCfg(SaleOrderHVO orderhead,
  // SaleOrderBVO orderbody, CfgBomDataViewVO[] bomconfigs) {
  //
  // OrderBomVO bomvo = new OrderBomVO();
  //
  // OrderBomHVO bomhead = this.buildOrderBomHVO(orderhead, orderbody);
  // bomvo.setParentVO(bomhead);
  //
  // OrderBomBVO[] bombodys = this.buildOrderBomBVOs(orderhead, bomconfigs);
  // bomvo.setChildrenVO(bombodys);
  // return bomvo;
  // }
  //
  // private OrderBomHVO buildOrderBomHVO(SaleOrderHVO orderhead,
  // SaleOrderBVO orderbody) {
  // OrderBomHVO bomhead = new OrderBomHVO();
  // // ������֯
  // bomhead.setPk_org(orderhead.getPk_org());
  // bomhead.setPk_org_v(orderhead.getPk_org_v());
  // // ��������
  // bomhead.setDbilldate(new UFDate());
  // // ����
  // bomhead.setPk_group(orderhead.getPk_group());
  // // �����ͻ�
  // bomhead.setCordercustid(orderhead.getCcustomerid());
  // // ��������
  // bomhead.setCorigcurrencyid(orderhead.getCorigcurrencyid());
  // // ��������
  // bomhead.setCordermaterialvid(orderbody.getCmaterialvid());
  // bomhead.setCordermaterialid(orderbody.getCmaterialid());
  // // ��������
  // bomhead.setCsaleorderid(orderbody.getCsaleorderid());
  // bomhead.setCsaleorderbid(orderbody.getCsaleorderbid());
  // // ���ۼ۸�
  // bomhead.setNsaleprice(orderbody.getNqtorigtaxprice());
  // // ��������
  // bomhead.setNrequirenum(orderbody.getNnum());
  //
  // return bomhead;
  // }
  //
  // private void checkOrderBomValidate(SaleOrderHVO voHead, SaleOrderBVO
  // voBody) {
  //
  // String ordercust = voHead.getCcustomerid();
  // String ordercurr = voHead.getCorigcurrencyid();
  // String ordermaterial = voBody.getCmaterialvid();
  // String sendstockorg = voBody.getCsendstockorgid();
  // // ���ǿ���
  // if (VOChecker.isEmpty(ordercust) || VOChecker.isEmpty(ordercurr)
  // || VOChecker.isEmpty(ordermaterial) || VOChecker.isEmpty(sendstockorg)) {
  // ExceptionUtils
  // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006011_0", "04006011-0004")/*@res "����ѡ�񶩵��ͻ������֡����ϡ����������֯"*/);
  // }
  // // ��鶩��������
  // String[] materialprokeys = new String[] {
  // MaterialProdVO.PK_MATERIAL, MaterialProdVO.ISCONFIGABLE
  // };
  // UFBoolean config = null;
  // Map<String, MaterialProdVO> mapProd =
  // MaterialPubService.queryMaterialProduceInfoByPks(new String[] {
  // ordermaterial
  // }, sendstockorg, materialprokeys);
  // if (null != mapProd.get(ordermaterial)) {
  // config = mapProd.get(ordermaterial).getIsconfigable();
  // }
  // if (null == config || !config.booleanValue()) {
  // ExceptionUtils
  // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006011_0", "04006011-0005")/*@res "�����ϲ��ɽ�������"*/);
  // }
  // // ��������
  // UFDouble ordernum = voBody.getNnum();
  // if (null == ordernum || ordernum.compareTo(UFDouble.ZERO_DBL) == 0) {
  // ExceptionUtils
  // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006011_0", "04006011-0006")/*@res "����������������"*/);
  // }
  // }
  //
  // private OrderBomVO[] getOrderBom(SaleOrderHVO voHead, SaleOrderBVO voBody)
  // {
  // // �Ƿ���й���������
  // String bomid = "";
  // OrderBomVO orderbomvo = null;
  // if (bomid.length() == 0) {
  // orderbomvo = this.getOrderBomFromMM(voHead, voBody);
  // }
  // else {
  // orderbomvo = this.queryOrderBom(bomid);
  // }
  // OrderBomVO[] treeboms =
  // OrderBomTreeBuilder.getInstance().getTreeAggVOs(orderbomvo);
  //
  // return treeboms;
  // }
  //
  // private OrderBomVO getOrderBomFromMM(SaleOrderHVO voHead, SaleOrderBVO
  // voBody) {
  // String pk_group = voHead.getPk_group();
  // String stockorg = voBody.getCsendstockorgid();
  // String materialvid = voBody.getCmaterialvid();
  // CfgBomDataViewVO[] bomconfigs = null;
  //
  // Map<String, CfgBomDataViewVO[]> mapCfgbom =
  // MMPacServiceForSO.queryCfgBomDataViewByMaterialIds(pk_group, stockorg,
  // new String[] {
  // materialvid
  // });
  // if (null != mapCfgbom) {
  // bomconfigs = mapCfgbom.get(materialvid);
  // }
  //
  // if (null == bomconfigs || bomconfigs.length == 0) {
  // ExceptionUtils
  // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006011_0", "04006011-0007")/*@res "������û������BOM����"*/);
  // }
  // return this.buildOrderBomByCfg(voHead, voBody, bomconfigs);
  // }
  //
  // private OrderBomVO queryOrderBom(String bomid) {
  // OrderBomVO retVO = null;
  // IOrderBomServiceTo30 service =
  // NCLocator.getInstance().lookup(IOrderBomServiceTo30.class);
  // try {
  // retVO = service.queryOrderBom(bomid);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // return retVO;
  // }
}
