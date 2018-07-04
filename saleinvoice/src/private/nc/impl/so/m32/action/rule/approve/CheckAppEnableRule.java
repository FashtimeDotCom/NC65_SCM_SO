package nc.impl.so.m32.action.rule.approve;

import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷�Ʊ����ǰ�����Ϸ���У�飨����״̬����������У�飩
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 * @since 6.1
 * @version 2012-12-21 ����09:20:13
 * @author yaogj
 */
public class CheckAppEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    UFDate curdate = AppContext.getInstance().getBusiDate();
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    for (SaleInvoiceVO invoicevo : vos) {
      // ���ɡ�������״̬�����������
      Integer status = invoicevo.getParentVO().getFstatusflag();
      if (!(BillStatus.FREE.equalsValue(status) || BillStatus.AUDITING
          .equalsValue(status))) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0029")/*@res "��ǰ��Ʊ����״̬�����ɽ���������"*/);
      }
      // ��ǰ���ں͵�������У��  beforeDate�Ƚϵ��ǵ�ǰ���ڲ�����ʱ����
      UFDate billdate = invoicevo.getParentVO().getDbilldate();
      if (curdate.beforeDate(billdate)) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0030", null, new String[] {
              curdate.toString(), billdate.toString()
            })/*@res "��Ʊ�������({0})�������ڿ�Ʊ����({1})��"*/);
      }

    }
  }

}
