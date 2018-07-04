package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.salequotation.ISalequotationService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�����۵��ı��� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * 
 * �������ڣ�(2010-7-6)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4310_WRITE extends AbstractCompiler2 {
  /**
   * N_4310_SAVEBASE ������ע�⡣
   */
  public N_4310_WRITE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject  "
        + "=(nc.vo.so.salequotation.entity.AggSalequotationHVO[])getVos ();\n"
        + "  Object retValue=nc.bs.framework.common.NCLocator.getInstance()."
        + "lookup(nc.itf.so.salequotation.ISalequotationService.class)."
        + "insert(inObject);\n  return retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      AggSalequotationHVO[] inObject = (AggSalequotationHVO[]) this.getVos();
      ISalequotationService savesrv =
          NCLocator.getInstance().lookup(ISalequotationService.class);
      Object retValue = savesrv.saveBase(inObject);

      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
