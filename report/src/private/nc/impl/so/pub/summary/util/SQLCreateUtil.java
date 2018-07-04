package nc.impl.so.pub.summary.util;

import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ����sql����������
 * 
 * @since 6.36
 * @version 2015-6-24 ����1:57:41
 * @author ����
 */
public class SQLCreateUtil {

  /**
   * ���������߾��ȳ�������sqlƬ��</br>
   * 
   * ������
   * select sum(5.00000000)/sum(0.30000000) from so_saleorder ���صĽ����16.666666</br>
   * 
   * ��ȷ����
   * select cast(sum(5.00000000)as decimal(28,8))/cast(sum(0.30000000)as
   * decimal(28,8)) from so_saleorder ���ؽ��Ϊ��16.6666666666
   * 
   * @param field1 �����ֶ�����
   * @param field2 �������ֶ�����
   * @return
   */
  public static String getSumDivsql(String field1, String field2) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" cast(");
    sql.append("sum(");
    sql.append(field1);
    sql.append(")");
    sql.append(" as decimal(28,8))");

    sql.append("/");

    sql.append(" cast(");
    sql.append(" sum(");
    sql.append(field2);
    sql.append(")");
    sql.append(" as decimal(28,8))");
    return sql.toString();
  }

}
