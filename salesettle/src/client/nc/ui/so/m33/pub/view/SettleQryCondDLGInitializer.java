package nc.ui.so.m33.pub.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.pub.SOItemKey;

public class SettleQryCondDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ù���
    this.setFilter(condDLGDelegator);

    // ���ñ�����������
    this.setRedundancyInfo(condDLGDelegator);

    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯�ɼ��Ŀ�Ʊ�ͻ�
    new QCustomerFilter(condDLGDelegator, SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.CINVOICECUSTID).addEditorListener();

    // ������֯�ɼ��Ĳ���
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfSO(condDLGDelegator,
            SquareOutBVO.CSALESQUAREBID + "." + SquareOutBVO.CDEPTID);
    deptFilter.setPk_orgCode(SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.CSALEORGID);
    deptFilter.addEditorListener();
    
    // ���ϱ���
    QMarterialoidFilter marteral =
        new QMarterialoidFilter(condDLGDelegator, "csendstockorgid",
            "csalesquarebid.cmaterialid.code");
    marteral.addEditorListener();
    
    // ������֯�ɼ���ҵ��Ա
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfSO(condDLGDelegator,
            "csalesquarebid.cemployeeid");
    psnFilter.setPk_orgCode(SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.CSALEORGID);
    psnFilter.addEditorListener();

    // �������֯���˲ֿ�
    new QWareHouseFilter(condDLGDelegator, SquareOutHVO.CSENDSTOCKORGID,
        SquareOutHVO.CSENDSTORDOCID).addEditorListener();

    // ���������͹��˽�������
    new QTransTypeFilter(condDLGDelegator, SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.VFIRSTTRANTYPE, SOBillType.Order.getCode()).filter();

    // �������ε���
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(condDLGDelegator, "csalesquarebid.vbatchcode");
    
    new QFfileFilterByMaterCode(condDLGDelegator, "csalesquarebid.cmaterialid.code", "csalesquarebid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "csalesquarebid.cmaterialid.code", "csalesquarebid.cmffileid.vskucode").addEditorListener();

  }

  private void setRedundancyInfo(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.addRedundancyInfo(SquareOutHVO.DBILLDATE,
        SquareOutBVO.CSALESQUAREBID + "." + SquareOutBVO.DBILLDATE);
  }

}
