package nc.vo.so.m32.rule;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class SrcBillInfoRule {

  private IKeyValue keyValue;

  /**
   * SrcBillInfoRule �Ĺ�����
   * 
   * @param keyValue
   */
  public SrcBillInfoRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���������������ж�index���Ƿ������Դ���ݡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-23 ����11:12:04
   */
  public boolean isExitSrc(int index) {
    // �ж��Ƿ������Դ����ID
    String srcbid =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CSRCBID);
    return VOChecker.isEmpty(srcbid);
  }
}
