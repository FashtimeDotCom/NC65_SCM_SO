package nc.pubimpl.so.m30.mmpps.lotreg;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;

import nc.pubitf.mmpub.sdmanage.IBillTraceParam;
import nc.pubitf.mmpub.sdmanage.IBillTraceResult;
import nc.pubitf.so.m30.mmpps.lotreg.IQuerySaleOrderForLotreg;
import nc.pubitf.so.m30.mmpps.lotreg.SaleOrderTraceResult;

/**
 * ���۶���Ϊ"����Ԥ��ά��"�ڵ��ṩ�ĵ���׷�ݽӿ�ʵ����
 * 
 * @since 6.3.1
 * @version 2013-08-21 09:00:33
 * @author ���Ʒ�
 * 
 */
public class QuerySaleOrderForLotRegImpl implements IQuerySaleOrderForLotreg {

  @Override
  public List<IBillTraceResult> getBills(IBillTraceParam billTraceParam) {

    String fromSql = this.getFrom(billTraceParam);
    String whereSql = this.getWhere(billTraceParam);

    List<IBillTraceResult> resultList = new ArrayList<IBillTraceResult>();
    SaleOrderTraceResult result = new SaleOrderTraceResult();

    result.setFromSql(fromSql);
    result.setWhereSql(whereSql);
    resultList.add(result);

    return resultList;

  }

  /**
   * ���whereƬ��
   * 
   * @param billTraceParam ����׷�ݲ�����
   * @return where��
   */
  private String getWhere(IBillTraceParam billTraceParam) {

    String tableName = billTraceParam.getTableName();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" so_saleorder.dr", 0);
    sql.append(" and so_saleorder_b.dr", 0);
    sql.append("and so_saleorder_b.vsrctype", SOBillType.PreOrder.getCode());
    sql.append(" and so_saleorder_b.csrcbid = " + tableName + "."
        + billTraceParam.getSrcBid());
    return sql.toString();
  }

  /**
   * ���fromƬ��
   * 
   * @param billTraceParam ����׷�ݲ�����
   * @return from��
   */
  private String getFrom(IBillTraceParam billTraceParam) {
    String tableName = billTraceParam.getTableName();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" so_saleorder_b so_saleorder_b inner join so_saleorder so_saleorder ");
    sql.append(" on so_saleorder_b.csaleorderid = so_saleorder.csaleorderid ");
    sql.append(" inner join " + tableName + " " + tableName);
    sql.append(" on so_saleorder_b.csrcid = " + tableName + "."
        + billTraceParam.getSrcId());
    return sql.toString();
  }
}
