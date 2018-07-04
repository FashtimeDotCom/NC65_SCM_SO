package nc.bs.so.pub.rule.rowno;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.pub.SOItemKey;

/**
 * ���۹����кŹ�����
 * 1�� �����к�
 * 2��У���к�
 * 
 * @since 6.0
 * @version 2012-4-26 ����07:41:59
 * @author ô��
 */
public class SORowNoUtil {

  public static void fillupRowNo(IBill vo) {
    AbstractBill bill = (AbstractBill) vo;
    // Ϊ�к�Ϊ�յ��в����кš�
    CircularlyAccessibleValueObject[] items = bill.getChildrenVO();
    List<CircularlyAccessibleValueObject> bvos =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (CircularlyAccessibleValueObject item : items) {
      int vostatus = item.getStatus();
      // ������ɾ������
      if (vostatus == VOStatus.DELETED) {
        continue;
      }
      bvos.add(item);
    }
    items = bvos.toArray(new CircularlyAccessibleValueObject[0]);
    VORowNoUtils.setVOsRowNoByRule(items, SOItemKey.CROWNO);
  }

  public static void checkRowNo(IBill vo) {
    AbstractBill bill = (AbstractBill) vo;
    CircularlyAccessibleValueObject[] items = bill.getChildrenVO();
    List<CircularlyAccessibleValueObject> bvos =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (CircularlyAccessibleValueObject item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // ������ɾ������
        continue;
      }
      bvos.add(item);
    }
    items = bvos.toArray(new CircularlyAccessibleValueObject[0]);
    try {
      VORowNoUtils.validateRowNo(items, SOItemKey.CROWNO);
    }
    catch (ValidationException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
