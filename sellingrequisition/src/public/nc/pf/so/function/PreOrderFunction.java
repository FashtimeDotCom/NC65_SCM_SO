package nc.pf.so.function;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.function.SOFunctionUtil;

public class PreOrderFunction {

  private BillTransferTool<PreOrderVO> transferTool;

  /**
   * ��������Ԥ���������еľ���/ѯ�����۵����ֵ
   * <p>
   * <b>֧��Ԥ������������Լ������</b>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3...
   * </ul>
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return
   *         <p>
   * @author ��־ΰ
   * @time 2010-10-19 ����01:45:01
   */
  public UFDouble getMaxPriceRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMaxPriceRate = util.getMaxPriceRate(bill);
    return dMaxPriceRate;
  }

  /**
   * ��������Ԥ���������еľ���/ѯ�����۵���Сֵ
   * <p>
   * <b>֧��Ԥ������������Լ������</b>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3...
   * </ul>
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo AggregatedValueObject
   * @return UFDouble
   *         <p>
   * @author ��־ΰ
   * @time 2010-10-19 ����01:45:01
   */
  public UFDouble getMinPriceRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMinPriceRate = util.getMinPriceRate(bill);
    return dMinPriceRate;
  }

  /**
   * �����������޸Ļ�ò�ȫVO
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  private PreOrderVO getFullBill(AggregatedValueObject vo) {
    // ����
    PreOrderVO bill = (PreOrderVO) vo;
    // �޸�
    String strOrderID = bill.getParentVO().getCpreorderid();
    if ((strOrderID != null) && (strOrderID.trim().length() > 0)) {
      bill = this.getClientInfoFullBill(bill);
    }
    return bill;
  }

  private PreOrderVO getClientInfoFullBill(PreOrderVO bill) {
    if (this.transferTool == null) {
      PreOrderVO[] bills = new PreOrderVO[] {
        bill
      };
      this.transferTool = new BillTransferTool<PreOrderVO>(bills);
    }
    return this.transferTool.getClientFullInfoBill()[0];
  }

  /**
   * �����������۾��۲���С�����ϵ���������ۼ�*�ͻ���������������ۼ۱���:
   * ���м�顢�����в����㼴����false
   * <p>
   * <b>֧��Ԥ������������Լ�����ã�</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3...
   * </ul>
   * 
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return UFBoolean
   *         <p>
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  public UFBoolean examSaleNetprice(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    return util.examSaleNetprice(bill);
  }

  /**
   * �������������������ۿ�*��Ʒ�ۿ۵����ֵ
   * <p>
   * <b>֧��Ԥ������������Լ�����ã�</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * 
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return UFDouble
   *         <p>
   * @author ��־ΰ
   * @time 2010-10-23 ����12:49:41
   */
  public UFDouble getMaxDiscountRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble maxDiscountRate = util.getMaxDiscountRate(bill);
    return maxDiscountRate;
  }

  /**
   * �������������������ۿ�*��Ʒ�ۿ۵���Сֵ
   * <p>
   * <b>֧��Ԥ������������Լ�����ã�</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * 
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return UFDouble
   *         <p>
   * @author ��־ΰ
   * @time 2010-10-23 ����12:49:41
   */
  public UFDouble getMinDiscountRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble minDiscountRate = util.getMinDiscountRate(bill);
    return minDiscountRate;
  }
}
