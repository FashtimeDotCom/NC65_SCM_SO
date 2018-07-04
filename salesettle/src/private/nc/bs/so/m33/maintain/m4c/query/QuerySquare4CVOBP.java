package nc.bs.so.m33.maintain.m4c.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
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
public class QuerySquare4CVOBP {

  /**
   * ��ѯǩ���˻ص����۳��ⵥ���ε���Դ�ڷ��������������۳��ⵥ
   * 
   * @param mout
   * @return <����4cbid,4331bid>
   */
  public Map<String, String> query4C4331bid(Map<String, SquareOutViewVO> mout) {
    SquareOutViewVO[] views =
        mout.values().toArray(new SquareOutViewVO[mout.size()]);
    // �������ֳ�������㵥bid
    Set<String> setoutbid = new HashSet<String>();
    for (SquareOutViewVO view : views) {
      UFBoolean flag =
          ValueUtils.getUFBoolean(view.getHead().getBreturnoutstock());
      if (flag.booleanValue()) {
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
   * �������۳�������㵥����ID��ѯ�����㵥VO
   * 
   * @param bids
   * @return <bid,SquareOutViewVO>
   */
  public Map<String, SquareOutViewVO> queryMapSquareOutViewVOByBID(String[] bids) {
    // ����id��ѯVO
    ViewQuery<SquareOutViewVO> query =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    SquareOutViewVO[] bills = query.query(bids);

    Map<String, SquareOutViewVO> map = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : bills) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    return map;
  }

  /**
   * �������۳��ⵥ����ID��ѯ���㵥��ϸVO
   * 
   * @param bidValues -- ���۳��ⵥ����ID
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] querySquareOutDetailVOBy4CBID(String[] bidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareOutDetailVOByPK(hids);
  }

  /**
   * �������۽�����ϸID��ѯ���۽��㵥��ϸVO
   * 
   * @param hids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] querySquareOutDetailVOByPK(String[] hids) {

    if (hids == null || hids.length == 0) {
      return null;
    }

    // ����id��ѯVO
    VOQuery<SquareOutDetailVO> query =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
    SquareOutDetailVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * �������۳�������㵥����ID��ѯ���㵥��ϸVO
   * 
   * @param bidValues -- ���۽��㵥����ID
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] querySquareOutDetailVOBySQBID(String[] bidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareOutDetailVOByPK(hids);
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
  public SquareOutViewVO[] querySquareOutViewVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.��ѯ����
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() == 0) {
      return null;
    }
    String[] bids = rowset.toOneDimensionStringArray();

    // 2.��ѯ��ͼvo
    SquareOutViewVO[] vos = this.querySquareOutViewVOByBID(bids);

    return vos;
  }

  /**
   * ���ݴ����㵥���ⵥ��id��ѯ�����㵥��ͼVO
   * 
   * @param outbids
   * @return ���������ϸViewVO
   */
  public SquareOutViewVO[] querySquareOutViewVOBy4CBID(String[] outbids) {
    String[] bids = this.querySquareOutVOPKsBy4CBID(outbids);
    return this.querySquareOutViewVOByBID(bids);
  }

  /**
   * �������۳�������㵥����ID��ѯ�����㵥VO
   * 
   * @param bids
   * @return ���������ͼVO
   */
  public SquareOutViewVO[] querySquareOutViewVOByBID(String[] bids) {
    // ����id��ѯVO
    ViewQuery<SquareOutViewVO> query =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    SquareOutViewVO[] bills = query.query(bids);
    return bills;
  }

  /**
   * �������۳�������㵥��ͷID��ѯ�����㵥VO
   * 
   * @param hids
   * @return ���������ͼVO
   */
  public SquareOutViewVO[] querySquareOutViewVOByPK(String[] hids) {
    // ����id��ѯVO
    ViewQuery<SquareOutViewVO> query =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    SquareOutViewVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * ���ݴ����㵥���ⵥid��ѯ�����㵥��ͼVO
   * 
   * @param hidValues
   * @return �������VO
   */
  public SquareOutVO[] querySquareOutVOBy4CID(String[] hidValues) {
    String[] hids = this.querySquareOutVOPKsBy4CID(hidValues);
    return this.querySquareOutVOByPK(hids);
  }

  /**
   * �������۳�������㵥��ͷID��ѯ�����㵥VO
   * 
   * @param hids
   * @return �������VO
   */
  public SquareOutVO[] querySquareOutVOByPK(String[] hids) {
    // ����id��ѯVO
    BillQuery<SquareOutVO> query =
        new BillQuery<SquareOutVO>(SquareOutVO.class);
    SquareOutVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * �������۶�����id��ѯ�����㵥��id
   * 
   * @param ordbids
   * @return ��������ӱ�ID
   */
  public String[] querySquareOutVOPKsBy30BID(String[] ordbids) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareout_b ");
    hsql.append("where so_squareout_b.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutBVO.CFIRSTBID, ordbids);
    hsql.append(where);
    String[] bids = this.queryIDsFromSql(hsql.toString());
    return bids;
  }

  /**
   * ���ݾɵĳ��������ͼˢ������
   * 
   * @param views
   * @return �µĳ��������ͼVO
   */
  public SquareOutViewVO[] refushView(SquareOutViewVO[] views) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(views, SquareOutBVO.CSALESQUAREBID);

    Map<String, SquareOutViewVO> oldmap =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      oldmap.put(view.getItem().getCsalesquarebid(), view);
    }

    SquareOutViewVO[] newview = this.querySquareOutViewVOByBID(bids);

    this.setValueForrefushVO(oldmap, newview);

    return newview;
  }

  /**
   * ���ݾɵĳ������VO����ˢ������
   * 
   * @param svos
   * @return �µĳ������VO
   */
  public SquareOutVO[] refushVO(SquareOutVO[] svos) {
    SquareOutViewVO[] oldviews =
        SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(svos);
    Map<String, SquareOutViewVO> oldmap =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : oldviews) {
      oldmap.put(view.getItem().getCsalesquarebid(), view);
    }
    String[] bids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSALESQUAREBID,
            String.class);
    SquareOutViewVO[] newview = this.querySquareOutViewVOByBID(bids);

    this.setValueForrefushVO(oldmap, newview);

    SquareOutVO[] newsvos = SquareOutVOUtils.getInstance().combineBill(newview);
    return newsvos;
  }

