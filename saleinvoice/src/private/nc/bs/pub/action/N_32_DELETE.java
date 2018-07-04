package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.m32.ISaleInvoiceScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��Ʊɾ���ű�����
 * 
 * @since 6.0
 * @version 2011-10-28 ����09:18:04
 * @author ô��
 */
public class N_32_DELETE extends AbstractCompiler2 {
  /**
   * N_32_DELETE �Ĺ�����
   */
  public N_32_DELETE() {
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
    Object retValue = null;
    try {
      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;

      int ilength = inCurObjects.length;
      SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (SaleInvoiceVO) inCurObjects[i];
      }
      ISaleInvoiceScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(ISaleInvoiceScriptMaintain.class);
      maintainsrv.saleInvoiceDelete(voInvoices);
      return retValue;
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
  }
}
