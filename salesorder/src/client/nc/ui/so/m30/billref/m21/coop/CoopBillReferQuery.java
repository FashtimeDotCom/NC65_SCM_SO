package nc.ui.so.m30.billref.m21.coop;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QPurchaseOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class CoopBillReferQuery extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public CoopBillReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    // ��ʼ�γ�����Լ��
    this.initFilterRef(dlgDelegator);
    // �����ӱ������ֶ�
    this.processBodyItem(dlgDelegator);

    // ����Ȩ��
    dlgDelegator.setPowerEnable(true);

    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "pk_puorg"
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // �ɹ���֯���� ����Эͬ����
    QPurchaseOrgFilter puorg =
        new QPurchaseOrgFilter(condDLGDelegator, "pk_puorg");
    puorg.filter();
    // �������Ͳ��չ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // // ���ϱ���:����������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // ���ϻ�������
    // QMarbasclassFilter marbasClass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialid.pk_marbasclass");
    // marbasClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // marbasClass.addEditorListener();
    // // �ջ��ͻ�
    // QCustomerFilter invoiceCust =
    // new QCustomerFilter(condDLGDelegator, "so_saleorder_b.creceivecustid");
    // invoiceCust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // invoiceCust.addEditorListener();

    // �������۲���
    QDeptFilter deptFilter =
        QDeptFilter
            .createDeptFilterOfSO(condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfSO(condDLGDelegator,
            SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();

    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode")
        .addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        "so_saleorder_b.pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        "so_saleorder_b.dbilldate");
  }
}
