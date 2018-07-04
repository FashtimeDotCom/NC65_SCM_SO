package nc.impl.so.ordersummary.temp;

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
import nc.vo.so.report.ordersummary.OrderSummaryViewMeta;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;

/**
 * ���۶���ִ�л�����ʱ��
 * 
 * @since 6.3
 * @version 2012-10-18 14:01:14
 * @author ������
 */
public class OrderSummaryTempTable {

  /**
   * ������ʱ��
   * 
   * @return temptable
   */
  public String createOrderSummaryTemptable() {
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
    Table table = new Table(SOTable.TMP_SO_ORDERSUMMARY.getName());
    for (Field field : viewmeta.getFields()) {
      Column column = new Column(table, field.getFldname());
      // ���ó���
      column.setLength(field.getPrecision());
      // ���þ���
      column.setPrecision(field.getScale());
      // �����ֶ�����
      column.setSqlType(field.getDbColumnType());
      // �������ݿ��ֶο���Ϊ��
      column.setNullable(true);
      // �������ݿ��ֶ�Ԫ����ʵ����
      column.setLabel(field.getFldname());
      // �������ݿ��ֶε����ݿ����
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
    OrderSummaryViewVO view = new OrderSummaryViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OrderSummaryViewMeta.TMPTABLE_ORDERKEYS) {

      // VO���Ե�Ԫ����;�������ֵõ�����
      IAttributeMeta attrmeta = viewmeta.getAttribute(key);
      // ���ݿ��ֶ�Ԫ����;�õ���Ӧ�����ݿ��ֶ�
      IColumnMeta colmeta = attrmeta.getColumn();
      Field field = new Field();
      // �������ݿ��ֶ�����
      field.setDbColumnType(colmeta.getSqlType());
      // �������ݿ��ֶ���
      field.setFldname(colmeta.getName());
      // �������ݳ���
      field.setPrecision(colmeta.getLength());
      // ����С��λ��
      field.setScale(colmeta.getPrecision());
      list.add(field);
    }
    for (String key : OrderSummaryViewMeta.EXTEND_STRKEYS) {
      Field field = new Field();
      field.setDbColumnType(Types.VARCHAR);
      field.setFldname(key);
      field.setPrecision(20);
      field.setScale(0);
      list.add(field);
    }
    for (String key : OrderSummaryViewMeta.EXTEND_UFKEYS) {
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
