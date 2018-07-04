package nc.pubimpl.so.m33.self.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;

import nc.pubitf.so.m33.self.pub.ISquare434CQuery;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���۽���Ϊ���۳��ⵥ�ṩ�Ĳ�ѯ����ʵ����
 * 
 * @since 6.1
 * @version 2012-11-29 10:19:13
 * @author ��ӱ�
 */
public class Square434CQueryImpl implements ISquare434CQuery {

  private QuerySquare4CVOBP queryBP;

  /**
   * ������
   */
  public Square434CQueryImpl() {
    this.queryBP = new QuerySquare4CVOBP();
  }

  @Override
  public Map<String, UFDouble[]> queryARNumBy4CBID(String[] outBids) {
    Map<String, UFDouble[]> mOutBidNum =
        this.queryARNumMnyBy4CBIDAndSquareType(outBids,
            SquareType.SQUARETYPE_AR);
    return mOutBidNum;
  }

  @Override
  public Map<String, UFDouble[]> queryARRushNumBy4CBID(String[] outBids) {
    Map<String, UFDouble[]> mOutBidNum =
        this.queryARNumMnyBy4CBIDAndSquareType(outBids,
            SquareType.SQUARETYPE_ARRUSH);
    return mOutBidNum;
  }

  @Override
  public String[] queryETIncomeBidBy4CBID(String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ET.getIntegerValue());
    String[] etOutBids =
        AggVOUtil.getDistinctFieldArray(sdvos,
            SquareOutDetailVO.CSQUAREBILLBID, String.class);
    return etOutBids;
  }

  @Override
  public Map<String, SquareOutDetailVO> queryETIncomeDvosBy4CBID(
      String[] outBids) {
	Map<String, SquareOutDetailVO> map = new HashMap<String, SquareOutDetailVO>();
	if(outBids==null||outBids.length==0){
		return map;
	}
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ET.getIntegerValue());

    if (sdvos != null) {
      for (SquareOutDetailVO dvo : sdvos) {
        map.put(dvo.getCsquarebillbid(), dvo);
      }
    }
    return map;
  }

  @Override
  public SquareOutViewVO[] queryETIncomeREGCostBidBy4CBID(String[] outBids) {
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquarebid from so_squareout_b where dr = 0 and ");
    hsql.append("(");
    hsql.append("(fpreartype", SquareType.SQUARETYPE_ET.getIntValue());
    hsql.append(" and isnull(nsquareestnum,.0)!=0) ");
    hsql.append(" or ");
    hsql.append("(fpreiatype", SquareType.SQUARETYPE_REG_DEBIT.getIntValue());
    hsql.append(" and isnull(nsquareregnum,.0)!=0) ");
    hsql.append(") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outBids);
    hsql.append(where);
    String[] bids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutViewVOByBID(bids);
  }

  /**
   * ���ݳ��ⵥ����DI��ѯ�ݹ�Ӧ�յĳ��ⵥ������ϸVO
   * 
   * @param outBids
   * @return ���ⵥ������ϸVO
   */
  public SquareOutDetailVO[] queryETIncomeSquareOutDetailVOBy4CBID(
      String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ET.getIntegerValue());
    return sdvos;
  }

  @Override
  public String[] queryREGCostBidBy4CBID(String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_REG_DEBIT.getIntegerValue());
    String[] etOutBids =
        AggVOUtil.getDistinctFieldArray(sdvos,
            SquareOutDetailVO.CSQUAREBILLBID, String.class);
    return etOutBids;
  }

  /**
   * ���ݳ��ⵥ����DI��ѯ���뷢����Ʒ�ĳ��ⵥ������ϸVO
   * 
   * @param outBids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryREGCostSquareOutDetailVOBy4CBID(
      String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_REG_DEBIT.getIntegerValue());
    return sdvos;
  }

  /**
   * �ǳ���Գ����۳��ⵥ��������Ʒ������ϸ����
   * 
   * @param bids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryREGCreditSquareOutDetailVOByBID(String[] bids) {
    String[] dids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquaredid from so_squareout_d where dr = 0 and ");
    hsql.append(" boutrushflag = 'N' and ");
    hsql.append("fsquaretype", SquareType.SQUARETYPE_REG_CREDIT.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids);
    hsql.append(where);
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    dids = qry.queryIDsFromSql(hsql.toString());
    return qry.querySquareOutDetailVOByPK(dids);
  }

  /**
   * ���ݳ��ⵥ����DI��ѯ�س�Ӧ�յĳ��������ϸVO
   * 
   * @param outBids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryRushIncomeSquareOutDetailVOBy4CBID(
      String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ARRUSH.getIntegerValue());
    return sdvos;
  }

  /**
   * �ǳ���Գ����۳��ⵥ���س�Ӧ����ϸ����
   * 
   * @param bids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryRushIncomeSquareOutDetailVOByBID(String[] bids) {
    String[] dids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquaredid from so_squareout_d where dr = 0 and ");
    hsql.append(" boutrushflag = 'N' and ");
    hsql.append("fsquaretype", SquareType.SQUARETYPE_ARRUSH.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids);
    hsql.append(where);
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    dids = qry.queryIDsFromSql(hsql.toString());
    return qry.querySquareOutDetailVOByPK(dids);
  }

  @Override
  public SquareOutDetailVO[] querySquareOutDetailVOBy4CBID(String[] bidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  @Override
  public SquareOutDetailVO[] querySquareOutDetailVOByPK(String[] outDetailPKs) {
    return this.queryBP.querySquareOutDetailVOByPK(outDetailPKs);
  }

  @Override
  public SquareOutViewVO[] querySquareOutViewVOBy4CBID(String[] outbids) {
    return this.queryBP.querySquareOutViewVOBy4CBID(outbids);
  }

  /**
   * ���ݳ��ⵥBID��ѯδ�س�Ӧ�յĳ��ⵥ������ͼVO
   * 
   * @param bids
   * @return ���������ͼVO
   */
  public SquareOutViewVO[] querySquareOutViewVOByBIDForNoETRushSquare(
      String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squareout_b where ");
    sql.append("(isnull(so_squareout_b.narrushnum,.0)=0 ");
    sql.append("and so_squareout_b.fpreartype="
        + SquareType.SQUARETYPE_ET.getIntValue() + ") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, bids));

    return new QuerySquare4CVOBP().querySquareOutViewVO(sql.toString());
  }

  /**
   * ���ݳ��ⵥBID��ѯδ���뷢����Ʒ�ĳ��������ͼVO
   * 
   * @param bids
   * @return ���������ͼVO
   */
  public SquareOutViewVO[] querySquareOutViewVOByBIDForNoREGSquare(String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squareout_b where ");
    sql.append("(isnull(so_squareout_b.nsquareregnum,.0)=0 and ");
    sql.append("so_squareout_b.fpreiatype="
        + SquareType.SQUARETYPE_REG_DEBIT.getIntValue() + ") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, bids));
    return new QuerySquare4CVOBP().querySquareOutViewVO(sql.toString());
  }

  /**
   * �������γ��ⵥ��ID�ʹ���������Ͳ�ѯ���λ���ǩ�տ�Ʊ�˻صĳ��ⵥ�����Ϣ
   * 
   * @param outBids
   * @param type
   * @return
   */
  private Map<String, UFDouble[]> queryARNumMnyBy4CBIDAndSquareType(
      String[] outBids, SquareType type) {
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select sob.csrcbid ,sum(isnull(sob.NNUM,0)) arnum, ");
    hsql.append("sum(isnull(norigtaxmny,.0)) artaxmny ");
    hsql.append(",sum(isnull(norigmny,.0)) armny ");
    hsql.append("from so_squareout so inner join SO_SQUAREOUT_B sob ");
    hsql.append("on so.CSALESQUAREID = sob.CSALESQUAREID ");
    hsql.append("and so.BRETURNOUTSTOCK = 'Y' and so.dr = 0 AND sob.dr = 0 ");
    hsql.append("where sob.vsrctype = '4C' and ");
    hsql.append("fpreartype", type.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareInvBVO.CSRCBID, outBids);
    hsql.append(where);
    hsql.append(" group by sob.csrcbid ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(hsql.toString());

    // ��װ���--<���ⵥ��id,�ۼƻس�Ӧ������>
    Map<String, UFDouble[]> mOutBidNum = null;
    int size = rowset.size();
    if (size > 0) {
      mOutBidNum = new HashMap<String, UFDouble[]>();
      while (rowset.next()) {
        mOutBidNum.put(rowset.getString(0), new UFDouble[] {
          rowset.getUFDouble(1), rowset.getUFDouble(2), rowset.getUFDouble(3)
        });
      }
    }
    return mOutBidNum;

  }

  private SquareOutDetailVO[] querySquareOutDetailVOBy4CBIDUseSquareType(
      String[] outBids, Integer squareType) {
    String[] dids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquaredid from so_squareout_d where dr = 0 and ");
    hsql.append("fsquaretype", squareType);
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outBids);
    hsql.append(where);
    dids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutDetailVOByPK(dids);
  }

}
