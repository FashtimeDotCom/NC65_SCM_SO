package nc.vo.so.m30.rule;

import java.util.Map;

import nc.vo.bd.income.IncomeChVO;
import nc.vo.bd.income.IncomeVO;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.payterm.PaytermService;

/**
 * �տ�Э����򡪡������տ�Э�������տ�Э������Ϣ
 * 
 * @since 6.0
 * @version 2011-7-27 ����03:08:46
 * @author ��־ΰ
 */
public class PayTermRule {

  private IKeyValue keyValue;

  /**
   * �˴�Ӧ�������տ������µ��տ�ʱ��ö�٣���Ŀǰnc.vo.uap.bd.income.incomeeffectdate�Ҳ���
   * 
   * 1001Z01000000000E4JX ��������
   * 1001Z01000000000E4JY ����ǩ������
   * 1001Z01000000000E4JZ ���ۿ�Ʊ����
   * 1001Z01000000000E4K0 ���۷�Ʊ�������
   * 1001Z01000000000E4K1 ���۶�������
   * 1001Z01000000000E4K2 ���ۺ�ͬ��Ч����
   */
  private static final String INCOMEEFFDATE_ORDER =
      IPaymentUtil.SALE_ORDER_DATE;// "1001Z01000000000E4K1"

  /**
   * ������
   * 
   * @param keyValue
   */
  public PayTermRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �����տ�Э����ϸ��Ϣ
   */
  public void setPayTermInfo() {

    this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE, null);
    this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVEQUOTA, null);
    this.keyValue.setHeadValue(SaleOrderHVO.BPRECEIVEFLAG, UFBoolean.FALSE);
    // �տ�Э��
    String paytermid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CPAYTERMID);
    if (PubAppTool.isNull(paytermid)) {
      return;
    }

    Map<String, IncomeVO> mapPaytem = null;

    mapPaytem = PaytermService.queryIncomeByPk(new String[] {
      paytermid
    });

    if (null == mapPaytem || mapPaytem.size() == 0) {
      return;
    }
    IncomeVO vo = mapPaytem.get(paytermid);
    IncomeChVO[] chvos = vo.getPaymentch();
    if (null == chvos || chvos.length == 0) {
      return;
    }
    UFDouble accrate = null;
    for (IncomeChVO chvo : chvos) {
      // Ŀǰnc.vo.uap.bd.income.incomeeffectdate�Ҳ���
      if (PayTermRule.INCOMEEFFDATE_ORDER.equals(chvo.getPk_incomeperiod())) {
        UFBoolean prepayment = chvo.getPrepayment();
        // ֻ�е���ѡ��Ԥ�տ�� �Ž������ñ�����Ԥ�տ��־�����㷽ʽ ��modify by wangshu6 20150403
        UFBoolean soHeadPrepayment =
        this.keyValue.getHeadUFBooleanValue(SaleOrderHVO.BPRECEIVEFLAG);
        // ͬʱ�Ѿ����ù�Ԥ�տ�� ֮����п�ʼ���ٽ�������
        if (soHeadPrepayment != null && soHeadPrepayment.booleanValue()) { 
        continue;
        } 
        // δȡ���տ�Э���ϵ�Ԥ�տ��������ȡģ��Ĭ��ֵ
        this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE,
            chvo.getAccrate());
        // Ԥ�տ��־
        this.keyValue.setHeadValue(SaleOrderHVO.BPRECEIVEFLAG, prepayment);
        accrate = chvo.getAccrate();
        // ���㷽ʽ
        this.keyValue.setHeadValue(SaleOrderHVO.CBALANCETYPEID,
            chvo.getPk_balatype());
      }
    }
    this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE, accrate);
    // �����տ��޶�
    new PreceiveQuotaRule(this.keyValue).calculatePreceiveQuoTa();

  }
}
