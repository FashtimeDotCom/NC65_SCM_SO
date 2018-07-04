package nc.ui.so.custmatrel.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.itf.so.custmatrel.ICustMatRelQueryResult;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.custmatrel.view.CardForm;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * 
 * @since 6.3
 * @version 2014-9-10 ����4:05:42
 * @author quyt
 */
@SuppressWarnings("restriction")
public class CustMatRelQueryAction extends DefaultQueryAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private CardForm editor = null;

  public CustMatRelQueryAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.QUERY);
  }

  public void executeQuery(IQueryScheme queryScheme) {

    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
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

    //�Ȳ�ѯ ������������10000�У������ʾ���󣬴�ʱ����Ϊ��ͷ��ֵ������������ť���á�
    super.executeQuery(queryScheme);
    
    // ����ѯģ���ϵ���֯��ֵ����ͷ
    this.getEditor().getOrgPanel().setPkOrg(pk_org);
    
    // ��ѯ����ڱ�ͷ����ǰ��ѡ��Ĳ�ѯ����
    saveQueryValue(pk_org, pk_custbaseclass, pk_custsaleclass, pk_customer,
        pk_marbasclass, pk_marsaleclass, pk_material);

    int row = this.getEditor().getBillCardPanel().getRowCount();
    for (int i = 0; i < row; i++) {
      this.getEditor().getBillCardPanel().getBillData().getBillModel()
          .loadEditRelationItemValue(i, CustMatRelBVO.PK_CUSTOMER);
      this.getEditor().getBillCardPanel().getBillData().getBillModel()
          .loadEditRelationItemValue(i, CustMatRelBVO.PK_MATERIAL_V);
    }

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
    CustMatRelVO desVO = (CustMatRelVO) getEditor().getValue();
    if (desVO != null) {
      CustMatRelBVO[] bvo = desVO.getChildrenVO();
      if (!ArrayUtil.isEmpty(bvo)) {
        size = bvo.length;
      }
    }
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0265", null, new String[] {
                "1" 
              })/*
                * @res
                * "��ѯ�ɹ����Ѳ鵽{0}�ŵ��ݡ�"
                */, this.getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          IShowMsgConstant.getQueryNullInfo(), this.getModel().getContext());
    }
  }

  public CardForm getEditor() {
    return this.editor;
  }

  public void setEditor(CardForm editor) {
    this.editor = editor;
  }

}
