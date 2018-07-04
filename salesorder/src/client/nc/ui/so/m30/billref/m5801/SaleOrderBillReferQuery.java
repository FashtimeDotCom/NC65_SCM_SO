package nc.ui.so.m30.billref.m5801;

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
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

/**
 * 
 * @since JCK 6.31
 * @version 2014-03-17 11:26:40
 * @author zhangyfr
 */
public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  /**
   * 
   * @param c
   * @param info
   */
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
    // ������������:�������۶�����ֱ�˱�ǵĽ�������¼�롣
    condDLGDelegator.setRefFilter(SaleOrderHVO.CTRANTYPEID, new IRefFilter() {

      @Override
      public void doFilter(UIRefPane refPane) {
        StringBuilder where = new StringBuilder();
        where.append(" and pk_billtypecode in(");
        where.append(" select so_m30trantype.vtrantypecode");
        where.append(" from so_m30trantype where so_m30trantype.fdirecttype=");
        where.append(DirectType.DIRECTTRAN_NO.getIntValue());
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
    
    //�������۲���
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
    condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
    condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();  
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

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
