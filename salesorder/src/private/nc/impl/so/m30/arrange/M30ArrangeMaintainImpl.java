package nc.impl.so.m30.arrange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.so.m30.arrange.IM30ArrangeMaintain;
import nc.ui.pub.bill.BillStatus;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

/**
 * 
 * @since 6.0
 * @version 2011-6-28 ����11:54:04
 * @author ף����
 */
public class M30ArrangeMaintainImpl implements IM30ArrangeMaintain {

  private SaleOrderVO[] convertViewToAggVO(SaleOrderViewVO[] views) {
    if (null == views || views.length == 0) {
      return null;
    }
    Map<String, SaleOrderHVO> maphead = new HashMap<String, SaleOrderHVO>();
    MapList<String, SaleOrderBVO> maplistbody =
        new MapList<String, SaleOrderBVO>();
    for (SaleOrderViewVO view : views) {
      String hid = view.getHead().getCsaleorderid();
      if (!maphead.containsKey(hid)) {
        maphead.put(hid, view.getHead());
      }
      maplistbody.put(hid, view.getBody());
    }
    SaleOrderVO[] bills = new SaleOrderVO[maphead.size()];
    int i = 0;
    for (Entry<String, SaleOrderHVO> entry : maphead.entrySet()) {
      String hid = entry.getKey();
      bills[i] = new SaleOrderVO();
      bills[i].setParentVO(entry.getValue());
      List<SaleOrderBVO> listbvo = maplistbody.get(hid);
      SaleOrderBVO[] bvos = new SaleOrderBVO[listbvo.size()];
      listbvo.toArray(bvos);
      bills[i].setChildrenVO(bvos);
      i++;
    }

    return bills;
  }

  @Override
  public SaleOrderVO[] querySaleOrder(IQueryScheme queryScheme)
      throws BusinessException {
    String sql = this.createRefSqlByQueryScheme(queryScheme);
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql).toOneDimensionStringArray();
    SaleOrderViewVO[] views =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    return this.convertViewToAggVO(views);
  }

  /**
   * ������=���ۿ���=��״̬=����̬������������ֱ�����ͱ�� = ֱ�˲ɹ�������ر� = ��
   * ���۶�������-�ۼư����빺������-�ۼư��Ųɹ���������-�ۼư��ŵ�����������-�ۼư��ŵ�����������-�ۼư���ί�ⶩ������-�ۼư��Ž��ں�ͬ������-�ۼư��������������� > 0
   * @param queryScheme
   * @return
   */
  private String createRefSqlByQueryScheme(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // ���ӹ̶�where����
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    // ���Ӽ���
    qsp.appendCurrentGroup();

    // �õ����ӱ����
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.BBOUTENDFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    qsp.appendFrom(" inner join so_saleorder_exe  on ");
    qsp.appendFrom(subTable + ".csaleorderbid = so_saleorder_exe.csaleorderbid");

    qsp.appendFrom(" inner join so_m30trantype on ");
    qsp.appendFrom(mainTableAlias + ".ctrantypeid = so_m30trantype.ctrantypeid");

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BLABORFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BDISCOUNTFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.FSTATUSFLAG, BillStatus.AUDIT);
    where.append(") ");

    where.append(" and (");
    where.append("so_m30trantype.fdirecttype",
        DirectType.DIRECTTRAN_PO.getIntValue());

    where.append(" or ");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BBOUTENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and abs(isnull(nnum,0) - isnull(narrangepoappnum,0)"
        + "- isnull(narrangeponum,0) - isnull(narrangetoappnum,0)"
        + "- isnull(narrangetoornum,0) - isnull(narrangescornum,0) - isnull(narrangeitcnum,0)" + ""
        + "- isnull(narrangemonum,0)) > 0 ");

    // ��������where����
    qsp.appendWhere(where.toString());
    String select = "select distinct(" + subTable + ".csaleorderbid) ";
    // sql���
    String sql = select + qsp.getFinalFromWhere();

    return sql.toString();

  }

}
