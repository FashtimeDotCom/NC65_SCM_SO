package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * �Զ��������ű�
 * 
 * @since 6.0
 * @version 2011-6-21 ����01:21:34
 * @author ô��
 */
public class N_32_SQUAREINCOME extends AbstractCompiler2 {

  /**
   * N_32_SQUAREINCOME �Ĺ�����
   */
  public N_32_SQUAREINCOME() {
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
      Object[] inCurObjects = this.getVos();
      if (inCurObjects == null) {
        inCurObjects = new SaleInvoiceVO[] {
          (SaleInvoiceVO) this.getVo()
        };
      }

      int ilength = inCurObjects.length;
      SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (SaleInvoiceVO) inCurObjects[i];
      }

      RemoteFormSOUtil.autoSquareIncomeSrv(voInvoices);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
