package nc.bs.so.m32.maintain.rule.update;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 
 * @description
 *              ���۷�Ʊ����ǰУ��so33�����Ƿ�����������޸�
 * @scene
 *        ���۷�Ʊ����ǰ
 * @param ��
 * 
 * @since 6.5
 * @version 2015-11-24 ����2:16:26
 * @author zhangby5
 */
public class CheckCanUpdateWhenAuditing implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    boolean so33 = this.getPara(vos[0].getParentVO().getPk_org());
    for (SaleInvoiceVO vo : vos) {
      Integer status = vo.getParentVO().getFstatusflag();
      if (BillStatus.AUDITING.getIntegerValue().equals(status) && !so33) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006008_0", "04006008-0163")/*����ʧ�ܣ�ҵ����֯����so33�������������޸ġ�*/);
      }
    }
  }

  private boolean getPara(String pk_org) {
    UFBoolean so33 = SOSysParaInitUtil.getSO33(pk_org);
    if (null == so33) {
      return false;
    }
    return so33.booleanValue();
  }

}
