package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * ��Ӧ�սű�����
 * 
 * @since 6.0
 * @version 2011-10-28 ����09:17:21
 * @author ô��
 */
public class N_32_ADJUSTINCOME extends AbstractCompiler2 {

  /**
   * N_32_ADJUSTINCOME �Ĺ�����
   */
  public N_32_ADJUSTINCOME() {
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

      RemoteFormSOUtil.squareAdjustIncomeSrv(voInvoices);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
