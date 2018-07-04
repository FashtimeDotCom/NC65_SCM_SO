package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.so.m32.maintain.CreateVoucherSaleInvoiceBP;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ���۷�Ʊ��ƾ֤�ű���
 * 
 * @since 6.0
 * @version 2011-3-28 ����11:38:15
 * @author ô��
 */
public class N_32_CREATEVOUCHER extends AbstractCompiler2 {
  /**
   * ���췽��
   */
  public N_32_CREATEVOUCHER() {
    super();
  }

  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  @Override
  public Object runComClass(PfParameterVO paraVo) throws BusinessException {
    super.m_tmpVo = paraVo;
    CreateVoucherSaleInvoiceBP bp = new CreateVoucherSaleInvoiceBP();
    SaleInvoiceVO vo = (SaleInvoiceVO) this.getPfParameterVO().m_preValueVo;
    SaleInvoiceVO[] vos = new SaleInvoiceVO[] {
      vo
    };
    bp.createVoucher(vos);
    // return super.runComClass(paraVo);
    return null;
  }
}