  /**
   * ��ѯǩ���˻ص����۳��ⵥ���ε���Դ�ڷ��������������۳��ⵥ
   * 
   * @param outbids -- ���۳��ⵥ����id
   * @return <4cbid,4331bid>
   */
  public Map<String, String> query4C4331bid(String[] outbids) {
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
   * ���ݴ����㵥���ⵥ��id��ѯ�����㵥��id
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareOutVOPKsBy4CBID(String[] bidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareout_b ");
    hsql.append("where so_squareout_b.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutBVO.CSQUAREBILLBID, bidValues);
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
  private String[] querySquareOutVOPKsBy4CID(String[] hidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquareid from so_squareout ");
    hsql.append("where so_squareout.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutHVO.CSQUAREBILLID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return hids;
  }

  /**
   * ���ڴ�DB��ˢ��vo�󣬽���vo�еļ������Ը�ֵ����vo��
   * 
   * @param oldmap -- ��vo map
   * @param newview -- �� vo
   */
  private void setValueForrefushVO(Map<String, SquareOutViewVO> oldmap,
      SquareOutViewVO[] newview) {
    for (SquareOutViewVO view : newview) {
      String bid = view.getItem().getCsalesquarebid();
      SquareOutViewVO oldview = oldmap.get(bid);
      view.getItem().setCsalesquaredid(oldview.getItem().getCsalesquaredid());
    }
  }

  /**
   * ���ݴ����㵥���ⵥ��id��ѯ�����㵥����Դ��bid����Դ�ڷ�������)
   * 
   * @param ibills
   * @return
   */
  public Map<String, String> queryOutSrcidBy4CBID(String[] bidValues) {
    VOQuery<SquareOutBVO> quey =
        new VOQuery<SquareOutBVO>(SquareOutBVO.class, new String[] {
          SquareOutBVO.CSQUAREBILLBID, SquareOutBVO.CSRCBID,
          SquareOutBVO.VSRCTYPE
        });
    // ��ѯ��ͷid
    SqlBuilder hsql = new SqlBuilder();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutBVO.CSQUAREBILLBID, bidValues);
    hsql.append(" and ");
    hsql.append(where);
    SquareOutBVO[] outbvos = quey.query(hsql.toString(), null);
    Map<String, String> outsrcmap = new HashMap<String, String>();
    for (SquareOutBVO outbvo : outbvos) {
      String vsrctype = outbvo.getVsrctype();
      if (!PubAppTool.isNull(vsrctype)
          && SOBillType.Delivery.getCode().equals(vsrctype))
        outsrcmap.put(outbvo.getCsquarebillbid(), outbvo.getCsrcbid());
    }
    return outsrcmap;
  }
}
