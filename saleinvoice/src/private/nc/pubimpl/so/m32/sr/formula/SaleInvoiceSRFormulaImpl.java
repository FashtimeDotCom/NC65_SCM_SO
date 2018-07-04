package nc.pubimpl.so.m32.sr.formula;

import nc.vo.pub.BusinessException;

import nc.pubitf.so.m32.sr.formula.SaleInvoiceFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;
import nc.pubitf.sr.formula.so.saleinvoice.ISaleInvoiceSRFormula;

/**
 * ���۷�Ʊ����ȡ�������ӿ�ʵ����
 * 
 * @since 6.1
 * @version 2012-11-27 16:23:24
 * @author ��ӱ�
 */
public class SaleInvoiceSRFormulaImpl implements ISaleInvoiceSRFormula {

  @Override
  public ISRFormulaSqlMap getMnyFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SaleInvoiceFormulaSqlMap sqlmap =
        new SaleInvoiceFormulaSqlMap("so_saleinvoice_b.norigtaxmny", para);
    return sqlmap;
  }

  @Override
  public ISRFormulaSqlMap getNumFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SaleInvoiceFormulaSqlMap sqlmap =
        new SaleInvoiceFormulaSqlMap("so_saleinvoice_b.nnum", para);
    return sqlmap;
  }

}
