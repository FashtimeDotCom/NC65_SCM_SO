/**
 * 
 */
package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.custmatrel.ICustMatRelQueryResult;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.custmatrel.view.CardForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * @author gdsjw
 * 
 */
@SuppressWarnings("serial")
public class CustMatRelRefreshAction extends DefaultRefreshAction {

  private CardForm editor = null;

  public CardForm getEditor() {
    return this.editor;
  }

  public void setEditor(CardForm editor) {
    this.editor = editor;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getDataManager().refresh();
    ModelDataManager data = (ModelDataManager) this.getDataManager();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(data.getQueryScheme());
    // ��ȡ��ѯ����
    String pk_org = this.getQueryValue(qrySchemeProcessor, "pk_org");
    String pk_custbaseclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custbaseclass");
    String pk_custsaleclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custsaleclass");
    String pk_customer =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_customer");
    String pk_marbasclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialbaseclass");
    String pk_marsaleclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialsaleclass");
    String pk_material =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_material");
    // ����ѯģ���ϵ���֯��ֵ����ͷ
    this.getEditor().getOrgPanel().setPkOrg(pk_org);

    // ��ѯ����ڱ�ͷ����ǰ��ѡ��Ĳ�ѯ����
    saveQueryValue(pk_org, pk_custbaseclass, pk_custsaleclass, pk_customer,
        pk_marbasclass, pk_marsaleclass, pk_material);

  }

  /**
   * �����ѯ������ֵ����ͷ
   * 
   * @param pk_custbaseclass
   * @param pk_custsaleclass
   * @param pk_customer
   */
  private void saveQueryValue(String pk_org, String pk_custbaseclass,
      String pk_custsaleclass, String pk_customer, String pk_marbasclass,
      String pk_marsaleclass, String pk_material) {
    if (!PubAppTool.isNull(pk_custbaseclass)) {
      this.getEditor().getBillCardPanel().getHeadItem(CustMatRelHVO.PK_ORG)
          .setValue(pk_org);
    }
    if (!PubAppTool.isNull(pk_custbaseclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_CUSTBASECLASS)
          .setValue(pk_custbaseclass);
    }
    if (!PubAppTool.isNull(pk_custsaleclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_CUSTSALECLASS)
          .setValue(pk_custsaleclass);
    }
    if (!PubAppTool.isNull(pk_customer)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_CUSTOMER).setValue(pk_customer);
    }
    if (!PubAppTool.isNull(pk_marbasclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_MATERIALBASECLASS)
          .setValue(pk_marbasclass);
    }
    if (!PubAppTool.isNull(pk_marsaleclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_MATERIALSALECLASS)
          .setValue(pk_marsaleclass);
    }
    if (!PubAppTool.isNull(pk_material)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_MATERIAL).setValue(pk_material);
    }
  }

  /**
   * ��ȡ��ѯ����ֵ
   * 
   * @param qrySchemeProcessor
   * @param code
   * @return
   */
  private String getQueryValue(QuerySchemeProcessor qrySchemeProcessor,
      String code) {
    String[] queryConditionValue = null;
    if (qrySchemeProcessor.getQueryCondition(code) != null) {
      queryConditionValue =
          qrySchemeProcessor.getQueryCondition(code).getValues();
      if (queryConditionValue[0].trim().startsWith("select")) {
        // ��ȡ��Ӧ�Ĳ�ѯ������ֵ
        String field =
            NCLocator.getInstance().lookup(ICustMatRelQueryResult.class)
                .custMatRelQueryResult(queryConditionValue);
        return field;
      }
      // ����ÿ����ѯ������ֻ����ѡһ����ѯ����������ȡ�����ڵ�һ��ֵ
      return queryConditionValue[0];
    }
    return null;
  }

  @Override
  protected void showQueryInfo() {
    int size = 0;
    if (this.getModel() instanceof BillManageModel) {
      size = ((BillManageModel) this.getModel()).getData().size();

    }
    else if (this.getModel() instanceof BatchBillTableModel) {
      size = ((BatchBillTableModel) this.getModel()).getRows().size();
    }
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0010",
              null, new String[] {
                Integer.toString(size)
              })/*ˢ����ɣ���ˢ��{0}�ŵ��ݡ�*/, this.getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0010",
              null, new String[] {
                Integer.toString(size)
              })/*ˢ����ɣ���ˢ��{0}�ŵ��ݡ�*/, this.getModel().getContext());
    }
  }
}
