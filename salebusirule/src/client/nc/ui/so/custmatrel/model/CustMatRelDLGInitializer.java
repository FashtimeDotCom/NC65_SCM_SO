package nc.ui.so.custmatrel.model;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.SOItemKey;

public class CustMatRelDLGInitializer implements IQueryConditionDLGInitializer {
	@Override
	  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
	    this.initRefCondition(condDLGDelegator);
	    // ����֯Ȩ��
	    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
	      SOItemKey.PK_ORG
	    });
	    
	    // ��ʼ�γ�����Լ��
	    this.initFilterRef(condDLGDelegator);
	    
	  }
	
	private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
	    RefCommonFilterListener filterutil =
	        new RefCommonFilterListener(condDLGDelegator, CustMatRelHVO.PK_ORG);

	    filterutil.addFilterMapsListeners();
	  }

	  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
	    // �ͻ�����
	    QCustomerFilter cordercustid =
	        new QCustomerFilter(condDLGDelegator, CustMatRelHVO.PK_CUSTOMER);
	    cordercustid.addEditorListener();
	  }
}
