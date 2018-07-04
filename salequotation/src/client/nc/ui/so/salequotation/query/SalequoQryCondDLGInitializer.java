package nc.ui.so.salequotation.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pub.query.QueryTempletTotalVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoQryCondDLGInitializer implements
    IQueryConditionDLGInitializer {
  private SalequoModel model;

  public SalequoModel getModel() {
    return this.model;
  }

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    QueryTempletTotalVO totalVO = condDLGDelegator.getTotalVO();
    SalequoQueryDLGUtils.hideMarClCondition(totalVO, this.getModel()
        .getContext().getPk_group());
    SalequoQueryDLGUtils.hideCusClCondition(totalVO, this.getModel()
        .getContext().getPk_group());

    // ��ʼ�γ�����Լ��
    this.initFilterRef(condDLGDelegator);

    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // ����Ȩ��
    condDLGDelegator.setPowerEnable(true);
  }

  public void setModel(SalequoModel model) {
    this.model = model;
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {

    // ���۵��������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, PriceContext.SALEQUOBILLTYPE);
    trantype.filter();

    RefCommonFilterListener filterUtil =
        new RefCommonFilterListener(condDLGDelegator, SalequotationHVO.PK_ORG);
    filterUtil.addFilterMapsListeners();
    
    
   //�������۲���
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
    condDLGDelegator, SalequotationHVO.PK_DEPT);
    deptFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
    condDLGDelegator, SalequotationHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    psnFilter.addEditorListener();

  }

}
