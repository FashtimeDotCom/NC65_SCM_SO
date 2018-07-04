package nc.bs.so.m33.maintain.m32.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���۳�����㵥��ѯ����BP
 * 
 * @author zhangcheng
 * 
 */
public class QuerySquare32VOBP {

  /**
   * ��ѯ���۷�Ʊ���ε���Դ�ڷ������ĳ��ⵥ
   * 
   * @param minv
   * @return <4cbid,4331bid>
   */
  public Map<String, String> query4C4331bid(Map<String, SquareInvViewVO> minv) {
    SquareInvViewVO[] views =
        minv.values().toArray(new SquareInvViewVO[minv.size()]);
    Set<String> setoutbid = new HashSet<String>();
    for (SquareInvViewVO view : views) {
      String vsrctype = view.getItem().getVsrctype();
      if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        setoutbid.add(view.getItem().getCsrcbid());
      }
    }

    Map<String, String> map = new HashMap<String, String>();
    // ���۷�Ʊ��Դ�����۳��ⵥ
    int size = setoutbid.size();
    if (size > 0) {
      map = this.query4C4331bid(setoutbid.toArray(new String[size]));
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
  public String[] queryIDsFromSql(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();
    return cbillids;
  }

  /**
   * �������۷�Ʊ�����㵥����ID��ѯ�����㵥VO
   * 
   * @param bids
   * @return <bid,SquareOutViewVO>
   */
  public Map<String, SquareInvViewVO> queryMapSquareInvViewVOByBID(String[] bids) {
    // ����id��ѯVO
    ViewQuery<SquareInvViewVO> query =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] bills = query.query(bids);

    Map<String, SquareInvViewVO> map = new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO view : bills) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    return map;
  }

  /**
   * �������۽�����ϸID��ѯ���۽��㵥��ϸVO
   * 
   * @param hids
   * @return ���۽��㵥��ϸVO
   */
  public SquareInvDetailVO[] querySquareInvDetailVOByPK(String[] hids) {

    if (hids == null || hids.length == 0) {
      return null;
    }

    // ����id��ѯVO
    VOQuery<SquareInvDetailVO> query =
        new VOQuery<SquareInvDetailVO>(SquareInvDetailVO.class);
    SquareInvDetailVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * �������۳�������㵥��ͷID��ѯ���㵥��ϸVO
   * 
   * @param hids -- ���۽��㵥��ͷID
   * @return ���㵥��ϸVO
   */
  public SquareInvDetailVO[] querySquareInvDetailVOBySQHID(String[] hids) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_SquareInv_d ");
    hsql.append("where so_SquareInv_d.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvDetailVO.CSALESQUAREID, hids);
    hsql.append(where);
    String[] newhids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareInvDetailVOByPK(newhids);
  }

  /**
   * ����������������ѯ��ͼvo
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql -- ��ѯ�ӱ�id��sql�����
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-6-10 ����02:56:20
   */
  public SquareInvViewVO[] querySquareInvViewVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.��ѯ����
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() == 0) {
      return null;
    }
    String[] bids = rowset.toOneDimensionStringArray();

    // 2.��ѯ��ͼvo
    ViewQuery<SquareInvViewVO> bquery =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] vos = bquery.query(bids);

    return vos;
  }

  /**
   * ���ݴ����㵥���ⵥid��ѯ�����㵥��ͼVO
   * 
   * @param invBidValues
   * @return �����㵥��ͼVO
   */
  public SquareInvViewVO[] querySquareInvViewVOBy32BID(String[] invBidValues) {
    if (VOChecker.isEmpty(invBidValues)) {
      return null;
    }
    String[] bids = this.querySquareInvVOPKsBy32BID(invBidValues);
    return this.querySquareInvViewVOByBID(bids);
  }

  /**
   * ���ݴ����㵥���ⵥid��ѯ�����㵥��ͼVO
   * 
   * @param hidValues
   * @return �����㵥��ͼVO
   */
  public SquareInvViewVO[] querySquareInvViewVOBy32ID(String[] hidValues) {
    String[] hids = this.querySquareInvVOPKsBy32ID(hidValues);
    return this.querySquareInvViewVOByPK(hids);
  }

  /**
   * ���ݴ����㵥��Դ����ID����Դ��ͷID��ѯ�����㵥��ͼVO
   * 
   * @param outbids
   * @param outhids
   * @param querykeys
   * @return SquareInvViewVO[]
   */
  public SquareInvViewVO[] querySquareInvViewVOBy4CBIDHID(String[] outbids,
      String[] outhids, String[] querykeys) {
    if (SOVOChecker.isEmpty(outbids) || SOVOChecker.isEmpty(outhids)) {
      return null;
    }
    StringBuilder sqlwhere = new StringBuilder(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String bidwhere = iq.buildSQL(SquareInvBVO.CSRCBID, outbids);
    sqlwhere.append(bidwhere);
    sqlwhere.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    String hidwhere = iq.buildSQL(SquareInvBVO.CSRCID, outhids);
    sqlwhere.append(hidwhere);

    EfficientViewQuery<SquareInvViewVO> viewQuery =
        new EfficientViewQuery<SquareInvViewVO>(SquareInvViewVO.class,
            querykeys);
    return viewQuery.query(sqlwhere.toString());
  }

  /**
   * �������۷�Ʊ�����㵥����ID��ѯ�����㵥VO
   * 
   * @param bids
   * @return �����㵥ViewVO
   */
  public SquareInvViewVO[] querySquareInvViewVOByBID(String[] bids) {
    // ����id��ѯVO
    ViewQuery<SquareInvViewVO> query =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] bills = query.query(bids);
    return bills;
  }

  /**
   * �������۳�������㵥��ͷID��ѯ�����㵥VO
   * 
   * @param hids
   * @return �����㵥ViewVO
   */
  public SquareInvViewVO[] querySquareInvViewVOByPK(String[] hids) {
    // ����id��ѯVO
    ViewQuery<SquareInvViewVO> query =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * ���ݴ����㵥���ⵥid��ѯ�����㵥��ͼVO
   * 
   * @param hidValues
   * @return �����㵥��ͼVO
   */
  public SquareInvVO[] querySquareInvVOBy32ID(String[] hidValues) {
    String[] hids = this.querySquareInvVOPKsBy32ID(hidValues);
    return this.querySquareInvVOByPK(hids);
  }

  /**
   * �������۳�������㵥��ͷID��ѯ�����㵥VO
   * 
   * @param hids
   * @return �����㵥VO
   */
  public SquareInvVO[] querySquareInvVOByPK(String[] hids) {
    // ����id��ѯVO
    BillQuery<SquareInvVO> query =
        new BillQuery<SquareInvVO>(SquareInvVO.class);
    SquareInvVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * ��ѯ���۷�Ʊ���ε���Դ�ڷ������ĳ��ⵥ
   * 
   * @param outbids -- ���۳��ⵥ����id
   * @return <4cbid,4331bid>
   */
  private Map<String, String> query4C4331bid(String[] outbids) {
    Map<String, String> map = new HashMap<String, String>();
    // ��ѯ���۳�������㵥
    SquareOutViewVO[] outviews =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outbids);

    // tianft 2013-05-28 �����˿��ʱ��Ӧ�����ⲿ����Դ�����������
    List<SquareOutViewVO> redOutFromSelf = new ArrayList<SquareOutViewVO>();
    Set<String> redoutidFromSelf = new HashSet<String>();
    for (SquareOutViewVO view : outviews) {
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)) {
        map.put(view.getItem().getCsquarebillbid(), view.getItem().getCsrcbid());
      }
      // tianft 2013-05-28 ���ⵥ��Դ�Լ��������ȼ�¼���������һ���ǲ�����Դ������
      else if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        redOutFromSelf.add(view);
        redoutidFromSelf.add(view.getItem().getCsrcbid());
      }
    }
    // ������ֳ����Ӧ�����Գ�����Դ�����������
    if (redOutFromSelf.size() > 0) {
      SquareOutViewVO[] blueviews =
          new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(redoutidFromSelf
              .toArray(new String[redoutidFromSelf.size()]));
      Map<String, SquareOutViewVO> blueViewMap =
          new HashMap<String, SquareOutViewVO>();
      if (ArrayUtils.isEmpty(blueviews)) {
        return map;
      }
      for (SquareOutViewVO view : blueviews) {
        blueViewMap.put(view.getItem().getCsquarebillbid(), view);
      }
      for (SquareOutViewVO redView : redOutFromSelf) {
        if (blueViewMap.containsKey(redView.getItem().getCsrcbid())) {
          SquareOutViewVO blueView =
              blueViewMap.get(redView.getItem().getCsrcbid());
          String vsrctype = blueView.getItem().getVsrctype();
          // ������ֳ��ⵥ��Դ����������Ӧ�������ֳ��ⵥ�͸÷���������ϵ
          if (SOBillType.Delivery.getCode().equals(vsrctype)) {
            map.put(redView.getItem().getCsquarebillbid(), blueView.getItem()
                .getCsrcbid());
          }
        }
      }
    }
    return map;
  }

  /**
   * ���ݴ����㵥���۷�ƱBid��ѯ�����㵥BID����
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareInvVOPKsBy32BID(String[] invBidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareinv_b ");
    hsql.append("where so_squareinv_b.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvBVO.CSQUAREBILLBID, invBidValues);
    hsql.append(where);
    String[] bids = this.queryIDsFromSql(hsql.toString());
    return bids;
  }

  /**
   * ���ݴ����㵥���ⵥid��ѯ�����㵥PK����
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareInvVOPKsBy32ID(String[] hidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquareid from so_SquareInv ");
    hsql.append("where so_SquareInv.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvHVO.CSQUAREBILLID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return hids;
  }

}
