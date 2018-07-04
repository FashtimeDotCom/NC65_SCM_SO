package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillTotalListener;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * ������棨�б���棩�ϼƴ���ֻ���ѡ����н��кϲ�����νѡ�������ָ��һ�д򹳵���Щ
 * 
 * @since 6.36
 * @version 2015-1-16 ����4:06:52
 * @author ��¼
 */
public class SOSelectedRowTotalListener implements BillTotalListener {

  private BillListPanel listPanel;

  private String viewClassName;

  /**
   * ��ʼ��
   * 
   * @param listPanel �б����
   * @param viewClassName ��ͼVO����
   */
  public SOSelectedRowTotalListener(BillListPanel listPanel,
      String viewClassName) {
    this.listPanel = listPanel;
    this.viewClassName = viewClassName;
  }

  @Override
  public UFDouble calcurateTotal(String key) {
    UFDouble total = UFDouble.ZERO_DBL;
    CircularlyAccessibleValueObject[] svos =
        this.listPanel.getBodyBillModel().getBodySelectedVOs(viewClassName);
    if (ArrayUtil.isEmpty(svos)) {
      return total;
    }

    for (int i = 0; i < svos.length; i++) {
      UFDouble num = (UFDouble) svos[i].getAttributeValue(key);
      total = MathTool.add(total, num);
    }
    return total;
  }
}
