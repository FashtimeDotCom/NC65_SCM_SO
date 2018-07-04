package nc.vo.so.m32.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.vo.so.m32.scale.SaleInvoiceScaleProcessor;


/**
 * @description
 * ���۷�Ʊ����ǰУ�龫��
 * @scene
 * ���۷�Ʊ�������޸ı���ǰ
 * @param
 * ��
 */
public class SaleInvoiceScaleCheckRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    new SaleInvoiceScaleProcessor().checkBillPrecision(vos);
  }

}
