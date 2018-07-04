package nc.bs.so.m33.maintain.m4c.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.constant.QueryFlag;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.so.m33.biz.m4c.bp.pub.ProcessWC;

import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ������㵥��ѯBP��
 * 
 * @since 6.1
 * @version 2012-11-29 10:00:20
 * @author ��ӱ�
 */
public class QuerySquare4CVOBizBP {

  private QuerySquare4CVOBP queryBP;

  /**
   * ������
   */
  public QuerySquare4CVOBizBP() {
    this.queryBP = new QuerySquare4CVOBP();
  }

  /**
   * �������۳��ⵥBID��ѯ�����ݹ����㵥
   * 
   * @param outbids
   * @return Map
   */
  public Map<String, SquareOutDetailVO> queryETSquareOutDetailVOBy4CBID(
      String[] outbids) {
    Map<String, SquareOutDetailVO> map =
        new HashMap<String, SquareOutDetailVO>();
    VOQuery<SquareOutDetailVO> qry =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String outbidWhere =
        idqb.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outbids);
    SqlBuilder where = new SqlBuilder();
    // 4cbid
    where.append(" and ");
    where.append(outbidWhere);
    // �ݹ����͵Ľ���
    where.append(" and ");
    where.append(SquareOutDetailVO.FSQUARETYPE,
        SquareType.SQUARETYPE_ET.getIntValue());
    SquareOutDetailVO[] retVOs = qry.query(where.toString(), null);
    if (!VOChecker.isEmpty(retVOs)) {
      for (SquareOutDetailVO retVO : retVOs) {
        map.put(retVO.getCsquarebillbid(), retVO);
      }
    }
    return map;
  }

  /**
   * �������۳�������㵥BID��ѯ�����ݹ����㵥
   * 
   * @param sqbids
   * @return ������㵥��ϸVO
   */
  public SquareOutDetailVO[] queryETSquareOutDetailVOBySQBID(String[] sqbids) {
    SquareOutDetailVO[] retVOs =
        this.querySquareOutDetailVOBySQBIDAndSquareType(sqbids,
            SquareType.SQUARETYPE_ET);
    return retVOs;
  }

  /**
   * �������۳�������㵥����id�ͽ������Ͳ�ѯ�ֹ���������
   * 
   * @param bids ���۳�������㵥����id
   * @param type ��������
   * @return ������㵥��ϸVO
   */
  public SquareOutDetailVO[] queryManualDetailVOBySQBIDandSQType(String[] bids,
      SquareType[] type) {
    SquareOutDetailVO[] sdvos = null;
    StringBuilder where = new StringBuilder();
    where.append(" and BAUTOSQUAREFLAG = 'N' ");
    where.append(" and dr = 0 ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    where.append(" and " + iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids));

    int len = type.length;
    where.append(" and (");
    for (int i = 0; i < len; i++) {
      if (i > 0) {
        where.append(" or fsquaretype = " + type[i].getIntValue());
      }
      else {
        where.append("fsquaretype = " + type[i].getIntValue());
      }
    }
    where.append(") ");

    sdvos =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class).query(
            where.toString(), null);

    return sdvos;
  }

  /**
   * �������۳��������ϸ��id�ͽ������Ͳ�ѯ�ֹ���������
   * 
   * @param bids ���۳�������㵥����id
   * @param processeids �������κ�
   * @param type ��������
   * @return ������㵥��ϸVO
   */
  public SquareOutDetailVO[] queryManualDetailVOBySQType(String[] processeids,
      String[] bids, SquareType[] type) {
    SquareOutDetailVO[] sdvos = null;
    StringBuilder where = new StringBuilder();
    where.append(" and BAUTOSQUAREFLAG = 'N' ");
    where.append(" and dr = 0 ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    where.append(" and "
        + iq.buildSQL(SquareOutDetailVO.PROCESSEID, processeids));

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where.append(" and " + iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids));

    int len = type.length;
    where.append(" and (");
    for (int i = 0; i < len; i++) {
      if (i > 0) {
        where.append(" or fsquaretype = " + type[i].getIntValue());
      }
      else {
        where.append("fsquaretype = " + type[i].getIntValue());
      }
    }
    where.append(") ");

    sdvos =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class).query(
            where.toString(), null);

    return sdvos;
  }

  /**
   * ���ݳ��ⵥBID��ѯ����Գ���ϸ��¼,Ϊ������ⵥ�Գ���Ϣ����
   * 
   * @param bidValues
   * @return ������㵥��ϸVO
   */
  public SquareOutDetailVO[] queryOutRushInfoForLinkQuery(String[] bidValues) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append(" where so_squareout_d.dr = 0 ");
    hsql.append(" and so_squareout_d.boutrushflag = 'Y' and so_squareout_d.FSQUARETYPE = "
        + SquareType.SQUARETYPE_OUTRUSH.getIntValue());
    hsql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);

    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());

    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  /**
   * ���ݳ��ⵥBID��ѯ����Գ���ϸ��¼
   * 
   * @param bidValues
   * @param rushbatchid
   * @return ������㵥��ϸVO
   */
  public SquareOutDetailVO[] queryOutRushSquareOutDetailVOBy4CBID(
      String[] bidValues, String[] rushbatchid) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    hsql.append("so_squareout_d.boutrushflag = 'Y' ");

    hsql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CRUSHOUTBATCHID, rushbatchid);
    hsql.append(where);

    hsql.append(" and ");
    where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);

    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());

    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  /**
   * ���ݶԳ���ⵥBID��ѯ����Գ���ϸ��¼
   * 
   * @param bidValues
   * @param rushbatchid
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryOutRushSquareOutDetailVOByRushOutBID(
      String[] bidValues, String[] rushbatchid) {
    // ��ѯ��ͷid
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    hsql.append("so_squareout_d.boutrushflag = 'Y' and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CRUSHOUTBATCHID, rushbatchid);
    hsql.append(where);
    hsql.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where = iq.buildSQL(SquareOutDetailVO.CRUSHGENERALBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  /**
   * �������۳�������㵥BID��ѯ���ⷢ����Ʒ�������㵥
   * 
   * @param sqbids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryREGCreditSquareOutDetailVOBySQBID(
      String[] sqbids) {
    SquareOutDetailVO[] retVOs =
        this.querySquareOutDetailVOBySQBIDAndSquareType(sqbids,
            SquareType.SQUARETYPE_REG_CREDIT);
    return retVOs;
  }

  /**
   * �������۳�������㵥BID��ѯ���ⷢ����Ʒ�跽���㵥
   * 
   * @param sqbids
   * @return ���������ϸVO
   */
  public SquareOutDetailVO[] queryREGDebitSquareOutDetailVOBySQBID(
      String[] sqbids) {
    SquareOutDetailVO[] retVOs =
        this.querySquareOutDetailVOBySQBIDAndSquareType(sqbids,
            SquareType.SQUARETYPE_REG_DEBIT);
    return retVOs;
  }

  /**
   * ��ѯ��������������ɱ�����������۳�������㵥
   * 
   * @param queryScheme
   * @return ���������ͼVO
   */
  public SquareOutViewVO[] querySquareOutFor4CSquare(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);

    // �õ������ӱ����
    String mainTable = qsp.getMainTableAlias();
    String subTable =
        qsp.getTableAliasOfAttribute(SquareOutBVO.MAINMETAPATH
            + SquareOutBVO.BCOST);

    // ƴ�ӹ̶���������
    qsp.appendCurrentGroup();
    qsp.appendFuncPermissionOrgSql();
    qsp.appendWhere(" and (" + mainTable + ".dr=0 and " + subTable + ".dr=0)");

    QueryCondition qc = qsp.getQueryCondition(QueryFlag.SQUAREFLAG);
    Object[] obj = qc.getValues();
    if (null == obj || obj.length == 0 || null == obj[0]) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0041")/*@res "����״̬����ѡ��!"*/);
    }
    else {
      int flag = Integer.parseInt(obj[0].toString());
      // �ǳ���Գ�
      if (flag != QueryFlag.UNOUTRUSHBLUE && flag != QueryFlag.UNOUTRUSHRED) {
        qsp.appendWhere(" and (" + mainTable + ".bautosquarecost = 'N' or "
            + mainTable + ".bautosquareincome = 'N')");
      }
      // ��ѯ
      return this.queryForAllUISquare(qsp, subTable, queryScheme);
    }

    return null;
  }

  private void buildNoETWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // ���˻���ǩ�տ�Ʊ�˻ص����۳��ⵥ�����㵥
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // �ѽ������������ڽ��㵥����
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable
        + ".nsquareestnum,.0) + isnull(" + subTable
        + ".ndownarnum,.0) + isnull(" + subTable + ".nrushnum,.0)) < " + "abs("
        + subTable + ".nnum)");
    squareWhere.endParentheses();

    // �ۼ����η�Ʊȷ��Ӧ�����������ڳ������������
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".ndownarnum,.0) != " + subTable
        + ".nnum");
    squareWhere.endParentheses();

    // ��������������������ۼƳ���Գ�����
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nnum,.0) != isnull("
        + subTable + ".nrushnum,.0) ");
    squareWhere.endParentheses();

    // ���Դ�Ӧ�յ�����
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".bincome = 'Y' ");
    squareWhere.endParentheses();

    // ���㷽ʽΪ���ݹ�Ӧ��
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_ET.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();
  }

  private void buildNoREGWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // ���˻���ǩ�տ�Ʊ�˻ص����۳��ⵥ�����㵥
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // �ѽ������������ڽ��㵥����
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable
        + ".nsquareregnum,.0) + isnull(" + subTable
        + ".ndownianum,.0) + isnull(" + subTable + ".nrushnum,.0)) < " + "abs("
        + subTable + ".nnum)");
    squareWhere.endParentheses();

    // �ۼ����η�Ʊ�ɱ��������������ڳ������������
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".ndownianum,.0) != " + subTable
        + ".nnum");
    squareWhere.endParentheses();

    // ��������������������ۼƳ���Գ�����
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nnum,.0) != isnull("
        + subTable + ".nrushnum,.0) ");
    squareWhere.endParentheses();

    // �п��Դ��������
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".bcost = 'Y' ");
    squareWhere.endParentheses();

    // ���㷽ʽΪ��������Ʒ
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_REG_DEBIT.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();
  }

  private void buildNoSquareWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // �ѽ������������� ���㵥����
    squareWhere.append(" and ");
    squareWhere.startParentheses();

    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable + ".nsquarearnum,.0)) < "
        + " abs(isnull(" + subTable + ".nnum,.0)) ");
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_AR.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".bincome = 'Y' ");
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();

    squareWhere.append(" or ");
    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable + ".nsquareianum,.0)) <"
        + " abs(isnull(" + subTable + ".nnum,0)) ");
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_IA.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".bcost = 'Y' ");
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();

    squareWhere.endParentheses();
  }

  private void buildYesETWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // ���˻���ǩ�տ�Ʊ�˻ص����۳��ⵥ�����㵥
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // ��������������0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquareestnum,.0)", "!=", 0);
    squareWhere.endParentheses();

    // ���㷽ʽΪ���ݹ�Ӧ��
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_ET.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();
  }

  private void buildYesREGWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // ���˻���ǩ�տ�Ʊ�˻ص����۳��ⵥ�����㵥
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // ��������������0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquareregnum,.0)", "!=", 0);
    squareWhere.endParentheses();

    // ���㷽ʽΪ��������Ʒ
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_REG_DEBIT.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();
  }

  private void buildYesRushBlueWhere(SqlBuilder squareWhere, String subTable) {
    // �ѳ���Գ�����������0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nrushnum,.0)!=0");
    squareWhere.endParentheses();

    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".nnum > 0 ");
    squareWhere.endParentheses();
  }

  private void buildYesRushRedWhere(SqlBuilder squareWhere, String subTable) {
    // �ѳ���Գ�����������0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nrushnum,.0)!=0");
    squareWhere.endParentheses();

    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".nnum < 0 ");
    squareWhere.endParentheses();

  }

  private void buildYesSquareWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // �ѽ������������� < ���㵥����
    squareWhere.append(" and ");
    squareWhere.startParentheses();

    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquarearnum,.0)", "!=", 0);
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_AR.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();

    squareWhere.append(" or ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquareianum,.0)", "!=", 0);
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_IA.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();

    squareWhere.endParentheses();
  }

  /**
   * ƴ������sql Ĭ�Ϸ��������ݺţ�
   * 
   * @param queryScheme
   * @return
   */
  private String createOrderSql(IQueryScheme queryScheme) {
    // ���ݵ��ݺš��к�����
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(SquareOutHVO.class,
            SquareOutHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(SaleOrderHVO.VBILLCODE);

    return order.toString();
  }

  private SquareOutViewVO[] queryBySchemeViewQuery(IQueryScheme scheme) {
    SchemeViewQuery<SquareOutViewVO> query =
        new SchemeViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    String order = this.createOrderSql(scheme);
    SquareOutViewVO[] views = query.query(scheme, order);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    for (SquareOutViewVO view : views) {
      SquareOutHVO headvo = view.getHead();
      SquareOutBVO bodyvo = view.getItem();
      headvo.setPk_group(bodyvo.getPk_group());
      headvo.setPk_org(bodyvo.getPk_org());
      headvo.setDbilldate(bodyvo.getDbilldate());
    }
    return views;
  }

  private SquareOutViewVO[] queryForAllUISquare(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    QueryCondition qc = qsp.getQueryCondition(QueryFlag.SQUAREFLAG);

    Object[] obj = qc.getValues();
    int flag = Integer.parseInt(obj[0].toString());
    SquareOutViewVO[] svos = null;
    // ��ѯ����������
    if (flag == QueryFlag.SQUARE) {
      svos = this.queryViewForNoSquare(qsp, subTable, queryScheme);
    }
    // ��ѯ�ѽ�������
    else if (flag == QueryFlag.SQUARED) {
      svos = this.queryViewForYesSquare(qsp, subTable, queryScheme);
    }
    // ��ѯ���ݹ�Ӧ������
    else if (flag == QueryFlag.ET) {
      svos = this.queryViewForNoET(qsp, subTable, queryScheme);
    }
    // ��ѯ���ݹ�Ӧ������
    else if (flag == QueryFlag.ETD) {
      svos = this.queryViewForYesET(qsp, subTable, queryScheme);
    }
    // ��ѯ�����뷢����Ʒ����
    else if (flag == QueryFlag.REG) {
      svos = this.queryViewForNoREG(qsp, subTable, queryScheme);
    }
    // ��ѯ�Ѽ��뷢����Ʒ����
    else if (flag == QueryFlag.REGD) {
      svos = this.queryViewForYesREG(qsp, subTable, queryScheme);
    }
    // ��ѯ�ѳ���Գ����ֳ��ⵥ����
    else if (flag == QueryFlag.UNOUTRUSHBLUE) {
      svos = this.queryViewForYesRushBlue(qsp, subTable, queryScheme);
    }
    // ��ѯ�ѳ���Գ���ֳ��ⵥ����
    else if (flag == QueryFlag.UNOUTRUSHRED) {
      svos = this.queryViewForYesRushRed(qsp, subTable, queryScheme);
    }
    return svos;
  }

  private SquareOutDetailVO[] querySquareOutDetailVOBySQBIDAndSquareType(
      String[] sqbids, SquareType type) {
    VOQuery<SquareOutDetailVO> qry =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String outbidWhere = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, sqbids);
    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(outbidWhere);
    where.append(" and ");
    where.append(SquareOutDetailVO.FSQUARETYPE, type.getIntValue());
    SquareOutDetailVO[] retVOs = qry.query(where.toString(), null);
    return retVOs;
  }

  /**
   * ��ѯδ�ݹ�Ӧ�յ����۳�������㵥
   * 
   * @param qsp
   * @param mainTable
   * @param subTable
   * @return
   */
  private SquareOutViewVO[] queryViewForNoET(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // ���ֹ��ݹ�Ӧ������
    this.buildNoETWhere(squareWhere, mainTable, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    // �������۶����ϵĿ�Ʊ�ر��ֶ�
    this.appendOrderInvEndWhere(qsp, subTable);
    // ��ѯ
    SquareOutViewVO[] svos = this.queryBySchemeViewQuery(queryScheme);

    // ���ñ��ο��ݹ�Ӧ������
    if (svos != null && svos.length > 0) {
      for (SquareOutViewVO svo : svos) {
        svo.setNthisnumForET();
      }
      new ProcessWC().reCalNumMny(svos);
    }
    return svos;
  }

  private void appendOrderInvEndWhere(QuerySchemeProcessor qsp, String subTable) {
    qsp.appendFrom(" inner join so_saleorder_b so_saleorder_b on ");
    qsp.appendFrom(subTable + ".cfirstbid = so_saleorder_b.csaleorderbid ");
    qsp.appendWhere(" and isnull(so_saleorder_b.bbinvoicendflag,'N') = 'N' ");
  }

  /**
   * ��ѯδ�ֹ����뷢����Ʒ�����۳�������㵥
   * 
   * @param qsp
   * @param mainTable
   * @param subTable
   * @return
   */
  private SquareOutViewVO[] queryViewForNoREG(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // ���ֹ����뷢����Ʒ����
    this.buildNoREGWhere(squareWhere, mainTable, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    // �������۶����ϵĿ�Ʊ�ر��ֶ�
    this.appendOrderInvEndWhere(qsp, subTable);
    // ��ѯ
    SquareOutViewVO[] svos = this.queryBySchemeViewQuery(queryScheme);

    // ���ñ��ο��ֹ����뷢����Ʒ����
    if (svos != null && svos.length > 0) {
      for (SquareOutViewVO svo : svos) {
        svo.setNthisnumForREG();
      }
      new ProcessWC().reCalNumMny(svos);
    }
    return svos;
  }

  /**
   * ��ѯδ����ɱ�����������۳�������㵥
   * 
   * @param qsp
   * @param mainTable
   * @param subTable
   * @return
   */
  private SquareOutViewVO[] queryViewForNoSquare(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // ���ֹ�����ɱ���������
    this.buildNoSquareWhere(squareWhere, mainTable, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    // ��ѯ
    SquareOutViewVO[] svos = this.queryBySchemeViewQuery(queryScheme);

    // ���ñ��οɽ�������
    if (svos != null && svos.length > 0) {
      for (SquareOutViewVO svo : svos) {
        svo.setNthisnumForManualSquare();
      }
      // ���ν�����������������������Ҫ���¼�����
      ProcessWC wc = new ProcessWC();
      wc.reCalNumMnyAndWCForManualSquareQuery(svos);
    }
    return svos;
  }

  private SquareOutViewVO[] queryViewForYesET(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // �ֹ��ݹ�Ӧ����ϸ��vo
      SquareOutDetailVO[] sdvos =
          this.queryManualDetailVOBySQBIDandSQType(bids, new SquareType[] {
            SquareType.SQUARETYPE_ET
          });

      // ��֯����vo
      vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
    }
    return vos;
  }

  /**
   * ��ѯ�Ѿ��ݹ�Ӧ�չ������۳�������㵥
   */
  private SquareOutViewVO[] queryViewForYesET(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // �Ѿ��ݹ�Ӧ�չ�����
    this.buildYesETWhere(squareWhere, mainTable, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesET(queryScheme);

    return vos;
  }

  private SquareOutViewVO[] queryViewForYesREG(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // �ֹ����뷢����Ʒ��ϸ��vo
      SquareOutDetailVO[] sdvos =
          this.queryManualDetailVOBySQBIDandSQType(bids, new SquareType[] {
            SquareType.SQUARETYPE_REG_DEBIT
          });

      // ��֯����vo
      vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
    }
    return vos;
  }

  /**
   * ��ѯ�Ѿ��ֹ����뷢����Ʒ�����۳�������㵥
   */
  private SquareOutViewVO[] queryViewForYesREG(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // �Ѿ��ֹ����뷢����Ʒ������
    this.buildYesREGWhere(squareWhere, mainTable, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesREG(queryScheme);

    return vos;
  }

  private SquareOutViewVO[] queryViewForYesRush(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // �ֹ�����ɱ�������ϸ��vo
      SquareOutDetailVO[] sdvos = null;
      StringBuilder where = new StringBuilder();
      where.append(" and boutrushflag = 'Y' ");
      where.append(" and dr = 0 ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      where.append(" and "
          + iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids));
      where.append(" and fsquaretype = "
          + SquareType.SQUARETYPE_OUTRUSH.getIntValue());
      sdvos =
          new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class).query(
              where.toString(), null);

      // ��֯����vo
      vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
    }

    return vos;
  }

  /**
   * ��ѯ�Ѿ���������Գ���������۳�������㵥
   */
  private SquareOutViewVO[] queryViewForYesRushBlue(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();

    // �Ѿ�����Գ�����ֳ��ⵥ����
    this.buildYesRushBlueWhere(squareWhere, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesRush(queryScheme);

    return vos;
  }

  /**
   * ��ѯ�Ѿ���������Գ�ĺ������۳�������㵥
   */
  private SquareOutViewVO[] queryViewForYesRushRed(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();

    // �Ѿ�����Գ�����ֳ��ⵥ����
    this.buildYesRushRedWhere(squareWhere, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesRush(queryScheme);

    return vos;
  }

  private SquareOutViewVO[] queryViewForYesSquare(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // �ֹ�����ɱ�������ϸ��vo
      SquareOutDetailVO[] sdvos =
          this.queryManualDetailVOBySQBIDandSQType(bids, new SquareType[] {
            SquareType.SQUARETYPE_AR, SquareType.SQUARETYPE_IA
          });

      // ��֯����vo
      if (VOChecker.isEmpty(sdvos)) {
        vos = null;
      }
      else {
        vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
      }
    }

    return vos;
  }

  /**
   * ��ѯ�Ѿ�����ɱ�����������۳�������㵥
   */
  private SquareOutViewVO[] queryViewForYesSquare(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // ���ֹ�����ɱ���������
    this.buildYesSquareWhere(squareWhere, mainTable, subTable);

    // ��������where����
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesSquare(queryScheme);

    return vos;
  }

}
