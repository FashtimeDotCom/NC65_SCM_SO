package nc.impl.so.invoicesummary.temp;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.so.report.invoicesummary.InvSummaryViewMeta;

/**
 * ���۷�Ʊ�м���ֶ�
 * 
 * @since 6.3
 * @version 2012-10-18 13:59:56
 * @author ������
 */
public class InvSummaryModelTable extends Table {

  /**
   * ���췽��
   * 
   * @param name
   */

  public InvSummaryModelTable(String name) {
    super(name);
    // TODO Auto-generated constructor stub
  }

  /**
   * ��ʱ����Ҫ���ӵ�����ֶ�
   */
  protected static final String SEQUEENCE = "seq";

  @Override
  public IColumnMeta[] getColumns() {
    List<IColumnMeta> columns = new ArrayList<IColumnMeta>();
    for (String key : InvSummaryViewMeta.SALEINV_HKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : InvSummaryViewMeta.SALEINV_BKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : InvSummaryViewMeta.EXTEND_STRKEYS) {
      columns.add(this.getColumn(key));
    }
    return columns.toArray(new IColumnMeta[columns.size()]);
  }
}
