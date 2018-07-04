package nc.bs.so.m33.maintain.m32.query;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.votools.SoVoTools;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>��Ӧ����ز�ѯ
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-7-6 ����04:41:13
 */
public class SquareADQuery {

  /**
   * ��ѯ��������㵥��������Ϣ
   * 
   * @param outBids
   * @return Map
   */
  public Map<String, UFDouble[]> queryTotalSquareADMnyBy4C(String[] outBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select so_squareinv_b.csrcbid ");
    sql.append(",sum(isnull(so_squareinv_d.norigtaxmny,.0)) ");
    sql.append(",sum(isnull(so_squareinv_d.norigmny,.0)) ");
    sql.append(" from so_squareinv_d,so_squareinv_b ");
    sql.append(" where so_squareinv_d.csalesquarebid = so_squareinv_b.csalesquarebid ");
    sql.append(" and so_squareinv_b.dr=0 and so_squareinv_d.dr=0 and ");
    sql.append(" so_squareinv_b.fpreartype",
        SquareType.SQUARETYPE_BALANCEAR.getIntValue());
    sql.append(" and ");
    sql.append(" so_squareinv_d.fsquaretype",
        SquareType.SQUARETYPE_AR.getIntValue());
    sql.append(" and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(idqb.buildSQL("so_squareinv_b.CSRCBID", outBids));
    sql.append(" group by so_squareinv_b.CSRCBID ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());

    Map<String, UFDouble[]> mInvADMny = new HashMap<String, UFDouble[]>();
    while (rowset.next()) {
      mInvADMny.put(rowset.getString(0), new UFDouble[] {
        rowset.getUFDouble(1), rowset.getUFDouble(2)
      });
    }
    return mInvADMny;
  }

  /**
   * ����������������ѯͬһ�����γ��ⵥ���η�Ʊ�ۼƽ�����Ϣ
   * <p>
   * <���ⵥ��id, [0]��Ʊ�ۼƽ������� [1]��Ʊ�ۼƽ��㺬˰��� [2]��Ʊ�ۼƽ�����˰���
   * <p>
   * 
   * @param vos
   * @return <���ⵥ��id,
   *         [0]��Ʊ�ۼƽ�������
   *         [1]��Ʊ�ۼƽ��㺬˰���
   *         [2]��Ʊ�ۼƽ�����˰���
   *         <p>
   * @author zhangcheng
   * @time 2010-7-1 ����07:12:51
   */
  public Map<String, UFDouble[]> queryTotalSquareNumBy4C(SquareInvVO[] vos) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select so_squareinv_b.CSRCBID,");
    sql.append("sum(isnull(so_squareinv_b.nnum,.0))");
    sql.append(",sum(isnull(so_squareinv_b.norigtaxmny,.0)) ");
    sql.append(",sum(isnull(so_squareinv_b.norigmny,.0)) ");
    sql.append("from so_squareinv_b where so_squareinv_b.dr=0 and ");
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(vos);
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(idqb.buildSQL("so_squareinv_b.CSRCBID",
        SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CSRCBID)));
    sql.append(" group by so_squareinv_b.CSRCBID ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());

    Map<String, UFDouble[]> m4Cbid_num = new HashMap<String, UFDouble[]>();
    while (rowset.next()) {
      m4Cbid_num.put(rowset.getString(0), new UFDouble[] {
        rowset.getUFDouble(1), rowset.getUFDouble(2), rowset.getUFDouble(3)
      });
    }
    return m4Cbid_num;
  }
}
