package nc.ui.so.m4331.billui.editor.bodyevent;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.rule.UnitChangeRateRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;

public class MateriaEditHandler {

  private DeliveryEditor editor;

  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);

    // --���ն�ѡ����
    UIRefPane ref =
        (UIRefPane) cardPanel.getBodyItem(e.getKey()).getComponent();
    String[] refPKs = ref.getRefPKs();
    if (refPKs!=null&&refPKs.length > 1) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006002_0", "04006002-0026")/*�������ѡ����ѡ��һ����*/);
    }
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());

    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);
    int row = e.getRow();
    String materialid =
        keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALVID);
    Map<String, String> mapunit =
        MaterialPubService.querySaleMeasdocIDByPks(new String[] {
          materialid
        });
    String defaultunit = mapunit.get(materialid);
    if (VOChecker.isEmpty(defaultunit)) {
      String unitid = keyValue.getBodyStringValue(row, DeliveryBVO.CUNITID);
      keyValue.setBodyValue(row, DeliveryBVO.CASTUNITID, unitid);
      keyValue.setBodyValue(row, DeliveryBVO.CQTUNITID, unitid);
    }
    else {
      keyValue.setBodyValue(row, DeliveryBVO.CASTUNITID, defaultunit);
      keyValue.setBodyValue(row, DeliveryBVO.CQTUNITID, defaultunit);
    }

    // �����ջ���������������˰����Ϣ
    SOCountryInfoRule conutry = new SOCountryInfoRule(keyValue);
    conutry.setCountryInfoByPk_Org(rows);
    // ���ù������͡�����ó��
    SOBuysellTriaRule buysellTria = new SOBuysellTriaRule(keyValue);
    buysellTria.setBuysellAndTriaFlag(rows);
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setDeliveryTaxInfo(rows);

    // ���㻻����
    UnitChangeRateRule changeraterule = new UnitChangeRateRule(keyValue);
    changeraterule.calcAstChangeRate(row);
    changeraterule.calcQTChangeRate(row);
  }

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UIRefPane refPane =
        (UIRefPane) util.getBodyItem(DeliveryBVO.CMATERIALVID).getComponent();
    refPane.setMultiSelectedEnabled(true);
    FilterMaterialRefUtils filtermaterial = new FilterMaterialRefUtils(refPane);
    filtermaterial.filterIsSealedShow(UFBoolean.FALSE);

    /*�¶���   �Ϸ���ԪNC������Ŀ ���������������ϣ������޸�Ϊ�滻�����ϡ��޸��滻���󣬷����������ε��ݹ�ϵ���ֲ���*/
    int row = e.getRow();
    String srcmaterialid =
        keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALVID);
    String csrcbid = keyValue.getBodyStringValue(row, DeliveryBVO.CSRCBID);
    if (null == this.getEditor().getCachematerialid()) {
      this.getEditor().setCachematerialid(new HashMap<String, String>());
    }
    String oldmaterialid = this.getEditor().getCachematerialid().get(csrcbid);
    if (PubAppTool.isNull(oldmaterialid)) {
      this.getEditor().getCachematerialid().put(csrcbid, srcmaterialid);
    }
    SqlBuilder sqlwhere = new SqlBuilder();
    sqlwhere.append(" enablestate =2 ");
    sqlwhere.append(" and ");
    String newmaterialid = this.getEditor().getCachematerialid().get(csrcbid);
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

  /**
   * 
   * @return DeliveryEditor
   */
  public DeliveryEditor getEditor() {
    return this.editor;
  }

  /**
   * 
   * @param editor
   */
  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

}
