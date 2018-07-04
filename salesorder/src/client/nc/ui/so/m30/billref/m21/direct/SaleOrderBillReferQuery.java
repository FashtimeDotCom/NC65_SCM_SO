package nc.ui.so.m30.billref.m21.direct;

import java.awt.Container;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.IRefFilter;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
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
      SaleOrderHVO.DEST_PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // ������������:�������۶���ֱ�˲ɹ���ǵĽ�������¼�롣
    condDLGDelegator.setRefFilter(SaleOrderHVO.CTRANTYPEID, new IRefFilter() {

      @Override
      public void doFilter(UIRefPane refPane) {
        StringBuilder where = new StringBuilder();
        where.append(" and pk_billtypecode in(");
        where.append(" select so_m30trantype.vtrantypecode");
        where.append(" from so_m30trantype where so_m30trantype.fdirecttype=");
        where.append(DirectType.DIRECTTRAN_PO.getIntValue());
        where.append(")");
        AbstractRefModel refModel = refPane.getRefModel();
        refModel.addWherePart(where.toString());
        FilterTransTypeRefUtils refUtil =
            new FilterTransTypeRefUtils(refPane, "");
        refUtil.filterTranType(new String[] {
          SOBillType.Order.getCode()
        });
      }
    });

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleOrderHVO.PK_ORG);
    String[] needfiltkeys = new String[] {
      "so_saleorder_b.cmaterialid.pk_marbasclass", "so_saleorder_b.cmaterialid"
    };
    // ���ϻ������ࣺ���ղɹ���֯�ɼ������ϻ������൵��
    // ���ϱ��룺���ղɹ���֯�ɼ������ϵ���
    filterutil.addFilterMaps(needfiltkeys, SaleOrderHVO.DEST_PK_ORG);
    filterutil.addFilterMapsListeners();

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
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();
    filterutil.removeFilterMaps(new String[] {"so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"});
    // // �ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    // QCustomerFilter cust =
    // new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
    // cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // cust.addEditorListener();
    //
    // // ���۲���:����������֯��Χ�Ĳ��ŵ���
    // QDeptFilter dept = new QDeptFilter(condDLGDelegator,
    // SaleOrderHVO.CDEPTID);
    // dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // dept.addEditorListener();
    //
    // // ����ҵ��Ա������������֯��Χ����Ա����
    // QPsndocFilter employee =
    // new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    // employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // employee.addEditorListener();
    //
    // // ���ϻ������ࣺ���ղɹ���֯�ɼ������ϻ������൵��
    // QMarbasclassFilter marbasClass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialid.pk_marbasclass");
    // marbasClass.setPk_orgCode(SaleOrderHVO.DEST_PK_ORG);
    // marbasClass.addEditorListener();
    //
    // // ���ϱ��룺���ղɹ���֯�ɼ������ϵ���
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, SaleOrderHVO.DEST_PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // �ջ���λ:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    // QCustomerFilter receivecust =
    // new QCustomerFilter(condDLGDelegator, "so_saleorder_b.creceivecustid");
    // receivecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // receivecust.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        SaleOrderBVO.METAPATH + "pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }

}
