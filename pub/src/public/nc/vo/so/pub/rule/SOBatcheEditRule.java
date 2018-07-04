package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���༭����������
 * ���༭��ʱ��ȶ����������ֶ� �Ƿ����һ������һ�£����һ�±����༭���ֵ�������һ���������Ӧֵ
 * 
 * @since 6.0
 * @version 2012-6-28 ����11:10:45
 * @author ô��
 */
public class SOBatcheEditRule {
  private String[] bykeys;

  private IKeyValue keyValue;

  // public SOBatcheEditRule(BillCardPanel cardPanel, String[] bykeys) {
  // this.keyValue = new CardKeyValue(cardPanel);
  // this.bykeys = bykeys;
  // }

  /**
   * 
   * @param keyValue
   * @param bykeys �����������ֶ�
   */
  public SOBatcheEditRule(IKeyValue keyValue, String[] bykeys) {
    this.keyValue = keyValue;
    this.bykeys = bykeys;
  }

  public void clearRows(int[] rows, String clearkey) {
    if (rows.length < 2) {
      return;
    }

    List<Integer> needclearrows = this.getNeedClearRows(rows);

    for (Integer i : needclearrows) {
      this.keyValue.setBodyValue(i.intValue(), clearkey, null);
    }

  }

  public void clearRows(int[] rows, String[] clearkeys) {
    if (rows.length < 2) {
      return;
    }

    List<Integer> needclearrows = this.getNeedClearRows(rows);
    for (String key : clearkeys) {
      for (Integer i : needclearrows) {
        this.keyValue.setBodyValue(i.intValue(), key, null);
      }
    }
  }

  /**
   * ��Ҫ������ݵ���
   * 
   * @param rows
   * @return
   */
  private List<Integer> getNeedClearRows(int[] rows) {
    Map<String, Object> onemap = new HashMap<String, Object>();
    for (String key : this.bykeys) {
      onemap.put(key, this.keyValue.getBodyValue(rows[0], key));
    }
    List<Integer> needclearrows = new ArrayList<Integer>();
    Object newob = null;
    Object oldob = null;
    for (int i : rows) {
      for (String key : this.bykeys) {
        newob = this.keyValue.getBodyValue(i, key);
        oldob = onemap.get(key);
        if (null != newob) {
          if (!newob.equals(oldob)) {
            needclearrows.add(Integer.valueOf(i));
          }
        }
        else if (null != oldob) {
          needclearrows.add(Integer.valueOf(i));
        }
      }
    }
    return needclearrows;
  }
}
