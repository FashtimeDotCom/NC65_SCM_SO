package nc.vo.so.m30.util;

import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶��������۷�Ʊ�ṩ�� Scheme����
 * 
 * @since 6.0
 * @version 2011-6-11 ����09:56:25
 * @author ô��
 */
public class SaleOrderQuerySchemeUtils {

  /**
   * 
   * @param qs
   * @param qsp
   */
  public void appendBusitype(IQueryScheme qs, QuerySchemeProcessor qsp) {
    String halias = qsp.getMainTableAlias();
    SqlBuilder whr = new SqlBuilder();
    // ҵ�����̹���
    Object ordersbusi = qs.get(SOItemKey.CBIZTYPEID);
    // ��������
    Object otrantype = qs.get(SOItemKey.CTRANTYPEID);
    boolean isnull = this.isNullBusitype(ordersbusi, otrantype);
    if (ordersbusi != null) {
      String[] busistr = (String[]) ordersbusi;
      whr.append(" and ((");
      whr.append(halias);
      whr.append(".");
      whr.append(SOItemKey.CBIZTYPEID, busistr);
      whr.append(") ");
    }
    if (otrantype != null) {
      String[] strantype = (String[]) otrantype;
      whr.append(" and (");
      whr.append(halias);
      whr.append(".");
      whr.append(SOItemKey.CTRANTYPEID, strantype);
      whr.append(") ");
    }
    if (!isnull) {
      Object is30to32busitypes = qs.get("is30to32busitypes");
      if (null != is30to32busitypes
          && !((UFBoolean) is30to32busitypes).booleanValue()) {
        this.appendOther(whr, qsp);
      }
    }
    // ���۳��ⵥ����Ʊ����ids
    Object obusi =
        qs.get(ICBillType.SaleOut.getCode() + SaleOrderHVO.CBIZTYPEID);
    if (obusi != null) {
      String[] bids = (String[]) obusi;
      if (!isnull) {
        whr.append(" or ");
      }
      else {
        whr.append(" and ");
      }
      whr.append(" (");
      whr.append(halias);
      whr.append(".");
      whr.append(SaleOrderHVO.CBIZTYPEID, bids);
      this.appendOther(whr, qsp);
      whr.append(") ");
    }
    if (!isnull) {
      whr.append(" ) ");
    }

    qsp.appendWhere(whr.toString());
  }

  public void appendFixedWhr(QuerySchemeProcessor qsp) {
    String halias = qsp.getMainTableAlias();
    SqlBuilder whr = new SqlBuilder();
    // ����״̬
    int[] status = new int[] {
      BillStatus.AUDIT.getIntValue(), BillStatus.CLOSED.getIntValue()
    };
    
  //=== lijj �Ǹֲ�Ʒó�ײ� ���۶����ͽ��ں�ͬͬ������ ������������ ȥ��������������==== 
    SpecialBusiUtil busiUtil = new SpecialBusiUtil();
    if(!busiUtil.isTheDept()){
    	whr.append(" and ");
    	whr.append(halias + "." + SOItemKey.FSTATUSFLAG, status);
    }
    //=== lijj �Ǹֲ�Ʒó�ײ� ���۶����ͽ��ں�ͬͬ������ ������������ ȥ��������������==== 
    
    qsp.appendWhere(whr.toString());
  }

  /**
   * ����id��������ƴ��IQueryScheme��
   * 
   * @param qs
   */
  public void appendRemoveIDSWhr(IQueryScheme qs, QuerySchemeProcessor qsp) {
    String subtable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.CSALEORDERBID);
    SqlBuilder whr = new SqlBuilder();

    // ԴͷBID
    Object obusi = qs.get(SaleOrderBVO.CSALEORDERBID);
    String[] bids = null;
    if (obusi != null) {
      bids = (String[]) obusi;
      if(bids.length!=0){
      
      whr.append(" and (");
      whr.append(subtable);
      whr.append(".");
      whr.append(this.appendNot(SaleOrderBVO.CSALEORDERBID, bids));
      whr.append(") ");
      }
    }
    qsp.appendWhere(whr.toString());
  }

  private String appendNot(String name, String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    int length = ids.length;
    if (length < 1000) {
      sql.appendNot(name, ids);
    }
    else {
      sql.append(name);
      sql.append(" not in ");
      sql.startParentheses();
      sql.append(" select id1 from ");
      TempTableDefine define = new TempTableDefine();
      String temptable = define.get(ids);
      sql.append(temptable);
      sql.endParentheses();
    }
    return sql.toString();
  }

  public void store4CTO32Business(IQueryScheme qs, String[] business) {
    qs.put(ICBillType.SaleOut.getCode() + SaleOrderHVO.CBIZTYPEID, business);
  }

  public void storeIs30to32busitypes(IQueryScheme qs,
      UFBoolean is30to32busitypes) {
    qs.put("is30to32busitypes", is30to32busitypes);
  }

  /**
   * ��װԴͷBID������IQueryScheme��
   * 
   * @param qs
   * @param srcbids
   */
  public void storeSrcbid(IQueryScheme qs, String[] srcbids) {
    qs.put(SaleOrderBVO.CSALEORDERBID, srcbids);
  }

  /**
   * ��װԴͷID������IQueryScheme��
   * 
   * @param qs
   * @param srcbids
   */
  public void storeSrcid(IQueryScheme qs, String[] srcids) {
    qs.put(SaleOrderHVO.CSALEORDERID, srcids);
  }

  private void appendOther(SqlBuilder whr, QuerySchemeProcessor qsp) {
    String subtable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.CSALEORDERBID);
    whr.append(" and (");
    whr.append(subtable);
    whr.append(".");
    whr.append(SaleOrderBVO.BLABORFLAG, UFBoolean.TRUE);

    whr.append(" or ");
    whr.append(subtable);
    whr.append(".");
    whr.append(SaleOrderBVO.BDISCOUNTFLAG, UFBoolean.TRUE);

    whr.append(" ) ");

  }

  /**
   * ���̺ͽ������Ͷ�Ϊnull
   * 
   * @param ordersbusi
   * @param otrantype
   * @return
   */
  private boolean isNullBusitype(Object ordersbusi, Object otrantype) {

    if (null != otrantype || null != ordersbusi) {
      return false;
    }
    return true;
  }
}
