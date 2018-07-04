package nc.pubimpl.so.pfxx.check;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ����̬���ݼ�����
 * 
 * @since 6.3
 * @version 2013-4-23 ����07:08:41
 * @author tianft
 */
public class BillFreeStatusCheckRule implements IRule<AggregatedValueObject> {

  private String billStatusKey = "fstatusflag";

  private BillStatus[] billStatusValues = {
    BillStatus.FREE, BillStatus.AUDIT
  };

  public BillFreeStatusCheckRule(String billStatusKey,
      BillStatus[] billStatusValues) {
    this.billStatusKey = billStatusKey;
    this.billStatusValues = billStatusValues;
  }

  public BillFreeStatusCheckRule() {
    //
  }

  @Override
  public void process(AggregatedValueObject[] vos) {
    for (AggregatedValueObject bill : vos) {
      int i = 0;
      for (BillStatus billStatusValue : billStatusValues) {
        if (billStatusValue != null
            && billStatusValue.getIntegerValue().equals(
                bill.getParentVO().getAttributeValue(this.billStatusKey))) {
          i++;
        }
      }
      if (i == 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006004_0", "04006004-0228")/* ���ڷ�����̬�ĵ��ݣ� */);
      }
      if (BillStatus.AUDIT.equalsValue(bill.getParentVO().getAttributeValue(
          this.billStatusKey))
          && !BillStatus.FREE.equalsValue(bill.getParentVO().getAttributeValue(
              "fpfstatusflag"))) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006004_0", "04006004-0249")/*����״̬����fstatusflag=2ʱ��fpfstatusflagӦ�õ���1*/);
      }
    }
  }

}
