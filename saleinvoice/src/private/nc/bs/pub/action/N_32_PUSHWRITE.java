package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ���۷�Ʊ��ʽ����ʵ��
 * 
 * @since 6.0
 * @version 2011-1-26 ����09:18:22
 * @author ��ӱ�
 */
public class N_32_PUSHWRITE extends AbstractCompiler2 {

  /**
   * N_32_PUSHWRITE �Ĺ�����
   */
  public N_32_PUSHWRITE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    super.m_tmpVo = vo;
    try {
      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;
      // if (null == inCurObjects) {
      // ExceptionUtils
      // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      // .getStrByID("4006008_0", "04006008-0011")/*@res
      // "������ϣ����ʽ��������۷�Ʊû������"*/);
      // return null;
      // }
      // if (!(inCurObjects instanceof SaleInvoiceVO[])) {
      //
      // ExceptionUtils
      // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      // .getStrByID("4006008_0", "04006008-0012")/*@res
      // "������ϣ����ʽ��������۷�Ʊ���Ͳ�ƥ��"*/);
      //
      // }

      int ilength = inCurObjects.length;
      SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (SaleInvoiceVO) inCurObjects[i];
      }
      return PfServiceScmUtil.processBatch("WRITE",
          SOBillType.Invoice.getCode(), voInvoices, null, null);
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
  }
}
