package nc.pubimpl.so.m33.so.m30;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.balend.enumeration.VirtualBalType;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.so.m33.so.m30.ISquare4CQryFor30SqEnd;
import nc.pubitf.so.m33.so.m30.RetVOFor30;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���۳�����㵥Ϊ�����ر��ṩ����ӿ�
 * 
 * @since 6.1
 * @version 2012-11-29 11:10:49
 * @author ��ӱ�
 */
public class Square4CQryFor30SqEndImpl implements ISquare4CQryFor30SqEnd {

  @Override
  public Map<String, VirtualBalType> query4CVirtualBalType(String[] ordBids) {
    // ��ѯ���۳�������㵥������Ϣ
    SquareOutViewVO[] svvos = this.queryViewVOByOrdBID(ordBids);

    // �����ݽ�����Ϣ���鷵�ؽ��
    Map<String, VirtualBalType> ret = new HashMap<String, VirtualBalType>();
    for (SquareOutViewVO svvo : svvos) {
      // ���ⵥ���η�Ʊ������������Ϊ��Ʊֻ���Զ��������ۼ�ȷ��Ӧ������=�ۼƽ���������
      UFDouble invSquNum = UFDouble.ZERO_DBL;
      if (!VOChecker.isEmpty(svvo.getItem().getNdownarnum())) {
        invSquNum = svvo.getItem().getNdownarnum();
      }

      // ���۳��ⵥ�ۼƳ���Գ�����
      UFDouble rushNum = UFDouble.ZERO_DBL;
      if (!VOChecker.isEmpty(svvo.getItem().getNrushnum())) {
        rushNum = svvo.getItem().getNrushnum();
      }

      // ���ⵥ�ɽ�������=���ⵥ����-���η�Ʊ�ۼƽ�������-���ⵥ�ۼƳ���Գ�����
      UFDouble enSquareNum =
          svvo.getItem().getNnum().sub(invSquNum).sub(rushNum);

      // ���ⵥ�������
      if (svvo.getItem().getBsquarearfinish().booleanValue()
          && svvo.getItem().getBsquareiafinish().booleanValue()) {
        ret.put(svvo.getItem().getCfirstbid(), VirtualBalType.BALED);
      }
      else if (enSquareNum.compareTo(UFDouble.ZERO_DBL) == 0) {
        ret.put(svvo.getItem().getCfirstbid(), VirtualBalType.HALFBAL);
      }
      else {
        ret.put(svvo.getItem().getCfirstbid(), VirtualBalType.NOTBAL);
      }
    } // end for

    return ret;
  }

  @Override
  public SquareOutViewVO[] queryETViewVOByOrdBIDForOrderEnd(String[] ordBids) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder(
            "select so_squareout_b.csalesquarebid from so_squareout_b inner join so_squareout on ");
    hsql.append(" so_squareout_b.CSALESQUAREID = so_squareout.CSALESQUAREID and so_squareout.dr=0 and so_squareout.BRETURNOUTSTOCK = 'N' ");
    hsql.append("where so_squareout_b.dr = 0 and so_squareout_b.fpreartype="
        + SquareType.SQUARETYPE_ET.getIntValue()
        + " and isnull(so_squareout_b.nsquareestnum,0)!= 0 ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    hsql.append(" and so_squareout_b.");
    hsql.append(iq.buildSQL(SquareOutBVO.CFIRSTBID, ordBids));
    QuerySquare4CVOBP qrybase = new QuerySquare4CVOBP();
    String[] bids = qrybase.queryIDsFromSql(hsql.toString());
    SquareOutViewVO[] ret = null;
    if (!VOChecker.isEmpty(bids)) {
      ret = qrybase.querySquareOutViewVOByBID(bids);
    }
    return ret;
  }

  @Override
  public SquareOutViewVO[] queryREGViewVOByOrdBIDForOrderEnd(String[] ordBids) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder(
            "select so_squareout_b.csalesquarebid from so_squareout_b inner join so_squareout on ");
    hsql.append(" so_squareout_b.CSALESQUAREID = so_squareout.CSALESQUAREID and so_squareout.dr=0 and so_squareout.BRETURNOUTSTOCK = 'N' ");
    hsql.append("where so_squareout_b.dr = 0 and so_squareout_b.fpreiatype="
        + SquareType.SQUARETYPE_REG_DEBIT.getIntValue()
        + " and isnull(so_squareout_b.nsquareregnum,0)!= 0 ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    hsql.append(" and so_squareout_b.");
    hsql.append(iq.buildSQL(SquareOutBVO.CFIRSTBID, ordBids));
    QuerySquare4CVOBP qrybase = new QuerySquare4CVOBP();
    String[] bids = qrybase.queryIDsFromSql(hsql.toString());
    SquareOutViewVO[] ret = null;
    if (!VOChecker.isEmpty(bids)) {
      ret = qrybase.querySquareOutViewVOByBID(bids);
    }
    return ret;
  }

  @Override
  public RetVOFor30[] querySqEndByOrdBID(String[] ordBids) {
    SquareOutViewVO[] vvos = this.queryViewVOByOrdBID(ordBids);
    RetVOFor30[] retvos = null;
    Map<String, RetVOFor30> map = new HashMap<String, RetVOFor30>();
    if (!VOChecker.isEmpty(vvos)) {
      for (SquareOutViewVO svvo : vvos) {
        SquareOutBVO bvo = svvo.getItem();
        RetVOFor30 rvo = map.get(bvo.getCfirstbid());
        if (rvo == null) {
          rvo = new RetVOFor30();
          map.put(bvo.getCfirstbid(), rvo);
        }
        rvo.setBid(bvo.getCfirstbid());
        rvo.setBsquarearfinish(bvo.getBsquarearfinish());
        rvo.setBsquareiafinish(bvo.getBsquareiafinish());
      }
    }
    if (map.size() > 0) {
      retvos = map.values().toArray(new RetVOFor30[map.size()]);
    }
    return retvos;
  }

  @Override
  public SquareOutViewVO[] queryViewVOByOrdBID(String[] ordBids) {
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    String[] bids = qry.querySquareOutVOPKsBy30BID(ordBids);
    SquareOutViewVO[] ret = null;
    if (!VOChecker.isEmpty(bids)) {
      ret = qry.querySquareOutViewVOByBID(bids);
    }
    return ret;
  }

}
