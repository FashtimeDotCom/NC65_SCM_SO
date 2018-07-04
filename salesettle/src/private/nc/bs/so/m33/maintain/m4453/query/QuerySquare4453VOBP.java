package nc.bs.so.m33.maintain.m4453.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ;�𵥴����㵥��ѯBP��
 * 
 * @since 6.1
 * @version 2012-11-29 09:56:35
 * @author ��ӱ�
 */
public class QuerySquare4453VOBP {

  /**
   * ��ѯ;�����ε���Դ�ڷ������ĳ��ⵥ
   * 
   * @param mwas
   * @return <4cbid,4331bid>>
   */
  public Map<String, String> query4C4331bid(Map<String, SquareWasViewVO> mwas) {
    SquareWasViewVO[] views =
        mwas.values().toArray(new SquareWasViewVO[mwas.size()]);
    Set<String> setoutbid = new HashSet<String>();
    for (SquareWasViewVO view : views) {
      String vsrctype = view.getItem().getVsrctype();
      if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        setoutbid.add(view.getItem().getCsrcbid());
      }
    }

    Map<String, String> map = new HashMap<String, String>();
    // ;����Դ�����۳��ⵥ
    int size = setoutbid.size();
    if (size > 0) {
      map = this.query4C4331bid(setoutbid.toArray(new String[size]));
    }

    return map;
  }

  /**
   * ����;������㵥����ID��ѯ�����㵥VO
   * 
   * @param bids
   * @return <bid,SquareOutViewVO>
   */
  public Map<String, SquareWasViewVO> queryMapSquareWasViewVOByBID(String[] bids) {
    // ����id��ѯVO
    ViewQuery<SquareWasViewVO> query =
        new ViewQuery<SquareWasViewVO>(SquareWasViewVO.class);
    SquareWasViewVO[] bills = query.query(bids);

    Map<String, SquareWasViewVO> map = new HashMap<String, SquareWasViewVO>();
    for (SquareWasViewVO view : bills) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    return map;
  }

  /**
   * ����������ѯ;����㵥
   * 
   * @param condition ��ѯ���� ���磺and name='cc'
   * @param order ��������
   * @return ;����㵥��ϸVO
   */
  public SquareWasDetailVO[] querySquareWasDetailVOByCondition(
      String condition, String order) {
    VOQuery<SquareWasDetailVO> query =
        new VOQuery<SquareWasDetailVO>(SquareWasDetailVO.class);
    return query.query(condition, order);
  }

  /**
   * �������۽�����ϸID��ѯ���۽��㵥��ϸVO
   * 
   * @param hids
   * @return ;����㵥��ϸVO
   */
  public SquareWasDetailVO[] querySquareWasDetailVOByPK(String[] hids) {

    if (hids == null || hids.length == 0) {
      return null;
    }

    // ����id��ѯVO
    VOQuery<SquareWasDetailVO> query =
        new VOQuery<SquareWasDetailVO>(SquareWasDetailVO.class);
    SquareWasDetailVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * �������۳�������㵥��ͷID��ѯ���㵥��ϸVO
   * 
   * @param hidValues -- ���۽��㵥��ͷID
   * @return ;����㵥��ϸVO
   */
  public SquareWasDetailVO[] querySquareWasDetailVOBySQHID(String[] hidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squarewas_d ");
    hsql.append("where so_squarewas_d.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareWasDetailVO.CSALESQUAREID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareWasDetailVOByPK(hids);
  }

  /**
   * ����SQL����ѯ;�������ͼVO
   * 
   * @param sql
   * @return ;�������ͼVO
   */
  public SquareWasViewVO[] querySquareWasViewVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.��ѯ����
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() == 0) {
      return null;
    }
    String[] bids = rowset.toOneDimensionStringArray();

    // 2.��ѯ��ͼvo
    SquareWasViewVO[] vos = this.querySquareWasViewVOByBID(bids);

    return vos;
  }

  /**
   * �������۳��ⵥ��id��ѯ��;����㵥
   * 
   * @param outBids
   * @return ;�������ͼVO
   */
  public SquareWasViewVO[] querySquareWasViewVOBy4CBID(String[] outBids) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squarewas_b where ");
    hsql.append("so_squarewas_b.dr = 0 and so_squarewas_b.vsrctype='4C' and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareWasBVO.CSRCBID, outBids);
    hsql.append(where);
    String[] bids = this.queryIDsFromSql(hsql.toString());
    SquareWasViewVO[] svvos = null;
    if (!VOChecker.isEmpty(bids)) {
      svvos = this.querySquareWasViewVOByBID(bids);
    }
    return svvos;
  }

  /**
   * ����;������㵥����ID��ѯ�����㵥VO
   * 
   * @param bids
   * @return ;�������ͼVO
   */
  public SquareWasViewVO[] querySquareWasViewVOByBID(String[] bids) {
    // ����id��ѯVO
    ViewQuery<SquareWasViewVO> query =
        new ViewQuery<SquareWasViewVO>(SquareWasViewVO.class);
    SquareWasViewVO[] bills = query.query(bids);
    return bills;
  }

  /**
   * ���ݴ����㵥;��id��ѯ�����㵥VO
   * 
   * @param hidValues
   * @return ;����㵥VO
   */
  public SquareWasVO[] querySquareWasVOBy4453ID(String[] hidValues) {
    String[] hids = this.querySquareWasVOPKsBy4453ID(hidValues);
    return this.querySquareWasVOByPK(hids);
  }

  /**
   * ����;������㵥��ͷID��ѯ�����㵥VO
   * 
   * @param hids
   * @return ;����㵥VO
   */
  public SquareWasVO[] querySquareWasVOByPK(String[] hids) {
    // ����id��ѯVO
    BillQuery<SquareWasVO> query =
        new BillQuery<SquareWasVO>(SquareWasVO.class);
    SquareWasVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * ��ѯ;�����ε���Դ�ڷ������ĳ��ⵥ
   * 
   * @param outbids -- ���۳��ⵥ����id
   * @return <4cbid,4331bid>
   */
  private Map<String, String> query4C4331bid(String[] outbids) {
    Map<String, String> map = new HashMap<String, String>();
    // ��ѯ���۳�������㵥
    SquareOutViewVO[] outviews =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outbids);
    for (SquareOutViewVO view : outviews) {
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)) {
        map.put(view.getItem().getCsquarebillbid(), view.getItem().getCsrcbid());
      }
    }
    return map;
  }

  /**
   * ������������������sql����ѯ���ز�ѯ���ID����
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql -- select id from ...........
   * @return id����
   *         <p>
   * @author zhangcheng
   * @time 2010-7-21 ����01:31:07
   */
  private String[] queryIDsFromSql(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();
    return cbillids;
  }

  /**
   * ���ݴ����㵥;��id��ѯ�����㵥PK����
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareWasVOPKsBy4453ID(String[] hidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquareid from so_squarewas ");
    hsql.append("where so_squarewas.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareWasHVO.CSQUAREBILLID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return hids;
  }

}
