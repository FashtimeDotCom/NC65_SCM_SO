/**
 * $�ļ�˵��$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 ����04:30:32
 */
package nc.impl.so.m30.sobalance;

import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m30.sobalance.action.InsertAction;
import nc.impl.so.m30.sobalance.action.UpdateAction;
import nc.itf.so.m30.sobalance.ISOBalanceMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
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
 * @author gdsjw
 * @time 2010-6-24 ����04:30:32
 */
public class SOBalanceMaintainImpl implements ISOBalanceMaintain {

  @Override
  public SoBalanceVO[] querySoBalanceVO(String where, Map<String, String[]> key)
      throws BusinessException {
    SoBalanceVO[] bills = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(where);
      String[] cbillids = rowset.toOneDimensionStringArray();

      // ����id��ѯVO
      BillQuery<SoBalanceVO> query =
          new BillQuery<SoBalanceVO>(SoBalanceVO.class);
      bills = query.query(cbillids);
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SoBalanceVO[] updateSoBalanceVO(SoBalanceVO[] bills)
      throws BusinessException {
    UpdateAction action = new UpdateAction();
    return action.update(bills);
  }

  @Override
  public SoBalanceVO[] querySoBalanceVO(IQueryScheme queryScheme)
      throws BusinessException {
    String sql = this.createSqlByQueryScheme(queryScheme);
    SoBalanceVO[] bills = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] cbillids = rowset.toOneDimensionStringArray();

      // ����id��ѯVO
      BillQuery<SoBalanceVO> query =
          new BillQuery<SoBalanceVO>(SoBalanceVO.class);
      bills = query.query(cbillids);
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  private String createSqlByQueryScheme(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    SqlBuilder sql = new SqlBuilder();
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    sql.append("select distinct(" + mainTableAlias + ".csobalanceid)");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    sql.append(" and so_balance.dr", 0);
    sql.append(" and so_balance.pk_group", BSContext.getInstance().getGroupID());
    return sql.toString();
  }

  @Override
  public SoBalanceVO[] insertSoBalanceVO(SoBalanceVO[] bills)
      throws BusinessException {
    InsertAction action = new InsertAction();
    return action.insert(bills);
  }
  //
  // @Override
  // public SoBalanceVO[] deleteSoBalanceVO(SoBalanceVO[] bills)
  // throws BusinessException {
  // DeleteAction action = new DeleteAction();
  // return action.delete(bills);
  // }

}
