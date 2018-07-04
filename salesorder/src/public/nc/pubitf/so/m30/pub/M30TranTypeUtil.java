package nc.pubitf.so.m30.pub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;

import nc.ui.dbcache.DBCacheQueryFacade;

/**
 * ���۶����������ͻ����ѯ������
 * 
 * @since 6.0
 * @version 2011-7-11 ����03:29:29
 * @author ��־ΰ
 */
public class M30TranTypeUtil {

  private static M30TranTypeUtil instance = new M30TranTypeUtil();

  private M30TranTypeUtil() {
    //
  }

  public static M30TranTypeUtil getInstance() {
    return M30TranTypeUtil.instance;
  }

  /**
   * ��ѯ���۶�����������ֱ������
   * 
   * @param ctrantypeids
   * @return Map<ctrantypeid, ֱ������>
   */
  public Map<String, Integer> queryDirectType(String[] ctrantypeids) {
    Map<String, Integer> returnMap = new HashMap<String, Integer>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        Integer directType =
            vo.getFdirecttype() == null ? DirectType.DIRECTTRAN_NO
                .getIntegerValue() : vo.getFdirecttype();
        returnMap.put(vo.getCtrantypeid(), directType);
      }
    }
    return returnMap;
  }

  /**
   * ��ѯ���۶�����������ֱ�������Ƿ�ֱ�˲ɹ�
   * 
   * @param ctrantypeids
   * @return Map<ctrantypeid, ֱ�������Ƿ�ֱ�˲ɹ�>
   */
  public Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids) {
    // Map<��������code, �Ƿ�ֱ�˲ɹ�>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        if (DirectType.DIRECTTRAN_PO.equalsValue(vo.getFdirecttype())) {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.TRUE);
        }
        else {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.FALSE);
        }
      }
    }
    return returnMap;
  }

  /**
   * ��ѯ���۶�����������ֱ�������Ƿ�ֱ�˵���
   * 
   * @param ctrantypeids
   * @return Map<ctrantypeid, ֱ�������Ƿ�ֱ�˵���>
   */
  public Map<String, UFBoolean> queryIsDirectTO(String[] ctrantypeids) {
    // Map<��������code, �Ƿ�ֱ�˵���>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        if (DirectType.DIRECTTRAN_TO.equalsValue(vo.getFdirecttype())) {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.TRUE);
        }
        else {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.FALSE);
        }
      }
    }
    return returnMap;
  }

  public String[] queryDirectTypeAllBillTypeCode() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ctrantypeid from so_m30trantype");
    sql.append(" where ");
    sql.append(" fdirecttype", 2);
    sql.append(" and pk_group ", AppContext.getInstance().getPkGroup());
    Object resultVOs =
        DBCacheQueryFacade.runQuery(sql.toString(), new ResultSetProcessor() {

          private static final long serialVersionUID = 1L;

          @Override
          public Object handleResultSet(ResultSet rs) throws SQLException {
            List<String> retList = new ArrayList<String>();
            while (rs.next()) {
              retList.add(rs.getString(1));
            }
            return retList;
          }
        });
    return ((List<?>) resultVOs).toArray(new String[0]);

  }

  /**
   * ��ѯ���з�ֱ�˽�������ID
   * 
   * @return String
   */
  public String[] queryNotDirectTypeAllBillTypeCode() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ctrantypeid from so_m30trantype");
    sql.append(" where ");
    sql.append(" fdirecttype", 1);
    sql.append(" and pk_group ", AppContext.getInstance().getPkGroup());
    Object resultVOs =
        DBCacheQueryFacade.runQuery(sql.toString(), new ResultSetProcessor() {

          private static final long serialVersionUID = 1L;

          @Override
          public Object handleResultSet(ResultSet rs) throws SQLException {
            List<String> retList = new ArrayList<String>();
            while (rs.next()) {
              retList.add(rs.getString(1));
            }
            return retList;
          }
        });
    return ((List<?>) resultVOs).toArray(new String[0]);

  }

  /**
   * ��ѯ���۶�����������VO
   * 
   * @param ctrantypeid
   * @return M30TranTypeVO
   */
  public M30TranTypeVO queryTranTypeVO(String ctrantypeid) {
    return this.queryTranTypeVOs(new String[] {
      ctrantypeid
    })[0];
  }

  /**
   * ��ѯ���۶�����������VOs
   * 
   * @param ctrantypeids
   * @return M30TranTypeVO[]
   */
  public M30TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids) {
    // ��������
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.getSelectFromSql());
    sql.append(" where ");
    sql.append(" ctrantypeid =?");
    sql.append(" and pk_group ", AppContext.getInstance().getPkGroup());

    SQLParameter[] params = new SQLParameter[ctrantypeids.length];
    for (int i = 0; i < ctrantypeids.length; i++) {
      params[i] = new SQLParameter();
      params[i].addParam(ctrantypeids[i]);
    }
    Object[] resultVOs =
        DBCacheQueryFacade.runQuery(sql.toString(), params,
            new M30ResultSetProcessor());
    M30TranTypeVO[] retVOs = new M30TranTypeVO[resultVOs.length];
    for (int i = 0; i < resultVOs.length; i++) {
      retVOs[i] = (M30TranTypeVO) resultVOs[i];
    }
    return retVOs;
  }

  private String getSelectFromSql() {
    return "select pk_trantype,ctrantypeid,fdirecttype,fsalemode,"
        + "bmorerows,bcanchangeout, faskqtrule,bmodifyaskedqt,"
        + "bmodifyunaskedqt,flargessgetqtrule,bmodifydiscount,pk_group,"
        + "vtrantypecode,breviseprice,bredorderpay,flargessdistype,"
        + "blargesspriceno,barrangeinv,barrangeout,bnofindpricehit,"
        + "fmodifymny,blossrenew  from so_m30trantype";
  }
}
