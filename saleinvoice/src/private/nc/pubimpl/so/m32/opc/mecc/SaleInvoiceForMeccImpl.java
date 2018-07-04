package nc.pubimpl.so.m32.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.m32.opc.mecc.SaleInvoiceQueryConditionVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m32.opc.mecc.ISaleInvoiceForMecc;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ��ѯ��Ʊ��Ϣ�ӿ�ʵ��
 * 
 * @since 6.3
 * @version 2012-10-22 ����08:16:34
 * @author ������
 */
public class SaleInvoiceForMeccImpl implements ISaleInvoiceForMecc {

  /**
   * �������۶�����ѯ��Ʊ
   * 
   * @param saleorderhids ���۶�����ͷID����
   * @param saleorderbids ���۶�������ID����
   * @param fieldnames ��Ʊ�ֶ�����
   * @return ���۷�ƱviewVO����
   * @throws BusinessException
   */
  @Override
  public SaleInvoiceViewVO[] querySaleInvoiceByOrder(String[] saleorderhids,
      String[] saleorderbids, String[] fieldnames) throws BusinessException {
    if (null == saleorderhids || saleorderhids.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0145")/*���۶�����ͷIDΪ��*/);
    }
    if (null == saleorderbids || saleorderbids.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0146")/*���۶�������IDΪ��*/);
    }
    if (null == fieldnames || fieldnames.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0147")/*��Ʊ�ֶ�����Ϊ��*/);
    }
    SaleInvoiceViewVO[] saleInvoiceViewVOs = null;
    try {
      // ��ѯ����
      SqlBuilder conditions = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      conditions.append(" and so_saleinvoice." + SaleInvoiceHVO.PK_GROUP,
          pk_group);
      conditions.append(" and so_saleinvoice_b." + SaleInvoiceBVO.PK_GROUP,
          pk_group);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      conditions.append(iq.buildSQL(" and so_saleinvoice_b."
          + SaleInvoiceBVO.CFIRSTID, saleorderhids));
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      conditions.append(iq.buildSQL(" and so_saleinvoice_b."
          + SaleInvoiceBVO.CFIRSTBID, saleorderbids));
      EfficientViewQuery<SaleInvoiceViewVO> voQuery =
          new EfficientViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class,
              fieldnames);
      voQuery.addQueryTable(SaleInvoiceHVO.CREATOR);
      voQuery.addQueryTable(SaleInvoiceBVO.CFIRSTID);
      saleInvoiceViewVOs = voQuery.query(conditions.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleInvoiceViewVOs;
  }

  @Override
  public SaleInvoiceViewVO[] querySaleInvoiceByCondition(
      SaleInvoiceQueryConditionVO condition, String[] fieldnames)
      throws BusinessException {

    // ��ò�ѯ����
    String[] customer = condition.getCustomer();
    String begindate = condition.getBegindate();
    String enddate = condition.getEnddate();
    String[] billstatus = condition.getBillstatus();

    SaleInvoiceViewVO[] saleInvoiceViewVOs = null;
    try {
      // ��ò�ѯSQL
      SqlBuilder conditions = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      conditions.append(" and so_saleinvoice." + SaleInvoiceHVO.PK_GROUP,
          pk_group);
      conditions.append(" and so_saleinvoice_b." + SaleInvoiceBVO.PK_GROUP,
          pk_group);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      conditions.append(iq.buildSQL(" and so_saleinvoice_b."
          + SaleInvoiceBVO.CORDERCUSTID, customer));
      conditions.append(" and (so_saleinvoice.dbilldate  >= '" + begindate
          + "' ");
      conditions.append(" and so_saleinvoice.dbilldate <= '" + enddate + "') ");
      conditions.append(" and (so_saleinvoice_b.dbilldate  >= '" + begindate
          + "' ");
      conditions.append(" and so_saleinvoice_b.dbilldate <= '" + enddate
          + "') ");
      if (null != billstatus && billstatus.length > 0) {
        iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
        conditions.append(iq.buildSQL(" and so_saleinvoice."
            + SaleInvoiceHVO.FSTATUSFLAG, billstatus));
      }
      EfficientViewQuery<SaleInvoiceViewVO> voQuery =
          new EfficientViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class,
              fieldnames);
      voQuery.addQueryTable(SaleInvoiceBVO.CORDERCUSTID);
      voQuery.addQueryTable(SaleInvoiceHVO.FSTATUSFLAG);
      saleInvoiceViewVOs = voQuery.query(conditions.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleInvoiceViewVOs;
  }
}
