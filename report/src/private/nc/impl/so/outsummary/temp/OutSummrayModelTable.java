package nc.impl.so.outsummary.temp;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.so.report.outsummary.OutSummaryViewMeta;

/**
 * ���ⵥ�м���ֶ�
 * 
 * @since 6.3
 * @version 2012-10-18 13:56:58
 * @author ������
 */
public class OutSummrayModelTable extends Table {

  /**
   * ���췽��
   * 
   * @param name
   */
  public OutSummrayModelTable(String name) {
    super(name);

  }

  /**
   * ��ʱ����Ҫ���ӵ�����ֶ�
   */
  protected static final String SEQUEENCE = "seq";

  @Override
  public IColumnMeta[] getColumns() {
    List<IColumnMeta> columns = new ArrayList<IColumnMeta>();
    for (String key : OutSummaryViewMeta.SALEOUT_HKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutSummaryViewMeta.SALEOUT_BKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutSummaryViewMeta.SALEOUT_EXEKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutSummaryViewMeta.EXTEND_STRKEYS) {
      columns.add(this.getColumn(key));
    }
    return columns.toArray(new IColumnMeta[columns.size()]);
  }
}
