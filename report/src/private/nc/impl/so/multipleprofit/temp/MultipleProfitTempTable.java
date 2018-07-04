package nc.impl.so.multipleprofit.temp;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.ITableMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.multipleprofit.MultipleProfitViewMeta;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;

/**
 * �ۺ�ë��������ʱ��
 * 
 * @since 6.3
 * @version 2012-10-18 14:27:47
 * @author zhangkai4
 */
public class MultipleProfitTempTable {

  /**
   * ������ʱ��
   * 
   * @return ��ʱ��ı���
   */
  public String createMulProfitTemptable() {
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();

    // 1.���Ԫ����
    MetaData metadata = this.getMetaData();

    // 2.�����ʱ���ֶ�
    ITableMeta tablemeta = this.getTableMeta(metadata);

    // 3.������ʱ��
    String temptable = tmptableutil.createTempTable(tablemeta);
    return temptable;
  }

  /**
   * �����ʱ���ֶ�
   * 
   * @param metadata
   * @return
   */
  private ITableMeta getTableMeta(MetaData viewmeta) {
    Table table = new Table(SOTable.TMP_SO_MULTIPLEPROFIT.getName());
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
    for (String key : MultipleProfitViewMeta.MULPROFIT_STRKEYS) {
      Field field = new Field();
      // �������ݿ��ֶ�����
      field.setDbColumnType(Types.VARCHAR);
      // �������ݿ��ֶ���
      field.setFldname(key);
      // �������ݾ���
      field.setPrecision(20);
      // ����С��λ��
      field.setScale(0);
      list.add(field);
    }
    for (String key : MultipleProfitViewMeta.MULPROFIT_UFDKEYS) {
      Field field = new Field();
      // �������ݿ��ֶ�����
      field.setDbColumnType(Types.DECIMAL);
      // �������ݿ��ֶ���
      field.setFldname(key);
      // �������ݾ���
      field.setPrecision(28);
      // ����С��λ��
      field.setScale(8);
      list.add(field);
    }
    return new MetaData(list.toArray(new Field[list.size()]));
  }
}
