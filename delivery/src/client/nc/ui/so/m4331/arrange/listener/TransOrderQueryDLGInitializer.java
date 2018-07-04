package nc.ui.so.m4331.arrange.listener;

import nc.ui.pubapp.uif2app.query2.DefaultQueryConditionDLG;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.pub.SOFunc;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;

public class TransOrderQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ���յ����������뽻������
    new QTransTypeFilter(condDLGDelegator, TOBillType.TransOrder.getCode())
        .filter();

    // ���ݿ����֯�����Ͻ��й���(��̬��)
    QMarterialoidFilter marterialFilter =
        new QMarterialoidFilter(condDLGDelegator, BillHeaderVO.CTOUTSTOCKORGID,
            "bodyfk.cinventoryid");
    marterialFilter.addEditorListener();
    // ���ݿ����֯�����Ͻ��й���(��̬��)
    QMarterialoidFilter marterialcodeFilter =
        new QMarterialoidFilter(condDLGDelegator, BillHeaderVO.CTOUTSTOCKORGID,
            "bodyfk.cinventoryid.code");
    marterialcodeFilter.addEditorListener();
    QMarbasclassFilter marbasclassFilter =
        new QMarbasclassFilter(condDLGDelegator,
            "bodyfk.cinventoryid.pk_marbasclass");
    marbasclassFilter.setPk_orgCode(BillHeaderVO.CTOUTSTOCKORGID);
    marbasclassFilter.addEditorListener();

    // ���ݿ����֯�Բֿ���й���
    QWareHouseFilter houseFilter1 =
        new QWareHouseFilter(condDLGDelegator, BillHeaderVO.PK_ORG,
            "bodyfk.coutstordocid");
    houseFilter1.addEditorListener();
    QWareHouseFilter houseFilter2 =
        new QWareHouseFilter(condDLGDelegator, BillHeaderVO.CTOUTSTOCKORGID,
            "bodyfk.ctoutstordocid");
    houseFilter2.addEditorListener();
    QWareHouseFilter houseFilter3 =
        new QWareHouseFilter(condDLGDelegator, BillHeaderVO.CINSTOCKORGID,
            "bodyfk.cinstordocid");
    houseFilter3.addEditorListener();

    // �ջ��ͻ����� ���ݵ�������֯
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, "bodyfk.creceivecustid");
    customerFilter.setPk_orgCode("cinstockorgid");
    customerFilter.addEditorListener();

    TemplateInfo tempalteinfo =
        ((DefaultQueryConditionDLG) condDLGDelegator.getQueryConditionDLG())
            .getTempInfo();
    tempalteinfo.setFunNode(SOFunc.N40060401.getCode());
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "bodyfk." + BillItemVO.CDELIVORGID
    });
    tempalteinfo.setFunNode(SOFunc.N40093010.getCode());
  }

}
