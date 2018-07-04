package nc.pubimpl.so.m30.mmdp.altc;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m30.mmdp.altc.IQueryToExcuteSaleOrderViewVOs;
import nc.ui.pub.bill.BillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.ArrayUtil;

/**
 * ����������Ľӿ�ʵ��
 * 
 * @since 6.36
 * @version 2015-1-22 ����6:37:56
 * @author ��¼
 */
public class QueryToExcuteSaleOrderViewVOsImpl implements
    IQueryToExcuteSaleOrderViewVOs {

  @Override
  public SaleOrderViewVO[] queryToExcuteSaleOrderViewVOs4PO(
      String[] csaleorderbids) throws BusinessException {
    SaleOrderViewVO[] results = null;
    try {
      results = this.creatSqlByOrderBIDs4PO(csaleorderbids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return results;
  }

  @Override
  public SaleOrderViewVO[] queryToExcuteSaleOrderViewVOs4MO(
      String[] csaleorderbids) throws BusinessException {
    SaleOrderViewVO[] results = null;
    try {
      results = this.creatSqlByOrderBIDs4MO(csaleorderbids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return results;
  }

  private SaleOrderViewVO[] creatSqlByOrderBIDs4PO(String[] csaleorderbids) {

    if (ArrayUtil.isEmpty(csaleorderbids)) {
      return null;
    }

    String sql =
        createSqlByOrderBIDs(csaleorderbids, SaleOrderBVO.NTOTALPLONUM);
    return queryViewVOsBySQL(sql);
  }

  private SaleOrderViewVO[] creatSqlByOrderBIDs4MO(String[] csaleorderbids) {
    if (ArrayUtil.isEmpty(csaleorderbids)) {
      return null;
    }

    String sql =
        createSqlByOrderBIDs(csaleorderbids, SaleOrderBVO.NARRANGEMONUM);
    return queryViewVOsBySQL(sql);
  }

  private SaleOrderViewVO[] queryViewVOsBySQL(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql).toOneDimensionStringArray();
    SaleOrderViewVO[] views =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    return views;
  }

  /**
   * ������=���ۿ���=��״̬=����̬������������ֱ�����ͱ�� = ֱ�˲ɹ�������ر� = ��
   * ���۶�������-�ۼư����빺������-�ۼư��Ųɹ���������-�ۼư��ŵ�����������-�ۼư��ŵ�����������-�ۼư���ί�ⶩ������-�ۼư��Ž��ں�ͬ������-
   * �ۼư��������������� > 0
   * 
   * @param csaleorderbids
   * @param naccnum
   * @return
   */
  private String createSqlByOrderBIDs(String[] csaleorderbids, String naccnum) {

    SqlBuilder where = new SqlBuilder();
    where.append("select distinct(so_saleorder_b.csaleorderbid) ");

    where.append(" from so_saleorder_b so_saleorder_b ");
    where
        .append(" inner join so_saleorder_exe so_saleorder_exe on so_saleorder_b.csaleorderbid = so_saleorder_exe.csaleorderbid ");
    where
        .append(" inner join so_saleorder so_saleorder on so_saleorder.csaleorderid = so_saleorder_b.csaleorderid ");

    where.append(" where so_saleorder_b.dr = 0 ");
    where.append(" and (so_saleorder_b.");
    where.append(SaleOrderBVO.BLABORFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (so_saleorder_b.");
    where.append(SaleOrderBVO.BDISCOUNTFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (so_saleorder.");
    where.append(SaleOrderHVO.FSTATUSFLAG, BillStatus.AUDIT);
    where.append(") ");

    where.append(" and (so_saleorder_b.");
    where.append(SaleOrderBVO.BBOUTENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    // SQL������£�and abs(isnull(nnum,0) - isnull(narrangepoappnum,0) -
    // isnull(narrangeponum,0) - isnull(narrangetoappnum,0)"
    // - isnull(narrangetoornum,0) - isnull(narrangescornum,0) -
    // isnull(narrangeitcnum,0) - isnull("
    // + naccnum + ",0)) > 0 ");

    where.append(" and abs(isnull(");
    where.append(SaleOrderBVO.NNUM);
    where.append(" ,0) ");
    appSpSql(where, SaleOrderBVO.NARRANGEPOAPPNUM);
    appSpSql(where, SaleOrderBVO.NARRANGEPONUM);
    appSpSql(where, SaleOrderBVO.NARRANGETOAPPNUM);
    appSpSql(where, SaleOrderBVO.NARRANGETOORNUM);
    appSpSql(where, SaleOrderBVO.NARRANGESCORNUM);
    appSpSql(where, SaleOrderBVO.NARRANGEITCNUM);
    appSpSql(where, naccnum);
    where.append(") > 0 ");

    return where.toString();

  }

  private void appSpSql(SqlBuilder where, String nnumKey) {
    where.append(" -isnull(");
    where.append(nnumKey);
    where.append(" ,0) ");
  }

}
