package nc.impl.so.outsummary.temp;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.outsummary.OutSummaryViewMeta;
import nc.vo.so.report.outsummary.OutSummaryViewVO;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;

/**
 * ���۳���ִ�л�����ʱ��
 * 
 * @since 6.3
 * @version 2012-10-18 14:00:22
 * @author ������
 */
/**
 * 
 * @since 6.3
 * @version 2012-10-18 14:02:21
 * @author ������
 */
public class OutSummaryTempTable {

  /**
   * ������ʱ��
   * 
   * @return temptable
   */
  public String createOutSummaryTemptable() {
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();

    // 1.���Ԫ����
    MetaData metadata = this.getMetaData();

    // 2.�����ʱ���ֶ�
    ITableMeta tablemeta = this.getTableMeta(metadata);

    // 3.������ʱ��
    String temptable = tmptableutil.createTempTable(tablemeta);

    return temptable;
  }

  private ITableMeta getTableMeta(MetaData viewmeta) {
    Table table = new Table(SOTable.TMP_SO_OUTSUMMARY.getName());
    for (Field field : viewmeta.getFields()) {
      Column column = new Column(table, field.getFldname());
      // ���ó��Ⱥ;���
      column.setLength(field.getPrecision());
      column.setPrecision(field.getScale());
      // �����ֶ�����
      column.setSqlType(field.getDbColumnType());

      column.setNullable(true);
      column.setLabel(field.getFldname());
      table.add(column);
    }
    return table;
  }

  /**
   * Ԫ����ģ��
   * 
   * @return MetaData
   */
  public MetaData getMetaData() {
    List<Field> list = new ArrayList<Field>();
    OutSummaryViewVO view = new OutSummaryViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OutSummaryViewMeta.TMPTABLE_OUTKEYS) {
      IAttributeMeta attrmeta = viewmeta.getAttribute(key);
      IColumnMeta colmeta = attrmeta.getColumn();
      Field field = new Field();
      field.setDbColumnType(colmeta.getSqlType());
      field.setFldname(colmeta.getName());
      field.setPrecision(colmeta.getLength());
      field.setScale(colmeta.getPrecision());
      list.add(field);
    }
    for (String key : OutSummaryViewMeta.EXTEND_STRKEYS) {
      Field field = new Field();
      field.setDbColumnType(Types.VARCHAR);
      field.setFldname(key);
      field.setPrecision(20);
      field.setScale(0);
      list.add(field);
    }
    for (String key : OutSummaryViewMeta.EXTEND_UFKEYS) {
      Field field = new Field();
      field.setDbColumnType(Types.DECIMAL);
      field.setFldname(key);
      field.setPrecision(28);
      field.setScale(8);
      list.add(field);
    }
    Field[] fields = new Field[list.size()];
    list.toArray(fields);
    return new MetaData(fields);
  }
}
