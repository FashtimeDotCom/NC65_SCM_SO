package nc.impl.so.m32.action.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * ���۷�Ʊ����ǰ�� <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ����ʱ����Ƿ���й����Ļ��ܲ���
 * </ul>
 * <p>
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-7-28 ����03:19:51
 */
public class CheckConsumeRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    for (SaleInvoiceVO voInvoice : vos) {
      for (SaleInvoiceBVO bvo : voInvoice.getChildrenVO()) {
        if (!StringUtil.isEmpty(bvo.getCsumid())) {
          ExceptionUtils.wrappBusinessException(nclangres.getStrByID(
              "4006008_0", "04006008-0032")/*@res "��ǰ��Ʊ�ѽ������Ļ��ܲ��������ɽ�������"*/);
        }
      }
    }
  }

}
