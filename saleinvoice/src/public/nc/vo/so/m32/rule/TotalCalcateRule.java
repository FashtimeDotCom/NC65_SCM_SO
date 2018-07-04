package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-22 ����08:12:33
 */
public class TotalCalcateRule {
  private IKeyValue keyValue;

  /**
   * HeadTotalCalculator �Ĺ�����
   * 
   * @param keyValue
   */
  public TotalCalcateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �������������� ���ݱ���ֵ�����ͷ�ϼơ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author ��ӱ�
   * @time 2010-4-21 ����09:55:19
   */
  public void calcHeadTotal() {
    UFDouble totalnum = new UFDouble(0);
    UFDouble totalmny = new UFDouble(0);
    UFDouble totalsub = new UFDouble(0);
    int rowcount = this.keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      // ɾ���в�����ϼ�
      if (RowStatus.DELETED == this.keyValue.getRowStatus(i)) {
        continue;
      }
      // ����
      UFDouble astnum =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NASTNUM);

      totalnum = MathTool.add(totalnum, astnum);
      // ��Ʒ��־
      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLARGESSFLAG);
      // ��Ʒ�н����뵽��ͷ�ۼƽ��
      if ((null != largessflag) && largessflag.booleanValue()) {
        continue;
      }
      // ��˰�ϼ�
      UFDouble orgtaxmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
      totalmny = MathTool.add(totalmny, orgtaxmny);
      // ������
      UFDouble orgsubmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGSUBMNY);
      totalsub = MathTool.add(totalsub, orgsubmny);
    }
    this.keyValue.setHeadValue(SaleInvoiceHVO.NTOTALASTNUM, totalnum);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGMNY, totalmny);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, totalsub);
  }
}
