package nc.impl.so.m30.revise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.SOTable;

/**
 * ��ѯ������չ������
 * 
 * @since 6.36
 * @version 2015-5-21 ����1:27:35
 * @author ����
 */
public class BillQueryOrderRevise {

  /**
   * �������۶����޶�VO��ѯ������չ��
   * 
   * ע�⣺�޶�ʱ�޷�ֱ��ȡ����չ��VO,������Ҫ�ֶ��������ǵ���չ����������κ�״̬�����Ա仯�ģ������޶����ٵ��ݽ���
   * 
   * @param vos
   * @return
   */
  public SaleOrderHistoryVO[] joinSaleOrderexe(SaleOrderHistoryVO[] vos) {
    Set<String> salebid = new HashSet<String>();
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryBVO[] historybvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO historybvo : historybvos) {
        salebid.add(historybvo.getCsaleorderbid());
      }
    }
    DataAccessUtils dao = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    createSelect(sql, salebid);
    IRowSet rowset = dao.query(sql.toString());
    Map<String, SaleOrderHistoryBVO> historybvomap = getOrderExeVO(rowset);
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryBVO[] historybvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO historybvo : historybvos) {
        SaleOrderHistoryBVO bvo =
            historybvomap.get(historybvo.getCsaleorderbid());
        if (bvo == null) {
          continue;
        }
        for (String filed : FIELDS) {
          historybvo.setAttributeValue(filed, bvo.getAttributeValue(filed));
        }
      }
    }
    return vos;
  }

  private void createSelect(SqlBuilder sql, Set<String> salebid) {
    sql.append("select ");
    for (String field : FIELDS) {
      sql.append(field);
      sql.append(",");
    }
    sql.deleteLastChar();
    sql.append(" from so_saleorder_exe where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SaleOrderBVO.CSALEORDERBID,
        salebid.toArray(new String[0])));
    sql.append(" and dr=0");
  }

  private Map<String, SaleOrderHistoryBVO> getOrderExeVO(IRowSet rowset) {
    Map<String, SaleOrderHistoryBVO> historybvomap = new HashMap<>();
    SaleOrderHistoryBVO[] bvos = new SaleOrderHistoryBVO[rowset.size()];
    int j = 0;
    while (rowset.next()) {
      bvos[j] = new SaleOrderHistoryBVO();
      int i = 0;
      for (String field : FIELDS) {
        bvos[j].setAttributeValue(field, rowset.getObject(i));
        i++;
      }
      historybvomap.put(bvos[j].getCsaleorderbid(), bvos[j]);
      j++;
    }
    return historybvomap;
  }

  // ��չ������
  private static final String[] FIELDS = new String[] {
    SaleOrderHistoryBVO.CSALEORDERBID,
    // �ۼƷ����������ۼƿ�Ʊ����
    SaleOrderHistoryBVO.NTOTALSENDNUM, SaleOrderHistoryBVO.NTOTALINVOICENUM,
    // �ۼƳ��������� �ۼ�Ӧ��δ��������
    SaleOrderHistoryBVO.NTOTALOUTNUM, SaleOrderHistoryBVO.NTOTALNOTOUTNUM,
    // �ۼ�ǩ�������� �ۼ�;������
    SaleOrderHistoryBVO.NTOTALSIGNNUM, SaleOrderHistoryBVO.NTRANSLOSSNUM,
    // �ۼƳ���Գ��������ۼ��ݹ�Ӧ������
    SaleOrderHistoryBVO.NTOTALRUSHNUM, SaleOrderHistoryBVO.NTOTALESTARNUM,
    // �ۼ�ȷ��Ӧ���������ۼƳɱ���������
    SaleOrderHistoryBVO.NTOTALARNUM, SaleOrderHistoryBVO.NTOTALCOSTNUM,
    // �ۼ��ݹ�Ӧ�ս� �ۼ�ȷ��Ӧ�ս��
    SaleOrderHistoryBVO.NTOTALESTARMNY, SaleOrderHistoryBVO.NTOTALARMNY,
    // �ۼư���ί�ⶩ���������ۼư����빺������
    SaleOrderHistoryBVO.NARRANGESCORNUM, SaleOrderHistoryBVO.NARRANGEPOAPPNUM,
    // �ۼư��ŵ��������������ۼư��ŵ�����������
    SaleOrderHistoryBVO.NARRANGETOORNUM, SaleOrderHistoryBVO.NARRANGETOAPPNUM,
    // �ۼư������������������ۼư��Ųɹ���������
    SaleOrderHistoryBVO.NARRANGEMONUM, SaleOrderHistoryBVO.NARRANGEPONUM,
    // �ۼƷ�����Ʒ�� �ۼ��˻�����
    SaleOrderHistoryBVO.NTOTALRETURNNUM, SaleOrderHistoryBVO.NTOTALTRADENUM
  };
}
